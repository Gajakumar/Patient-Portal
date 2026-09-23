import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
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
Random randm = new Random()

int areaCode = 200 + randm.nextInt(800)
int prefix   = 200 + randm.nextInt(800)
int lineNum  = 1000 + randm.nextInt(9000)

String mobilePlain = "${areaCode}${prefix}${lineNum}"
String mobileFormatted = String.format("(%03d) %03d-%04d", areaCode, prefix, lineNum)

WebUI.assertElementText(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/h2_record-match-title'), 
    'Record Match Found', 0)

WebUI.assertElementText(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/p_We have found a similar matching record with u'), 
    'We have found a similar matching record with us but with different details. Did you enter details correctly?', 0)

WebUI.assertElementText(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/span_test test'), 'test test', 
    0)

WebUI.assertElementText(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/span__'), '*****/****', 0)

WebUI.assertElementText(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/span_22_22'), '22******22', 
    0)

WebUI.assertElementText(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/span__first-insight.com'), 
    '**********@first-insight.com', 0)

WebUI.assertElementPresent(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/button_Grant Access'), 
    0)

WebUI.assertElementText(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/h3_grant-access-title'), 
    'Do you want to grant access to test test?', 0)

WebUI.click(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/button_Grant Access'))

WebUI.assertElementText(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/h2_Access Granted'), 'Access Granted', 
    0)

WebUI.assertElementText(findTestObject('Authorized Individual/Record Match/Page_Patient Portal/p_test test can now access your records'), 
    'test test can now access your records!', 0)

