package common

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

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
 * SPEED: instead of one Katalon step per field (lookup + wait + scroll + log for every field),
 * all fields of a tab are filled in ONE JavaScript call (fillMany) and read back in ONE
 * JavaScript call (readState). Only phone inputs (masked) and the "Checkbox in Dropdown"
 * widgets are driven through real browser interaction.
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

	static TestObject byXpath(String name, String xpath) {
		TestObject to = new TestObject(name)
		to.addProperty('xpath', ConditionType.EQUALS, xpath)
		return to
	}

	static TestObject byId(String id) {
		return byXpath(id, "//*[@id='${id}']".toString())
	}

	private static JavascriptExecutor js() {
		return (JavascriptExecutor) DriverFactory.getWebDriver()
	}

	// ======================================================= FAST: fill many fields at once
	/**
	 * Fills everything in ONE JavaScript call. Map keys:
	 *   texts  : [id: value]      inputs / textareas / <select> (selects match by option value)
	 *   radios : [question: 'Yes'|'No']
	 *   checks : [id: true|false]
	 * Fires input / keyup / change / blur events so the form's own scripts see the change.
	 * Elements that cannot be found are reported in the log.
	 */
	static void fillMany(Map data) {
		String script = """
			var d = JSON.parse(arguments[0]);
			var missing = [];
			function fire(el, names) {
				names.forEach(function(n) {
					var ev = document.createEvent('HTMLEvents');
					ev.initEvent(n, true, true);
					el.dispatchEvent(ev);
				});
			}
			function setVal(el, v) {
				var proto = el.tagName === 'TEXTAREA' ? window.HTMLTextAreaElement.prototype
					: (el.tagName === 'SELECT' ? window.HTMLSelectElement.prototype : window.HTMLInputElement.prototype);
				var desc = Object.getOwnPropertyDescriptor(proto, 'value');
				if (desc && desc.set) { desc.set.call(el, v); } else { el.value = v; }
			}
			var texts = d.texts || {};
			Object.keys(texts).forEach(function(id) {
				var el = document.getElementById(id);
				if (!el) { missing.push(id); return; }
				if (el.tagName === 'SELECT') {
					setVal(el, texts[id]);
					fire(el, ['change']);
				} else {
					try { el.focus(); } catch (e) {}
					setVal(el, texts[id]);
					fire(el, ['input', 'keyup', 'change', 'blur']);
				}
			});
			var radios = d.radios || {};
			Object.keys(radios).forEach(function(q) {
				var id = 'input_' + q + '_' + (radios[q] === 'Yes' ? 0 : 1);
				var el = document.getElementById(id);
				if (!el) { missing.push(id); return; }
				if (!el.checked) { el.click(); }
			});
			var checks = d.checks || {};
			Object.keys(checks).forEach(function(id) {
				var el = document.getElementById(id);
				if (!el) { missing.push(id); return; }
				if (el.checked !== checks[id]) { el.click(); }
			});
			return JSON.stringify(missing);
		"""
		String json = JsonOutput.toJson([texts: data.texts ?: [:], radios: data.radios ?: [:], checks: data.checks ?: [:]])
		String missing = js().executeScript(script, json) as String
		if (missing && missing != '[]') {
			KeywordUtil.logInfo("WARN  elements not found while filling: ${missing}")
		}
	}

	/** Masked phone inputs: real typing (the input mask needs real key events). */
	static void typePhones(Map phones) {
		WebDriver driver = DriverFactory.getWebDriver()
		phones.each { id, digits ->
			WebElement el = driver.findElement(By.id(id as String))
			js().executeScript("arguments[0].scrollIntoView({block:'center'});", el)
			el.click()
			el.sendKeys(Keys.chord(Keys.CONTROL, 'a'), Keys.BACK_SPACE)
			el.sendKeys(digits as String)
		}
	}

	// ======================================================= FAST: read many fields at once
	/**
	 * Reads everything in ONE JavaScript call.
	 *   refs   : element ids, or xpaths (starting with / or ( )      -> state.vals[ref]
	 *   radios : question ids                                         -> state.radios[q]  'Yes'|'No'|'(none selected)'
	 *   checks : element ids                                          -> state.checks[id] true|false
	 * Missing elements come back as '(element not found)' / null.
	 */
	static Map readState(List<String> refs, List<String> radios, List<String> checks) {
		String script = """
			var req = JSON.parse(arguments[0]);
			var out = {vals: {}, radios: {}, checks: {}};
			function find(ref) {
				var c = ref.charAt(0);
				if (c === '/' || c === '(') {
					return document.evaluate(ref, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
				}
				return document.getElementById(ref);
			}
			(req.refs || []).forEach(function(r) {
				var e = find(r);
				if (!e) { out.vals[r] = '(element not found)'; return; }
				if (e.tagName === 'SELECT') {
					var o = e.options[e.selectedIndex];
					out.vals[r] = o ? o.text : '';
				} else if (e.value !== undefined && e.value !== null && e.value !== '') {
					out.vals[r] = e.value;
				} else {
					out.vals[r] = e.textContent || '';
				}
			});
			(req.radios || []).forEach(function(q) {
				var y = document.getElementById('input_' + q + '_0');
				var n = document.getElementById('input_' + q + '_1');
				if (!y && !n) { out.radios[q] = '(element not found)'; return; }
				out.radios[q] = (y && y.checked) ? 'Yes' : ((n && n.checked) ? 'No' : '(none selected)');
			});
			(req.checks || []).forEach(function(id) {
				var e = document.getElementById(id);
				out.checks[id] = e ? e.checked : null;
			});
			return JSON.stringify(out);
		"""
		String json = JsonOutput.toJson([refs: refs ?: [], radios: radios ?: [], checks: checks ?: []])
		String res = js().executeScript(script, json) as String
		return new JsonSlurper().parseText(res) as Map
	}

	// ======================================================== verification helpers
	static String norm(String t) {
		return (t ?: '').replaceAll('\\s+', ' ').trim().toLowerCase()
	}

	/** Pure-Groovy comparison (no Katalon step per field). Logs failures always, passes only if verbose. */
	static void check(List<String> errors, String label, String expected, String actual, boolean digitsOnly, boolean verbose) {
		String exp = (expected ?: '').trim()
		String act = (actual ?: '').trim()
		if (digitsOnly) {
			exp = exp.replaceAll('\\D', '')
			act = act.replaceAll('\\D', '')
		}
		if (exp == act) {
			if (verbose) {
				KeywordUtil.logInfo("PASS  ${label}: '${actual}'")
			}
		} else {
			String msg = "FAIL  ${label}: expected '${expected}' but found '${actual}'"
			KeywordUtil.logInfo(msg)
			errors << msg
		}
	}

	/** Verifies every entry of the data map (see class doc) with a single page read. Returns failure messages. */
	static List<String> verifyData(Map data, Map labels, boolean verbose) {
		List<String> errors = []
		Map lbl = labels ?: [:]
		Map texts   = (data.texts ?: [:]) as Map
		Map phones  = (data.phones ?: [:]) as Map
		Map radios  = (data.radios ?: [:]) as Map
		Map checks  = (data.checks ?: [:]) as Map
		Map widgets = (data.widgets ?: [:]) as Map

		List<String> refs = []
		refs.addAll(texts.keySet().collect { it as String })
		refs.addAll(phones.keySet().collect { it as String })
		refs.addAll(widgets.keySet().collect { it as String })
		Map st = readState(refs, radios.keySet().collect { it as String }, checks.keySet().collect { it as String })

		texts.each { id, v ->
			check(errors, (lbl[id] ?: id) as String, v as String, st.vals[id] as String, false, verbose)
		}
		phones.each { id, v ->
			check(errors, (lbl[id] ?: id) as String, v as String, st.vals[id] as String, true, verbose)
		}
		radios.each { q, v ->
			check(errors, (lbl[q] ?: q) as String, v as String, st.radios[q] as String, false, verbose)
		}
		checks.each { id, v ->
			String actual = st.checks[id] == null ? '(element not found)' : (st.checks[id] ? 'checked' : 'unchecked')
			check(errors, (lbl[id] ?: id) as String, (v as boolean) ? 'checked' : 'unchecked', actual, false, verbose)
		}
		widgets.each { id, v ->
			List expected = (v as List).collect { it.toString().trim() }.sort()
			List actual = splitLines(st.vals[id] as String)
			check(errors, (lbl[id] ?: id) as String, expected.join(', '), actual.join(', '), false, verbose)
		}

		int total = texts.size() + phones.size() + radios.size() + checks.size() + widgets.size()
		KeywordUtil.logInfo("Checked ${total} fields: ${total - errors.size()} passed, ${errors.size()} failed")
		return errors
	}

	static List splitLines(String raw) {
		return (raw ?: '').split('\n').collect { it.trim() }.findAll { it }.sort()
	}

	static void failIfErrors(String title, List<String> errors) {
		if (errors) {
			KeywordUtil.markFailed("${title} verification failed for ${errors.size()} field(s):\n" + errors.join('\n'))
		} else {
			KeywordUtil.logInfo("All ${title} fields verified successfully")
		}
	}

	// ================================================ "Checkbox in Dropdown" widgets
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

	/** Current selection of a widget, read from its hidden input. */
	static List currentValues(String hiddenId) {
		Map st = readState([hiddenId], [], [])
		return splitLines(st.vals[hiddenId] as String)
	}

	// ====================================================================== buttons
	static void clickButton(String id, String name) {
		TestObject btn = byId(id)
		WebUI.waitForElementClickable(btn, 10)
		WebUI.scrollToElement(btn, 5, FailureHandling.OPTIONAL)
		try {
			WebUI.click(btn)
		} catch (Exception e) {
			WebElement el = WebUI.findWebElement(btn, 10)
			js().executeScript('arguments[0].click();', el)
		}
		KeywordUtil.logInfo("Clicked '${name}'")
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