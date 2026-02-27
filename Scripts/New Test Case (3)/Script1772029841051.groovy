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

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/span_OBDYhCUh RRmZfUDDZf'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/h3_Switch to'), 
    'Switch to')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/button_Cancel'), 
    'Cancel')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/button_Proceed'), 
    'Proceed')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/button_Cancel'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/span_OBDYhCUh RRmZfUDDZf'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/select_OBDYhCUh RRmZfUDDZfJohn Doe'), 
    'OBDYhCUh RRmZfUDDZfJohn Doe')

WebUI.selectOptionByValue(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/select_OBDYhCUh RRmZfUDDZfJohn Doe'), 
    '1209', false)

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/select_OBDYhCUh RRmZfUDDZfJohn Doe'), 
    'OBDYhCUh RRmZfUDDZfJohn Doe')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/button_Proceed'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/p_You are viewing record for John Doe_1'), 
    'You are viewing record for John Doe')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/div_w-10 h-10 flex items-center justify-center r'))

