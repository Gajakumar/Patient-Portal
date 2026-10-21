import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
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

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/span_glyphicon-circelplus font17 padR2 fg-skyblu'))

WebUI.setText(findTestObject('Page_MaximEyes/Page_MaximEyes/input_REACTION_DATE_GF_398a_GF_I'), '10/20/2026')

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/input_ALLERGY_GF_398a_GF_I'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/div_ALLERGY_GF_398a_GF_DDD_L_D'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/td_ALLERGY_GF_398a_GF_DDD_L_LBI1246T0'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/input_REACTION_GF_398a_GF_I'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/td_REACTION_GF_398a_GF_DDD_L_LBI4T0'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/img_Severity_GF_398a_GF_B-1Img'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/td_Severity_GF_398a_GF_DDD_L_LBI1T0'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/img_STATUS_GF_398a_GF_B-1Img'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/td_STATUS_GF_398a_GF_DDD_L_LBI0T0'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/img_SOURCE_GF_398a_GF_B-1Img'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/td_STATUS_GF_398a_GF_DDD_L_LBI0T0'))

WebUI.click(findTestObject('Page_MaximEyes/Page_MaximEyes/div_encounterform12e7f9662'))

