package custom

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



class CancelAppointmentKeywords {

    /**
     * Cancels all appointments using
     * "Cancel Appt (Office Request)".
     *
     * The keyword keeps checking for Appointment Actions dropdowns
     * and cancels the first available appointment until none remain.
     */
    @Keyword
    def cancelAllAppointments() {

        KeywordUtil.logInfo("==========================================")
        KeywordUtil.logInfo("Starting cancellation of all appointments.")
        KeywordUtil.logInfo("==========================================")

        def appointmentDropdown = findTestObject(
            'Appointments/Page_MaximEyes/SkyBlue Dropdown'
        )

        def cancelAppointment = findTestObject(
            'Object Repository/Appointments/Page_MaximEyes/Cancel appt Office Request'
        )

        int cancelledCount = 0

        while (true) {

            // Re-find the dropdowns after every cancellation
            // because the page/DOM may refresh.
            def appointmentDropdowns = WebUI.findWebElements(
                appointmentDropdown,
                5
            )

            int remainingAppointments = appointmentDropdowns.size()

            KeywordUtil.logInfo(
                "Appointment Actions dropdowns remaining: ${remainingAppointments}"
            )

            // No appointments remaining
            if (remainingAppointments == 0) {

                KeywordUtil.logInfo(
                    "No more appointments found."
                )

                break
            }

            KeywordUtil.logInfo(
                "Processing appointment ${cancelledCount + 1}."
            )

            // Always click the first available appointment
            appointmentDropdowns.get(0).click()

            KeywordUtil.logInfo(
                "Appointment Actions dropdown opened successfully."
            )

            // Click Cancel Appt (Office Request)
            WebUI.waitForElementClickable(
                cancelAppointment,
                10
            )

            WebUI.click(cancelAppointment)

            KeywordUtil.logInfo(
                "'Cancel Appt (Office Request)' selected successfully."
            )

            cancelledCount++

            // Give the page time to update/remove the cancelled appointment
            WebUI.delay(1)
        }

        KeywordUtil.logInfo("==========================================")
        KeywordUtil.logInfo(
            "Total appointments cancelled: ${cancelledCount}"
        )
        KeywordUtil.logInfo("==========================================")
    }
}

