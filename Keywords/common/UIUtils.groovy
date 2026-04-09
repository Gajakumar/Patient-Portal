package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import org.openqa.selenium.WebElement

class UIUtils {

    /**
     * Toggle checkbox/radio based on desired state
     * @param obj TestObject
     * @param shouldBeChecked true = check, false = uncheck
     */
    @Keyword
    def toggleCheckbox(TestObject obj, boolean shouldBeChecked) {

        WebUI.waitForElementPresent(obj, 10)
        WebUI.waitForElementClickable(obj, 10)

        boolean isChecked = false

        try {
            // Case 1: Standard input checkbox/radio
            String checkedAttr = WebUI.getAttribute(obj, "checked")
            isChecked = (checkedAttr != null)

        } catch (Exception e) {

            // Case 2: Custom UI (span/div checkbox)
            try {
                String classAttr = WebUI.getAttribute(obj, "class")
                isChecked = classAttr != null && (
                    classAttr.contains("checked") ||
                    classAttr.contains("active")  ||
                    classAttr.contains("selected")
                )
            } catch (Exception ex) {
                isChecked = false
            }
        }

        // Perform action only if needed
        if (isChecked != shouldBeChecked) {

            try {
                WebUI.click(obj)
            } catch (Exception clickException) {

                // Fallback: JS click (handles overlay issues)
                WebElement element = WebUI.findWebElement(obj)
                WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(element))
            }

            println("✔ Toggled: " + obj.getObjectId())

        } else {
            println("✔ Already in desired state: " + obj.getObjectId())
        }
    }
	
	@Keyword
	def clickCloseIconByIndex(int index) {
	
		TestObject dynamicObj = new TestObject()
	
		String xpath = "(//div[normalize-space(text())='✕'])[" + index + "]"
	
		dynamicObj.addProperty("xpath",
			com.kms.katalon.core.testobject.ConditionType.EQUALS,
			xpath)
	
		WebUI.waitForElementClickable(dynamicObj, 10)
		WebUI.click(dynamicObj)
	
		WebUI.comment("✅ Clicked ✕ icon at index: " + index)
	}
}