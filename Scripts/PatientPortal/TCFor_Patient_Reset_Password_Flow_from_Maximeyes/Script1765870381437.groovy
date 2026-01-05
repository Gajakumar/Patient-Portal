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

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Create Random Patient in Maximeyes'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), SignUp_Email_Toast)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/span_Patient Portal_PtOverviewResetPatientToPortalPP'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/span_Reset password and Print Instructions_icons'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/input_Edit Email Address_btnproceed'))

WebUI.delay(5) 

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

CustomKeywords.'common.CaptureCredentials.captureAndStoreCredentials'('Object Repository/Patient_Overview/PortaliNvitaionUrlPassUsername')

WebUI.closeBrowser()

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.PortalURL)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.RestUserName, ('Password') : GlobalVariable.RestPassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)


WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.RestUserName, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

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

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Verify_Setting_Dropdown_Options'), [:], FailureHandling.STOP_ON_FAILURE)





