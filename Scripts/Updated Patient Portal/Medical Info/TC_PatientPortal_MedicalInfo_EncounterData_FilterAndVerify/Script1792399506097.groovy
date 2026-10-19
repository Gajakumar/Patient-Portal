import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import custom.DateFilterKeywords as DF
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import custom.DateFilterKeywords as DF
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory
import custom.DateFilterKeywords as DF
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


// =====================================================
// ✅ STEP 1: Portal Login
// =====================================================

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Dsmith Portal Login'), [:], FailureHandling.STOP_ON_FAILURE)

TestObject portalPtId = findTestObject(
	'PatientPortal/Page_Patient Portal/Pt Id Dob on dashboard'
)

WebUI.waitForElementVisible(
	portalPtId, 10, FailureHandling.STOP_ON_FAILURE
)

String patientInfo = WebUI.getAttribute(portalPtId, 'innerText') ?: ''

// Extract only the Patient ID
String patientId = patientInfo.replaceAll(/^PT ID:\s*|\s*\|.*$/, '').trim()

println("Patient ID: ${patientId}")

// Store Patient ID in GlobalVariable
GlobalVariable.PPPatientId = patientId


WebUI.click(findTestObject('Medical Info/Page_Patient Portal/svg_text-primary'))

WebUI.click(findTestObject('Medical Info/Page_Patient Portal/span_Medical Info'))

WebUI.assertElementText(findTestObject('Medical Info/Page_Patient Portal/h1_Medical Info'), 'Medical Info', 0)

WebUI.assertElementText(findTestObject('Medical Info/Page_Patient Portal/span_David Smith'), 'David Smith', 0)


WebUI.assertElementText(findTestObject('Medical Info/Page_Patient Portal/p_No Data to show'), 'No Data to show', 0)

WebUI.assertElementPresent(findTestObject('Medical Info/Page_Patient Portal/select_Select Profile'), 0)

TestObject profileDropdown = new TestObject('medical-info-profile')
profileDropdown.addProperty('xpath', ConditionType.EQUALS, "//select[@id='medical-info-profile']")
 
// Expected options in order: [value : visible text]. The first (blank) option is the default.
List<List<String>> expected = [
	['',                           ''],
	['allergies',                  'Allergies'],
	['medications',                'Medications'],
	['problems-conditions',        'Problems / Conditions'],
	['procedures',                 'Procedures'],
	['immunizations',              'Immunizations'],
	['laboratory-results',         'Laboratory Results'],
	['vital-signs',                'Vital Signs'],
	['care-plans',                 'Care Plans'],
	['clinical-notes',             'Clinical Notes'],
	['encounters',                 'Encounters'],
	['smoking-status',             'Smoking Status'],
	['medical-devices',            'Medical Devices'],
	['assessment-treatment-plans', 'Assessment & Treatment Plans'],
	['goals',                      'Goals'],
	['patient-demographics',       'Patient Demographics']
]
 
WebUI.waitForElementVisible(profileDropdown, 10)
 
WebElement selectEl = WebUI.findWebElement(profileDropdown)
Select select = new Select(selectEl)
List<WebElement> options = select.getOptions()
 
// ---- 1) Option count ----
WebUI.verifyEqual(options.size(), expected.size())
 
// ---- 2) Each option: value + text, in order ----
List<String> actualValues = options.collect { it.getAttribute('value') }
List<String> actualTexts  = options.collect { it.getText().trim() }
 
expected.eachWithIndex { exp, i ->
	WebUI.verifyEqual(actualValues[i], exp[0], com.kms.katalon.core.model.FailureHandling.CONTINUE_ON_FAILURE)
	WebUI.verifyEqual(actualTexts[i],  exp[1], com.kms.katalon.core.model.FailureHandling.CONTINUE_ON_FAILURE)
}
 
// ---- 3) Report anything missing / unexpected (order-independent) ----
Set<String> expectedValues = expected.collect { it[0] } as Set
Set<String> missing    = expectedValues - (actualValues as Set)
Set<String> unexpected = (actualValues as Set) - expectedValues
if (missing)    KeywordUtil.markFailed("Missing options: ${missing}")
if (unexpected) KeywordUtil.markFailed("Unexpected options: ${unexpected}")
 
// ---- 4) Default state: no profile selected ----
WebElement selected = select.getFirstSelectedOption()
WebUI.verifyEqual(selected.getAttribute('value'), '')          // blank option selected
WebUI.verifyEqual(selected.getText().trim(), '')               // no visible text
WebUI.verifyEqual(WebUI.getAttribute(profileDropdown, 'value'), '')   // select's value is empty
 
// None of the real profiles is selected
long selectedCount = options.count { it.isSelected() && it.getAttribute('value') != '' }
WebUI.verifyEqual(selectedCount, 0L)



WebUI.assertElementPresent(findTestObject('Medical Info/Page_Patient Portal/input_Select date range'), 0)



WebUI.assertElementPresent(findTestObject('Medical Info/Page_Patient Portal/img_calendar'), 0)



WebUI.assertElementPresent(findTestObject('Medical Info/Page_Patient Portal/button_Search Record'), 0)

//WebUI.click(findTestObject('Medical Info/Page_Patient Portal/img_calendar'))

//WebUI.click(findTestObject('Medical Info/Page_Patient Portal/div_20'))

// =====================================================================
// SECTION 2 : TC_DF_01  - Verify Duration filter UI (Start/End + Quick Select)   [point a]
// =====================================================================

 
DF.openDurationPicker()
 
WebUI.verifyElementText(DF.xp("(//div[@role='dialog']//h4[1])[1]"), 'Duration')
 
// Quick select options
['Today','Yesterday','Last 7 days','Last 30 days','Last 1 Year','This Month','Last Month','This Year'].each { opt ->
	WebUI.verifyElementPresent(DF.QUICK(opt), 5)
}
 
// Custom option + range calendars
WebUI.verifyElementPresent(DF.CUSTOM_BTN(), 5)
WebUI.click(DF.CUSTOM_BTN())
WebUI.verifyElementPresent(DF.START_INPUT(), 5)   // Start date
WebUI.verifyElementPresent(DF.END_INPUT(), 5)     // End date
WebUI.verifyElementPresent(DF.CONFIRM_BTN(), 5)
WebUI.verifyElementPresent(DF.CANCEL_BTN(), 5)
WebUI.click(DF.CONFIRM_BTN())

import custom.DateFilterKeywords as DF
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

def validPresets       = ['Today','Yesterday','Last 7 days','Last 30 days','Last 1 Year','Last Month']
def futureRangePresets = ['This Month', 'This Year']

// 1) Presets that always end in the past or today: popup closes
validPresets.each { opt ->
	DF.openDurationPicker()
	DF.selectQuick(opt)
	WebUI.click(DF.CONFIRM_BTN())
	WebUI.verifyElementNotPresent(DF.DIALOG(), 5)
}

// 2) This Month / This Year: behaviour depends on today's date
futureRangePresets.each { opt ->

	// true if the preset's end date (end of month / end of year) is after today
	boolean endsInFuture = (opt == 'This Month') ?
			DF.today().getDayOfMonth() < DF.today().lengthOfMonth() :
			DF.today().getDayOfYear()  < DF.today().lengthOfYear()

	DF.openDurationPicker()
	DF.selectQuick(opt)
	WebUI.click(DF.CONFIRM_BTN())

	if (endsInFuture) {
		// Normal case: validation shown, popup stays open
		WebUI.verifyElementText(DF.ERROR_MSG(), 'Future dates shall not be allowed.')
		WebUI.verifyElementVisible(DF.DIALOG())
		WebUI.click(DF.CANCEL_BTN())
		WebUI.verifyElementNotPresent(DF.DIALOG(), 5)
	} else {
		// Last day of the month / year: range has no future dates, so it applies and closes
		WebUI.verifyElementNotPresent(DF.DIALOG(), 5)
	}
}
 
 
// =====================================================================
// SECTION 3 : TC_DF_02  - Future dates are disabled in calendar + validation message   [point b]
// =====================================================================

 
DF.openDurationPicker()
 
LocalDate t = DF.today()
String monthLabel = t.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + t.getYear()
 
// Today is selectable, tomorrow's cell (if in same month) is disabled
WebUI.verifyElementHasAttribute(DF.DAY_CELL(String.valueOf(t.getDayOfMonth()), monthLabel), 'class', 5)
if (t.getDayOfMonth() < t.lengthOfMonth()) {
	String tomorrow = String.valueOf(t.getDayOfMonth() + 1)
	String cls = WebUI.getAttribute(DF.DAY_CELL(tomorrow, monthLabel), 'class')
	assert cls.contains('cursor-not-allowed')
}
 
// Click a disabled future date -> validation visible
WebUI.click(DF.DAY_CELL(String.valueOf(t.lengthOfMonth()), monthLabel), FailureHandling.OPTIONAL)
WebUI.verifyElementText(DF.ERROR_MSG(), 'Future dates shall not be allowed.')
 
 
// =====================================================================
// SECTION 4 : TC_DF_03  - Manually typed FUTURE date in Custom filter   [point b]
// =====================================================================

 
String futureStart = DF.fmt(DF.today().plusDays(1))
String futureEnd   = DF.fmt(DF.today().plusDays(5))
 
//DF.openDurationPicker()
DF.enterCustomRange(futureStart, futureEnd)
 
WebUI.verifyElementText(DF.ERROR_MSG(), 'Future dates shall not be allowed.')
WebUI.click(DF.PRESET_BTN())
 
// Mixed: valid start, future end
DF.enterCustomRange(DF.fmt(DF.today().minusDays(3)), futureEnd)
WebUI.verifyElementText(DF.ERROR_MSG(), 'Future dates shall not be allowed.')
WebUI.click(DF.CANCEL_BTN())

 
// =====================================================================
// SECTION 5 : TC_DF_04  - Manual entry of a VALID date + MM/DD/YYYY format   [point b]

 
String start = DF.fmt(DF.today().minusDays(7))   // MM/dd/yyyy
String end   = DF.fmt(DF.today())
 
assert start ==~ /^(0[1-9]|1[0-2])\/(0[1-9]|[12]\d|3[01])\/\d{4}$/
 
DF.openDurationPicker()
DF.enterCustomRange(start, end)
 
WebUI.verifyEqual(WebUI.getAttribute(DF.START_INPUT(), 'value'), start)
WebUI.verifyEqual(WebUI.getAttribute(DF.END_INPUT(), 'value'), end)
WebUI.verifyElementNotPresent(DF.ERROR_MSG(), 3)
 
WebUI.click(DF.CONFIRM_BTN())
WebUI.verifyElementNotPresent(DF.DIALOG(), 5)
 
// Wrong format (DD/MM/YYYY style / YYYY-MM-DD) should NOT be accepted
DF.openDurationPicker()

DF.enterCustomRange('2026-10-10', '2026-10-15')
WebUI.verifyElementPresent(DF.ERROR_MSG(), 3)     // expects a validation message
WebUI.click(DF.PRESET_BTN())
 
 
// =====================================================================
// SECTION 6 : TC_DF_05  - Blank date -> "Please Select valid date range"   [point c]
// =====================================================================

 
//// Case 1: both dates cleared
////DF.openDurationPicker()
//DF.enterCustomRange('', '')
//WebUI.click(DF.CONFIRM_BTN())
//WebUI.verifyElementText(DF.ERROR_MSG(), 'Please Select valid date range')
// 
//// Case 2: only start date
//DF.enterCustomRange(DF.fmt(DF.today().minusDays(2)), '')
//WebUI.click(DF.CONFIRM_BTN())
//WebUI.verifyElementText(DF.ERROR_MSG(), 'Please Select valid date range')
// 
//// Case 3: only end date
//DF.enterCustomRange('', DF.fmt(DF.today()))
//WebUI.click(DF.CONFIRM_BTN())
//WebUI.verifyElementText(DF.ERROR_MSG(), 'Please Select valid date range')
// 
//// Case 4: Search with blank date on the page itself (after clearing the filter)  >>> UPDATE if needed <<<
//WebUI.click(DF.CANCEL_BTN())
//WebUI.click(DF.SEARCH_BTN())
//// WebUI.verifyTextPresent('Please Select valid date range', false)
 
 
//// =====================================================================
//// SECTION 7 : TC_DF_06  - Filter data matches selected range   [point 6]
//// =====================================================================
//
// 
//DateTimeFormatter gridFmt = DateTimeFormatter.ofPattern("MM/dd/yyyy")   // >>> UPDATE to the date format shown in your grid <<<
//int DATE_COL = 3                                                        // >>> UPDATE: column index holding the date <<<
// 
//def verifyRowsWithin = { LocalDate from, LocalDate to ->
//	WebDriver driver = DriverFactory.getWebDriver()
//	def cells = driver.findElements(By.xpath("//table//tbody//tr/td[${DATE_COL}]"))
//	assert cells.size() > 0 : "Expected records for ${from} - ${to}"
//	cells.each { c ->
//		LocalDate d = LocalDate.parse(c.getText().trim(), gridFmt)
//		assert !d.isBefore(from) && !d.isAfter(to) : "Row date ${d} outside ${from} - ${to}"
//	}
//}
// 
//// Quick select: Last 7 days
//DF.openDurationPicker()
//DF.selectQuick('Last 7 days')
//WebUI.click(DF.CONFIRM_BTN())
//WebUI.click(DF.SEARCH_BTN())
//WebUI.waitForElementPresent(DF.RESULT_ROWS(), 10)
//verifyRowsWithin(DF.today().minusDays(7), DF.today())
// 
//// Quick select: This Month
//DF.openDurationPicker()
//DF.selectQuick('This Month')
//WebUI.click(DF.CONFIRM_BTN())
//WebUI.click(DF.SEARCH_BTN())
//WebUI.waitForElementPresent(DF.RESULT_ROWS(), 10)
//verifyRowsWithin(DF.today().withDayOfMonth(1), DF.today())
// 
//// Custom range
//LocalDate s = DF.today().minusDays(30)
//LocalDate e = DF.today().minusDays(10)
//DF.openDurationPicker()
//DF.enterCustomRange(DF.fmt(s), DF.fmt(e))
//WebUI.click(DF.CONFIRM_BTN())
//WebUI.click(DF.SEARCH_BTN())
//WebUI.waitForElementPresent(DF.RESULT_ROWS(), 10)
//verifyRowsWithin(s, e)
// 
//// Single day (start = end)
//DF.openDurationPicker()
//DF.enterCustomRange(DF.fmt(e), DF.fmt(e))
//WebUI.click(DF.CONFIRM_BTN())
//WebUI.click(DF.SEARCH_BTN())
//verifyRowsWithin(e, e)
 
 
// =====================================================================
// SECTION 8 : TC_DF_07  - No records -> "No Data to Show"   [point 7]
// =====================================================================

 
// Pick a range known to have no data (e.g. far past)   >>> UPDATE with a range that is empty in your test data <<<
String noDataStart = '01/01/2000'
String noDataEnd   = '01/07/2000'
 
//DF.openDurationPicker()

DF.enterCustomRange(noDataStart, noDataEnd)
WebUI.verifyElementNotPresent(DF.ERROR_MSG(), 3)    // past dates must be valid
WebUI.click(DF.CONFIRM_BTN())
WebUI.selectOptionByValue(profileDropdown, 'allergies', false)
WebUI.click(DF.SEARCH_BTN())
 
WebUI.waitForElementVisible(DF.NO_DATA_MSG(), 10)
WebUI.verifyElementText(DF.NO_DATA_MSG(), 'No Data to show')
WebUI.verifyElementNotPresent(DF.RESULT_ROWS(), 3)

WebUI.selectOptionByValue(profileDropdown, 'patient-demographics', false)
WebUI.click(DF.SEARCH_BTN())
CustomKeywords.'custom.PatientDemographicsKeywords.selectAndVerifyPatientDemographics'(GlobalVariable.PPPatientId)