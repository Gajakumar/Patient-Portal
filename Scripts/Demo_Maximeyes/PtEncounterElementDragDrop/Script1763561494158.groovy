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

WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/UserLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient'))

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName'), 'Rahul')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID'), '60', 
    true)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnCreateNewEncounter'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Electronic Files_encTabList_17'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(10)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Template_mif-circle-plus font20'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/img_Central Retina_img-responsive'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Botox_btnAddDrawingTemplate'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.dragAndDropToObject(
    findTestObject('Object Repository/Page_MaximEyes/Branch Occlusion'),
    findTestObject('Object Repository/Page_MaximEyes/Target Central Retaina')
)
WebUI.delay(10)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Delete Drawing'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Procced Button on Popup'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Tests  Miscellaneous_encIcon enctPlusIcon'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Final Findings_truncateWord'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Annual exam_glyphicon-circelplus font1_0c94a1'))

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Search_textbox'), 'h')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/button_CLEAR_searchICD10Codes'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Description_fg-black'))

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Search_textbox'), 'm')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/button_CLEAR_searchICD10Codes'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Description_fg-black_1'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/td'))

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Search_textbox'), 'p')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/button_CLEAR_searchICD10Codes'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Description_fg-black_2'))

WebUI.delay(5)

WebUI.dragAndDropToObject(
	findTestObject('Object Repository/Page_MaximEyes/H81'),
	findTestObject('Object Repository/Page_MaximEyes/E72')
)

WebUI.delay(5)

WebUI.dragAndDropToObject(
	findTestObject('Object Repository/Page_MaximEyes/D45'),
	findTestObject('Object Repository/Page_MaximEyes/H81')
)

WebUI.delay(5)

WebUI.dragAndDropToObject(
	findTestObject('Object Repository/Page_MaximEyes/E72'),
	findTestObject('Object Repository/Page_MaximEyes/H81')
)

WebUI.delay(8)


WebUI.closeBrowser()

