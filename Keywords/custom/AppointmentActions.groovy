package custom

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType


class AppointmentActions {

    @Keyword
    def verifyAppointmentsReverseChronologicalOrderAndStatus(TestObject appointmentCards) {

        WebUI.waitForElementVisible(
            appointmentCards,
            15,
            FailureHandling.STOP_ON_FAILURE
        )

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

        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(
                'M/d/yyyy h:mm a',
                Locale.ENGLISH
            )

        List<Map> actualAppointments = []

        /*
         * Read each appointment card
         */
        for (int index = 0; index < cards.size(); index++) {

            WebElement card = cards[index]

            String cardText = card
                .getText()
                .replace('\u00A0', ' ')
                .trim()

            KeywordUtil.logInfo(
                "========== Appointment ${index + 1} =========="
            )

            KeywordUtil.logInfo(cardText)

            /*
             * Find appointment date/time line
             *
             * Example:
             * 10/02/2026 | 08:55 AM | Patient Portal
             */
            String appointmentLine = cardText
                .split(/\r?\n/)
                .find { String line ->
                    line.contains('|') &&
                    line ==~ /.*\d{1,2}\/\d{1,2}\/\d{4}.*/
                }

            if (appointmentLine == null) {

                KeywordUtil.markFailed(
                    "Unable to find appointment date/time in card ${index + 1}.\n${cardText}"
                )

                return false
            }

            String[] parts = appointmentLine.split(/\|/)

            if (parts.length < 2) {

                KeywordUtil.markFailed(
                    "Invalid appointment format in card ${index + 1}: ${appointmentLine}"
                )

                return false
            }

            String datePart = parts[0].trim()

            String timePart = parts[1]
                .trim()
                .replaceAll(/\s+/, ' ')
                .toUpperCase()

            String parserValue = "${datePart} ${timePart}"

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
                    "Appointment: ${appointmentLine}\n" +
                    "Parser value: ${parserValue}\n" +
                    "Error: ${e.message}"
                )

                return false
            }

            /*
             * Read appointment status
             *
             * Current HTML:
             *
             * <div class="text-sm mt-1">
             *     Status:
             *     <span class="text-green-600">Confirmed</span>
             * </div>
             */
            List<WebElement> statusElements =
                card.findElements(
                    By.cssSelector('div.text-sm.mt-1 span')
                )

            if (statusElements == null || statusElements.isEmpty()) {

                KeywordUtil.markFailed(
                    "Appointment status was not displayed in card ${index + 1}.\n${cardText}"
                )

                return false
            }

            String status =
                statusElements[0]
                    .getText()
                    .trim()

            if (!(status.equalsIgnoreCase('Confirmed') ||
                  status.equalsIgnoreCase('Unconfirmed'))) {

                KeywordUtil.markFailed(
                    "Invalid appointment status in card ${index + 1}: '${status}'. " +
                    "Expected Confirmed or Unconfirmed."
                )

                return false
            }

            KeywordUtil.logInfo(
                "Date/Time : ${datePart} | ${timePart}"
            )

            KeywordUtil.logInfo(
                "Status    : ${status}"
            )

            actualAppointments << [
                uiIndex      : index + 1,
                dateTime     : appointmentDateTime,
                dateTimeText : "${datePart} | ${timePart}",
                status       : status,
                fullText     : cardText
            ]
        }

        /*
         * Create expected order.
         *
         * Latest appointment first.
         */
        List<Map> expectedAppointments =
            new ArrayList<>(actualAppointments)

        expectedAppointments.sort { first, second ->
            second.dateTime <=> first.dateTime
        }

        KeywordUtil.logInfo(
            '========== EXPECTED ORDER: LATEST → EARLIEST =========='
        )

        expectedAppointments.eachWithIndex { appointment, index ->

            KeywordUtil.logInfo(
                "${index + 1}. " +
                "${appointment.dateTimeText} | " +
                "${appointment.status}"
            )
        }

        KeywordUtil.logInfo(
            '========== ACTUAL UI ORDER =========='
        )

        actualAppointments.eachWithIndex { appointment, index ->

            KeywordUtil.logInfo(
                "${index + 1}. " +
                "${appointment.dateTimeText} | " +
                "${appointment.status}"
            )
        }

        /*
         * Compare actual UI order with expected order
         */
        boolean correctOrder = true

        for (int index = 0;
             index < actualAppointments.size();
             index++) {

            Map actual = actualAppointments[index]
            Map expected = expectedAppointments[index]

            if (actual.dateTime != expected.dateTime) {

                correctOrder = false

                KeywordUtil.logInfo(
                    "Mismatch at position ${index + 1}\n" +
                    "Expected: ${expected.dateTimeText}\n" +
                    "Actual  : ${actual.dateTimeText}"
                )

            } else {

                KeywordUtil.logInfo(
                    "Position ${index + 1}: PASS - " +
                    "${actual.dateTimeText} | " +
                    "${actual.status}"
                )
            }
        }

        if (!correctOrder) {

            KeywordUtil.markFailed(
                'Upcoming appointments are not displayed in reverse chronological order. ' +
                'Expected latest appointment first.'
            )

            return false
        }

        KeywordUtil.markPassed(
            'Upcoming appointments are displayed in reverse chronological order ' +
            '(latest appointment first) and every appointment displays a valid ' +
            'Confirmed/Unconfirmed status.'
        )

        return true
    }
	
	@Keyword
	def verifyAppointmentStatus(
		TestObject appointmentCards,
		int appointmentIndex,
		String expectedStatus
	) {
	
		List<WebElement> cards =
			WebUI.findWebElements(
				appointmentCards,
				15
			)
	
		if (cards == null || cards.isEmpty()) {
	
			KeywordUtil.markFailed(
				'No appointment cards were found.'
			)
	
			return false
		}
	
		if (appointmentIndex < 1 ||
			appointmentIndex > cards.size()) {
	
			KeywordUtil.markFailed(
				"Invalid appointment index: ${appointmentIndex}. " +
				"Available appointments: ${cards.size()}"
			)
	
			return false
		}
	
		WebElement card =
			cards[appointmentIndex - 1]
	
		List<WebElement> statusElements =
			card.findElements(
				By.cssSelector('div.text-sm.mt-1 span')
			)
	
		if (statusElements.isEmpty()) {
	
			KeywordUtil.markFailed(
				"Status was not displayed for appointment ${appointmentIndex}."
			)
	
			return false
		}
	
		String actualStatus =
			statusElements[0]
				.getText()
				.trim()
	
		KeywordUtil.logInfo(
			"Appointment ${appointmentIndex} - " +
			"Expected Status: ${expectedStatus}"
		)
	
		KeywordUtil.logInfo(
			"Appointment ${appointmentIndex} - " +
			"Actual Status: ${actualStatus}"
		)
	
		if (!actualStatus.equalsIgnoreCase(expectedStatus)) {
	
			KeywordUtil.markFailed(
				"Appointment ${appointmentIndex} status mismatch.\n" +
				"Expected: ${expectedStatus}\n" +
				"Actual: ${actualStatus}"
			)
	
			return false
		}
	
		KeywordUtil.markPassed(
			"Appointment ${appointmentIndex} status is '${actualStatus}'."
		)
	
		return true
	}
	
	@Keyword
	def Map clickCancelAndVerifyPrompt(TestObject appointmentCards, int appointmentIndex) {

		Map result = [success: false, date: null, time: null, location: null]

		List<WebElement> cards = WebUI.findWebElements(appointmentCards, 15)

		if (cards == null || cards.isEmpty()) {
			KeywordUtil.markFailed('No appointment cards were found.')
			return result
		}

		if (appointmentIndex < 1 || appointmentIndex > cards.size()) {
			KeywordUtil.markFailed("Invalid appointment index: ${appointmentIndex}")
			return result
		}

		WebElement card = cards[appointmentIndex - 1]

		// Read date/time BEFORE clicking Cancel
		String headerText = card.findElement(
			By.xpath(".//div[contains(@class,'font-medium')]")
		).getText().trim()
		// "10/05/2026 | 11:50 AM | Patient Portal"

		List<String> parts = headerText.split('\\|')*.trim()

		result.date     = parts[0]
		result.time     = parts.size() > 1 ? parts[1] : null
		result.location = parts.size() > 2 ? parts[2] : null

		KeywordUtil.logInfo("Cancelling appointment ${appointmentIndex}: Date=${result.date}, Time=${result.time}")

		// Click Cancel inside this card only (leading dot scopes it to the card)
		card.findElement(By.xpath(".//button[normalize-space()='Cancel']")).click()

		WebUI.delay(1)

		String expectedMessage = 'Do you really want to cancel this Appointment'

		TestObject promptObj = new TestObject('CancelConfirmationPrompt')
		promptObj.addProperty(
			'xpath',
			ConditionType.EQUALS,
			"//*[contains(normalize-space(.),'${expectedMessage}')]"
		)

		List<WebElement> prompt = WebUI.findWebElements(promptObj, 5)

		if (prompt == null || prompt.isEmpty()) {
			KeywordUtil.markFailed("Cancel confirmation prompt was not displayed.\nExpected: ${expectedMessage}")
			return result
		}

		KeywordUtil.markPassed('Cancel confirmation prompt displayed successfully.')
		result.success = true
		return result
	}
	
	@Keyword
	def respondToCancelConfirmation(String response) {
	
		if (!(response.equalsIgnoreCase('Yes') ||
			  response.equalsIgnoreCase('No'))) {
	
			KeywordUtil.markFailed(
				"Invalid cancellation response: ${response}. " +
				"Expected Yes or No."
			)
	
			return false
		}
	
		TestObject responseButton = new TestObject(
			"CancelConfirmation_${response}"
		)
	
		responseButton.addProperty(
			'xpath',
			com.kms.katalon.core.testobject.ConditionType.EQUALS,
			"//button[normalize-space()='${response}']"
		)
	
		WebUI.click(
			responseButton,
			FailureHandling.STOP_ON_FAILURE
		)
	
		KeywordUtil.logInfo(
			"Selected '${response}' on cancellation confirmation."
		)
	
		return true
	}
	
	@Keyword
	def clickRescheduleAndVerifyPrompt(
		TestObject appointmentCards,
		int appointmentIndex
	) {
	
		List<WebElement> cards =
			WebUI.findWebElements(
				appointmentCards,
				15
			)
	
		if (cards == null || cards.isEmpty()) {
	
			KeywordUtil.markFailed(
				'No appointment cards were found.'
			)
	
			return false
		}
	
		if (appointmentIndex < 1 ||
			appointmentIndex > cards.size()) {
	
			KeywordUtil.markFailed(
				"Invalid appointment index: ${appointmentIndex}"
			)
	
			return false
		}
	
		WebElement card =
			cards[appointmentIndex - 1]
	
		WebElement rescheduleButton =
			card.findElement(
				By.xpath(
					"//button[normalize-space()='Reschedule']"
				)
			)
	
		rescheduleButton.click()
	
		KeywordUtil.logInfo(
			"Reschedule clicked for appointment ${appointmentIndex}."
		)
	
		WebUI.delay(1)
	
		String expectedMessage =
			'Do you really want to reschedule this Appointment'
	
		TestObject prompt =
			new TestObject('RescheduleConfirmationPrompt')
	
		prompt.addProperty(
			'xpath',
			com.kms.katalon.core.testobject.ConditionType.EQUALS,
			"//*[normalize-space(text())='${expectedMessage}']"
		)
	
		if (!WebUI.verifyElementVisible(
			prompt,
			FailureHandling.OPTIONAL
		)) {
	
			KeywordUtil.markFailed(
				"Reschedule confirmation prompt was not displayed.\n" +
				"Expected: ${expectedMessage}"
			)
	
			return false
		}
	
		KeywordUtil.markPassed(
			'Reschedule confirmation prompt displayed successfully.'
		)
	
		return true
	}
}