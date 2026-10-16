import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

/**
 * MIPSKno2 — steps 5–11 (requires steps 1–4: patient with data, Phy Test external physician, provider).
 */
final String MIPS_PROVIDER = 'Katalon'
final String EXT_PHY_STEP4_LAST = 'Phy'
final String EXT_PHY_STEP4_FIRST = 'Test'
final String EXT_PHY_UPLOAD_LAST = 'KnoTwo'
final String EXT_PHY_UPLOAD_FIRST = 'Katalon'

//Login to maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on find patient
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Find Patient'))

//Search patient with first and last name
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_LastName'),
	'Count')

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_FirstName'),
	'Den')

//Click on search button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Active_btnSearchPatient'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//// 5) Select patient with all data
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Find Patient'))
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_LastName'),
//	GlobalVariable.PatientLastName)
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_FirstName'),
//	GlobalVariable.PatientFirstName)
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Active_btnSearchPatient'))
//WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([
	TopMenuOption: 'Encounters',
	SubItem: 'Encounter Hx'
])
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Delete available encounters'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'stories.NavigateStory.ClickMegaMenuItems'([
	TopMenuOption: 'Encounters',
	SubItem: 'Add New Encounter'
])

// 6) New encounter (today) + Allergy, Medication, Problem, Plans referral → save
CustomKeywords.'custom.CreateNewEncounter.fillNewEncounter'(
    'Automation Element Test Encounter',   // Encounter Type
    'Patient Portal',            // Exam Location
    '',           // Exam Date (MM/dd/yyyy)
    'Katalon Provider',         // Provider
    '',              // Technician (optional)
    ''                 // Referring physician (optional)
)
CustomKeywords.'custom.MipsKno2Keywords.addAllergyMedicationProblemAndReferralInPlans'()

// 7) MIPS PI report — Den = 1, data displayed
CustomKeywords.'custom.MipsKno2Keywords.openPromotingInteroperabilityReport'()
CustomKeywords.'custom.MipsKno2Keywords.applyProviderAndTodayDateFilter'(MIPS_PROVIDER)
CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopMeasureRowVisible'()
CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopDenCount'('1')

// 8) Health Information Exchange — electronic referral loops (Den count = 1, same as step 7)
CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopDenCount'('1')

// 9) SOC → external physician (step 4) → save
CustomKeywords.'custom.MipsKno2Keywords.openSummaryOfCareFromEncounter'()
CustomKeywords.'custom.MipsKno2Keywords.selectExternalPhysicianOnSoc'(EXT_PHY_UPLOAD_LAST, EXT_PHY_UPLOAD_FIRST)
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

// 10) MIPS PI report — Num = 1
CustomKeywords.'custom.MipsKno2Keywords.openPromotingInteroperabilityReport'()
CustomKeywords.'custom.MipsKno2Keywords.applyProviderAndTodayDateFilter'(MIPS_PROVIDER)
CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopMeasureRowVisible'()
CustomKeywords.'custom.MipsKno2Keywords.verifyReferralLoopNumCount'('1')

// 11) SOC → Upload → external physician Katalon KnoTwo
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Encounter Details'))
CustomKeywords.'custom.MipsKno2Keywords.openSummaryOfCareFromEncounter'()
CustomKeywords.'custom.MipsKno2Keywords.selectExternalPhysicianOnSoc'('KnoTwo', EXT_PHY_UPLOAD_LAST, EXT_PHY_UPLOAD_FIRST)
CustomKeywords.'custom.MipsKno2Keywords.uploadSocToPatientPortal'()
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

WebUI.comment('MIPSKno2 steps 5–11 completed')
