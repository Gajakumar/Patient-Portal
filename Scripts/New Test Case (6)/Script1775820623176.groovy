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

//Click on setting icon
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/div_w-10 h-10 flex items-center justify-center r'))

//click on opt out
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/span_Opt Out'))

////Verify patient name and date
//WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/p_Srwzqm Hydvkolh _ 04_10_2026'), 'Srwzqm Hydvkolh | 04/10/2026')

//Verify patient name and date
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Patient name and todays date'),[:], FailureHandling.STOP_ON_FAILURE)

//Click on make my account inactive
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/button_Make my account inactive'))

//Verify toast
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/div_1_1'), 'Please accept the Terms of Service')

//check check box for accept terms
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/input_I Accept'))

//Click on make my account inactive
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/div_Make my account inactive'))

//Verify toast
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/div_1_1'), 'Please accept the Terms of Service')

//Uncheck check box for accept terms
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/input_I Accept'))

////Add signature
//WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/canvas_signature-canvas'))

//Add Signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Add Signature On Canvas'),[:], FailureHandling.STOP_ON_FAILURE)

//Click on make my account inactive
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/button_Make my account inactive'))

//Verify toast
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/div_1_1'), 'Please accept the Terms of Service')

//check check box for accept terms
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/input_I Accept'))

//Click on make my account inactive
WebUI.click(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/button_Make my account inactive'))

//Verify text after opt out
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/h4_You will be missed'), 'You will be missed')
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/p_You have successfully opted out from Patient P'), 
    'You have successfully opted out from Patient Portal.')
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/p_Were sad to see you go'), 'We\'re sad to see you go.')
WebUI.verifyElementText(findTestObject('PatientPortal/Opt Out/Page_Patient Portal/h4_Opted Out'), 'Opted Out')

