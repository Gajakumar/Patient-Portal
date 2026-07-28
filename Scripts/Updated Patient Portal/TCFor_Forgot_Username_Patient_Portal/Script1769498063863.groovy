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
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
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
// STEP 4: Navigate to the login page and open "Forgot Username"
// =====================================================================================
 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// Click Sign In button to reveal the login form / forgot links
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
 
// Verify the "Forgot Username?" and "Forgot Password?" links are present with correct text
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Username'),
	'Forgot Username?'
)
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Password'),
	'Forgot Password?'
)
 
// Click "Forgot Username?" to begin the recovery flow
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Username'))
 
 
// =====================================================================================
// STEP 5: Validate static text on the "Recover your Username" page
// =====================================================================================
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h1_Recover your Username'),
	'Recover your Username'
)
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_We can help you reset your Username'),
	'We can help you reset your Username.'
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
// STEP 6: Negative test - submit with no email entered
// =====================================================================================
 
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Next'))
 
// Expect a validation error since the email field was left blank
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Email ID required'),
	'Email ID is required'
)
 
// Cancel out and confirm we return to the Sign In page
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Cancel'))
 
WebUI.verifyElementText(
	findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'),
	'Sign In'
)
 
 
// =====================================================================================
// STEP 7: Negative test - submit with an invalid email format
// =====================================================================================
 
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Username'))
 
// Enter a malformed email address
WebUI.setText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Email Address_form-control ps-5 py-2 _415fae'),
	'test'
)
 
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Next'))
 
// Expect an "invalid email" validation error
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Please enter valid Email'),
	'Please enter valid Email'
)
 
 
// =====================================================================================
// STEP 8: Negative test - submit with an incorrect captcha value
// =====================================================================================
 
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Email Address_form-control ps-5 py-2 _415fae'))
 
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
 
// Cancel out and start the flow fresh
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Cancel'))
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Username'))
 
 
// =====================================================================================
// STEP 9: Negative test - submit with a valid email but no captcha entered
// =====================================================================================
 
WebUI.setText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Email Address_form-control ps-5 py-2 _415fae'),
	'gajakumara@first-insight.com'
)
 
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'))
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Next'))
 
// Expect a "please enter the captcha" validation error
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Captcha does not match'),
	'Please enter the captcha'
)
 
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'))
 
 
// =====================================================================================
// STEP 10: Verify the captcha image actually changes on refresh
// =====================================================================================
 
TestObject captchaObj = findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Captcha Code')
TestObject refreshBtn = findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh')
 
// Capture the current captcha value
String oldCaptcha = WebUI.getText(captchaObj)?.trim()
assert oldCaptcha != null && oldCaptcha.length() > 0
println("Old Captcha: " + oldCaptcha)
 
// Click refresh to generate a new captcha
WebUI.click(refreshBtn)
 
// Poll until the captcha value changes (or we exhaust retry attempts)
String newCaptcha = ""
int maxAttempts = 10
int attempt = 0
 
while (attempt < maxAttempts) {
	WebUI.delay(1)
 
	newCaptcha = WebUI.getText(captchaObj)?.trim()
 
	if (newCaptcha != null && newCaptcha != oldCaptcha) {
		break
	}
 
	attempt++
}
 
// Assertion 1: Captcha must have changed after refresh
assert newCaptcha != oldCaptcha
 
// Assertion 2: New captcha must be a valid 4-character value
assert newCaptcha != null && newCaptcha.length() == 4
 
println("New Captcha: " + newCaptcha)
 
 
// =====================================================================================
// STEP 11: Positive path - enter valid email and correct captcha
// =====================================================================================
 
// Read the captcha text currently displayed on the page
String captchaText = WebUI.getText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Captcha Code'))
captchaText = captchaText.trim()
 
// If the captcha didn't render correctly (not exactly 4 characters), refresh and retry once
if (captchaText == null || captchaText.length() != 4) {
	WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'))
	WebUI.delay(1)
	captchaText = WebUI.getText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Captcha Code')).trim()
}
 
// Enter the (valid) captcha text into the input field
WebUI.setText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Refresh_form-control py-2 border-danger_3'),
	captchaText
)
 
// Submit the request to proceed to DOB confirmation
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Next'))
 
 
// =====================================================================================
// STEP 12: DOB confirmation and "Verify & Send Username"
// =====================================================================================
 
// NOTE: Legacy conditional block kept for reference - superseded by the direct
// entry + JS-click approach below, which proved more reliable in practice.
//
//TestObject verifyAndSendUn = findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/button_Verify  Send Username')
//
//if (WebUI.verifyElementPresent(verifyAndSendUn, 10, FailureHandling.OPTIONAL)) {
//
//	WebUI.setText(findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/input_First Name_1'), GlobalVariable.PatientFirstName)
//
//	WebUI.sendKeys(findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/input_MM_DD_YYYY'),
//	    GlobalVariable.DOB)
//	TestObject closeBtn = findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/button_CLOSE')
//
//	if (WebUI.verifyElementPresent(closeBtn, 10, FailureHandling.OPTIONAL)) {
//
//		WebUI.scrollToElement(closeBtn, 2)
//		WebUI.waitForElementVisible(closeBtn, 10)
//		WebUI.click(closeBtn)
//	}
//
//	//Click on send username
//	WebUI.click(findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/button_Verify  Send Username'))
//}
 
// Enter patient first name and date of birth to confirm identity
WebUI.setText(findTestObject('PatientPortal/Page_Patient Portal/input_First Name'), GlobalVariable.PatientFirstName)
WebUI.setText(findTestObject('PatientPortal/Page_Patient Portal/input_MM_DD_YYYY'), GlobalVariable.DOB)
 
// Click "Verify & Send Username" via JS click (native WebUI.click was unreliable
// for this button, likely due to overlay/positioning of its nested wrapper divs)
WebElement verifySendUsernameBtn = WebUI.findWebElement(findTestObject('PatientPortal/Page_Patient Portal/button_Verify  Send Username'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(verifySendUsernameBtn))
WebUI.delay(5)
 
 
// =====================================================================================
// STEP 13: Poll Gmail via IMAP for the username-recovery email and extract the link
// =====================================================================================
// (Also covers the "multiple account exists" scenario, if applicable)
 
// ----- IMAP configuration -----
String host = "imap.gmail.com"
String username = GlobalVariable.MyEmail_Id
String password = GlobalVariable.Email_Key
String expectedSubject = "Username for Patient portal"
 
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
 
// Poll the inbox until the username-recovery email arrives or the timeout is reached
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
 
// Extract the first URL found in the email body (the username-recovery link)
Matcher matcher = Pattern.compile("(https://[^\\s]+)").matcher(emailBody)
assert matcher.find() : "Portal link not found in email"
 
String portalLink = matcher.group(1)
println("fetched link = " + portalLink)
 
 
// =====================================================================================
// STEP 14: Open the recovery link and validate the "Your Username" page
// =====================================================================================
 
WebUI.openBrowser('')
WebUI.navigateToUrl(portalLink)
 
// Confirm the "Your Username" heading is displayed
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h1_Your Username'),
	'Your Username'
)
 
// Capture the recovered username for use in later login steps
GlobalVariable.updatedUsername = WebUI.getText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Your username is 0316Den31')
).trim()
 
println("fetched Username = " + GlobalVariable.updatedUsername)
 
// Verify supporting text on the page
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_You can change your username once you'),
	'You can change your username once you'
)
 
// Verify the "Sign In" prompt is displayed
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Sign In'),
	'Sign In.'
)
 
// Click through to the Sign In page
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Sign In Button on Update UN'))
 
// Confirm the Sign In page loaded
WebUI.verifyElementPresent(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Sign In_1'),
	5
)
 
 
// =====================================================================================
// STEP 15: Negative test - sign in with an incorrect username and password
// =====================================================================================
 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[
		('Username') : "Test565",
		('Password') : "Test565",
	],
	FailureHandling.STOP_ON_FAILURE
)
 
// Expect a "Sign In attempt failed" error
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h2_Sign In attempt failed'),
	'Sign In attempt failed'
)
 
WebUI.verifyElementText(
	findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_The username or password entered is invalid'),
	'The username or password entered is invalid.'
)
 
 
// =====================================================================================
// STEP 16: Positive path - sign in with the recovered username and original password
// =====================================================================================
 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[
		('Username') : GlobalVariable.updatedUsername,
		('Password') : GlobalVariable.GV_Password,
	],
	FailureHandling.STOP_ON_FAILURE
)
 
// Confirm Date of Birth and accept Terms & Conditions
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
 
// =====================================================================================
// STEP 17: OTP negative-scenario checks, then retrieve and enter the valid OTP
// =====================================================================================
 
// Validate negative OTP scenarios and the "resend" flow
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/OTP Negative Scenario and Resend Verification'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
WebUI.delay(10)
 
// Retrieve the OTP from the verification email
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)
 
println("OTP fetched = " + otp)
 
// Split the 4-digit OTP into individual characters for the 4 separate input boxes
String[] digits = otp.toCharArray()
 
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())
 
WebUI.delay(5)
 
// Wait for the "Proceed" button to become clickable after OTP entry, then click it
TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)
 
 
// =====================================================================================
// STEP 18: Update the existing password, then log in again with the new password
// =====================================================================================
 
// Update the account password
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
 
// Log back in using the original username and the newly updated password
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[
		('Username') : GlobalVariable.GV_Username,
		('Password') : GlobalVariable.UpdatePassword,
	],
	FailureHandling.STOP_ON_FAILURE
)
 
 
// =====================================================================================
// STEP 19: Retrieve and enter the OTP for the post-password-update login
// =====================================================================================
 
String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)
 
println("OTP fetched = " + otp1)
 
// Split the 4-digit OTP into individual characters for the 4 separate input boxes
String[] digits1 = otp1.toCharArray()
 
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits1[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits1[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits1[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits1[3].toString())
 
// Wait for the "Proceed" button to become clickable after OTP entry, then click it
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)
 
 
// =====================================================================================
// STEP 20: Final verification - confirm patient dashboard loads with correct details
// =====================================================================================
 
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'),
	[
		('Firstname') : GlobalVariable.PatientFirstName,
		('Lastname')  : GlobalVariable.PatientLastName,
	],
	FailureHandling.STOP_ON_FAILURE
)