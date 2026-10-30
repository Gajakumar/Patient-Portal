// ===================== IMPORTS (only what is used) =====================
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase // Find a test case by its path
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject // Find an object from Object Repository
import com.kms.katalon.core.model.FailureHandling as FailureHandling // Failure handling options (STOP_ON_FAILURE etc.)
import com.kms.katalon.core.testobject.TestObject as TestObject // TestObject type for element variables
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI // Web UI keywords (click, setText, wait...)
import internal.GlobalVariable as GlobalVariable // Access global profile variables

// ===================== CONSTANTS: TEST CASE PATHS =====================
final String COMMON = 'Test Cases/common/Patient_Portal_Common/' // Base folder of common test cases
final String TC_LOGIN_MAXIMEYES = COMMON + 'User Login in Maximeyes Pt Portal' // Login to MaximEyes
final String TC_CREATE_PATIENT = COMMON + 'Create Random Patient in Maximeyes' // Create a random patient
final String TC_LOGIN_PORTAL = COMMON + 'User Login With Username and Password' // Portal login with credentials
final String TC_DOB_TERMS = COMMON + 'DOB Confirmation and Accept Terms' // DOB confirmation and terms acceptance
final String TC_UPDATE_PASSWORD = COMMON + 'Update Password' // Update password flow (MBT 48416)
final String TC_VERIFY_DASHBOARD = COMMON + 'Verify Date Time and Patient name on Dashboard' // Dashboard verification
final String TC_VERIFY_SETTINGS = COMMON + 'Verify_Setting_Dropdown_Options' // Settings dropdown verification

// ===================== CONSTANTS: TEST OBJECTS (MaximEyes) =====================
final String OR_MAX = 'Object Repository/Page_MaximEyes/' // MaximEyes object folder
final String OR_MAX_PO = OR_MAX + 'Patient_Overview/' // MaximEyes Patient Overview folder
final TestObject SIGNUP_PP_BTN = findTestObject(OR_MAX + 'span_Patient Portal_ptoverviewsignupforpp') // Sign up for portal button
final TestObject SEND_SIGNUP_EMAIL = findTestObject(OR_MAX + 'span_Send Sign Up Email to_icons') // Send sign-up email option
final TestObject SIGNUP_PROCEED_BTN = findTestObject(OR_MAX + 'input_Edit Email Address_btnProceedSaveNewP_fc225c') // Proceed on email popup
final TestObject BUSY_INDICATOR = findTestObject('Page_MaximEyes/Busy Indicator') // Loading spinner
final TestObject TOAST_MSG = findTestObject(OR_MAX + 'Toast Msg') // Toast message element
final TestObject RESET_PORTAL_BTN = findTestObject(OR_MAX_PO + 'span_Patient Portal_PtOverviewResetPatientToPortalPP') // Reset portal button
final TestObject RESET_PWD_PRINT = findTestObject(OR_MAX_PO + 'span_Reset password and Print Instructions_icons') // Reset password option
final TestObject RESET_PROCEED_BTN = findTestObject(OR_MAX_PO + 'input_Edit Email Address_btnproceed') // Proceed on reset popup

// ===================== CONSTANTS: TEST OBJECTS (Patient Portal) =====================
final String OR_PORTAL = 'Object Repository/PatientPortal/SignInPage_Patient Portal/' // Portal sign-in object folder
final TestObject SIGN_IN_BTN = findTestObject(OR_PORTAL + 'SignInBtn') // Portal sign-in button
final TestObject PROCEED_BTN = findTestObject(OR_PORTAL + 'ProccedBtnAfterOTPVerification') // Proceed button after OTP
final List<TestObject> OTP_FIELDS = [ // The four OTP input boxes in order
	findTestObject(OR_PORTAL + 'otp1'), // OTP digit 1
	findTestObject(OR_PORTAL + 'otp2'), // OTP digit 2
	findTestObject(OR_PORTAL + 'otp3'), // OTP digit 3
	findTestObject(OR_PORTAL + 'otp4')  // OTP digit 4
]

// ===================== CONSTANTS: OTHER VALUES =====================
final String CRED_OBJECT = 'Object Repository/Page_MaximEyes/Patient_Overview/PortaliNvitaionUrlPassUsername' // Object holding invitation credentials
final String RESET_TOAST_TEXT = 'Reset Completed.' // Expected toast after password reset
final String IMAP_HOST = 'imap.gmail.com' // Mailbox IMAP server
final String OTP_SUBJECT = 'Verification' // Subject keyword of OTP email
final int BUSY_TIMEOUT_SIGNUP = 30 // Seconds to wait for spinner after sign-up email
final int BUSY_TIMEOUT_RESET = 10 // Seconds to wait for spinner after reset
final int CLICK_TIMEOUT = 15 // Seconds to wait for element to be clickable
final int OTP_PAGE_TIMEOUT = 15 // Seconds to wait for OTP page to appear
final FailureHandling STOP = FailureHandling.STOP_ON_FAILURE // Shortcut: stop test on any failure

// ===================== HELPER: READ OTP FROM EMAIL AND SUBMIT =====================
def enterOtpAndProceed(List<TestObject> otpFields, TestObject proceedBtn, String host, String subject, int pageTimeout, int clickTimeout, FailureHandling fh) {
	WebUI.waitForElementVisible(otpFields[0], pageTimeout, fh) // Wait for OTP page instead of fixed delay
	String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(host, GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, GlobalVariable.Sender_Email, subject) // Read OTP from inbox
	println('OTP fetched = ' + otp) // Log the OTP for debugging
	otp.toList().eachWithIndex { String digit, int i -> WebUI.setText(otpFields[i], digit) } // Type each digit in its box
	WebUI.waitForElementClickable(proceedBtn, clickTimeout, fh) // Wait until Proceed is enabled
	WebUI.click(proceedBtn, fh) // Click Proceed
}

// ===================== STEP 1: LOGIN & CREATE PATIENT =====================
WebUI.callTestCase(findTestCase(TC_LOGIN_MAXIMEYES), [:], STOP) // Login to MaximEyes
WebUI.callTestCase(findTestCase(TC_CREATE_PATIENT), [('phoneNumber') : GlobalVariable.Mobile, ('emailId') : GlobalVariable.MyEmail_Id], STOP) // Create random patient with test phone/email

// ===================== STEP 2: SEND SIGN-UP EMAIL =====================
WebUI.click(SIGNUP_PP_BTN) // Open portal sign-up options
WebUI.click(SEND_SIGNUP_EMAIL) // Choose "Send Sign Up Email"
WebUI.click(SIGNUP_PROCEED_BTN) // Confirm email address
WebUI.waitForElementNotVisible(BUSY_INDICATOR, BUSY_TIMEOUT_SIGNUP) // Wait for spinner to disappear
WebUI.verifyElementText(TOAST_MSG, SignUp_Email_Toast) // Verify sign-up toast (SignUp_Email_Toast = test case variable)

// ===================== STEP 3: RESET PORTAL PASSWORD =====================
WebUI.click(RESET_PORTAL_BTN) // Open reset portal options
WebUI.click(RESET_PWD_PRINT) // Choose "Reset password and Print Instructions"
WebUI.click(RESET_PROCEED_BTN) // Confirm reset
WebUI.waitForElementNotVisible(BUSY_INDICATOR, BUSY_TIMEOUT_RESET) // Wait for spinner to disappear
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(RESET_TOAST_TEXT) // Verify reset toast message
CustomKeywords.'common.CaptureCredentials.captureAndStoreCredentials'(CRED_OBJECT) // Capture username/password from invitation

// ===================== STEP 4: FIRST PORTAL LOGIN (TEMP PASSWORD) =====================
WebUI.navigateToUrl(GlobalVariable.PortalURL) // Open patient portal
WebUI.click(SIGN_IN_BTN) // Click Sign In
WebUI.callTestCase(findTestCase(TC_LOGIN_PORTAL), [('Username') : GlobalVariable.RestUserName, ('Password') : GlobalVariable.RestPassword], STOP) // Login with temp credentials
WebUI.callTestCase(findTestCase(TC_DOB_TERMS), [:], STOP) // Confirm DOB and accept terms
enterOtpAndProceed(OTP_FIELDS, PROCEED_BTN, IMAP_HOST, OTP_SUBJECT, OTP_PAGE_TIMEOUT, CLICK_TIMEOUT, STOP) // Fetch OTP, enter it, proceed

// ===================== STEP 5: UPDATE PASSWORD (MBT 48416) =====================
WebUI.callTestCase(findTestCase(TC_UPDATE_PASSWORD), [:], STOP) // Update the password

// ===================== STEP 6: LOGIN WITH UPDATED PASSWORD =====================
WebUI.callTestCase(findTestCase(TC_LOGIN_PORTAL), [('Username') : GlobalVariable.RestUserName, ('Password') : GlobalVariable.UpdatePassword], STOP) // Login with new password
enterOtpAndProceed(OTP_FIELDS, PROCEED_BTN, IMAP_HOST, OTP_SUBJECT, OTP_PAGE_TIMEOUT, CLICK_TIMEOUT, STOP) // Fetch new OTP, enter it, proceed

// ===================== STEP 7: DASHBOARD VERIFICATION =====================
WebUI.waitForPageLoad(CLICK_TIMEOUT) // Wait for dashboard load instead of fixed 10s delay
WebUI.callTestCase(findTestCase(TC_VERIFY_DASHBOARD), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], STOP) // Verify date/time and patient name
WebUI.callTestCase(findTestCase(TC_VERIFY_SETTINGS), [:], STOP) // Verify Settings dropdown options