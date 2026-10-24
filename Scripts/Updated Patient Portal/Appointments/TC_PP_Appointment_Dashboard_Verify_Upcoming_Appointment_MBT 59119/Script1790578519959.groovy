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
import com.kms.katalon.core.util.KeywordUtil


// ============================================================================
// IMPORTS
// ============================================================================
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject                    // Lets us call findTestObject(...) directly
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase                         // Lets us call findTestCase(...) directly
import com.kms.katalon.core.model.FailureHandling as FailureHandling                             // STOP_ON_FAILURE / OPTIONAL etc.
import com.kms.katalon.core.testobject.TestObject                                                // Type used for all UI element references
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI                          // Katalon web keywords
import internal.GlobalVariable as GlobalVariable                                                 // Project-wide variables (email, patient, etc.)
import org.openqa.selenium.WebElement                                                            // Needed for the JS click on the Proceed button
import java.time.LocalDate                                                                       // Used to calculate dynamic dates
import java.time.format.DateTimeFormatter                                                        // Used to format dates as MM/dd/yyyy

// ============================================================================
// TEST DATA (centralized for easy maintenance)
// ============================================================================
String OTP_IMAP_SERVER      = 'imap.gmail.com'                                                   // Mail server used to read the OTP
String OTP_SEARCH_TERM      = 'Verification'                                                     // Subject keyword of the OTP email
String CREDENTIAL_SUBJECT   = 'Access to your health data'                                       // Subject keyword of the sign-up email

String LOCATION_NAME        = 'Patient Portal - Bellingham, WA'                                  // Location dropdown label
String PROVIDER_NAME        = 'Patient Portal'                                                   // Provider dropdown label
String REASON_NAME          = 'Patient Portal'                                                   // Reason dropdown label
String REASON_FOR_VISIT     = 'Patient Portal Appt Reason'                                       // Free-text reason for visit
String APPOINTMENT_TIME     = '11:50 AM'                                                         // Preferred first time slot
String APPOINTMENT_TIME1    = '01:35 PM'                                                         // Preferred second time slot

int TOTAL_DAYS_RANGE        = 15                                                                 // Calendar range: today -> today + 15 days
int APPOINTMENT_DAYS_AHEAD  = 0                                                                  // 1st and 2nd appointment: today
int APPOINTMENT_DAYS_AHEAD1 = 5                                                                  // 3rd appointment: today + 5 days
int APPOINTMENT_DAYS_AHEAD2 = 8                                                                  // 4th appointment: today + 8 days
String DATE_FORMAT          = 'MM/dd/yyyy'                                                       // Date format used by the portal calendar

String OFFICE_PHONE         = '(800) 920-1940'                                                   // Office phone shown in the emergency note
String EMERGENCY_PHONE      = '911'                                                              // Emergency number shown in the emergency note

int EMAIL_WAIT              = 10                                                                 // Seconds to let the sign-up email arrive
int PAGE_LOAD_TIMEOUT       = 30                                                                 // Max seconds to wait for a page load
int CLICKABLE_TIMEOUT       = 15                                                                 // Max seconds to wait for an element to be clickable
int ELEMENT_TIMEOUT         = 10                                                                 // Default max seconds for presence/text checks
int BUSY_TIMEOUT            = 10                                                                 // Max seconds to wait for the busy spinner to vanish

String CANCEL_POPUP_TEXT    = 'Your Appointment cannot be canceled/rescheduled online. Please call the office directly 800 920 1940'   // Expected popup text after Cancel
String RESCH_POPUP_TEXT     = 'Your appointment cannot be cancelled/rescheduled online. Please call the office directly 800 920 1940'  // Expected popup text after Reschedule

// ============================================================================
// TEST OBJECTS (only the ones actually used)
// ============================================================================
String PORTAL_REPO = 'Appointments/PP Appointment/Page_Patient Portal/'                          // Common repository folder for portal objects
String ME_REPO     = 'Appointments/Appt Type/Page_MaximEyes/'                                    // Common repository folder for MaximEyes objects

TestObject signInButton        = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn')                          // Sign In button
List<TestObject> otpBoxes      = (1..4).collect { findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp' + it) }       // The four OTP input boxes
TestObject proceedBtnAfterOTP  = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')   // Proceed button after OTP

TestObject dashboardMenuBtn    = findTestObject(PORTAL_REPO + 'div_dashboard-menu-icon-btn border-2 rounded-ful')                // Dashboard menu icon
TestObject upcomingApptsHeader = findTestObject(PORTAL_REPO + 'h2_Upcoming Appointments')                                        // "Upcoming Appointments" header
TestObject noApptsMessage      = findTestObject(PORTAL_REPO + 'p_No appointments found for selected period')                     // Empty-state message 1
TestObject noUpcomingApptsMsg  = findTestObject(PORTAL_REPO + 'h3_No upcoming appointments')                                     // Empty-state message 2
TestObject requestNewApptBtn   = findTestObject(PORTAL_REPO + 'button_Request New Appointment')                                  // "Request New Appointment" button
TestObject requestNewApptPlus  = findTestObject(PORTAL_REPO + 'Request New Appt Plus btn')                                       // "+" button for another appointment
TestObject appointmentCards    = findTestObject(PORTAL_REPO + 'appointmentCards')                                                // All appointment cards

TestObject requestApptTitle    = findTestObject(PORTAL_REPO + 'h1_Request Appointment')                                          // Request form title
TestObject emergencyNote       = findTestObject(PORTAL_REPO + 'p_Note_If this is a medical emergency, please di')               // Emergency note paragraph
TestObject locationDropdown    = findTestObject(PORTAL_REPO + 'select_Select Location')                                          // Location dropdown
TestObject reasonDropdown      = findTestObject(PORTAL_REPO + 'select_Select Reason')                                            // Reason dropdown
TestObject providerDropdown    = findTestObject(PORTAL_REPO + 'select_Select Reason')                                            // Provider dropdown (NOTE: same object as reason - verify!)
TestObject proceedApptBtn      = findTestObject(PORTAL_REPO + 'button_Request New Appointment')                                  // Proceed on request form (same object as request button)

TestObject calendarIcon        = findTestObject('Object Repository/Appointments/Calendar/Calender icon')                         // Calendar icon
TestObject reasonTextarea      = findTestObject(PORTAL_REPO + 'textarea_Reason for visit _')                                     // Reason-for-visit textarea
TestObject proceedBtn1         = findTestObject(PORTAL_REPO + 'button_Proceed')                                                  // Proceed after date/time
TestObject proceedBtn2         = findTestObject(PORTAL_REPO + 'button_Proceed_1')                                                // Proceed after reason

TestObject confirmationHeader  = findTestObject(PORTAL_REPO + 'h4_Appointment Confirmed')                                        // "Appointment Confirmed" header
TestObject appointmentCard     = findTestObject(PORTAL_REPO + 'div_Dr. Patient Portal9_55 PM Friday, Sep 25, 20')               // Confirmation card
TestObject apptSummaryContainer = findTestObject(PORTAL_REPO + 'div_09_25_2026 _ 09_55 PM _ Patient PortalFirst')                // Appointment summary container
TestObject appointmentsBtn     = findTestObject(PORTAL_REPO + 'button_Appointments')                                             // Appointments nav button
TestObject cancelBtn           = findTestObject(PORTAL_REPO + 'button_Cancel')                                                   // Cancel button
TestObject rescheduleBtn       = findTestObject(PORTAL_REPO + 'button_Reschedule')                                               // Reschedule button
TestObject cannotChangePopup   = findTestObject('Appointments/CancelResch App/Your Appointment cannot be canceledresche_popup text')  // "Cannot cancel/reschedule" popup text
TestObject apptDateTimeObj     = findTestObject('Appointments/CancelResch App/ApptDateTime')                                     // Appointment date/time element
TestObject okBtnOnPopup        = findTestObject('Appointments/CancelResch App/button_OK on can not cancel popup')               // OK button on the popup

TestObject busyIndicator       = findTestObject('Page_MaximEyes/Busy Indicator')                                                 // MaximEyes busy spinner
TestObject homeButton = findTestObject('Authorized Individual/Page_Patient Portal/HomeFromOA')
// ============================================================================
// REUSABLE HELPERS (closures replace the copy-pasted blocks)
// ============================================================================

// Logs in to MaximEyes through the shared test case
def loginMaximEyes = {
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE) // Run shared login
}

// Opens Office Admin > Modules > Schedule settings page
def openScheduleSettings = {
	WebUI.click(findTestObject(ME_REPO + 'a_Office Admin'))                                      // Open Office Admin menu
	WebUI.click(findTestObject(ME_REPO + 'a_Modules'))                                           // Open Modules submenu
	WebUI.click(findTestObject(ME_REPO + 'a_ui-id-21'))                                          // Open Schedule settings tab
}

// Saves appointment settings with the given scheduling and cancelation hours
def applyScheduleSettings = { String schedulingHours, String cancelationHours ->
	CustomKeywords.'custom.AppointmentSettingsKeywords.setAppointmentSettings'(
		[
			"SCHEDULING_HOURS"              : schedulingHours,                                   // Min hours before an appointment can be booked
			"CANCELATION_HOURS"             : cancelationHours,                                  // Min hours before an appointment can be cancelled
			"DAILY_LIMIT_HOURS"             : "0",                                               // No daily limit
			"ApptLimitForOnlineAppointment" : "5"                                                // Max online appointments
		],
		[
			"idLimitOnlineEnable"                : false,                                        // Online limit checkbox off
			"idIsOnlineApptActivityReportEnable" : false,                                        // Activity report checkbox off
			"idIsEnableOnlineScheduleInsurance"  : false                                         // Insurance checkbox off
		]
	)
	WebUI.click(findTestObject(ME_REPO + 'a_Modules'))                                           // Click Modules to leave/save the page
	WebUI.waitForElementNotVisible(busyIndicator, BUSY_TIMEOUT)                                  // Wait until saving finishes
}

// Reads the OTP from Gmail, types it into the four boxes and clicks Proceed
def enterOtpAndProceed = {
	String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
		OTP_IMAP_SERVER, GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, GlobalVariable.Sender_Email, OTP_SEARCH_TERM) // Fetch OTP from email
	println("OTP fetched = " + otp)                                                              // Log the OTP
	otp.toList().eachWithIndex { String digit, int i ->                                          // Loop through each digit with its index
		WebUI.setText(otpBoxes[i], digit)                                                        // Type the digit into the matching box
	}
	WebUI.waitForElementClickable(proceedBtnAfterOTP, CLICKABLE_TIMEOUT, FailureHandling.STOP_ON_FAILURE) // Wait for Proceed to enable (replaces fixed delay)
	WebUI.click(proceedBtnAfterOTP, FailureHandling.STOP_ON_FAILURE)                             // Click Proceed
}

// Clicks Cancel/Reschedule on a card, then answers the confirmation prompt
def clickActionAndAnswer = { int cardIndex, String action, String answer ->
	Map r = CustomKeywords.'custom.AppointmentActions.clickActionAndVerifyPrompt'(appointmentCards, cardIndex, action) // Click action and verify prompt
	CustomKeywords.'custom.AppointmentActions.respondToConfirmation'(answer, action)             // Answer Yes/No on the prompt
	return r                                                                                     // Return date/time of the card acted on
}

// Verifies the "cannot cancel/reschedule online" popup text and closes it
def verifyPopupAndClose = { String expected ->
	String actual = WebUI.getText(cannotChangePopup).replaceAll("\\s+", " ").trim()             // Read popup text and normalise whitespace
	WebUI.verifyMatch(actual, expected, false)                                                   // Compare with expected text (no regex)
	WebUI.click(okBtnOnPopup)                                                                    // Close the popup
}

// Books one appointment. The time is a PREFERENCE; returns the time actually booked
def requestAppointment = { String apptDate, String preferredTime, List<String> usedTimes, String fromDate, String toDate ->
	WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)                            // Choose location
	WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)                            // Choose provider
	WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)                                // Choose reason
	WebUI.waitForElementClickable(proceedApptBtn, CLICKABLE_TIMEOUT)                             // Wait for Proceed (replaces fixed delay)
	WebElement proceedElement = WebUI.findWebElement(proceedApptBtn, ELEMENT_TIMEOUT)            // Locate the Proceed element
	WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proceedElement))              // JS click avoids overlay interception
	WebUI.waitForElementClickable(calendarIcon, CLICKABLE_TIMEOUT)                               // Wait for the next page to load
	WebUI.click(calendarIcon)                                                                    // Open the calendar picker
	WebUI.comment("Range: ${fromDate} - ${toDate} | Date: ${apptDate} | Preferred: ${preferredTime}") // Log booking inputs
	String bookedTime = CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
		fromDate, toDate, apptDate, preferredTime, usedTimes)                                    // Pick date; preferred time or first free slot not used
	WebUI.comment('Time actually booked: ' + bookedTime)                                         // Log the real booked time
	WebUI.click(proceedBtn1)                                                                     // Go to reason-for-visit page
	WebUI.waitForElementVisible(reasonTextarea, PAGE_LOAD_TIMEOUT)                               // Wait for textarea instead of full page load
	WebUI.setText(reasonTextarea, REASON_FOR_VISIT)                                              // Enter the reason for visit
	WebUI.click(proceedBtn2)                                                                     // Submit the request
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                                     // Wait for confirmation page
	return bookedTime                                                                            // Hand back the booked time
}

// ============================================================================
// TEST EXECUTION
// ============================================================================
try {

	// ---------- SETUP IN MAXIMEYES ----------
	loginMaximEyes()                                                                             // Log in to MaximEyes

	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
		[('phoneNumber'): GlobalVariable.Mobile, ('emailId'): GlobalVariable.MyEmail_Id],
		FailureHandling.STOP_ON_FAILURE)                                                         // Create a random patient with our phone/email

	TestObject patientIdObj = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen') // Patient ID field
	WebUI.waitForElementVisible(patientIdObj, 15)                                                // Wait for the overview screen
	GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''                // Store patient ID (used by cleanup)
	println "✅ Patient ID stored: " + GlobalVariable.GV_PatientID                               // Log the patient ID

	WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))      // Click "+" for portal sign up
	WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))               // Choose "Send Sign Up Email to"
	WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c')) // Click Proceed
	WebUI.waitForElementNotVisible(busyIndicator, 30)                                            // Wait for spinner to disappear
	WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), 'Patient Portal Sign Up Completed. Email Sent.') // Verify toast

	WebUI.delay(EMAIL_WAIT)                                                                      // Give the sign-up email time to arrive

	CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
		GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, GlobalVariable.Sender_Email, CREDENTIAL_SUBJECT) // Read username/password from email
	println "Username: " + GlobalVariable.GV_Username                                            // Log username
	println "Password: " + GlobalVariable.GV_Password                                            // Log password

	openScheduleSettings()                                                                       // Go to Office Admin > Modules > Schedule
	applyScheduleSettings("2", "1")                                                              // Scheduling 2h, cancelation 1h

	// ---------- PATIENT PORTAL LOGIN ----------
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE) // Open the portal
	WebUI.click(signInButton)                                                                    // Open the sign-in form

	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
		[('Username'): GlobalVariable.GV_Username, ('Password'): GlobalVariable.GV_Password],
		FailureHandling.STOP_ON_FAILURE)                                                         // First login with emailed credentials

	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                                     // Wait for next screen (replaces fixed delay)
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE) // Confirm DOB, accept terms

	enterOtpAndProceed()                                                                         // First OTP verification

	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE) // Change the temporary password

	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
		[('Username'): GlobalVariable.GV_Username, ('Password'): GlobalVariable.UpdatePassword],
		FailureHandling.STOP_ON_FAILURE)                                                         // Login again with the new password

	WebUI.waitForElementVisible(otpBoxes[0], PAGE_LOAD_TIMEOUT)                                  // Wait for OTP screen (replaces fixed delay)
	enterOtpAndProceed()                                                                         // Second OTP verification

	WebUI.waitForElementClickable(dashboardMenuBtn, PAGE_LOAD_TIMEOUT)                           // Wait for dashboard (replaces 10s delay)

	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'),
		[('Firstname'): GlobalVariable.PatientFirstName, ('Lastname'): GlobalVariable.PatientLastName],
		FailureHandling.STOP_ON_FAILURE)                                                         // Verify name/date/time on dashboard

	// ---------- VERIFY EMPTY STATE ----------
	WebUI.click(dashboardMenuBtn)                                                                // Open dashboard menu
	WebUI.assertElementPresent(upcomingApptsHeader, ELEMENT_TIMEOUT)                             // "Upcoming Appointments" header exists
	WebUI.assertElementText(noApptsMessage, 'No appointments found for selected period', ELEMENT_TIMEOUT) // Empty-state text 1
	WebUI.assertElementText(noUpcomingApptsMsg, 'No upcoming appointments', ELEMENT_TIMEOUT)     // Empty-state text 2
	WebUI.assertElementPresent(requestNewApptBtn, ELEMENT_TIMEOUT)                               // Request button exists
	WebUI.click(requestNewApptBtn)                                                               // Open the request form

	// ---------- VERIFY REQUEST FORM ----------
	WebUI.assertElementText(requestApptTitle, 'Request Appointment', ELEMENT_TIMEOUT)            // Form title check
	String expectedEmergencyNote = 'Note:If this is a medical emergency, please dial ' + EMERGENCY_PHONE +
		' immediately or go to the nearest emergency room. If experiencing flashes, floaters, or sudden loss of vision please call the office immediately at ' +
		OFFICE_PHONE                                                                             // Build expected note text
	WebUI.assertElementText(emergencyNote, expectedEmergencyNote, ELEMENT_TIMEOUT)               // Compare with UI text

	// ---------- CALCULATE DATES ----------
	LocalDate today = LocalDate.now()                                                            // Today's date
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)                       // Date formatter
	String fromDate         = today.format(formatter)                                            // Range start = today
	String toDate           = today.plusDays(TOTAL_DAYS_RANGE).format(formatter)                 // Range end
	String appointmentDate  = today.plusDays(APPOINTMENT_DAYS_AHEAD).format(formatter)           // Date for appointments 1 and 2
	String appointmentDate1 = today.plusDays(APPOINTMENT_DAYS_AHEAD1).format(formatter)          // Date for appointment 3 (and reschedule)
	String appointmentDate2 = today.plusDays(APPOINTMENT_DAYS_AHEAD2).format(formatter)          // Date for appointment 4

	List<String> usedTimes = []                                                                  // Times already booked in this run

	// ---------- BOOK 1ST APPOINTMENT ----------
	String bookedTime1 = requestAppointment(appointmentDate, APPOINTMENT_TIME, usedTimes, fromDate, toDate) // Book appointment 1
	usedTimes << bookedTime1                                                                     // Remember its time

	// ---------- REMINDER EMAIL ----------
	String patientName = GlobalVariable.PatientFirstName + " " + GlobalVariable.PatientLastName  // Patient name used to match emails
	String cancelLink = CustomKeywords.'email.GmailAppointmentReminder.getCancelRescheduleLink'(
		GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, patientName)                        // Extract cancel/reschedule link from email
	WebUI.comment("Cancel Link : " + cancelLink)                                                 // Log the link

	// ---------- VERIFY CONFIRMATION ----------
	WebUI.assertElementText(confirmationHeader, 'Appointment Confirmed', ELEMENT_TIMEOUT)        // Confirmation header check
	CustomKeywords.'custom.ApptDateTimeVerification.verifyAppointmentCard'(
		appointmentCard, 'Dr.  Patient  Portal', appointmentDate, bookedTime1)                   // Verify provider, date and booked time

	WebUI.click(appointmentsBtn)                                                                 // Open appointments list
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                                     // Wait for page load

	CustomKeywords.'custom.ApptDateTimeVerification.verifyAppointmentSummary'(
		apptSummaryContainer, appointmentDate, bookedTime1, 'Patient Portal', 'First Insight Vision', 'Confirmed') // Verify summary details

	WebUI.assertElementPresent(cancelBtn, ELEMENT_TIMEOUT)                                       // Cancel button exists
	WebUI.assertElementPresent(rescheduleBtn, ELEMENT_TIMEOUT)                                   // Reschedule button exists

	// ---------- BOOK 2ND, 3RD, 4TH APPOINTMENTS ----------
	[
		[appointmentDate,  APPOINTMENT_TIME1],                                                   // 2nd: same day, second slot
		[appointmentDate1, APPOINTMENT_TIME],                                                    // 3rd: later day, first slot
		[appointmentDate2, APPOINTMENT_TIME1]                                                    // 4th: latest day, second slot
	].each { String apptDate, String preferred ->                                                // Loop instead of three copy-pasted blocks
		WebUI.click(requestNewApptPlus)                                                          // Click "+" to add another appointment
		usedTimes << requestAppointment(apptDate, preferred, usedTimes, fromDate, toDate)        // Book and remember the real time
		WebUI.click(appointmentsBtn)                                                             // Back to appointments list
		WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                                 // Wait for page load
	}
	WebUI.comment('Booked times: ' + usedTimes)                                                  // Log all booked times

	// ---------- VERIFY ORDER AND STATUS ----------
	CustomKeywords.'custom.AppointmentSequence.verifyUpcomingAppointmentsChronologicalOrder'(appointmentCards) // Cards must be chronological
	CustomKeywords.'custom.AppointmentActions.verifyAppointmentStatus'(appointmentCards, 1, 'Confirmed')       // Card 1 is Confirmed
	CustomKeywords.'custom.AppointmentActions.verifyAppointmentStatus'(appointmentCards, 2, 'Confirmed')       // Card 2 is Confirmed

	// ---------- CANCEL FLOW (prompt: No, then Yes) ----------
	clickActionAndAnswer(1, 'Cancel', 'No')                                                      // Cancel, then decline the prompt
	Map res = clickActionAndAnswer(1, 'Cancel', 'Yes')                                           // Cancel, then confirm the prompt
	println "Cancelled appointment on ${res.date} at ${res.time}"                                // Log what was cancelled

	String formattedDate = CustomKeywords.'email.GmailAppointmentCancellation.convertAppointmentDate'(res.date + " " + res.time) // Format date for email search
	println("✅ Formatted Date: ${formattedDate}")                                              // Log the formatted date

	boolean emailReceived = CustomKeywords.'email.GmailAppointmentCancellation.verifyAppointmentCancellationEmail'(
		GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, patientName, formattedDate)         // Check cancellation email arrived
	assert emailReceived                                                                         // Fail if the email is missing

	WebUI.click(appointmentsBtn)                                                                 // Back to appointments list
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                                     // Wait for page load

	// ---------- TIGHTEN CANCELATION WINDOW IN 2ND TAB ----------
	int cancellationBufferHours = CustomKeywords.'custom.AppointmentCancellationKeywords.getCancellationBufferHours'(apptDateTimeObj) // Hours buffer based on appt time (EST)
	println("Cancellation Buffer Hours: ${cancellationBufferHours}")                             // Log buffer hours

	WebUI.executeJavaScript("window.open('about:blank','_blank');", [])                          // Open a new browser tab
	WebUI.switchToWindowIndex(1)                                                                 // Switch to the new tab
	loginMaximEyes()                                                                             // Log in to MaximEyes in tab 2
	openScheduleSettings()                                                                       // Go to Schedule settings
	applyScheduleSettings("12", String.valueOf(cancellationBufferHours))                         // Block online cancellation for this appointment
	WebUI.switchToWindowIndex(0)                                                                 // Back to the portal tab

	// ---------- CANNOT CANCEL / RESCHEDULE POPUPS ----------
	clickActionAndAnswer(1, 'Cancel', 'Yes')                                                     // Try to cancel
	verifyPopupAndClose(CANCEL_POPUP_TEXT)                                                       // Verify and close the popup

	clickActionAndAnswer(1, 'Reschedule', 'No')                                                  // Reschedule, decline the prompt
	clickActionAndAnswer(1, 'Reschedule', 'Yes')                                                 // Reschedule, confirm the prompt
	verifyPopupAndClose(RESCH_POPUP_TEXT)                                                        // Verify and close the popup

	// ---------- RELAX WINDOW AGAIN, THEN RESCHEDULE ----------
	WebUI.switchToWindowIndex(1)                                                                 // Switch to MaximEyes tab
	WebUI.click(findTestObject(ME_REPO + 'a_ui-id-21'))                                          // Re-open Schedule settings
	applyScheduleSettings("12", "1")                                                             // Restore 1h cancelation window
	WebUI.switchToWindowIndex(0)                                                                 // Back to the portal tab

	clickActionAndAnswer(1, 'Reschedule', 'Yes')                                                 // Reschedule now succeeds
	String bookedTimeRe = requestAppointment(appointmentDate1, APPOINTMENT_TIME, usedTimes, fromDate, toDate) // Pick the new slot
	usedTimes << bookedTimeRe                                                                    // Remember the new time

	String reschFormattedDate = CustomKeywords.'email.GmailAppointmentCancellation.convertAppointmentDate'(appointmentDate1 + " " + bookedTimeRe) // Format new date for email search
	println("✅ Formatted Date: ${reschFormattedDate}")                                         // Log the formatted date

	
	WebUI.delay(10)
	
	Map<String, String> links = CustomKeywords.'email.GmailAppointmentReschedule.getRescheduleAppointmentLinks'(
		GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, patientName, reschFormattedDate)    // Get links from the reschedule email
	assert links != null

String cancelRescheduleLink = links.cancelRescheduleUrl
String intakeFormLink = links.intakeFormUrl                                                                       // Fail if no email/links found

KeywordUtil.logInfo("Cancel/Reschedule URL: ${cancelRescheduleLink}")
KeywordUtil.logInfo("Intake Form URL: ${intakeFormLink}")                             // Log intake form link
	WebUI.comment('✓ Test Completed Successfully: Appointments created and verified')           // Final success log

} catch (Throwable t) {
	WebUI.comment('✗ Test failed: ' + t.toString())                                              // Log the failure reason
	throw t                                                                                      // Rethrow so the test is marked FAILED
} finally {
	try {
		WebUI.comment('Starting cleanup in finally block')                                       // Log cleanup start
		if (GlobalVariable.GV_PatientID) {  
			WebUI.switchToWindowIndex(1)                                                       // Only clean up if a patient was created
			WebUI.click(homeButton)             
			WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)                                                           // Log in to MaximEyes
			WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
				[('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.OPTIONAL)          // Open the patient by ID
			CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Schedule', SubItem: 'Schedule']) // Go to Schedule
			CustomKeywords.'custom.CancelAppointmentKeywords.cancelAllAppointments'()            // Cancel every appointment for the patient
		}
	} catch (Throwable cleanupError) {
		WebUI.comment('Cleanup failed: ' + cleanupError.toString())                              // Never let cleanup mask the real failure
	}
}