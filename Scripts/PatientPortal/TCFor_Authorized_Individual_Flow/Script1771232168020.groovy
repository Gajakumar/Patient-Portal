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

WebUI.doubleClick(findTestObject('Authorized Individual/Page_Patient Portal/div_Add authorized individual'))


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
String email = "gajakumara+1@first-insight.com"


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



String activationLink =
CustomKeywords.'utils.GmailUniversalVerifier.validateGrantAndActivationEmails'(
		name,
		mobilePlain,
		email
)

//WebUI.navigateToUrl(activationLink)
println(activationLink)


//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))


//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'), firstName +" "+ lastName)

//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/td_02_18_2026'), '02/18/2026')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/svg_a'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Are you sure you want to remove this authorize'),
	'Are you sure you want to remove this authorized individual?')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Cancel'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/svg_a'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Delete'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/td_No authorized individuals found'),
	'No authorized individuals found.')

