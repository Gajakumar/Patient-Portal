import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

////Navigate to Patient Portal
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Click on Sign In Button
//WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
//
////Enter User name and password and click on sign in button
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : 'MdeMmr0316', ('Password') : 'Test@1234'], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.delay(5)
//
////Fetch the otp from the email
//String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
//	'imap.gmail.com',
//	GlobalVariable.MyEmail_Id,
//	GlobalVariable.Email_Key,
//	GlobalVariable.Sender_Email,
//	'Verification'
//)
//
//println("OTP fetched = " + otp1)
//
//
//// Auto type into four input boxes
//String[] digits1 = otp1.toCharArray()
//
////Enter the OTP
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits1[0].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits1[1].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits1[2].toString())
//WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits1[3].toString())
//
//WebUI.delay(5)
//
//TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
//
//// Wait until the button is clickable (visible and enabled)
//WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)
//
////Click on Procced button
//WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
//
//
//WebUI.delay(5)
//
//WebUI.click(findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_dashboard-menu-icon-btn border-2 rounded-ful'))
//
//WebUI.assertElementPresent(findTestObject('Appointments/PP Appointment/Page_Patient Portal/h2_Upcoming Appointments'), 0)
//
//WebUI.assertElementText(findTestObject('Appointments/PP Appointment/Page_Patient Portal/p_No appointments found for selected period'), 
//    'No appointments found for selected period', 0)
//
//WebUI.assertElementText(findTestObject('Appointments/PP Appointment/Page_Patient Portal/h3_No upcoming appointments'), 'No upcoming appointments', 
//    0)
//
//WebUI.assertElementPresent(findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Request New Appointment'), 
//    0)
//
//WebUI.click(findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Request New Appointment'))
//
//WebUI.assertElementText(findTestObject('Appointments/PP Appointment/Page_Patient Portal/h1_Request Appointment'), 'Request Appointment', 
//    0)
//
//WebUI.assertElementText(findTestObject('Appointments/PP Appointment/Page_Patient Portal/p_Note_If this is a medical emergency, please di'), 
//    'Note:If this is a medical emergency, please dial 911 immediately or go to the nearest emergency room. If experiencing flashes, floaters, or sudden loss of vision please call the office immediately at (232) 435-4342', 
//    0)
//
//WebUI.selectOptionByValue(findTestObject('Appointments/PP Appointment/Page_Patient Portal/select_Select Location'), '4', 
//    false)
//
//WebUI.selectOptionByValue(findTestObject('Appointments/PP Appointment/Page_Patient Portal/select_Select Reason'), '2', false)
//
//WebUI.delay(3)
////WebUI.click(findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Request New Appointment'))
//WebElement proccedApptButton = WebUI.findWebElement(findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Request New Appointment'), 10)
//WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedApptButton))
//
//WebUI.waitForPageLoad(30)
//
//WebUI.click(findTestObject('Object Repository/Appointments/Calendar/Calender icon'))
//
//// Today's date
//LocalDate today = LocalDate.now()
//
//// Number of days to add
//int toDateDays = 7
//int appointmentDays = 4
//
//// Format required by AppointmentKeywords
//DateTimeFormatter formatter =
//		DateTimeFormatter.ofPattern("MM/dd/yyyy")
//
//// Dynamic dates
//String fromDate =
//		today.format(formatter)
//
//String toDate =
//		today.plusDays(toDateDays).format(formatter)
//
//String appointmentDate =
//		today.plusDays(appointmentDays).format(formatter)
//
//// Select appointment
//CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
//		fromDate,
//		toDate,
//		appointmentDate,
//		'02:30 PM'
//)
//
//
//
//WebUI.click(findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Proceed'))
//
//WebUI.waitForPageLoad(30)
//
//WebUI.setText(findTestObject('Appointments/PP Appointment/Page_Patient Portal/textarea_Reason for visit _'), 'Patient Portal Appt Reason')
//
//WebUI.click(findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Proceed_1'))
//
//WebUI.waitForPageLoad(30)
//
//WebUI.assertElementText(findTestObject('Appointments/PP Appointment/Page_Patient Portal/h4_Appointment Confirmed'), 'Appointment Confirmed', 
//    0)
//
////WebUI.assertElementPresent(findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_Dr. Patient Portal9_55 PM Friday, Sep 25, 20'), 
////    0)
//
//CustomKeywords.'custom.ApptDateTimeVerification.verifyAppointmentCard'(
//	findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_Dr. Patient Portal9_55 PM Friday, Sep 25, 20'),
//	'Dr.  Patient  Portal',
//	appointmentDate,
//	'2:30 PM'
//)
//
//WebUI.click(findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Appointments'))
//
//WebUI.waitForPageLoad(30)
//
////WebUI.assertElementText(findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_09_25_2026 _ 09_55 PM _ Patient PortalPatien'), 
////    '10/15/2026 | 02:30 PM | Patient PortalPatient Portal | Patient PortalStatus: Confirmed', 0)
//
////WebUI.assertElementText(findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_09_25_2026 _ 09_55 PM _ Patient PortalFirst'), 
////    '10/15/2026 | 02:30 PM | Patient PortalFirst Insight Vision, Patient PortalStatus: Confirmed', 0)
//
//CustomKeywords.'custom.ApptDateTimeVerification.verifyAppointmentSummary'(
//	findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_09_25_2026 _ 09_55 PM _ Patient PortalFirst'),
//	appointmentDate,
//	'2:30 PM',
//	'Patient Portal',
//	'First Insight Vision',
//	'Confirmed'
//)
//
//WebUI.assertElementPresent(findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Cancel'), 0)
//
//WebUI.assertElementPresent(findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Reschedule'), 0)


/**
 * Test: Patient Portal - Request and Verify Appointment
 * Description: This test automates the complete workflow of signing into the patient portal,
 *              requesting a new appointment, selecting date/time, and verifying confirmation.
 * Author: [Your Name]
 * Date: [Date]
 */



// ============================================================================
// TEST DATA - VARIABLES (Centralized for easy maintenance)
// ============================================================================

// User Credentials
String USERNAME = 'MdeMmr0316'
String PASSWORD = 'Test@1234'

// Email Configuration for OTP Retrieval
String IMAP_SERVER = 'imap.gmail.com'
String OTP_SEARCH_TERM = 'Verification'

// Appointment Data
String LOCATION_ID = '4'
String REASON_ID = '2'
String REASON_FOR_VISIT = 'Patient Portal Appt Reason'
String APPOINTMENT_TIME = '02:30 PM'

// Calendar Configuration
int TOTAL_DAYS_RANGE = 7
int APPOINTMENT_DAYS_AHEAD = 4
String DATE_FORMAT = 'MM/dd/yyyy'

// Contact Information
String OFFICE_PHONE = '(232) 435-4342'
String EMERGENCY_PHONE = '911'

// Delays (in seconds)
int DELAY_AFTER_LOGIN = 5
int DELAY_AFTER_OTP_ENTRY = 5
int DELAY_AFTER_REASON_SELECTION = 3
int DELAY_BEFORE_CALENDAR = 5
int PAGE_LOAD_TIMEOUT = 30
int ELEMENT_CLICKABLE_TIMEOUT = 15

// ============================================================================
// TEST OBJECTS - Declarations (Centralized at top for easy reference)
// ============================================================================

// Sign In Page Objects
TestObject signInButton = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn')
TestObject otpInput1 = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1')
TestObject otpInput2 = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2')
TestObject otpInput3 = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3')
TestObject otpInput4 = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4')
TestObject proceedBtnAfterOTP = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Dashboard Objects
TestObject dashboardMenuBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_dashboard-menu-icon-btn border-2 rounded-ful')

// Appointments Page Objects
TestObject upcomingAppointmentsHeader = findTestObject('Appointments/PP Appointment/Page_Patient Portal/h2_Upcoming Appointments')
TestObject noAppointmentsMessage = findTestObject('Appointments/PP Appointment/Page_Patient Portal/p_No appointments found for selected period')
TestObject noUpcomingAppointmentsMsg = findTestObject('Appointments/PP Appointment/Page_Patient Portal/h3_No upcoming appointments')
TestObject requestNewAppointmentBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Request New Appointment')

// Request Appointment Form Objects
TestObject requestAppointmentTitle = findTestObject('Appointments/PP Appointment/Page_Patient Portal/h1_Request Appointment')
TestObject emergencyNote = findTestObject('Appointments/PP Appointment/Page_Patient Portal/p_Note_If this is a medical emergency, please di')
TestObject locationDropdown = findTestObject('Appointments/PP Appointment/Page_Patient Portal/select_Select Location')
TestObject reasonDropdown = findTestObject('Appointments/PP Appointment/Page_Patient Portal/select_Select Reason')
TestObject proceedApptBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Request New Appointment')

// Calendar Objects
TestObject calendarIcon = findTestObject('Object Repository/Appointments/Calendar/Calender icon')

// Reason for Visit Objects
TestObject reasonTextarea = findTestObject('Appointments/PP Appointment/Page_Patient Portal/textarea_Reason for visit _')
TestObject proceedBtn1 = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Proceed')
TestObject proceedBtn2 = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Proceed_1')

// Confirmation Objects
TestObject confirmationHeader = findTestObject('Appointments/PP Appointment/Page_Patient Portal/h4_Appointment Confirmed')
TestObject appointmentCard = findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_Dr. Patient Portal9_55 PM Friday, Sep 25, 20')

// Appointments Summary Objects
TestObject appointmentsSummaryContainer = findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_09_25_2026 _ 09_55 PM _ Patient PortalFirst')
TestObject appointmentsBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Appointments')
TestObject cancelBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Cancel')
TestObject rescheduleBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Reschedule')

// ============================================================================
// TEST EXECUTION
// ============================================================================

try {
	// ---------- STEP 1: Navigate to Patient Portal ----------
	WebUI.comment('Step 1: Navigate to Patient Portal Site')
	WebUI.callTestCase(
		findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
		[:],
		FailureHandling.STOP_ON_FAILURE
	)
	
	// ---------- STEP 2: Click Sign In Button ----------
	WebUI.comment('Step 2: Click on Sign In Button')
	WebUI.click(signInButton)
	
	// ---------- STEP 3: Login with Username and Password ----------
	WebUI.comment('Step 3: Enter Username and Password and Sign In')
	WebUI.callTestCase(
		findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
		[('Username') : USERNAME, ('Password') : PASSWORD],
		FailureHandling.STOP_ON_FAILURE
	)
	
	WebUI.delay(DELAY_AFTER_LOGIN)
	
	// ---------- STEP 4: Fetch OTP from Email ----------
	WebUI.comment('Step 4: Fetch OTP from Email via Gmail IMAP')
	String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
		IMAP_SERVER,
		GlobalVariable.MyEmail_Id,
		GlobalVariable.Email_Key,
		GlobalVariable.Sender_Email,
		OTP_SEARCH_TERM
	)
	
	WebUI.comment('OTP fetched: ' + otp)
	println("OTP fetched = " + otp)
	
	// ---------- STEP 5: Enter OTP in Four Input Fields ----------
	WebUI.comment('Step 5: Enter OTP digits into individual input fields')
	String[] otpDigits = otp.toCharArray()
	
	WebUI.setText(otpInput1, otpDigits[0].toString())
	WebUI.setText(otpInput2, otpDigits[1].toString())
	WebUI.setText(otpInput3, otpDigits[2].toString())
	WebUI.setText(otpInput4, otpDigits[3].toString())
	
	WebUI.delay(DELAY_AFTER_OTP_ENTRY)
	
	// ---------- STEP 6: Click Proceed Button After OTP Verification ----------
	WebUI.comment('Step 6: Wait for and click Proceed button after OTP verification')
	WebUI.waitForElementClickable(proceedBtnAfterOTP, ELEMENT_CLICKABLE_TIMEOUT, FailureHandling.STOP_ON_FAILURE)
	WebUI.click(proceedBtnAfterOTP, FailureHandling.STOP_ON_FAILURE)
	
	WebUI.delay(DELAY_AFTER_LOGIN)
	
	// ---------- STEP 7: Open Dashboard Menu ----------
	WebUI.comment('Step 7: Click on Dashboard Menu Icon')
	WebUI.click(dashboardMenuBtn)
	
	// ---------- STEP 8: Verify No Appointments Currently Exist ----------
	WebUI.comment('Step 8: Verify "Upcoming Appointments" section is present')
	WebUI.assertElementPresent(upcomingAppointmentsHeader, 0)
	
	WebUI.comment('Step 9: Verify "No appointments found for selected period" message')
	WebUI.assertElementText(noAppointmentsMessage, 'No appointments found for selected period', 0)
	
	WebUI.comment('Step 10: Verify "No upcoming appointments" message')
	WebUI.assertElementText(noUpcomingAppointmentsMsg, 'No upcoming appointments', 0)
	
	// ---------- STEP 9: Click Request New Appointment Button ----------
	WebUI.comment('Step 11: Verify "Request New Appointment" button is present')
	WebUI.assertElementPresent(requestNewAppointmentBtn, 0)
	
	WebUI.comment('Step 12: Click on "Request New Appointment" button')
	WebUI.click(requestNewAppointmentBtn)
	
	// ---------- STEP 10: Verify Request Appointment Form ----------
	WebUI.comment('Step 13: Verify "Request Appointment" title is displayed')
	WebUI.assertElementText(requestAppointmentTitle, 'Request Appointment', 0)
	
	WebUI.comment('Step 14: Verify emergency note is displayed with contact information')
	String expectedEmergencyNote = 'Note:If this is a medical emergency, please dial ' + EMERGENCY_PHONE +
		' immediately or go to the nearest emergency room. If experiencing flashes, floaters, or sudden loss of vision please call the office immediately at ' +
		OFFICE_PHONE
	WebUI.assertElementText(emergencyNote, expectedEmergencyNote, 0)
	
	// ---------- STEP 11: Select Location and Reason ----------
	WebUI.comment('Step 15: Select Location from dropdown')
	WebUI.selectOptionByValue(locationDropdown, LOCATION_ID, false)
	
	WebUI.comment('Step 16: Select Reason from dropdown')
	WebUI.selectOptionByValue(reasonDropdown, REASON_ID, false)
	
	WebUI.delay(DELAY_AFTER_REASON_SELECTION)
	
	// ---------- STEP 12: Click Proceed Button ----------
	WebUI.comment('Step 17: Click Proceed button to continue to date/time selection')
	WebElement proceedElement = WebUI.findWebElement(proceedApptBtn, 10)
	WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proceedElement))
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	// ---------- STEP 13: Open Calendar for Date Selection ----------
	WebUI.comment('Step 18: Click on Calendar icon to open date picker')
	WebUI.click(calendarIcon)
	
	// ---------- STEP 14: Calculate Dynamic Dates ----------
	WebUI.comment('Step 19: Calculate appointment dates dynamically')
	LocalDate today = LocalDate.now()
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
	
	String fromDate = today.format(formatter)
	String toDate = today.plusDays(TOTAL_DAYS_RANGE).format(formatter)
	String appointmentDate = today.plusDays(APPOINTMENT_DAYS_AHEAD).format(formatter)
	
	WebUI.comment('From Date: ' + fromDate + ' | To Date: ' + toDate + ' | Appointment Date: ' + appointmentDate)
	
	// ---------- STEP 15: Select Appointment Date and Time ----------
	WebUI.comment('Step 20: Select appointment date and time from calendar')
	CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
		fromDate,
		toDate,
		appointmentDate,
		APPOINTMENT_TIME
	)
	
	// ---------- STEP 16: Proceed to Reason for Visit ----------
	WebUI.comment('Step 21: Click Proceed to proceed to reason for visit page')
	WebUI.click(proceedBtn1)
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	// ---------- STEP 17: Enter Reason for Visit ----------
	WebUI.comment('Step 22: Enter reason for visit in textarea')
	WebUI.setText(reasonTextarea, REASON_FOR_VISIT)
	
	// ---------- STEP 18: Proceed to Confirmation ----------
	WebUI.comment('Step 23: Click Proceed to submit appointment request')
	WebUI.click(proceedBtn2)
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	// ---------- STEP 19: Verify Appointment Confirmation ----------
	WebUI.comment('Step 24: Verify "Appointment Confirmed" message is displayed')
	WebUI.assertElementText(confirmationHeader, 'Appointment Confirmed', 0)
	
	WebUI.comment('Step 25: Verify appointment card details with custom keyword')
	CustomKeywords.'custom.ApptDateTimeVerification.verifyAppointmentCard'(
		appointmentCard,
		'Dr.  Patient  Portal',
		appointmentDate,
		APPOINTMENT_TIME
	)
	
	// ---------- STEP 20: Navigate to Appointments Summary ----------
	WebUI.comment('Step 26: Click on Appointments button to view appointment summary')
	WebUI.click(appointmentsBtn)
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	// ---------- STEP 21: Verify Appointment Summary Details ----------
	WebUI.comment('Step 27: Verify appointment summary with custom keyword')
	CustomKeywords.'custom.ApptDateTimeVerification.verifyAppointmentSummary'(
		appointmentsSummaryContainer,
		appointmentDate,
		APPOINTMENT_TIME,
		'Patient Portal',
		'First Insight Vision',
		'Confirmed'
	)
	
	// ---------- STEP 22: Verify Action Buttons ----------
	WebUI.comment('Step 28: Verify Cancel button is present')
	WebUI.assertElementPresent(cancelBtn, 0)
	
	WebUI.comment('Step 29: Verify Reschedule button is present')
	WebUI.assertElementPresent(rescheduleBtn, 0)
	
	WebUI.comment('✓ Test Completed Successfully: Appointment created and verified')
	
} catch (Exception e) {
	WebUI.comment('✗ Test Failed with Exception: ' + e.message)
	throw e
}