import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

/*
 * 1) Login MaximEyes
 * 2) Select patient (create random for stable run)
 * 3) Encounter Details -> Hamburger -> Summary Of Care (C-CDA)
 * 4) SOC opens
 * 5) Check Referral Note
 * 6) Click Gear -> SWW must NOT appear
 */

final String ME_MIX = 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/'

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'), [
	('phoneNumber'): GlobalVariable.Mobile,
	('emailId')    : GlobalVariable.MyEmail_Id
], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Add New Encounter_Create New'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Enter Data In Enc Elements'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'custom.SocCcdaTestKeywords.openSummaryOfCareFromEncounter'()

WebUI.verifyElementText(findTestObject(ME_MIX + 'h2_Summary of Care (C-CDA)'), 'Summary of Care (C-CDA)')

CustomKeywords.'custom.SocCcdaTestKeywords.setReferralNoteCheckbox'(true)
CustomKeywords.'custom.SocCcdaTestKeywords.clickSocGearIconOnMaximEyes'()
CustomKeywords.'custom.SocCcdaTestKeywords.verifySomethingWentWrongNotShown'(8)

WebUI.comment('TC_SOC_ReferralNote_GearIcon_No_SWW completed')
