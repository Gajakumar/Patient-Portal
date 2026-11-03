package stories

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement

public class JsClick {
	@Keyword
	def jsClick(TestObject to, int timeout = 10) {
		WebUI.waitForElementPresent(to, timeout)
		WebElement element = WebUiCommonHelper.findWebElement(to, timeout)
		WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(element))
	}
}