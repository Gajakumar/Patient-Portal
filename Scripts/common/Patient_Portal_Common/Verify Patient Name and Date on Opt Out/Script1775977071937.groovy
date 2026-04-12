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

String today = LocalDate.now().format(DateTimeFormatter.ofPattern('MM/dd/yyyy'))

println('Today: ' + today)

String uiText = WebUI.getText(findTestObject('Object Repository/PatientPortal/Opt Out/Page_Patient Portal/Patient name and date on opt out'))

println('UI Text: ' + uiText)

String[] parts = uiText.split('\\|')

String namePart = (parts[0]).trim()

String datePart = (parts[1]).trim()

WebUI.verifyEqual(datePart, today)

String[] nameSplit = namePart.split(' ')

String uiFirstName = (nameSplit[0]).trim()

String uiLastName = (nameSplit[1]).trim()

println('UI First Name: ' + uiFirstName)

println('UI Last Name: ' + uiLastName)

WebUI.verifyEqual(uiFirstName, GlobalVariable.PatientFirstName, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyEqual(uiLastName, GlobalVariable.PatientLastName, FailureHandling.CONTINUE_ON_FAILURE)