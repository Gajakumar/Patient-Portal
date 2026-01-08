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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.testobject.TestObject
import java.time.*
import java.time.format.DateTimeFormatter
import org.openqa.selenium.WebElement
//Login to Patient Portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

//Click on Sign In Button
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/SignInBtn'))

//Sign in With User Name and Password
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/User Login With Username and Password'), [('Username') : PatientName, ('Password') : GlobalVariable.RestUpdatedPass], FailureHandling.STOP_ON_FAILURE)

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

//Click on Setting Icon
WebUI.click(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Update_Insurance_Screen/Setting Icon on Portal'))

//Select Activity Log
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/span_Authorized Individuals_block pr-14 py-_f7d1ec'))

//Verify Activity Log page is displayed
WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/h1_ZD_display-6 fw-semibold'), 
    0)

//Verify Date Field is displayed
WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/div_Date_px-3 py-2 border border-gray-300 r_2058d1'), 
    0)

//Verify Action Dropdown is displayed
WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/select_Action_action-select'), 
    0)

//Verify Date time column is displayed with Asending/Descending column
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/th_Action_sortable'), 
    'Date & Time ↓')

//Verify User column is displayed with Asending/Descending column
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/th_Date  Time_sortable'), 
    'User ↓')

//Verify Action column is displayed with Asending/Descending column
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/th_User_sortable'), 'Action ↓')

//Click on Date Field
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/input_Date_flex-grow text-sm text-gray-700 _ec6023'))

//Click on Calender icon
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/svg_Date_a'))

//Verify calender is open with Today -This Year selection
WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Custom_block w-full text-left p-2 te_24c6aa'), 
    'Today')

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Today_block w-full text-left p-2 tex_c858af'), 
    'Yesterday')

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Yesterday_block w-full text-left p-2_2e0646'), 
    'Last 7 days')

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Last 7 days_block w-full text-left p_65634a'), 
    'Last 30 days')

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Last 30 days_block w-full text-left _272ea7'), 
    'This Month')

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_This Month_block w-full text-left p-_803087'), 
    'Last Month')

WebUI.verifyElementText(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Last Month_block w-full text-left p-_87c594'), 
    'This Year')

//Verify Cancel button is displayed on calender
WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Sat_px-4 py-2 text-sm text-gray-600 _0effdf'), 
    0)

//Verify okay button is displayed on calender
WebUI.verifyElementPresent(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Cancel_px-4 py-2 text-sm bg-blue-500_c90599'), 
    0)

//Click on Cancel button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/button_Sat_px-4 py-2 text-sm text-gray-600 _0effdf'))


// ================= Test Objects =================
TestObject actionDropdown = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/select_Action_action-select')
TestObject dateTimeCells  = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Date Time Rows')
TestObject sortUpArrow    = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Date Time Down  and Up Arrow')
TestObject sortDownArrow  = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Date Time Down  and Up Arrow')

// ================= DATE FORMAT (GMT) =================
ZoneId gmtZone = ZoneId.of('GMT')

DateTimeFormatter formatter =
    DateTimeFormatter.ofPattern('MM/dd/yyyy hh:mm:ss a', Locale.US)

// ================= CURRENT WEEK (GMT) =================
ZonedDateTime nowGMT = ZonedDateTime.now(gmtZone)

ZonedDateTime weekStartGMT =
    nowGMT.with(DayOfWeek.MONDAY)
          .toLocalDate()
          .atStartOfDay(gmtZone)

ZonedDateTime weekEndGMT =
    nowGMT.with(DayOfWeek.SUNDAY)
          .toLocalDate()
          .atTime(LocalTime.MAX)
          .atZone(gmtZone)

// ================= SCENARIO 3 =================
// Action should be "All"
WebUI.verifyOptionSelectedByLabel(
    actionDropdown,
    'All',
    false,
    5
)

// Fetch all Date & Time values
List<WebElement> rows =
    WebUiCommonHelper.findWebElements(dateTimeCells, 10)
	println 'Total Rows: '+ rows.size()
assert rows.size() > 0 : '❌ No Activity Logs found'

// Parse UI DateTime safely (handle hidden spaces)
List<ZonedDateTime> logDates = rows.collect { WebElement el ->

    String cleanText = el.text
        .replace('\u00A0', ' ')      // non-breaking space
        .replaceAll('\\s+', ' ')     // collapse spaces
        .trim()

    return LocalDateTime.parse(cleanText, formatter)
                        .atZone(gmtZone)
}

// Verify dates are in current week (GMT)
logDates.each { ZonedDateTime dt ->
    assert !dt.isBefore(weekStartGMT) && !dt.isAfter(weekEndGMT) :
        "❌ Log date not in current week (GMT): ${dt}"
}

// ================= SCENARIO 4 =================
// Default sorting should be DESCENDING
List<ZonedDateTime> expectedDesc =
    logDates.sort { a, b -> b <=> a }

assert logDates == expectedDesc :
    '❌ Logs are NOT sorted in Descending order by default'

// ================= SCENARIO 5 =================
// Click UP arrow → ASCENDING
WebUI.click(sortUpArrow)
WebUI.delay(2)

List<ZonedDateTime> ascDates =
    WebUiCommonHelper.findWebElements(dateTimeCells, 10)
        .collect { WebElement el ->

            String cleanText = el.text
                .replace('\u00A0', ' ')
                .replaceAll('\\s+', ' ')
                .trim()

            LocalDateTime.parse(cleanText, formatter)
                         .atZone(gmtZone)
        }

assert ascDates == ascDates.sort() :
    '❌ Logs NOT sorted in Ascending order after clicking UP arrow'

// ================= SCENARIO 6 =================
// Click DOWN arrow → DESCENDING
WebUI.click(sortDownArrow)
WebUI.delay(2)

List<ZonedDateTime> descDates =
    WebUiCommonHelper.findWebElements(dateTimeCells, 10)
        .collect { WebElement el ->

            String cleanText = el.text
                .replace('\u00A0', ' ')
                .replaceAll('\\s+', ' ')
                .trim()

            LocalDateTime.parse(cleanText, formatter)
                         .atZone(gmtZone)
        }

assert descDates == descDates.sort { a, b -> b <=> a } :
    '❌ Logs NOT sorted in Descending order after clicking DOWN arrow'

	
	import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
	import com.kms.katalon.core.testobject.TestObject
	
	/* =========================
	   COMMON SORT VALIDATION
	   ========================= */
	def verifySorted(List<String> actual, boolean asc, String columnName) {
	
		assert actual.size() > 0 : "❌ No data found in ${columnName} column"
	
		List<String> expected = new ArrayList<>(actual)
	
		expected.sort { a, b ->
			asc ? a.compareToIgnoreCase(b) : b.compareToIgnoreCase(a)
		}
	
		assert actual == expected :
				"❌ ${columnName} column NOT sorted correctly (${asc ? 'ASC' : 'DESC'})"
	
		WebUI.comment("✅ ${columnName} sorted correctly (${asc ? 'ASC' : 'DESC'})")
	}
	
	/* =========================
	   GENERIC SORT TEST
	   ========================= */
	def validateColumnSort(TestObject sortHeader, TestObject columnCells, String columnName) {
	
		// ---- ASCENDING ----
		WebUI.click(sortHeader)
		WebUI.delay(2)
	
		List<String> ascValues = WebUI.findWebElements(columnCells, 10)
				.findAll { it.isDisplayed() }
				.collect { it.getText().trim() }
	
		verifySorted(ascValues, true, columnName)
	
		// ---- DESCENDING ----
		WebUI.click(sortHeader)
		WebUI.delay(2)
	
		List<String> descValues = WebUI.findWebElements(columnCells, 10)
				.findAll { it.isDisplayed() }
				.collect { it.getText().trim() }
	
		verifySorted(descValues, false, columnName)
	}
	
	/* =========================
	   TEST OBJECT MAPPING
	   ========================= */
	TestObject userSortHeader   = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/User Column Sorting')
	TestObject userColumnCells  = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/User Column Sorting')
	
	TestObject actionSortHeader = findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Action Column Sorting')
	TestObject actionColumnCells= findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_Patient Portal/Action Column Sorting')
	
	/* =========================
	   EXECUTION
	   ========================= */
	validateColumnSort(userSortHeader, userColumnCells, "User")
	validateColumnSort(actionSortHeader, actionColumnCells, "Action")
	



