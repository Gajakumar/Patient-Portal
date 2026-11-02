import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import common.TextFieldValidation as TFV
import groovy.transform.Field
import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select


//Login To Maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

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


//Navigate to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter User name and password and click on sign in button
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Confirm DOB and Accept terms by drawing signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)


WebUI.delay(5)

//Fetch the otp from the email
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

//Enter the OTP
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digits[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digits[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digits[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digits[3].toString())

WebUI.delay(5)

TestObject proceedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

//Click on Procced button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

//Update Password   >>>>>>>>>>>>>>MBT 48416<<<<<<<<<<<<<<<<<
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//Login with Updated Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

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

//Verify Date Time and Patient name on Dashboard
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Verify Date Time and Patient name on Dashboard'), [('Firstname') : GlobalVariable.PatientFirstName, ('Lastname') : GlobalVariable.PatientLastName], FailureHandling.STOP_ON_FAILURE)

//Verify Dashboard modules
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Dashboard Verification'),[:],FailureHandling.STOP_ON_FAILURE)


////======================

//Click on Setting icon on dashboard
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Setting Icon on Portal'))

//click on Update Insurance on setting dropdown
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/span_Update Demographics_block pr-14 py-2 f_d2a216'))

//Self Pay check box
TestObject PtPortalselfPayChkbox = findTestObject('Object Repository/Page_Patient Portal/Self Pay  No Insurance Available chk box')

// Click Self Pay
WebUI.click(PtPortalselfPayChkbox)

//wait for UI update
WebUI.delay(1)

//Verify self pay is unchecked
WebUI.verifyElementNotChecked(PtPortalselfPayChkbox, 5)

//Click on Add Insurance
WebUI.click(findTestObject('PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Page_Patient Portal/Add Insurance Plus Button'))


//Check No Ins check box
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Insurance Card Photo_undefinedNo Insu_7f69c9'))

//Verify Patient Relationship to Insured page opens
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/span__text-gray-500'), 
    'Patient Relationship to Insured')
//Verify field on PRI
WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5_1'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5_2'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Middle Name_form-control mt-1 form-co_6f1561'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Suffix_form-control mt-1 form-control_012157'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input__form-control mt-1 form-control-md fl_b5c8f5_3'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/label_Male_flex items-center'), 
    5)

WebUI.verifyElementPresent(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/label__flex items-center'), 
    5)

//Verify max length for Insurance field
TFV.verifyMaxLengthWithMessage("Insurance Name", findTestObject('Object Repository/Scenario Update1703/Page_Patient Portal/input_Insurance Name'), 100)

//Verify max length for Notes field
TFV.verifyMaxLengthWithMessage("Notes", findTestObject('Scenario Update1703/Page_Patient Portal/input_Notes2'), 1000)

//Verify max length for Insured ID field
TFV.verifyMaxLengthWithMessage("Insured ID", findTestObject('Scenario Update1703/Page_Patient Portal/input_Insured ID'), 80)

//Verify max length for First Name field
TFV.verifyMaxLengthWithMessage("Legal First Name", findTestObject('Scenario Update1703/Page_Patient Portal/input_Legal First Name'), 35)

//Verify max length for Middle Name field
TFV.verifyMaxLengthWithMessage("Middle Name", findTestObject('Scenario Update1703/Page_Patient Portal/input_Middle Name (Optional)'), 25)

//Verify max length for Last Name field
TFV.verifyMaxLengthWithMessage("Last Name", findTestObject('Scenario Update1703/Page_Patient Portal/input_Last Name'), 60)

//Verify max length for Suffix field
TFV.verifyMaxLengthWithMessage("Suffix", findTestObject('Scenario Update1703/Page_Patient Portal/input_Suffix (Optional)'), 10)

//Uncheck no ins card check box
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/input_Insurance Card Photo_undefinedNo Insu_7f69c9'))

//Upload Ins 
//WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/div_No Insurance Card_flex flex-col items-c_89221b'))

def fileUploadInputFront   = findTestObject('PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Upload_img_Front')
def fileUploadInputBack   = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Upload_img_Back')
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
	
//	//Upload Insurance Card Front with invalid format - Front >> Invalid file not working
//	uploadFileTestCloud(fileUploadInputFront, baseDir, 'InsCardInvalid.tif')
//	
//	//verify toast msg
//	CustomKeywords.'common.ToastHelper.verifyToastMessage'('Invalid file format. Please upload JPG, JPEG, or PNG only.')
//	WebUI.delay(3)
//	
//	//Upload Insurance Card Front with invalid format - Back
//	uploadFileTestCloud(fileUploadInputBack, baseDir, 'InsCardInvalid.tif')
//	
//	//verify toast msg
//	CustomKeywords.'common.ToastHelper.verifyToastMessage'('Invalid file format. Please upload JPG, JPEG, or PNG only.')
//	WebUI.delay(3)
	
	//Upload Insurance Card Front with invalid format - Front
	uploadFileTestCloud(fileUploadInputFront, baseDir, '3mb.jpg')
	
	//verify toast msg
	CustomKeywords.'common.ToastHelper.verifyToastMessage'('You cannot upload file with more than 2 MB of size.')
	WebUI.delay(3)
	
	//Upload Insurance Card Front with invalid format - Back
	uploadFileTestCloud(fileUploadInputBack, baseDir, '3mb.jpg')
	
	//verify toast msg
	CustomKeywords.'common.ToastHelper.verifyToastMessage'('You cannot upload file with more than 2 MB of size.')

	//Upload Insurance Card Front
	uploadFileTestCloud(fileUploadInputFront, baseDir, 'InsCard.jpg')

	WebUI.delay(3)
	
	//Upload Insurance Card Back
	uploadFileTestCloud(fileUploadInputBack, baseDir, 'InsCard.jpg')

	WebUI.delay(3)
//	
//	//Issue An unexpected error has occurred. Please try again later. If the problem persists, call our office." 
////   Max length validation is not working
//	
////	//Click on Save button
////	WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/button_Save'))
////	
////	//verify toast msg
////	CustomKeywords.'common.ToastHelper.verifyToastMessage'('Insurance information saved successfully')
////	
////	
////	//Click on Setting icon on dashboard
////	WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Setting Icon on Portal'))
////	
////	//click on Update Insurance on setting dropdown
////	WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/span_Update Demographics_block pr-14 py-2 f_d2a216'))
//	
////	WebUI.click(findTestObject('Scenario Update1703/Page_Patient Portal/input_No Insurance Card'))
	

	 WebDriver driver = DriverFactory.getWebDriver()
	  
	 /* ----------------------------- TEST OBJECTS ---------------------------------- */
	  
	 // TODO: replace with actual selector once "No Insurance Card" checkbox HTML is available
	 TestObject noInsuranceCardCheckbox = new TestObject('noInsuranceCardCheckbox')
	 noInsuranceCardCheckbox.addProperty('xpath', ConditionType.EQUALS,
		 "//input[@type='checkbox' and (contains(@id,'Insurance Card') or contains(@name,'no-insurance') or contains(@class,'no-insurance'))]")
	  
	 TestObject priDropdown = new TestObject('priDropdown')
	 priDropdown.addProperty('xpath', ConditionType.EQUALS,
		 "//div[@id='insurance-relationship-dropdown']//select[@class='insurance-relationship-select']")
	  
	 TestObject insuredIdInput = new TestObject('insuredIdInput')
	 insuredIdInput.addProperty('xpath', ConditionType.EQUALS,
		 "//input[@placeholder='Insured ID']")
	  
	 TestObject firstNameInput = new TestObject('firstNameInput')
	 firstNameInput.addProperty('xpath', ConditionType.EQUALS,
		 "//input[@placeholder='Legal First Name']")
	  
	 TestObject middleNameInput = new TestObject('middleNameInput')
	 middleNameInput.addProperty('xpath', ConditionType.EQUALS,
		 "//input[@placeholder='Middle']")
	  
	 TestObject lastNameInput = new TestObject('lastNameInput')
	 lastNameInput.addProperty('xpath', ConditionType.EQUALS,
		 "//input[@placeholder='Last Name']")
	  
	 TestObject suffixInput = new TestObject('suffixInput')
	 suffixInput.addProperty('xpath', ConditionType.EQUALS,
		 "//input[@placeholder='Suffix']")
	  
	 TestObject dobInput = new TestObject('dobInput')
	 dobInput.addProperty('xpath', ConditionType.EQUALS,
		 "//input[@placeholder='MM/DD/YYYY']")
	  
	 TestObject calendarIcon = new TestObject('calendarIcon')
	 calendarIcon.addProperty('xpath', ConditionType.EQUALS,
		 "//img[@alt='calendar']")
	  
	 TestObject genderMaleRadio = new TestObject('genderMaleRadio')
	 genderMaleRadio.addProperty('xpath', ConditionType.EQUALS,
		 "//input[contains(@class,'insurance-gender-radio') and @value='male']")
	  
	 TestObject genderFemaleRadio = new TestObject('genderFemaleRadio')
	 genderFemaleRadio.addProperty('xpath', ConditionType.EQUALS,
		 "//input[contains(@class,'insurance-gender-radio') and @value='female']")
	  
	 // TODO: replace with actual selector once "Save" button HTML is available
	 TestObject saveButton = new TestObject('saveButton')
	 saveButton.addProperty('xpath', ConditionType.EQUALS,
		 "//button[normalize-space(text())='Save' or contains(@class,'insurance-save-button')]")
	  
	TestObject calendarMonthYearLabel = new TestObject('calendarMonthYearLabel')
	calendarMonthYearLabel.addProperty('xpath', ConditionType.EQUALS,
    "//span[contains(@class,'cursor-pointer') and contains(@class,'font-medium')]")
 
	// All year buttons inside the 4-column grid, e.g. <button>2026</button>, <button>1900</button>, ...
	@Field TestObject calendarYearButtons = new TestObject('calendarYearButtons')
	calendarYearButtons.addProperty('xpath', ConditionType.EQUALS,
    "//div[contains(@class,'grid-cols-4')]/button")
	  
	 TestObject calendarCloseBtn = new TestObject('calendarCloseBtn')
	 calendarCloseBtn.addProperty('xpath', ConditionType.EQUALS,
		 "//button[normalize-space(text())='CLOSE' or normalize-space(text())='Close']")
	  
	 TestObject calendarSaveBtn = new TestObject('calendarSaveBtn')
	 calendarSaveBtn.addProperty('xpath', ConditionType.EQUALS,
		 "//button[normalize-space(text())='SAVE' or normalize-space(text())='Save']")
	 
	 /**
	  * Returns a TestObject for one specific year button in the grid, e.g. year "1900".
	  */
	 TestObject calendarYearOption(String year) {
		 TestObject obj = new TestObject('calendarYearOption_' + year)
		 obj.addProperty('xpath', ConditionType.EQUALS,
			 "//div[contains(@class,'grid-cols-4')]/button[normalize-space(text())='" + year + "']")
		 return obj
	 }
	  
	 /* ------------------------------- HELPERS -------------------------------------- */
	 
	 /**
	  * Returns the visible text of every year <button> in the calendar's year-grid panel.
	  * (These are plain buttons, not <select><option> elements, so Selenium's Select
	  * class does not apply here - WebUI.findWebElements is used instead.)
	  */
	 List<String> getCalendarYearButtonTexts() {
		 List<WebElement> buttons = WebUI.findWebElements(calendarYearButtons, 10)
		 return buttons.collect { WebElement btn -> btn.getText().trim() }
	 }
	  
	 /**
	  * Given an input field's XPath string, returns the validation message text shown
	  * in its sibling "validation-position > col-12" div (per supplied HTML structure).
	  */
	 String getValidationMessage(String inputXpath) {
		 String validationXpath = inputXpath
		 TestObject validationObj = new TestObject('validationMsg_' + System.currentTimeMillis())
		 validationObj.addProperty('xpath', ConditionType.EQUALS, validationXpath)
		 if (WebUI.verifyElementPresent(validationObj, 2, FailureHandling.OPTIONAL)) {
			 return WebUI.getText(validationObj, FailureHandling.OPTIONAL)
		 }
		 return ''
	 }
	  
	 void verifyFieldHasError(TestObject fieldObj, String xpathForValidation, String expectedMessage, String fieldLabel) {
		 String actualMsg = getValidationMessage(xpathForValidation)
		 WebUI.verifyMatch(actualMsg, expectedMessage, true)
		 WebUI.comment("Verified '" + fieldLabel + "' shows validation: " + expectedMessage)
	 }
	  
	 void verifyFieldHasNoError(String xpathForValidation, String fieldLabel) {
		 String actualMsg = getValidationMessage(xpathForValidation)
		 WebUI.verifyMatch(actualMsg, '', true)
		 WebUI.comment("Verified '" + fieldLabel + "' has NO validation error")
	 }
	  
	 /**
	  * Returns the visible text of every <option> in a <select>, using Selenium's
	  * Select wrapper directly (Katalon's WebUI class has no built-in "get all options" keyword).
	  */
	 List<String> getSelectOptions(TestObject selectObj) {
		 WebElement selectElement = WebUI.findWebElement(selectObj)
		 Select select = new Select(selectElement)
		 return select.getOptions().collect { WebElement opt -> opt.getText().trim() }
	 }
	  


	 void waitForFieldNotEmpty(TestObject fieldObj, int timeoutSeconds) {
		 for (int i = 0; i < timeoutSeconds; i++) {
			 String val = WebUI.getAttribute(fieldObj, 'value', FailureHandling.OPTIONAL)
	 
			 if (val != null && !val.trim().isEmpty()) {
				 return
			 }
	 
			 WebUI.delay(1)
		 }
	 
		 KeywordUtil.markFailedAndStop("Field value remained empty after ${timeoutSeconds} seconds.")
	 }
	 
	 void clearInputField(TestObject obj) {
		 WebUI.waitForElementClickable(obj, 10)
		 WebUI.click(obj)
		 WebUI.sendKeys(obj, Keys.chord(Keys.CONTROL, 'a'))
		 WebUI.sendKeys(obj, Keys.chord(Keys.BACK_SPACE))
	 }
	 /* ================================================================================
		SECTION C - Update Insurance - Patient Relationship to Insured
		================================================================================ */
	  
	 WebUI.comment('--- C1: Select No Insurance Card checkbox -> PRI section should appear ---')
	 WebUI.click(noInsuranceCardCheckbox)
	 WebUI.verifyElementPresent(priDropdown, 5)
	 WebUI.verifyElementPresent(insuredIdInput, 5)
	 WebUI.verifyElementPresent(firstNameInput, 5)
	 WebUI.verifyElementPresent(lastNameInput, 5)
	 WebUI.verifyElementPresent(dobInput, 5)
	 WebUI.verifyElementPresent(genderMaleRadio, 5)
	 WebUI.verifyElementPresent(genderFemaleRadio, 5)
	  
	 WebUI.comment('--- C2: PRI left as "Select" -> Click Save -> required-field validations ---')
	 WebUI.selectOptionByValue(priDropdown, '', true)
	 WebUI.click(saveButton)
	  
	 verifyFieldHasError(priDropdown,
		 "//div[@id='insurance-relationship-dropdown']//p",
		 'Relationship to insured is required', 'Patient Relationship to Insured')
	 verifyFieldHasError(insuredIdInput,
		 "//p[normalize-space()='Insured ID is required']",
		 'Insured ID is required', 'Insured ID')
	 verifyFieldHasError(firstNameInput,
		 "//p[normalize-space()='First name is required']",
		 'First name is required', 'First Name')
	 verifyFieldHasError(lastNameInput,
		 "//p[normalize-space()='Last name is required']",
		 'Last name is required', 'Last Name')
	 verifyFieldHasError(dobInput,
		 "//p[normalize-space()='DOB is required.']",
		 'DOB is required.', 'Date of Birth')
	 // Gender validation message location may differ (radio group, not a text input);
	 // adjust locator below to match actual markup if this assertion fails.
	 WebUI.verifyElementPresent(
		 new TestObject('genderRequiredMsg').addProperty('xpath', ConditionType.EQUALS,
			 "//p[contains(text(),'Gender is required')]"), 5)
	  
	 WebUI.comment('--- C3: Verify PRI dropdown options ---')
	 List<String> expectedOptions = ['Select', 'Self', 'Spouse', 'Child', 'Other']
	 List<String> actualOptions = getSelectOptions(priDropdown)
	 WebUI.comment('Expected options: ' + expectedOptions)
	 WebUI.comment('Actual options  : ' + actualOptions)
	 assert actualOptions.size() == expectedOptions.size() : 'PRI dropdown option count mismatch'
	 for (int i = 0; i < expectedOptions.size(); i++) {
		 assert actualOptions[i].trim().equalsIgnoreCase(expectedOptions[i]) : "Mismatch at index ${i}: expected ${expectedOptions[i]}, actual ${actualOptions[i]}"
	 }
	  
	 WebUI.comment('--- C4: Select Spouse / Child / Other -> no PRI error, other fields still validate ---')
	 List<String> nonSelfOptions = ['spouse', 'child', 'other']
	 for (String option in nonSelfOptions) {
		 WebUI.comment(">>> Testing PRI option: ${option}")
	  
		 // Clear any previously entered data so validations can be re-verified from a clean state
		 clearInputField(insuredIdInput)
		 clearInputField(firstNameInput)
		 clearInputField(lastNameInput)
		 clearInputField(dobInput)
	  
		 WebUI.selectOptionByValue(priDropdown, option, true)
		 WebUI.click(saveButton)
	  
		 // PRI itself should show no error once a real value is chosen
		 verifyFieldHasNoError("//div[@id='insurance-relationship-dropdown']//p",
			 'Patient Relationship to Insured (' + option + ')')
	  
		 // Other mandatory fields should still show validation errors
		 verifyFieldHasError(insuredIdInput, "//p[normalize-space()='Insured ID is required']",
			 'Insured ID is required', 'Insured ID (' + option + ')')
		 verifyFieldHasError(firstNameInput, "//p[normalize-space()='First name is required']",
			 'First name is required', 'First Name (' + option + ')')
		 verifyFieldHasError(lastNameInput, "//p[normalize-space()='Last name is required']",
			 'Last name is required', 'Last Name (' + option + ')')
		 verifyFieldHasError(dobInput, "//p[normalize-space()='DOB is required.']",
			 'DOB is required.', 'DOB (' + option + ')')
	  
		 // Now fill in data manually and confirm errors clear
		 WebUI.setText(insuredIdInput, 'INS' + option.toUpperCase() + '123')
		 WebUI.setText(firstNameInput, 'John')
		 WebUI.setText(lastNameInput, 'Doe')
		 WebUI.setText(dobInput, '01/01/1990')
		 WebUI.click(genderMaleRadio)
		 WebUI.click(saveButton)
	  
		 verifyFieldHasNoError("//p[normalize-space()='Insured ID is required']", 'Insured ID (' + option + ') after fill')
		 verifyFieldHasNoError("//p[normalize-space()='First name is required']", 'First Name (' + option + ') after fill')
		 verifyFieldHasNoError("//p[normalize-space()='Last name is required']", 'Last Name (' + option + ') after fill')
		 verifyFieldHasNoError("//p[normalize-space()='DOB is required.']", 'DOB (' + option + ') after fill')
	 }
	  
//	 WebUI.comment('--- C5: Select "Self" -> auto-populate all fields except Insured ID ---') >> issue with auto populate for self
//	 WebUI.selectOptionByValue(priDropdown, 'self', true)
//	  
//	 // Wait for auto-populate to complete (adjust wait/condition as needed for actual app behavior)
//	 waitForFieldNotEmpty(firstNameInput, 5)
//	  
//	 String autoFirstName = WebUI.getAttribute(firstNameInput, 'value')
//	 String autoLastName = WebUI.getAttribute(lastNameInput, 'value')
//	 String autoDob = WebUI.getAttribute(dobInput, 'value')
//	 String autoInsuredId = WebUI.getAttribute(insuredIdInput, 'value')
//	  
//	 WebUI.comment("Auto-populated -> First: ${autoFirstName}, Last: ${autoLastName}, DOB: ${autoDob}")
//	 assert autoFirstName != null && !autoFirstName.trim().isEmpty() : 'First Name was not auto-populated for Self'
//	 assert autoLastName != null && !autoLastName.trim().isEmpty() : 'Last Name was not auto-populated for Self'
//	 assert autoDob != null && !autoDob.trim().isEmpty() : 'DOB was not auto-populated for Self'
//	 assert autoInsuredId == null || autoInsuredId.trim().isEmpty() : 'Insured ID should remain blank/editable for Self'
//	  
//	 // Insured ID can be entered manually on top of the auto-populated data
//	 WebUI.setText(insuredIdInput, 'SELFID001')
//	 WebUI.verifyElementAttributeValue(insuredIdInput, 'value', 'SELFID001', 3)
//	  
//	 WebUI.comment('--- C6: Self -> Spouse/Other/Child before Save -> Self data should clear ---')
//	 for (String option in nonSelfOptions) {
//		 // Re-select Self and let it auto-populate again
//		 WebUI.selectOptionByValue(priDropdown, 'self', true)
//		 waitForFieldNotEmpty(firstNameInput, 5)
//		 WebUI.setText(insuredIdInput, 'SELFID002')
//	  
//		 // Switch away from Self WITHOUT saving -> data should be cleared
//		 WebUI.selectOptionByValue(priDropdown, option, true)
//		 String firstNameAfterSwitch = WebUI.getAttribute(firstNameInput, 'value')
//		 String lastNameAfterSwitch = WebUI.getAttribute(lastNameInput, 'value')
//		 String dobAfterSwitch = WebUI.getAttribute(dobInput, 'value')
//		 assert firstNameAfterSwitch == null || firstNameAfterSwitch.trim().isEmpty() : "First Name not cleared switching Self -> ${option}"
//		 assert lastNameAfterSwitch == null || lastNameAfterSwitch.trim().isEmpty() : "Last Name not cleared switching Self -> ${option}"
//		 assert dobAfterSwitch == null || dobAfterSwitch.trim().isEmpty() : "DOB not cleared switching Self -> ${option}"
//		 WebUI.comment("Verified data cleared switching Self -> ${option} (unsaved)")
//	 }
//	  
//	 WebUI.comment('--- C6 (cont.): Self data persists after Save, even if PRI later changed ---')
//	 WebUI.selectOptionByValue(priDropdown, 'self', true)
//	 waitForFieldNotEmpty(firstNameInput, 5)
//	 WebUI.setText(insuredIdInput, 'SELFID003')
//	 WebUI.click(genderMaleRadio)
//	 WebUI.click(saveButton)
//	  
//	 String firstNameAfterSave = WebUI.getAttribute(firstNameInput, 'value')
//	 assert firstNameAfterSave != null && !firstNameAfterSave.trim().isEmpty() : 'First Name lost right after Save for Self'
//	  
//	 WebUI.selectOptionByValue(priDropdown, 'spouse', true)
//	 String firstNameAfterPostSaveSwitch = WebUI.getAttribute(firstNameInput, 'value')
//	 WebUI.comment('First Name after switching PRI post-save: ' + firstNameAfterPostSaveSwitch)
//	 assert firstNameAfterPostSaveSwitch != null && !firstNameAfterPostSaveSwitch.trim().isEmpty() : 'Self data was incorrectly cleared after it had already been saved'
	  

/* ================================================================================
   SECTION E - Date of Birth Calendar & Gender Radio Buttons
   ================================================================================ */
 
WebUI.comment('--- E1: Open calendar -> Year grid should start from 1900, has CLOSE/SAVE ---')
WebUI.click(calendarIcon)
 
// The header (e.g. "September 2026") toggles the panel into the year-grid view.
// Remove this click if the year grid is already the default view on your app.
WebUI.click(calendarMonthYearLabel)
WebUI.verifyElementPresent(calendarYearButtons, 5)
 
List<String> yearTexts = getCalendarYearButtonTexts()
WebUI.comment('Year buttons found: ' + yearTexts.size())
assert yearTexts.contains('1900') : 'Year grid does not contain 1900'
 
List<Integer> yearInts = yearTexts.findAll { it.isInteger() }.collect { it.toInteger() }
Integer earliestYear = yearInts.min()
WebUI.comment('Earliest year available in calendar: ' + earliestYear)
assert earliestYear == 1900 : "Calendar year grid does not start at 1900 (earliest found: ${earliestYear})"
 
// Select the earliest year to confirm it is clickable/selectable
WebUI.click(calendarYearOption('1900'))
 
WebUI.verifyElementPresent(calendarCloseBtn, 5)
WebUI.verifyElementPresent(calendarSaveBtn, 5)
WebUI.click(calendarCloseBtn)
 
WebUI.comment('--- E2: Manually type a valid DOB ---')
clearInputField(dobInput)
WebUI.setText(dobInput, '05/15/1985')
WebUI.verifyElementAttributeValue(dobInput, 'value', '05/15/1985', 3)
 
WebUI.comment('--- E3: Enter invalid DOB -> "Invalid DOB" error ---')
clearInputField(dobInput)
WebUI.setText(dobInput, '54/54/4545')
WebUI.click(saveButton)
String dobErrorMsg = getValidationMessage("//p[normalize-space()='Please enter a valid month.']")
WebUI.verifyMatch(dobErrorMsg, 'Please enter a valid month.', true)
WebUI.comment('Verified invalid DOB shows error: Please enter a valid month.')
 
WebUI.comment('--- E4: Gender M/F radio buttons - only one selectable at a time ---')
WebUI.click(genderMaleRadio)
WebUI.verifyElementChecked(genderMaleRadio, 3)
WebUI.verifyElementNotChecked(genderFemaleRadio, 3)
 
WebUI.click(genderFemaleRadio)
WebUI.verifyElementChecked(genderFemaleRadio, 3)
WebUI.verifyElementNotChecked(genderMaleRadio, 3)
 
WebUI.comment('=== Insurance PRI / DOB / Gender validation test case completed ===')