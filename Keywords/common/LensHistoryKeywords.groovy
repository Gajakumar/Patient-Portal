package common

import com.kms.katalon.core.annotation.Keyword

/**
 * "Lens Hx" tab - split into reusable keywords (needs common.IntakeFormSupport).
 *
 *   def lens = CustomKeywords.'common.LensHistoryKeywords.enterLensHistory'()                 // portal: enter
 *   CustomKeywords.'common.LensHistoryKeywords.verifyLensHistory'(lens)                        // portal: verify
 *   CustomKeywords.'common.LensHistoryKeywords.submitLensHistory'()                            // portal: Submit
 *   ...
 *   CustomKeywords.'common.LensHistoryKeywords.verifyLensHistoryWithOptions'(lens, [frame: 'welcome'])  // MaximEyes
 *
 * Options: frame ('intake' default | 'welcome' | 'none'), frameXpath (custom iframe xpath),
 * verbose (true = log every PASS line, default = log failures + summary only).
 *
 * Note: the "Set All To No" widget is NOT used - every Yes/No radio is answered individually.
 */
class LensHistoryKeywords {

	private static final String ID_SUBMIT = 'input_304'

	// "Checkbox in Dropdown" widgets: [hidden input id, label, iframe id, options, how many to pick]
	// The widget has a configurable "max selections" setting, so only 1 option is picked by default.
	private static final List WIDGETS = [
		['input_554', 'Type of glasses', 'customFieldFrame_554',
			['Full Time', 'Part Time', 'Distance', 'Close'], 1],
		['input_555', 'Glasses owned', 'customFieldFrame_555',
			['Single Vision', 'Trifocals', 'Safety Glasses', 'Progressive', 'Bifocals', 'Back-up Glasses', 'Sports Glasses', 'Other'], 1]
	]

	private static final Map<String, String> TEXTS = [
		'input_252': 'How many hours or day?',
		'input_253': 'Distance from Computer?',
		'input_259': 'Mileage to work each way?',
		'input_265': 'Since (glasses)',
		'input_283': 'Reason for stopping',
		'input_285': 'Since (contact lenses)',
		'input_287': 'Type and brand of contact lenses',
		'input_289': 'How many hours/day?',
		'input_288': 'How many days/week?',
		'input_290': "Today's Wearing Time",
		'input_782': 'Lens comfort - Left',
		'input_783': 'Lens comfort - Right',
		'input_784': 'Distance vision - Left',
		'input_785': 'Distance vision - Right',
		'input_786': 'Near vision - Left',
		'input_787': 'Near vision - Right',
		'input_770': 'Solutions - Cleaner',
		'input_771': 'Solutions - Disinfectant',
		'input_772': 'Solutions - Enzyme'
	]
	private static final List RATING_IDS = ['input_782', 'input_783', 'input_784', 'input_785', 'input_786', 'input_787']  // 1-10
	private static final List YEAR_IDS   = ['input_265', 'input_285']
	private static final List NUMBER_IDS = ['input_252', 'input_253', 'input_259', 'input_289', 'input_288', 'input_290']

	private static final Map<String, String> RADIOS = [
		'250': 'Do you use a computer?',
		'258': 'Do you drive?',
		'261': 'Do you have glare problems?',
		'263': 'Problems with night vision?',
		'262': 'Visual difficulty when driving?',
		'264': 'Do you currently wear glasses?',
		'269': 'Trouble in the past with glasses?',
		'273': 'Do you wear sunglasses?',
		'274': 'Are your sun glasses your current prescription?',
		'452': 'If not contact lens wearer, are you interested in trying contact lenses at this time?',
		'282': 'Have you ever tried to wear contact lenses?',
		'284': 'Do you currently wear contact lenses?'
	]

	private static final Map<String, String> EYEWEAR = [
		'input_277_0': 'Special Eyewear Needs - Computer',
		'input_277_1': 'Special Eyewear Needs - Safety Glasses',
		'input_277_2': 'Special Eyewear Needs - Occupational',
		'input_277_3': 'Special Eyewear Needs - Sports/Hobbies'
	]

	private static final Map<String, String> WIDGET_LABELS = [
		'input_554': 'Type of glasses',
		'input_555': 'Glasses owned'
	]

	private static final Map<String, String> LABELS = TEXTS + RADIOS + EYEWEAR + WIDGET_LABELS

	private final Random rnd = new Random()

	// ================================================================== KEYWORDS
	/** Fills every field on the Lens Hx tab (random data) and returns the data map. */
	@Keyword
	def enterLensHistory() {
		return IntakeFormSupport.withFormFrame(IntakeFormSupport.INTAKE_FRAME_XPATH) { doEnter() }
	}

	/** Verifies the Lens Hx tab of the portal intake form against the data map. */
	@Keyword
	def verifyLensHistory(Map expected) {
		verifyLensHistoryWithOptions(expected, [:])
	}

	/** Same, with options - use [frame: 'welcome'] for the MaximEyes welcome form. */
	@Keyword
	def verifyLensHistoryWithOptions(Map expected, Map options) {
		String frameXpath = IntakeFormSupport.resolveFrameXpath(options)
		List<String> errors = IntakeFormSupport.withFormFrame(frameXpath) {
			IntakeFormSupport.verifyData(expected, LABELS, ((options ?: [:]).verbose == true))
		}
		IntakeFormSupport.failIfErrors('Lens History', errors)
	}

	/** Clicks "Submit" on the portal intake form. */
	@Keyword
	def submitLensHistory() {
		IntakeFormSupport.withFormFrame(IntakeFormSupport.INTAKE_FRAME_XPATH) {
			IntakeFormSupport.clickButton(ID_SUBMIT, 'Submit')
		}
	}

	/** Old all-in-one behaviour: enter + verify + Submit. */
	@Keyword
	def fillAndVerifyLensHistory() {
		Map data = enterLensHistory()
		verifyLensHistory(data)
		submitLensHistory()
		return data
	}

	// =================================================================== ENTER
	private Map doEnter() {
		Map<String, String> texts = [:]
		TEXTS.keySet().each { String id -> texts[id] = randomText(id) }

		Map<String, String> radios = [:]
		RADIOS.keySet().each { String q -> radios[q] = rnd.nextBoolean() ? 'Yes' : 'No' }

		Map<String, Boolean> checks = [:]
		EYEWEAR.keySet().each { String id -> checks[id] = rnd.nextBoolean() }

		Map<String, List> widgets = [:]          // hidden input id -> chosen options
		WIDGETS.each { List w ->
			List options = new ArrayList(w[3] as List)
			Collections.shuffle(options, rnd)
			widgets[w[0] as String] = options.take(w[4] as int)
		}

		// ONE JavaScript call fills text fields, radios and the eyewear checkboxes
		IntakeFormSupport.fillMany([texts: texts, radios: radios, checks: checks])
		// the two "Checkbox in Dropdown" widgets need real interaction inside their own iframes
		WIDGETS.each { List w -> IntakeFormSupport.selectWidget(w[2] as String, w[0] as String, widgets[w[0] as String]) }

		return [texts: texts, radios: radios, checks: checks, widgets: widgets]
	}

	private String randomText(String id) {
		if (RATING_IDS.contains(id)) return String.valueOf(1 + rnd.nextInt(10))
		if (YEAR_IDS.contains(id))   return String.valueOf(2005 + rnd.nextInt(20))
		if (NUMBER_IDS.contains(id)) return String.valueOf(1 + rnd.nextInt(12))
		return 'Test' + IntakeFormSupport.randLetters(rnd, 7)
	}
}