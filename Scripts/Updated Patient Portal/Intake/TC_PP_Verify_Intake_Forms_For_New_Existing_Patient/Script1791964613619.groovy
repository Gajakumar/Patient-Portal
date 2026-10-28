import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/*
 * Intake1 (standalone): portal intake OA prefs + book one appointment.
 * Uses: IntakeOApreferences + appointment common test cases (not SOC keywords).
 */

final int TIMEOUT_SEC         = 15
final int BUSY_TIMEOUT_SEC    = 30
final int EMAIL_ARRIVAL_DELAY = 10
final int PAGE_LOAD_TIMEOUT   = 30
final int PORTAL_TAB          = 1

final int    DATE_RANGE_DAYS        = 15
final int    APPOINTMENT_DAYS_AHEAD = 0
final String DATE_FORMAT            = 'MM/dd/yyyy'
final String PREFERRED_TIME         = '11:50 AM'
final String LOCATION_NAME          = 'Patient Portal - Bellingham, WA'
final String PROVIDER_NAME          = 'Patient Portal'
final String REASON_NAME            = 'Patient Portal'
final String REASON_FOR_VISIT       = 'Test Intake Appointment'
final String SIGNUP_EMAIL_SUBJECT   = 'Access to your health data'
final String OTP_EMAIL_SUBJECT      = 'Verification'

final String PORTAL_COMMON = 'Test Cases/common/Patient_Portal_Common/'
final String PORTAL_SIGNIN = 'Object Repository/PatientPortal/SignInPage_Patient Portal/'
final String PORTAL_APPT   = 'Appointments/PP Appointment/Page_Patient Portal/'
final String ME_REPO       = 'Appointments/Appt Type/Page_MaximEyes/'

final Map INTAKE_PREFS = [
	active              : true,
	formsOnline         : 'HIPAA, Billing Policy, Patient History',
	insurance           : true,
	referringPhysicians : false,
	howFound            : false,
	landingText         : 'We are honored you have chosen us for your vision and eye health needs!',
	importMode          : 'Automatically Import',
	defaultForm         : 'Online Patient Intake Form (Demographics, insurance, medical history)'
]

TestObject busyIndicator = findTestObject('Page_MaximEyes/Busy Indicator')
TestObject patientIdObj = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen')
TestObject signInButton = findTestObject(PORTAL_SIGNIN + 'SignInBtn')
TestObject proceedAfterOtpButton = findTestObject(PORTAL_SIGNIN + 'ProccedBtnAfterOTPVerification')
List<TestObject> otpInputs = (1..4).collect { findTestObject(PORTAL_SIGNIN + "otp${it}") }
TestObject dashboardMenuButton = findTestObject(PORTAL_APPT + 'div_dashboard-menu-icon-btn border-2 rounded-ful')
TestObject requestNewApptButton = findTestObject(PORTAL_APPT + 'button_Request New Appointment')
TestObject locationDropdown = findTestObject(PORTAL_APPT + 'select_Select Location')
TestObject providerDropdown = findTestObject('Object Repository/' + PORTAL_APPT + 'Select Provider')
TestObject reasonDropdown = findTestObject(PORTAL_APPT + 'select_Select Reason')
TestObject calendarIcon = findTestObject('Object Repository/Appointments/Calendar/Calender icon')
TestObject reasonTextarea = findTestObject(PORTAL_APPT + 'textarea_Reason for visit _')
TestObject proceedToReasonButton = findTestObject(PORTAL_APPT + 'button_Proceed')
TestObject proceedBtn2 = findTestObject(PORTAL_APPT + 'button_Proceed_1')
TestObject confirmationHeader = findTestObject(PORTAL_APPT + 'h4_Appointment Confirmed')
TestObject appointmentCard = findTestObject(PORTAL_APPT + 'div_Dr. Patient Portal9_55 PM Friday, Sep 25, 20')
TestObject apptSummaryContainer = findTestObject(PORTAL_APPT + 'div_09_25_2026 _ 09_55 PM _ Patient PortalFirst')
TestObject appointmentsBtn = findTestObject(PORTAL_APPT + 'button_Appointments')
TestObject homeButton = findTestObject('Authorized Individual/Page_Patient Portal/HomeFromOA')
TestObject oaProceedBtn = findTestObject('Appointments/General Pref/Page_MaximEyes/input_btnProceed')

def runCommon = { String name, Map params = [:] ->
	WebUI.callTestCase(findTestCase(PORTAL_COMMON + name), params, FailureHandling.STOP_ON_FAILURE)
}

def safeClick = { TestObject obj ->
	WebUI.waitForElementClickable(obj, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebUI.click(obj, FailureHandling.STOP_ON_FAILURE)
}

def dismissOaWarningModalIfPresent = {
	if (WebUI.waitForElementClickable(oaProceedBtn, 3, FailureHandling.OPTIONAL)) {
		WebUI.click(oaProceedBtn, FailureHandling.OPTIONAL)
		WebUI.waitForElementNotVisible(busyIndicator, BUSY_TIMEOUT_SEC, FailureHandling.OPTIONAL)
	}
}

def completeOtpVerification = {
	WebUI.waitForElementVisible(otpInputs[0], TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
		'imap.gmail.com', GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
		GlobalVariable.Sender_Email, OTP_EMAIL_SUBJECT)
	if (otp == null || !(otp.trim() ==~ /\d{4}/)) {
		throw new IllegalStateException('OTP handler did not return a valid four-digit OTP.')
	}
	String digits = otp.trim()
	otpInputs.eachWithIndex { TestObject box, int i -> WebUI.setText(box, digits[i]) }
	safeClick(proceedAfterOtpButton)
}

def openScheduleSettings = {
	WebUI.click(findTestObject(ME_REPO + 'a_Office Admin'))
	WebUI.click(findTestObject(ME_REPO + 'a_Modules'))
	WebUI.click(findTestObject(ME_REPO + 'a_ui-id-21'))
}

def applyScheduleSettings = { String schedulingHours, String cancelationHours ->
	CustomKeywords.'custom.AppointmentSettingsKeywords.setAppointmentSettings'(
		[
			'SCHEDULING_HOURS'              : schedulingHours,
			'CANCELATION_HOURS'             : cancelationHours,
			'DAILY_LIMIT_HOURS'             : '2',
			'ApptLimitForOnlineAppointment' : '5'
		],
		[
			'idLimitOnlineEnable'                : false,
			'idIsOnlineApptActivityReportEnable' : false,
			'idIsEnableOnlineScheduleInsurance'  : false
		]
	)
	WebUI.click(findTestObject(ME_REPO + 'a_Modules'))
	WebUI.waitForElementNotVisible(busyIndicator, BUSY_TIMEOUT_SEC)
}

GlobalVariable.GV_PatientID = ''

try {
	runCommon('User Login in Maximeyes Pt Portal')
	runCommon('Create Random Patient in Maximeyes', [
		('phoneNumber'): GlobalVariable.Mobile, ('emailId'): GlobalVariable.MyEmail_Id])

	WebUI.waitForElementNotVisible(busyIndicator, BUSY_TIMEOUT_SEC)
	WebUI.waitForElementVisible(patientIdObj, TIMEOUT_SEC)
	GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''
	if (!GlobalVariable.GV_PatientID) {
		throw new IllegalStateException('Patient ID was not populated after patient creation.')
	}
	KeywordUtil.logInfo('Patient ID: ' + GlobalVariable.GV_PatientID)

	runCommon('Portal Sign up using email')
	WebUI.delay(EMAIL_ARRIVAL_DELAY)
	CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
		GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, GlobalVariable.Sender_Email, SIGNUP_EMAIL_SUBJECT)
	if (!GlobalVariable.GV_Username || !GlobalVariable.GV_Password) {
		throw new IllegalStateException('Portal username/password could not be extracted from the sign-up email.')
	}

	WebUI.click(findTestObject('Page_MaximEyes/OAButton'))
	CustomKeywords.'stories.NavigateStory.ClickOnOfficeAdminNavigationTabs'([
		MainTab: 'Modules', SubTabL1: 'Patient', SubTabL2: null])

	WebUI.waitForElementNotVisible(busyIndicator, BUSY_TIMEOUT_SEC, FailureHandling.OPTIONAL)
	dismissOaWarningModalIfPresent()

	TestObject intakePrefsReady = new TestObject('intake_SyncActive')
	intakePrefsReady.addProperty('xpath', ConditionType.EQUALS, "//*[@id='SyncActive']")
	WebUI.waitForElementPresent(intakePrefsReady, TIMEOUT_SEC)

	CustomKeywords.'custom.IntakeOAPreferences.setIntakePreferences'(INTAKE_PREFS)
	dismissOaWarningModalIfPresent()

	TestObject defaultIntakeFormSelect = new TestObject('intake_DefaultIntakeFormPreferenceValue')
	defaultIntakeFormSelect.addProperty('xpath', ConditionType.EQUALS, "//*[@id='DefaultIntakeFormPreferenceValue']")
	WebUI.waitForElementPresent(defaultIntakeFormSelect, BUSY_TIMEOUT_SEC)

	CustomKeywords.'custom.IntakeOAPreferences.verifyIntakePreferences'(INTAKE_PREFS)

	WebUI.click(findTestObject(ME_REPO + 'a_Modules'))
//	openScheduleSettings()
//	applyScheduleSettings('2', '1')

	WebUI.executeJavaScript("window.open('about:blank', '_blank');", [])
	WebUI.switchToWindowIndex(PORTAL_TAB)
	runCommon('Navigate to Patient Portal Site')
	safeClick(signInButton)
	runCommon('User Login With Username and Password', [
		('Username'): GlobalVariable.GV_Username, ('Password'): GlobalVariable.GV_Password])
	runCommon('DOB Confirmation and Accept Terms')
	completeOtpVerification()
	runCommon('Update Password')
	runCommon('User Login With Username and Password', [
		('Username'): GlobalVariable.GV_Username, ('Password'): GlobalVariable.UpdatePassword])
	completeOtpVerification()
	runCommon('Verify Date Time and Patient name on Dashboard', [
		('Firstname'): GlobalVariable.PatientFirstName, ('Lastname'): GlobalVariable.PatientLastName])

	safeClick(dashboardMenuButton)
	safeClick(requestNewApptButton)

	LocalDate today = LocalDate.now()
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_FORMAT)
	String fromDate = today.format(fmt)
	String toDate = today.plusDays(DATE_RANGE_DAYS).format(fmt)
	String appointmentDate = today.plusDays(APPOINTMENT_DAYS_AHEAD).format(fmt)

	WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)
	WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)
	WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)
	WebUI.waitForElementClickable(requestNewApptButton, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebElement proceedElement = WebUI.findWebElement(requestNewApptButton, TIMEOUT_SEC)
	WebUI.executeJavaScript('arguments[0].click();', [proceedElement])
	safeClick(calendarIcon)

	List<String> usedTimes = []
	String bookedTime = CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
		fromDate, toDate, appointmentDate, PREFERRED_TIME, usedTimes)
	if (!bookedTime) {
		throw new IllegalStateException('selectAppointmentDateTime() did not return a time.')
	}
	WebUI.comment("Booked slot: ${appointmentDate} ${bookedTime}")

	safeClick(proceedToReasonButton)
	WebUI.waitForElementVisible(reasonTextarea, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebUI.setText(reasonTextarea, REASON_FOR_VISIT)
	safeClick(proceedBtn2)
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)

	WebUI.assertElementText(confirmationHeader, 'Appointment Confirmed', TIMEOUT_SEC)
	CustomKeywords.'custom.ApptDateTimeVerification.verifyAppointmentCard'(
		appointmentCard, 'Dr.  Patient  Portal', appointmentDate, bookedTime)

	String patientName = GlobalVariable.PatientFirstName + ' ' + GlobalVariable.PatientLastName
	
	//Click on procced button
	WebUI.click(findTestObject('Intake/Page_Patient Portal/button_Proceed'))
	
	CustomKeywords.'common.VerifyHipaaPolicy.verifyAndCompleteHipaaPolicy'(
		patientName,
		'Accepted'
	)
	
	CustomKeywords.'common.VerifyHipaaPolicy.verifyAndCompleteBillingPolicy'(
		patientName,
		'Accepted'
	)

	//Save button on insurance page
	WebUI.click(findTestObject('Intake1/Page_Patient Portal/button_Save'))
	
	
	// ---- Portal: enter + verify each tab, then move on ----
	def demo    = CustomKeywords.'common.DemographicsKeywords.enterDemographics'()
	def prefilled = [firstName: GlobalVariable.PatientFirstName, lastName: GlobalVariable.PatientLastName, preferredName: GlobalVariable.PatientFirstName,
		dob: GlobalVariable.DOB, sex: 'Male', primaryEmail: GlobalVariable.MyEmail_Id]
	CustomKeywords.'common.DemographicsKeywords.verifyDemographics'(prefilled + demo)
	CustomKeywords.'common.DemographicsKeywords.clickNextOnDemographics'()
	
	CustomKeywords.'common.PatientIntakeKeywords.clickPatientIntakeTab'('Social Hx')
	def social = CustomKeywords.'common.SocialHistoryKeywords.enterSocialHistory'()
	CustomKeywords.'common.SocialHistoryKeywords.verifySocialHistory'(social)
	CustomKeywords.'common.SocialHistoryKeywords.clickNextOnSocialHistory'()
	
	CustomKeywords.'common.PatientIntakeKeywords.clickPatientIntakeTab'('Medical Hx')
	def medical = CustomKeywords.'common.MedicalHistoryKeywords.enterMedicalHistory'()
	CustomKeywords.'common.MedicalHistoryKeywords.verifyMedicalHistory'(medical)
	CustomKeywords.'common.MedicalHistoryKeywords.clickNextOnMedicalHistory'()
	
	CustomKeywords.'common.PatientIntakeKeywords.clickPatientIntakeTab'('Lens Hx')
	def lens = CustomKeywords.'common.LensHistoryKeywords.enterLensHistory'()
	CustomKeywords.'common.LensHistoryKeywords.verifyLensHistory'(lens)
	CustomKeywords.'common.LensHistoryKeywords.submitLensHistory'()
	

	
	
	WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/h1_David Smith'), patientName, 5)
	
	
	WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/p_You have an upcoming appointment'), 'You have an upcoming appointment',
		0)
	

	
DateTimeFormatter inputDateFmt = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH)
DateTimeFormatter displayDateFmt = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", Locale.ENGLISH)

DateTimeFormatter inputTimeFmt = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
DateTimeFormatter displayTimeFmt = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)

LocalDate date = LocalDate.parse(appointmentDate, inputDateFmt)
String formattedTime = LocalTime.parse(bookedTime, inputTimeFmt).format(displayTimeFmt)

String expectedText = formattedTime + " " + date.format(displayDateFmt)

WebUI.comment("EXPECTED: ${expectedText}")

WebUI.assertElementText(
    findTestObject('Intake1/Page_Patient Portal/p_11_10 AM Monday, October 26, 2026_1'),
    expectedText,
    5
)
	
	WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/p_Reason_ Patient Portal'), 'Reason: Patient Portal',
		0)

	
//	WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/h2_All forms and History forms are up to date'), 'All forms completed.',
//		0)
	
	WebUI.assertElementPresent(findTestObject('Intake1/Page_Patient Portal/button_Cancel Appointment'), 0)
	
	WebUI.assertElementPresent(findTestObject('Intake1/Page_Patient Portal/button_Reschedule'), 0)
	
	//Home button on intake completed
	WebUI.click(findTestObject('Intake1/Page_Patient Portal/button_Save'))
	
	
	
	WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/h3_Speed up your arrival'), 'Speed up your arrival',
		0)
	

	
	WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/p_Save time at the doctors office by fillingInt'), "Save time at the doctor's office by fillingIntake Form",
		0)
	
	WebUI.click(findTestObject('Intake1/Page_Patient Portal/a_Intake Form'))
	
	
	
	WebUI.switchToWindowIndex(0)
	WebUI.click(homeButton)
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	WebUI.callTestCase(
		findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
		[('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.OPTIONAL)
	CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Patient', SubItem: 'Welcome Form'])
	
	// ---- MaximEyes: open the patient's welcome form, then verify the same data ----
	def welcome = [frame: 'welcome']
	CustomKeywords.'common.DemographicsKeywords.verifyDemographicsWithOptions'(prefilled + demo, welcome)
	CustomKeywords.'common.SocialHistoryKeywords.verifySocialHistoryWithOptions'(social, welcome)
	CustomKeywords.'common.MedicalHistoryKeywords.verifyMedicalHistoryWithOptions'(medical, welcome)
	CustomKeywords.'common.LensHistoryKeywords.verifyLensHistoryWithOptions'(lens, welcome)
	
	

	WebUI.comment('Intake1 completed')
} catch (Throwable t) {
	WebUI.comment('Test failed: ' + t.toString())
	throw t
} finally {
	try {
		if (GlobalVariable.GV_PatientID) {
			WebUI.switchToWindowIndex(0)
//			WebUI.click(homeButton)
			WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
			WebUI.callTestCase(
				findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
				[('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.OPTIONAL)
			CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Schedule', SubItem: 'Schedule'])
			CustomKeywords.'custom.CancelAppointmentKeywords.cancelAllAppointments'()
		}
	} catch (Throwable cleanupError) {
		WebUI.comment('Cleanup failed: ' + cleanupError.toString())
	}
}
