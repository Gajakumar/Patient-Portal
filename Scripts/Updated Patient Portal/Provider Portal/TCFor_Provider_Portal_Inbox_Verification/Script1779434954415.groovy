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

//Add Subject as "Demo1"
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_f186a3_5'),
	'Test Inbox')

//Add Message for Doctor
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/textarea_Message For Doctor_form-control mt_4ab4b2'),"Inbox Provider Portal")

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

//Upload Invalid File
uploadFileTestCloud(fileUploadInput, baseDir, 'file1.jpg')

//Click on Send Button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/button_Send_Msg'))

//Click on + Icon to compose message
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))

//Add Subject as "Demo1"
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_f186a3_5'),
	'Test Inbox 1st Msg')

//Add Message for Doctor
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/textarea_Message For Doctor_form-control mt_4ab4b2'),"Inbox Provider Portal")

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

//Verify Unread messages in bold
TestObject objcls = findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_David Smith_2')

String classAttr = WebUI.getAttribute(objcls, 'class')

assert classAttr.contains('bold') : "❌ Message is NOT unread (not bold)"

println("✅ Message is unread (bold)")

//Verify new message display with time
String actualTime = WebUI.getText(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_06_40 AM'))

// Regex for 12-hour format: 01:00 AM to 12:59 PM
boolean isValidTime = actualTime ==~ /^(0?[1-9]|1[0-2]):[0-5][0-9]\s?(AM|PM)$/

assert isValidTime : "❌ Invalid time format: " + actualTime

println("✅ Valid time format: " + actualTime)

//Verify old message display with date
String actualDate = WebUI.getText(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_05_20_2026'))

// Regex for MM/DD/YYYY
boolean isValidDate = actualDate ==~ /^(0?[1-9]|1[0-2])\/(0?[1-9]|[12][0-9]|3[01])\/\d{4}$/

assert isValidDate : "❌ Invalid date format: " + actualDate

println("✅ Valid date format: " + actualDate)

//Verify attachment icon is present for message
WebUI.verifyElementPresent(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_mif-Attach font22 fg-grayLight floatL line-'),
	5)

//Below search field following icons should display

// ===== Timeout =====
int timeout = 5

// ===== Reusable function =====
def verify = { path ->
	TestObject obj = findTestObject(path)
	WebUI.waitForElementVisible(obj, timeout)
	WebUI.verifyElementPresent(obj, timeout)
}

// ===== Verify Top Icons =====
verify('Provider Portal/Inbox/Page_MaximEyes/span_markAsReviewed') 
verify('Provider Portal/Inbox/Page_MaximEyes/span_flagForProvider')
verify('Provider Portal/Inbox/Page_MaximEyes/span_btnCompose')
verify('Provider Portal/Inbox/Page_MaximEyes/span_openmoreactionmenu')

// ===== Click Hamburger Menu =====
WebUI.click(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_openmoreactionmenu'))

// ===== Verify Menu Options =====
verify('Provider Portal/Inbox/Page_MaximEyes/div_Outbox')
verify('Provider Portal/Inbox/Page_MaximEyes/div_Sent Messages')
verify('Provider Portal/Inbox/Page_MaximEyes/div_Deleted Messages')
verify('Provider Portal/Inbox/Page_MaximEyes/div_Activity Log')

//Thread Icons Validation
// ===== Thread Action Icons =====
verify('Provider Portal/Inbox/Page_MaximEyes/span_Mark as Reviewed')
verify('Provider Portal/Inbox/Page_MaximEyes/span_Create Task')
verify('Provider Portal/Inbox/Page_MaximEyes/span_Flag this message for a provider')
verify('Provider Portal/Inbox/Page_MaximEyes/span_btnreplyicon')
verify('Provider Portal/Inbox/Page_MaximEyes/span_ForwardScreenicon')
verify('Provider Portal/Inbox/Page_MaximEyes/span_deleteThisThread')

// ===== Click Patient Name =====
TestObject patientName = findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_David Smith')

WebUI.waitForElementClickable(patientName, 5)
String expectedName = WebUI.getText(patientName).trim()

WebUI.click(patientName)

// ===== Verify Patient Details Page =====
TestObject patientDetailsHeader = findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_Patient Details')

WebUI.waitForElementVisible(patientDetailsHeader, 10)

// Normalize text (removes spaces/newlines)
String actualHeader = WebUI.getText(patientDetailsHeader)
                          .toLowerCase()
                          .replaceAll("\\s+", "")
                          .trim()

assert actualHeader.contains('patientdetails') : 
       "❌ Header mismatch: " + actualHeader


println("✅ Patient Details page opened")

// ===== Verify Patient Name =====
String actualName = WebUI.getText(findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_David Smith_1')).trim()

assert actualName == expectedName :
	   "❌ Patient name mismatch. Expected: ${expectedName}, Found: ${actualName}"

println("✅ Patient name verified: " + actualName)



// ===== Navigate to Inbox =====
WebUI.waitForElementClickable(findTestObject('Provider Portal/Inbox/Page_MaximEyes/a_navItemHome'), timeout)
WebUI.click(findTestObject('Provider Portal/Inbox/Page_MaximEyes/a_navItemHome'))

WebUI.waitForElementClickable(findTestObject('Provider Portal/Inbox/Page_MaximEyes/a_ui-id-9'), timeout)
WebUI.click(findTestObject('Provider Portal/Inbox/Page_MaximEyes/a_ui-id-9'))

// ===== Click Delete from Right pane (1st time - Cancel) =====
TestObject deletReBtn = findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_deleteThisThread')

WebUI.waitForElementClickable(deletReBtn, timeout)
WebUI.click(deletReBtn)

// Cancel delete
TestObject cancelBtn = findTestObject('Provider Portal/Inbox/Page_MaximEyes/input_btnCancelDeleteMsg')
WebUI.waitForElementClickable(cancelBtn, timeout)
WebUI.click(cancelBtn)

println("✅ Delete cancelled successfully")

// ===== Click Delete Again (Confirm) =====
WebUI.waitForElementClickable(deletReBtn, timeout)
WebUI.click(deletReBtn)

// Confirm delete
TestObject confirmDelete = findTestObject('Provider Portal/Inbox/Page_MaximEyes/input_btnDeleteMsg')
WebUI.waitForElementClickable(confirmDelete, timeout)
WebUI.click(confirmDelete)

//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Message(s) Deleted.')

//Delete message from left pane
// ===== Select Message (Checkbox) =====
TestObject checkbox = findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_icon-checked')
WebUI.waitForElementClickable(checkbox, timeout)
WebUI.click(checkbox)

// ===== Click Delete =====
TestObject deleteBtn = findTestObject('Provider Portal/Inbox/Page_MaximEyes/span_Delete')
WebUI.waitForElementClickable(deleteBtn, timeout)
WebUI.click(deleteBtn)

// ===== Verify Confirmation Popup =====
TestObject popupMsg = findTestObject('Provider Portal/Inbox/Page_MaximEyes/h4_Are you sure you want to permanently delete s')

WebUI.waitForElementVisible(popupMsg, 10)
String actualPopupText = WebUI.getText(popupMsg).trim()

assert actualPopupText.contains('permanently delete') :
	   "❌ confirmation message: " + actualPopupText

println("✅ Delete confirmation popup verified")

// ===== Cancel Delete =====
WebUI.waitForElementClickable(cancelBtn, timeout)
WebUI.click(cancelBtn)

println("✅ Delete cancelled")

// ===== Delete Again (Confirm Flow) =====
WebUI.waitForElementClickable(deleteBtn, timeout)
WebUI.click(deleteBtn)

// Confirm delete
TestObject confirmBtn = findTestObject('Provider Portal/Inbox/Page_MaximEyes/input_btnDeleteMsgs')
WebUI.waitForElementClickable(confirmBtn, timeout)
WebUI.click(confirmBtn)

println("✅ Messages deleted successfully")

//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Message Deleted.')

//================= Verify Pagination =====================

// =====================================================
// ✅ STEP 2: Validate max 10 rows
// =====================================================
TestObject rowsObj = new TestObject()
rowsObj.addProperty("xpath", ConditionType.EQUALS,
	"//tbody[@id='idquicklinkGridofinbox']//tr[contains(@class,'fixedGridTR')]")

List<WebElement> rows = WebUiCommonHelper.findWebElements(rowsObj, 10)
int rowCount = rows.size()

println "Rows on page: " + rowCount
assert rowCount <= 10 : "More than 10 rows displayed!"

// =====================================================
// ✅ STEP 3: Get pagination info
// =====================================================
TestObject pageInfo = new TestObject()
pageInfo.addProperty("xpath", ConditionType.EQUALS,
	"//span[contains(@class,'showResultpos')]")

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
		"//div[@id='inboxpagebuttons']//span[text()='›']")

	WebUI.verifyElementPresent(nextBtn, 5)

	// First row (for data comparison)
	TestObject firstRow = new TestObject()
	firstRow.addProperty("xpath", ConditionType.EQUALS,
		"(//tbody[@id='idquicklinkGridofinbox']//tr)[1]")

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


