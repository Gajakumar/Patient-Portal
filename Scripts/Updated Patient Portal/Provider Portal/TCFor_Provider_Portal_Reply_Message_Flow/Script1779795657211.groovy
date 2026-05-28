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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil

// =====================================================
// ✅ STEP 1: Portal Login
// =====================================================

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Dsmith Portal Login'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Sent Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))
WebUI.delay(2)

//Delete message if avaialable
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Delete Sent Messages'), [:], FailureHandling.STOP_ON_FAILURE)

//Verify No Message displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Sent Messages_text-lg mt-2'),
	'You have no messages in sent messages')

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Inbox
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_px-4 py-2 hoverbg-gray-10_0f01e2'))

//Click on + Icon to compose message
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))

//Add Subject 
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_f186a3_5'),
	'Forward Message')

//Add Message for Doctor
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/textarea_Message For Doctor_form-control mt_4ab4b2'),"Forward Message Inbox")

def fileUploadInputReact   = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attach File Input')
String projectDir = RunConfiguration.getProjectDir()
File baseDir = new File(projectDir, 'Include/Files/TestFiles')


def uploadFileTestCloud1(TestObject uploadObj, File baseDir, String fileName) {
	
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

//Upload Invalid File
uploadFileTestCloud1(fileUploadInputReact, baseDir, 'file2.jpg')

//Click on Send Button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/button_Send_Msg'))

//Click on + Icon to compose message
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))

//Add Subject as "Demo1"
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_f186a3_5'),
	'Test Inbox Forward 1st Msg')

//Add Message for Doctor
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/textarea_Message For Doctor_form-control mt_4ab4b2'),"Inbox Forward Provider Portal")

//Click on Send Button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/button_Send_Msg'))

// =====================================================
// ✅ STEP 1: Max Login
// =====================================================
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

// =====================================================
// ✅ STEP 2: Secure Messages
// =====================================================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/a_ui-id-9'))

//Verify Practice name is displayed in to field
WebUI.verifyElementAttributeValue(
	findTestObject('Provider Portal/Forword Message/Page_MaximEyes/To Field Practice name'),
	'innerText',
	'To: First Insight Vision',
	5
)

//Click on Reply btn
WebUI.click(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'))

//Verify Reply screen is displayed
WebUI.verifyElementPresent(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/h4_Reply'), 5)

//Verify Forward info icon text
WebUI.mouseOver(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/span_Reply_Info'))
TestObject infoMsg = findTestObject('Provider Portal/Forword Message/Page_MaximEyes/div_Type a simple text message.Note_ You will no')
String infoMsgactualText = WebUI.getText(infoMsg)

// Normalize: remove line breaks + extra spaces
infoMsgactualText = infoMsgactualText.replaceAll("\\s+", " ").trim()
String expectedText = "Type a simple text message. Note: You will not be able to make changes to this message or attachments after sending it to Practice."
assert infoMsgactualText.equals(expectedText)

//Verify To field
WebUI.verifyElementAttributeValue(
	findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/input_Reply Search Patient or Referring Physician'),
	'value',
	'Smith David | DOB: 03/16/1982 (44 yrs), M',
	5
)


//Verify Provider Radio button is selected
WebUI.verifyElementChecked(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/Reply span_Provider'),5)

//Verify send to
WebUI.verifyElementText(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/Reply span_Patient Portal'), 'Patient Portal')

//Verify subject
WebUI.verifyElementAttributeValue(
    findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/Reply input_forwardSubject'),
    'value',
    'Re: Test Inbox Forward 1st Msg',
    5
)

//Verify Inbox text
TestObject inboxFieldobj = findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/Reply Message Text box')
String actualInboxText = WebUI.getAttribute(inboxFieldobj, 'value')

// Normalize
actualInboxText = actualInboxText.replaceAll("\\s+", " ").trim()

// Assertions
assert actualInboxText.contains("From: David Smith")
assert actualInboxText.contains("Inbox Forward Provider Portal")
assert actualInboxText.matches(".*\\d{2}/\\d{2}/\\d{4}.*")

//Verify attachment text
WebUI.verifyElementPresent(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/Reply span_Attachments'), 5)

//Verify send button is present
WebUI.verifyElementPresent(findTestObject('Object Repository/Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_reply-send-button'), 
    5)

def sendButton = findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_reply-send-button')

//click on send button
WebUI.click(sendButton)

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

String pattern = "(?i)from:\\s*david smith\\s*\\|\\s*\\d{2}/\\d{2}/\\d{4}\\s*\\d{2}:\\d{2}\\s*(am|pm)\\s*inbox forward provider portal"

CustomKeywords.'email.ProviderPEmail.verifyProviderPEmail'(
	"Fwd: Test Inbox Forward 1st Msg",
	pattern
)

//click 2nd message from left pane
WebUI.click(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Fwd msg inbox'))

//Click on Reply btn
WebUI.click(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'))


// ==========================
// TEST DATA
// ==========================
String validSubject = "Test Subject"
String specialChars = "!#\$%&'*+-/=?^_`{|}~,"
String maxSubject = "A" * 988
String exceedSubject = "A" * 989

String maxMessage = "M" * 384000
String exceedMessage = "M" * 384001

// ==========================
// OBJECTS
// ==========================
def subjectField = findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/Reply input_forwardSubject')
def messageField = findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/Reply Message Text box')


// Toast & Popup
def toastMessage = findTestObject('Object Repository/Page_MaximEyes/Toast Msg')
def popupText = findTestObject('Provider Portal/Page_MaximEyes/h4_confirmMessageText')
def popupOk = findTestObject('Object Repository/Provider Portal/Page_MaximEyes/input_confirmYesBtn')
def popupCancel = findTestObject('Provider Portal/Page_MaximEyes/input_confirmNoBtn')

// ==========================
// 3. SUBJECT CHARACTER LIMIT
// ==========================
WebUI.comment("=== Test: Subject Character Limit ===")

WebUI.setText(subjectField, maxSubject)
WebUI.verifyMatch(WebUI.getAttribute(subjectField, "value").length().toString(), "988", false)

// Exceed limit
WebUI.setText(subjectField, exceedSubject)
WebUI.setText(messageField, "Test")
WebUI.click(sendButton)

//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'("The Subject Character limit has been reached")


// ==========================
// 4. MESSAGE CHARACTER LIMIT
// ==========================
WebUI.comment("=== Test: Message Character Limit ===")
WebUI.setText(subjectField, maxSubject)

WebUI.executeJavaScript(
	"arguments[0].value = arguments[1];",
	Arrays.asList(WebUI.findWebElement(messageField, 10), maxMessage)
)

// Exceed limit
//WebUI.setText(messageField, exceedMessage)
WebUI.executeJavaScript(
	"arguments[0].value = arguments[1];",
	Arrays.asList(WebUI.findWebElement(messageField, 10), exceedMessage)
)


// ==========================
// 5. SPECIAL CHARACTER VALIDATION
// ==========================
WebUI.comment("=== Test: Special Characters Allowed ===")

WebUI.setText(subjectField, specialChars)
WebUI.setText(messageField, specialChars)

WebUI.click(sendButton)

// Should NOT show validation error
boolean isToastVisible = WebUI.waitForElementVisible(toastMessage, 3, FailureHandling.OPTIONAL)

if (isToastVisible) {
	String toastText = WebUI.getText(toastMessage)
	println("Toast message: " + toastText)

	if (toastText.toLowerCase().contains("error") ||
		toastText.contains("limit") ||
		toastText.contains("required")) {
		
		KeywordUtil.markFailed("❌ Error toast displayed: " + toastText)
	}
}

//click 2nd msg
WebUI.click(findTestObject('Object Repository/Provider Portal/Inbox/Page_MaximEyes/Fwd msg'))

//Click on Reply btn
WebUI.waitForElementVisible(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'), 10)
WebUI.click(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'))

// =====================================================
// ✅ STEP 4: Initialize Objects & File Path
// =====================================================
TestObject fileUploadInput = new TestObject()
fileUploadInput.addProperty("id", ConditionType.EQUALS, "fileInputCompose")

def popup = findTestObject('Object Repository/Provider Portal/Page_MaximEyes/h4_Unsupported file format  csv')

// =====================================================
// ✅ STEP 5: Upload Helper Method (TestCloud)
// =====================================================
def uploadFileTestCloud(TestObject uploadObj, File baseDir, String fileName) {

	assert uploadObj != null : '❌ Upload object is NULL'

	File file = new File(baseDir, fileName)
	assert file.exists() : "❌ File not found: ${file.absolutePath}"

	println("Uploading: " + file.absolutePath)

	CustomKeywords.'com.katalon.testcloud.FileExecutor.uploadFileToWeb'(
		uploadObj,
		file.absolutePath
	)
}

// =====================================================
// ✅ STEP 10: Upload Oversized File (>25MB)
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'oversize_single_26MB.pdf')

// =====================================================
// ✅ STEP 11: Verify Size Limit Toast Message
// =====================================================
//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('The attachment size exceeds the allowable limit. Maximum size of all attachments allowed is 25 MB.')

//Verify forward attachement
WebUI.click(findTestObject('Object Repository/Provider Portal/Inbox/Page_MaximEyes/Fwd msg'))

//Click on button on upcoming popup
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'))

//Click on forward btn
WebUI.waitForElementVisible(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'), 10)
WebUI.click(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'))


// =====================================================
// ✅ STEP 16: Upload Duplicate File
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'file2.jpg')


//Verify forward attachement
WebUI.click(findTestObject('Object Repository/Provider Portal/Inbox/Page_MaximEyes/Fwd msg'))

//Click on button on upcoming popup
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'))

//Click on forward btn
WebUI.waitForElementVisible(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'), 10)
WebUI.click(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'))

// =====================================================
// ✅ STEP 14: Upload 6 Files (Exceed Limit)
// =====================================================
['file1.jpg','file3.jpg','file4.jpg','file5.jpg','file6.jpg'].each { fileName ->
	uploadFileTestCloud(fileUploadInput, baseDir, fileName)
}

// =====================================================
// ✅ STEP 15: Verify Max Attachment Limit
// =====================================================
//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'("Maximum 5 attachments are allowed")

//Verify forward attachement
WebUI.click(findTestObject('Object Repository/Provider Portal/Inbox/Page_MaximEyes/Fwd msg'))

//Click on button on upcoming popup
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'))

//Click on forward btn
WebUI.waitForElementVisible(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'), 10)
WebUI.click(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/button_Reply'))

// =====================================================
// ✅ STEP 12: Upload Zero Byte File
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'zeroByte.txt')

// =====================================================
// ✅ STEP 13: Verify Zero Byte Validation
// =====================================================
//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Attached files must be greater than 0 bytes')

