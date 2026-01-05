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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://qa8.maximeyes.com/Account/Login')

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838'), 'QA_User')

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053'), 'Automation@2026')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)


WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (2)'))

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (2)'), 'Rahul')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (2)'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(5)

WebUI.sendKeys(findTestObject('Page_MaximEyes/FileUploadInput'), 'C:\\Users\\Gajakumar_a\\Pictures\\Rahul.png')

WebUI.delay(10)

WebUI.click(findTestObject('Page_MaximEyes/OAButton'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(5)

WebUI.closeBrowser()

