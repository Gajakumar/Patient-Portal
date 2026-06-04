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
WebUI.click(findTestObject('Appointments/Appt Template/Page_MaximEyes/a_ApptTemplate'))

WebUI.setText(findTestObject('Appointments/Appt Template/Page_MaximEyes/input_Search in data grid'), 'Katalon')

// ================= SEARCH & DELETE (IF EXISTS) =================
String apptTemp = 'Katalon'

TestObject searchBox = findTestObject('Appointments/Appt Template/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtn = findTestObject('Appointments/Appt Template/Page_MaximEyes/span_Delete')

WebUI.setText(searchBox, apptTemp)

// Wait for grid refresh
WebUI.delay(2)

if (WebUI.verifyElementPresent(deleteBtn, 3, FailureHandling.OPTIONAL)) {

	WebUI.click(deleteBtn)
	WebUI.click(findTestObject('Appointments/Appt Template/Page_MaximEyes/input_btnBP_Yes'))

	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"Appointment template deleted successfully."
	)

} else {
	WebUI.comment("⚠️ Appt Temp '${apptTemp}' not found, skipping delete.")
}

WebUI.delay(2)

//// ================= ADD NEW TEMPLATE =================

WebUI.click(findTestObject('Appointments/Appt Template/Page_MaximEyes/span_mif-circle-plus font20 fg-purple line-heigh'))

WebUI.delay(2)
//Click Save without entering data (to trigger validation)
WebUI.click(findTestObject('Appointments/Appt Template/Page_MaximEyes/input_temp_save'))

// Verify validation error message is displayed
WebUI.verifyElementText(findTestObject('Appointments/Appt Template/Page_MaximEyes/div_You must fix all the validation errors'),
	'You must fix all the validation errors.')

// ================= ENTER TEMPLATE DETAILS =================

// Enter Template Name
WebUI.setText(findTestObject('Appointments/Appt Template/Page_MaximEyes/input_txtTemplateName'), 'Katalon Template')

// Open Appointment Type dropdown
WebUI.setText(findTestObject('Appointments/Appt Template/Page_MaximEyes/input_drpAppointmentType_I'), 'Patient Portal')

// Select Appointment Type option
WebUI.click(findTestObject('Appointments/Appt Template/Page_MaximEyes/td_drpAppointmentType_DDD_L_LBI9T0'))

// ================= SET END TIME =================

// Create dynamic object
TestObject timeOption = new TestObject('timeOption')
timeOption.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//div[contains(@id,'drpEndTime_DDD_L')]//td[normalize-space()='05:00 pm']"
)

// Open dropdown
WebUI.click(findTestObject('Appointments/Appt Template/Page_MaximEyes/input_drpEndTime_I'))

// Scroll loop inside dropdown
for (int i = 0; i < 50; i++) {

	// Check if visible
	if (WebUI.verifyElementPresent(timeOption, 1, FailureHandling.OPTIONAL)) {
		WebUI.click(timeOption)
		break
	}

	// Scroll inside dropdown container
   WebUI.executeJavaScript("""
    var list = document.querySelector("#drpEndTime_DDD_L_D");
    if(list){
        list.scrollTop = list.scrollTop + 50;
    }
""", null)

	WebUI.delay(0.5)
}

// Click "Add Event to Template"
WebUI.click(findTestObject('Appointments/Appt Template/Page_MaximEyes/input_BtnAddEventToTemplate'))

// Verify event (Patient Portal) is added
WebUI.verifyElementPresent(findTestObject('Appointments/Appt Template/Page_MaximEyes/div_Patient Portal'), 5)


// ================= SAVE TEMPLATE =================

// Click Save button
WebUI.click(findTestObject('Appointments/Appt Template/Page_MaximEyes/input_temp_save'))

// Verify success message
WebUI.verifyElementText(findTestObject('Appointments/Appt Template/Page_MaximEyes/div_Template saved successfully'), 'Template saved successfully.')




