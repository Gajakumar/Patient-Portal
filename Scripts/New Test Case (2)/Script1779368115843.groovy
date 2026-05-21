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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import java.util.Arrays
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import java.util.Arrays

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import java.util.Arrays

// =====================================================
// ✅ STEP 1: Login
// =====================================================
WebUI.callTestCase(
    findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
    [:],
    FailureHandling.STOP_ON_FAILURE
)

// =====================================================
// ✅ STEP 2: Validate max 10 rows
// =====================================================
TestObject rowsObj = new TestObject()
rowsObj.addProperty("xpath", ConditionType.EQUALS,
    "//tbody[@id='idquicklinkGrid1']//tr[contains(@class,'fixedGridTR')]")

List<WebElement> rows = WebUiCommonHelper.findWebElements(rowsObj, 10)
int rowCount = rows.size()

println "Rows on page: " + rowCount
assert rowCount <= 10 : "More than 10 rows displayed!"

// =====================================================
// ✅ STEP 3: Get pagination info
// =====================================================
TestObject pageInfo = new TestObject()
pageInfo.addProperty("xpath", ConditionType.EQUALS,
    "(//span[contains(@class,'showResult')])[2]")

WebUI.waitForElementVisible(pageInfo, 10)

String pageText = WebUI.getText(pageInfo).trim()
println "Page Text: " + pageText

assert pageText.contains("of") : "Pagination text not loaded!"

def matcher = (pageText =~ /of\s+(\d+)/)
assert matcher.find()

int totalRecords = matcher.group(1).toInteger()
println "Total records: " + totalRecords

// =====================================================
// ✅ STEP 4: Validate pagination (if >10 records)
// =====================================================
if (totalRecords > 10) {

    // Next button
    TestObject nextBtn = new TestObject()
    nextBtn.addProperty("xpath", ConditionType.EQUALS,
        "//div[@id='sentpagebuttons']//span[text()='›']")

    WebUI.verifyElementPresent(nextBtn, 5)

    // First row (for data comparison)
    TestObject firstRow = new TestObject()
    firstRow.addProperty("xpath", ConditionType.EQUALS,
        "(//tbody[@id='idquicklinkGrid1']//tr)[1]")

    String beforeClick = WebUI.getText(firstRow)
    println "Before Click Row: " + beforeClick

    // =====================================================
    // ✅ Click NEXT (robust)
    // =====================================================
    try {
        WebUI.click(nextBtn)
    } catch (Exception e) {
        WebUI.executeJavaScript(
            "arguments[0].click();",
            Arrays.asList(WebUI.findWebElement(nextBtn))
        )
    }

    // =====================================================
    // ✅ Wait until data changes (AJAX safe)
    // =====================================================
    int maxWait = 10
    boolean pageChanged = false

    for (int i = 0; i < maxWait; i++) {

        String afterClick = WebUI.getText(firstRow)

        if (afterClick != beforeClick) {
            println "After Click Row: " + afterClick
            pageChanged = true
            break
        }

        WebUI.delay(1)
    }

    assert pageChanged : "Pagination not working! Data did not change."
}