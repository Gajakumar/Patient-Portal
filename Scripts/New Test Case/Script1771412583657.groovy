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

//WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Add authorized individual'))
//
//WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), "Zak")
//
//WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), "Duckett")
//
//WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), "212-121-2121")
//
//WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), GlobalVariable.MyEmail_Id)
//
//WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))
//
//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_Add New Individual'), 'Add New Individual')
//
//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_record-match-title'), 'Record Match Found')
//
//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_We have found a similar matching record'),
//	'We have found a matching record with us.')
//
//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/button_Home'), 'Home')
//
//WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/button_Try Again'), 'Try Again')
//
//WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Try Again'))

//--------------------------------------------


import org.apache.commons.lang.RandomStringUtils
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import java.util.Random

// ==========================
// STEP 1: LOGIN AS PATIENT
// ==========================

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
		[('Username') : userName, ('Password') : GlobalVariable.RestUpdatedPass],
		FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

// Fetch OTP
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
		'imap.gmail.com',
		GlobalVariable.MyEmail_Id,
		GlobalVariable.Email_Key,
		GlobalVariable.Sender_Email,
		'Verification'
)

String[] digits = otp.toCharArray()
WebUI.setText(findTestObject("PatientPortal/SignInPage_Patient Portal/otp1"), digits[0])
WebUI.setText(findTestObject("PatientPortal/SignInPage_Patient Portal/otp2"), digits[1])
WebUI.setText(findTestObject("PatientPortal/SignInPage_Patient Portal/otp3"), digits[2])
WebUI.setText(findTestObject("PatientPortal/SignInPage_Patient Portal/otp4"), digits[3])

WebUI.click(findTestObject('PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification'))

WebUI.waitForPageLoad(10)


// ==========================
// STEP 2: ADD AUTHORIZED INDIVIDUAL
// ==========================

WebUI.click(findTestObject('Page_Patient Portal/Setting Icon on Portal'))
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Add authorized individual'))

Random rand = new Random()
String firstName = RandomStringUtils.randomAlphabetic(8).capitalize()
String lastName  = RandomStringUtils.randomAlphabetic(10).capitalize()

int areaCode = 200 + rand.nextInt(800)
int prefix   = 200 + rand.nextInt(800)
int lineNum  = 1000 + rand.nextInt(9000)

String mobilePlain = "${areaCode}${prefix}${lineNum}"
String mobileFormatted = String.format("(%03d) %03d-%04d", areaCode, prefix, lineNum)

String email = "gajakumara+${System.currentTimeMillis()}@first-insight.com"

WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Legal First Name'), firstName)
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Last Name'), lastName)
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_(000) 000-0000'), mobileFormatted)
WebUI.setText(findTestObject('Authorized Individual/Page_Patient Portal/input_Email'), email)

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_Proceed_1'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_Access Granted'), 'Access Granted')

String fullName = firstName + " " + lastName


// ==========================
// STEP 3: VERIFY IN LIST
// ==========================

WebUI.click(findTestObject('Page_Patient Portal/Home Btn Patient Portal'))
WebUI.click(findTestObject('Page_Patient Portal/Setting Icon on Portal'))
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'), fullName)

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'))


// ==========================
// STEP 4: UPDATE PERMISSION
// ==========================

TestObject permission1 = findTestObject('Authorized Individual/Permissions/Page_Patient Portal/input_permission1')
WebUI.uncheck(permission1)

WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save'))

WebUI.verifyElementText(
		findTestObject('Authorized Individual/Permissions/Page_Patient Portal/p_confirmation'),
		'Do you want to update access permissions for the selected Authorized Individual?'
)

WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Save_1'))

WebUI.verifyElementText(
		findTestObject('Authorized Individual/Page_Patient Portal/toast_message'),
		'Authorized Individual Permission Updated'
)


// ==========================
// STEP 5: RESEND SIGNUP EMAIL
// ==========================

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'))
WebUI.click(findTestObject('Authorized Individual/Permissions/Page_Patient Portal/button_Resend Signup Email'))

String activationLink = CustomKeywords.'email.EmailVerification.verifyAccessEmailsWithPolling'(
		"imap.gmail.com",
		GlobalVariable.MyEmail_Id,
		GlobalVariable.Email_Key,
		fullName,
		mobilePlain,
		email,
		GlobalVariable.Sender_Email,
		120
)

assert activationLink != null


// ==========================
// STEP 6: OPEN ACTIVATION LINK
// ==========================

WebUI.navigateToUrl(activationLink)

WebUI.verifyElementText(
		findTestObject('AuthSignup/Page_Terms/h2_Terms & Conditions'),
		'Terms & Conditions'
)


// ==========================
// STEP 7: TERMS VALIDATION
// ==========================

WebUI.click(findTestObject('AuthSignup/Page_Terms/button_Proceed'))

WebUI.verifyElementText(
		findTestObject('AuthSignup/Page_Terms/toast_message'),
		'Please accept Terms of Service'
)

WebUI.click(findTestObject('AuthSignup/Page_Terms/checkbox_accept'))
WebUI.click(findTestObject('AuthSignup/Page_Terms/signature_pad'))
WebUI.delay(2)

WebUI.click(findTestObject('AuthSignup/Page_Terms/button_Proceed'))

WebUI.verifyElementPresent(findTestObject('AuthSignup/Page_OTP/h2_OTP'), 10)


// ==========================
// STEP 8: AUTH OTP VALIDATION
// ==========================

// Invalid OTP
WebUI.setText(findTestObject('AuthSignup/Page_OTP/input_otp'), '1111')
WebUI.click(findTestObject('AuthSignup/Page_OTP/button_Proceed'))

WebUI.verifyElementText(findTestObject('AuthSignup/Page_OTP/error_invalid'), 'Invalid or expired OTP')

// Fetch correct OTP
String authOtp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
		'imap.gmail.com',
		GlobalVariable.MyEmail_Id,
		GlobalVariable.Email_Key,
		GlobalVariable.Sender_Email,
		'Verification'
)

WebUI.setText(findTestObject('AuthSignup/Page_OTP/input_otp'), authOtp)
WebUI.click(findTestObject('AuthSignup/Page_OTP/button_Proceed'))


// ==========================
// STEP 9: CREATE PASSWORD
// ==========================

WebUI.setText(findTestObject('AuthSignup/Page_CreatePassword/input_Password'), 'Test@123')
WebUI.setText(findTestObject('AuthSignup/Page_CreatePassword/input_ConfirmPassword'), 'Test@123')

WebUI.click(findTestObject('AuthSignup/Page_CreatePassword/button_Proceed'))

WebUI.verifyElementPresent(findTestObject('AuthPortal/Login/button_SignIn'), 10)


// ==========================
// STEP 10: LOGIN AS AUTH
// ==========================

WebUI.setText(findTestObject('AuthPortal/Login/input_Username'), fullName)
WebUI.setText(findTestObject('AuthPortal/Login/input_Password'), 'Test@123')

WebUI.click(findTestObject('AuthPortal/Login/button_SignIn'))

String loginOtp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
		'imap.gmail.com',
		GlobalVariable.MyEmail_Id,
		GlobalVariable.Email_Key,
		GlobalVariable.Sender_Email,
		'Verification'
)

WebUI.setText(findTestObject('AuthPortal/OTP/input_otp'), loginOtp)
WebUI.click(findTestObject('AuthPortal/OTP/button_Proceed'))


// ==========================
// STEP 11: PATIENT SELECTION
// ==========================

WebUI.click(findTestObject('AuthPortal/SignupAsPatient/button_Proceed'))

WebUI.verifyElementText(
		findTestObject('AuthPortal/SignupAsPatient/p_validation'),
		'Please select one Patient from Dropdown'
)

WebUI.selectOptionByIndex(findTestObject('AuthPortal/SignupAsPatient/dropdown_Patient'), 1)
WebUI.click(findTestObject('AuthPortal/SignupAsPatient/button_Proceed'))

WebUI.verifyElementPresent(findTestObject('AuthPortal/ViewAccount'), 10)


// ==========================
// STEP 12: DELETE AUTHORIZED INDIVIDUAL
// ==========================

WebUI.click(findTestObject('Page_Patient Portal/Setting Icon on Portal'))
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))
WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/svg_a'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Delete'))

WebUI.verifyElementText(
		findTestObject('Authorized Individual/Page_Patient Portal/td_No authorized individuals found'),
		'No authorized individuals found.'
)

println("✅ END-TO-END FLOW COMPLETED SUCCESSFULLY")
