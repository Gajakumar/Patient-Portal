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
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_ACTIONS_imgFindPatient'))

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Find Patient_FirstName'), 'Mark')

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Find Patient_LastName'), 'Wood')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Active_btnSearchPatient'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Actions_Encounters  Add New Encounter'))

WebUI.selectOptionByLabel('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Encounter Type_EncounterTypeID', 'Automation Element Test Encounter', false)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Intake Form_encTabList_1'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_glyphicon glyphicon-circelp_06a9eb'))

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Loading_DESCRIPTION_GF_b04f_GF_I'), 
    'Alcohol abuse')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/em'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_B-1'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_DDD_L_LBI0T0'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_B-1'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_DDD_L_LBI0T0'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_--Select--_problemListGridView_b04f_E_114e19'))

WebUI.delay(2)

TestObject button = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_EHR_EducationMaterial')
TestObject popup  = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Health Information Resource_title')

int maxAttempts = 3

for (int i = 1; i <= 3; i++) {
	WebUI.click(button)

	if (WebUI.waitForElementVisible(popup, 2, FailureHandling.OPTIONAL)) {
		KeywordUtil.logInfo("Popup opened in attempt: " + i)
		break
	}

	if (i == maxAttempts) {
		KeywordUtil.markFailed("Popup did not open after ${maxAttempts} clicks")
	}
}


WebUI.mouseOver(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'), 
    'Health information resource uploaded successfully on Patient Portal.')

DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("hh:mm a")

def expectedTimeGMT1 = [
    ZonedDateTime.now(ZoneId.of("GMT")).minusMinutes(1).format(formatter1).toUpperCase(),
    ZonedDateTime.now(ZoneId.of("GMT")).format(formatter1).toUpperCase(),
    ZonedDateTime.now(ZoneId.of("GMT")).plusMinutes(1).format(formatter1).toUpperCase()
]

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/button_Health Information Resource_dialog-c_57ea1d'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_glyphicon glyphicon-circelp_06a9eb'))
WebUI.delay(2)

TestObject problemCell = findTestObject(
	'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Problem Row',
	[
		'rowId' : 1,
		'colId' : 1
	]
)

WebUI.click(problemCell)

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Loading_DESCRIPTION_GF_b04f_GF_I'), 
    'Gout')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_DESCRIPTION_GF_b04f_GF_DDD_L_LBI0T0'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_B-1'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_DDD_L_LBI0T0'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_B-1'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_DDD_L_LBI0T0'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_--Select--_problemListGridView_b04f_E_114e19'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_EducationMaterial'))

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'), 
    'Select at least one record to view education material.')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Actions_problemListGridView_b04f_EEG_D_045673'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_EducationMaterial'))



WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Health Information Resource_title'), 
    5)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))

//WebUI.waitForElementPresent('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message', 5)
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'), 
//    'Health information resource uploaded successfully on Patient Portal.')

DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm a")

def expectedTimeGMT2 = [
    ZonedDateTime.now(ZoneId.of("GMT")).minusMinutes(1).format(formatter2).toUpperCase(),
    ZonedDateTime.now(ZoneId.of("GMT")).format(formatter2).toUpperCase(),
    ZonedDateTime.now(ZoneId.of("GMT")).plusMinutes(1).format(formatter2).toUpperCase()
]
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/button_Health Information Resource_dialog-c_57ea1d'))


//Login to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Sign in With User Name and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : PtUserName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

// OTP Verification
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp)


// Auto type into four input boxes
String[] digits = otp.toCharArray()

WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

String actualUnreadMsgCount = WebUI.getText(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Message Count')
).replaceAll("\\s+", "").trim()

WebUI.verifyMatch(
	actualUnreadMsgCount,
	"2unreadmessages",
	false
)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Mark Wood_border-2 rounded-full p-4 smp_311faa'))

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/p_To Mark Wood_text-sm font-medium text-gra_224964'), 
    'Multiple Education Materials')

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/p_To Mark Wood_text-sm font-medium text-gra_224964_1'), 
    'Education Material: Alcohol Use Disorder (AUD)')

String actualTime1 = WebUI.getText(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/span_To Mark Wood_text-xs text-gray-500')
).toUpperCase().replaceAll("\\s+", " ").trim()

assert expectedTimeGMT1.any { it == actualTime1 }
println "Expected Date and Time: " + expectedTimeGMT1

String actualTime2 = WebUI.getText(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/span_To Mark Wood_text-xs text-gray-500_1')
).toUpperCase().replaceAll("\\s+", " ").trim()

assert expectedTimeGMT2.any { it == actualTime2 }
println "Expected Date and Time: " + expectedTimeGMT2

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Inbox_px-3 py-3 border-b border-gray-20_cf1afb'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_To Mark Wood_px-3 py-3 border-b border-_da13b9'))

