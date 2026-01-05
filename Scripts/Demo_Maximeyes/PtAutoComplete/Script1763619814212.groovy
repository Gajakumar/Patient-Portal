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

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Optical_dropdown-toggle menu-large recentmodule'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Optical  Add Spec order'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Lab_ddlabs'), '21', true)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Lab_btnSelectSLLab'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_SPECTACLE RX_fg-purple mif-circle-plus_ec892d'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Add New_IS_OUTSIDE_RX'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Purpose_PURPOSE'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/li_PURCHASE_ui-id-46'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_OD_SPHERICAL_OD'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/li_PURCHASE_ui-id-123'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_OD_CYLINDER_OD'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/li_PURCHASE_ui-id-214'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_OD_AXIS_OD'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/li_PURCHASE_ui-id-294'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_OD_ADD_OD'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/li_PURCHASE_ui-id-331'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_OS_btnSaveNewPEPopup'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span__mif-cog font20 head-icon-shadow fg-white'))

WebUI.closeBrowser()

