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
WebUI.click(findTestObject('Appointments/Sch Resource/Page_MaximEyes/a_Schedule Resources'))

WebUI.setText(findTestObject('Appointments/Sch Resource/Page_MaximEyes/input_Search in data grid'), 'Katalon')

// ================= SEARCH & DELETE (IF EXISTS) =================
String schResource = 'Katalon'

TestObject searchBox = findTestObject('Appointments/Sch Resource/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtn = findTestObject('Appointments/Sch Resource/Page_MaximEyes/span_Delete')

WebUI.setText(searchBox, schResource)

// Wait for grid refresh
WebUI.delay(2)

if (WebUI.verifyElementPresent(deleteBtn, 3, FailureHandling.OPTIONAL)) {

	WebUI.click(deleteBtn)
	WebUI.click(findTestObject('Appointments/Sch Resource/Page_MaximEyes/input_btnBP_Yes'))

	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"Schedule Resource updated successfully."
	)

} else {
	WebUI.comment("⚠️ Schedule Resource '${schResource}' not found, skipping delete.")
}

WebUI.delay(2)

//click on Add new resource
WebUI.click(findTestObject('Appointments/Sch Resource/Page_MaximEyes/span_mif-circle-plus font20 fg-purple line-heigh'))

//Click submit button
WebUI.click(findTestObject('Appointments/Sch Resource/Page_MaximEyes/input_btSubmit'))

//Verify error toast
WebUI.verifyElementText(findTestObject('Appointments/Sch Resource/Page_MaximEyes/div_You must fix all the validation errors'), 
    'You must fix all the validation errors.')

//Enter Resource name
WebUI.setText(findTestObject('Appointments/Sch Resource/Page_MaximEyes/input_txtName'), 'Katalon Resource')

//Enter short name
WebUI.setText(findTestObject('Appointments/Sch Resource/Page_MaximEyes/input_txtShortName'), 'Katalon')

//Select Provider
WebUI.selectOptionByLabel(
	findTestObject('Appointments/Sch Resource/Page_MaximEyes/select_txtPracticePerson'),
	'Patient Portal',
	false
)

//select Default Appointment Type for Quick Search
WebUI.click(findTestObject('Appointments/Sch Resource/Page_MaximEyes/button_ui-multiselect ui-widget ui-state-default'))

//Select as patient portal
WebUI.click(findTestObject('Appointments/Sch Resource/Page_MaximEyes/input_Patient Portal'))

//Check location check box
WebUI.click(findTestObject('Appointments/Sch Resource/Page_MaximEyes/ins_jstree-checkbox'))

//Verify is active checkbox is checked
WebUI.verifyElementChecked(
	findTestObject('Appointments/Sch Resource/Page_MaximEyes/input_Active _'),
	5
)

//Verify Available for Online Scheduling checkbox is checked
WebUI.verifyElementChecked(
	findTestObject('Appointments/Sch Resource/Page_MaximEyes/span_Available for Online Scheduling'),
	5
)

//Click submit button
WebUI.click(findTestObject('Appointments/Sch Resource/Page_MaximEyes/input_btSubmit'))

//Verify success toast message
WebUI.verifyElementText(findTestObject('Appointments/Sch Resource/Page_MaximEyes/div_Schedule Resource saved successfully'), 
    'Schedule Resource saved successfully.')

