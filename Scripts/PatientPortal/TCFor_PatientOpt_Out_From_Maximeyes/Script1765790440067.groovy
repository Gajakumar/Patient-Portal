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
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
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

String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Create Random Patient in Maximeyes'), [:], FailureHandling.STOP_ON_FAILURE)

//Get Patient ID
TestObject patientIdObj = findTestObject(
    'Object Repository/Page_MaximEyes/Patient_Overview/Patient ID on Overview Screen'
)

WebUI.waitForElementVisible(patientIdObj, 15)

GlobalVariable.GV_PatientID =
    WebUI.getAttribute(patientIdObj, 'value') ?: ''

println "✅ Patient ID stored: " + GlobalVariable.GV_PatientID

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'),'Patient Portal Sign Up Completed. Email Sent.')

CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(
	GlobalVariable.MyEmail_Id,
	GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email,
	"Access to your health data"
)

println "Username: " + GlobalVariable.GV_Username
println "Password: " + GlobalVariable.GV_Password



WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/span_Patient Portal_PtOverviewResetPatientT_a02aab'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/span_Opt-outDisabled Patient Portal account_b24fb6'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/input_Edit Email Address_btnproceed'))

// 1. Locate your canvas
TestObject canvasObj = findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/canvas_Provider Signature_pad')

// 2. Wait until canvas becomes visible
WebUI.waitForElementVisible(canvasObj, 30)

// 3. Get WebElement
WebElement canvasElement = WebUI.findWebElement(canvasObj)

if(canvasElement == null){
	KeywordUtil.markFailed("❌ Canvas not found! Check XPath.")
}

// 4. Use Actions to draw
Actions actions = new Actions(DriverFactory.getWebDriver())

// Starting point inside the canvas
int startX = canvasElement.getLocation().getX() + (canvasElement.getSize().width / 4)
int startY = canvasElement.getLocation().getY() + (canvasElement.getSize().height / 2)

actions.moveByOffset(startX, startY)
	   .clickAndHold()
	   .moveByOffset(50, 10)     // draw line 1
	   .moveByOffset(30, -20)    // draw line 2
	   .moveByOffset(40, 15)     // draw line 3
	   .moveByOffset(-20, 10)    // draw line 4
	   .release()
	   .perform()

println("✔ Signature drawn successfully!")

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/input_Provider Signature_optOutPatient'))

//Mouse Hover On Green Check
WebUI.mouseOver(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient Portal Green Check On Overview'))

//Verify Text on Hover
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/div__no-margin font14 pad15'),Text)

WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/span_Patient Portal_PtOverviewOptOutDatePP'), expectedDate)

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/a_Overview_PatientDetailsTabLink'))

WebUI.click(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/a_Office Admin  General  Documents_ui-id-35'))

WebUI.delay(3)

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Verify Opt Out Text
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h2_Opt-In Options_text-white me-2'), OptOutText)

//Verify Opt Out Text
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/h3_Do you want to Opt-In_text-dark me-2'),'You have been opted out on '+ expectedDate +' by Self.')

//Click on Opt Out Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Opt Out Button On Patient Portal'))

//Click on Sign button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter UserName and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Click on Opt In button Patient Portal
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Opt-In_btn on Patient Portal'))

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Enter UserName and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login With Username and Password'), [('Username') : GlobalVariable.GV_Username, ('Password') : GlobalVariable.GV_Password], FailureHandling.STOP_ON_FAILURE)

//Click on Opt In Button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_You have been opted out on 12152025 _84a11e'))

WebUI.delay(3)

//Enter Blank DOB
WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), '')

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/ProccedBtnAftrDOBConfirm'))

//Verify DOB Required alart displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Confirm DOB Screen/DOB Required Text'),DOBAlart)

//Enter Invalid  DOB
WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), '99/99/9999')

//Verify DOB Required alart displayed
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Confirm DOB Screen/DOB Required Text'),ValidDOBAlart)

//Enter Valid DOB
WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/ConfirmDOB'), GlobalVariable.DOB)

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/ProccedBtnAftrDOBConfirm'))

//Do not Accept Terms and click on Procced button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))

//Verify Please Accpet Terms toast display
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Terms Of Service Page/Please Accept Terms Alart'),AcceptTermToast)

WebUI.delay(3)

//Accept Terms check box
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))

//Do not enter sign and click on Procced button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))

////Verify Please Enter Sign toast display
//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Add Your Sign Toast'),AddSignToast)

TestObject toast = findTestObject(
	'Object Repository/PatientPortal/SignInPage_Patient Portal/Add Your Sign Toast'
)

WebUI.waitForElementPresent(toast, 10)

String toastText = WebUI.getText(toast).trim()
println "Toast found: " + toastText

WebUI.verifyMatch(toastText, AddSignToast, false)

WebUI.delay(3)

//Uncheck Accpet Term check box
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))

//Add Signature
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/Add Signature On Canvas'),[:], FailureHandling.STOP_ON_FAILURE)

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))

//Verify Please Accpet Terms toast display
WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Terms Of Service Page/Accpet Terms of Service Toast'),AcceptTermToast)

WebUI.delay(3)

//Accept Terms check box
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Procced Buttono Accept Terms Of Service Page'))


WebUI.waitForElementVisible(
	findTestObject('Object Repository/Page_Patient Portal/Welcome Back Toast After Opt In'),
	10
)

//Verify user is landed on home screen
WebUI.verifyElementText(findTestObject('Object Repository/Page_Patient Portal/Welcome Back Toast After Opt In'),WelcomeText)

//Login To Maximeyes
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Comman/User Login in Maximeyes Pt Portal'), [:], FailureHandling.STOP_ON_FAILURE)

//Search Patient
WebUI.callTestCase(findTestCase('Test Cases/common/Maximeyes/Find Patient Using Patient ID'), [('PatientID'): GlobalVariable.GV_PatientID], FailureHandling.STOP_ON_FAILURE)

//Mouse Hover On Green Check
WebUI.mouseOver(findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/Patient Portal Green Check On Overview'))

//Verify Text on Hover
CustomKeywords.'common.MessageVerifier.verifyFullText'(
	findTestObject('Object Repository/Page_MaximEyes/Patient_Overview/div__no-margin font14 pad15'),
	AfterOptInTextonOverview
)





