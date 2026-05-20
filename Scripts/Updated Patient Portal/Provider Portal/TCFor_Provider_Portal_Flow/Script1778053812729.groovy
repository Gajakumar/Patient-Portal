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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.WebElement as WebElement
import java.util.Arrays as Arrays
import utils.CheckboxKeywords as CK
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import java.util.Arrays
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType


//Login to Maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)


//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/a_ui-id-9'))
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_Search Message'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_Inbox'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_markAsReviewed'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_flagForProvider'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_btnCompose'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenu'), 5)
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenu'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/div_Outbox'), 'Outbox')
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/div_Sent Messages'), 'Sent Messages')
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/div_Deleted Messages'), 'Deleted Messages')
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/div_Activity Log'), 'Activity Log')
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_btnCompose'))
//
////WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/h4_Compose'), '\n                                Compose\n\n                                \n                                \n                                \n\n                            ')
//
//WebUI.mouseOver(findTestObject('Provider Portal/Page_MaximEyes/span_mif-info font22 fg-purple line-height20 Sec'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/div_Type a simple text message.Note_ You will no'), 
//    'Type a simple text message.Note: You will not be able to make changes to this message or attachments after sending it to Practice.')
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_Search'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/h4_Do you want to search Patients or External Ph'), 
//    'Do you want to search Patients or External Physicians?')
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/input_btnPatients'), '')
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/input_btnReferringPhysicians'), '')
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnPatients'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/div_ADVANCED PATIENT FIND'), 'ADVANCED PATIENT FIND')
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/button_Close'))
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_Search'))
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnReferringPhysicians'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/div_ADVANCED PATIENT FIND'), 'ADVANCED PATIENT FIND')
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/button_Close'))
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/div_Send as'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_Subject'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_ComposeSubject'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_Message'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/textarea_input InboxTextarea font20 pad05'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_Attachments'), 5)
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button'), 5)
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/div_Please specify at least one recipient'), 'Please specify at least one recipient.')
//
//WebUI.rightClick(findTestObject('Provider Portal/Page_MaximEyes/input_Search Patient or Referring Physician'))
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/div_Please specify at least one recipient'), 'Please specify at least one recipient.')
//
//WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_ComposeSubject'), 'Test')
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/h4_confirmMessageText'), 'Send this message without text in the body?')
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/input_confirmYesBtn'), '')
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/input_confirmNoBtn'), '')
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_confirmNoBtn'))
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_attachmentIconCompose'))
//
//WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_fileInputCompose'), 'C:\\fakepath\\InsCard.png')
//
//WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/button_InsCard.png'), 5)
//
//WebUI.verifyElementClickable(findTestObject('Provider Portal/Page_MaximEyes/span_dattachefile'))
//


// Open Messages tab
TestObject menu = findTestObject('Provider Portal/Page_MaximEyes/a_ui-id-9')
WebUI.waitForElementClickable(menu, 10)
WebUI.click(menu)

// Wait for main elements
WebUI.waitForElementVisible(findTestObject('Provider Portal/Page_MaximEyes/input_Search Message'), 10)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_Inbox'), 10)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_markAsReviewed'), 10)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_flagForProvider'), 10)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_btnCompose'), 10)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenu'), 10)

TestObject toField = findTestObject('Provider Portal/Page_MaximEyes/input_Search Patient or Referring Physician')

WebUI.waitForElementVisible(toField, 10)

// Verify field is readonly/restricted
boolean isReadonly = WebUI.getAttribute(toField, "readonly") != null

println("Readonly attribute: " + isReadonly)

assert isReadonly : "❌ Manual entry should be restricted"


//// Get populated email
//String emailValue = WebUI.getAttribute(toField, "value")
//
//println("Selected email: " + emailValue)
//
//// Email validation regex
//String emailRegex = '^(?!\\.)(?!.*\\.\\.)([A-Za-z0-9!#$%&\'*+\\-/=?^_`{|}~]+(\\.[A-Za-z0-9!#$%&\'*+\\-/=?^_`{|}~]+)*)@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+$'
//
//// Validations
//assert !emailValue.contains(",") : "❌ Multiple email IDs are not allowed"
//
//assert emailValue ==~ emailRegex :
//        "❌ Invalid email format: " + emailValue

// Open More Actions
TestObject moreMenu = findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenu')
WebUI.waitForElementClickable(moreMenu, 10)
WebUI.click(moreMenu)

// Verify menu options (use verifyMatch for safety)
WebUI.verifyMatch(WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/div_Outbox')).trim(), 'Outbox', false)
WebUI.verifyMatch(WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/div_Sent Messages')).trim(), 'Sent Messages', false)
WebUI.verifyMatch(WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/div_Deleted Messages')).trim(), 'Deleted Messages', false)
WebUI.verifyMatch(WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/div_Activity Log')).trim(), 'Activity Log', false)

// Click Compose
TestObject composeBtn = findTestObject('Provider Portal/Page_MaximEyes/span_btnCompose')
WebUI.waitForElementClickable(composeBtn, 10)
WebUI.click(composeBtn)

// Hover info icon
TestObject infoIcon = findTestObject('Provider Portal/Page_MaximEyes/span_mif-info font22 fg-purple line-height20 Sec')
WebUI.waitForElementVisible(infoIcon, 10)
WebUI.mouseOver(infoIcon)

// Normalize and verify message text
String actualText = WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/div_Type a simple text message.Note_ You will no'))
actualText = actualText.replaceAll("\\s+", " ").trim()

String expectedText = "Type a simple text message. Note: You will not be able to make changes to this message or attachments after sending it to Practice."
WebUI.verifyMatch(actualText, expectedText, false)

// Search popup
TestObject searchBtn = findTestObject('Provider Portal/Page_MaximEyes/span_Search')
WebUI.waitForElementClickable(searchBtn, 10)
WebUI.click(searchBtn)

WebUI.verifyMatch(WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/h4_Do you want to search Patients or External Ph')).trim(),
	'Do you want to search Patients or External Physicians?', false)

// Patients flow
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnPatients'))
WebUI.waitForElementVisible(findTestObject('Provider Portal/Page_MaximEyes/div_ADVANCED PATIENT FIND'), 10)
WebUI.verifyMatch(WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/div_ADVANCED PATIENT FIND')).trim(), 'ADVANCED PATIENT FIND', false)
//Click on close button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

// Physicians flow
WebUI.click(searchBtn)
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnReferringPhysicians'))
WebUI.waitForElementVisible(findTestObject('Provider Portal/Page_MaximEyes/div_ADVANCED PATIENT FIND'), 10)
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/div_Select External Physician')).trim(), 'SELECT EXTERNAL PHYSICIAN', false)
//Click on close button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

// Verify compose fields
WebUI.verifyElementVisible(findTestObject('Provider Portal/Page_MaximEyes/div_Send as'))
WebUI.verifyElementVisible(findTestObject('Provider Portal/Page_MaximEyes/span_Subject'))
WebUI.verifyElementVisible(findTestObject('Provider Portal/Page_MaximEyes/input_ComposeSubject'))
WebUI.verifyElementVisible(findTestObject('Provider Portal/Page_MaximEyes/span_Message'))
WebUI.verifyElementVisible(findTestObject('Provider Portal/Page_MaximEyes/textarea_input InboxTextarea font20 pad05'))
WebUI.verifyElementVisible(findTestObject('Provider Portal/Page_MaximEyes/span_Attachments'))
WebUI.verifyElementClickable(findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button'))

//Verify efile is disable
TestObject detachIcon = findTestObject('Provider Portal/Page_MaximEyes/span_dattachefile')

WebUI.waitForElementPresent(detachIcon, 10)

String classValue = WebUI.getAttribute(detachIcon, "class")

println("Class value: " + classValue)

assert classValue.contains("disabled") : 
        "❌ Element is not disabled"


// click send button
TestObject sendBtn = findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button')
WebUI.click(sendBtn)

WebUI.verifyMatch(WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/div_Please specify at least one recipient')).trim(),
	'Please specify at least one recipient.', false)

// Patients flow

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/Page_MaximEyes/span_Search'))

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/Page_MaximEyes/input_btnPatients'))

WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/Page_MaximEyes/input_Last Name'), 'Smith')

WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/Page_MaximEyes/input_First Name'), 'David')

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/Page_MaximEyes/input_button primary small-button'))

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/Page_MaximEyes/td_Smith'))

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/Page_MaximEyes/input_btnSendemail'))



// Enter subject only
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_ComposeSubject'), 'Test')
WebUI.click(sendBtn)

// Confirm popup
WebUI.waitForElementVisible(findTestObject('Provider Portal/Page_MaximEyes/h4_confirmMessageText'), 10)
WebUI.verifyMatch(WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/h4_confirmMessageText')).trim(),
	'Send this message without text in the body?', false)

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_confirmNoBtn'))

// File upload (IMPORTANT FIX)
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_attachmentIconCompose'))
//
//String filePath = 'C:\\Users\\YourUser\\Desktop\\InsCard.png'   // ✅ real path required
//WebUI.uploadFile(findTestObject('Provider Portal/Page_MaximEyes/input_fileInputCompose'), filePath)

def fileUploadInput   = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attach File Input')

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

//Upload  File
uploadFileTestCloud(fileUploadInput, baseDir, 'InsCard.jpg')

// Verify attachment
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/button_InsCard.png'), 10)
WebUI.verifyElementClickable(findTestObject('Provider Portal/Page_MaximEyes/span_dattachefile'))


