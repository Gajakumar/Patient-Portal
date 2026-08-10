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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Create New Account btn'))

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_First_legalFirstName'), PtFirstName)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Last_lastName'), PtLastName)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Country Code_mobile'), PtMobile)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Email_email'), PtMailid)

//WebUI.sendKeys(findTestObject('Object Repository/Page_Patient Portal/input_DOB_flex h-10 w-full rounded-md px-3 _017e9b_1'), PtDOB)
//
//WebUI.clearText(findTestObject('Object Repository/Page_Patient Portal/input_DOB_flex h-10 w-full rounded-md px-3 _017e9b_1'))

TestObject dobField = findTestObject(
	'Object Repository/Page_Patient Portal/input_DOB_flex h-10 w-full rounded-md px-3 _017e9b_1'
)

WebUI.sendKeys(dobField, PtDOB)

//WebUI.click(dobField)
//WebUI.sendKeys(
//	dobField,
//	org.openqa.selenium.Keys.chord(
//		org.openqa.selenium.Keys.CONTROL, 'A'
//	)
//)
//WebUI.sendKeys(
//	dobField,
//	org.openqa.selenium.Keys.chord(
//		org.openqa.selenium.Keys.CONTROL, 'A'
//	),
//	org.openqa.selenium.Keys.chord(
//		
//		org.openqa.selenium.Keys.BACK_SPACE,
//	)
//)
//
//WebUI.verifyEqual(
//	WebUI.getAttribute(dobField, 'value'),
//	''
//)

TestObject closeBtnOnCal = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/button_CLOSE')

if (WebUI.verifyElementPresent(closeBtnOnCal, 3, FailureHandling.OPTIONAL)) {
		WebUI.click(closeBtnOnCal)
	}

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_First_legalFirstName'))
	
WebUI.waitForElementClickable((findTestObject('Object Repository/Page_Patient Portal/div_Create New Account_container-fluid d-fl_3cce60')), 10)
	
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Create New Account_container-fluid d-fl_3cce60'))
