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
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/th_Actions'), 'Actions')

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
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Mobile format is invalid'), 'Mobile format is invalid!')

//Verify error message
WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Email format is invalid'), 'Email format is invalid!')

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



