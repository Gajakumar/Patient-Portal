import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
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
import custom.DownloadHelper

//Navigate to patient portal site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

// Login again with new password
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/UserName'), "VfnQxs0316")



WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Password'), "Test@1234")



WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))



WebUI.delay(5)

// Fetch OTP from email
String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println('OTP fetched = ' + otp1)

// Enter OTP digits
String[] digits1 = otp1.toCharArray()

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1'), digits1[0])
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2'), digits1[1])
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3'), digits1[2])
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'), digits1[3])

WebUI.delay(5)
TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
// Click Proceed
WebUI.waitForElementClickable(proceedBtn, 15)
WebUI.click(proceedBtn)

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))

WebUI.waitForElementNotVisible(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Loader'), 30)

DownloadHelper downloadHelper = new DownloadHelper()

// Step 1: arm the blob interceptor BEFORE clicking download
downloadHelper.captureBlobDownload()

// Step 2: trigger the actual download click
WebUI.click(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/btn_DownloadCCDA'))

// Step 3: pull the captured blob back as a real file, works locally and on TestCloud
String filePath = downloadHelper.retrieveCapturedBlobAsFile(null, 30)

println "Downloaded XML saved at: " + filePath
assert new File(filePath).exists() : "XML file was not saved"

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Click on + Icon to compose message
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))

//Upload File 
def fileUploadInput   = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attach File Input')
def uploadFileTestCloud(TestObject uploadObj, File fileToUpload) {
	
		assert uploadObj != null : '❌ Upload input TestObject is NULL'
		assert fileToUpload.exists() && fileToUpload.isFile() :
				"❌ Upload file not found: ${fileToUpload.absolutePath}"
	
		println "☁ TestCloud uploading: ${fileToUpload.absolutePath}"
	
		CustomKeywords.'com.katalon.testcloud.FileExecutor.uploadFileToWeb'(
			uploadObj,
			fileToUpload.absolutePath
		)
	}

	uploadFileTestCloud(fileUploadInput, new File(filePath))