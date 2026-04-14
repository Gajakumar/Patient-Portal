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

//
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

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)


WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)

println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password

WebUI.delay(5)


//Login to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Username'),
	'Forgot Username?')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Password'),
	'Forgot Password?')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Password'))

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h1_Recover your Username'),
	'Recover your account')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_We can help you reset your Username'),
	'We can help you reset your password.')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Follow the instruction below'),
	'Follow the instruction below.')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'),
	'Refresh')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Input symbols'),
	'Input symbols')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Next'))

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Email ID required'),
	'Username required')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Cancel'))

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'),
	'Sign In')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Password'))


WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Refresh_form-control py-2 border-danger_3'),
	'1234')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Next'))

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Captcha does not match'),
	'Captcha does not match')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/svg_Email Address_text-dark'))

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Email Address_form-control ps-5 py-2 _415fae'),
	GlobalVariable.GV_Username)

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'))



// Get captcha text
String captchaText = WebUI.getText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Captcha Code'))

// Clean it (just in case)
captchaText = captchaText.trim()

if (captchaText == null || captchaText.length() != 4) {
	WebUI.click(findTestObject('Page_Login/button_RefreshCaptcha'))
	WebUI.delay(1)
	captchaText = WebUI.getText(findTestObject('Page_Login/div_CaptchaText')).trim()
}

// Enter captcha into input field
WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Refresh_form-control py-2 border-danger_3'), captchaText)


WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Next'))


WebUI.delay(8)

// ================= CONFIG =================
String host = "imap.gmail.com"
String username = GlobalVariable.MyEmail_Id
String password = GlobalVariable.Email_Key
String expectedSubject = "Reset Password for Patient portal"

int timeoutInSeconds = 180
int pollInterval = 10
// ==========================================

// Mail session
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

while ((System.currentTimeMillis() - startTime) / 1000 < timeoutInSeconds) {

	inbox.open(Folder.READ_ONLY)
	Message[] messages = inbox.getMessages()

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

// ================= ASSERT =================
assert emailBody != null : "Email not received within ${timeoutInSeconds} seconds"

// ================= EXTRACT LINK =================
Matcher matcher = Pattern.compile("(https://[^\\s]+)").matcher(emailBody)
assert matcher.find() : "Portal link not found in email"

String passwordLink = matcher.group(1)
println("fetched link = " + passwordLink)
// ================= OPEN LINK =================
WebUI.openBrowser('')
WebUI.navigateToUrl(passwordLink)

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h1_Reset Your Password'),
	'Reset Your Password')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Choose a new password to reset account'),
	'Choose a new password to reset account.')

WebUI.verifyElementPresent(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5'),
	0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_2c0f9a'),
	0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Password CriteriaAt least 8 characters _c4ca31'),
	0)

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Confirm'))

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Please meet all password criteria'),
	'Please meet all password criteria')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Password needs to follow criteria'),
	'Password needs to follow criteria')

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5_15'),
	'1234')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Confirm'))

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Password needs to follow criteria'),
	'Password needs to follow criteria')

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5_15'),
	'Test@1234')

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5_11'),
	'1234@Test')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Confirm'))

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Passwords do not match'),
	'Passwords do not match')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Confirm Password does not match the Password'),
	'Confirm Password does not match the Password')

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5_15'),
	GlobalVariable.GV_Password)

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_2c0f9a_1'),
	GlobalVariable.GV_Password)

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Confirm'))

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast'),
	'New Password Matches with last 3 Passwords.')

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5_15'),
	GlobalVariable.UpdatePassword)

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_2c0f9a_1'),
	GlobalVariable.UpdatePassword)

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Confirm'))

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Password reset successfully'),
	'Password reset successfully')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Sign In'),
	'Sign In')

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/UpdatePass'), GlobalVariable.RestUpdatedPass)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmPass'), GlobalVariable.RestUpdatedPass)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/ProccedBtnAfterConifrmPass'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp)


// Auto type into four input boxes
String[] digits = otp.toCharArray()

WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)




