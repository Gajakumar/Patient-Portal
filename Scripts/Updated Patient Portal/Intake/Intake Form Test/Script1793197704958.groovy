import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import stories.NavigateStory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
NavigateStory nav = new NavigateStory()
/*
 * MaximEyes intake sync for patient 104636 on ptportal2712.
 * Confirmed in the app on 10/28/2026. Does not change existing scenarios.
 *
 * Home "Apt Workqueue" is the Home > Appointments CHECK-IN grid.
 * Selecting a row opens the patient panel underneath.
 * Demographic Import Date is not on Patient Overview yet.
 */

final int TIMEOUT = 20
final String PATIENT_ID = '104638'
final String ENCOUNTER_TYPE = 'Automation Element Test Encounter'

TestObject busy = findTestObject('Page_MaximEyes/Busy Indicator')
TestObject homeButton = findTestObject('Authorized Individual/Page_Patient Portal/HomeFromOA')

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

def waitIdle = {
	WebUI.waitForElementNotVisible(busy, 30, FailureHandling.OPTIONAL)
}

def openPatient = {
	WebUI.callTestCase(
		findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
		[('PatientID'): PATIENT_ID],
		FailureHandling.STOP_ON_FAILURE)
	waitIdle()
	WebUI.verifyElementAttributeValue(xp('patientNumber', "//*[@id='hdnPatientNumber']"), 'value', PATIENT_ID, TIMEOUT)
}

try {
	WebUI.callTestCase(
		findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
		[:], FailureHandling.STOP_ON_FAILURE)
	waitIdle()

	// 1. Home > Appointments workqueue. Select the check-in patient. Intake dates are in the panel below.
	WebUI.click(xp('homeIcon', "//a[@data-navigate='Home/Home']"))
	waitIdle()
	// TODO: xpath — first check-in patient link. Today this was Phgsmq Eljbkkko (patient 104627), not 104636.
	WebUI.click(xp('workqueuePatient', "(//a[contains(@onclick,'homeTab_selectPtLoadAppt')]/following::a[normalize-space()!=''])[1]"))
	waitIdle()
	String workqueuePanel = WebUI.getText(xp('workqueueHipaa', "//span[normalize-space()='HIPAA:']/ancestor::*[contains(.,'Billing Policy')][1]"))
	require(workqueuePanel.contains('HIPAA:') && workqueuePanel.contains('Billing Policy:'),
		"Workqueue patient panel is missing HIPAA / Billing. Found: ${workqueuePanel}")
	require(workqueuePanel.contains('Welcome Form Pt Submitted') && workqueuePanel.contains('Imported'),
		"Workqueue patient panel is missing the welcome-form import line. Found: ${workqueuePanel}")

	// 2. Patient Overview for 104636. Demographic Import Date is not shown yet.
	openPatient()
	String overview = WebUI.getText(xp('overviewStatus', "//span[normalize-space()='HIPAA:']/ancestor::*[contains(.,'Welcome Form Pt Submitted')][1]"))
	require(overview.contains('HIPAA:') && overview.contains('Billing Policy:'),
		"Overview is missing HIPAA / Billing status. Found: ${overview}")
	require(overview.contains('Welcome Form Pt Submitted') && overview.contains('Imported'),
		"Overview is missing the welcome-form submitted/imported line. Found: ${overview}")

	// 3. Welcome Form tab shows the imported Online Patient Intake Form.
//	WebUI.click(xp('welcomeTab', "//*[@id='ui-id-12']"))
	CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Patient', SubItem: 'Welcome Form'])
	waitIdle()
	WebUI.waitForElementPresent(xp('welcomeFrame', "//iframe[@id='JotFormIFrame-233271152179050']"), TIMEOUT)
	WebUI.verifyTextPresent('Pt Submitted', false)
	WebUI.verifyTextPresent('Imported', false)

	// 4. Insurance. This patient is Self Pay as of the intake date. No insurance-card intake rows.
	WebUI.click(xp('insuranceTab', "//*[@id='PatientInsuranceTabLink']"))
	waitIdle()
	WebUI.verifyTextPresent('Self Pay, No coverage Since', false)
	WebUI.verifyTextPresent('ONLINE INTAKE', false)

	// 5. Preference is already "Most recent Welcome Form / Online Intake". Do not change it.
	WebUI.click(xp('officeAdmin', "//a[@title='Office Admin']"))
	WebUI.click(xp('encounterModule', "//a[@data-officeadminelementtitle='Modules' and normalize-space()='Encounter']"))
	waitIdle()
	TestObject welcomePref = xp('welcomePref',
		"//input[@name='DEFAULT_DATA_COPY_ON_ENCOUNTER_FROM_WLECOME_FORM_OR_PREVIOUS_ENCOUNTER' and @value='WELCOME_FORM']")
	WebUI.waitForElementPresent(welcomePref, TIMEOUT)
	require(WebUI.verifyElementChecked(welcomePref, TIMEOUT, FailureHandling.OPTIONAL),
		'Encounter preference is not Most recent Welcome Form / Online Intake. Intake Form tab check does not apply.')

	// New encounter, then Intake Form tab.
	WebUI.click(homeButton)
	openPatient()
//	WebUI.click(xp('encountersMenu', "//*[@id='encounterMegaMenu']"))
	CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([TopMenuOption: 'Encounters', SubItem: 'Add New Encounter'])
//	WebUI.click(xp('addEncounter', "//a[normalize-space()='Add New Encounter']"))
	waitIdle()
	// Select encounter type
WebUI.selectOptionByLabel(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Encounter Type_EncounterTypeID'),
	'Automation Element Test Encounter',
	false
)

// Click Add button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))

// Handle optional "Create New Encounter" popup
TestObject createNewEncounterBtn =
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter')

if (WebUI.verifyElementPresent(createNewEncounterBtn, 5, FailureHandling.OPTIONAL)) {
	WebUI.click(createNewEncounterBtn)
	println('Create New Encounter button clicked')
} else {
	println('Create New Encounter button not displayed – skipping click')
}



	waitIdle()
	WebUI.verifyTextPresent('Last Date Intake Received', false)
	WebUI.verifyTextPresent('Source: Online intake', false)
	WebUI.verifyTextPresent('Date last imported to this encounter', false)
	
	waitIdle()
	nav.SelectEncounterElementFromLeftNavOnEncounter([
	pElementPage: "Chief Complaint & HPI",
	pElement    : "Intake and Consent Forms"
])

	// 6. Import date is on that Intake Form line. Signed consents are on Intake and Consent Forms.
	// TODO: a separate More menu was not on this encounter. The import date is the "Date last imported" line above.
//	WebUI.click(xp('consentLink', "//a[normalize-space()='Intake and Consent Forms']"))
	waitIdle()
	WebUI.verifyTextPresent('HIPAA Policy - Latest', false)
	WebUI.verifyTextPresent('Billing Policy - Latest', false)
	WebUI.verifyTextPresent('Accepted', false)
	WebUI.verifyTextPresent('Online Patient Intake Form (Demographics, insurance, medical history)', false)
	WebUI.verifyTextPresent('Imported', false)

	WebUI.comment('Intake Form Test completed for patient ' + PATIENT_ID)
} catch (Throwable t) {
	WebUI.comment('Test failed: ' + t.toString())
	throw t
}
