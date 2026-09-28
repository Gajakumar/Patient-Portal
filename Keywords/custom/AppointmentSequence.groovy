package custom

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement


class AppointmentSequence {

    @Keyword
    def verifyUpcomingAppointmentsChronologicalOrder(TestObject appointmentCards) {

        // ====================================================
        // Step 1: Wait for appointment cards
        // ====================================================

        WebUI.waitForElementVisible(
            appointmentCards,
            15,
            FailureHandling.STOP_ON_FAILURE
        )


        // ====================================================
        // Step 2: Get appointment cards
        // ====================================================

        List<WebElement> cards = WebUI.findWebElements(
            appointmentCards,
            15
        )


        if (cards == null || cards.isEmpty()) {

            KeywordUtil.markFailed(
                'No upcoming appointments were displayed on the dashboard.'
            )

            return false
        }


        KeywordUtil.logInfo(
            "Total upcoming appointments: ${cards.size()}"
        )


        // ====================================================
        // Step 3: Date/time formatter
        // ====================================================

        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(
                'MM/dd/yyyy h:mm a',
                Locale.ENGLISH
            )


        // ====================================================
        // Step 4: Read appointments
        // ====================================================

        List<Map> actualAppointments = []


        cards.eachWithIndex { WebElement card, int index ->

            String cardText = card.getText()
                .replace('\u00A0', ' ')
                .trim()


            KeywordUtil.logInfo(
                "Appointment ${index + 1}: ${cardText}"
            )


            // ------------------------------------------------
            // Get first line
            // Example:
            //
            // 10/02/2026 | 08:55 AM | Patient Portal
            // ------------------------------------------------

            String appointmentLine = cardText
                .split(/\r?\n/)
                .find { line ->
                    line.contains('|') &&
                    line ==~ /.*\d{2}\/\d{2}\/\d{4}.*/
                }


            if (appointmentLine == null) {

                KeywordUtil.markFailed(
                    "Unable to find appointment date/time " +
                    "in card ${index + 1}.\n${cardText}"
                )

                return false
            }


            // ------------------------------------------------
            // Split the appointment line
            // ------------------------------------------------

            String[] parts = appointmentLine.split(/\|/)


            if (parts.length < 2) {

                KeywordUtil.markFailed(
                    "Invalid appointment format: ${appointmentLine}"
                )

                return false
            }


            String datePart = parts[0].trim()

            String timePart = parts[1].trim()


            // ------------------------------------------------
            // Normalize AM / PM
            // ------------------------------------------------

            timePart = timePart
                .replaceAll(/\s+/, ' ')
                .toUpperCase()


            // ------------------------------------------------
            // Create parser value
            //
            // Example:
            // 10/02/2026 08:55 AM
            // ------------------------------------------------

            String parserValue =
                "${datePart} ${timePart}"


            KeywordUtil.logInfo(
                "Parser value: ${parserValue}"
            )


            // ------------------------------------------------
            // Parse date/time
            // ------------------------------------------------

            LocalDateTime appointmentDateTime

            try {

                appointmentDateTime =
                    LocalDateTime.parse(
                        parserValue,
                        formatter
                    )

            } catch (Exception e) {

                KeywordUtil.markFailed(
                    "Unable to parse appointment date/time.\n" +
                    "Original: ${appointmentLine}\n" +
                    "Parser value: ${parserValue}\n" +
                    "Error: ${e.message}"
                )

                return false
            }


            // ------------------------------------------------
            // Store appointment
            // ------------------------------------------------

            actualAppointments << [
                uiIndex: index + 1,
                dateTimeText: "${datePart} | ${timePart}",
                dateTime: appointmentDateTime,
                fullText: cardText
            ]
        }


        // ====================================================
        // Step 5: Make sure appointments were parsed
        // ====================================================

        if (actualAppointments.isEmpty()) {

            KeywordUtil.markFailed(
                'No valid appointment date/time values were found.'
            )

            return false
        }


        // ====================================================
        // Step 6: Create expected chronological order
        // ====================================================

        List<Map> expectedAppointments =
            new ArrayList<>(actualAppointments)


        expectedAppointments.sort { first, second ->
            first.dateTime <=> second.dateTime
        }


        // ====================================================
        // Step 7: Log expected order
        // ====================================================

        KeywordUtil.logInfo(
            '========== EXPECTED ORDER =========='
        )


        expectedAppointments.eachWithIndex {
            appointment, index ->

            KeywordUtil.logInfo(
                "${index + 1}. ${appointment.dateTimeText}"
            )
        }


        // ====================================================
        // Step 8: Log actual UI order
        // ====================================================

        KeywordUtil.logInfo(
            '========== ACTUAL UI ORDER =========='
        )


        actualAppointments.eachWithIndex {
            appointment, index ->

            KeywordUtil.logInfo(
                "${index + 1}. ${appointment.dateTimeText}"
            )
        }


        // ====================================================
        // Step 9: Verify nearest appointment
        // ====================================================

        Map nearestAppointment =
            expectedAppointments[0]

        Map firstDisplayedAppointment =
            actualAppointments[0]


        KeywordUtil.logInfo(
            "Expected first: " +
            nearestAppointment.dateTimeText
        )

        KeywordUtil.logInfo(
            "Actual first: " +
            firstDisplayedAppointment.dateTimeText
        )


        if (firstDisplayedAppointment.dateTime !=
            nearestAppointment.dateTime) {

            KeywordUtil.markFailed(
                "Nearest appointment is not displayed first.\n" +
                "Expected: ${nearestAppointment.dateTimeText}\n" +
                "Actual: ${firstDisplayedAppointment.dateTimeText}"
            )

            return false
        }


        // ====================================================
        // Step 10: Verify complete sequence
        // ====================================================

        boolean correctOrder = true


        actualAppointments.eachWithIndex {
            actual, index ->

            Map expected =
                expectedAppointments[index]


            if (actual.dateTime != expected.dateTime) {

                correctOrder = false

                KeywordUtil.logInfo(
                    "Mismatch at position ${index + 1}\n" +
                    "Expected: ${expected.dateTimeText}\n" +
                    "Actual: ${actual.dateTimeText}"
                )

            } else {

                KeywordUtil.logInfo(
                    "Position ${index + 1}: PASS - " +
                    actual.dateTimeText
                )
            }
        }


        // ====================================================
        // Step 11: Final result
        // ====================================================

        if (!correctOrder) {

            KeywordUtil.markFailed(
                'Upcoming appointments are not displayed ' +
                'in chronological order.'
            )

            return false
        }


        KeywordUtil.markPassed(
            'Upcoming appointments are displayed in chronological ' +
            'order. The nearest appointment is first and same-day ' +
            'appointments are ordered by earliest time.'
        )

        return true
    }
}

