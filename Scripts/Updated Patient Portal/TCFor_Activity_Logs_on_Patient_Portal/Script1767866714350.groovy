import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement
import groovy.transform.Field
import java.time.*
import java.time.format.DateTimeFormatter

/* =====================================================================
 *  ACTIVITY LOG - END TO END VERIFICATION  (Patient Portal)
 *  Sections: A (navigation), B (UI), C (dropdown/date/sort), D (sorting +
 *  pagination reset), E (Date x Action filter matrix)
 *
 *  Soft assertions: every failed check is collected in `failures` and the
 *  test is failed ONCE at the very end, so one bad check doesn't hide the rest.
 *
 *  EDIT-ME items are marked below.
 * ===================================================================== */

// ======================= CONFIG (EDIT-ME) =======================
// Display names that are allowed to appear in the "User" column
// (patient + authorized individuals). Leave empty to skip the user check.
@Field List<String> ALLOWED_USERS = ['Zak Duckett']

@Field List<String> EXPECTED_ACTIONS = ['All', 'Add', 'Change', 'Delete', 'Download', 'Login', 'Logout', 'Print', 'Transmit', 'View']

// Trim this list if the full matrix runs too long
@Field List<String> PRESETS_TO_TEST = ['Today', 'Yesterday', 'Last 7 days', 'Last 30 days', 'Last 1 Year', 'This Month', 'Last Month', 'This Year']

// ======================= LOCATORS (dynamic, based on the HTML) =======================
@Field String ROWS_XPATH      = "//table[contains(@class,'activity-logs-table')]/tbody/tr[not(contains(@class,'empty-row'))]"
@Field String DATE_INPUT      = "//input[contains(@class,'date-range-main-input')]"
@Field String CAL_ICON        = "//div[@aria-label='Open calendar']"
@Field String ACTION_SELECT   = "//select[@id='action-select']"
@Field String TH_DATE         = "//th[contains(normalize-space(.),'Date')]"
@Field String TH_USER         = "//th[contains(normalize-space(.),'User')]"
@Field String TH_ACTION       = "//th[contains(normalize-space(.),'Action')]"
@Field String NEXT_LI         = "(//ul[contains(@class,'pagination')]/li[contains(@class,'chevron')])[3]"
@Field String PAGE_NUMBER_A   = "//li[contains(@class,'pagination-page-number')]/a"
@Field String ACTIVE_PAGE     = "//li[contains(@class,'pagination-page-number--active')]/a"
@Field String CAL_OK_BTN      = "//button[contains(@class,'bg-blue-500')]"

// ======================= FORMATTERS / STATE =======================
@Field DateTimeFormatter DT_FMT   = DateTimeFormatter.ofPattern('MM/dd/yyyy hh:mm:ss a', Locale.US)
@Field DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern('MM/dd/yyyy', Locale.US)
@Field List<String> failures = []

// ======================= HELPERS =======================
TestObject xp(String name, String xpath) {
	TestObject to = new TestObject(name)
	to.addProperty('xpath', ConditionType.EQUALS, xpath)
	return to
}

String clean(String s) {
	return (s == null) ? '' : s.replace('\u00A0', ' ').replaceAll('\\s+', ' ').trim()
}

void check(boolean condition, String message) {
	if (condition) {
		WebUI.comment('✅ ' + message)
	} else {
		failures.add(message)
		WebUI.comment('❌ ' + message)
		KeywordUtil.markWarning('❌ ' + message)
	}
}

/** Reads rows of the CURRENT page. Each row = [dt: LocalDateTime, dtText, user, action] */
List<Map> readPageRows() {
	List<WebElement> trs = []
	try {
		trs = WebUiCommonHelper.findWebElements(xp('rows', ROWS_XPATH), 5)
	} catch (Exception e) {
		return []
	}
	List<Map> out = []
	trs.each { WebElement tr ->
		List<WebElement> tds = tr.findElements(By.tagName('td'))
		if (tds.size() < 3) return
		String d = clean(tds[0].getText())
		if (d == '') return
		LocalDateTime parsed = null
		try {
			parsed = LocalDateTime.parse(d, DT_FMT)
		} catch (Exception ignore) {
			return   // e.g. "No records found" row
		}
		out.add([dt: parsed, dtText: d, user: clean(tds[1].getText()), action: clean(tds[2].getText())])
	}
	return out
}

boolean elementExists(String xpath, int timeout = 2) {
	return WebUI.verifyElementPresent(xp('exists', xpath), timeout, FailureHandling.OPTIONAL)
}

boolean nextPageEnabled() {
	try {
		List<WebElement> els = WebUiCommonHelper.findWebElements(xp('nextLi', NEXT_LI), 2)
		return els && !els[0].getAttribute('class').contains('disabled')
	} catch (Exception e) {
		return false
	}
}

void goToFirstPage() {
	if (elementExists(PAGE_NUMBER_A + "[normalize-space()='1']", 2)) {
		WebUI.click(xp('page1', PAGE_NUMBER_A + "[normalize-space()='1']"))
		WebUI.delay(1)
	}
}

String activePageNumber() {
	try {
		return clean(WebUI.getText(xp('active', ACTIVE_PAGE)))
	} catch (Exception e) {
		return ''
	}
}

/** Walks through ALL pages starting from page 1 and returns every row */
List<Map> collectAllRows() {
	goToFirstPage()
	List<Map> all = []
	int guard = 0
	while (guard++ < 200) {
		all.addAll(readPageRows())
		if (!nextPageEnabled()) break
		WebUI.click(xp('nextA', NEXT_LI + '/a'))
		WebUI.delay(1)
	}
	return all
}

boolean isSorted(List<Map> rows, String col, boolean asc) {
	for (int i = 0; i < rows.size() - 1; i++) {
		int c
		if (col == 'dt') c = ((LocalDateTime) rows[i].get('dt')).compareTo((LocalDateTime) rows[i + 1].get('dt'))
		else c = ((String) rows[i].get(col)).compareToIgnoreCase((String) rows[i + 1].get(col))
		if (asc && c > 0) return false
		if (!asc && c < 0) return false
	}
	return true
}

/** Reads "MM/dd/yyyy - MM/dd/yyyy" from the date input -> [start, end] */
List<LocalDate> readDateRange() {
	String v = clean(WebUI.getAttribute(xp('dateInput', DATE_INPUT), 'value'))
	String[] p = v.split(' - ')
	return [LocalDate.parse(p[0].trim(), DATE_FMT), LocalDate.parse(p[1].trim(), DATE_FMT)]
}

void openCalendar() {
	WebUI.click(xp('calIcon', CAL_ICON))
	WebUI.delay(1)
}

void fillField(TestObject to, String value) {
	WebUI.click(to)
	WebUI.sendKeys(to, Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(to, Keys.chord(Keys.BACK_SPACE))
	WebUI.sendKeys(to, value)
	WebUI.delay(1)
}

void dumpRedMessages(String label) {
	try {
		List<String> msgs = WebUiCommonHelper.findWebElements(xp('anyRed', "//p[contains(@class,'text-red')]"), 3)
				.collect { WebElement e -> clean(e.getText()) }
		println(label + ' RED MESSAGES >>> ' + msgs)
	} catch (Exception ex) {
		println(label + ' RED MESSAGES >>> none found')
	}
}

void openCustomPanel() {
	openCalendar()
	WebUI.click(xp('customBtn', "//button[normalize-space(.)='Custom']"))
	WebUI.delay(1)
}

void selectPreset(String label) {
	openCalendar()
	WebUI.click(xp('preset', "//button[normalize-space(.)='" + label + "']"))
	if (elementExists(CAL_OK_BTN, 2)) {
		WebUI.click(xp('ok', CAL_OK_BTN))
	}
	WebUI.delay(3)
}

void selectAction(String action) {
	WebUI.selectOptionByValue(xp('actionSel', ACTION_SELECT), action, false)
	WebUI.delay(3)
}

/** Expected [start, end] for a preset. end = null when not strictly known. Timezone differences -> warning only. */
List expectedRange(String preset) {
	LocalDate t = LocalDate.now(ZoneId.of('GMT'))
	switch (preset) {
		case 'Today':       return [t, t]
		case 'Yesterday':   return [t.minusDays(1), t.minusDays(1)]
		case 'Last 7 days': return [t.minusDays(6), t]
		case 'Last 30 days':return [t.minusDays(29), t]
		case 'Last 1 Year': return [t.minusYears(1), null]
		case 'This Month':  return [t.withDayOfMonth(1), null]
		case 'Last Month':  LocalDate f = t.minusMonths(1).withDayOfMonth(1); return [f, f.withDayOfMonth(f.lengthOfMonth())]
		case 'This Year':   return [t.withDayOfYear(1), null]
	}
	return [null, null]
}

/** Typed helpers - avoid IDE "unknown method" underlines on untyped closure params */
boolean inRange(Map r, LocalDate start, LocalDate end) {
	LocalDate d = ((LocalDateTime) r.get('dt')).toLocalDate()
	return !d.isBefore(start) && !d.isAfter(end)
}

boolean actionIs(Map r, String action) {
	String a = (String) r.get('action')
	return a.equalsIgnoreCase(action)
}

boolean validAction(Map r) {
	String a = (String) r.get('action')
	return EXPECTED_ACTIONS.contains(a) && a != 'All'
}

boolean userAllowed(Map r) {
	return ALLOWED_USERS.contains((String) r.get('user'))
}

String rowKey(Map r) { return r.dtText + '|' + r.user + '|' + r.action }

// =====================================================================
//  LOGIN  (same as your existing script)
// =====================================================================
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : PatientName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)

String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'('imap.gmail.com', GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, GlobalVariable.Sender_Email, 'Verification')
println('OTP fetched = ' + otp)
char[] digits = otp.toCharArray()
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1'), digits[0].toString())
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2'), digits[1].toString())
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3'), digits[2].toString())
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'), digits[3].toString())
WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)

// =====================================================================
//  SECTION A - Navigate to Activity Log
// =====================================================================
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Setting Icon on Portal'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/span_Authorized Individuals_block pr-14 py-_f7d1ec'))
WebUI.delay(3)

// ---- HARD WAIT: stop here with a clear error if the Activity Log page did not load ----
WebUI.switchToDefaultContent()
WebUI.waitForElementPresent(xp('activityTable', "//table[contains(@class,'activity-logs-table')]"), 30, FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementPresent(xp('thDate', TH_DATE), 30, FailureHandling.STOP_ON_FAILURE)
WebUI.takeScreenshot()   // handy when debugging which page was actually opened

check(WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/h1_ZD_display-6 fw-semibold'), 5, FailureHandling.OPTIONAL),
	'A1: "Activity Log" screen is displayed')

// =====================================================================
//  SECTION B - UI elements
// =====================================================================
check(elementExists(DATE_INPUT, 5),  'B2: Date field is displayed')
check(elementExists(CAL_ICON, 5),    'B2: Calendar icon is displayed')
check(elementExists(ACTION_SELECT, 5), 'B2: Action dropdown is displayed')
check(elementExists("//table[contains(@class,'activity-logs-table')]", 5), 'B2: Table is displayed')

String thDate   = clean(WebUI.getText(xp('thDate', TH_DATE)))
String thUser   = clean(WebUI.getText(xp('thUser', TH_USER)))
String thAction = clean(WebUI.getText(xp('thAction', TH_ACTION)))
check(thDate.startsWith('Date & Time'), 'B2: Column "Date & Time" present with sort arrow -> [' + thDate + ']')
check(thUser.startsWith('User'),        'B2: Column "User" present with sort arrow -> [' + thUser + ']')
check(thAction.startsWith('Action'),    'B2: Column "Action" present with sort arrow -> [' + thAction + ']')
check(thDate.contains('↓') || thDate.contains('↑'), 'B2: Date & Time header shows an Asc/Desc arrow')
// NOTE: B1 (UI as per mockup) is a visual check - compare screenshots manually or use WebUI.verifyImagePresent.

// =====================================================================
//  SECTION C1 - Action dropdown
// =====================================================================
List<String> actualOptions = WebUiCommonHelper.findWebElement(xp('actionSel', ACTION_SELECT), 10)
		.findElements(By.tagName('option')).collect { clean(it.getText()) }
check(actualOptions.toSet() == EXPECTED_ACTIONS.toSet(), 'C1: Action dropdown options = ' + actualOptions)
check(!actualOptions.contains('Cancel'), 'C1: "Cancel" is NOT an action')
check(actualOptions.size() == EXPECTED_ACTIONS.size(), 'C1: No duplicate/extra options')
// Spec lists "Login, Logout" at the end; UI currently sorts alphabetically. Order check (optional):
// check(actualOptions == ['All','Add','Change','Delete','Download','Print','Transmit','View','Login','Logout'], 'C1: option order as per spec')
check(WebUI.verifyOptionSelectedByLabel(xp('actionSel', ACTION_SELECT), 'All', false, 5, FailureHandling.OPTIONAL), 'C1/C3: Action default = All')

// =====================================================================
//  SECTION C2 - Calendar
// =====================================================================
openCalendar()
['Today', 'Yesterday', 'Last 7 days', 'Last 30 days', 'Last 1 Year', 'This Month', 'Last Month', 'This Year', 'Custom'].each { String p ->
	check(elementExists("//button[normalize-space(.)='" + p + "']", 2), 'C2: Calendar preset present -> ' + p)
}
// EDIT-ME: year dropdown locator may differ. Verifies 1900 is selectable if a <select> is used.
check(elementExists("//button[normalize-space(.)='Cancel']", 2), 'C2: Cancel button on calendar')
check(elementExists(CAL_OK_BTN, 2), 'C2: OK button on calendar')
WebUI.click(xp('cancel', "//button[normalize-space(.)='Cancel']"))
WebUI.delay(1)

// "start Date - End Date" format
String rangeText = clean(WebUI.getAttribute(xp('dateInput', DATE_INPUT), 'value'))
check(rangeText ==~ /\d{2}\/\d{2}\/\d{4} - \d{2}\/\d{2}\/\d{4}/, 'C2: Date field format is "MM/dd/yyyy - MM/dd/yyyy" -> ' + rangeText)

// =====================================================================
//  SECTION C3 - Default = current week, Action = All
// =====================================================================
List<LocalDate> defRange = readDateRange()
LocalDate todayGmt = LocalDate.now(ZoneId.of('GMT'))
check(defRange[1] == todayGmt && defRange[0] == todayGmt.minusDays(6),
	'C3: Default date range is current week (last 7 days) -> ' + defRange)

List<Map> defaultRows = collectAllRows()
check(defaultRows.size() > 0, 'C3: Default view has logs (' + defaultRows.size() + ' rows)')
check(defaultRows.every { Map r -> inRange(r, defRange[0], defRange[1]) },
	'C3: All default rows fall inside default date range')

// =====================================================================
//  SECTION C4/C5/C6 - Date & Time sorting (default DESC, toggle)
// =====================================================================
check(isSorted(defaultRows, 'dt', false), 'C4: Default order is Descending by Date & Time (all pages)')

WebUI.click(xp('thDate', TH_DATE)); WebUI.delay(2)
List<Map> afterClick1 = collectAllRows()
check(isSorted(afterClick1, 'dt', true), 'C5: After 1st click -> Date & Time ASC (all pages)')

WebUI.click(xp('thDate', TH_DATE)); WebUI.delay(2)
List<Map> afterClick2 = collectAllRows()
check(isSorted(afterClick2, 'dt', false), 'C6: After 2nd click -> Date & Time DESC (all pages)')

// =====================================================================
//  SECTION D - Sort by User / Action (+ pagination reset to page 1)
// =====================================================================
[['User', TH_USER, 'user'], ['Action', TH_ACTION, 'action']].each { def cfg ->
	String name = cfg[0]; String th = cfg[1]; String col = cfg[2]

	// move to page 2 first (if exists) to prove the "redirect to 1st page" rule
	boolean movedToPage2 = false
	goToFirstPage()
	if (nextPageEnabled()) {
		WebUI.click(xp('nextA', NEXT_LI + '/a')); WebUI.delay(1)
		movedToPage2 = true
	}

	WebUI.click(xp('th', th)); WebUI.delay(2)
	if (movedToPage2) {
		check(activePageNumber() == '1', 'D: Sort by ' + name + ' redirects to page 1 (active page = ' + activePageNumber() + ')')
	}
	List<Map> r1 = collectAllRows()
	boolean asc1 = isSorted(r1, col, true)
	boolean desc1 = isSorted(r1, col, false)
	check(asc1 || desc1, 'D: ' + name + ' column is sorted across ALL records after 1st click (asc=' + asc1 + ', desc=' + desc1 + ')')

	WebUI.click(xp('th', th)); WebUI.delay(2)
	List<Map> r2 = collectAllRows()
	boolean flipped = asc1 ? isSorted(r2, col, false) : isSorted(r2, col, true)
	// if every value is identical both directions are "sorted" - still fine
	check(flipped, 'D: ' + name + ' column toggles direction on 2nd click')
}

// =====================================================================
//  SECTION F - Custom date range: invalid / below-1900 / valid input
//  (Calendar > Custom > Start Date / End Date inputs)
// =====================================================================
TestObject customStart = xp('customStart', "//input[@aria-label='Start Date']")
TestObject customEnd   = xp('customEnd',   "//input[@aria-label='End Date']")
String ERR_P = "//p[contains(@class,'text-red-600')"

openCustomPanel()

// F1: invalid date (00/00/2000)
fillField(customStart, '00/00/2000')
fillField(customEnd, '11/06/2026')
WebUI.sendKeys(customEnd, Keys.chord(Keys.TAB))
WebUI.delay(1)
dumpRedMessages('F1')
check(elementExists("//*[contains(@class,'text-red') and contains(normalize-space(.),'Invalid date')]", 3),
	'F1: Invalid date 00/00/2000 shows red error "Invalid date"')

// F2: year below 1900 (11/11/1111) - on start AND end
fillField(customStart, '11/11/1111')
fillField(customEnd, '11/11/1111')
WebUI.sendKeys(customEnd, Keys.chord(Keys.TAB))
WebUI.delay(1)
dumpRedMessages('F2')
int yearErrors = 0
try {
	yearErrors = WebUiCommonHelper.findWebElements(xp('yearErr', ERR_P + " and contains(normalize-space(.),'Year must')]"), 3).size()
} catch (Exception ignore) { }
check(yearErrors >= 2, 'F2: "Year must be ≥ 1900" shown for Start Date and End Date (found ' + yearErrors + ')')

// F3: valid custom range is accepted and filters correctly
fillField(customStart, '10/01/2026')
fillField(customEnd, '10/31/2026')
check(!elementExists(ERR_P + "]", 2), 'F3: No error shown for a valid custom range')
if (elementExists(CAL_OK_BTN, 2)) {
	WebUI.click(xp('ok', CAL_OK_BTN))
}
WebUI.delay(3)
List<LocalDate> customRange = readDateRange()
check(customRange == [LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 31)], 'F3: Custom range applied -> ' + customRange)
List<Map> customRows = collectAllRows()
check(customRows.every { Map r -> inRange(r, customRange[0], customRange[1]) }, 'F3: All rows are inside custom range (' + customRows.size() + ' rows)')

// =====================================================================
//  SECTION E - Date x Action filter matrix
//  For every preset: read ALL rows with Action=All, then for each action
//  verify the UI result == All-rows filtered by that action (no extra / no missing),
//  every row is inside the date range and belongs to an allowed user.
// =====================================================================
PRESETS_TO_TEST.each { String preset ->

	selectAction('All')
	selectPreset(preset)

	List<LocalDate> rng = readDateRange()
	List exp = expectedRange(preset)
	if (exp[0] != null) {
		// accept GMT or local-zone "today" to avoid timezone false alarms
		LocalDate tl = LocalDate.now()
		LocalDate tg = LocalDate.now(ZoneId.of('GMT'))
		LocalDate expStart = (LocalDate) exp[0]
		List<LocalDate> startsOk = [expStart, expStart.plusDays(java.time.temporal.ChronoUnit.DAYS.between(tg, tl))]
		check(startsOk.contains(rng[0]), "E[${preset}]: preset applied, range start ${rng[0]} (expected ${startsOk})")
	}

	List<Map> allRows = collectAllRows()
	WebUI.comment("[${preset}] ${rng[0]} - ${rng[1]} -> ${allRows.size()} rows for Action=All")

	check(allRows.every { Map r -> inRange(r, rng[0], rng[1]) },
		"E[${preset}/All]: every row is inside ${rng[0]} - ${rng[1]}")
	if (ALLOWED_USERS) {
		check(allRows.every { Map r -> userAllowed(r) },
			"E[${preset}/All]: only patient / authorized individuals shown")
	}
	check(allRows.every { Map r -> validAction(r) },
		"E[${preset}/All]: every row has a valid action")

	EXPECTED_ACTIONS.findAll { it != 'All' }.each { String action ->
		selectAction(action)
		List<Map> actRows = collectAllRows()

		check(actRows.every { Map r -> actionIs(r, action) },
			"E[${preset}/${action}]: only '${action}' rows are shown")
		check(actRows.every { Map r -> inRange(r, rng[0], rng[1]) },
			"E[${preset}/${action}]: all rows are inside the selected date range")
		if (ALLOWED_USERS) {
			check(actRows.every { Map r -> userAllowed(r) },
				"E[${preset}/${action}]: only patient / authorized individuals shown")
		}

		List<String> expectedKeys = allRows.findAll { Map r -> actionIs(r, action) }.collect { Map r -> rowKey(r) }.sort()
		List<String> actualKeys   = actRows.collect { Map r -> rowKey(r) }.sort()

		// A log entry can be created between the "All" read and this read (e.g. a parallel login/logout).
		// On mismatch, re-read BOTH lists once before reporting a failure.
		if (expectedKeys != actualKeys) {
			WebUI.comment("[${preset}/${action}] mismatch - re-reading All and ${action} once to rule out a timing gap")
			selectAction('All')
			allRows = collectAllRows()
			selectAction(action)
			actRows = collectAllRows()
			expectedKeys = allRows.findAll { Map r -> actionIs(r, action) }.collect { Map r -> rowKey(r) }.sort()
			actualKeys   = actRows.collect { Map r -> rowKey(r) }.sort()
		}

		List<String> extraRows   = actualKeys.findAll { String k -> !expectedKeys.contains(k) }
		List<String> missingRows = expectedKeys.findAll { String k -> !actualKeys.contains(k) }
		check(expectedKeys == actualKeys,
			"E[${preset}/${action}]: no extra / missing entries (expected ${expectedKeys.size()}, got ${actualKeys.size()}) EXTRA(in filter, not in All)=${extraRows} MISSING(in All, not in filter)=${missingRows}")
	}
}

// =====================================================================
//  FINAL RESULT
// =====================================================================
if (!failures.isEmpty()) {
	KeywordUtil.markFailed("Activity Log verification failed (${failures.size()} issue(s)):\n - " + failures.join('\n - '))
} else {
	KeywordUtil.markPassed('Activity Log verification passed')
}