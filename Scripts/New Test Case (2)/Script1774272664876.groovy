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

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/input_No Insurance Card'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/div_Patient Relationship to Insured'), 'Patient Relationship to Insured')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/input_Insured ID'), '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/input_Legal First Name'), '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/input_Last Name'), '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/input_Middle Name (Optional)'), '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/input_Suffix (Optional)'), '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/input_Enter value'), '')

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/input_Male'))

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/input_Female'))

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/button_Save'))

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/div_Patient Relationship to Insured'))

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/li_Self'))

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/input_No Insurance Card_1'))

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/button_Save'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/div_1'), 'Insurance information saved successfully')

