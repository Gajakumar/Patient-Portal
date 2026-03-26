package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

class TextFieldValidation {

    /**
     * Validate max length and restrict extra characters
     * @param fieldName - Friendly name for reporting
     * @param obj - TestObject
     * @param maxLength - Expected max length
     */
    static void verifyMaxLengthWithMessage(String fieldName, TestObject obj, int maxLength) {

        try {
            // Generate test data
            String validData = 'A' * maxLength
            String invalidData = 'A' * (maxLength + 10)

            // Step 1: Enter valid data
            WebUI.clearText(obj)
            WebUI.setText(obj, validData)

            String actualValue = WebUI.getAttribute(obj, 'value')

            if (actualValue.length() == maxLength) {
                KeywordUtil.markPassed("✅ ${fieldName}: Accepts maximum allowed length (${maxLength}) correctly.")
            } else {
                KeywordUtil.markFailed("❌ ${fieldName}: Expected max length ${maxLength}, but accepted ${actualValue.length()}.")
            }

            // Step 2: Try exceeding limit
            WebUI.clearText(obj)
            WebUI.setText(obj, invalidData)

            String actualAfter = WebUI.getAttribute(obj, 'value')

            if (actualAfter.length() <= maxLength) {
                KeywordUtil.markPassed("✅ ${fieldName}: Restricts input beyond ${maxLength} characters as expected.")
            } else {
                KeywordUtil.markFailed("❌ ${fieldName}: Allows more than ${maxLength} characters (Actual: ${actualAfter.length()}).")
            }

        } catch (Exception e) {
            KeywordUtil.markFailed("❌ ${fieldName}: Validation failed due to exception -> " + e.getMessage())
        }
    }
}