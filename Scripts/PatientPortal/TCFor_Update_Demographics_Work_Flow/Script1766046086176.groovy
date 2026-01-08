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
import org.openqa.selenium.Keys
import java.util.Arrays
import utils.CheckboxKeywords as CK
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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.WebElement
import java.util.Arrays
import utils.CheckboxKeywords as CK
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : UserNamePt, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

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

WebUI.delay(10)

//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//click on Update Demographics option
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Profile_block pr-14 py-2 font-normal t_ea6dd6'))

//Verify update demographics page opened
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h1_Update Demographics_py-1 text-lg mdtext-_e6d4ac'),
	'Update Demographics')


WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h2_Update Demographics_py-1 text-lg mdtext-_f809d9'),
	'Name')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h2_Name_text-lg mdtext-xl font-normal text-_385e1c'),
	'Phone & Email')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/label_Number_labelText'), 'Primary Email')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/label_Primary Email_labelText'), 'Secondary Email')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h2_Secondary Email_text-lg mdtext-xl font-n_53665b'),
	'Address')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Action_-checkbox form-check mb-1 d-xxl-_c6c14f'),
	'Primary')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Address1_-checkbox form-check mb-1 d-xx_c93613'),
	'Primary')

// ----------------------------------------------------
// 1️⃣ PRIMARY CHECKBOX – ADDRESS
// ----------------------------------------------------
def Phonecheckbox = findTestObject('Object Repository/Page_Patient Portal/Primay Phone Email checkbox')
def Addresscheckbox = findTestObject('Object Repository/Page_Patient Portal/Primary Address checkbox')

// Create instance of the class
CK ckInstance = new CK()

// Call method on instance
ckInstance.verifyCheckboxDisabled(Phonecheckbox, true)

ckInstance.verifyCheckboxDisabled(Addresscheckbox, true)

// Verify color = GREEN
WebElement addressEl = WebUI.findWebElement(Addresscheckbox)
String addressColor = addressEl.getCssValue('background-color')
KeywordUtil.logInfo("Address Primary Color: " + addressColor)

// Expected blue (example: rgb(126, 46, 239))
CustomKeywords.'color.VerifyBagroundColor.verifyBagroundColor'(findTestObject('Object Repository/Page_Patient Portal/Primary Address checkbox'), "background-color",
    "rgb(126, 46, 239)")

// ----------------------------------------------------
// 2️⃣ PRIMARY CHECKBOX – PHONE
// ----------------------------------------------------


// Verify color = BLUE
WebElement phoneEl = WebUI.findWebElement(Phonecheckbox)
String phoneColor = phoneEl.getCssValue('background-color')
KeywordUtil.logInfo("Phone Primary Color: " + phoneColor)

// Expected blue (example: rgb(126, 46, 239))

CustomKeywords.'color.VerifyBagroundColor.verifyBagroundColor'(findTestObject('Object Repository/Page_Patient Portal/Primay Phone Email checkbox'), "background-color",
	"rgb(126, 46, 239)")
// ----------------------------------------------------
// 3️⃣ FALL DOWN – TITLE
// ----------------------------------------------------
TestObject titleDropdown = findTestObject(
    'Object Repository/Page_Patient Portal/select_Name_title-select'
)

WebUI.click(titleDropdown)

List<String> expectedTitles = ['Mr.', 'Mrs.', 'Ms.', 'Miss', 'Dr.']
expectedTitles.each {
    WebUI.verifyTextPresent(it, false)
}

// ----------------------------------------------------
// 4️⃣ FALL DOWN – SUFFIX
// ----------------------------------------------------
TestObject suffixDropdown = findTestObject(
    'Object Repository/Page_Patient Portal/select_Name_suffix-select'
)

WebUI.click(suffixDropdown)

List<String> expectedSuffix = ['Jr', 'Sr', 'II', 'III', 'IV']
expectedSuffix.each {
    WebUI.verifyTextPresent(it, false)
}

// ----------------------------------------------------
// 5️⃣ FALL DOWN – PHONE TYPE
// ----------------------------------------------------
TestObject phoneTypeDropdown = findTestObject(
    'Object Repository/Page_Patient Portal/select_Action_phone-type-0'
)

WebUI.click(phoneTypeDropdown)

List<String> expectedPhoneTypes = ['Select', 'Mobile', 'Home', 'Work']
expectedPhoneTypes.each {
    WebUI.verifyTextPresent(it, false)
}

// ----------------------------------------------------
// 6️⃣ FALL DOWN – COUNTRY
// ----------------------------------------------------
TestObject countryDropdown = findTestObject(
    'Object Repository/Page_Patient Portal/select_Primary_country-0'
)

WebUI.click(countryDropdown)
List<String> expectedcountryTypes = ['Select Country', 'ATG', 'BHS', 'CAN', 'DMA', 'LCA', 'TTO', 'USA' ]
expectedcountryTypes.each {
    WebUI.verifyTextPresent(it, false)
}


CustomKeywords.'utils.InputUtils.clearAndSetInput'(findTestObject('Object Repository/Page_Patient Portal/input_Name_firstName'),'')
CustomKeywords.'utils.InputUtils.clearAndSetInput'(findTestObject('Object Repository/Page_Patient Portal/input_Name_lastName'),'')
CustomKeywords.'utils.InputUtils.clearAndSetInput'(findTestObject('Object Repository/Page_Patient Portal/input_Primary Email_primaryEmail'),'')
CustomKeywords.'utils.InputUtils.clearAndSetInput'(findTestObject('Object Repository/Page_Patient Portal/input_Name_dob'),'')
CustomKeywords.'utils.InputUtils.clearAndSetInput'(findTestObject('Object Repository/Page_Patient Portal/input_Action_form-control mt-1 form-control_3def46_9'),'')
CustomKeywords.'utils.InputUtils.clearAndSetInput'(findTestObject('Object Repository/Page_Patient Portal/input_Primary_address1-0'),'')

//Select Phone type
WebUI.selectOptionByLabel(
    findTestObject("Object Repository/Page_Patient Portal/select_Action_phone-type-0"),
    "Select",
    false
)
// Click Save Changes Button 
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Save Changes Btn on Update Demographics'))

// Verify validations
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Name_text-red-500 text-sm mt-1'),'First name is required')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Name_text-red-500 text-xs mt-1'),'Last name is required')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Primary_text-red-500 text-sm'), 'Type and Number are required')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Primary Email_text-red-500 text-xs mt-1'),'Primary email is required')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Name_text-red-500 text-xs mt-1_1'), 'Date of birth required')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Primary_text-red-500 text-sm mt-1'), 'Address is required')


//Select Phone type as Mobile
WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Patient Portal/select_Action_phone-type-0'), 'Mobile',true)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Action_form-control mt-1 form-control_c48331'))

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Action_form-control mt-1 form-control_3def46_9'),
	'987-654-3210')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/svg_Primary_a'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Save Changes_px-8 py-2 rounded bg-re_63977b'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/svg_Primary_a'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Save Changes_text-lg mb-6 text-center max-w-xs'),
	'At least one phone number is required. You cannot delete the only number.')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Save Changes_px-8 py-2 rounded bg-re_63977b'))

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Primary_address1-0'), '1234 Main Street')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Patient Portal/select_Primary_country-0'), 'ATG', true)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Primary_city-0'), 'LA')

//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Patient Portal/select_Primary_state-0'), 'ALL SAINTS', true)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Primary_zip-0'), '465012')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/svg_Primary_a_1'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Save Changes_text-lg mb-6 text-center max-w-xs_1'),
	'At least one address is required. You cannot delete the only address.')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Save Changes_px-8 py-2 rounded bg-re_63977b'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/svg_Phone  Email_text-primary'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Patient Portal/select_Primary_phone-type-1'), 'Home', true)

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Action_form-control mt-1 form-control_c48331'))

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Primary_form-control mt-1 form-contro_dae147_11'),
	'121211121212')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/svg_Primary_a_2'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Save Changes_text-lg mb-6 text-center max-w-xs_2'),
	'Are you sure you want to delete this phone number?')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Delete_px-8 py-2 rounded bg-gray-500_5566e9'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/svg_Primary_a_2'))

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Save Changes_px-8 py-2 rounded bg-re_63977b_1'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/div_Messages_1'), 'Phone number deleted successfully!')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/svg_Address_text-primary'))

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Primary_address1-1'), 'Test')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Patient Portal/select_Primary_country-1'), 'CAN', true)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Primary_city-1'), 'CAN')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Patient Portal/select_Primary_state-1'), 'MB', true)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Primary_zip-1'), '2345678')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/svg_Primary_a_3'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Save Changes_text-lg mb-6 text-center max-w-xs_3'),
	'Are you sure you want to delete this address?')

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Save Changes_px-8 py-2 rounded bg-re_63977b_1'))

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Primary Email_primaryEmail'), 'gajakumara@first-insight.com')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Name_firstName'), 'David')

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Name_lastName'), 'Smith')

//Click on Save Changes Button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Save Changes Btn on Update Demographics'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/p_Primary_text-red-500 text-xs'), 'Invalid ZIP code format')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Patient Portal/select_Primary_state-0_1'), 'YT', true)

WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Primary_zip-0'), '46077')

CustomKeywords.'utils.InputUtils.clearAndSetInput'(findTestObject('Object Repository/Page_Patient Portal/input_Name_dob'),GlobalVariable.DOB)

//Click on Save Changes Button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Save Changes Btn on Update Demographics'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/Insurance Updated Saved Toast'), 'Demographics data has been updated!')

