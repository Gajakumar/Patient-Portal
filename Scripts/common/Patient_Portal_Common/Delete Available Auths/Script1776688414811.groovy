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
TestObject deleteBtnObj = new TestObject()
deleteBtnObj.addProperty("xpath", ConditionType.EQUALS,
	"//*[name()='svg' and .//*[name()='line'] and .//*[name()='path']]/parent::div"
)

while (true) {
	List<WebElement> buttons = WebUI.findWebElements(deleteBtnObj, 5)

	if (buttons.size() == 0) {
		println("No more delete buttons")
		break
	}

//	buttons.get(0).click()
//	WebUI.delay(1)
	//Click on delete button
	WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Delete'))
	WebUI.delay(1)
	
	WebUI.click(findTestObject('Object Repository/Authorized Individual/Page_Patient Portal/Delete button on confirmation popup'))
	
}