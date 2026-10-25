package encounterElements

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
import java.text.SimpleDateFormat

/**
 * Keywords for the Allergy History element (DevExpress batch-edit grid) in an encounter.
 * Save as: Keywords/allergy/AllergyKeywords.groovy
 */
class AllergyKeywords {

	// Newest inline row created by the "+" icon (the id suffix of the grid is dynamic)
	private static final String ROW =
		"(//tr[contains(@id,'allergyHistoryGridView_') and contains(@id,'_DXDataRow-')])[last()]"

	private static TestObject xp(String name, String xpath) {
		TestObject to = new TestObject(name)
		to.addProperty('xpath', ConditionType.EQUALS, xpath)
		return to
	}

	// Safe XPath string literal (handles values containing ' such as "Goat's Rue (Galega Officinalis)")
	private static String lit(String s) {
		if (!s.contains("'")) return "'" + s + "'"
		return "concat('" + s.replace("'", "',\"'\",'") + "')"
	}

	private static boolean has(String s) {
		return s != null && s.trim().length() > 0
	}

	private static TestObject editor(String prefix) {
		return xp('editor_' + prefix,
			ROW + "//input[starts-with(@id," + lit(prefix) + ") and substring(@id,string-length(@id)-1)='_I']")
	}

	private static TestObject cell(int col) {
		return xp('cell_' + col, ROW + "/td[" + col + "]/div[contains(@class,'dxgBCTC')]")
	}

	// Type into a combo, then pick the matching list item (Enter as fallback)
	private static void fillCombo(String prefix, String value) {
		TestObject ed = editor(prefix)
		WebUI.waitForElementVisible(ed, 10)
		WebUI.click(ed)
		WebUI.clearText(ed)
		WebUI.sendKeys(ed, value)
		WebUI.delay(1)
		TestObject item = xp('item_' + prefix,
			"//td[starts-with(@id," + lit(prefix) + ") and contains(@id,'_DDD_L_LBI') and normalize-space(.)=" + lit(value) + "]")
		if (WebUI.waitForElementVisible(item, 3, FailureHandling.OPTIONAL)) {
			WebUI.click(item)
		} else {
			WebUI.sendKeys(ed, Keys.chord(Keys.ENTER))
		}
		WebUI.delay(1)
	}

	/**
	 * Adds one allergy row to the Allergy History grid.
	 * Any argument that is null or blank is skipped (the field is left as the page defaults it).
	 *
	 * @param reactionDate MM/dd/yyyy, must not be in the future. Use "TODAY" for today's date.
	 * @param allergy      Allergy name exactly as in the dropdown, e.g. "Aspirin". Allergy Type fills automatically.
	 * @param reaction     e.g. "Rash"
	 * @param severity     "Mild" / "Moderate" / "Severe"
	 * @param status       "Active", "Acute", "Chronic", "Controlled", "Inactive", "Resolved"
	 * @param source       "EHR", "EHR - Dr", "EHR - Tech", "EyeClinic.net", "Patient", "PMS", "Welcome Form", "Transition of Care"
	 * @param verify       true = verify the committed grid values after adding
	 */
	@Keyword
	def addAllergy(String reactionDate, String allergy, String reaction, String severity,
			String status, String source, boolean verify = true) {

		TestObject addBtn = xp('addAllergy',
			"//a[@title='Add New Record' and contains(@onclick,'allergyHistoryGridView')]")

		if (has(reactionDate) && reactionDate.trim().equalsIgnoreCase('TODAY')) {
			reactionDate = new SimpleDateFormat('MM/dd/yyyy').format(new Date())
		}

		WebUI.waitForElementPresent(addBtn, 20)
		WebUI.scrollToElement(addBtn, 5)

		// Uncheck "No known drug allergies" if checked
		boolean nkdaChecked = WebUI.executeJavaScript(
			'''return $('input[name*="Allergy_History.NO_KNOWN_ALLERGIES"]').prop('checked') === true''', null)
		if (nkdaChecked) {
			WebUI.click(xp('nkdaLabel', "//label[@name='Allergy_History.NO_KNOWN_ALLERGIES']"))
		}

		// 1. Add the inline row
		WebUI.click(addBtn)
		WebUI.waitForElementVisible(xp('newRow', ROW), 10)

		// 2. Reaction date
		if (has(reactionDate)) {
			TestObject dateEd = editor('REACTION_DATE_GF_')
			WebUI.waitForElementVisible(dateEd, 10)
			WebUI.click(dateEd)
			WebUI.clearText(dateEd)
			WebUI.sendKeys(dateEd, reactionDate)
			WebUI.sendKeys(dateEd, Keys.chord(Keys.TAB))
		}

		// 3. Allergy (page fills Allergy Type through AJAX)
		if (has(allergy)) {
			fillCombo('ALLERGY_GF_', allergy)
			WebUI.delay(2)
		}

		// 4. Remaining combos
		if (has(reaction)) fillCombo('REACTION_GF_', reaction)
		if (has(severity)) fillCombo('Severity_GF_', severity)
		if (has(status))   fillCombo('STATUS_GF_', status)
		if (has(source))   fillCombo('SOURCE_GF_', source)

		// 5. Commit the row through the grid's client API
		WebUI.executeJavaScript('''
			var name = Object.keys(window).filter(function (n) {
				return /^allergyHistoryGridView_.*_EEG$/.test(n);
			})[0];
			var grid = window[name];
			if (grid && grid.batchEditApi) { grid.batchEditApi.EndEdit(); }
			return name;
		''', null)
		WebUI.delay(2)

		// 6. Verify
		if (verify) {
			if (has(allergy))  WebUI.verifyElementText(cell(2), allergy)
			if (has(reaction)) WebUI.verifyElementText(cell(4), reaction)
			if (has(severity)) WebUI.verifyElementText(cell(5), severity)
			if (has(status))   WebUI.verifyElementText(cell(6), status)
			if (has(source))   WebUI.verifyElementText(cell(7), source)
			if (has(reactionDate)) WebUI.verifyMatch(WebUI.getText(cell(1)), reactionDate, false)
		}
	}
}
