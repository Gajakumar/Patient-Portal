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
import stories.NavigateStory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.JavascriptExecutor

NavigateStory nav = new NavigateStory()
// =====================================================
// LOGIN TO MAXIMEYES
// =====================================================

// Login to Maximeyes Patient Portal
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)
//Navigate to OA
WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/span__mif-cog font20 head-icon-shadow fg-white'))

//Click on Modules
WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/a_Modules'))

//Click on Encounters
WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/a_Encounters'))

//Click on Incentive Programs
WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/a_Incentive Programs'))

//Send Education Material from all Elements on Sign off of encounter
CustomKeywords.'common.UIUtils.toggleCheckbox'(findTestObject('OA Maximeyes/Page_MaximEyes/span_Send Education Material from all Elements o'), true)


//Navigate to Home
WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/Home btn'))

// Create Random Patient (dynamic data)
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)

// Click on Patient Portal signup (+ icon)
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

// Select "Send Sign Up Email"
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

// Click Proceed button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

// Wait for loader to disappear
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)

// Verify success toast message
WebUI.verifyElementText(
	findTestObject('Object Repository/Page_MaximEyes/Toast Msg'),
	'Patient Portal Sign Up Completed. Email Sent.'
)

// Wait for email delivery
WebUI.delay(10)

// Extract Username & Password from email
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Access to your health data'
)

println('Username: ' + GlobalVariable.GV_Username)
println('Password: ' + GlobalVariable.GV_Password)


//Click on encounter dropdown
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

//Click on Create new encounter
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Actions_Encounters  Add New Encounter'))

//Select Encounter type as : Automation Element Test Encounter
WebUI.selectOptionByLabel(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Encounter Type_EncounterTypeID'),
	'Automation Element Test Encounter',
	false
)

//Click on Add button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))


TestObject createNewEncounterBtn =
		findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter')

//Verify Create new encounter button is displayed then click on it
if (WebUI.verifyElementPresent(createNewEncounterBtn, 5, FailureHandling.OPTIONAL)) {
	WebUI.click(createNewEncounterBtn)
	println('Create New Encounter button clicked')
} else {
	println('Create New Encounter button not displayed – skipping click')
}

WebUI.delay(2)

//Click on Cheif Compalaints
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Intake Form_encTabList_1'))

// =====================================================
// ADD FIRST PROBLEM
// =====================================================

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Add Problem Plus button'))

WebUI.setText(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Loading_DESCRIPTION_GF_b04f_GF_I'),
	'Alcohol abuse'
)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/em'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_B-1'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_DDD_L_LBI0T0'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_B-1'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_DDD_L_LBI0T0'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_--Select--_problemListGridView_b04f_E_114e19'))

nav.SelectEncounterElementFromLeftNavOnEncounter([
	pElementPage: "Final Findings",
	pElement    : "Final Outbound Documents"
])


nav.SelectEncounterElementFromLeftNavOnEncounter([
	pElementPage: "Chief Complaint & HPI",
	pElement    : "Problems"
])

// =====================================================
// ADD SECOND PROBLEM
// =====================================================
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Add Problem Plus button'))
WebUI.delay(2)
TestObject problemCell = findTestObject( 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Problem Row', [ 'rowId' : 1, 'colId' : 1 ] )
WebUI.click(problemCell)
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Loading_DESCRIPTION_GF_b04f_GF_I'), 'Gout')
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_DESCRIPTION_GF_b04f_GF_DDD_L_LBI0T0'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_B-1'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_DDD_L_LBI0T0'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_B-1'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_DDD_L_LBI0T0'))


// Navigate to Final Outbound Documents
nav.SelectEncounterElementFromLeftNavOnEncounter([
	pElementPage: "Final Findings",
	pElement    : "Final Outbound Documents"
])

WebUI.delay(5)

// Click "+" to add document
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_glyphicon-circelplus font17 fg-skyblue'))

// Wait for loaders
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Scenario Update1703/Page_MaximEyes/Page Loader'), 30)
WebUI.waitForElementNotVisible(findTestObject('Scenario Update1703/Page_MaximEyes/svg_txRibbonLoader'), 30)

// Select document type & template
WebUI.selectOptionByValue(findTestObject('Scenario Update1703/Page_MaximEyes/select_ddlDocumentType'), '11', false)
WebUI.selectOptionByValue(findTestObject('Scenario Update1703/Page_MaximEyes/select_ddlDocumentTemplate'), '180', false)

// Click Select Document
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/button_btnSelectDocument'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

// Add recipient
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_btnAddRecipient'))

// Select recipient type = Patient
WebUI.selectOptionByValue(findTestObject('Scenario Update1703/Page_MaximEyes/select_ddlRecipientType'), 'Patient', false)

// Select Print + Portal
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_chkIsPrint'))
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_icon-checked'))

// Save recipient
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_btnAddChildRecipientDetails'))
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_btnSaveChildRecipients'))

//Click on OK button
WebUI.click(findTestObject('Object Repository/Scenario Update1703/Page_MaximEyes/OK Btn on Add FOD Doc'))

//wait for busy indicator to disapear 
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Click on sign off button
WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/span_TOC Req_spnSignOff'))

//click on yes button on upcoming prompt
WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/input_Are you sure you want to sign off the_f71194'))

//Enter password
WebUI.setText(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/input_Patient Portal_signaturePassword'), '123456')

//click ok button
WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/input_Patient Portal_authenticateUserSignature'))

//Verify toast msg
//CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Health information resource uploaded successfully on Patient Portal.')

// Open new tab
WebUI.executeJavaScript("window.open('about:blank','_blank');", [])

// Switch to 2nd tab
WebUI.switchToWindowIndex(1)

// =====================================================
// LOGIN TO PATIENT PORTAL
// =====================================================

// Open Patient Portal
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

// Click Sign In
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

// Login with credentials
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password],
	FailureHandling.STOP_ON_FAILURE
)

// DOB confirmation + signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

// Fetch OTP from email
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println('OTP fetched = ' + otp)

// Enter OTP digits
String[] digits = otp.toCharArray()

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1'), digits[0])
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2'), digits[1])
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3'), digits[2])
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'), digits[3])

WebUI.delay(5)

// Click Proceed
TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
WebUI.waitForElementClickable(proceedBtn, 15)
WebUI.click(proceedBtn)

// Update password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

// Login again with new password
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword],
	FailureHandling.STOP_ON_FAILURE
)

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

// Click Proceed
WebUI.waitForElementClickable(proceedBtn, 15)
WebUI.click(proceedBtn)

WebUI.delay(10)


// Verify dashboard name + date
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'),
	[('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName],
	FailureHandling.STOP_ON_FAILURE
)

// Verify unread message count
String actualUnreadMsgCount = WebUI.getText(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Message Count')
).replaceAll("\\s+", "").trim()

WebUI.verifyMatch(actualUnreadMsgCount, "2", false)

// =====================================================
// MESSAGE VALIDATION
// =====================================================

// Open Messages
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Mark Wood_border-2 rounded-full p-4 smp_311faa'))

// Verify subject
WebUI.verifyElementText(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/p_To Mark Wood_text-sm font-medium text-gra_224964'),
	'Education material: Cataract Consultation'
)

// Open first message
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Inbox_px-3 py-3 border-b border-gray-20_cf1afb'))

// Download attachment
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Download Attchment'))

// Validate document content
TestObject docObj = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/MsgContent')

CustomKeywords.'common.PatientPortalValidator.validatePatientPortalDocument'(
	docObj,
	GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName
)

//Verify msg contains
WebUI.verifyElementText(
		findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/p_To Mark Wood_text-sm font-medium text-gra_224964_1'),
		'Multiple Education Materials'
)

//Click on 2nd msg
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_To Mark Wood_px-3 py-3 border-b border-_da13b9'))

// ---------- Configurable timeout ----------

int TIMEOUT = 30
try {
	TIMEOUT = Integer.parseInt((GlobalVariable.G_Timeout as String).trim())
} catch (Exception ignored) {
	TIMEOUT = 30
}

// ---------- Test Objects (Patient Portal - Inbox view) ----------
TestObject to_btnArchive        = findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive Message Thread')
TestObject to_btnReply          = findTestObject('Edu Material Verification/Page_Patient Portal/button_Reply Message Thread')
TestObject to_btnDelete         = findTestObject('Edu Material Verification/Page_Patient Portal/button_Delete Message Thread')
TestObject to_btnPrint          = findTestObject('Edu Material Verification/Page_Patient Portal/button_Print Education Material')
TestObject to_btnDownloadPDF    = findTestObject('Edu Material Verification/Page_Patient Portal/button_Download PDF')
TestObject to_pPatientPortal    = findTestObject('Edu Material Verification/Page_Patient Portal/p_Patient Portal')
TestObject to_spanAUD           = findTestObject('Edu Material Verification/Page_Patient Portal/span_Alcohol Use Disorder (AUD)')
TestObject to_linkAUD           = findTestObject('Edu Material Verification/Page_Patient Portal/a_https_medlineplus.gov_alcoholusedisorderaud.ht')

TestObject to_btnArchive1       = findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive Message Thread_1')
TestObject to_pConfirmArchive   = findTestObject('Edu Material Verification/Page_Patient Portal/p_Are you sure you want to archive the selected')
TestObject to_btnArchiveConfirm = findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive')
TestObject to_divArchivedToast  = findTestObject('Edu Material Verification/Page_Patient Portal/div_1')
TestObject to_divTooltip        = findTestObject('Edu Material Verification/Page_Patient Portal/div_tooltip')
TestObject to_divArchivedMenu   = findTestObject('Edu Material Verification/Page_Patient Portal/div_Archived Messages')

// ---------- Test Objects (Archived Messages view) ----------
TestObject to_threadRowArchived   = findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi')
TestObject to_btnUnarchive1       = findTestObject('Edu Material Verification/Page_Patient Portal/button_Unarchive Message Thread_1')
//TestObject to_btnUnarchive2       = findTestObject('Edu Material Verification/Page_Patient Portal/button_Unarchive Message Thread_2')
TestObject to_pNoArchivedMessages = findTestObject('Edu Material Verification/Page_Patient Portal/p_You have no messages in archived messages')
TestObject to_divTooltip1         = findTestObject('Edu Material Verification/Page_Patient Portal/div_tooltip_1')
TestObject to_divInboxMenu        = findTestObject('Edu Material Verification/Page_Patient Portal/div_Inbox')
TestObject to_threadRowInbox1     = findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi_1')

// ==============================================================
// Helper methods (timeout is always a REQUIRED explicit argument)
// ==============================================================

/** Waits for an element to be present, then asserts presence (fails test if missing). */
def verifyPresent(TestObject to, int timeout) {
	WebUI.waitForElementPresent(to, timeout)
	WebUI.assertElementPresent(to, 0)
}

/** Waits for an element to be clickable, then clicks it. */
def safeClick(TestObject to, int timeout) {
	WebUI.waitForElementClickable(to, timeout)
	WebUI.click(to)
}

/** Waits for an element to be visible, then asserts its text (trims both sides to avoid whitespace flakiness). */
def verifyText(TestObject to, String expectedText, int timeout) {
	WebUI.waitForElementVisible(to, timeout)
	String actualText = WebUI.getText(to)
	if (actualText?.trim() != expectedText?.trim()) {
		KeywordUtil.markFailed("Text mismatch for '${to.getObjectId()}'. Expected: '${expectedText}' | Actual: '${actualText}'")
	}
}

/**
 * Verifies the standard set of thread-action buttons + edu material buttons
 * present in either the Inbox or Archived Messages view.
 */
def verifyThreadActionButtons(TestObject archiveOrUnarchiveBtn, TestObject replyBtn, TestObject deleteBtn,
							   TestObject printBtn, TestObject downloadBtn, int timeout) {
	verifyPresent(archiveOrUnarchiveBtn, timeout)
	verifyPresent(replyBtn, timeout)
	verifyPresent(deleteBtn, timeout)
	verifyPresent(printBtn, timeout)
	verifyPresent(downloadBtn, timeout)
}

/**
 * Clicks a link that is expected to open in a NEW browser tab,
 * verifies the new tab opened and navigated to the expected URL
 * fragment, then closes the new tab and returns focus to the
 * original (parent) tab.
 */
def verifyLinkOpensInNewTab(TestObject linkObject, String expectedUrlFragment, int timeout) {
	WebDriver driver = DriverFactory.getWebDriver()
	String parentHandle = driver.getWindowHandle()
	Set<String> originalHandles = driver.getWindowHandles()

	WebUI.waitForElementClickable(linkObject, timeout)
	WebUI.click(linkObject)

	// Poll for a new window handle to appear instead of a fixed sleep
	Set<String> newHandles = null
	int waited = 0
	while (waited < timeout) {
		newHandles = driver.getWindowHandles()
		if (newHandles.size() > originalHandles.size()) break
		WebUI.delay(1)
		waited++
	}

	if (newHandles == null || newHandles.size() <= originalHandles.size()) {
		KeywordUtil.markFailed("Expected a new tab to open after clicking '${linkObject.getObjectId()}', but no new window handle was detected within ${timeout}s.")
		return
	}

	newHandles.removeAll(originalHandles)
	String newTabHandle = newHandles.iterator().next()

	driver.switchTo().window(newTabHandle)
	WebUI.waitForPageLoad(timeout)

	String newTabUrl = driver.getCurrentUrl()
	if (!newTabUrl?.toLowerCase()?.contains(expectedUrlFragment.toLowerCase())) {
		KeywordUtil.markFailed("New tab URL did not contain expected fragment. Expected to contain: '${expectedUrlFragment}' | Actual: '${newTabUrl}'")
	} else {
		KeywordUtil.logInfo("Verified new tab opened with URL: ${newTabUrl}")
	}

	// Close the new tab and return focus to the original tab
	driver.close()
	driver.switchTo().window(parentHandle)
}

//  DOWNLOAD-STARTED VERIFICATION (works locally AND on Katalon TestCloud)
// ---------------------------------------------------------------

def verifyDownloadStarted(TestObject downloadBtn, int timeout) {
	WebDriver driver = DriverFactory.getWebDriver()
	String originalUrl = driver.getCurrentUrl()
 
	safeClick(downloadBtn, timeout)
 
	String detectedFileName = null
	int waited = 0
	while (waited < timeout) {
		driver.get('chrome://downloads/')
		try {
			detectedFileName = (String) ((JavascriptExecutor) driver).executeScript(
				"var list = document.querySelector('downloads-manager') && " +
				"document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList').items; " +
				"return (list && list.length > 0) ? list[0].fileName : null;"
			)
		} catch (Exception ignored) {
			detectedFileName = null
		}
		if (detectedFileName) break
		WebUI.delay(1)
		waited++
	}
 
	// Return focus to the app regardless of outcome
	driver.get(originalUrl)
	WebUI.waitForPageLoad(timeout)
 
	if (!detectedFileName) {
		KeywordUtil.markFailed("No download appeared in chrome://downloads/ within ${timeout}s after clicking Download PDF.")
		return
	}
 
	KeywordUtil.logInfo("Download started successfully: '${detectedFileName}'.")
}
// ==============================================================
// Test Steps
// ==============================================================

// ---- 1. Verify Inbox thread view: action buttons + text ----
verifyThreadActionButtons(to_btnArchive, to_btnReply, to_btnDelete, to_btnPrint, to_btnDownloadPDF, TIMEOUT)
verifyText(to_pPatientPortal, 'Patient Portal', TIMEOUT)
verifyText(to_spanAUD, ' Alcohol Use Disorder (AUD)', TIMEOUT)

// ---- 2. Verify the education material link opens in a new tab ----
verifyLinkOpensInNewTab(to_linkAUD, 'medlineplus.gov/alcoholusedisorderaud', TIMEOUT)

// ---- 3. Archive the message thread ----
safeClick(to_btnArchive1, TIMEOUT)
verifyText(to_pConfirmArchive, 'Are you sure you want to archive the selected messages?', TIMEOUT)
safeClick(to_btnArchiveConfirm, TIMEOUT)
//verify toast msg
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Message(s) archived successfully!')

// ---- 4. Navigate to Archived Messages and verify state ----
safeClick(to_divTooltip, TIMEOUT)
safeClick(to_divArchivedMenu, TIMEOUT)

verifyPresent(to_threadRowArchived, TIMEOUT)
verifyThreadActionButtons(to_btnUnarchive1, to_btnReply, to_btnDelete, to_btnPrint, to_btnDownloadPDF, TIMEOUT)

// ---- 5. Unarchive the message thread ----
safeClick(to_btnUnarchive1, TIMEOUT)
verifyText(to_pConfirmArchive, 'Are you sure you want to unarchive the selected messages?', TIMEOUT)
safeClick(to_btnArchiveConfirm, TIMEOUT)
verifyText(to_pNoArchivedMessages, 'You have no messages in archived messages', TIMEOUT)

// ---- 6. Navigate back to Inbox and verify the thread returned ----
safeClick(to_divTooltip1, TIMEOUT)
safeClick(to_divInboxMenu, TIMEOUT)
safeClick(to_threadRowInbox1, TIMEOUT)
verifyPresent(to_threadRowArchived, TIMEOUT)

// ---- 7. Click Download PDF and verify the download actually started ----
verifyDownloadStarted(to_btnDownloadPDF, TIMEOUT)

KeywordUtil.logInfo("Edu Material Verification - Patient Portal test completed successfully.")