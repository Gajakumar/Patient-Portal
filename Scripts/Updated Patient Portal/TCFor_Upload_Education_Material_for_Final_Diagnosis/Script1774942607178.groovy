import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import stories.NavigateStory
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

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


// =====================================================
// CREATE ENCOUNTER & SEND DOCUMENT
// =====================================================

// Open Encounters dropdown
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

// Click "Add New Encounter"
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Actions_Encounters  Add New Encounter'))

// Select encounter type
WebUI.selectOptionByLabel(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Encounter Type_EncounterTypeID'),
	'Automation Element Test Encounter',
	false
)

// Click Add button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))

// Handle optional "Create New Encounter" popup
TestObject createNewEncounterBtn =
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter')

if (WebUI.verifyElementPresent(createNewEncounterBtn, 5, FailureHandling.OPTIONAL)) {
	WebUI.click(createNewEncounterBtn)
	println('Create New Encounter button clicked')
} else {
	println('Create New Encounter button not displayed – skipping click')
}

WebUI.delay(2)

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

// Send document
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_btnSendDocument'))


// =====================================================
// VALIDATE DOCUMENT SENT STATUS
// =====================================================

// Verify document name
TestObject obj = findTestObject('Object Repository/Scenario Update1703/Page_MaximEyes/a_Cataract Consultation')

WebUI.waitForElementPresent(obj, 20)
WebUI.verifyElementText(obj, 'Cataract Consultation')

// Verify "Sent" status
TestObject sentStatus = findTestObject('Scenario Update1703/Page_MaximEyes/span_Sent_1')

WebUI.waitForElementPresent(sentStatus, 15)

if (WebUI.verifyElementPresent(sentStatus, 5, FailureHandling.OPTIONAL)) {
	String text = WebUI.getText(sentStatus).trim()
	assert text.equalsIgnoreCase("Sent")
} else {
	KeywordUtil.markFailed("❌ 'Sent' status not found")
}

// Open dropdown
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_mif-dropdown font15 fg-skyblue'))

// Verify "Send To Portal"
WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Send To Portal'), 'Send To Portal')

// Click again
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_mif-dropdown font15 fg-skyblue_1'))

// Validate dynamic patient name
String expectedPatientName = GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName + " (Patient)"

TestObject patientCell = new TestObject().addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//td[contains(@class,'data-hj-suppress') and contains(text(),'Patient')]"
)

WebUI.waitForElementPresent(patientCell, 15)

String actualText = WebUI.getText(patientCell).trim()

assert actualText.contains(expectedPatientName) :
"❌ Expected name not found. Actual: " + actualText

// Verify status labels
WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Send To Portal'), 'Send To Portal')
WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Sent'), 'Sent')

// Verify success message
WebUI.verifyElementText(
	findTestObject('Scenario Update1703/Page_MaximEyes/td_Document sent to Patient Portal successfully'),
	'Document sent to Patient Portal successfully.'
)

// Close encounter
WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/div_encounterform13ea63070d'))

// Navigate to Encounter History
nav.ClickMegaMenuItems([('TopMenuOption') : 'Encounters', ('SubItem') : 'Encounter Hx'])


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

WebUI.verifyMatch(actualUnreadMsgCount, "1", false)


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