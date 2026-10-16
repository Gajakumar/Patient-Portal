package custom

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil


public class CreateNewEncounter {



// ---------- Element IDs taken from the page HTML ----------
	private static final String ENCOUNTER_TYPE_ID = 'EncounterTypeID'
	private static final String LOCATION_ID       = 'NewPE_PracticeLocationID'
	private static final String EXAM_DATE_ID      = 'NewPE_ExamDate'
	private static final String PROVIDER_ID       = 'NewPE_ProviderID'
	private static final String TECHNICIAN_ID     = 'NewPE_TechnicianID'
	private static final String REF_PHYSICIAN_ID  = 'NewPE_RefPhysicianID'
 
	// ---------- Object Repository paths ----------
	private static final String SAVE_BTN_PATH =
			'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'

	private static final String CREATE_NEW_ENCOUNTER_BTN_PATH =
			'Maximeyes_Portal_Mix/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter'
	private static final String BUSY_INDICATOR_PATH = 'Page_MaximEyes/Busy Indicator'
 
	/**
	 * Fills the New Patient Encounter form and saves it.
	 * Pass null or empty for any optional field (technician, refPhysician, examDate) to skip it.
	 *
	 * @param encounterType e.g. "Annual Vision Exam"
	 * @param location      e.g. "Hillsboro"
	 * @param examDate      MM/dd/yyyy e.g. "10/16/2026" (null/"" keeps the default - today)
	 * @param provider      e.g. "Albert Davis"
	 * @param technician    e.g. "QA User" (optional)
	 * @param refPhysician  text to search in the autocomplete (optional)
	 */
	@Keyword
	def fillNewEncounter(String encounterType, String location, String examDate,
			String provider, String technician = null, String refPhysician = null) {
 
		selectEncounterType(encounterType)
		selectExamLocation(location)      // location change reloads dependent data, so do it before provider
		setExamDate(examDate)
		selectProvider(provider)
		selectTechnician(technician)
		setReferringPhysician(refPhysician)
 
		KeywordUtil.logInfo("Encounter form filled: type=${encounterType}, location=${location}, " +
				"date=${examDate}, provider=${provider}, tech=${technician}, refPhy=${refPhysician}")
 
		saveEncounter()
	}
 
	/**
	 * Clicks Save, then clicks "Create New Encounter" if it appears (optional),
	 * and waits for the busy indicator to disappear.
	 */
	@Keyword
	def saveEncounter() {
		// click on save button
		WebUI.click(findTestObject(SAVE_BTN_PATH))
 
		// Check if button is present within 5 seconds
		TestObject createNewEncounterBtn = findTestObject(CREATE_NEW_ENCOUNTER_BTN_PATH)
		if (WebUI.verifyElementPresent(createNewEncounterBtn, 5, FailureHandling.OPTIONAL)) {
			WebUI.click(createNewEncounterBtn)
			KeywordUtil.logInfo('Create New Encounter button clicked')
		} else {
			KeywordUtil.logInfo('Create New Encounter button not displayed - skipping click')
		}
 
		WebUI.waitForElementNotVisible(findTestObject(BUSY_INDICATOR_PATH), 30)
	}
 
	@Keyword
	def selectEncounterType(String text) {
		selectByVisibleText(ENCOUNTER_TYPE_ID, text, 'Encounter Type')
	}
 
	@Keyword
	def selectExamLocation(String text) {
		selectByVisibleText(LOCATION_ID, text, 'Exam Location')
		WebUI.delay(1)   // let peLocationChangeEvent finish
	}
 
	@Keyword
	def selectProvider(String text) {
		selectByVisibleText(PROVIDER_ID, text, 'Provider')
	}
 
	@Keyword
	def selectTechnician(String text) {
		if (isBlank(text)) return
		selectByVisibleText(TECHNICIAN_ID, text, 'Technician')
	}
 
	/** The Exam Date input is readonly, so the value is set via JS and a change event is fired. */
	@Keyword
	def setExamDate(String date) {
		if (isBlank(date)) return
		TestObject to = byId(EXAM_DATE_ID)
		WebUI.waitForElementPresent(to, 10)
		String script = '''
			var el = document.getElementById(arguments[0]);
			el.removeAttribute('readonly');
			el.value = arguments[1];
			el.dispatchEvent(new Event('input',  {bubbles: true}));
			el.dispatchEvent(new Event('change', {bubbles: true}));
			el.setAttribute('readonly', 'readonly');
			return el.value;
		'''
		def result = WebUI.executeJavaScript(script, [EXAM_DATE_ID, date])
		if (result != date) {
			KeywordUtil.markFailed("Could not set Exam Date. Expected '${date}' but found '${result}'")
		}
	}
 
	/** Types into the autocomplete and clicks the first matching suggestion. */
	@Keyword
	def setReferringPhysician(String name) {
		if (isBlank(name)) return
		TestObject input = byId(REF_PHYSICIAN_ID)
		WebUI.waitForElementClickable(input, 10)
		WebUI.click(input)
		WebUI.clearText(input)
		WebUI.sendKeys(input, name)
 
		String safe = name.replace("'", "\\'")
		TestObject suggestion = new TestObject('refPhySuggestion')
		suggestion.addProperty('xpath', ConditionType.EQUALS,
				"(//ul[contains(@class,'ui-autocomplete') and not(contains(@style,'display: none'))]" +
				"//li[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" +
				safe.toLowerCase() + "')])[1]")
 
		if (WebUI.waitForElementClickable(suggestion, 10, FailureHandling.OPTIONAL)) {
			WebUI.click(suggestion)
		} else {
			KeywordUtil.markFailed("No referring physician suggestion found for '${name}'")
		}
	}
 
	// ---------- helpers ----------
 
	/**
	 * Selects an <option> by its visible text. Tries the normal Katalon way first;
	 * falls back to JavaScript because some selects here use class "wm-hide" (hidden/custom widget).
	 * The JS fallback also fires 'change' so inline handlers (onchange="...") run.
	 */
	private void selectByVisibleText(String selectId, String text, String fieldName) {
		if (isBlank(text)) {
			KeywordUtil.markFailed("${fieldName} value is required")
			return
		}
		TestObject to = byId(selectId)
		WebUI.waitForElementPresent(to, 15)
 
		boolean done = false
		try {
			if (WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL)) {
				WebUI.selectOptionByLabel(to, '^' + java.util.regex.Pattern.quote(text) + '$', true, FailureHandling.STOP_ON_FAILURE)
				done = true
			}
		} catch (Exception ignored) {
			done = false
		}
 
		if (!done) {
			String script = '''
				var sel = document.getElementById(arguments[0]);
				var wanted = arguments[1].trim().toLowerCase();
				for (var i = 0; i < sel.options.length; i++) {
					if (sel.options[i].text.trim().toLowerCase() === wanted) {
						sel.selectedIndex = i;
						sel.dispatchEvent(new Event('change', {bubbles: true}));
						if (window.jQuery) { window.jQuery(sel).trigger('change'); }
						return true;
					}
				}
				return false;
			'''
			def found = WebUI.executeJavaScript(script, [selectId, text])
			if (!found) {
				KeywordUtil.markFailed("${fieldName}: option '${text}' not found")
			}
		}
		KeywordUtil.logInfo("${fieldName} set to '${text}'")
	}
 
	private TestObject byId(String id) {
		TestObject to = new TestObject("obj_" + id)
		to.addProperty('id', ConditionType.EQUALS, id)
		return to
	}
 
	private boolean isBlank(String s) {
		return s == null || s.trim().isEmpty()
	}
}