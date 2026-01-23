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
import java.time.*
import java.time.format.*
import java.util.Locale
import internal.GlobalVariable

class GMTTimeAuditVerifier {

    private static ZonedDateTime sendTimeGMT

    /**
     * Call immediately AFTER clicking Send button
     */
    @Keyword
    void captureSendTimeGMT() {
        sendTimeGMT = ZonedDateTime.now(ZoneId.of("GMT"))
                .withSecond(0)
                .withNano(0)

        println "✅ Captured Send Time (GMT): ${sendTimeGMT}"
    }

    /**
     * Verifies UI GMT DateTime is within ± given minutes of Send time
     *
     * @param uiText Raw UI text (e.g. "| 01/21/2026 03:08 PM")
     * @param minutesTolerance Allowed minutes (default = 5)
     */
    @Keyword
    void verifyUITimeWithinMinutesOfSendGMT(
            String uiText,
            int minutesTolerance = 5
    ) {

        assert sendTimeGMT != null :
                "❌ Send time not captured. Call captureSendTimeGMT() after clicking Send."

        // Clean UI text
        String cleanedText = uiText
                .replace('|', '')
                .replaceAll('\\s+', ' ')
                .trim()

        // Formatter with Locale (MANDATORY for AM/PM)
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH)

        // Parse UI time as GMT
        ZonedDateTime actualGMT =
                ZonedDateTime.of(
                        LocalDateTime.parse(cleanedText, formatter),
                        ZoneId.of("GMT")
                ).withSecond(0).withNano(0)

        ZonedDateTime minAllowed = sendTimeGMT.minusMinutes(minutesTolerance)
        ZonedDateTime maxAllowed = sendTimeGMT.plusMinutes(minutesTolerance)

        assert !actualGMT.isBefore(minAllowed) &&
               !actualGMT.isAfter(maxAllowed) :
               """❌ UI DateTime NOT within ±${minutesTolerance} minutes
               Send Time : ${sendTimeGMT}
               Allowed   : ${minAllowed} → ${maxAllowed}
               UI Time   : ${actualGMT}
               """
    }
}