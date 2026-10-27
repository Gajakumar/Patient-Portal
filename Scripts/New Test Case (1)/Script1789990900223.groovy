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

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/h3_Speed up your arrival'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/h3_Speed up your arrival'), 'Speed up your arrival', 
    0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/p_Save time at the doctors office by fillingInt'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/p_Save time at the doctors office by fillingInt'), 'Save time at the doctor\'s office by fillingIntake Form', 
    0)

// Dashboard Appt
WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/h2_You have an upcoming appointment'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/h2_You have an upcoming appointment'), 'You have an upcoming appointment', 
    0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/p_10_25 AM on Monday, October 26, 2026Reason_ Pa'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/p_10_25 AM on Monday, October 26, 2026Reason_ Pa'), 
    '10:25 AM on Monday, October 26, 2026Reason: Patient Portal', 0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/div_Cancel'))

WebUI.assertElementPresent(findTestObject('Intake1/Page_Patient Portal/div_Cancel'), 0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/div_Reschedule'))

WebUI.assertElementPresent(findTestObject('Intake1/Page_Patient Portal/div_Reschedule'), 0)

//Ins page

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/input_Self Pay _ No Insurance Available'))

WebUI.assertElementPresent(findTestObject('Intake1/Page_Patient Portal/input_Self Pay _ No Insurance Available'), 0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/h1_Insurance'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/h1_Insurance'), 'Insurance', 0)

WebUI.click(findTestObject('Intake1/Page_Patient Portal/button_Save'))

//After intake submit

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/h1_David Smith'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/h1_David Smith'), 'David Smith', 0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/p_You have an upcoming appointment'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/p_You have an upcoming appointment'), 'You have an upcoming appointment', 
    0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/p_11_10 AM Monday, October 26, 2026'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/p_11_10 AM Monday, October 26, 2026_1'), '11:10 AM Monday, October 26, 2026', 
    0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/p_Reason_ Patient Portal'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/p_Reason_ Patient Portal'), 'Reason: Patient Portal', 
    0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/h2_All forms and History forms are up to date'))

WebUI.assertElementText(findTestObject('Intake1/Page_Patient Portal/h2_All forms and History forms are up to date'), 'All forms completed.', 
    0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/button_Cancel Appointment'))

WebUI.assertElementPresent(findTestObject('Intake1/Page_Patient Portal/button_Cancel Appointment'), 0)

WebUI.rightClick(findTestObject('Intake1/Page_Patient Portal/button_Reschedule'))

WebUI.assertElementPresent(findTestObject('Intake1/Page_Patient Portal/button_Reschedule'), 0)

//Home button on intake completed
WebUI.click(findTestObject('Intake1/Page_Patient Portal/button_Save'))

// intake link on dash
WebUI.click(findTestObject('Intake1/Page_Patient Portal/a_Intake Form'))

