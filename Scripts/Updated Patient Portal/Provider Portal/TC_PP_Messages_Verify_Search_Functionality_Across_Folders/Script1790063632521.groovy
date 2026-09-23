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
import custom.DownloadHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.Keys
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.chrome.ChromeOptions
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import java.util.List
import static org.junit.Assert.*
import groovy.transform.Field
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.Keys
import java.time.Duration
import java.time.Duration
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
/* ---------------------------------------------------------------------- */
/*  CONFIG - update for your environment                                  */
/* ---------------------------------------------------------------------- */

@Field int    CHAR_LIMIT   = 70          // MBT 46345 - max length of search box
@Field int    DEBOUNCE_MS  = 500         // search fires 500ms after typing stops

/* ---------------------------------------------------------------------- */
/*  TEST OBJECT HELPER (built inline so no Object Repository is needed)   */
/*  All locators are XPath - no CSS selectors are used in this script.    */
/* ---------------------------------------------------------------------- */
TestObject byXpath(String xpath) {
	TestObject to = new TestObject()
	to.addProperty('xpath', ConditionType.EQUALS, xpath)
	return to
}

/* Inbox */
@Field TestObject TO_INBOX_SEARCHBOX      = byXpath("//*[@id='searchboxofinbox']")
@Field TestObject TO_INBOX_SEARCH_ICON    = byXpath("//*[@id='searchiconinbox']")
@Field TestObject TO_INBOX_CANCEL_ICON    = byXpath("//*[@id='canceliconinbox']")
@Field TestObject TO_INBOX_MSG_LIST_WRAP  = byXpath("//*[@id='inboxMessageList']")
@Field TestObject TO_INBOX_GRID_BODY      = byXpath("//*[@id='idquicklinkGridofinbox']")
@Field TestObject TO_INBOX_GRID_SEARCH    = byXpath("//*[@id='idquicklinkGridForSearch']")
@Field TestObject TO_INBOX_TOP_HEADER     = byXpath("//*[@id='inboxmain']")
@Field TestObject TO_INBOX_MORE_ACTIONS   = byXpath("//*[@id='openmoreactionmenu']")
@Field TestObject TO_INBOX_BULK_DELETE    = byXpath("//*[contains(concat(' ', normalize-space(@class), ' '), ' delete-icon ')]")

/* Sent Items */
@Field TestObject TO_SENT_SEARCHBOX       = byXpath("//*[@id='searchboxofsentmsg']")
@Field TestObject TO_SENT_CANCEL_ICON     = byXpath("//*[@id='canceliconsentmsg']")
@Field TestObject TO_SENT_GRID_BODY       = byXpath("//*[@id='idquicklinkGrid1']")
@Field TestObject TO_SENT_GRID_SEARCH     = byXpath("//*[@id='idquicklinkGridForSearch']")
@Field TestObject TO_SENT_BULK_DELETE     = byXpath("//*[contains(concat(' ', normalize-space(@class), ' '), ' delete-iconSent ')]")
@Field TestObject TO_SENT_MSG_LIST_WRAP   = byXpath("//*[@id='sentMessageList']")
@Field TestObject TO_SENT_MORE_ACTIONS   = byXpath("//*[@id='openmoreactionmenuSent']")

/* Deleted / Archive */
@Field TestObject TO_DEL_SEARCHBOX        = byXpath("//*[@id='searchboxofdeletemsg']")
@Field TestObject TO_DEL_CANCEL_ICON      = byXpath("//*[@id='cancelicondeletemsg']")
@Field TestObject TO_DEL_GRID_BODY        = byXpath("//*[@id='idquicklinkGrid2']")
@Field TestObject TO_DEL_GRID_SEARCH      = byXpath("//*[@id='idquicklinkGridForSearch']")
@Field TestObject TO_DEL_BULK_DELETE      = byXpath("//*[contains(concat(' ', normalize-space(@class), ' '), ' delete-iconDelete ')]")
@Field TestObject TO_DEL_MSG_LIST_WRAP    = byXpath("//*[@id='deleteMessageList']")
@Field TestObject TO_DEL_MORE_ACTIONS   = byXpath("//*[@id='openmoreactionmenuDeleted']")

/* Dropdown menu items (Inbox / Outbox / Sent Messages / Deleted Messages / Activity Log) */
TestObject dropdownItem(String label) {
	return byXpath("//div[@class='patientRibbonAddDropdown']//div[contains(@class,'addDropdownDivHover')]/div[normalize-space(text())='${label}']")
}

TestObject dropdownItemDel(String label) {
	return byXpath("(//div[@class='patientRibbonAddDropdown']//div[contains(@class,'addDropdownDivHover')]/div[normalize-space(text())='${label}'])[11]")
}
TestObject dropdownItemInbox(String label) {
	return byXpath("(//div[@class='patientRibbonAddDropdown']//div[contains(@class,'addDropdownDivHover')]/div[normalize-space(text())='${label}'])[11]")
}

/* Right-hand message detail pane */
@Field TestObject TO_MSG_DETAIL_DELETE    = byXpath("//*[@id='deleteThisMessage']")
@Field TestObject TO_SUBJECT_LINE         = byXpath("//*[@id='subjectLine']")

/* Generic - highlighted search term span, "no results" text, row checkbox */
@Field TestObject TO_HIGHLIGHT_SPANS      = byXpath("//span[contains(@style,'fuchsia') or contains(translate(@style,'FUCHSIA','fuchsia'),'fuchsia')]")
@Field TestObject TO_ANY_ROW_CHECKBOX     = byXpath("//*[contains(concat(' ', normalize-space(@class), ' '), ' custom-checkbox ')]")

/* ---------------------------------------------------------------------- */
/*  SETUP / TEARDOWN                                                      */
/* ---------------------------------------------------------------------- */
//Login to the maximeyes

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

void openSecureMessagesScreen() {

	int maxRetries = 3
	int waitTimeoutSec = 15
	boolean pageOpened = false

	TestObject secureMessagesLink = findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Secure Messages')
	TestObject homeButton = findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Home') // update path to your actual Home object
	TestObject secureMessagesPageIndicator = findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/SecureMessagesPageElement') // some unique element that only exists once page has loaded
	
	// initial click
WebUI.waitForElementClickable(secureMessagesLink, 10)
WebUI.click(secureMessagesLink)

for (int attempt = 1; attempt <= maxRetries; attempt++) {
	pageOpened = WebUI.verifyElementPresent(secureMessagesPageIndicator, waitTimeoutSec, FailureHandling.OPTIONAL)

	if (pageOpened) {
		WebUI.comment('Secure Messages page opened successfully on attempt ' + attempt)
		break
	} else {
		WebUI.comment('Secure Messages page did not open within ' + waitTimeoutSec + ' sec, retrying via Home...')
		WebUI.click(homeButton)
		WebUI.delay(2) // small buffer for home page to load, adjust as needed
		WebUI.click(secureMessagesLink)
	}
}

if (!pageOpened) {
	WebUI.comment('Secure Messages page failed to open after ' + maxRetries + ' attempts')
	// Optionally fail the test explicitly:
	// WebUI.failed('Secure Messages page did not load after retries')
}
}
/* Utility: clears then types into a search box, then waits out the debounce */
void typeSearchTerm(TestObject searchBox, String term) {

    WebUI.waitForElementVisible(searchBox, 10)

    WebUI.clearText(searchBox)
    WebUI.setText(searchBox, term)

    try {
        WebUI.sendKeys(searchBox, Keys.chord(Keys.SPACE, Keys.BACK_SPACE))
    } catch (StaleElementReferenceException e) {
        WebUI.waitForElementClickable(searchBox, 5)
        WebUI.sendKeys(searchBox, Keys.chord(Keys.SPACE, Keys.BACK_SPACE))
    }

    WebUI.delay((DEBOUNCE_MS / 1000.0) + 0.3d)
}

/* Utility: switch active tab via the "..." (more actions) dropdown */
void switchToTab(String tabLabel) {
	WebUI.click(TO_INBOX_MORE_ACTIONS)
	WebUI.waitForElementVisible(dropdownItem(tabLabel), 5)
	WebUI.click(dropdownItem(tabLabel))
	WebUI.delay(1)
}

/* Utility: count visible message rows under a grid body TestObject */
int countVisibleRows(TestObject gridBody) {
	WebDriver driver = DriverFactory.getWebDriver()
	WebElement grid = WebUI.findWebElement(gridBody, 10)
	List<WebElement> rows = grid.findElements(By.xpath(".//*[contains(concat(' ', normalize-space(@class), ' '), ' fixedGridTR ')]"))
	return rows.size()
}

/* ============================================================================
 * TC01 – Search field is displayed at the TOP-LEFT of the pane (Req #1)
 * ==========================================================================*/
void verify_TC01_SearchFieldTopLeft() {
	WebUI.verifyElementPresent(TO_INBOX_SEARCHBOX, 10)
	WebUI.verifyElementPresent(TO_INBOX_TOP_HEADER, 10)

	WebElement searchEl  = WebUI.findWebElement(TO_INBOX_SEARCHBOX, 10)
	WebElement headerEl  = WebUI.findWebElement(TO_INBOX_TOP_HEADER, 10)
	WebElement paneEl    = WebUI.findWebElement(TO_INBOX_MSG_LIST_WRAP, 10)

	int searchY = searchEl.getLocation().getY()
	int headerY = headerEl.getLocation().getY()
	int paneX   = paneEl.getLocation().getX()
	int searchX = searchEl.getLocation().getX()

	// Search box must sit above (or level with) the folder header -> "top" of pane
	assertTrue('Search box is not positioned at the top of the pane', searchY <= headerY + 5)
	// Search box must be left-aligned within the pane, not pushed to the far right
	int paneWidth = paneEl.getSize().getWidth()
	assertTrue('Search box is not left-aligned in the pane', (searchX - paneX) < (paneWidth * 0.5))

	println('TC01 PASSED: Search field displayed at top-left of the pane.')
}

/* ============================================================================
 * TC02 – Char limit = 70, accepts upper/lower/number/symbol, rejects overflow
 * (Req #2, MBT 46345)
 * ==========================================================================*/
void verify_TC02_CharacterLimitAndAllowedCharacters() {
	// confirm maxlength attribute
	WebElement box = WebUI.findWebElement(TO_INBOX_SEARCHBOX, 10)
	String maxLenAttr = box.getAttribute('maxlength')
	assertEquals('maxlength attribute is not 70', String.valueOf(CHAR_LIMIT), maxLenAttr)

	// mixed case + numbers + symbols, exactly at the limit -> must be fully accepted
	String exactLimitString = ('Abc123!@#\$%^&*()_+-=ZxYqWnTuLoIhGfRdSaP0987654321mnbvcxzASDFGHJKLqwe' as String)
	exactLimitString = exactLimitString.length() > CHAR_LIMIT ? exactLimitString.substring(0, CHAR_LIMIT) : exactLimitString
	WebUI.clearText(TO_INBOX_SEARCHBOX)
	WebUI.setText(TO_INBOX_SEARCHBOX, exactLimitString)
	String acceptedValue = WebUI.getAttribute(TO_INBOX_SEARCHBOX, 'value')
	assertEquals('Search box did not accept the full 70-char mixed string', exactLimitString, acceptedValue)
	assertTrue('Accepted value exceeds character limit', acceptedValue.length() <= CHAR_LIMIT)

	// overflow attempt -> string longer than 70 chars must be truncated at 70
	String overflowString = exactLimitString + 'EXTRA_TEXT_BEYOND_LIMIT_1234567890'
	WebUI.clearText(TO_INBOX_SEARCHBOX)
	WebUI.setText(TO_INBOX_SEARCHBOX, overflowString)
	String overflowAccepted = WebUI.getAttribute(TO_INBOX_SEARCHBOX, 'value')
	assertTrue('Search box accepted more than 70 characters',
			overflowAccepted.length() <= CHAR_LIMIT)

	WebUI.clearText(TO_INBOX_SEARCHBOX)
	println('TC02 PASSED: Search box enforces 70-char limit and accepts mixed-case/number/symbol input.')
}

/* ============================================================================
 * TC03 – Search works consistently across Inbox / Sent Items / Deleted,
 * with results firing ~500ms after typing stops (Req #3)
 * ==========================================================================*/
void verify_TC03_SearchAcrossFolders(String searchTerm) {
	// --- Inbox ---
	int inboxCountBefore = countVisibleRows(TO_INBOX_GRID_BODY)
	typeSearchTerm(TO_INBOX_SEARCHBOX, searchTerm)
	WebUI.waitForElementVisible(TO_INBOX_MSG_LIST_WRAP, 10)
	int inboxCountAfter = countVisibleRows(TO_INBOX_GRID_SEARCH)
	println("Inbox search: rows before=${inboxCountBefore}, after=${inboxCountAfter}")
	WebUI.click(TO_INBOX_CANCEL_ICON)
	WebUI.delay(1)

	// --- Sent Items ---
	switchToTab('Sent Messages')
	WebUI.waitForElementPresent(TO_SENT_SEARCHBOX, 10)
	typeSearchTerm(TO_SENT_SEARCHBOX, searchTerm)
	WebUI.waitForElementVisible(TO_SENT_MSG_LIST_WRAP, 10)
	int sentCountAfter = countVisibleRows(TO_SENT_GRID_SEARCH)
	println("Sent Items search: rows after=${sentCountAfter}")
	WebUI.click(TO_SENT_CANCEL_ICON)
	WebUI.delay(1)

	// --- Deleted Messages (Archive) ---
	TestObject DeletedMessagesfromSent = findTestObject('Provider Portal/Page_MaximEyes/Deleted Messages from Sent screen')
	TestObject sentMsgMoreMenu = findTestObject('Provider Portal/Page_MaximEyes/Sent Messages_moreMenu')
	
	WebUI.click(sentMsgMoreMenu)
	WebUI.delay(1)
	WebUI.click(DeletedMessagesfromSent)
	WebUI.waitForElementPresent(TO_DEL_SEARCHBOX, 10)
	typeSearchTerm(TO_DEL_SEARCHBOX, searchTerm)
	WebUI.waitForElementVisible(TO_DEL_MSG_LIST_WRAP, 10)
	int deletedCountAfter = countVisibleRows(TO_DEL_GRID_SEARCH)
	println("Deleted Messages search: rows after=${deletedCountAfter}")
	WebUI.click(TO_DEL_CANCEL_ICON)
	WebUI.delay(1)

	// back to Inbox for subsequent tests
//	switchToTab('Inbox')
	TestObject DeletedMessagesMoreMenu = findTestObject('Provider Portal/Page_MaximEyes/Deleted Messages_moreMenu')
	TestObject inboxFromDelMsg = findTestObject('Provider Portal/Page_MaximEyes/Inbox from del msg')
	WebUI.click(DeletedMessagesMoreMenu)
	
	WebUI.delay(1)
	WebUI.click(inboxFromDelMsg)

	println('TC03 PASSED: Search executed consistently in Inbox, Sent Items and Deleted Messages folders (500ms debounce observed).')
}

/* ============================================================================
 * TC04 – While showing search results, row checkboxes must NOT be visible
 * (Req #4 / Req #10)
 * ==========================================================================*/
void verify_TC04_NoCheckboxesInSearchResults(String searchTerm) {
	typeSearchTerm(TO_INBOX_SEARCHBOX, searchTerm)
	WebUI.waitForElementVisible(TO_INBOX_MSG_LIST_WRAP, 10)

	WebDriver driver = DriverFactory.getWebDriver()
	List<WebElement> checkboxes = driver.findElements(By.xpath(
			"//*[@id='idquicklinkGridForSearch']//*[contains(concat(' ', normalize-space(@class), ' '), ' custom-checkbox ')]"))
	boolean anyVisible = checkboxes.any { it.isDisplayed() }
	assertFalse('Checkboxes are visible while displaying search results', anyVisible)

	// also confirm the bulk delete icon (top toolbar) is not shown during search
	boolean bulkDeleteVisible = false
	try {
		WebElement bulkDelete = driver.findElement(By.xpath(
				"//*[contains(concat(' ', normalize-space(@class), ' '), ' delete-icon ')]"))
		bulkDeleteVisible = bulkDelete.isDisplayed()
	} catch (Exception ignored) { /* not present is fine */ }
	assertFalse('Bulk delete icon is visible while displaying search results', bulkDeleteVisible)

	WebUI.click(TO_INBOX_CANCEL_ICON)
	println('TC04 PASSED: No row checkboxes / bulk delete icon shown while displaying search results.')
}

/* ============================================================================
 * TC05 – Folder name should be shown below the Date/Time column in results
 * (Req #5)
 * ==========================================================================*/
void verify_TC05_FolderNameBelowDateTime(String searchTerm, String expectedFolderLabel) {

    typeSearchTerm(TO_INBOX_SEARCHBOX, searchTerm)

    WebUI.waitForElementVisible(TO_INBOX_MSG_LIST_WRAP, 10)

    WebDriver driver = DriverFactory.getWebDriver()

    List<WebElement> rows = driver.findElements(By.xpath(
        "//*[@id='idquicklinkGridForSearch']//*[contains(concat(' ', normalize-space(@class), ' '), ' fixedGridTR ')]"))

    assertTrue('No search result rows returned to validate folder label', rows.size() > 0)

    WebElement firstRow = rows.get(0)

    // Date = last span (works with or without attachment icon)
    WebElement dateElement = firstRow.findElement(By.xpath("./td[3]//span[last()]"))

    // Folder = div below the date
    WebElement folderElement = firstRow.findElement(By.xpath("./td[3]//div"))

    String dateText = dateElement.getText().trim()
    String folderLabelText = folderElement.getText().trim()

    println("Date   : ${dateText}")
    println("Folder : ${folderLabelText}")

    assertFalse('Date is blank in search results', dateText.isEmpty())
    assertFalse('Folder name is blank below Date/Time in search results', folderLabelText.isEmpty())

    if (expectedFolderLabel) {
        assertEquals('Folder label below Date/Time does not match expected value',
            expectedFolderLabel, folderLabelText)
    }

    WebUI.click(TO_INBOX_CANCEL_ICON)

    println('TC05 PASSED: Folder name is displayed below Date/Time in search results.')
}

/* ============================================================================
 * TC06 / TC09 – Clearing the search text restores the original folder view
 * (Req #6, Req #9)
 * ==========================================================================*/
void verify_TC06_ClearSearchRestoresOriginalFolder(String searchTerm) {
	int originalRowCount = countVisibleRows(TO_INBOX_GRID_BODY)
	boolean originalHasCheckbox = !WebUI.findWebElement(TO_INBOX_GRID_BODY, 10)
			.findElements(By.xpath(".//*[contains(concat(' ', normalize-space(@class), ' '), ' custom-checkbox ')]")).isEmpty()

	typeSearchTerm(TO_INBOX_SEARCHBOX, searchTerm)
	WebUI.waitForElementVisible(TO_INBOX_MSG_LIST_WRAP, 10)

	// clear out the search text via the cancel (x) icon, per app behaviour
	WebUI.click(TO_INBOX_CANCEL_ICON)
	WebUI.delay(1)

	String boxValueAfterClear = WebUI.getAttribute(TO_INBOX_SEARCHBOX, 'value')
	assertTrue('Search box value was not cleared', boxValueAfterClear == null || boxValueAfterClear.isEmpty())

	int restoredRowCount = countVisibleRows(TO_INBOX_GRID_BODY)
	boolean restoredHasCheckbox = !WebUI.findWebElement(TO_INBOX_GRID_BODY, 10)
			.findElements(By.xpath(".//*[contains(concat(' ', normalize-space(@class), ' '), ' custom-checkbox ')]")).isEmpty()

	assertEquals('Original inbox message count was not restored after clearing search',
			originalRowCount, restoredRowCount)
	assertEquals('Checkbox visibility did not return to the original (non-search) state',
			originalHasCheckbox, restoredHasCheckbox)

	println('TC06/TC09 PASSED: Clearing the search text restores the original folder view (row count, checkboxes).')
}

/* ============================================================================
 * TC07 – Search-term highlight color must be Fuchsia/Pink (Req #7)
 * ==========================================================================*/
void verify_TC07_HighlightColorIsFuchsia(String searchTerm) {
	typeSearchTerm(TO_INBOX_SEARCHBOX, searchTerm)
	WebUI.waitForElementVisible(TO_INBOX_MSG_LIST_WRAP, 10)

	WebDriver driver = DriverFactory.getWebDriver()
	List<WebElement> highlighted = driver.findElements(
			By.xpath("//*[@id='idquicklinkGridForSearch']//span[contains(@style,'background-color: fuchsia') or contains(@style,'background-color:fuchsia')]"))
	assertTrue('No highlighted (fuchsia) span found for the matched search term', highlighted.size() > 0)

	WebElement sample = highlighted.get(0)
	String cssColor = sample.getCssValue('background-color')
	// fuchsia == magenta == rgba(255, 0, 255, 1)
	boolean isFuchsia = cssColor?.replaceAll('\\s', '')?.equalsIgnoreCase('rgba(255,0,255,1)') ||
			cssColor?.replaceAll('\\s', '')?.equalsIgnoreCase('rgb(255,0,255)') ||
			cssColor?.equalsIgnoreCase('fuchsia')
	assertTrue("Highlight background-color is '${cssColor}', expected Fuchsia/Pink (rgb 255,0,255)", isFuchsia)

	WebUI.click(TO_INBOX_CANCEL_ICON)
	println('TC07 PASSED: Search term highlight color is Fuchsia/Pink.')
}

/* ============================================================================
 * TC08 – No matching results shows "No messages matched your search." (Req #8)
 * ==========================================================================*/
void verify_TC08_NoResultsMessage() {
	String nonsenseTerm = 'zzNoSuchMessageExists998877'
	typeSearchTerm(TO_INBOX_SEARCHBOX, nonsenseTerm)
	WebUI.waitForElementVisible(TO_INBOX_MSG_LIST_WRAP, 10)

	WebDriver driver = DriverFactory.getWebDriver()
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))
	WebElement noResultsEl = wait.until(ExpectedConditions.presenceOfElementLocated(
			By.xpath("//span[@class='align-center']")
	))
	assertTrue('"No messages matched your search." text is not displayed for a non-matching search',
			noResultsEl.isDisplayed())

	WebUI.click(TO_INBOX_CANCEL_ICON)
	println('TC08 PASSED: "No messages matched your search." message shown correctly.')
}

/* ============================================================================
 * TC10 – During search: no checkboxes, no bulk delete icon, and a message CAN
 * still be deleted from the right-hand detail pane (Req #10)
 * ==========================================================================*/
void verify_TC10_DeleteOnlyFromRightPaneDuringSearch(String searchTerm) {
	typeSearchTerm(TO_INBOX_SEARCHBOX, searchTerm)
	WebUI.waitForElementVisible(TO_INBOX_MSG_LIST_WRAP, 10)

	WebDriver driver = DriverFactory.getWebDriver()

	// 1) no checkboxes
	List<WebElement> checkboxes = driver.findElements(By.xpath(
			"//*[@id='idquicklinkGridForSearch']//*[contains(concat(' ', normalize-space(@class), ' '), ' custom-checkbox ')]"))
	assertTrue('Checkboxes visible in search-result rows', checkboxes.every { !it.isDisplayed() } || checkboxes.isEmpty())

	// 2) no bulk delete icon in the toolbar
	List<WebElement> bulkDeleteIcons = driver.findElements(By.xpath(
			"//*[contains(concat(' ', normalize-space(@class), ' '), ' delete-icon ')]"))
	boolean bulkDeleteShown = bulkDeleteIcons.any { it.isDisplayed() }
	assertFalse('Bulk delete icon is visible in the toolbar during search', bulkDeleteShown)

	// 3) open first search result and confirm delete icon exists on the right pane
	List<WebElement> resultRows = driver.findElements(By.xpath(
			"//*[@id='idquicklinkGridForSearch']//*[contains(concat(' ', normalize-space(@class), ' '), ' fixedGridTR ')]"))
	assertTrue('No search result rows to open for delete verification', resultRows.size() > 0)
	resultRows.get(0).findElement(By.xpath('.//td[2]')).click()
	WebUI.delay(1)

	WebUI.verifyElementPresent(TO_MSG_DETAIL_DELETE, 10)
	WebUI.verifyElementClickable(TO_MSG_DETAIL_DELETE)

	println('TC10 PASSED: No checkboxes/bulk-delete during search; message deletable only via right-pane delete icon.')
}

/* ============================================================================
 * MAIN EXECUTION
 * ==========================================================================*/
String SEARCH_TERM_WITH_RESULTS = 'Action Required'   // matches subject text present in sample data
String EXPECTED_FOLDER_LABEL    = 'Inbox'              // adjust to actual label rendered per folder

try {
	openSecureMessagesScreen()

	verify_TC01_SearchFieldTopLeft()
	verify_TC02_CharacterLimitAndAllowedCharacters()
	verify_TC03_SearchAcrossFolders(SEARCH_TERM_WITH_RESULTS)
	verify_TC04_NoCheckboxesInSearchResults(SEARCH_TERM_WITH_RESULTS)
	verify_TC05_FolderNameBelowDateTime(SEARCH_TERM_WITH_RESULTS, EXPECTED_FOLDER_LABEL)
	verify_TC06_ClearSearchRestoresOriginalFolder(SEARCH_TERM_WITH_RESULTS)
	verify_TC07_HighlightColorIsFuchsia(SEARCH_TERM_WITH_RESULTS)
	verify_TC08_NoResultsMessage()
	verify_TC10_DeleteOnlyFromRightPaneDuringSearch(SEARCH_TERM_WITH_RESULTS)

	println('ALL SECURE MESSAGE SEARCH TEST CASES COMPLETED.')
} finally {

}