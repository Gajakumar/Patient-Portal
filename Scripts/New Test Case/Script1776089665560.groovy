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

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/svg_opacity-100'))

WebUI.setText(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/input_file'), 'C:\\fakepath\\InsCardInvalid.tif')

WebUI.rightClick(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/p_Invalid Image Format Select an alternative im'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/p_Invalid Image Format Select an alternative im'), 
    'Invalid Image Format! Select an alternative image source file such as a PNG, JPEG, JPG.')

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/button_Ok'))

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/svg_opacity-100'))

WebUI.setText(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/input_file'), 'C:\\fakepath\\2mb.jpg')

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/svg_text-light'))

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/svg_opacity-100'))

WebUI.setText(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/input_file'), 'C:\\fakepath\\InsCardInvalid.tif')

WebUI.rightClick(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/p_Invalid Image Format Select an alternative im'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/p_Invalid Image Format Select an alternative im'), 
    'Invalid Image Format! Select an alternative image source file such as a PNG, JPEG, JPG.')

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/button_Ok'))

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/svg_opacity-100'))

WebUI.setText(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/input_file'), 'C:\\fakepath\\2mb.jpg')

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Page_Patient Portal/svg_text-light'))

WebUI.openBrowser('')

WebUI.navigateToUrl('https://ptportal-react.maximeyes.com/ptportal2710')

WebUI.rightClick(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_opacity-50'))

WebUI.verifyElementNotClickable(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_opacity-50'))

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_opacity-100_1'))

WebUI.setText(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/input_file_1'), 'C:\\fakepath\\2mb.jpg')

WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_text-light_1'))

WebUI.closeBrowser()

