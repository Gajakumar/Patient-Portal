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

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/UserLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (3)'))

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (3)'), 
    'Rahul')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (3)'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3 (2)'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter (2)'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID (2)'), 
    '60', true)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Create Encounter Button'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Hamburger Menue'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/ePrescribe'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Download Data into MaximEyes'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/EncounterHx'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.closeBrowser()

