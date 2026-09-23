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
import org.openqa.selenium.WebElement

Random randm = new Random()

String firstName = RandomStringUtils.randomAlphabetic(8).capitalize()
String lastName  = RandomStringUtils.randomAlphabetic(10).capitalize()

String firstName1 = RandomStringUtils.randomAlphabetic(8).capitalize()
String lastName1  = RandomStringUtils.randomAlphabetic(10).capitalize()

GlobalVariable.fname = firstName1
GlobalVariable.lname = lastName1

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

String email = GlobalVariable.MyEmail_Id
int timeOut = 10

//println(email)

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
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Portal sign up using email
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Portal Sign up using email'), [:], FailureHandling.STOP_ON_FAILURE)

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

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
 
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

//Click on Setting button on dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Select Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

//Verify Authorized Individuals Screen is open
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_Authorized Individuals'), 'Authorized Individuals')

//Click on Add Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Add authorized individual'))

//Verify Add Authorized Individual page opens
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h3_Add Authorized Individual'), 'Add Authorized Individual')

//Enter valid first name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), GlobalVariable.PatientFirstName)

//Enter valid last name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), GlobalVariable.PatientLastName)

//Enter valid mobile
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), mobileFormatted)

//Enter valid email
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), email)

//Click on Procced button
WebElement proccedButton = WebUI.findWebElement(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedButton))

//You cannot add yourself as Authorized User! toast verification
CustomKeywords.'common.ToastHelper.verifyToastMessage'('You cannot add yourself as Authorized User!')


//Enter valid first name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), firstName1)

//Enter valid last name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), lastName1)

//Click on Procced button
WebElement proccedButton1 = WebUI.findWebElement(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedButton1))


// Build masked mobile: first 2 digits + stars + last 2 digits (based on digits in mobileFormatted)
String mobileDigits = mobileFormatted.replaceAll("[^0-9]", "")
String firstTwo     = mobileDigits.substring(0, 2)
String lastTwo      = mobileDigits.substring(mobileDigits.length() - 2)
String starCount    = "*" * (mobileDigits.length() - 4)
String mobileMasked = "${firstTwo}${starCount}${lastTwo}"

// ----------------------------
// Test Objects
// ----------------------------
def TO_recordMatchTitle   = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/h2_record-match-title')
def TO_recordMatchDesc    = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/p_We have found a similar matching record with u')
def TO_patientName        = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/span_test test')
def TO_maskedDob          = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/span__')
def TO_maskedMobile       = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/span_22_22')
def TO_maskedEmail        = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/span__first-insight.com')
def TO_grantAccessButton  = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/button_Grant Access')
def TO_grantAccessTitle   = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/h3_grant-access-title')
def TO_accessGrantedTitle = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/h2_Access Granted')
def TO_accessGrantedDesc  = findTestObject('Authorized Individual/Record Match/Page_Patient Portal/p_test test can now access your records')

// ----------------------------
// Record Match Assertions
// ----------------------------
WebUI.assertElementText(TO_recordMatchTitle, 'Record Match Found', timeOut)

WebUI.assertElementText(TO_recordMatchDesc,
	'We have found a similar matching record with us but with different details. Did you enter details correctly?', timeOut)

WebUI.assertElementText(TO_patientName, firstName1+" "+lastName1, timeOut)

WebUI.assertElementText(TO_maskedDob, '*****/****', timeOut)

WebUI.assertElementText(TO_maskedMobile, mobileMasked, timeOut)

WebUI.assertElementText(TO_maskedEmail, '**********@first-insight.com', timeOut)

// ----------------------------
// Grant Access
// ----------------------------
WebUI.assertElementPresent(TO_grantAccessButton, timeOut)

WebUI.assertElementText(TO_grantAccessTitle, 'Do you want to grant access to '+firstName1+" "+lastName1+'?', timeOut)

WebUI.click(TO_grantAccessButton)

WebUI.waitForElementVisible(TO_accessGrantedTitle, 30)

// ----------------------------
// Access Granted Assertions
// ----------------------------
WebUI.assertElementText(TO_accessGrantedTitle, 'Access Granted', timeOut)

WebUI.assertElementText(TO_accessGrantedDesc, firstName1+" "+lastName1+' can now access your records!', timeOut)

String name = firstName1 +" "+ lastName1

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
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Click on Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

//Click on Add Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Add authorized individual'))

//Verify Add Authorized Individual page opens
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h3_Add Authorized Individual'), 'Add Authorized Individual')

//Enter valid first name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), firstName1)

//Enter valid last name
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), lastName1)

//Enter valid mobile
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), mobileFormatted)

//Enter valid email
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), email)

//Click on Procced button
WebElement proccedButton2 = WebUI.findWebElement(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedButton2))

//Authorised User already exist! toast verification
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Authorised User already exist!')

