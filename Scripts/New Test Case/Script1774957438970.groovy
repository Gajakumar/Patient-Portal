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

import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.*
import com.kms.katalon.core.testobject.ConditionType


//Navigate to Patient portal
WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Navigate to Patient Portal Site'), [:], FailureHandling.STOP_ON_FAILURE)

// ======================================================
// 🔹 TEST DATA
// ======================================================
String searchText = "ed"

// ======================================================
// 🔹 TEST OBJECTS (ALL DECLARED AT START)
// ======================================================

// Static Objects
TestObject searchBox     = findTestObject('Object Repository/PatientPortal/Page_Patient Portal/Message Screen/Search Message')
//TestObject inboxHeader   = findTestObject('Page_Messages/label_Inbox')

// Dynamic Objects
TestObject resultsObj = new TestObject("resultsObj")
resultsObj.addProperty("xpath", ConditionType.EQUALS,
	"//section[@aria-label='Messages list']//div[contains(@class,'border-b')]")

TestObject highlightObj = new TestObject("highlightObj")
highlightObj.addProperty("xpath", ConditionType.EQUALS,
	"//mark | //span[contains(@class,'highlight')]")

TestObject checkboxObj = new TestObject("checkboxObj")
checkboxObj.addProperty("xpath", ConditionType.EQUALS,
	"//section[@aria-label='Messages list']//input[@type='checkbox']")

//TestObject folderObj = new TestObject("folderObj")
//folderObj.addProperty("xpath", ConditionType.EQUALS,
//	"//span[contains(@class,'folder-name')]")
//
//TestObject noResultObj = new TestObject("noResultObj")
//noResultObj.addProperty("xpath", ConditionType.EQUALS,
//	"//*[text()='No messages matched your search.']")
//
//TestObject deleteBtn = new TestObject("deleteBtn")
//deleteBtn.addProperty("xpath", ConditionType.EQUALS,
//	"(//button[contains(@class,'delete')])[1]")

// ======================================================
// 🔹 STEP 1: MINIMUM CHARACTER SEARCH (1 CHAR)
// ======================================================
WebUI.waitForElementVisible(searchBox, 10)

WebUI.clearText(searchBox)
WebUI.setText(searchBox, "a")
WebUI.sendKeys(searchBox, Keys.chord(Keys.ENTER))
WebUI.delay(2)

KeywordUtil.logInfo("✅ Minimum 1 character search executed")

// ======================================================
// 🔹 STEP 2: MAIN SEARCH
// ======================================================
WebUI.clearText(searchBox)
WebUI.setText(searchBox, searchText)
WebUI.sendKeys(searchBox, Keys.chord(Keys.ENTER))
WebUI.delay(2)

KeywordUtil.logInfo("✅ Search performed with text: " + searchText)

// ======================================================
// 🔹 STEP 3: FETCH RESULTS
// ======================================================
List<WebElement> results = WebUI.findWebElements(resultsObj, 10)

if (results.size() > 0) {

	KeywordUtil.logInfo("✅ Results found: " + results.size())

	// --------------------------------------------------
	// 🔸 Highlight Validation (Tailwind Pink FIXED)
	// --------------------------------------------------
	List<WebElement> highlights = WebUI.findWebElements(highlightObj, 10)

	boolean isPinkFound = false

	for (WebElement el : highlights) {

		String bgColor   = el.getCssValue("background-color")
		String classAttr = el.getAttribute("class")

		KeywordUtil.logInfo("BG Color: " + bgColor)
		KeywordUtil.logInfo("Class: " + classAttr)

		// ✅ PRIMARY (CLASS CHECK)
		if (classAttr != null && classAttr.contains("bg-pink-300")) {
			isPinkFound = true
			break
		}

		// ✅ FALLBACK (COLOR CHECK)
		if (bgColor != null && bgColor.contains("249, 168, 212")) {
			isPinkFound = true
			break
		}
	}

	if (!isPinkFound) {
		KeywordUtil.markFailed("❌ Highlight color is NOT Tailwind Pink (bg-pink-300)")
	} else {
		KeywordUtil.logInfo("✅ Highlight color validated (bg-pink-300)")
	}

	// --------------------------------------------------
	// 🔸 No Checkbox Validation
	// --------------------------------------------------
	List<WebElement> checkboxes = WebUI.findWebElements(checkboxObj, 5)

	if (checkboxes.size() > 0) {
		KeywordUtil.markFailed("❌ Checkbox should NOT be visible in search results")
	} else {
		KeywordUtil.logInfo("✅ No checkbox present")
	}


	

//	// --------------------------------------------------
//	// 🔸 Folder Name Validation
//	// --------------------------------------------------
//	List<WebElement> folders = WebUI.findWebElements(folderObj, 10)
//
//	if (folders.size() == 0) {
//		KeywordUtil.markFailed("❌ Folder name not displayed")
//	} else {
//		KeywordUtil.logInfo("✅ Folder name displayed")
//	}
//
//} else {
//
//	// --------------------------------------------------
//	// 🔸 No Result Message Validation
//	// --------------------------------------------------
//	WebUI.verifyElementVisible(noResultObj)
//
//	String color = WebUI.getCSSValue(noResultObj, "color")
//
//	if (!color.contains("128")) {
//		KeywordUtil.markFailed("❌ No result message is not in gray color")
//	} else {
//		KeywordUtil.logInfo("✅ No result message displayed in gray")
//	}
//}

// ======================================================
// 🔹 STEP 4: CLEAR SEARCH → RETAIN ORIGINAL FOLDER
// ======================================================
WebUI.clearText(searchBox)
WebUI.sendKeys(searchBox, Keys.chord(Keys.ENTER))
WebUI.delay(2)

//WebUI.verifyElementVisible(inboxHeader)
//KeywordUtil.logInfo("✅ Original folder retained after clearing search")

// ======================================================
// 🔹 STEP 5: DELETE DURING SEARCH
// ======================================================
WebUI.setText(searchBox, searchText)
WebUI.sendKeys(searchBox, Keys.chord(Keys.ENTER))
WebUI.delay(2)

//if (WebUI.verifyElementClickable(deleteBtn, FailureHandling.OPTIONAL)) {
//
//	WebUI.click(deleteBtn)
//	WebUI.delay(2)
//
//	List<WebElement> resultsAfterDelete = WebUI.findWebElements(resultsObj, 10)
//
//	if (resultsAfterDelete.size() >= 0) {
//		KeywordUtil.logInfo("✅ Search results still visible after delete")
//	} else {
//		KeywordUtil.markFailed("❌ Search results lost after delete")
//	}
//}
}
