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

//Login to Maximeyes using QA_User
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

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

// Not clickable (if no email)
TestObject radioBtn = findTestObject('Scenario Update1703/Page_MaximEyes/span_Send Sign Up Email to')

// Verify radio button is disabled
WebUI.verifyElementHasAttribute(radioBtn, "disabled", 5)


//Click on Edit email address
WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/span_LinkEdiEmailAddressPP'))

//Verify Header
WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/div_Primary Email Address'),
	'PRIMARY EMAIL ADDRESS')

//click on save button
WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_btnSavePatientEmailIdPP'))

//Verify toast
WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/div_Email is not valid'),
	'Email is not valid.')

//Enter invalid email
WebUI.setText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_txtPatPrimEmailPP'), '1234')

//click on save button
WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_btnSavePatientEmailIdPP'))

//Verify toast
WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/div_Email is not valid'),
	'Email is not valid.')

//Enter valid email
WebUI.setText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_txtPatPrimEmailPP'), GlobalVariable.MyEmail_Id)

//click on save but
WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/input_btnSavePatientEmailIdPP'))

//Verify email added
WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/label_lblPatientEmailPP'),
	"<" + GlobalVariable.MyEmail_Id + ">")

//Select Send Sign Up Email to radio button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait unitil busy indicator is not visible
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg "Patient Portal Sign Up Completed. Email Sent."
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'),'Patient Portal Sign Up Completed. Email Sent.')

//Verify email id at overview page
WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_Patient Portal/Page_MaximEyes/span_lblDashboardEmail'),
	GlobalVariable.MyEmail_Id)


