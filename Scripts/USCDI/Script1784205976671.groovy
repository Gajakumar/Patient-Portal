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



// ================= LOGIN =================
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

//Create Random Patient
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_PatientDetailsTabLink'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race'), 'American Indian or Alaska Native')

WebUI.click(findTestObject('USCDI/Page_MaximEyes/ins_jstree-checkbox'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_EmployerName'))

WebUI.assertElementPresent(findTestObject('USCDI/Page_MaximEyes/select_PR_TribalAffiliation_7b080746'), 0)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race'), 'American Indian or Alaska Native')

WebUI.assertElementPresent(findTestObject('USCDI/Page_MaximEyes/select_PR_TribalAffiliation_7b080746'), 0)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race_1'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race_1'), 'American Indian or Alaska Native')

WebUI.click(findTestObject('USCDI/Page_MaximEyes/ins_jstree-checkbox'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_ui-id-14'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_PatientDetailsTabLink'))

WebUI.assertElementPresent(findTestObject('USCDI/Page_MaximEyes/select_PR_TribalAffiliation_7b080746'), 0)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race_1'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race_1'), 'Native Hawaiian or Other Pacific Islander')

WebUI.click(findTestObject('USCDI/Page_MaximEyes/ins_jstree-checkbox'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_EmployerName'))

WebUI.assertElementPresent(findTestObject('USCDI/Page_MaximEyes/select_PR_TribalAffiliation_7b080746'), 0)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race_1'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race'), 'Native Hawaiian or Other Pacific Islander')

WebUI.click(findTestObject('USCDI/Page_MaximEyes/ins_jstree-checkbox'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_ui-id-14'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_PatientDetailsTabLink'))

WebUI.assertElementPresent(findTestObject('USCDI/Page_MaximEyes/select_PR_TribalAffiliation_7b080746'), 0)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race_1'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race_1'), 'Other Race')

WebUI.click(findTestObject('USCDI/Page_MaximEyes/ins_jstree-checkbox_1'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_EmployerName'))

WebUI.assertElementPresent(findTestObject('USCDI/Page_MaximEyes/select_PR_TribalAffiliation_7b080746'), 0)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race_1'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race'), 'Other Race')

WebUI.click(findTestObject('USCDI/Page_MaximEyes/ins_jstree-checkbox_2'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_ui-id-14'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_PatientDetailsTabLink'))

WebUI.assertElementPresent(findTestObject('USCDI/Page_MaximEyes/select_PR_TribalAffiliation_7b080746'), 0)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race'), 'Asian')

WebUI.click(findTestObject('USCDI/Page_MaximEyes/ins_jstree-checkbox'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_EmployerName'))

WebUI.assertElementNotPresent(findTestObject('USCDI/Page_MaximEyes/select_PR_TribalAffiliation_7b080746'), 0)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race'), 'Asian')

WebUI.click(findTestObject('USCDI/Page_MaximEyes/ins_jstree-checkbox'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_EmployerName'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_Select Race'))

WebUI.setText(findTestObject('USCDI/Page_MaximEyes/input_Search Race'), 'American Indian or Alaska Native')

WebUI.mouseOver(findTestObject('USCDI/Page_MaximEyes/li_American Indian or Alaska Native American Ind'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/ins_jstree-checkbox'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_EmployerName'))

WebUI.selectOptionByValue(findTestObject('USCDI/Page_MaximEyes/select_PR_TribalAffiliation_7b080746'), 'Native Village of Akutan', 
    false)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_dropdown-toggle menu-large recentmodule'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_Add New Encounter'))

WebUI.selectOptionByValue(findTestObject('USCDI/Page_MaximEyes/select_EncounterTypeID'), '51', false)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/input_btnSaveNewPEPopup'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/span_More'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/span_spnMiscCommunicationStatus'))

WebUI.rightClick(findTestObject('USCDI/Page_MaximEyes/td_American Indian or Alaska Native'))

WebUI.assertElementText(findTestObject('USCDI/Page_MaximEyes/td_American Indian or Alaska Native'), 'American Indian or Alaska Native', 
    0)

WebUI.rightClick(findTestObject('USCDI/Page_MaximEyes/td_Native Village of Akutan'))

WebUI.assertElementText(findTestObject('USCDI/Page_MaximEyes/td_Native Village of Akutan'), 'Native Village of Akutan', 
    0)

WebUI.click(findTestObject('USCDI/Page_MaximEyes/button_Close'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_dropdown-toggle menu-large recentmodule_1'))

WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_Patient Details'))

