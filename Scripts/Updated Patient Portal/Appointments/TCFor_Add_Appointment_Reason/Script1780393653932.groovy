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
import org.openqa.selenium.WebElement
// ================= LOGIN =================
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)


WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/a_Office Admin'))

WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/a_Modules'))

WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/a_ui-id-21'))

WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/a_Appointment Reasons'))


// ================= SEARCH & DELETE (IF EXISTS) =================
String apptName = 'Katalon'

TestObject searchBox = findTestObject('Appointments/Appt Reason/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtn = findTestObject('Appointments/Appt Reason/Page_MaximEyes/span_Delete')

WebUI.setText(searchBox, apptName)
WebUI.delay(5)
// Wait for grid refresh
WebUI.waitForElementVisible(deleteBtn, 5, FailureHandling.OPTIONAL)

if (WebUI.verifyElementPresent(deleteBtn, 3, FailureHandling.OPTIONAL)) {
	
	WebUI.click(deleteBtn)
	WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/input_btnBP_Yes'))

	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"Appointment reason deleted successfully."
	)

} else {
	WebUI.comment("⚠️ Appt '${apptName}' not found, skipping delete.")
}


WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/span_mif-circle-plus font20 fg-purple line-heigh'))

WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/input_saveApptReason'))

WebUI.verifyElementText(findTestObject('Appointments/Appt Reason/Page_MaximEyes/div_You must fix all the validation errors'),
	'You must fix all the validation errors.')

WebUI.setText(findTestObject('Appointments/Appt Reason/Page_MaximEyes/input_txtReasonName'), 'Katalon Appt Reason')

WebUI.selectOptionByValue(findTestObject('Appointments/Appt Reason/Page_MaximEyes/select_DefaultStatus'), 'CONFIRMED', false)

WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/input_isAvailableForOnlineBooking'))



def selectByValue(TestObject to, String value) {
    WebElement element = WebUI.findWebElement(to)
    WebUI.executeJavaScript(
        "arguments[0].value='" + value + "'; arguments[0].dispatchEvent(new Event('change'));",
        Arrays.asList(element)
    )
}

// Email - Confirmed
selectByValue(
    findTestObject('Appointments/Appt Reason/Page_MaximEyes/select_NonTelehealthEmailConfirmedId'),
    '271'
)

// Email - Unconfirmed
selectByValue(
    findTestObject('Appointments/Appt Reason/Page_MaximEyes/select_NonTelehealthEmailUnconfirmedId'),
    '267'
)

// Email - No Show
selectByValue(
    findTestObject('Appointments/Appt Reason/Page_MaximEyes/select_NonTelehealthEmailNoShowId'),
    '296'
)

// SMS - Confirmed
selectByValue(
    findTestObject('Appointments/Appt Reason/Page_MaximEyes/select_NonTelehealthSMSConfirmedId'),
    '269'
)

// SMS - Unconfirmed
selectByValue(
    findTestObject('Appointments/Appt Reason/Page_MaximEyes/select_NonTelehealthSMSUnconfirmedId'),
    '266'
)

// SMS - No Show
selectByValue(
    findTestObject('Appointments/Appt Reason/Page_MaximEyes/select_NonTelehealthSMSNoShowId'),
    '295'
)

WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/input_saveApptReason'))


WebUI.verifyElementText(findTestObject('Appointments/Appt Reason/Page_MaximEyes/div_Appointment Reason saved successfully'), 
    'Appointment Reason saved successfully.')



