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
import org.openqa.selenium.WebElement

TestObject imgObj    = findTestObject('Object Repository/Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/Uploaded Image')


// Step 2: Wait for image to appear
WebUI.waitForElementVisible(imgObj, 15)

// ✅ Assertion 1: Image is visible
assert WebUI.verifyElementVisible(imgObj, FailureHandling.OPTIONAL) == true

// Step 3: Get src attribute
String imgSrc = WebUI.getAttribute(imgObj, 'src')

// ✅ Assertion 2: src should not be null or empty
assert imgSrc != null && imgSrc.trim().length() > 0

// Step 4: Verify image is fully loaded
WebElement imgElement = WebUI.findWebElement(imgObj)

Boolean isLoaded = WebUI.executeJavaScript(
	"return arguments[0].complete && arguments[0].naturalWidth > 0;",
	Arrays.asList(imgElement)
)

// ✅ Assertion 3: Image must be rendered properly
assert isLoaded == true

// Step 5: (Optional) Validate Base64 format
assert imgSrc.startsWith("data:image")

println("✅ Image upload validation PASSED")