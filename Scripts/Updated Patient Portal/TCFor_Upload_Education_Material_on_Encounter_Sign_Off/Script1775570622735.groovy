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