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

//Login to maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

//Navigate to Reports
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Reports_dropdown-toggle menu-large recentmodule'))

//Click on Incentive Programs
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Incentive Programs'))

//Click on MIPS Reports
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_MIPS Reports'))

//Click on Promoting Interoperability reports
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Promoting Interoperability'))

//Select provider check box
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_ProvGroup_provFilterBoxPIM'), 
    'Katalon')

//
WebUI.click(findTestObject('Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/span_ipcert ipcert_icon-checked'))

//Click on go button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/button_GO'))

WebUI.delay(3)
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
//Verify den count
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/td_0'), '0')

//Verify Num count
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/td_0_1'), '0')

//Verify percentage
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/td_25'), '25')

//Click on find patient
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Find Patient'))

//Search patient with first and last name
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_LastName'), 
    'Count')

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Find Patient_FirstName'), 
    'Den')

//Click on search button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Active_btnSearchPatient'))

//Click on Encounter mega menu
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

//Click on Add Encounter
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Add New Encounter'))

//Select encounter type
WebUI.selectOptionByValue(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/select_Annual Health Maintenance, History a_2d641d'), 
    '51', true)

//Click on create encounter
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))

//Click on Hamburger menu
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30'))

//Click on SOC
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/span_Summary Of Care(C-CDA)'))

//Click on Upload To PatientPortal
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/span_Patient Portal_UploadToPatientPortal'))

//Verify toast msg
CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Upload to Patient Portal completed successfully.')

//Click on close button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

//Navigate to Reports
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Reports_dropdown-toggle menu-large recentmodule'))

//Click on Incentive Programs
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Incentive Programs'))

//Click on MIPS Reports
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_MIPS Reports'))

//Click on Promoting Interoperability reports
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Promoting Interoperability'))

//Select provider check box
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_ProvGroup_provFilterBoxPIM'), 
    'Katalon')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/span_ipcert ipcert_icon-checked'))

//Click on go button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/button_GO'))

//Verify den count
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/td_1'), '1')

//Verify Num count
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/td_1_1'), '1')

//Verify percentage
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/td_25_1'), 
    '25')

//Click on Encounter mega menu
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

//Navigate to Encounter Hx
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Encounter Hx'))

//Click on delete icon
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/span_NO_mif-remove font16 line-height18 fg-skyblue'))

//Click Yes button on confirmation popup
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_Are you sure you want to remove this _8974bb'))

//Navigate to Reports
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Reports_dropdown-toggle menu-large recentmodule'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Incentive Programs'))

//Click on MIPS Reports
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_MIPS Reports'))

//Click on Promoting Interoperability reports
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/a_Promoting Interoperability'))

//Select provider check box
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/input_ProvGroup_provFilterBoxPIM'), 
    'katalon')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/span_ipcert ipcert_icon-checked'))

//Click on go button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/button_GO'))

//Verify den count
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/td_0'), '0')

//Verify Num count
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/td_0_1'), '0')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Page_MaximEyes/span_Getting MaximEyes ready_mif-home font1_5ca900'))



