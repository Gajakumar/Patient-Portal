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
import java.util.Random
import org.apache.commons.lang.RandomStringUtils
import utils.GmailVerifier
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import java.time.LocalDate
import java.time.ZoneId
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import email.EmailVerification
import utils.EmailUtils
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

Random randm = new Random()

String firstName = RandomStringUtils.randomAlphabetic(8).capitalize()
String lastName  = RandomStringUtils.randomAlphabetic(10).capitalize()

// -------- Random US Mobile (10 digits) --------
int areaCode = 200 + randm.nextInt(800)
int prefix   = 200 + randm.nextInt(800)
int lineNum  = 1000 + randm.nextInt(9000)

String mobilePlain = "${areaCode}${prefix}${lineNum}"
String mobileFormatted = String.format("(%03d) %03d-%04d", areaCode, prefix, lineNum)

// -------- Random Email --------
//String email = "gajakumara+007@first-insight.com"
int randomNum = new Random().nextInt(1000)
String threeDigit = String.format("%03d", randomNum)

String email = "gajakumara+" + threeDigit + "@first-insight.com"

println(email)


//Navigate to Patient Portal Site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Login with username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : userName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Get OTP from email
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

//Enter OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the proceed button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//Click on Setting button on dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Select Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

//Verify Authorized Individuals Screen is open
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_Authorized Individuals'), 'Authorized Individuals')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Authorized Individuals will have full access t'),
	'Authorized Individuals will have full access to your Patient Portal account. They can view health record, send message to your provider, make payments or schedule appointment on your behalf. Invite only whom you trust.')

//Verify fields name on Authorized Individuals
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h4_Access to My Portal'), 'Access to My Portal')

//Verify fields name on Authorized Individuals
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/th_Name'), 'Name')

//Verify fields name on Authorized Individuals
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/th_Expires'), 'Expires')

//Verify fields name on Authorized Individuals
//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/th_Actions'), 'Actions')


//Verify and delete Available auths
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Delete Available Auths'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Add Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Add authorized individual'))

//Verify Add Authorized Individual page opens
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h3_Add Authorized Individual'), 'Add Authorized Individual')

//Mouse hover on info icon
WebUI.mouseOver(findTestObject('Authorized Individual/Page_Patient Portal/path_icon'))

//Verify fields present on page
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), '')

//Verify fields present on page
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), '')

//Verify fields present on page
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_Enter value'), '')

//Verify fields present on page
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), '')

//Verify fields present on page
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_MM_DD_YYYY'), '')

//Verify fields present on page
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/textarea_Reason'), '')

//Verify fields present on page
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'), 'Proceed')

//Click on Procced button
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_First Name is required'), 'First Name is required!')

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Last Name is required'), 'Last Name is required!')

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Mobile format is invalid'), 'Mobile is required')

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Email format is invalid'), 'Email is required')

//Add Invalid Mobile
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), '(123')

//Click on mobile field
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/main_Add Authorized IndividualAdd Authorized Ind'))

//Click on Procced button
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Mobile format is invalid'), 'Mobile format is invalid!')

//Enter invalid email
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), '@gmail.com')

//Click on Procced button
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Email format is invalid'), 'Email format is invalid!')

//Enter invalid email
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email_1'), 'yrfdf')

//Click on Procced button
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Email format is invalid'), 'Email format is invalid!')

//Enter valid first name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), firstName)

//Enter valid last name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), lastName)

//Enter valid mobile
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), mobileFormatted)

//Enter valid email
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), email)

//click on Expiration Date calendar icon
WebUI.click(findTestObject('Object Repository/Authorized Individual/Auth User Sign Up/Page_Patient Portal/Expiration Date Calendar icon'))

//Verify previous date entry is disabled
WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Authorized Individual/Auth User Sign Up/Page_Patient Portal/Prev arrow on calendar'), 'disabled', 5)

//Click on Close button on calendar
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/button_CLOSE'))

// Get current date in GMT and add 5 days
ZonedDateTime gmtDate = ZonedDateTime.now(ZoneId.of("GMT")).plusDays(5)

// Format as MM/dd/yyyy
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
String formattedDate = gmtDate.format(formatter)

// Set value in field
WebUI.sendKeys(findTestObject('Authorized Individual/Page_Patient Portal/td_02_18_2026'), formattedDate)

println("Date entered: " + formattedDate)

//Click on Procced button
TestObject proccedButton = findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1')

if (WebUI.verifyElementPresent(proccedButton, 10, FailureHandling.OPTIONAL)) {
	
	WebUI.click(proccedButton)
}

//Click on Home icon
TestObject homeBtn = findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal')

WebUI.waitForElementVisible(homeBtn, 30)
WebUI.waitForElementClickable(homeBtn, 30)
WebUI.click(homeBtn)

//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

String name = firstName +" "+ lastName

//Verify and get activation link from Authorized email
String activationLink = CustomKeywords.'email.EmailVerification.verifyAccessEmailsWithPolling'(
	"imap.gmail.com",
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	name,
	mobilePlain,
	email,
	GlobalVariable.Sender_Email,
	120   // timeout in seconds
)

println("Activation Link: " + activationLink)

//Verify email from User email
CustomKeywords.'utils.EmailUtils.verifyAccessGrantEmail'(
"imap.gmail.com",
GlobalVariable.MyEmail_Id,
GlobalVariable.Email_Key,
name,
mobilePlain,
email
)



//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Click on Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

//Verify added auth is displayed
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'), firstName +" "+ lastName)

//Verify orange info icon is not present in front of name
WebUI.verifyElementPresent(findTestObject('Object Repository/Authorized Individual/Page_Patient Portal/Not Signed Up icon'), 2)

//Verify Exp date
WebUI.verifyElementText(findTestObject('Object Repository/Authorized Individual/Page_Patient Portal/td_04232026'), formattedDate)

//Click on name link
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'))

String last4 = mobilePlain.substring(mobilePlain.length() - 4)

//Verify Permissions screen is open
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/h2_Permissions'), 'Permissions')

//Verify fields on Permission screen
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/h2_Authorized Individual'),
	'Authorized Individual')

//Verify name on Permission screen
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/p_Name_ EBCHzKcV SAdeaQaEDU'),
	'Name: '+firstName +" "+ lastName)


// Verify masked format like: Phone: XXXXXX1234
//WebUI.verifyMatch(phoneText, "Phone:\\s*X+${last4}", true)

//Verify Phone field on Permission screen
String phoneText = WebUI.getText(findTestObject(
	'Object Repository/Authorized Individual/Permissions/Page_Patient Portal/p_Phone_ XXXXXX9406'))

println("Actual UI Phone: " + phoneText)

String expectedPhone = "Phone: ${mobilePlain.substring(0,2)}*${mobilePlain.substring(3,5)}*${mobilePlain.substring(6,7)}*${mobilePlain.substring(8,10)}"
WebUI.verifyEqual(phoneText, expectedPhone)

////Verify masked email on Permission screen
//String firstLetter = email.substring(0,1)
//String lastCharBeforeAt = email.substring(email.indexOf('@') - 1, email.indexOf('@'))
//String domain = email.substring(email.indexOf('@'))
//
//String emailText = WebUI.getText(findTestObject('Object Repository/Authorized Individual/Permissions/Page_Patient Portal/p_Email_ gxxxxxxxxxx1first-insight.com'))
//
//WebUI.verifyMatch(emailText,
//	"Email:\\s*${firstLetter}x+${lastCharBeforeAt}${domain.replace('.', '\\.')}",
//	true)


//// Verify masked email on Permission screen
//String[] parts = email.split('@')
//String localPart = parts[0]
//String domain = parts[1]
//
//// Local part: keep first 2 characters, rest masked
//String maskedLocal = localPart.substring(0, 2) + "\\*+"
//
//// Mask the domain according to the application's rule:
//// Keep first 2 characters of each domain segment, mask the rest.
//String maskedDomain = domain.split("\\.").collect { segment ->
//	if (segment.length() <= 2) {
//		return segment
//	}
//	return segment.substring(0, 2) + "\\*".repeat(segment.length() - 2)
//}.join("\\.")
//
//String emailText = WebUI.getText(findTestObject(
//	'Object Repository/Authorized Individual/Permissions/Page_Patient Portal/p_Email_ gxxxxxxxxxx1first-insight.com'))
//
//WebUI.verifyMatch(
//	emailText,
//	"Email:\\s*${maskedLocal}@${maskedDomain}",
//	true
//)

String emailText = WebUI.getText(findTestObject('Object Repository/Authorized Individual/Permissions/Page_Patient Portal/p_Email_ gxxxxxxxxxx1first-insight.com'))

String maskEmail(String email) {
	String[] parts = email.split("@")
	String local = parts[0]

	// Mask local part (keep first 2 characters)
	String maskedLocal = local.substring(0, 2) + "*" * (local.length() - 2)

	// Since the domain is always first-insight.com
	String maskedDomain = "fi**t-**si**t*.com"

	return "${maskedLocal}@${maskedDomain}"
}

String expectedEmail = "Email: " + maskEmail(email)

println(expectedEmail)

// Verify
WebUI.verifyEqual(emailText, expectedEmail)

//Verify date added text
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/span_Pending'), 'Pending Signup')

//Verify portal access
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/h4_Portal Access'), 'Portal Access')

//Verify portal access checkboxes are by default checked
WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined'), 5)
WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_1'), 5)
WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_2'), 5)
WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_3'), 5)
WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_4'), 5)

//Verify Expairation date
WebUI.verifyElementAttributeValue(
    findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_MM_DD_YYYY'),
    'value',
    formattedDate,
    10
)

//Verify buttons on the Permission screen
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/div_Resend Signup Email'),
	'Resend Email')

//Verify buttons on the Permission screen
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Cancel'), 'Cancel')

//Verify buttons on the Permission screen
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save'), 'Save')

//Click on Resend Signup Email button
WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Resend Signup Email'))

WebUI.delay(10)

//Get activation link from email
String activationLink1 = CustomKeywords.'email.EmailVerification.verifyAccessEmailsWithPolling'(
	"imap.gmail.com",
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	name,
	mobilePlain,
	email,
	GlobalVariable.Sender_Email,
	120   // timeout in seconds
)

println("Activation Link: " + activationLink1)

//Verify email from User email
CustomKeywords.'utils.EmailUtils.verifyAccessGrantEmail'(
"imap.gmail.com",
GlobalVariable.MyEmail_Id,
GlobalVariable.Email_Key,
name,
mobilePlain,
email
)

//Click on save button on the Permission screen
WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save'))

//Verify confirmation popup
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/p_Do you want to update access permissions for t'),
	'Do you want to update access permissions for the selected Authorized Individual?')

//click on yes button
WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save_1'))

//Click on name link
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'))

// ==========================
// UNCHECK & SAVE PERMISSIONS
// ==========================

TestObject permission1 = findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_1')

//Uncheck the permission									
WebUI.uncheck(permission1)

//Click on Save button
WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save'))

//Verify confirmation popup
WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/p_Do you want to update access permissions for t'),
	'Do you want to update access permissions for the selected Authorized Individual?')

//Click on save
WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save_1'))


// ==========================
// RE-OPEN & VERIFY PERSISTENCE
// ==========================

// Wait for redirect to list
WebUI.waitForElementPresent(
		findTestObject('Authorized Individual/Page_Patient Portal/h2_Authorized Individuals'),
		10
)

// Click same Authorized Individual again
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'))

// Wait for permissions page
WebUI.waitForElementPresent(
		findTestObject('Authorized Individual/Permissions/Page_Patient Portal/h2_Permissions'),
		10
)

// 🔎 VERIFY CHECKBOX IS STILL UNCHECKED
WebUI.verifyElementNotChecked(permission1, 5)

println("✅ Permission successfully persisted as UNCHECKED")

//Click on save button
WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save'))

//Click on yes button on popup
WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save_1'))

//================================================

// Open new tab
WebUI.executeJavaScript("window.open('about:blank','_blank');", [])

//Switch to new tab
WebUI.switchToWindowIndex(1)

//navigate to received activation link
WebUI.navigateToUrl(activationLink1)

//>>>>>> as per new implimentation Terms Of Service Page page is removed
//WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'), 10)
//
////Do not Accept Terms and click on Procced button
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))
//
////Verify Please Accpet Terms toast display
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Terms Of Service Page/Please Accept Terms Alart'),AcceptTermToast)
//
//WebUI.delay(3)
//
////Accept Terms check box
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))
//
////Do not enter sign and click on Procced button
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))
//
//////Verify Please Enter Sign toast display
////WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Add Your Sign Toast'),AddSignToast)
//
//TestObject toast = findTestObject(
//	'Object Repository/PatientPortal/SignInPage_Patient Portal/Add Your Sign Toast'
//)
//
//WebUI.waitForElementPresent(toast, 10)
//
//String toastText = WebUI.getText(toast).trim()
//println "Toast found: " + toastText
//
//WebUI.verifyMatch(toastText, AddSignToast, false)
//
//WebUI.delay(3)
//
////Uncheck Accpet Term check box
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))
//
////Add Signature
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Add Signature On Canvas'),[:], FailureHandling.STOP_ON_FAILURE)
//
////Click on Procced button
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))
//
////Verify Please Accpet Terms toast display
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Terms Of Service Page/Accpet Terms of Service Toast'),AcceptTermToast)
//
//WebUI.delay(3)
//
////Accept Terms check box
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))
//
////Click on Procced button
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))

////OTP negative scenario verification
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/OTP Negative Scenario and Resend Verification'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Get OTP from email
String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp1)


// Auto type into four input boxes
String[] digits1 = otp1.toCharArray()

//Enter OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits1[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits1[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits1[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits1[3].toString())

WebUI.delay(5)


// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//Verify Create Credential Text is visible
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h1_Create Credentials'),
	'Create Credentials')

//Verify Choose a unique Username text is visible
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h2_Choose a unique Username'),
	'Choose a unique Username')

//Verify Choose a new Password
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h2_Choose a new Password'),
	'Choose a new Password')

//enter username
WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Username'), firstName)

//enter password
WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password'),GlobalVariable.RestUpdatedPass )

//confirm password
WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Confirm Password'),GlobalVariable.RestUpdatedPass)

//click on procced button
//WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebElement proccedBtnUpdtPass = WebUI.findWebElement(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedBtnUpdtPass))

//verify sign up completed text
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h1_Sign Up Completed'),
	'Sign Up Completed')

//verify sign up completed text
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_pageTitle'), 'Sign up completed')

//click on procced button
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

//Login with username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : firstName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Get OTP
String otp2 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp2)


// Auto type into four input boxes
String[] digits2 = otp2.toCharArray()

//Enter otp
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits2[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits2[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits2[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits2[3].toString())

WebUI.delay(5)

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//Verify username
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h3_HvIwLoIs DCgghJzngx'),firstName +" "+ lastName)

//Click on procced button
WebUI.verifyElementNotClickable(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

//Click on select user
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_Select User'))

//Select John Doe user
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/li_John Doe'))

//Click on procced button
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

//toast is displaying before page load
//TestObject ptNameToast = new TestObject()
//ptNameToast.addProperty(
//    "xpath",
//    com.kms.katalon.core.testobject.ConditionType.EQUALS,
//    "(//p[contains(@class,'text-blue-700') and contains(.,'You are viewing record')])[2]"
//)
//
//
//String toastTextA = ''
//boolean found = false
//
//// FAST polling (critical)
//for (int i = 0; i < 30; i++) {
//    try {
//        if (WebUI.verifyElementPresent(ptNameToast, 0, FailureHandling.OPTIONAL)) {
//            toastTextA = WebUI.getText(ptNameToast).trim()
//            
//            if (!toastTextA.isEmpty()) {
//                found = true
//                break
//            }
//        }
//    } catch (Exception e) {}
//
//    WebUI.delay(0.2)  // fast retry
//}
//
//println("Toast Message: " + toastTextA)
//
//assert found
//assert toastTextA.contains("You are viewing record")

//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Verify links are disbled
CustomKeywords.'common.UIAssertions.verifyElementDisabled'(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_Profile')
)

CustomKeywords.'common.UIAssertions.verifyElementDisabled'(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_Communication Preferences')
)

CustomKeywords.'common.UIAssertions.verifyElementDisabled'(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_Authorized Individuals')
)

CustomKeywords.'common.UIAssertions.verifyElementDisabled'(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_Opt Out')
	
)

//Navigate to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Login with username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : userName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Get OTP
String otpA = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otpA)


// Auto type into four input boxes
String[] digitsA = otpA.toCharArray()

//Enter OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digitsA[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digitsA[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digitsA[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digitsA[3].toString())

WebUI.delay(5)

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Click on Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

//Verify orange info icon is not present in front of name
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Authorized Individual/Page_Patient Portal/Not Signed info icon'), 2)

//Click on name link
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'))

//Verify resend button is not present
WebUI.verifyElementNotPresent(findTestObject('Authorized Individual/Page_Patient Portal/button_Resend Signup Email'), 2)

// Set GMT Timezone
TimeZone tz = TimeZone.getTimeZone("GMT")
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy")
sdf.setTimeZone(tz)

// Get today's date in GMT
String todayGMT = sdf.format(new Date())

// Get date from UI
String actualDate = WebUI.getText(findTestObject('Object Repository/Authorized Individual/Page_Patient Portal/Date Added'))

// Verify date
WebUI.verifyEqual(actualDate, todayGMT)

// 1️⃣ Click Calendar Icon
WebUI.click(findTestObject('Object Repository/Authorized Individual/Page_Patient Portal/Exp Date Calender icon'))

// 2️⃣ Get today's date in GMT (UTC)
LocalDate todayDateGMT = LocalDate.now(ZoneId.of("UTC"))
int today = todayDateGMT.getDayOfMonth()

println("Today's GMT date: " + today)

//// 3️⃣ Verify all past dates are disabled   (Issue when future date +5 date is selected and it will go to next month)
//for (int i = 1; i < today; i++) {
//
//	TestObject pastDate = new TestObject()
//	pastDate.addProperty(
//		"xpath",
//		ConditionType.EQUALS,
//		"//button[@disabled and text()='" + i + "']"
//	)
//
//	WebUI.verifyElementPresent(pastDate, 5)
//	println("Verified disabled date: " + i)
//}
//
//println("All past dates (GMT based) are correctly disabled.")

//Click on save button
WebUI.click(findTestObject('Object Repository/Authorized Individual/Page_Patient Portal/button_SAVE'))

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

// Auth Sign Up

//Navigate to Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign in button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Login with Username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : firstName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Get OTP
String otp3 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp3)

// Auto type into four input boxes
String[] digits3 = otp3.toCharArray()

//Enter OTP 
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits3[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits3[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits3[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits3[3].toString())

WebUI.delay(5)

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//click on Sign Up as a patient
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_Sign Up as a patientPlan your first visit to'))

//Verify first name
WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Legal First Name'),
	'value',
	firstName,
	10
)

//Verify last name
WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Last Name'),
	'value',
	lastName,
	10
)

//Verify Mobile number
WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_(000) 000-0000'),
	'value',
	mobileFormatted,
	10
)

//Verify Email
WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Email'),
	'value',
	email,
	10
)

//Verify date
WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'),
	'value',
	'',
	10
)

//Click on Procced  button
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_DOB is required'),'DOB is required.')

//Enter invalid date
WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'), '67/54/3222')

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_DOB is required'),
	'Please enter a valid month.')

////Enter valid DOB
//WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'), GlobalVariable.DOB)

TestObject dobField = findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY')

WebUI.executeJavaScript(
	"arguments[0].value=''; arguments[0].dispatchEvent(new Event('input', {bubbles:true}));",
	Arrays.asList(WebUI.findWebElement(dobField, 10))
)

WebUI.setText(dobField, GlobalVariable.DOB)

//Click on Close calender button
TestObject closeBtnOnCal = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/button_CLOSE')

if (WebUI.verifyElementPresent(closeBtnOnCal, 3, FailureHandling.OPTIONAL)) {
		WebUI.click(closeBtnOnCal)
	}

//Click on procced button

WebElement proccedBtnSignupAsPt = WebUI.findWebElement(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedBtnSignupAsPt))

//Verify sign up completed is displayed
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_Sign up completed'),
	'Sign up completed')

//Click on procced button
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.delay(5)

//Get username and password
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)

println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password

WebUI.delay(5)

//Login with username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Verify DOB and accept terms
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'))

//Add DOB
WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), GlobalVariable.DOB)

//Click on Procced Butoon
WebElement proccedBtnDOB = WebUI.findWebElement(findTestObject('Object Repository/Page_Patient Portal/ProccedBtnAftrDOBConfirm'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedBtnDOB))

WebUI.delay(5)

String otp3A = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp3A)


// Auto type into four input boxes
String[] digits3A = otp3A.toCharArray()

WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits3A[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits3A[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits3A[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits3A[3].toString())

WebUI.delay(5)

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)


//update password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//Login with updated password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)


String otp4 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp4)


// Auto type into four input boxes
String[] digits4 = otp4.toCharArray()

WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits4[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits4[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits4[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits4[3].toString())

WebUI.delay(5)

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//Verify Patient name and Date on dashboard
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : firstName, ('Lastname') : lastName], FailureHandling.STOP_ON_FAILURE)

//Click on name
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/span_OBDYhCUh RRmZfUDDZf'))

//Verify switch to popup displayed
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/h3_Switch to'),
	'Switch to')

//Verify cancel button is displayed
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/button_Cancel'),
	'Cancel')

//Verify Proceed button is displayed
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/button_Proceed'),
	'Proceed')

//Click on cancel button
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/button_Cancel'))

//Click on name
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/span_OBDYhCUh RRmZfUDDZf'))

//WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/select_OBDYhCUh RRmZfUDDZfJohn Doe'),
//	'OBDYhCUh RRmZfUDDZfJohn Doe')

//WebUI.selectOptionByValue(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/select_OBDYhCUh RRmZfUDDZfJohn Doe'),
//	'1209', false)

//Select John Doe
WebUI.selectOptionByLabel(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/select_OBDYhCUh RRmZfUDDZfJohn Doe'),
	'John Doe',
	false
)
//WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/select_OBDYhCUh RRmZfUDDZfJohn Doe'),
//	'OBDYhCUh RRmZfUDDZfJohn Doe')

//Click on procced button
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/button_Proceed'))

//WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/p_You are viewing record for John Doe_1'),
//	'You are viewing record for John Doe')


// ==========================
// Delete Auth Record
// ==========================

//Switch back to first window
WebUI.switchToWindowIndex(0)

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Delete'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Are you sure you want to remove this authorize'),
	'Are you sure you want to remove this authorized individual?')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Cancel'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Delete'))

WebUI.click(findTestObject('Object Repository/Authorized Individual/Page_Patient Portal/Delete Btn On Popup'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/td_No authorized individuals found'),
	'No authorized individuals found.') 

//----Sign in with deleted auth------------

//Navigate to Patient Portal Site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Login with updated password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)


String otp5 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp5)


// Auto type into four input boxes
String[] digits5 = otp5.toCharArray()

WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits5[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits5[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits5[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits5[3].toString())

WebUI.delay(5)

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//Verify Manage account screen is not visible
WebUI.verifyElementNotPresent(findTestObject("Object Repository/Authorized Individual/Page_Patient Portal/h2_View Manage Accounts"), 5)

//Verify select dropdown is not visible
WebUI.verifyElementNotPresent(findTestObject("Object Repository/Authorized Individual/Page_Patient Portal/div_Select User"), 5)

//Verify name is NOT a clickable link
TestObject nonClickable = new TestObject()
nonClickable.addProperty("xpath", ConditionType.EQUALS,
"(//span[contains(@class,'text-base')])[2]"
)

WebUI.verifyElementPresent(nonClickable, 5)
