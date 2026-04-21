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
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import java.util.Arrays
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType

//Login to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Sign in With User Name and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : UserName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

// OTP Verification
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

// Click the Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Sent Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))
WebUI.delay(2)

//Verify No Message displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Sent Messages_text-lg mt-2'),
	'You have no messages in sent messages')

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Inbox
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_px-4 py-2 hoverbg-gray-10_0f01e2'))


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
	5)

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
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/textarea_Message For Doctor_form-control mt_4ab4b2'),DrMessage)

//Click on Send Button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/button_Send_Msg'))


TestObject messageHeader = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/h2_Select a Message_text-4xl font-semibold _a3c113')

// Wait until element is visible
WebUI.waitForElementVisible(messageHeader, 20)

// Verify text
WebUI.verifyElementText(messageHeader, 'Message Sent')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/p_Message Sent_text-sm text-gray-500 mt-2'),
	'Your message has been sent successfully.')
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/svg_Select a Message_Layer_1'),
	0)

//Click on + Icon to compose message
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))


// =====================================================
// 🔹 TEST OBJECT DECLARATIONS
// =====================================================
def btnPlusIcon        = findTestObject('Object Repository/Page_Patient Portal/Compose Button')
def composeScreen     = findTestObject('Object Repository/Page_Patient Portal/h1_Inbox_text-xl font-semibold text-gray-900 mr-4')
def inputSubject      = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_f186a3_5')
def attachmentIcon    = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attachment Icon')
def fileUploadInput   = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attach File Input')
def toastMessage      = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Messages - Toasts')

WebUI.click(btnPlusIcon)
WebUI.verifyElementVisible(composeScreen)

String projectDir = RunConfiguration.getProjectDir()
File baseDir = new File(projectDir, 'Include/Files/TestFiles')


def uploadFileTestCloud(TestObject uploadObj, File baseDir, String fileName) {
	
		assert uploadObj != null : '❌ Upload input TestObject is NULL'
	
		File fileToUpload = new File(baseDir, fileName)
		assert fileToUpload.exists() && fileToUpload.isFile() :
				"❌ Upload file not found: ${fileToUpload.absolutePath}"
	
		println "☁ TestCloud uploading: ${fileToUpload.absolutePath}"
	
		CustomKeywords.'com.katalon.testcloud.FileExecutor.uploadFileToWeb'(
			uploadObj,
			fileToUpload.absolutePath
		)
	}

//Upload Invalid File
uploadFileTestCloud(fileUploadInput, baseDir, 'invalid.csv')

WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(
	toastMessage,
	'Invalid File Format of invalid.csv'
)

WebUI.delay(5)

//upload File size exceeds 25 MB
uploadFileTestCloud(fileUploadInput, baseDir, 'oversize_single_26MB.pdf')

WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(
	toastMessage,'The attachment size exceeds the allowable limit. Maximum size of all attachments allowed is 25 MB.'
//	'Total attachment size cannot exceed 25MB. Current size: 0.00MB, New files size: 26.00MB.'
)

WebUI.delay(5)

//Upload zero bit file

uploadFileTestCloud(fileUploadInput, baseDir, 'zeroByte.txt')

WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(
	toastMessage, 'Attached file must be greater than 0 bytes!'
//	'Cannot attach empty file: zeroByte.txt'
)

WebUI.delay(5)

//Maximum 5 attachments

['file1.jpg','file2.jpg', 'file3.jpg', 'file4.jpg', 'file5.jpg', 'file6.jpg'].each { fileName ->
	uploadFileTestCloud(fileUploadInput, baseDir, fileName)
}

WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(
	toastMessage, 'Maximum 5 attachments are allowed'
//	'You can only attach a maximum of 5 files. Currently you have 5 file(s) attached.'
)

WebUI.delay(5)

//Duplicated file validation
uploadFileTestCloud(fileUploadInput, baseDir, 'file1.jpg')

WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(
	toastMessage, 'This file has been already uploaded'

)

WebUI.delay(5)

// =====================================================
KeywordUtil.markPassed('✔ All attachment validations completed successfully')
// =====================================================

def verifyNormalizedText(def testObject, String expectedText) {
    WebUI.verifyMatch(
        WebUI.getText(testObject)
             .replaceAll("\\s+", "")
             .trim(),
        expectedText
             .replaceAll("\\s+", "")
             .trim(),
        false
    )
}

//Verify Attched File is displayed
verifyNormalizedText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Attachments_bg-gray-100 text-xs smtext-_e859e9'),
	'file1.jpg200.0 KB✕')

//Verify Attched File is displayed
verifyNormalizedText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div__bg-gray-100 text-xs smtext-sm px-2 py-_ca8324'),
	'file2.jpg200.0 KB✕')

//Verify Attched File is displayed
verifyNormalizedText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div__bg-gray-100 text-xs smtext-sm px-2 py-_ca8324_1'),
	'file3.jpg200.0 KB✕')

//Verify Attched File is displayed
verifyNormalizedText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div__bg-gray-100 text-xs smtext-sm px-2 py-_ca8324_2'),
	'file4.jpg200.0 KB✕')

//Verify Attched File is displayed
verifyNormalizedText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div__bg-gray-100 text-xs smtext-sm px-2 py-_ca8324_3'),
	'file5.jpg200.0 KB✕')

//Delete any file from attachment
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Delete Attachment'))

//Verify deleted file is removed from attachment
verifyNormalizedText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Attachments_flex flex-wrap gap-2'),
	'file1.jpg200.0 KB✕file2.jpg200.0 KB✕file3.jpg200.0 KB✕file4.jpg200.0 KB✕')

//delete some more files 
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_1'))

//Verify deleted file is removed from attachment
verifyNormalizedText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Attachments_flex flex-wrap gap-2_1'),
	'file1.jpg200.0 KB✕file3.jpg200.0 KB✕file4.jpg200.0 KB✕')


//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Click on + Icon to compose message
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))

//Verify Compose screen is display
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Patient Portal/h1_Inbox_text-xl font-semibold text-gray-900 mr-4'),
	5)

//Add Subject as "Demo1"
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_f186a3_5'),Subject)

//Add Message for Doctor
WebUI.setText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/textarea_Message For Doctor_form-control mt_4ab4b2'),DrMessage)

 //Upload file
uploadFileTestCloud(fileUploadInput, baseDir, 'file1.jpg')

//Get the date and time before click on send button
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")

def nowGMT = ZonedDateTime.now(ZoneId.of("GMT"))

def expectedTimeGMT = ( -3..3 ).collect {
	nowGMT.plusMinutes(it).format(formatter).toUpperCase()
}

//Click on Send Button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/button_Send_Msg'))

//wait for loader to invisible
WebUI.waitForElementNotVisible(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Loader'), 10)

//Verify Message sent screen is displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/h2_Select a Message_text-4xl font-semibold _a3c113'),
	'Message Sent')

WebUI.delay(2)

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Sent Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))

//------------------ Verify First Message is READ ------------------//

// Title should NOT be bold
TestObject messageTitle = new TestObject().addProperty("xpath", ConditionType.EQUALS,
"(//section[@aria-label='Messages list']//div[contains(@class,'border-b')])[1]//span[contains(@class,'text-sm')]")

String classAttr = WebUI.getAttribute(messageTitle, "class")

assert !classAttr.contains("font-semibold") : "❌ First message is UNREAD (bold text found)"
println("✅ First message title is normal (READ)")


// Blue dot should NOT be present (FIXED INDEX = 1)
TestObject firstMessageBlueDot = new TestObject().addProperty("xpath", ConditionType.EQUALS,
"(//section[@aria-label='Messages list']//div[contains(@class,'border-b')])[1]//div[contains(@style,'rgb(5, 79, 141)')]")

List<WebElement> dots = WebUI.findWebElements(firstMessageBlueDot, 5)

assert dots.isEmpty() : "❌ First message has blue dot (UNREAD)"
println("✅ First message has NO blue dot (READ)")

//Verify To : Test is displayed at left pane
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/span_Sent Messages_text-sm font-semibold te_dc5d8b'),
	'To: test')

//Verify Subject is displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_To test_text-sm font-medium text-gray-700_7dcd2d'),
	Subject)

//Verify Attachment icon is displayed
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_To test_a'),
	0)

//Click on 1st displayed sent message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_px-3 py-3 border-b border_1b73e7'))

//Verify Patient Name is displayed at top
WebUI.verifyElementText(
	findTestObject('Object Repository/Page_Patient Portal/Pt Name on Msg'),PatientName
)

//Verify Date and time is displayed of sent message
// Get actual UI time
String actualTime = WebUI.getText(findTestObject(
	'Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_PT_text-sm text-gray-500'
)).trim().toUpperCase()

println "Expected Time Range: " + expectedTimeGMT
println "Actual UI Time: " + actualTime

assert actualTime in expectedTimeGMT :
	"❌ Time mismatch. Actual: ${actualTime}, Expected Range: ${expectedTimeGMT}"


//Verify Doctor Message is displayed
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/DrMsg'), DrMessage)

//Verify Attchment is displayed
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Download Attchment'),
	5)

//Click on Download attachment
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Download Attchment'))



//Select checkbox for first message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_w-4 h-4 border-2 border-g_15d988'))

//Select checkbox for second  message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_To test_w-4 h-4 border-2 border-gray-40_1b2e97'))

//Verify 2 selected at the top
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/span_DS_font-semibold text-lg cursor-pointer'),
	'2 selected')

//Mouse hover on Archive icon
WebUI.mouseOver(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_DS_text-light'))

//Click on Archive icon
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_DS_text-light'))

//Verify are you sure you want to archive the selected messages? popup displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Forward_text-lg mb-6 text-center max-w-xs'),
	'Are you sure you want to archive the selected messages?')

//Click on Cancel button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Archive_px-8 py-2 rounded bg-gray-50_22ec44'))

// Again Click on Archive icon
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_DS_text-light'))

//Click on okay button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Forward_px-8 py-2 rounded bg-red-600_662017'))

//Verify toast
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Message(s) archived successfully!')

//Click on three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Sent Messages_text-light'))

//Select Archive message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Archived Messages'))

//Verify both Archived messages are displayed here
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_To test_text-sm  text-gray-700 truncate m_15a22c'),
	'Demo2')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_To test_text-sm  text-gray-700 truncate m_15a22c_1'),
	'Demo1')

//Open first message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Archived Messages_px-3 py-3 border-b bo_650fe8'))

//Verify patient name is displayed
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/Pt Name on Msg'),PatientName)

//Verify Dr message is displayed correctly
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/DrMsg'),DrMessage)

//verify attachment is present
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Download Attchment'),
	5)

//Select checkbox for first message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_w-4 h-4 border-2 border-g_15d988'))

//Select checkbox for second message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_To test_w-4 h-4 border-2 border-gray-40_1b2e97'))

//Verify 2 selected at the top
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/span_DS_font-semibold text-lg cursor-pointer'),
	'2 selected')

//Click on Unarchive icon
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_DS_text-light'))

//Verify popup displayed as Are you sure you want to unarchive the selected messages?
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Forward_text-lg mb-6 text-center max-w-xs_1'),
	'Are you sure you want to unarchive the selected messages?')

//Click on Cancel button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Archive_px-8 py-2 rounded bg-gray-50_22ec44'))

//Click on Unarchive icon
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_DS_text-light'))

//Click on Yes button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Forward_px-8 py-2 rounded bg-red-600_662017_1'))

//Verify toast is displayed as Message(s) unarchived successfully!
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Message(s) unarchived successfully!')

//Click on Three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Archived Messages_text-light'))

//Click on Sent Messages
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Message Archived'))

//Verify both Unarchived messages are displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_To test_text-sm  text-gray-700 truncate m_15a22c'),
	'Demo2')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_To test_text-sm  text-gray-700 truncate m_15a22c_1'),
	'Demo1')

//click 
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_px-3 py-3 border-b border_1b73e7'))

//Click on archive msg
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Demo2_text-primary'))

//Verify confirmation msg is displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Forward_text-lg mb-6 text-center max-w-xs'),
	'Are you sure you want to archive the selected messages?')

//Click on Yes button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Forward_px-8 py-2 rounded bg-red-600_662017'))

//Verify toast
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Message(s) archived successfully!')

//Click on Three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Sent Messages_text-light'))

//Click on Archived Messages
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Inbox_px-4 py-2 hoverbg-gray-100 cursor_acd0eb'))

//Select Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Archived Messages_px-3 py-3 border-b bo_650fe8_1'))

//Click on Unarchived button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Demo2_text-primary'))

//Verify popup displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Forward_text-lg mb-6 text-center max-w-xs_1'),
	'Are you sure you want to unarchive the selected messages?')

//Click on Yes button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Forward_px-8 py-2 rounded bg-red-600_662017_1'))

//Verify toast is displayed as Message(s) unarchived successfully!
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Message(s) unarchived successfully!')

//Verify No Messages displayed in Archived
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Sent Messages_text-lg mt-2'),
	'You have no messages in archived messages')

//Click on Three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Archived Messages_text-light'))

//Click on Sent Messages
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Inbox_px-4 py-2 hoverbg-gray-100 cursor_acd0eb_1'))

//Verify Message displayed in Sent Messages
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_To test_text-sm  text-gray-700 truncate m_15a22c'),
	'Demo2')

//===========================================================================

//Click on 1st sent message from left pane
WebUI.click(findTestObject('Object Repository/Scenario Update1703/Message Pt Portal/Page_Patient Portal/Sent Msg first one'))

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
WebUI.verifyElementText(findTestObject('Object Repository/Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_No Attachments'), 'No Attachments')

//Add doctors message
WebUI.setText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/textarea_On Apr 8, 2026 at 1_49 PM, David Smith'),
	'I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve., Message For Doctor')

//Upload attachment
 uploadFileTestCloud(fileUploadInput, baseDir, 'InsCard.jpg')

//Verify Attchment
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/span_InsCard.png'), 'InsCard.jpg')

//Click on send button
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/button_Send'))

//Verify messgae sent
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/h2_Message Sent'), 'Message Sent')

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Sent Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))


//Verify sent message displayed in sent box
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/p_Re_ Demo2'), 'Re: Demo2')

////click on that messgae
//WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/p_Re_ Demo2'))

//Verify doctor message
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_On Apr 8, 2026 at 1_49 PM, David Smith wrote'),
	'I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve., Message For Doctor')

//Verify attachment is present
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/span_InsCard.png'), 'InsCard.jpg')

//----------------------------------------------------------
//Click on any sent message from left pane
WebUI.click(findTestObject('Object Repository/Scenario Update1703/Message Pt Portal/Page_Patient Portal/2nd msg from left pan'))

//Click on Replay arrow at top
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_tooltip530613'))


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
WebUI.verifyElementText(findTestObject('Object Repository/Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_No Attachments'), 'No Attachments')

//Add doctors message
WebUI.setText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/textarea_On Apr 8, 2026 at 1_49 PM, David Smith'),
	'I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve., Message For Doctor')

//Upload attachment
 uploadFileTestCloud(fileUploadInput, baseDir, 'InsCard.jpg')

//Verify Attchment
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/span_InsCard.png'), 'InsCard.jpg')

//Click on send button
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/button_Send'))

//Verify messgae sent
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/h2_Message Sent'), 'Message Sent')

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Sent Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))

//Verify sent message displayed in sent box
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/p_Re_ Demo2'), 'Re: Demo2')

//click on that messgae
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/p_Re_ Demo2'))

//Verify doctor message
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_On Apr 8, 2026 at 1_49 PM, David Smith wrote'),
	'I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve., Message For Doctor')

//Verify attachment is present
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/span_InsCard.png'), 'InsCard.jpg')


//============================================================================

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

//Verify Doctors Message
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

//upload File size exceeds 25 MB
uploadFileTestCloud(fileUploadInput, baseDir, 'oversize_single_26MB.pdf')

WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(
	toastMessage,'The attachment size exceeds the allowable limit. Maximum size of all attachments allowed is 25 MB.'
//	'Total attachment size cannot exceed 25MB. Current size: 0.00MB, New files size: 26.00MB.'
)

WebUI.delay(5)


//Maximum 5 attachments

['file2.jpg', 'file3.jpg', 'file4.jpg', 'file5.jpg', 'file6.jpg'].each { fileName ->
	uploadFileTestCloud(fileUploadInput, baseDir, fileName)
}

WebUI.waitForElementVisible(toastMessage, 5)
WebUI.verifyElementText(
	toastMessage, 'Maximum 5 attachments are allowed'
//	'You can only attach a maximum of 5 files. Currently you have 5 file(s) attached.'
)

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

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Sent Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))

//Verify email received
CustomKeywords.'common.ForwardEmailVerification.verifyEmail'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Fwd: Demo2',

	// Body keywords (IMPORTANT)
	[
		"Dr Mary Smith",
		"Ref letter from Dr Steve"
	],

	// Attachment (optional)
	"file1.jpg"
)

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
        "innerText"
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

//verify toast msg
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Please enter a recipient email address.')

//Enter enmail id
WebUI.setText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/input_Enter Text_2'),
	'gajakumara@first-insight.com')

//Click on send button
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/button_Send'))

//Verify message sent
WebUI.verifyElementText(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/h2_Message Sent'),
	'Message Sent')

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on Message Icon on Dashboard
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/div_Request New Appointment_border-2 rounde_c23dec'))

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Sent Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))

//Verify email received
CustomKeywords.'common.ForwardEmailVerification.verifyEmail'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Fwd: Demo2',

	// Body keywords (IMPORTANT)
	[
		"Dr Mary Smith",
		"Ref letter from Dr Steve"
	],

	// Attachment (optional)
	"file1.jpg"
)

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
        "innerText"
    ).replaceAll("\\s+", " "),

    ".*Forwarded message.*From: David Smith.*Subject: Demo2.*I have taken appointment for my son with Dr Mary Smith.*Ref letter from Dr Steve.*",

    true
)

//Verify attachment
WebUI.verifyElementPresent(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/button_file1.jpg'),
	5)




//=============================================================================

//Select checkbox for first message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_w-4 h-4 border-2 border-g_15d988'))

//Select checkbox for second  message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_To test_w-4 h-4 border-2 border-gray-40_1b2e97'))

//Select checkbox for 3rd message
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_w-4 h-4 border-2 border-gray-400 rounded fle'))

//Select checkbox for 4th message
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Page_Patient Portal/div_w-4 h-4 border-2 border-gray-400 rounded fle_1'))

//select 5th message
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_w-4 h-4 border-2 border-gray-400 rounded fle'))

//select 6th message
WebUI.click(findTestObject('Scenario Update1703/Message Pt Portal/Fwd Message/Page_Patient Portal/div_w-4 h-4 border-2 border-gray-400 rounded fle_1'))

//Verify 2 selected at the top
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/span_DS_font-semibold text-lg cursor-pointer'),
	'6 selected')


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
CustomKeywords.'common.ToastHelper.verifyToastMessage'('Message(s) deleted successfully!')

//Verify toast message is displyed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Sent Messages_text-lg mt-2'),
	'You have no messages in sent messages')
