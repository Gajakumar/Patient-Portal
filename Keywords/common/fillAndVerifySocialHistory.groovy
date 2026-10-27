package common

import com.kms.katalon.core.annotation.Keyword

/**
 * "Social Hx" tab - split into reusable keywords (needs common.IntakeFormSupport).
 *
 *   def social = CustomKeywords.'common.SocialHistoryKeywords.enterSocialHistory'()              // portal: enter
 *   CustomKeywords.'common.SocialHistoryKeywords.verifySocialHistory'(social)                     // portal: verify
 *   CustomKeywords.'common.SocialHistoryKeywords.clickNextOnSocialHistory'()                      // portal: Next
 *   ...
 *   CustomKeywords.'common.SocialHistoryKeywords.verifySocialHistoryWithOptions'(social, [frame: 'welcome'])  // MaximEyes
 *
 * Options: frame ('intake' default | 'welcome' | 'none'), frameXpath (custom iframe xpath).
 */
class SocialHistoryKeywords {

	private static final String ID_ALCOHOL = 'input_534'
	private static final String ID_SMOKING = 'input_533'
	private static final String ID_NEXT    = 'form-pagebreak-next_500'

	private static final Map<String, String> TEXT_LABELS = [
		'input_534': 'Do you drink alcohol?',
		'input_533': 'Smoking status',
		'input_243': 'Current occupation',
		'input_244': 'Years',
		'input_245': 'Employer',
		'input_247': 'Hobbies/Interests'
	]

	// Yes/No radio groups: question id -> label
	private static final Map<String, String> RADIOS = [
		'236': 'Tobacco use cessation intervention, counselling?',
		'237': 'Tobacco use cessation pharmacologic therapy?',
		'239': 'Do you use illegal drugs',
		'240': 'Do you engage in regular exercise?',
		'241': 'Use nutritional supplements vitamins, etc.'
	]

	private static final Map<String, String> LABELS = TEXT_LABELS + RADIOS

	private static final List ALCOHOL = ['No', 'Occasional', '1 per day', '2-3 per day', '4+ per day']
	private static final List SMOKING = [
		'Non-smoker (finding)', 'Ex-smoker (finding)', 'Never smoked tobacco (finding)',
		'Cigarette smoker (finding)', 'Cigar smoker (finding)', 'Pipe smoker (finding)',
		'Electronic cigarette user (finding)', 'Occasional cigarette smoker (finding)',
		'Former light tobacco smoker (finding)', 'Tobacco non-user (finding)'
	]
	private static final List OCCUPATIONS = ['Teacher', 'Engineer', 'Nurse', 'Accountant', 'Electrician', 'Chef', 'Designer']
	private static final List HOBBIES = ['Reading', 'Hiking', 'Photography', 'Cooking', 'Cycling', 'Painting', 'Chess']

	private final Random rnd = new Random()

	// ================================================================== KEYWORDS
	/** Fills every field on the Social Hx tab (random data) and returns the data map. */
	@Keyword
	def enterSocialHistory() {
		return IntakeFormSupport.withFormFrame(IntakeFormSupport.INTAKE_FRAME_XPATH) { doEnter() }
	}

	/** Verifies the Social Hx tab of the portal intake form against the data map. */
	@Keyword
	def verifySocialHistory(Map expected) {
		verifySocialHistoryWithOptions(expected, [:])
	}

	/** Same, with options - use [frame: 'welcome'] for the MaximEyes welcome form. */
	@Keyword
	def verifySocialHistoryWithOptions(Map expected, Map options) {
		String frameXpath = IntakeFormSupport.resolveFrameXpath(options)
		List<String> errors = IntakeFormSupport.withFormFrame(frameXpath) {
			IntakeFormSupport.verifyData(expected, LABELS)
		}
		IntakeFormSupport.failIfErrors('Social History', errors)
	}

	@Keyword
	def clickNextOnSocialHistory() {
		IntakeFormSupport.withFormFrame(IntakeFormSupport.INTAKE_FRAME_XPATH) {
			IntakeFormSupport.clickButton(ID_NEXT, 'Next')
		}
	}

	/** Old all-in-one behaviour: enter + verify + Next. */
	@Keyword
	def fillAndVerifySocialHistory() {
		Map data = enterSocialHistory()
		verifySocialHistory(data)
		clickNextOnSocialHistory()
		return data
	}

	// =================================================================== ENTER
	private Map doEnter() {
		Map<String, String> texts = [
			(ID_ALCOHOL): IntakeFormSupport.pick(rnd, ALCOHOL),
			(ID_SMOKING): IntakeFormSupport.pick(rnd, SMOKING),
			'input_243' : IntakeFormSupport.pick(rnd, OCCUPATIONS),
			'input_244' : String.valueOf(1 + rnd.nextInt(30)),
			'input_245' : 'Employer' + IntakeFormSupport.randLetters(rnd, 5),
			'input_247' : IntakeFormSupport.pick(rnd, HOBBIES) + ', ' + IntakeFormSupport.pick(rnd, HOBBIES) + ' ' + IntakeFormSupport.randLetters(rnd, 4)
		]
		Map<String, String> radios = [:]
		RADIOS.keySet().each { String q -> radios[q] = rnd.nextBoolean() ? 'Yes' : 'No' }

		IntakeFormSupport.select(ID_ALCOHOL, texts[ID_ALCOHOL])
		IntakeFormSupport.select(ID_SMOKING, texts[ID_SMOKING])
		radios.each { String q, String answer -> IntakeFormSupport.clickRadio(q, answer) }
		['input_243', 'input_244', 'input_245', 'input_247'].each { String id -> IntakeFormSupport.type(id, texts[id]) }

		return [texts: texts, radios: radios]
	}
}