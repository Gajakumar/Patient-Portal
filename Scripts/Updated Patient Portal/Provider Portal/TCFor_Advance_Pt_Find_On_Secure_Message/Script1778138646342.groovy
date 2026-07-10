import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
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

//Login to Maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

// Open Messages tab
TestObject menu = findTestObject('Provider Portal/Page_MaximEyes/a_ui-id-9')
WebUI.waitForElementClickable(menu, 10)
WebUI.click(menu)

// Click Compose
TestObject composeBtn = findTestObject('Provider Portal/Page_MaximEyes/span_btnCompose')
WebUI.waitForElementClickable(composeBtn, 10)
WebUI.click(composeBtn)

// Search popup
TestObject searchBtn = findTestObject('Provider Portal/Page_MaximEyes/span_Search')
WebUI.waitForElementClickable(searchBtn, 10)
WebUI.click(searchBtn)

// Patients flow
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnPatients'))
WebUI.waitForElementVisible(findTestObject('Provider Portal/Page_MaximEyes/div_ADVANCED PATIENT FIND'), 10)
WebUI.verifyMatch(WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/div_ADVANCED PATIENT FIND')).trim(), 'ADVANCED PATIENT FIND', false)


// ===============================
// 🔹 Verify Search Form Elements
// ===============================
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_Patient ID'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_Location'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_Provider'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_SSN'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_Chart Number'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_Is Active'), 0)

// Buttons
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'), 0)   // Search
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button_1'), 0) // Clear

// ===============================
// 🔹 Verify Table Headers
// ===============================
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/th_Patient ID_ activate to sort column descendin'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/th_Last Name_ activate to sort column ascending'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/th_First Name_ activate to sort column ascending'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/th_Middle Name_ activate to sort column ascendin'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/th_Date of Birth_ activate to sort column ascend'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/th_Age_ activate to sort column ascending'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/th_Sex_ activate to sort column ascending'), 0)

// ===============================
// 🔹 Search Info Tooltip Validation
// ===============================
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_Search'), 0)
WebUI.mouseOver(findTestObject('Provider Portal/Page_MaximEyes/span_mif-info fg-skyblue line-height28 font17 tb'))

String actualText = WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/p_Type a value to search the current page. This'))

// Normalize: remove line breaks & extra spaces
actualText = actualText.replaceAll("\\s+", " ").trim()

String expectedText = "Type a value to search the current page. This search will not search across multiple pages."

WebUI.verifyMatch(actualText, expectedText, false)

// ===============================
// 🔹 Validate Empty Search Behavior
// ===============================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))

WebUI.verifyElementText(
	findTestObject('Provider Portal/Page_MaximEyes/div_You must enter one criteria to find patients'),
	'You must enter one criteria to find patients.'
)

WebUI.verifyElementText(
	findTestObject('Provider Portal/Page_MaximEyes/td_No information available to display'),
	'No information available to display'
)

// ===============================
// 🔹 Search Using First Name
// ===============================
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), 'test')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))

String actualTextNum = WebUI.getText(findTestObject("Provider Portal/Page_MaximEyes/span_Showing 1 to 10 of 116"))

// normalize text
actualTextNum = actualTextNum.replaceAll("\\s+", " ").trim()

WebUI.verifyMatch(actualTextNum, "Showing 1 to 10 of \\d+", true)

// Pagination validation
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_'), 0)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span__1'), 0)

// ===============================
// 🔹 Clear Search
// ===============================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button_1'))
WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), '')

// ===============================
// 🔹 Select Patient & Verify Actions
// ===============================
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), 'test')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/td_test17'))

WebUI.verifyElementClickable(findTestObject('Provider Portal/Page_MaximEyes/input_btnSendemail'))
WebUI.verifyElementClickable(findTestObject('Provider Portal/Page_MaximEyes/input_btnSendToPatientPortal'))

// ===============================
// 🔹 Search Using Last Name
// ===============================
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name'), 'testdata')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/td_TESTDATA'))

WebUI.verifyElementVisible(findTestObject('Provider Portal/Page_MaximEyes/table_tblAdvanceFindPatientRecords'))

// ===============================
// 🔹 Validate Patient Without Email
// ===============================
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name'), 'Email')
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), 'No')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/td_TESTDATA'))

String actualTextAge = WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/td_00 months'))

// Normalize text
actualTextAge = actualTextAge.replaceAll("\\s+", " ").trim()

// Validate: number + "month" or "months"
WebUI.verifyMatch(actualTextAge, "\\d+ month(s)?", true)

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnSendemail'))

WebUI.waitForElementVisible(
	findTestObject('Provider Portal/Page_MaximEyes/h4_The Patient selected does not have an Email I'),
	5
)

WebUI.verifyElementText(
	findTestObject('Provider Portal/Page_MaximEyes/h4_The Patient selected does not have an Email I'),
	'The Patient selected does not have an Email ID. You can add Email ID from Patient Overview.'
)

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'))
//Click on close button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

// ===============================
// 🔹 Add Email to Patient
// ===============================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/a_imgFindPatient'))
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name_1'), 'Email')
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name_Preferred'), 'No')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnSearchPatient'))

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/a_PatientDetailsTabLink'))
WebUI.selectOptionByValue(findTestObject('Provider Portal/Page_MaximEyes/select_PR_DdlPhoneType_3b6128db1'), 'Email', false)
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_PR_EMAIL_3b6128db1'), 'gajakumara@first-insight.com')

// ===============================
// 🔹 Send Email After Adding
// ===============================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_mif-home font19 head-icon-shadow fg-white'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_btnCompose'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_Search'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnPatients'))

WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name'), 'email')
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), 'no')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/td_TESTDATA'))

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnSendemail'))

String actualValue = WebUI.getAttribute(
    findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search Patient or Referring Physician'),
    "value"
)

String expectedRegex = /^Email No \| Email: gajakumara@first-insight\.com \| DOB: 5\/1\/2026 \(.+\), M$/

WebUI.verifyMatch(actualValue, expectedRegex, true)

WebUI.verifyElementText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/span_Email'), 'Email')

WebUI.verifyElementText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/span_Note_ Emailing patient health information t'),
	'Note: Emailing patient health information to this address is not HIPAA compliant!')

//----------

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_Search'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnPatients'))

WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name'), 'Smith')
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), 'David')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/td_TESTDATA'))

WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_btnSendToPatientPortal'))

WebUI.verifyElementText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/span_Patient Portal'), 'Patient Portal')

WebUI.verifyElementAttributeValue(
	findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search Patient or Referring Physician'),
	"value",
	"Smith David | DOB: 3/16/1982 (44 yrs), M",
	5
)


// ===============================
// 🔹 Cleanup - Remove Email
// ===============================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/a_imgFindPatient'))
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name_1'), 'Email')
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name_Preferred'), 'No')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnSearchPatient'))

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/a_PatientDetailsTabLink'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_mif-remove fg-skyblue font15 ptDetailsPhEma'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnRemovePhoneEmailYes'))

// ===============================
// 🔹 Navigate Back to Home
// ===============================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_mif-home font19 head-icon-shadow fg-white'))

