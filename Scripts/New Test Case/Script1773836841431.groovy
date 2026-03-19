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

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/span_LinkEdiEmailAddressPP'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/div_Primary Email Address'), 
    'Primary Email Address')

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_btnSavePatientEmailIdPP'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/div_Email is not valid'), 
    'Email is not valid.')

WebUI.setText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_txtPatPrimEmailPP'), '1234')

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_btnSavePatientEmailIdPP'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/div_Email is not valid'), 
    'Email is not valid.')

WebUI.setText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_txtPatPrimEmailPP'), 'gajakumara@first-insight.com')

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_btnSavePatientEmailIdPP'))


WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/label_lblPatientEmailPP'), 
    '<gajakumara@first-insight.com>')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/span_lblDashboardEmail'), 
    'gajakumara@first-insight.com')

