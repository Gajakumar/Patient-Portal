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

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_DOB_flex h-10 w-full rounded-md px-3 _017e9b_1'), PtDOB)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Create New Account_container-fluid d-fl_3cce60'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_1'))