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
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import common.TextFieldValidation as TFV

//Login To Maximeyes
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

//Select Send Sign Up Email to
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait until busy indicator invisible
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), 'Patient Portal Sign Up Completed. Email Sent.')

WebUI.delay(10)

//get Username & Password from email
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, 
    GlobalVariable.Sender_Email, 'Access to your health data')

println('Username: ' + GlobalVariable.GV_Username)

println('Password: ' + GlobalVariable.GV_Password)


//Navigate to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter User name and password and click on sign in button
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Confirm DOB and Accept terms by drawing signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)


WebUI.delay(5)

//Fetch the otp from the email
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

//Enter the OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

//Click on Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

//Update Password   >>>>>>>>>>>>>>MBT 48416<<<<<<<<<<<<<<<<<
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//Login with Updated Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

//Fetch the otp from the email
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

//Enter the OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits1[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits1[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits1[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits1[3].toString())

WebUI.delay(5)

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

//Click on Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)


WebUI.delay(5)

//Verify Date Time and Patient name on Dashboard
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

//Verify Dashboard modules
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Dashboard Verification'),[:],FailureHandling.STOP_ON_FAILURE)


////======================

//Click on Setting icon on dashboard
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Setting Icon on Portal'))

//click on Update Insurance on setting dropdown
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/span_Update Demographics_block pr-14 py-2 f_d2a216'))


//Check No Ins check box
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Insurance Card Photo_undefinedNo Insu_7f69c9'))

//Verify Patient Relationship to Insured page opens
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/span__text-gray-500'), 
    'Patient Relationship to Insured')
//Verify field on PRI
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5_1'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5_2'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Middle Name_form-control mt-1 form-co_6f1561'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Suffix_form-control mt-1 form-control_012157'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5_3'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/label_Male_flex items-center'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/label__flex items-center'), 
    5)

//Uncheck no ins card check box
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Insurance Card Photo_undefinedNo Insu_7f69c9'))

//Upload Ins 
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/div_No Insurance Card_flex flex-col items-c_89221b'))

def fileUploadInputFront   = findTestObject('PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Upload_img_Front')
def fileUploadInputBack   = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Upload_img_Back')
String projectDir = RunConfiguration.getProjectDir()
File baseDir = new File(projectDir, 'Include/Files/TestFiles')


def uploadFileTestCloud(TestObject uploadObj, File baseDir, String fileName) {
	
		assert uploadObj != null : '❌ Upload input TestObject is NULL'
	
		File fileToUpload = new File(baseDir, fileName)
		assert fileToUpload.exists() && fileToUpload.isFile() :
				"❌ Upload file not found: ${fileToUpload.absolutePath}"
	
		println "☁ TestCloud uploading: ${fileToUpload.absolutePath}"
	
		CustomKeywords.'com.katalon.testcloud.FileExecutor.uploadFileToWeb'(
			uploadObj,
			fileToUpload.absolutePath
		)
	}
	
	//Upload Insurance Card Front with invalid format - Front
	uploadFileTestCloud(fileUploadInputFront, baseDir, 'InsCardInvalid.tif')
	
	//verify toast msg
	CustomKeywords.'common.ToastHelper.verifyToastMessage'('Invalid file format. Please upload JPG, JPEG, or PNG only.')
	WebUI.delay(3)
	
	//Upload Insurance Card Front with invalid format - Back
	uploadFileTestCloud(fileUploadInputBack, baseDir, 'InsCardInvalid.tif')
	
	//verify toast msg
	CustomKeywords.'common.ToastHelper.verifyToastMessage'('Invalid file format. Please upload JPG, JPEG, or PNG only.')
	WebUI.delay(3)
	
	//Upload Insurance Card Front with invalid format - Front
	uploadFileTestCloud(fileUploadInputFront, baseDir, '3mb.jpg')
	
	//verify toast msg
	CustomKeywords.'common.ToastHelper.verifyToastMessage'('File size exceeds 2MB limit. Please upload a smaller image.')
	WebUI.delay(3)
	
	//Upload Insurance Card Front with invalid format - Back
	uploadFileTestCloud(fileUploadInputBack, baseDir, '3mb.jpg')
	
	//verify toast msg
	CustomKeywords.'common.ToastHelper.verifyToastMessage'('File size exceeds 2MB limit. Please upload a smaller image.')

	//Upload Insurance Card Front
	uploadFileTestCloud(fileUploadInputFront, baseDir, 'InsCard.jpg')

	WebUI.delay(3)
	
	//Upload Insurance Card Back
	uploadFileTestCloud(fileUploadInputBack, baseDir, 'InsCard.jpg')

	WebUI.delay(3)
	
	//Issue An unexpected error has occurred. Please try again later. If the problem persists, call our office." 
//   Max length validation is not working
	
//	//Click on Save button
//	WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/button_Save'))
//	
//	//verify toast msg
//	CustomKeywords.'common.ToastHelper.verifyToastMessage'('Insurance information saved successfully')
//	
//	
//	//Click on Setting icon on dashboard
//	WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Setting Icon on Portal'))
//	
//	//click on Update Insurance on setting dropdown
//	WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/span_Update Demographics_block pr-14 py-2 f_d2a216'))
	
//	WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/input_No Insurance Card'))
	
//	TFV.verifyMaxLengthWithMessage("Insurance Name", findTestObject('Scenario Update1703/Page_Patient Portal/input_Legal First Name'), 100)
//	
//	TFV.verifyMaxLengthWithMessage("Notes", findTestObject('Object Repository/.../textarea_Notes'), 1000)
	
	
	
	
//	TFV.verifyMaxLengthWithMessage("Insured ID", findTestObject('Scenario Update1703/Page_Patient Portal/input_Insured ID'), 50)
//	
//	TFV.verifyMaxLengthWithMessage("Legal First Name", findTestObject('Scenario Update1703/Page_Patient Portal/input_Legal First Name'), 50)
//	
//	TFV.verifyMaxLengthWithMessage("Middle Name", findTestObject('Scenario Update1703/Page_Patient Portal/input_Middle Name (Optional)'), 50)
//	
//	TFV.verifyMaxLengthWithMessage("Last Name", findTestObject('Scenario Update1703/Page_Patient Portal/input_Last Name'), 50)
//	
//	TFV.verifyMaxLengthWithMessage("Suffix", findTestObject('Scenario Update1703/Page_Patient Portal/input_Suffix (Optional'), 100)
//	
	



