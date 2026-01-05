import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

// Web Drivers and Helpers
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper

// Selenium
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence

// Java
import org.openqa.selenium.Point
import java.time.Duration
import java.awt.Robot
import java.awt.event.InputEvent

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/UserLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Create New Patient'), [:], FailureHandling.STOP_ON_FAILURE)

//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_CA_CreateNew'))
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Schedule_dropdown-toggle menu-large recentmodule'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_concat(Test, , S)_Schedule  Schedule'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(5)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Resource_PatientScheduleResourceId'), 
    '28', true)

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/div_PM_scheduler_commonControlsBlock_select_b30d52'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.doubleClick(findTestObject('Object Repository/Page_MaximEyes/Sch 8 45 Am'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(5)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Type_TypeID'), '1046', true)

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/SaveAppointment'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(10)

WebUI.dragAndDropToObject('Object Repository/Max.com/Page_MaximEyes/Appt Time 8 45 AM', 'Object Repository/Max.com/Page_MaximEyes/Appt Time 9 45 AM')

//// === Get source & target ===
//WebElement source = WebUiCommonHelper.findWebElement(
//        findTestObject('Object Repository/Page_MaximEyes/Appt Time 8 45 AM'), 20)
//
//WebElement target = WebUiCommonHelper.findWebElement(
//        findTestObject('Object Repository/Page_MaximEyes/Appt Time 9 45 AM'), 20)
//
//Actions act = new Actions(DriverFactory.getWebDriver())
//
//// === Start drag from CENTER of source element ===
//int sourceCenterX = source.getSize().getWidth() / 2
//int sourceCenterY = source.getSize().getHeight() / 2
//
//int sourceAbsX = source.getLocation().getX() + sourceCenterX
//int sourceAbsY = source.getLocation().getY() + sourceCenterY
//
//int targetAbsX = target.getLocation().getX() + 15   // inside target cell
//int targetAbsY = target.getLocation().getY() + 15   // inside target cell
//
//int moveX = targetAbsX - sourceAbsX
//int moveY = targetAbsY - sourceAbsY
//
//// === Start the drag ===
//act.moveToElement(source, sourceCenterX, sourceCenterY)
//   .clickAndHold()
//   .perform()
//
//WebUI.delay(0.5)
//
//// === Move slowly pixel-by-pixel ===
//int steps = 30
//
//for (int i = 1; i <= steps; i++) {
//    int stepX = (int)(moveX * i / steps)
//    int stepY = (int)(moveY * i / steps)
//
//    int prevX = (int)(moveX * (i-1) / steps)
//    int prevY = (int)(moveY * (i-1) / steps)
//
//    act.moveByOffset(stepX - prevX, stepY - prevY).perform()
//    WebUI.delay(0.05)
//}
//
//// === Release ===
//act.release().perform()


WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnOfficeApptRechedule'))

WebUI.delay(5)

WebUI.takeScreenshot()

WebUI.closeBrowser()

