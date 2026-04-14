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

//Click on delete button
WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_opacity-100'))

//Verify confirmation toast is displayed
WebUI.verifyElementText(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/p_Do you want to delete this picture'),
	'Do you want to delete this picture?')

//Click on cancel button
WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/button_Cancel'))

//Click on delete button
WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_opacity-100'))

//Click on Procced buton on confirmation popup
WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/button_Proceed'))

//Verify toast photo deleted
WebUI.verifyElementText(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/div_1'), 'Photo deleted ')





WebUI.verifyElementText(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/div_DS'), 'DS')

WebUI.closeBrowser()

