// ---------------------------------------------------------------
// Imports (only the ones actually used in this script)
// ---------------------------------------------------------------
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase // Helper to locate a test case
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject // Helper to locate a test object
import com.kms.katalon.core.model.FailureHandling as FailureHandling // Failure handling options
import com.kms.katalon.core.testobject.TestObject as TestObject // TestObject type
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI // Built-in web keywords
import internal.GlobalVariable as GlobalVariable // Global variables

// ---------------------------------------------------------------
// Hard-coded values moved into variables
// ---------------------------------------------------------------

// ---- Common test cases ----
final String TC_LOGIN_ADMIN      = 'Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal' // Admin login test case
final String TC_CREATE_PATIENT   = 'Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes' // Create patient test case
final String TC_LOGIN_PATIENT    = 'Test Cases/common/Patient_Portal_Common/User Login With Username and Password' // Patient login test case
final String TC_DOB_TERMS        = 'Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms' // DOB + terms test case
final String TC_VERIFY_DASHBOARD = 'Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard' // Dashboard verification test case

// ---- Object repository: MaximEyes (staff side) ----
final String OBJ_SIGNUP_PP_BTN       = 'Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp' // Sign up for patient portal button
final String OBJ_SEND_SIGNUP_EMAIL   = 'Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons' // Send sign-up email option
final String OBJ_SIGNUP_PROCEED_BTN  = 'Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c' // Proceed on email dialog
final String OBJ_BUSY_INDICATOR      = 'Page_MaximEyes/Busy Indicator' // Loading spinner
final String OBJ_TOAST_MSG           = 'Object Repository/Page_MaximEyes/Toast Msg' // Toast message
final String OBJ_RESET_PP_BTN        = 'Object Repository/Page_MaximEyes/Patient_Overview/span_Patient Portal_PtOverviewResetPatientToPortalPP' // Reset portal button
final String OBJ_RESET_SEND_EMAIL    = 'Object Repository/Page_MaximEyes/Patient_Overview/span_Reset password and send email to_icons' // Reset password and send email option
final String OBJ_RESET_PROCEED_BTN   = 'Object Repository/Page_MaximEyes/Patient_Overview/input_Edit Email Address_btnproceed' // Proceed on reset email dialog

// ---- Object repository: Patient Portal ----
final String OBJ_NEW_PASSWORD        = 'Object Repository/PatientPortal/Page_Patient Portal/Rest Password' // New password field
final String OBJ_CONFIRM_PASSWORD    = 'Object Repository/PatientPortal/Page_Patient Portal/Confirm Rest Password' // Confirm password field
final String OBJ_CONFIRM_BTN         = 'Object Repository/PatientPortal/Page_Patient Portal/Confirm Btn' // Confirm button
final String OBJ_OTP_PROCEED_BTN     = 'Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification' // Proceed after OTP
final List<String> OBJ_OTP_FIELDS = [ // The four OTP input boxes, in order
	'Object Repository/PatientPortal/SignInPage_Patient Portal/otp1',
	'Object Repository/PatientPortal/SignInPage_Patient Portal/otp2',
	'Object Repository/PatientPortal/SignInPage_Patient Portal/otp3',
	'Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'
]

// ---- Custom keywords ----
final String IMAP_HOST = 'imap.gmail.com' // Gmail IMAP server

// ---- Email subjects ----
final String SUBJECT_SIGNUP = 'Access to your health data'         // Sign-up email subject
final String SUBJECT_RESET  = 'Reset Password for Patient portal'  // Reset-password email subject
final String SUBJECT_OTP    = 'Verification'                       // OTP email subject

// ---- Timeouts (seconds) ----
final int TIMEOUT_BUSY      = 30 // Max wait for busy indicator to disappear
final int TIMEOUT_CLICKABLE = 15 // Max wait for button to become clickable
final int DELAY_SHORT       = 5  // Short static delay (email delivery / UI settle)
final int DELAY_LONG        = 10 // Long static delay (dashboard load)

// ---------------------------------------------------------------
// Test steps
// ---------------------------------------------------------------

// Log in to MaximEyes as staff user
WebUI.callTestCase(findTestCase(TC_LOGIN_ADMIN), [:], FailureHandling.STOP_ON_FAILURE)

// Create a random patient with the configured phone and email
WebUI.callTestCase(
	findTestCase(TC_CREATE_PATIENT), // Test case to call
	[
		('phoneNumber') : GlobalVariable.Mobile,      // Patient phone number
		('emailId')     : GlobalVariable.MyEmail_Id   // Patient email address
	],
	FailureHandling.STOP_ON_FAILURE // Stop if patient creation fails
)

// Click "Sign up for Patient Portal"
WebUI.click(findTestObject(OBJ_SIGNUP_PP_BTN))

// Choose "Send Sign Up Email"
WebUI.click(findTestObject(OBJ_SEND_SIGNUP_EMAIL))

// Confirm the email address and proceed
WebUI.click(findTestObject(OBJ_SIGNUP_PROCEED_BTN))

// Wait for the busy indicator to disappear
WebUI.waitForElementNotVisible(findTestObject(OBJ_BUSY_INDICATOR), TIMEOUT_BUSY)

// Verify the sign-up toast message (SignUp_Email_Toast is a test case variable)
WebUI.verifyElementText(findTestObject(OBJ_TOAST_MSG), SignUp_Email_Toast)

// Wait for the sign-up email to arrive
WebUI.delay(DELAY_SHORT)

// Read the sign-up email and extract the username and password
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,  // Mailbox to read
	GlobalVariable.Email_Key,   // Mailbox app key
	GlobalVariable.Sender_Email, // Expected sender
	SUBJECT_SIGNUP              // Expected subject
)

// Log the extracted username
println "Username: " + GlobalVariable.GV_Username

// Log the extracted password
println "Password: " + GlobalVariable.GV_Password

// Short wait before starting the reset flow
WebUI.delay(DELAY_SHORT)

// Click "Reset patient to portal"
WebUI.click(findTestObject(OBJ_RESET_PP_BTN))

// Choose "Reset password and send email"
WebUI.click(findTestObject(OBJ_RESET_SEND_EMAIL))

// Confirm the email address and proceed
WebUI.click(findTestObject(OBJ_RESET_PROCEED_BTN))

// Wait for the reset email to arrive
WebUI.delay(DELAY_SHORT)

// Read the reset email and store the reset link in GlobalVariable.ResetPasswordURL
CustomKeywords.'email.ResetPasswordLinkReader.fetchResetLink'(
	IMAP_HOST,                   // IMAP server
	GlobalVariable.MyEmail_Id,   // Mailbox to read
	GlobalVariable.Email_Key,    // Mailbox app key
	GlobalVariable.Sender_Email, // Expected sender
	SUBJECT_RESET                // Expected subject
)

// Open the reset password link
WebUI.navigateToUrl(GlobalVariable.ResetPasswordURL)

// Verify the password criteria rules on the page
CustomKeywords.'common.PasswordKeywords.verifyPasswordCriteria'()

// Enter the new password
WebUI.setText(findTestObject(OBJ_NEW_PASSWORD), GlobalVariable.RestUpdatedPass)

// Re-enter the new password to confirm
WebUI.setText(findTestObject(OBJ_CONFIRM_PASSWORD), GlobalVariable.RestUpdatedPass)

// Submit the new password
WebUI.click(findTestObject(OBJ_CONFIRM_BTN))

// Log in to the patient portal with the new password
WebUI.callTestCase(
	findTestCase(TC_LOGIN_PATIENT), // Test case to call
	[
		('Username') : GlobalVariable.GV_Username,       // Username from sign-up email
		('Password') : GlobalVariable.RestUpdatedPass    // Newly set password
	],
	FailureHandling.STOP_ON_FAILURE // Stop if login fails
)

// Confirm date of birth and accept terms
WebUI.callTestCase(findTestCase(TC_DOB_TERMS), [:], FailureHandling.STOP_ON_FAILURE)

// Fetch the OTP from the verification email
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	IMAP_HOST,                   // IMAP server
	GlobalVariable.MyEmail_Id,   // Mailbox to read
	GlobalVariable.Email_Key,    // Mailbox app key
	GlobalVariable.Sender_Email, // Expected sender
	SUBJECT_OTP                  // Expected subject
)

// Log the fetched OTP
println("OTP fetched = " + otp)

// Split the OTP into single characters
char[] digits = otp.toCharArray()

// Type each digit into its matching OTP input box
OBJ_OTP_FIELDS.eachWithIndex { String path, int i ->
	WebUI.setText(findTestObject(path), digits[i].toString()) // Enter digit i into box i
}

// Short wait for the UI to enable the proceed button
WebUI.delay(DELAY_SHORT)

// Get the proceed button object
TestObject proceedBtn = findTestObject(OBJ_OTP_PROCEED_BTN)

// Wait until the proceed button is visible and enabled
WebUI.waitForElementClickable(proceedBtn, TIMEOUT_CLICKABLE, FailureHandling.STOP_ON_FAILURE)

// Click the proceed button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

// Wait for the dashboard to load
WebUI.delay(DELAY_LONG)

// Verify the date/time and patient name on the dashboard
WebUI.callTestCase(
	findTestCase(TC_VERIFY_DASHBOARD), // Test case to call
	[
		('Firstname') : GlobalVariable.PatientFirstName, // Expected first name
		('Lastname')  : GlobalVariable.PatientLastName   // Expected last name
	],
	FailureHandling.STOP_ON_FAILURE // Stop if verification fails
)

// Open new tab
WebUI.executeJavaScript("window.open('about:blank','_blank');", [])

// Switch to 2nd tab
WebUI.switchToWindowIndex(1)

// Open the reset password link
WebUI.navigateToUrl(GlobalVariable.ResetPasswordURL)

//Verify link expired text is displayed as we alredy clicked this url
CustomKeywords.'common.PasswordKeywords.verifyLinkExpiredMessage'()