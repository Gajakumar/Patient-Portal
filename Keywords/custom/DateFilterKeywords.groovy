
/*
* Katalon Studio - Duration (date range) filter scenarios
*
* HOW TO USE
*  1. Create ONE custom keyword class (Keywords > custom > DateFilterKeywords.groovy) from SECTION 1.
*  2. Create each TEST CASE (SECTIONS 2-8) as its own Test Case script, then add them to a Test Suite.
*  3. Replace the placeholders marked  >>> UPDATE <<<  (trigger button, Search button, result table, etc.).
*
* ASSUMPTIONS (not visible in the DOM you shared - please confirm)
*  - Clicking "Custom" reveals two text inputs (Start / End) in MM/DD/YYYY format.
*    Adjust START_INPUT / END_INPUT locators if different.
*  - Quick-select presets and Confirm apply the range to the page; a "Search" button runs the query.
*  - The error text <p class="text-red-600"> shows validation messages.
*/

// =====================================================================
// SECTION 1 : Custom keyword class  (Keywords/custom/DateFilterKeywords.groovy)
// =====================================================================
package custom

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
import com.kms.katalon.core.util.KeywordUtil


public class DateFilterKeywords {

   static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MM/dd/yyyy")

   // ---------- locators ----------
   static TestObject xp(String xpath) {
	   TestObject to = new TestObject(xpath)
	   to.addProperty("xpath", ConditionType.EQUALS, xpath)
	   return to
   }

   static TestObject DURATION_TRIGGER() {
	return findTestObject('Medical Info/Page_Patient Portal/img_calendar')
}
   static TestObject SEARCH_BTN()       { xp("//button[normalize-space()='Search Record']") }                                      // >>> UPDATE <<<
   static TestObject RESULT_ROWS()      { xp("//table//tbody//tr") }                                                        // >>> UPDATE <<<
//   static TestObject NO_DATA_MSG()      { xp("//*[normalize-space()='No Data to Show']") }
   
   static TestObject NO_DATA_MSG() {
	   return findTestObject('Medical Info/Page_Patient Portal/p_No Data to show')
   }

   static TestObject DIALOG()     { xp("//div[@role='dialog' and @aria-label='Select date range']") }
   static TestObject QUICK(String label) { xp("//div[@role='dialog']//button[normalize-space()='${label}']") }
   static TestObject CUSTOM_BTN() { xp("//div[@role='dialog']//button[normalize-space()='Custom']") }
   static TestObject PRESET_BTN() { xp("//div[@role='dialog']//button[normalize-space()='Presets']") }
   static TestObject CONFIRM_BTN(){ xp("//div[@role='dialog']//button[normalize-space()='Confirm']") }
   static TestObject CANCEL_BTN() { xp("//div[@role='dialog']//button[normalize-space()='Cancel']") }
   static TestObject START_INPUT(){ xp("(//div[@role='dialog']//input)[1]") }          // >>> UPDATE <<<
   static TestObject END_INPUT()  { xp("(//div[@role='dialog']//input)[2]") }          // >>> UPDATE <<<
   static TestObject ERROR_MSG()  { xp("//div[@role='dialog']//p[contains(@class,'text-red-600')]") }
   static TestObject DAY_CELL(String day, String monthLabel) {
	   // day cell inside the calendar block whose heading equals e.g. "Oct 2026"
	   xp("//h3[normalize-space()='${monthLabel}']/ancestor::div[contains(@class,'p-4')][1]" +
		  "//div[contains(@class,'w-8 h-8') and normalize-space()='${day}']")
   }

   // ---------- helpers ----------
 
@Keyword
static void openDurationPicker() {
	WebUI.click(DURATION_TRIGGER())
//	WebUI.verifyElementVisible(DIALOG())
}

   @Keyword
   static void selectQuick(String label) {
	   WebUI.click(QUICK(label))
   }


// Clears an input reliably, then confirms it is empty
@Keyword
static void clearInput(TestObject to) {
	WebUI.waitForElementVisible(to, 5)
	WebUI.click(to)

	boolean isMac = System.getProperty('os.name').toLowerCase().contains('mac')
	WebUI.sendKeys(to, Keys.chord(isMac ? Keys.COMMAND : Keys.CONTROL, 'a'))
	WebUI.sendKeys(to, Keys.chord(Keys.BACK_SPACE))      // fixed

	String value = WebUI.getAttribute(to, 'value')
	if (value != null && value.trim() != '') {
		WebUI.executeJavaScript(
			"var el = arguments[0];" +
			"var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
			"setter.call(el, '');" +
			"el.dispatchEvent(new Event('input',  {bubbles: true}));" +
			"el.dispatchEvent(new Event('change', {bubbles: true}));",
			Arrays.asList(WebUI.findWebElement(to)))
	}

	String after = WebUI.getAttribute(to, 'value')
	if (after != null && after.trim() != '') {
		KeywordUtil.markFailed("Could not clear input. Current value: '${after}'")
	}
}

@Keyword
static void enterCustomRange(String start, String end) {
	// Open Custom mode only if the inputs are not already showing
	if (!WebUI.verifyElementPresent(START_INPUT(), 2, FailureHandling.OPTIONAL)) {
		WebUI.click(CUSTOM_BTN())
	}

	clearInput(START_INPUT())
	if (start) {
		WebUI.setText(START_INPUT(), start)
	}

	clearInput(END_INPUT())
	if (end) {
		WebUI.setText(END_INPUT(), end)
	}
}

   @Keyword
   static String fmt(LocalDate d) { d.format(FMT) }

   @Keyword
   static LocalDate today() { LocalDate.now() }
}