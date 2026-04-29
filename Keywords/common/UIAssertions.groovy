package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject

class UIAssertions {

    @Keyword
    def verifyElementDisabled(TestObject obj) {

        WebUI.waitForElementPresent(obj, 10)

        String classValue = WebUI.getAttribute(obj, "class")

        println("Class value: " + classValue)

        // HARD ASSERT
        assert classValue != null && classValue.contains("cursor-not-allowed") :
                "Element is clickable but should be disabled. Actual class: " + classValue

        println("Element is correctly disabled")
    }
	
	@Keyword
	def verifyElementEnabled(TestObject obj) {

		WebUI.waitForElementPresent(obj, 10)

		String classValue = WebUI.getAttribute(obj, "class")

		println("Class value: " + classValue)

		// HARD ASSERT
		assert classValue == null || !classValue.contains("cursor-not-allowed") :
				"Element is disabled but should be enabled. Actual class: " + classValue

		println("Element is correctly enabled")
}
}