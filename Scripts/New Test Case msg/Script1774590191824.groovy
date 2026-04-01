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

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/span_12unread messages'), '12unread messages')

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/div_12unread messages'))

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/svg_a'))

WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Page_Patient Portal/section_Messages list'), 0)

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/ul_12'), '12')

WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Page_Patient Portal/svg_a_1'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Page_Patient Portal/path_icon'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Page_Patient Portal/a_2'), 0)

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/a_2'), '2')

WebUI.rightClick(findTestObject('Scenario Update1703/Page_Patient Portal/div_w-2 h-2 rounded-full'))

WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Page_Patient Portal/div_w-2 h-2 rounded-full'), 0)

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/a_2'))

WebUI.rightClick(findTestObject('Scenario Update1703/Page_Patient Portal/p_Education Material_ Alcohol Use Disorder (AUD)'))

WebUI.rightClick(findTestObject('Scenario Update1703/Page_Patient Portal/p_Education Material_ Alcohol Use Disorder (AUD)'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/p_Education Material_ Alcohol Use Disorder (AUD)'), 
    'Education Material: Alcohol Use Disorder (AUD)')

WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/svg_a_3'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/ul_12_1'), '12')

WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Page_Patient Portal/a_2'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Page_Patient Portal/svg_a_4'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Page_Patient Portal/path_icon_1'), 0)

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/div_To_ Gakrrq Mwwvxiur05_25 AM'), 'To: Gakrrq Mwwvxiur05:25 AM')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/div_To_ Gakrrq Mwwvxiur05_25 AM_1'), 'To: Gakrrq Mwwvxiur05:25 AM')

