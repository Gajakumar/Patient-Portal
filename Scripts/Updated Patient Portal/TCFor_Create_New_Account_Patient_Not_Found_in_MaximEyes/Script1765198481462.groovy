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
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement as WebElement

//Navigate to patient portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Create new account and add the data
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Create New Patient and Sign In'), [
		('PtFirstName') : PtFirstName,
		('PtLastName') : PtLastName,
		('PtMobile') : GlobalVariable.Mobile,
		('PtMailid') : GlobalVariable.MyEmail_Id,
		('PtDOB') : GlobalVariable.DOB
	], FailureHandling.STOP_ON_FAILURE)

//Verify no match found page opens
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/No_Match_Found'), ElementText)

//Verify text on page
WebUI.verifyElementText(findTestObject('PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/h2_Please review your information'),
	'Please review your information')

//Verify text on page
WebUI.verifyElementText(findTestObject('PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/p_Make sure you have entered the correct credent'),
	'Make sure you have entered the correct credentials.')

//Verify text on page
WebUI.verifyElementText(findTestObject('PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/p_Please contact practice for more information'),
	'Please contact practice for more information.')

//Verify text on page
WebUI.verifyElementText(findTestObject('PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/button_Try Again'),
	'Try Again')

//Click on Try Again
WebUI.click(findTestObject('PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/div_Try Again'))

//Verify Create New Account page opens
WebUI.verifyElementText(findTestObject('PatientPortal/SignInPage_Patient Portal/Page_Patient Portal/h1_Create New Account'),
	'Create New Account')

//Verify Create Account button is preasent
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Create New Account_container-fluid d-fl_3cce60'),
	'Proceed')