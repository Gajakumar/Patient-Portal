import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.katalon.webui.keyword.action.image.WebUIAbstractImageActionKeyword
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

// =====================================================================
// HELPERS (locators come from the page DOM)
// =====================================================================
def xp(String name, String xpath) {
	TestObject to = new TestObject(name)
	to.addProperty('xpath', ConditionType.EQUALS, xpath)
	return to
}

def byId(String id) { return xp(id, "//*[@id='" + id + "']") }

/** Phone number input of the Nth row (1-based) in the phone table - the input has no id */
def phoneNumberRow(int n) { return xp('phoneNumber' + n, "(//table//tbody/tr)[" + n + "]//input[@type='text']") }

/** Delete (bin) icon of the Nth phone row */
def phoneDeleteRow(int n) { return xp('phoneDelete' + n, "(//table//tbody/tr)[" + n + "]//td[contains(@class,'custom-delete-icon')]//*[name()='svg']") }

/** Delete (bin) icon of "Address N" card */
def addressDelete(int n) { return xp('addrDelete' + n, "//label[normalize-space()='Address " + n + "']/ancestor::div[contains(@class,'justify-between')][1]//*[name()='svg']") }

/** Clear the field first, then type (WebUI.setText appends on these React inputs) */
def setField(TestObject to, String text) {
	CustomKeywords.'utils.InputUtils.clearAndSetInput'(to, text)
}

def verifyDropdownOptions(TestObject select, List<String> expected) {
	WebUI.click(select)
	WebElement el = WebUI.findWebElement(select)
	List<String> actual = el.findElements(By.tagName('option')).collect { it.getText().trim() }
	KeywordUtil.logInfo('Dropdown options: ' + actual)
	List<String> missing = expected.findAll { !actual.contains(it) }
	if (!missing.isEmpty()) {
		KeywordUtil.markFailed('Missing options ' + missing + ' | actual ' + actual)
	}
	WebUI.sendKeys(select, Keys.chord(Keys.ESCAPE))
}

/** <input type="date"> is a native control - set value via JS (yyyy-MM-dd) and fire React-compatible events */
def setDate(TestObject to, String isoDate) {
	WebElement el = WebUI.findWebElement(to)
	WebUI.executeJavaScript(
		"var el=arguments[0];" +
		"var set=Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype,'value').set;" +
		"set.call(el,arguments[1]);" +
		"el.dispatchEvent(new Event('input',{bubbles:true}));" +
		"el.dispatchEvent(new Event('change',{bubbles:true}));", Arrays.asList(el, isoDate))
}

/** Wait (max N sec) until the profile data is loaded: phone number filled and primary phone checkbox checked */
def waitForProfileData(int maxSeconds) {
	for (int i = 0; i < maxSeconds; i++) {
		try {
			String num = WebUI.findWebElement(phoneNumberRow(1), 2).getAttribute('value')
			boolean chk = WebUI.findWebElement(byId('phone-primary-0undefined'), 2).isSelected()
			if (num != null && !num.trim().isEmpty() && chk) {
				KeywordUtil.logInfo('Profile data loaded after ~' + i + 's (phone=' + num + ')')
				return true
			}
		} catch (Exception e) {
			// element not ready yet
		}
		WebUI.delay(1)
	}
	softFail('Profile data (phone number / primary phone checked) did not load within ' + maxSeconds + 's')
	return false
}

/** Soft failure: log + remember, test is failed once at the very end */
def softFail(String msg) {
	KeywordUtil.markWarning(msg)
	softFailures.add(msg)
}

/** Logs checked/disabled state and colours, then verifies checked + violet (bg, border or accent). Continues on failure. */
def verifyPrimaryCheckbox(String label, TestObject cb, boolean expectDisabled) {
	String violet = 'rgb(126, 46, 239)'
	WebElement el = WebUI.findWebElement(cb)
	boolean checked  = el.isSelected()
	boolean disabled = !el.isEnabled()
	String bg      = el.getCssValue('background-color')
	String border  = el.getCssValue('border-color')
	String accent  = el.getCssValue('accent-color')
	String wrapBg  = WebUI.executeJavaScript("var e=arguments[0].parentElement;return getComputedStyle(e).backgroundColor;", Arrays.asList(el))
	KeywordUtil.logInfo(label + ' -> checked=' + checked + ', disabled=' + disabled + ', bg=' + bg +
		', border=' + border + ', accent=' + accent + ', wrapperBg=' + wrapBg)

	if (disabled != expectDisabled) {
		softFail(label + ': expected disabled=' + expectDisabled + ' but was ' + disabled)
	}
	if (!checked) {
		softFail(label + ' primary checkbox is NOT checked')
	}
	if (![bg, border, accent, wrapBg].contains(violet)) {
		softFail(label + ' is not violet. bg=' + bg + ', border=' + border + ', accent=' + accent)
	}
}

/** Number of visible autocomplete suggestion items (City / Zip lists) */
def visibleSuggestions() {
	Object n = WebUI.executeJavaScript(
		"return Array.from(document.querySelectorAll('li.cursor-pointer')).filter(function(e){return e.offsetParent!==null;}).length;", null)
	return (n == null) ? 0 : ((Number) n).intValue()
}

/** Wait for async City/Zip suggestion lists, then close them (ESC + click outside). If they refuse to close, pick the first suggestion. */
def closeOpenDropdowns() {
	WebUI.delay(2)   // suggestions are loaded asynchronously after typing
	for (int attempt = 1; attempt <= 3; attempt++) {
		int cnt = visibleSuggestions()
		if (cnt == 0) {
			return true
		}
		KeywordUtil.logInfo('Suggestion list open (' + cnt + ' items), closing - attempt ' + attempt)
		WebUI.executeJavaScript("if (document.activeElement) { document.activeElement.blur(); }", null)
		WebUI.sendKeys(xp('body', '//body'), Keys.chord(Keys.ESCAPE), FailureHandling.OPTIONAL)
		WebUI.click(xp('neutralHeading', "//section[@id='address-section']//h2[normalize-space()='Address']"), FailureHandling.OPTIONAL)
		WebUI.delay(1)
	}
	if (visibleSuggestions() > 0) {
		KeywordUtil.markWarning('Suggestion list would not close - selecting the first suggestion')
		WebUI.click(xp('firstSuggestion', "(//li[contains(@class,'cursor-pointer')])[1]"), FailureHandling.OPTIONAL)
		WebUI.delay(1)
	}
	return visibleSuggestions() == 0
}

/** Returns a description of the element covering the Save button, or '' if the button is the top element */
def saveCoveredBy() {
	WebElement btn = WebUI.findWebElement(saveBtn)
	String res = WebUI.executeJavaScript(
		"var b=arguments[0];var r=b.getBoundingClientRect();" +
		"var e=document.elementFromPoint(r.left+r.width/2,r.top+r.height/2);" +
		"if(!e||b===e||b.contains(e)){return '';}" +
		"return e.tagName+' | '+(e.className||'')+' | '+(e.innerText||'').substring(0,60);", Arrays.asList(btn))
	return res == null ? '' : res
}

/** Click Save Changes safely: dismiss dropdowns, scroll into view, never fire a click that would be intercepted */
def clickSave() {
	closeOpenDropdowns()
	WebUI.scrollToElement(saveBtn, 3, FailureHandling.OPTIONAL)
	WebUI.delay(1)

	String cover = saveCoveredBy()
	if (!cover.isEmpty()) {
		KeywordUtil.logInfo('Save button is covered by: ' + cover + ' -> pressing ESC / clicking outside')
		WebUI.sendKeys(xp('body', '//body'), Keys.chord(Keys.ESCAPE), FailureHandling.OPTIONAL)
		WebUI.click(xp('neutralHeading2', "//h2[normalize-space()='Phone & Email']"), FailureHandling.OPTIONAL)
		WebUI.scrollToElement(saveBtn, 3, FailureHandling.OPTIONAL)
		WebUI.delay(1)
		cover = saveCoveredBy()
	}

	if (cover.isEmpty()) {
		WebUI.click(saveBtn)
	} else {
		KeywordUtil.markWarning('Save still covered by: ' + cover + ' -> using JS click')
		softFail('Dropdown list left open over Save button: ' + cover)
		WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(WebUI.findWebElement(saveBtn)))
	}
}

def verifyPopupAndClose(TestObject msgObj, String expectedMsg, TestObject buttonObj) {
	WebUI.verifyElementText(msgObj, expectedMsg)
	WebUI.click(buttonObj)
	WebUI.verifyElementNotPresent(msgObj, 3)
}

softFailures = []   // collected soft failures (binding variable, visible in methods)

// =====================================================================
// OBJECTS
// =====================================================================
TestObject titleSel    = byId('title-select')
TestObject suffixSel   = byId('suffix-select')
TestObject firstName   = byId('firstName')
TestObject lastName    = byId('lastName')
TestObject dob         = byId('dob')
TestObject primaryMail = byId('primaryEmail')
TestObject phoneType0  = byId('phone-type-0')
TestObject phoneType1  = byId('phone-type-1')
TestObject phoneCb     = byId('phone-primary-0undefined')
TestObject addrCb      = byId('address-primary-0undefined')
TestObject address1_0  = byId('address1-0')
TestObject city0       = byId('city-0')
TestObject state0      = byId('state-0')
TestObject zip0        = byId('zip-0')
TestObject country0    = byId('country-0')
TestObject address1_1  = byId('address1-1')
TestObject city1       = byId('city-1')
TestObject state1      = byId('state-1')
TestObject zip1        = byId('zip-1')
TestObject country1    = byId('country-1')

saveBtn = xp('saveBtn', "//button[@type='submit'][contains(.,'Save Changes')]")
TestObject phonePlus   = xp('phonePlus', "//h2[normalize-space()='Phone & Email']/following-sibling::div[1]")
TestObject addressPlus = xp('addressPlus', "//section[@id='address-section']//h2[normalize-space()='Address']/following-sibling::div[1]")
TestObject dobCalIcon  = xp('dobCalIcon', "//img[contains(@class,'demographics-dob-calendar-icon')]")

// Popup objects are NOT in the supplied HTML - keep your recorded Object Repository ones
TestObject popupMsg    = findTestObject('Object Repository/Page_Patient Portal/p_Save Changes_text-lg mb-6 text-center max-w-xs')
TestObject okBtn       = findTestObject('Object Repository/Page_Patient Portal/button_Save Changes_px-8 py-2 rounded bg-re_63977b')   // Ok / Yes
TestObject noBtn       = findTestObject('Object Repository/Page_Patient Portal/button_Delete_px-8 py-2 rounded bg-gray-500_5566e9') // No

// =====================================================================
// LOGIN - William Hover
// =====================================================================
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'),
	[('Username') : UserNamePt, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(5)

String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'('imap.gmail.com', GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email, 'Verification')
char[] digits = otp.toCharArray()
for (int i = 0; i < 4; i++) {
	WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp' + (i + 1)), String.valueOf(digits[i]))
}
WebUI.delay(5)
TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'stories.JsClick.jsClick'(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification'))
//WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(10)

// =====================================================================
// SCENARIO 1 - open screen, verify sections
// =====================================================================
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Profile_block pr-14 py-2 font-normal t_ea6dd6'))
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h1_Update Demographics_py-1 text-lg mdtext-_e6d4ac'), 'Update Demographics')

// Wait until saved profile data is loaded into the form before verifying anything
waitForProfileData(20)

WebUI.verifyElementPresent(xp('hName', "//h2[normalize-space()='Name']"), 5)
WebUI.verifyElementPresent(xp('hPhone', "//h2[normalize-space()='Phone & Email']"), 5)
WebUI.verifyElementPresent(xp('lPrimaryEmail', "//label[@for='primaryEmail'][normalize-space()='Primary Email']"), 5)
WebUI.verifyElementPresent(xp('lSecondaryEmail', "//label[@for='secondaryEmail'][normalize-space()='Secondary Email']"), 5)
WebUI.verifyElementPresent(xp('hAddress', "//h2[normalize-space()='Address']"), 5)

// Pre-filled registration data for William Hover
WebUI.verifyElementAttributeValue(firstName, 'value', 'William', 5)
WebUI.verifyElementAttributeValue(lastName, 'value', 'Hover', 5)

// Save button at the bottom (below the address section)
int saveY    = WebUI.findWebElement(saveBtn).getLocation().getY()
int addressY = WebUI.findWebElement(address1_0).getLocation().getY()
KeywordUtil.logInfo('Save Y = ' + saveY + ', Address1 Y = ' + addressY)
if (saveY <= addressY) {
	KeywordUtil.markFailed('Save button is not at the bottom of the screen')
}

// =====================================================================
// PRIMARY CHECKBOXES
// =====================================================================
// Phone primary: disabled by default, violet
verifyPrimaryCheckbox('Phone primary', phoneCb, true)
WebUI.click(phoneCb, FailureHandling.OPTIONAL)
verifyPrimaryCheckbox('Phone primary (after click)', phoneCb, true)

// =====================================================================
// FALLDOWNS (ids from DOM)
// =====================================================================
verifyDropdownOptions(titleSel,  ['Mr.', 'Mrs.', 'Ms.', 'Miss', 'Dr.'])
verifyDropdownOptions(suffixSel, ['Jr', 'Sr', 'II', 'III', 'IV'])
verifyDropdownOptions(phoneType0, ['Select', 'Mobile', 'Home', 'Day', 'Fax', 'Pager'])
verifyDropdownOptions(country0,  ['Country', 'ATG', 'BHS', 'CAN', 'DMA', 'LCA', 'TTO', 'USA'])

// =====================================================================
// DOB - native <input type="date"> (min = 1900-01-01)
// The browser's calendar popup (Cancel/Save, year list) is NOT in the DOM, so Selenium cannot
// click it. We verify the 1900 limit via the min attribute and test manual entry.
// =====================================================================
WebUI.verifyElementPresent(dobCalIcon, 5)
WebUI.verifyElementAttributeValue(dob, 'min', '1900-01-01', 5)

setDate(dob, '1990-05-20')
WebUI.verifyElementAttributeValue(dob, 'value', '1990-05-20', 5)

// =====================================================================
// SCENARIO 2.1 - validations
// =====================================================================
setField(firstName, '')
setField(lastName, '')
setField(primaryMail, '')
setDate(dob, '')
setField(address1_0, '')

// Phone Type validation: number must be ENTERED and type changed to "Select"
setField(phoneNumberRow(1), '232-121-2121')
WebUI.selectOptionByValue(phoneType0, '', true)   // "Select"

clickSave()

WebUI.verifyTextPresent('First Name is required', false)
WebUI.verifyTextPresent('Last Name is required', false)
WebUI.verifyTextPresent('DOB is required', false)
WebUI.verifyTextPresent('Phone Type is required', false)
WebUI.verifyTextPresent('Primary email is required', false)
//WebUI.verifyTextNotPresent('Address is required', false)   // MBT 40242

// Invalid DOB (below min 1900) and invalid email
setDate(dob, '1800-01-01')
clickSave()
WebUI.verifyTextPresent('Year must be 1900 or later', false)

setField(primaryMail, 'invalid-email')
clickSave()
WebUI.verifyTextPresent('Please Enter valid email', false)

// Number validation: type selected (Mobile) but number cleared
WebUI.selectOptionByValue(phoneType0, 'Mobile', true)
setField(phoneNumberRow(1), '')
clickSave()
WebUI.verifyTextPresent('Number is required', false)

// =====================================================================
// SCENARIO 2.2 - delete PRIMARY phone
// =====================================================================
WebUI.selectOptionByValue(phoneType0, 'Mobile', true)
setField(phoneNumberRow(1), '987-654-3210')
WebUI.click(phoneDeleteRow(1))
verifyPopupAndClose(popupMsg, 'At least one phone number is required. You cannot delete the only number.', okBtn)

// =====================================================================
// SCENARIO 2.3 - delete PRIMARY address
// =====================================================================
setField(address1_0, '1234 Main Street')
setField(city0, 'LA')
closeOpenDropdowns()
WebUI.selectOptionByValue(country0, 'USA', true)
WebUI.selectOptionByValue(state0, 'CA', true)
setField(zip0, '46077')
closeOpenDropdowns()

// Only one address exists -> it is auto-primary and its checkbox is DISABLED (same as phone); violet, stays checked
WebUI.click(addrCb, FailureHandling.OPTIONAL)
verifyPrimaryCheckbox('Address primary', addrCb, true)

WebUI.click(addressDelete(1))
verifyPopupAndClose(popupMsg, 'At least one address is required. You cannot delete the only address.', okBtn)

// =====================================================================
// SCENARIO 5 - non-primary PHONE
// =====================================================================
WebUI.click(phonePlus)
WebUI.selectOptionByValue(phoneType1, 'Home', true)
setField(phoneNumberRow(2), '121211121212')

WebUI.click(phoneDeleteRow(2))
verifyPopupAndClose(popupMsg, 'Are you sure you want to delete this home number?', noBtn)
WebUI.verifyElementPresent(phoneNumberRow(2), 3)          // not deleted

WebUI.click(phoneDeleteRow(2))
verifyPopupAndClose(popupMsg, 'Are you sure you want to delete this home number?', okBtn)
WebUI.verifyElementNotPresent(phoneNumberRow(2), 3)       // deleted

// =====================================================================
// SCENARIO 5 - non-primary ADDRESS
// =====================================================================
// NOTE: Address 2 already exists in the DOM you shared; click "+" only if it is not yet present
if (!WebUI.verifyElementPresent(address1_1, 2, FailureHandling.OPTIONAL)) {
	WebUI.click(addressPlus)
}
setField(address1_1, 'Test')
setField(city1, 'Toronto')
WebUI.sendKeys(city1, Keys.chord(Keys.TAB))
closeOpenDropdowns()
//WebUI.selectOptionByValue(country1, 'USA', true)
//WebUI.selectOptionByValue(state1, 'IA', true)
//setField(zip1, '2345678')

WebUI.click(addressDelete(2))
verifyPopupAndClose(popupMsg, 'Are you sure you want to delete this address?', noBtn)
WebUI.verifyElementPresent(address1_1, 3)

WebUI.click(addressDelete(2))
verifyPopupAndClose(popupMsg, 'Are you sure you want to delete this address?', okBtn)
WebUI.verifyElementNotPresent(address1_1, 3)
WebUI.delay(5)
// =====================================================================
// SCENARIO 4 - valid data & Save
// =====================================================================
setField(primaryMail, GlobalVariable.MyEmail_Id)
setField(firstName, 'William')
setField(lastName, 'Hover')
setDate(dob, '1982-03-16')

clickSave()
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Demographics data has been updated!')

// =====================================================================
// FINAL RESULT - fail if any soft failure was recorded
// =====================================================================
if (!softFailures.isEmpty()) {
	KeywordUtil.markFailed('Soft failures (' + softFailures.size() + '):\n - ' + softFailures.join('\n - '))
}