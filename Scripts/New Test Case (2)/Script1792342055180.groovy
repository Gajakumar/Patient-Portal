import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
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

WebUI.click(findTestObject('Intake/Page_Patient Portal/button_Proceed'))



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/h1_Patient Intake Form'), 'Patient Intake Form', 0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/h1_header_1'), 'Billing Policy', 0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/p_As a courtesy, Business Name , verifies your b'), 'As a courtesy,   Business Name    , verifies your benefits with your insurance company. A quote of benefits is not a guarantee of benefits or payment. Your claim will process according to your plan, if your claim processes differently from the benefits we were quoted, the insurance company will side with the plan and will not honor the benefit quote we received.It is the policy of Business Name  that payment is due at the time of service unless other financial arrangements are made in advance. We require all patients to pay their deductible, copay and/or coinsurance payment at the beginning of each visit. The office manager at your location will explain this information to you prior to your first visit. At the conclusion of your visits with us you may be billed for any outstanding balances. If there is a credit, you will be provided a refund promptly.If you are covered by health insurance benefits, we will be happy to bill your insurance. Please provide your insurance information to the front office staff and we will verify your coverage as a courtesy. Accepting your insurance does not place all financial responsibilities onto this practice, and you will be held accountable forany unpaid balances by your plan.Although we are contracted with most insurance carriers, our services may not be covered by your particular insurance plan. Being referred to our clinic by another physician does not necessarily guarantee that your insurance will cover our services. Please remember that you are 100 percent responsible for all charges incurred: your physician\'s referral and our verification of your insurance benefits are not a guarantee of payment.We highly recommend you also contact your insurance carrier and check into your coverage for Optometry/Ophthalmology services. Do not assume that you will not owe anything if you have more than one insurance policy.', 
    0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/input_Patient Name'), '', 0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/input_last_10'), '', 0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/input_MM-DD-YYYY'), '', 0)

WebUI.mouseOver(findTestObject('Intake/Page_Patient Portal/li_id_9'))

WebUI.mouseOver(findTestObject('Intake/Page_Patient Portal/li_id_18'))

WebUI.click(findTestObject('Intake/Page_Patient Portal/canvas_E-Signature pad. Draw your signature here'))

WebUI.click(findTestObject('Intake/Page_Patient Portal/button_Clear signature'))

WebUI.mouseOver(findTestObject('Intake/Page_Patient Portal/li_id_9_1'))

WebUI.click(findTestObject('Intake/Page_Patient Portal/canvas_E-Signature pad. Draw your signature here'))

WebUI.click(findTestObject('Intake/Page_Patient Portal/label_label_input_19_0'))

WebUI.mouseOver(findTestObject('Intake/Page_Patient Portal/li_id_19'))

WebUI.click(findTestObject('Intake/Page_Patient Portal/button_input_2'))

WebUI.click(findTestObject('Intake/Page_Patient Portal/button_Proceed'))


WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/h1_Patient Intake Form'), 'Patient Intake Form', 0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/p_You have an upcoming appointment'), 'You have an upcoming appointment', 
    0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/p_8_50 PM Sunday, October 18, 2026'), '8:50 PM Sunday, October 18, 2026', 
    0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/p_Reason_ Patient Portal'), 'Reason: Patient Portal', 
    0)

WebUI.rightClick(findTestObject('Intake/Page_Patient Portal/h2_All forms and History forms are up to date'))

WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/h2_All forms and History forms are up to date'), 'All forms and History forms are up to date.', 
    0)



WebUI.assertElementPresent(findTestObject('Intake/Page_Patient Portal/button_Proceed'), 0)



WebUI.assertElementPresent(findTestObject('Intake/Page_Patient Portal/button_Cancel Appointment'), 0)



WebUI.assertElementPresent(findTestObject('Intake/Page_Patient Portal/button_Reschedule'), 0)

WebUI.click(findTestObject('Intake/Page_Patient Portal/button_Proceed'))



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/h2_You have an upcoming appointment'), 'You have an upcoming appointment', 
    0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/p_08_50 PM on Sunday, October 18, 2026Reason_ Pa'), '08:50 PM on Sunday, October 18, 2026Reason: Patient Portal', 
    0)



WebUI.assertElementPresent(findTestObject('Intake/Page_Patient Portal/div_Cancel'), 0)



WebUI.assertElementPresent(findTestObject('Intake/Page_Patient Portal/div_Reschedule'), 0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/h3_Speed up your arrival'), 'Speed up your arrival', 
    0)



WebUI.assertElementText(findTestObject('Intake/Page_Patient Portal/p_Save time at the doctors office by filling In'), 'Save time at the doctor\'s office by filling Intake Form', 
    0)

WebUI.click(findTestObject('Intake/Page_Patient Portal/a_Intake Form'))

