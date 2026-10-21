package custom

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
import CustomKeywords

class MipsKno2Keywords {

	private static final String ME = 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/'
	private static final String ME_PAGE = 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/'
	private static final String REFERRAL_MEASURE = 'Support electronic referral loops by sending health information'

	private static TestObject xpath(String name, String xp) {
		TestObject to = new TestObject(name)
		to.addProperty('xpath', ConditionType.EQUALS, xp)
		return to
	}

	private static TestObject byXpath(String xp) {
		TestObject testObject = new TestObject()
		testObject.addProperty('xpath', ConditionType.EQUALS, xp)
		return testObject
	}

	@Keyword
	def addAllergy(Map data) {
		WebUI.click(byXpath('//div[contains(@class,"allergyWhiteSpace")]//ul//li[1]/a'))

		WebUI.setText(byXpath("//input[contains(@id,'REACTION_DATE') and contains(@class, 'dxeEditArea_Metropolis') and contains(@name,'REACTION_DATE')]"), data.Reaction_Date)
		WebUI.setText(byXpath("//input[contains(@id,'ALLERGY') and contains(@class, 'dxeEditArea_Metropolis') and contains(@name,'ALLERGY_GF')]"), data.Allergy)
		WebUI.sendKeys(byXpath("//input[contains(@id,'ALLERGY') and contains(@class, 'dxeEditArea_Metropolis') and contains(@name,'ALLERGY_GF')]"), Keys.TAB.toString())

		
		WebUI.setText(byXpath("//input[contains(@id,'ALLERGY_TYPE') and contains(@class, 'dxeEditArea_Metropolis') and contains(@name,'ALLERGY_TYPE')]"), data.Allergy_Type)
		WebUI.setText(byXpath("//input[contains(@id,'ALLERGY_TYPE') and contains(@class, 'dxeEditArea_Metropolis') and contains(@name,'ALLERGY_TYPE')]"), Keys.TAB.toString())
		WebUI.setText(byXpath("//input[contains(@id,'REACTION_GF') and contains(@class, 'dxeEditArea_Metropolis') and contains(@name,'REACTION_GF')]"), data.Reaction)
		WebUI.setText(byXpath("//input[contains(@id,'REACTION_GF') and contains(@class, 'dxeEditArea_Metropolis') and contains(@name,'REACTION_GF')]"), Keys.TAB.toString())
		WebUI.setText(byXpath("//input[contains(@id,'Severity_GF') and contains(@class, 'dxeEditArea_Metropolis') and contains(@name,'Severity_GF')]"), data.Severity)
		WebUI.setText(byXpath("//input[contains(@id,'Severity_GF') and contains(@class, 'dxeEditArea_Metropolis') and contains(@name,'Severity_GF')]"), Keys.TAB.toString())
		WebUI.setText(byXpath("(//table[contains(@id,'allergyHistoryGridView')]//input[contains(@class, 'dxeEditArea_Metropolis')])[6]"), data.Status)
		WebUI.setText(byXpath("(//table[contains(@id,'allergyHistoryGridView')]//input[contains(@class, 'dxeEditArea_Metropolis')])[6]"), Keys.TAB.toString())
		WebUI.setText(byXpath("(//table[contains(@id,'allergyHistoryGridView')]//input[contains(@class, 'dxeEditArea_Metropolis')])[7]"), data.Source)
	}

	@Keyword
	def openPromotingInteroperabilityReport() {
		WebUI.click(findTestObject(ME_PAGE + 'a_Reports_dropdown-toggle menu-large recentmodule'))
		WebUI.click(findTestObject(ME_PAGE + 'a_Incentive Programs'))
		WebUI.click(findTestObject(ME_PAGE + 'a_MIPS Reports'))
		WebUI.click(findTestObject(ME_PAGE + 'a_Promoting Interoperability'))
		WebUI.waitForElementVisible(findTestObject(ME_PAGE + 'input_ProvGroup_provFilterBoxPIM'), 30)
	}

	@Keyword
	def applyProviderAndTodayDateFilter(String providerSearch) {
		String today = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern('MM/dd/yyyy'))
		TestObject fromDate = xpath('pi_from_date', "//div[@id='ProInterGrid']/div[1]//input[1]")
		TestObject toDate = xpath('pi_to_date', "//div[@id='ProInterGrid']/div[1]//input[2]")
		if (WebUI.waitForElementPresent(fromDate, 5, FailureHandling.OPTIONAL)) {
			WebUI.setText(fromDate, today)
			WebUI.setText(toDate, today)
		} else {
			KeywordUtil.logInfo('PI date inputs not found under ProInterGrid div[1]; continuing with provider filter only.')
		}
		WebUI.setText(findTestObject(ME_PAGE + 'input_ProvGroup_provFilterBoxPIM'), providerSearch)
		WebUI.click(findTestObject(ME_PAGE + 'span_ipcert ipcert_icon-checked'))
		WebUI.click(findTestObject(ME_PAGE + 'button_GO'))
		WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30, FailureHandling.OPTIONAL)
		WebUI.delay(2)
	}

	@Keyword
	def verifyReferralLoopMeasureRowVisible() {
		TestObject row = xpath('referral_measure_row',
			"//tr[.//td[contains(normalize-space(.),'${REFERRAL_MEASURE}')]]")
		WebUI.verifyElementVisible(row, FailureHandling.STOP_ON_FAILURE)
	}

	@Keyword
	def verifyReferralLoopDenCount(String expected) {
		verifyReferralLoopMeasureCount('clsDen', expected)
	}

	@Keyword
	def verifyReferralLoopNumCount(String expected) {
		verifyReferralLoopMeasureCount('clsNum', expected)
	}

	private void verifyReferralLoopMeasureCount(String clsPrefix, String expected) {
		TestObject cell = xpath("referral_${clsPrefix}",
			"//tr[.//td[contains(normalize-space(.),'${REFERRAL_MEASURE}')]]/td[contains(@class,'${clsPrefix}')]")
		WebUI.waitForElementVisible(cell, 20)
		WebUI.verifyElementText(cell, expected)
	}

	@Keyword
	def addAllergyMedicationProblemAndReferralInPlans() {
		String today = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern('MM/dd/yyyy'))
		addAllergy([
			Reaction_Date: today,
			Allergy: 'Penicillins',
			Allergy_Type: 'Drug allergy',
			Reaction: 'Rash',
			Severity: 'Mild',
			Status: 'Active',
			Source: 'Patient',
		])

		WebUI.click(findTestObject(ME + 'Add Problem Plus button'))
		WebUI.click(findTestObject(ME + 'td_Asthma_DESCRIPTION_GF_df4a_GF_DDD_L_LBI3T0'))
		WebUI.click(findTestObject(ME + 'img_Loading_STATUS_GF_df4a_GF_B-1Img'))
		WebUI.click(findTestObject(ME + 'td_Loading_STATUS_GF_df4a_GF_DDD_L_LBI0T0'))
		WebUI.click(findTestObject(ME + 'td_Chronic_SOURCE_GF_df4a_GF_B-1'))
		WebUI.click(findTestObject(ME + 'td_Chronic_SOURCE_GF_df4a_GF_DDD_L_LBI0T0'))

		WebUI.click(findTestObject(ME + 'span_--Select--_glyphicon-circelplus font17_9bfae2'))
		WebUI.setText(findTestObject(ME + 'input_Search Drug_MEDICATION_BRAND_NAME_I'), 'Lipitor,atorvastatin')
		WebUI.click(findTestObject(ME + 'input_Directions to Pharmacist_Add'))
		WebUI.click(findTestObject(ME + 'input_Refills_btnOk'))

//		TestObject referralInPlans = xpath('plans_referral_link',
//			"//div[contains(@id,'encounterPlansData')]//*[self::a or self::span][contains(normalize-space(),'Referral')]")
//		if (WebUI.waitForElementClickable(referralInPlans, 8, FailureHandling.OPTIONAL)) {
//			WebUI.click(referralInPlans)
//		} else {
//			TestObject plansEditor = xpath('plans_editor', "//div[contains(@id,'encounterPlansData')]")
//			WebUI.click(plansEditor)
//			WebUI.sendKeys(plansEditor, 'Referral to specialist')
//		}

//		WebUI.click(findTestObject(ME + 'input_PLANS_Save'))
//		WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30, FailureHandling.OPTIONAL)
	}

	@Keyword
	def openSummaryOfCareFromEncounter() {
		CustomKeywords.'custom.SocCcdaTestKeywords.openSummaryOfCareFromEncounter'()
	}

	@Keyword
	def selectExternalPhysicianOnSoc(String lastName, String firstName) {
		TestObject extPhyBtn = xpath('soc_ext_phy_btn', "//span[@id='SendToExternalPhysicianPatientPortal']")
		WebUI.waitForElementClickable(extPhyBtn, 20)
		WebUI.click(extPhyBtn)

		TestObject grid = xpath('ext_phy_grid', "//div[@id='PatientExtPhysiciangrid']")
		WebUI.waitForElementVisible(grid, 20)

		TestObject search = xpath('ext_phy_search',
			"//div[@id='PatientExtPhysiciangrid']//input[@aria-label='Search in data grid' or contains(@class,'dx-texteditor-input')]")
		WebUI.waitForElementVisible(search, 15)
		WebUI.setText(search, lastName)
		WebUI.delay(1)

		TestObject row = xpath('ext_phy_row',
			"//div[@id='PatientExtPhysiciangrid']//tr[contains(@class,'dx-data-row')][td[normalize-space()='${lastName}']][td[normalize-space()='${firstName}']]")
		WebUI.waitForElementClickable(row, 15)
		WebUI.click(row)

//		TestObject saveBtn = xpath('soc_save',
//			"//div[contains(@class,'dialog') or contains(@class,'window')][.//*[contains(normalize-space(),'Summary of Care')]]//input[@id='Save' or @value='Save']")
//		if (WebUI.waitForElementClickable(saveBtn, 5, FailureHandling.OPTIONAL)) {
//			WebUI.click(saveBtn)
//		}
		WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30, FailureHandling.OPTIONAL)
	}

	@Keyword
	def uploadSocToPatientPortal() {
		CustomKeywords.'custom.SocCcdaTestKeywords.uploadSocToPatientPortalFromMaximEyes'()
	}
}
