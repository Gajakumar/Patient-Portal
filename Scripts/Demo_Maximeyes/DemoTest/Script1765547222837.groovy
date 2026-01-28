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
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import java.time.ZonedDateTime as ZonedDateTime
import java.time.ZoneId as ZoneId
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter



WebUI.setEncryptedText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5'), 
    'w8atsD+HWGA=')

WebUI.setEncryptedText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_2c0f9a_1'), 
    'cvW8qx4B2o3J/qo+fRAOjg==')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Confirm'))

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_New Password Matches with last 3 Passwords'), 
    'New Password Matches with last 3 Passwords.')

WebUI.setEncryptedText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5_11'), 
    'cvW8qx4B2o2oc6lZtTuXDw==')

WebUI.setEncryptedText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_f51da5_10'), 
    'cvW8qx4B2o3J/qo+fRAOjg==')

WebUI.setEncryptedText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_2c0f9a_2'), 
    'cvW8qx4B2o2oc6lZtTuXDw==')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Confirm'))

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/div_Password reset successfully'), 
    'Password reset successfully')

WebUI.verifyElementText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Sign In'), 
    'Sign In')

