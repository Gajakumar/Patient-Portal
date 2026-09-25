package custom

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale


import com.kms.katalon.core.testobject.ConditionType

import internal.GlobalVariable

public class ApptDateTimeVerification {
	
@Keyword
def verifyAppointmentCard(
        TestObject appointmentObj,
        String providerName,
        String appointmentDate,
        String appointmentTime) {

    DateTimeFormatter inputFmt =
            DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH)

    DateTimeFormatter displayFmt =
            DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy", Locale.ENGLISH)

    LocalDate date = LocalDate.parse(appointmentDate, inputFmt)

    String expectedText =
            providerName + "\n" +
            appointmentTime + " " + date.format(displayFmt)

    // Normalize multiple spaces and line breaks
    String actual = WebUI.getText(appointmentObj)
            .replaceAll(/[ \t]+/, " ")
            .trim()

    String expected = expectedText
            .replaceAll(/[ \t]+/, " ")
            .trim()

    println("ACTUAL:\n" + actual)
    println("EXPECTED:\n" + expected)

    assert actual == expected : "Appointment card text mismatch"
}
	
	@Keyword
def verifyAppointmentSummary(
        TestObject appointmentObject,
        String appointmentDate,
        String appointmentTime,
        String appointmentReason,
        String practiceName,
        String status) {

    DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH)

    DateTimeFormatter timeFormatter =
            DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

    LocalDate date =
            LocalDate.parse(appointmentDate, inputFormatter)

    LocalTime time =
            LocalTime.parse(
                    appointmentTime.toUpperCase(Locale.ENGLISH),
                    DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
            )

    String expectedText =
            date.format(inputFormatter) +
            " | " +
            time.format(timeFormatter) +
            " | " +
            appointmentReason +
            "\n" +
            practiceName +
            ", " +
            appointmentReason +
            "\n" +
            "Status: " +
            status

    println("Expected Appointment Text:")
    println(expectedText)

    WebUI.assertElementText(
            appointmentObject,
            expectedText,
            0
    )
}

}
