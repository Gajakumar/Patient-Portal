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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import javax.mail.Message
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.mail.*
import java.util.Properties
import javax.mail.*
import javax.mail.internet.MimeMultipart
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.util.regex.*
import java.util.Properties
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

 
// =====================================================================================
// STEP 1: Log in to MaximEyes and create a new random patient
// =====================================================================================
 
// Log in to the MaximEyes application
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// Create a new patient record using a randomly generated phone number and the test email
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)
 
// Wait for the busy/loading indicator to disappear before proceeding
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
 
 
// =====================================================================================
// STEP 2: Sign up the patient for portal access using their email
// =====================================================================================
 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Portal Sign up using email'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
 
// =====================================================================================
// STEP 3: Retrieve portal login credentials from the "welcome" email
// =====================================================================================
 
// Navigate to the Patient Portal site
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// Extract the auto-generated username/password from the "Access to your health data" email
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)
 
println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password
 
WebUI.delay(5)
 
 
// =====================================================================================
// STEP 4: Navigate to the login page and open "Forgot Password"
// =====================================================================================
 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// (Optional) Click Sign In button - currently disabled/not needed
//WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
 
// Verify the "Forgot Username?" and "Forgot Password?" links are present with correct text
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Username'),
	'Forgot Username?'
)
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Password'),
	'Forgot Password?'
)
 
// Click "Forgot Password?" to begin the reset flow
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Password'))
 
 
// =====================================================================================
// STEP 5: Validate static text on the "Recover your account" page
// =====================================================================================
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h1_Recover your Username'),
	'Recover your account'
)
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_We can help you reset your Username'),
	'We can help you reset your password.'
)
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Follow the instruction below'),
	'Follow the instruction below.'
)
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'),
	'Refresh'
)
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Input symbols'),
	'Input symbols'
)
 
 
// =====================================================================================
// STEP 6: Negative test - submit with no username entered
// =====================================================================================
 
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Next'))
 
// Expect a validation error since the username field was left blank
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Email ID required'),
	'Username required'
)
 
// Cancel out and confirm we return to the Sign In page
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Cancel'))
 
WebUI.verifyElementText(
	findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'),
	'Sign In'
)
 
 
// =====================================================================================
// STEP 7: Negative test - submit with an incorrect captcha value
// =====================================================================================
 
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Password'))
 
// Enter a deliberately wrong captcha value
WebUI.setText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Refresh_form-control py-2 border-danger_3'),
	'1234'
)
 
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Next'))
 
// Expect a captcha mismatch error
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Captcha does not match'),
	'Captcha does not match'
)
 
 
// =====================================================================================
// STEP 8: Positive path - enter valid username and correct captcha
// =====================================================================================
 
// Focus the email/username field and enter the extracted username
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/svg_Email Address_text-dark'))
 
WebUI.setText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Email Address_form-control ps-5 py-2 _415fae'),
	GlobalVariable.GV_Username
)
 
// Refresh captcha to get a fresh, readable value
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'))
 
// Read the captcha text from the page
String captchaText = WebUI.getText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Captcha Code'))
captchaText = captchaText.trim()
 
// If the captcha didn't render correctly (not exactly 4 characters), refresh and retry once
if (captchaText == null || captchaText.length() != 4) {
	WebUI.click(findTestObject('Page_Login/button_RefreshCaptcha'))
	WebUI.delay(1)
	captchaText = WebUI.getText(findTestObject('Page_Login/div_CaptchaText')).trim()
}
 
// Enter the (valid) captcha text into the input field
WebUI.setText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Refresh_form-control py-2 border-danger_3'),
	captchaText
)
 
// Submit the request to trigger the password-reset email
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Next'))
 
WebUI.delay(8)
 
 
// =====================================================================================
// STEP 9: Poll Gmail via IMAP for the password-reset email and extract the reset link
// =====================================================================================
 
// ----- IMAP configuration -----
String host = "imap.gmail.com"
String username = GlobalVariable.MyEmail_Id
String password = GlobalVariable.Email_Key
String expectedSubject = "Reset Password for Patient portal"
 
int timeoutInSeconds = 180
int pollInterval = 10
// -------------------------------
 
// Set up the mail session/store for IMAP over SSL
Properties props = new Properties()
props.put("mail.store.protocol", "imaps")
props.put("mail.imaps.host", host)
props.put("mail.imaps.port", "993")
 
Session session = Session.getInstance(props)
Store store = session.getStore("imaps")
store.connect(host, username, password)
 
Folder inbox = store.getFolder("INBOX")
 
long startTime = System.currentTimeMillis()
String emailBody = null
 
// Poll the inbox until the reset email arrives or the timeout is reached
while ((System.currentTimeMillis() - startTime) / 1000 < timeoutInSeconds) {
 
	inbox.open(Folder.READ_ONLY)
	Message[] messages = inbox.getMessages()
 
	// Search newest-first for a message matching the expected subject
	for (int i = messages.length - 1; i >= 0; i--) {
 
		if (messages[i].getSubject() != null &&
			messages[i].getSubject().equalsIgnoreCase(expectedSubject)) {
 
			Object content = messages[i].getContent()
 
			if (content instanceof String) {
				emailBody = content
			} else if (content instanceof MimeMultipart) {
				MimeMultipart mp = (MimeMultipart) content
				emailBody = mp.getBodyPart(0).getContent().toString()
			}
			break
		}
	}
 
	inbox.close(false)
 
	if (emailBody != null) break
 
	WebUI.comment("Waiting for email...")
	WebUI.delay(pollInterval)
}
 
// Fail fast if the email never arrived
assert emailBody != null : "Email not received within ${timeoutInSeconds} seconds"
 
// Extract the first URL found in the email body (the password-reset link)
Matcher matcher = Pattern.compile("(https://[^\\s]+)").matcher(emailBody)
assert matcher.find() : "Portal link not found in email"
 
String passwordLink = matcher.group(1)
println("fetched link = " + passwordLink)
 
 
// =====================================================================================
// STEP 10: Open the reset-password link in a new browser session
// =====================================================================================
 
WebUI.openBrowser('')
WebUI.navigateToUrl(passwordLink)
 
// Validate the "Reset Password" page loaded with the expected heading/text
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h1_Reset Your Password'),
	'Reset Password'
)
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Choose a new password to reset account'),
	'Choose a new password to reset account.'
)
 
// Confirm the New Password / Confirm Password fields and criteria panel are present
WebUI.verifyElementPresent(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5'),
	5
)
 
WebUI.verifyElementPresent(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_2c0f9a'),
	5
)
 
WebUI.verifyElementPresent(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Password CriteriaAt least 8 characters _c4ca31'),
	5
)
 
 
// =====================================================================================
// STEP 11: Negative test - submit with empty password fields
// =====================================================================================
 
WebUI.click(findTestObject('Rest Password/Page_Patient Portal/button_Change Password'))
 
WebUI.verifyElementText(
	findTestObject('Rest Password/Page_Patient Portal/div_Password needs to follow criteria'),
	'Password is required'
)
 
 
// =====================================================================================
// STEP 12: Negative test - password that fails the strength criteria
// =====================================================================================
 
WebUI.click(findTestObject('Rest Password/Page_Patient Portal/input_New Password'))
WebUI.setText(findTestObject('Rest Password/Page_Patient Portal/input_New Password'), '1234')
 
WebUI.click(findTestObject('Rest Password/Page_Patient Portal/button_Change Password'))
 
WebUI.verifyElementText(
	findTestObject('Rest Password/Page_Patient Portal/div_Password needs to follow criteria'),
	'Password criteria is not matched'
)
 
 
// =====================================================================================
// STEP 13: Negative test - confirm-password does not match new password
// =====================================================================================

WebUI.click(findTestObject('Rest Password/Page_Patient Portal/input_New Password'))
WebUI.setText(findTestObject('Rest Password/Page_Patient Portal/input_New Password'), 'Abcd@1234')
 
WebUI.click(findTestObject('Rest Password/Page_Patient Portal/input_Confirm Password'))
WebUI.setText(findTestObject('Rest Password/Page_Patient Portal/input_Confirm Password'), 'Abcd@9876')
 
WebUI.verifyElementText(
	findTestObject('Rest Password/Page_Patient Portal/div_Confirm Password does not match the Password'),
	'Confirm Password does not match the Password'
)
 
 
// =====================================================================================
// STEP 14: Negative test - reuse of a previous password (last 3 passwords check)
// =====================================================================================
 
// Enter the OLD (current) password/confirm-password to trigger the reuse check
WebUI.setText(findTestObject('Rest Password/Page_Patient Portal/input_New Password'), GlobalVariable.GV_Password)
WebUI.setText(findTestObject('Rest Password/Page_Patient Portal/input_Confirm Password'), GlobalVariable.GV_Password)
 
// Confirm all password-criteria checklist items are shown as satisfied
WebUI.verifyElementPresent(
	findTestObject('Rest Password/Page_Patient Portal/div_At least 8 characters longAt least 1 upperca'),
	5
)

//Verify color of all lines are changed to green
WebDriver driver = DriverFactory.getWebDriver()

// Expected color
String expectedColor = "rgba(130, 255, 117, 1)"

// Locate all password rule texts
List<WebElement> rules = driver.findElements(
	By.xpath("//div[contains(@class,'d-flex flex-column')]//span")
)

assert rules.size() == 5 : "Expected 5 password rules but found ${rules.size()}"

rules.each { WebElement rule ->

	String text = rule.getText()
	String actualColor = rule.getCssValue("color")

	WebUI.comment("Rule: ${text}")
	WebUI.comment("Color: ${actualColor}")

	assert actualColor == expectedColor :
		"Color mismatch for '${text}'. Expected: ${expectedColor}, Actual: ${actualColor}"
}
 
WebUI.click(findTestObject('Rest Password/Page_Patient Portal/button_Change Password'))
 
// Expect the "matches last 3 passwords" warning since we reused the old password
WebUI.verifyElementText(
	findTestObject('Rest Password/Page_Patient Portal/p_New Password Matches with last 3 Passwords'),
	'New Password Matches with last 3 Passwords.'
)
 
WebUI.click(findTestObject('Rest Password/Page_Patient Portal/button_Ok'))
 
 
// =====================================================================================
// STEP 15: Positive path - set a brand-new, valid password
// =====================================================================================
 
WebUI.setText(findTestObject('Rest Password/Page_Patient Portal/input_New Password'), GlobalVariable.UpdatePassword)
WebUI.setText(findTestObject('Rest Password/Page_Patient Portal/input_Confirm Password'), GlobalVariable.UpdatePassword)
 
WebUI.click(findTestObject('Rest Password/Page_Patient Portal/button_Change Password'))
 
// Confirm the success message and instructions are displayed
WebUI.verifyElementText(
	findTestObject('Rest Password/Page_Patient Portal/h2_Password Updated'),
	'Password Updated'
)
 
WebUI.verifyElementText(
	findTestObject('Rest Password/Page_Patient Portal/p_Sign in to your account using your username an'),
	'Sign in to your account using your username and updated password.'
)
 
 
// =====================================================================================
// STEP 16: Log in with the updated credentials
// =====================================================================================
 
WebUI.setText(findTestObject('Rest Password/Page_Patient Portal/input_Username'), GlobalVariable.GV_Username)
WebUI.setText(findTestObject('Rest Password/Page_Patient Portal/input_Password'), GlobalVariable.UpdatePassword)
 
WebUI.click(findTestObject('Rest Password/Page_Patient Portal/button_signInButton'))
 
// Handle the Date-of-Birth confirmation and Terms & Conditions acceptance step
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
 
// =====================================================================================
// STEP 17: Retrieve and enter the email-based OTP for verification
// =====================================================================================
 
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)
 
println('OTP fetched = ' + otp)
 
// Split the 4-digit OTP into individual characters for the 4 separate input boxes
String[] digits = otp.toCharArray()
 
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1'), (digits[0]).toString())
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2'), (digits[1]).toString())
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3'), (digits[2]).toString())
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'), (digits[3]).toString())
 
WebUI.delay(5)
 
// Wait for the "Proceed" button to become clickable after OTP entry, then click it
TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
 
WebUI.delay(2)
 
 
// =====================================================================================
// STEP 18: Final verification - confirm patient dashboard loads with correct details
// =====================================================================================
 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'),
	[
		('Firstname') : GlobalVariable.PatientFirstName,
		('Lastname')  : GlobalVariable.PatientLastName,
	],
	FailureHandling.STOP_ON_FAILURE
)