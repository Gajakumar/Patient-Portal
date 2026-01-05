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

WebUI.delay(5)

CustomKeywords.'email.GmailCredentialExtractorKey_Value.extractCredentials'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data",
	"User1",
)

println GlobalVariable.GV_Credentials["User1"].username
println GlobalVariable.GV_Credentials["User1"].password

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Create Random Patient in Maximeyes'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), SignUp_Email_Toast)

WebUI.delay(5)

CustomKeywords.'email.GmailCredentialExtractorKey_Value.extractCredentials'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data",
	"User2"
)

println GlobalVariable.GV_Credentials["User2"].username
println GlobalVariable.GV_Credentials["User2"].password

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_2_Attempts_Remaining)
WebUI.delay(3)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User2"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_2_Attempts_Remaining)

WebUI.delay(3)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_1_Attempts_Remaining)

WebUI.delay(3)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : GlobalVariable.GV_Credentials["User1"].password], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_2_Attempts_Remaining)

WebUI.delay(3)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Sign In Attempt Failed'), Sign_In_Failed_1_Attempts_Remaining)

WebUI.delay(3)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : "Test@3432"], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.ToastTimeVerifier.verifyGmtTimeToast'(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast'))

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Acc Locked'), "Account Locked")

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-3 lh-base'), Locked_Accout_Test1)

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-4 lh-base'), Locked_Accout_Test2)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/button_Account Locked_backToSignInButton'))

WebUI.delay(3)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Credentials["User1"].username, ('Password') : GlobalVariable.GV_Credentials["User1"].password], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.ToastTimeVerifier.verifyGmtTimeToast'(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast'))

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Acc Locked'), "Account Locked")

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-3 lh-base'), Locked_Accout_Test1)

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Account Locked_text-white mb-4 lh-base'), Locked_Accout_Test2)






