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
// ✅ STEP 2: Secure Messages
// =====================================================
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/a_ui-id-9'))

// Navigate to Deleted Messages
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_openmoreactionmenu'))
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/div_Deleted Messages'))

// Verify page header
WebUI.verifyElementText(
	findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/h1_Deleted Messages'),
	'Deleted Messages'
)


// ----------------------
// VERIFY CANCEL ACTIONS
// ----------------------

// Select message and click Delete (Cancel flow)
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_icon-checked'))
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_Delete'))

WebUI.verifyElementText(
	findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/h4_Are you sure you want to permanently delete s'),
	'Are you sure you want to permanently delete selected message?'
)

WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/input_btnCancelDeleteMsgs'))


// Repeat cancel flow (second checkbox)
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_icon-checked_1'))
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_Delete'))

WebUI.verifyElementText(
	findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/h4_Are you sure you want to permanently delete s'),
	'Are you sure you want to permanently delete selected message(s)?'
)

WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/input_btnCancelDeleteMsgs'))


// Delete thread → Cancel
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_deleteThisThread'))

WebUI.verifyElementText(
	findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/h4_Are you sure you want to permanently delete s'),
	'Are you sure you want to permanently delete selected thread?'
)

WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/input_btnCancelDeleteMsg'))


// Delete single message → Cancel
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_deleteThisMessage'))

WebUI.verifyElementText(
	findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/h4_Are you sure you want to permanently delete s'),
	'Are you sure you want to permanently delete selected message?'
)

WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/input_btnCancelDeleteMsg'))


// ----------------------
// VERIFY DELETE ACTIONS
// ----------------------

// Bulk delete
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_Delete'))
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/input_btnDeleteMsgs'))

WebUI.verifyElementText(
	findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/div_Message(s) Deleted'),
	'Message(s) Deleted.'
)


// Delete thread
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_deleteThisThread'))
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/input_btnDeleteMsg'))

WebUI.verifyElementText(
	findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/div_Message(s) Deleted'),
	'Message(s) Deleted.'
)


// Delete single message
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_deleteThisMessage'))
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/input_btnDeleteMsg'))

WebUI.verifyElementText(
	findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/div_Message(s) Deleted'),
	'Message Deleted.'
)


// Checkbox-based delete
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/input_custom-checkbox_1'))
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/span_Delete'))
WebUI.click(findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/input_btnDeleteMsgs'))

WebUI.verifyElementText(
	findTestObject('Provider Portal/Deleted Messages/Page_MaximEyes/div_Message(s) Deleted'),
	'Message Deleted.'
)

//================= Verify Pagination =====================

// =====================================================
// ✅ STEP 2: Validate max 10 rows
// =====================================================
TestObject rowsObj = new TestObject()
rowsObj.addProperty("xpath", ConditionType.EQUALS,
	"//table[@id='idtblquicklinkDelete']//tr[contains(@class,'fixedGridTR')]")

List<WebElement> rows = WebUiCommonHelper.findWebElements(rowsObj, 10)
int rowCount = rows.size()

println "Rows on page: " + rowCount
assert rowCount <= 10 : "More than 10 rows displayed!"

// =====================================================
// ✅ STEP 3: Get pagination info
// =====================================================
TestObject pageInfo = new TestObject()
pageInfo.addProperty("xpath", ConditionType.EQUALS,
	"(//span[contains(@class,'showResult')])[3]")

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
		"//*[@id='deletepagebuttons']//span[text()='›']")

	WebUI.verifyElementPresent(nextBtn, 5)

	// First row (for data comparison)
	TestObject firstRow = new TestObject()
	firstRow.addProperty("xpath", ConditionType.EQUALS,
		"(//table[@id='idtblquicklinkDelete']//tr)[2]")

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

