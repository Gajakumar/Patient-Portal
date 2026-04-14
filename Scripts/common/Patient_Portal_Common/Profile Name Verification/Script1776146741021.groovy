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

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/label_Name'),
	'Name')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Edit'),
	'Edit')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_David'),
	'David')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_Smith'),
	'Smith')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Edit'))

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control mb-2 text-dark py-2_d59cf8'))

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control mb-2 text-dark py-2_d59cf8_1'),
	'David1')

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5'),
	'Smith')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5'))

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5_1'),
	'Smith2')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Save'),
	'Save')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Save'))

WebUI.delay(2)

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Edit'),
	'Edit')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_David1'),
	'David1')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_Smith2'),
	'Smith2')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Edit'))

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control mb-2 text-dark py-2_d59cf8_1'))

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control mb-2 text-dark py-2_d59cf8'),
	'David')

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5_1'),
	'Smith2')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5_1'))

WebUI.setText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/input_Save_form-control text-dark py-2 px-3 fs-5'),
	'Smith')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/button_Save'))

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_David'),
	'David')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_Smith'),
	'Smith')

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/svg_Messages_text-light'))

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/svg_David Smith_text-primary'))

WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/span_Profile'))

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_David'),
	'David')

WebUI.verifyElementText(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/Page_Patient Portal/p_Smith'),
	'Smith')