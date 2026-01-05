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

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient'))

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName'), 'Rahul')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Billing_dropdown-toggle menu-large recentmodule'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Billing  Add New Superbill'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/CreateNewBill'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Shortcut1'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Shorcut1Input'))

WebUI.delay(2)

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/Shorcut1Input'), '99214')

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/td_Price_billChargesGridView_565e_EEG_DXEdi_df15ad'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Audited by_AuditedBy'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Current Bill_Contextmenu_PrintOpt'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/li'))

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Current Bill_Contextmenu_PrintOpt'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/li'))

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span__mif-cog font20 head-icon-shadow fg-white'))

WebUI.closeBrowser()

