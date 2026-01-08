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

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys

def randomFirstName = "Patient" + UUID.randomUUID().toString().take(5)
def randomLastName = "Test" + UUID.randomUUID().toString().take(5)

println "Random Patient: ${randomFirstName} ${randomLastName}"

GlobalVariable.PatientFirstName = randomFirstName
GlobalVariable.PatientLastName = randomLastName


WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Plus button to add new patient'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Add New - Patient'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Name_AddNew_Patient_FirstName (2)'), GlobalVariable.PatientFirstName)

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Name_AddNew_Patient_LastName (2)'), GlobalVariable.PatientLastName)

WebUI.delay(3)

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Primary_PhoneTypeID2'), '2', true)

WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Primary_PR_EMAIL_undefined (1)'), 'gajakumara@first-insight.com')

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/tbody_Primary_fixedGridTbody'))
WebUI.delay(2)

WebUI.click(findTestObject('Page_MaximEyes/input_DOB_DOBB'))

WebUI.setText(findTestObject('Page_MaximEyes/input_DOB_DOBB'), '03/16/1982')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Sex_AddNew_Patient_GenderID'),'M', true)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Preferred Facility_AddNewPatientPopup_adb9ef'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/td_POS_AddNewPatientPopupFacility_DDD_L_LBI0T0'))
WebUI.delay(2)
WebUI.selectOptionByLabel(
    findTestObject('Object Repository/Maximeyes_Portal_Mix/select_Preferred Provider_AddNew_Patient_Pr_ffc4bf'),
    'Patient Portal',
    false
)
WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Group   Group Name_btnSave (2)'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(3)

TestObject obj = findTestObject('Object Repository/Page_MaximEyes/input_CA_CreateNew')


if (WebUI.verifyElementPresent(obj, 5, FailureHandling.OPTIONAL) && WebUI.verifyElementVisible(obj, FailureHandling.OPTIONAL)) {
	WebUI.click(obj)
}

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(5)