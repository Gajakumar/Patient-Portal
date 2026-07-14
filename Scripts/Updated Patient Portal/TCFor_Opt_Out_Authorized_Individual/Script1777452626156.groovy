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

//Login to Maximeyes
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
//Get Patient ID
TestObject patientIdObj = findTestObject(
	'Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen'
)

WebUI.waitForElementVisible(patientIdObj, 15)

//Get patient Id
GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''
println "✅ Patient ID stored: " + GlobalVariable.GV_PatientID

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Send Sign Up Email to
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait for busy indicator invisible
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'),'Patient Portal Sign Up Completed. Email Sent.')

WebUI.delay(10)

//get username and password
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)

println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password

//Navigate to Patient Portal Site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on sign in button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter Username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Confirm DOB and accept terms
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

//get otp from email
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

//enter otp
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

//Update existing password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//Login with new password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//get otp from email
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

//enter otp
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

//Verify patient name date and time displayed correctly
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

//Click on Setting button on dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Select Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

//Verify and delete Available auths
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Delete Available Auths'), [:], FailureHandling.STOP_ON_FAILURE)

//verify auth is deleted
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/td_No authorized individuals found'),
	'No authorized individuals found.')

//Click on Add Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Add authorized individual'))

//Enter valid first name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), firstName)

//Enter valid last name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), lastName)

//Enter valid mobile
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), mobileFormatted)

//Enter valid email
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), email)

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

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on setting icon
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/div_w-10 h-10 flex items-center justify-center r'))

//click on opt out
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/span_Opt Out'))

//Verify patient name and date
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Patient Name and Date on Opt Out'),[:], FailureHandling.STOP_ON_FAILURE)

//check check box for accept terms
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/input_I Accept'))

//Add Signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Add Signature On Canvas'),[:], FailureHandling.STOP_ON_FAILURE)

//Click on make my account inactive
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/button_Make my account inactive'))

//Verify text after opt out
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/h4_You will be missed'), 'You will be missed')
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/p_You have successfully opted out from Patient P'),
	'You have successfully opted out from Patient Portal.')
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/p_Were sad to see you go'), 'We\'re sad to see you go.')
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/h4_Opted Out'), 'Opted Out')

//========================================================
// Open new tab
WebUI.executeJavaScript("window.open('about:blank','_blank');", [])

//Switch to new tab
WebUI.switchToWindowIndex(1)

//navigate to received activation link
WebUI.navigateToUrl(activationLink)

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
WebElement proccedBtnCrtCred = WebUI.findWebElement(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedBtnCrtCred))

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
//WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h3_HvIwLoIs DCgghJzngx'),firstName +" "+ lastName)

//Verify no access for patient
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h2_View_ Manage Accounts'),
	'View/ Manage Accounts')

//click on select user
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/div_Select User_1'))

//Select patient
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/li_Jerry Wilson'))

//Click on procced
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/button_Proceed'))
