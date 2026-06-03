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
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_Appointment Types'))

// ================= SEARCH & DELETE (IF EXISTS) =================
String apptTypeName = 'Katalon'

TestObject searchBox = findTestObject('Appointments/Appt Type/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtn = findTestObject('Appointments/Appt Type/Page_MaximEyes/span_Delete')

WebUI.setText(searchBox, apptTypeName)

// Wait for grid refresh
WebUI.delay(2)

if (WebUI.verifyElementPresent(deleteBtn, 3, FailureHandling.OPTIONAL)) {

	WebUI.click(deleteBtn)
	WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/input_btnBP_Yes'))

	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"Appointment type deleted successfully."
	)

} else {
	WebUI.comment("⚠️ Appt Type '${apptTypeName}' not found, skipping delete.")
}

WebUI.delay(2)

// ================= CREATE NEW APPT TYPE =================
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/span_mif-circle-plus font20 fg-purple line-heigh'))

WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/input_savePractBus'))

WebUI.verifyElementText(
	findTestObject('Appointments/Appt Type/Page_MaximEyes/div_you must fix all the validation errors'),
	'you must fix all the validation errors.'
)

// Fill form
WebUI.setText(findTestObject('Appointments/Appt Type/Page_MaximEyes/input_txtType'), 'Katalon Appt Type')

// verify Is Active checkbox is checked
WebUI.verifyElementChecked(findTestObject('Appointments/Appt Type/Page_MaximEyes/span_Is Active'), 10)

// verify allow scheduling radio button is selected
WebUI.verifyElementChecked(findTestObject('Appointments/Appt Type/Page_MaximEyes/span_Allow'), 10)

//Add discription
WebUI.setText(
	findTestObject('Appointments/Appt Type/Page_MaximEyes/input_txtTypeDescription'),
	'Katalon'
)

// ================= SELECT APPT REASON =================

// Click grid cell (safe click)
TestObject gridCell = findTestObject('Appointments/Appt Type/Page_MaximEyes/td_dxgv_editable_cell dxgv dx-ellipsis')

WebUI.scrollToElement(gridCell, 5)
WebUI.waitForElementVisible(gridCell, 10)

// Use JS click for DevExpress
WebUI.executeJavaScript(
	"arguments[0].click();",
	Arrays.asList(WebUI.findWebElement(gridCell, 10))
)

WebUI.delay(2)

// Search reason
WebUI.setText(
	findTestObject('Appointments/Appt Type/Page_MaximEyes/input_AppointmentTypeReasonGridView_d0da_EEG_DXE'),
	'katalon'
)

// Select row
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/td_AppointmentTypeReasonGridView_0205_EEG_DXEdit'))

// Click outside to close dropdown
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/input_txtTypeDescription'))

// Save
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/input_savePractBus'))

// Verify success
WebUI.verifyElementText(
	findTestObject('Appointments/Appt Type/Page_MaximEyes/div_Appointment type saved successfully'),
	'Appointment type saved successfully.'
)

// ================= VERIFY IN APPT REASONS =================
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_Appointment Reasons'))

WebUI.setText(searchBox, 'Katalon')

// Select reason
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/td_Katalon Appt Reason'))

// Get mapped type
String actualText = WebUI.getText(
	findTestObject('Appointments/Appt Type/Page_MaximEyes/td_Katalon Appt Type_1')
)

// Normalize text
actualText = actualText.replaceAll("\\s+", " ").trim()

// Verify exact match
WebUI.verifyMatch(actualText, "Katalon Appt Type", false)

// Final save
WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/input_saveApptReason'))

