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
//import gmail.GmailAppointmentReminder as email

// ============================================================================
// TEST DATA - VARIABLES (Centralized for easy maintenance)
// ============================================================================
// Email Configuration for OTP Retrieval
String IMAP_SERVER = 'imap.gmail.com'
String OTP_SEARCH_TERM = 'Verification'

// Appointment Data
String LOCATION_NAME  = 'Patient Portal - Bellingham, WA'
String PROVIDER_NAME = 'Patient Portal'
String REASON_NAME = 'Patient Portal'
String REASON_FOR_VISIT = 'Patient Portal Appt Reason'
String APPOINTMENT_TIME = '11:50 AM'
String APPOINTMENT_TIME1 = '01:35 PM'

// Calendar Configuration
int TOTAL_DAYS_RANGE = 15
int APPOINTMENT_DAYS_AHEAD = 6
int APPOINTMENT_DAYS_AHEAD1 = 7
int APPOINTMENT_DAYS_AHEAD2 = 8
String DATE_FORMAT = 'MM/dd/yyyy'

// Contact Information
//String OFFICE_PHONE = '(232) 435-4342'
String OFFICE_PHONE = '(800) 920-1940'
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
	
	// ------------------------------------------------------------------------
	// REUSABLE FLOW: Request one appointment.
	// APPOINTMENT TIME IS A PREFERENCE: if the preferred slot is taken, the
	// keyword books the first available slot that is not in usedTimes.
	// Returns the time that was ACTUALLY booked.
	// ------------------------------------------------------------------------
	def requestAppointment = { String apptDate, String preferredTime, List<String> usedTimes, String fromDate, String toDate ->
 
		WebUI.comment('Select Location from dropdown')                                 // Log step
		WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)              // Choose location by visible label
 
		WebUI.comment('Select Provider from dropdown')                                 // Log step
		WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)              // Choose provider by visible label
 
		WebUI.comment('Select Reason from dropdown')                                   // Log step
		WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)                  // Choose reason by visible label
 
		WebUI.delay(DELAY_AFTER_REASON_SELECTION)                                      // Wait for the UI to settle after selection
 
		WebUI.comment('Click Proceed to continue to date/time selection')              // Log step
		WebElement proceedElement = WebUI.findWebElement(proceedApptBtn, 10)           // Locate the Proceed button (10s timeout)
		WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proceedElement)) // JS click (avoids overlay/intercept issues)
 
		                                       // Wait for the next page to load
 
		WebUI.comment('Open calendar date picker')                                     // Log step
		WebUI.click(calendarIcon)                                                      // Open the calendar
 
		WebUI.comment('From: ' + fromDate + ' | To: ' + toDate + ' | Appointment: ' + apptDate + ' | Preferred time: ' + preferredTime) // Log inputs
 
		WebUI.comment('Select appointment date and preferred time (fallback to first available)') // Log step
		String bookedTime = CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
			fromDate,                                                                  // Start of the selectable range
			toDate,                                                                    // End of the selectable range
			apptDate,                                                                  // Date to pick
			preferredTime,                                                             // Preferred time slot
			usedTimes                                                                  // Times already booked in this test (skipped)
		)
 
		WebUI.comment('Time actually booked: ' + bookedTime)                           // Log the real booked time
 
		WebUI.comment('Click Proceed to go to reason for visit page')                  // Log step
		WebUI.click(proceedBtn1)                                                       // Move to the reason-for-visit page
		WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                       // Wait for page load
 
		WebUI.comment('Enter reason for visit')                                        // Log step
		WebUI.setText(reasonTextarea, REASON_FOR_VISIT)                                // Type the reason into the textarea
 
		WebUI.comment('Click Proceed to submit appointment request')                   // Log step
		WebUI.click(proceedBtn2)                                                       // Submit the request
		WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                       // Wait for confirmation page
 
		return bookedTime                                                              // Hand back the time that was really booked
	}
 
	                                
	//Login to Maximeyes
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
	
	//Create Random Patient
	WebUI.callTestCase(
		findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
		[
			('phoneNumber') : GlobalVariable.Mobile,
			('emailId')     : GlobalVariable.MyEmail_Id,
		],
		FailureHandling.STOP_ON_FAILURE
	)
	//Get Patient ID
	TestObject patientIdObj = findTestObject(
		'Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen'
	)
	
	WebUI.waitForElementVisible(patientIdObj, 15)
	
	//Get patient Id
	GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''
	println "✅ Patient ID stored: " + GlobalVariable.GV_PatientID
	
	//Click on + button
	WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))
	
	//Select Send Sign Up Email to
	WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))
	
	//Click on Procced button
	WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))
	
	//Wait for busy indicator invisible
	WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
	
	//Verify toast msg
	WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'),'Patient Portal Sign Up Completed. Email Sent.')
	
	WebUI.delay(10)
	
	//get username and password
	CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
		GlobalVariable.MyEmail_Id,
		GlobalVariable.Email_Key,
		GlobalVariable.Sender_Email,
		"Access to your health data"
	)
	
	println "Username: " + GlobalVariable.GV_Username
	println "Password: " + GlobalVariable.GV_Password
	
 //Navigate to OA >> Schedule
	WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_Office Admin'))
	WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_Modules'))
	WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_ui-id-21'))
	
	CustomKeywords.'custom.AppointmentSettingsKeywords.setAppointmentSettings'(
		[
			"SCHEDULING_HOURS"              : "12",
			"CANCELATION_HOURS"             : "2",
			"DAILY_LIMIT_HOURS"             : "0",
			"ApptLimitForOnlineAppointment" : "5"
		],
		[
			"idLimitOnlineEnable"               : false,
			"idIsOnlineApptActivityReportEnable": false,
			"idIsEnableOnlineScheduleInsurance": false
		]
	)
	
	WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_Modules'))
	
	// ---------- STEP 1: Navigate to Patient Portal ----------
	WebUI.comment('Step 1: Navigate to Patient Portal Site')                           // Log step
	WebUI.callTestCase(
		findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), // Reusable navigation test case
		[:],                                                                           // No parameters needed
		FailureHandling.STOP_ON_FAILURE                                                // Abort test if navigation fails
	)
 
	// ---------- STEP 2: Click Sign In ----------
	WebUI.comment('Step 2: Click on Sign In Button')                                   // Log step
	WebUI.click(signInButton)                                                          // Open the sign-in form
 
	// ---------- STEP 3: Login ----------
	WebUI.comment('Step 3: Enter Username and Password and Sign In')                   // Log step
	//Enter Username and password
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)
 
	WebUI.delay(DELAY_AFTER_LOGIN)                                                     // Wait for OTP screen to load
 
	//Confirm DOB and accept terms
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

	//get otp from email
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

	//enter otp
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

	//Update existing password
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

	//Login with new password
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

	WebUI.delay(5)

	//get otp from email
	String otpA = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
	)

	println("OTP fetched = " + otpA)


	// Auto type into four input boxes
	String[] digitsA = otpA.toCharArray()

	//enter otp
	WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digitsA[0].toString())
	WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digitsA[1].toString())
	WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digitsA[2].toString())
	WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digitsA[3].toString())

	WebUI.delay(5)


	// Wait until the button is clickable (visible and enabled)
	WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

	// Click the button
	WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

	WebUI.delay(10)

	//Verify patient name date and time displayed correctly
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)                                 // Wait for dashboard to load
 
	// ---------- STEP 7: Open Dashboard Menu ----------
	WebUI.comment('Step 7: Click on Dashboard Menu Icon')                              // Log step
	WebUI.click(dashboardMenuBtn)                                                      // Open dashboard menu
 
	// ---------- STEP 8: Verify no appointments exist ----------
	WebUI.comment('Step 8: Verify "Upcoming Appointments" section is present')         // Log step
	WebUI.assertElementPresent(upcomingAppointmentsHeader, 10)                         // Header must be visible
 
	WebUI.comment('Step 9: Verify "No appointments found for selected period" message') // Log step
	WebUI.assertElementText(noAppointmentsMessage, 'No appointments found for selected period', 10) // Empty-state text
 
	WebUI.comment('Step 10: Verify "No upcoming appointments" message')                // Log step
	WebUI.assertElementText(noUpcomingAppointmentsMsg, 'No upcoming appointments', 10) // Second empty-state text
 
	// ---------- STEP 9: Click Request New Appointment ----------
	WebUI.comment('Step 11: Verify "Request New Appointment" button is present')       // Log step
	WebUI.assertElementPresent(requestNewAppointmentBtn, 10)                           // Button must exist
 
	WebUI.comment('Step 12: Click on "Request New Appointment" button')                // Log step
	WebUI.click(requestNewAppointmentBtn)                                              // Open the request form
 
	// ---------- STEP 10: Verify Request Appointment form ----------
	WebUI.comment('Step 13: Verify "Request Appointment" title is displayed')          // Log step
	WebUI.assertElementText(requestAppointmentTitle, 'Request Appointment', 10)        // Form title check
 
	WebUI.comment('Step 14: Verify emergency note is displayed with contact information') // Log step
	String expectedEmergencyNote = 'Note:If this is a medical emergency, please dial ' + EMERGENCY_PHONE +
		' immediately or go to the nearest emergency room. If experiencing flashes, floaters, or sudden loss of vision please call the office immediately at ' +
		OFFICE_PHONE                                                                   // Build expected note text with phone numbers
	WebUI.assertElementText(emergencyNote, expectedEmergencyNote, 10)                  // Compare with the UI text
 
	// ---------- STEP 11: Calculate dynamic dates ----------
	WebUI.comment('Step 15: Calculate appointment dates dynamically')                  // Log step
	LocalDate today = LocalDate.now()                                                  // Today's date
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)             // Formatter using the configured pattern
 
	String fromDate         = today.format(formatter)                                  // Range start = today
	String toDate           = today.plusDays(TOTAL_DAYS_RANGE).format(formatter)       // Range end = today + range
	String appointmentDate  = today.plusDays(APPOINTMENT_DAYS_AHEAD).format(formatter) // 1st and 2nd appointment date
	String appointmentDate1 = today.plusDays(APPOINTMENT_DAYS_AHEAD1).format(formatter) // 3rd appointment date
	String appointmentDate2 = today.plusDays(APPOINTMENT_DAYS_AHEAD2).format(formatter) // 4th appointment date
 
	// Tracks times already booked in this run so later bookings never reuse them
	List<String> usedTimes = []
 
	// ---------- STEP 12: Book 1st appointment ----------
	WebUI.comment('Step 16: Book 1st appointment')                                     // Log step
	String bookedTime1 = requestAppointment(appointmentDate, APPOINTMENT_TIME, usedTimes, fromDate, toDate) // Run the booking flow
	usedTimes << bookedTime1                                                           // Remember the booked time
 
	// ---------- STEP 13: Verify appointment reminder email ----------
	WebUI.comment('Step 17: Get Cancel/Reschedule link from reminder email')           // Log step
	String patientName = GlobalVariable.PatientFirstName+" "+GlobalVariable.PatientLastName                                             // Patient name used to find the email
 
	String cancelLink = CustomKeywords.'email.GmailAppointmentReminder.getCancelRescheduleLink'(
		GlobalVariable.MyEmail_Id,                                                     // Mailbox address
		GlobalVariable.Email_Key,                                                      // App password / access key
		patientName                                                                    // Patient name to match in the email
	)
 
	WebUI.comment("Cancel Link : " + cancelLink)                                       // Log the extracted link
//	WebUI.navigateToUrl(cancelLink)                                                    // Open the link directly
 
	// ---------- STEP 14: Verify confirmation ----------
	WebUI.comment('Step 18: Verify "Appointment Confirmed" message is displayed')      // Log step
	WebUI.assertElementText(confirmationHeader, 'Appointment Confirmed', 10)           // Confirmation header check
 
	WebUI.comment('Step 19: Verify appointment card details with custom keyword')      // Log step
	CustomKeywords.'custom.ApptDateTimeVerification.verifyAppointmentCard'(
		appointmentCard,                                                               // Card element
		'Dr.  Patient  Portal',                                                        // Expected provider (spacing matches UI)
		appointmentDate,                                                               // Expected date
		bookedTime1                                                                    // Time that was actually booked
	)
 
	// ---------- STEP 15: Go to Appointments summary ----------
	WebUI.comment('Step 20: Click on Appointments button to view appointment summary') // Log step
	WebUI.click(appointmentsBtn)                                                       // Open appointments list
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                           // Wait for page load
 
	// ---------- STEP 16: Verify summary details ----------
	WebUI.comment('Step 21: Verify appointment summary with custom keyword')           // Log step
	CustomKeywords.'custom.ApptDateTimeVerification.verifyAppointmentSummary'(
		appointmentsSummaryContainer,                                                  // Summary container element
		appointmentDate,                                                               // Expected date
		bookedTime1,                                                                   // Time that was actually booked
		'Patient Portal',                                                              // Expected patient/provider text
		'First Insight Vision',                                                        // Expected location/practice
		'Confirmed'                                                                    // Expected status
	)
 
	// ---------- STEP 17: Verify action buttons ----------
	WebUI.comment('Step 22: Verify Cancel button is present')                          // Log step
	WebUI.assertElementPresent(cancelBtn, 10)                                          // Cancel button exists
 
	WebUI.comment('Step 23: Verify Reschedule button is present')                      // Log step
	WebUI.assertElementPresent(rescheduleBtn, 10)                                      // Reschedule button exists
 
	// ---------- STEP 18: Book 2nd appointment ----------
	WebUI.comment('Step 24: Book 2nd appointment')                                     // Log step
	WebUI.click(requestNewAppointmentPlusBtn)                                          // Click "+" to add another appointment
	String bookedTime2 = requestAppointment(appointmentDate, APPOINTMENT_TIME1, usedTimes, fromDate, toDate) // Same date, different time
	usedTimes << bookedTime2                                                           // Remember the booked time
	WebUI.click(appointmentsBtn)                                                       // Back to appointments list
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                           // Wait for page load
 
	// ---------- STEP 19: Book 3rd appointment ----------
	WebUI.comment('Step 25: Book 3rd appointment')                                     // Log step
	WebUI.click(requestNewAppointmentPlusBtn)                                          // Click "+" to add another appointment
	String bookedTime3 = requestAppointment(appointmentDate1, APPOINTMENT_TIME, usedTimes, fromDate, toDate) // Later date, first time slot
	usedTimes << bookedTime3                                                           // Remember the booked time
	WebUI.click(appointmentsBtn)                                                       // Back to appointments list
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                           // Wait for page load
 
	// ---------- STEP 20: Book 4th appointment ----------
	WebUI.comment('Step 26: Book 4th appointment')                                     // Log step
	WebUI.click(requestNewAppointmentPlusBtn)                                          // Click "+" to add another appointment
	String bookedTime4 = requestAppointment(appointmentDate2, APPOINTMENT_TIME1, usedTimes, fromDate, toDate) // Latest date, second time slot
	usedTimes << bookedTime4                                                           // Remember the booked time
	WebUI.click(appointmentsBtn)                                                       // Back to appointments list
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                           // Wait for page load
 
	WebUI.comment('Booked times: ' + usedTimes)                                        // Log all times booked in this run
 
	// ---------- STEP 21: Verify chronological order ----------
	WebUI.comment('Step 27: Verify upcoming appointments are displayed in chronological order') // Log step
	CustomKeywords.'custom.AppointmentSequence.verifyUpcomingAppointmentsChronologicalOrder'(
		appointmentCards                                                               // All appointment cards on the page
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
	
//	//Appointment order status wise
//	CustomKeywords.'custom.AppointmentActions.verifyAppointmentsReverseChronologicalOrderAndStatus'(
//		appointmentCards
//	)
	
	
	//cancel appointment
	Map res = CustomKeywords.'custom.AppointmentActions.clickCancelAndVerifyPrompt'(
	appointmentCards, 1
	)


	
	//Click no button
	CustomKeywords.'custom.AppointmentActions.respondToCancelConfirmation'(
		'No'
	)
	
	//cancel appointment
		//cancel appointment
	res = CustomKeywords.'custom.AppointmentActions.clickCancelAndVerifyPrompt'(
	appointmentCards, 1
	)
	
	//Click Yes button
	CustomKeywords.'custom.AppointmentActions.respondToCancelConfirmation'(
		'Yes'
	)
	
	println "Cancelled appointment on ${res.date} at ${res.time}"
	
	String ApptDateTime = res.date+" "+res.time
	
	String formattedDate = CustomKeywords.'email.GmailAppointmentCancellation.convertAppointmentDate'(
		ApptDateTime
	)
	
	println("✅ Formatted Date: ${formattedDate}")
	
	boolean emailReceived = CustomKeywords.'email.GmailAppointmentCancellation.verifyAppointmentCancellationEmail'(
		GlobalVariable.MyEmail_Id,
		GlobalVariable.Email_Key,
		patientName,
		formattedDate
	)
	
	assert emailReceived
 
	WebUI.comment('✓ Test Completed Successfully: Appointments created and verified') // Final success log
 
}catch (Throwable t) {
	WebUI.comment('✗ Test failed: ' + t.toString())

	throw t                  // keeps the test marked FAILED; finally still runs afterward
}
finally {
	try {
		WebUI.comment('Starting cleanup in finally block')

		if (GlobalVariable.GV_PatientID) {

			// Login to Maximeyes
			WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.OPTIONAL)

			// Search patient using patient ID
			WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'), [('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.OPTIONAL)

			// Navigate to Schedule
			CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Schedule', SubItem: 'Schedule'])

			// Cancel all appointments
			CustomKeywords.'custom.CancelAppointmentKeywords.cancelAllAppointments'()
		}
	} catch (Throwable cleanupError) {
		WebUI.comment('Cleanup failed: ' + cleanupError.toString())
	}
}



