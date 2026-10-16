package custom

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Preference-based keywords for:
 *   Patient Records > General Office Admin > Online Intake preferences
 *
 * Notes
 *  - The page uses a custom (Flat-UI style) skin: native <input>/<select> are often
 *    hidden behind styled spans, so state changes are done with JavaScript and the
 *    real 'click' / 'change' events are fired so the inline handlers
 *    (SavePatientRecordGeneralOfficeAdminFormData, UpdatePatientRecord..., etc.) run.
 *  - Every setter is idempotent: it only acts if the current state differs.
 *
 * Usage examples
 *   CustomKeywords.'com.maximeyes.keywords.IntakePreferences.setIntakeActive'(true)
 *   CustomKeywords.'com.maximeyes.keywords.IntakePreferences.selectFormsAvailableOnline'('HIPAA, Billing Policy Only')
 *   CustomKeywords.'com.maximeyes.keywords.IntakePreferences.setIntakePreferences'([
 *        active: true, formsOnline: 'Patient History Only', insurance: false,
 *        referringPhysicians: true, howFound: false,
 *        landingText: 'Welcome!', importMode: 'Manual Import Only'])
 */
class IntakeOAPreferences {

	// ---------- element IDs (from the DOM) ----------
	private static final String ID_ACTIVE          = 'SyncActive'
	private static final String ID_FORMS_ONLINE    = 'FormsAvailableOnlinePreferenceValue'
	private static final String ID_INS_ENABLE      = 'INSURANCE_PAGE_ENABLE'
	private static final String ID_INS_DISABLE     = 'INSURANCE_PAGE_DISABLE'
	private static final String ID_REF_ENABLE      = 'REFERRING_PHYSICIANS_ENABLE'
	private static final String ID_REF_DISABLE     = 'REFERRING_PHYSICIANS_DISABLE'
	private static final String ID_HOWFOUND_ENABLE = 'HOW_FOUND_ENABLE'
	private static final String ID_HOWFOUND_DISABLE= 'HOW_FOUND_DISABLE'
	private static final String ID_LANDING_TEXT    = 'intakeLandingPageTextID'
	private static final String ID_IMPORT_MODE     = 'ImportWelcomeFormPreferenceValue'
	private static final String ID_DEFAULT_FORM    = 'DefaultIntakeFormPreferenceValue'
	private static final String ID_INTAKE_URL      = 'npwfurl'
	private static final String ID_OFFICE_URL      = 'ODurl'

	private static final int LANDING_TEXT_MAX = 80
	private static final int TIMEOUT = 10
	private static final int RELOAD_DETECT_SECONDS = 5   // how long to watch for a reload after a change
	private static final int PAGE_LOAD_TIMEOUT = 30

	// ---------- helpers ----------
	private static TestObject byId(String id) {
		TestObject to = new TestObject("pref_" + id)
		to.addProperty('xpath', ConditionType.EQUALS, "//*[@id='${id}']")
		return to
	}

	private static JavascriptExecutor js() {
		return (JavascriptExecutor) DriverFactory.getWebDriver()
	}

	private static boolean isChecked(String id) {
		return (Boolean) js().executeScript("return document.getElementById(arguments[0]).checked;", id)
	}

	/** Tag the current document so we can tell later whether the page was reloaded. */
	private static void markPage() {
		js().executeScript("window.__prefMarker = true;")
	}

	/**
	 * Call after an action that saves a preference. If the page reloads (marker lost),
	 * wait for the new page to finish loading. If it doesn't reload within
	 * RELOAD_DETECT_SECONDS, assume a no-reload (AJAX) save and carry on.
	 */
	private static void waitAfterSave() {
		boolean reloaded = false
		long end = System.currentTimeMillis() + (RELOAD_DETECT_SECONDS * 1000L)
		while (System.currentTimeMillis() < end) {
			try {
				Object m = js().executeScript("return window.__prefMarker === true;")
				if (!(Boolean) m) { reloaded = true; break }
			} catch (Exception e) {
				reloaded = true   // script failed mid-navigation => page is reloading
				break
			}
			Thread.sleep(250)
		}
		if (reloaded) {
			WebUI.waitForPageLoad(PAGE_LOAD_TIMEOUT)
		}
		// give the document a moment to settle either way
		WebUI.delay(1)
	}

	private static void jsClick(String id) {
		markPage()
		js().executeScript("document.getElementById(arguments[0]).click();", id)
		waitAfterSave()
	}

	/** Set <select> by value or visible text and fire 'change' so the inline onchange handler saves it. */
	private static void jsSelect(String id, String valueOrText) {
		markPage()
		Object ok = js().executeScript('''
			var s = document.getElementById(arguments[0]);
			var want = arguments[1], idx = -1;
			for (var i = 0; i < s.options.length; i++) {
				if (s.options[i].value === want || s.options[i].text.trim() === want) { idx = i; break; }
			}
			if (idx < 0) return false;
			s.selectedIndex = idx;
			// keep the styled "holder" span in sync
			var holder = s.parentNode.querySelector('.holder');
			if (holder) holder.textContent = s.options[idx].text;
			s.dispatchEvent(new Event('change', {bubbles: true}));
			return true;
		''', id, valueOrText)
		if (!(Boolean) ok) {
			WebUI.comment("Option '${valueOrText}' not found in #${id}")
			throw new IllegalArgumentException("Option '${valueOrText}' not found in dropdown #${id}")
		}
		waitAfterSave()   // the page reloads after a dropdown change
	}

	/**
	 * Reads the selected option of a <select>, retrying while the element is missing.
	 * The page re-renders after a preference is saved, so the node can briefly be null.
	 * Returns [text, value].
	 */
	private static List<String> readSelected(String id) {
		long end = System.currentTimeMillis() + (TIMEOUT * 1000L)
		while (System.currentTimeMillis() < end) {
			try {
				Object r = js().executeScript('''
					var s = document.getElementById(arguments[0]);
					if (!s || s.selectedIndex < 0) return null;
					var o = s.options[s.selectedIndex];
					return [o.text.trim(), o.value];
				''', id)
				if (r != null) {
					return [(String) r[0], (String) r[1]]
				}
			} catch (Exception ignored) {
				// stale/navigating DOM - retry
			}
			Thread.sleep(300)
		}
		throw new IllegalStateException("Dropdown #${id} not found after ${TIMEOUT}s (page may have navigated or the id changed)")
	}

	private static String selectedText(String id) {
		return readSelected(id)[0]
	}

	private static String selectedValue(String id) {
		return readSelected(id)[1]
	}

	/** Enable/Disable radio pair helper. */
	private static void setRadioPair(String enableId, String disableId, boolean enable) {
		WebUI.waitForElementPresent(byId(enableId), TIMEOUT)
		String target = enable ? enableId : disableId
		if (!isChecked(target)) {
			jsClick(target)
			WebUI.delay(1)
		}
		assert isChecked(target) : "Radio #${target} was not selected"
	}

	// ---------- Is Active ----------
	@Keyword
	def setIntakeActive(boolean active) {
		WebUI.waitForElementPresent(byId(ID_ACTIVE), TIMEOUT)
		if (isChecked(ID_ACTIVE) != active) {
			jsClick(ID_ACTIVE)   // fires onclick=UpdatePatientRecordGeneralOfficeAdminIntakeEnable
			WebUI.delay(1)
		}
		assert isChecked(ID_ACTIVE) == active : "'Is Active?' expected ${active}"
	}

	@Keyword
	def verifyIntakeActive(boolean expected) {
		WebUI.verifyEqual(isChecked(ID_ACTIVE), expected, FailureHandling.CONTINUE_ON_FAILURE)
	}

	// ---------- Select Forms Available Online ----------
	/** Options: 'HIPAA, Billing Policy, Patient History' | 'HIPAA, Billing Policy Only' | 'Patient History Only' */
	@Keyword
	def selectFormsAvailableOnline(String option) {
		jsSelect(ID_FORMS_ONLINE, option)
		WebUI.delay(1)
		assert selectedText(ID_FORMS_ONLINE) == option
	}

	@Keyword
	def verifyFormsAvailableOnline(String expected) {
		WebUI.verifyEqual(selectedText(ID_FORMS_ONLINE), expected, FailureHandling.CONTINUE_ON_FAILURE)
	}

	// ---------- Insurance on Intake form ----------
	@Keyword
	def setInsuranceOnIntake(boolean enable) {
		setRadioPair(ID_INS_ENABLE, ID_INS_DISABLE, enable)
	}

	@Keyword
	def verifyInsuranceOnIntake(boolean expectedEnabled) {
		WebUI.verifyEqual(isChecked(ID_INS_ENABLE), expectedEnabled, FailureHandling.CONTINUE_ON_FAILURE)
	}

	// ---------- Include Referring & Primary Care Physicians ----------
	@Keyword
	def setReferringPhysicians(boolean enable) {
		setRadioPair(ID_REF_ENABLE, ID_REF_DISABLE, enable)
	}

	@Keyword
	def verifyReferringPhysicians(boolean expectedEnabled) {
		WebUI.verifyEqual(isChecked(ID_REF_ENABLE), expectedEnabled, FailureHandling.CONTINUE_ON_FAILURE)
	}

	// ---------- Use How Found Page ----------
	@Keyword
	def setHowFoundPage(boolean enable) {
		setRadioPair(ID_HOWFOUND_ENABLE, ID_HOWFOUND_DISABLE, enable)
	}

	@Keyword
	def verifyHowFoundPage(boolean expectedEnabled) {
		WebUI.verifyEqual(isChecked(ID_HOWFOUND_ENABLE), expectedEnabled, FailureHandling.CONTINUE_ON_FAILURE)
	}

	// ---------- Intake form landing page text (max 80 chars, saved on keyup) ----------
	@Keyword
	def setLandingPageText(String text) {
		if (text.length() > LANDING_TEXT_MAX) {
			throw new IllegalArgumentException("Landing text is ${text.length()} chars; max is ${LANDING_TEXT_MAX}")
		}
		TestObject to = byId(ID_LANDING_TEXT)
		WebUI.waitForElementVisible(to, TIMEOUT)
		WebUI.clearText(to)
		// sendKeys (not setText) so the onkeyup save handler fires
		WebUI.sendKeys(to, text)
		WebUI.delay(1)
		assert WebUI.getAttribute(to, 'value') == text
	}

	@Keyword
	def verifyLandingPageText(String expected) {
		WebUI.verifyEqual(WebUI.getAttribute(byId(ID_LANDING_TEXT), 'value'), expected, FailureHandling.CONTINUE_ON_FAILURE)
	}

	@Keyword
	def verifyLandingPageTextMaxLength() {
		WebUI.verifyEqual(WebUI.getAttribute(byId(ID_LANDING_TEXT), 'maxlength'), String.valueOf(LANDING_TEXT_MAX),
				FailureHandling.CONTINUE_ON_FAILURE)
	}

	// ---------- Import Intake Form ----------
	/** Options: 'Manual Import Only' | 'Automatically Import' */
	@Keyword
	def selectImportIntakeForm(String option) {
		jsSelect(ID_IMPORT_MODE, option)
		WebUI.delay(1)
		assert selectedText(ID_IMPORT_MODE) == option
	}

	@Keyword
	def verifyImportIntakeForm(String expected) {
		WebUI.verifyEqual(selectedText(ID_IMPORT_MODE), expected, FailureHandling.CONTINUE_ON_FAILURE)
	}

	// ---------- Default Intake Form (match by visible text OR option value) ----------
	@Keyword
	def selectDefaultIntakeForm(String textOrValue) {
		jsSelect(ID_DEFAULT_FORM, textOrValue)
		WebUI.delay(1)
		// element may be re-rendered after save; wait for it to come back
		WebUI.waitForElementPresent(byId(ID_DEFAULT_FORM), TIMEOUT)
		List<String> sel = readSelected(ID_DEFAULT_FORM)
		assert (sel[0] == textOrValue || sel[1] == textOrValue) :
			"Default Intake Form is '${sel[0]}' (${sel[1]}), expected '${textOrValue}'"
	}

	@Keyword
	def verifyDefaultIntakeForm(String expectedText) {
		WebUI.verifyEqual(selectedText(ID_DEFAULT_FORM), expectedText, FailureHandling.CONTINUE_ON_FAILURE)
	}

	// ---------- URLs ----------
	@Keyword
	def String getOnlineIntakeUrl() {
		return WebUI.getText(byId(ID_INTAKE_URL)).trim()
	}

	@Keyword
	def String getInOfficeDeviceUrl() {
		return WebUI.getText(byId(ID_OFFICE_URL)).trim()
	}

	/** In-office URL should be the intake URL + ?haveverificationcode=true */
	@Keyword
	def verifyUrlRelationship() {
		String intake = getOnlineIntakeUrl()
		String office = getInOfficeDeviceUrl()
		WebUI.verifyEqual(office, intake + '?haveverificationcode=true', FailureHandling.CONTINUE_ON_FAILURE)
	}

	// ---------- Master keyword: apply any subset of preferences from a map ----------
	/**
	 * Keys (all optional):
	 *   active (boolean), formsOnline (String), insurance (boolean),
	 *   referringPhysicians (boolean), howFound (boolean),
	 *   landingText (String), importMode (String), defaultForm (String)
	 */
	@Keyword
	def setIntakePreferences(Map prefs) {
		if (prefs.containsKey('active'))              setIntakeActive((boolean) prefs.active)
		if (prefs.containsKey('formsOnline'))         selectFormsAvailableOnline((String) prefs.formsOnline)
		if (prefs.containsKey('insurance'))           setInsuranceOnIntake((boolean) prefs.insurance)
		if (prefs.containsKey('referringPhysicians')) setReferringPhysicians((boolean) prefs.referringPhysicians)
		if (prefs.containsKey('howFound'))            setHowFoundPage((boolean) prefs.howFound)
		if (prefs.containsKey('landingText'))         setLandingPageText((String) prefs.landingText)
		if (prefs.containsKey('importMode'))          selectImportIntakeForm((String) prefs.importMode)
		if (prefs.containsKey('defaultForm'))         selectDefaultIntakeForm((String) prefs.defaultForm)
	}

	/** Same keys as setIntakePreferences; soft-verifies every supplied value. */
	@Keyword
	def verifyIntakePreferences(Map prefs) {
		if (prefs.containsKey('active'))              verifyIntakeActive((boolean) prefs.active)
		if (prefs.containsKey('formsOnline'))         verifyFormsAvailableOnline((String) prefs.formsOnline)
		if (prefs.containsKey('insurance'))           verifyInsuranceOnIntake((boolean) prefs.insurance)
		if (prefs.containsKey('referringPhysicians')) verifyReferringPhysicians((boolean) prefs.referringPhysicians)
		if (prefs.containsKey('howFound'))            verifyHowFoundPage((boolean) prefs.howFound)
		if (prefs.containsKey('landingText'))         verifyLandingPageText((String) prefs.landingText)
		if (prefs.containsKey('importMode'))          verifyImportIntakeForm((String) prefs.importMode)
		if (prefs.containsKey('defaultForm'))         verifyDefaultIntakeForm((String) prefs.defaultForm)
	}
}