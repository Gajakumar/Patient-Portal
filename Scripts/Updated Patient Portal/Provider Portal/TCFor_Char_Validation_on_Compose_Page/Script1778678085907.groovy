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
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Keys


//Login to Maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

// Open Messages tab
TestObject menu = findTestObject('Provider Portal/Page_MaximEyes/a_ui-id-9')
WebUI.waitForElementClickable(menu, 10)
WebUI.click(menu)

// Click Compose
TestObject composeBtn = findTestObject('Provider Portal/Page_MaximEyes/span_btnCompose')
WebUI.waitForElementClickable(composeBtn, 10)
WebUI.click(composeBtn)

// Search popup
TestObject searchBtn = findTestObject('Provider Portal/Page_MaximEyes/span_Search')
WebUI.waitForElementClickable(searchBtn, 10)
WebUI.click(searchBtn)

// Patients flow
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnPatients'))
WebUI.waitForElementVisible(findTestObject('Provider Portal/Page_MaximEyes/div_ADVANCED PATIENT FIND'), 10)

WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name'), 'Portal')
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), 'Provider')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/td_TESTDATA'))

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnSendemail'))

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
def subjectField = findTestObject('Provider Portal/Page_MaximEyes/input_ComposeSubject')
def messageField = findTestObject('Provider Portal/New Folder3/Page_MaximEyes/textarea_input InboxTextarea font20 pad05')
def sendButton = findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button')

// Toast & Popup
def toastMessage = findTestObject('Object Repository/Page_MaximEyes/Toast Msg')
def popupText = findTestObject('Provider Portal/Page_MaximEyes/h4_confirmMessageText')
def popupOk = findTestObject('Object Repository/Provider Portal/Page_MaximEyes/input_confirmYesBtn')
def popupCancel = findTestObject('Provider Portal/Page_MaximEyes/input_confirmNoBtn')

// ==========================
// 1. SUBJECT MANDATORY VALIDATION
// ==========================
WebUI.comment("=== Test: Subject Mandatory ===")

WebUI.setText(subjectField, "")
WebUI.setText(messageField, "Test message")
WebUI.click(sendButton)

WebUI.verifyElementVisible(toastMessage)
WebUI.verifyElementText(toastMessage, "Subject is required")

// ==========================
// 2. EMPTY MESSAGE POPUP VALIDATION
// ==========================
WebUI.comment("=== Test: Empty Message Popup ===")

WebUI.setText(subjectField, validSubject)
WebUI.setText(messageField, "")
WebUI.click(sendButton)

// Verify popup
WebUI.verifyElementVisible(popupText)
WebUI.verifyElementText(popupText, "Send this message without text in the body?")

// ---- Cancel Flow ----
WebUI.click(popupCancel)
WebUI.verifyElementVisible(sendButton) // still on compose screen

// ---- OK Flow ----
//WebUI.click(sendButton)
//WebUI.waitForElementVisible(popupOk, 5)
//WebUI.click(popupOk)

// Optional: Verify success toast or navigation
// WebUI.verifyElementText(toastMessage, "Message sent")

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

WebUI.verifyElementVisible(toastMessage)
WebUI.verifyElementText(toastMessage, "The Subject Character limit has been reached")


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
//WebUI.verifyElementVisible(toastMessage)
//WebUI.verifyElementText(toastMessage, "The Message char limit has been reached")

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
// ==========================
// END
// ==========================
WebUI.comment("=== All validations executed ===")
