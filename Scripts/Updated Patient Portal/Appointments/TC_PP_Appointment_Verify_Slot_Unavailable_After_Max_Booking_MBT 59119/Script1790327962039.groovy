import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/*
 * TEST: Patient Portal appointment slot conflict
 *
 * Flow:
 *   1. MaximEyes  : log in, create patient, send portal sign-up email
 *   2. Portal     : first login -> OTP -> change password -> second login -> OTP
 *   3. Portal     : pick a slot, stop BEFORE submitting
 *   4. MaximEyes  : book the same slot (creates the conflict)
 *   5. Portal     : submit -> verify "no longer available" popup
 *   6. Cleanup    : cancel all appointments for the test patient (always runs)
 */

// ============================================================================
// CONFIGURATION
// ============================================================================

// --- Timing -----------------------------------------------------------------
final int TIMEOUT_SEC          = 15   // Default explicit wait
final int BUSY_TIMEOUT_SEC     = 30   // Wait for MaximEyes busy spinner
final int EMAIL_ARRIVAL_DELAY  = 10   // Seconds for sign-up email to arrive
final int PAGE_LOAD_TIMEOUT    = 30

// --- Appointment data -------------------------------------------------------
final int    DATE_RANGE_DAYS         = 15
final int    APPOINTMENT_DAYS_AHEAD  = 0
final String DATE_FORMAT             = 'MM/dd/yyyy'
final String PREFERRED_TIME          = '11:50 AM'
final String LOCATION_NAME           = 'Patient Portal - Bellingham, WA'
final String PROVIDER_NAME           = 'Patient Portal'
final String REASON_NAME             = 'Patient Portal'
final String REASON_FOR_VISIT        = 'Patient Portal Appt Reason'

// --- Email subjects ---------------------------------------------------------
final String SIGNUP_EMAIL_SUBJECT = 'Access to your health data'
final String OTP_EMAIL_SUBJECT    = 'Verification'

// --- Expected messages ------------------------------------------------------
final String EXPECTED_SIGNUP_TOAST = 'Patient Portal Sign Up Completed. Email Sent.'
final String EXPECTED_SLOT_UNAVAILABLE_MESSAGE =
		'The appointment date/time you selected is no longer available. ' +
		'Please choose a different date/time for your appointment.'

// --- Browser tab indexes ----------------------------------------------------
final int MAXIMEYES_TAB = 0
final int PORTAL_TAB    = 1

// --- Common test case folders (avoids repeating long paths) -----------------
final String PORTAL_COMMON = 'Test Cases/common/Patient_Portal_Common/'
final String PORTAL_SIGNIN = 'Object Repository/PatientPortal/SignInPage_Patient Portal/'
final String PORTAL_APPT   = 'Appointments/PP Appointment/Page_Patient Portal/'
final String PORTAL_REPO = 'Appointments/PP Appointment/Page_Patient Portal/'                          // Common repository folder for portal objects
final String ME_REPO     = 'Appointments/Appt Type/Page_MaximEyes/'

// ============================================================================
// TEST OBJECTS (keep paths aligned with the Object Repository)
// ============================================================================

// --- MaximEyes --------------------------------------------------------------
TestObject patientIdField = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen')
TestObject portalSignUpButton = findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp')
TestObject sendSignUpEmailOption = findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons')
TestObject sendSignUpProceedButton = findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c')
TestObject busyIndicator = findTestObject('Page_MaximEyes/Busy Indicator')
TestObject signupToast = findTestObject('Object Repository/Page_MaximEyes/Toast Msg')

// --- Portal: sign-in and OTP ------------------------------------------------
TestObject signInButton = findTestObject(PORTAL_SIGNIN + 'SignInBtn')
TestObject proceedAfterOtpButton = findTestObject(PORTAL_SIGNIN + 'ProccedBtnAfterOTPVerification')
List<TestObject> otpInputs = (1..4).collect { findTestObject(PORTAL_SIGNIN + "otp${it}") }

// --- Portal: appointment request --------------------------------------------
TestObject dashboardMenuButton = findTestObject(PORTAL_APPT + 'div_dashboard-menu-icon-btn border-2 rounded-ful')
TestObject requestNewApptButton = findTestObject(PORTAL_APPT + 'button_Request New Appointment') // Also proceeds to time selection
TestObject locationDropdown = findTestObject(PORTAL_APPT + 'select_Select Location')
TestObject providerDropdown = findTestObject('Object Repository/' + PORTAL_APPT + 'Select Provider')
TestObject reasonDropdown = findTestObject(PORTAL_APPT + 'select_Select Reason')
TestObject calendarIcon = findTestObject('Object Repository/Appointments/Calendar/Calender icon')
TestObject reasonTextarea = findTestObject(PORTAL_APPT + 'textarea_Reason for visit _')
TestObject proceedToReasonButton = findTestObject(PORTAL_APPT + 'button_Proceed')
TestObject submitRequestButton = findTestObject(PORTAL_APPT + 'button_Proceed_1')
TestObject proceedBtn2 = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Proceed_1')
TestObject appointmentsBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/button_Appointments')
TestObject requestNewAppointmentPlusBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/Request New Appt Plus btn')
// --- Portal: slot conflict popup --------------------------------------------
TestObject slotUnavailablePopup = findTestObject('Appointments/Page_Patient Portal/The appointment datetime you selected is no longer available. Please choose a different datetime for your appointment')
TestObject slotUnavailableOkButton = findTestObject('Appointments/Page_Patient Portal/button_OK_Appt_no longer available')
TestObject homeButton = findTestObject('Authorized Individual/Page_Patient Portal/HomeFromOA')
TestObject okButtonOnLimitExceed = findTestObject('Appointments/PP Appointment/Page_Patient Portal/Ok Button On Exceeded daily limit')
// ============================================================================
// HELPERS
// ============================================================================

// Run a reusable Katalon test case; stops the test on failure.
def runCommon = { String name, Map params = [:] ->
	WebUI.callTestCase(findTestCase(PORTAL_COMMON + name), params, FailureHandling.STOP_ON_FAILURE)
}

// Wait until an element is clickable, then click it (more stable than a blind click).
def safeClick = { TestObject obj ->
	WebUI.waitForElementClickable(obj, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebUI.click(obj, FailureHandling.STOP_ON_FAILURE)
}

// Fetch the 4-digit OTP from Gmail, type one digit per box, and proceed.
// The OTP value is never written to the report.
def completeOtpVerification = {
	WebUI.waitForElementVisible(otpInputs[0], TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)

	String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
			'imap.gmail.com',
			GlobalVariable.MyEmail_Id,
			GlobalVariable.Email_Key,
			GlobalVariable.Sender_Email,
			OTP_EMAIL_SUBJECT)

	// Fail fast if the handler returned nothing or something unexpected
	if (otp == null || !(otp.trim() ==~ /\d{4}/)) {
		throw new IllegalStateException('OTP handler did not return a valid four-digit OTP.')
	}

	String digits = otp.trim()
	otpInputs.eachWithIndex { TestObject box, int i -> WebUI.setText(box, digits[i]) }

	safeClick(proceedAfterOtpButton)
}

// Select the preferred time (falls back to any free time inside the keyword).
def selectAppointmentTime = { String apptDate, List<String> usedTimes, String fromDate, String toDate ->
	return CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
			fromDate, toDate, apptDate, PREFERRED_TIME, usedTimes)
}

// Open Schedule screen in MaximEyes via the mega menu.
def openSchedule = {
	CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Schedule', SubItem: 'Schedule'])
}

// ============================================================================
// TEST FLOW
// ============================================================================

// Reset so cleanup can never target a patient from a previous run
GlobalVariable.GV_PatientID = ''

try {

	// ------------------------------------------------------------------------
	// 1. MaximEyes: login, create patient, send portal sign-up email
	// ------------------------------------------------------------------------
	WebUI.comment('Step 1-2: Log in to MaximEyes and create a random patient')

	runCommon('User Login in Maximeyes Pt Portal')
	runCommon('Create Random Patient in Maximeyes', [
			('phoneNumber'): GlobalVariable.Mobile,
			('emailId')    : GlobalVariable.MyEmail_Id
	])

	// Capture the new patient ID (needed for cleanup)
	WebUI.waitForElementVisible(patientIdField, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdField, 'value') ?: ''

	if (!GlobalVariable.GV_PatientID) {
		throw new IllegalStateException('Patient ID was not populated after patient creation.')
	}
	KeywordUtil.logInfo('Created patient ID: ' + GlobalVariable.GV_PatientID)

	// Send the sign-up email and confirm the toast
	safeClick(portalSignUpButton)
	safeClick(sendSignUpEmailOption)
	safeClick(sendSignUpProceedButton)

	WebUI.waitForElementNotVisible(busyIndicator, BUSY_TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyElementText(signupToast, EXPECTED_SIGNUP_TOAST, FailureHandling.STOP_ON_FAILURE)

	// Give the email time to arrive before reading credentials
	WebUI.delay(EMAIL_ARRIVAL_DELAY)

	CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
			GlobalVariable.MyEmail_Id,
			GlobalVariable.Email_Key,
			GlobalVariable.Sender_Email,
			SIGNUP_EMAIL_SUBJECT)

	if (!GlobalVariable.GV_Username || !GlobalVariable.GV_Password) {
		throw new IllegalStateException('Portal username/password could not be extracted from the sign-up email.')
	}
	KeywordUtil.logInfo('Patient Portal credentials extracted successfully.')
	
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
				"DAILY_LIMIT_HOURS"             : "2",                                               // No daily limit
				"ApptLimitForOnlineAppointment" : "5"                                                // Max online appointments
			],
			[
				"idLimitOnlineEnable"                : false,                                        // Online limit checkbox off
				"idIsOnlineApptActivityReportEnable" : false,                                        // Activity report checkbox off
				"idIsEnableOnlineScheduleInsurance"  : false                                         // Insurance checkbox off
			]
		)
		WebUI.click(findTestObject(ME_REPO + 'a_Modules'))                                           // Click Modules to leave/save the page
		WebUI.waitForElementNotVisible(busyIndicator, 30)                                  // Wait until saving finishes
	}
	
	openScheduleSettings()                                                                       // Go to Office Admin > Modules > Schedule
	applyScheduleSettings("2", "1")

	// ------------------------------------------------------------------------
	// 2. Portal: first login, OTP, password update, second login
	// ------------------------------------------------------------------------
	WebUI.comment('Step 3-4: Open Patient Portal in a new tab and sign in')

	WebUI.executeJavaScript("window.open('about:blank', '_blank');", [])
	WebUI.switchToWindowIndex(PORTAL_TAB)

	runCommon('Navigate to Patient Portal Site')
	safeClick(signInButton)

	// First login with the temporary password
	runCommon('User Login With Username and Password', [
			('Username'): GlobalVariable.GV_Username,
			('Password'): GlobalVariable.GV_Password
	])
	runCommon('DOB Confirmation and Accept Terms')
	completeOtpVerification()

	// Replace the temporary password, then log in again with the new one
	runCommon('Update Password')
	runCommon('User Login With Username and Password', [
			('Username'): GlobalVariable.GV_Username,
			('Password'): GlobalVariable.UpdatePassword
	])
	completeOtpVerification()

	runCommon('Verify Date Time and Patient name on Dashboard', [
			('Firstname'): GlobalVariable.PatientFirstName,
			('Lastname') : GlobalVariable.PatientLastName
	])

	// ------------------------------------------------------------------------
	// 3. Portal: choose a slot but do NOT submit yet
	// ------------------------------------------------------------------------
	WebUI.comment('Step 5: Start a new appointment request')

	safeClick(dashboardMenuButton)
	safeClick(requestNewApptButton)

	// Build the date window used by the time-selection keyword
	LocalDate today = LocalDate.now()
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_FORMAT)
	String fromDate        = today.format(fmt)
	String toDate          = today.plusDays(DATE_RANGE_DAYS).format(fmt)
	String appointmentDate = today.plusDays(APPOINTMENT_DAYS_AHEAD).format(fmt)

	WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)
	WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)
	WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)

	// JS click avoids the click-interception issue on this form
	WebUI.waitForElementClickable(requestNewApptButton, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebElement proceedElement = WebUI.findWebElement(requestNewApptButton, TIMEOUT_SEC)
	WebUI.executeJavaScript('arguments[0].click();', [proceedElement])

	safeClick(calendarIcon)

	List<String> usedTimes = []
	String bookedTime = selectAppointmentTime(appointmentDate, usedTimes, fromDate, toDate)

	if (!bookedTime) {
		throw new IllegalStateException('selectAppointmentDateTime() did not return a time.')
	}
	usedTimes << bookedTime
	WebUI.comment("Slot selected in portal: ${bookedTime}")

	safeClick(proceedToReasonButton)
	WebUI.waitForElementVisible(reasonTextarea, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)

	// ------------------------------------------------------------------------
	// 4. MaximEyes: take the same slot to create the conflict
	// ------------------------------------------------------------------------
	WebUI.comment("Step 6: Book ${bookedTime} in MaximEyes to create the conflict")

	WebUI.switchToWindowIndex(MAXIMEYES_TAB)
	
	WebUI.click(homeButton)
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	WebUI.callTestCase(
		findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
		[('PatientID'): GlobalVariable.GV_PatientID],
		FailureHandling.OPTIONAL)
	
	openSchedule()
	CustomKeywords.'custom.MaximeyesAppointment.bookAppointmentAtTime'(bookedTime)

	// ------------------------------------------------------------------------
	// 5. Portal: submit the now-stale slot and verify the popup
	// ------------------------------------------------------------------------
	WebUI.comment('Step 7: Submit portal request and verify the unavailable-slot message')

	WebUI.switchToWindowIndex(PORTAL_TAB)
	WebUI.setText(reasonTextarea, REASON_FOR_VISIT)
	safeClick(submitRequestButton)

	WebUI.waitForElementVisible(slotUnavailablePopup, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyElementText(slotUnavailablePopup, EXPECTED_SLOT_UNAVAILABLE_MESSAGE, FailureHandling.STOP_ON_FAILURE)
	safeClick(slotUnavailableOkButton)

	WebUI.comment('Verified: the selected appointment slot is no longer available.')
	
	
	// ---------- STEP 18: Book 2nd appointment ----------
	safeClick(calendarIcon)
	List<String> usedTimes2 = []
	String bookedTime2 = selectAppointmentTime(appointmentDate, usedTimes, fromDate, toDate)

	if (!bookedTime2) {
		throw new IllegalStateException('selectAppointmentDateTime() did not return a time.')
	}
	usedTimes2 << bookedTime2
	WebUI.comment("Slot selected in portal: ${bookedTime2}")

	safeClick(proceedToReasonButton)
	// Type the reason into the textarea
//	WebUI.setText(reasonTextarea, REASON_FOR_VISIT)       
	
	// Step 1: Verify maximum character message
	String expectedMessage = 'Maximum 1000 characters allowed'
	
	// Replace with the actual TestObject for the message
	String actualMessage = WebUI.getText(
		findTestObject('Appointments/PP Appointment/Page_Patient Portal/Reason Field_Maximum 1000 characters allowed')
	).trim()
	
	KeywordUtil.logInfo("Expected Message: ${expectedMessage}")
	KeywordUtil.logInfo("Actual Message  : ${actualMessage}")
	
	WebUI.verifyMatch(actualMessage, expectedMessage, false)
	
	
	// Step 2: Enter exactly 1000 characters
	String text1000 = 'A' * 1000
	
	WebUI.setText(reasonTextarea, text1000)
	
	String actualText = WebUI.getAttribute(reasonTextarea, 'value')
	
	KeywordUtil.logInfo("Entered Character Count: ${actualText.length()}")
	
	assert actualText.length() == 1000 :
		"Expected 1000 characters, but field contains ${actualText.length()}"
	
	
	// Step 3: Try entering 1001 characters
	String text1001 = 'A' * 1001
	
	WebUI.setText(reasonTextarea, text1001)
	
	String finalText = WebUI.getAttribute(reasonTextarea, 'value')
	
	KeywordUtil.logInfo("Character Count After Entering 1001: ${finalText.length()}")
	
	assert finalText.length() <= 1000 :
		"Field accepts more than 1000 characters. Actual count: ${finalText.length()}"
	
	KeywordUtil.logInfo('Reason for Visit maximum character limit verified successfully.')

	// Submit the request               
	WebUI.click(proceedBtn2)                                                       
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	WebUI.click(appointmentsBtn)
	
	
	WebUI.click(requestNewAppointmentPlusBtn)
	

   CustomKeywords.'custom.VerifyLocProvReason.reset'()
	
	WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)
	
List locations = CustomKeywords.'custom.VerifyLocProvReason.readPortalDropdown'('location-select')
List providers = CustomKeywords.'custom.VerifyLocProvReason.readPortalDropdown'('provider-select')
List reasons   = CustomKeywords.'custom.VerifyLocProvReason.readPortalDropdown'('reason-select')

	
	WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)
	WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)

	// JS click avoids the click-interception issue on this form
	WebUI.waitForElementClickable(requestNewApptButton, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebElement proceedElement1 = WebUI.findWebElement(requestNewApptButton, TIMEOUT_SEC)
	WebUI.executeJavaScript('arguments[0].click();', [proceedElement1])
	
	safeClick(calendarIcon)
	List<String> usedTimes3 = []
	String bookedTime3 = selectAppointmentTime(appointmentDate, usedTimes, fromDate, toDate)

	if (!bookedTime3) {
		throw new IllegalStateException('selectAppointmentDateTime() did not return a time.')
	}
	usedTimes3 << bookedTime3
	WebUI.comment("Slot selected in portal: ${bookedTime3}")

	safeClick(proceedToReasonButton)
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	

	
	// Get today's date in MM/dd/yyyy format
	String todayDate = LocalDate.now().format(
		DateTimeFormatter.ofPattern("MM/dd/yyyy")
	)
	
	// Expected popup message
	String expectedApptLimitMessage = "You have exceeded daily limit of appointments for ${todayDate}. Choose a different date."
	
	// Read actual popup text (replace with your popup TestObject)
	String actualApptLimiMessage = WebUI.getText(
		findTestObject('Appointments/PP Appointment/Page_Patient Portal/You have exceeded daily limit of appointm popup')
	).replaceAll("\\s+", " ").trim()
	
	// Log expected and actual messages
	KeywordUtil.logInfo("Expected: ${expectedApptLimitMessage}")
	KeywordUtil.logInfo("Actual  : ${actualApptLimiMessage}")
	
	// Verify popup text
	WebUI.verifyMatch(actualApptLimiMessage, expectedApptLimitMessage, false)
	
	
	WebUI.click(okButtonOnLimitExceed)
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	
	WebUI.switchToWindowIndex(MAXIMEYES_TAB)
	
	WebUI.click(findTestObject(ME_REPO + 'a_Office Admin'))
	
	WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Business Administration'))
	WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_ui-id-20'))
	
	CustomKeywords.'custom.VerifyLocProvReason.verify'('LOCATION', locations)
	
//	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-23'))
//	CustomKeywords.'custom.VerifyLocProvReason.verify'('PROVIDER', providers)
	
	WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/a_Modules'))
	
	WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/a_ui-id-21'))
	
	WebUI.click(findTestObject('Appointments/Appt Reason/Page_MaximEyes/a_Appointment Reasons'))
	CustomKeywords.'custom.VerifyLocProvReason.verify'('REASON', reasons)
	
	CustomKeywords.'custom.VerifyLocProvReason.assertNoFailures'()
	

} catch (Throwable testError) {
	// Log, then rethrow so Katalon still marks the test as failed
	WebUI.comment('Test failed: ' + testError.toString())
	throw testError

} finally {

	// ------------------------------------------------------------------------
	// 6. Cleanup: always cancel appointments created for the test patient
	// ------------------------------------------------------------------------
	try {
		if (GlobalVariable.GV_PatientID) {
			WebUI.comment('Cleanup: cancelling appointments for the test patient.')

			WebUI.switchToWindowIndex(MAXIMEYES_TAB)
			WebUI.click(homeButton)

			WebUI.callTestCase(
					findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
					[('PatientID'): GlobalVariable.GV_PatientID],
					FailureHandling.OPTIONAL)

			openSchedule()
			CustomKeywords.'custom.CancelAppointmentKeywords.cancelAllAppointments'()
		}
	} catch (Throwable cleanupError) {
		// Never mask the original test failure with a cleanup failure
		WebUI.comment('Cleanup failed: ' + cleanupError.toString())
	}
}
