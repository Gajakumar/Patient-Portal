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



//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive Message Thread'), 
//    0)
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Reply Message Thread'), 
//    0)
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Delete Message Thread'), 
//    0)
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Print Education Material'), 
//    0)
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Download PDF'), 0)
//
//
//
//WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/p_Patient Portal'), 'Patient Portal', 
//    0)
//
//
//
//WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/span_Alcohol Use Disorder (AUD)'), 
//    ' Alcohol Use Disorder (AUD)', 0)
//
////WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/a_https_medlineplus.gov_alcoholusedisorderaud.ht'))
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive Message Thread_1'))
//
//
//
//WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/p_Are you sure you want to archive the selected'), 
//    'Are you sure you want to archive the selected messages?', 0)
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive'))
//
//
//
//WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/div_1'), 'Message(s) archived successfully!', 
//    0)
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_tooltip'))
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_Archived Messages'))
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi'), 
//    0)
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Unarchive Message Thread_1'), 
//    0)
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Reply Message Thread_2'), 
//    0)
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Delete Message Thread_2'), 
//    0)
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Print Education Material'), 
//    0)
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Download PDF'), 0)
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Unarchive Message Thread_2'))
//
//
//
//WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/p_Are you sure you want to archive the selected'), 
//    'Are you sure you want to archive the selected messages?', 0)
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive'))
//
//
//
//WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/p_You have no messages in archived messages'), 
//    'You have no messages in archived messages', 0)
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_tooltip_1'))
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_Inbox'))
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi_1'))
//
//
//
//WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi'), 
//    0)
//
//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Download PDF'))

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.JavascriptExecutor


// ---------- Configurable timeout ----------

int TIMEOUT = 30
try {
    TIMEOUT = Integer.parseInt((GlobalVariable.G_Timeout as String).trim())
} catch (Exception ignored) {
    TIMEOUT = 30
}

// ---------- Test Objects (Patient Portal - Inbox view) ----------
TestObject to_btnArchive        = findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive Message Thread')
TestObject to_btnReply          = findTestObject('Edu Material Verification/Page_Patient Portal/button_Reply Message Thread')
TestObject to_btnDelete         = findTestObject('Edu Material Verification/Page_Patient Portal/button_Delete Message Thread')
TestObject to_btnPrint          = findTestObject('Edu Material Verification/Page_Patient Portal/button_Print Education Material')
TestObject to_btnDownloadPDF    = findTestObject('Edu Material Verification/Page_Patient Portal/button_Download PDF')
TestObject to_pPatientPortal    = findTestObject('Edu Material Verification/Page_Patient Portal/p_Patient Portal')
TestObject to_spanAUD           = findTestObject('Edu Material Verification/Page_Patient Portal/span_Alcohol Use Disorder (AUD)')
TestObject to_linkAUD           = findTestObject('Edu Material Verification/Page_Patient Portal/a_https_medlineplus.gov_alcoholusedisorderaud.ht')

TestObject to_btnArchive1       = findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive Message Thread_1')
TestObject to_pConfirmArchive   = findTestObject('Edu Material Verification/Page_Patient Portal/p_Are you sure you want to archive the selected')
TestObject to_btnArchiveConfirm = findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive')
TestObject to_divArchivedToast  = findTestObject('Edu Material Verification/Page_Patient Portal/div_1')
TestObject to_divTooltip        = findTestObject('Edu Material Verification/Page_Patient Portal/div_tooltip')
TestObject to_divArchivedMenu   = findTestObject('Edu Material Verification/Page_Patient Portal/div_Archived Messages')

// ---------- Test Objects (Archived Messages view) ----------
TestObject to_threadRowArchived   = findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi')
TestObject to_btnUnarchive1       = findTestObject('Edu Material Verification/Page_Patient Portal/button_Unarchive Message Thread_1')
//TestObject to_btnUnarchive2       = findTestObject('Edu Material Verification/Page_Patient Portal/button_Unarchive Message Thread_2')
TestObject to_pNoArchivedMessages = findTestObject('Edu Material Verification/Page_Patient Portal/p_You have no messages in archived messages')
TestObject to_divTooltip1         = findTestObject('Edu Material Verification/Page_Patient Portal/div_tooltip_1')
TestObject to_divInboxMenu        = findTestObject('Edu Material Verification/Page_Patient Portal/div_Inbox')
TestObject to_threadRowInbox1     = findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi_1')

// ==============================================================
// Helper methods (timeout is always a REQUIRED explicit argument)
// ==============================================================

/** Waits for an element to be present, then asserts presence (fails test if missing). */
def verifyPresent(TestObject to, int timeout) {
    WebUI.waitForElementPresent(to, timeout)
    WebUI.assertElementPresent(to, 0)
}

/** Waits for an element to be clickable, then clicks it. */
def safeClick(TestObject to, int timeout) {
    WebUI.waitForElementClickable(to, timeout)
    WebUI.click(to)
}

/** Waits for an element to be visible, then asserts its text (trims both sides to avoid whitespace flakiness). */
def verifyText(TestObject to, String expectedText, int timeout) {
    WebUI.waitForElementVisible(to, timeout)
    String actualText = WebUI.getText(to)
    if (actualText?.trim() != expectedText?.trim()) {
        KeywordUtil.markFailed("Text mismatch for '${to.getObjectId()}'. Expected: '${expectedText}' | Actual: '${actualText}'")
    }
}

/**
 * Verifies the standard set of thread-action buttons + edu material buttons
 * present in either the Inbox or Archived Messages view.
 */
def verifyThreadActionButtons(TestObject archiveOrUnarchiveBtn, TestObject replyBtn, TestObject deleteBtn,
                               TestObject printBtn, TestObject downloadBtn, int timeout) {
    verifyPresent(archiveOrUnarchiveBtn, timeout)
    verifyPresent(replyBtn, timeout)
    verifyPresent(deleteBtn, timeout)
    verifyPresent(printBtn, timeout)
    verifyPresent(downloadBtn, timeout)
}

/**
 * Clicks a link that is expected to open in a NEW browser tab,
 * verifies the new tab opened and navigated to the expected URL
 * fragment, then closes the new tab and returns focus to the
 * original (parent) tab.
 */
def verifyLinkOpensInNewTab(TestObject linkObject, String expectedUrlFragment, int timeout) {
    WebDriver driver = DriverFactory.getWebDriver()
    String parentHandle = driver.getWindowHandle()
    Set<String> originalHandles = driver.getWindowHandles()

    WebUI.waitForElementClickable(linkObject, timeout)
    WebUI.click(linkObject)

    // Poll for a new window handle to appear instead of a fixed sleep
    Set<String> newHandles = null
    int waited = 0
    while (waited < timeout) {
        newHandles = driver.getWindowHandles()
        if (newHandles.size() > originalHandles.size()) break
        WebUI.delay(1)
        waited++
    }

    if (newHandles == null || newHandles.size() <= originalHandles.size()) {
        KeywordUtil.markFailed("Expected a new tab to open after clicking '${linkObject.getObjectId()}', but no new window handle was detected within ${timeout}s.")
        return
    }

    newHandles.removeAll(originalHandles)
    String newTabHandle = newHandles.iterator().next()

    driver.switchTo().window(newTabHandle)
    WebUI.waitForPageLoad(timeout)

    String newTabUrl = driver.getCurrentUrl()
    if (!newTabUrl?.toLowerCase()?.contains(expectedUrlFragment.toLowerCase())) {
        KeywordUtil.markFailed("New tab URL did not contain expected fragment. Expected to contain: '${expectedUrlFragment}' | Actual: '${newTabUrl}'")
    } else {
        KeywordUtil.logInfo("Verified new tab opened with URL: ${newTabUrl}")
    }

    // Close the new tab and return focus to the original tab
    driver.close()
    driver.switchTo().window(parentHandle)
}

//  DOWNLOAD-STARTED VERIFICATION (works locally AND on Katalon TestCloud)
// ---------------------------------------------------------------

def verifyDownloadStarted(TestObject downloadBtn, int timeout) {
	WebDriver driver = DriverFactory.getWebDriver()
	String originalUrl = driver.getCurrentUrl()
 
	safeClick(downloadBtn, timeout)
 
	String detectedFileName = null
	int waited = 0
	while (waited < timeout) {
		driver.get('chrome://downloads/')
		try {
			detectedFileName = (String) ((JavascriptExecutor) driver).executeScript(
				"var list = document.querySelector('downloads-manager') && " +
				"document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList').items; " +
				"return (list && list.length > 0) ? list[0].fileName : null;"
			)
		} catch (Exception ignored) {
			detectedFileName = null
		}
		if (detectedFileName) break
		WebUI.delay(1)
		waited++
	}
 
	// Return focus to the app regardless of outcome
	driver.get(originalUrl)
	WebUI.waitForPageLoad(timeout)
 
	if (!detectedFileName) {
		KeywordUtil.markFailed("No download appeared in chrome://downloads/ within ${timeout}s after clicking Download PDF.")
		return
	}
 
	KeywordUtil.logInfo("Download started successfully: '${detectedFileName}'.")
}
// ==============================================================
// Test Steps
// ==============================================================

// ---- 1. Verify Inbox thread view: action buttons + text ----
verifyThreadActionButtons(to_btnArchive, to_btnReply, to_btnDelete, to_btnPrint, to_btnDownloadPDF, TIMEOUT)
verifyText(to_pPatientPortal, 'Patient Portal', TIMEOUT)
verifyText(to_spanAUD, ' Alcohol Use Disorder (AUD)', TIMEOUT)

// ---- 2. Verify the education material link opens in a new tab ----
verifyLinkOpensInNewTab(to_linkAUD, 'medlineplus.gov/alcoholusedisorderaud', TIMEOUT)

// ---- 3. Archive the message thread ----
safeClick(to_btnArchive1, TIMEOUT)
verifyText(to_pConfirmArchive, 'Are you sure you want to archive the selected messages?', TIMEOUT)
safeClick(to_btnArchiveConfirm, TIMEOUT)
//verify toast msg
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Message(s) archived successfully!')

// ---- 4. Navigate to Archived Messages and verify state ----
safeClick(to_divTooltip, TIMEOUT)
safeClick(to_divArchivedMenu, TIMEOUT)

verifyPresent(to_threadRowArchived, TIMEOUT)
verifyThreadActionButtons(to_btnUnarchive1, to_btnReply, to_btnDelete, to_btnPrint, to_btnDownloadPDF, TIMEOUT)

// ---- 5. Unarchive the message thread ----
safeClick(to_btnUnarchive1, TIMEOUT)
verifyText(to_pConfirmArchive, 'Are you sure you want to unarchive the selected messages?', TIMEOUT)
safeClick(to_btnArchiveConfirm, TIMEOUT)
verifyText(to_pNoArchivedMessages, 'You have no messages in archived messages', TIMEOUT)

// ---- 6. Navigate back to Inbox and verify the thread returned ----
safeClick(to_divTooltip1, TIMEOUT)
safeClick(to_divInboxMenu, TIMEOUT)
safeClick(to_threadRowInbox1, TIMEOUT)
verifyPresent(to_threadRowArchived, TIMEOUT)

// ---- 7. Click Download PDF and verify the download actually started ----
verifyDownloadStarted(to_btnDownloadPDF, TIMEOUT)

KeywordUtil.logInfo("Edu Material Verification - Patient Portal test completed successfully.")

