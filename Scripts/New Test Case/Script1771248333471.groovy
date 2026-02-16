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

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h1_Add New Individual'), 'Add New Individual')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_Access Granted'), 'Access Granted')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_tewsdrw asww can now access your records'), 
    'tewsdrw asww can now access your records!')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/h2_QUESTIONS'), 'QUESTIONS?')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/svg_text-light'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/div_w-10 h-10 flex items-center justify-center r'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/span_Authorized Individuals'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/span_tewsdrw asww'), 'tewsdrw asww ')

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/td_02_18_2026'), '02/18/2026')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/svg_a'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/p_Are you sure you want to remove this authorize'), 
    'Are you sure you want to remove this authorized individual?')

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Cancel'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/svg_a'))

WebUI.click(findTestObject('Authorized Individual/Page_Patient Portal/button_Delete'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Page_Patient Portal/td_No authorized individuals found'), 
    'No authorized individuals found.')

