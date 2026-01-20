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
//Login to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Sign in With User Name and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : UserName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

// OTP Verification
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

// Click the Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Setting icon on pt portal dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/span_Profile'))

WebUI.mouseOver(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/div_Profile_border border-2 border-primary _91433f'))


WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/div_Photo'), 'Photo')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/div_IDDrivers License'), 
    'ID/Driver\'s License')

//def fileUploadInput   = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attach File Input')
//def toastMessage      = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Messages - Toasts')
//
//
//
//// =====================================================
//// 🔹 PROJECT FILE PATH (LOCAL + CLOUD SAFE)
//// =====================================================
//String projectDir = RunConfiguration.getProjectDir()
//File baseDir = new File(projectDir, 'TestFiles')
//
//assert baseDir.exists() && baseDir.isDirectory() :
//		"❌ TestFiles folder not found at: ${baseDir.absolutePath}"
//
//// =====================================================
//// 🔹 Helper: Upload File (SAFE)
//// =====================================================
//def uploadFile(TestObject uploadObj, File baseDir, String fileName) {
//
//	assert uploadObj != null : '❌ Upload input TestObject is NULL'
//
//	File fileToUpload = new File(baseDir, fileName)
//
//	assert fileToUpload.exists() && fileToUpload.isFile() :
//			"❌ Upload file not found: ${fileToUpload.absolutePath}"
//
//	WebUI.sendKeys(uploadObj, fileToUpload.absolutePath)
//}
//
//// =====================================================
//// 1) Unsupported file format
//// =====================================================
//uploadFile(fileUploadInput, baseDir, 'invalid.csv')
//WebUI.waitForElementVisible(toastMessage, 5)
//WebUI.verifyElementText(toastMessage, 'Only JPG, JPEG, PNG, BMP, and WebP formats are allowed')
//
//// =====================================================
//// 2) File size exceeds 3 MB
//// =====================================================
//uploadFile(fileUploadInput, baseDir, 'oversize_single_26MB.pdf')
//WebUI.waitForElementVisible(toastMessage, 5)
//WebUI.verifyElementText(toastMessage,'File size should not exceed 3MB')


WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/label_Name'),
	'Name')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Edit'),
	'Edit')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_David'),
	'David')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_Smith'),
	'Smith')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Edit'))

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control mb-2 text-dark py-2_d59cf8'))

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control mb-2 text-dark py-2_d59cf8_1'),
	'David1')

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5'),
	'Smith')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5'))

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5_1'),
	'Smith2')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Save'),
	'Save')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Save'))

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Edit'),
	'Edit')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_David1'),
	'David1')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_Smith2'),
	'Smith2')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Edit'))

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control mb-2 text-dark py-2_d59cf8_1'))

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control mb-2 text-dark py-2_d59cf8'),
	'David')

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5_1'),
	'Smith2')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5_1'))

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5'),
	'Smith')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Save'))

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_David'),
	'David')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_Smith'),
	'Smith')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/svg_Messages_text-light'))

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/svg_David Smith_text-primary'))

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/span_Profile'))

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_David'),
	'David')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_Smith'),
	'Smith')

//Maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/span__mif-cog font20 head-icon-shadow fg-white'))

WebUI.click(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/a_Integrations'))

WebUI.click(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/a_Patient Portal'))

WebUI.selectOptionByValue(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/select_Eyeclinic.net PortalMaximeyes Patien_ff78b6'),
	'Maximeyes Patient Portal', true)

WebUI.verifyElementText(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/label_Sync Profile Image'), 'Sync Profile Image')

WebUI.verifyElementPresent(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/span_Photo_icon-checked'), 5)

String actualPhotoText = WebUI.getText(
	findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/label_Photo')
).trim()

WebUI.verifyMatch(actualPhotoText, 'Photo', false)

WebUI.verifyElementPresent(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/span_concat(IDDriver, , s License)_icon-checked'),
	5)

String actualIDText = WebUI.getText(
	findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/label_IDDrivers License')
).trim()

WebUI.verifyMatch(actualIDText, "ID/Driver's License", false)

WebUI.mouseOver(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/span_Sync Profile Image_mif-info fg-skyblue_4476df'))


String actualMouseHoverText = WebUI.getText(
	findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/p_Select if you want to sync the Patient pr_0b39f8')
)
	.replaceAll('\\s+', ' ')
	.trim()

String expectedText =
	'Select if you want to sync the Patient profile image from MaximEyes.com to Patients MaximEyes Patient Portal account.'

WebUI.verifyMatch(actualMouseHoverText, expectedText, false)





