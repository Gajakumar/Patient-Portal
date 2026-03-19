package common

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
class ToastHelper {

    @Keyword
    def verifyToastMessage(String expectedMessage, int timeout = 5) {
        TestObject toast = new TestObject()

        // Generic XPath covering most toast implementations
        toast.addProperty("xpath", 
            com.kms.katalon.core.testobject.ConditionType.EQUALS, 
            "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' Toastify__toast ')]"
        )

        // Wait for toast to appear
        boolean isVisible = WebUI.waitForElementVisible(toast, timeout, FailureHandling.OPTIONAL)

        if (!isVisible) {
            WebUI.comment("❌ Toast not visible within timeout")
            assert false
        }

        // Get toast text
        String actualMessage = WebUI.getText(toast).trim()

        WebUI.comment("🔍 Expected Toast: " + expectedMessage)
        WebUI.comment("🔍 Actual Toast: " + actualMessage)

        // Verify message
        if (!actualMessage.contains(expectedMessage)) {
            WebUI.comment("❌ Toast message mismatch")
            assert false
        }

        WebUI.comment("✅ Toast message verified successfully")
    }
}