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

String todayGMT = CustomKeywords.'common.DateUtil.getTodayDateGMT'()
WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/h2_Summary of Care (C-CDA)'),
	'Summary of Care (C-CDA)')

//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/h4_Patientbca16 Test8848f'),
//	'Patientbca16 Test8848f')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/font_01162026'), todayGMT)

//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_100636'), '100636')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_03161982'), '03/16/1982')

//WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_100328'), '100328')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Automation Element Test Encounter'),
	'Automation Element Test Encounter')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_01162026'), todayGMT)

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Patient Portal'),
	'Patient Portal')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Lipitor atorvastatin 10 617314'),
	'Lipitor atorvastatin 10 [617314]')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Take 10 mg by mouth once a day'),
	'Take 10 mg by mouth once a day')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_01162026_1'), todayGMT)

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/span_195967001'), '195967001')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Asthma'), 'Asthma')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Active'), 'Active')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Date'), 'Date')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_01162026_2'), todayGMT)

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Birth Sex'), 'Birth Sex')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Male'), 'Male')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Weight'), 'Weight')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_123 lbs'), '123 lbs')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_HeightLength'),
	'Height/Length')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_165.1 cm'), '165.1 cm')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Respiration rate'),
	'Respiration rate')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_10 min'), '10 /min')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Body Temperature'),
	'Body Temperature')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_80 F'), '80 F')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Pulse Oximetry'),
	'Pulse Oximetry')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_40'), '40 %')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Inhaled Oxygen Concentration'),
	'Inhaled Oxygen Concentration')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_90'), '90 %')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Heart Beat'), 'Heart Beat')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_123  min'), '123 /min')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Hartnups disease'),
	'Hartnup\'s disease')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Patient Portal_1'),
	'Patient Portal')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/span_Automated VF, Central - 40'),
	'Automated VF, Central - 40')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Automated VF'),
	'Automated VF')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Completed'), 'Completed')

WebUI.verifyElementText(findTestObject('Object Repository/SOC Verification On PP/Page_Patient Portal/td_Test Diag'), 'Test Diag')