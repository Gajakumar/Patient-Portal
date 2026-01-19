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
import stories.NavigateStory
import java.awt.Robot
import java.awt.event.KeyEvent
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.Date

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)


WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'),'Patient Portal Sign Up Completed. Email Sent.')

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

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Add New Encounter_Create New'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Enter Data In Enc Elements'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Summary Of Care(C-CDA)'))

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/h2_Summary of Care (C-CDA)'), 
    'Summary of Care (C-CDA)')

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/h4_Zak Duckett'), GlobalVariable.PatientFirstName +' '+ GlobalVariable.PatientLastName)

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/SOC_Verification'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_UploadToPatientPortal'))

WebUI.waitForElementVisible(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
	15
)

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Upload to Patient Portal completed succ_91bdc3_1'), 
    'Upload to Patient Portal completed successfully.')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))



WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

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

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

String actualUnreadSummaryCount = WebUI.getText(
	findTestObject('Object Repository/Page_Patient Portal/span_1unread messages')
).replaceAll("\\s+", "").trim()

WebUI.verifyMatch(
	actualUnreadSummaryCount,
	"1unreadmessages",
	false
)

String todayGMT = CustomKeywords.'common.DateUtil.getTodayDateGMT'()

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Visit Date 01162026'), 'Visit Date: '+ todayGMT)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/SOC Verification On Patient Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_a'))

WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_a_1'))

WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_a_2'))

WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_text-primary'))

WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_a_3'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/First Health Summary'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Downlaod PDF'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Download CCDA File'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Print CCDA'))

WebUI.delay(2)

Robot robot = new Robot()
robot.keyPress(KeyEvent.VK_ESCAPE)
robot.keyRelease(KeyEvent.VK_ESCAPE)

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Transmit to another prov'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Select Format PDF'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Save'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Send'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Email address is required'), 'Email address is required')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Subject is required'), 'Subject is required')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_4'),
	'abcd')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Send'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Please enter a valid email address'),
	'Please enter a valid email address')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_14'),
	'abcd@gmail.com')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Send'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Subject is required'), 'Subject is required')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e'))

WebUI.clearText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_14'))

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_32'),
	'gajakumara@first-insight.com')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_eec205_12'),
	'Patient CCDA')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/textarea_Test Patient'), 'Test Patient')

//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/span_100642_Download_Transmit.pdf'), '100642_Download_Transmit.pdf')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Send'))

String actualText = WebUI.getText(
    findTestObject('Object Repository/Page_Patient Portal/div_Message SentYour health summary has bee_02649d')
)

// Normalize spaces & line breaks
actualText = actualText.replaceAll('\\s+', ' ').trim()

String expectedText = 'Message Sent Your health summary has been successfully transmitted.'

WebUI.verifyMatch(actualText, expectedText, false)

