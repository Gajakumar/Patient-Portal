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
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'))
//
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), GlobalVariable.DOB)

//TestObject dobObj = findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB')
//
//// Get element WITHOUT clicking
//def element = WebUI.findWebElement(dobObj, 10)
//
//// Set value + remove focus trigger
//WebUI.executeJavaScript("""
//    let el = arguments[0];
//    el.removeAttribute('readonly');
//    el.setAttribute('value','${GlobalVariable.DOB}');
//    el.dispatchEvent(new Event('input', { bubbles: true }));
//    el.dispatchEvent(new Event('change', { bubbles: true }));
//    el.blur();
//""", Arrays.asList(WebUI.findWebElement(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), 10)))

//CustomKeywords.'common.DatePickerHelper.selectDOB'(GlobalVariable.DOB)

WebUI.sendKeys(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), GlobalVariable.DOB)

//TestObject closeBtnOnCal = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/button_CLOSE')
//
//WebUI.delay(2)
//
//
//if (WebUI.waitForElementClickable(closeBtnOnCal, 5, FailureHandling.OPTIONAL)) {
//    WebUI.click(closeBtnOnCal)
//}

TestObject closeBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/button_CLOSE')

if (WebUI.verifyElementPresent(closeBtn, 10, FailureHandling.OPTIONAL)) {
	
	WebUI.scrollToElement(closeBtn, 2)
	WebUI.waitForElementVisible(closeBtn, 10)

	try {
		WebUI.waitForElementClickable(closeBtn, 5)
		WebUI.click(closeBtn)
		println("✅ Normal click worked")
		
	} catch (Exception e1) {
		println("⚠️ Normal click failed, trying Actions click")

		try {
			WebElement el = WebUI.findWebElement(closeBtn)
			new Actions(DriverFactory.getWebDriver()).moveToElement(el).click().perform()
			println("✅ Actions click worked")

		} catch (Exception e2) {
			println("⚠️ Actions click failed, trying JS click")

			WebElement el = WebUI.findWebElement(closeBtn)
			WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el))
			println("✅ JS click worked")
		}
	}
}

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/ProccedBtnAftrDOBConfirm'))



WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))

String today = LocalDate.now().format(DateTimeFormatter.ofPattern('MM/dd/yyyy'))

println('Today: ' + today)

String uiText = WebUI.getText(findTestObject('Object Repository/Page_Patient Portal/div_I Accept_fs-5'))

println('UI Text: ' + uiText)

String[] parts = uiText.split('\\|')

String namePart = (parts[0]).trim()

String datePart = (parts[1]).trim()

WebUI.verifyEqual(datePart, today)

String[] nameSplit = namePart.split(' ')

String uiFirstName = (nameSplit[0]).trim()

String uiLastName = (nameSplit[1]).trim()

println('UI First Name: ' + uiFirstName)

println('UI Last Name: ' + uiLastName)

WebUI.verifyEqual(uiFirstName, GlobalVariable.PatientFirstName, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyEqual(uiLastName, GlobalVariable.PatientLastName, FailureHandling.CONTINUE_ON_FAILURE)

WebElement canvas = WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))

// 1. Locate canvas
TestObject canvasObj = findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas')

// 2. Wait & scroll
WebUI.waitForElementVisible(canvasObj, 30)
WebUI.scrollToElement(canvasObj, 5)

// 3. Get WebElement
WebElement canvasElement = WebUiCommonHelper.findWebElement(canvasObj, 10)

if (canvasElement == null) {
    KeywordUtil.markFailed("❌ Canvas not found! Check XPath.")
}

// 4. Draw signature safely
Actions actions = new Actions(DriverFactory.getWebDriver())

actions.moveToElement(canvasElement, 10, 10)   // move INSIDE canvas
       .clickAndHold()
       .moveByOffset(40, 10)
       .moveByOffset(30, -15)
       .moveByOffset(35, 20)
       .moveByOffset(-25, 15)
       .release()
       .perform()

println("✔ Signature drawn successfully!")

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))

CustomKeywords.'common.ToastHelper.verifyToastMessage'("Terms accepted successfully!")