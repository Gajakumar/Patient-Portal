import java.util.Arrays
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling

// Parameter: TestObject imgObj

WebUI.waitForElementVisible(imgObj, 15)

// Verify image is visible
assert WebUI.verifyElementVisible(imgObj, FailureHandling.OPTIONAL)

// Get src attribute
String imgSrc = WebUI.getAttribute(imgObj, 'src')
assert imgSrc != null && imgSrc.trim().length() > 0

// Verify image is fully loaded
WebElement imgElement = WebUI.findWebElement(imgObj)

Boolean isLoaded = WebUI.executeJavaScript(
    'return arguments[0].complete && arguments[0].naturalWidth > 0;',
    Arrays.asList(imgElement)
)

assert isLoaded

// Verify Base64 image
assert(imgSrc.startsWith('data:image') || imgSrc.startsWith('https://ptportal'));

println("✅ Image upload validation PASSED")