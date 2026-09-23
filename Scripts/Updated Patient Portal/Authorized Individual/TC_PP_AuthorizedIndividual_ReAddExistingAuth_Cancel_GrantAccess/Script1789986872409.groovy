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
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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
WebElement proccedButton = WebUI.findWebElement(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedButton))


String name = firstName +" "+ lastName

String updatePermissionPopup = "Do you want to update access permissions for the " + name +"?"

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
TestObject homeBtn = findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal')

WebUI.waitForElementVisible(homeBtn, 30)
WebUI.waitForElementClickable(homeBtn, 30)
WebUI.click(homeBtn)

//============
// Open new tab
WebUI.executeJavaScript("window.open('about:blank','_blank');", [])

//Switch to new tab
WebUI.switchToWindowIndex(1)

//navigate to received activation link
WebUI.navigateToUrl(activationLink)

WebUI.delay(5)

//Get OTP from email
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

//Enter OTP
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

//Click on procced button

WebElement proccedBtnCrtCred = WebUI.findWebElement(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedBtnCrtCred))

//verify sign up completed text
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h1_Sign Up Completed'),
	'Sign Up Completed')

//verify sign up completed text
WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_pageTitle'), 'Sign up completed')

//click on procced button
WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

//Switch to new tab
WebUI.switchToWindowIndex(0)

//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Click on Authorized Individuals
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

//Delete button on auth list
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Delete'))

//Confirmation popup
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Are you sure you want to remove this authorize'),
	'Delete Authorized Individual?')

//Click on yes button
WebUI.click(findTestObject('Object Repository/Authorized Individual/Page_Patient Portal/Delete button on confirmation popup'))

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

// Set value in field
WebUI.sendKeys(findTestObject('Authorized Individual/Page_Patient Portal/td_02_18_2026'), formattedDate)

println("Date entered: " + formattedDate)

//Click on Procced button
WebElement proccedButton1 = WebUI.findWebElement(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedButton1))

// ---------------------------------------------------------------------------
// Config
// ---------------------------------------------------------------------------
final int TIMEOUT = 10 // seconds
final int STATE_CHECK_TIMEOUT = 2
 
final String OR_PATH = 'Authorized Individual/Auth User Sign Up/' +
		'Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/'
 
final String EXPECTED_MESSAGE = 'Account already exists for '+ firstName +" "+ lastName+'. Do you want to activate the same account again?'
 
// ---------------------------------------------------------------------------
// Test objects
// ---------------------------------------------------------------------------
TestObject txtAccountExists = findTestObject(OR_PATH + 'p_Account already exists for test a. Do you want')
TestObject btnActivateAccount = findTestObject(OR_PATH + 'button_Activate Account')
TestObject btnCancel = findTestObject(OR_PATH + 'button_Cancel')
TestObject btnProceed = findTestObject(OR_PATH + 'button_Proceed')
TestObject btnSave = findTestObject(OR_PATH + 'button_Save')
TestObject btnYes = findTestObject(OR_PATH + 'button_Yes')
 
TestObject input1 = findTestObject(OR_PATH + 'input_undefinedundefined')
TestObject input2 = findTestObject(OR_PATH + 'input_undefinedundefined_1')
TestObject input3 = findTestObject(OR_PATH + 'input_undefinedundefined_2')
TestObject input4 = findTestObject(OR_PATH + 'input_undefinedundefined_3')
TestObject input5 = findTestObject(OR_PATH + 'input_undefinedundefined_4')
 
// ---------------------------------------------------------------------------
// Helper
// ---------------------------------------------------------------------------
def clickWhenReady = { TestObject to ->
    WebUI.waitForElementClickable(to, TIMEOUT)
    WebUI.click(to)
}
 
// Ensures a checkbox ends up checked: skips it if already checked, clicks it if not
def checkIfUnchecked = { TestObject to, String chkBoxname ->
    WebUI.waitForElementPresent(to, TIMEOUT)
 
    // OPTIONAL so an unchecked box returns false instead of failing the test
    boolean isChecked = WebUI.verifyElementChecked(to, STATE_CHECK_TIMEOUT, FailureHandling.OPTIONAL)
 
    if (isChecked) {
        WebUI.comment("${chkBoxname} is already checked - skipping click")
    } else {
        WebUI.comment("${chkBoxname} is unchecked - clicking")
        clickWhenReady(to)
    }
}
 
// ---------------------------------------------------------------------------
// Test steps
// ---------------------------------------------------------------------------
 
// Verify the "account already exists" dialog
WebUI.waitForElementVisible(txtAccountExists, TIMEOUT)
WebUI.assertElementText(txtAccountExists, EXPECTED_MESSAGE, TIMEOUT)
WebUI.assertElementPresent(btnActivateAccount, TIMEOUT)
WebUI.assertElementPresent(btnCancel, TIMEOUT)
 
// Cancel, then proceed again
clickWhenReady(btnCancel)
clickWhenReady(btnProceed)
 
// Re-activate the account
clickWhenReady(btnActivateAccount)
 
//// Select options (only click the ones that are not already checked)
//[
//	input1: input1,
//	input2: input2,
//	input3: input3,
//	input4: input4,
//	input5: input5
//].each { chkBoxname, checkbox -> checkIfUnchecked(checkbox, chkBoxname) }
 
// Save and confirm
clickWhenReady(btnSave)
clickWhenReady(btnYes)

//Verify email received for auth access
String patientName = GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName
String portalVersion = "2712"

CustomKeywords.'email.GmailGrantAccessReader.verifyAccessGrantedEmail'(
	patientName,
	portalVersion
)