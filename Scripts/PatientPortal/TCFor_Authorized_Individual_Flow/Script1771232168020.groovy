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

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : userName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

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

WebUI.delay(10)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_Authorized Individuals'), 'Authorized Individuals')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Authorized Individuals will have full access t'), 
    'Authorized Individuals will have full access to your Patient Portal account. They can view health record, send message to your provider, make payments or schedule appointment on your behalf. Invite only whom you trust.')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h4_Access to My Portal'), 'Access to My Portal')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/th_Name'), 'Name')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/th_Expires'), 'Expires')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/th_Actions'), 'Actions')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Add authorized individual'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h3_Add Authorized Individual'), 'Add Authorized Individual')

WebUI.mouseOver(findTestObject('Authorized Individual/Page_Patient Portal/path_icon'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_Enter value'), '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/input_MM_DD_YYYY'), '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/textarea_Reason'), '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'), 'Proceed')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_First Name is required'), 'First Name is required!')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Last Name is required'), 'Last Name is required!')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Mobile format is invalid'), 'Mobile format is invalid!')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Email format is invalid'), 'Email format is invalid!')

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), '(123')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/main_Add Authorized IndividualAdd Authorized Ind'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Mobile format is invalid'), 'Mobile format is invalid!')

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), '@gmail.com')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Email format is invalid'), 'Email format is invalid!')

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email_1'), 'yrfdf')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Email format is invalid'), 'Email format is invalid!')

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
String email = "gajakumara+4@first-insight.com"


WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), firstName)

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), lastName)

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), mobileFormatted)

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), email)

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))


WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h1_Add New Individual'), 'Add New Individual')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_Access Granted'), 'Access Granted')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_tewsdrw asww can now access your records'),
	firstName +" "+ lastName + ' can now access your records!')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_QUESTIONS'), 'QUESTIONS?')

WebUI.delay(10)

String name = firstName +" "+ lastName


import email.EmailVerification

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



//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))


//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'), firstName +" "+ lastName)

//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/td_02_18_2026'), '02/18/2026')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'))

String last4 = mobilePlain.substring(mobilePlain.length() - 4)

WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/h2_Permissions'), 'Permissions')

WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/h2_Authorized Individual'),
	'Authorized Individual')

WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/p_Name_ EBCHzKcV SAdeaQaEDU'),
	'Name: '+firstName +" "+ lastName)

String phoneText = WebUI.getText(findTestObject(
	'Object Repository/Authorized Individual/Permissions/Page_Patient Portal/p_Phone_ XXXXXX9406'))

println("Actual UI Phone: " + phoneText)

// Verify masked format like: Phone: XXXXXX1234
WebUI.verifyMatch(phoneText, "Phone:\\s*X+${last4}", true)

String firstLetter = email.substring(0,1)
String lastCharBeforeAt = email.substring(email.indexOf('@') - 1, email.indexOf('@'))
String domain = email.substring(email.indexOf('@'))

String emailText = WebUI.getText(findTestObject('Object Repository/Authorized Individual/Permissions/Page_Patient Portal/p_Email_ gxxxxxxxxxx1first-insight.com'))

WebUI.verifyMatch(emailText,
	"Email:\\s*${firstLetter}x+${lastCharBeforeAt}${domain.replace('.', '\\.')}",
	true)

WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/span_Pending'), 'Pending')

WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/h4_Portal Access'), 'Portal Access')

WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined'), 5)
WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_1'), 5)
WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_2'), 5)
WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_3'), 5)
WebUI.verifyElementChecked(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_4'), 5)


WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_MM_DD_YYYY'), '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/div_Resend Signup Email'),
	'Resend Signup Email')

WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Cancel'), 'Cancel')

WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save'), 'Save')

WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Resend Signup Email'))

WebUI.delay(10)

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

WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/p_Do you want to update access permissions for t'),
	'Do you want to update access permissions for the selected Authorized Individual?')

WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save_1'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'))

// ==========================
// UNCHECK & SAVE
// ==========================

TestObject permission1 = findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_undefinedundefined_1')
									
WebUI.uncheck(permission1)

WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save'))


WebUI.verifyElementText(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/p_Do you want to update access permissions for t'),
	'Do you want to update access permissions for the selected Authorized Individual?')

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

WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save'))

WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save_1'))

// ==========================
// Record Match Found
// ==========================

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Add authorized individual'))

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), firstName)

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), lastName)

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), mobileFormatted)

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), email)

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))


WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_Add New Individual'), 'Add New Individual')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_record-match-title'), 'Record Match Found')


WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_We have found a similar matching record'),
	'We have found a similar matching record with us but with different details. Did you enter details correctly?')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Home'))

WebUI.closeBrowser()

WebUI.openBrowser(activationLink1)

WebUI.maximizeWindow()


//WebUI.navigateToUrl(activationLink1)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))

// 1. Locate canvas
TestObject canvasObj = findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas')

// 2. Wait & scroll
WebUI.waitForElementVisible(canvasObj, 30)
WebUI.scrollToElement(canvasObj, 5)

// 3. Get WebElement
WebElement canvasElement = WebUiCommonHelper.findWebElement(canvasObj, 10)

if (canvasElement == null) {
	KeywordUtil.markFailed("❌ Canvas not found! Check XPath.")
}

// 4. Draw signature safely
Actions actions = new Actions(DriverFactory.getWebDriver())

actions.moveToElement(canvasElement, 10, 10)   // move INSIDE canvas
	   .clickAndHold()
	   .moveByOffset(40, 10)
	   .moveByOffset(30, -15)
	   .moveByOffset(35, 20)
	   .moveByOffset(-25, 15)
	   .release()
	   .perform()

println("✔ Signature drawn successfully!")

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))

WebUI.delay(5)

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

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h1_Create Credentials'),
	'Create Credentials')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h2_Choose a unique Username'),
	'Choose a unique Username')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h2_Choose a new Password'),
	'Choose a new Password')


WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Username'), firstName)

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password'),GlobalVariable.RestUpdatedPass )

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Confirm Password'),GlobalVariable.RestUpdatedPass)

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h1_Sign Up Completed'),
	'Sign Up Completed')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_pageTitle'), 'Sign up completed')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : firstName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

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

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h3_HvIwLoIs DCgghJzngx'),firstName +" "+ lastName)

WebUI.verifyElementNotClickable(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_Select User'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/li_John Doe'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

//// 1. Locate 
//TestObject ptNameToast = findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_You are viewing record for John Doe_1')
//
//// 2. Wait 
//WebUI.waitForElementVisible(ptNameToast, 10)
//
//WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_You are viewing record for John Doe_1'),
//	'You are viewing record for John Doe')
//
//TestObject ptNameToast = findTestObject(
//	'Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_You are viewing record for John Doe_1'
//)
//
//// Wait for it to appear (short timeout)
//WebUI.waitForElementPresent(ptNameToast, 5)
//
//// Immediately capture text
//String toastText = WebUI.getText(ptNameToast)
//
//println("Toast Message: " + toastText)
////
//// Verify using contains (safer for dynamic name)
//assert toastText.contains("You are viewing record for John Doe")



WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))


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


WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/li_Log Out'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : firstName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)


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

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_Sign Up as a patientPlan your first visit to'))

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Legal First Name'),
	'value',
	firstName,
	10
)

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Last Name'),
	'value',
	lastName,
	10
)

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_(000) 000-0000'),
	'value',
	mobileFormatted,
	10
)

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Email'),
	'value',
	email,
	10
)

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'),
	'value',
	'',
	10
)

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_DOB is required'),
	'DOB is required!')

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'), '67/54/3222')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_DOB is required'),
	'Please enter a valid date in MM/DD/YYYY format')

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'), GlobalVariable.DOB)

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.rightClick(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_Sign up completed'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_Sign up completed'),
	'Sign up completed')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.delay(5)

CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)

println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password

WebUI.delay(5)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

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

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : firstName, ('Lastname') : lastName], FailureHandling.STOP_ON_FAILURE)



//// ==========================
//// Delete Auth Record 
//// ==========================
//
//
////Click on setting icon
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))
//
//WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))
//
//WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/svg_a'))
//
//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Are you sure you want to remove this authorize'),
//	'Are you sure you want to remove this authorized individual?')
//
//WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Cancel'))
//
//WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/svg_a'))
//
//WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Delete'))
//
//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/td_No authorized individuals found'),
//	'No authorized individuals found.')



