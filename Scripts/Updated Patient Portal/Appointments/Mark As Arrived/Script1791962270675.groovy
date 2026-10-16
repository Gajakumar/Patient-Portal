import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

/* =====================================================================
 * Patient Portal > Dashboard > Mark As Arrived
 * Split each "TC" block below into its own Katalon Test Case
 * (or keep in one file and comment out the ones you don't need).
 *
 * Prerequisites (test data):
 *  - TC1 / TC2 : patient has a NON-telehealth appointment TODAY, and the
 *                current time is within 30 mins before the appointment.
 *  - TC3       : patient whose earliest appointment is NOT in that window
 *                (future date, or > 30 mins away, or telehealth).
 *  - Replace 'Common/Login' with your own login test case.
 * ===================================================================== */

// ---------- Helper ----------
TestObject xp(String name, String xpath) {
	TestObject to = new TestObject(name)
	to.addProperty('xpath', ConditionType.EQUALS, xpath)
	return to
}

// ---------- Locators (built from the shared HTML) ----------
TestObject btnMarkArrived   = xp('btnMarkArrived',
		"//div[contains(@class,'dashboard-appt-action')][.//span[normalize-space()='Mark As Arrived']]")
TestObject lblConfirmMsg    = xp('lblConfirmMsg',
		"//p[contains(normalize-space(),'Please confirm that you have physically arrived at the office and are ready to start your visit.')]")
TestObject btnYesArrived    = xp('btnYesArrived',   "//button[normalize-space()='Yes, Arrived']")
TestObject btnNotHereYet    = xp('btnNotHereYet',   "//button[normalize-space()='Not here yet']")
TestObject lblArrivedStatus = xp('lblArrivedStatus', "//span[normalize-space()='Arrived']")
TestObject lblArrivedMsg    = xp('lblArrivedMsg',   "//p[contains(normalize-space(),'You have arrived for your')]")
TestObject lnkIntakeForm    = xp('lnkIntakeForm',   "//a[@href='/intake-form']")
TestObject lblIntakeText    = xp('lblIntakeText',   "//a[@href='/intake-form']//span[normalize-space()='Intake Form']")

int TIMEOUT = 10

// Expected values (set via GlobalVariable / test data in real runs)
String expectedApptTime = '10:30 AM'          // e.g. GlobalVariable.apptTime
String expectedProvider = 'Dr. John Smith'    // e.g. GlobalVariable.providerName

/* =====================================================================
 * TC2 : Not here yet -> popup closes, no change, button still available
 * ===================================================================== */


WebUI.waitForElementVisible(btnMarkArrived, TIMEOUT)
WebUI.click(btnMarkArrived)
WebUI.waitForElementVisible(lblConfirmMsg, TIMEOUT)

WebUI.click(btnNotHereYet)

// Popup gone, still on dashboard
WebUI.waitForElementNotPresent(lblConfirmMsg, TIMEOUT)
assert WebUI.getUrl().contains('/dashboard')

// No arrival recorded: no Arrived status/message, button still there
WebUI.verifyElementNotPresent(lblArrivedStatus, 3)
WebUI.verifyElementNotPresent(lblArrivedMsg, 3)
WebUI.verifyElementPresent(btnMarkArrived, TIMEOUT)


/* =====================================================================
 * TC1 : Yes, Arrived  -> practice notified, dashboard shows Arrived
 * ===================================================================== */


// 1. Button is visible (appointment today, within 30 mins window)
WebUI.waitForElementVisible(btnMarkArrived, TIMEOUT)
WebUI.verifyElementPresent(btnMarkArrived, TIMEOUT)

// 2. Click -> confirmation popup
WebUI.click(btnMarkArrived)
WebUI.waitForElementVisible(lblConfirmMsg, TIMEOUT)
WebUI.verifyElementVisible(btnYesArrived)
WebUI.verifyElementVisible(btnNotHereYet)

// 3. Click Yes, Arrived
WebUI.click(btnYesArrived)

// 4. Popup closes and patient is on dashboard with Arrived state
WebUI.waitForElementNotPresent(lblConfirmMsg, TIMEOUT)
WebUI.verifyTextPresent('/dashboard', true, FailureHandling.OPTIONAL) // soft check, remove if URL differs
WebUI.waitForElementVisible(lblArrivedStatus, TIMEOUT)
WebUI.verifyElementText(lblArrivedStatus, 'Arrived')

// 5. Message: "You have arrived for your <time> appointment with <provider>. Someone will assist you soon."
String actualMsg = WebUI.getText(lblArrivedMsg).trim()
KeywordUtil.logInfo('Arrived message: ' + actualMsg)

assert actualMsg ==~ /You have arrived for your .+ appointment with .+\. Someone will assist you soon\./ :
		"Arrived message format mismatch: ${actualMsg}"
assert actualMsg.contains(expectedApptTime) : "Appointment time missing in message: ${actualMsg}"
assert actualMsg.contains(expectedProvider) : "Provider name missing in message: ${actualMsg}"

// 6. Mark As Arrived button should no longer be offered
WebUI.verifyElementNotPresent(btnMarkArrived, TIMEOUT)

// 7. Intake form still reachable (view / update)
WebUI.verifyElementVisible(lnkIntakeForm)
WebUI.verifyElementText(lblIntakeText, 'Intake Form')
WebUI.click(lnkIntakeForm)
WebUI.waitForPageLoad(TIMEOUT)
assert WebUI.getUrl().contains('/intake-form')









///* =====================================================================
// * TC3 : Button must NOT show (outside 30-min window / not today / telehealth)
// * Run with a patient/appointment that matches one of those conditions.
// * ===================================================================== */
//WebUI.openBrowser('')
//WebUI.maximizeWindow()
//WebUI.navigateToUrl(GlobalVariable.baseUrl + '/dashboard')
//WebUI.callTestCase(findTestCase('Common/Login'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.waitForPageLoad(TIMEOUT)
//WebUI.verifyElementNotPresent(btnMarkArrived, 5)
//
//WebUI.closeBrowser()