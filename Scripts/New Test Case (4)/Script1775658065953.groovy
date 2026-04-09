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

//Verify Replay button is present
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/button_Reply'), 5)

//Verify forward button is present
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/button_Forward'), 5)

//verify Replay arrow is present
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/svg_a'), 5)

//Verify forward arrow is present
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/svg_a_1'), 5)

//Verify Archive button is present
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/path_icon'), 5)

//Click on Replay button at bottum
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/button_Reply'))

//verify subject
WebUI.verifyMatch(
    WebUI.getAttribute(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/input_Enter Text'), "value"),
    "Re: Demo2",
    false
)


//Verify Message For Doctor
WebUI.verifyMatch(
	WebUI.getText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/textarea_On Apr 8, 2026 at 1_49 PM, David Smith')),
	".*I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve.*",
	true
)

//Verify no attachment is displayed
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_Attachments _'), 'Attachments :')

//Add doctors message
WebUI.setText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/textarea_On Apr 8, 2026 at 1_49 PM, David Smith'), 
    'I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve., Message For Doctor')

//Upload attachment
//Upload Insurance Card Front
	uploadFileTestCloud(fileUploadInputFront, baseDir, 'InsCard.jpg')

//Verify Attchment
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/span_InsCard.png'), 'InsCard.png')

//Click on send button
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/button_Send'))

//Verify messgae sent
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/h2_Message Sent'), 'Message Sent')

//Verify sent message displayed in sent box
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/p_Re_ Demo2'), 'Re: Demo2')

//click on that messgae
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_To_ test02_06 PMRe_ Demo2'))

//Verify doctor message
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_On Apr 8, 2026 at 1_49 PM, David Smith wrote'), 
    'I have taken appointment for my son with Dr Mary Smith. As discussed attached is Ref letter from Dr Steve')

//Verify attachment is present
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/span_InsCard.png'), 'InsCard.png')

//Click on any sent message from left pane
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_To_ test01_49 PMDemo2'))

//Click on Replay arrow at top
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_tooltip530613'))

//select 3 & 4
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_w-4 h-4 border-2 border-gray-400 rounded fle'))

WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_w-4 h-4 border-2 border-gray-400 rounded fle_1'))

