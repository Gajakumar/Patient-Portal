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

//Login to maximeyes
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

//Patient portal sign up using email
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Portal Sign up using email'), [:], FailureHandling.STOP_ON_FAILURE)


//Navigate to Patient Portal Site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Get username and password from email
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

//Verify Forgot Username? text
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Username'), 
    'Forgot Username?')

//Verify Forgot Password? text
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Password'), 
    'Forgot Password?')

//Click on Forgot Username
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Username'))

//Verify Recover Username page is open
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h1_Recover your Username'), 
    'Recover your Username')

//Verify text on the page
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_We can help you reset your Username'), 
    'We can help you reset your Username.')

//Verify text on the page
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Follow the instruction below'), 
    'Follow the instruction below.')

//Verify Refresh button is present
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'), 
    'Refresh')

//Verify text on the page
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Input symbols'), 
    'Input symbols')

//Click on Next button
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Next'))

//Verify error message
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Email ID required'), 
    'Email ID is required')

//Click on Cancel button
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Cancel'))

//Verify sign in page displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'), 
    'Sign In')

//Click on forgot username
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/a_Forgot Username'))

//Enter invalid email id
WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Email Address_form-control ps-5 py-2 _415fae'), 
    'test')

//Click on next button
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Next'))

//Verify error message
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Please enter valid Email'), 
    'Please enter valid Email')

//Click on captcha input
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Email Address_form-control ps-5 py-2 _415fae'))

//Enter wrong captcha
WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Refresh_form-control py-2 border-danger_3'), 
    '1234')

//Click on next button
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Next'))

//Verify error message
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Captcha does not match'), 
    'Captcha does not match')

//click on email input
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/svg_Email Address_text-dark'))

//enter email id
WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Email Address_form-control ps-5 py-2 _415fae'), 
    'gajakumara@first-insight.com')

//click on refresh button
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'))

//Click on next button
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Next'))

//Verify error message
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Captcha does not match'),
	'Please enter the captcha')

//click on refresh button
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'))



// Step 1: Get old captcha
TestObject captchaObj = findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Captcha Code')
TestObject refreshBtn = findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh')

String oldCaptcha = WebUI.getText(captchaObj)?.trim()

assert oldCaptcha != null && oldCaptcha.length() > 0
println("Old Captcha: " + oldCaptcha)

//Step 2: Click refresh
WebUI.click(refreshBtn)


// Step 3: Wait until captcha updates (retry logic)
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

// ✅ Assertion 1: Captcha must change
assert newCaptcha != oldCaptcha

// ✅ Assertion 2: Captcha should be valid (length check)
assert newCaptcha != null && newCaptcha.length() == 4

println("New Captcha: " + newCaptcha)



// Get captcha text
String captchaText = WebUI.getText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Captcha Code'))

// Clean it (just in case)
captchaText = captchaText.trim()

if (captchaText == null || captchaText.length() != 4) {
	WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Refresh'))
	WebUI.delay(1)
	captchaText = WebUI.getText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Captcha Code')).trim()
}

// Enter captcha into input field
WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Refresh_form-control py-2 border-danger_3'), captchaText)

//Click on next button
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Next'))

//Verify and send username after DOB match
TestObject verifyAndSendUn = findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/button_Verify  Send Username')

if (WebUI.verifyElementPresent(verifyAndSendUn, 10, FailureHandling.OPTIONAL)) {
	
	WebUI.setText(findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/input_First Name_1'), GlobalVariable.PatientFirstName)

WebUI.sendKeys(findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/input_MM_DD_YYYY'), 
    GlobalVariable.DOB)
TestObject closeBtn = findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/button_CLOSE')

if (WebUI.verifyElementPresent(closeBtn, 10, FailureHandling.OPTIONAL)) {
	
	WebUI.scrollToElement(closeBtn, 2)
	WebUI.waitForElementVisible(closeBtn, 10)
	WebUI.click(closeBtn)
}

//Click on send username
WebUI.click(findTestObject('PatientPortal/SignInPage_Patient Portal/Forgot Username/Page_Patient Portal/button_Verify  Send Username'))
}

WebUI.delay(5)

//get reset username link from email
//================= CONFIG =================
String host = "imap.gmail.com"
String username = GlobalVariable.MyEmail_Id
String password = GlobalVariable.Email_Key
String expectedSubject = "Username for Patient portal"

int timeoutInSeconds = 180
int pollInterval = 10
//==========================================

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

String portalLink = matcher.group(1)
println("fetched link = " + portalLink)
// ================= OPEN RESET PASSWORD LINK =================
WebUI.openBrowser('')
WebUI.navigateToUrl(portalLink)

//Verify username field
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h1_Your Username'),
	'Your Username')


// Get username text
GlobalVariable.updatedUsername = WebUI.getText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Your username is 0316Den31')).trim()

println("fetched Username = " + GlobalVariable.updatedUsername)

//verify text on the page
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_You can change your username once you'),
	'You can change your username once you')

//Verify sign in button is displayed
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_Sign In'),
	'Sign In.')

//click on sign in button
WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/Sign In Button on Update UN'))

//verify sign in page displayed
WebUI.verifyElementPresent(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Sign In_1'),
	5)

//Wrong Username & Wrong Pass
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : "Test565", ('Password') : "Test565"], FailureHandling.STOP_ON_FAILURE)

//Verify error message
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/h2_Sign In attempt failed'),
	'Sign In attempt failed')

//Verify error message
WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/p_The username or password entered is invalid'),
	'The username or password entered is invalid.')

//Enter valid username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.updatedUsername, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Confirmation DOB and Accept Terms
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

//Update existing password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//Login with updated password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//OTP negative scenario verification
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/OTP Negative Scenario and Resend Verification'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Get otp from the mail
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp)


//Auto type into four input boxes
String[] digits = otp.toCharArray()

//Enter OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the Procced button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)

//Verify username, date and time on Dashboard
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)
