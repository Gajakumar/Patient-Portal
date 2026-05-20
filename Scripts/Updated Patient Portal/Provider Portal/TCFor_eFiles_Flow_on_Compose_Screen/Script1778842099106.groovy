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
// ✅ STEP 1: Login
// =====================================================
WebUI.callTestCase(
    findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
    [:],
    FailureHandling.STOP_ON_FAILURE
)

// =====================================================
// ✅ STEP 2: Open Messages → Compose
// =====================================================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/a_ui-id-9'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_btnCompose'))


// =====================================================
// ✅ STEP 3: Define Objects
// =====================================================
TestObject efileIcon = findTestObject('Provider Portal/Page_MaximEyes/span_dattachefile')
TestObject popup = findTestObject('Object Repository/Provider Portal/Page_MaximEyes/eFile Popup')
TestObject attachBtn = findTestObject('Object Repository/Provider Portal/Page_MaximEyes/Attach Btn')
TestObject toast = findTestObject('Object Repository/Page_MaximEyes/Toast Msg')
TestObject closePopup = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC')


// =====================================================
// ✅ STEP 4: Verify Efile Disabled (No Patient Selected)
// =====================================================
WebUI.waitForElementPresent(efileIcon, 10)

String classValue = WebUI.getAttribute(efileIcon, "class")

println("Class value: " + classValue)

assert classValue.contains("disabled") : 
        "❌ Element is not disabled"


// =====================================================
// ✅ STEP 6: Verify Disabled for Referring Physician
// =====================================================
// (Assuming object exists)
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_Search'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnReferringPhysicians'))

WebUI.setText(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search in data grid'), 'email')
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/td_Katalon'))

WebUI.waitForElementPresent(efileIcon, 10)

println("Class value: " + classValue)

assert classValue.contains("disabled") : 
        "❌ Element is not disabled"

// =====================================================
// ✅ STEP 5: Select Patient → Enable Efile
// =====================================================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_Search'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnPatients'))

WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_Last Name'), 'Portal')
WebUI.setText(findTestObject('Provider Portal/Page_MaximEyes/input_First Name'), 'Provider')

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_button primary small-button'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/td_TESTDATA'))

WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnSendemail'))

WebUI.waitForElementPresent(efileIcon, 10)

// =====================================================
// ✅ STEP 7: Open Efile Popup
// =====================================================
//Verify efile icon enable
WebUI.click(efileIcon)
WebUI.verifyElementVisible(popup)


// =====================================================
// ✅ STEP 8: Verify Columns + Attach Button
// =====================================================
WebUI.verifyElementVisible(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/th_File Name'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/th_Category'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/th_Date'))
WebUI.verifyElementVisible(attachBtn)

// =====================================================
// ✅ STEP 9: Attach Without Selection
// =====================================================
WebUI.click(attachBtn)


// =====================================================
// ✅ STEP 10: Reopen Popup & Close via X
// =====================================================
WebUI.click(efileIcon)
WebUI.click(closePopup)


// =====================================================
// ✅ STEP 11: Select Multiple Efiles
// =====================================================
WebUI.click(efileIcon)

TestObject getEfileCheckbox(int rowIndex) {
	TestObject to = new TestObject()
	String xpath = "//div[@id='extDivEfilePopup']//table/tbody/tr[" + rowIndex + "]//label//span/span[2]"
	to.addProperty("xpath", ConditionType.EQUALS, xpath)
	return to
}


WebUI.click(getEfileCheckbox(1))
WebUI.click(getEfileCheckbox(2))

WebUI.click(attachBtn)


// =====================================================
// ✅ STEP 12: Verify Attachments Section Updated
// =====================================================
WebUI.verifyElementVisible(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/button_ePriscribe.png'))
WebUI.verifyElementVisible(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/button_Screenshot.png'))

// Get text from UI
String fileName = WebUI.getText(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/button_ePriscribe.png'))

// Trim spaces 
String trimmedName = fileName.trim()

// Get length
int length = trimmedName.length()

println("Actual text: '" + fileName + "'")
println("Trimmed text: '" + trimmedName + "'")
println("Length: " + length)

// Verify max 14 characters
assert length <= 14 : "❌ Filename exceeds 14 characters. Actual length: " + length

// Get text from UI
String fileName1 = WebUI.getText(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/button_Screenshot.png'))

// Trim spaces
String trimmedName1 = fileName1.trim()

// Get length
int length1 = trimmedName1.length()

println("Actual text: '" + fileName1 + "'")
println("Trimmed text: '" + trimmedName1 + "'")
println("Length: " + length1)

// Verify max 14 characters
assert length1 <= 14 : "❌ Filename exceeds 14 characters. Actual length: " + length1


// =====================================================
// ✅ STEP 14: Duplicate Efile Validation
// =====================================================
WebUI.click(efileIcon)
WebUI.click(getEfileCheckbox(1))
WebUI.click(getEfileCheckbox(2))
WebUI.click(attachBtn)

WebUI.verifyElementText(toast, "This e-file has already been shared")


// =====================================================
// ✅ STEP 15: Max 5 Attachments Validation
// =====================================================
WebUI.click(efileIcon)

WebUI.click(getEfileCheckbox(1))
WebUI.click(getEfileCheckbox(2))
WebUI.click(getEfileCheckbox(3))
WebUI.click(getEfileCheckbox(4))
WebUI.click(getEfileCheckbox(5))
WebUI.click(getEfileCheckbox(6))

WebUI.click(attachBtn)

WebUI.verifyElementText(toast, "Maximum 5 attachments are allowed")

//Uncheck checkbox
WebUI.click(getEfileCheckbox(1))
WebUI.click(getEfileCheckbox(2))
WebUI.click(getEfileCheckbox(3))
WebUI.click(getEfileCheckbox(4))
WebUI.click(getEfileCheckbox(5))
WebUI.click(getEfileCheckbox(6))
// =====================================================
// ✅ STEP 16: File Size Validation (>25MB)
// =====================================================

//Check the 4th checkbox
WebUI.click(getEfileCheckbox(4))

WebUI.click(attachBtn)

WebUI.verifyElementText(
	toast,
	"The attachment size exceeds the allowable limit. Maximum size of all attachments allowed is 25 MB"
)


// =====================================================
// ✅ STEP 17: Zero Byte File
// =====================================================
WebUI.click(efileIcon)

//Check the 3rd checkbox
WebUI.click(getEfileCheckbox(3))

WebUI.click(attachBtn)

WebUI.verifyElementText(
	toast,
	"Attached files must be greater than 0 bytes!"
)




// =====================================================
// ✅ STEP 19: Remove Attachment via X
// =====================================================
WebUI.click(findTestObject('Object Repository/Provider Portal/Delete File Verification/Delete file1'))

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/button_ePriscribe.png'), 5)

WebUI.click(findTestObject('Object Repository/Provider Portal/Delete File Verification/Delete file1'))

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Provider Portal/Page_MaximEyes/button_Screenshot.png'), 5)

String actualAttachText = WebUI.getText(
	findTestObject('Object Repository/Provider Portal/Delete File Verification/span_No Attachments')
).trim()

WebUI.verifyMatch(actualAttachText, "No Attachments", false)

//Attch efile
WebUI.click(efileIcon)

WebUI.click(getEfileCheckbox(1))
WebUI.click(getEfileCheckbox(2))

WebUI.click(attachBtn)

//Enter subject and message
def subjectField = findTestObject('Provider Portal/Page_MaximEyes/input_ComposeSubject')
def messageField = findTestObject('Provider Portal/New Folder3/Page_MaximEyes/textarea_input InboxTextarea font20 pad05')
def sendButton = findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button')

WebUI.setText(subjectField, "eFile Attachments")
WebUI.setText(messageField, "Test eFile Attachments")

WebUI.click(sendButton)
WebUI.delay(5)

CustomKeywords.'email.ProviderPortalEmail.verifyProviderPortalEmail'(
    "eFile Attachments",
    "Test eFile Attachments",
    ["ePriscribe Test Sigma.png", "Screenshot 2025-12-18 104303.png"]
)
// =====================================================
// ✅ FINAL STEP
// =====================================================
KeywordUtil.markPassed('✔ E-File validation completed successfully')

// ===============================
// 🔹 Validate Sent Message
// ===============================
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_openmoreactionmenu'))
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/div_Sent Messages'))

// Reusable function for text verification
def verifyText(String objPath, String expectedText) {
	TestObject obj = findTestObject(objPath)

	WebUI.waitForElementVisible(obj, 10)   // ✅ Fix
	WebUI.verifyElementPresent(obj, 5)

	String actualText = WebUI.getText(obj).trim()
	String expected = expectedText.trim()

	WebUI.verifyMatch(actualText, expected, false)
}

// Reusable function for click
def clickElement(String objPath) {
	TestObject obj = findTestObject(objPath)
	WebUI.waitForElementClickable(obj, 5)
	WebUI.click(obj)
}

// ====== TEXT VERIFICATIONS ======
verifyText('Provider Portal/Page_MaximEyes/div_eFile Attachments', 'eFile Attachments')
verifyText('Provider Portal/Page_MaximEyes/span_QA User', 'QA User')
verifyText('Provider Portal/Page_MaximEyes/span_QU', 'QU')
verifyText('Provider Portal/Page_MaximEyes/span_To_ Provider Portal', 'To: Provider Portal')
verifyText('Provider Portal/Page_MaximEyes/p_Test eFile Attachments', 'Test eFile Attachments')

// ====== ATTACHMENT VALIDATION ======
String[] attachments = [
	'button_ePriscribe Test Sigma.png',
	'button_Screenshot 2025-12-18 104303.png'
]

attachments.each { file ->
	WebUI.verifyElementPresent(
		findTestObject("Provider Portal/Page_MaximEyes/${file}"),
		5
	)
}



// ====== DELETE FILE IF EXISTS ======
def deleteIfExists(String path, String fileName) {
	File file = new File(path + "/" + fileName)
	if (file.exists()) {
		file.delete()
		println("🗑️ Deleted old file: " + fileName)
	}
}

// ====== WAIT FOR DOWNLOAD ======
def waitForFileDownload(String fileName, String path, int timeout = 30) {
	File dir = new File(path)
	int waited = 0

	while (waited < timeout) {
		File[] files = dir.listFiles()

		if (files != null && files.any {
			it.name.startsWith(fileName.replace(".png","")) &&
			!it.name.endsWith(".crdownload")
		}) {
			println("✅ File downloaded: " + fileName)
			return true
		}

		WebUI.delay(1)
		waited++
	}

	assert false : "❌ File NOT downloaded: " + fileName
}

// ====== MAIN LOGIC ======
String downloadPath = System.getProperty("user.home") + "/Downloads"

Map<String, String> fileMap = [
	'span_ePriscribe Test Sigma.png' : 'ePriscribe Test Sigma.png',
	'span_Screenshot 2025-12-18 104303.png' : 'Screenshot 2025-12-18 104303.png'
]

fileMap.each { obj, fName ->

	// Delete old file (VERY IMPORTANT)
	deleteIfExists(downloadPath, fName)

	// Click attachment
	TestObject to = findTestObject("Provider Portal/Page_MaximEyes/${obj}")
	WebUI.waitForElementClickable(to, 5)
	WebUI.click(to)

	// Wait for download
	waitForFileDownload(fName, downloadPath, 30)

	// Optional: small buffer before next click
	WebUI.delay(2)
}

// Click Compose Sent
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_btnComposeSent'))

// Open menu → Click Inbox → Validate popup
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenuSent'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/div_Inbox'))

String actualPopup = WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/h4_Are you sure you want to navigate away from t')).trim()

WebUI.verifyMatch(actualPopup, 'Are you sure you want to navigate away from the compose message?', false)

// Validate buttons exist (instead of empty text check)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_btnCancel'), 5)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'), 5)

// Click Cancel → Stay on Compose
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnCancel'))


String actualComp = WebUI.getText(findTestObject('Provider Portal/Page_MaximEyes/h4_Compose')).trim()
WebUI.waitForElementVisible(findTestObject('Provider Portal/Page_MaximEyes/h4_Compose'), 10)
WebUI.verifyMatch(actualComp, 'Compose', false)

// Repeat flow → Click OK this time
TestObject inboxOption = findTestObject('Provider Portal/Page_MaximEyes/div_Inbox')
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenuSent'))
WebUI.executeJavaScript(
    "arguments[0].click();",
    Arrays.asList(WebUI.findWebElement(inboxOption, 10))
)
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'))

// Verify Inbox navigation
TestObject inbox = findTestObject('Provider Portal/Page_MaximEyes/div_Inbox_1')

WebUI.waitForElementVisible(inbox, 10)
WebUI.verifyElementPresent(inbox, 10)