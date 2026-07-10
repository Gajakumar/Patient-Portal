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


// =====================================================
// ✅ STEP 1: Max Login
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
// ✅ STEP 14: Upload 5 Files 
// =====================================================
['file1.jpg','file2.jpg','file3.jpg','file4.jpg','file5.jpg'].each { fileName ->
    uploadFileTestCloud(fileUploadInput, baseDir, fileName)
}

def subjectField = findTestObject('Provider Portal/Page_MaximEyes/input_ComposeSubject')
def messageField = findTestObject('Provider Portal/New Folder3/Page_MaximEyes/textarea_input InboxTextarea font20 pad05')
def sendButton = findTestObject('Provider Portal/Page_MaximEyes/button_compose-send-button')
// =====================================================
// ✅ STEP 29: Click Send
// =====================================================
WebUI.setText(subjectField, "Outbox")
WebUI.setText(messageField, "Test Outbox")
WebUI.click(sendButton)

WebUI.closeBrowser()
WebUI.openBrowser('')
WebUI.maximizeWindow()

// =====================================================
// ✅ STEP 1: Max Login
// =====================================================
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

// =====================================================
// ✅ STEP 2: Open Messages Tab
// =====================================================
WebUI.waitForElementClickable(menu, 10)
WebUI.click(menu)

// ===== Click Hamburger Menu >> Outbox =====
WebUI.click(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_openmoreactionmenu'))
WebUI.click(findTestObject('Provider Portal/Inbox/Page_MaximEyes/div_Outbox'))

// Verify attachment is present in Outbox
TestObject fileObj = findTestObject('Provider Portal/Outbox/Page_MaximEyes/button_largeFile1.pdf')
WebUI.verifyElementPresent(fileObj, 5)

// Verify page title
TestObject outboxTitle = findTestObject('Provider Portal/Outbox/Page_MaximEyes/p_Test Outbox')
WebUI.verifyElementText(outboxTitle, 'Test Outbox')

// Verify portal header
TestObject portalHeader = findTestObject('Provider Portal/Outbox/Page_MaximEyes/span_Provider Portal')
WebUI.verifyElementText(portalHeader, 'Provider Portal')

// Verify status (trim spaces issue handled)
TestObject sendingStatus = findTestObject('Provider Portal/Outbox/Page_MaximEyes/span_Sending')
String actualStatus = WebUI.getText(sendingStatus).trim()

assert actualStatus.contains('Sending...')

// Retry sending
TestObject retryBtn = findTestObject('Provider Portal/Outbox/Page_MaximEyes/button_btnmainRetry')
WebUI.waitForElementClickable(retryBtn, 10)
WebUI.click(retryBtn)


// Verify success message
TestObject successMsg = findTestObject('Provider Portal/Outbox/Page_MaximEyes/div_Email sent successfully')
WebUI.waitForElementVisible(successMsg, 10)
WebUI.verifyElementText(successMsg, 'Email sent successfully')

// ===== Click Hamburger Menu >> Inbox =====
WebUI.waitForElementClickable(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_openmoremenuoutbox'), 10)
WebUI.click(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_openmoremenuoutbox'))
WebUI.click(findTestObject('Provider Portal/Inbox/Page_MaximEyes/div_Inbox'))

// ===== Click Hamburger Menu >> Outbox =====
WebUI.waitForElementClickable(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_openmoreactionmenu'), 10)
WebUI.click(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_openmoreactionmenu'))
WebUI.waitForElementClickable(findTestObject('Provider Portal/Inbox/Page_MaximEyes/div_Outbox - Inbox'), 10)
WebUI.click(findTestObject('Provider Portal/Inbox/Page_MaximEyes/div_Outbox - Inbox'))

// Select checkbox
TestObject checkbox = findTestObject('Provider Portal/Outbox/Page_MaximEyes/input_custom-checkbox')
WebUI.waitForElementClickable(checkbox, 10)
WebUI.click(checkbox)


// Click Delete
TestObject deleteBtn = findTestObject('Provider Portal/Outbox/Page_MaximEyes/span_Delete')
WebUI.waitForElementClickable(deleteBtn, 10)
WebUI.click(deleteBtn)


// Handle confirmation popup → NO (Cancel)
TestObject cancelDelete = findTestObject('Provider Portal/Outbox/Page_MaximEyes/input_btnCancelDeleteMsgs')
WebUI.waitForElementVisible(cancelDelete, 10)
WebUI.click(cancelDelete)


// Click Delete again
WebUI.waitForElementClickable(deleteBtn, 10)
WebUI.click(deleteBtn)


// Confirm deletion → YES
TestObject confirmDelete = findTestObject('Provider Portal/Outbox/Page_MaximEyes/input_btnDeleteMsgs')
WebUI.waitForElementVisible(confirmDelete, 10)
WebUI.click(confirmDelete)

//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Message Deleted.')

// Delete thread
TestObject deleteThread = findTestObject('Provider Portal/Outbox/Page_MaximEyes/span_deleteThisThread')
WebUI.waitForElementClickable(deleteThread, 10)
WebUI.click(deleteThread)


// Final confirmation
TestObject finalDelete = findTestObject('Provider Portal/Outbox/Page_MaximEyes/input_btnDeleteMsg')
WebUI.waitForElementVisible(finalDelete, 10)
WebUI.click(finalDelete)

//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Message(s) Deleted.')

//================= Verify Pagination =====================

// =====================================================
// ✅ STEP 2: Validate max 10 rows
// =====================================================
TestObject rowsObj = new TestObject()
rowsObj.addProperty("xpath", ConditionType.EQUALS,
	"//tbody[@id='idquicklinkGridForOutbox']//tr[contains(@class,'fixedGridTR')]")

List<WebElement> rows = WebUiCommonHelper.findWebElements(rowsObj, 10)
int rowCount = rows.size()

println "Rows on page: " + rowCount
assert rowCount <= 10 : "More than 10 rows displayed!"

// =====================================================
// ✅ STEP 3: Get pagination info
// =====================================================
TestObject pageInfo = new TestObject()
pageInfo.addProperty("xpath", ConditionType.EQUALS,
	"//span[contains(@class,'showResult pos')]")

WebUI.waitForElementVisible(pageInfo, 10)

String pageText = WebUI.getText(pageInfo).trim()
println "Page Text: " + pageText

assert pageText.contains("of") : "Pagination text not loaded!"

def matcher = (pageText =~ /of\s+(\d+)/)
assert matcher.find()

int totalRecords = matcher.group(1).toInteger()
println "Total records: " + totalRecords

// =====================================================
// ✅ STEP 4: Validate pagination (if >10 records)
// =====================================================
if (totalRecords > 10) {

	// Next button
	TestObject nextBtn = new TestObject()
	nextBtn.addProperty("xpath", ConditionType.EQUALS,
		"//div[@id='outboxpagebuttons']//span[text()='›']")

	WebUI.verifyElementPresent(nextBtn, 5)

	// First row (for data comparison)
	TestObject firstRow = new TestObject()
	firstRow.addProperty("xpath", ConditionType.EQUALS,
		"(//tbody[@id='idquicklinkGridForOutbox']//tr)[1]")

	String beforeClick = WebUI.getText(firstRow)
	println "Before Click Row: " + beforeClick

	// =====================================================
	// ✅ Click NEXT (robust)
	// =====================================================
	try {
		WebUI.click(nextBtn)
	} catch (Exception e) {
		WebUI.executeJavaScript(
			"arguments[0].click();",
			Arrays.asList(WebUI.findWebElement(nextBtn))
		)
	}

	// =====================================================
	// ✅ Wait until data changes (AJAX safe)
	// =====================================================
	int maxWait = 10
	boolean pageChanged = false

	for (int i = 0; i < maxWait; i++) {

		String afterClick = WebUI.getText(firstRow)

		if (afterClick != beforeClick) {
			println "After Click Row: " + afterClick
			pageChanged = true
			break
		}

		WebUI.delay(1)
	}

	assert pageChanged : "Pagination not working! Data did not change."
}





