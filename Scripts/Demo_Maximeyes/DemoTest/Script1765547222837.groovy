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

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Getting MaximEyes ready_mif-home font1_5ca900'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/a_Secure Messages'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Inbox_btnCompose'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To_mif-search font18 fg-skyblue'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Do you want to search Patients or Ext_4d59c0'))

WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_ADVANCED PATIENT FIND_LastName'), 
    'Test44fed')

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Is Active_button primary small-button'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_100740'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input__btnSendToPatientPortal'))

WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/input_Subject_ComposeSubject'), 'XML CCDA File')

WebUI.setText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/textarea_Message_input InboxTextarea font20 pad05'), 
    'Message to Patient including CCDA File')

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Compose_attachmentIconCompose'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_100739_Test.xml'), 
    0)

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/button_Send'))

WebUI.rightClick(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Email sent successfully'))

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Email sent successfully_1'), 
    'Email sent successfully')

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_Inbox_openmoreactionmenu'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_Sent Messages'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/td_To Patienteafce Test44fedXML CCDA File'))

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/span_To Patienteafce Test44fed'), 
    'To: Patienteafce Test44fed')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/p_Message to Patient including CCDA File'), 
    'Message to Patient including CCDA File')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_MaximEyes/div_XML CCDA File'), 'XML CCDA File')

WebUI.verifyElementText(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/span_1unread messages'), 
    '1unread messages')

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_1unread messages'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/div_To Patienteafce Test44fed1102 AMXML CCDA File'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/svg_Messages - Patient Portal_text-light'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/svg_Patienteafce Test44fed_text-primary'))

WebUI.click(findTestObject('Object Repository/Health Summary Section/Page_Patient Portal/span_Activity Log'))

