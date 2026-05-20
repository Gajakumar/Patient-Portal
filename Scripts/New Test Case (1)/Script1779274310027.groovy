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

//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_btnComposeSent'))
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenuSent'))
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/div_Inbox'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/h4_Are you sure you want to navigate away from t'), 
//    'Are you sure you want to navigate away from the compose message?')
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/input_btnCancel'), '')
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'), '')
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnCancel'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/h4_Compose'), '\n                                Compose\n\n                                \n                                \n                                \n\n                            ')
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenuSent'))
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/div_Inbox_1'))
//
//WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'))
//
//WebUI.verifyElementText(findTestObject('Provider Portal/Page_MaximEyes/span_Inbox'), '\n                        Inbox\n                    ')

// Reusable function for trimmed text verification
def verifyText(TestObject obj, String expected) {
	String actual = WebUI.getText(obj).trim()
	WebUI.verifyMatch(actual, expected, false)
}

// Click Compose Sent
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_btnComposeSent'))

// Open menu → Click Inbox → Validate popup
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenuSent'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/div_Inbox'))

verifyText(findTestObject('Provider Portal/Page_MaximEyes/h4_Are you sure you want to navigate away from t'),
		   'Are you sure you want to navigate away from the compose message?')

// Validate buttons exist (instead of empty text check)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_btnCancel'), 5)
WebUI.verifyElementPresent(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'), 5)

// Click Cancel → Stay on Compose
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnCancel'))

verifyText(findTestObject('Provider Portal/Page_MaximEyes/h4_Compose'), 'Compose')

// Repeat flow → Click OK this time
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/span_openmoreactionmenuSent'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/div_Inbox_1'))
WebUI.click(findTestObject('Provider Portal/Page_MaximEyes/input_btnOk'))

// Verify Inbox navigation
TestObject inbox = findTestObject('Provider Portal/Page_MaximEyes/div_Inbox_1')

WebUI.waitForElementVisible(inbox, 10)
WebUI.verifyElementPresent(inbox, 10)

