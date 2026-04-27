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
import java.util.Random
import org.apache.commons.lang.RandomStringUtils
import utils.GmailVerifier
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import java.time.LocalDate
import java.time.ZoneId
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import email.EmailVerification
import utils.EmailUtils
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.WebElement

// Checkbox locator
TestObject checkboxObj = new TestObject()
checkboxObj.addProperty("xpath", ConditionType.EQUALS,
	"//div[@class='flex items-center cursor-pointer']//div"
)

// Delete button
TestObject deleteBtn = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_DS_a')

// Confirm button
TestObject yesBtn = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Select a Message_px-8 py-2 rounded b_18739d')

// Popup text (stable)
TestObject popupText = new TestObject()
popupText.addProperty("xpath", ConditionType.EQUALS,
"//p[contains(text(),'Are you sure you want to delete')]"
)

// Get count
def getCount(TestObject checkboxObj) {
    return WebUiCommonHelper.findWebElements(checkboxObj, 5).size()
}


int initialCount = getCount()
KeywordUtil.logInfo("Initial messages: " + initialCount)

// Get all checkboxes
List<WebElement> checkboxes = WebUiCommonHelper.findWebElements(checkboxObj, 10)

if (checkboxes.size() == 0) {
    KeywordUtil.logInfo("No messages to delete")
    return
}

// ===== SELECT ALL CHECKBOXES =====
for (WebElement checkbox : checkboxes) {
    WebUI.executeJavaScript("arguments[0].scrollIntoView(true);", Arrays.asList(checkbox))
    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(checkbox))
    WebUI.delay(0.3)   // small gap to avoid UI miss
}

KeywordUtil.logInfo("✅ Selected " + checkboxes.size() + " messages")

// ===== CLICK DELETE ONCE =====
WebUI.waitForElementClickable(deleteBtn, 10)
WebUI.click(deleteBtn)

// ===== VERIFY POPUP =====
WebUI.waitForElementVisible(popupText, 10)

String text = WebUI.getText(popupText)
if (!text.contains("Are you sure you want to delete")) {
    KeywordUtil.markFailed("❌ Popup not displayed correctly")
}

// ===== CONFIRM DELETE =====
WebUI.waitForElementClickable(yesBtn, 10)
WebUI.click(yesBtn)

// ===== WAIT FOR DELETE =====
WebUI.delay(3)


