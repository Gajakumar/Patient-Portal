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
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
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

String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))

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

//Mouse Hover On Green Check
WebUI.mouseOver(findTestObject('Object Repository/2710/Page_MaximEyes/span_Patient Portal_PtOverviewResetPatientToPortalPP'))

//Verify Text on Hover
CustomKeywords.'common.MessageVerifier.verifyFullText'(
	findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/div__no-margin font14 pad15'),
	AfterOptInTextonOverview
)

//click on green check
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/span_Patient Portal_PtOverviewResetPatientT_a02aab'))

//Select opt out radio button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/span_Opt-outDisabled Patient Portal account_b24fb6'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/input_Edit Email Address_btnproceed'))

// 1. Locate canvas
TestObject canvasObj = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/canvas_Provider Signature_pad')

// 2. Wait for visibility
WebUI.waitForElementVisible(canvasObj, 30)

// 3. Get WebElement
WebElement canvasElement = WebUI.findWebElement(canvasObj)

// 4. Scroll into view (IMPORTANT)
WebUI.scrollToElement(canvasObj, 5)

// 5. Actions
Actions actions = new Actions(DriverFactory.getWebDriver())

// Move to canvas FIRST (center or offset inside)
actions.moveToElement(canvasElement, 10, 10)   // small offset inside canvas
       .clickAndHold()
       .moveByOffset(50, 10)
       .moveByOffset(30, -20)
       .moveByOffset(40, 15)
       .moveByOffset(-20, 10)
       .release()
       .perform()

println("✔ Signature drawn successfully!")

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/input_Provider Signature_optOutPatient'))

NavigateStory nav = new NavigateStory()
nav.ClickMegaMenuItems([('TopMenuOption') : 'Patient', ('SubItem') : 'Patient Details'])

nav.ClickMegaMenuItems([('TopMenuOption') : 'Patient', ('SubItem') : 'Overview'])

//Mouse Hover On Green Check
WebUI.mouseOver(findTestObject('Object Repository/2710/Page_MaximEyes/span_Patient Portal_PtOverviewOptOutPP'))

//Verify Text on Hover
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/div__no-margin font14 pad15'),Text)

//Verify opt out todays date
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/span_Patient Portal_PtOverviewOptOutDatePP'), expectedDate)

//navigate to patient details
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/a_Overview_PatientDetailsTabLink'))

//navigate to patient overview
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/a_Office Admin  General  Documents_ui-id-35'))

WebUI.delay(3)

//Navigate to Patient Portal Site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on sign in button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter Username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Verify Opt Out Text
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h2_Opt-In Options_text-white me-2'), OptOutText)

//Verify Opt Out Text
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h3_Do you want to Opt-In_text-dark me-2'),'You have been opted out on '+ expectedDate +' by Patient Portal.')

//Click on Opt Out Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Opt Out Button On Patient Portal'))

//Click on Sign button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter UserName and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Click on Opt In button Patient Portal
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Opt-In_btn on Patient Portal'))

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter UserName and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Click on Opt In Button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_You have been opted out on 12152025 _84a11e'))

WebUI.delay(3)

//Enter Blank DOB
WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), '')
//CustomKeywords.'common.DOBHelper.setDOBWithoutCalendar'('')
WebUI.delay(2)

//Click on Procced button
WebElement proccedBtn = WebUI.findWebElement(findTestObject('Object Repository/Page_Patient Portal/ProccedBtnAftrDOBConfirm'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedBtn))


//Verify DOB Required alart displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Confirm DOB Screen/DOB Required Text'),DOBAlart)

//Enter Invalid  DOB
WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), '99/99/9999')
//CustomKeywords.'common.DOBHelper.setDOBWithoutCalendar'('99/99/9999')
WebUI.delay(2)

//Click on Procced button
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedBtn))

//Verify DOB Required alart displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Confirm DOB Screen/DOB Required Text'),ValidDOBAlart)

//Enter Valid DOB
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), GlobalVariable.DOB)
//CustomKeywords.'common.DOBHelper.setDOBWithoutCalendar'(GlobalVariable.DOB)
WebUI.delay(2)


//=================================================================================================================

//Enter DOB
//CustomKeywords.'common.DatePickerHelper.selectDOB'(GlobalVariable.DOB)
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'))

//Add DOB
WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), GlobalVariable.DOB)


//Click on Procced button
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(proccedBtn))

////Do not Accept Terms and click on Procced button   >> terms and condition page is removed from application
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))
//
////Verify Please Accpet Terms toast display
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Terms Of Service Page/Please Accept Terms Alart'),AcceptTermToast)
//
//WebUI.delay(3)
//
////Accept Terms check box
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))
//
////Do not enter sign and click on Procced button
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))
//
//////Verify Please Enter Sign toast display
////WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Add Your Sign Toast'),AddSignToast)
//
//TestObject toast = findTestObject(
//	'Object Repository/PatientPortal/SignInPage_Patient Portal/Add Your Sign Toast'
//)
//
//WebUI.waitForElementPresent(toast, 10)
//
//String toastText = WebUI.getText(toast).trim()
//println "Toast found: " + toastText
//
//WebUI.verifyMatch(toastText, AddSignToast, false)
//
//WebUI.delay(3)
//
////Uncheck Accpet Term check box
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))
//
////Add Signature
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Add Signature On Canvas'),[:], FailureHandling.STOP_ON_FAILURE)
//
////Click on Procced button
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))
//
////Verify Please Accpet Terms toast display
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Terms Of Service Page/Accpet Terms of Service Toast'),AcceptTermToast)
//
//WebUI.delay(3)
//
////Accept Terms check box
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))
//
////Click on Procced button
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))


//Verify welcome text toast
CustomKeywords.'common.ToastHelper.verifyToastMessage'(WelcomeText)

//Add Auth


//Login To Maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

//Search Patient
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'), [('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.STOP_ON_FAILURE)

//Mouse Hover On Green Check
WebUI.mouseOver(findTestObject('Object Repository/2710/Page_MaximEyes/span_Patient Portal_PtOverviewResetPatientToPortalPP'))

//Verify Text on Hover
CustomKeywords.'common.MessageVerifier.verifyFullText'(
	findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/div__no-margin font14 pad15'),
	AfterOptInTextonOverview
)

//========================Opt Out form Patient Portal==================================


//Navigate to Patient Portal Site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on sign in button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter Username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Fetch the otp from the email
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

//Enter the OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())

WebUI.delay(5)

TestObject proceedBtnOTP = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtnOTP, 15, FailureHandling.STOP_ON_FAILURE)

//Click on Procced button
WebUI.click(proceedBtnOTP, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//Verify Date Time and Patient name on Dashboard
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)


//Click on setting icon
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/div_w-10 h-10 flex items-center justify-center r'))

//click on opt out
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/span_Opt Out'))

//Verify patient name and date
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Patient Name and Date on Opt Out'),[:], FailureHandling.STOP_ON_FAILURE)

//Click on make my account inactive
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/button_Make my account inactive'))

//Verify toast
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Please accept the Terms of Service')

//check check box for accept terms
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/input_I Accept'))

//Click on make my account inactive
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/div_Make my account inactive'))

WebUI.delay(2)

//Verify toast
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Please add your signature')

//Uncheck check box for accept terms
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/input_I Accept'))

//Add Signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Add Signature On Canvas'),[:], FailureHandling.STOP_ON_FAILURE)

//Click on make my account inactive
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/button_Make my account inactive'))

//Verify toast
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Please accept the Terms of Service')

//check check box for accept terms
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/input_I Accept'))

//Click on make my account inactive
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/button_Make my account inactive'))

//Verify text after opt out
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/h4_You will be missed'), 'You will be missed')
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/p_You have successfully opted out from Patient P'),
	'You have successfully opted out from Patient Portal.')
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/p_Were sad to see you go'), 'We\'re sad to see you go.')
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/h4_Opted Out'), 'Opted Out')


//===================

//Login To Maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

//Search Patient
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'), [('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.STOP_ON_FAILURE)

//Mouse Hover On Green Check
WebUI.mouseOver(findTestObject('Object Repository/2710/Page_MaximEyes/span_Patient Portal_PtOverviewResetPatientToPortalPP'))

//Mouse Hover On Green Check
WebUI.mouseOver(findTestObject('Object Repository/2710/Page_MaximEyes/span_Patient Portal_PtOverviewOptOutPP'))

//Verify Text on Hover
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/div__no-margin font14 pad15'),Text)

//Verify opt out todays date
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/span_Patient Portal_PtOverviewOptOutDatePP'), expectedDate)

//Verify Electronic file on Overview
WebUI.verifyElementText(findTestObject('Page_MaximEyes/Page_MaximEyes/a_OptedOut.pdf'), 'OptedOut.pdf')

//Verify Category
WebUI.verifyElementText(findTestObject('Page_MaximEyes/Page_MaximEyes/span_Patient Portal'), 'Patient Portal')

//Verify Date Added
WebUI.verifyElementText(findTestObject('Page_MaximEyes/Page_MaximEyes/td_04_10_2026'), expectedDate)

//Verify Date Modified
WebUI.verifyElementText(findTestObject('Page_MaximEyes/Page_MaximEyes/td_04_10_2026_1'), expectedDate)

//Navigate to Electronic files
WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/a_ui-id-14'))

//Verify patient portal file is present
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Electronic file name'), 'Patient Portal')

