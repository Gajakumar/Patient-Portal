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
import common.SessionKeywords as Session
import common.SessionKeywords


WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : UserNamePt, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(5)
//
//
//String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
//	'imap.gmail.com',
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	GlobalVariable.Sender_Email,
//	'Verification'
//)
//
//println("OTP fetched = " + otp)
//
//
//// Auto type into four input boxes
//String[] digits = otp.toCharArray()
//
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())
//
//WebUI.delay(5)
//
TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
//
//// Wait until the button is clickable (visible and enabled)
//WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
//
//// Click the button
//WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(10)
//
////Click on setting icon
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))
//
////Click on Log Out button
//WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/LogOut Button'))
//
////Verify Logged Out screen displayed
//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/Logged Out Screen'),
//	'Logged Out')
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : UserNamePt, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(5)
//
//
//String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
//	'imap.gmail.com',
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	GlobalVariable.Sender_Email,
//	'Verification'
//)
//
//println("OTP fetched = " + otp1)
//
//
//// Auto type into four input boxes
//String[] digits1 = otp1.toCharArray()
//
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits1[0].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits1[1].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits1[2].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits1[3].toString())
//
//WebUI.delay(5)
//
//// Wait until the button is clickable (visible and enabled)
//WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
//
//// Click the button
//WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(10)
//
////Refresh page
//WebUI.refresh()
//
////Verify sign in screen displayed
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'),'Sign In')

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : UserNamePt, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

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

// Force idle timeout without waiting 15 minutes
new SessionKeywords().forceIdleTimeout(16)

//Verify sign in screen displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'),'Sign In')

// Simulate activity every 2 minutes (5 times)
new SessionKeywords().simulateUserActivity(3, 5)

// Verify still on home page
WebUI.verifyElementPresent(
	findTestObject('Object Repository/Page_Patient Portal/Home Screen'),
	10
)

// Verify NOT redirected to login
WebUI.verifyElementNotPresent(
	findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'),
	3
)



