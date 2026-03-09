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
import stories.NavigateStory
import java.awt.Robot
import java.awt.event.KeyEvent

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/span__mif-cog font20 head-icon-shadow fg-white'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/a_Modules'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/a_Encounters'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/a_Incentive Programs'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/span_Do nothing_icon-checked'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/Home btn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'), [:], FailureHandling.STOP_ON_FAILURE)

//Get Patient ID
TestObject patientIdObj = findTestObject(
	'Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen'
)

WebUI.waitForElementVisible(patientIdObj, 15)

GlobalVariable.GV_PatientID =
	WebUI.getAttribute(patientIdObj, 'value') ?: ''

println "✅ Patient ID stored: " + GlobalVariable.GV_PatientID


WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Add New Encounter_Create New'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/span_TOC Req_spnSignOff'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/input_Are you sure you want to sign off the_f71194'))

WebUI.setText(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/input_Patient Portal_signaturePassword'), '123456')

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/input_Patient Portal_authenticateUserSignature'))

//WebUI.waitForElementVisible(
//	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
//	5
//)
//
//WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'), 
//    'This encounter is now signed off.')

WebUI.delay(5)

Robot robot = new Robot()
robot.keyPress(KeyEvent.VK_ESCAPE)
robot.keyRelease(KeyEvent.VK_ESCAPE)

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/span__mif-cog font20 head-icon-shadow fg-white'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/a_Modules'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/a_Encounters_1'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/a_Incentive Programs'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/span_Upload for Patient_icon-checked'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/span_First Insight Agreements_mif-home font_746c66'))

//Search Patient
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'), [('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Add New Encounter_Create New'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/span_TOC Req_spnSignOff'))

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/input_Are you sure you want to sign off the_f71194'))

WebUI.setText(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/input_Patient Portal_signaturePassword'), '123456')

WebUI.click(findTestObject('Object Repository/SOC Upload/Page_MaximEyes/input_Patient Portal_authenticateUserSignature'))

WebUI.waitForElementVisible(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
	15
)

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'), 
    'Upload to Patient Portal completed successfully.')

