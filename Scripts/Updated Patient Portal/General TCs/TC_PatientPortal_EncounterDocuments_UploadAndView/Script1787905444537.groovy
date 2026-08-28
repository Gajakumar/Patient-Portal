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
import stories.NavigateStory

NavigateStory nav = new NavigateStory()

//Login to Maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

//Create Random Patient
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Get Patient ID
TestObject patientIdObj = findTestObject(
	'Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen'
)

WebUI.waitForElementVisible(patientIdObj, 15)

GlobalVariable.GV_PatientID =
	WebUI.getAttribute(patientIdObj, 'value') ?: ''

println "✅ Patient ID stored: " + GlobalVariable.GV_PatientID

//Portal sign up using email
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Portal Sign up using email'), [:], FailureHandling.STOP_ON_FAILURE)

//Get username and password from email
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)

println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password

WebUI.delay(5)

//Create new encounter
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Add New Encounter_Create New'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Hamburger menu at left
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))

//Click on Summary Of Care(C-CDA)
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Summary Of Care(C-CDA)'))

//Verify SOC page is opened
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/h2_Summary of Care (C-CDA)'),
	'Summary of Care (C-CDA)')

//Click on Upload button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_UploadToPatientPortal'))

//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Upload to Patient Portal completed successfully.')

//Click on close button on SOC
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

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

WebUI.delay(2)

TestObject popup =
		findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Health Information Resource_title')

TestObject eduMaterial = findTestObject(
		'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_EHR_EducationMaterial',
		['Desc': 'Alcohol abuse']
)

WebUI.waitForElementPresent(eduMaterial, 20)

int maxAttempts = 3

for (int i = 1; i <= maxAttempts; i++) {

	WebUI.scrollToElement(eduMaterial, 5)
	WebUI.waitForElementClickable(eduMaterial, 10)
	WebUI.click(eduMaterial)

	if (WebUI.waitForElementVisible(popup, 3, FailureHandling.OPTIONAL)) {
		KeywordUtil.logInfo("Popup opened in attempt: " + i)
		break
	}

	if (i == maxAttempts) {
		KeywordUtil.markFailed("Popup did not open after ${maxAttempts} clicks")
	}
}

// =====================================================
// UPLOAD TO PATIENT PORTAL
// =====================================================

WebUI.mouseOver(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))

TestObject toastMsg =
		findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message')

WebUI.waitForElementVisible(toastMsg, 30, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(
		toastMsg,
		'Health information resource uploaded successfully on Patient Portal.'
)

//Click on close button on SOC
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))


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
