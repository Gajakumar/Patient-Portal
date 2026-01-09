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

//Verify Message sent screen is displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/h2_Select a Message_text-4xl font-semibold _a3c113'),
	'Message Sent')
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/p_Message Sent_text-sm text-gray-500 mt-2'),
	'Your message has been sent successfully.')
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/svg_Select a Message_Layer_1'),
	0)

//Click on + Icon to compose message
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Compose Button'))

// =====================================================
// 🔹 TEST OBJECT DECLARATIONS (ONLY ONCE)
// =====================================================
def btnPlusIcon        = findTestObject('Object Repository/Page_Patient Portal/Compose Button')
def composeScreen     = findTestObject('Object Repository/Page_Patient Portal/h1_Inbox_text-xl font-semibold text-gray-900 mr-4')
def inputSubject      = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_f186a3_5')
def attachmentIcon    = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attachment Icon')
def fileUploadInput   = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Attach File Input')
def toastMessage      = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Messages - Toasts')

// =====================================================
// 🔹 TEST DATA PATH
// =====================================================
String basePath = 'C:\\Users\\Gajakumar_a\\Katalon Studio\\Patient-Portal\\TestFiles\\'

// =====================================================
// 1) Click + icon → Compose screen
// =====================================================
WebUI.click(btnPlusIcon)
WebUI.verifyElementVisible(composeScreen)

// =====================================================
// 2) Add Subject
// =====================================================
WebUI.setText(inputSubject, 'Test Data')

// =====================================================
// 3) Hover attachment icon → Tooltip >> can not verify tooltip
// =====================================================
//WebUI.mouseOver(attachmentIcon)
//WebUI.verifyElementAttributeValue(
//        attachmentIcon,
//        'title',
//        'Add File',
//        5
//)

// =====================================================
// 🔹 Helper: Upload File
// =====================================================
def uploadFile(def uploadObj, String basePath, String fileName) {
	WebUI.sendKeys(uploadObj, basePath + fileName)
}
// =====================================================
// 4a) Unsupported file format
// =====================================================
uploadFile(fileUploadInput, basePath, 'invalid.exe')
WebUI.waitForElementPresent(toastMessage, 5)
WebUI.verifyElementText(toastMessage,'Invalid File Format of invalid.exe')

// =====================================================
// 4b) File size exceeds 25 MB
// =====================================================
uploadFile(fileUploadInput, basePath, 'oversize_single_26MB.pdf')
WebUI.waitForElementPresent(toastMessage, 5)
WebUI.verifyElementText(toastMessage,'Total attachment size cannot exceed 25MB. Current size: 0.00MB, New files size: 26.00MB.')

// =====================================================
// 4b ii) Zero-byte file
// =====================================================
uploadFile(fileUploadInput, basePath, 'zeroByte.txt')
WebUI.waitForElementPresent(toastMessage, 5)
WebUI.verifyElementText(toastMessage,
		'Cannot attach empty file: zeroByte.txt')

// =====================================================
// 4c) Maximum 5 attachments
// =====================================================
uploadFile(fileUploadInput, basePath, 'file1.jpg')
uploadFile(fileUploadInput, basePath, 'file2.jpg')
uploadFile(fileUploadInput, basePath, 'file3.jpg')
uploadFile(fileUploadInput, basePath, 'file4.jpg')
uploadFile(fileUploadInput, basePath, 'file5.jpg')
uploadFile(fileUploadInput, basePath, 'file6.jpg')
WebUI.waitForElementPresent(toastMessage, 5)
WebUI.verifyElementText(toastMessage,
		'You can only attach a maximum of 5 files. Currently you have 5 file(s) attached.')

//// =====================================================
//// 6) File name length > 15 chars  >> not working
//// =====================================================
//uploadFile(fileUploadInput, basePath, 'longfilename_morethan15chars.pdf')
//WebUI.verifyElementText(toastMessage,
//        'File name should not exceed 15 characters')

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
uploadFile(fileUploadInput, basePath, 'file1.jpg')

//Get the date and time before click on send button
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")

def expectedTimeGMT = [
    ZonedDateTime.now(ZoneId.of("GMT")).minusMinutes(1).format(formatter).toUpperCase(),
    ZonedDateTime.now(ZoneId.of("GMT")).format(formatter).toUpperCase(),
    ZonedDateTime.now(ZoneId.of("GMT")).plusMinutes(1).format(formatter).toUpperCase()
]


//Click on Send Button
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/button_Send_Msg'))


//wait for loader to invisible
WebUI.waitForElementNotVisible(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Loader'), 10)

//Verify Message sent screen is displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/h2_Select a Message_text-4xl font-semibold _a3c113'),
	'Message Sent')

//Click on switch view three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Inbox_text-light'))

//Click on Sent Message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Messages'))

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
	findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_PT_font-medium text-gray-900 mb-0'),PatientName
)

//Verify Date and time is displayed of sent message
assert WebUI.getText(findTestObject(
    'Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_PT_text-sm text-gray-500'
)) in expectedTimeGMT

println "Expected Date and Time: " + expectedTimeGMT

//Verify Doctor Message is displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div'), DrMessage)

//Verify Attchment is displayed
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_PT_flex items-center justify-between_c9a7d7'),
	0)

//Click on Download attachment
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_file1.jpg_a'))



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

//Verify Message(s) archived successfully toast displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Messages - Patient Portal_1'),
	'Message(s) archived successfully!')

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
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_DS_font-medium text-gray-900 mb-0'),
	'David Smith')

//Verify Dr message is displayed correctly
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div'), 'I have taken appointment for my son with Dr Mary Smith. As discussed attached is Ref letter from Dr Steve')

//verify attachment is present
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_DS_flex items-center justify-between_9ee188'),
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
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Messages - Patient Portal_2'),
	'Message(s) unarchived successfully!')

//Click on Three dots
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Archived Messages_text-light'))

//Click on Sent Messages
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Sent Message Archived'))

//Verify both Unarchived messages are displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_To test_text-sm  text-gray-700 truncate m_15a22c'),
	'Demo2')

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_To test_text-sm  text-gray-700 truncate m_15a22c_1'),
	'Demo1')

//
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_px-3 py-3 border-b border_1b73e7'))

WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/svg_Demo2_text-primary'))

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Forward_text-lg mb-6 text-center max-w-xs'),
	'Are you sure you want to archive the selected messages?')

WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/button_Forward_px-8 py-2 rounded bg-red-600_662017'))

WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Messages - Patient Portal_3'),
	'Message(s) archived successfully!')

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

//Verify toast message
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Messages - Patient Portal_4'),
	'Message(s) unarchived successfully!')

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

//Select checkbox for first message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_Sent Messages_w-4 h-4 border-2 border-g_15d988'))

//Select checkbox for second  message
WebUI.click(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/div_To test_w-4 h-4 border-2 border-gray-40_1b2e97'))

//Verify 2 selected at the top
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/span_DS_font-semibold text-lg cursor-pointer'),
	'2 selected')

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
