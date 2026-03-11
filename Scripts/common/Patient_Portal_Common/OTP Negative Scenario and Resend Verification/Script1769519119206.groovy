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


WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Security code digit1of 4_otp-0'),
	'1')

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Security code digit2of 4_otp-1'),
	'2')

TestObject proccedBtn = findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/ProccedBtnAfterOTPVerification')

WebUI.verifyElementHasAttribute(proccedBtn, 'disabled', 5)

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Security code digit3of 4_otp-2'),
	'1')

WebUI.setText(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/input_Security code digit4of 4_otp-3'),
	'2')

WebUI.click(findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Proceed'))


WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast'),
	'Invalid Security Code')

def clearByKeys = { TestObject obj ->
    WebUI.click(obj)
    WebUI.sendKeys(obj, Keys.chord(Keys.CONTROL, 'a').toString())
    WebUI.sendKeys(obj, Keys.BACK_SPACE.toString())
}

clearByKeys(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp1"))
clearByKeys(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp2"))
clearByKeys(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp3"))
clearByKeys(findTestObject("Object Repository/PatientPortal/SignInPage_Patient Portal/otp4"))


TestObject resendBtn = findTestObject('Object Repository/Forgot Username and Password/Page_Patient Portal/button_Resend Code')

boolean isDisabled = WebUI.executeJavaScript("""
  const btn = arguments[0];
  return btn.disabled === true
      || btn.hasAttribute('disabled')
      || btn.classList.contains('disabled');
""", Arrays.asList(WebUI.findWebElement(resendBtn)))
  
  println "Is disabled = " + isDisabled


int timeout = 90   // seconds
int poll = 5

boolean clicked = false

for (int i = 0; i < timeout / poll; i++) {

	boolean enabled = WebUI.executeJavaScript("""
        const btn = arguments[0];
        return !btn.disabled
            && !btn.hasAttribute('disabled')
            && !btn.classList.contains('disabled')
            && btn.innerText.toLowerCase().includes('resend');
    """, Arrays.asList(WebUI.findWebElement(resendBtn)))

	if (enabled) {
		WebUI.executeJavaScript("arguments[0].click()",
			Arrays.asList(WebUI.findWebElement(resendBtn)))
		println "✅ Resend clicked"
		clicked = true
		break
	}

	WebUI.delay(poll)
}

assert clicked : "❌ Resend button never became enabled"

//WebUI.verifyElementText(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast'),
//	'A new security code has been sent to your phone/email')

WebUI.waitForElementVisible(findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast'), 10)

WebUI.verifyElementText(
	findTestObject('Object Repository/PatientPortal/SignInPage_Patient Portal/Account Lock Toast'),
	'A new security code has been sent to your phone/email'
)

