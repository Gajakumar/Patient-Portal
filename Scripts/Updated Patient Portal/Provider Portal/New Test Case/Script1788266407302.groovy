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
import custom.DownloadHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.Keys
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.chrome.ChromeOptions
import com.kms.katalon.core.testobject.ConditionType

//Login to the maximeyes

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

TestObject secureMessagesLink = findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Secure Messages')
TestObject homeButton = findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Home') // update path to your actual Home object
TestObject secureMessagesPageIndicator = findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/SecureMessagesPageElement') // some unique element that only exists once page has loaded

int maxRetries = 3
int waitTimeoutSec = 15
boolean pageOpened = false

// initial click
WebUI.click(secureMessagesLink)

for (int attempt = 1; attempt <= maxRetries; attempt++) {
    pageOpened = WebUI.verifyElementPresent(secureMessagesPageIndicator, waitTimeoutSec, FailureHandling.OPTIONAL)

    if (pageOpened) {
        WebUI.comment('Secure Messages page opened successfully on attempt ' + attempt)
        break
    } else {
        WebUI.comment('Secure Messages page did not open within ' + waitTimeoutSec + ' sec, retrying via Home...')
        WebUI.click(homeButton)
        WebUI.delay(2) // small buffer for home page to load, adjust as needed
        WebUI.click(secureMessagesLink)
    }
}

if (!pageOpened) {
    WebUI.comment('Secure Messages page failed to open after ' + maxRetries + ' attempts')
    // Optionally fail the test explicitly:
    // WebUI.failed('Secure Messages page did not load after retries')
}

//WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Secure Messages_searchboxofinbox'),
//	GlobalVariable.PatientFirstName)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To First Insight VisionAction Required P_5fe8ca'))

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Action Required Patient Request to Rest_3cb7ef'),
	'Action Required: Patient Request to Restrict Access to Health Data')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Patient78ae0 Testaeb66'),
	GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName)


String uiDateTime = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_01212026 1154 AM')
)

CustomKeywords.'common.GMTTimeAuditVerifier.verifyUITimeWithinMinutesOfSendGMT'(
	uiDateTime,
	5   // ±5 minutes
)

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To First Insight Vision'),
	'To: First Insight Vision')

String actualMailText = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Dear Provider,A patient has submitted a_f926cb')
)

// Normalize whitespace (removes line breaks, tabs, extra spaces)
actualMailText = actualMailText.replaceAll('\\s+', ' ').trim()

// Static content checks
assert actualMailText.contains('Dear Provider')
assert actualMailText.contains('A patient has submitted a request to restrict access to a specific element of their Electronic Health Information (EHI)')
assert actualMailText.contains('Restriction Request Details')
assert actualMailText.contains('Additional Notes')
assert actualMailText.contains('This action will be recorded in the audit log for compliance')
assert actualMailText.contains('The patient will be notified of your decision')
assert actualMailText.contains('Patient Data Restrictions')
assert actualMailText.contains('Accept Request')
assert actualMailText.contains('Deny Request')

// Dynamic field checks (pass values from test data or variables)
assert actualMailText.contains('Patient Name: '+ GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName)
assert actualMailText.contains('Patient ID: '+GlobalVariable.GV_PatientID)
assert actualMailText.contains('Requested USCDI Element(s): Vital signs')

// Date & Time (EST) – format check only (recommended)
assert actualMailText =~ /Date & Time of Request: [A-Za-z]+ \d{2}, \d{4}, \d{2}:\d{2} (AM|PM) \(EST\)/


WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Accept Request'))

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
	'Action details are logged successfully.')

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Patient78ae0 Testaeb66'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounter Details'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Summary Of Care(C-CDA)'))

String actualTextVitalExclude1 = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Vital SignsInfo has been Excluded')
)

// Normalize spaces & line breaks
actualTextVitalExclude1 = actualTextVitalExclude1.replaceAll('\\s+', ' ').trim()

String expectedTextVital1 = 'Vital Signs Info has been Excluded.'

WebUI.verifyMatch(actualTextVitalExclude1, expectedTextVital1, false)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Cancel_dialog-close-button btn-close 143f7'))

//Navigate to patient portal site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

// Login again with new password
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/UserName'), "VfnQxs0316")



WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Password'), "Test@1234")



WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))



WebUI.delay(5)

// Fetch OTP from email
String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println('OTP fetched = ' + otp1)

// Enter OTP digits
String[] digits1 = otp1.toCharArray()

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1'), digits1[0])
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2'), digits1[1])
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3'), digits1[2])
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'), digits1[3])

WebUI.delay(5)
TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
// Click Proceed
WebUI.waitForElementClickable(proceedBtn, 15)
WebUI.click(proceedBtn)

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))

String actualTextVitalExclude = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_Vital SignsInfo has been Excluded')
)

// Normalize spaces & line breaks
actualTextVitalExclude = actualTextVitalExclude.replaceAll('\\s+', ' ').trim()

String expectedTextVital = 'Vital Signs Info has been Excluded.'

WebUI.verifyMatch(actualTextVitalExclude, expectedTextVital, false)

WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/icon_Settings'))

String checked = WebUI.getAttribute(
	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'),
	'checked'
)

WebUI.verifyEqual(checked, 'true')

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))

WebUI.waitForElementNotVisible(
	findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
	15
)


WebUI.delay(3)

//***************Deny button*******************
 
 WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
 
 WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Secure Messages'))
 
 
 WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To First Insight VisionAction Required P_5fe8ca'))
 
 WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/button_Deny Request'))
 
 WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
	 'Action details are logged successfully.')
 
 WebUI.delay(5)
 
 
 CustomKeywords.'common.RestrictionEmailFullVerifier.fetchAndVerifyDeniedEmailBySubject'(
	 "imap.gmail.com",
	 GlobalVariable.MyEmail_Id,
	 GlobalVariable.Email_Key,
	 "Update on Your Data Restriction Request", // exact subject
	 GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName,
	 "Vital signs"
 )
 
 //********************************Compose Msg**********************************
 
 WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Inbox_btnCompose'))
 
 WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To_mif-search font18 fg-skyblue'))
 
 WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Do you want to search Patients or Ext_4d59c0'))
 
 WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_ADVANCED PATIENT FIND_LastName'),GlobalVariable.PatientLastName)
 
 WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/Page_MaximEyes/input_ADVANCED PATIENT FIND_FirstName'),GlobalVariable.PatientFirstName)
 
 WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Is Active_button primary small-button'))
 
 WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_100740'))
 
 WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input__btnSendToPatientPortal'))
 
 WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Subject_ComposeSubject'), 'XML CCDA File')
 
 WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/textarea_Message_input InboxTextarea font20 pad05'),
	 'Message to Patient including CCDA File')
 
