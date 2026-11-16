//import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
//import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
//import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
//import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
//import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
//import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
//import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
//import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
//import com.kms.katalon.core.model.FailureHandling as FailureHandling
//import com.kms.katalon.core.testcase.TestCase as TestCase
//import com.kms.katalon.core.testdata.TestData as TestData
//import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
//import com.kms.katalon.core.testobject.TestObject as TestObject
//import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
//import internal.GlobalVariable as GlobalVariable
//import org.openqa.selenium.Keys as Keys
//import stories.NavigateStory
//import java.awt.Robot
//import java.awt.event.KeyEvent
//import java.text.SimpleDateFormat
//import java.util.TimeZone
//import java.util.Date
//import java.time.ZonedDateTime
//import java.time.ZoneId
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//import com.kms.katalon.core.configuration.RunConfiguration
//import org.openqa.selenium.chrome.ChromeOptions
//import org.openqa.selenium.WebDriver
//import org.openqa.selenium.chrome.ChromeDriver
//import com.kms.katalon.core.webui.driver.DriverFactory
//import com.kms.katalon.core.configuration.RunConfiguration
//import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
//import com.kms.katalon.core.testobject.RequestObject
//import com.kms.katalon.core.testobject.TestObjectProperty
//import com.kms.katalon.core.testobject.ConditionType
//import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
//import com.kms.katalon.core.testobject.RequestObject
//import com.kms.katalon.core.testobject.TestObjectProperty
//import com.kms.katalon.core.testobject.ConditionType
//import groovy.json.JsonSlurper
//import org.openqa.selenium.JavascriptExecutor
//import custom.DownloadHelper
//
////Login to Maximeyes
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Create Random Patient
//WebUI.callTestCase(
//	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
//	[
//		('phoneNumber') : GlobalVariable.Mobile,
//		('emailId')     : GlobalVariable.MyEmail_Id,
//	],
//	FailureHandling.STOP_ON_FAILURE
//)
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
////Portal sign up using email
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Portal Sign up using email'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Get username and password from email
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
////Create new encounter
//WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Add New Encounter_Create New'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Enter data in encounter elements
//WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Enter Data In Enc Elements'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Click on Hamburger menu at left
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))
//
////Click on Summary Of Care(C-CDA)
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Summary Of Care(C-CDA)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
//
////Verify SOC page is opened
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/h2_Summary of Care (C-CDA)'), 
//    'Summary of Care (C-CDA)')
//
////Verify patient name on SOC
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/h4_Zak Duckett'), GlobalVariable.PatientFirstName +' '+ GlobalVariable.PatientLastName)
//
////Verify all element data is displayed on soc
//WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/SOC_Verification'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Click on Upload button
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_UploadToPatientPortal'))
//
//
////Verify toast msg
//CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Upload to Patient Portal completed successfully.')
//
////Click on close button on SOC
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))
//
////Navigate to patient portal site
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Click on Sign In button
//WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
//
////Enter User name and password
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)
//
////Confirm DOB and Accept terms
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)
//
//
//
////Get otp from email
//String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
//	'imap.gmail.com',
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	GlobalVariable.Sender_Email,
//	'Verification'
//)
//
//println("OTP fetched = " + otp)
//
//
//// Auto type into four input boxes
//String[] digits = otp.toCharArray()
//
////Enter OTP
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())
//
//WebUI.delay(5)
//
//// Click Proceed
//TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
//WebUI.waitForElementClickable(proceedBtn, 15)
//WebUI.click(proceedBtn)
//
//// Update password
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)
//
//// Login again with new password
//WebUI.callTestCase(
//	findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
//	[('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword],
//	FailureHandling.STOP_ON_FAILURE
//)
//
//WebUI.delay(5)
//
//// Fetch OTP from email
//String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
//	'imap.gmail.com',
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	GlobalVariable.Sender_Email,
//	'Verification'
//)
//
//println('OTP fetched = ' + otp1)
//
//// Enter OTP digits
//String[] digits1 = otp1.toCharArray()
//
//WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1'), digits1[0])
//WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2'), digits1[1])
//WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3'), digits1[2])
//WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'), digits1[3])
//
//WebUI.delay(5)
//
//// Click Proceed
//WebUI.waitForElementClickable(proceedBtn, 15)
//WebUI.click(proceedBtn)
//
//WebUI.delay(10)
//
//// Verify dashboard name + date
//WebUI.callTestCase(
//	findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'),
//	[('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName],
//	FailureHandling.STOP_ON_FAILURE
//)
//
////Verify unread Summary count
//String actualUnreadSummaryCount = WebUI.getText(
//	findTestObject('Object Repository/Page_Patient Portal/span_1unread messages')
//).replaceAll("\\s+", "").trim()
//
//WebUI.verifyMatch(
//	actualUnreadSummaryCount,
//	"1",
//	false
//)
//
//String todayGMT = CustomKeywords.'common.DateUtil.getTodayDateGMT'()
//
////Click on message
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))
//
////Verify visit date is displayed as todays date
//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Visit Date 01162026'), 'Visit Date: '+ todayGMT)
//
////Verify uploaded SOC data on portal
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/SOC Verification On Patient Portal'), [:], FailureHandling.STOP_ON_FAILURE)
//
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/btn_Transmit'),5, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/btn_Print'),5, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/btn_DownloadCCDA'),5, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/btn_DownloadPDF'),5, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/icon_Settings'),5, FailureHandling.STOP_ON_FAILURE)
//
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/First Health Summary'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Loader'), 30)
//
//
//
//DownloadHelper downloadHelper = new DownloadHelper()
//
//// Step 1: arm the blob interceptor BEFORE clicking download
//downloadHelper.captureBlobDownload()
//
//// Step 2: trigger the actual download click
//WebUI.click(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/btn_DownloadCCDA'))
//
//// Step 3: pull the captured blob back as a real file, works locally and on TestCloud
//String filePath = downloadHelper.retrieveCapturedBlobAsFile(null, 30)
//
//println "Downloaded XML saved at: " + filePath
//assert new File(filePath).exists() : "XML file was not saved"
//
////----------------XML Downloaded Successfully--------------------------
//
////WebDriver driver = DriverFactory.getWebDriver()
////
////// 1. Capture the current window handle BEFORE triggering print
////String originalHandle = driver.getWindowHandle()
////WebUI.delay(2)
////
////WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/btn_Print'))
////
////WebUI.delay(2)
////
////
////// 3. Verify + close the print dialog using the custom keyword
////boolean closed = CustomKeywords.'custom.PrintDialogKeywords.verifyAndClosePrintDialog'(originalHandle, 5)
////
////if (!closed) {
////    println("Warning: print dialog was not detected or could not be closed")
////}   >> close print popup not working, will check this later
//
//WebUI.delay(2)
//WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/btn_Transmit'))
//
//// Dynamic PDF button
//TestObject fileTypeButton = findTestObject(
//    'Page_Patient Portal/Select Format',
//    [('fileType') : 'PDF']
//)
//
//// Modal overlay
//TestObject modalOverlay = new TestObject('modalOverlay')
//modalOverlay.addProperty(
//    'xpath',
//    ConditionType.EQUALS,
//    "//div[contains(@class,'fixed') and contains(@class,'inset-0') and contains(@class,'bg-black')]"
//)
//
//// Wait for PDF button
//WebUI.waitForElementVisible(fileTypeButton, 15)
//
//// Wait for overlay to disappear, if present
//if (WebUI.verifyElementPresent(modalOverlay, 3, FailureHandling.OPTIONAL)) {
//    WebUI.waitForElementNotVisible(modalOverlay, 15)
//}
//
//// Select PDF
//WebUI.waitForElementClickable(fileTypeButton, 10)
//WebUI.click(fileTypeButton)
//
//// Save
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Save'))
//
//// Send
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Send'))
//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Email address is required'), 'Email address is required')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Subject is required'), 'Subject is required')
//
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_4'),
//	'abcd')
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Send'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Please enter a valid email address'),
//	'Please enter a valid email address')
//
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_14'),
//	'abcd@gmail.com')
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Send'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Subject is required'), 'Subject is required')
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e'))
//
//WebUI.clearText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_14'))
//
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_32'),
//	'gajakumara@first-insight.com')
//
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_eec205_12'),
//	'Patient CCDA')
//
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/textarea_Test Patient'), 'Test Patient')
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Send'))
//
//String actualText = WebUI.getText(
//	findTestObject('Object Repository/Page_Patient Portal/div_Message SentYour health summary has bee_02649d')
//)
//
//// Normalize spaces & line breaks
//actualText = actualText.replaceAll('\\s+', ' ').trim()
//
//String expectedText = 'Your health summary has been successfully transmitted.'
//
//WebUI.verifyMatch(actualText, expectedText, false)
//
//
////**************************Health Summary Settings***************************************
//
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/p_Visit Date 01162026'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Loader'), 30)
//
//WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/icon_Settings'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
//	'Health Summary Settings')
//
//
//Map<String, String> expected = [
//	'2.16.840.1.113883.10.20.22.2.13'   : 'Chief complaint and reason for visit section',
//	'2.16.840.1.113883.10.20.22.2.1.1'  : 'Medications',
//	'2.16.840.1.113883.10.20.22.2.6.1'  : 'Allergies and reactions',
//	'2.16.840.1.113883.10.20.22.2.5.1'  : 'Problem list',
//	'2.16.840.1.113883.10.20.22.2.17'   : 'Smoking status',
//	'2.16.840.1.113883.10.20.22.2.18'   : 'Insurance providers',
//	'2.16.840.1.113883.10.20.22.2.15'   : 'Family history',
//	'2.16.840.1.113883.10.20.22.2.2.1'  : 'Immunizations',
//	'2.16.840.1.113883.10.20.22.2.4.1'  : 'Vital signs',
//	'2.16.840.1.113883.10.20.22.2.14'   : 'Functional status',
//	'2.16.840.1.113883.10.20.22.4.80'   : 'Encounter diagnoses',
//	'2.16.840.1.113883.10.20.22.2.3.1'  : 'Lab tests and values/results',
//	'2.16.840.1.113883.10.20.22.2.7.1'  : 'Diagnostic imaging',
//	'2.16.840.1.113883.10.20.22.2.10'   : 'Patient summary and plan',
//	'2.16.840.1.113883.10.20.22.2.45'   : 'Instructions',
//	'2.16.840.1.113883.10.20.22.2.60'   : 'Goals',
//	'2.16.840.1.113883.10.20.22.2.58'   : 'Health status assessments',
//	'2.16.840.1.113883.10.20.22.2.23'   : 'Unique patient identifier(s) for a patient’s implantable device(s)',
//	'2.16.840.1.113883.10.20.34.2.3'    : 'Procedures',
//	'2.16.840.1.113883.10.20.22.2.3.1.1': 'Clinical tests'
//]
//
//// Helper to build a test object from XPath
//TestObject xp(String xpath) {
//	TestObject to = new TestObject(xpath)
//	to.addProperty('xpath', ConditionType.EQUALS, xpath)
//	return to
//}
//
//String container = "//div[@data-rbd-droppable-id='health-summary-elements']"
//
//// 1) Verify total count of rows
//int rows = WebUI.findWebElements(xp(container + "/div[@data-rbd-draggable-id]"), 10).size()
//WebUI.verifyEqual(rows, expected.size())
//
//// 2) Verify each label text, and that its checkbox exists and is unchecked
//expected.each { oid, text ->
//	String row = container + "//div[@data-rbd-draggable-id='${oid}']"
//
//	TestObject label    = xp(row + "//label[contains(@class,'text-sm')]")
//	TestObject checkbox = xp(row + "//input[@type='checkbox']")
//
//	WebUI.verifyElementPresent(label, 10)
//	WebUI.verifyElementText(label, text)          // exact match
//
//	WebUI.verifyElementPresent(checkbox, 10)
//	WebUI.verifyElementNotChecked(checkbox, 5)    // default state
//}
//
//WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'), 'Save')
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Medications_undefinedundefined'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))
//
//WebUI.waitForElementNotVisible(
//	findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
//	15
//)
//WebUI.delay(3)
//
//String actualTextMedExclude = WebUI.getText(
//	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_MedicationsInfo has been Excluded')
//)
//
//// Normalize spaces & line breaks
//actualTextMedExclude = actualTextMedExclude.replaceAll('\\s+', ' ').trim()
//
//String expectedTextMedExclude = 'Medications Info has been Excluded.'
//
//WebUI.verifyMatch(actualTextMedExclude, expectedTextMedExclude, false)
//
//
//WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/icon_Settings'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Medications_undefinedundefined'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))
//
//
//WebUI.waitForElementNotVisible(
//	findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
//	15
//)
//WebUI.delay(3)
//
//String actualTextMedInclude = WebUI.getText(
//	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_Medications')
//)
//
//// Normalize spaces & line breaks
//actualTextMedInclude = actualTextMedInclude.replaceAll('\\s+', ' ').trim()
//
//WebUI.verifyEqual(actualTextMedInclude.contains("Lipitor"), true)
//WebUI.verifyEqual(actualTextMedInclude.contains("atorvastatin 10"), true)
//WebUI.verifyEqual(actualTextMedInclude.contains("Take 10 mg by mouth once a day"), true)
//
//
//WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/icon_Settings'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))
//
//WebUI.waitForElementNotVisible(
//	findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
//	15
//)
//
//WebUI.delay(3)
//
//
//String actualTextVitalExclude = WebUI.getText(
//	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_Vital SignsInfo has been Excluded')
//)
//
//// Normalize spaces & line breaks
//actualTextVitalExclude = actualTextVitalExclude.replaceAll('\\s+', ' ').trim()
//
//String expectedTextVital = 'Vital Signs Info has been Excluded.'
//
//WebUI.verifyMatch(actualTextVitalExclude, expectedTextVital, false)
//
//WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/icon_Settings'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))
//
//WebUI.waitForElementNotVisible(
//	findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
//	15
//)
//
//WebUI.delay(3)
//
//String actualTextVitalInclude = WebUI.getText(
//	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_Vital SignsInfo has been Excluded')
//)
//
//// Normalize spaces & line breaks
//actualTextVitalInclude = actualTextVitalInclude.replaceAll('\\s+', ' ').trim()
//
//WebUI.verifyEqual(actualTextVitalInclude.contains("Vital Signs"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Weight 123 lb"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Height/Length 165.1 cm"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Respiration rate 10 /min"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Body Temperature 80 F"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Pulse Oximetry 40 %"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Inhaled Oxygen Concentration 90 %"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Heart Beat 123 /min"), true)
//
//WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/icon_Settings'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))
//
//WebUI.waitForElementNotVisible(
//	findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
//	15
//)
//
//
//CustomKeywords.'common.GMTTimeAuditVerifier.captureSendTimeGMT'()
//
//WebUI.delay(5)
//
////Login to the maximeyes
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
//
//TestObject secureMessagesLink = findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Secure Messages')
//TestObject homeButton = findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Home') // update path to your actual Home object
//TestObject secureMessagesPageIndicator = findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/SecureMessagesPageElement') // some unique element that only exists once page has loaded
//
//int maxRetries = 3
//int waitTimeoutSec = 15
//boolean pageOpened = false
//
//// initial click
//WebUI.click(secureMessagesLink)
//
//for (int attempt = 1; attempt <= maxRetries; attempt++) {
//    pageOpened = WebUI.verifyElementPresent(secureMessagesPageIndicator, waitTimeoutSec, FailureHandling.OPTIONAL)
//
//    if (pageOpened) {
//        WebUI.comment('Secure Messages page opened successfully on attempt ' + attempt)
//        break
//    } else {
//        WebUI.comment('Secure Messages page did not open within ' + waitTimeoutSec + ' sec, retrying via Home...')
//        WebUI.click(homeButton)
//        WebUI.delay(2) // small buffer for home page to load, adjust as needed
//        WebUI.click(secureMessagesLink)
//    }
//}
//
//if (!pageOpened) {
//    WebUI.comment('Secure Messages page failed to open after ' + maxRetries + ' attempts')
//    // Optionally fail the test explicitly:
//    // WebUI.failed('Secure Messages page did not load after retries')
//}
//
//
//WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Secure Messages_searchboxofinbox'),
//	GlobalVariable.PatientFirstName)
//
//WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 20)
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To First Insight VisionAction Required P_5fe8ca'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Action Required Patient Request to Rest_3cb7ef'),
//	'Action Required: Patient Request to Restrict Access to Health Data')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Patient78ae0 Testaeb66'),
//	GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName)
//
//
//String uiDateTime = WebUI.getText(
//	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_01212026 1154 AM')
//)
//
//CustomKeywords.'common.GMTTimeAuditVerifier.verifyUITimeWithinMinutesOfSendGMT'(
//	uiDateTime,
//	5   // ±5 minutes
//)
//
////WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To First Insight Vision'),
////	'To: First Insight Vision')
//
//String actualMailText = WebUI.getText(
//	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Dear Provider,A patient has submitted a_f926cb')
//)
//
//// Normalize whitespace (removes line breaks, tabs, extra spaces)
//actualMailText = actualMailText.replaceAll('\\s+', ' ').trim()
//
//// Static content checks
//assert actualMailText.contains('Dear Provider')
//assert actualMailText.contains('A patient has submitted a request to restrict access to a specific element of their Electronic Health Information (EHI)')
//assert actualMailText.contains('Restriction Request Details')
//assert actualMailText.contains('Additional Notes')
//assert actualMailText.contains('This action will be recorded in the audit log for compliance')
//assert actualMailText.contains('The patient will be notified of your decision')
//assert actualMailText.contains('Patient Data Restrictions')
//assert actualMailText.contains('Accept Request')
//assert actualMailText.contains('Deny Request')
//
//// Dynamic field checks (pass values from test data or variables)
//assert actualMailText.contains('Patient Name: '+ GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName)
//assert actualMailText.contains('Patient ID: '+GlobalVariable.GV_PatientID)
//assert actualMailText.contains('Requested USCDI Element(s): Vital signs')
//
//// Date & Time (EST) – format check only (recommended)
//assert actualMailText =~ /Date & Time of Request: [A-Za-z]+ \d{2}, \d{4}, \d{2}:\d{2} (AM|PM) \(EST\)/
//
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Accept Request'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
//	'Action details are logged successfully.')
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Patient78ae0 Testaeb66'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounter Details'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Summary Of Care(C-CDA)'))
//
//String actualTextVitalExclude1 = WebUI.getText(
//	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Vital SignsInfo has been Excluded')
//)
//
//// Normalize spaces & line breaks
//actualTextVitalExclude1 = actualTextVitalExclude1.replaceAll('\\s+', ' ').trim()
//
//String expectedTextVital1 = 'Vital Signs Info has been Excluded.'
//
//WebUI.verifyMatch(actualTextVitalExclude1, expectedTextVital1, false)
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Cancel_dialog-close-button btn-close 143f7'))
//
////login to patient portal
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(5)
//
//
//String otp2 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
//	'imap.gmail.com',
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	GlobalVariable.Sender_Email,
//	'Verification'
//)
//
//println("OTP fetched = " + otp2)
//
//
//// Auto type into four input boxes
//String[] digits2 = otp2.toCharArray()
//
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits2[0].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits2[1].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits2[2].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits2[3].toString())
//
//WebUI.delay(5)
//
//
//// Wait until the button is clickable (visible and enabled)
//WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
//
//// Click the button
//WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(10)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))
//
//WebUI.verifyMatch(actualTextVitalExclude, expectedTextVital, false)
//
//WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/icon_Settings'))
//
//String checked = WebUI.getAttribute(
//	findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'),
//	'checked'
//)
//
//WebUI.verifyEqual(checked, 'true')
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))
//
//WebUI.waitForElementNotVisible(
//    findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
//    15
//)
//
//
//WebUI.delay(3)
//
////***************Deny button*******************
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Secure Messages'))
//
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To First Insight VisionAction Required P_5fe8ca'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/button_Deny Request'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
//	'Action details are logged successfully.')
//
//WebUI.delay(5)
//
//
//CustomKeywords.'common.RestrictionEmailFullVerifier.fetchAndVerifyDeniedEmailBySubject'(
//	"imap.gmail.com",
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	"Update on Your Data Restriction Request", // exact subject
//	GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName,
//	"Vital signs"
//)
//
////********************************Compose Msg**********************************
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Inbox_btnCompose'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To_mif-search font18 fg-skyblue'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Do you want to search Patients or Ext_4d59c0'))
//
//WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_ADVANCED PATIENT FIND_LastName'),GlobalVariable.PatientLastName)
//
//WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/Page_MaximEyes/input_ADVANCED PATIENT FIND_FirstName'),GlobalVariable.PatientFirstName)
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Is Active_button primary small-button'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_100740'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input__btnSendToPatientPortal'))
//
//WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Subject_ComposeSubject'), 'XML CCDA File')
//
//WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/textarea_Message_input InboxTextarea font20 pad05'),
//	'Message to Patient including CCDA File')
//
////----------------Attach Downloaded XML------------------------------
//
//TestObject fileInput = new TestObject()
//fileInput.addProperty("id", com.kms.katalon.core.testobject.ConditionType.EQUALS, "fileInputCompose")
//
////Upload File
//def uploadFileTestCloud(TestObject uploadObj, File fileToUpload) {
//	
//		assert uploadObj != null : '❌ Upload input TestObject is NULL'
//		assert fileToUpload.exists() && fileToUpload.isFile() :
//				"❌ Upload file not found: ${fileToUpload.absolutePath}"
//	
//		println "☁ TestCloud uploading: ${fileToUpload.absolutePath}"
//	
//		CustomKeywords.'com.katalon.testcloud.FileExecutor.uploadFileToWeb'(
//			uploadObj,
//			fileToUpload.absolutePath
//		)
//	}
//
//	uploadFileTestCloud(fileInput, new File(filePath))
//
//println "XML file successfully uploaded"
//
//
////----------------Attached Downloaded XML Sucessfully------------------------------
//
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_100739_Test.xml'),
//	0)
//
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Send'))
//
//WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.delay(2)
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Inbox_openmoreactionmenu'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Sent Messages'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To Patienteafce Test44fedXML CCDA File'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To Patienteafce Test44fed'),
//	'To: '+GlobalVariable.PatientFirstName +" "+ GlobalVariable.PatientLastName)
//
//WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/p_Message to Patient including CCDA File'),
//	'Message to Patient including CCDA File')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_XML CCDA File'), 'XML CCDA File')
//
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/span_Sent Messages_openmoreactionmenuSent'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/Page_MaximEyes/div_Inbox'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To First Insight VisionAction Required P_5fe8ca'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Patient78ae0 Testaeb66'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Encounter Details'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Summary Of Care(C-CDA)'))
//
//String actualTextVitalIncluded = WebUI.getText(
//	findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Vital SignsInfo has been Excluded')
//)
//
//// Normalize spaces & line breaks
//actualTextVitalIncluded = actualTextVitalIncluded.replaceAll('\\s+', ' ').trim()
//
//WebUI.verifyEqual(actualTextVitalIncluded.contains("Vital Signs"), true)
//WebUI.verifyEqual(actualTextVitalIncluded.contains("Weight 123 lb"), true)
//WebUI.verifyEqual(actualTextVitalIncluded.contains("Height/Length 165.1 cm"), true)
//WebUI.verifyEqual(actualTextVitalIncluded.contains("Respiration rate 10 /min"), true)
//WebUI.verifyEqual(actualTextVitalIncluded.contains("Body Temperature 80 F"), true)
//WebUI.verifyEqual(actualTextVitalIncluded.contains("Pulse Oximetry 40 %"), true)
//WebUI.verifyEqual(actualTextVitalIncluded.contains("Inhaled Oxygen Concentration 90 %"), true)
//WebUI.verifyEqual(actualTextVitalIncluded.contains("Heart Beat 123 /min"), true)
//
////WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Cancel_dialog-close-button btn-close 143f7'))
//
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(5)
//
//
//String otp3 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
//	'imap.gmail.com',
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	GlobalVariable.Sender_Email,
//	'Verification'
//)
//
//println("OTP fetched = " + otp3)
//
//
//// Auto type into four input boxes
//String[] digits3 = otp3.toCharArray()
//
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits3[0].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits3[1].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits3[2].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits3[3].toString())
//
//WebUI.delay(5)
//
//
//// Wait until the button is clickable (visible and enabled)
//WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
//
//// Click the button
//WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(10)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))
//
//WebUI.verifyEqual(actualTextVitalInclude.contains("Vital Signs"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Weight 123 lb"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Height/Length 165.1 cm"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Respiration rate 10 /min"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Body Temperature 80 F"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Pulse Oximetry 40 %"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Inhaled Oxygen Concentration 90 %"), true)
//WebUI.verifyEqual(actualTextVitalInclude.contains("Heart Beat 123 /min"), true)
//
//WebUI.click(findTestObject('SOC Verification On PP/Page_Patient Portal/icon_Settings'))
//
//
//// Verify checkbox is NOT checked
//WebUI.verifyElementNotChecked(
//    findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/input_Vital signs_undefinedundefined'),
//    5 // timeout in seconds
//)
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/button_Save'))
//
//WebUI.waitForElementNotVisible(
//    findTestObject('Health Summary Section/Page_Patient Portal/h3_Health Summary Settings'),
//    15
//)
//
//
//WebUI.delay(3)
//
////Click on Home icon
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))
//
//String actualUnreadMsgCount = WebUI.getText(
//	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Message Count')
//).replaceAll("\\s+", "").trim()
//
//WebUI.verifyMatch(
//	actualUnreadMsgCount,
//	"1",
//	false
//)
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_1unread messages'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_To Patienteafce Test44fed1102 AMXML CCDA File'))
//
//WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/XML File'))
//
//WebUI.delay(3)
//
//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Visit Date 01162026'), 'Visit Date: '+ todayGMT)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/SOC Verification On Patient Portal'), [:], FailureHandling.STOP_ON_FAILURE)
//

// ============================================================================
// TC: Health Summary SOC Upload  (cleaned + refactored)
// Save as UTF-8 (label text contains a curly apostrophe)
// ============================================================================
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase           // find a test case by path
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject      // find an object-repository object
import com.kms.katalon.core.model.FailureHandling                                 // failure handling options
import com.kms.katalon.core.testobject.TestObject                                 // test object type
import com.kms.katalon.core.testobject.ConditionType                              // locator condition type
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI           // web keywords
import internal.GlobalVariable                                                    // global variables
import groovy.transform.Field                                                     // lets constants be seen inside methods
import custom.DownloadHelper                                                      // custom blob-download helper

// ============================================================================
// CONSTANTS
// ============================================================================

// ---- timeouts / waits (seconds) -------------------------------------------
@Field final int T_SHORT = 5                       // short element wait
@Field final int T_MED   = 15                      // medium element wait
@Field final int T_LONG  = 30                      // long element wait (loaders)
@Field final int SETTLE_SEC = 1                    // tiny buffer after save (was 3s)
@Field final int MAIL_WAIT_SEC = 5                 // wait for email/message delivery
@Field final int TIME_TOLERANCE_MIN = 5            // +/- minutes for UI time audit

// ---- test case paths ------------------------------------------------------
@Field final String TC_PP = 'Test Cases/common/Patient_Portal_Common/'   // patient portal common TCs
@Field final String TC_MX = 'Test Cases/common/Maximeyes/'               // Maximeyes common TCs

// ---- object repository prefixes -------------------------------------------
@Field final String OR      = 'Object Repository/'                                          // repo root
@Field final String PP      = OR + 'Page_Patient Portal/'                                   // portal objects
@Field final String HS_PP   = OR + 'Health Summary Section/Page_Patient Portal/'            // HS portal objects
@Field final String HS_MX   = OR + 'Health Summary Section/Page_MaximEyes/'                 // HS Maximeyes objects
@Field final String SOC_PP  = OR + 'SOC Verification On PP/Page_Patient Portal/'            // SOC portal objects
@Field final String MX_MIX  = OR + 'Maximeyes_Portal_Mix/Page_MaximEyes/'                   // mixed Maximeyes objects
@Field final String SIGNIN  = OR + 'PatientPortal/SignInPage_Patient Portal/'               // sign-in objects

// ---- frequently used objects ----------------------------------------------
@Field final String BUSY          = OR + 'Page_MaximEyes/Busy Indicator'    // Maximeyes busy spinner
@Field final String LOADER        = SIGNIN + 'Loader'                       // portal loader
@Field final String PROCEED_BTN   = SIGNIN + 'ProccedBtnAfterOTPVerification' // OTP proceed button
@Field final String SETTINGS_ICON = SOC_PP + 'icon_Settings'                // settings gear
@Field final String SETTINGS_TITLE= HS_PP + 'h3_Health Summary Settings'    // settings dialog title
@Field final String SETTINGS_SAVE = HS_PP + 'button_Save'                   // settings save button
@Field final String CHK_MEDS      = HS_PP + 'input_Medications_undefinedundefined'  // Medications checkbox
@Field final String CHK_VITALS    = HS_PP + 'input_Vital signs_undefinedundefined'  // Vital signs checkbox
@Field final String DIV_MEDS      = HS_PP + 'div_Medications'               // Medications content
@Field final String DIV_VITALS    = HS_PP + 'div_Vital SignsInfo has been Excluded'  // Vitals content/excluded msg
@Field final String VISIT_DATE    = PP + 'p_Visit Date 01162026'            // visit date label
@Field final String UNREAD_MSG    = PP + 'div_1unread messages'             // unread message row
@Field final String MX_TOAST      = MX_MIX + 'div_Cancel_jquery-notific8-message' // Maximeyes toast
@Field final String MX_MSG_ROW    = HS_MX + 'td_To First Insight VisionAction Required P_5fe8ca' // restriction msg row
@Field final String MX_PATIENT    = HS_MX + 'span_Patient78ae0 Testaeb66'   // patient name link in msg
@Field final String MX_HAMBURGER  = HS_MX + 'span_TOC Req_mif-Hamburger fg-gray font30' // hamburger menu
@Field final String MX_SOC_LINK   = HS_MX + 'span_Summary Of Care(C-CDA)'   // SOC menu link

// ---- expected texts -------------------------------------------------------
@Field final String SOC_TITLE            = 'Summary of Care (C-CDA)'                   // SOC page header
@Field final String UPLOAD_TOAST         = 'Upload to Patient Portal completed successfully.' // upload toast
@Field final String ACTION_LOGGED_TOAST  = 'Action details are logged successfully.'   // accept/deny toast
@Field final String TRANSMIT_OK_TEXT     = 'Your health summary has been successfully transmitted.' // transmit toast
@Field final String SETTINGS_TITLE_TEXT  = 'Health Summary Settings'                   // settings title
@Field final String SAVE_TEXT            = 'Save'                                      // save button text
@Field final String MEDS_EXCLUDED_TEXT   = 'Medications Info has been Excluded.'       // meds excluded msg
@Field final String VITALS_EXCLUDED_TEXT = 'Vital Signs Info has been Excluded.'       // vitals excluded msg
@Field final String EMAIL_REQUIRED_TEXT  = 'Email address is required'                 // validation msg
@Field final String SUBJECT_REQUIRED_TEXT= 'Subject is required'                       // validation msg
@Field final String EMAIL_INVALID_TEXT   = 'Please enter a valid email address'        // validation msg
@Field final String RESTRICT_TITLE       = 'Action Required: Patient Request to Restrict Access to Health Data' // msg title
@Field final String EXPECTED_UNREAD      = '1'                                         // expected unread count
@Field final String REQUESTED_ELEMENT    = 'Vital signs'                               // restricted element
@Field final String DENIED_EMAIL_SUBJECT = 'Update on Your Data Restriction Request'   // denied email subject

// ---- test input data ------------------------------------------------------
@Field final String FORMAT_PDF       = 'PDF'                          // transmit format
@Field final String BAD_EMAIL        = 'abcd'                         // invalid email input
@Field final String OK_FORMAT_EMAIL  = 'abcd@gmail.com'               // valid format, no subject
@Field final String TRANSMIT_TO      = 'gajakumara@first-insight.com' // transmit recipient
@Field final String TRANSMIT_SUBJECT = 'Patient CCDA'                 // transmit subject
@Field final String TRANSMIT_BODY    = 'Test Patient'                 // transmit body
@Field final String COMPOSE_SUBJECT  = 'XML CCDA File'                // compose subject
@Field final String COMPOSE_BODY     = 'Message to Patient including CCDA File' // compose body
@Field final String FILE_INPUT_ID    = 'fileInputCompose'             // upload input id
@Field final String IMAP_HOST        = 'imap.gmail.com'               // mail server
@Field final String OTP_SUBJECT      = 'Verification'                 // OTP email subject
@Field final String SOC_EMAIL_SUBJECT= 'Access to your health data'   // portal credentials email subject

// ---- expected data lists --------------------------------------------------
@Field final List<String> MEDS_EXPECTED = [
	'Lipitor', 'atorvastatin 10', 'Take 10 mg by mouth once a day'
]                                                                      // medication text checks

@Field final List<String> VITALS_EXPECTED = [
	'Vital Signs', 'Weight 123 lb', 'Height/Length 165.1 cm', 'Respiration rate 10 /min',
	'Body Temperature 80 F', 'Pulse Oximetry 40 %', 'Inhaled Oxygen Concentration 90 %',
	'Heart Beat 123 /min'
]                                                                      // vitals text checks

@Field final List<String> RESTRICT_MAIL_EXPECTED = [
	'Dear Provider',
	'A patient has submitted a request to restrict access to a specific element of their Electronic Health Information (EHI)',
	'Restriction Request Details', 'Additional Notes',
	'This action will be recorded in the audit log for compliance',
	'The patient will be notified of your decision',
	'Patient Data Restrictions', 'Accept Request', 'Deny Request'
]                                                                      // static mail content checks

@Field final String MAIL_DATE_REGEX =
	/Date & Time of Request: [A-Za-z]+ \d{2}, \d{4}, \d{2}:\d{2} (AM|PM) \(EST\)/   // date format check

@Field final Map<String, String> SETTINGS_LABELS = [                   // OID -> label, in DOM order
	'2.16.840.1.113883.10.20.22.2.13'   : 'Chief complaint and reason for visit section',
	'2.16.840.1.113883.10.20.22.2.1.1'  : 'Medications',
	'2.16.840.1.113883.10.20.22.2.6.1'  : 'Allergies and reactions',
	'2.16.840.1.113883.10.20.22.2.5.1'  : 'Problem list',
	'2.16.840.1.113883.10.20.22.2.17'   : 'Smoking status',
	'2.16.840.1.113883.10.20.22.2.18'   : 'Insurance providers',
	'2.16.840.1.113883.10.20.22.2.15'   : 'Family history',
	'2.16.840.1.113883.10.20.22.2.2.1'  : 'Immunizations',
	'2.16.840.1.113883.10.20.22.2.4.1'  : 'Vital signs',
	'2.16.840.1.113883.10.20.22.2.14'   : 'Functional status',
	'2.16.840.1.113883.10.20.22.4.80'   : 'Encounter diagnoses',
	'2.16.840.1.113883.10.20.22.2.3.1'  : 'Lab tests and values/results',
	'2.16.840.1.113883.10.20.22.2.7.1'  : 'Diagnostic imaging',
	'2.16.840.1.113883.10.20.22.2.10'   : 'Patient summary and plan',
	'2.16.840.1.113883.10.20.22.2.45'   : 'Instructions',
	'2.16.840.1.113883.10.20.22.2.60'   : 'Goals',
	'2.16.840.1.113883.10.20.22.2.58'   : 'Health status assessments',
	'2.16.840.1.113883.10.20.22.2.23'   : 'Unique patient identifier(s) for a patient’s implantable device(s)',
	'2.16.840.1.113883.10.20.34.2.3'    : 'Procedures',
	'2.16.840.1.113883.10.20.22.2.3.1.1': 'Clinical tests'
]

// ============================================================================
// HELPERS
// ============================================================================

// Calls a common test case and stops the run on failure
void callTC(String path, Map params = [:]) {
	WebUI.callTestCase(findTestCase(path), params, FailureHandling.STOP_ON_FAILURE)
}

// Clicks an object by repository path
void clickObj(String path) {
	WebUI.click(findTestObject(path))
}

// Types text into an object by repository path
void typeInto(String path, String text) {
	WebUI.setText(findTestObject(path), text)
}

// Verifies an object's exact text
void verifyText(String path, String expected) {
	WebUI.verifyElementText(findTestObject(path), expected)
}

// Waits for the Maximeyes busy spinner to go away
void waitBusy(int timeout = 30) {
	WebUI.waitForElementNotVisible(findTestObject(BUSY), timeout)
}

// Waits for the portal loader to go away
void waitLoader(int timeout = 30) {
	WebUI.waitForElementNotVisible(findTestObject(LOADER), timeout)
}

// Reads an element's text with whitespace collapsed
String textOf(String path) {
	return WebUI.getText(findTestObject(path)).replaceAll('\\s+', ' ').trim()
}

// Verifies the text contains every expected fragment
void verifyContainsAll(String actual, List<String> expected) {
	expected.each { WebUI.verifyEqual(actual.contains(it), true) }
}

// Builds a TestObject from an XPath
TestObject xp(String xpath) {
	TestObject to = new TestObject(xpath)
	to.addProperty('xpath', ConditionType.EQUALS, xpath)
	return to
}

// Fetches the OTP code from the mailbox
String fetchOtp() {
	return CustomKeywords.'otp.GmailOTPHandler.readOTP'(
		IMAP_HOST, GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
		GlobalVariable.Sender_Email, OTP_SUBJECT)
}

// Reads OTP, types it into the 4 boxes and clicks Proceed
void enterOtpAndProceed(boolean waitForMail) {
	if (waitForMail) {
		WebUI.delay(MAIL_WAIT_SEC)                                   // give the OTP email time to arrive
	}
	String otp = fetchOtp()                                          // read OTP from email
	println('OTP fetched = ' + otp)                                  // log OTP for debugging
	otp.take(4).toList().eachWithIndex { d, i ->                     // type each digit
		typeInto(SIGNIN + 'otp' + (i + 1), d.toString())
	}
	WebUI.waitForElementClickable(findTestObject(PROCEED_BTN), T_MED) // wait for Proceed to enable
	clickObj(PROCEED_BTN)                                            // click Proceed
	waitLoader()                                                     // wait for page to finish loading (was delay 10)
}

// Full patient-portal login (navigate + credentials + OTP)
void portalLogin(String password) {
	callTC(TC_PP + 'Navigate to Patient Portal Site')                // open portal
	callTC(TC_PP + 'User Login With Username and Password',
		   [Username: GlobalVariable.GV_Username, Password: password]) // enter credentials
	enterOtpAndProceed(true)                                         // complete OTP
}

// Verifies dashboard patient name + date
void verifyDashboard() {
	callTC(TC_PP + 'Verify Date Time and Patient name on Dashboard',
		   [Firstname: GlobalVariable.PatientFirstName, Lastname: GlobalVariable.PatientLastName])
}

// Waits briefly for the UI to settle after saving settings
void settle() {
	waitLoader(T_MED)                                                // wait for loader
	WebUI.delay(SETTLE_SEC)                                          // small buffer
}

// Toggles a settings checkbox and saves (optionally opens settings first)
void toggleAndSave(String checkboxPath, boolean openSettings = true) {
	if (openSettings) {
		clickObj(SETTINGS_ICON)                                      // open settings dialog
	}
	clickObj(checkboxPath)                                           // toggle checkbox
	clickObj(SETTINGS_SAVE)                                          // save
	WebUI.waitForElementNotVisible(findTestObject(SETTINGS_TITLE), T_MED) // dialog closes
	settle()                                                         // let content refresh
}

// Verifies settings labels (text + order) and that all checkboxes are unchecked, using 2 lookups
void verifySettingsLabels() {
	String c = "//div[@data-rbd-droppable-id='health-summary-elements']"                     // list container
	List<String> labels = WebUI.findWebElements(xp(c + "//label[contains(@class,'text-sm')]"), T_MED)*.getText()*.trim() // all labels
	WebUI.verifyEqual(labels, SETTINGS_LABELS.values().toList())                             // text + order
	def boxes = WebUI.findWebElements(xp(c + "//input[@type='checkbox']"), T_MED)            // all checkboxes
	WebUI.verifyEqual(boxes.size(), SETTINGS_LABELS.size())                                  // one per row
	WebUI.verifyEqual(boxes.count { it.isSelected() }, 0)                                    // all unchecked by default
}

// Maximeyes: opens Secure Messages with retry via Home
void openSecureMessages() {
	TestObject link  = findTestObject(HS_MX + 'a_Secure Messages')                           // secure messages link
	TestObject home  = findTestObject(HS_MX + 'a_Home')                                      // home button
	TestObject ready = findTestObject(HS_MX + 'SecureMessagesPageElement')                   // page-loaded marker
	WebUI.click(link)                                                                        // first attempt
	for (int attempt = 1; attempt <= 3; attempt++) {                                         // up to 3 tries
		if (WebUI.verifyElementPresent(ready, T_MED, FailureHandling.OPTIONAL)) {            // page opened?
			WebUI.comment('Secure Messages opened on attempt ' + attempt)
			return                                                                           // done
		}
		WebUI.click(home)                                                                    // go Home and retry
		WebUI.click(link)                                                                    // click Secure Messages again
	}
	WebUI.failed('Secure Messages page did not load after 3 attempts')                       // fail explicitly
}

// Maximeyes: from a restriction message open patient > Encounter Details > SOC
void openSocFromMessage() {
	clickObj(MX_PATIENT)                                                                     // patient link
	clickObj(HS_MX + 'a_Encounters_dropdown-toggle menu-large rec_046ac3')                   // Encounters menu
	clickObj(HS_MX + 'a_Encounter Details')                                                  // Encounter Details
	clickObj(MX_HAMBURGER)                                                                   // hamburger
	clickObj(MX_SOC_LINK)                                                                    // Summary Of Care
}

// Uploads a file on TestCloud
void uploadFileTestCloud(TestObject uploadObj, File file) {
	assert file.exists() && file.isFile() : "Upload file not found: ${file.absolutePath}"    // sanity check
	CustomKeywords.'com.katalon.testcloud.FileExecutor.uploadFileToWeb'(uploadObj, file.absolutePath) // upload
}

// ============================================================================
// TEST FLOW
// ============================================================================

// ---------------------------- Maximeyes setup --------------------------------
callTC(TC_PP + 'User Login in Maximeyes Pt Portal')                                // login to Maximeyes
callTC(TC_PP + 'Create Random Patient in Maximeyes',
	   [phoneNumber: GlobalVariable.Mobile, emailId: GlobalVariable.MyEmail_Id])   // create patient
waitBusy()                                                                         // wait for page to load

TestObject patientIdObj = findTestObject(OR + 'Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen') // patient id field
WebUI.waitForElementVisible(patientIdObj, T_MED)                                   // wait for field
GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''      // store patient id
println('Patient ID stored: ' + GlobalVariable.GV_PatientID)                       // log id

callTC(TC_PP + 'Portal Sign up using email')                                       // invite patient to portal

CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email, SOC_EMAIL_SUBJECT)                                // read portal credentials from email
println('Username: ' + GlobalVariable.GV_Username)                                 // log username only (password no longer logged)

callTC(TC_MX + 'Add New Encounter_Create New')                                     // new encounter
callTC(TC_MX + 'Enter Data In Enc Elements')                                       // fill encounter data

// ---------------------------- SOC in Maximeyes -------------------------------
clickObj(MX_MIX + 'span_TOC Req_mif-Hamburger fg-gray font30')                     // open hamburger
clickObj(MX_MIX + 'span_Summary Of Care(C-CDA)')                                   // open Summary Of Care
waitBusy()                                                                         // wait for SOC load
verifyText(MX_MIX + 'h2_Summary of Care (C-CDA)', SOC_TITLE)                       // SOC page header
verifyText(MX_MIX + 'h4_Zak Duckett',
		   GlobalVariable.PatientFirstName + ' ' + GlobalVariable.PatientLastName) // patient name on SOC
callTC(TC_MX + 'SOC_Verification')                                                 // verify all SOC data
clickObj(MX_MIX + 'span_Patient Portal_UploadToPatientPortal')                     // upload to portal
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(UPLOAD_TOAST)      // verify upload toast
clickObj(MX_MIX + 'Close Button SOC')                                              // close SOC

// ---------------------------- Portal first login -----------------------------
callTC(TC_PP + 'Navigate to Patient Portal Site')                                  // open portal
clickObj(SIGNIN + 'SignInBtn')                                                     // click Sign In
callTC(TC_PP + 'User Login With Username and Password',
	   [Username: GlobalVariable.GV_Username, Password: GlobalVariable.GV_Password]) // first login
callTC(TC_PP + 'DOB Confirmation and Accept Terms')                                // confirm DOB, accept terms
enterOtpAndProceed(false)                                                          // OTP (no extra wait needed)
callTC(TC_PP + 'Update Password')                                                  // set new password
callTC(TC_PP + 'User Login With Username and Password',
	   [Username: GlobalVariable.GV_Username, Password: GlobalVariable.UpdatePassword]) // login with new password
enterOtpAndProceed(true)                                                           // OTP again
verifyDashboard()                                                                  // dashboard name + date

// ---------------------------- Portal: message + SOC --------------------------
WebUI.verifyMatch(WebUI.getText(findTestObject(PP + 'span_1unread messages')).replaceAll('\\s+', ''),
				  EXPECTED_UNREAD, false)                                          // unread count = 1
String todayGMT = CustomKeywords.'common.DateUtil.getTodayDateGMT'()               // today's date (GMT)
clickObj(UNREAD_MSG)                                                               // open message
verifyText(VISIT_DATE, 'Visit Date: ' + todayGMT)                                  // visit date = today
callTC(TC_PP + 'SOC Verification On Patient Portal')                               // verify SOC data on portal

['btn_Transmit', 'btn_Print', 'btn_DownloadCCDA', 'btn_DownloadPDF'].each {        // action buttons present
	WebUI.verifyElementPresent(findTestObject(SOC_PP + it), T_SHORT, FailureHandling.STOP_ON_FAILURE)
}
WebUI.verifyElementPresent(findTestObject(SETTINGS_ICON), T_SHORT, FailureHandling.STOP_ON_FAILURE) // settings icon present

clickObj(PP + 'First Health Summary')                                              // open first health summary
waitLoader()                                                                       // wait for load

// ---------------------------- Download CCDA (XML) ----------------------------
DownloadHelper downloadHelper = new DownloadHelper()                               // helper instance
downloadHelper.captureBlobDownload()                                               // arm blob interceptor BEFORE clicking
clickObj(SOC_PP + 'btn_DownloadCCDA')                                              // trigger download
String filePath = downloadHelper.retrieveCapturedBlobAsFile(null, T_LONG)          // fetch captured blob as file
println('Downloaded XML saved at: ' + filePath)                                    // log path
assert new File(filePath).exists() : 'XML file was not saved'                      // file exists

// NOTE: print-dialog check disabled (popup close not working) - revisit later

// ---------------------------- Transmit flow ----------------------------------
clickObj(SOC_PP + 'btn_Transmit')                                                  // open transmit dialog

TestObject pdfBtn = findTestObject('Page_Patient Portal/Select Format', [fileType: FORMAT_PDF]) // dynamic format button
TestObject overlay = xp("//div[contains(@class,'fixed') and contains(@class,'inset-0') and contains(@class,'bg-black')]") // modal overlay
WebUI.waitForElementVisible(pdfBtn, T_MED)                                         // wait for format button
WebUI.waitForElementNotPresent(overlay, T_MED, FailureHandling.OPTIONAL)           // wait for overlay to go (returns at once if absent)
WebUI.waitForElementClickable(pdfBtn, T_MED)                                       // wait until clickable
WebUI.click(pdfBtn)                                                                // select PDF
clickObj(PP + 'button_Save')                                                       // save format
clickObj(PP + 'button_Send')                                                       // send with empty fields
verifyText(PP + 'div_Email address is required', EMAIL_REQUIRED_TEXT)              // email required
verifyText(PP + 'div_Subject is required', SUBJECT_REQUIRED_TEXT)                  // subject required

typeInto(PP + 'input_To_form-control mt-1 form-control-md _ef350e_4', BAD_EMAIL)   // invalid email
clickObj(PP + 'div_Send')                                                          // send
verifyText(PP + 'div_Please enter a valid email address', EMAIL_INVALID_TEXT)      // invalid email msg

typeInto(PP + 'input_To_form-control mt-1 form-control-md _ef350e_14', OK_FORMAT_EMAIL) // valid email format
clickObj(PP + 'div_Send')                                                          // send
verifyText(PP + 'div_Subject is required', SUBJECT_REQUIRED_TEXT)                  // subject still required

clickObj(PP + 'input_To_form-control mt-1 form-control-md _ef350e')                // focus To field
WebUI.clearText(findTestObject(PP + 'input_To_form-control mt-1 form-control-md _ef350e_14')) // clear old email
typeInto(PP + 'input_To_form-control mt-1 form-control-md _ef350e_32', TRANSMIT_TO) // recipient
typeInto(PP + 'input_Subject_form-control mt-1 form-contro_eec205_12', TRANSMIT_SUBJECT) // subject
typeInto(PP + 'textarea_Test Patient', TRANSMIT_BODY)                              // body
clickObj(PP + 'button_Send')                                                       // send
WebUI.verifyMatch(textOf(PP + 'div_Message SentYour health summary has bee_02649d'),
				  TRANSMIT_OK_TEXT, false)                                         // success message

// ---------------------------- Health Summary Settings ------------------------
clickObj(VISIT_DATE)                                                               // back to visit
waitLoader()                                                                       // wait for load
clickObj(SETTINGS_ICON)                                                            // open settings
verifyText(SETTINGS_TITLE, SETTINGS_TITLE_TEXT)                                    // dialog title
verifySettingsLabels()                                                             // 20 labels + unchecked (fast, 2 lookups)
verifyText(SETTINGS_SAVE, SAVE_TEXT)                                               // save button text

// -- Medications: exclude, then include --
toggleAndSave(CHK_MEDS, false)                                                     // exclude meds (dialog already open)
WebUI.verifyMatch(textOf(HS_PP + 'div_MedicationsInfo has been Excluded'),
				  MEDS_EXCLUDED_TEXT, false)                                       // excluded message
toggleAndSave(CHK_MEDS)                                                            // include meds again
verifyContainsAll(textOf(DIV_MEDS), MEDS_EXPECTED)                                 // meds data back

// -- Vital signs: exclude, then include, then exclude (for restriction flow) --
toggleAndSave(CHK_VITALS)                                                          // exclude vitals
WebUI.verifyMatch(textOf(DIV_VITALS), VITALS_EXCLUDED_TEXT, false)                 // excluded message
toggleAndSave(CHK_VITALS)                                                          // include vitals
verifyContainsAll(textOf(DIV_VITALS), VITALS_EXPECTED)                             // vitals data back
toggleAndSave(CHK_VITALS)                                                          // exclude vitals -> sends restriction request

CustomKeywords.'common.GMTTimeAuditVerifier.captureSendTimeGMT'()                  // capture send time for audit
WebUI.delay(MAIL_WAIT_SEC)                                                         // allow request to reach Maximeyes

// ---------------------------- Maximeyes: Accept ------------------------------
callTC(TC_PP + 'User Login in Maximeyes Pt Portal')                                // login to Maximeyes
openSecureMessages()                                                               // open Secure Messages (with retry)
typeInto(HS_MX + 'input_Secure Messages_searchboxofinbox', GlobalVariable.PatientFirstName) // search patient
waitBusy(20)                                                                       // wait for results
clickObj(MX_MSG_ROW)                                                               // open restriction message
String patientFullName = GlobalVariable.PatientFirstName + ' ' + GlobalVariable.PatientLastName // full name
verifyText(HS_MX + 'div_Action Required Patient Request to Rest_3cb7ef', RESTRICT_TITLE) // message title
verifyText(MX_PATIENT, patientFullName)                                            // patient name
CustomKeywords.'common.GMTTimeAuditVerifier.verifyUITimeWithinMinutesOfSendGMT'(
	WebUI.getText(findTestObject(HS_MX + 'span_01212026 1154 AM')), TIME_TOLERANCE_MIN) // UI time within tolerance

String mail = textOf(HS_MX + 'div_Dear Provider,A patient has submitted a_f926cb') // message body
verifyContainsAll(mail, RESTRICT_MAIL_EXPECTED)                                    // static content
verifyContainsAll(mail, [
	'Patient Name: ' + patientFullName,
	'Patient ID: ' + GlobalVariable.GV_PatientID,
	'Requested USCDI Element(s): ' + REQUESTED_ELEMENT
])                                                                                 // dynamic content
assert mail =~ MAIL_DATE_REGEX                                                     // date/time format

clickObj(HS_MX + 'button_Accept Request')                                          // accept
verifyText(MX_TOAST, ACTION_LOGGED_TOAST)                                          // action logged toast
openSocFromMessage()                                                               // go to SOC
WebUI.verifyMatch(textOf(HS_MX + 'div_Vital SignsInfo has been Excluded'),
				  VITALS_EXCLUDED_TEXT, false)                                     // vitals excluded in Maximeyes
clickObj(HS_MX + 'button_Cancel_dialog-close-button btn-close 143f7')              // close SOC

// ---------------------------- Portal: after Accept ---------------------------
portalLogin(GlobalVariable.UpdatePassword)                                         // login to portal
verifyDashboard()                                                                  // dashboard check
clickObj(UNREAD_MSG)                                                               // open message
WebUI.verifyMatch(textOf(DIV_VITALS), VITALS_EXCLUDED_TEXT, false)                 // re-read live page (was stale variable)
clickObj(SETTINGS_ICON)                                                            // open settings
WebUI.verifyEqual(WebUI.getAttribute(findTestObject(CHK_VITALS), 'checked'), 'true') // vitals checkbox is checked
clickObj(SETTINGS_SAVE)                                                            // close dialog
WebUI.waitForElementNotVisible(findTestObject(SETTINGS_TITLE), T_MED)              // dialog closed
settle()                                                                           // let UI settle

// ---------------------------- Maximeyes: Deny --------------------------------
callTC(TC_PP + 'User Login in Maximeyes Pt Portal')                                // login to Maximeyes
clickObj(HS_MX + 'a_Secure Messages')                                              // open Secure Messages
clickObj(MX_MSG_ROW)                                                               // open restriction message
clickObj(HS_MX + 'Page_MaximEyes/button_Deny Request')                             // deny
verifyText(MX_TOAST, ACTION_LOGGED_TOAST)                                          // action logged toast
WebUI.delay(MAIL_WAIT_SEC)                                                         // wait for denial email
CustomKeywords.'common.RestrictionEmailFullVerifier.fetchAndVerifyDeniedEmailBySubject'(
	IMAP_HOST, GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
	DENIED_EMAIL_SUBJECT, patientFullName, REQUESTED_ELEMENT)                      // verify denial email

// ---------------------------- Compose message with XML -----------------------
clickObj(HS_MX + 'span_Inbox_btnCompose')                                          // compose
clickObj(HS_MX + 'span_To_mif-search font18 fg-skyblue')                           // open patient search
clickObj(HS_MX + 'input_Do you want to search Patients or Ext_4d59c0')             // choose patient search
typeInto(HS_MX + 'input_ADVANCED PATIENT FIND_LastName', GlobalVariable.PatientLastName)  // last name
typeInto(HS_MX + 'Page_MaximEyes/Page_MaximEyes/input_ADVANCED PATIENT FIND_FirstName', GlobalVariable.PatientFirstName) // first name
clickObj(HS_MX + 'input_Is Active_button primary small-button')                    // search
clickObj(HS_MX + 'td_100740')                                                      // select patient
clickObj(HS_MX + 'input__btnSendToPatientPortal')                                  // send to portal
typeInto(HS_MX + 'input_Subject_ComposeSubject', COMPOSE_SUBJECT)                  // subject
typeInto(HS_MX + 'textarea_Message_input InboxTextarea font20 pad05', COMPOSE_BODY) // body

TestObject fileInput = new TestObject()                                            // upload input
fileInput.addProperty('id', ConditionType.EQUALS, FILE_INPUT_ID)                   // locate by id
uploadFileTestCloud(fileInput, new File(filePath))                                 // attach downloaded XML
println('XML file successfully uploaded')                                          // log

WebUI.verifyElementPresent(findTestObject(HS_MX + 'button_100739_Test.xml'), T_SHORT) // attachment chip visible
clickObj(HS_MX + 'button_Send')                                                    // send
waitBusy()                                                                         // wait for send

// ---------------------------- Verify Sent Messages ---------------------------
clickObj(HS_MX + 'span_Inbox_openmoreactionmenu')                                  // folder menu
clickObj(HS_MX + 'div_Sent Messages')                                              // Sent Messages
clickObj(HS_MX + 'td_To Patienteafce Test44fedXML CCDA File')                      // open sent message
verifyText(HS_MX + 'span_To Patienteafce Test44fed', 'To: ' + patientFullName)     // recipient
verifyText(HS_MX + 'p_Message to Patient including CCDA File', COMPOSE_BODY)       // body
verifyText(HS_MX + 'div_XML CCDA File', COMPOSE_SUBJECT)                           // subject
clickObj(HS_MX + 'Page_MaximEyes/span_Sent Messages_openmoreactionmenuSent')       // folder menu
clickObj(HS_MX + 'Page_MaximEyes/div_Inbox')                                       // back to Inbox
clickObj(MX_MSG_ROW)                                                               // open restriction message
openSocFromMessage()                                                               // go to SOC
verifyContainsAll(textOf(HS_MX + 'div_Vital SignsInfo has been Excluded'), VITALS_EXPECTED) // vitals visible again after deny

// ---------------------------- Portal: after Deny -----------------------------
portalLogin(GlobalVariable.UpdatePassword)                                         // login to portal
verifyDashboard()                                                                  // dashboard check
clickObj(UNREAD_MSG)                                                               // open message
verifyContainsAll(textOf(DIV_VITALS), VITALS_EXPECTED)                             // re-read live page (was stale variable)
clickObj(SETTINGS_ICON)                                                            // open settings
WebUI.verifyElementNotChecked(findTestObject(CHK_VITALS), T_SHORT)                 // vitals unchecked after deny
clickObj(SETTINGS_SAVE)                                                            // close dialog
WebUI.waitForElementNotVisible(findTestObject(SETTINGS_TITLE), T_MED)              // dialog closed
settle()                                                                           // let UI settle

// ---------------------------- Portal: XML message ----------------------------
clickObj(PP + 'Home Btn Patient Portal')                                           // go Home
WebUI.verifyMatch(WebUI.getText(findTestObject(OR + 'Maximeyes_Portal_Mix/Page_Patient Portal/Message Count')).replaceAll('\\s+', ''),
				  EXPECTED_UNREAD, false)                                          // unread count = 1
clickObj(HS_PP + 'div_1unread messages')                                           // open unread messages
clickObj(HS_PP + 'div_To Patienteafce Test44fed1102 AMXML CCDA File')              // open XML message
clickObj(HS_PP + 'XML File')                                                       // open XML attachment
waitLoader()                                                                       // wait for load (was delay 3)
verifyText(VISIT_DATE, 'Visit Date: ' + todayGMT)                                  // visit date = today
callTC(TC_PP + 'SOC Verification On Patient Portal')                               // final SOC verification
