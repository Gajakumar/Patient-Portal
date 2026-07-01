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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.time.*
import java.time.format.*
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import java.util.List
// =====================================================
// LOGIN TO MAXIMEYES
// =====================================================

WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

//Create Random Patient
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Send Sign Up Email to
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait until busy indicator invisible
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), 'Patient Portal Sign Up Completed. Email Sent.')

WebUI.delay(10)

//get Username & Password from email
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email, 'Access to your health data')

println('Username: ' + GlobalVariable.GV_Username)

println('Password: ' + GlobalVariable.GV_Password)


//Click on encounter dropdown
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

//Click on Create new encounter
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Actions_Encounters  Add New Encounter'))

//Select Encounter type as : Automation Element Test Encounter
WebUI.selectOptionByLabel(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Encounter Type_EncounterTypeID'),
	'Automation Element Test Encounter',
	false
)

//Click on Add button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))


TestObject createNewEncounterBtn =
		findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter')

//Verify Create new encounter button is displayed then click on it
if (WebUI.verifyElementPresent(createNewEncounterBtn, 5, FailureHandling.OPTIONAL)) {
	WebUI.click(createNewEncounterBtn)
	println('Create New Encounter button clicked')
} else {
	println('Create New Encounter button not displayed – skipping click')
}

WebUI.delay(2)

//Click on Cheif Compalaints
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Intake Form_encTabList_1'))

// =====================================================
// ADD FIRST PROBLEM
// =====================================================

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Add Problem Plus button'))

WebUI.setText(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Loading_DESCRIPTION_GF_b04f_GF_I'),
	'Alcohol abuse'
)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/em'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_B-1'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_b04f_GF_DDD_L_LBI0T0'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_B-1'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_b04f_GF_DDD_L_LBI0T0'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_--Select--_problemListGridView_b04f_E_114e19'))

WebUI.delay(2)

TestObject popup =
		findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Health Information Resource_title')

TestObject eduMaterial = findTestObject(
		'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_EHR_EducationMaterial',
		['Desc': 'Alcohol abuse']
)

WebUI.waitForElementPresent(eduMaterial, 20)

int maxAttempts = 3

for (int i = 1; i <= maxAttempts; i++) {

	WebUI.scrollToElement(eduMaterial, 5)
	WebUI.waitForElementClickable(eduMaterial, 10)
	WebUI.click(eduMaterial)

	if (WebUI.waitForElementVisible(popup, 3, FailureHandling.OPTIONAL)) {
		KeywordUtil.logInfo("Popup opened in attempt: " + i)
		break
	}

	if (i == maxAttempts) {
		KeywordUtil.markFailed("Popup did not open after ${maxAttempts} clicks")
	}
}

// =====================================================
// UPLOAD TO PATIENT PORTAL
// =====================================================

WebUI.mouseOver(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))

TestObject toastMsg =
		findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message')

WebUI.waitForElementVisible(toastMsg, 30, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(
		toastMsg,
		'Health information resource uploaded successfully on Patient Portal.'
)

for (int i = 1; i <= 11; i++)
{
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Patient Portal_uploadEMToPatientPortal'))
WebUI.delay(2)
}

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/button_Health Information Resource_dialog-c_57ea1d'))


// =====================================================
// LOGIN TO PATIENT PORTAL
// =====================================================

WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter User name and password and click on sign in button
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Confirm DOB and Accept terms by drawing signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

//Read OTP from received over email
String otp = CustomKeywords.'otp.GmailOTPHandler.readOTP'('imap.gmail.com', GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email, 'Verification')

println('OTP fetched = ' + otp)

// Auto type otp into four input boxes
String[] digits = otp.toCharArray()

//Enter OTP
WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp1'), (digits[0]).toString())

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp2'), (digits[1]).toString())

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp3'), (digits[2]).toString())

WebUI.setText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/otp4'), (digits[3]).toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click on Procced button after OTP entered
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

//Update Password   >>>>>>>>>>>>>>MBT 48416<<<<<<<<<<<<<<<<<
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//Login with Updated Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

//Fetch the otp from the email
String otp1 = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otp1)


// Auto type into four input boxes
String[] digits1 = otp1.toCharArray()

//Enter the OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits1[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits1[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits1[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits1[3].toString())

WebUI.delay(5)

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

//Click on Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)


WebUI.delay(5)

//Verify Username, Todays date and current time on dashboard
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'),
	[('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

//Verify msg count on dashboard
String actualUnreadMsgCount = WebUI.getText( findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Message Count') ).replaceAll("\\s+", "").trim()
WebUI.verifyMatch( actualUnreadMsgCount, "12", false)


//------------------ Click on Message ------------------//
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Mark Wood_border-2 rounded-full p-4 smp_311faa'))

//------------------ Message Count ------------------//
TestObject messageList = findTestObject('Object Repository/Scenario Update1703/Page_Patient Portal/Unread Message Count')

WebUI.waitForElementPresent(messageList, 30)

List<WebElement> messages = WebUI.findWebElements(messageList, 30)
int actualCount = messages.size()
int expectedCount = 10   // update as needed

assert actualCount == expectedCount : "❌ Message count mismatch. Expected: ${expectedCount}, Found: ${actualCount}"
println("✅ Message count verified: ${actualCount}")

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


//------------------ Verify Second Message is UNREAD ------------------//

// Title SHOULD be bold
TestObject secondMessageTitle = new TestObject().addProperty("xpath", ConditionType.EQUALS,
"(//section[@aria-label='Messages list']//div[contains(@class,'border-b')])[2]//span[contains(@class,'text-sm')]")

String classAttr2 = WebUI.getAttribute(secondMessageTitle, "class")

assert classAttr2.contains("font-semibold") : "❌ Second message is NOT bold (Expected UNREAD)"
println("✅ Second message title is bold (UNREAD)")


// Blue dot SHOULD be present
TestObject secondMessageBlueDot = new TestObject().addProperty("xpath", ConditionType.EQUALS,
"(//section[@aria-label='Messages list']//div[contains(@class,'border-b')])[2]//div[contains(@style,'rgb(5, 79, 141)')]")

List<WebElement> dots2 = WebUI.findWebElements(secondMessageBlueDot, 5)

assert !dots2.isEmpty() : "❌ Second message has NO blue dot (Expected UNREAD)"
println("✅ Second message has blue dot (UNREAD)")

//------------------ Pagination Validation ------------------//

TestObject pagination = findTestObject('Scenario Update1703/Page_Patient Portal/ul_12')
WebUI.waitForElementVisible(pagination, 30)

// Page numbers
TestObject page1 = new TestObject().addProperty("xpath", ConditionType.EQUALS,
"//ul[contains(@class,'pagination')]//li[a[text()='1']]")

TestObject page2 = new TestObject().addProperty("xpath", ConditionType.EQUALS,
"//ul[contains(@class,'pagination')]//li[a[text()='2']]")

assert WebUI.verifyElementPresent(page1, 10, FailureHandling.OPTIONAL) : "❌ Page 1 not present"
assert WebUI.verifyElementPresent(page2, 10, FailureHandling.OPTIONAL) : "❌ Page 2 not present"
println("✅ Pagination numbers 1 and 2 verified")

// Arrows
TestObject prevArrow = new TestObject().addProperty("xpath", ConditionType.EQUALS,
"(//ul[contains(@class,'pagination')]//li[contains(@class,'chevron')])[1]")

TestObject nextArrow = new TestObject().addProperty("xpath", ConditionType.EQUALS,
"(//ul[contains(@class,'pagination')]//li[contains(@class,'chevron')])[last()]")

assert WebUI.verifyElementPresent(prevArrow, 10, FailureHandling.OPTIONAL) : "❌ Previous arrow not present"
assert WebUI.verifyElementPresent(nextArrow, 10, FailureHandling.OPTIONAL) : "❌ Next arrow not present"
println("✅ Pagination arrows verified")

// Previous arrow should be disabled
String prevClass = WebUI.getAttribute(prevArrow, "class")

assert prevClass.contains("disabled") : "❌ Previous arrow is not disabled on first page"
println("✅ Previous arrow is disabled on first page")

//------------------ Click on Unread Filter ------------------//
WebUI.waitForElementPresent(findTestObject('PatientPortal/Page_Patient Portal/Message Screen/Page_Patient Portal/Show Unread button'), 15)
WebUI.click(findTestObject('PatientPortal/Page_Patient Portal/Message Screen/Page_Patient Portal/Show Unread button'))

WebUI.waitForElementPresent(messageList, 30)

//------------------ Unread Count ------------------//
List<WebElement> unreadMessages = WebUI.findWebElements(messageList, 30)
int actualUnreadCount = unreadMessages.size()
int expectedUnreadCount = 10   // update as needed

assert actualUnreadCount == expectedUnreadCount :
"❌ Unread message count mismatch. Expected: ${expectedUnreadCount}, Found: ${actualUnreadCount}"

println("✅ Unread message count verified: ${actualUnreadCount}")

//------------------ Pagination Again ------------------//

assert WebUI.verifyElementPresent(page1, 10, FailureHandling.OPTIONAL) : "❌ Page 1 not present (Unread)"
assert WebUI.verifyElementPresent(page2, 10, FailureHandling.OPTIONAL) : "❌ Page 2 not present (Unread)"

assert WebUI.verifyElementPresent(prevArrow, 10, FailureHandling.OPTIONAL) : "❌ Prev arrow missing (Unread)"
assert WebUI.verifyElementPresent(nextArrow, 10, FailureHandling.OPTIONAL) : "❌ Next arrow missing (Unread)"

println("✅ Pagination verified for unread messages")

String prevClassUnread = WebUI.getAttribute(prevArrow, "class")

assert prevClassUnread.contains("disabled") : "❌ Previous arrow not disabled in unread view"

println("✅ Previous arrow disabled in unread view")