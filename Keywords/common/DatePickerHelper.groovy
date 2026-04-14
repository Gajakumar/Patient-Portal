package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.*
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

class DatePickerHelper {

@Keyword
def selectDOB(String dob) {

	// ============================
	// Validate & Split DOB
	// ============================
	if (!dob || !dob.contains("/")) {
		throw new Exception("Invalid DOB format. Use MM/DD/YYYY")
	}

	String[] parts = dob.split("/")
	if (parts.length < 3) {
		throw new Exception("Invalid DOB format. Use MM/DD/YYYY")
	}

	int targetMonth = Integer.parseInt(parts[0])
	int targetDay   = Integer.parseInt(parts[1])
	String targetYear  = parts[2]

	// ============================
	// Month Name → Number Map (FIXED)
	// ============================
	Map monthMap = [
		"January":1, "February":2, "March":3, "April":4,
		"May":5, "June":6, "July":7, "August":8,
		"September":9, "October":10, "November":11, "December":12
	]

	// ============================
	// Test Objects
	// ============================
	TestObject dobField = new TestObject()
	dobField.addProperty("xpath", ConditionType.EQUALS, "//input[@placeholder='MM/DD/YYYY']")

	TestObject header = new TestObject()
	header.addProperty("xpath", ConditionType.EQUALS, "//span[contains(@class,'cursor-pointer')]")

	TestObject prevBtn = new TestObject()
	prevBtn.addProperty("xpath", ConditionType.EQUALS, "(//div[contains(@class,'flex') and contains(@class,'items-center')]//button)[1]")

	TestObject nextBtn = new TestObject()
	nextBtn.addProperty("xpath", ConditionType.EQUALS, "(//div[contains(@class,'flex') and contains(@class,'items-center')]//button)[2]")

	// ============================
	// Step 1: Open Calendar
	// ============================
	WebUI.waitForElementVisible(dobField, 10)
	WebUI.click(dobField)

	// ============================
	// Step 2: Open Year Selection
	// ============================
	WebUI.waitForElementVisible(header, 10)
	WebUI.click(header)

	// ============================
	// Step 3: Select Year
	// ============================
	TestObject yearObj = new TestObject()
	yearObj.addProperty("xpath", ConditionType.EQUALS, "//button[normalize-space()='" + targetYear + "']")

	WebUI.waitForElementVisible(yearObj, 10)
	WebUI.click(yearObj)

	// ============================
	// Step 4: Get Current Month
	// ============================
	String current = WebUI.getText(header)
	current = current.replaceAll("\\s+", " ").trim()

	println("Header text: " + current)

	if (!current.contains(" ")) {
		throw new Exception("Invalid header format: " + current)
	}

	String[] currParts = current.split(" ")
	String currMonthName = currParts[0]

	Integer currMonth = monthMap.get(currMonthName)

	if (currMonth == null) {
		throw new Exception("Month not found: " + currMonthName)
	}

	// ============================
	// Step 5: Adjust Month (FAST)
	// ============================
	int diff = currMonth - targetMonth

	if (diff > 0) {
		for (int i = 0; i < diff; i++) {
			WebUI.click(prevBtn, FailureHandling.CONTINUE_ON_FAILURE)
		}
	} else if (diff < 0) {
		for (int i = 0; i < Math.abs(diff); i++) {
			WebUI.click(nextBtn, FailureHandling.CONTINUE_ON_FAILURE)
		}
	}

	// ============================
	// Step 6: Select Day
	// ============================
	TestObject dayObj = new TestObject()
	dayObj.addProperty("xpath", ConditionType.EQUALS,
		"//button[not(contains(@class,'text-gray-400')) and text()='" + targetDay + "']"
	)

	WebUI.waitForElementVisible(dayObj, 10)
	WebUI.click(dayObj)

	// ============================
	// Optional: Click SAVE
	// ============================
	TestObject saveBtn = new TestObject()
	saveBtn.addProperty("xpath", ConditionType.EQUALS, "//button[text()='SAVE']")

	if (WebUI.verifyElementPresent(saveBtn, 3, FailureHandling.OPTIONAL)) {
		WebUI.click(saveBtn)
	}
    }
}
