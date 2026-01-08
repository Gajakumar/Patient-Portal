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
import com.kms.katalon.core.webui.common.WebUiCommonHelper

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span__mif-cog font20 head-icon-shadow fg-white'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Office Admin_Office Admin  Modules'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Schedule_ui-id-22'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Inventory_NevIncentiveProgram'))

TestObject SendEducationMaterialfromallElementsradioBtn = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Send Education Material from all Element radio btn')

if (!(WebUI.verifyElementChecked(SendEducationMaterialfromallElementsradioBtn, 1, FailureHandling.OPTIONAL))) {
	WebUI.click(SendEducationMaterialfromallElementsradioBtn)
}

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_First Insight Agreements_mif-home font_746c66'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_ACTIONS_imgFindPatient'))

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Find Patient_FirstName'), 'Mark')

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Find Patient_LastName'), 'Wood')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Active_btnSearchPatient'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Actions_Encounters  Add New Encounter'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Encounter Type_EncounterTypeID'),
	'51', true)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))

TestObject createNewEncounterBtn = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter')

// Check if button is present within 5 seconds
if (WebUI.verifyElementPresent(createNewEncounterBtn, 5, FailureHandling.OPTIONAL)) {
	WebUI.click(createNewEncounterBtn)

	println('Create New Encounter button clicked')
} else {
	println('Create New Encounter button not displayed – skipping click')
}

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Add Problem Plus button'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Asthma_DESCRIPTION_GF_df4a_GF_DDD_L_LBI3T0'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/img_Loading_STATUS_GF_df4a_GF_B-1Img'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Loading_STATUS_GF_df4a_GF_DDD_L_LBI0T0'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_df4a_GF_B-1'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td_Chronic_SOURCE_GF_df4a_GF_DDD_L_LBI0T0'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/Search Problem Field'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_--Select--_glyphicon-circelplus font17_9bfae2'))

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Search Drug_MEDICATION_BRAND_NAME_I'),
	'Lipitor')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Directions to Pharmacist_Add'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Refills_btnOk'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Chief Complaint  HPI_encTabList_2'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_Annual exam_glyphicon-circelplus font1_0c94a1'))

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Search_textbox'), 'H disease')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/button_CLEAR_searchICD10Codes'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/td'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_PLANS_Save'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Final Findings_encTabList_3'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/span_TOC Req_font12 line-height18 signOffTi_2ff70e'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Are you sure you want to sign off the_f71194'))

WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Patient Portal_signaturePassword'),
	'123456')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Patient Portal_authenticateUserSignature'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(2)

WebUI.waitForElementVisible(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/div_Cancel_jquery-notific8-message'),
	15
)

//Login to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Sign in With User Name and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : UserNameA, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

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

String actualUnreadMsgCount = WebUI.getText(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Message Count')
).replaceAll("\\s+", "").trim()

WebUI.verifyMatch(
	actualUnreadMsgCount,
	"3unreadmessages",
	false
)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/div_Mark Wood_border-2 rounded-full p-4 smp_311faa'))

List<String> expectedMessages = [
	'Education Material: Amino Acid Metabolism Disorders',
	'Education Material: Obesity',
	'Education Material: Atorvastatin'
]

TestObject inboxMessages =
findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/p_Inbox_Message_Title')

List<WebElement> messageElements =
WebUiCommonHelper.findWebElements(inboxMessages, 10)

List<String> actualMessages = messageElements.collect { it.text.trim() }

expectedMessages.each { expected ->
assert actualMessages.contains(expected) :
	"Inbox message NOT found: ${expected}"
}

WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/div_Inbox_w-2 h-2 rounded-full'), 5)

WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/div_To Mark Wood_w-2 h-2 rounded-full'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/div_To Mark Wood_w-2 h-2 rounded-full_1'), 
    5)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/div_Inbox_px-3 py-3 border-b border-gray-20_cf1afb'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/span_PP_keyStr'), 5)

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/p_PP_text-sm text-gray-600 mb-1'), 'To: Mark Wood')

WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/button_Subtitle_flex items-center justify-b_5e8906'), 
    0)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/div_To Mark Wood_px-3 py-3 border-b border-_da13b9'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/span_PP_keyStr_1'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/button_Subtitle_flex items-center justify-b_5e8906_1'), 
    0)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/div_To Mark Wood_px-3 py-3 border-b border-_da13b9_1'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/span_PP_keyStr_2'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/button_Subtitle_flex items-center justify-b_5e8906_2'), 
    0)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/div_Inbox_w-4 h-4 border-2 border-gray-400 _2e2b61'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/div_MW_w-4 h-4 border-2 border-white rounde_4d889b'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/svg_MW_a'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/button_Forward_px-8 py-2 rounded bg-red-600_662017'))

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/p_Inbox_text-lg mt-2'), 'You have no messages in inbox')

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/svg_Messages - Patient Portal_text-light'))

