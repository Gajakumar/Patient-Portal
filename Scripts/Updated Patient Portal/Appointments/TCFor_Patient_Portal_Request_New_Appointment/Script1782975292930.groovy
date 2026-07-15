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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import javax.mail.Message
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.mail.*
import java.util.Properties
import javax.mail.*
import javax.mail.internet.MimeMultipart
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.util.regex.*
import java.util.Properties
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

// =====================================================
// ✅ STEP 1: Portal Login (Dsmith)
// =====================================================

WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Dsmith Portal Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/div_border-2 rounded-full p-4 sm_p-6 lg_p-8 flex'))

WebUI.assertElementText(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/h2_Upcoming Appointments'), 
    'Upcoming Appointments', 0)

WebUI.assertElementText(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/button_Request New Appointment'), 
    'Request New Appointment', 0)

WebUI.click(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/button_Request New Appointment'))

WebUI.assertElementText(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/h1_Request Appointment'), 
    'Request Appointment', 0)

WebUI.mouseOver(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/p_Note_ Note_ If this is a medical emergency, pl'))


WebUI.assertElementText(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/label_Select Location'), 'Select Location', 
    0)

WebUI.assertElementText(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/label_Select Provider'), 'Select Provider', 
    0)

WebUI.assertElementText(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/label_Select Reason'), 'Select Reason', 
    0)

WebUI.assertElementText(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/div_Proceed'), 'Proceed', 
    0)

WebUI.selectOptionByValue(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/select_Select Location'), 
    '4', false)

WebUI.selectOptionByValue(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/select_Select Reason'), 
    '2', false)

WebElement ApptproccedBtn = WebUI.findWebElement(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/button_Request New Appointment'), 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(ApptproccedBtn))

WebUI.assertElementText(findTestObject('Appointments/Online Sch Pt Portal/Page_Patient Portal/h2_Request Appointment'), 
    'Request Appointment', 0)

