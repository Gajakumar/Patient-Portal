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

// -------------------------------------------------------------------------
// STEP 1: Log in to Maximeyes back office with an existing user
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// -------------------------------------------------------------------------
// STEP 2: Create a random new patient in Maximeyes
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)
 
// -------------------------------------------------------------------------
// STEP 3: Navigate to the Patient Portal site
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// -------------------------------------------------------------------------
// STEP 4: Self-register the new patient via "Create New Patient and Sign In"
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create New Patient and Sign In'),
	[
		('PtFirstName') : GlobalVariable.PatientFirstName,
		('PtLastName')  : GlobalVariable.PatientLastName,
		('PtMobile')    : GlobalVariable.Mobile,
		('PtMailid')    : GlobalVariable.MyEmail_Id,
		('PtDOB')       : GlobalVariable.DOB
	],
	FailureHandling.STOP_ON_FAILURE
)
 
// -------------------------------------------------------------------------
// STEP 5: Verify the "Send me credentials" prompt is shown, then request
//         credentials to be emailed to the patient
// -------------------------------------------------------------------------
WebUI.verifyElementVisible(findTestObject('Object Repository/Page_Patient Portal/h1_Send me credentials_fs-2 mb-3'))
 
TestObject sendCred = findTestObject('Object Repository/Page_Patient Portal/button_Send me credentials')
 
WebUI.click(sendCred)
 
// Defensive re-click: some environments render the button twice / re-prompt
if (WebUI.verifyElementPresent(sendCred, 5, FailureHandling.OPTIONAL)) {
	WebUI.click(sendCred)
}
 
WebUI.delay(5)
 
// -------------------------------------------------------------------------
// STEP 6: Extract the emailed username & password
// -------------------------------------------------------------------------
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)
 
println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password
 
// -------------------------------------------------------------------------
// STEP 7: Return to the Patient Portal site and log in with the emailed
//         credentials
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password],
	FailureHandling.STOP_ON_FAILURE
)
 
// -------------------------------------------------------------------------
// STEP 8: Confirm date of birth and accept Terms & Conditions
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// -------------------------------------------------------------------------
// STEP 9: Fetch OTP (1st verification) and enter it into the 4-digit fields
// -------------------------------------------------------------------------
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)
 
println("OTP fetched = " + otp)
 
String[] digits = otp.toCharArray()
 
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())
 
// -------------------------------------------------------------------------
// STEP 10: Click "Proceed" once OTP verification (1st time) completes
// -------------------------------------------------------------------------
TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
 
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
 
// -------------------------------------------------------------------------
// STEP 11: Update / reset the password
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// -------------------------------------------------------------------------
// STEP 12: Log in again using the newly updated password
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword],
	FailureHandling.STOP_ON_FAILURE
)
 
WebUI.delay(5)
 
// -------------------------------------------------------------------------
// STEP 13: Fetch OTP (2nd verification) and enter it into the 4-digit fields
// -------------------------------------------------------------------------
String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)
 
println("OTP fetched = " + otp1)
 
String[] digits1 = otp1.toCharArray()
 
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits1[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits1[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits1[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits1[3].toString())
 
WebUI.delay(5)
 
// -------------------------------------------------------------------------
// STEP 14: Click "Proceed" once OTP verification (2nd time) completes
// -------------------------------------------------------------------------
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
 
WebUI.delay(10)
 
// -------------------------------------------------------------------------
// STEP 15: Verify patient name and date/time are displayed correctly on
//          the dashboard
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'),
	[('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName],
	FailureHandling.STOP_ON_FAILURE
)
 
//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Click on Log Out button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/LogOut Button'))

//Verify Logged Out screen displayed
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/Logged Out Screen'),
	'Logged Out')

// -------------------------------------------------------------------------
// STEP 3: Navigate to the Patient Portal site
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// -------------------------------------------------------------------------
// STEP 4: Self-register the new patient via "Create New Patient and Sign In"
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create New Patient and Sign In'),
	[
		('PtFirstName') : GlobalVariable.PatientFirstName,
		('PtLastName')  : GlobalVariable.PatientLastName,
		('PtMobile')    : GlobalVariable.Mobile,
		('PtMailid')    : GlobalVariable.MyEmail_Id,
		('PtDOB')       : GlobalVariable.DOB
	],
	FailureHandling.STOP_ON_FAILURE
)

//Verify send credentials is visible
WebUI.verifyElementVisible(findTestObject('Object Repository/Page_Patient Portal/h1_Send me credentials_fs-2 mb-3'))