package common

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Shared helpers for the intake / welcome form keywords (not a keyword class itself).
 *
 * Data map used by the enter / verify keywords of the Social, Medical and Lens tabs:
 *   texts   : [elementId : value]          text inputs, textareas and <select> dropdowns
 *   phones  : [elementId : '5551234567']   masked phone inputs (compared digits-only)
 *   radios  : [questionId: 'Yes'|'No']     radios are input_<questionId>_0 (Yes) / _1 (No)
 *   checks  : [elementId : true|false]     single checkboxes
 *   widgets : [hiddenInputId : ['Opt', ...]]  "Checkbox in Dropdown" widgets
 */
class IntakeFormSupport {

	// Patient portal intake form iframe
	static final String INTAKE_FRAME_XPATH =
		"//iframe[@title='Patient Intake Form - Step 1' or contains(@src,'forms.maximeyes.com')]"
	// MaximEyes "New Patient Welcome Form" iframe (same form, same field locators)
	static final String WELCOME_FRAME_XPATH =
		"//iframe[@id='JotFormIFrame-233271152179050' or @title='New Patient Welcome Form - As per wireframes']"

	// If a widget dropdown has to be opened before its checkboxes exist (inside the widget iframe)
	static final String WIDGET_OPEN_XPATH =
		"(//*[contains(@class,'dropdown') or contains(@class,'select') or @id='dropdown'])[1]"

	// ============================================================ frame handling
	/**
	 * options.frame       : 'intake' (default) | 'welcome' (MaximEyes welcome form) | 'none' (no iframe)
	 * options.frameXpath  : custom iframe xpath (wins over options.frame)
	 * Returns the iframe xpath to switch into, or null for no iframe.
	 */
	static String resolveFrameXpath(Map opts) {
		Map o = opts ?: [:]
		if (o.frameXpath) {
			return o.frameXpath.toString()
		}
		switch ((o.frame ?: 'intake').toString().toLowerCase()) {
			case 'welcome': return WELCOME_FRAME_XPATH
			case 'none':    return null
			default:        return INTAKE_FRAME_XPATH
		}
	}

	static def withFormFrame(String frameXpath, Closure body) {
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

	// ================================================================ locators
	static TestObject byXpath(String name, String xpath) {
		TestObject to = new TestObject(name)
		to.addProperty('xpath', ConditionType.EQUALS, xpath)
		return to
	}

	static TestObject byId(String id) {
		return byXpath(id, "//*[@id='${id}']".toString())
	}

	// ================================================================== entering
	static void type(String id, String text) {
		TestObject to = byId(id)
		WebUI.waitForElementVisible(to, 10)
		WebUI.scrollToElement(to, 5, FailureHandling.OPTIONAL)
		WebUI.clearText(to)
		WebUI.setText(to, text)
	}

	/** For fields with an input mask (phone): type digits key by key. */
	static void typeMasked(String id, String digits) {
		TestObject to = byId(id)
		WebUI.waitForElementVisible(to, 10)
		WebUI.scrollToElement(to, 5, FailureHandling.OPTIONAL)
		WebUI.click(to)
		WebUI.sendKeys(to, Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(to, Keys.BACK_SPACE.toString())
		WebUI.sendKeys(to, digits)
	}

	static void select(String id, String value) {
		TestObject to = byId(id)
		WebUI.waitForElementVisible(to, 10)
		WebUI.scrollToElement(to, 5, FailureHandling.OPTIONAL)
		WebUI.selectOptionByValue(to, value, false)
	}

	/** Yes = input_<q>_0, No = input_<q>_1 */
	static void clickRadio(String q, String answer) {
		clickInput("input_${q}_${answer == 'Yes' ? 0 : 1}".toString())
	}

	static void setCheckbox(String id, boolean wanted) {
		if (isChecked(id) != wanted) {
			clickInput(id)
		}
	}

	/** Clicks the label (the input may be visually hidden); JS click as fallback. */
	static void clickInput(String inputId) {
		WebDriver driver = DriverFactory.getWebDriver()
		JavascriptExecutor js = (JavascriptExecutor) driver
		WebElement input = driver.findElement(By.id(inputId))
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", input)
		try {
			driver.findElement(By.cssSelector("label[for='" + inputId + "']")).click()
		} catch (Exception e) {
			js.executeScript('arguments[0].click();', input)
		}
		if (!isChecked(inputId)) {
			js.executeScript('arguments[0].click();', driver.findElement(By.id(inputId)))
		}
	}

	static boolean isChecked(String inputId) {
		return DriverFactory.getWebDriver().findElement(By.id(inputId)).isSelected()
	}

	static void clickButton(String id, String name) {
		TestObject btn = byId(id)
		WebUI.waitForElementClickable(btn, 10)
		WebUI.scrollToElement(btn, 5, FailureHandling.OPTIONAL)
		try {
			WebUI.click(btn)
		} catch (Exception e) {
			WebElement el = WebUI.findWebElement(btn, 10)
			((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript('arguments[0].click();', el)
		}
		KeywordUtil.logInfo("Clicked '${name}'")
	}

	// ============================================== "Checkbox in Dropdown" widgets
	/**
	 * Unchecks every option that is checked but not wanted, checks the wanted ones, then
	 * confirms through the widget's hidden input. Retries up to 3 times.
	 */
	static void selectWidget(String frameId, String hiddenId, List wanted) {
		List wantedSorted = wanted.collect { it.toString().trim() }.sort()
		for (int attempt = 1; attempt <= 3; attempt++) {
			applyWidgetSelection(frameId, wantedSorted)
			WebUI.delay(1)
			if (currentValues(hiddenId) == wantedSorted) {
				KeywordUtil.logInfo("Widget ${hiddenId} set to ${wantedSorted} (attempt ${attempt})")
				return
			}
			KeywordUtil.logInfo("Widget ${hiddenId} currently ${currentValues(hiddenId)}, retrying (attempt ${attempt})")
		}
		KeywordUtil.logInfo("WARN  Could not set widget ${hiddenId} to ${wantedSorted}")
	}

	private static void applyWidgetSelection(String frameId, List wanted) {
		WebDriver driver = DriverFactory.getWebDriver()
		JavascriptExecutor js = (JavascriptExecutor) driver
		WebElement frame = driver.findElement(By.id(frameId))
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", frame)
		driver.switchTo().frame(frame)
		try {
			List<WebElement> boxes = driver.findElements(By.xpath("//input[@type='checkbox']"))
			if (boxes.isEmpty()) {
				driver.findElement(By.xpath(WIDGET_OPEN_XPATH)).click()
				boxes = driver.findElements(By.xpath("//input[@type='checkbox']"))
			}
			int total = boxes.size()
			for (int i = 0; i < total; i++) {
				// re-find each time: the widget may re-render after a click
				WebElement box = driver.findElements(By.xpath("//input[@type='checkbox']")).get(i)
				List<String> names = checkboxNames(js, box)
				boolean shouldBeChecked = names.any { n -> wanted.any { w -> norm(w.toString()) == norm(n) } }
				boolean isOn = box.isSelected()
				KeywordUtil.logInfo("Widget option #${i}: names=${names}, checked=${isOn}, wanted=${shouldBeChecked}")
				if (isOn != shouldBeChecked) {
					js.executeScript('arguments[0].click();', box)
				}
			}
		} finally {
			driver.switchTo().parentFrame()
		}
	}

	private static List<String> checkboxNames(JavascriptExecutor js, WebElement box) {
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

	static String norm(String t) {
		return (t ?: '').replaceAll('\\s+', ' ').trim().toLowerCase()
	}

	static List currentValues(String hiddenId) {
		String actual = readValue(byId(hiddenId)) ?: ''
		return actual.split('\n').collect { it.trim() }.findAll { it }.sort()
	}

	// =================================================================== verifying
	/** Reads what a field currently shows: selected option text for <select>, value for inputs, text otherwise. */
	static String readValue(TestObject to) {
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

	static void compare(List<String> errors, String label, String expected, String actual, boolean digitsOnly) {
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

	private static String radioState(String q) {
		try {
			boolean yes = isChecked("input_${q}_0".toString())
			boolean no  = isChecked("input_${q}_1".toString())
			return yes ? 'Yes' : (no ? 'No' : '(none selected)')
		} catch (Exception e) {
			return '(element not found)'
		}
	}

	private static String checkState(String id) {
		try {
			return isChecked(id) ? 'checked' : 'unchecked'
		} catch (Exception e) {
			return '(element not found)'
		}
	}

	/** Verifies every entry of the data map (see class doc) and returns the list of failure messages. */
	static List<String> verifyData(Map data, Map labels) {
		List<String> errors = []
		Map lbl = labels ?: [:]

		(data.texts ?: [:]).each { id, v ->
			compare(errors, (lbl[id] ?: id) as String, v as String, readValue(byId(id as String)), false)
		}
		(data.phones ?: [:]).each { id, v ->
			compare(errors, (lbl[id] ?: id) as String, v as String, readValue(byId(id as String)), true)
		}
		(data.radios ?: [:]).each { q, v ->
			compare(errors, (lbl[q] ?: q) as String, v as String, radioState(q as String), false)
		}
		(data.checks ?: [:]).each { id, v ->
			compare(errors, (lbl[id] ?: id) as String, (v as boolean) ? 'checked' : 'unchecked', checkState(id as String), false)
		}
		(data.widgets ?: [:]).each { id, v ->
			List expected = (v as List).collect { it.toString().trim() }.sort()
			compare(errors, (lbl[id] ?: id) as String, expected.join(', '), currentValues(id as String).join(', '), false)
		}
		return errors
	}

	static void failIfErrors(String title, List<String> errors) {
		if (errors) {
			KeywordUtil.markFailed("${title} verification failed for ${errors.size()} field(s):\n" + errors.join('\n'))
		}
		KeywordUtil.logInfo("All ${title} fields verified successfully")
	}

	// ==================================================================== random
	static String randLetters(Random rnd, int n) {
		String abc = 'abcdefghijklmnopqrstuvwxyz'
		return (1..n).collect { abc[rnd.nextInt(abc.length())] }.join('')
	}

	static String randDigits(Random rnd, int n) {
		return (1..n).collect { rnd.nextInt(10) }.join('')
	}

	static String pick(Random rnd, List list) {
		return list[rnd.nextInt(list.size())]
	}
}