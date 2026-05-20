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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.*
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.WebElement


// =====================================================
// ✅ STEP 1: Login to Maximeyes
// =====================================================
WebUI.callTestCase(
    findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
    [:],
    FailureHandling.STOP_ON_FAILURE
)


// =====================================================
// ✅ STEP 2: Open Messages Tab
// =====================================================
TestObject menu = findTestObject('Provider Portal/Page_MaximEyes/a_ui-id-9')
WebUI.waitForElementClickable(menu, 10)
WebUI.click(menu)


// =====================================================
// ✅ STEP 3: Click Compose Button
// =====================================================
TestObject composeBtn = findTestObject('Provider Portal/Page_MaximEyes/span_btnCompose')
WebUI.waitForElementClickable(composeBtn, 10)
WebUI.click(composeBtn)


// =====================================================
// ✅ STEP 4: Initialize Objects & File Path
// =====================================================
TestObject fileUploadInput = new TestObject()
fileUploadInput.addProperty("id", ConditionType.EQUALS, "fileInputCompose")

def toastMessage = findTestObject('Object Repository/Page_MaximEyes/Toast Msg')
def popup = findTestObject('Object Repository/Provider Portal/Page_MaximEyes/h4_Unsupported file format  csv')

String projectDir = RunConfiguration.getProjectDir()
File baseDir = new File(projectDir, 'Include/Files/TestFiles')


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
// ✅ STEP 6: Upload Invalid File (CSV)
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'invalid.csv')


// =====================================================
// ✅ STEP 7: Verify Unsupported File Popup
// =====================================================
WebUI.waitForElementVisible(popup, 10)
WebUI.verifyElementText(popup, "Unsupported file format : csv")


// =====================================================
// ✅ STEP 8: Click Cancel on Popup
// =====================================================
WebUI.click(findTestObject(
    'Object Repository/Provider Portal/Page_MaximEyes/input_Unsupported file format  csv_btnCance_0b742d'
))


// =====================================================
// ✅ STEP 9: Re-open Compose (IMPORTANT STEP - NOT SKIPPED)
// =====================================================
WebUI.waitForElementClickable(composeBtn, 10)
WebUI.click(composeBtn)


// =====================================================
// ✅ STEP 10: Upload Oversized File (>25MB)
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'oversize_single_26MB.pdf')


// =====================================================
// ✅ STEP 11: Verify Size Limit Toast Message
// =====================================================
WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(
    toastMessage,
    'The attachment size exceeds the allowable limit. Maximum size of all attachments allowed is 25 MB.'
)

WebUI.delay(5)


// =====================================================
// ✅ STEP 12: Upload Zero Byte File
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'zeroByte.txt')


// =====================================================
// ✅ STEP 13: Verify Zero Byte Validation
// =====================================================
WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(
    toastMessage,
    'Attached files must be greater than 0 bytes'
)

WebUI.delay(5)


// =====================================================
// ✅ STEP 14: Upload 6 Files (Exceed Limit)
// =====================================================
['file1.jpg','file2.jpg','file3.jpg','file4.jpg','file5.jpg','file6.jpg'].each { fileName ->
    uploadFileTestCloud(fileUploadInput, baseDir, fileName)
}


// =====================================================
// ✅ STEP 15: Verify Max Attachment Limit
// =====================================================
WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(toastMessage, 'Maximum 5 attachments are allowed')

WebUI.delay(5)


// =====================================================
// ✅ STEP 16: Upload Duplicate File
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'file1.jpg')


// =====================================================
// ✅ STEP 17: Verify Duplicate File Message
// =====================================================
WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(toastMessage, "You've already attached this file.")


// =====================================================
// ✅ STEP 18: Click Compose Again (RESET STATE)
// =====================================================
WebUI.waitForElementClickable(composeBtn, 10)
WebUI.click(composeBtn)


// =====================================================
// ✅ STEP 19: Open Search Popup
// =====================================================
TestObject searchBtn = findTestObject('Provider Portal/Page_MaximEyes/span_Search')
WebUI.waitForElementClickable(searchBtn, 10)
WebUI.click(searchBtn)


// =====================================================
// ✅ STEP 20: Patient Search Flow
// =====================================================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnPatients'))

WebUI.waitForElementVisible(
    findTestObject('Provider Portal/Page_MaximEyes/div_ADVANCED PATIENT FIND'),
    10
)

WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name'), 'Portal')
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), 'Provider')

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/td_TESTDATA'))

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnSendemail'))


// =====================================================
// ✅ STEP 21: Prepare Fields
// =====================================================
def subjectField = findTestObject('Provider Portal/Page_MaximEyes/input_ComposeSubject')
def messageField = findTestObject('Provider Portal/New Folder3/Page_MaximEyes/textarea_input InboxTextarea font20 pad05')
def sendButton = findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button')


// =====================================================
// ✅ STEP 22: Upload 2 Files
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'file1.jpg')
WebUI.delay(3)

uploadFileTestCloud(fileUploadInput, baseDir, 'file2.jpg')
WebUI.delay(3)


// =====================================================
// ✅ STEP 23: Delete First File
// =====================================================
WebUI.click(findTestObject('Object Repository/Provider Portal/Delete File Verification/Delete file1'))


// =====================================================
// ✅ STEP 24: Verify Remaining File
// =====================================================
String actualFileText = WebUI.getText(
    findTestObject('Object Repository/Provider Portal/Delete File Verification/button_file2.jpg')
).trim()

WebUI.verifyMatch(actualFileText, "file2.jpg", false)


// =====================================================
// ✅ STEP 25: Delete Remaining File
// =====================================================
WebUI.click(findTestObject('Object Repository/Provider Portal/Delete File Verification/Delete file1'))


// =====================================================
// ✅ STEP 26: Verify No Attachments
// =====================================================
String actualAttachText = WebUI.getText(
    findTestObject('Object Repository/Provider Portal/Delete File Verification/span_No Attachments')
).trim()

WebUI.verifyMatch(actualAttachText, "No Attachments", false)


// =====================================================
// ✅ STEP 27: Upload Files Again for Final Send
// =====================================================
uploadFileTestCloud(fileUploadInput, baseDir, 'file1.jpg')
WebUI.delay(3)

uploadFileTestCloud(fileUploadInput, baseDir, 'file2.jpg')


// =====================================================
// ✅ STEP 28: Enter Subject & Message
// =====================================================
WebUI.setText(subjectField, "Attachments")
WebUI.setText(messageField, "Test Attachments")


// =====================================================
// ✅ STEP 29: Click Send
// =====================================================
WebUI.click(sendButton)
WebUI.delay(5)


// =====================================================
// ✅ STEP 30: Mark Test Passed
// =====================================================
KeywordUtil.markPassed('✔ All attachment validations completed successfully')


// =====================================================
// ✅ STEP 31: Verify Email Attachments
// =====================================================
CustomKeywords.'email.ProviderPortalEmail.verifyProviderPortalEmail'(
    "Attachments",
    "Test Attachments",
    ["file1.jpg", "file2.jpg"]
)