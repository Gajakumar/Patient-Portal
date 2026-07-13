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
WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Office Admin'))
WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Business Administration'))
WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_ui-id-20'))

// ================= SEARCH & DELETE (IF EXISTS) =================
String locationName = 'Katalon Location'

TestObject searchBox = findTestObject('Appointments/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtn = findTestObject('Appointments/Page_MaximEyes/span_Delete')

WebUI.setText(searchBox, locationName)

// Wait for grid refresh
WebUI.waitForElementVisible(deleteBtn, 5, FailureHandling.OPTIONAL)

if (WebUI.verifyElementPresent(deleteBtn, 3, FailureHandling.OPTIONAL)) {
	
//	WebUI.click(deleteBtn)
//	WebUI.click(findTestObject('Appointments/Page_MaximEyes/input_btnBP_Yes'))

//	// Verify toast message
//	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
//		"Practice Location Is Present"
//	)
	WebUI.comment("⚠️ Location '${locationName}' found, Skipping Create New")

} else {
	WebUI.comment("⚠️ Location '${locationName}' not found, Create New")
	
	// ================= CREATE NEW LOCATION =================
	WebUI.click(findTestObject('Appointments/Page_MaximEyes/span_new-practice-location'))
	
	WebUI.selectOptionByValue(
		findTestObject('Appointments/Page_MaximEyes/select_Info_BusinessNameID'),
		'1',
		false
	)
	
	WebUI.setText(
		findTestObject('Appointments/Page_MaximEyes/input_Info_LocationName'),
		'Katalon Location'
	)
	
	WebUI.setText(
		findTestObject('Appointments/Page_MaximEyes/input_Info_LocationShortName'),
		'Kloc'
	)
	
	WebUI.selectOptionByValue(
		findTestObject('Appointments/Page_MaximEyes/select_TimeZoneID'),
		'India Standard Time',
		false
	)
	
	// Verify checkbox is checked
	WebUI.verifyElementChecked(
		findTestObject('Appointments/Page_MaximEyes/input_Set as Active Location'),
		5
	)
	
	// Enable intake option
	WebUI.click(findTestObject('Appointments/Page_MaximEyes/span_Show Location for online intake'))
	
	// Address details
	WebUI.setText(
		findTestObject('Appointments/Page_MaximEyes/input_PL_Main_Line1_beaee0de'),
		'Katalon Address'
	)
	
	WebUI.setText(
		findTestObject('Appointments/Page_MaximEyes/input_PR_Info_Address_Main_ZipCode_beaee0de'),
		'90008'
	)
	
	WebUI.click(findTestObject('Appointments/Page_MaximEyes/li_ui-id-474'))
	
	// Notes & fees
	WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_addnote'))
	
	WebUI.selectOptionByValue(
		findTestObject('Appointments/Page_MaximEyes/select_ddlScheduleFeesGroup'),
		'1',
		false
	)
	
	// Save
	WebUI.click(findTestObject('Appointments/Page_MaximEyes/input_btnSave'))
	
	// Verify success message
	WebUI.verifyElementText(
		findTestObject('Appointments/Page_MaximEyes/div_Practice location added'),
		'Practice location added.'
	)
}


