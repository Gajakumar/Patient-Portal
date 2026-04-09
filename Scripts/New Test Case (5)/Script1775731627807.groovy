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

//Click on any sent msg from left
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_Demo2'))

//Click on Forward button
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/span_Forward'))

//Verify to field
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/label_labelText'), 
    'To:')


//verify subject
WebUI.verifyMatch(
	WebUI.getAttribute(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/input_Enter Text'), "value"),
	"Fwd: Demo2",
	false
)

//vERIFY DOCTORS MESSAGE
WebUI.verifyMatch(
    WebUI.getAttribute(
        findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/textarea_Forwarded message -From_ David SmithDat'),
        "value"
    ).replaceAll("\\s+", " "),

    ".*Forwarded message.*From: David Smith.*Subject: Demo2.*I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve.*",

    true
)

//Verify attachment is present
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_file1.jpg200.0 KB'), 
    5)

//Enter invalid mail in To field
WebUI.setText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/input_Enter Text_1'), 
    'ABCX')


//Verify validation msg
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/span_Please enter a valid email address'), 
    'Please enter a valid email address.')

//Enter enmail id
WebUI.setText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/input_Enter Text_2'), 
    'gajakumara@first-insight.com')

//Click on send button
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/button_Send'))

//Verify message sent
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/h2_Message Sent'), 
    'Message Sent')


//Verify sent message in left pane
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/span_To_ gajakumarafirst-insight.com'), 
    'To: gajakumara@first-insight.com')

//Verify subject
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_Fwd_ Demo2'), 
    'Fwd: Demo2')

//Click on that message
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_Fwd_ Demo2'))

//Verify correct mail id is displayed
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_To_ gajakumarafirst-insight.com'), 
    'To: gajakumara@first-insight.com')

//Verify doctors message
WebUI.verifyMatch(
	WebUI.getAttribute(
		findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_Forwarded message -From_ David SmithDate_ Ap'),
		"value"
	).replaceAll("\\s+", " "),

	".*Forwarded message.*From: David Smith.*Subject: Demo2.*I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve.*",

	true
)

//Verify attachment
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/button_file1.jpg'), 
    5)



//===================================

//Click on any message
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_Demo2_1'))

//Click on  forword arrow at top
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/button_Forward'))

//Click on send button
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/button_Send'))

//Verify validation popup
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_1'), 'Please enter a recipient email address.')

//Enter enmail id
WebUI.setText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/input_Enter Text_2'),
	'gajakumara@first-insight.com')

//Click on send button
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/button_Send'))

//Verify message sent
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/h2_Message Sent'),
	'Message Sent')


//Verify sent message in left pane
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/span_To_ gajakumarafirst-insight.com'),
	'To: gajakumara@first-insight.com')

//Verify subject
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_Fwd_ Demo2'),
	'Fwd: Demo2')

//Click on that message
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_Fwd_ Demo2'))

//Verify correct mail id is displayed
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/p_To_ gajakumarafirst-insight.com'),
	'To: gajakumara@first-insight.com')

//Verify doctors message
WebUI.verifyMatch(
	WebUI.getAttribute(
		findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_Forwarded message -From_ David SmithDate_ Ap'),
		"value"
	).replaceAll("\\s+", " "),

	".*Forwarded message.*From: David Smith.*Subject: Demo2.*I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve.*",

	true
)

//Verify attachment
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/button_file1.jpg'),
	5)

//select 5th message
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_w-4 h-4 border-2 border-gray-400 rounded fle'))

//select 6th message
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_w-4 h-4 border-2 border-gray-400 rounded fle_1'))

