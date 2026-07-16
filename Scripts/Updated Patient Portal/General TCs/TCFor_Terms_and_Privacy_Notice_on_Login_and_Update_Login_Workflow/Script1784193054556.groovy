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
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

// -------------------------------------------------------------------------
// STEP 1: Navigate to the Patient Portal site
// -------------------------------------------------------------------------
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// -------------------------------------------------------------------------
// STEP 2: Open the Sign In page
// -------------------------------------------------------------------------
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
 
// -------------------------------------------------------------------------
// STEP 3: Verify the "Terms of Use & Privacy Policy" consent text is shown
// -------------------------------------------------------------------------
WebUI.assertElementText(
	findTestObject('Terms and Privacy/Page_Patient Portal/p_By continuing, you agree to EVAA Patient Porta'),
	'By continuing, you agree to EVAA Patient Portal by First Insight Terms of Use and Privacy Policy.',
	5
)
 
// -------------------------------------------------------------------------
// STEP 4: Open the Privacy Policy link and verify the modal/page header
// -------------------------------------------------------------------------
WebUI.click(findTestObject('Terms and Privacy/Page_Patient Portal/a_Privacy Policy'))
 
WebUI.assertElementText(
	findTestObject('Terms and Privacy/Page_Patient Portal/h5_EVAA Patient Portal Terms of Use and Privacy'),
	'EVAA Patient Portal Terms of Use and Privacy Policy',
	5
)
 
// -------------------------------------------------------------------------
// STEP 5: Verify all required Terms & Privacy sections are present
// -------------------------------------------------------------------------
List<String> expectedSections = [
	"1. INTRODUCTION",
	"2. RELATIONSHIP BETWEEN FIRST INSIGHT AND THE PRACTICE",
	"3. PURPOSE OF SERVICE",
	"4. DATA PRIVACY AND SECURITY",
	"5. INFORMATION WE COLLECT",
	"6. USE OF SMS AND COMMUNICATION CHANNELS",
	"7. PATIENT CONSENT AND ACKNOWLEDGMENT",
	"8. PAYMENTS AND TRANSACTIONS",
	"9. LIMITATIONS OF LIABILITY",
	"10. INTELLECTUAL PROPERTY",
	"11. UPDATES AND REVISIONS",
	"12. CONTACT INFORMATION",
	"13. COPYRIGHT NOTICE"
]
 
CustomKeywords.'common.VerifyTerms.verifyTermsSections'(
	findTestObject('Terms and Privacy/Page_Patient Portal/div_1. INTRODUCTIONThese Terms  Conditions (Te'),
	expectedSections
)
 
// Close the Terms & Privacy modal
WebUI.click(findTestObject('Terms and Privacy/Page_Patient Portal/button_Close'))
 
// -------------------------------------------------------------------------
// STEP 6: Re-open Sign In and log in with username/password
// -------------------------------------------------------------------------
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[('Username') : UserName, ('Password') : GlobalVariable.RestUpdatedPass],
	FailureHandling.STOP_ON_FAILURE
)
 
// Give the OTP screen time to render before attempting to fetch/enter the code
WebUI.delay(5)
 
// -------------------------------------------------------------------------
// STEP 7: Fetch the OTP from the inbox via Gmail IMAP
// -------------------------------------------------------------------------
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)
 
println("OTP fetched = " + otp)
 
// Guard: fail fast with a clear message if the OTP is missing/invalid,
// instead of a confusing NPE/ArrayIndexOutOfBounds later on
assert otp != null && otp.length() == 4, "OTP fetch failed or returned an unexpected format: '${otp}'"
 
// -------------------------------------------------------------------------
// STEP 8: Enter the 4-digit OTP into the individual input boxes
// -------------------------------------------------------------------------
String[] digits = otp.toCharArray()
 
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())
 
WebUI.delay(5)
 
// -------------------------------------------------------------------------
// STEP 9: Click "Proceed" once OTP verification completes
// -------------------------------------------------------------------------
TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
 
// Wait until the button is visible AND enabled before clicking
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
 
WebUI.delay(5)
 
// -------------------------------------------------------------------------
// STEP 10: Open the user/profile menu and navigate to Activity Log
// -------------------------------------------------------------------------
WebUI.click(findTestObject('Terms and Privacy/Page_Patient Portal/div_w-10 h-10 flex items-center justify-center r'))
WebUI.click(findTestObject('Terms and Privacy/Page_Patient Portal/span_Activity Log'))
 
// -------------------------------------------------------------------------
// STEP 11: Filter the Activity Log by "Today" using the calendar picker
// -------------------------------------------------------------------------
WebUI.click(findTestObject('Terms and Privacy/Page_Patient Portal/img_calendar'))
WebUI.click(findTestObject('Terms and Privacy/Page_Patient Portal/button_Today'))
WebUI.click(findTestObject('Terms and Privacy/Page_Patient Portal/button_Confirm'))
 
// -------------------------------------------------------------------------
// STEP 12: Verify the displayed timestamp matches the current date/time
// -------------------------------------------------------------------------
CustomKeywords.'common.VerifyDateTime.verifyCurrentDateTime'(
	findTestObject('Terms and Privacy/Page_Patient Portal/div_07_16_2026 08_44_59 AM'),
	2
)
 
// -------------------------------------------------------------------------
// STEP 13: Verify the latest Activity Log row shows the correct user & action
// -------------------------------------------------------------------------
WebUI.assertElementText(findTestObject('Terms and Privacy/Page_Patient Portal/td_David Smith'), 'David Smith', 5)
WebUI.assertElementText(findTestObject('Terms and Privacy/Page_Patient Portal/td_Login'), 'Login', 5)
 