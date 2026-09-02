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



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive Message Thread'), 
    0)



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Reply Message Thread'), 
    0)



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Delete Message Thread'), 
    0)


WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Print Education Material'), 
    0)



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Download PDF'), 0)



WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/p_Patient Portal'), 'Patient Portal', 
    0)



WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/span_Alcohol Use Disorder (AUD)'), 
    ' Alcohol Use Disorder (AUD)', 0)

//WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/a_https_medlineplus.gov_alcoholusedisorderaud.ht'))

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive Message Thread_1'))



WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/p_Are you sure you want to archive the selected'), 
    'Are you sure you want to archive the selected messages?', 0)

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive'))



WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/div_1'), 'Message(s) archived successfully!', 
    0)

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_tooltip'))

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_Archived Messages'))



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi'), 
    0)



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Unarchive Message Thread_1'), 
    0)



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Reply Message Thread_2'), 
    0)



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Delete Message Thread_2'), 
    0)



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Print Education Material'), 
    0)



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/button_Download PDF'), 0)

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Unarchive Message Thread_2'))



WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/p_Are you sure you want to archive the selected'), 
    'Are you sure you want to archive the selected messages?', 0)

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Archive'))



WebUI.assertElementText(findTestObject('Edu Material Verification/Page_Patient Portal/p_You have no messages in archived messages'), 
    'You have no messages in archived messages', 0)

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_tooltip_1'))

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_Inbox'))

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi_1'))



WebUI.assertElementPresent(findTestObject('Edu Material Verification/Page_Patient Portal/div_To_ Evarku FmtvsxtsMultiple Education Materi'), 
    0)

WebUI.click(findTestObject('Edu Material Verification/Page_Patient Portal/button_Download PDF'))

