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


WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : UserName1, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

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
String basePath = 'C:\\TestFiles\\'

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
// 3) Hover attachment icon → Tooltip
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
def uploadFile(def uploadObj, String fileName) {
    WebUI.sendKeys(uploadObj, basePath + fileName)
}

// =====================================================
// 4a) Unsupported file format
// =====================================================
uploadFile('invalid.exe')
WebUI.verifyElementText(toastMessage,
        'Invalid File Format of invalid.exe')

// =====================================================
// 4b) File size exceeds 25 MB
// =====================================================
uploadFile('largeFile1.pdf')
uploadFile('largeFile2.pdf')
WebUI.verifyElementText(toastMessage,
        'Total attachment size cannot exceed 25MB. Current size: 0.00MB, New files size: 27.00MB.')

// =====================================================
// 4b ii) Zero-byte file
// =====================================================
uploadFile('zeroByte.txt')
WebUI.verifyElementText(toastMessage,
        'Cannot attach empty file: zeroByte.txt')

// =====================================================
// 4c) Maximum 5 attachments
// =====================================================
uploadFile('file1.jpg')
uploadFile('file2.jpg')
uploadFile('file3.jpg')
uploadFile('file4.jpg')
uploadFile('file5.jpg')
uploadFile('file6.jpg')
WebUI.verifyElementText(toastMessage,
        'You can only attach a maximum of 5 files. Currently you have 0 file(s) attached.')

//// =====================================================
//// 6) File name length > 15 chars
//// =====================================================
//uploadFile('longfilename_morethan15chars.pdf')
//WebUI.verifyElementText(toastMessage,
//        'File name should not exceed 15 characters')

// =====================================================
KeywordUtil.markPassed('✔ All attachment validations completed successfully')
// =====================================================
