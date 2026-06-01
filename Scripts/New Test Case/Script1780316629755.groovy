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
WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Office Admin'))
WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Business Administration'))
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/a_ui-id-22'))

// ================= SEARCH & DELETE (IF EXISTS) =================
String providerName = 'Allen'

TestObject searchBox = findTestObject('Appointments/Provider/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtn = findTestObject('Appointments/Provider/Page_MaximEyes/span_Delete')

WebUI.setText(searchBox, providerName)

// Wait for grid refresh
WebUI.waitForElementVisible(deleteBtn, 5, FailureHandling.OPTIONAL)

if (WebUI.verifyElementPresent(deleteBtn, 3, FailureHandling.OPTIONAL)) {
	
	WebUI.click(deleteBtn)
	WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_btnBP_Yes'))

	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"Provider info deleted."
	)

} else {
	WebUI.comment("⚠️ Location '${providerName}' not found, skipping delete.")
}


//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/span_BtnAddProvider'))
//
//WebUI.setText(findTestObject('Appointments/Provider/Page_MaximEyes/input_Info_FirstName'), 'Finn')
//
//WebUI.setText(findTestObject('Appointments/Provider/Page_MaximEyes/input_Info_LastName'), 'Allen')
//
//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/button_Select options'))
//
//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_OD'))
//
//WebUI.setText(findTestObject('Appointments/Provider/Page_MaximEyes/input_Address_Email'), 'gajakumara@first-insight.com')
//
//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/ins_jstree-icon'))
//
//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/ins_jstree-checkbox'))
//
//WebUI.setText(findTestObject('Appointments/Provider/Page_MaximEyes/input_PRV_NPI_8e7c9ebf'), '1111111111')
//
//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_btnSaveProvider'))
//
//WebUI.rightClick(findTestObject('Appointments/Provider/Page_MaximEyes/div_Provider info added'))
//
//WebUI.verifyElementText(findTestObject('Appointments/Provider/Page_MaximEyes/div_Provider info added_1'), 'Provider info added.')

// ================= ADD PROVIDER =================
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/span_BtnAddProvider'))

// ================= BASIC INFO =================
WebUI.setText(
	findTestObject('Appointments/Provider/Page_MaximEyes/input_Info_FirstName'),
	'Finn'
)

WebUI.setText(
	findTestObject('Appointments/Provider/Page_MaximEyes/input_Info_LastName'),
	'Allen'
)

//// ================= PROVIDER TYPE =================
//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/button_Select options'))
//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_OD'))

// ================= CONTACT INFO =================
WebUI.setText(
	findTestObject('Appointments/Provider/Page_MaximEyes/input_Address_Email'),
	'gajakumara@first-insight.com'
)

// ================= LOCATION SELECTION =================
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/ins_jstree-icon'))
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/ins_jstree-checkbox'))

// ================= NPI =================
WebUI.setText(
	findTestObject('Appointments/Provider/Page_MaximEyes/input_PRV_NPI_8e7c9ebf'),
	'1111111111'
)

// ================= SAVE =================
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_btnSaveProvider'))

// ================= VERIFY SUCCESS =================
	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"Provider info added."
	)
