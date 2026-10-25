// ===================== IMPORTS =====================
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase // find a test case by path
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject // find a test object by path

import com.kms.katalon.core.model.FailureHandling // failure handling options (STOP_ON_FAILURE, OPTIONAL)
import com.kms.katalon.core.testobject.ConditionType // used for dynamic xpath objects
import com.kms.katalon.core.testobject.TestObject // TestObject type
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI // WebUI keywords
import internal.GlobalVariable as GlobalVariable // global variables
import org.openqa.selenium.Keys // keyboard keys
import stories.NavigateStory // custom navigation helper

// ===================== VARIABLES (EDIT HERE) =====================

// ---- Timeouts ----
final int TIMEOUT = 15 // default explicit wait in seconds
final int BUSY_TIMEOUT = 30 // wait for busy indicator to disappear

// ---- Test case paths ----
final String TC_LOGIN = 'Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal' // login test case
final String TC_CREATE_PATIENT = 'Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes' // random patient creation
final String TC_CREATE_ENCOUNTER = 'Test Cases/common/Maximeyes/Create New Encounter With Pro and Ext Phy' // encounter creation
final String TC_DELETE_ENCOUNTERS = 'Test Cases/common/Maximeyes/Delete available encounters' // encounter cleanup

// ---- Object repository prefixes ----
final String ME_MIX = 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/' // mixed MaximEyes objects
final String RPT = ME_MIX + 'Page_MaximEyes/' // report / menu objects
final String APPT = 'Appointments/Page_MaximEyes/' // office admin menu objects
final String USER_OR = 'Appointments/User/Page_MaximEyes/' // user screen objects
final String PROV_OR = 'Appointments/Provider/Page_MaximEyes/' // provider screen objects
final String EXT_OR = 'Provider Portal/Ext Phy/Page_MaximEyes/' // external physician objects
final String KNO2_OR = 'Kno2/Page_MaximEyes/' // Kno2 objects
final String OA_OR = 'OA Maximeyes/Page_MaximEyes/' // OA objects
final String BUSY = 'Page_MaximEyes/Busy Indicator' // busy indicator object
final String DEN_OBJ = '2710/Page_MaximEyes/Page_MaximEyes/Page_MaximEyes/Den Count Support electronic referral loops by sending health information' // Den count cell
final String NUM_OBJ = '2710/Page_MaximEyes/Page_MaximEyes/Page_MaximEyes/Num Count Support electronic referral loops by sending health information' // Num count cell

// ---- Provider / user data ----
final String PROV_FIRST = 'Zak' // provider first name (also used as search text)
final String PROV_LAST = 'Duckett' // provider last name
final String PROV_FULL = PROV_FIRST + ' ' + PROV_LAST // provider display name
final String PROV_NPI = '1111111111' // provider NPI
final String PROV_EMAIL = 'records@first-insight-cehrt.messaging.kno2fy-stage.com' // provider secure email
final String PROV_LOCATION = 'Hillsboro' // practice location
final String USER_PASSWORD = 'Test@1234' // password for new provider user
final String USER_LOGIN = 'ZakDuc' // login name for new provider user
final String AUTH_PASSWORD = '12345' // authority password

// ---- External physician data ----
final String EXT_FIRST = 'Jacob' // external physician first name
final String EXT_LAST = 'Duffy' // external physician last name
final String EXT_CLINIC = 'JD' // external physician clinic
final String EXT_EMAIL = 'maximeyesprof@first-insight-cehrt.messaging.kno2fy-stage.com' // external physician secure email
final String EXT_REFERRING = EXT_LAST + ' ' + EXT_FIRST // referring physician display name

// ---- Encounter data ----
final String ENC_TYPE = 'Automation Element Test Encounter' // encounter type

// ---- Expected report counts ----
final String DEN_EXPECTED = '1' // expected denominator
final String NUM_BEFORE_SEND = '0' // expected numerator before sending SOC
final String NUM_AFTER_SEND = '1' // expected numerator after sending SOC

// ---- Helper instance ----
NavigateStory nav = new NavigateStory() // navigation helper object

// ===================== HELPER CLOSURES =====================

// Calls a test case and stops the run on failure
def runTC = { String path, Map params = [:] ->
	WebUI.callTestCase(findTestCase(path), params, FailureHandling.STOP_ON_FAILURE) // execute the test case
}

// Waits until the busy spinner is gone
def waitBusy = { int sec = BUSY_TIMEOUT ->
	WebUI.waitForElementNotVisible(findTestObject(BUSY), sec) // wait for spinner to hide
}

// Reads and stores the Patient ID from the overview screen
def storePatientId = {
	TestObject idObj = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen') // patient id field
	WebUI.waitForElementVisible(idObj, TIMEOUT) // wait for field
	GlobalVariable.GV_PatientID = WebUI.getAttribute(idObj, 'value') ?: '' // save id (empty if null)
	println 'Patient ID: ' + GlobalVariable.GV_PatientID // log the id
}

// Creates a random unregistered patient
def createUnregisteredPatient = {
	runTC(TC_CREATE_PATIENT, [('phoneNumber'): GlobalVariable.Mobile, ('emailId'): GlobalVariable.MyEmail_Id]) // create patient
	waitBusy() // wait for page load
	storePatientId() // store patient id
}

// Opens Reports > Incentive Programs > MIPS Reports > Promoting Interoperability
def openPIReport = {
	WebUI.click(findTestObject(RPT + 'a_Reports_dropdown-toggle menu-large recentmodule')) // Reports menu
	WebUI.click(findTestObject(RPT + 'a_Incentive Programs')) // Incentive Programs
	WebUI.click(findTestObject(RPT + 'a_MIPS Reports')) // MIPS Reports
	WebUI.click(findTestObject(RPT + 'a_Promoting Interoperability')) // Promoting Interoperability
}

// Filters by provider, runs the report and verifies Den / Num counts
def runPIReportAndVerify = { String providerCheckboxObj, String expDen, String expNum ->
	openPIReport() // open the report
	WebUI.setText(findTestObject(RPT + 'input_ProvGroup_provFilterBoxPIM'), PROV_FIRST) // filter provider list
	WebUI.click(findTestObject(providerCheckboxObj)) // tick the provider checkbox
	WebUI.click(findTestObject(RPT + 'button_GO')) // run the report
	WebUI.verifyElementText(findTestObject(DEN_OBJ), expDen) // verify denominator
	WebUI.verifyElementText(findTestObject(NUM_OBJ), expNum) // verify numerator
}

// Adds Problem, Medication, Allergy, Final Diagnosis and Referral plan to the open encounter
def addEncounterElements = {
	// ---- Problem ----
	WebUI.click(findTestObject(ME_MIX + 'a_Intake Form_encTabList_1')) // Intake tab
	WebUI.delay(1) // let tab render
	WebUI.click(findTestObject(ME_MIX + 'Add Problem Plus button')) // add problem
	WebUI.setText(findTestObject(ME_MIX + 'input_Loading_DESCRIPTION_GF_b04f_GF_I'), 'Alcohol abuse') // problem name
	WebUI.click(findTestObject(ME_MIX + 'em')) // pick suggestion
	WebUI.click(findTestObject(ME_MIX + 'td_Loading_STATUS_GF_b04f_GF_B-1')) // open status dropdown
	WebUI.click(findTestObject(ME_MIX + 'td_Loading_STATUS_GF_b04f_GF_DDD_L_LBI0T0')) // choose status
	WebUI.click(findTestObject(ME_MIX + 'td_Chronic_SOURCE_GF_b04f_GF_B-1')) // open source dropdown
	WebUI.click(findTestObject(ME_MIX + 'td_Chronic_SOURCE_GF_b04f_GF_DDD_L_LBI0T0')) // choose source
	WebUI.click(findTestObject(ME_MIX + 'input_--Select--_problemListGridView_b04f_E_114e19')) // save problem
	WebUI.delay(1) // let grid refresh

	// ---- Medication ----
	WebUI.click(findTestObject(ME_MIX + 'span_--Select--_glyphicon-circelplus font17_9bfae2')) // add medication
	WebUI.setText(findTestObject(ME_MIX + 'input_Search Drug_MEDICATION_BRAND_NAME_I'), 'Lipitor,atorvastatin') // drug search
	WebUI.delay(1) // wait for results
	WebUI.click(findTestObject(ME_MIX + 'input_Directions to Pharmacist_Add')) // add drug
	WebUI.click(findTestObject(ME_MIX + 'input_Refills_btnOk')) // confirm refills

	// ---- Allergy ----
	CustomKeywords.'encounterElements.AllergyKeywords.addAllergy'('TODAY', 'Aspirin', 'Rash', 'Moderate', 'Active', 'Patient', true) // date, allergy, reaction, severity, status, source, verify

	// ---- Final Diagnosis ----
	nav.SelectEncounterElementFromLeftNavOnEncounter([pElementPage: 'Final Findings', pElement: 'Final Diagnoses']) // open Final Diagnoses
	WebUI.delay(2) // let page load
	WebUI.click(findTestObject(ME_MIX + 'span_Annual exam_glyphicon-circelplus font1_0c94a1')) // add diagnosis
	WebUI.waitForElementVisible(findTestObject(ME_MIX + 'input_Search_textbox'), TIMEOUT) // wait for search box
	WebUI.setText(findTestObject(ME_MIX + 'input_Search_textbox'), 'H disease') // diagnosis search text
	WebUI.click(findTestObject(ME_MIX + 'button_GO')) // search
	WebUI.waitForElementVisible(findTestObject(ME_MIX + 'td_H disease'), TIMEOUT) // wait for result
	WebUI.click(findTestObject(ME_MIX + 'td_H disease')) // select diagnosis

	// ---- Plan: refer to external physician ----
	WebUI.click(findTestObject(KNO2_OR + 'a_Plan')) // Plan tab
	WebUI.click(findTestObject(KNO2_OR + 'span_Refer to')) // Refer to
	WebUI.setText(findTestObject(KNO2_OR + 'input_Search'), EXT_FIRST) // search external physician
	WebUI.click(findTestObject(KNO2_OR + 'span_Physican External')) // select physician
	WebUI.click(findTestObject(KNO2_OR + 'button_btnIsPlans')) // add to plan
	WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_PLANS_Save')) // save plan
}

try { // main test flow
	// ===================== LOGIN =====================
	runTC(TC_LOGIN) // log in to MaximEyes

	// ===================== DELETE EXISTING USER (IF ANY) =====================
	WebUI.click(findTestObject(APPT + 'a_Office Admin')) // Office Admin
	WebUI.click(findTestObject(APPT + 'a_Business Administration')) // Business Administration
	WebUI.click(findTestObject(USER_OR + 'a_ui-id-23')) // Users tab

	TestObject userSearch = findTestObject(USER_OR + 'input_Search in data grid') // user grid search box
	TestObject userDelete = findTestObject(USER_OR + 'span_Delete') // user delete icon

	WebUI.setText(userSearch, PROV_FIRST) // search user
	WebUI.delay(3) // wait for grid refresh
	WebUI.waitForElementVisible(userDelete, 5, FailureHandling.OPTIONAL) // wait for delete icon (optional)

	if (WebUI.verifyElementPresent(userDelete, 3, FailureHandling.OPTIONAL)) { // user exists?
		WebUI.click(userDelete) // delete user
		WebUI.click(findTestObject(USER_OR + 'input_btnBP_Yes')) // confirm delete
		CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('User deleted successfully.') // verify toast
	} else {
		WebUI.comment("User '${PROV_FIRST}' not found, skipping delete.") // nothing to delete
	}

	// ===================== CREATE PROVIDER (IF NOT FOUND) =====================
	WebUI.click(findTestObject(APPT + 'a_Business Administration')) // Business Administration
	WebUI.click(findTestObject(PROV_OR + 'a_ui-id-22')) // Providers tab

	TestObject provSearch = findTestObject(PROV_OR + 'input_Search in data grid') // provider grid search box
	TestObject provDelete = findTestObject(PROV_OR + 'span_Delete') // provider delete icon

	WebUI.setText(provSearch, PROV_FIRST) // search provider
	WebUI.delay(3) // wait for grid refresh
	WebUI.waitForElementVisible(provDelete, 5, FailureHandling.OPTIONAL) // wait for delete icon (optional)

	if (WebUI.verifyElementPresent(provDelete, 3, FailureHandling.OPTIONAL)) { // provider exists?
		WebUI.comment("Provider '${PROV_FIRST}' found, no need to create new.") // reuse existing provider
	} else {
		WebUI.comment("Provider '${PROV_FIRST}' not found, creating new.") // create new provider
		WebUI.click(findTestObject(PROV_OR + 'span_BtnAddProvider')) // Add Provider
		WebUI.click(findTestObject(PROV_OR + 'input_btnSaveProvider')) // save empty form
		CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('You must fix all the validation errors.') // verify validation toast
		WebUI.setText(findTestObject(PROV_OR + 'input_Info_FirstName'), PROV_FIRST) // first name
		WebUI.setText(findTestObject(PROV_OR + 'input_Info_LastName'), PROV_LAST) // last name
		WebUI.setText(findTestObject(PROV_OR + 'input_Address_Email'), PROV_EMAIL) // email
		WebUI.click(findTestObject(PROV_OR + 'ins_jstree-icon')) // expand location tree
		WebUI.click(findTestObject(PROV_OR + 'ins_jstree-checkbox')) // tick location
		WebUI.setText(findTestObject(PROV_OR + 'input_PRV_NPI_8e7c9ebf'), PROV_NPI) // NPI
		WebUI.click(findTestObject(PROV_OR + 'input_btnSaveProvider')) // save provider
		CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Provider info added.') // verify success toast
	}

	// ===================== CREATE PROVIDER USER =====================
	WebUI.click(findTestObject(APPT + 'a_Business Administration')) // Business Administration
	WebUI.click(findTestObject(USER_OR + 'a_ui-id-23')) // Users tab
	WebUI.click(findTestObject(USER_OR + 'span_mif-circle-plus font20 fg-purple line-heigh')) // Add User
	WebUI.click(findTestObject(USER_OR + 'input_IsProviderSelectedForUser')) // mark user as provider
	WebUI.selectOptionByLabel(findTestObject(USER_OR + 'select_ProviderID'), PROV_FULL, false) // choose provider
	WebUI.selectOptionByLabel(findTestObject(USER_OR + 'select_PracticeLocationID'), PROV_LOCATION, false) // choose location
	WebUI.setText(findTestObject('Object Repository/OA Maximeyes/Page_MaximEyes/Secure Email_SecuredEmail'), PROV_EMAIL) // secure email

	WebUI.click(findTestObject(USER_OR + 'a_ui-id-28')) // permissions without location tab
	WebUI.click(findTestObject(USER_OR + 'input_chkAllPermissionWithoutLocation')) // select all
	WebUI.click(findTestObject(USER_OR + 'a_ui-id-26')) // permissions with location tab
	WebUI.click(findTestObject(USER_OR + 'input_chkAllPermissionWithLocation')) // select all
	WebUI.click(findTestObject(USER_OR + 'input_IsPracticeAdminForUser')) // practice admin
	WebUI.click(findTestObject(USER_OR + 'a_ui-id-29')) // open tab
	WebUI.click(findTestObject(USER_OR + 'a_ui-id-27')) // open tab

	WebUI.click(findTestObject(USER_OR + 'button_Set Password')) // set password
	WebUI.setText(findTestObject(USER_OR + 'input_Enter new password'), USER_PASSWORD) // new password
	WebUI.setText(findTestObject(USER_OR + 'input_Re-enter new password'), USER_PASSWORD) // confirm password
	WebUI.click(findTestObject(USER_OR + 'input_awyfbyzh')) // save password
	WebUI.setText(findTestObject(USER_OR + 'input_AuthorityPassword'), AUTH_PASSWORD) // authority password
	WebUI.click(findTestObject(USER_OR + 'input_SaveBtn')) // save user
	WebUI.click(findTestObject(USER_OR + 'input_btnOk')) // confirm

	// ===================== CREATE EXTERNAL PHYSICIAN =====================
	WebUI.click(findTestObject(EXT_OR + 'a_Value Lists')) // Value Lists
	WebUI.click(findTestObject(EXT_OR + 'a_ui-id-26')) // External Physicians tab

	TestObject extSearch = findTestObject(EXT_OR + 'input_Search in data grid') // ext physician search box
	WebUI.clearText(extSearch) // clear old text
	WebUI.setText(extSearch, EXT_FIRST) // search physician
	WebUI.sendKeys(extSearch, Keys.chord(Keys.ENTER)) // run search

	TestObject extRow = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//tr[.//td[normalize-space()='${EXT_FIRST}']]") // row of existing physician
	TestObject extDelete = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//td[normalize-space()='${EXT_LAST}']/following-sibling::td//span[contains(@title,'Delete')]") // delete icon of that row

	if (WebUI.verifyElementPresent(extRow, 5, FailureHandling.OPTIONAL)) { // physician already exists?
		WebUI.click(extDelete) // delete it
		WebUI.click(findTestObject(EXT_OR + 'input_btnBP_Yes')) // confirm
		WebUI.waitForElementNotPresent(extRow, 10) // verify removal
	}

	WebUI.click(findTestObject(EXT_OR + 'span_BtnExternalPhysician')) // Add External Physician
	WebUI.setText(findTestObject(EXT_OR + 'input_First Name'), EXT_FIRST) // first name
	WebUI.setText(findTestObject(EXT_OR + 'input_Last Name'), EXT_LAST) // last name
	WebUI.setText(findTestObject(EXT_OR + 'input_Info_Clinic'), EXT_CLINIC) // clinic
	WebUI.selectOptionByLabel(findTestObject(OA_OR + 'select_PhoneEmailModel_0_SelectedTypeId'), 'Secure Email', false) // contact type
	WebUI.setText(findTestObject(OA_OR + 'input_PhoneEmailEntry_2205fc3b'), EXT_EMAIL) // secure email

	['Referring', 'PCP', 'Surgeon', 'Other'].each { // tick every specialty
		WebUI.click(findTestObject("${EXT_OR}span_${it}")) // specialty checkbox
	}

	WebUI.click(findTestObject(EXT_OR + 'input_btnphysiciansave')) // save physician
	WebUI.delay(5) // wait for save to finish

	// ===================== LOG OUT / LOG IN AS NEW USER =====================
	WebUI.click(findTestObject('Object Repository/OA Maximeyes/LogOut Link')) // log out
	waitBusy(10) // wait for logout
	WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838'), USER_LOGIN) // username
	WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053'), USER_PASSWORD) // password
	WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027')) // login button
	waitBusy(10) // wait for login

	// ===================== PATIENT + ENCOUNTER =====================
	createUnregisteredPatient() // create random patient

	runTC(TC_CREATE_ENCOUNTER, [ // create encounter with provider and external physician
		('EncounterType')     : ENC_TYPE,
		('Location')          : PROV_LOCATION,
		('Provider')          : PROV_FULL,
		('ReferringPhysician'): EXT_REFERRING
	])

	addEncounterElements() // add problem, medication, allergy, FD and referral plan

	// ===================== REPORT CHECK 1: Den = 1, Num = 0 =====================
	runPIReportAndVerify('Object Repository/Page_MaximEyes/Page_MaximEyes/Select checkbox Prov', DEN_EXPECTED, NUM_BEFORE_SEND) // verify counts before sending

	// ===================== SEND SOC TO EXTERNAL PHYSICIAN =====================
	CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Encounters', SubItem: 'Encounter Details']) // open Encounter Details
	WebUI.click(findTestObject(KNO2_OR + 'span_More')) // More menu
	WebUI.click(findTestObject(KNO2_OR + 'span_spnMiscCommunicationStatus')) // communication status
	WebUI.click(findTestObject(KNO2_OR + 'span_SendToExternalPhysicianPatientPortal')) // send to external physician
	WebUI.setText(findTestObject(KNO2_OR + 'input_Search in data grid'), EXT_FIRST) // search physician
	WebUI.click(findTestObject(KNO2_OR + 'td_Extfirst')) // select physician
	WebUI.click(findTestObject(KNO2_OR + 'button_Close_1')) // close dialog

	// ===================== REPORT CHECK 2: Den = 1, Num = 1 =====================
	runPIReportAndVerify(RPT + 'span_ipcert ipcert_icon-checked', DEN_EXPECTED, NUM_AFTER_SEND) // verify counts after sending
} finally { // always runs, even if a step above failed
	// ===================== CLEANUP: DELETE ENCOUNTERS =====================
	try { // keep cleanup errors from hiding the original failure
		CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Encounters', SubItem: 'Encounter Hx']) // open Encounter History
		runTC(TC_DELETE_ENCOUNTERS) // delete encounters if available
	} catch (Exception e) { // cleanup itself failed
		WebUI.comment('Cleanup failed: ' + e.getMessage()) // log and continue
	}
}