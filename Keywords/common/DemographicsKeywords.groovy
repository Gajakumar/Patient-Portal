package common

import java.util.regex.Matcher

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Keywords for the Intake form -> "Demographics" tab, split into reusable parts.
 *
 *  1. enterDemographics()                      -> fills the random-data fields, RETURNS the entered data
 *  2. verifyDemographics(expected)             -> verifies the intake form against a data map
 *     verifyDemographicsWithOptions(expected, options)
 *                                              -> same, but can run on ANOTHER page (e.g. MaximEyes
 *                                                 welcome form) by overriding locators / frame use
 *  3. clickNextOnDemographics()                -> clicks "Next"
 *  4. fillAndVerifyDemographics(prefilled)     -> old all-in-one behaviour (enter + verify + next)
 *
 * ---------------------------------------------------------------------------------------------
 * DATA MAP (returned by enterDemographics, accepted by the verify keywords). Only the keys that
 * are present (non-null) are verified, so you can verify a subset:
 *
 *   firstName, middle, lastName, suffix, title, preferredName, dob, sex, language, marital,
 *   heightFt, heightIn, heightUnit, weightLbs, weightUnit,
 *   races      : ['Asian', ...]
 *   phones     : [[type: 'Mobile', digits: '5551234567'], ...]   (up to 4)
 *   primaryEmail, email2, addr1, addr2, country, state, city, zip
 *
 * ---------------------------------------------------------------------------------------------
 * EXAMPLE 1 - intake form (same page, same locators):
 *
 *   def entered = CustomKeywords.'common.DemographicsKeywords.enterDemographics'()
 *   def prefilled = [firstName: 'John', lastName: 'Smith', preferredName: 'Johnny',
 *                    dob: '01/15/1985', sex: 'Male', primaryEmail: 'john@example.com']
 *   CustomKeywords.'common.DemographicsKeywords.verifyDemographics'(prefilled + entered)
 *   CustomKeywords.'common.DemographicsKeywords.clickNextOnDemographics'()
 *
 * EXAMPLE 2 - verify the same data on the MaximEyes welcome form (same field locators, different iframe):
 *
 *   CustomKeywords.'common.DemographicsKeywords.verifyDemographicsWithOptions'(prefilled + entered,
 *       [frame: 'welcome'])
 *
 * EXAMPLE 3 - a field has a different locator on the target page (xpath starts with / or ( , else it is an id):
 *
 *   CustomKeywords.'common.DemographicsKeywords.verifyDemographicsWithOptions'(prefilled + entered,
 *       [frame: 'welcome', locators: [city: '//input[@id="cityField"]']])
 * ---------------------------------------------------------------------------------------------
 * Locator override keys: every key of the data map above, plus
 *   race, phoneType1..4, phoneNumber1..4
 * Anything you do not override uses the intake-form locator (the welcome form uses the same ones).
 *
 * Other options for verifyDemographicsWithOptions:
 *   frame        : 'intake' (default) | 'welcome' (MaximEyes welcome form iframe) | 'none' (no iframe)
 *   frameXpath   : custom iframe xpath, if neither built-in iframe matches (wins over "frame")
 *   raceExact    : true (default) = actual races must equal the expected set;
 *                  false = expected races only need to be contained in the displayed text
 */
class DemographicsKeywords {

	// ---------------------------------------------------------------- locators
	// Patient portal intake form iframe
	private static final String INTAKE_FRAME_XPATH =
		"//iframe[@title='Patient Intake Form - Step 1' or contains(@src,'forms.maximeyes.com')]"
	// MaximEyes "New Patient Welcome Form" iframe (same form, same field locators)
	private static final String WELCOME_FRAME_XPATH =
		"//iframe[@id='JotFormIFrame-233271152179050' or @title='New Patient Welcome Form - As per wireframes']"

	private static final String ID_RACE_FRAME = 'customFieldFrame_2012'
	private static final String ID_NEXT       = 'form-pagebreak-next_480'

	// data-map key -> intake-form element id
	private static final Map<String, String> DEFAULT_IDS = [
		title        : 'prefix_621',
		firstName    : 'first_621',
		middle       : 'middle_621',
		lastName     : 'last_621',
		suffix       : 'suffix_621',
		preferredName: 'input_317',
		dob          : 'lite_mode_11',
		sex          : 'input_2010',
		language     : 'input_2011',
		race         : 'input_2012',     // hidden input updated by the Race & Ethnicity widget
		marital      : 'input_2021',
		heightFt     : 'input_2015',
		heightIn     : 'input_2016',
		heightUnit   : 'input_2025',
		weightLbs    : 'input_2022',
		weightUnit   : 'input_2026',
		primaryEmail : 'input_649',
		email2       : 'input_659',
		addr1        : 'input_668',
		addr2        : 'input_669',
		country      : 'input_656',
		state        : 'input_665',
		city         : 'input_2004',
		zip          : 'input_2005'
	]

	// verification order + labels for the simple (single value) fields
	private static final List<List<String>> SIMPLE_FIELDS = [
		['firstName', 'First Name'], ['middle', 'Middle Name'], ['lastName', 'Last Name'],
		['suffix', 'Suffix'], ['title', 'Title'], ['preferredName', 'Preferred Name'],
		['dob', 'DOB'], ['sex', 'Sex'], ['language', 'Language'], ['marital', 'Marital status'],
		['heightFt', 'Height (ft)'], ['heightIn', 'Height (in)'], ['heightUnit', 'Height unit'],
		['weightLbs', 'Weight (lbs)'], ['weightUnit', 'Weight unit'],
		['primaryEmail', 'Primary Email'], ['email2', 'Secondary Email'],
		['addr1', 'Address Line 1'], ['addr2', 'Address Line 2'], ['country', 'Country'],
		['state', 'State'], ['city', 'City'], ['zip', 'Postal / Zip']
	]

	// Phone rows: [type dropdown id, number input id]
	private static final List PHONE_ROWS = [
		['input_463', 'input_660_full'],
		['input_461', 'input_661_full'],
		['input_329', 'input_662_full'],
		['input_459', 'input_663_full']
	]
	// "Day" is excluded on purpose because it may reveal the hidden Extension field
	private static final List PHONE_TYPES = ['Mobile', 'Home', 'Fax', 'Pager']

	// If the race dropdown has to be opened before its checkboxes exist (inside the widget iframe)
	private static final String RACE_OPEN_XPATH =
		"(//*[contains(@class,'dropdown') or contains(@class,'select') or @id='dropdown'])[1]"

	private static final List TITLES    = ['Mr.', 'Mrs.', 'Ms.', 'Miss', 'Dr.']
	private static final List LANGUAGES = ['English', 'Spanish; Castilian', 'French', 'German', 'Hindi', 'Japanese']
	private static final List MARITAL   = ['Single', 'Married', 'Other']
	private static final List RACES     = ['American Indian or Alaska Native', 'Asian', 'Black or African American',
		'Native Hawaiian or Other Pacific Islander', 'White']
	private static final List STATES    = ['AL', 'CA', 'FL', 'NY', 'TX', 'WA']

	private final Random rnd = new Random()

	// ================================================================== KEYWORDS

	/** Fills every random-data field on the intake Demographics tab and returns what was entered. */
	@Keyword
	def enterDemographics() {
		return withFormFrame(INTAKE_FRAME_XPATH) { doEnter() }
	}

	/** Verifies the intake Demographics tab against the data map (see class doc). */
	@Keyword
	def verifyDemographics(Map expected) {
		verifyDemographicsWithOptions(expected, [:])
	}

	/** Same as verifyDemographics, with options (useFormFrame, locators, raceExact) - see class doc. */
	@Keyword
	def verifyDemographicsWithOptions(Map expected, Map options) {
		Map opts = options ?: [:]
		String frameXpath = resolveFrameXpath(opts)
		boolean raceExact = opts.containsKey('raceExact') ? (opts.raceExact as boolean) : true
		Map locators = (opts.locators ?: [:]) as Map

		List<String> errors = withFormFrame(frameXpath) { doVerify(expected, locators, raceExact) }
		if (errors) {
			KeywordUtil.markFailed("Demographics verification failed for ${errors.size()} field(s):\n" + errors.join('\n'))
		}
		KeywordUtil.logInfo('All Demographics fields verified successfully')
	}

	/** Clicks "Next" on the intake Demographics tab. */
	@Keyword
	def clickNextOnDemographics() {
		withFormFrame(INTAKE_FRAME_XPATH) { clickNext() }
	}

	/** Old all-in-one behaviour: enter + verify everything + click Next. */
	@Keyword
	def fillAndVerifyDemographics(Map prefilled) {
		Map entered = enterDemographics()
		verifyDemographics(prefilled + entered)
		clickNextOnDemographics()
		return entered
	}

	// ============================================================ frame handling
	/**
	 * options.frame      : 'intake' (default) | 'welcome' (MaximEyes welcome form) | 'none' (page has no iframe)
	 * options.frameXpath : custom xpath of the form iframe (wins over options.frame)
	 * options.useFormFrame: false is the same as frame: 'none' (kept for compatibility)
	 * Returns the iframe xpath to switch into, or null for no iframe.
	 */
	private String resolveFrameXpath(Map opts) {
		if (opts.frameXpath) {
			return opts.frameXpath.toString()
		}
		if (opts.containsKey('useFormFrame') && !(opts.useFormFrame as boolean)) {
			return null
		}
		switch ((opts.frame ?: 'intake').toString().toLowerCase()) {
			case 'welcome': return WELCOME_FRAME_XPATH
			case 'none':    return null
			default:        return INTAKE_FRAME_XPATH
		}
	}

	private def withFormFrame(String frameXpath, Closure body) {
		if (frameXpath == null) {
			return body.call()
		}
		WebUI.switchToDefaultContent()
		TestObject formFrame = byXpath('formFrame', frameXpath)
		WebUI.waitForElementPresent(formFrame, 30)
		WebUI.switchToFrame(formFrame, 30)
		try {
			return body.call()
		} finally {
			WebUI.switchToDefaultContent()
		}
	}

	// ================================================================== ENTER
	private Map doEnter() {
		Map data = [
			title     : pick(TITLES),
			middle    : 'Mid' + randLetters(5),
			suffix    : pick(['Jr.', 'Sr.', 'II', 'III']),
			language  : pick(LANGUAGES),
			races     : [pick(RACES)],
			marital   : pick(MARITAL),
			heightFt  : String.valueOf(4 + rnd.nextInt(3)),     // 4-6
			heightIn  : String.valueOf(rnd.nextInt(12)),        // 0-11
			heightUnit: 'ft in',
			weightLbs : String.valueOf(100 + rnd.nextInt(150)), // 100-249
			weightUnit: 'lbs',
			email2    : "alt${randDigits(5)}@example.com".toString(),
			addr1     : "${100 + rnd.nextInt(900)} ${randLetters(6)} Street".toString(),
			addr2     : "Apt ${1 + rnd.nextInt(99)}".toString(),
			country   : 'USA',
			state     : pick(STATES),
			city      : 'City' + randLetters(5),
			zip       : String.format('%05d', rnd.nextInt(99999)),
			phones    : []
		]
		List types = new ArrayList(PHONE_TYPES)
		Collections.shuffle(types, rnd)
		PHONE_ROWS.eachWithIndex { row, i ->
			data.phones << [type: types[i], digits: '555' + randDigits(7)]
		}

		select(DEFAULT_IDS.title, data.title)
		type(DEFAULT_IDS.middle, data.middle)
		type(DEFAULT_IDS.suffix, data.suffix)
		select(DEFAULT_IDS.language, data.language)
		selectRace(data.races)
		select(DEFAULT_IDS.marital, data.marital)
		type(DEFAULT_IDS.heightFt, data.heightFt)
		type(DEFAULT_IDS.heightIn, data.heightIn)
		select(DEFAULT_IDS.heightUnit, data.heightUnit)
		type(DEFAULT_IDS.weightLbs, data.weightLbs)
		select(DEFAULT_IDS.weightUnit, data.weightUnit)

		PHONE_ROWS.eachWithIndex { row, i ->
			select(row[0], data.phones[i].type)
			typeMasked(row[1], data.phones[i].digits)
		}

		type(DEFAULT_IDS.email2, data.email2)
		type(DEFAULT_IDS.addr1, data.addr1)
		type(DEFAULT_IDS.addr2, data.addr2)
		select(DEFAULT_IDS.country, data.country)
		select(DEFAULT_IDS.state, data.state)
		type(DEFAULT_IDS.city, data.city)
		type(DEFAULT_IDS.zip, data.zip)

		return data
	}

	// ================================================================= VERIFY
	private List<String> doVerify(Map expected, Map locators, boolean raceExact) {
		List<String> errors = []

		SIMPLE_FIELDS.each { List<String> f ->
			String key = f[0]
			if (expected[key] != null) {
				String actual = readValue(locatorFor(key, locators))
				compare(errors, f[1], expected[key].toString(), actual, false)
			}
		}

		// Race & Ethnicity
		if (expected.races != null) {
			List<String> expectedRaces = (expected.races as List).collect { it.toString().trim() }.sort()
			String actualRaw = readValue(locatorFor('race', locators))
			List<String> actualRaces = actualRaw.split('[\\n,;]').collect { it.trim() }.findAll { it }.sort()
			if (raceExact) {
				compare(errors, 'Race & Ethnicity', expectedRaces.join(', '), actualRaces.join(', '), false)
			} else {
				String hay = norm(actualRaw)
				List missing = expectedRaces.findAll { !hay.contains(norm(it)) }
				compare(errors, 'Race & Ethnicity', expectedRaces.join(', '),
					missing.isEmpty() ? expectedRaces.join(', ') : "'${actualRaw.trim()}' (missing ${missing})", false)
			}
		}

		// Phones
		if (expected.phones != null) {
			(expected.phones as List).eachWithIndex { Map p, int i ->
				int n = i + 1
				if (p.type != null) {
					compare(errors, "Phone type ${n}", p.type.toString(),
						readValue(locatorFor("phoneType${n}", locators)), false)
				}
				if (p.digits != null) {
					compare(errors, "Phone number ${n}", p.digits.toString(),
						readValue(locatorFor("phoneNumber${n}", locators)), true)   // digits-only comparison
				}
			}
		}

		return errors
	}

	/** Resolves a data-map key to a TestObject: user override first, otherwise the intake-form id. */
	private TestObject locatorFor(String key, Map locators) {
		String ref = locators[key]
		if (!ref) {
			Matcher m = (key =~ /^(phoneType|phoneNumber)(\d)$/)
			if (m.matches()) {
				int idx = Integer.parseInt(m.group(2)) - 1
				ref = PHONE_ROWS[idx][m.group(1) == 'phoneType' ? 0 : 1]
			} else {
				ref = DEFAULT_IDS[key]
			}
		}
		return toTestObject(ref)
	}

	/** A reference starting with "/" or "(" is an xpath; anything else is an element id. */
	private TestObject toTestObject(String ref) {
		return (ref.startsWith('/') || ref.startsWith('(')) ? byXpath(ref, ref) : byId(ref)
	}

	/** Reads what a field currently shows: selected option text for <select>, value for inputs, text otherwise. */
	private String readValue(TestObject to) {
		try {
			WebUI.waitForElementPresent(to, 10, FailureHandling.OPTIONAL)
			WebElement el = WebUI.findWebElement(to, 5)
			String script = """
				var e = arguments[0];
				if (e.tagName === 'SELECT') { var o = e.options[e.selectedIndex]; return o ? o.text : ''; }
				if (e.value !== undefined && e.value !== null && e.value !== '') return e.value;
				return e.textContent || '';
			"""
			return ((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript(script, el) as String
		} catch (Exception e) {
			return '(element not found)'
		}
	}

	private void compare(List<String> errors, String label, String expected, String actual, boolean digitsOnly) {
		String exp = (expected ?: '').trim()
		String act = (actual ?: '').trim()
		if (digitsOnly) {
			exp = exp.replaceAll('\\D', '')
			act = act.replaceAll('\\D', '')
		}
		boolean ok = WebUI.verifyEqual(act, exp, FailureHandling.CONTINUE_ON_FAILURE)
		if (ok) {
			KeywordUtil.logInfo("PASS  ${label}: '${actual}'")
		} else {
			String msg = "FAIL  ${label}: expected '${expected}' but found '${actual}'"
			KeywordUtil.logInfo(msg)
			errors << msg
		}
	}

	// ============================================================ intake helpers
	private TestObject byId(String id) {
		return byXpath(id, "//*[@id='${id}']".toString())
	}

	private TestObject byXpath(String name, String xpath) {
		TestObject to = new TestObject(name)
		to.addProperty('xpath', ConditionType.EQUALS, xpath)
		return to
	}

	private void type(String id, String text) {
		TestObject to = byId(id)
		WebUI.waitForElementVisible(to, 10)
		WebUI.scrollToElement(to, 5, FailureHandling.OPTIONAL)
		WebUI.clearText(to)
		WebUI.setText(to, text)
	}

	/** For fields with an input mask (phone): type digits key by key. */
	private void typeMasked(String id, String digits) {
		TestObject to = byId(id)
		WebUI.waitForElementVisible(to, 10)
		WebUI.scrollToElement(to, 5, FailureHandling.OPTIONAL)
		WebUI.click(to)
		WebUI.sendKeys(to, Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(to, Keys.BACK_SPACE.toString())
		WebUI.sendKeys(to, digits)
	}

	private void select(String id, String value) {
		TestObject to = byId(id)
		WebUI.waitForElementVisible(to, 10)
		WebUI.scrollToElement(to, 5, FailureHandling.OPTIONAL)
		WebUI.selectOptionByValue(to, value, false)
	}

	/**
	 * Race & Ethnicity: custom "Checkbox in Dropdown" widget (nested iframe).
	 * Unchecks every option that is checked but not wanted, checks the wanted ones, then
	 * confirms through the hidden input. Retries up to 3 times.
	 */
	private void selectRace(List<String> races) {
		TestObject frame = byId(ID_RACE_FRAME)
		WebUI.waitForElementPresent(frame, 10)
		WebUI.scrollToElement(frame, 5, FailureHandling.OPTIONAL)

		List wanted = races.collect { it.trim() }.sort()
		for (int attempt = 1; attempt <= 3; attempt++) {
			applyRaceSelection(wanted)
			WebUI.delay(1)
			if (currentRaces() == wanted) {
				KeywordUtil.logInfo("Race & Ethnicity set to ${wanted} (attempt ${attempt})")
				return
			}
			KeywordUtil.logInfo("Race & Ethnicity currently ${currentRaces()}, retrying (attempt ${attempt})")
		}
		KeywordUtil.logInfo("WARN  Could not set Race & Ethnicity to ${wanted}")
	}

	private void applyRaceSelection(List wanted) {
		WebDriver driver = DriverFactory.getWebDriver()
		JavascriptExecutor js = (JavascriptExecutor) driver
		driver.switchTo().frame(driver.findElement(By.id(ID_RACE_FRAME)))
		try {
			List<WebElement> boxes = driver.findElements(By.xpath("//input[@type='checkbox']"))
			if (boxes.isEmpty()) {
				// options are only rendered once the dropdown is opened
				driver.findElement(By.xpath(RACE_OPEN_XPATH)).click()
				boxes = driver.findElements(By.xpath("//input[@type='checkbox']"))
			}
			int total = boxes.size()
			for (int i = 0; i < total; i++) {
				// re-find each time: the widget may re-render after a click
				WebElement box = driver.findElements(By.xpath("//input[@type='checkbox']")).get(i)
				List<String> names = raceNames(js, box)
				boolean shouldBeChecked = names.any { n -> wanted.any { w -> norm(w) == norm(n) } }
				boolean isChecked = box.isSelected()
				KeywordUtil.logInfo("Race option #${i}: names=${names}, checked=${isChecked}, wanted=${shouldBeChecked}")
				if (isChecked != shouldBeChecked) {
					js.executeScript('arguments[0].click();', box)
					KeywordUtil.logInfo("${shouldBeChecked ? 'Checked' : 'Unchecked'} race option #${i}")
				}
			}
		} finally {
			driver.switchTo().parentFrame()
		}
	}

	/** All text candidates that may identify a checkbox: label, value, aria-label, sibling and parent text. */
	private List<String> raceNames(JavascriptExecutor js, WebElement box) {
		String script = """
			var e = arguments[0];
			var out = [];
			var l = e.closest('label') || (e.id ? document.querySelector("label[for='" + e.id + "']") : null);
			if (l) out.push(l.textContent);
			out.push(e.value);
			out.push(e.getAttribute('aria-label'));
			out.push(e.getAttribute('title'));
			if (e.nextSibling) out.push(e.nextSibling.textContent);
			if (e.nextElementSibling) out.push(e.nextElementSibling.textContent);
			if (e.parentElement) out.push(e.parentElement.textContent);
			return out;
		"""
		List raw = (List) js.executeScript(script, box)
		return raw.findAll { it != null }.collect { norm(it.toString()) }.findAll { it }.unique()
	}

	private String norm(String t) {
		return (t ?: '').replaceAll('\\s+', ' ').trim().toLowerCase()
	}

	private List currentRaces() {
		String actual = WebUI.getAttribute(byId(DEFAULT_IDS.race), 'value') ?: ''
		return actual.split('\n').collect { it.trim() }.findAll { it }.sort()
	}

	private void clickNext() {
		TestObject next = byId(ID_NEXT)
		WebUI.waitForElementClickable(next, 10)
		WebUI.scrollToElement(next, 5, FailureHandling.OPTIONAL)
		try {
			WebUI.click(next)
		} catch (Exception e) {
			WebElement el = WebUI.findWebElement(next, 10)
			((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript('arguments[0].click();', el)
		}
		KeywordUtil.logInfo("Clicked 'Next'")
	}

	private String pick(List list) {
		return list[rnd.nextInt(list.size())]
	}

	private String randLetters(int n) {
		String abc = 'abcdefghijklmnopqrstuvwxyz'
		return (1..n).collect { abc[rnd.nextInt(abc.length())] }.join('')
	}

	private String randDigits(int n) {
		return (1..n).collect { rnd.nextInt(10) }.join('')
	}
}