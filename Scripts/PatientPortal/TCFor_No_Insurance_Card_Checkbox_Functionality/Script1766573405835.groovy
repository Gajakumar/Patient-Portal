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

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)

WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), 'Patient Portal Sign Up Completed. Email Sent.')

CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, 
    GlobalVariable.Sender_Email, 'Access to your health data')

println('Username: ' + GlobalVariable.GV_Username)

println('Password: ' + GlobalVariable.GV_Password)

//Navigate to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter User name and password and click on sign in button
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username
        , ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Confirm DOB and Accept terms by drawing signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

//Update password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//Again login with updated password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username
        , ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Read OTP from received over email
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'('imap.gmail.com', GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, 
    GlobalVariable.Sender_Email, 'Verification')

println('OTP fetched = ' + otp)

// Auto type otp into four input boxes
String[] digits = otp.toCharArray()

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1'), (digits[0]).toString())

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2'), (digits[1]).toString())

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3'), (digits[2]).toString())

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'), (digits[3]).toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click on Procced button after OTP entered
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//Verify Username, Todays date and current time on dashboard
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), 
    [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

//Click on Setting icon on dashboard
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Setting Icon on Portal'))

//click on Update Insurance on setting dropdown
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/span_Update Demographics_block pr-14 py-2 f_d2a216'))

//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/div_No Insurance Card_flex flex-col items-c_89221b'), 
//    '📷')
//
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/div__flex flex-col items-center justify-cen_408b51'), 
//    '📷')

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Insurance Card Photo_undefinedNo Insu_7f69c9'))

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/span__text-gray-500'), 
    'Patient Relationship to Insured')

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5_1'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5_2'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Middle Name_form-control mt-1 form-co_6f1561'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Suffix_form-control mt-1 form-control_012157'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5_3'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/label_Male_flex items-center'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/label__flex items-center'), 
    0)

//Uncheck no ins card check box
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Insurance Card Photo_undefinedNo Insu_7f69c9'))

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/div_No Insurance Card_flex flex-col items-c_89221b'))

WebUI.sendKeys(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Upload_img_Front'), 'C:\\Users\\Gajakumar_a\\Pictures\\Rahul.png')
WebUI.delay(3)

WebUI.sendKeys(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Upload_img_Back'), 'C:\\Users\\Gajakumar_a\\Pictures\\Rahul.png')
WebUI.delay(3)
