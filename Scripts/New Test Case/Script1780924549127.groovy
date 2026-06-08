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
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor


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
WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/a_General Preferences'))

TestObject onlineSchcheckbox = findTestObject('Appointments/General Pref/Page_MaximEyes/input_IsOnlineSchedulingEnabled')

// Wait for element
WebUI.waitForElementVisible(onlineSchcheckbox, 10)

// Check current state
boolean isChecked = WebUI.verifyElementChecked(onlineSchcheckbox, 2, FailureHandling.OPTIONAL)

// Click ONLY if unchecked
if (!isChecked) {
	WebUI.click(onlineSchcheckbox)

	WebUI.verifyElementText(
		findTestObject('Appointments/General Pref/Page_MaximEyes/h4_Are you sure you want to disable Online Sched'),
		'Do you want to enable Online Scheduling?'
	)

	WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/input_btnProceed'))
}

WebUI.selectOptionByValue(findTestObject('Appointments/General Pref/Page_MaximEyes/select_SCHEDULING_HOURS'), '12', false)

WebUI.selectOptionByValue(findTestObject('Appointments/General Pref/Page_MaximEyes/select_CANCELATION_HOURS'), '24', false)

WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/a_ui-id-29'))

// Verify toast message
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'("Preferences Saved.")

WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/a_ui-id-28'))

WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/a_General Preferences'))

WebUI.verifyElementPresent(
    findTestObject('Appointments/General Pref/Page_MaximEyes/span_icon-checked'),
    5
)

//WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/input_copyurl'))
//
//WebUI.verifyElementText(findTestObject('Appointments/General Pref/Page_MaximEyes/div_Preferences Saved'), 'Online Appointment Booking url copied to clipboard.')
//
//WebUI.delay(2)
//// Get URL from clipboard
//String copiedUrl = Toolkit.getDefaultToolkit()
//    .getSystemClipboard()
//    .getData(DataFlavor.stringFlavor)
//
//println("Copied URL: " + copiedUrl)
//
//// Open new tab with copied URL
//WebUI.executeJavaScript("window.open('" + copiedUrl + "','_blank');", [])
//
//// Switch to new tab
//WebUI.switchToWindowIndex(1)

// Step 1: Get expected URL from UI
String expectedUrl = WebUI.getAttribute(
	findTestObject('Appointments/General Pref/Page_MaximEyes/a_CopyUrl'),
	'title'
)

println("Expected URL: " + expectedUrl)

// Step 2: Click COPY button
WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/input_copyurl'))

// Step 3: Verify success message
WebUI.verifyElementText(
	findTestObject('Appointments/General Pref/Page_MaximEyes/div_Preferences Saved'),
	'Online Appointment Booking url copied to clipboard.'
)

// ✅ This is your actual verification (source of truth)
assert expectedUrl != null && expectedUrl.trim() != ''

// Step 4: Open URL in NEW TAB
WebUI.executeJavaScript("window.open(arguments[0], '_blank');", Arrays.asList(expectedUrl))

// Step 5: Switch to new tab
WebUI.switchToWindowIndex(1)

WebUI.click(findTestObject('Appointments/General Pref/Page_Eyeclinic/div_Hillsboro'))

WebUI.click(findTestObject('Appointments/General Pref/Page_Eyeclinic/div_Patient Portal'))

WebUI.click(findTestObject('Appointments/General Pref/Page_Eyeclinic/button_Proceed'))

WebUI.verifyElementPresent(findTestObject('Appointments/General Pref/Page_Eyeclinic/button_Proceed_1'), 5)

// Switch default tab
WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/span_icon-checked'))

WebUI.verifyElementText(findTestObject('Appointments/General Pref/Page_MaximEyes/h4_Are you sure you want to disable Online Sched'), 
    'Are you sure you want to disable Online Scheduling integration?')

WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/input_btnIOApiYes'))

// Verify toast message
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'("Module changes Saved")

// Switch to new tab
WebUI.switchToWindowIndex(1)

WebUI.navigateToUrl(expectedUrl)

WebUI.click(findTestObject('Appointments/General Pref/Page_Eyeclinic/div_Hillsboro'))

WebUI.click(findTestObject('Appointments/General Pref/Page_Eyeclinic/div_Patient Portal'))

WebUI.click(findTestObject('Appointments/General Pref/Page_Eyeclinic/button_Proceed'))

String actualText = WebUI.getText(
    findTestObject('Appointments/General Pref/Page_Eyeclinic/div_Module is disabled by practice, you cant bo')
)

// Normalize spaces
actualText = actualText.replaceAll("\\s+", " ").trim()

String expectedText = "Module is disabled by practice, you can't book an appointment."

WebUI.verifyMatch(actualText, expectedText, false)


// Switch default tab
WebUI.switchToWindowIndex(0)


WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/input_IsOnlineSchedulingEnabled'))

WebUI.verifyElementText(findTestObject('Appointments/General Pref/Page_MaximEyes/h4_Are you sure you want to disable Online Sched'), 
    'Do you want to enable Online Scheduling?')

WebUI.click(findTestObject('Appointments/General Pref/Page_MaximEyes/input_btnProceed'))


WebUI.verifyElementText(findTestObject('Appointments/General Pref/Page_MaximEyes/div_Preferences Saved'), 'Preferences Saved.')

