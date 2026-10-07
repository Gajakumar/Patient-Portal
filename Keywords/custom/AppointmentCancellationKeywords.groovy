package custom

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class AppointmentCancellationKeywords {

    @Keyword
    int getCancellationBufferHours(TestObject appointmentObject) {

        // ---------------------------------------------------------
        // 1. Extract appointment text from UI
        // ---------------------------------------------------------

        String appointmentText =
                WebUI.getText(appointmentObject).trim()

        KeywordUtil.logInfo(
                "Appointment Text: ${appointmentText}"
        )

        // Example:
        // 10/07/2026 | 10:55 AM | Patient Portal

        // ---------------------------------------------------------
        // 2. Extract Date and Time
        // ---------------------------------------------------------

        String[] parts = appointmentText.split('\\|')

        if (parts.length < 2) {
            KeywordUtil.markFailed(
                    "Unable to extract appointment date/time from: " +
                    appointmentText
            )
        }

        String appointmentDate = parts[0].trim()
        String appointmentTime = parts[1].trim()

        KeywordUtil.logInfo(
                "Appointment Date: ${appointmentDate}"
        )

        KeywordUtil.logInfo(
                "Appointment Time: ${appointmentTime}"
        )

        // ---------------------------------------------------------
        // 3. Parse Appointment Date + Time
        // ---------------------------------------------------------

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "MM/dd/yyyy hh:mm a",
                        Locale.US
                )

        LocalDateTime appointmentLocalDateTime =
                LocalDateTime.parse(
                        "${appointmentDate} ${appointmentTime}",
                        formatter
                )

        // ---------------------------------------------------------
        // 4. Convert to Eastern Time
        // ---------------------------------------------------------

        ZoneId easternZone =
                ZoneId.of("America/New_York")

        ZonedDateTime appointmentDateTime =
                appointmentLocalDateTime.atZone(easternZone)

        ZonedDateTime currentEasternTime =
                ZonedDateTime.now(easternZone)

        KeywordUtil.logInfo(
                "Appointment Eastern Time: ${appointmentDateTime}"
        )

        KeywordUtil.logInfo(
                "Current Eastern Time: ${currentEasternTime}"
        )

        // ---------------------------------------------------------
        // 5. Calculate time difference
        // ---------------------------------------------------------

        long minutesUntilAppointment =
                Duration.between(
                        currentEasternTime,
                        appointmentDateTime
                ).toMinutes()

        KeywordUtil.logInfo(
                "Minutes Until Appointment: ${minutesUntilAppointment}"
        )

        if (minutesUntilAppointment <= 0) {
            KeywordUtil.markFailed(
                    "Appointment time has already passed: " +
                    appointmentDateTime
            )
        }

        // ---------------------------------------------------------
        // 6. Convert minutes to hours
        // ---------------------------------------------------------

        int bufferHours =
                (int) Math.ceil(
                        minutesUntilAppointment / 60.0
                )

        // ---------------------------------------------------------
        // 7. Restrict buffer to 1 - 24 hours
        // ---------------------------------------------------------

        bufferHours = Math.max(1, bufferHours)
        bufferHours = Math.min(24, bufferHours)

        KeywordUtil.logInfo(
                "Cancellation Buffer Hours: ${bufferHours}"
        )

        return bufferHours
    }
}