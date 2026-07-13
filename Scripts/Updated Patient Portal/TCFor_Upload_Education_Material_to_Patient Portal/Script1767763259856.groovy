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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.time.*
import java.time.format.*
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.*
import com.kms.katalon.core.testobject.ConditionType

import org.openqa.selenium.Keys


// =====================================================
// LOGIN TO MAXIMEYES
// =====================================================

def fileUploadInput   = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attach File Input')

String projectDir = RunConfiguration.getProjectDir()
File baseDir = new File(projectDir, 'Include/Files/TestFiles')


def uploadFileTestCloud(TestObject uploadObj, File baseDir, String fileName) {
	
		assert uploadObj != null : '❌ Upload input TestObject is NULL'
	
		File fileToUpload = new File(baseDir, fileName)
		assert fileToUpload.exists() && fileToUpload.isFile() :
				"❌ Upload file not found: ${fileToUpload.absolutePath}"
	
		println "☁ TestCloud uploading: ${fileToUpload.absolutePath}"
	
		CustomKeywords.'com.katalon.testcloud.FileExecutor.uploadFileToWeb'(
			uploadObj,
			fileToUpload.absolutePath
		)
	}
	
	
WebUI.callTestCase(
    findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
    [:],
    FailureHandling.STOP_ON_FAILURE
)

//Create Random Patient
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Send Sign Up Email to
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait until busy indicator invisible
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), 'Patient Portal Sign Up Completed. Email Sent.')

WebUI.delay(10)

//get Username & Password from email
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email, 'Access to your health data')

println('Username: ' + GlobalVariable.GV_Username)

println('Password: ' + GlobalVariable.GV_Password)


//Click on encounter dropdown
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

//Click on Create new encounter
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Actions_Encounters  Add New Encounter'))

//Select Encounter type as : Automation Element Test Encounter
WebUI.selectOptionByLabel(
    findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Encounter Type_EncounterTypeID'),
    'Automation Element Test Encounter',
    false
)

//Click on Add button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))


TestObject createNewEncounterBtn =
        findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter')

//Verify Create new encounter button is displayed then click on it
if (WebUI.verifyElementPresent(createNewEncounterBtn, 5, FailureHandling.OPTIONAL)) {
    WebUI.click(createNewEncounterBtn)
    println('Create New Encounter button clicked')
} else {
    println('Create New Encounter button not displayed – skipping click')
}

WebUI.delay(2)

//Click on Cheif Compalaints
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Intake Form_encTabList_1'))

// =====================================================
// ADD FIRST PROBLEM
// =====================================================

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Add Problem Plus button'))

WebUI.setText(
    findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Loading_DESCRIPTION_GF_b04f_GF_I'),
    'Alcohol abuse'
)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/em'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_B-1'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_DDD_L_LBI0T0'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_B-1'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_DDD_L_LBI0T0'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_--Select--_problemListGridView_b04f_E_114e19'))

WebUI.delay(2)

TestObject popup =
        findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Health Information Resource_title')

TestObject eduMaterial = findTestObject(
        'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_EHR_EducationMaterial',
        ['Desc': 'Alcohol abuse']
)

WebUI.waitForElementPresent(eduMaterial, 20)

int maxAttempts = 3

for (int i = 1; i <= maxAttempts; i++) {

    WebUI.scrollToElement(eduMaterial, 5)
    WebUI.waitForElementClickable(eduMaterial, 10)
    WebUI.click(eduMaterial)

    if (WebUI.waitForElementVisible(popup, 3, FailureHandling.OPTIONAL)) {
        KeywordUtil.logInfo("Popup opened in attempt: " + i)
        break
    }

    if (i == maxAttempts) {
        KeywordUtil.markFailed("Popup did not open after ${maxAttempts} clicks")
    }
}

// =====================================================
// UPLOAD TO PATIENT PORTAL
// =====================================================

WebUI.mouseOver(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))

TestObject toastMsg =
        findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message')

WebUI.waitForElementVisible(toastMsg, 30, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(
        toastMsg,
        'Health information resource uploaded successfully on Patient Portal.'
)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/button_Health Information Resource_dialog-c_57ea1d'))

WebUI.delay(2) 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Add Problem Plus button')) 
WebUI.delay(2) 
TestObject problemCell = findTestObject( 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Problem Row', [ 'rowId' : 1, 'colId' : 1 ] ) 
WebUI.click(problemCell) 
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Loading_DESCRIPTION_GF_b04f_GF_I'), 'Gout') 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_DESCRIPTION_GF_b04f_GF_DDD_L_LBI0T0')) 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_B-1')) 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_DDD_L_LBI0T0')) 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_B-1')) 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_DDD_L_LBI0T0')) 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_--Select--_problemListGridView_b04f_E_114e19')) 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_EducationMaterial')) 

//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Select at least one record to view education material.')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Actions_problemListGridView_b04f_EEG_D_045673')) 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_EducationMaterial')) 
WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Health Information Resource_title'), 5) 
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))

//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Health information resource uploaded successfully on Patient Portal.')

// =====================================================
// LOGIN TO PATIENT PORTAL
// =====================================================

WebUI.callTestCase(
    findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
    [:],
    FailureHandling.STOP_ON_FAILURE
)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter User name and password and click on sign in button
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Confirm DOB and Accept terms by drawing signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Read OTP from received over email
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'('imap.gmail.com', GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, 
    GlobalVariable.Sender_Email, 'Verification')

println('OTP fetched = ' + otp)

// Auto type otp into four input boxes
String[] digits = otp.toCharArray()

//Enter OTP
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1'), (digits[0]).toString())

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2'), (digits[1]).toString())

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3'), (digits[2]).toString())

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'), (digits[3]).toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click on Procced button after OTP entered
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

//Update Password   >>>>>>>>>>>>>>MBT 48416<<<<<<<<<<<<<<<<<
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//Login with Updated Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

//Fetch the otp from the email
String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp1)


// Auto type into four input boxes
String[] digits1 = otp1.toCharArray()

//Enter the OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits1[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits1[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits1[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits1[3].toString())

WebUI.delay(5)

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

//Click on Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)


WebUI.delay(5)


//Verify Username, Todays date and current time on dashboard
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), 
    [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

//Verify msg count on dashboard
String actualUnreadMsgCount = WebUI.getText( findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Message Count') ).replaceAll("\\s+", "").trim() 
WebUI.verifyMatch( actualUnreadMsgCount, "2", false)

// =====================================================
// VALIDATIONS
// =====================================================

//Click on Message
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Mark Wood_border-2 rounded-full p-4 smp_311faa'))

//Verify Inbox msg
WebUI.verifyElementText(
        findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/p_To Mark Wood_text-sm font-medium text-gra_224964'),
        'Multiple Education Materials'
)

//Verify msg contains
WebUI.verifyElementText(
        findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/p_To Mark Wood_text-sm font-medium text-gra_224964_1'),
        'Education Material: Alcohol Use Disorder (AUD)'
)

// =====================================================
// TIME VALIDATION
// =====================================================

//Verify Time of the msg
DateTimeFormatter formatter = new DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendPattern("hh:mm a")
        .toFormatter(Locale.ENGLISH)

// -------- First Time --------

String actualTime1 = WebUI.getText(
        findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/span_To Mark Wood_text-xs text-gray-500')
).replaceAll("\\s+", " ").trim()

println("Actual Time 1 Raw: '$actualTime1'")

LocalTime actual1 = LocalTime.parse(actualTime1, formatter)
LocalTime nowGMT1 = ZonedDateTime.now(ZoneId.of("GMT")).toLocalTime()

long diff1 = Math.abs(Duration.between(nowGMT1, actual1).toMinutes())

println("Time difference 1 (minutes): $diff1")

assert diff1 <= 10 :
        "Time difference >10 minutes. Actual: $actualTime1"

// -------- Second Time --------

String actualTime2 = WebUI.getText(
        findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/span_To Mark Wood_text-xs text-gray-500_1')
).replaceAll("\\s+", " ").trim()

println("Actual Time 2 Raw: '$actualTime2'")

LocalTime actual2 = LocalTime.parse(actualTime2, formatter)
LocalTime nowGMT2 = ZonedDateTime.now(ZoneId.of("GMT")).toLocalTime()

long diff2 = Math.abs(Duration.between(nowGMT2, actual2).toMinutes())

println("Time difference 2 (minutes): $diff2")

assert diff2 <= 10 :
        "Time difference >10 minutes. Actual: $actualTime2"

//Click on 1st Msg
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Inbox_px-3 py-3 border-b border-gray-20_cf1afb'))

//Download the attchment
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Download Attchment'))

//Click on 2nd msg
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_To Mark Wood_px-3 py-3 border-b border-_da13b9'))

//Download the attchment
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Download Attchment'))

//=====================================================

//Click on 1st sent message from left pane
WebUI.click(findTestObject('Object Repository/Scenario Update1703/Message Pt Portal/Page_Patient Portal/Sent Msg first one'))

//Verify Replay button is present
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/button_Reply'), 5)

//Click on Replay button at bottum
WebUI.click(findTestObject('Provider Portal/Forword Message/Page_MaximEyes/Reply Message/Page_Patient Portal/button_Reply'))

//verify subject
WebUI.verifyMatch(
	WebUI.getAttribute(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/input_Enter Text'), "value"),
	"Re: Multiple Education Materials",
	false
)


////Verify Message For Doctor
//WebUI.verifyMatch(
//    WebUI.getAttribute(
//        findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/textarea_On Apr 8, 2026 at 1_49 PM, David Smith'),
//        "value"
//    ),
//    ".*What is alcohol use disorder \\(AUD\\)\\?.*Craving - a strong need to drink.*What is gout\\?.*",
//    true
//)

//Verify no attachment is displayed
WebUI.verifyElementText(findTestObject('Object Repository/Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_No Attachments'), 'No Attachments')

//Add doctors message
WebUI.setText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/textarea_On Apr 8, 2026 at 1_49 PM, David Smith'),
	'I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve., Message For Doctor')

//Upload attachment
 uploadFileTestCloud(fileUploadInput, baseDir, 'InsCard.jpg')

//Verify Attchment
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/span_InsCard.png'), 'InsCard.jpg')

//Click on send button
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/button_Send'))

//Verify messgae sent
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/h2_Select a Message_text-4xl font-semibold _a3c113'), 'Message Sent')

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Three Dots INBOX'))

//Click on Sent Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))


//Verify sent message displayed in sent box
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/p_Re_ Demo2'), 'Multiple Education Materials')


//Verify doctor message
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_On Apr 8, 2026 at 1_49 PM, David Smith wrote'),
	'I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve., Message For Doctor')

//Verify attachment is present
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/span_InsCard.png'), 'InsCard.jpg')

//======================================================
//Forward Messages part is removed from the application
////Click on any sent msg from left
//WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_Demo2'))

////Click on Forward button
//WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/span_Forward'))
//
////Verify to field
//WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/label_labelText'),
//	'To')
//
//
////verify subject
//WebUI.verifyMatch(
//	WebUI.getAttribute(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/input_Enter Text'), "value"),
//	"Fwd: Re: Multiple Education Materials",
//	false
//)
//
//////Verify Doctors Message
////WebUI.verifyMatch(
////	WebUI.getAttribute(
////		findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/textarea_Forwarded message -From_ David SmithDat'),
////		"value"
////	).replaceAll("\\s+", " "),
////
////	".*Forwarded message.*From: .*Subject: (Re: )?Multiple Education Materials.*I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve.*",
////
////	true
////)
//
////Verify attachment is present
//WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_file1.jpg200.0 KB'),
//	5)
//
////Enter enmail id
//WebUI.setText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/input_Enter Text_2'),
//	'gajakumara@first-insight.com')
//
////Click on send button
//WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/button_Send'))
//
////Verify message sent
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/h2_Select a Message_text-4xl font-semibold _a3c113'),
//	'Message Sent')
//
////Click on Home icon
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))
//
////Click on Message Icon on Dashboard
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))
//
////Click on switch view three dots
//WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Three Dots INBOX'))
//
////Click on Sent Message
//WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))
//
//
////Verify sent message in left pane
//WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/span_To_ gajakumarafirst-insight.com'),
//	'To: gajakumara@first-insight.com')
//
////Verify subject
//WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_Fwd_ Demo2'),
//	'Fwd: Re: Multiple Education Materials')
//
////Click on that message
//WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_Fwd_ Demo2'))
//
////Verify correct mail id is displayed
//WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_To_ gajakumarafirst-insight.com'),
//	'To: gajakumara@first-insight.com')
//
//////Verify doctors message
////WebUI.verifyMatch(
////	WebUI.getAttribute(
////		findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_Forwarded message -From_ David SmithDate_ Ap'),
////		"innerText"
////	).replaceAll("\\s+", " "),
////
////	".*Forwarded message.*From: .*Subject: (Re: )?Multiple Education Materials.*I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve.*",
////
////
////	true
////)
//
////Verify attachment
//WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/button_file1.jpg'),
//	5)
//=======================================================

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Search text
String searchText = "ed"

//Test Objects
TestObject searchBox     = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Search Message')
TestObject closeSearch = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/CloseSearch')

// Dynamic Objects
TestObject resultsObj = new TestObject("resultsObj")
resultsObj.addProperty("xpath", ConditionType.EQUALS,
	"//section[@aria-label='Messages list']//div[contains(@class,'border-b')]")

TestObject highlightObj = new TestObject("highlightObj")
highlightObj.addProperty("xpath", ConditionType.EQUALS,
	"//mark | //span[contains(@class,'highlight')]")

TestObject checkboxObj = new TestObject("checkboxObj")
checkboxObj.addProperty("xpath", ConditionType.EQUALS,
	"//section[@aria-label='Messages list']//input[@type='checkbox']")

// ======================================================
// 🔹 STEP 1: MINIMUM CHARACTER SEARCH (1 CHAR)
// ======================================================
WebUI.waitForElementVisible(searchBox, 10)

WebUI.clearText(searchBox)
WebUI.setText(searchBox, "a")
WebUI.sendKeys(searchBox, Keys.chord(Keys.ENTER))
WebUI.delay(2)

KeywordUtil.logInfo("✅ Minimum 1 character search executed")

// ======================================================
// 🔹 STEP 2: MAIN SEARCH
// ======================================================
WebUI.clearText(searchBox)
WebUI.setText(searchBox, searchText)
WebUI.sendKeys(searchBox, Keys.chord(Keys.ENTER))
WebUI.delay(2)

KeywordUtil.logInfo("✅ Search performed with text: " + searchText)

// ======================================================
// 🔹 STEP 3: FETCH RESULTS
// ======================================================
List<WebElement> results = WebUI.findWebElements(resultsObj, 10)

if (results.size() > 0) {

	KeywordUtil.logInfo("✅ Results found: " + results.size())

	// --------------------------------------------------
	// 🔸 Highlight Validation (Tailwind Pink FIXED)
	// --------------------------------------------------
	List<WebElement> highlights = WebUI.findWebElements(highlightObj, 10)

	boolean isPinkFound = false

	for (WebElement el : highlights) {

		String bgColor   = el.getCssValue("background-color")
		String classAttr = el.getAttribute("class")

		KeywordUtil.logInfo("BG Color: " + bgColor)
		KeywordUtil.logInfo("Class: " + classAttr)

		// ✅ PRIMARY (CLASS CHECK)
		if (classAttr != null && classAttr.contains("bg-fuchsia")) {
			isPinkFound = true
			break
		}

		// ✅ FALLBACK (COLOR CHECK)
		if (bgColor != null && bgColor.contains("255, 0, 255")) {
			isPinkFound = true
			break
		}
	}

	if (!isPinkFound) {
		KeywordUtil.markFailed("❌ Highlight color is NOT Tailwind Pink (bg-pink-300)")
	} else {
		KeywordUtil.logInfo("✅ Highlight color validated (bg-fuchsia)")
	}

	// --------------------------------------------------
	// 🔸 No Checkbox Validation
	// --------------------------------------------------
	List<WebElement> checkboxes = WebUI.findWebElements(checkboxObj, 5)

	if (checkboxes.size() > 0) {
		KeywordUtil.markFailed("❌ Checkbox should NOT be visible in search results")
	} else {
		KeywordUtil.logInfo("✅ No checkbox present")
	}
	
	
	// ======================================================
	// 🔹 STEP 4: CLEAR SEARCH → RETAIN ORIGINAL FOLDER
	// ======================================================
	WebUI.click(closeSearch)
	WebUI.delay(2)
	
	
	KeywordUtil.logInfo("✅ Search cleared")
	
	// --------------------------------------------------
	// 🔸 Verify NO highlight remains
	// --------------------------------------------------
	List<WebElement> highlightsAfterClear = WebUI.findWebElements(highlightObj, 5)
	
	boolean highlightStillPresent = false
	
	for (WebElement el : highlightsAfterClear) {
	
		String classAttr = el.getAttribute("class")
		String bgColor   = el.getCssValue("background-color")
	
		if (
			(classAttr != null && classAttr.contains("bg-fuchsia")) ||
			(bgColor != null && bgColor.contains("255, 0, 255"))
		) {
			highlightStillPresent = true
			break
		}
	}
	
	if (highlightStillPresent) {
		KeywordUtil.markFailed("❌ Highlight (pink) still visible after clearing search")
	} else {
		KeywordUtil.logInfo("✅ All highlights removed after clearing search")
	}
}
//=======================================================

////Select checkbox for first message
//WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_w-4 h-4 border-2 border-g_15d988'))
//
////Select checkbox for second  message
//WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_To test_w-4 h-4 border-2 border-gray-40_1b2e97'))
//
////Verify 2 selected at the top
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/span_DS_font-semibold text-lg cursor-pointer'),
//	'2 selected')
//
////Click on Delete button
//WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_DS_a'))
//
////Verify delete popup is displayed
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Select a Message_text-lg mb-6 text-center_8b71ba'),
//	'Are you sure you want to delete the selected messages? This action cannot be undone.')
//
////Click on yes button
//WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Select a Message_px-8 py-2 rounded b_18739d'))
//
////Verify toast message is displayed
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Messages - Patient Portal_1'),
//	'Message(s) deleted successfully!')
//
////Verify toast message is displyed
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Sent Messages_text-lg mt-2'),
//	'You have no messages in inbox')