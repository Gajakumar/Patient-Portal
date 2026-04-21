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