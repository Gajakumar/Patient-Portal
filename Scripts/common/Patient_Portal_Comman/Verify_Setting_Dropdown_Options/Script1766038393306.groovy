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

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/span_Patientafb26 Test8936c_block pr-14 py-_560001'),'Profile')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/span_Profile_block pr-14 py-2 font-normal t_ea6dd6'),'Update Demographics')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/span_Update Demographics_block pr-14 py-2 f_d2a216'),'Update Insurance')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/span_Update Insurance_block pr-14 py-2 font_4627be'),'Communication Preferences')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/span_Communication Preferences_block pr-14 _4d9414'),'Authorized Individuals')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/span_Authorized Individuals_block pr-14 py-_f7d1ec'),'Activity Log')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/span_Activity Log_block pr-14 py-2 font-nor_60db84'),'Opt Out')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/span_Opt Out_block pr-14 py-2 font-normal t_79500b'),'Log Out')

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Close_Setting_btn'))

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Patientafb26 Test8936c_text-base smtext-l_9ae91e'),
	'Messages')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Patientafb26 Test8936c_text-base smtext-l_9ae91e_1'),
	'Appointments')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Patientafb26 Test8936c_text-base smtext-l_9ae91e_2'),
	'Policies & Consent Forms')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Patientafb26 Test8936c_text-base smtext-l_9ae91e_3'),
	'Health Summary')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Patientafb26 Test8936c_text-base smtext-l_9ae91e_4'),
	'Eyewear & Rx')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/p_Patientafb26 Test8936c_text-base smtext-l_9ae91e_5'),
	'Bills & Payments')