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

//Login to Maximeyes using QA_User
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

//Create Random Patient 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(3)

//Mouse hover on + button
WebUI.mouseOver(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Verify hover text of + button
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/div__no-margin font14 pad15'), 'Sign Up For Patient Portal')

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Verify header text of popup
WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/div_PATIENT PORTAL ACCOUNT'), 'PATIENT PORTAL ACCOUNT')


def normalize = { String text ->
	return text.replaceAll("\\s+", " ").trim()
}

// Send Sign Up Email to
WebUI.verifyMatch(
	normalize(WebUI.getText(findTestObject('Scenario Update1703/Page_MaximEyes/label_Send Sign Up Email to'))),
	"Send Sign Up Email to",
	false
)

// Patient Email
WebUI.verifyMatch(
	normalize(WebUI.getText(findTestObject('Scenario Update1703/Page_MaximEyes/label_lblPatientEmailPP'))),
	"<gajakumara@first-insight.com>",
	false
)

// Print Sign Up Instructions
WebUI.verifyMatch(
	normalize(WebUI.getText(findTestObject('Scenario Update1703/Page_MaximEyes/label_Print Sign Up Instructions'))),
	"Print Sign Up Instructions",
	false
)

// Opt-out
WebUI.verifyMatch(
	normalize(WebUI.getText(findTestObject('Scenario Update1703/Page_MaximEyes/label_Opt-out'))),
	"Opt-out",
	false
)

// Edit Email Address
WebUI.verifyMatch(
	normalize(WebUI.getText(findTestObject('Scenario Update1703/Page_MaximEyes/span_LinkEdiEmailAddressPP'))),
	"Edit Email Address",
	false
)



//WebUI.verifyMatch(combinedText.contains("<surajp@first-insight.com>"), true, false)


//Select Send Sign Up Email to radio button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait unitil busy indicator is not visible
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg "Patient Portal Sign Up Completed. Email Sent."
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'),'Patient Portal Sign Up Completed. Email Sent.')

//Mouse hover on check 
WebUI.mouseOver(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_PtOverviewResetPatientT_a02aab'))

String actual = WebUI.getText(findTestObject('Object Repository/Page_MaximEyes/p'))

// Replace all new lines and extra spaces with single spaces
actual = actual.replaceAll("\\s+", " ").trim()
String expected = "Patient Signup has been provided to patient. Click to Reset Patient's Password or Opt Patient Out of Patient Portal."

//Verify mouse hover text
WebUI.verifyMatch(actual, expected, true)

//Verify + button is turned into green check mark
CustomKeywords.'color.VerifyColor.verifyColor'(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_PtOverviewResetPatientT_a02aab'),'rgb(103, 184, 53)')

//Navigate to Patient Portal Site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//get the Username and Password from the sign up mail received on provided email id
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)

println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password

String ExpUserName = GlobalVariable.ShortName + "0316"

// Verify Username received on email
WebUI.verifyMatch(GlobalVariable.GV_Username, ExpUserName, true)

WebUI.delay(5)

//Click on sign in button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter Username and Password from received email
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//confirm DOB and Accept Terms
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

////Update Password   >>>>>>>>>>>>>>MBT 48416<<<<<<<<<<<<<<<<<
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Login with Updated Password
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(5)
//
////Fetch the otp from the email
//String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
//	'imap.gmail.com',
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	GlobalVariable.Sender_Email,
//	'Verification'
//)
//
//println("OTP fetched = " + otp)
//
//
//// Auto type into four input boxes
//String[] digits = otp.toCharArray()
//
////Enter the OTP
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())
//
//WebUI.delay(5)
//
//TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
//
//// Wait until the button is clickable (visible and enabled)
//WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
//
////Click on Procced button
//WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(10)

//Verify Date Time and Patient name on Dashboard
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

//Verify Dashboard modules
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Dashboard Verification'),[:],FailureHandling.STOP_ON_FAILURE)
