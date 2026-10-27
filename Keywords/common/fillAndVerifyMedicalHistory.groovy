package common

import com.kms.katalon.core.annotation.Keyword

/**
 * "Medical Hx" tab - split into reusable keywords (needs common.IntakeFormSupport).
 *
 *   def medical = CustomKeywords.'common.MedicalHistoryKeywords.enterMedicalHistory'()             // portal: enter
 *   CustomKeywords.'common.MedicalHistoryKeywords.verifyMedicalHistory'(medical)                    // portal: verify
 *   CustomKeywords.'common.MedicalHistoryKeywords.clickNextOnMedicalHistory'()                      // portal: Next
 *   ...
 *   CustomKeywords.'common.MedicalHistoryKeywords.verifyMedicalHistoryWithOptions'(medical, [frame: 'welcome'])  // MaximEyes
 *
 * Options: frame ('intake' default | 'welcome' | 'none'), frameXpath (custom iframe xpath).
 *
 * Note: the "Set All To No" widgets are NOT used - every Yes/No radio is answered individually.
 */
class MedicalHistoryKeywords {

	private static final String ID_NEXT = 'form-pagebreak-next_609'

	// "Is PCP" checkboxes: id -> [label, value]. Only PCP 1 is ticked (two PCPs makes no sense).
	private static final Map<String, List> PCP_CHECKBOXES = [
		'input_573_0': ['Is PCP 1', true],
		'input_84_0' : ['Is PCP 2', false]
	]

	private static final Map<String, String> CREDENTIALS = [
		'input_572': 'Physician 1 - Credentials',
		'input_45' : 'Physician 2 - Credentials'
	]
	private static final List CREDENTIAL_VALUES = ['O.D.', 'M.D.', 'P.A.', 'N.P.', 'R.N.']

	private static final Map<String, String> PHONES = [
		'input_42_full' : 'Physician 1 - Phone Number',
		'input_355_full': 'Physician 2 - Phone Number'
	]

	private static final Map<String, String> TEXTS = [
		'input_574': 'Physician 1 - First Name',
		'input_508': 'Physician 1 - Middle Name',
		'input_509': 'Physician 1 - Last Name',
		'input_510': 'Physician 1 - Suffix',
		'input_511': 'Physician 1 - Clinic Name',
		'input_371': 'Physician 2 - First Name',
		'input_372': 'Physician 2 - Middle Name',
		'input_373': 'Physician 2 - Last Name',
		'input_374': 'Physician 2 - Suffix',
		'input_47' : 'Physician 2 - Clinic Name',
		'input_92' : "Reason for today's exam",
		'input_93' : 'When was your last exam?',
		'input_94' : 'When was your last health exam?',
		'input_421': 'Past illness or injuries',
		'input_395': 'Past surgeries',
		'input_397': 'Past surgeries 1',
		'input_398': 'Past surgeries 2',
		'input_399': 'Past surgeries 3',
		'input_402': 'Current eye drops',
		'input_403': 'Current eye drops 2',
		'input_404': 'Current eye drops 3',
		'input_405': 'Current eye drops 4',
		'input_406': 'Current medications',
		'input_409': 'Current medications 2',
		'input_410': 'Current medications 3',
		'input_411': 'Current medications 4',
		'input_412': 'Current medications 5',
		'input_413': 'Current medications 6',
		'input_414': 'Current medications 7',
		'input_415': 'Current medications 8',
		'input_416': 'Reactions/sensitivities medicines',
		'input_417': 'Reactions/sensitivities medicines 2',
		'input_419': 'Specific allergies',
		'input_528': 'Specific allergies 2',
		'input_529': 'Specific allergies 3',
		'input_537': 'Specific allergies 4'
	]
	private static final List DATE_TEXT_IDS = ['input_93', 'input_94']

	// Yes/No radio groups: question id -> label
	private static final Map<String, String> RADIOS = [
		// Current Eye Symptoms
		'680': 'Current Eye Symptoms - Glare Sensitivity',
		'682': 'Current Eye Symptoms - Headaches',
		'684': 'Current Eye Symptoms - Light Sensitivity',
		'685': 'Current Eye Symptoms - Tired Eyes',
		'686': 'Current Eye Symptoms - Burning',
		'687': 'Current Eye Symptoms - Dryness',
		'689': 'Current Eye Symptoms - Excess Tearing/Watering',
		'690': 'Current Eye Symptoms - Eyelid Swelling',
		'692': 'Current Eye Symptoms - Eye Pain Or Soreness',
		'693': 'Current Eye Symptoms - Foreign Body Sensation',
		'694': 'Current Eye Symptoms - Infection of Eye or Lid',
		'695': 'Current Eye Symptoms - Itching',
		'696': 'Current Eye Symptoms - Mucous Discharge',
		'697': 'Current Eye Symptoms - Drooping Eyelid',
		'698': 'Current Eye Symptoms - Redness',
		'699': 'Current Eye Symptoms - Sandy or Gritty Feeling',
		'700': 'Current Eye Symptoms - Blurred Vision Distance',
		'701': 'Current Eye Symptoms - Blurred Vision Near',
		'702': 'Current Eye Symptoms - Distorted Vision (halos)',
		'703': 'Current Eye Symptoms - Double Vision',
		'704': 'Current Eye Symptoms - Flashes',
		'705': 'Current Eye Symptoms - Floaters or Spots',
		'706': 'Current Eye Symptoms - Fluctuating Vision',
		'707': 'Current Eye Symptoms - Loss of Central Vision',
		'708': 'Current Eye Symptoms - Loss of Side Vision',
		'709': 'Current Eye Symptoms - Loss of Vision',
		'710': 'Current Eye Symptoms - Other',
		// Eye History
		'712': 'Eye History - Amblyopia (Lazy Eye)',
		'713': 'Eye History - Infection of Eye or Lid',
		'714': 'Eye History - Blindness',
		'715': 'Eye History - Cataract',
		'716': 'Eye History - Color Blindness',
		'717': 'Eye History - Diabetic Retinopathy',
		'718': 'Eye History - Dry Eye Syndrome',
		'719': 'Eye History - Eye Injuries',
		'720': 'Eye History - Glaucoma',
		'721': 'Eye History - Glaucoma Suspect',
		'722': 'Eye History - High Risk Medication',
		'723': 'Eye History - Macular Degeneration',
		'724': 'Eye History - PVD(Vitreous Detachment)',
		'725': 'Eye History - Retinal Detachment',
		'726': 'Eye History - Crossed Eyes',
		'727': 'Eye History - Keratoconus',
		'728': 'Eye History - Corneal Disease',
		'729': 'Eye History - Other',
		// General Health Condition
		'731': 'General Health - Fever,Weight Loss,Fatigue,etc',
		'732': 'General Health - Ears, Nose, Throat issues',
		'733': 'General Health - Cardiovascular (high BP,etc.)',
		'734': 'General Health - Respiratory (Asthma)',
		'735': 'General Health - Gastrointestinal',
		'736': 'General Health - Kidney, Bladder issues',
		'737': 'General Health - Muscles, Bones, Joints issues',
		'738': 'General Health - Skin (Rash, Itching, etc)',
		'739': 'General Health - Neurological (Multiple Sclerosis)',
		'740': 'General Health - Anxiety or Depression',
		'741': 'General Health - Thyroid, Diabetes',
		'742': 'General Health - Blood (Cholesterol, Anemia, etc)',
		'743': 'General Health - Allergic, Immuno',
		'744': 'General Health - Pregnant',
		'745': 'General Health - Nursing',
		// Family History
		'681': 'Family History - Amblyopia (Lazy Eye)',
		'746': 'Family History - Blindness',
		'747': 'Family History - Cataract(s)',
		'748': 'Family History - Color Blindness',
		'749': 'Family History - Eye Tumors',
		'750': 'Family History - Glaucoma',
		'751': 'Family History - Glaucoma Suspect',
		'759': 'Family History - Macular Degeneration',
		'760': 'Family History - Rentinal Detachment',
		'761': 'Family History - Strabismus (Eye Turn)',
		'762': 'Family History - Arthritis',
		'763': 'Family History - Cancer',
		'764': 'Family History - Diabetes',
		'765': 'Family History - Heart Disease',
		'752': 'Family History - High Blood Pressure',
		'753': 'Family History - Kidney Disease',
		'754': 'Family History - Lupus',
		'755': 'Family History - Stroke',
		'756': 'Family History - Thyroid Disease',
		'757': 'Family History - Others'
	]

	private static final Map<String, String> PCP_LABELS = [
		'input_573_0': 'Is PCP 1',
		'input_84_0' : 'Is PCP 2'
	]

	private static final Map<String, String> LABELS = TEXTS + CREDENTIALS + PHONES + RADIOS + PCP_LABELS

	private final Random rnd = new Random()

	// ================================================================== KEYWORDS
	/** Fills every field on the Medical Hx tab (random data) and returns the data map. */
	@Keyword
	def enterMedicalHistory() {
		return IntakeFormSupport.withFormFrame(IntakeFormSupport.INTAKE_FRAME_XPATH) { doEnter() }
	}

	/** Verifies the Medical Hx tab of the portal intake form against the data map. */
	@Keyword
	def verifyMedicalHistory(Map expected) {
		verifyMedicalHistoryWithOptions(expected, [:])
	}

	/** Same, with options - use [frame: 'welcome'] for the MaximEyes welcome form. */
	@Keyword
	def verifyMedicalHistoryWithOptions(Map expected, Map options) {
		String frameXpath = IntakeFormSupport.resolveFrameXpath(options)
		List<String> errors = IntakeFormSupport.withFormFrame(frameXpath) {
			IntakeFormSupport.verifyData(expected, LABELS)
		}
		IntakeFormSupport.failIfErrors('Medical History', errors)
	}

	@Keyword
	def clickNextOnMedicalHistory() {
		IntakeFormSupport.withFormFrame(IntakeFormSupport.INTAKE_FRAME_XPATH) {
			IntakeFormSupport.clickButton(ID_NEXT, 'Next')
		}
	}

	/** Old all-in-one behaviour: enter + verify + Next. */
	@Keyword
	def fillAndVerifyMedicalHistory() {
		Map data = enterMedicalHistory()
		verifyMedicalHistory(data)
		clickNextOnMedicalHistory()
		return data
	}

	// =================================================================== ENTER
	private Map doEnter() {
		Map<String, String> texts = [:]          // text inputs + credential dropdowns
		TEXTS.keySet().each { String id -> texts[id] = randomText(id) }
		CREDENTIALS.keySet().each { String id -> texts[id] = IntakeFormSupport.pick(rnd, CREDENTIAL_VALUES) }

		Map<String, String> phones = [:]         // id -> 10 digits
		PHONES.keySet().each { String id -> phones[id] = '555' + IntakeFormSupport.randDigits(rnd, 7) }

		Map<String, String> radios = [:]
		RADIOS.keySet().each { String q -> radios[q] = rnd.nextBoolean() ? 'Yes' : 'No' }

		Map<String, Boolean> checks = [:]
		PCP_CHECKBOXES.each { String id, List cfg -> checks[id] = (boolean) cfg[1] }

		// fill
		checks.each { String id, Boolean v -> IntakeFormSupport.setCheckbox(id, v) }
		TEXTS.keySet().each { String id -> IntakeFormSupport.type(id, texts[id]) }
		CREDENTIALS.keySet().each { String id -> IntakeFormSupport.select(id, texts[id]) }
		phones.each { String id, String digits -> IntakeFormSupport.typeMasked(id, digits) }
		radios.each { String q, String answer -> IntakeFormSupport.clickRadio(q, answer) }

		return [texts: texts, phones: phones, radios: radios, checks: checks]
	}

	private String randomText(String id) {
		if (DATE_TEXT_IDS.contains(id)) {
			String month = IntakeFormSupport.pick(rnd, ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'])
			return "${month} ${2018 + rnd.nextInt(8)}".toString()
		}
		return 'Test' + IntakeFormSupport.randLetters(rnd, 7)
	}
}