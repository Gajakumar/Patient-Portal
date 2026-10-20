import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
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

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/*
 * MBT 58366 - Maximeyes AI > Patient Portal section (UI consistency)
 * Covers steps: 2, 3, 4 (state check only), 5, 6
 * Skipped: navigation to Integrations > Maximeyes AI (already handled),
 *          step 7 (expiry passed -> MBT 55470),
 *          clicking the admin console button (bug 58383)
 *
 * PRECONDITION: you are already on the Maximeyes AI page, and the
 * Patient Portal license is purchased (Evaa portal) with module enabled
 * from backend. If the page is inside an iframe, add
 * WebUI.switchToFrame(findTestObject(...), 10) before running.
 */

// ---------- helper: build test objects from XPath (no Object Repository needed) ----------
TestObject xp(String name, String xpath) {
	TestObject to = new TestObject(name)
	to.addProperty('xpath', ConditionType.EQUALS, xpath)
	return to
}

// ---------- Locators ----------
TestObject ppCard        = xp('ppCard',        "//div[contains(@class,'card-div')][.//div[@class='cardTitle' and normalize-space()='PATIENT PORTAL']]")
TestObject ppHeader      = xp('ppHeader',      "//div[@class='cardTitle' and normalize-space()='PATIENT PORTAL']")
TestObject ppCheckbox    = xp('ppCheckbox',    "//input[@id='maximeyesPatientPortalCheckboxId']")
TestObject ppCheckboxLbl = xp('ppCheckboxLbl', "//input[@id='maximeyesPatientPortalCheckboxId']/ancestor::label")
TestObject ppExpiry      = xp('ppExpiry',      "//div[contains(@class,'expiryDateMaximeyesPatientPortal')]")
TestObject ppDescription = xp('ppDescription', "//div[contains(@class,'card-div')][.//div[normalize-space()='PATIENT PORTAL']]//div[@class='cardData']")
TestObject ppOpenBtn     = xp('ppOpenBtn',     "//button[@id='btnRedirPPTLink']")
TestObject vaOpenBtn     = xp('vaOpenBtn',     "//button[@id='evaaAdminPortalOpenBtnId']")
TestObject billingBtn    = xp('billingBtn',    "//button[@id='btnRedirFPHLink']")

int TIMEOUT = 15
String EXPECTED_DESC = 'It is a secure online website or app that allows patients to access their personal health information anytime and from anywhere.'

// ---------- helpers ----------
def isChecked = {
	return WebUI.executeJavaScript("return document.getElementById('maximeyesPatientPortalCheckboxId').checked", null) as boolean
}
def toggleModule = { boolean target ->
	if (isChecked() != target) {
		// JS click fires the same 'change' event as a real click (the native input is styled/hidden by the theme)
		WebUI.executeJavaScript("document.getElementById('maximeyesPatientPortalCheckboxId').click()", null)
		WebUI.delay(3) // allow ajax + busy indicator to finish
	}
}
def isBtnDisabled = {
	return WebUI.getAttribute(ppOpenBtn, 'disabled', FailureHandling.OPTIONAL) != null
}

final String PORTAL_COMMON = 'Test Cases/common/Patient_Portal_Common/'

def runCommon = { String name, Map params = [:] ->
	WebUI.callTestCase(findTestCase(PORTAL_COMMON + name), params, FailureHandling.STOP_ON_FAILURE)
}
// =====================================================================
// STEP 2 - Verify new 'Patient Portal' section components
// =====================================================================

runCommon('User Login in Maximeyes Pt Portal')

WebUI.click(findTestObject('Page_MaximEyes/OAButton'))
CustomKeywords.'stories.NavigateStory.ClickOnOfficeAdminNavigationTabs'([
	MainTab: 'General', SubTabL1: 'Integrations', SubTabL2: 'MaximEyes AI'])


WebUI.verifyElementPresent(ppCard, TIMEOUT)

// Header
WebUI.verifyElementPresent(ppHeader, TIMEOUT)
WebUI.verifyEqual(WebUI.getText(ppHeader).trim(), 'PATIENT PORTAL')

// Checkbox + label "Module enabled"
WebUI.verifyElementPresent(ppCheckbox, TIMEOUT)
WebUI.verifyMatch(WebUI.getText(ppCheckboxLbl).replace('\u00A0', ' ').trim(), 'Module enabled', false)

// Expiry field - "Expires MM/DD/YYYY" (read-only label, not an input)
WebUI.verifyElementPresent(ppExpiry, TIMEOUT)
WebUI.verifyMatch(WebUI.getText(ppExpiry).trim(), '^Expires \\d{2}/\\d{2}/\\d{4}$', true)
WebUI.verifyElementNotPresent(xp('ppExpiryInput', "//div[contains(@class,'expiryDateMaximeyesPatientPortal')]//input"), 2)

// Description text
WebUI.verifyEqual(WebUI.getText(ppDescription).trim(), EXPECTED_DESC)

// Button label
WebUI.verifyElementPresent(ppOpenBtn, TIMEOUT)
WebUI.verifyEqual(WebUI.getText(ppOpenBtn).trim(), 'OPEN PATIENT PORTAL ADMIN CONSOLE')

// =====================================================================
// STEP 3 - VIRTUAL ASSISTANT & BILLING ASSISTANT buttons
// =====================================================================
WebUI.verifyElementPresent(vaOpenBtn, TIMEOUT)
WebUI.verifyEqual(WebUI.getText(vaOpenBtn).trim(), 'OPEN EVAA VIRTUAL ASSISTANT ADMIN CONSOLE')

WebUI.verifyElementPresent(billingBtn, TIMEOUT)
WebUI.verifyEqual(WebUI.getText(billingBtn).trim(), 'OPEN EVAA BILLING ASSISTANT ADMIN CONSOLE')

// =====================================================================
// STEP 4 (state check) - license purchased + module enabled => button enabled
// (purchase itself is done manually on the Evaa portal as a precondition)
// =====================================================================
WebUI.comment('Precondition: license purchased on Evaa portal & module enabled from backend')
WebUI.verifyEqual(isChecked(), true, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyEqual(isBtnDisabled(), false, FailureHandling.CONTINUE_ON_FAILURE)

// =====================================================================
// STEP 5 & 6 - Manual disable / enable; button follows checkbox state
// =====================================================================
// --- Disable ---
toggleModule(false)
WebUI.verifyEqual(isChecked(), false, FailureHandling.CONTINUE_ON_FAILURE)
// Step 6: button auto-disabled once module disabled
WebUI.verifyEqual(isBtnDisabled(), true, FailureHandling.CONTINUE_ON_FAILURE)

// Persistence check - state survives a refresh

CustomKeywords.'stories.NavigateStory.ClickOnOfficeAdminNavigationTabs'([
	MainTab: 'General', SubTabL1: 'Tasks', SubTabL2: null])


CustomKeywords.'stories.NavigateStory.ClickOnOfficeAdminNavigationTabs'([
	MainTab: 'General', SubTabL1: 'Integrations', SubTabL2: 'MaximEyes AI'])

WebUI.waitForPageLoad(TIMEOUT)
WebUI.verifyElementPresent(ppCheckbox, TIMEOUT)
WebUI.verifyEqual(isChecked(), false, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyEqual(isBtnDisabled(), true, FailureHandling.CONTINUE_ON_FAILURE)

// --- Enable again ---
toggleModule(true)
WebUI.verifyEqual(isChecked(), true, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyEqual(isBtnDisabled(), false, FailureHandling.CONTINUE_ON_FAILURE)


CustomKeywords.'stories.NavigateStory.ClickOnOfficeAdminNavigationTabs'([
	MainTab: 'General', SubTabL1: 'Tasks', SubTabL2: null])


CustomKeywords.'stories.NavigateStory.ClickOnOfficeAdminNavigationTabs'([
	MainTab: 'General', SubTabL1: 'Integrations', SubTabL2: 'MaximEyes AI'])
WebUI.waitForPageLoad(TIMEOUT)
WebUI.verifyEqual(isChecked(), true, FailureHandling.CONTINUE_ON_FAILURE)
WebUI.verifyEqual(isBtnDisabled(), false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.comment('Skipped: step 7 (expiry passed -> MBT 55470) and button click (bug 58383)')