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
import org.openqa.selenium.WebElement as WebElement
import java.util.Arrays as Arrays
import utils.CheckboxKeywords as CK


//Click on Delete button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_DS_a'))

//Verify delete popup is displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Select a Message_text-lg mb-6 text-center_8b71ba'), 
    'Are you sure you want to delete the selected messages? This action cannot be undone.')

//Click on Cancel button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Delete_px-8 py-2 rounded bg-gray-500_5566e9'))

//Click on Delete button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_DS_a'))

//Click on yes button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Select a Message_px-8 py-2 rounded b_18739d'))

//Verify toast message is displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Messages - Patient Portal_1'), 
    'Message(s) deleted successfully!')

//Verify toast message is displyed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Sent Messages_text-lg mt-2'), 
    'You have no messages in sent messages')
