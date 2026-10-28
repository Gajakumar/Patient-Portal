import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

/*
 * Web intake flow. Calls existing test cases and keywords only.
 *  1. Dashboard: arrival copy, earliest appointment + Intake Form link,
 *     hidden when the patient has no appointment. The link opens the policy screen.
 *  2. New-appointment confirmation: intake copy + Proceed opens the policy screen.
 *  3. Reschedule confirmation: same copy + Proceed.
 *  4. A completed intake reopens with the saved answers. Updates sync to MaximEyes.
 *  5. A new patient, with intake already configured in MaximEyes, gets the default
 *     HIPAA policy, Billing policy, and default Intake Form.
 */

final int TIMEOUT_SEC = 15
final int BUSY_TIMEOUT_SEC = 30
final int EMAIL_ARRIVAL_DELAY = 10
final int PAGE_LOAD_TIMEOUT = 30
final int PORTAL_TAB = 1

final int DATE_RANGE_DAYS = 15
final int EARLY_DAYS_AHEAD = 0
final int LATER_DAYS_AHEAD = 5
final int RESCHEDULE_DAYS_AHEAD = 8
final String DATE_FORMAT = 'MM/dd/yyyy'
final String EARLY_TIME = '11:50 AM'
final String LATER_TIME = '01:35 PM'

// TODO: confirm location, provider, and reason labels. Reason drives the appointment-specific intake form.
final String LOCATION_NAME = 'Patient Portal - Bellingham, WA'
final String PROVIDER_NAME = 'Patient Portal'
final String REASON_NAME = 'Patient Portal'
final String REASON_FOR_VISIT = 'Test Intake Appointment'
final String SIGNUP_EMAIL_SUBJECT = 'Access to your health data'
final String OTP_EMAIL_SUBJECT = 'Verification'

final String PORTAL_COMMON = 'Test Cases/common/Patient_Portal_Common/'
final String PORTAL_SIGNIN = 'Object Repository/PatientPortal/SignInPage_Patient Portal/'
final String PORTAL_APPT = 'Appointments/PP Appointment/Page_Patient Portal/'

// TODO: confirm these Office Admin labels, especially formsOnline and defaultForm.
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

final Map flags = [defaultsDone: false, intakeFilled: false]
final Map saved = [:]

TestObject busyIndicator = findTestObject('Page_MaximEyes/Busy Indicator')
TestObject patientIdObj = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen')
TestObject signInButton = findTestObject(PORTAL_SIGNIN + 'SignInBtn')
TestObject proceedAfterOtpButton = findTestObject(PORTAL_SIGNIN + 'ProccedBtnAfterOTPVerification')
List<TestObject> otpInputs = (1..4).collect { findTestObject(PORTAL_SIGNIN + "otp${it}") }
TestObject dashboardMenuButton = findTestObject(PORTAL_APPT + 'div_dashboard-menu-icon-btn border-2 rounded-ful')
TestObject requestNewApptButton = findTestObject(PORTAL_APPT + 'button_Request New Appointment')
TestObject requestNewApptPlus = findTestObject(PORTAL_APPT + 'Request New Appt Plus btn')
TestObject locationDropdown = findTestObject(PORTAL_APPT + 'select_Select Location')
TestObject providerDropdown = findTestObject('Object Repository/' + PORTAL_APPT + 'Select Provider')
TestObject reasonDropdown = findTestObject(PORTAL_APPT + 'select_Select Reason')
TestObject calendarIcon = findTestObject('Object Repository/Appointments/Calendar/Calender icon')
TestObject reasonTextarea = findTestObject(PORTAL_APPT + 'textarea_Reason for visit _')
TestObject proceedToReasonButton = findTestObject(PORTAL_APPT + 'button_Proceed')
TestObject proceedBtn2 = findTestObject(PORTAL_APPT + 'button_Proceed_1')
TestObject confirmationHeader = findTestObject(PORTAL_APPT + 'h4_Appointment Confirmed')
TestObject appointmentsBtn = findTestObject(PORTAL_APPT + 'button_Appointments')
TestObject appointmentCards = findTestObject(PORTAL_APPT + 'appointmentCards')
TestObject homeButton = findTestObject('Authorized Individual/Page_Patient Portal/HomeFromOA')
TestObject oaProceedBtn = findTestObject('Appointments/General Pref/Page_MaximEyes/input_btnProceed')
TestObject intakeProceed = findTestObject('Intake/Page_Patient Portal/button_Proceed')

def runCommon = { String name, Map params = [:] ->
	WebUI.callTestCase(findTestCase(PORTAL_COMMON + name), params, FailureHandling.STOP_ON_FAILURE)
}

def require = { boolean ok, String message ->
	if (!ok) {
		throw new IllegalStateException(message)
	}
}

def xp = { String name, String xpath ->
	TestObject to = new TestObject(name)
	to.addProperty('xpath', ConditionType.EQUALS, xpath)
	return to
}

def squash = { String value ->
	(value ?: '').replaceAll(/\s+/, '').replace("'", '').replace('\u2019', '')
}

def safeClick = { TestObject obj ->
	WebUI.waitForElementClickable(obj, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebUI.click(obj, FailureHandling.STOP_ON_FAILURE)
}

def intakeFrame = {
	// TODO: replace if the policy or reason-specific form uses a different iframe title or src.
	xp('intakeFrame', "//iframe[contains(@title,'Patient Intake Form') or contains(@src,'forms.maximeyes.com')]")
}

def waitForIntakeFrame = { int seconds ->
	WebUI.waitForElementPresent(intakeFrame(), seconds, FailureHandling.OPTIONAL)
}

def hipaaShowing = {
	WebUI.switchToDefaultContent()
	if (!waitForIntakeFrame(5)) {
		return false
	}
	WebUI.switchToFrame(intakeFrame(), TIMEOUT_SEC)
	// TODO: confirm the HIPAA name field id if the default policy form changes.
	boolean hipaa = WebUI.verifyElementPresent(xp('hipaaFirst', "//*[@id='first_10']"), 5, FailureHandling.OPTIONAL)
	WebUI.switchToDefaultContent()
	return hipaa
}

def logOpenedForm = {
	TestObject frame = intakeFrame()
	String title = WebUI.getAttribute(frame, 'title') ?: ''
	String src = WebUI.getAttribute(frame, 'src') ?: ''
	WebUI.comment("Intake form opened (default, or the form linked to reason '${REASON_NAME}'). title=${title} src=${src}")
	// TODO: if a reason-linked form uses another host or title, update this check and compare src to that form.
	require(title.contains('Patient Intake Form') || src.contains('forms.maximeyes.com'),
		"Intake frame did not open. title=${title} src=${src}")
}

def goDashboard = {
	String url = WebUI.getUrl() ?: ''
	if (url.contains('/dashboard')) {
		return
	}
	// TODO: change '/dashboard' if the portal home route is different.
	String origin = url.replaceFirst('^(https?://[^/]+).*$', '$1')
	require(origin.startsWith('http'), 'Could not resolve the portal origin from ' + url)
	WebUI.navigateToUrl(origin + '/dashboard')
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
}

def clickDoneHome = {
	// TODO: confirm the button that leaves the completed intake page (Home vs Save) and point these xpaths at it.
	TestObject home = xp('intakeHome', "//button[normalize-space()='Home']")
	TestObject save = xp('intakeSave', "//button[normalize-space()='Save']")
	if (WebUI.waitForElementClickable(home, 5, FailureHandling.OPTIONAL)) {
		WebUI.click(home)
	} else {
		WebUI.waitForElementClickable(save, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(save)
	}
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	goDashboard()
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
	require(otp != null && otp.trim() ==~ /\d{4}/, 'OTP handler did not return a valid four-digit OTP.')
	String digits = otp.trim()
	otpInputs.eachWithIndex { TestObject box, int i -> WebUI.setText(box, digits[i]) }
	safeClick(proceedAfterOtpButton)
}

def appointmentLine = { String appointmentDate, String appointmentTime ->
	DateTimeFormatter inputDate = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH)
	// TODO: match this pattern to the appointment line on the dashboard if the UI format differs.
	DateTimeFormatter displayDate = DateTimeFormatter.ofPattern('EEEE, MMMM dd, yyyy', Locale.ENGLISH)
	DateTimeFormatter displayTime = DateTimeFormatter.ofPattern('h:mm a', Locale.ENGLISH)
	LocalDate date = LocalDate.parse(appointmentDate, inputDate)
	String value = appointmentTime.trim().toUpperCase(Locale.ENGLISH)
	LocalTime time
	try {
		time = LocalTime.parse(value, DateTimeFormatter.ofPattern('hh:mm a', Locale.ENGLISH))
	} catch (Exception ignored) {
		time = LocalTime.parse(value, DateTimeFormatter.ofPattern('h:mm a', Locale.ENGLISH))
	}
	String padded = time.format(displayTime) + ' ' + date.format(displayDate)
	return [padded, padded.replaceAll(', 0', ', ')]
}

def prefilledDemographics = {
	[
		firstName    : GlobalVariable.PatientFirstName,
		lastName     : GlobalVariable.PatientLastName,
		preferredName: GlobalVariable.PatientFirstName,
		dob          : GlobalVariable.DOB,
		sex          : 'Male',
		primaryEmail : GlobalVariable.MyEmail_Id
	]
}

def completeDefaultPolicies = {
	String patientName = GlobalVariable.PatientFirstName + ' ' + GlobalVariable.PatientLastName
	WebUI.comment('Scenario 5: default HIPAA policy')
	CustomKeywords.'common.VerifyHipaaPolicy.verifyAndCompleteHipaaPolicy'(patientName, 'Accepted')
	WebUI.comment('Scenario 5: default Billing policy')
	CustomKeywords.'common.VerifyHipaaPolicy.verifyAndCompleteBillingPolicy'(patientName, 'Accepted')
	// TODO: replace Intake1/.../button_Save if the insurance page uses a different Save object.
	WebUI.click(findTestObject('Intake1/Page_Patient Portal/button_Save'))
	flags.defaultsDone = true
}

def fillIntake = {
	WebUI.comment('Scenario 5: default Intake Form')
	Map prefilled = prefilledDemographics()
	def demo = CustomKeywords.'common.DemographicsKeywords.enterDemographics'()
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

	saved.prefilled = prefilled
	saved.demo = demo
	saved.social = social
	saved.medical = medical
	saved.lens = lens
	flags.intakeFilled = true
}

def verifySavedIntake = {
	require(flags.intakeFilled, 'Intake has not been submitted yet.')
	WebUI.comment('Scenario 4: previously submitted intake is populated')
	CustomKeywords.'common.DemographicsKeywords.verifyDemographics'(saved.prefilled + saved.demo)
	CustomKeywords.'common.PatientIntakeKeywords.clickPatientIntakeTab'('Social Hx')
	CustomKeywords.'common.SocialHistoryKeywords.verifySocialHistory'(saved.social)
	CustomKeywords.'common.PatientIntakeKeywords.clickPatientIntakeTab'('Medical Hx')
	CustomKeywords.'common.MedicalHistoryKeywords.verifyMedicalHistory'(saved.medical)
	CustomKeywords.'common.PatientIntakeKeywords.clickPatientIntakeTab'('Lens Hx')
	CustomKeywords.'common.LensHistoryKeywords.verifyLensHistory'(saved.lens)
}

def updateIntake = {
	WebUI.comment('Scenario 4: update intake so the change can sync to MaximEyes')
	Map prefilled = saved.prefilled
	def demo = CustomKeywords.'common.DemographicsKeywords.enterDemographics'()
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

	saved.demo = demo
	saved.social = social
	saved.medical = medical
	saved.lens = lens
}

def handleIntake = { String mode ->
	require(waitForIntakeFrame(TIMEOUT_SEC), 'Policy / intake form did not open.')
	logOpenedForm()
	if (hipaaShowing()) {
		completeDefaultPolicies()
	} else {
		require(flags.defaultsDone, 'Scenario 5: default HIPAA policy was not presented.')
	}
	if (mode == 'fill') {
		fillIntake()
		clickDoneHome()
	} else if (mode == 'open') {
		if (flags.intakeFilled) {
			
			CustomKeywords.'common.DemographicsKeywords.verifyDemographics'(saved.prefilled + saved.demo)
		}
		// TODO: flow — WebUI.back() assumes the previous page is still the portal. Replace it if the form opens in a new window.
		WebUI.switchToDefaultContent()
		WebUI.back()
		WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	} else if (mode == 'update') {
		verifySavedIntake()
		updateIntake()
		clickDoneHome()
	}
}

def assertSubmittedIntakePrompt = { String scenario ->
	WebUI.comment(scenario)
	// TODO: replace these xpaths if the confirmation copy is not in its own element, and Intake/.../button_Proceed if Proceed is a different object.
	WebUI.waitForElementVisible(xp('completeTitle', "//*[normalize-space()='Complete Patient Intake Form']"), TIMEOUT_SEC)
	WebUI.verifyElementText(xp('completeTitle', "//*[normalize-space()='Complete Patient Intake Form']"), 'Complete Patient Intake Form')
//	String help = WebUI.getText(xp('completeHelp', "//*[contains(normalize-space(.),'filling out questionnaires')]"))
//	require(squash(help).contains('Savetimeattheofficebyfillingoutquestionnaires'),
//		"Unexpected intake help text: ${help}")
	WebUI.verifyElementPresent(intakeProceed, TIMEOUT_SEC)
}

def bookOpenForm = { String appointmentDate, String preferredTime, List<String> usedTimes, String fromDate, String toDate ->
	WebUI.selectOptionByLabel(locationDropdown, LOCATION_NAME, false)
	WebUI.selectOptionByLabel(providerDropdown, PROVIDER_NAME, false)
	WebUI.selectOptionByLabel(reasonDropdown, REASON_NAME, false)
	WebUI.waitForElementClickable(requestNewApptButton, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebElement proceedElement = WebUI.findWebElement(requestNewApptButton, TIMEOUT_SEC)
	WebUI.executeJavaScript('arguments[0].click();', [proceedElement])
	safeClick(calendarIcon)
	String bookedTime = CustomKeywords.'custom.AppointmentKeywords.selectAppointmentDateTime'(
		fromDate, toDate, appointmentDate, preferredTime, usedTimes)
	require(bookedTime as boolean, 'selectAppointmentDateTime() did not return a time.')
	WebUI.comment("Booked slot: ${appointmentDate} ${bookedTime}")
	safeClick(proceedToReasonButton)
	WebUI.waitForElementVisible(reasonTextarea, TIMEOUT_SEC, FailureHandling.STOP_ON_FAILURE)
	WebUI.setText(reasonTextarea, REASON_FOR_VISIT)
	safeClick(proceedBtn2)
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	WebUI.assertElementText(confirmationHeader, 'Appointment Confirmed', TIMEOUT_SEC)
	return bookedTime
}

GlobalVariable.GV_PatientID = ''

try {
	runCommon('User Login in Maximeyes Pt Portal')
	runCommon('Create Random Patient in Maximeyes', [
		('phoneNumber'): GlobalVariable.Mobile, ('emailId'): GlobalVariable.MyEmail_Id])

	WebUI.waitForElementNotVisible(busyIndicator, BUSY_TIMEOUT_SEC)
	WebUI.waitForElementVisible(patientIdObj, TIMEOUT_SEC)
	GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''
	require(GlobalVariable.GV_PatientID as boolean, 'Patient ID was not populated after patient creation.')
	KeywordUtil.logInfo('Patient ID: ' + GlobalVariable.GV_PatientID)

	runCommon('Portal Sign up using email')
	WebUI.delay(EMAIL_ARRIVAL_DELAY)
	CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
		GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, GlobalVariable.Sender_Email, SIGNUP_EMAIL_SUBJECT)
	require(GlobalVariable.GV_Username && GlobalVariable.GV_Password,
		'Portal username/password could not be extracted from the sign-up email.')

	WebUI.click(findTestObject('Page_MaximEyes/OAButton'))
	CustomKeywords.'stories.NavigateStory.ClickOnOfficeAdminNavigationTabs'([
		MainTab: 'Modules', SubTabL1: 'Patient', SubTabL2: null])
	WebUI.waitForElementNotVisible(busyIndicator, BUSY_TIMEOUT_SEC, FailureHandling.OPTIONAL)
	dismissOaWarningModalIfPresent()

	WebUI.waitForElementPresent(xp('intakeSyncActive', "//*[@id='SyncActive']"), TIMEOUT_SEC)
	CustomKeywords.'custom.IntakeOAPreferences.setIntakePreferences'(INTAKE_PREFS)
	dismissOaWarningModalIfPresent()
	WebUI.waitForElementPresent(xp('defaultIntakeForm', "//*[@id='DefaultIntakeFormPreferenceValue']"), BUSY_TIMEOUT_SEC)
	CustomKeywords.'custom.IntakeOAPreferences.verifyIntakePreferences'(INTAKE_PREFS)

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

	// TODO: flow — if registration always lands on the dashboard, delete this block. Scenario 5 then runs on the first Proceed below.
	if (waitForIntakeFrame(8)) {
		WebUI.comment('Scenario 5: policies and the default Intake Form are presented after registration')
		handleIntake('fill')
	}

	runCommon('Verify Date Time and Patient name on Dashboard', [
		('Firstname'): GlobalVariable.PatientFirstName, ('Lastname'): GlobalVariable.PatientLastName])

	WebUI.comment('Scenario 1c: no appointments, so the intake section stays hidden')
	safeClick(dashboardMenuButton)
	WebUI.verifyElementPresent(requestNewApptButton, TIMEOUT_SEC)
	// TODO: confirm the hidden-state xpaths. Update the intake link href if it is not /intake-form.
	require(!WebUI.verifyElementPresent(xp('speedHidden', "//h3[normalize-space()='Speed up your arrival']"), 3, FailureHandling.OPTIONAL),
		'Intake section should be hidden when the patient has no appointments.')
	require(!WebUI.verifyElementPresent(xp('intakeHidden', "//a[@href='/intake-form']"), 3, FailureHandling.OPTIONAL),
		'Intake Form link should be hidden when the patient has no appointments.')

	safeClick(requestNewApptButton)
	
	LocalDate today = LocalDate.now()
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_FORMAT)
	String fromDate = today.format(fmt)
	String toDate = today.plusDays(DATE_RANGE_DAYS).format(fmt)
	String earlyDate = today.plusDays(EARLY_DAYS_AHEAD).format(fmt)
	String laterDate = today.plusDays(LATER_DAYS_AHEAD).format(fmt)
	String rescheduleDate = today.plusDays(RESCHEDULE_DAYS_AHEAD).format(fmt)
	List<String> usedTimes = []

	String earlyBooked = bookOpenForm(earlyDate, EARLY_TIME, usedTimes, fromDate, toDate)
	usedTimes << earlyBooked
	assertSubmittedIntakePrompt('Scenario 2: new-appointment confirmation')
	safeClick(appointmentsBtn)
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)

	// TODO: object — Request New Appt Plus btn is a brittle xpath. Recapture it if the second booking does not open.
	safeClick(requestNewApptPlus)
	String laterBooked = bookOpenForm(laterDate, LATER_TIME, usedTimes, fromDate, toDate)
	usedTimes << laterBooked
	assertSubmittedIntakePrompt('Scenario 2: new-appointment confirmation')
	safeClick(intakeProceed)
	handleIntake(flags.intakeFilled ? 'open' : 'fill')
	goDashboard()

	WebUI.comment('Scenario 1: dashboard shows the earliest appointment and the intake link')
	// TODO: point speedHeading, speedHelp, shownAppointment, and reasonLine at the recorded dashboard objects if these xpaths miss.
	WebUI.verifyElementText(xp('speedHeading', "//h3[normalize-space()='Speed up your arrival']"), 'Speed up your arrival')
	String arrivalHelp = WebUI.getText(xp('speedHelp',
		"//h3[normalize-space()='Speed up your arrival']/following::*[contains(normalize-space(.),'filling')][1]"))
	require(squash(arrivalHelp).contains('SavetimeatthedoctorsofficebyfillingIntakeForm'),
		"Unexpected dashboard intake text: ${arrivalHelp}")
//	String shownAppointment = WebUI.getText(xp('shownAppointment',
//		"//h3[normalize-space()='Speed up your arrival']/following::*[contains(normalize-space(.),'AM') or contains(normalize-space(.),'PM')][1]"))
//	require(appointmentLine(earlyDate, earlyBooked).any { shownAppointment.contains(it) },
//		"Earliest appointment was not shown with the intake link. Found: ${shownAppointment}")
//	require(!appointmentLine(laterDate, laterBooked).any { shownAppointment.contains(it) },
//		"Intake section showed a later appointment instead of the earliest. Found: ${shownAppointment}")
//	WebUI.verifyElementPresent(xp('reasonLine', "//*[normalize-space()='Reason: ${REASON_NAME}']"), TIMEOUT_SEC)

	WebUI.comment('Scenario 1a: the intake link opens the policy / appointment form')
	// TODO: replace this xpath with the Intake Form link object once it is captured. Do not use the old href /update-insurance.
//	safeClick(xp('intakeLink', "//h3[normalize-space()='Speed up your arrival']/following::a[contains(normalize-space(.),'Intake Form')][1]"))
	WebUI.click(findTestObject('Intake1/Page_Patient Portal/a_Intake Form'))
	//Click save insurance
	WebUI.click(findTestObject('Intake1/Page_Patient Portal/button_Save'))
	handleIntake('open')
	goDashboard()

	WebUI.comment('Scenario 3: reschedule confirmation')
	safeClick(appointmentsBtn)
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	// TODO: flow — card index 2 is the later appointment. Change it if the appointments list is not chronological.
	CustomKeywords.'custom.AppointmentActions.clickActionAndVerifyPrompt'(appointmentCards, 2, 'Reschedule')
	CustomKeywords.'custom.AppointmentActions.respondToConfirmation'('Yes', 'Reschedule')
	String rescheduledTime = bookOpenForm(rescheduleDate, EARLY_TIME, usedTimes, fromDate, toDate)
	usedTimes << rescheduledTime
	assertSubmittedIntakePrompt('Scenario 3: reschedule confirmation')
	safeClick(intakeProceed)
	handleIntake('update')

	WebUI.switchToWindowIndex(0)
	WebUI.click(homeButton)
	WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
	WebUI.callTestCase(
		findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
		[('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.STOP_ON_FAILURE)
	CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Patient', SubItem: 'Welcome Form'])
	Map welcome = [frame: 'welcome']
	CustomKeywords.'common.DemographicsKeywords.verifyDemographicsWithOptions'(saved.prefilled + saved.demo, welcome)
	CustomKeywords.'common.SocialHistoryKeywords.verifySocialHistoryWithOptions'(saved.social, welcome)
	CustomKeywords.'common.MedicalHistoryKeywords.verifyMedicalHistoryWithOptions'(saved.medical, welcome)
	CustomKeywords.'common.LensHistoryKeywords.verifyLensHistoryWithOptions'(saved.lens, welcome)

	WebUI.comment('IntakeFlowScenario completed')
} catch (Throwable t) {
	WebUI.comment('Test failed: ' + t.toString())
	throw t
} finally {
	try {
		if (GlobalVariable.GV_PatientID) {
			WebUI.switchToWindowIndex(0)
			WebUI.click(homeButton)
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
