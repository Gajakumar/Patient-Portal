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
import com.kms.katalon.core.util.KeywordUtil
import java.util.regex.Matcher
import java.util.regex.Pattern




//Login to Maximeyes using QA_User
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

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Send Sign Up Email to radio button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait unitil busy indicator is not visible
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg "Patient Portal Sign Up Completed. Email Sent."
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), SignUp_Email_Toast)

WebUI.delay(5)

//get the Username and Password from the sign up mail received on provided email id
CustomKeywords.'email.GmailCredentialExtractorKey_Value.extractCredentials'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data",
	"User1",
)

println GlobalVariable.GV_Credentials["User1"].username
println GlobalVariable.GV_Credentials["User1"].password

//Create Random Patient
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Send Sign Up Email to radio button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait unitil busy indicator is not visible
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg "Patient Portal Sign Up Completed. Email Sent."
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), SignUp_Email_Toast)

WebUI.delay(5)

//get the Username and Password from the sign up mail received on provided email id
CustomKeywords.'email.GmailCredentialExtractorKey_Value.extractCredentials'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data",
	"User2"
)

println GlobalVariable.GV_Credentials["User2"].username
println GlobalVariable.GV_Credentials["User2"].password


//Navigate to Patient Portal site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign in button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter wrong Username and Password 
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

//Verify element text sign in failed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_2_Attempts_Remaining)
WebUI.delay(3)

//Enter wrong Username and Password 
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User2"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

//Verify element text sign in failed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_2_Attempts_Remaining)

WebUI.delay(3)

//Enter wrong Username and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

//Verify element text sign in failed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_1_Attempts_Remaining)

WebUI.delay(3)

//Enter correct Username and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : GlobalVariable.GV_Credentials["User1"].password], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Navigate to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click sign in
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter wrong Username and Password 1st time
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

//Verify element text sign in failed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_2_Attempts_Remaining)

WebUI.delay(3)

//Enter wrong Username and Password 2nd time
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

//Verify element text sign in failed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_1_Attempts_Remaining)

WebUI.delay(3)

//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

//TestObject toasMsgGmtTime = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast')
//
//WebUI.waitForElementVisible(toasMsgGmtTime, 5)

//Enter wrong Username and Password 3rd time
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/UserName'), GlobalVariable.GV_Credentials["User1"].username)
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Password'), "Test@3432")
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Verify toast "Account Locked for 15 min" with current GMT time
CustomKeywords.'common.ToastTimeVerifier.verifyGmtTimeToast'(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast'))

//Verify account locked
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Acc Locked'), "Account Locked")

//Verify account locked text
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-3 lh-base'), Locked_Accout_Test1)
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-4 lh-base'), Locked_Accout_Test2)

CustomKeywords.'common.AccountLockMessageVerifier.verifyAccountLockCountdown'(
    findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-4 lh-base')
)

//Click on Back to login button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/button_Account Locked_backToSignInButton'))

WebUI.delay(3)

//Now enter correct username and password
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/UserName'), GlobalVariable.GV_Credentials["User1"].username)
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Password'), GlobalVariable.GV_Credentials["User1"].password)

WebUI.delay(2)
//Click on sign in button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//It should not allow login & same toast "Account Locked for 15 min" with current GMT time should display
CustomKeywords.'common.ToastTimeVerifier.verifyGmtTimeToast'(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast'))

//Verify account locked
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Acc Locked'), "Account Locked")

//Verify account locked text
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-3 lh-base'), Locked_Accout_Test1)
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-4 lh-base'), Locked_Accout_Test2)

CustomKeywords.'common.AccountLockMessageVerifier.verifyAccountLockCountdown'(
	findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-4 lh-base')
)

////Wait for 15 min and re-login
//// Wait for unlock (15 min buffer)
//WebUI.comment("⏳ Waiting 15 minutes for account unlock...")
//WebUI.delay(905)
//
////Click on Back to login button
//WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/button_Account Locked_backToSignInButton'))
//
//// Re-login
//WebUI.setText(
//    findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/UserName'),
//    GlobalVariable.GV_Credentials["User1"].username
//)
//
//WebUI.setText(
//	findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Password'), 
//	GlobalVariable.GV_Credentials["User1"].password)
//
//
//WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
//
//// Verify success 
//WebUI.verifyElementPresent(
//    findTestObject("Object Repository/Page_Patient Portal/ConfirmDOB"),
//    10
//)

	




