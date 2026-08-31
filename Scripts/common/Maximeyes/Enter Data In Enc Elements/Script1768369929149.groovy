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
import com.kms.katalon.core.util.KeywordUtil

//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_HPI Notes_4243.Chief_Complaint.CHI_605b27'),
//	'Patient Chief Complaint')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_HPI Notes_4243.Chief_Complaint.EXT_25c863'),
//	'HPI Notes')
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_HPI Notes_glyphicon-circelplus font17 _a7e203'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Aphakia'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Biometry for cataract surgery_LOCATION_G_f4921f'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_OD'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_RLL_QUALITY_GF_9933_GF_B-1'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Aching'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/img_Radiating_SEVERITY_GF_9933_GF_B-1Img'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Mild'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/img_Severe_DURATION_GF_9933_GF_B-1Img'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_5 min'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/img_All day_TIMING_GF_9933_GF_B-1Img'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Only Once'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Monthly_CONTEXT_GF_9933_GF_B-1'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Allergies'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Using computer_MODIFYING_FACTORS_GF_9933_GF_B-1'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Feels better after washing with cold water'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Feels better when cold compress is appli_ecf732'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Watery'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_glyphicon-circelplus font17_9bfae2'))
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Search Drug_MEDICATION_BRAND_NAME_I'),
//	'Lipitor,atorvastatin')
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Directions to Pharmacist_Add'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Refills_btnOk'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_glyphicon glyphicon-circelp_06a9eb'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Asthma'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_d9d0_GF_B-1'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Active'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_d9d0_GF_B-1'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_EHR'))
//
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Welcome Form_DATE_OF_ONSET_GF_d9d0_GF_B-1'))
//
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_1'))
////
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Cancel_DATE_DIAGNOSED_GF_d9d0_GF_B-1'))
//
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_1_1'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_--Select--_problemListGridView_d9d0_E_83923f'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Preliminaries'))
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_65308e'),
//	'123')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_738589'),
//	'5')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_f1ed07'),
//	'5')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_d7bb12'),
//	'123')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_971c23'),
//	'')
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_971c23'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_80'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Signs.PULSE'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_40'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_6c9424'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_10'))
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_d8a00b'),
//	'90')
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_958913'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_C_fg-skyblue enccontoltime font15'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_C_fg-skyblue enccontoltime font15_1'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Tests  Miscellaneous'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Orders Completed_mif-circle-plus font2_4b7a2b'))
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_---Select---Automated VFFundus Photo_c7326f'),
//	'Automated VF', true)
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Type_ORDER_TYPE'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_Central - 40'))
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Add AddedAlbert Daviscert firstcert _8f61c4'),
//	'12', true)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_---Select---ODOSOU'),
//	'OD', true)
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Select options_Diag_Code_Data'),
//	'T67.3XXA')
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_T67.3XXA-Heat exhaustion, anhydrotic_1'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_1 selected'), '1 selected')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Note_TestNotes'), 'Test Diag')
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Due Date_IS_TEST_DUE_DATE'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Test Performed_icon-checked'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Result RcvdEntered_icon-checked'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Interpreted_icon-checked'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Billed_icon-checked'))
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Findings_FINDINGS'), 'Test Findings')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Clinic Notes_CLINIC_NOTES'),
//	'Clinical Notes')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Test_INTERPRET_TEST_RESULTS'),
//	'Test')
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Image_INTERPRET_IMAGE_RESULTS'),
//	'Image Test')
//
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_Good data - patient alert'))
////
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_Hemianopia - bi-temporal'))
////
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_Baseline fields'))
////
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_Clinical correlation required'))
////
////WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Image_mif-copy-od-os font16 fg-skyblue_63960b'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Recordof_btnAddCollapse'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Final Findings'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Annual exam_glyphicon-circelplus font1_0c94a1'))
//
//WebUI.waitForElementVisible(
//    findTestObject('Maximeyes_Portal_Mix/Page_MaximEyes/input_Search_textbox'),
//    5,
//    FailureHandling.STOP_ON_FAILURE
//)
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Search_textbox'), 'H disease')
//
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/button_GO'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_H disease'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Final Procedures'))
//
//WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Search_textboxCPTCodeOmniSearch'),
//	'99214')
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/button_GO_1'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_99214'))
//
//WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_PLANS_Save'))


import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/*
 * =============================================================================
 *  MaximEyes Portal - HPI / Vitals / Orders / Final Findings / Procedures Flow
 * =============================================================================
 *  Hardened version of the original recorded script.
 *
 *  v2 NOTE: the first version of this script took ~22 minutes to run. Root
 *  cause was waiting THREE separate times per action (present, then visible,
 *  then clickable - each up to 20s) plus a flat 400ms delay after every
 *  single action, all repeated on every retry. That's overhead, not safety.
 *  This version fixes that:
 *   1. ONE wait call per action (waitForElementClickable already implies
 *      present + visible + enabled), not three stacked waits.
 *   2. Default timeout down to 8s (elements that exist show up fast; slow
 *      timeouts should only apply to genuinely async things).
 *   3. No blanket delay after every action. A short delay is only inserted
 *      right after actions that are KNOWN to trigger async UI (search
 *      autocomplete, results grids) - listed explicitly at the call site.
 *   4. Retries are capped at 2 with a short 300ms backoff, and only retry
 *      on genuine failure - no re-waiting on things that already worked.
 *   5. Logical section headers + step-level comments for readability.
 *   6. A wrapping try/catch/finally so a failure is captured with context
 *      instead of silently aborting, without adding extra wait time.
 *
 *  Tune TIMEOUT / RETRY_COUNT below to match your environment's latency.
 *  If your app is consistently slow, raise TIMEOUT modestly rather than
 *  adding more wait calls - the win is fewer, right-sized waits.
 * =============================================================================
 */
 
// ---------------------------------------------------------------------------
// Config
// ---------------------------------------------------------------------------
int TIMEOUT = 8                  // seconds to wait for an element (single wait, not stacked)
int RETRY_COUNT = 2              // retries per action before failing
int RETRY_WAIT_MS = 300          // pause between retries (ms)
 
// ---------------------------------------------------------------------------
// Robust wrapper keywords
// ---------------------------------------------------------------------------
 
/**
 * Click an element. ONE wait (clickable = present + visible + enabled),
 * then click. Retries only kick in on actual failure - no re-waiting on
 * a step that already succeeded.
 */
def robustClick = { TestObject to, String label = null, double extraDelaySec = 0 ->
    String name = label ?: to.getObjectId()
    int attempt = 0
    Exception lastError = null
    while (attempt <= RETRY_COUNT) {
        try {
            WebUI.waitForElementClickable(to, TIMEOUT, FailureHandling.STOP_ON_FAILURE)
            WebUI.click(to, FailureHandling.STOP_ON_FAILURE)
            if (extraDelaySec > 0) WebUI.delay(extraDelaySec)
            return
        } catch (Exception e) {
            lastError = e
            attempt++
            if (attempt > RETRY_COUNT) break
            KeywordUtil.logInfo("Click retry ${attempt}/${RETRY_COUNT} on [${name}]: ${e.getMessage()}")
            WebUI.delay(RETRY_WAIT_MS / 1000.0)
        }
    }
    KeywordUtil.markFailedAndStop("Unable to click [${name}] after ${RETRY_COUNT} retries: ${lastError?.getMessage()}")
}
 
/** Set text into a field. ONE wait (visible), then set. Retries only on failure. */
def robustSetText = { TestObject to, String text, String label = null, double extraDelaySec = 0 ->
    String name = label ?: to.getObjectId()
    int attempt = 0
    Exception lastError = null
    while (attempt <= RETRY_COUNT) {
        try {
            WebUI.waitForElementVisible(to, TIMEOUT, FailureHandling.STOP_ON_FAILURE)
            WebUI.setText(to, text, FailureHandling.STOP_ON_FAILURE)
            if (extraDelaySec > 0) WebUI.delay(extraDelaySec)
            return
        } catch (Exception e) {
            lastError = e
            attempt++
            if (attempt > RETRY_COUNT) break
            KeywordUtil.logInfo("SetText retry ${attempt}/${RETRY_COUNT} on [${name}]: ${e.getMessage()}")
            WebUI.delay(RETRY_WAIT_MS / 1000.0)
        }
    }
    KeywordUtil.markFailedAndStop("Unable to set text on [${name}] after ${RETRY_COUNT} retries: ${lastError?.getMessage()}")
}
 
/** Select a <select> option by value. ONE wait (visible), then select. Retries only on failure. */
def robustSelectByValue = { TestObject to, String value, boolean isRegex = true, String label = null ->
    String name = label ?: to.getObjectId()
    int attempt = 0
    Exception lastError = null
    while (attempt <= RETRY_COUNT) {
        try {
            WebUI.waitForElementVisible(to, TIMEOUT, FailureHandling.STOP_ON_FAILURE)
            WebUI.selectOptionByValue(to, value, isRegex, FailureHandling.STOP_ON_FAILURE)
            return
        } catch (Exception e) {
            lastError = e
            attempt++
            if (attempt > RETRY_COUNT) break
            KeywordUtil.logInfo("Select retry ${attempt}/${RETRY_COUNT} on [${name}]: ${e.getMessage()}")
            WebUI.delay(RETRY_WAIT_MS / 1000.0)
        }
    }
    KeywordUtil.markFailedAndStop("Unable to select value [${value}] on [${name}] after ${RETRY_COUNT} retries: ${lastError?.getMessage()}")
}
 
/** Wait for text on an element to match expected value (single wait, for verify steps). */
def robustVerifyText = { TestObject to, String expected, String label = null ->
    WebUI.waitForElementVisible(to, TIMEOUT, FailureHandling.STOP_ON_FAILURE)
    WebUI.verifyElementText(to, expected, FailureHandling.STOP_ON_FAILURE)
}
 
// ---------------------------------------------------------------------------
// Test Flow
// ---------------------------------------------------------------------------
try {
 
    KeywordUtil.logInfo("=== SECTION: HPI Notes - Chief Complaint ===")
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_HPI Notes_4243.Chief_Complaint.CHI_605b27'),
        'Patient Chief Complaint', 'Chief Complaint textarea')
 
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_HPI Notes_4243.Chief_Complaint.EXT_25c863'),
        'HPI Notes', 'HPI Notes textarea')
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_HPI Notes_glyphicon-circelplus font17 _a7e203'),
        'HPI Notes expand icon')
 
    KeywordUtil.logInfo("=== SECTION: HPI Notes - Detail Grid Selections ===")
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Aphakia'), 'Aphakia')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Biometry for cataract surgery_LOCATION_G_f4921f'), 'Biometry for cataract surgery (Location)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_OD'), 'OD')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_RLL_QUALITY_GF_9933_GF_B-1'), 'RLL (Quality)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Aching'), 'Aching')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/img_Radiating_SEVERITY_GF_9933_GF_B-1Img'), 'Radiating (Severity)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Mild'), 'Mild')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/img_Severe_DURATION_GF_9933_GF_B-1Img'), 'Severe (Duration)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_5 min'), '5 min')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/img_All day_TIMING_GF_9933_GF_B-1Img'), 'All day (Timing)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Only Once'), 'Only Once')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Monthly_CONTEXT_GF_9933_GF_B-1'), 'Monthly (Context)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Allergies'), 'Allergies')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Using computer_MODIFYING_FACTORS_GF_9933_GF_B-1'), 'Using computer (Modifying Factors)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Feels better after washing with cold water'), 'Feels better after washing with cold water')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Feels better when cold compress is appli_ecf732'), 'Feels better when cold compress is applied')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Watery'), 'Watery')
 
    KeywordUtil.logInfo("=== SECTION: Medication Search & Add ===")
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_glyphicon-circelplus font17_9bfae2'), 'Medication section expand icon')
 
    // 0.6s extra delay: lets the drug-search autocomplete populate before the next click
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Search Drug_MEDICATION_BRAND_NAME_I'),
        'Lipitor,atorvastatin', 'Search Drug field', 0.6)
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Directions to Pharmacist_Add'), 'Add (Directions to Pharmacist)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Refills_btnOk'), 'Refills OK button')
 
    KeywordUtil.logInfo("=== SECTION: Problem List ===")
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_glyphicon glyphicon-circelp_06a9eb'), 'Problem List expand icon')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Asthma'), 'Asthma')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_d9d0_GF_B-1'), 'Loading (Status)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Active'), 'Active')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_d9d0_GF_B-1'), 'Chronic (Source)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_EHR'), 'EHR')
 
    // Kept disabled to match the original recorded flow - re-enable if the Date of Onset /
    // Date Diagnosed steps are needed:
    // robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Welcome Form_DATE_OF_ONSET_GF_d9d0_GF_B-1'), 'Date of Onset')
    // robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_1'), 'Date of Onset day')
    // robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Cancel_DATE_DIAGNOSED_GF_d9d0_GF_B-1'), 'Date Diagnosed cancel')
    // robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_1_1'), 'Date Diagnosed day')
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_--Select--_problemListGridView_d9d0_E_83923f'), 'Problem List row selector')
 
    KeywordUtil.logInfo("=== SECTION: Preliminaries / Pediatric Vital Signs ===")
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Preliminaries'), 'Preliminaries tab')
 
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_65308e'), '123', 'Vital Sign field 1')
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_738589'), '5', 'Vital Sign field 2')
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_f1ed07'), '5', 'Vital Sign field 3')
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_d7bb12'), '123', 'Vital Sign field 4')
 
    // Field is intentionally cleared, then used to open a picker list (BP / dropdown-style input)
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_971c23'), '', 'Vital Sign field 5 (clear)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_971c23'), 'Vital Sign field 5 (open picker)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_80'), 'Vital sign picker value 80')
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Signs.PULSE'), 'Pulse field (open picker)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_40'), 'Pulse picker value 40')
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_6c9424'), 'Vital Sign field 6 (open picker)')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_10'), 'Vital sign picker value 10')
 
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_d8a00b'), '90', 'Vital Sign field 7')
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Pediatric Vital Signs_4250.Vital_Sign_958913'), 'Vital Sign field 8')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_C_fg-skyblue enccontoltime font15'), 'Encounter control time icon 1')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_C_fg-skyblue enccontoltime font15_1'), 'Encounter control time icon 2')
 
    KeywordUtil.logInfo("=== SECTION: Tests & Miscellaneous / Orders ===")
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Tests  Miscellaneous'), 'Tests & Miscellaneous tab')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Orders Completed_mif-circle-plus font2_4b7a2b'), 'Orders Completed expand icon')
 
    robustSelectByValue(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_---Select---Automated VFFundus Photo_c7326f'),
        'Automated VF', true, 'Order type dropdown')
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Type_ORDER_TYPE'), 'Order Type field')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_Central - 40'), 'Central - 40 option')
 
    robustSelectByValue(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Add AddedAlbert Daviscert firstcert _8f61c4'),
        '12', true, 'Added By dropdown')
 
    robustSelectByValue(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_---Select---ODOSOU'),
        'OD', true, 'Eye (OD/OS/OU) dropdown')
 
    KeywordUtil.logInfo("=== SECTION: Diagnosis Code Search ===")
    // 0.6s extra delay: lets the diag-code autocomplete results load before the next click
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Select options_Diag_Code_Data'),
        'T67.3XXA', 'Diagnosis code search field', 0.6)
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/li_T67.3XXA-Heat exhaustion, anhydrotic_1'), 'T67.3XXA - Heat exhaustion, anhydrotic option')
 
    robustVerifyText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_1 selected'), '1 selected', '"1 selected" confirmation label')
 
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Note_TestNotes'), 'Test Diag', 'Order Note textarea')
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Due Date_IS_TEST_DUE_DATE'), 'Due Date field')
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Test Performed_icon-checked'), 'Test Performed checkbox')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Result RcvdEntered_icon-checked'), 'Result Rcvd/Entered checkbox')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Interpreted_icon-checked'), 'Interpreted checkbox')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Billed_icon-checked'), 'Billed checkbox')
 
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Findings_FINDINGS'), 'Test Findings', 'Findings textarea')
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Clinic Notes_CLINIC_NOTES'), 'Clinical Notes', 'Clinic Notes textarea')
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Test_INTERPRET_TEST_RESULTS'), 'Test', 'Interpret Test Results textarea')
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/textarea_Image_INTERPRET_IMAGE_RESULTS'), 'Image Test', 'Interpret Image Results textarea')
 
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Recordof_btnAddCollapse'), 'Record of / Add & Collapse button')
 
    KeywordUtil.logInfo("=== SECTION: Final Findings ===")
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Final Findings'), 'Final Findings tab')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Annual exam_glyphicon-circelplus font1_0c94a1'), 'Annual exam expand icon')
 
    WebUI.waitForElementVisible(
        findTestObject('Maximeyes_Portal_Mix/Page_MaximEyes/input_Search_textbox'),
        TIMEOUT,
        FailureHandling.STOP_ON_FAILURE
    )
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Search_textbox'), 'H disease', 'Final Findings search field')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/button_GO'), 'Final Findings GO button')
 
    // wait for the search results grid to refresh before clicking a row
    WebUI.waitForElementVisible(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_H disease'), TIMEOUT, FailureHandling.STOP_ON_FAILURE)
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_H disease'), 'H disease result row')
 
    KeywordUtil.logInfo("=== SECTION: Final Procedures ===")
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Final Procedures'), 'Final Procedures tab')
 
    robustSetText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Search_textboxCPTCodeOmniSearch'), '99214', 'CPT code search field')
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/button_GO_1'), 'CPT code GO button')
 
    WebUI.waitForElementVisible(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_99214'), TIMEOUT, FailureHandling.STOP_ON_FAILURE)
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_99214'), '99214 result row')
 
    KeywordUtil.logInfo("=== SECTION: Save ===")
    robustClick(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_PLANS_Save'), 'Save (PLANS) button')
 
    KeywordUtil.logInfo("=== Flow completed successfully ===")
 
} catch (Exception e) {
    KeywordUtil.markFailedAndStop("Test flow failed: ${e.getMessage()}")
} finally {
    // Always attempt to capture evidence, even on failure, without masking the real error
    try {
        WebUI.takeScreenshot()
    } catch (Exception ignored) {
        KeywordUtil.logInfo("Screenshot capture skipped/failed: ${ignored.getMessage()}")
    }
}
 