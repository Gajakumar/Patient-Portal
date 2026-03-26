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
import stories.NavigateStory

//Navigate to Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on sign in button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter Username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : UserNamePt, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Get OPT from email
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

//Enter OTP
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
WebUI.delay(5)

//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Select Update Demographics
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Profile_block pr-14 py-2 font-normal t_ea6dd6'))


//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Name_middleName'), 'Test')
//
////Click on Save Changes btn
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Save Changes Btn on Update Demographics'))
//
//// Open new tab
//WebUI.executeJavaScript("window.open('about:blank','_blank');", [])
//
//// Switch to 2nd tab
//WebUI.switchToWindowIndex(1)
//
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
//
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName'), 'David')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_PP_underline'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Overview_PatientDetailsTabLink'))
//
//WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Page_MaximEyes/input_Name_PatientInfo_PatientDetail_MiddleInitial'), 
//    'value','Test',10)



// ===================== COMMON OBJECTS =====================

// Portal
TestObject firstNameField  = findTestObject('Object Repository/Page_Patient Portal/input_Name_firstName')
TestObject lastNameField   = findTestObject('Object Repository/Page_Patient Portal/input_Name_lastName')
TestObject phoneField      = findTestObject('Object Repository/Page_Patient Portal/input_Action_form-control mt-1 form-control_3def46_9')
TestObject emailField      = findTestObject('Object Repository/Page_Patient Portal/input_Primary Email_primaryEmail')
TestObject saveBtn         = findTestObject('Object Repository/Page_Patient Portal/Save Changes Btn on Update Demographics')

// Max

TestObject searchIcon     = findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient')
TestObject findBtn     = findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient')
TestObject searchedPt      = findTestObject('Object Repository/Page_MaximEyes/a_PP_underline')
TestObject patientTab      = findTestObject('Object Repository/Max/Patient/tab_PatientDetails')

TestObject nameLabel       = findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/span_David Smith')
TestObject FirstnameMax    =findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName')
TestObject phoneLabel      = findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/span_lblDashboardPhone')
TestObject emailLabel      = findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/span_lblDashboardEmail')



// ===================== TEST DATA =====================
String firstName = "SteveEdit"
String lastName  = "MarshEdit"
String phone     = "9876543210"
String email     = "sync_test@mail.com"
String fullName  = firstName + " " + lastName

// ===================== UPDATE DEMOGRAPHICS =====================

WebUI.waitForElementVisible(firstNameField, 10)

WebUI.clearText(firstNameField)
WebUI.setText(firstNameField, firstName)

WebUI.clearText(lastNameField)
WebUI.setText(lastNameField, lastName)

WebUI.clearText(phoneField)
WebUI.setText(phoneField, phone)

WebUI.clearText(emailField)
WebUI.setText(emailField, email)

// ===================== CAPTURE PORTAL DATA =====================
String portalFirstName = WebUI.getAttribute(firstNameField, 'value')
String portalLastName = WebUI.getAttribute(lastNameField, 'value')
String portalEmail = WebUI.getAttribute(emailField, 'value')
String portalPhone = WebUI.getAttribute(phoneField, 'value')

WebUI.click(saveBtn)


// ===================== LOGIN MAX =====================

// Open new tab
WebUI.executeJavaScript("window.open('about:blank','_blank');", [])

// Switch to 2nd tab
WebUI.switchToWindowIndex(1)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
// ===================== SEARCH PATIENT =====================
WebUI.click(searchIcon)
WebUI.setText(FirstnameMax, firstName)
WebUI.click(findBtn)

//WebUI.click(searchedPt)


// ===================== GET MAX DATA =====================
String maxName  = WebUI.getText(nameLabel)
String maxPhone = WebUI.getText(phoneLabel).replaceAll('\\s+', '').trim()
String maxEmail = WebUI.getText(emailLabel).replaceAll('\\s+', '').trim()



// ===================== VERIFY =====================
WebUI.verifyMatch(maxName, fullName, false)

//WebUI.verifyMatch(
//	maxPhone.replaceAll('[^0-9]', ''),
//	portalPhone.replaceAll('[^0-9]', ''),
//	false
//)

//WebUI.verifyMatch(maxEmail, portalEmail, false)


//// ===================== UPDATE IN MAX =====================

NavigateStory nav = new NavigateStory()
nav.ClickMegaMenuItems([('TopMenuOption') : 'Patient', ('SubItem') : 'Patient Details'])

String Fname = "Steve"
String Lname = "Marsh"

String updatedPhone = "9998887776"
WebUI.setText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_FirstName'),Fname)
WebUI.setText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_LastName'),Lname)
WebUI.setText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PR_PhoneNumber_d3beb4800'),updatedPhone)
WebUI.setText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PR_EMAIL_d3beb4801'),GlobalVariable.MyEmail_Id)

nav.ClickMegaMenuItems([('TopMenuOption') : 'Patient', ('SubItem') : 'Overview'])


// Switch to 1st tab
WebUI.switchToWindowIndex(0)


//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//Select Update Demographics
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Profile_block pr-14 py-2 font-normal t_ea6dd6'))

// ===================== CAPTURE PORTAL DATA =====================
String portalFirstNameUpdated = WebUI.getAttribute(firstNameField, 'value')
String portalLastNameUpdated = WebUI.getAttribute(lastNameField, 'value')
String portalEmailUpdated = WebUI.getAttribute(emailField, 'value')
String portalPhoneUpdated = WebUI.getAttribute(phoneField, 'value')

WebUI.verifyMatch(Fname, portalFirstNameUpdated, false)
WebUI.verifyMatch(Lname, portalLastNameUpdated, false)
WebUI.verifyMatch(GlobalVariable.MyEmail_Id, portalEmailUpdated, false)
WebUI.verifyMatch(updatedPhone, portalPhoneUpdated, false)


//// ===================== VERIFY BACK IN PORTAL =====================
//WebUI.openBrowser('')
//WebUI.navigateToUrl(GlobalVariable.Portal_URL)
//
//WebUI.setText(usernameField, GlobalVariable.Patient_User)
//WebUI.setEncryptedText(passwordField, GlobalVariable.Patient_Password)
//WebUI.click(loginBtn)
//
//WebUI.click(settingsIcon)
//WebUI.click(demographicsMenu)
//
//String updatedPortalPhone = WebUI.getAttribute(phoneField, 'value')
//
//WebUI.verifyMatch(
//	updatedPortalPhone.replaceAll('[^0-9]', ''),
//	updatedPhone,
//	false
//)
//
//WebUI.closeBrowser()

