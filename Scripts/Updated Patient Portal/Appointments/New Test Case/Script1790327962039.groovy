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
import gmail.GmailAppointmentReminder as email

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
String LOCATION_NAME  = 'Patient Portal - Bellingham, WA'
String PROVIDER_NAME = 'Patient Portal'
String REASON_NAME = 'Patient Portal'
String REASON_FOR_VISIT = 'Patient Portal Appt Reason'
String APPOINTMENT_TIME = '11:55 AM'
String APPOINTMENT_TIME1 = '01:35 PM'

// Calendar Configuration
int TOTAL_DAYS_RANGE = 15
int APPOINTMENT_DAYS_AHEAD = 4
int APPOINTMENT_DAYS_AHEAD1 = 5
int APPOINTMENT_DAYS_AHEAD2 = 6
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
TestObject requestNewAppointmentPlusBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/Request New Appt Plus btn')
TestObject appointmentCards = findTestObject('Appointments/PP Appointment/Page_Patient Portal/appointmentCards')

// Request Appointment Form Objects
TestObject requestAppointmentTitle = findTestObject('Appointments/PP Appointment/Page_Patient Portal/h1_Request Appointment')
TestObject emergencyNote = findTestObject('Appointments/PP Appointment/Page_Patient Portal/p_Note_If this is a medical emergency, please di')
TestObject locationDropdown = findTestObject('Appointments/PP Appointment/Page_Patient Portal/select_Select Location')
TestObject reasonDropdown = findTestObject('Appointments/PP Appointment/Page_Patient Portal/select_Select Reason')
TestObject providerDropdown = findTestObject('Appointments/PP Appointment/Page_Patient Portal/select_Select Reason')
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
	WebUI.assertElementPresent(upcomingAppointmentsHeader, 10)
	
	WebUI.comment('Step 9: Verify "No appointments found for selected period" message')
	WebUI.assertElementText(noAppointmentsMessage, 'No appointments found for selected period', 10)
	
	WebUI.comment('Step 10: Verify "No upcoming appointments" message')
	WebUI.assertElementText(noUpcomingAppointmentsMsg, 'No upcoming appointments', 10)
	
	// ---------- STEP 9: Click Request New Appointment Button ----------
	WebUI.comment('Step 11: Verify "Request New Appointment" button is present')
	WebUI.assertElementPresent(requestNewAppointmentBtn, 10)
	
	WebUI.comment('Step 12: Click on "Request New Appointment" button')
	WebUI.click(requestNewAppointmentBtn)
	
	// ---------- STEP 10: Verify Request Appointment Form ----------
	WebUI.comment('Step 13: Verify "Request Appointment" title is displayed')
	WebUI.assertElementText(requestAppointmentTitle, 'Request Appointment', 10)
	
	WebUI.comment('Step 14: Verify emergency note is displayed with contact information')
	String expectedEmergencyNote = 'Note:If this is a medical emergency, please dial ' + EMERGENCY_PHONE +
		' immediately or go to the nearest emergency room. If experiencing flashes, floaters, or sudden loss of vision please call the office immediately at ' +
		OFFICE_PHONE
	WebUI.assertElementText(emergencyNote, expectedEmergencyNote, 10)
	
	// ---------- STEP 11: Select Location and Reason ----------
	WebUI.comment('Step 15: Select Location from dropdown')
	WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)

	WebUI.comment('Step 16: Select Provider from dropdown')
	WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)
	
	WebUI.comment('Step 17: Select Reason from dropdown')
	WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)
	
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
	
	//Verify appointment reminder email
	String patientName = "Mdealz Mmrhzmvc"
	
	String cancelLink = CustomKeywords.'email.GmailAppointmentReminder.getCancelRescheduleLink'(
		GlobalVariable.MyEmail_Id,
		GlobalVariable.Email_Key,
		patientName
	)
	
	WebUI.comment("Cancel Link : " + cancelLink)
	
	// Open directly
	WebUI.navigateToUrl(cancelLink)
	
	// ---------- STEP 19: Verify Appointment Confirmation ----------
	WebUI.comment('Step 24: Verify "Appointment Confirmed" message is displayed')
	WebUI.assertElementText(confirmationHeader, 'Appointment Confirmed', 10)
	
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
	WebUI.assertElementPresent(cancelBtn, 10)
	
	WebUI.comment('Step 29: Verify Reschedule button is present')
	WebUI.assertElementPresent(rescheduleBtn, 10)
	
	WebUI.comment('✓ Test Completed Successfully: Appointment created and verified')
	
	// ---------- STEP 23: Add some more appointments ----------
	WebUI.click(requestNewAppointmentPlusBtn)
	
	// ---------- STEP 11: Select Location and Reason ----------
	WebUI.comment('Step 15: Select Location from dropdown')
	WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)

	WebUI.comment('Step 16: Select Provider from dropdown')
	WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)
	
	WebUI.comment('Step 17: Select Reason from dropdown')
	WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)
	
	WebUI.delay(DELAY_AFTER_REASON_SELECTION)
	
	// ---------- STEP 12: Click Proceed Button ----------
	WebUI.comment('Step 17: Click Proceed button to continue to date/time selection')
	TestObject proceedApptBtn1 = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Request New Appointment')
	WebElement proceedElement1 = WebUI.findWebElement(proceedApptBtn1, 10)
	WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proceedElement1))
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	// ---------- STEP 13: Open Calendar for Date Selection ----------
	WebUI.comment('Step 18: Click on Calendar icon to open date picker')
	WebUI.click(calendarIcon)
	
	
	WebUI.comment('From Date: ' + fromDate + ' | To Date: ' + toDate + ' | Appointment Date: ' + appointmentDate)
	
	// ---------- STEP 15: Select Appointment Date and Time ----------
	WebUI.comment('Step 20: Select appointment date and time from calendar')
	CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
		fromDate,
		toDate,
		appointmentDate,
		APPOINTMENT_TIME1
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
	
	// ---------- STEP 20: Navigate to Appointments Summary ----------
	WebUI.comment('Step 26: Click on Appointments button to view appointment summary')
	WebUI.click(appointmentsBtn)
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	// ---------- STEP 23: Add some more appointments ----------
	WebUI.click(requestNewAppointmentPlusBtn)
	
	// ---------- STEP 11: Select Location and Reason ----------
	WebUI.comment('Step 15: Select Location from dropdown')
	WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)

	WebUI.comment('Step 16: Select Provider from dropdown')
	WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)
	
	WebUI.comment('Step 17: Select Reason from dropdown')
	WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)
	
	WebUI.delay(DELAY_AFTER_REASON_SELECTION)
	
	// ---------- STEP 12: Click Proceed Button ----------
	WebUI.comment('Step 17: Click Proceed button to continue to date/time selection')
	TestObject proceedApptBtn2 = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Request New Appointment')
	WebElement proceedElement2 = WebUI.findWebElement(proceedApptBtn2, 10)
	WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proceedElement2))
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	// ---------- STEP 13: Open Calendar for Date Selection ----------
	WebUI.comment('Step 18: Click on Calendar icon to open date picker')
	WebUI.click(calendarIcon)
	
	String appointmentDate1 = today.plusDays(APPOINTMENT_DAYS_AHEAD1).format(formatter)
	
	WebUI.comment('From Date: ' + fromDate + ' | To Date: ' + toDate + ' | Appointment Date: ' + appointmentDate1)

	
	// ---------- STEP 15: Select Appointment Date and Time ----------
	WebUI.comment('Step 20: Select appointment date and time from calendar')
	CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
		fromDate,
		toDate,
		appointmentDate1,
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
	
	// ---------- STEP 20: Navigate to Appointments Summary ----------
	WebUI.comment('Step 26: Click on Appointments button to view appointment summary')
	WebUI.click(appointmentsBtn)
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	// ---------- STEP 23: Add some more appointments ----------
	WebUI.click(requestNewAppointmentPlusBtn)
	
	// ---------- STEP 11: Select Location and Reason ----------
	WebUI.comment('Step 15: Select Location from dropdown')
	WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)

	WebUI.comment('Step 16: Select Provider from dropdown')
	WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)
	
	WebUI.comment('Step 17: Select Reason from dropdown')
	WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)
	
	WebUI.delay(DELAY_AFTER_REASON_SELECTION)
	
	// ---------- STEP 12: Click Proceed Button ----------
	WebUI.comment('Step 17: Click Proceed button to continue to date/time selection')
	TestObject proceedApptBtn3 = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Request New Appointment')
	WebElement proceedElement3 = WebUI.findWebElement(proceedApptBtn3, 10)
	WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proceedElement3))
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	// ---------- STEP 13: Open Calendar for Date Selection ----------
	WebUI.comment('Step 18: Click on Calendar icon to open date picker')
	WebUI.click(calendarIcon)
	
	String appointmentDate2 = today.plusDays(APPOINTMENT_DAYS_AHEAD2).format(formatter)
	
	WebUI.comment('From Date: ' + fromDate + ' | To Date: ' + toDate + ' | Appointment Date: ' + appointmentDate2)

	
	// ---------- STEP 15: Select Appointment Date and Time ----------
	WebUI.comment('Step 20: Select appointment date and time from calendar')
	CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
		fromDate,
		toDate,
		appointmentDate2,
		APPOINTMENT_TIME1
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
	
	// ---------- STEP 20: Navigate to Appointments Summary ----------
	WebUI.comment('Step 26: Click on Appointments button to view appointment summary')
	WebUI.click(appointmentsBtn)
	
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	WebUI.comment('Verify upcoming appointments are displayed in chronological order')

	//Appointment oreder date and time wise	
CustomKeywords.'custom.AppointmentSequence.verifyUpcomingAppointmentsChronologicalOrder'(
	appointmentCards	
)
	//Appointment order status wise
CustomKeywords.'custom.AppointmentActions.verifyAppointmentsReverseChronologicalOrderAndStatus'(
	appointmentCards
)

//Appointemnt status Sequence wise
CustomKeywords.'custom.AppointmentActions.verifyAppointmentStatus'(
	appointmentCards,
	1,
	'Confirmed'
)

CustomKeywords.'custom.AppointmentActions.verifyAppointmentStatus'(
	appointmentCards,
	2,
	'Confirmed'
)

//cancel appointment 
CustomKeywords.'custom.AppointmentActions.clickCancelAndVerifyPrompt'(
	appointmentCards,
	1
)

//Click no button
CustomKeywords.'custom.AppointmentActions.respondToCancelConfirmation'(
	'No'
)

//cancel appointment
CustomKeywords.'custom.AppointmentActions.clickCancelAndVerifyPrompt'(
	appointmentCards,
	1
)

//Click Yes button
CustomKeywords.'custom.AppointmentActions.respondToCancelConfirmation'(
	'Yes'
)

String formattedDate = CustomKeywords.'yourPackage.YourKeyword.convertAppointmentDate'(
	appointmentDate, APPOINTMENT_TIME
)

boolean emailReceived = CustomKeywords.'email.GmailAppointmentCancellation.verifyAppointmentCancellationEmail'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	patientName,
	formattedDate
)

assert emailReceived

} catch (Exception e) {
	WebUI.comment('✗ Test Failed with Exception: ' + e.message)
	throw e
}