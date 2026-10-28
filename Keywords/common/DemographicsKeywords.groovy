package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

/**
 * Keywords for the Intake form -> "Demographics" tab (needs common.IntakeFormSupport).
 *
 *  1. enterDemographics()                    -> fills the random-data fields, RETURNS the entered data
 *  2. verifyDemographics(expected)           -> verifies the portal intake form against a data map
 *     verifyDemographicsWithOptions(expected, options)
 *                                            -> same, but can run on another page / iframe
 *  3. clickNextOnDemographics()              -> clicks "Next"
 *  4. fillAndVerifyDemographics(prefilled)   -> enter + verify + Next in one call
 *
 * DATA MAP (returned by enterDemographics, accepted by the verify keywords). Only keys that are
 * present (non-null) are verified, so you can verify a subset:
 *
 *   firstName, middle, lastName, suffix, title, preferredName, dob, sex, language, marital,
 *   heightFt, heightIn, heightUnit, weightLbs, weightUnit,
 *   races      : ['Asian', ...]
 *   phones     : [[type: 'Mobile', digits: '5551234567'], ...]   (up to 4)
 *   primaryEmail, email2, addr1, addr2, country, state, city, zip
 *
 * Workflow:
 *   def entered   = CustomKeywords.'common.DemographicsKeywords.enterDemographics'()
 *   def prefilled = [firstName: 'John', lastName: 'Smith', preferredName: 'Johnny',
 *                    dob: '01/15/1985', sex: 'Male', primaryEmail: 'john@example.com']
 *   CustomKeywords.'common.DemographicsKeywords.verifyDemographics'(prefilled + entered)            // portal
 *   CustomKeywords.'common.DemographicsKeywords.clickNextOnDemographics'()
 *   CustomKeywords.'common.DemographicsKeywords.verifyDemographicsWithOptions'(prefilled + entered,
 *       [frame: 'welcome'])                                                                         // MaximEyes
 *
 * Options for verifyDemographicsWithOptions:
 *   frame      : 'intake' (default) | 'welcome' (MaximEyes welcome form) | 'none' (no iframe)
 *   frameXpath : custom iframe xpath (wins over "frame")
 *   locators   : [dataKey: 'elementId' or '//xpath'] overrides for fields whose locator differs on the target page.
 *                Keys: every data key above plus race, phoneType1..4, phoneNumber1..4
 *   raceExact  : true (default) = actual races must equal expected set; false = expected only need to be contained
 *   verbose    : true = log every PASS line; default = log failures + a summary
 *   strictPhoneOrder : false (default) = each expected phone (type + number together) only has to exist
 *                      on SOME row, because MaximEyes may list the phone rows in a different order;
 *                      true = row 1 must match row 1, row 2 row 2, ...
 */
class DemographicsKeywords {

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
		return IntakeFormSupport.withFormFrame(IntakeFormSupport.INTAKE_FRAME_XPATH) { doEnter() }
	}

	/** Verifies the intake Demographics tab against the data map. */
	@Keyword
	def verifyDemographics(Map expected) {
		verifyDemographicsWithOptions(expected, [:])
	}

	/** Same as verifyDemographics, with options (frame, frameXpath, locators, raceExact, verbose). */
	@Keyword
	def verifyDemographicsWithOptions(Map expected, Map options) {
		Map opts = options ?: [:]
		String frameXpath = IntakeFormSupport.resolveFrameXpath(opts)
		Map locators = (opts.locators ?: [:]) as Map
		boolean raceExact = opts.containsKey('raceExact') ? (opts.raceExact as boolean) : true
		boolean verbose = (opts.verbose == true)
		boolean strictPhoneOrder = (opts.strictPhoneOrder == true)

		List<String> errors = IntakeFormSupport.withFormFrame(frameXpath) {
			doVerify(expected, locators, raceExact, verbose, strictPhoneOrder)
		}
		IntakeFormSupport.failIfErrors('Demographics', errors)
	}

	/** Clicks "Next" on the intake Demographics tab. */
	@Keyword
	def clickNextOnDemographics() {
		IntakeFormSupport.withFormFrame(IntakeFormSupport.INTAKE_FRAME_XPATH) {
			IntakeFormSupport.clickButton(ID_NEXT, 'Next')
		}
	}

	/** Enter + verify everything + click Next. */
	@Keyword
	def fillAndVerifyDemographics(Map prefilled) {
		Map entered = enterDemographics()
		verifyDemographics(prefilled + entered)
		clickNextOnDemographics()
		return entered
	}

	// ================================================================== ENTER
	private Map doEnter() {
		Map data = [
			title     : IntakeFormSupport.pick(rnd, TITLES),
			middle    : 'Mid' + IntakeFormSupport.randLetters(rnd, 5),
			suffix    : IntakeFormSupport.pick(rnd, ['Jr.', 'Sr.', 'II', 'III']),
			language  : IntakeFormSupport.pick(rnd, LANGUAGES),
			races     : [IntakeFormSupport.pick(rnd, RACES)],
			marital   : IntakeFormSupport.pick(rnd, MARITAL),
			heightFt  : String.valueOf(4 + rnd.nextInt(3)),     // 4-6
			heightIn  : String.valueOf(rnd.nextInt(12)),        // 0-11
			heightUnit: 'ft in',
			weightLbs : String.valueOf(100 + rnd.nextInt(150)), // 100-249
			weightUnit: 'lbs',
			email2    : "alt${IntakeFormSupport.randDigits(rnd, 5)}@example.com".toString(),
			addr1     : "${100 + rnd.nextInt(900)} ${IntakeFormSupport.randLetters(rnd, 6)} Street".toString(),
			addr2     : "Apt ${1 + rnd.nextInt(99)}".toString(),
			country   : 'USA',
			state     : IntakeFormSupport.pick(rnd, STATES),
			city      : 'City' + IntakeFormSupport.randLetters(rnd, 5),
			zip       : String.format('%05d', rnd.nextInt(99999)),
			phones    : []
		]
		List types = new ArrayList(PHONE_TYPES)
		Collections.shuffle(types, rnd)
		PHONE_ROWS.eachWithIndex { row, i ->
			data.phones << [type: types[i], digits: '555' + IntakeFormSupport.randDigits(rnd, 7)]
		}

		// ONE JavaScript call fills every dropdown and text field (country is set before state)
		Map<String, String> texts = [
			(DEFAULT_IDS.title)     : data.title,
			(DEFAULT_IDS.middle)    : data.middle,
			(DEFAULT_IDS.suffix)    : data.suffix,
			(DEFAULT_IDS.language)  : data.language,
			(DEFAULT_IDS.marital)   : data.marital,
			(DEFAULT_IDS.heightFt)  : data.heightFt,
			(DEFAULT_IDS.heightIn)  : data.heightIn,
			(DEFAULT_IDS.heightUnit): data.heightUnit,
			(DEFAULT_IDS.weightLbs) : data.weightLbs,
			(DEFAULT_IDS.weightUnit): data.weightUnit,
			(DEFAULT_IDS.email2)    : data.email2,
			(DEFAULT_IDS.addr1)     : data.addr1,
			(DEFAULT_IDS.addr2)     : data.addr2,
			(DEFAULT_IDS.country)   : data.country,
			(DEFAULT_IDS.state)     : data.state,
			(DEFAULT_IDS.city)      : data.city,
			(DEFAULT_IDS.zip)       : data.zip
		]
		PHONE_ROWS.eachWithIndex { row, i -> texts[row[0] as String] = data.phones[i].type }
		IntakeFormSupport.fillMany([texts: texts])

		// phone inputs are masked, so they are typed for real
		Map<String, String> phoneDigits = [:]
		PHONE_ROWS.eachWithIndex { row, i -> phoneDigits[row[1] as String] = data.phones[i].digits }
		IntakeFormSupport.typePhones(phoneDigits)

		// Race & Ethnicity: "Checkbox in Dropdown" widget in its own iframe
		IntakeFormSupport.selectWidget(ID_RACE_FRAME, DEFAULT_IDS.race, data.races)

		return data
	}

	// ================================================================= VERIFY
	private List<String> doVerify(Map expected, Map locators, boolean raceExact, boolean verbose, boolean strictPhoneOrder) {
		List<String> errors = []

		// collect everything to check, then read the page ONCE
		List<Map> items = []
		SIMPLE_FIELDS.each { List<String> f ->
			if (expected[f[0]] != null) {
				items << [label: f[1], ref: refFor(f[0], locators), expected: expected[f[0]].toString(), digits: false]
			}
		}

		// phone rows on the target page: [type ref, number ref] x 4
		List<List<String>> phoneRefs = (1..4).collect { int n ->
			[refFor("phoneType${n}".toString(), locators), refFor("phoneNumber${n}".toString(), locators)]
		}
		int phoneCount = 0
		if (expected.phones != null) {
			List expectedPhones = expected.phones as List
			phoneCount = expectedPhones.size() * 2
			if (strictPhoneOrder) {
				expectedPhones.eachWithIndex { Map p, int i ->
					int n = i + 1
					if (p.type != null) {
						items << [label: "Phone type ${n}".toString(), ref: phoneRefs[i][0], expected: p.type.toString(), digits: false]
					}
					if (p.digits != null) {
						items << [label: "Phone number ${n}".toString(), ref: phoneRefs[i][1], expected: p.digits.toString(), digits: true]
					}
				}
			}
		}

		List<String> refs = items.collect { it.ref as String }
		if (expected.phones != null && !strictPhoneOrder) {
			phoneRefs.each { refs.addAll(it) }
		}
		String raceRef = null
		if (expected.races != null) {
			raceRef = refFor('race', locators)
			refs << raceRef
		}

		Map st = IntakeFormSupport.readState(refs, [], [])

		items.each { Map item ->
			IntakeFormSupport.check(errors, item.label as String, item.expected as String, st.vals[item.ref] as String, item.digits as boolean, verbose)
		}

		if (expected.phones != null && !strictPhoneOrder) {
			verifyPhonesAnyOrder(errors, expected.phones as List, phoneRefs, st, verbose)
		}

		if (raceRef != null) {
			List<String> expectedRaces = (expected.races as List).collect { it.toString().trim() }.sort()
			String actualRaw = st.vals[raceRef] as String
			List<String> actualRaces = (actualRaw ?: '').split('[\\n,;]').collect { it.trim() }.findAll { it }.sort()
			if (raceExact) {
				IntakeFormSupport.check(errors, 'Race & Ethnicity', expectedRaces.join(', '), actualRaces.join(', '), false, verbose)
			} else {
				String hay = IntakeFormSupport.norm(actualRaw)
				List missing = expectedRaces.findAll { !hay.contains(IntakeFormSupport.norm(it)) }
				IntakeFormSupport.check(errors, 'Race & Ethnicity', expectedRaces.join(', '),
					missing.isEmpty() ? expectedRaces.join(', ') : "'${(actualRaw ?: '').trim()}' (missing ${missing})".toString(),
					false, verbose)
			}
		}

		int total = items.size() + phoneCount * (strictPhoneOrder ? 0 : 1) + (raceRef != null ? 1 : 0)
		KeywordUtil.logInfo("Checked ${total} fields: ${total - errors.size()} passed, ${errors.size()} failed")
		return errors
	}

	/**
	 * Each expected phone (type AND number together) must exist on some row of the page.
	 * Row order is ignored; a row can only be matched once.
	 */
	private void verifyPhonesAnyOrder(List<String> errors, List expectedPhones, List<List<String>> phoneRefs, Map st, boolean verbose) {
		List<Map> actual = phoneRefs.collect { List<String> r ->
			[type  : ((st.vals[r[0]] ?: '') as String).trim(),
			 digits: ((st.vals[r[1]] ?: '') as String).replaceAll('\\D', '')]
		}.findAll { it.type || it.digits }

		expectedPhones.eachWithIndex { Map p, int i ->
			String t = (p.type ?: '').toString().trim()
			String d = (p.digits ?: '').toString().replaceAll('\\D', '')
			int idx = actual.findIndexOf { Map row -> (row.type as String).equalsIgnoreCase(t) && (row.digits as String) == d }
			if (idx >= 0) {
				actual.remove(idx)
				if (verbose) {
					KeywordUtil.logInfo("PASS  Phone ${i + 1}: '${t}' ${d} found")
				}
			} else {
				String found = actual.collect { "${it.type} ${it.digits}".toString() }.join(' | ')
				String msg = "FAIL  Phone ${i + 1}: expected '${t}' ${d} but no such phone on the page. Remaining phones: [${found}]".toString()
				KeywordUtil.logInfo(msg)
				errors << msg
			}
		}
	}

	/** Resolves a data-map key to an element id or xpath: user override first, otherwise the intake-form id. */
	private String refFor(String key, Map locators) {
		String ref = locators[key]
		if (ref) {
			return ref
		}
		if (key.startsWith('phoneType') || key.startsWith('phoneNumber')) {
			int idx = Integer.parseInt(key.substring(key.length() - 1)) - 1
			return PHONE_ROWS[idx][key.startsWith('phoneType') ? 0 : 1] as String
		}
		return DEFAULT_IDS[key]
	}
}