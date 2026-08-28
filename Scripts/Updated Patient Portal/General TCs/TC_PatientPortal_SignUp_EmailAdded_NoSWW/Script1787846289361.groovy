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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.*
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement

//Login to Maximeyes using QA_User
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

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

WebUI.delay(3)

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Send Sign Up Email to radio button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Verify SWW not displayed 
CustomKeywords.'common.ToastHelper.verifySWWToastNotDisplayed'('Something went wrong')


//Create Random Patient
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : "",
	],
	FailureHandling.STOP_ON_FAILURE
)
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Print Sign Up Instructions radio button
WebUI.click(findTestObject('Object Repository/Scenario Update1703/Page_MaximEyes/span_Print Sign Up Instructions'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Verify SWW not displayed
//CustomKeywords.'common.ToastHelper.verifySWWToastNotDisplayed'('Something went wrong')

//Click on close button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Close Button SOC'))

//Verify SWW not displayed
CustomKeywords.'common.ToastHelper.verifySWWToastNotDisplayed'('Something went wrong')


//Create Random Patient
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : "",
	],
	FailureHandling.STOP_ON_FAILURE
)
//Wait For Busy Indicator to invisible
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Click on Mega Menu
WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_dropdown-toggle menu-large recentmodule_1'))

//Click on Patient Details
WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_Patient Details'))

// Dropdown
TestObject ddlObj = new TestObject().addProperty(
	"xpath",
	ConditionType.EQUALS,
	"(//select[starts-with(@id,'PR_DdlPhoneType_')])[2]"
)

WebElement ddl = WebUiCommonHelper.findWebElement(ddlObj, 10)

// Set Email in hidden select
WebUI.executeJavaScript("""
    arguments[0].value = 'Email';
    arguments[0].dispatchEvent(new Event('change', {bubbles:true}));
""", [ddl])

WebUI.delay(3)

// Textbox
TestObject txtObj = new TestObject().addProperty(
	"xpath",
	ConditionType.EQUALS,
	"(//input[starts-with(@id,'PR_EMAIL_')])[1]"
)

WebElement txt = WebUiCommonHelper.findWebElement(txtObj, 10)

// Enter email
WebUI.setText(txtObj, GlobalVariable.MyEmail_Id)
WebUI.sendKeys(txtObj, Keys.chord(Keys.TAB))

//Click on Mega Menu
WebUI.click(findTestObject('USCDI/Page_MaximEyes/a_dropdown-toggle menu-large recentmodule_1'))

//Click on Patient Details
WebUI.click(findTestObject('USCDI/Page_MaximEyes/Overview'))

WebUI.delay(3)

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Send Sign Up Email to radio button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Verify SWW not displayed
CustomKeywords.'common.ToastHelper.verifySWWToastNotDisplayed'('Something went wrong')
