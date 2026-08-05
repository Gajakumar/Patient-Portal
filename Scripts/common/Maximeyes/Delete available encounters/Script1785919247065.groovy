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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.model.FailureHandling

WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(2)

// Check if payment symbol is visible
if (WebUI.verifyElementVisible(findTestObject('Object Repository/Page_MaximEyes/Delete Enc Button'), FailureHandling.OPTIONAL)) {

	List<WebElement> totalEncs = driver.findElements(
			By.xpath("//span[@title='Delete']")
	)

	WebUI.comment("Total Encounters = ${totalEncs.size()}")

	for (int i = 0; i < totalEncs.size(); i++) {

		WebUI.delay(2)

		// Click first available Enc
		WebUI.click(findTestObject('Page_MaximEyes/Delete Enc Button'))

		WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
		WebUI.delay(3)

		WebUI.comment("Clicked on Delete button")

		// Click Yes if confirmation dialog appears
		TestObject yesButton = new TestObject()
		yesButton.addProperty(
				"xpath",
				com.kms.katalon.core.testobject.ConditionType.EQUALS,
				"//input[@id='OK']"
		)

		if (WebUI.verifyElementVisible(yesButton, FailureHandling.OPTIONAL)) {

			WebUI.click(yesButton)

			WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
			WebUI.delay(2)
		}
	}

} else {

	WebUI.comment("No Existing Encounters are displayed")
}

