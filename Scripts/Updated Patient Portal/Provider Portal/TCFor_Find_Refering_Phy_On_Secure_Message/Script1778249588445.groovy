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
import java.text.SimpleDateFormat
import java.util.TimeZone
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.Keys
import utils.ProviderPortalEmailUtils

// ===============================
// 🔹 Login
// ===============================
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

// ===============================
// 🔹 Delete "Email" Value List (if exists)
// ===============================
WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/a_Office Admin'))
WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/a_Value Lists'))
WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/a_ui-id-26'))


TestObject searchBox = findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_Search in data grid')

WebUI.clearText(searchBox)
WebUI.setText(searchBox, 'Email')
WebUI.sendKeys(searchBox, Keys.chord(Keys.ENTER))

TestObject row = new TestObject().addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//tr[.//td[normalize-space()='Email']]"
)

TestObject deleteBtn = new TestObject().addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//td[normalize-space()='Email']/following-sibling::td//span[contains(@title,'Delete')]"
)

if (WebUI.verifyElementPresent(row, 5, FailureHandling.OPTIONAL)) {

	WebUI.click(deleteBtn)
	WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_btnBP_Yes'))

	// verify deletion
	WebUI.waitForElementNotPresent(row, 10)
}

// ===============================
// 🔹 Create External Physician (without email)
// ===============================
WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/span_BtnExternalPhysician'))

WebUI.setText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_First Name'), 'Email')
WebUI.setText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_Last Name'), 'No')
WebUI.setText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_Info_Clinic'), 'Email')

// Select all specialties
['Referring','PCP','Surgeon','Other'].each {
	WebUI.click(findTestObject("Provider Portal/Ext Phy/Page_MaximEyes/span_${it}"))
}

WebUI.click(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_btnphysiciansave'))

// ===============================
// 🔹 Navigate to Compose
// ===============================
TestObject homeBtn = findTestObject('Provider Portal/New Folder3/Page_MaximEyes/a_navItemHome')
WebUI.waitForElementClickable(homeBtn, 15)
WebUI.scrollToElement(homeBtn, 5)
WebUI.click(homeBtn)

WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/a_ui-id-9'))

TestObject composeBtn = findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_btnCompose')
WebUI.waitForElementClickable(composeBtn, 15)
WebUI.scrollToElement(composeBtn, 5)
WebUI.click(composeBtn)

WebUI.waitForElementClickable(findTestObject('Provider Portal/Page_MaximEyes/span_Search'), 10)
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_Search'))
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_btnReferringPhysicians'))

// ===============================
// 🔹 Verify UI Texts
// ===============================
WebUI.verifyElementPresent(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search in data grid'), 0)

['Last Name','First Name','Clinic','Specialty','Address','Email','Phone'].each { col ->
	WebUI.verifyTextPresent(col, false)
}

// ===============================
// 🔹 Search & Select Physician
// ===============================
WebUI.setText(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search in data grid'), 'Katalon')


WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/td_Katalon'))

// Verify To field
WebUI.verifyElementAttributeValue(
	findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search Patient or Referring Physician'),
	"value",
	"Katalon Phy | Email: gajakumara@first-insight.com | Clinic: Katalon",
	5
)

// ===============================
// 🔹 Validate No Email Error
// ===============================
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_Search'))
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_btnReferringPhysicians'))

WebUI.setText(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search in data grid'), 'Email')
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/td_Katalon'))

WebUI.verifyTextPresent('does not have an Email ID', false)

WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_btnJumpToOA'))

// ===============================
// 🔹 Add Email to Physician
// ===============================
WebUI.setText(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search in data grid'), 'Email')
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/td_No'))

WebUI.selectOptionByValue(
	findTestObject('Provider Portal/New Folder3/Page_MaximEyes/select_PhoneEmailModel_0_SelectedTypeId'),
	'2',
	false
)

WebUI.setText(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_PhoneEmailEntry_1579ea8f'),
	'gajakumara@first-insight.com')

WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_btnphysiciansave'))
WebUI.verifyTextPresent('External physician info saved.', false)

WebUI.delay(3)

// ===============================
// 🔹 Send Message
// ===============================
TestObject homeBtn1 = findTestObject('Object Repository/Provider Portal/New Folder3/Page_MaximEyes/a_navItemHome - Copy')

WebUI.waitForElementClickable(homeBtn1, 15)
WebUI.scrollToElement(homeBtn1, 5)
WebUI.click(homeBtn1)
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/a_ui-id-9'))
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_btnCompose'))

WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_Search'))
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_btnReferringPhysicians'))

WebUI.setText(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search in data grid'), 'email')
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/td_Katalon'))

WebUI.verifyElementAttributeValue(
	findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search Patient or Referring Physician'),
	"value",
	"Email No | Email: gajakumara@first-insight.com | Clinic: Email",
	5
)

WebUI.setText(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_ComposeSubject'), 'Katalon Test')
WebUI.setText(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/textarea_input InboxTextarea font20 pad05'),
	'Note: For Hippa compliance patient information should be shared via patient portal!')

WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/button_compose-send-button'))



// ===============================
// 🔹 Validate Sent Message
// ===============================
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_openmoreactionmenu'))
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/div_Sent Messages'))


TestObject obj = findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_Katalon Test')

// Wait until element is clickable
WebUI.waitForElementClickable(obj, 20)

// Then click
WebUI.click(obj)

//verify email
WebUI.verifyTextPresent('To: gajakumara@first-insight.com', false)

TestObject noteObj = findTestObject('Provider Portal/New Folder3/Page_MaximEyes/p_Note_ For Hippa complaince patient information')

WebUI.waitForElementVisible(noteObj, 10)

String actualText = WebUI.getText(noteObj).trim()

assert actualText.contains('Note: For Hippa compliance patient information should be shared via patient portal!')

WebUI.verifyElementText(findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/span_Reffering Physician'), 'Reffering Physician')

//Verify email received
ProviderPortalEmailUtils email = new ProviderPortalEmailUtils()

email.verifyEmail(
	"Katalon Test",
	"Hippa compliance",
	120
)

// -------------------- TIME VALIDATION --------------------

def sdf = new SimpleDateFormat("MM/dd/yyyy hh")
sdf.setTimeZone(TimeZone.getTimeZone("GMT"))

String expected = sdf.format(new Date())
String actual = WebUI.getText(
	findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span__ 05_08_2026 12_20 PM')
).replaceAll("_", "/").trim()

assert actual.contains(expected)

WebUI.verifyElementPresent(
	findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_Dispatched'),
	5
)

//-----------------------Practice Flow----------------------



TestObject sentComoseBtn = findTestObject('Object Repository/Provider Portal/Ext Phy/Page_MaximEyes/span_Sent Messages_btnComposeSent')
//click on compose button
WebUI.waitForElementClickable(sentComoseBtn, 15)
WebUI.scrollToElement(sentComoseBtn, 5)
WebUI.click(sentComoseBtn)

WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_Search'))
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_btnReferringPhysicians'))

WebUI.setText(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search in data grid'), 'email')
WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/td_Katalon'))

WebUI.verifyElementAttributeValue(
	findTestObject('Provider Portal/New Folder3/Page_MaximEyes/input_Search Patient or Referring Physician'),
	"value",
	"Email No | Email: gajakumara@first-insight.com | Clinic: Email",
	5
)


TestObject practiceTab = findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/span_Practice')
TestObject subjectInput = findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/input_ComposeSubject')
TestObject messageBox = findTestObject('Provider Portal/Ext Phy/Page_MaximEyes/textarea_input InboxTextarea font20 pad05')

// Wait + click
WebUI.waitForElementClickable(practiceTab, 10)
WebUI.click(practiceTab)

// Set subject
WebUI.waitForElementVisible(subjectInput, 10)
WebUI.setText(subjectInput, 'Practice Katalon Subject')

// Set message (clean string)
String message = "I have taken appointment for my son with Dr Mary Smith. Ref letter from Dr Steve., Message For Doctor"

WebUI.waitForElementVisible(messageBox, 10)
WebUI.setText(messageBox, message)

WebUI.click(findTestObject('Provider Portal/New Folder3/Page_MaximEyes/button_compose-send-button'))

// ===============================
// 🔹 Validate Sent Message
// ===============================
WebUI.delay(10)

//verify email
WebUI.verifyTextPresent('To: gajakumara@first-insight.com', false)


WebUI.waitForElementVisible(noteObj, 10)

String actualText1 = WebUI.getText(noteObj).trim()

assert actualText1.contains(message)

//Verify email received

email.verifyEmail(
	"Practice Katalon Subject",
	"taken appointment",
	120
)

// -------------------- TIME VALIDATION --------------------



String expected1 = sdf.format(new Date())
String actualTime = WebUI.getText(
	findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span__ 05_08_2026 12_20 PM')
).replaceAll("_", "/").trim()

assert actualTime.contains(expected1)

WebUI.verifyElementPresent(
	findTestObject('Provider Portal/New Folder3/Page_MaximEyes/span_Dispatched'),
	5
)