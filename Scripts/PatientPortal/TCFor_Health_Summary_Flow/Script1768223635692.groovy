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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

//Login to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Sign in With User Name and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : PatientLoginName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

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


//Click on Health Summary
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_David Smith_border-2 rounded-full p-4 s_33889a'))

//Verify Date Range displayed from todays to next month
// Formatter matching UI format (MM/dd/yyyy)
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

// Calculate dates
LocalDate today = LocalDate.now()
LocalDate lastYear = today.minusYears(1)

// Build expected value
String expectedDateRange = "${lastYear.format(formatter)} - ${today.format(formatter)}"

// Find object
TestObject dateRange = findTestObject(
    'Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/input_Health Summary_flex-grow text-sm text_4de207'
)

// Verify
WebUI.verifyElementAttributeValue(
    dateRange,
    'value',
    expectedDateRange,
    10
)

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/input_Health Summary_flex-grow text-sm text_4de207'))

WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Health Summary'))

//Clear the date field
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/input_Health Summary_flex-grow text-sm text_4de207_1'), 
    '')
//Enter Invalid Date
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/input_Health Summary_flex-grow text-sm text_4de207_9'), 
    '00/00/2020')

//Verify error msg is displayed
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/p_Please enter valid date range'), 
    'Please enter valid date range')

//Click on Date field
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Health SummaryPlease enter valid date range'))

//Clear the date field
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/input_Health Summary_flex-grow text-sm text_4de207_1'), 
    '')

//Inter date range 11/11/1111
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/input_Health Summary_flex-grow text-sm text_4de207_17'), 
    '11/11/1111')

//Verify error msg is displayed
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/p_Please enter date range above year 1900'), 
    'Please enter date range above year 1900')

//Click on Date field
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Health SummaryPlease enter date range a_6fe300'))

//Clear the date field
WebUI.setText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/input_Health Summary_flex-grow text-sm text_4de207_1'), 
    '')

//Click on calender icon
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/svg_Health Summary_a'))

//Verify Visit date calender popup is opened
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/h4_Visit Date'), 'Visit Date')

//Verify fields on Visit date popup
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/h4_Quick Select'), 'Quick Select')
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Today'), 'Today')
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Yesterday'), 'Yesterday')
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Last 7 days'), 
    'Last 7 days')
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Last 30 days'), 
    'Last 30 days')
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_This Month'), 
    'This Month')
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Last Month'), 
    'Last Month')
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_This Year'), 'This Year')

//Verify cancel button is present
WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Cancel'), 5)

//Verify confirm button is prasent
WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Confirm'), 
    5)
//Click on cancel button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Cancel'))

//Click on calender icon
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/svg_Health Summary_a'))


// Calculate expected values
LocalDate startDate = today.withDayOfMonth(1)
LocalDate endDate   = today.plusMonths(1).withDayOfMonth(1)

String expectedSelectedRange =
		"${startDate.format(formatter)} - ${endDate.format(formatter)}"

// Open calendar
WebUI.click(dateRange)

// ----------------------------
// Select 01 from CURRENT month (LEFT calendar)
// ----------------------------
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_1'))

// ----------------------------
// Select 01 from NEXT month (RIGHT calendar)
// ----------------------------
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_1_1'))

// Confirm selection
WebUI.click(findTestObject(
	'Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Confirm'
))

// ====================================================
// VERIFY selected date range
// ====================================================
WebUI.verifyElementAttributeValue(
	dateRange,
	'value',
	expectedSelectedRange,
	10
)

