import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Keys

/*
 * Demographics - Name / Email / Address field validations
 * All checks are "soft": failures are collected and reported once at the end.
 */

// ------------------------------ CONFIG ------------------------------
int WAIT = 10

// Zip auto-suggest items. Each <li> text format: "zip, city, state, country" e.g. "92277, 29 Palms, CA, USA"
String ZIP_SUGGESTION_XPATH = "//ul[contains(@class,'absolute') and contains(@class,'z-10') and contains(@class,'overflow-auto')]/li"

// Set true if your app only shows validation errors after clicking "Save Changes"
boolean VALIDATE_ON_SAVE = false

// Expected State values (must match MaximEyes > Patient Demographics > Address)
List<String> EXPECTED_STATES = ['AA','AE','AK','AL','AP','AR','AS','AZ','CA','CO','CT','DC','DE','FL','FM','GA','GU',
	'HI','IA','ID','IL','IN','KS','KY','LA','MA','MD','ME','MH','MI','MN','MO','MP','MS','MT','NC','ND','NE','NH',
	'NJ','NM','NV','NY','OH','OK','OR','PA','PR','PW','RI','SC','SD','TN','TX','UT','VA','VI','VT','WA','WI','WV','WY']

List<String> failures = []

// ------------------------------ HELPERS ------------------------------
TestObject byId(String id) {
	TestObject to = new TestObject(id)
	to.addProperty('xpath', ConditionType.EQUALS, "//*[@id='${id}']")
	return to
}

TestObject byXpath(String name, String xp) {
	TestObject to = new TestObject(name)
	to.addProperty('xpath', ConditionType.EQUALS, xp)
	return to
}

void check(List failures, boolean ok, String msg) {
	if (ok) {
		KeywordUtil.logInfo("PASS - " + msg)
	} else {
		KeywordUtil.markWarning("FAIL - " + msg)
		failures << msg
	}
}

void logTime(String label, long start) {
	KeywordUtil.logInfo("TIME - ${label}: ${(System.currentTimeMillis() - start) / 1000} s")
}

String valueOf(TestObject to) {
	return WebUI.getAttribute(to, 'value') ?: ''
}

// Reliable clear: select-all + delete, then JS fallback for React-controlled inputs
void clearField(TestObject to) {
	WebUI.waitForElementVisible(to, 10)
	WebUI.click(to)
	WebUI.sendKeys(to, Keys.chord(Keys.CONTROL, 'a'))
	WebUI.sendKeys(to, Keys.chord(Keys.BACK_SPACE))
	if (valueOf(to) != '') {
		WebUI.executeJavaScript('''
			var el = arguments[0];
			var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;
			setter.call(el, '');
			el.dispatchEvent(new Event('input', {bubbles: true}));
			el.dispatchEvent(new Event('change', {bubbles: true}));
		''', Arrays.asList(WebUI.findWebElement(to, 5)))
	}
	if (valueOf(to) != '') {
		KeywordUtil.markWarning("Could not clear field ${to.getObjectId()}, value is '${valueOf(to)}'")
	}
}

// Clear first, then type real keystrokes so the browser's maxlength is honoured
void typeInto(TestObject to, String text) {
	clearField(to)
	if (text != null && text.length() > 0) {
		WebUI.sendKeys(to, text)
	}
}

// Text of the inline validation message that belongs to an input (single JS call, no waiting).
// App shows errors as <p class="demographics-field-error">, so we look at the smallest wrapper that
// contains only this one field, which avoids picking up another field's error.
String errorOf(String id) {
	def r = WebUI.executeJavaScript('''
		var el = document.getElementById(arguments[0]);
		if (!el) return '';
		var c = el.parentElement, best = c;
		while (c && c.querySelectorAll('input, select').length <= 1) { best = c; c = c.parentElement; }
		if (!best) return '';
		var nodes = best.querySelectorAll('.demographics-field-error, .validation-position');
		for (var i = 0; i < nodes.length; i++) {
			var t = nodes[i].innerText.trim();
			if (t) return t;
		}
		return '';
	''', Arrays.asList(id))
	return (r ?: '').toString()
}

int countByXpath(String xp) {
	def r = WebUI.executeJavaScript('''
		return document.evaluate(arguments[0], document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null).snapshotLength;
	''', Arrays.asList(xp))
	return (r ?: 0) as int
}

// Reads the whole suggestion list in ONE JS call (the list has hundreds of rows, never loop with WebUI per item)
Map<String, Object> suggestionStats(String xp, String prefix) {
	return (Map<String, Object>) WebUI.executeJavaScript('''
		var xp = arguments[0], pre = arguments[1];
		var s = document.evaluate(xp, document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
		var bad = 0, firstBad = '', firstGood = '';
		for (var i = 0; i < s.snapshotLength; i++) {
			var t = s.snapshotItem(i).innerText.trim();
			if (t.indexOf(pre) === 0) { if (!firstGood) firstGood = t; }
			else { bad++; if (!firstBad) firstBad = t; }
		}
		return {total: s.snapshotLength, badCount: bad, firstBad: firstBad, firstGood: firstGood};
	''', Arrays.asList(xp, prefix))
}

// ---- max length: type (limit + 10) chars, value must be exactly limit chars
void verifyMaxLength(List failures, String id, String label, int limit, String chars = 'A') {
	TestObject to = byId(id)
	String longText = (chars * (limit + 10)).substring(0, limit + 10)
	typeInto(to, longText)
	int actual = valueOf(to).length()
	check(failures, actual == limit, "${label}: accepted ${actual} chars, expected max ${limit}")
	String attr = WebUI.getAttribute(to, 'maxlength')
	check(failures, attr == String.valueOf(limit), "${label}: maxlength attribute is '${attr}', expected '${limit}'")
}

// ---- exact-limit value is accepted in full
void verifyExactLimit(List failures, String id, String label, int limit, String chars = 'B') {
	TestObject to = byId(id)
	String text = (chars * limit).substring(0, limit)
	typeInto(to, text)
	check(failures, valueOf(to) == text, "${label}: exactly ${limit} chars should be accepted in full, got ${valueOf(to).length()}")
}

// ---- disallowed special characters must be stripped or flagged
void verifyNoSpecialChars(List failures, String id, String label) {
	TestObject to = byId(id)
	typeInto(to, 'John@#$%^&*()!<>?Doe')
	WebUI.sendKeys(to, Keys.chord(Keys.TAB))
	String val = valueOf(to)
	boolean stripped = !(val =~ /[^A-Za-z\s]/).find()
	boolean errored  = errorOf(id) != ''
	check(failures, stripped || errored, "${label}: special characters must be blocked/flagged, value was '${val}'")
}

// ---- allowed special characters retained
void verifySpecialCharsAllowed(List failures, String id, String label, String allowed) {
	TestObject to = byId(id)
	String text = "12 Main St " + allowed
	typeInto(to, text)
	check(failures, valueOf(to) == text, "${label}: allowed special chars '${allowed}' should be retained, value was '${valueOf(to)}'")
}

// ---- email check
void verifyEmail(List failures, String value, boolean shouldBeValid, boolean validateOnSave) {
	TestObject to = byId('primaryEmail')
	typeInto(to, value)
	WebUI.sendKeys(to, Keys.chord(Keys.TAB))
	WebUI.delay(1)
	boolean hasError = errorOf('primaryEmail') != ''
	if (!hasError && validateOnSave && !shouldBeValid) {
		WebUI.click(byXpath('saveBtn', "//button[@type='submit']"))
		WebUI.delay(1)
		hasError = errorOf('primaryEmail') != ''
	}
	check(failures, hasError != shouldBeValid,
		"Email '${value}' expected ${shouldBeValid ? 'VALID (no error)' : 'INVALID (error shown)'}")
}

void loginAndOpenDemographics() {
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Dsmith Portal Login'), [:], FailureHandling.STOP_ON_FAILURE)
	// Setting icon on pt portal dashboard
	WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))
	// click on Update Demographics option
	WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Profile_block pr-14 py-2 font-normal t_ea6dd6'))
}

// ------------------------------ TEST STEPS ------------------------------
long t0 = System.currentTimeMillis()
long ts

try {
	loginAndOpenDemographics()
	WebUI.waitForElementVisible(byId('firstName'), WAIT)
	logTime('login + open page', t0)

	// ===================== NAME =====================
	ts = System.currentTimeMillis()
	KeywordUtil.logInfo('=== NAME VALIDATIONS ===')
	verifyMaxLength(failures, 'firstName',  'First Name',  35)
	verifyMaxLength(failures, 'middleName', 'Middle Name', 25)
	verifyMaxLength(failures, 'lastName',   'Last Name',   60)

	verifyExactLimit(failures, 'firstName',  'First Name',  35)
	verifyExactLimit(failures, 'middleName', 'Middle Name', 25)
	verifyExactLimit(failures, 'lastName',   'Last Name',   60)

	verifyNoSpecialChars(failures, 'firstName',  'First Name')
	verifyNoSpecialChars(failures, 'middleName', 'Middle Name')
	verifyNoSpecialChars(failures, 'lastName',   'Last Name')
	logTime('name', ts)

	// ===================== PREFERRED FIRST NAME =====================
	ts = System.currentTimeMillis()
	KeywordUtil.logInfo('=== PREFERRED FIRST NAME ===')
	// Auto-populate from First Name
	typeInto(byId('firstName'), 'Michael')
	WebUI.sendKeys(byId('firstName'), Keys.chord(Keys.TAB))
	WebUI.delay(1)
	check(failures, valueOf(byId('preferredFirstName')) == 'Michael',
		"Preferred First Name should auto-populate from First Name, was '${valueOf(byId('preferredFirstName'))}'")

	// Editable
	typeInto(byId('preferredFirstName'), 'Mike')
	check(failures, valueOf(byId('preferredFirstName')) == 'Mike', 'Preferred First Name should be editable')

	// Limit = 35 (DOM currently has maxlength=30 -> expected to flag a defect)
	verifyMaxLength(failures, 'preferredFirstName', 'Preferred First Name', 35)

	// Special chars allowed
	String pfSpecial = "Mike-O'Neil_#1"
	typeInto(byId('preferredFirstName'), pfSpecial)
	check(failures, valueOf(byId('preferredFirstName')) == pfSpecial,
		"Preferred First Name should allow special characters, was '${valueOf(byId('preferredFirstName'))}'")
	logTime('preferred first name', ts)

	// ===================== EMAIL =====================
	ts = System.currentTimeMillis()
	KeywordUtil.logInfo('=== EMAIL VALIDATIONS ===')
	['abc@test.com', 'ABC.xyz@Test.com', 'user123@test.co', "a!#\$%&'*+-/=?^_`{|}~b@test.com",
	 'first.last@test.com'].each { verifyEmail(failures, it, true, VALIDATE_ON_SAVE) }
	['.abc@test.com', 'abc.@test.com', 'ab..c@test.com', 'abc@@test.com', 'a@b@test.com',
	 'abc.test.com', 'abc@', '@test.com', 'abc one@test.com', 'abc@test.com, xyz@test.com'
	].each { verifyEmail(failures, it, false, VALIDATE_ON_SAVE) }
	logTime('email', ts)

	// ===================== ADDRESS =====================
	ts = System.currentTimeMillis()
	KeywordUtil.logInfo('=== ADDRESS VALIDATIONS ===')
	verifyMaxLength(failures, 'address1-0', 'Address Line 1', 55)
	verifyMaxLength(failures, 'address2-0', 'Address Line 2', 55)
	verifyExactLimit(failures, 'address1-0', 'Address Line 1', 55)
	verifyExactLimit(failures, 'address2-0', 'Address Line 2', 55)

	// Allowed: # , . / \   (spec shows "/l" - assumed to be "/\")
	verifySpecialCharsAllowed(failures, 'address1-0', 'Address Line 1', '#,./\\')
	verifySpecialCharsAllowed(failures, 'address2-0', 'Address Line 2', '#,./\\')

	// Disallowed specials (assumption: anything outside the allowed list is rejected)
	['address1-0', 'address2-0'].each { id ->
		typeInto(byId(id), 'Main@St')
		check(failures, !valueOf(byId(id)).contains('@'), "${id}: '@' is not in allowed list and should be rejected")
	}

	// City
	verifyMaxLength(failures, 'city-0', 'City', 255)

	// Zip Code (20 numeric) and Zip Extension (4 numeric)
	verifyMaxLength(failures, 'zip-0', 'Zip Code', 20, '1')
	verifyMaxLength(failures, 'address3-0', 'Zip Code Extension', 4, '1')

	typeInto(byId('zip-0'), 'abc!@#')
	check(failures, valueOf(byId('zip-0')) == '', "Zip Code must accept numerics only, was '${valueOf(byId('zip-0'))}'")
	typeInto(byId('address3-0'), 'ab-#')
	check(failures, valueOf(byId('address3-0')) == '', "Zip Extension must accept numerics only, was '${valueOf(byId('address3-0'))}'")
	logTime('address', ts)

	// ===================== STATE DROPDOWN SYNC =====================
	ts = System.currentTimeMillis()
	KeywordUtil.logInfo('=== STATE DROPDOWN ===')
	List<String> actualStates = (List<String>) WebUI.executeJavaScript(
		"return Array.from(document.querySelectorAll('#state-0 option')).map(function(o){return o.value;}).filter(function(v){return v;});", null)
	check(failures, actualStates == EXPECTED_STATES,
		"State dropdown out of sync with MaximEyes. Missing: ${EXPECTED_STATES - actualStates}, Extra: ${actualStates - EXPECTED_STATES}")
	logTime('state dropdown', ts)

	// ===================== ZIP AUTO-POPULATE =====================
	ts = System.currentTimeMillis()
	KeywordUtil.logInfo('=== ZIP AUTO-POPULATE ===')
	// --- clear everything first, then verify it is really empty
	clearField(byId('zip-0'))
	clearField(byId('address3-0'))
	clearField(byId('city-0'))
	WebUI.selectOptionByValue(byId('state-0'), '', false)
	WebUI.selectOptionByValue(byId('country-0'), 'USA', false)
	check(failures, valueOf(byId('zip-0')) == '' && valueOf(byId('city-0')) == '' && WebUI.getAttribute(byId('state-0'), 'value') == '',
		'Zip, City and State must be empty before the auto-populate check')

	// --- type first few digits and poll (max 5 s) for suggestions
	String typed = '902'
	WebUI.sendKeys(byId('zip-0'), typed)
	boolean suggestShown = false
	for (int i = 0; i < 10 && !suggestShown; i++) {
		suggestShown = countByXpath(ZIP_SUGGESTION_XPATH) > 0
		if (!suggestShown) WebUI.delay(0.5)
	}
	check(failures, suggestShown, 'Zip Code auto-suggest list should appear after entering first few digits')

	if (suggestShown) {
		Map<String, Object> st = suggestionStats(ZIP_SUGGESTION_XPATH, typed)
		int stTotal = st.total as int
		int stBad = st.badCount as int
		String stFirstBad = (st.firstBad ?: '') as String
		String stFirstGood = (st.firstGood ?: '') as String

		KeywordUtil.logInfo("Zip suggestions for '${typed}': total=${stTotal}, not starting with '${typed}'=${stBad}")
		check(failures, stBad == 0,
			"All zip suggestions should start with the typed digits '${typed}', but ${stBad} of ${stTotal} do not (e.g. '${stFirstBad}')")

		if (stFirstGood) {
			// format: "zip, city, state, country"
			List<String> parts = stFirstGood.split(',').collect { it.trim() }
			String expZip = parts[0], expState = parts[-2], expCountry = parts[-1]
			String expCity = parts[1..-3].join(', ')
			WebUI.click(byXpath('firstSuggestion', "(${ZIP_SUGGESTION_XPATH})[starts-with(normalize-space(.),'${typed}')][1]"))
			WebUI.delay(1)
			check(failures, valueOf(byId('zip-0')) == expZip, "Zip should be '${expZip}' after selecting '${stFirstGood}', was '${valueOf(byId('zip-0'))}'")
			check(failures, valueOf(byId('city-0')).equalsIgnoreCase(expCity), "City should be '${expCity}', was '${valueOf(byId('city-0'))}'")
			check(failures, WebUI.getAttribute(byId('state-0'), 'value') == expState, "State should be '${expState}', was '${WebUI.getAttribute(byId('state-0'), 'value')}'")
			check(failures, WebUI.getAttribute(byId('country-0'), 'value') == expCountry, "Country should be '${expCountry}', was '${WebUI.getAttribute(byId('country-0'), 'value')}'")
		} else {
			check(failures, false, "No zip suggestion starts with '${typed}', cannot test selection")
		}
	}

	// --- suggestions must follow the selected country: clear, switch country, type again
	clearField(byId('zip-0'))
	clearField(byId('city-0'))
	WebUI.selectOptionByValue(byId('state-0'), '', false)
	WebUI.selectOptionByValue(byId('country-0'), 'CAN', false)
	WebUI.sendKeys(byId('zip-0'), 'K1A')
	WebUI.delay(2)
	int canCount = countByXpath(ZIP_SUGGESTION_XPATH)
	if (canCount > 0) {
		Map<String, Object> stCan = suggestionStats(ZIP_SUGGESTION_XPATH, 'K1A')
		int canTotal = stCan.total as int
		int canBad = stCan.badCount as int
		String canFirstBad = (stCan.firstBad ?: '') as String
		String canFirst = (stCan.firstGood ?: '') as String

		check(failures, canBad == 0, "CAN suggestions should start with 'K1A', ${canBad} of ${canTotal} do not (e.g. '${canFirstBad}')")
		check(failures, canFirst == '' || !canFirst.endsWith('USA'), "CAN selected but suggestion is a USA entry: '${canFirst}'")
	} else {
		KeywordUtil.markWarning("No CAN suggestions for 'K1A' - verify with MaximEyes data")
	}
	logTime('zip auto-populate', ts)

} catch (Exception e) {
	failures << "Unexpected error: ${e.message}"
}

logTime('TOTAL', t0)

// ------------------------------ RESULT ------------------------------
if (!failures.isEmpty()) {
	KeywordUtil.markFailed("${failures.size()} validation(s) failed:\n - " + failures.join('\n - '))
} else {
	KeywordUtil.markPassed('All field validations passed')
}