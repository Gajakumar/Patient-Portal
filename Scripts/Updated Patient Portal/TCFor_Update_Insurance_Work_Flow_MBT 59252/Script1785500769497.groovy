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
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

//Login to Maximeyes
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
//Get Patient ID
TestObject patientIdObj = findTestObject(
	'Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen'
)

WebUI.waitForElementVisible(patientIdObj, 15)

//Get patient Id
GlobalVariable.GV_PatientID = WebUI.getAttribute(patientIdObj, 'value') ?: ''
println "✅ Patient ID stored: " + GlobalVariable.GV_PatientID

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Send Sign Up Email to
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait for busy indicator invisible
WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'),'Patient Portal Sign Up Completed. Email Sent.')

WebUI.delay(10)

//get username and password
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)

println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password

//Navigate to Patient Portal Site
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on sign in button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter Username and password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Confirm DOB and accept terms
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/DOB Confirmation and Accept Terms'), [:], FailureHandling.STOP_ON_FAILURE)

//get otp from email
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

//enter otp
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

//Update existing password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Update Password'), [:], FailureHandling.STOP_ON_FAILURE)

//Login with new password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.UpdatePassword], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

//get otp from email
String otpA = CustomKeywords.'otp.GmailOTPHandler.readOTP'(
	'imap.gmail.com',
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	'Verification'
)

println("OTP fetched = " + otpA)


// Auto type into four input boxes
String[] digitsA = otpA.toCharArray()

//enter otp
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"), digitsA[0].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"), digitsA[1].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"), digitsA[2].toString())
WebUI.setText(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"), digitsA[3].toString())

WebUI.delay(5)


// Wait until the button is clickable (visible and enabled)
WebUI.waitForElementClickable(proceedBtn, 15, FailureHandling.STOP_ON_FAILURE)

// Click the button
WebUI.click(proceedBtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

//Click on Setting button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))

//click on Update Insurance
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Update Demographics_block pr-14 py-2 f_d2a216'))

//Verify field on Insurance Screen
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h1_Update Insurance_text-lg mdtext-xl font-_fff19e'), 
    'Insurance')
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/label_Insurance_form-check-label ps-2'), 'Self Pay / No Insurance Available')

//Self Pay check box
TestObject PtPortalselfPayChkbox = findTestObject('Object Repository/Page_Patient Portal/Self Pay  No Insurance Available chk box')

//Verify self pay is  checked by default
WebUI.verifyElementChecked(PtPortalselfPayChkbox, 5)

//+ icon
TestObject svgAddInsIcon = findTestObject('Object Repository/Page_Patient Portal/svg_Insurance_text-primary')


//Hover on + icon
WebUI.mouseOver(svgAddInsIcon)
WebUI.delay(1)

//Get tool tip
boolean tooltipShown = WebUI.executeJavaScript(
	"return document.body.innerHTML.includes('insurance')",
	null
)

//Verify tool tip
WebUI.verifyEqual(tooltipShown, true)

// Click on add insurance + button
WebUI.click(svgAddInsIcon)

// Verify insurance options disappear
WebUI.verifyElementNotPresent(
    findTestObject('Object Repository/Page_Patient Portal/Insurance Name'),
    5
)

WebUI.verifyElementNotPresent(
	findTestObject('Object Repository/Page_Patient Portal/Insurance Notes'),
	5
)

// Click Self Pay
WebUI.click(PtPortalselfPayChkbox)

//wait for UI update
WebUI.delay(1)

//Verify self pay is unchecked
WebUI.verifyElementNotChecked(PtPortalselfPayChkbox, 5)

// Click on add insurance + button
WebUI.click(svgAddInsIcon)

WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h3_Self Pay  No Insurance Available_text-lg_6bf2f6'), 
    'Insurance 1')
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/Ins Card Photo Ins 1'), 'Insurance Card Photo')
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/label_Insurance Card Photo_form-check-label ps-2'), 
    'No Insurance Card')
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/I no longer use this'),
	'I no longer use this')

// Verify insurance options present
WebUI.verifyElementPresent(
	findTestObject('Object Repository/Page_Patient Portal/Insurance Name'),
	5
)

WebUI.verifyElementPresent(
	findTestObject('Object Repository/Page_Patient Portal/Insurance Notes'),
	5
)



// Open new tab
WebUI.executeJavaScript("window.open('about:blank','_blank');", [])

// Switch to 2nd tab
WebUI.switchToWindowIndex(1)

//Login to Maximeyes.com
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)


//Find Patient
WebUI.callTestCase(
		findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
		[('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.OPTIONAL)


//Navigate to Insurance
NavigateStory nav = new NavigateStory()
nav.ClickMegaMenuItems([('TopMenuOption') : 'Patient', ('SubItem') : 'Insurance'])

//Verify self pay is checked on insurance page
WebUI.verifyElementChecked(findTestObject('Object Repository/Page_MaximEyes/Self Pay, No coverage'), 5)



//// Get checked state using Selenium
//boolean isChecked = WebUI.findWebElement(selfPayCheckbox, 10).isSelected()
//
//if (isChecked) {
//	KeywordUtil.logInfo('Self Pay is already CHECKED → unchecking it')
//	WebUI.click(selfPayCheckbox)
//} else {
//	KeywordUtil.logInfo('Self Pay is already UNCHECKED')
//}

// Switch to 2nd tab
WebUI.switchToWindowIndex(0)

//Click on Home icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))

//Click on setting icon
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))


//click on Update Insurance
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Update Demographics_block pr-14 py-2 f_d2a216'))
WebUI.delay(2)

// Click Self Pay
WebUI.click(PtPortalselfPayChkbox)

//wait for UI update
WebUI.delay(1)

//Verify self pay is unchecked
WebUI.verifyElementNotChecked(PtPortalselfPayChkbox, 5)

// Click on add insurance + button
WebUI.click(svgAddInsIcon)


//Add Insurance Name
WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_I no longer use this_form-control mt-_6ba832_5'),'Aetna')

//Add Insurance Note
WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_I no longer use this_form-control mt-_1536b8_5'),'Aetna Test')

//Click on Save button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button__btn btn-primary position-relative a_8f9d4e'))
WebUI.delay(3)

// Switch to 2nd tab
WebUI.switchToWindowIndex(1)


//Find Patient
WebUI.callTestCase(
		findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'),
		[('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.OPTIONAL)


//Navigate to Insurance
nav.ClickMegaMenuItems([('TopMenuOption') : 'Patient', ('SubItem') : 'Insurance'])


/* =========================================================
 * Test: Online Intake > Unprocessed > Aetna > (+) > Save >
 *       Import Insurance details for Selected > Verify
 * ========================================================= */

// ---------- helpers ----------
TestObject xp(String xpath) {
    TestObject to = new TestObject(xpath)
    to.addProperty('xpath', ConditionType.EQUALS, xpath)
    return to
}

// 1) Selenium Actions click (real mouse event, good for DevExtreme)
// 2) normal Katalon click
// 3) JS click as a last resort
void safeClick(TestObject to, int timeout) {
    WebUI.waitForElementPresent(to, timeout)
    WebUI.scrollToElement(to, 3, FailureHandling.OPTIONAL)
    try {
        WebElement el = WebUI.findWebElement(to, timeout)
        new Actions(DriverFactory.getWebDriver()).moveToElement(el).click().perform()
    } catch (Throwable e1) {
        WebUI.comment("Actions click failed, trying normal click: " + e1.getMessage())
        try {
            WebUI.click(to, FailureHandling.STOP_ON_FAILURE)
        } catch (Throwable e2) {
            WebUI.comment("Normal click failed, using JS click: " + e2.getMessage())
            WebElement el2 = WebUI.findWebElement(to, timeout)
            WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(el2))
        }
    }
}

// Wait until the dark modal overlay (b-modal) is gone
void waitForOverlayGone(TestObject overlay, int timeout) {
    WebUI.waitForElementNotVisible(overlay, timeout, FailureHandling.OPTIONAL)
}

int T = 15

// ---------- locators ----------
// Overlay
TestObject overlay        = xp("//div[contains(@class,'b-modal')]")

// Online Intake grid (Insurance tab)
TestObject rdoUnprocessed = xp("//label[contains(.,'Unprocessed')]//input[@type='radio']")
TestObject rowIntakeAetna = xp("//table[@id='tblEligibilityAuth']//tr[@data-insurance_company_name='Aetna'][1]")

// Popup 1: Insured details from patient
TestObject lblInsuredDtls = xp("//h3[contains(normalize-space(),'INSURED DETAILS FROM PATIENT')]")
TestObject btnAddNewIns   = xp("//h3[contains(normalize-space(),'SELECT AN INSURANCE TO UPDATE')]//button[@title='Add New Insurance']/span[contains(@onclick,'closemodelandcallnew')]")
TestObject rowSelectAetna = xp("//div[@id='tblInsuranceCompaniesjsPanelPopup']//td[@role='gridcell' and normalize-space()='Aetna']")
TestObject btnImport      = xp("//input[@id='btnInsSave' and @value='Import Insurance details for Selected']")

// Popup 2: Find insurances to add
TestObject lblFindIns     = xp("//h3[contains(normalize-space(),'Find Insurances to add')]")
TestObject txtSearchInsCo = xp("//div[@id='tblInsuranceCompanies']//input[contains(@class,'dx-texteditor-input')]")
// Fixed-column overlay copy of the row: this is the cell that actually receives the click
TestObject rowFindAetna   = xp("//div[@id='tblInsuranceCompanies']//div[contains(@class,'dx-datagrid-content-fixed')]//td[@role='gridcell' and normalize-space()='Aetna']")
TestObject rowAddedAetna  = xp("//tbody[@id='tblInsuranceGrid']//td[@title='Aetna']")
TestObject btnSave        = xp("//input[@id='btnInsSave' and @value='SAVE']")

// Final verification (Insurance tab)
TestObject lnkAetna       = xp("//a[contains(@class,'patientInsLink')][@data-insurance-name='Aetna']")
TestObject lblAetna       = xp("//a[contains(@class,'patientInsLink')][@data-insurance-name='Aetna']//div[contains(@class,'InsuranceCName')]")

/* =========================================================
 * STEP 1: Intake queue shows "Unprocessed"
 * ========================================================= */
WebUI.waitForElementPresent(rdoUnprocessed, T)
WebUI.verifyElementChecked(rdoUnprocessed, T)

/* =========================================================
 * STEP 2: Open the Aetna intake record (popup 1)
 * ========================================================= */
WebUI.waitForElementClickable(rowIntakeAetna, T)
WebUI.click(rowIntakeAetna)
WebUI.waitForElementVisible(lblInsuredDtls, T)

// Import button must be disabled until an insurance is selected
WebUI.verifyElementHasAttribute(btnImport, 'disabled', T)

/* =========================================================
 * STEP 3: Click (+) Add New Insurance (popup 1 closes, popup 2 opens)
 * ========================================================= */
safeClick(btnAddNewIns, T)

WebUI.waitForElementNotPresent(lblInsuredDtls, T, FailureHandling.OPTIONAL)
waitForOverlayGone(overlay, 3)
WebUI.waitForElementVisible(lblFindIns, T)
WebUI.waitForElementVisible(txtSearchInsCo, T)

/* =========================================================
 * STEP 4: Find Aetna and add it
 * ========================================================= */
WebUI.click(txtSearchInsCo)
WebUI.clearText(txtSearchInsCo, FailureHandling.OPTIONAL)
WebUI.sendKeys(txtSearchInsCo, 'Aetna')
WebUI.delay(2)                                    // let the DevExtreme filter finish

WebUI.waitForElementPresent(rowFindAetna, T)
safeClick(rowFindAetna, T)
//WebUI.verifyElementPresent(rowAddedAetna, T)

/* =========================================================
 * STEP 5: SAVE
 * ========================================================= */
safeClick(btnSave, T)
WebUI.waitForElementNotPresent(lblFindIns, T, FailureHandling.OPTIONAL)
waitForOverlayGone(overlay, T)

/* =========================================================
 * STEP 6: Reopen intake record, select Aetna, import
 * ========================================================= */
//WebUI.waitForElementClickable(rowIntakeAetna, T)  // grid is re-rendered, so find it again
//WebUI.click(rowIntakeAetna)
//WebUI.waitForElementVisible(lblInsuredDtls, T)
//
//safeClick(rowSelectAetna, T)

// Import button should now be enabled
WebUI.verifyElementNotHasAttribute(btnImport, 'disabled', T)

int beforeCount = WebUI.findWebElements(lnkAetna, 3).size()
WebUI.comment("Aetna links before import: " + beforeCount)

safeClick(btnImport, T)

/* =========================================================
 * STEP 7: Verify insurance added
 * ========================================================= */
WebUI.waitForElementNotPresent(lblInsuredDtls, T, FailureHandling.OPTIONAL)
waitForOverlayGone(overlay, T)
WebUI.waitForElementVisible(lnkAetna, T)

int afterCount = WebUI.findWebElements(lnkAetna, T).size()
WebUI.comment("Aetna links after import: " + afterCount)
assert afterCount >= 1 : "Aetna insurance link not found on Insurance tab"

WebUI.verifyElementPresent(lnkAetna, T)
WebUI.verifyElementAttributeValue(lnkAetna, 'data-insurance-name', 'Aetna', T)
WebUI.verifyElementAttributeValue(lnkAetna, 'data-priority', 'Primary', T)
WebUI.verifyElementAttributeValue(lnkAetna, 'data-deleted', 'False', T)
WebUI.verifyElementText(lblAetna, 'Aetna (P/M)', FailureHandling.CONTINUE_ON_FAILURE)

WebUI.comment("PASS: Aetna insurance imported from Online Intake.")



//// Switch to 2nd tab
//WebUI.switchToWindowIndex(0)
//
////Click on setting icon
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))
//
//
////click on Update Insurance
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span_Update Demographics_block pr-14 py-2 f_d2a216'))
//WebUI.delay(2)
//
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
//
////Verify toast
//CustomKeywords.'common.ToastHelper.verifyToastMessage'('Insurance information saved successfully')
