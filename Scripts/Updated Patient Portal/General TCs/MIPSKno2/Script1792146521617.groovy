import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.Keys
//
///**
// * MIPSKno2 — steps 5–11 (requires steps 1–4: patient with data, Phy Test external physician, provider).
// */
//final String MIPS_PROVIDER = 'Katalon'
//final String EXT_PHY_STEP4_LAST = 'Phy'
//final String EXT_PHY_STEP4_FIRST = 'Test'
//final String EXT_PHY_UPLOAD_LAST = 'KnoTwo'
//final String EXT_PHY_UPLOAD_FIRST = 'Katalon'
//
////Login to maximeyes
//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)
//
////Click on find patient
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Find Patient'))
//
////Search patient with first and last name
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_LastName'),
//	'Count')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_FirstName'),
//	'Den')
//
////Click on search button
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Active_btnSearchPatient'))
//
//WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
//
////// 5) Select patient with all data
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Find Patient'))
////WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_LastName'),
////	GlobalVariable.PatientLastName)
////WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_FirstName'),
////	GlobalVariable.PatientFirstName)
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Active_btnSearchPatient'))
////WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
//
//CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([
//	TopMenuOption: 'Encounters',
//	SubItem: 'Encounter Hx'
//])
//WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Delete available encounters'), [:], FailureHandling.STOP_ON_FAILURE)
//
//CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([
//	TopMenuOption: 'Encounters',
//	SubItem: 'Add New Encounter'
//])
//
//// 6) New encounter (today) + Allergy, Medication, Problem, Plans referral → save
//CustomKeywords.'custom.CreateNewEncounter.fillNewEncounter'(
//    'Automation Element Test Encounter',   // Encounter Type
//    'Patient Portal',            // Exam Location
//    '',           // Exam Date (MM/dd/yyyy)
//    'Katalon Provider',         // Provider
//    '',              // Technician (optional)
//    ''                 // Referring physician (optional)
//)
//CustomKeywords.'custom.MipsKno2Keywords.addAllergyMedicationProblemAndReferralInPlans'()
//
//// 7) MIPS PI report — Den = 1, data displayed
//CustomKeywords.'custom.MipsKno2Keywords.openPromotingInteroperabilityReport'()
//CustomKeywords.'custom.MipsKno2Keywords.applyProviderAndTodayDateFilter'(MIPS_PROVIDER)
//CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopMeasureRowVisible'()
//CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopDenCount'('1')
//
//// 8) Health Information Exchange — electronic referral loops (Den count = 1, same as step 7)
//CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopDenCount'('1')
//
//// 9) SOC → external physician (step 4) → save
//CustomKeywords.'custom.MipsKno2Keywords.openSummaryOfCareFromEncounter'()
//CustomKeywords.'custom.MipsKno2Keywords.selectExternalPhysicianOnSoc'(EXT_PHY_UPLOAD_LAST, EXT_PHY_UPLOAD_FIRST)
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))
//
//// 10) MIPS PI report — Num = 1
//CustomKeywords.'custom.MipsKno2Keywords.openPromotingInteroperabilityReport'()
//CustomKeywords.'custom.MipsKno2Keywords.applyProviderAndTodayDateFilter'(MIPS_PROVIDER)
//CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopMeasureRowVisible'()
//CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopNumCount'('1')
//
//// 11) SOC → Upload → external physician Katalon KnoTwo
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Encounter Details'))
//CustomKeywords.'custom.MipsKno2Keywords.openSummaryOfCareFromEncounter'()
//CustomKeywords.'custom.MipsKno2Keywords.selectExternalPhysicianOnSoc'('KnoTwo', EXT_PHY_UPLOAD_LAST, EXT_PHY_UPLOAD_FIRST)
//CustomKeywords.'custom.MipsKno2Keywords.uploadSocToPatientPortal'()
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))
//
//WebUI.comment('MIPSKno2 steps 5–11 completed')



import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.katalon.webui.keyword.action.image.WebUIAbstractImageActionKeyword
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

final String PORTAL_COMMON = 'Test Cases/common/Patient_Portal_Common/'
final String ME_COMMON = 'Test Cases/common/Maximeyes/'
final String PP_SIGNIN = 'Object Repository/PatientPortal/SignInPage_Patient Portal/'
final String ME_MIX = 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/'
final String SOC_OA = 'Object Repository/SOC Upload/Page_MaximEyes/'

final int TIMEOUT = 15

NavigateStory nav = new NavigateStory()

def runCommon = { String name, Map params = [:] ->
	WebUI.callTestCase(findTestCase(PORTAL_COMMON + name), params, FailureHandling.STOP_ON_FAILURE)
}

def runMe = { String name, Map params = [:] ->
	WebUI.callTestCase(findTestCase(ME_COMMON + name), params, FailureHandling.STOP_ON_FAILURE)
}

def storePatientId = {
	TestObject patientIdObj = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen')
	WebUI.waitForElementVisible(patientIdObj, TIMEOUT)
	GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''
	println 'Patient ID: ' + GlobalVariable.GV_PatientID
}

def createUnregisteredPatient = {
	runCommon('Create Random Patient in Maximeyes', [
		('phoneNumber'): GlobalVariable.Mobile,
		('emailId')    : GlobalVariable.MyEmail_Id
	])
	WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
	storePatientId()

}

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
	
	//Allergy
	CustomKeywords.'encounterElements.AllergyKeywords.addAllergy'(
		'TODAY',        // reactionDate (MM/dd/yyyy, or TODAY)
		'Aspirin',      // allergy
		'Rash',         // reaction
		'Moderate',     // severity
		'Active',       // status
		'Patient',      // source
		true)           // verify (optional, default true)

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
	
	WebUI.click(findTestObject('Kno2/Page_MaximEyes/a_Plan'))
	
	WebUI.click(findTestObject('Kno2/Page_MaximEyes/span_Refer to'))
	
	WebUI.setText(findTestObject('Kno2/Page_MaximEyes/input_Search'), 'Jacob')
	
	WebUI.click(findTestObject('Kno2/Page_MaximEyes/span_Physican External'))
	
	WebUI.click(findTestObject('Kno2/Page_MaximEyes/button_btnIsPlans'))
	
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_PLANS_Save'))

	
}

// ================= LOGIN =================
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

// ================= NAVIGATION =================
WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Office Admin'))
WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Business Administration'))
WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-23'))

// ================= SEARCH & DELETE (IF EXISTS) =================
String userName = 'Zak'

TestObject searchBoxUser = findTestObject('Appointments/User/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtnUser = findTestObject('Appointments/User/Page_MaximEyes/span_Delete')

WebUI.setText(searchBoxUser, userName)
WebUI.delay(3)
// Wait for grid refresh
WebUI.waitForElementVisible(deleteBtnUser, 5, FailureHandling.OPTIONAL)

if (WebUI.verifyElementPresent(deleteBtnUser, 3, FailureHandling.OPTIONAL)) {
	
	WebUI.click(deleteBtnUser)
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_btnBP_Yes'))

	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"User deleted successfully."
	)

} else {
	WebUI.comment("⚠️ User '${userName}' not found, skipping delete.")
}

// ================= NAVIGATION =================

WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Business Administration'))
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/a_ui-id-22'))

// ================= SEARCH & DELETE (IF EXISTS) =================
String providerName = 'Zak'

TestObject searchBoxPro = findTestObject('Appointments/Provider/Page_MaximEyes/input_Search in data grid')
TestObject deleteBtnProv = findTestObject('Appointments/Provider/Page_MaximEyes/span_Delete')

WebUI.setText(searchBoxPro, providerName)
WebUI.delay(3)
// Wait for grid refresh
WebUI.waitForElementVisible(deleteBtnProv, 5, FailureHandling.OPTIONAL)

if (WebUI.verifyElementPresent(deleteBtnProv, 3, FailureHandling.OPTIONAL)) {
	
//	WebUI.click(deleteBtn)
//	WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_btnBP_Yes'))
//
//	// Verify toast message
//	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
//		"Provider info deleted."
//	)
	WebUI.comment("⚠️ Provider '${providerName}' Found, no need to create new")

} else {
	WebUI.comment("⚠️ Provider '${providerName}' not found, creating new")


// ================= ADD PROVIDER =================
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/span_BtnAddProvider'))

// ================= SAVE =================
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_btnSaveProvider'))

// ================= VERIFY toast =================
// Verify toast message
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
	"You must fix all the validation errors."
)


// ================= BASIC INFO =================
WebUI.setText(
	findTestObject('Appointments/Provider/Page_MaximEyes/input_Info_FirstName'),
	'Zak'
)

WebUI.setText(
	findTestObject('Appointments/Provider/Page_MaximEyes/input_Info_LastName'),
	'Duckett'
)

//// ================= PROVIDER TYPE =================
//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/button_Select options'))
//WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_OD'))

// ================= CONTACT INFO =================
WebUI.setText(
	findTestObject('Appointments/Provider/Page_MaximEyes/input_Address_Email'),
	'records@first-insight-cehrt.messaging.kno2fy-stage.com'
)

// ================= LOCATION SELECTION =================
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/ins_jstree-icon'))
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/ins_jstree-checkbox'))

// ================= NPI =================
WebUI.setText(
	findTestObject('Appointments/Provider/Page_MaximEyes/input_PRV_NPI_8e7c9ebf'),
	'1111111111'
)

// ================= SAVE =================
WebUI.click(findTestObject('Appointments/Provider/Page_MaximEyes/input_btnSaveProvider'))

// ================= VERIFY SUCCESS =================
	// Verify toast message
	CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'(
		"Provider info added."
	)
	
}
	//==================Create Provider User============

	WebUI.click(findTestObject('Appointments/Page_MaximEyes/a_Business Administration'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-23'))
	
	// Click Add User button
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/span_mif-circle-plus font20 fg-purple line-heigh'))
	
	// Enable Provider selection
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_IsProviderSelectedForUser'))
	
	// Select Provider and Location
// Select Provider by visible text
WebUI.selectOptionByLabel(
	findTestObject('Appointments/User/Page_MaximEyes/select_ProviderID'),
	'Zak Duckett',
	false
)

// Select Practice Location by visible text
WebUI.selectOptionByLabel(
	findTestObject('Appointments/User/Page_MaximEyes/select_PracticeLocationID'),
	'Hillsboro',
	false
)

WebUI.setText(
	findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/Secure Email_SecuredEmail'),
	'records@first-insight-cehrt.messaging.kno2fy-stage.com'
)
	
	// Permissions - Without Location
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-28'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_chkAllPermissionWithoutLocation'))
	
	// Permissions - With Location
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-26'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_chkAllPermissionWithLocation'))
	
	// Set Practice Admin
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_IsPracticeAdminForUser'))
	
	// Navigate Tabs
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-29'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/a_ui-id-27'))
	
	// Set Password
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/button_Set Password'))
	
	WebUI.setText(
		findTestObject('Appointments/User/Page_MaximEyes/input_Enter new password'),
		'Test@1234'
	)
	
	WebUI.setText(
		findTestObject('Appointments/User/Page_MaximEyes/input_Re-enter new password'),
		'Test@1234'
	)
	
	// click on save button
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_awyfbyzh'))
	
	// Authority Password
	WebUI.setText(
		findTestObject('Appointments/User/Page_MaximEyes/input_AuthorityPassword'),
		'12345'
	)
	
	// Save and Confirm
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_SaveBtn'))
	WebUI.click(findTestObject('Appointments/User/Page_MaximEyes/input_btnOk'))
	
	
	WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/a_Value Lists'))
	WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/a_ui-id-26'))
	
	
	TestObject searchBoxExt = findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_Search in data grid')
	
	WebUI.clearText(searchBoxExt)
	WebUI.setText(searchBoxExt, 'Jacob')
	WebUI.sendKeys(searchBoxExt, Keys.chord(Keys.ENTER))
	
	TestObject row = new TestObject().addProperty(
		"xpath",
		ConditionType.EQUALS,
		"//tr[.//td[normalize-space()='Jacob']]"
	)
	
	TestObject deleteBtnExt = new TestObject().addProperty(
		"xpath",
		ConditionType.EQUALS,
		"//td[normalize-space()='Duffy']/following-sibling::td//span[contains(@title,'Delete')]"
	)
	
	if (WebUI.verifyElementPresent(row, 5, FailureHandling.OPTIONAL)) {
	
		WebUI.click(deleteBtnExt)
		WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_btnBP_Yes'))
	
		// verify deletion
		WebUI.waitForElementNotPresent(row, 10)
	}
	
	// ===============================
	// 🔹 Create External Physician (without email)
	// ===============================
	WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/span_BtnExternalPhysician'))
	
	WebUI.setText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_First Name'), 'Jacob')
	WebUI.setText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_Last Name'), 'Duffy')
	WebUI.setText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_Info_Clinic'), 'JD')
	
	WebUI.selectOptionByLabel(
    findTestObject('OA Maximeyes/Page_MaximEyes/select_PhoneEmailModel_0_SelectedTypeId'),
    'Secure Email',
    false
)
	
	WebUI.setText(findTestObject('OA Maximeyes/Page_MaximEyes/input_PhoneEmailEntry_2205fc3b'), 'maximeyesprof@first-insight-cehrt.messaging.kno2fy-stage.com')
	
	// Select all specialties
	['Referring','PCP','Surgeon','Other'].each {
		WebUI.click(findTestObject("Provider Portal/Ext Phy/Page_MaximEyes/span_${it}"))
	}
	
	WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_btnphysiciansave'))
	WebUI.delay(5)
	
	WebUI.click(findTestObject('Object Repository/OA Maximeyes/LogOut Link'))
	WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 10)


WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838'),'ZakDuc')

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053'), 'Test@1234')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 10)
	
//Create random patient
	createUnregisteredPatient()
	
	WebUI.callTestCase(
    findTestCase('Test Cases/common/Maximeyes/Create New Encounter With Pro and Ext Phy'),
    [
        ('EncounterType')     : 'Automation Element Test Encounter',
        ('Location')          : 'Hillsboro',
        ('Provider')          : 'Zak Duckett',
        ('ReferringPhysician'): 'Duffy Jacob'
    ],
    FailureHandling.STOP_ON_FAILURE
)
	
	addMentionedEncounterElements()
	
	//Navigate to Reports
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Reports_dropdown-toggle menu-large recentmodule'))
	
	//Click on Incentive Programs
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Incentive Programs'))
	
	//Click on MIPS Reports
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_MIPS Reports'))
	
	//Click on Promoting Interoperability reports
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Promoting Interoperability'))
	
	//Select provider check box
	WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_ProvGroup_provFilterBoxPIM'),
		'Zak')
	
	WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Page_MaximEyes/Select checkbox Prov'))
	
	//Click on go button
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/button_GO'))
	
	//Verify den count
	WebUI.verifyElementText(findTestObject('2710/Page_MaximEyes/Page_MaximEyes/Page_MaximEyes/Den Count Support electronic referral loops by sending health information'), '1')
	
	//Verify Num count
	WebUI.verifyElementText(findTestObject('2710/Page_MaximEyes/Page_MaximEyes/Page_MaximEyes/Num Count Support electronic referral loops by sending health information'), '0')
	
//	//Verify percentage
//	WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/td_25_1'),
//		'25')
//	
	
	CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([
		TopMenuOption: 'Encounters',
		SubItem: 'Encounter Details'
	])
	
	WebUI.click(findTestObject('Kno2/Page_MaximEyes/span_More'))
	
	WebUI.click(findTestObject('Kno2/Page_MaximEyes/span_spnMiscCommunicationStatus'))
	
	WebUI.click(findTestObject('Kno2/Page_MaximEyes/span_SendToExternalPhysicianPatientPortal'))
	
	WebUI.setText(findTestObject('Kno2/Page_MaximEyes/input_Search in data grid'), 'Jacob')
	
	WebUI.click(findTestObject('Kno2/Page_MaximEyes/td_Extfirst'))
	
	
	WebUI.click(findTestObject('Kno2/Page_MaximEyes/button_Close_1'))
	
	
	//Navigate to Reports
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Reports_dropdown-toggle menu-large recentmodule'))
	
	//Click on Incentive Programs
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Incentive Programs'))
	
	//Click on MIPS Reports
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_MIPS Reports'))
	
	//Click on Promoting Interoperability reports
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Promoting Interoperability'))
	
	//Select provider check box
	WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_ProvGroup_provFilterBoxPIM'),
		'Zak')
	
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/span_ipcert ipcert_icon-checked'))
	
	//Click on go button
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/button_GO'))
	
	//Verify den count
	WebUI.verifyElementText(findTestObject('2710/Page_MaximEyes/Page_MaximEyes/Page_MaximEyes/Den Count Support electronic referral loops by sending health information'), '1')
	
	//Verify Num count
	WebUI.verifyElementText(findTestObject('2710/Page_MaximEyes/Page_MaximEyes/Page_MaximEyes/Num Count Support electronic referral loops by sending health information'), '1')
