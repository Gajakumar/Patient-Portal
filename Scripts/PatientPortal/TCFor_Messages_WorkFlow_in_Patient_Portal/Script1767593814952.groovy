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
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : UserName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)


String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp)


// Auto type into four input boxes
String[] digits = otp.toCharArray()

WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Verify No Messages in Inbox
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Inbox_text-lg mt-2'), 'You have no messages in inbox')

//Verify Tooltip of Message icon >> It should display "Show Unread"
WebUI.mouseOver(findTestObject('Object Repository/Page_Patient Portal/svg_Inbox_a'))

//Click on Inbox Message icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/svg_Inbox_a'))

//Verify no unread messages is displayed
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Inbox_text-lg mt-2_1'), 'You have no unread messages.')

//Mouse hover on + Icon
WebUI.mouseOver(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))

//Click on + Icon to compose message
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))

//Verify Compose screen is display
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Patient Portal/h1_Inbox_text-xl font-semibold text-gray-900 mr-4'),
	0)

//Mouse hover on Compose i icon
WebUI.mouseOver(findTestObject('Object Repository/Page_Patient Portal/svg_Compose_uuid-d1e71e09-949d-48ab-b7dc-b1_dbbb70'))

//Verify Note on Compose msg screen
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Compose_text-md text-red-600'), NoteOnComposeMsg)

//Verify Text fields are displayed Subject,Message For Doctor,Attachments
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/label_Note_labelText'), 'Subject:')
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/label'), 'Message For Doctor')
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/span_Message For Doctor_mr-2'), 'Attachments :')

//Click on Subject Field
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_f186a3'))

//Add Subject as "Demo1"
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_f186a3_5'),
	'Demo1')

//Add Message for Doctor
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/textarea_Message For Doctor_form-control mt_4ab4b2'),
	'I have taken appointment for my son with Dr Mary Smith. As discussed attached is Ref letter from Dr Steve')

//Click on Send Button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/button_Send_Msg'))

//Verify Message sent screen is displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/h2_Select a Message_text-4xl font-semibold _a3c113'),
	'Message Sent')
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/p_Message Sent_text-sm text-gray-500 mt-2'),
	'Your message has been sent successfully.')
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/svg_Select a Message_Layer_1'),
	0)

//Click on + Icon to compose message
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))

