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
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
//import gmail.GmailAppointmentReminder as email
import com.kms.katalon.core.util.KeywordUtil


// Calendar Configuration
int TOTAL_DAYS_RANGE = 15
int APPOINTMENT_DAYS_AHEAD = 0
int APPOINTMENT_DAYS_AHEAD1 = 5
int APPOINTMENT_DAYS_AHEAD2 = 8

String DATE_FORMAT = 'MM/dd/yyyy'

// Appointment Data
String LOCATION_NAME  = 'Patient Portal - Bellingham, WA'
String PROVIDER_NAME = 'Patient Portal'
String REASON_NAME = 'Patient Portal'
String REASON_FOR_VISIT = 'Patient Portal Appt Reason'
String APPOINTMENT_TIME = '11:50 AM'

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
TestObject canceledAppConifrmText = findTestObject('Appointments/CancelResch App/Your Appointment is canceled Page')
TestObject scheduleNewApptText = findTestObject('Appointments/CancelResch App/Schedule New Appointment')
TestObject scheduleNewProccedBtn = findTestObject('Appointments/CancelResch App/button_Proceed on cancelled appt page')
TestObject cannotCancelApptPopu = findTestObject('Appointments/CancelResch App/Your Appointment cannot be canceledresche_popup text')
TestObject apptDateTime = findTestObject('Appointments/CancelResch App/ApptDateTime')
TestObject okBtnOnCanNotCancelApptPopup = findTestObject('Appointments/CancelResch App/button_OK on can not cancel popup')
TestObject ApptNoLongerAvailablePopup = findTestObject('Appointments/Page_Patient Portal/The appointment datetime you selected is no longer available. Please choose a different datetime for your appointment')
TestObject okBtnOnApptNoLongerAvailablePopup = findTestObject('Appointments/Page_Patient Portal/button_OK_Appt_no longer available')

TestObject homeButton = findTestObject('Authorized Individual/Page_Patient Portal/HomeFromOA')


def requestAppointment = { String apptDate, String preferredTime, List<String> usedTimes, String fromDate, String toDate ->
	
	WebUI.comment('Select appointment date and preferred time (fallback to first available)') // Log step
	String bookedTime = CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
		fromDate,                                                                  // Start of the selectable range
		toDate,                                                                    // End of the selectable range
		apptDate,                                                                  // Date to pick
		preferredTime,                                                             // Preferred time slot
		usedTimes                                                                  // Times already booked in this test (skipped)
	)

	return bookedTime                                                              // Hand back the time that was really booked
}

try {
	

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


//===============================

// Open new tab
WebUI.executeJavaScript("window.open('about:blank','_blank');", [])

// Switch to 2nd tab
WebUI.switchToWindowIndex(1)

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

WebUI.delay(5)                                                     // Wait for OTP screen to load

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
                

WebUI.comment('Step 12: Click on "Request New Appointment" button')                // Log step
WebUI.click(requestNewAppointmentBtn)                                              // Open the request form



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

WebUI.comment('Select Location from dropdown')                                 // Log step
		WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)              // Choose location by visible label
 
		WebUI.comment('Select Provider from dropdown')                                 // Log step
		WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)              // Choose provider by visible label
 
		WebUI.comment('Select Reason from dropdown')                                   // Log step
		WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)                  // Choose reason by visible label
 
		WebUI.delay(5)                                      // Wait for the UI to settle after selection
 
		WebUI.comment('Click Proceed to continue to date/time selection')              // Log step
		WebElement proceedElement = WebUI.findWebElement(proceedApptBtn, 10)           // Locate the Proceed button (10s timeout)
		WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proceedElement)) // JS click (avoids overlay/intercept issues)
 
		                                       // Wait for the next page to load
 
		WebUI.comment('Open calendar date picker')                                     // Log step
		WebUI.click(calendarIcon)                                                      // Open the calendar
 
 
	// ---------- STEP 12: Book 1st appointment ----------
	WebUI.comment('Step 16: Book 1st appointment')                                     // Log step
	String bookedTime1 = requestAppointment(appointmentDate, APPOINTMENT_TIME, usedTimes, fromDate, toDate) // Run the booking flow
	usedTimes << bookedTime1   
 
		WebUI.comment('Time actually booked: ' + bookedTime1)                           // Log the real booked time
 
		WebUI.comment('Click Proceed to go to reason for visit page')                  // Log step
		WebUI.click(proceedBtn1)                                                       // Move to the reason-for-visit page
		WebUI.waitForPageLoad(5)                                       // Wait for page load
		
		
		// Switch to default window
		WebUI.switchToWindowIndex(0)
		
		// Navigate to Schedule
		CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Schedule', SubItem: 'Schedule'])
		
		CustomKeywords.'custom.MaximeyesAppointment.bookAppointmentAtTime'(
			bookedTime1
		)
		
		
		// Switch to 1st window
		WebUI.switchToWindowIndex(1)
		WebUI.comment('Enter reason for visit')                                        // Log step
		WebUI.setText(reasonTextarea, REASON_FOR_VISIT)                                // Type the reason into the textarea
 
		WebUI.comment('Click Proceed to submit appointment request')                   // Log step
		WebUI.click(proceedBtn2)
		
		WebUI.waitForElementVisible(ApptNoLongerAvailablePopup, 5)
		WebUI.assertElementText(ApptNoLongerAvailablePopup, 'The appointment date/time you selected is no longer available. Please choose a different date/time for your appointment.', 10)
		WebUI.click(okBtnOnApptNoLongerAvailablePopup)
		
}
catch (Throwable t) {
	WebUI.comment('✗ Test failed: ' + t.toString())                                              // Log the failure reason
	throw t                                                                                      // Rethrow so the test is marked FAILED
}
		finally {
			try {
				WebUI.comment('Starting cleanup in finally block')                                       // Log cleanup start
				if (GlobalVariable.GV_PatientID) {
					WebUI.switchToWindowIndex(0)                                                       // Only clean up if a patient was created
					                                                           // Log in to MaximEyes
					WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
						[('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.OPTIONAL)          // Open the patient by ID
					CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Schedule', SubItem: 'Schedule']) // Go to Schedule
					CustomKeywords.'custom.CancelAppointmentKeywords.cancelAllAppointments'()            // Cancel every appointment for the patient
				}
			} catch (Throwable cleanupError) {
				WebUI.comment('Cleanup failed: ' + cleanupError.toString())                              // Never let cleanup mask the real failure
			}
		}