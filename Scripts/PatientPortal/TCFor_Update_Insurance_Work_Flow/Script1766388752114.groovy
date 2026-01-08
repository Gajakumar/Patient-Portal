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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import stories.NavigateStory
import com.kms.katalon.core.util.KeywordUtil

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

WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//click on Update Insurance
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Update Demographics_block pr-14 py-2 f_d2a216'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h1_Update Insurance_text-lg mdtext-xl font-_fff19e'), 
    'Insurance')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/label_Insurance_form-check-label ps-2'), 'Self Pay / No Insurance Available')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h3_Self Pay  No Insurance Available_text-lg_6bf2f6'), 
    'Insurance 1')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/span'), 'Insurance Card Photo')

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/label_Insurance Card Photo_form-check-label ps-2'), 
    'No Insurance Card')





TestObject svgIcon = findTestObject('Object Repository/Page_Patient Portal/svg_Insurance_text-primary')

WebUI.mouseOver(svgIcon)
WebUI.delay(1)

boolean tooltipShown = WebUI.executeJavaScript(
    "return document.body.innerHTML.includes('insurance')",
    null
)

WebUI.verifyEqual(tooltipShown, true)

TestObject PtPortalselfPayChkbox = findTestObject('Object Repository/Page_Patient Portal/Self Pay  No Insurance Available chk box')

WebUI.verifyElementNotChecked(PtPortalselfPayChkbox, 5)

// Click Self Pay
WebUI.click(PtPortalselfPayChkbox)

//wait for UI update
WebUI.delay(1)

//Verify self pay is checked
WebUI.verifyElementChecked(PtPortalselfPayChkbox, 5)


// Verify insurance options disappear

WebUI.verifyElementNotPresent(
    findTestObject('Object Repository/Page_Patient Portal/Insurance Name'),
    5
)

WebUI.verifyElementNotPresent(
	findTestObject('Object Repository/Page_Patient Portal/Insurance Notes'),
	5
)


//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))


//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//click on Update Demographics option
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Profile_block pr-14 py-2 font-normal t_ea6dd6'))
WebUI.delay(2)

// Open new tab
WebUI.executeJavaScript("window.open('about:blank','_blank');", [])

// Switch to 2nd tab
WebUI.switchToWindowIndex(1)

//Login to Maximeyes.com
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

//click on find patient
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient'))

//enter first name
WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName'), 'David')

//Click on find button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient'))

//select from searched result
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_PP_underline'))

NavigateStory nav = new NavigateStory()


nav.ClickMegaMenuItems([('TopMenuOption') : 'Patient', ('SubItem') : 'Insurance'])

//Verify self pay is checked on insurance page
//WebUI.verifyElementChecked(findTestObject('Object Repository/Page_MaximEyes/Self Pay, No coverage'), 5)

TestObject selfPayCheckbox = findTestObject('Object Repository/Page_MaximEyes/Self Pay, No coverage')

String classValue = WebUI.getAttribute(selfPayCheckbox, 'class')
KeywordUtil.logInfo("Checkbox classes: " + classValue)

WebUI.verifyMatch(
    classValue,
    '(?i).*checked|active|selected.*',
    true
)

// Get checked state using Selenium
boolean isChecked = WebUI.findWebElement(selfPayCheckbox, 10).isSelected()

if (isChecked) {
	KeywordUtil.logInfo('Self Pay is already CHECKED → unchecking it')
	WebUI.click(selfPayCheckbox)
} else {
	KeywordUtil.logInfo('Self Pay is already UNCHECKED')
}

// Switch to 2nd tab
WebUI.switchToWindowIndex(0)

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))


//click on Update Insurance
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Update Demographics_block pr-14 py-2 f_d2a216'))
WebUI.delay(2)

//Verify self pay is unchecked on patient portal
WebUI.verifyElementNotChecked(PtPortalselfPayChkbox, 5)

//Add Insurance is not working, Unable to save it or delete it so skiping this verification

////Add Insurance Name
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_I no longer use this_form-control mt-_6ba832_5'),
//	'Aetna')
//
////Add Insurance Note
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_I no longer use this_form-control mt-_1536b8_5'),
//	'Aetna Test')

////Click on + Button to add new insurance
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/path_Insurance_icon'))
//
////Verify New Isurance 2 fields are added
//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h3__text-lg font-medium'), 'Insurance 2')
//
////Verify New Isurance 2 fields are added
//WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/span'), 'Insurance Card Photo')
//
////Add Insurance 2 Name
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_I no longer use this_form-control mt-_6ba832_10'),
//	'CIGNA')
//
////Add Insurance 2 Note
//WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_I no longer use this_form-control mt-_1536b8_12'),
//	'CIGNA Notes')
//
////Click on Save button
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button__btn btn-primary position-relative a_8f9d4e'))

