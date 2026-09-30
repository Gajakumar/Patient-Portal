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

 //Navigate to OA >> Schedule

	WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_Office Admin'))
	WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_Modules'))
	WebUI.click(findTestObject('Appointments/Appt Type/Page_MaximEyes/a_ui-id-21'))
	
	CustomKeywords.'custom.AppointmentSettingsKeywords.setAppointmentSettings'(
		[
			"SCHEDULING_HOURS"              : "10",
			"CANCELATION_HOURS"             : "2",
			"DAILY_LIMIT_HOURS"             : "1",
			"ApptLimitForOnlineAppointment" : "5"
		],
		[
			"idLimitOnlineEnable"               : false,
			"idIsOnlineApptActivityReportEnable": true,
			"idIsEnableOnlineScheduleInsurance": false
		]
	)