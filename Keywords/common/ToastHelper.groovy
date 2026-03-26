package common

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper
class ToastHelper {

@Keyword
def verifyToastMessage(String expectedMessage, int timeout = 5) {

    TestObject toast = new TestObject()
    toast.addProperty("xpath",
        com.kms.katalon.core.testobject.ConditionType.EQUALS,
        "//div[contains(@class,'Toastify__toast') and @role='alert']"
    )

    String actualMessage = ""
    boolean found = false

    long startTime = System.currentTimeMillis()

    while ((System.currentTimeMillis() - startTime) < (timeout * 1000)) {

        List<WebElement> toasts = WebUiCommonHelper.findWebElements(toast, 1)

        if (toasts.size() > 0) {
            actualMessage = toasts[0].getText().trim()

            if (actualMessage) {
                found = true
                break
            }
        }

        WebUI.delay(0.5)
    }

    WebUI.comment("🔍 Expected Toast: " + expectedMessage)
    WebUI.comment("🔍 Actual Toast: " + actualMessage)

    if (!found || !actualMessage.contains(expectedMessage)) {
        WebUI.comment("❌ Toast message mismatch or not captured in time")
        assert false
    }

    WebUI.comment("✅ Toast message verified successfully")
	
}

@Keyword
def verifyMaximeyesToastMessage(String expectedMessage, int timeout = 5) {

	TestObject toast = new TestObject()
	toast.addProperty("xpath",
		com.kms.katalon.core.testobject.ConditionType.EQUALS,
		"//div[starts-with(@id, 'jquery-notific')]/div[2]"
	)

	String actualMessage = ""
	boolean found = false

	long startTime = System.currentTimeMillis()

	while ((System.currentTimeMillis() - startTime) < (timeout * 1000)) {

		List<WebElement> toasts = WebUiCommonHelper.findWebElements(toast, 1)

		if (toasts.size() > 0) {
			actualMessage = toasts[0].getText().trim()

			if (actualMessage) {
				found = true
				break
			}
		}

		WebUI.delay(0.5)
	}

	WebUI.comment("🔍 Expected Toast: " + expectedMessage)
	WebUI.comment("🔍 Actual Toast: " + actualMessage)

	if (!found || !actualMessage.contains(expectedMessage)) {
		WebUI.comment("❌ Toast message mismatch or not captured in time")
		assert false
	}

	WebUI.comment("✅ Toast message verified successfully")
	
}
}