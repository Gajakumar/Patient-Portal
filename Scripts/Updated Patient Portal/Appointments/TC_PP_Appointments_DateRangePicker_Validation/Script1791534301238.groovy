import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.WebElement as WebElement
import java.util.Arrays as Arrays
import utils.CheckboxKeywords as CK
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import java.util.Arrays
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType

/* ---------------- CONFIG ---------------- */
final DateTimeFormatter FMT = DateTimeFormatter.ofPattern('MM/dd/yyyy')
final DateTimeFormatter MON = DateTimeFormatter.ofPattern('MMM yyyy')
final String SEP = ' / '          // TODO: confirm separator shown in the field
// Dashboard Objects
TestObject dashboardMenuBtn = findTestObject('Appointments/PP Appointment/Page_Patient Portal/div_dashboard-menu-icon-btn border-2 rounded-ful')
/* ---------------- LOCATORS ---------------- */
def xp = { String x ->
    TestObject to = new TestObject(x)
    to.addProperty('xpath', ConditionType.EQUALS, x)
    return to
}

TestObject dateField  = xp("//div[@aria-label='Open calendar']//*[name()='svg']")  
TestObject dialog     = xp("//div[@role='dialog'][@aria-label='Select date range']")
TestObject btnCancel  = xp("//div[@role='dialog']//button[normalize-space()='Cancel']")
TestObject btnConfirm = xp("//div[@role='dialog']//button[normalize-space()='Confirm']")
TestObject btnCustom  = xp("//div[@role='dialog']//button[normalize-space()='Custom']")
TestObject btnPresets = xp("//div[@role='dialog']//button[normalize-space()='Presets']")
TestObject startInput = xp("//div[@role='dialog']//input[@aria-label='Start Date']")
TestObject endInput   = xp("//div[@role='dialog']//input[@aria-label='End Date']")
// Error container sits right above Cancel/Confirm; verify once an error is showing
TestObject errorMsg   = xp("//div[@role='dialog']//div[contains(@class,'px-3') and contains(@class,'pt-2')]")
TestObject errorText  = xp("//div[@role='dialog']//*[contains(@class,'text-red') or contains(@class,'red')]")

def preset = { String n -> xp("//div[@role='dialog']//button[normalize-space()='${n}']") }
def dayCell = { String month, int day ->
    xp("//div[@role='dialog']//h3[normalize-space()='${month}']" +
       "/ancestor::div[contains(@class,'p-4')][1]" +
       "//div[contains(@class,'cursor-pointer') and normalize-space()='${day}']")
}

//Login to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Sign in With User Name and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : UserName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

// OTP Verification
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp)


// Auto type into four input boxes
String[] digits = otp.toCharArray()

WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.comment('Step 7: Click on Dashboard Menu Icon')                              // Log step
WebUI.click(dashboardMenuBtn)


/* ---------------- HELPERS ---------------- */
def openPicker   = { WebUI.click(dateField); WebUI.verifyElementVisible(dialog) }
def verifyClosed = { WebUI.verifyElementNotPresent(dialog, 3) }
def fieldValue   = {
    String v = WebUI.getAttribute(dateField, 'value')
    return (v != null && v.trim()) ? v.trim() : WebUI.getText(dateField).trim()
}
def expectedRange = { LocalDate s, LocalDate e -> s.format(FMT) + SEP + e.format(FMT) }

// React-controlled inputs: select all + delete is more reliable than clearText
def typeInto = { TestObject o, String v ->
    WebUI.click(o)
    WebUI.sendKeys(o, Keys.chord(Keys.CONTROL, 'a'))
    WebUI.sendKeys(o, Keys.chord(Keys.BACK_SPACE))
    if (v) { WebUI.sendKeys(o, v) }
}

LocalDate today = LocalDate.now()

/* ---------- NEW / CHANGED HELPERS ---------- */

// Reopen picker -> Custom -> read inputs -> Cancel (does not change the selection)
def getAppliedRange = {
    openPicker()
    WebUI.click(btnCustom)
    String s = WebUI.getAttribute(startInput, 'value')
    String e = WebUI.getAttribute(endInput, 'value')
    WebUI.click(btnCancel)
    verifyClosed()
    return [s, e]
}

def verifyAppliedRange = { LocalDate s, LocalDate e ->
    def r = getAppliedRange()
    WebUI.verifyEqual(r[0], s.format(FMT))
    WebUI.verifyEqual(r[1], e.format(FMT))
}

// Highlight check for the currently open picker (only dates in the left-hand month)
// start/end = bg-blue-500, in-between = bg-blue-100 (from your HTML)
def verifyHighlight = { LocalDate s, LocalDate e ->
    String month = today.format(MON)
    def cell = { int d, String cls ->
        xp("//div[@role='dialog']//h3[normalize-space()='${month}']" +
           "/ancestor::div[contains(@class,'p-4')][1]" +
           "//div[contains(@class,'${cls}') and normalize-space()='${d}']")
    }
    LocalDate d = s
    while (!d.isAfter(e)) {
        if (d.getMonth() == today.getMonth() && d.getYear() == today.getYear()) {
            String cls = (d == s || d == e) ? 'bg-blue-500' : 'bg-blue-100'
            WebUI.verifyElementPresent(cell(d.getDayOfMonth(), cls), 3)
        }
        d = d.plusDays(1)
    }
}

def selectPresetAndConfirm = { String n, LocalDate s, LocalDate e ->
    openPicker()
    WebUI.click(preset(n))
    WebUI.click(btnConfirm)
    verifyClosed()
    verifyAppliedRange(s, e)          // replaces the fieldValue() check
}

/* ---------- CHANGED TEST CASES ---------- */

/* TC01 - Default = last 1 year (verified via picker, not the field) */
verifyAppliedRange(today.minusYears(1), today)

/* TC03 - Predefined ranges */
selectPresetAndConfirm('Today',        today, today)
selectPresetAndConfirm('Yesterday',    today.minusDays(1), today.minusDays(1))
selectPresetAndConfirm('Last 7 days',  today.minusDays(6),  today)
selectPresetAndConfirm('Last 30 days', today.minusDays(29), today)
selectPresetAndConfirm('Last 1 Year',  today.minusYears(1), today)

// Full-period presets: first day -> last day of the period
selectPresetAndConfirm('This Month',   today.withDayOfMonth(1),
                                       today.withDayOfMonth(today.lengthOfMonth()))      // 10/01 - 10/31

LocalDate lm = today.minusMonths(1)
selectPresetAndConfirm('Last Month',   lm.withDayOfMonth(1),
                                       lm.withDayOfMonth(lm.lengthOfMonth()))            // 09/01 - 09/30

selectPresetAndConfirm('This Year',    today.withDayOfYear(1),
                                       today.withDayOfYear(today.lengthOfYear()))  

/* TC03b - Highlight matches the screenshot for Last 7 days */
openPicker()
WebUI.click(preset('Last 7 days'))
verifyHighlight(today.minusDays(6), today)    // 3 and 9 solid, 4-8 light
WebUI.click(btnConfirm)
verifyClosed()
openPicker()
verifyHighlight(today.minusDays(6), today)    // highlight persists after reopening
WebUI.click(btnCancel)

/* TC04 - Cancel keeps previous range */
selectPresetAndConfirm('Last 30 days', today.minusDays(29), today)   // known starting state
openPicker()
WebUI.click(preset('Last 7 days'))
WebUI.click(btnCancel)
verifyClosed()
verifyAppliedRange(today.minusDays(29), today)

/* TC05 - Pick start/end from calendar, then Confirm */
openPicker()
WebUI.click(dayCell(today.format(MON), 1))
WebUI.click(dayCell(today.format(MON), 5))
WebUI.click(btnConfirm)
verifyClosed()
verifyAppliedRange(today.withDayOfMonth(1), today.withDayOfMonth(5))

/* TC10 - Valid custom range */
openPicker()
WebUI.click(btnCustom)
typeInto(startInput, '01/01/2025')
typeInto(endInput,   '01/31/2025')
WebUI.click(btnConfirm)
verifyClosed()
verifyAppliedRange(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31))

/* TC11 - Cancel in Custom mode restores previous range */
openPicker()
WebUI.click(btnCustom)
typeInto(startInput, '02/02/2025')
WebUI.click(btnCancel)
verifyClosed()
verifyAppliedRange(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31))