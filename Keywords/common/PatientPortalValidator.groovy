package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.*

import internal.GlobalVariable

public class PatientPortalValidator {
	@Keyword
	def validatePatientPortalDocument(TestObject docObj, String expectedPatientName = null) {

		String text = WebUI.getText(docObj)
		println("===== DOCUMENT TEXT =====")
		println(text)

		// ------------------ HEADER VALIDATION ------------------ //
		verifyContains(text, "First Insight Vision")
		verifyContains(text, "Patient Portal")
		verifyContains(text, "Cataract Consultation")

		// ------------------ DATE VALIDATION ------------------ //

		// Top Date → 7 April, 2026
		String todayLong = LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM, yyyy"))
		verifyContains(text, todayLong, "Top Date")

		// Exam Date → 04/07/2026
		String todayShort = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
		verifyContains(text, "Exam Date: " + todayShort, "Exam Date")

		// ------------------ PATIENT NAME ------------------ //
		if (expectedPatientName != null && expectedPatientName.trim() != "") {

			if (!text.contains(expectedPatientName)) {
				KeywordUtil.markFailed("❌ Patient Name mismatch. Expected: " + expectedPatientName)
			} else {
				KeywordUtil.markPassed("✅ Patient Name verified: " + expectedPatientName)
			}

		} else {
			Pattern namePattern = Pattern.compile("Patient Name:\\s+[A-Za-z]+\\s+[A-Za-z]+")
			if (!namePattern.matcher(text).find()) {
				KeywordUtil.markFailed("❌ Patient Name format not found")
			} else {
				KeywordUtil.markPassed("✅ Patient Name format verified")
			}
		}

		// ------------------ DOB VALIDATION ------------------ //
		String expectedDOB = "Date of Birth: 03/16/1982"

if (!text.contains(expectedDOB)) {
    KeywordUtil.markFailed("❌ DOB mismatch. Expected: " + expectedDOB)
} else {
    KeywordUtil.markPassed("✅ DOB verified: " + expectedDOB)
}

		// ------------------ CONTENT VALIDATION ------------------ //
		verifyContains(text, "cataract")
		verifyContains(text, "blurred vision")
		verifyContains(text, "Slit Lamp Examination")
		verifyContains(text, "Assessment")
		verifyContains(text, "Plan")

		KeywordUtil.markPassed("✅ Patient Portal Document Validation Completed")
	}


	// ------------------ REUSABLE HELPER ------------------ //
	private void verifyContains(String text, String value, String label = null) {

		String field = (label != null) ? label : value

		if (!text.contains(value)) {
			KeywordUtil.markFailed("❌ Missing: " + field)
		} else {
			KeywordUtil.markPassed("✅ Verified: " + field)
		}
	}

}
