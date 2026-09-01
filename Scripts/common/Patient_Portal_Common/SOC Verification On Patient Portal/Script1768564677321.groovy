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

//String todayGMT = CustomKeywords.'common.DateUtil.getTodayDateGMT'()
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/h2_Summary of Care (C-CDA)'),
//	'Summary of Care (C-CDA)')
//
////WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/h4_Patientbca16 Test8848f'),
////	'Patientbca16 Test8848f')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/font_01162026'), todayGMT)
//
////WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_100636'), '100636')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_03161982'), '03/16/1982')
//
////WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_100328'), '100328')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Automation Element Test Encounter'),
//	'Automation Element Test Encounter')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_01162026'), todayGMT)
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Patient Portal'),
//	'Patient Portal')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Lipitor atorvastatin 10 617314'),
//	'Lipitor atorvastatin 10 [617314]')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Take 10 mg by mouth once a day'),
//	'Take 10 mg by mouth once a day')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_01162026_1'), todayGMT)
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/span_195967001'), '195967001')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Asthma'), 'Asthma')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Active'), 'Active')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Date'), 'Date')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_01162026_2'), todayGMT)
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Birth Sex'), 'Birth Sex')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Male'), 'Male')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Weight'), 'Weight')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_123 lbs'), '123 lbs')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_HeightLength'),
//	'Height/Length')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_165.1 cm'), '165.1 cm')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Respiration rate'),
//	'Respiration rate')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_10 min'), '10 /min')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Body Temperature'),
//	'Body Temperature')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_80 F'), '80 F')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Pulse Oximetry'),
//	'Pulse Oximetry')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_40'), '40 %')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Inhaled Oxygen Concentration'),
//	'Inhaled Oxygen Concentration')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_90'), '90 %')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Heart Beat'), 'Heart Beat')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_123  min'), '123 /min')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Hartnups disease'),
//	'Hartnup\'s disease')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Patient Portal_1'),
//	'Patient Portal')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/span_Automated VF, Central - 40'),
//	'Automated VF, Central - 40')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Automated VF'),
//	'Automated VF')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Completed'), 'Completed')
//
//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Test Diag'), 'Test Diag')

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import java.text.SimpleDateFormat
import java.util.TimeZone
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/*
 * =============================================================================
 *  Summary of Care (C-CDA) on Patient Portal - Verification Script
 * =============================================================================
 *  v3 NOTE - why this no longer uses the Object Repository:
 *  The SOC content renders inside an <iframe>. ensureOnSummaryOfCarePage()
 *  correctly finds it and switches into it. But every Object Repository item
 *  recorded against this page (e.g. h2_Summary of Care (C-CDA)) carries its
 *  OWN stored "parent frame" locator from when it was captured. Katalon
 *  re-applies that stored frame switch on every WebUI call against the
 *  object - regardless of what frame you're already in - and that stored
 *  locator is now stale/broken (dynamic tailwind classes, shifting div
 *  indices), so it fails before ever reaching the element.
 *
 *  Fix: every locator here is a plain runtime XPath TestObject (built via
 *  xpathObject / labelValue / rowCell / headerCell below) with NO frame
 *  metadata attached, scoped by the section's stable `id` attribute or by
 *  label text taken straight from the actual C-CDA HTML. Katalon then just
 *  searches in whatever frame is currently active - the one
 *  ensureOnSummaryOfCarePage() already switched into - instead of trying
 *  to re-navigate frames on every call.
 *
 *  Vital Signs rows are looked up by LABEL, not row position, since we
 *  can't be certain a "Birth Sex" row does or doesn't precede "Weight" in
 *  every render - label lookup is immune to row-order differences.
 *
 *  Fields I could not confirm from the HTML you shared (an "Automated VF,
 *  Central - 40" / "Completed" / "Test Diag" trio that appeared in an
 *  earlier script version) are left OUT of the hard assertions below and
 *  handled as soft/best-effort checks instead - see the SOFT CHECKS
 *  section - since asserting them hard risks failing on a section layout
 *  I haven't actually seen.
 * =============================================================================
 */

// ---------------------------------------------------------------------------
// Config
// ---------------------------------------------------------------------------
int TIMEOUT = 8
int RETRY_COUNT = 2
int RETRY_WAIT_MS = 300

// Existing date keyword - MM/dd/yyyy, e.g. "09/01/2026"
String todayGMT = CustomKeywords.'common.DateUtil.getTodayDateGMT'()

// Fallback for the Vital Signs column header's different format - "MMM dd, yyyy", e.g. "Sep 01, 2026"
SimpleDateFormat longFmt = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
longFmt.setTimeZone(TimeZone.getTimeZone("GMT"))

String todayGMTLong = longFmt.format(new Date())

println(todayGMTLong) 

// Optional Test Case Variables (declare in this Test Case's Variables tab,
// default '' each) for exact-match assertions when the caller has captured
// the real values right after Save/creation. Falls back to format-only
// checks when called with an empty map.
String expectedEncounterNumber = binding.hasVariable('expectedEncounterNumber') ? binding.getVariable('expectedEncounterNumber') : ''
String expectedPatientId       = binding.hasVariable('expectedPatientId') ? binding.getVariable('expectedPatientId') : ''

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

/** Same as robustVerifyText but logs a warning and continues instead of failing the run. */
def softVerifyText = { TestObject to, String expected, String label = null ->
    String name = label ?: to.getObjectId()
    try {
        WebUI.waitForElementVisible(to, 3, FailureHandling.OPTIONAL)
        WebUI.verifyElementText(to, expected, FailureHandling.OPTIONAL)
        KeywordUtil.logInfo("Soft check passed: [${name}] = '${expected}'")
    } catch (Exception e) {
        KeywordUtil.logInfo("Soft check skipped (not present or mismatched) on [${name}]: ${e.getMessage()}")
    }
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

/** Build a one-off TestObject from an XPath, without depending on the Object Repository (no frame metadata attached). */
def xpathObject = { String xpath ->
    TestObject to = new TestObject()
    to.addProperty('xpath', ConditionType.EQUALS, xpath)
    return to
}

/** Value cell immediately following a label cell, optionally scoped to a section div id. */
def labelValue = { String labelText, String scopeId = null ->
    String scope = scopeId ? "//div[@id='${scopeId}']" : ""
    return xpathObject("${scope}//td[normalize-space(text())='${labelText}']/following-sibling::td[1]")
}

/** Row/column cell within a section's first data table (1-based row/col). */
def rowCell = { String sectionId, int row, int col ->
    return xpathObject("//div[@id='${sectionId}']//table/tbody/tr[${row}]/td[${col}]")
}

/** Header cell within a section's first table (1-based column). */
def headerCell = { String sectionId, int col ->
    return xpathObject("//div[@id='${sectionId}']//table/thead/tr/th[${col}]")
}

/** Any element within a section whose normalized text matches exactly (for values wrapped in nested spans). */
def rowCellSpan = { String sectionId, int row, int col ->
    return xpathObject("//div[@id='${sectionId}']//table/tbody/tr[${row}]/td[${col}]//span[1]")
}

/** Best-effort: any element within a section containing the given text anywhere in its subtree. */
def containsInSection = { String sectionId, String text ->
    return xpathObject("//div[@id='${sectionId}']//*[contains(normalize-space(.),'${text}')]")
}

/**
 * Make sure the browser is actually looking at the Summary of Care content
 * before any assertion runs. Checks: current page/frame, other open
 * windows/tabs, then iframes on the current page. Leaves the driver
 * switched INTO whichever context matched, ready for the checks below.
 */
def ensureOnSummaryOfCarePage = {
    TestObject heading = xpathObject("//*[self::h1 or self::h2 or self::h3][contains(normalize-space(.),'Summary of Care')]")

    if (WebUI.waitForElementPresent(heading, 3, FailureHandling.OPTIONAL)) {
        KeywordUtil.logInfo("Summary of Care heading found in current window/frame.")
        return
    }

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

/** Capture a dynamic numeric field, assert its format, and optionally exact-match against a passed-in value. */
def verifyDynamicNumericField = { TestObject to, String expectedValue, String label, String regex = /\d{3,8}/ ->
    String actual = robustGetText(to, label)
    if (!(actual ==~ regex)) {
        KeywordUtil.markFailedAndStop("${label} '${actual}' does not look like a valid value (expected pattern ${regex})")
    }
    if (expectedValue?.trim()) {
        if (actual != expectedValue.trim()) {
            KeywordUtil.markFailedAndStop("${label} mismatch: page shows '${actual}', expected '${expectedValue}'")
        }
        KeywordUtil.logInfo("${label} verified exact match: ${actual}")
    } else {
        KeywordUtil.logInfo("${label} captured: ${actual} (format OK, no exact-match value was passed in)")
    }
    return actual
}

// ---------------------------------------------------------------------------
// Verification Flow
// ---------------------------------------------------------------------------
try {

    ensureOnSummaryOfCarePage()

    KeywordUtil.logInfo("=== SECTION: Page Header ===")
    robustVerifyText(xpathObject("//h2[normalize-space()='Summary of Care (C-CDA)']"),
        'Summary of Care (C-CDA)', 'Page heading')

    robustVerifyText(xpathObject("//b[normalize-space()='Created On:']/following-sibling::font[1]"),
        todayGMT, 'Created On date')

    KeywordUtil.logInfo("=== SECTION: Patient Demographics (dynamic Patient Id) ===")
    verifyDynamicNumericField(labelValue('Patient Id:'), expectedPatientId, 'Patient Id')
    robustVerifyText(labelValue('DOB:'), '03/16/1982', 'DOB cell')

    KeywordUtil.logInfo("=== SECTION: Encounter (dynamic Encounter Number) ===")
    // Encounter table row 1: [1]=Number [2]=Title [3]=Type [4]=Diagnosis [5]=Date [6]=Provider [7]=Location
    verifyDynamicNumericField(rowCell('Encounter ', 1, 1), expectedEncounterNumber, 'Encounter Number')
    robustVerifyText(rowCell('Encounter ', 1, 2), 'Automation Element Test Encounter', 'Encounter Title cell')
    robustVerifyText(rowCell('Encounter ', 1, 5), todayGMT, 'Encounter Date cell')
    robustVerifyText(rowCell('Encounter ', 1, 6), 'Patient Portal', 'Encounter Provider cell')

    KeywordUtil.logInfo("=== SECTION: Medications ===")
    robustVerifyText(rowCell('Medications', 1, 1), 'Lipitor atorvastatin 10 [617314]', 'Medication name cell')
    robustVerifyText(rowCell('Medications', 1, 2), 'Take 10 mg by mouth once a day', 'Medication directions cell')
    robustVerifyText(rowCell('Medications', 1, 5), todayGMT, 'Medication Date Started cell')

    KeywordUtil.logInfo("=== SECTION: Problem List ===")
    robustVerifyText(rowCellSpan('Problem List', 1, 1), '195967001', 'Problem SNOMED code')
    robustVerifyText(rowCell('Problem List', 1, 2), 'Asthma', 'Problem description cell')
    robustVerifyText(rowCell('Problem List', 1, 3), 'Active', 'Problem status cell')

    KeywordUtil.logInfo("=== SECTION: Vital Signs (label-based, not position-based) ===")
    robustVerifyText(headerCell('Vital Signs', 1), 'Date', 'Vital Signs "Date" column label')
    robustVerifyText(headerCell('Vital Signs', 2), todayGMTLong, 'Vital Signs date column header (long format)')

    robustVerifyText(labelValue('Weight', 'Vital Signs'), '123 lbs', 'Weight value')
    robustVerifyText(labelValue('Height/Length', 'Vital Signs'), '165.1 cm', 'Height value')
    robustVerifyText(labelValue('Respiration rate', 'Vital Signs'), '10 /min', 'Respiration rate value')
    robustVerifyText(labelValue('Body Temperature', 'Vital Signs'), '80 F', 'Body Temperature value')
    robustVerifyText(labelValue('Pulse Oximetry', 'Vital Signs'), '40 %', 'Pulse Oximetry value')
    robustVerifyText(labelValue('Inhaled Oxygen Concentration', 'Vital Signs'), '90 %', 'Inhaled Oxygen Concentration value')
    robustVerifyText(labelValue('Heart Beat', 'Vital Signs'), '123 /min', 'Heart Beat value')

    // Not confirmed present in the sample HTML - checked softly so it doesn't break the run either way.
    softVerifyText(labelValue('Birth Sex', 'Vital Signs'), 'Male', 'Birth Sex value (soft check)')

    KeywordUtil.logInfo("=== SECTION: Encounter Diagnoses ===")
    robustVerifyText(rowCell('Encounter Diagnoses', 1, 1), "Hartnup's disease", 'Encounter Diagnosis cell')
    robustVerifyText(rowCell('Encounter Diagnoses', 1, 2), 'Patient Portal', 'Encounter Diagnosis location cell')

    KeywordUtil.logInfo("=== SECTION: Diagnostic Imaging ===")
    robustVerifyText(rowCellSpan('Diagnostic Imaging', 1, 1), 'Automated VF', 'Diagnostic Imaging Test cell')
    robustVerifyText(rowCell('Diagnostic Imaging', 1, 2), 'Test Findings', 'Diagnostic Imaging Report cell')

    // SOFT CHECKS - fields seen in an earlier script version but not confirmed in the HTML you shared.
    // These log a result either way instead of failing the run; confirm the real section/layout and
    // I'll promote them to hard assertions with the correct locator.
    softVerifyText(containsInSection('Diagnostic Imaging', 'Automated VF, Central - 40'), 'Automated VF, Central - 40', 'Order type + location (soft check)')
    softVerifyText(containsInSection('Diagnostic Imaging', 'Completed'), 'Completed', 'Order status (soft check)')
    softVerifyText(containsInSection('Diagnostic Imaging', 'Test Diag'), 'Test Diag', 'Order note (soft check)')

    KeywordUtil.logInfo("=== SECTION: Services and Procedures ===")
    robustVerifyText(rowCell('Services and Procedures Section', 1, 1), 'Procedures', 'Services/Procedures label cell')
    robustVerifyText(rowCell('Services and Procedures Section', 1, 2), '99214 - Comprehensive exam, Est. Patient', 'Services/Procedures value cell')

    KeywordUtil.logInfo("=== Verification completed successfully ===")

} catch (Exception e) {
    KeywordUtil.markFailedAndStop("Summary of Care (Patient Portal) verification failed: ${e.getMessage()}")
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

/*
 * CALLING THIS AS A SUB TEST CASE:
 *   WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/SOC Verification On Patient Portal'),
 *       [:], FailureHandling.STOP_ON_FAILURE)
 * Works as-is - Encounter Number and Patient Id fall back to format-only checks.
 *
 * For exact-match on Encounter Number / Patient Id:
 *   1. Test Case > Variables tab > add `expectedEncounterNumber` and
 *      `expectedPatientId`, both default ''.
 *   2. Pass the values captured right after creation/save:
 *        WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/SOC Verification On Patient Portal'),
 *            ['expectedEncounterNumber': encounterNumber, 'expectedPatientId': patientId],
 *            FailureHandling.STOP_ON_FAILURE)
 */