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

// ================= LOGIN =================
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

// ================= NAVIGATION =================
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_Office Admin'))
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_Modules'))
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_ui-id-21'))
WebUI.click(findTestObject('Appointments/Apply Template/Page_MaximEyes/a_ApplyTemplate'))


WebUI.setText(findTestObject('Appointments/Apply Template/Page_MaximEyes/input_Search'), 'Katalon')

// ================= SEARCH & DELETE (IF EXISTS) =================
String schResource = 'Katalon'

TestObject obj = findTestObject('Appointments/Apply Template/Page_MaximEyes/h4_Are you sure you want to remove this template')
TestObject searchBox = findTestObject('Appointments/Apply Template/Page_MaximEyes/input_Search')
TestObject deleteBtn = findTestObject('Appointments/Apply Template/Page_MaximEyes/span_Remove Applied Template')

WebUI.setText(searchBox, schResource)

// Wait for grid refresh
WebUI.delay(2)

if (WebUI.verifyElementPresent(deleteBtn, 3, FailureHandling.OPTIONAL)) {

	WebUI.click(deleteBtn)
	
	// Get actual text
	String actualText = WebUI.getText(obj)
	
	// Normalize (remove line breaks + extra spaces)
	actualText = actualText.replaceAll("\\s+", " ").trim()
	
	String expectedText = "Are you sure you want to remove this template from the schedule resource and location it was applied to? Note: Removing the template from the scheduled resource will not delete any scheduled patient appointments, it will only remove the open appointment type as defined in the Appointment Template"
	
	// Normalize expected too
	expectedText = expectedText.replaceAll("\\s+", " ").trim()
	
	// Compare
	assert actualText.equals(expectedText)
	
	WebUI.click(findTestObject('Appointments/Apply Template/Page_MaximEyes/input_btnBP_Yes'))

	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"Applied Template deleted successfully."
	)

} else {
	WebUI.comment("⚠️ Applied Template '${schResource}' not found, skipping delete.")
}

WebUI.delay(2)

//Click on Add New Template
WebUI.click(findTestObject('Appointments/Apply Template/Page_MaximEyes/span_Add Template'))

//Click save  button
WebUI.click(findTestObject('Appointments/Apply Template/Page_MaximEyes/input_wizard_save'))

//Verify error toast
WebUI.verifyElementText(findTestObject('Appointments/Apply Template/Page_MaximEyes/div_You must fix all validation'), 'You must fix all validation.')

// Select Location
WebUI.selectOptionByLabel(
    findTestObject('Appointments/Apply Template/Page_MaximEyes/select_drdnOAWLocation'),
    'Patient Portal',
    false
)

// Select Resource
WebUI.selectOptionByLabel(
    findTestObject('Appointments/Apply Template/Page_MaximEyes/select_drdnOAWResource'),
    'Katalon Resource',
    false
)

// Select Template
WebUI.selectOptionByLabel(
    findTestObject('Appointments/Apply Template/Page_MaximEyes/select_drdnOAWTemplate'),
    'Patient Portal',
    false
)

//Click on 	Recurrence
WebUI.click(findTestObject('Appointments/Apply Template/Page_MaximEyes/span_OAW_RecurrenceControl_ChkRecurrence_S_D'))

//Click on No end date
WebUI.click(findTestObject('Appointments/Apply Template/Page_MaximEyes/span_OAW_RecurrenceControl_AptRecCtl_RangeCtl_De'))

//Click on Apply Template
WebUI.click(findTestObject('Appointments/Apply Template/Page_MaximEyes/input_btnContinue'))

TestObject applyTempobj = findTestObject('Appointments/Apply Template/Page_MaximEyes/div_Patient Portal')

// Wait for apply temp
WebUI.waitForElementVisible(applyTempobj, 10)

// Then verify its applied 
WebUI.verifyElementPresent(applyTempobj, 5)

//Click on Save button
WebUI.click(findTestObject('Appointments/Apply Template/Page_MaximEyes/input_wizard_save'))

//Verify success toast
WebUI.verifyElementText(findTestObject('Appointments/Apply Template/Page_MaximEyes/div_Applied Template saved successfully'), 
    'Applied Template saved successfully.')
