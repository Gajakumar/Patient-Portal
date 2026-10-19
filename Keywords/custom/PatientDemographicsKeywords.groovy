/*
 * Keywords/custom/PatientDemographicsKeywords.groovy
 *
 * USAGE in a test case:
 *   import custom.PatientDemographicsKeywords as PD
 *   PD.selectProfile('patient-demographics')
 *   PD.verifyPatientDemographics('104013')
 *
 *   or:  CustomKeywords.'custom.PatientDemographicsKeywords.verifyPatientDemographics'('104013')
 */
package custom

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class PatientDemographicsKeywords {

	// ---------- Fixed expected data (update here if it ever changes) ----------
	static final String RESOURCE_TYPE = 'Patient'
	static final String NAME          = 'David Smith'
	static final String DOB           = '03/16/1982'
	static final String GENDER        = 'male'
	static final String PHONE         = '1234567890'
	static final String EMAIL         = 'gajakumara@first-insight.com'
	static final String ADDRESS       = '—'
	static final String DATE_FORMAT   = 'MM/dd/yyyy'

	// ---------- Locators ----------
	static final String TABLE = "//table[contains(@class,'medical-info-detail-table')]"

	static TestObject xp(String name, String xpath) {
		TestObject t = new TestObject(name)
		t.addProperty('xpath', ConditionType.EQUALS, xpath)
		return t
	}

	static TestObject PROFILE_DROPDOWN() { xp('medical-info-profile', "//select[@id='medical-info-profile']") }
	static TestObject DETAIL_ROWS()      { xp('detail-rows',  "${TABLE}//tbody/tr".toString()) }
	static TestObject HEADER_FIELD()     { xp('th-field',     "${TABLE}//thead//th[1]".toString()) }
	static TestObject HEADER_VALUE()     { xp('th-value',     "${TABLE}//thead//th[2]".toString()) }

	// ---------- Keywords ----------

	/** Select a profile in the Medical Info dropdown by its value, e.g. 'patient-demographics'. */
	@Keyword
	static void selectProfile(String value) {
		WebUI.waitForElementClickable(PROFILE_DROPDOWN(), 10)
		WebUI.selectOptionByValue(PROFILE_DROPDOWN(), value, false)
		WebUI.verifyOptionSelectedByValue(PROFILE_DROPDOWN(), value, false, 5)
	}

	/** Builds the expected Field -> Value map. ID is passed in; Last Updated is always today. */
	@Keyword
	static Map<String, String> buildExpectedDemographics(String patientId) {
		String today   = LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT))
		String contact = "phone: ${PHONE}, email: ${EMAIL}".toString()

		Map<String, String> expected = new LinkedHashMap<String, String>()
		expected.put('Resource Type', RESOURCE_TYPE)
		expected.put('ID',            patientId)
		expected.put('Name',          NAME)
		expected.put('Date',          DOB)
		expected.put('Last Updated',  today)
		expected.put('Details',       "Gender: ${GENDER} | DOB: ${DOB} | Phone: ${contact} | Address: ${ADDRESS}".toString())
		expected.put('Gender',        GENDER)
		expected.put('Date of Birth', DOB)
		expected.put('Contact',       contact)
		return expected
	}

	/** Reads the Field/Value table into a map (label -> value). */
	@Keyword
	static Map<String, String> readDemographicsTable() {
		WebUI.waitForElementVisible(DETAIL_ROWS(), 10)
		Map<String, String> actual = new LinkedHashMap<String, String>()
		List<WebElement> rows = WebUI.findWebElements(DETAIL_ROWS(), 10)
		rows.each { WebElement tr ->
			String label = tr.findElement(By.cssSelector('td.medical-info-grid-label')).getText().trim()
			String value = tr.findElement(By.cssSelector('td.medical-info-grid-value')).getText().trim()
			actual.put(label, value)
		}
		return actual
	}

	/**
	 * Verifies the Patient Demographics table that is currently displayed.
	 * @param patientId expected ID (passed in). "Last Updated" is always verified against today's date.
	 * Reports ALL mismatches in a single failure message.
	 */
	@Keyword
	static void verifyPatientDemographics(String patientId) {
		Map<String, String> expected = buildExpectedDemographics(patientId)

		// Headers
		WebUI.verifyElementText(HEADER_FIELD(), 'Field')
		WebUI.verifyElementText(HEADER_VALUE(), 'Value')

		Map<String, String> actual = readDemographicsTable()
		List<String> errors = []

		if (actual.size() != expected.size()) {
			errors << "Row count: expected ${expected.size()} but was ${actual.size()}".toString()
		}
		(expected.keySet() - actual.keySet()).each { errors << "Missing field: ${it}".toString() }
		(actual.keySet() - expected.keySet()).each { errors << "Unexpected field: ${it}".toString() }

		expected.each { String label, String expValue ->
			if (actual.containsKey(label)) {
				String act = actual.get(label)
				if (act == expValue) {
					KeywordUtil.logInfo("PASS  ${label}: '${act}'")
				} else {
					errors << "${label}: expected '${expValue}' but was '${act}'".toString()
				}
			}
		}

		if (errors) {
			KeywordUtil.markFailed("Patient Demographics mismatches:\n - " + errors.join("\n - "))
		}
		KeywordUtil.markPassed("Patient Demographics verified for ID ${patientId}")
	}

	/** One-step: select 'Patient Demographics' and verify the data. */
	@Keyword
	static void selectAndVerifyPatientDemographics(String patientId) {
		selectProfile('patient-demographics')
		verifyPatientDemographics(patientId)
	}
}