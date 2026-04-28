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

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/div_Select User_1'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/li_Jerry Wilson'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/button_Proceed'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/p_You do not have access for the Portal. Kindly'), 
    'You do not have access for the Portal. Kindly ask Jerry Wilson to enable access for you')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/button_OK'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/div_Select User_1'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/li_Jerry Wilson'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/button_Proceed'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/Page_Patient Portal/Page_Patient Portal/svg_text-primary'))

