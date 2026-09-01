import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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



////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_100414'), '100414')
////
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_100259'), '100259')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Automation Element Test Encounter'),
//	'Automation Element Test Encounter')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_01132026'), '01/13/2026')
////
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Patient Portal'), 'Patient Portal')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_Patient Chief Complaint'),
//	'Patient Chief Complaint')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Lipitor atorvastatin 10 617314'),
//	'Lipitor atorvastatin 10 [617314]')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Take 10 mg by mouth once a day'),
//	'Take 10 mg by mouth once a day')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_01132026_1'), '01/13/2026')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Problem List'), 'Problem List')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_195967001'), '195967001')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Asthma_1'), 'Asthma')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Active_1'), 'Active')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_01012026'), '01/01/2026')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Social History'), 'Social History')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Date'), 'Date Range')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_01132026_2'), '01/13/2026')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Birth Sex'), 'Birth Sex')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Male'), 'Male')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Vital Signs'), 'Vital Signs')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Weight'), 'Weight')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_123 lbs'), '123 lbs')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_HeightLength'), 'Height/Length')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_165.1 cm'), '165.1 cm')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Respiration rate'), 'Respiration rate')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_10 min'), '10 /min')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Body Temperature'), 'Body Temperature')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_80 F'), '80 F')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Pulse Oximetry'), 'Pulse Oximetry')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_40'), '40 %')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Inhaled Oxygen Concentration'),
//	'Inhaled Oxygen Concentration')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_90'), '90 %')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Heart Beat'), 'Heart Beat')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_123  min'), '123 /min')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Encounter Diagnoses'), 'Encounter Diagnoses')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Hartnups disease'), 'Hartnup\'s disease')
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Patient Portal_1'), 'Patient Portal')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Procedures'), 'Procedures')
//
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Automated VF, Central - 40'),
////	'Automated VF, Central - 40')
////
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Automated VF'), 'Automated VF')
////
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Completed'), 'Completed')
////
////WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Test Diag'), 'Test Diag')


import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import java.text.SimpleDateFormat
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/*
 * =============================================================================
 *  Summary of Care (C-CDA) - Verification Script
 * =============================================================================
 *  Verifies the data entered by the encounter-entry flow (HPI, Meds, Problem
 *  List, Vitals, Final Findings, Final Procedures) actually landed correctly
 *  on the generated Summary of Care / C-CDA page.
 *
 *  IMPORTANT - dynamic fields:
 *  Encounter Number and Encounter Date are generated by the system at save
 *  time and are DIFFERENT on every run. The original recorded script hard
 *  coded object locators built from a specific number/date (e.g. "td_100414",
 *  "td_01132026") - those locators literally embed the value they're supposed
 *  to verify, so they only ever match the run they were recorded on and were
 *  correctly commented out.
 *
 *  Fix applied here:
 *   - Encounter Date is verified against TODAY'S DATE, computed in Groovy at
 *     run time (MM/dd/yyyy, matching the page's format), not a hardcoded string.
 *   - Encounter Number is NOT locator-encoded. It's located by its COLUMN
 *     POSITION in the Encounter table (position-based XPath, built at
 *     runtime, not through a value-based Object Repository entry), then its
 *     text is captured and checked against a numeric-format pattern rather
 *     than an exact value. If you need to assert it matches the number the
 *     app displayed right after Save, capture that value in the entry script
 *     and pass it into this test case as a parameter, then compare here.
 *
 *  All checks reuse the same lean robust pattern from the entry script:
 *  ONE wait per check (no stacked present+visible+clickable waits), short
 *  timeout, capped retries, no blanket delays.
 *
 *  CALLING THIS AS A SUB TEST CASE:
 *    WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/SOC_Verification'),
 *        [:], FailureHandling.STOP_ON_FAILURE)
 *  Works as-is with an empty map - the date is computed internally, and the
 *  Encounter Number falls back to a format-only check when nothing is passed in.
 *
 *  To assert the Encounter Number matches the exact one the app showed right
 *  after Save (instead of just checking it looks numeric), do TWO things:
 *   1. In Katalon: open this Test Case > Variables tab > add a variable named
 *      `expectedEncounterNumber` with an empty-string default value. (Katalon
 *      only binds variables passed via callTestCase's map if they're declared
 *      here - undeclared names will fail to bind.)
 *   2. In the calling script, capture the number after Save and pass it in:
 *        String encounterNumber = WebUI.getText(findTestObject(<encounter number field after save>))
 *        WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/SOC_Verification'),
 *            ['expectedEncounterNumber': encounterNumber], FailureHandling.STOP_ON_FAILURE)
 * =============================================================================
 */

// ---------------------------------------------------------------------------
// Config
// ---------------------------------------------------------------------------
int TIMEOUT = 8
int RETRY_COUNT = 2
int RETRY_WAIT_MS = 300

// Expected date format shown on the Summary of Care page, e.g. "08/31/2026"
String todayDate = new SimpleDateFormat('MM/dd/yyyy').format(new Date())

// Optional Test Case Variable (declare in this Test Case's Variables tab as
// `expectedEncounterNumber`, default ''). When the caller passes a value via
// WebUI.callTestCase(..., ['expectedEncounterNumber': '...'], ...), Katalon
// binds it directly into this script under that name - no groovy declaration
// needed on this side. Guard with binding.hasVariable so the script still
// runs fine when called with an empty map (falls back to format-only check).
String expectedEncounterNumber = binding.hasVariable('expectedEncounterNumber') ? binding.getVariable('expectedEncounterNumber') : ''

// ---------------------------------------------------------------------------
// Robust wrapper keywords
// ---------------------------------------------------------------------------

/** Wait for an element to be visible, then verify its text. Retries only on genuine failure. */
def robustVerifyText = { TestObject to, String expected, String label = null ->
    String name = label ?: to.getObjectId()
    int attempt = 0
    Exception lastError = null
    while (attempt <= RETRY_COUNT) {
        try {
            WebUI.waitForElementVisible(to, TIMEOUT, FailureHandling.STOP_ON_FAILURE)
            WebUI.verifyElementText(to, expected, FailureHandling.STOP_ON_FAILURE)
            return
        } catch (Exception e) {
            lastError = e
            attempt++
            if (attempt > RETRY_COUNT) break
            KeywordUtil.logInfo("Verify retry ${attempt}/${RETRY_COUNT} on [${name}]: ${e.getMessage()}")
            WebUI.delay(RETRY_WAIT_MS / 1000.0)
        }
    }
    KeywordUtil.markFailedAndStop("Verification failed on [${name}]: expected '${expected}' - ${lastError?.getMessage()}")
}

/** Wait for an element to be visible, then return its text (for dynamic/format-only checks). */
def robustGetText = { TestObject to, String label = null ->
    String name = label ?: to.getObjectId()
    int attempt = 0
    Exception lastError = null
    while (attempt <= RETRY_COUNT) {
        try {
            WebUI.waitForElementVisible(to, TIMEOUT, FailureHandling.STOP_ON_FAILURE)
            return WebUI.getText(to, FailureHandling.STOP_ON_FAILURE)?.trim()
        } catch (Exception e) {
            lastError = e
            attempt++
            if (attempt > RETRY_COUNT) break
            KeywordUtil.logInfo("GetText retry ${attempt}/${RETRY_COUNT} on [${name}]: ${e.getMessage()}")
            WebUI.delay(RETRY_WAIT_MS / 1000.0)
        }
    }
    KeywordUtil.markFailedAndStop("Unable to read text from [${name}]: ${lastError?.getMessage()}")
    return null
}

/** Build a one-off TestObject from an XPath, without depending on the Object Repository. */
def xpathObject = { String xpath ->
    TestObject to = new TestObject()
    to.addProperty('xpath', ConditionType.EQUALS, xpath)
    return to
}

/**
 * Make sure the browser is actually looking at the Summary of Care content
 * before any assertion runs. Handles the three common reasons a fresh
 * xpath check comes back "element not found" even though the raw HTML
 * looks correct:
 *   1. Content is already in the current page/frame - nothing to do.
 *   2. Content opened in a NEW browser tab/window (e.g. a "view/generate
 *      SOC" action opens the generated document in a fresh tab).
 *   3. Content is rendered inside an <iframe> on the current page.
 * If none of these find it, fails fast with a clear message instead of
 * a bare "element not found" - most likely the calling test case hasn't
 * navigated to the SOC document yet.
 */
def ensureOnSummaryOfCarePage = {
    TestObject heading = xpathObject("//*[self::h1 or self::h2 or self::h3][contains(normalize-space(.),'Summary of Care')]")

    // Case 1: already visible in the current window/frame.
    if (WebUI.waitForElementPresent(heading, 3, FailureHandling.OPTIONAL)) {
        KeywordUtil.logInfo("Summary of Care heading found in current window/frame.")
        return
    }

    // Case 2: check other open browser windows/tabs.
    try {
        Set<String> handles = DriverFactory.getWebDriver().getWindowHandles()
        if (handles) {
            int idx = 0
            for (handle in handles) {
                WebUI.switchToWindowIndex(idx, FailureHandling.OPTIONAL)
                if (WebUI.waitForElementPresent(heading, 3, FailureHandling.OPTIONAL)) {
                    KeywordUtil.logInfo("Summary of Care heading found after switching to window index ${idx}.")
                    return
                }
                idx++
            }
        }
    } catch (Exception e) {
        KeywordUtil.logInfo("Window-handle search skipped: ${e.getMessage()}")
    }

    // Case 3: check iframes on the current page (switch back to default content first).
    try {
        WebUI.switchToDefaultContent()
        List<WebElement> frames = DriverFactory.getWebDriver().findElements(By.tagName('iframe'))
        for (int i = 0; i < frames.size(); i++) {
            TestObject frameObj = xpathObject("(//iframe)[${i + 1}]")
            if (WebUI.switchToFrame(frameObj, 3, FailureHandling.OPTIONAL)) {
                if (WebUI.waitForElementPresent(heading, 3, FailureHandling.OPTIONAL)) {
                    KeywordUtil.logInfo("Summary of Care heading found inside iframe #${i + 1}.")
                    return
                }
                WebUI.switchToDefaultContent()
            }
        }
    } catch (Exception e) {
        KeywordUtil.logInfo("Iframe search skipped: ${e.getMessage()}")
    }

    KeywordUtil.markFailedAndStop("Could not locate the Summary of Care page in the current window, any other open window, or any iframe. " +
        "Check that the calling test case actually navigates to / opens the generated SOC document before calling this verification.")
}

// ---------------------------------------------------------------------------
// Verification Flow
// ---------------------------------------------------------------------------
try {

    ensureOnSummaryOfCarePage()

    KeywordUtil.logInfo("=== SECTION: Encounter (dynamic Number / Date) ===")

    // Encounter table row 1: [1]=Number [2]=Title [3]=Type [4]=Diagnosis [5]=Date [6]=Provider [7]=Location
    // Position-based, NOT value-based, so it keeps working when the number/date change every run.
    def encounterNumberObj = xpathObject("(//div[@id='Encounter ']//table/tbody/tr[1]/td)[1]")
    def encounterDateObj   = xpathObject("(//div[@id='Encounter ']//table/tbody/tr[1]/td)[5]")

    String encounterNumber = robustGetText(encounterNumberObj, 'Encounter Number cell')
    if (!(encounterNumber ==~ /\d{3,8}/)) {
        KeywordUtil.markFailedAndStop("Encounter Number '${encounterNumber}' does not look like a valid numeric encounter id")
    }
    if (expectedEncounterNumber?.trim()) {
        // Caller passed the number captured right after Save - assert exact match.
        if (encounterNumber != expectedEncounterNumber.trim()) {
            KeywordUtil.markFailedAndStop("Encounter Number mismatch: Summary of Care shows '${encounterNumber}', expected '${expectedEncounterNumber}'")
        }
        KeywordUtil.logInfo("Encounter Number verified exact match: ${encounterNumber}")
    } else {
        // No expected value supplied - just confirm it's a well-formed encounter id.
        KeywordUtil.logInfo("Encounter Number captured: ${encounterNumber} (format OK, no exact-match value was passed in)")
    }

    robustVerifyText(encounterDateObj, todayDate, 'Encounter Date cell (today: ' + todayDate + ')')

    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Automation Element Test Encounter'),
        'Automation Element Test Encounter', 'Encounter Title cell')

    KeywordUtil.logInfo("=== SECTION: Chief Complaint ===")
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_Patient Chief Complaint'),
        'Patient Chief Complaint', 'Chief Complaint list item')

    KeywordUtil.logInfo("=== SECTION: Medications ===")
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Lipitor atorvastatin 10 617314'),
        'Lipitor atorvastatin 10 [617314]', 'Medication name cell')

    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Take 10 mg by mouth once a day'),
        'Take 10 mg by mouth once a day', 'Medication directions cell')

    KeywordUtil.logInfo("=== SECTION: Problem List ===")
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_195967001'), '195967001', 'Problem SNOMED code')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Asthma_1'), 'Asthma', 'Problem description cell')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Active_1'), 'Active', 'Problem status cell')

    KeywordUtil.logInfo("=== SECTION: Demographics ===")
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Birth Sex'), 'Birth Sex', 'Birth Sex label')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Male'), 'Male', 'Birth Sex value')

    KeywordUtil.logInfo("=== SECTION: Vital Signs ===")
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Weight'), 'Weight', 'Weight label')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_123 lbs'), '123 lbs', 'Weight value')

    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_HeightLength'), 'Height/Length', 'Height label')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_165.1 cm'), '165.1 cm', 'Height value')

    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Respiration rate'), 'Respiration rate', 'Respiration rate label')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_10 min'), '10 /min', 'Respiration rate value')

    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Body Temperature'), 'Body Temperature', 'Body Temperature label')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_80 F'), '80 F', 'Body Temperature value')

    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Pulse Oximetry'), 'Pulse Oximetry', 'Pulse Oximetry label')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_40'), '40 %', 'Pulse Oximetry value')

    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Inhaled Oxygen Concentration'),
        'Inhaled Oxygen Concentration', 'Inhaled Oxygen Concentration label')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_90'), '90 %', 'Inhaled Oxygen Concentration value')

    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Heart Beat'), 'Heart Beat', 'Heart Beat label')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_123  min'), '123 /min', 'Heart Beat value')

    KeywordUtil.logInfo("=== SECTION: Encounter Diagnoses (Final Findings) ===")
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Hartnups disease'), "Hartnup's disease", 'Encounter Diagnosis cell')
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Patient Portal_1'), 'Patient Portal', 'Encounter Diagnosis location cell')

    KeywordUtil.logInfo("=== SECTION: Services and Procedures (Final Procedures) ===")
    // Position-based rather than an exact recorded object, since row content shifts if more
    // procedures are added; still explicit enough to be unambiguous for this single-row table.
    def procedureLabelObj = xpathObject("//div[@id='Services and Procedures Section']//table/tbody/tr[1]/td[1]")
    def procedureValueObj = xpathObject("//div[@id='Services and Procedures Section']//table/tbody/tr[1]/td[2]")
    robustVerifyText(procedureLabelObj, 'Procedures', 'Services/Procedures label cell')
    robustVerifyText(procedureValueObj, '99214 - Comprehensive exam, Est. Patient', 'Services/Procedures value cell')

    KeywordUtil.logInfo("=== Verification completed successfully ===")

} catch (Exception e) {
    KeywordUtil.markFailedAndStop("Summary of Care verification failed: ${e.getMessage()}")
} finally {
    try {
        WebUI.switchToDefaultContent()
    } catch (Exception ignored) { }
    try {
        WebUI.takeScreenshot()
    } catch (Exception ignored) {
        KeywordUtil.logInfo("Screenshot capture skipped/failed: ${ignored.getMessage()}")
    }
}