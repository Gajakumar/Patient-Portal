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
WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-23'))

// ================= SEARCH & DELETE (IF EXISTS) =================
String userName = 'Allen'

TestObject searchBoxUser = findTestObject('Appointments/User/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtnUser = findTestObject('Appointments/User/Page_MaximEyes/span_Delete')

WebUI.setText(searchBoxUser, userName)
WebUI.delay(3)
// Wait for grid refresh
WebUI.waitForElementVisible(deleteBtnUser, 5, FailureHandling.OPTIONAL)

if (WebUI.verifyElementPresent(deleteBtnUser, 3, FailureHandling.OPTIONAL)) {
	
	WebUI.click(deleteBtnUser)
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_btnBP_Yes'))

	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"User deleted successfully."
	)

} else {
	WebUI.comment("⚠️ User '${userName}' not found, skipping delete.")
}

// ================= NAVIGATION =================

WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Business Administration'))
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/a_ui-id-22'))

// ================= SEARCH & DELETE (IF EXISTS) =================
String providerName = 'Allen'

TestObject searchBox = findTestObject('Appointments/Provider/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtn = findTestObject('Appointments/Provider/Page_MaximEyes/span_Delete')

WebUI.setText(searchBox, providerName)
WebUI.delay(3)
// Wait for grid refresh
WebUI.waitForElementVisible(deleteBtn, 5, FailureHandling.OPTIONAL)

if (WebUI.verifyElementPresent(deleteBtn, 3, FailureHandling.OPTIONAL)) {
	
//	WebUI.click(deleteBtn)
//	WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_btnBP_Yes'))
//
//	// Verify toast message
//	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
//		"Provider info deleted."
//	)
	WebUI.comment("⚠️ Provider '${providerName}' Found, no need to create new")

} else {
	WebUI.comment("⚠️ Provider '${providerName}' not found, creating new")


// ================= ADD PROVIDER =================
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/span_BtnAddProvider'))

// ================= SAVE =================
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_btnSaveProvider'))

// ================= VERIFY toast =================
// Verify toast message
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
	"You must fix all the validation errors."
)


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
	
}
	//==================Create Provider User============

	WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Business Administration'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-23'))
	
	// Click Add User button
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/span_mif-circle-plus font20 fg-purple line-heigh'))
	
	// Enable Provider selection
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_IsProviderSelectedForUser'))
	
	// Select Provider and Location
// Select Provider by visible text
WebUI.selectOptionByLabel(
    findTestObject('Appointments/User/Page_MaximEyes/select_ProviderID'),
    'Finn Allen',
    false
)

// Select Practice Location by visible text
WebUI.selectOptionByLabel(
    findTestObject('Appointments/User/Page_MaximEyes/select_PracticeLocationID'),
    'Hillsboro',
    false
)
	
	// Permissions - Without Location
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-28'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_chkAllPermissionWithoutLocation'))
	
	// Permissions - With Location
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-26'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_chkAllPermissionWithLocation'))
	
	// Set Practice Admin
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_IsPracticeAdminForUser'))
	
	// Navigate Tabs
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-29'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-27'))
	
	// Set Password
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/button_Set Password'))
	
	WebUI.setText(
		findTestObject('Appointments/User/Page_MaximEyes/input_Enter new password'),
		'Test@1234'
	)
	
	WebUI.setText(
		findTestObject('Appointments/User/Page_MaximEyes/input_Re-enter new password'),
		'Test@1234'
	)
	
	// click on save button
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_awyfbyzh'))
	
	// Authority Password
	WebUI.setText(
		findTestObject('Appointments/User/Page_MaximEyes/input_AuthorityPassword'),
		'12345'
	)
	
	// Save and Confirm
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_SaveBtn'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_btnOk'))