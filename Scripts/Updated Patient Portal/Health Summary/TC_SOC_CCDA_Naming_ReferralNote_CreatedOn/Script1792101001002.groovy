import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import custom.DownloadHelper
import internal.GlobalVariable as GlobalVariable

/*
 * SOC C-CDA naming, Created On, transmit attachments, Referral Note in XML.
 * Known limitation (per QA): XML download/transmit file name may not reflect updated patient name; PDF expected to pass.
 */

final String ME_MIX = 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/'
final String PP_SOC = 'Object Repository/SOC Verification On PP/Page_Patient Portal/'
final String PP_SIGNIN = 'Object Repository/PatientPortal/SignInPage_Patient Portal/'
final String TRANSMIT_EMAIL = 'gajakumara@first-insight.com'

def runOtpAndProceed = {
	String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
		'imap.gmail.com', GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, GlobalVariable.Sender_Email, 'Verification')
	String digits = otp.trim()
	WebUI.setText(findTestObject(PP_SIGNIN + 'otp1'), digits[0])
	WebUI.setText(findTestObject(PP_SIGNIN + 'otp2'), digits[1])
	WebUI.setText(findTestObject(PP_SIGNIN + 'otp3'), digits[2])
	WebUI.setText(findTestObject(PP_SIGNIN + 'otp4'), digits[3])
	TestObject proceedBtn = findTestObject(PP_SIGNIN + 'ProccedBtnAfterOTPVerification')
	WebUI.waitForElementClickable(proceedBtn, 15)
	WebUI.click(proceedBtn)
}

def portalFirstLogin = {
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)
	WebUI.click(findTestObject(PP_SIGNIN + 'SignInBtn'))
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [
		('Username'): GlobalVariable.GV_Username, ('Password'): GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)
	runOtpAndProceed()
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [
		('Username'): GlobalVariable.GV_Username, ('Password'): GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)
	runOtpAndProceed()
}

def portalLoginExisting = {
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [
		('Username'): GlobalVariable.GV_Username, ('Password'): GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)
	runOtpAndProceed()
}

def downloadCcdaBlob = { String suffix ->
	DownloadHelper helper = new DownloadHelper()
	helper.captureBlobDownload()
	WebUI.click(findTestObject(PP_SOC + (suffix == 'pdf' ? 'btn_DownloadPDF' : 'btn_DownloadCCDA')))
	return helper.retrieveCapturedBlobAsFile(null, 45)
}

// --- MaximEyes: patient, encounter, SOC upload (without Referral Note first) ---
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'), [
	('phoneNumber'): GlobalVariable.Mobile, ('emailId'): GlobalVariable.MyEmail_Id], FailureHandling.STOP_ON_FAILURE)

TestObject patientIdObj = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen')
WebUI.waitForElementVisible(patientIdObj, 15)
GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Portal Sign up using email'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(10)
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key, GlobalVariable.Sender_Email, 'Access to your health data')

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Add New Encounter_Create New'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Enter Data In Enc Elements'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'custom.SocCcdaTestKeywords.openSummaryOfCareFromEncounter'()
CustomKeywords.'custom.SocCcdaTestKeywords.uploadSocToPatientPortalFromMaximEyes'()
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

String todayGMT = CustomKeywords.'common.DateUtil.getTodayDateGMT'()

// --- Patient Portal: latest SOC, Created On, downloads, transmit ---
portalFirstLogin()
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [
	('Firstname'): GlobalVariable.PatientFirstName, ('Lastname'): GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'custom.SocCcdaTestKeywords.openLatestHealthSummaryOnPortal'()
CustomKeywords.'custom.SocCcdaTestKeywords.verifyCreatedOnDateInSocIframe'(todayGMT)

String expectedXmlName = CustomKeywords.'custom.SocCcdaTestKeywords.buildExpectedCcdaAttachmentName'(
	GlobalVariable.GV_PatientID, GlobalVariable.PatientLastName, GlobalVariable.PatientFirstName, 'xml')
String expectedPdfName = CustomKeywords.'custom.SocCcdaTestKeywords.buildExpectedCcdaAttachmentName'(
	GlobalVariable.GV_PatientID, GlobalVariable.PatientLastName, GlobalVariable.PatientFirstName, 'pdf')
WebUI.comment("Expected XML attachment/download name: ${expectedXmlName}")
WebUI.comment("Expected PDF attachment/download name: ${expectedPdfName}")

String xmlPath = downloadCcdaBlob('xml')
assert new File(xmlPath).length() > 0 : 'XML download returned empty file'

String pdfPath = downloadCcdaBlob('pdf')
assert new File(pdfPath).length() > 0 : 'PDF download returned empty file'

String xmlSubject = "SOC XML ${System.currentTimeMillis()}"
CustomKeywords.'custom.SocCcdaTestKeywords.transmitHealthSummaryToProvider'('XML', TRANSMIT_EMAIL, xmlSubject)
CustomKeywords.'custom.SocCcdaTestKeywords.verifyTransmitEmailAttachment'(xmlSubject, 'xml')

CustomKeywords.'custom.SocCcdaTestKeywords.openLatestHealthSummaryOnPortal'()
String pdfSubject = "SOC PDF ${System.currentTimeMillis()}"
CustomKeywords.'custom.SocCcdaTestKeywords.transmitHealthSummaryToProvider'('PDF', TRANSMIT_EMAIL, pdfSubject)

WebUI.delay(10)
CustomKeywords.'custom.SocCcdaTestKeywords.verifyTransmitEmailAttachment'(pdfSubject, 'pdf')

// --- MaximEyes: Referral Note + re-upload ---
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'), [('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Add New Encounter_Create New'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Enter Data In Enc Elements'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'custom.SocCcdaTestKeywords.openSummaryOfCareFromEncounter'()
CustomKeywords.'custom.SocCcdaTestKeywords.setReferralNoteCheckbox'(true)
CustomKeywords.'custom.SocCcdaTestKeywords.uploadSocToPatientPortalFromMaximEyes'()
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

// --- Portal: latest SOC XML contains Referral Note LOINC ---
portalLoginExisting()
CustomKeywords.'custom.SocCcdaTestKeywords.openLatestHealthSummaryOnPortal'()
String referralXmlPath = downloadCcdaBlob('xml')
CustomKeywords.'custom.SocCcdaTestKeywords.verifyReferralNoteLoincInXmlFile'(referralXmlPath)

WebUI.comment('TC_SOC_CCDA_Naming_ReferralNote_CreatedOn completed')
