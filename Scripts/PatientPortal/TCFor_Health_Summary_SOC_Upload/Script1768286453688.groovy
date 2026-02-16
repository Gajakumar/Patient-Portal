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
import stories.NavigateStory
import java.awt.Robot
import java.awt.event.KeyEvent
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.Date
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.configuration.RunConfiguration
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import groovy.json.JsonSlurper
import org.openqa.selenium.JavascriptExecutor

//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
//
////Get Patient ID
//TestObject patientIdObj = findTestObject(
//	'Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen'
//)
//
//WebUI.waitForElementVisible(patientIdObj, 15)
//
//GlobalVariable.GV_PatientID =
//	WebUI.getAttribute(patientIdObj, 'value') ?: ''
//
//println "✅ Patient ID stored: " + GlobalVariable.GV_PatientID
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))
//
//WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'),'Patient Portal Sign Up Completed. Email Sent.')
//
//WebUI.delay(5)
//
//CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	GlobalVariable.Sender_Email,
//	"Access to your health data"
//)
//
//println "Username: " + GlobalVariable.GV_Username
//println "Password: " + GlobalVariable.GV_Password
//
//WebUI.delay(5)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Add New Encounter_Create New'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Enter Data In Enc Elements'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Summary Of Care(C-CDA)'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/h2_Summary of Care (C-CDA)'), 
//    'Summary of Care (C-CDA)')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/h4_Zak Duckett'), GlobalVariable.PatientFirstName +' '+ GlobalVariable.PatientLastName)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/SOC_Verification'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_UploadToPatientPortal'))
//
//WebUI.waitForElementVisible(
//	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
//	15
//)
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Upload to Patient Portal completed succ_91bdc3_1'), 
//    'Upload to Patient Portal completed successfully.')
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))
//
//
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)
/////////////////////////////////

WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[('Username'): "RonOli0316", ('Password'): GlobalVariable.RestUpdatedPass],
	FailureHandling.STOP_ON_FAILURE
)

///////////////////////////////////////////
WebUI.delay(5)


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

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)
//
//String actualUnreadSummaryCount = WebUI.getText(
//	findTestObject('Object Repository/Page_Patient Portal/span_1unread messages')
//).replaceAll("\\s+", "").trim()
//
//WebUI.verifyMatch(
//	actualUnreadSummaryCount,
//	"1unreadmessages",
//	false
//)
//
//String todayGMT = CustomKeywords.'common.DateUtil.getTodayDateGMT'()

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))

//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Visit Date 01162026'), 'Visit Date: '+ todayGMT)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/SOC Verification On Patient Portal'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_a'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_a_1'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_a_2'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_text-primary'))
//
//WebUI.verifyElementVisible(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/svg_Visit Date 01162026_a_3'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/First Health Summary'))
//
//WebUI.delay(3)

//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Downlaod PDF'))
WebUI.delay(3)
//----------------Download XML--------------------------

// Clean Downloads
//new File(System.getProperty("user.home") + "/Downloads")
//    .listFiles()
//    ?.findAll { it.name.toLowerCase().endsWith(".xml") }
//    ?.each { it.delete() }
//
//// Trigger download
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Download CCDA File'))
//
//// Wait for XML
//File xmlFile = CustomKeywords.'common.BrowserDownloadHelper.waitForLatestXML'(60)
//
//println "Downloaded XML: ${xmlFile.absolutePath}"

//// Use TestCloud safe temp directory
//String downloadDir = System.getProperty("java.io.tmpdir")
//println "Cloud Download Directory: " + downloadDir
//
//// Step 1: Clean old XML files
//new File(downloadDir)
//		.listFiles()
//		?.findAll { it.name.toLowerCase().endsWith(".xml") }
//		?.each { it.delete() }
//
//// Step 2: Trigger download
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Download CCDA File'))
//
//// Step 3: Wait for XML file
//int timeoutSeconds = 60
//long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000)
//File downloadedFile = null
//
//while (System.currentTimeMillis() < endTime) {
//
//	downloadedFile = new File(downloadDir)
//			.listFiles()
//			?.findAll {
//				it.name.toLowerCase().endsWith(".xml") &&
//				!it.name.endsWith(".crdownload")
//			}
//			?.max { it.lastModified() }
//
//	if (downloadedFile != null) {
//		break
//	}
//
//	Thread.sleep(1000)
//}
//
//assert downloadedFile != null : "XML file not downloaded within timeout"
//
//println "Downloaded XML: ${downloadedFile.absolutePath}"

// -------------------------------------------------------------
// STEP 1: Get ALL browser cookies
// -------------------------------------------------------------

WebDriver driver = DriverFactory.getWebDriver()

def cookies = driver.manage().getCookies()

String cookieHeader = cookies.collect { it.getName() + "=" + it.getValue() }.join("; ")

println("Cookie Header: " + cookieHeader)


// -------------------------------------------------------------
// STEP 2: Call CCDA Download API using Cookie header
// -------------------------------------------------------------

String practiceName = "ptportal278"
String healthSummaryId = "1626"

RequestObject request = new RequestObject()
request.setRestUrl("https://ptportalapiqacert.maximeyes.com/api/PatientPortal/DownloadHealthSummaryCCDA?PracticeName="
        + practiceName +
        "&HealthSummaryId=" + healthSummaryId)

request.setRestRequestMethod("GET")

request.setHttpHeaderProperties([
        new TestObjectProperty("Cookie", ConditionType.EQUALS, cookieHeader)
])

def response = WS.sendRequest(request)

println("Download API Status Code: " + response.getStatusCode())

//if (response.getStatusCode() != 200) {
//    println("Response Body: " + response.getResponseBodyContent())
//    assert false : "CCDA Download Failed"
//}

println("Status Code: " + response.getStatusCode())
println("Response Body: ")
println(response.getResponseBodyContent())


// -------------------------------------------------------------
// STEP 3: Save XML
// -------------------------------------------------------------

String tempDir = System.getProperty("java.io.tmpdir")
File xmlFile = new File(tempDir + "/CCDA_File.xml")

xmlFile.write(response.getResponseBodyContent())

println("XML saved at: " + xmlFile.absolutePath)

//----------------XML Downloaded--------------------------

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Print CCDA'))

WebUI.delay(5)

Robot robot = new Robot()
robot.keyPress(KeyEvent.VK_ESCAPE)
robot.keyRelease(KeyEvent.VK_ESCAPE)

WebUI.delay(2)


WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Transmit to another prov'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Select Format PDF'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Save'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Send'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Email address is required'), 'Email address is required')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Subject is required'), 'Subject is required')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_4'),
	'abcd')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Send'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Please enter a valid email address'),
	'Please enter a valid email address')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_14'),
	'abcd@gmail.com')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Send'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Subject is required'), 'Subject is required')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e'))

WebUI.clearText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_14'))

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_32'),
	'gajakumara@first-insight.com')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_eec205_12'),
	'Patient CCDA')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/textarea_Test Patient'), 'Test Patient')

//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/span_100642_Download_Transmit.pdf'), '100642_Download_Transmit.pdf')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Send'))

String actualText = WebUI.getText(
    findTestObject('Object Repository/Page_Patient Portal/div_Message SentYour health summary has bee_02649d')
)

// Normalize spaces & line breaks
actualText = actualText.replaceAll('\\s+', ' ').trim()

String expectedText = 'Message Sent Your health summary has been successfully transmitted.'

WebUI.verifyMatch(actualText, expectedText, false)


//**************************Health Summary Settings***************************************


WebUI.click(findTestObject('Object Repository/Page_Patient Portal/p_Visit Date 01162026'))

//WebUI.waitForElementNotVisible(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Loader'), 10)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/svg_Visit Date 01212026_text-primary'))

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
	'Health Summary Settings')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Chief complaint and reason for visit section'),
	'Chief complaint and reason for visit section')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Medications'),
	'Medications')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Allergies and reactions'),
	'Allergies and reactions')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Problem list'),
	'Problem list')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Social history'),
	'Social history')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Insurance providers'),
	'Insurance providers')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Family history'),
	'Family history')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Immunizations'),
	'Immunizations')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Vital signs'),
	'Vital signs')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Mental status'),
	'Mental status')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Functional status'),
	'Functional status')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Encounter diagnoses'),
	'Encounter diagnoses')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Lab tests and valuesresults'),
	'Lab tests and values/results')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Procedures'),
	'Procedures')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Plan of treatment'),
	'Plan of treatment')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Reason for referral'),
	'Reason for referral')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Instructions'),
	'Instructions')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Goals'), 'Goals')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Health concerns'),
	'Health concerns')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Implantable devices'),
	'Implantable devices')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Assessments'),
	'Assessments')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Diagnostic imaging'),
	'Diagnostic imaging')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Laboratory location'),
	'Laboratory location')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/label_Notes section'),
	'Notes section')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'), 'Save')

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Medications_undefinedundefined'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))

WebUI.waitForElementNotVisible(
    findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
    15
)
WebUI.delay(3)

String actualTextMedExclude = WebUI.getText(
    findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_MedicationsInfo has been Excluded')
)

// Normalize spaces & line breaks
actualTextMedExclude = actualTextMedExclude.replaceAll('\\s+', ' ').trim()

String expectedTextMedExclude = 'Medications Info has been Excluded.'

WebUI.verifyMatch(actualTextMedExclude, expectedTextMedExclude, false)


WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/svg_Visit Date 01212026_text-primary'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Medications_undefinedundefined'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))


WebUI.waitForElementNotVisible(
    findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
    15
)
WebUI.delay(3)

String actualTextMedInclude = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_Medications')
)

// Normalize spaces & line breaks
actualTextMedInclude = actualTextMedInclude.replaceAll('\\s+', ' ').trim()

WebUI.verifyEqual(actualTextMedInclude.contains("Lipitor"), true)
WebUI.verifyEqual(actualTextMedInclude.contains("atorvastatin 10"), true)
WebUI.verifyEqual(actualTextMedInclude.contains("Take 10 mg by mouth once a day"), true)


WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/svg_Visit Date 01212026_text-primary'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))

WebUI.waitForElementNotVisible(
    findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
    15
)

WebUI.delay(3)


String actualTextVitalExclude = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_Vital SignsInfo has been Excluded')
)

// Normalize spaces & line breaks
actualTextVitalExclude = actualTextVitalExclude.replaceAll('\\s+', ' ').trim()

String expectedTextVital = 'Vital Signs Info has been Excluded.'

WebUI.verifyMatch(actualTextVitalExclude, expectedTextVital, false)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/svg_Visit Date 01212026_text-primary'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))

WebUI.waitForElementNotVisible(
    findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
    15
)

WebUI.delay(3)

String actualTextVitalInclude = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_Vital SignsInfo has been Excluded')
)

// Normalize spaces & line breaks
actualTextVitalInclude = actualTextVitalInclude.replaceAll('\\s+', ' ').trim()

WebUI.verifyEqual(actualTextVitalInclude.contains("Vital Signs"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Weight 123 lb"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Height/Length 165.1 cm"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Respiration rate 10 /min"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Body Temperature 80 F"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Pulse Oximetry 40 %"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Inhaled Oxygen Concentration 90 %"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Heart Beat 123 /min"), true)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/svg_Visit Date 01212026_text-primary'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))

WebUI.waitForElementNotVisible(
    findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
    15
)


CustomKeywords.'common.GMTTimeAuditVerifier.captureSendTimeGMT'()

WebUI.delay(5)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Secure Messages'))

//WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Secure Messages_searchboxofinbox'),
//	GlobalVariable.PatientFirstName)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To First Insight VisionAction Required P_5fe8ca'))

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Action Required Patient Request to Rest_3cb7ef'),
	'Action Required: Patient Request to Restrict Access to Health Data')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Patient78ae0 Testaeb66'),
	GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName)


String uiDateTime = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_01212026 1154 AM')
)

CustomKeywords.'common.GMTTimeAuditVerifier.verifyUITimeWithinMinutesOfSendGMT'(
	uiDateTime,
	5   // ±5 minutes
)

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To First Insight Vision'),
	'To: First Insight Vision')

String actualMailText = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Dear Provider,A patient has submitted a_f926cb')
)

// Normalize whitespace (removes line breaks, tabs, extra spaces)
actualMailText = actualMailText.replaceAll('\\s+', ' ').trim()

// Static content checks
assert actualMailText.contains('Dear Provider')
assert actualMailText.contains('A patient has submitted a request to restrict access to a specific element of their Electronic Health Information (EHI)')
assert actualMailText.contains('Restriction Request Details')
assert actualMailText.contains('Additional Notes')
assert actualMailText.contains('This action will be recorded in the audit log for compliance')
assert actualMailText.contains('The patient will be notified of your decision')
assert actualMailText.contains('Patient Data Restrictions')
assert actualMailText.contains('Accept Request')
assert actualMailText.contains('Deny Request')

// Dynamic field checks (pass values from test data or variables)
assert actualMailText.contains('Patient Name: '+ GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName)
assert actualMailText.contains('Patient ID: '+GlobalVariable.GV_PatientID)
assert actualMailText.contains('Requested USCDI Element(s): Vital signs')

// Date & Time (EST) – format check only (recommended)
assert actualMailText =~ /Date & Time of Request: [A-Za-z]+ \d{2}, \d{4}, \d{2}:\d{2} (AM|PM) \(EST\)/


WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Accept Request'))

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
	'Action details are logged successfully.')

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Patient78ae0 Testaeb66'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounter Details'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Summary Of Care(C-CDA)'))

String actualTextVitalExclude1 = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Vital SignsInfo has been Excluded')
)

// Normalize spaces & line breaks
actualTextVitalExclude1 = actualTextVitalExclude1.replaceAll('\\s+', ' ').trim()

String expectedTextVital1 = 'Vital Signs Info has been Excluded.'

WebUI.verifyMatch(actualTextVitalExclude1, expectedTextVital1, false)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Cancel_dialog-close-button btn-close 143f7'))



WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)


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

WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits1[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits1[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits1[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits1[3].toString())

WebUI.delay(5)


// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))

WebUI.verifyMatch(actualTextVitalExclude, expectedTextVital, false)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/svg_Visit Date 01212026_text-primary'))

String checked = WebUI.getAttribute(
	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'),
	'checked'
)

WebUI.verifyEqual(checked, 'true')

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))

WebUI.waitForElementNotVisible(
    findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
    15
)


WebUI.delay(3)

//***************Deny button*******************

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Secure Messages'))


WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To First Insight VisionAction Required P_5fe8ca'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/button_Deny Request'))

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
	'Action details are logged successfully.')

WebUI.delay(5)


CustomKeywords.'common.RestrictionEmailFullVerifier.fetchAndVerifyDeniedEmailBySubject'(
	"imap.gmail.com",
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	"Update on Your Data Restriction Request", // exact subject
	GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName,
	"Vital signs"
)

//********************************Compose Msg**********************************

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Inbox_btnCompose'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To_mif-search font18 fg-skyblue'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Do you want to search Patients or Ext_4d59c0'))

WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_ADVANCED PATIENT FIND_LastName'),GlobalVariable.PatientLastName)

WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/Page_MaximEyes/input_ADVANCED PATIENT FIND_FirstName'),GlobalVariable.PatientFirstName)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Is Active_button primary small-button'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_100740'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input__btnSendToPatientPortal'))

WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Subject_ComposeSubject'), 'XML CCDA File')

WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/textarea_Message_input InboxTextarea font20 pad05'),
	'Message to Patient including CCDA File')

//----------------Attach Downloaded XML------------------------------


//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Compose_attachmentIconCompose'))
//WebUI.delay(2)
//CustomKeywords.'common.RobotUploadHelper.uploadFileUsingRobot'(xmlFile.absolutePath)

//WebUI.uploadFile(findTestObject('Upload_Input_Object'), downloadedFile.absolutePath)

//----------------------------------------------
// Attach downloaded XML (NO Robot)
//----------------------------------------------
//WebUI.click(findTestObject(
//	'Object Repository/Health Summary Section/Page_MaximEyes/span_Compose_attachmentIconCompose'
//))
//
//WebUI.delay(2)
//
//WebUI.uploadFile(
//	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_FileUpload'),
//	xmlFile.getAbsolutePath()
//)

//----------------------------------------------
// Done
//----------------------------------------------

TestObject fileInput = new TestObject()
fileInput.addProperty("id", com.kms.katalon.core.testobject.ConditionType.EQUALS, "fileInputCompose")
//
//// Make hidden input visible (required because style=display:none)
//WebUI.executeJavaScript(
//		"arguments[0].style.display='block';",
//		Arrays.asList(WebUI.findWebElement(fileInput))
//)
//
//// Upload file directly
//WebUI.uploadFile(fileInput, downloadedFile.absolutePath)
//
//println "File uploaded successfully: ${downloadedFile.absolutePath}"

WebUI.uploadFile(fileInput, xmlFile.absolutePath)

println "XML file successfully uploaded"


//----------------Attached Downloaded XML------------------------------


WebUI.verifyElementPresent(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_100739_Test.xml'),
	0)


WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Send'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Inbox_openmoreactionmenu'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Sent Messages'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To Patienteafce Test44fedXML CCDA File'))

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To Patienteafce Test44fed'),
	'To: '+GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName)

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/p_Message to Patient including CCDA File'),
	'Message to Patient including CCDA File')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_XML CCDA File'), 'XML CCDA File')


WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/span_Sent Messages_openmoreactionmenuSent'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/div_Inbox'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To First Insight VisionAction Required P_5fe8ca'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Patient78ae0 Testaeb66'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounter Details'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Summary Of Care(C-CDA)'))

String actualTextVitalIncluded = WebUI.getText(
	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Vital SignsInfo has been Excluded')
)

// Normalize spaces & line breaks
actualTextVitalIncluded = actualTextVitalIncluded.replaceAll('\\s+', ' ').trim()

WebUI.verifyEqual(actualTextVitalIncluded.contains("Vital Signs"), true)
WebUI.verifyEqual(actualTextVitalIncluded.contains("Weight 123 lb"), true)
WebUI.verifyEqual(actualTextVitalIncluded.contains("Height/Length 165.1 cm"), true)
WebUI.verifyEqual(actualTextVitalIncluded.contains("Respiration rate 10 /min"), true)
WebUI.verifyEqual(actualTextVitalIncluded.contains("Body Temperature 80 F"), true)
WebUI.verifyEqual(actualTextVitalIncluded.contains("Pulse Oximetry 40 %"), true)
WebUI.verifyEqual(actualTextVitalIncluded.contains("Inhaled Oxygen Concentration 90 %"), true)
WebUI.verifyEqual(actualTextVitalIncluded.contains("Heart Beat 123 /min"), true)

//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Cancel_dialog-close-button btn-close 143f7'))


WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)


String otp2 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp2)


// Auto type into four input boxes
String[] digits2 = otp2.toCharArray()

WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits2[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits2[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits2[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits2[3].toString())

WebUI.delay(5)


// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))

WebUI.verifyEqual(actualTextVitalInclude.contains("Vital Signs"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Weight 123 lb"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Height/Length 165.1 cm"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Respiration rate 10 /min"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Body Temperature 80 F"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Pulse Oximetry 40 %"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Inhaled Oxygen Concentration 90 %"), true)
WebUI.verifyEqual(actualTextVitalInclude.contains("Heart Beat 123 /min"), true)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/svg_Visit Date 01212026_text-primary'))


// Verify checkbox is NOT checked
WebUI.verifyElementNotChecked(
    findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'),
    5 // timeout in seconds
)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))

WebUI.waitForElementNotVisible(
    findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
    15
)


WebUI.delay(3)

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

String actualUnreadMsgCount = WebUI.getText(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Message Count')
).replaceAll("\\s+", "").trim()

WebUI.verifyMatch(
	actualUnreadMsgCount,
	"1unreadmessages",
	false
)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_1unread messages'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_To Patienteafce Test44fed1102 AMXML CCDA File'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/XML File'))

WebUI.delay(3)

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Visit Date 01162026'), 'Visit Date: '+ todayGMT)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/SOC Verification On Patient Portal'), [:], FailureHandling.STOP_ON_FAILURE)

