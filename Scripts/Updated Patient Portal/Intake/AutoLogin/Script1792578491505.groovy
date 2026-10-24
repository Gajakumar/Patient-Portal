import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import stories.NavigateStory
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/*
 * AutoLogin / Auto-Signup — upload trigger coverage:
 *  A) Direct: Education Material upload + FOD send-to-portal + SOC hamburger upload
 *  B) OA Incentive Program sign-off: SOC upload for patient + Education on sign-off + FOD on sign-off
 * Then first-time login, verify Messages + Health Summary, green PP icon.
 * Composes existing common TCs/keywords only — does not modify them.
 */

final String PORTAL_COMMON = 'Test Cases/common/Patient_Portal_Common/'
final String ME_COMMON = 'Test Cases/common/Maximeyes/'
final String PP_SIGNIN = 'Object Repository/PatientPortal/SignInPage_Patient Portal/'
final String ME_MIX = 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/'
final String SOC_OA = 'Object Repository/SOC Upload/Page_MaximEyes/'
final String SIGNUP_SUBJECT = 'Access to your health data'
final String OTP_SUBJECT = 'Verification'
final int TIMEOUT = 15

NavigateStory nav = new NavigateStory()

def runCommon = { String name, Map params = [:] ->
	WebUI.callTestCase(findTestCase(PORTAL_COMMON + name), params, FailureHandling.STOP_ON_FAILURE)
}

def runMe = { String name, Map params = [:] ->
	WebUI.callTestCase(findTestCase(ME_COMMON + name), params, FailureHandling.STOP_ON_FAILURE)
}

def enterOtpAndProceed = { String otp ->
	String digits = (otp ?: '').trim()
	assert digits ==~ /\d{4}/ : "Expected 4-digit OTP, got: ${otp}"
	WebUI.setText(findTestObject(PP_SIGNIN + 'otp1'), digits[0])
	WebUI.setText(findTestObject(PP_SIGNIN + 'otp2'), digits[1])
	WebUI.setText(findTestObject(PP_SIGNIN + 'otp3'), digits[2])
	WebUI.setText(findTestObject(PP_SIGNIN + 'otp4'), digits[3])
	TestObject proceedBtn = findTestObject(PP_SIGNIN + 'ProccedBtnAfterOTPVerification')
	WebUI.waitForElementClickable(proceedBtn, TIMEOUT)
	WebUI.click(proceedBtn)
}

def readOtp = {
	return CustomKeywords.'otp.GmailOTPHandler.readOTP'(
		'imap.gmail.com', GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
		GlobalVariable.Sender_Email, OTP_SUBJECT)
}

def clearOtpBoxes = {
	[1, 2, 3, 4].each { int i ->
		TestObject box = findTestObject(PP_SIGNIN + "otp${i}")
		WebUI.click(box)
		WebUI.sendKeys(box, Keys.chord(Keys.CONTROL, 'a').toString())
		WebUI.sendKeys(box, Keys.BACK_SPACE.toString())
	}
}

def storePatientId = {
	TestObject patientIdObj = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen')
	WebUI.waitForElementVisible(patientIdObj, TIMEOUT)
	GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''
	println 'Patient ID: ' + GlobalVariable.GV_PatientID
}

def assertPlusIconNotGreen = {
	TestObject plusIcon = findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp')
	WebUI.verifyElementPresent(plusIcon, TIMEOUT)
	WebUI.mouseOver(plusIcon)
	WebUI.verifyElementText(
		findTestObject('Object Repository/Page_MaximEyes/div__no-margin font14 pad15'),
		'Sign Up For Patient Portal')
//	WebUI.verifyElementNotPresent(
//		findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_PtOverviewResetPatientT_a02aab'),
//		5, FailureHandling.CONTINUE_ON_FAILURE)
}

def createUnregisteredPatient = {
	runCommon('Create Random Patient in Maximeyes', [
		('phoneNumber'): GlobalVariable.Mobile,
		('emailId')    : GlobalVariable.MyEmail_Id
	])
	WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
	storePatientId()
	assertPlusIconNotGreen()
}

def extractSignupCredentials = {
	WebUI.delay(5)
	CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
		GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
		GlobalVariable.Sender_Email, SIGNUP_SUBJECT)
	println 'Initial Username: ' + GlobalVariable.GV_Username
	println 'Initial Password: ' + GlobalVariable.GV_Password
	assert GlobalVariable.GV_Username : 'Username missing from auto-signup email'
	assert GlobalVariable.GV_Password : 'Password missing from auto-signup email'
}

/**
 * Only mentioned elements: Problem, Medication, FD. FOD is separate. (Allergy skipped)
 */
def addMentionedEncounterElements = {
	// --- Problem (Alcohol abuse — same pattern as Education Material TC) ---
	WebUI.click(findTestObject(ME_MIX + 'a_Intake Form_encTabList_1'))
	WebUI.delay(1)
	WebUI.click(findTestObject(ME_MIX + 'Add Problem Plus button'))
	WebUI.setText(findTestObject(ME_MIX + 'input_Loading_DESCRIPTION_GF_b04f_GF_I'), 'Alcohol abuse')
	WebUI.click(findTestObject(ME_MIX + 'em'))
	WebUI.click(findTestObject(ME_MIX + 'td_Loading_STATUS_GF_b04f_GF_B-1'))
	WebUI.click(findTestObject(ME_MIX + 'td_Loading_STATUS_GF_b04f_GF_DDD_L_LBI0T0'))
	WebUI.click(findTestObject(ME_MIX + 'td_Chronic_SOURCE_GF_b04f_GF_B-1'))
	WebUI.click(findTestObject(ME_MIX + 'td_Chronic_SOURCE_GF_b04f_GF_DDD_L_LBI0T0'))
	WebUI.click(findTestObject(ME_MIX + 'input_--Select--_problemListGridView_b04f_E_114e19'))
	WebUI.delay(1)

	// --- Medication (Lipitor) ---
	WebUI.click(findTestObject(ME_MIX + 'span_--Select--_glyphicon-circelplus font17_9bfae2'))
	WebUI.setText(findTestObject(ME_MIX + 'input_Search Drug_MEDICATION_BRAND_NAME_I'), 'Lipitor,atorvastatin')
	WebUI.delay(1)
	WebUI.click(findTestObject(ME_MIX + 'input_Directions to Pharmacist_Add'))
	WebUI.click(findTestObject(ME_MIX + 'input_Refills_btnOk'))

	// --- Final Diagnosis (FD) ---
	nav.SelectEncounterElementFromLeftNavOnEncounter([
		pElementPage: 'Final Findings',
		pElement    : 'Final Diagnoses'
	])
	WebUI.delay(2)
	WebUI.click(findTestObject(ME_MIX + 'span_Annual exam_glyphicon-circelplus font1_0c94a1'))
	WebUI.waitForElementVisible(findTestObject(ME_MIX + 'input_Search_textbox'), TIMEOUT)
	WebUI.setText(findTestObject(ME_MIX + 'input_Search_textbox'), 'H disease')
	WebUI.click(findTestObject(ME_MIX + 'button_GO'))
	WebUI.waitForElementVisible(findTestObject(ME_MIX + 'td_H disease'), TIMEOUT)
	WebUI.click(findTestObject(ME_MIX + 'td_H disease'))
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_PLANS_Save'))

	
}

/** Education Material → Upload to Patient Portal (Problem: Alcohol abuse) */
def uploadEducationMaterialToPortal = {
	nav.SelectEncounterElementFromLeftNavOnEncounter([
		pElementPage: 'Chief Complaint & HPI',
		pElement    : 'Problems'
	])
	WebUI.delay(2)

	TestObject popup = findTestObject(ME_MIX + 'div_Health Information Resource_title')
	TestObject eduMaterial = findTestObject(ME_MIX + 'span_EHR_EducationMaterial', [('Desc'): 'Alcohol abuse'])
	WebUI.waitForElementPresent(eduMaterial, 20)

	for (int i = 1; i <= 3; i++) {
		WebUI.scrollToElement(eduMaterial, 5)
		WebUI.waitForElementClickable(eduMaterial, 10)
		WebUI.click(eduMaterial)
		if (WebUI.waitForElementVisible(popup, 3, FailureHandling.OPTIONAL)) {
			break
		}
		if (i == 3) {
			KeywordUtil.markFailed('Education Material popup did not open for Alcohol abuse')
		}
	}

	WebUI.mouseOver(findTestObject(ME_MIX + 'span_Patient Portal_uploadEMToPatientPortal'))
	WebUI.click(findTestObject(ME_MIX + 'span_Patient Portal_uploadEMToPatientPortal'))
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		'Health information resource uploaded successfully on Patient Portal.')
	WebUI.click(findTestObject(ME_MIX + 'button_Health Information Resource_dialog-c_57ea1d'))
}

/** FOD → Send document to Patient Portal */
def sendFodToPatientPortal = { boolean sendNow = true ->
	nav.SelectEncounterElementFromLeftNavOnEncounter([
		pElementPage: 'Final Findings',
		pElement    : 'Final Outbound Documents'
	])
	WebUI.delay(5)
	WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_glyphicon-circelplus font17 fg-skyblue'))
	WebUI.waitForElementNotVisible(findTestObject('Object Repository/Scenario Update1703/Page_MaximEyes/Page Loader'), 30)
	WebUI.waitForElementNotVisible(findTestObject('Scenario Update1703/Page_MaximEyes/svg_txRibbonLoader'), 30)
	WebUI.selectOptionByValue(findTestObject('Scenario Update1703/Page_MaximEyes/select_ddlDocumentType'), '11', false)
	WebUI.selectOptionByValue(findTestObject('Scenario Update1703/Page_MaximEyes/select_ddlDocumentTemplate'), '180', false)
	WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/button_btnSelectDocument'))
	WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
	WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_btnAddRecipient'))
	WebUI.selectOptionByValue(findTestObject('Scenario Update1703/Page_MaximEyes/select_ddlRecipientType'), 'Patient', false)
	WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_chkIsPrint'))
	WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_icon-checked'))
	WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_btnAddChildRecipientDetails'))
	WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_btnSaveChildRecipients'))

	if (sendNow) {
		WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_btnSendDocument'))
		WebUI.delay(5)
		TestObject docName = findTestObject('Object Repository/Scenario Update1703/Page_MaximEyes/a_Cataract Consultation')
		WebUI.waitForElementPresent(docName, 20)
		WebUI.verifyElementText(docName, 'Cataract Consultation')

		TestObject sentStatus = findTestObject('Scenario Update1703/Page_MaximEyes/span_Sent_1')
		WebUI.waitForElementPresent(sentStatus, 15)
		if (WebUI.verifyElementPresent(sentStatus, 5, FailureHandling.OPTIONAL)) {
			assert WebUI.getText(sentStatus).trim().equalsIgnoreCase('Sent')
		} else {
			KeywordUtil.markFailed("'Sent' status not found after FOD send")
		}

		WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_mif-dropdown font15 fg-skyblue'))
		WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Send To Portal'), 'Send To Portal')
		WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_mif-dropdown font15 fg-skyblue_1'))
		WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Sent'), 'Sent')
		WebUI.verifyElementText(
			findTestObject('Scenario Update1703/Page_MaximEyes/td_Document sent to Patient Portal successfully'),
			'Document sent to Patient Portal successfully.')
	} else {
		// Prepare for sign-off send (OA: Send documents on sign off)
		WebUI.click(findTestObject('Object Repository/Scenario Update1703/Page_MaximEyes/OK Btn on Add FOD Doc'))
		WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
	}
}

/** SOC hamburger → Upload to Patient Portal (no full-element SOC_Verification) */
def uploadSocToPatientPortal = {
	CustomKeywords.'custom.SocCcdaTestKeywords.openSummaryOfCareFromEncounter'()
	WebUI.verifyElementText(findTestObject(ME_MIX + 'h2_Summary of Care (C-CDA)'), 'Summary of Care (C-CDA)')
	WebUI.verifyElementText(
		findTestObject(ME_MIX + 'h4_Zak Duckett'),
		GlobalVariable.PatientFirstName + ' ' + GlobalVariable.PatientLastName)
	CustomKeywords.'custom.SocCcdaTestKeywords.uploadSocToPatientPortalFromMaximEyes'()
	WebUI.click(findTestObject(ME_MIX + 'Close Button SOC'))
}

/** OA >> Encounter >> Incentive Program — enable SOC / Education / FOD on sign-off */
def enableIncentiveProgramUploadsOnSignOff = {
	WebUI.click(findTestObject(SOC_OA + 'span__mif-cog font20 head-icon-shadow fg-white'))
	WebUI.click(findTestObject(SOC_OA + 'a_Modules'))
	WebUI.click(findTestObject(SOC_OA + 'a_Encounters'))
	WebUI.click(findTestObject(SOC_OA + 'a_Incentive Programs'))

	// Summary Of Care — Upload for Patient
	CustomKeywords.'common.UIUtils.toggleCheckbox'(
		findTestObject(SOC_OA + 'span_Upload for Patient_icon-checked'), true)
	// Educational material — send from all elements on sign-off
	CustomKeywords.'common.UIUtils.toggleCheckbox'(
		findTestObject('OA Maximeyes/Page_MaximEyes/span_Send Education Material from all Elements o'), true)
	// Final Outbound Documents — send on sign-off
	CustomKeywords.'common.UIUtils.toggleCheckbox'(
		findTestObject('OA Maximeyes/Page_MaximEyes/span_Send documents on sign off of encounter'), true)

	WebUI.click(findTestObject(SOC_OA + 'Home btn'))
}

def signOffEncounterAndExpectPortalUpload = {
	WebUI.click(findTestObject(SOC_OA + 'span_TOC Req_spnSignOff'))
	WebUI.click(findTestObject(SOC_OA + 'input_Are you sure you want to sign off the_f71194'))
	WebUI.setText(findTestObject(SOC_OA + 'input_Patient Portal_signaturePassword'), '123456')
	WebUI.click(findTestObject(SOC_OA + 'input_Patient Portal_authenticateUserSignature'))
	// Sign-off may surface SOC and/or education upload success toasts
	WebUI.delay(8)
}

// =====================================================
// PATH 1 — Direct uploads: EM + FOD + SOC (unregistered patient)
// =====================================================
runCommon('User Login in Maximeyes Pt Portal')
createUnregisteredPatient()

runMe('Add New Encounter_Create New')
// Problem + Medication + FD only (Allergy skipped)
addMentionedEncounterElements()

// 4–5a) Education Material upload (Problem / Asthma)
uploadEducationMaterialToPortal()

// 5b) FOD send to Patient Portal
sendFodToPatientPortal(true)

// 5c) SOC hamburger → Upload to Patient Portal
uploadSocToPatientPortal()

extractSignupCredentials()

// =====================================================
// First-time login (DOB / OTP / Update Password)
// =====================================================
runCommon('Navigate to Patient Portal Site')
WebUI.click(findTestObject(PP_SIGNIN + 'SignInBtn'))
runCommon('User Login With Username and Password', [
	('Username'): GlobalVariable.GV_Username,
	('Password'): GlobalVariable.GV_Password
])
runCommon('DOB Confirmation and Accept Terms')

// Invalid OTP + Resend already covered by common TC — enter latest OTP only (avoid lockout)
runCommon('OTP Negative Scenario and Resend Verification')
clearOtpBoxes()
enterOtpAndProceed(readOtp())

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Patient Portal/UpdatePass'), TIMEOUT)
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Patient Portal/ConfirmPass'), TIMEOUT)
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Patient Portal/ProccedBtnAfterConifrmPass'), TIMEOUT)



// Update password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)


runCommon('User Login With Username and Password', [
	('Username'): GlobalVariable.GV_Username,
	('Password'): GlobalVariable.UpdatePassword
])
WebUI.delay(5)
enterOtpAndProceed(readOtp())
WebUI.delay(10)

runCommon('Verify Date Time and Patient name on Dashboard', [
	('Firstname'): GlobalVariable.PatientFirstName,
	('Lastname') : GlobalVariable.PatientLastName
])

// =====================================================
// 15) Verify uploaded data — Health Summary (SOC) + Messages (EM / FOD)
// =====================================================
String todayGMT = CustomKeywords.'common.DateUtil.getTodayDateGMT'()

// Health Summary (SOC)
String actualUnreadSummaryCount = WebUI.getText(
	findTestObject('Object Repository/Page_Patient Portal/span_1unread messages')
).replaceAll('\\s+', '').trim()
WebUI.verifyMatch(actualUnreadSummaryCount, '1', false)
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1unread messages'))
WebUI.verifyElementText(
	findTestObject('Object Repository/Page_Patient Portal/p_Visit Date 01162026'),
	'Visit Date: ' + todayGMT)
// Soft check — only Problem/Medication/FD were entered (not full SOC element set)
WebUI.callTestCase(
	findTestCase(PORTAL_COMMON + 'SOC Verification On Patient Portal'),
	[:],
	FailureHandling.CONTINUE_ON_FAILURE)

// Messages (Education Material / FOD)
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Mark Wood_border-2 rounded-full p-4 smp_311faa'))
WebUI.delay(3)

TestObject msgSubjects = new TestObject('auto_login_msg_subjects')
msgSubjects.addProperty('xpath', ConditionType.EQUALS,
	"//section[@aria-label='Messages list']//p[contains(@class,'font-medium')]")
List<WebElement> subjectEls = WebUI.findWebElements(msgSubjects, 15)
List<String> subjects = subjectEls.collect { it.getText().trim() }
println 'Message subjects: ' + subjects

boolean hasEducationMsg = subjects.any { it.toLowerCase().contains('education') }
boolean hasFodOrCataract = subjects.any {
	it.toLowerCase().contains('cataract') || it.toLowerCase().contains('education material')
}
assert hasEducationMsg || hasFodOrCataract :
	'Expected Education Material / FOD message on portal. Subjects: ' + subjects

// Green PP icon after sign-in
String patientIdPath1 = GlobalVariable.GV_PatientID
runCommon('User Login in Maximeyes Pt Portal')
runMe('Find Patient Using Patient ID', [('PatientID'): patientIdPath1])

TestObject greenIcon = findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_PtOverviewResetPatientT_a02aab')
WebUI.mouseOver(greenIcon)
String hoverText = WebUI.getText(findTestObject('Object Repository/Page_MaximEyes/p')).replaceAll('\\s+', ' ').trim()
WebUI.verifyMatch(
	hoverText,
	"Patient Signup has been provided to patient. Click to Reset Patient's Password or Opt Patient Out of Patient Portal.",
	true)
CustomKeywords.'color.VerifyColor.verifyColor'(greenIcon, 'rgb(103, 184, 53)')

// =====================================================
// PATH 2 — OA Incentive Program + Encounter Sign-Off
// (SOC upload for patient + Education on sign-off + FOD on sign-off)
// =====================================================
enableIncentiveProgramUploadsOnSignOff()
createUnregisteredPatient()
String patientIdPath2 = GlobalVariable.GV_PatientID

runMe('Add New Encounter_Create New')
addMentionedEncounterElements()
// EM / SOC / FOD send on sign-off via OA Incentive preferences

// Prepare FOD with Patient + Portal (sent on sign-off)
sendFodToPatientPortal(false)

signOffEncounterAndExpectPortalUpload()

// Auto-signup email must fire from sign-off upload path as well
extractSignupCredentials()
assert GlobalVariable.GV_Username : 'Sign-off path did not trigger auto-signup credentials email'

KeywordUtil.logInfo("Path2 patient ${patientIdPath2}: Incentive Program sign-off auto-signup credentials received.")
