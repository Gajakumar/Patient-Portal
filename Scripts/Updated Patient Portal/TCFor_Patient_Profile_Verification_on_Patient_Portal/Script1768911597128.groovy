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

TestObject profileObj = findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/div_DS')
TestObject imgInsideProfile = findTestObject('Object Repository/Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/Image at Profile circle')

//Verify user initials displayed at profile circle
assert WebUI.getText(profileObj).trim().length() > 0
WebUI.verifyElementText(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/div_DS'), 'DS')

//Setting icon on pt portal dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Click on Profile
WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/span_Profile'))

//Mouse hover on photo
WebUI.mouseOver(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/div_Profile_border border-2 border-primary _91433f'))

//Verify element text as photo
WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/div_Photo'), 'Photo')

//Verify element text as Drivers License
WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/div_IDDrivers License'), 
    'ID/Driver\'s License')

//Profile name save/edit verification
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Profile Name Verification'), [:], FailureHandling.STOP_ON_FAILURE)

def fileUploadInput   = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attach File Input')
def toastMessage      = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Messages - Toasts')


//Verify Delete button is disabled
WebUI.verifyElementHasAttribute(
	findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_opacity-50'),
	'disabled',
	10
)


// =====================================================
// 🔹 PROJECT FILE PATH (LOCAL + CLOUD SAFE)
// =====================================================
String projectDir = RunConfiguration.getProjectDir()
File baseDir = new File(projectDir, 'Include/Files/TestFiles')

assert baseDir.exists() && baseDir.isDirectory() :
		"❌ TestFiles folder not found at: ${baseDir.absolutePath}"

// =====================================================
// 🔹 Helper: Upload File (SAFE)
// =====================================================
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

// =====================================================
// 1) Unsupported file format
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'invalid.csv')

//Verify invalid image popup is displayed
WebUI.verifyElementText(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/p_Invalid Image Format Select an alternative im'), 
    'Invalid Image Format! Select an alternative image source file such as a PNG, JPEG or JPG.')

//Click on OK button
WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/button_Ok'))
WebUI.delay(2)
// =====================================================
// 2) File size exceeds 2 MB
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'oversize_single_26MB.pdf')

//Verify toast message is displayed
CustomKeywords.'common.ToastHelper.verifyToastMessage'('The attachment size exceeds the allowable limit. Maximum size of attachment allowed is 2 MB.')


// =====================================================
// 2) File size  2 MB
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, '2mb.jpg')

//Verify file upload button is disabled
WebUI.verifyElementHasAttribute(
	fileUploadInput,
	'disabled',
	10
)

//Profile image verification
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Uploaded Profile Image Validation'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Profile image at profile circle
WebUI.waitForElementPresent(imgInsideProfile, 15)

// Extra wait for rendering
WebUI.delay(2)

def img = WebUI.findWebElement(imgInsideProfile)

Boolean isLoaded = WebUI.executeJavaScript(
    "return arguments[0].complete && arguments[0].naturalWidth > 0;",
    Arrays.asList(img)
)

//Setting icon on pt portal dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Click on Profile
WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/span_Profile'))

//Profile image verification
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Uploaded Profile Image Validation'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on delete button
WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_opacity-100'))

//Verify confirmation toast is displayed
WebUI.verifyElementText(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/p_Do you want to delete this picture'),
	'Do you want to delete this picture?')

//Click on cancel button
WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/button_Cancel'))

//Click on delete button
WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_opacity-100'))

//Click on Procced buton on confirmation popup
WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/button_Proceed'))

//==============================Maximeyes===============================

//Navigate to maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

//Navigate to OA 
WebUI.click(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/span__mif-cog font20 head-icon-shadow fg-white'))

//Click on Intigrations
WebUI.click(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/a_Integrations'))

//Click on Patient Portal
WebUI.click(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/a_Patient Portal'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Select Maximeyes Patient Portal from dropdown
WebUI.selectOptionByValue(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/select_Eyeclinic.net PortalMaximeyes Patien_ff78b6'),
	'Maximeyes Patient Portal', true)

//Verify Sync Profile Image
WebUI.verifyElementText(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/label_Sync Profile Image'), 'Sync Profile Image')

//Verify checkbox are present
WebUI.verifyElementPresent(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/span_Photo_icon-checked'), 5)

String actualPhotoText = WebUI.getText(
	findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/label_Photo')
).trim()

//Verify text
WebUI.verifyMatch(actualPhotoText, 'Photo', false)

//Verify Driver License check box
WebUI.verifyElementPresent(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/span_concat(IDDriver, , s License)_icon-checked'),
	5)

String actualIDText = WebUI.getText(
	findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/label_IDDrivers License')
).trim()

//Verify text
WebUI.verifyMatch(actualIDText, "ID/Driver's License", false)

//Mouse hover on i icon
WebUI.mouseOver(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/span_Sync Profile Image_mif-info fg-skyblue_4476df'))


String actualMouseHoverText = WebUI.getText(
	findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/p_Select if you want to sync the Patient pr_0b39f8')
)
	.replaceAll('\\s+', ' ')
	.trim()

String expectedText =
	'Select if you want to sync the Patient profile image from MaximEyes.com to Patients MaximEyes Patient Portal account.'

//Verify info icon text
WebUI.verifyMatch(actualMouseHoverText, expectedText, false)





