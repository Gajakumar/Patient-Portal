package custom

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


class AppointmentKeywords {

    // ============================================================
    // Date Formatters
    // ============================================================

    private static final DateTimeFormatter INPUT_FMT =
            DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH)

    /*
     * Calendar UI displays:
     *
     * Sep 2026
     * Oct 2026
     *
     * Locale.ENGLISH is important because the JVM may not
     * use English as its default locale.
     */
    private static final DateTimeFormatter MONTH_FMT =
            DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH)

    private static final DateTimeFormatter DAY_NAME =
            DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)

    private static final DateTimeFormatter MONTH_DAY =
            DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH)


    // ============================================================
    // Main Keyword
    // ============================================================

    /**
     * Selects:
     *
     * 1. Calendar start date
     * 2. Calendar end date
     * 3. Appointment date
     * 4. Appointment time
     *
     * Example:
     *
     * selectAppointmentDateTime(
     *     "09/01/2026",
     *     "09/30/2026",
     *     "09/25/2026",
     *     "02:30 PM"
     * )
     */
    @Keyword
    def selectAppointmentDateTime(
            String fromDate,
            String toDate,
            String appointmentDate,
            String appointmentTime) {

        // --------------------------------------------------------
        // Convert input strings to LocalDate
        // --------------------------------------------------------

        LocalDate from = LocalDate.parse(
                fromDate,
                INPUT_FMT
        )

        LocalDate to = LocalDate.parse(
                toDate,
                INPUT_FMT
        )

        LocalDate appt = LocalDate.parse(
                appointmentDate,
                INPUT_FMT
        )

        println("==========================================")
        println("Appointment Selection")
        println("From Date          : " + fromDate)
        println("To Date            : " + toDate)
        println("Appointment Date   : " + appointmentDate)
        println("Appointment Time   : " + appointmentTime)
        println("==========================================")


        // --------------------------------------------------------
        // Select calendar FROM date
        // --------------------------------------------------------

        selectCalendarDate(from)


        // --------------------------------------------------------
        // Select calendar TO date
        // --------------------------------------------------------

        selectCalendarDate(to)


        // --------------------------------------------------------
        // Confirm date range
        // --------------------------------------------------------

        TestObject confirmButton =
                findTestObject('Appointments/Calendar/btn_Confirm')

        WebUI.waitForElementClickable(
                confirmButton,
                10,
                FailureHandling.STOP_ON_FAILURE
        )

        WebUI.click(confirmButton)


        // --------------------------------------------------------
        // Select appointment time
        // --------------------------------------------------------

        selectTimeSlot(
                appt,
                appointmentTime
        )
    }


    // ============================================================
    // Calendar Date Selection
    // ============================================================

    /**
     * Dynamically selects any calendar date.
     *
     * The method checks:
     *
     * LEFT calendar
     * RIGHT calendar
     *
     * If the target month is not visible, it navigates
     * Previous / Next until the target month is displayed.
     */
    private void selectCalendarDate(LocalDate targetDate) {

        String targetMonth =
                targetDate.format(MONTH_FMT)

        println("------------------------------------------")
        println("Selecting Calendar Date")
        println("Target Date  : " + targetDate)
        println("Target Month : " + targetMonth)
        println("------------------------------------------")


        while (true) {

            // ----------------------------------------------------
            // Read currently displayed months
            // ----------------------------------------------------

            String leftMonth =
                    WebUI.getText(
                            findTestObject(
                                    'Appointments/Calendar/lbl_LeftMonth'
                            )
                    ).trim()

            String rightMonth =
                    WebUI.getText(
                            findTestObject(
                                    'Appointments/Calendar/lbl_RightMonth'
                            )
                    ).trim()


            println("Current Left Month  : " + leftMonth)
            println("Current Right Month : " + rightMonth)


            // ----------------------------------------------------
            // Target month is LEFT calendar
            // ----------------------------------------------------

            if (leftMonth.equalsIgnoreCase(targetMonth)) {

                println(
                        "Target month found in LEFT calendar"
                )

                clickDay(
                        findTestObject(
                                'Appointments/Calendar/lbl_LeftMonth'
                        ),
                        targetDate.dayOfMonth
                )

                return
            }


            // ----------------------------------------------------
            // Target month is RIGHT calendar
            // ----------------------------------------------------

            if (rightMonth.equalsIgnoreCase(targetMonth)) {

                println(
                        "Target month found in RIGHT calendar"
                )

                clickDay(
                        findTestObject(
                                'Appointments/Calendar/lbl_RightMonth'
                        ),
                        targetDate.dayOfMonth
                )

                return
            }


            // ----------------------------------------------------
            // Target month is not visible
            // Determine navigation direction
            // ----------------------------------------------------

            YearMonth left =
                    YearMonth.parse(
                            leftMonth,
                            MONTH_FMT
                    )

            YearMonth target =
                    YearMonth.from(targetDate)


            if (target.isBefore(left)) {

                println(
                        "Clicking PREVIOUS month"
                )

                WebUI.click(
                        findTestObject(
                                'Appointments/Calendar/btn_Previous'
                        )
                )

            } else {

                println(
                        "Clicking NEXT month"
                )

                WebUI.click(
                        findTestObject(
                                'Appointments/Calendar/btn_Next'
                        )
                )
            }


            // Allow calendar DOM to update
            WebUI.delay(0.5)
        }
    }


    // ============================================================
    // Click Calendar Day
    // ============================================================

    /**
     * Clicks the requested day inside the calendar associated
     * with the supplied month label.
     *
     * The supplied TestObject is either:
     *
     * lbl_LeftMonth
     * lbl_RightMonth
     *
     * The date HTML is:
     *
     * <div class="grid grid-cols-7 gap-1">
     *
     *     ...
     *
     *     <div class="... cursor-pointer ...">
     *         25
     *     </div>
     *
     * </div>
     *
     * Disabled dates contain:
     *
     * cursor-not-allowed
     *
     * Therefore we specifically require:
     *
     * cursor-pointer
     */
    private void clickDay(
            TestObject monthLabel,
            int day) {

        String monthXPath =
                monthLabel.findPropertyValue("xpath")


        println("Month XPath : " + monthXPath)
        println("Day         : " + day)


        /*
         * Find the calendar container that contains the month
         * label, then find its date grid.
         *
         * The exact DOM around the month header can vary, so
         * we locate the nearest ancestor containing a
         * grid-cols-7 calendar.
         */
        String xpath =
                "(" +
                monthXPath +
                "/ancestor::div[" +
                ".//div[" +
                "contains(@class,'grid-cols-7')" +
                " and contains(@class,'gap-1')" +
                "]" +
                "][1]" +
                "//div[" +
                "contains(@class,'grid-cols-7')" +
                " and contains(@class,'gap-1')" +
                "]" +
                "//div[" +
                "normalize-space()='" + day + "'" +
                " and contains(@class,'cursor-pointer')" +
                "]" +
                ")[1]"


        println("Generated Day XPath:")
        println(xpath)


        TestObject dayObject =
                new TestObject(
                        "DynamicCalendarDay_" + day
                )


        dayObject.addProperty(
                "xpath",
                ConditionType.EQUALS,
                xpath
        )


        // --------------------------------------------------------
        // Wait for day to appear
        // --------------------------------------------------------

        WebUI.waitForElementVisible(
                dayObject,
                10,
                FailureHandling.STOP_ON_FAILURE
        )


        // --------------------------------------------------------
        // Scroll to day
        // --------------------------------------------------------

        WebUI.scrollToElement(
                dayObject,
                5
        )


        // --------------------------------------------------------
        // Wait until clickable
        // --------------------------------------------------------

        WebUI.waitForElementClickable(
                dayObject,
                10,
                FailureHandling.STOP_ON_FAILURE
        )


        // --------------------------------------------------------
        // Click day
        // --------------------------------------------------------

        WebUI.click(dayObject)
    }


    // ============================================================
    // Appointment Time Selection
    // ============================================================

    /**
     * Selects the requested appointment time under the
     * requested appointment date.
     *
     * Example:
     *
     * Friday
     * Sep 25
     * 02:30 PM
     */
    private void selectTimeSlot(
            LocalDate appointmentDate,
            String appointmentTime) {


        String dayName =
                appointmentDate
                        .format(DAY_NAME)
                        .toUpperCase(Locale.ENGLISH)


        String monthDay =
                appointmentDate
                        .format(MONTH_DAY)
                        .toUpperCase(Locale.ENGLISH)


        println("------------------------------------------")
        println("Selecting Appointment Time")
        println("Day       : " + dayName)
        println("Date      : " + monthDay)
        println("Time      : " + appointmentTime)
        println("------------------------------------------")


        TestObject slot =
                new TestObject(
                        "DynamicAppointmentTime"
                )


        String xpath =
                "//div[contains(@class,'mb-6')]" +
                "[.//div[normalize-space()='" +
                dayName +
                "']" +
                " and .//div[contains(" +
                "normalize-space(),'" +
                monthDay +
                "')]]" +
                "//button[" +
                ".//*[normalize-space()='" +
                appointmentTime +
                "']" +
                "]"


        println("Appointment Slot XPath:")
        println(xpath)


        slot.addProperty(
                "xpath",
                ConditionType.EQUALS,
                xpath
        )


        // --------------------------------------------------------
        // Scroll to appointment time
        // --------------------------------------------------------

        WebUI.scrollToElement(
                slot,
                10
        )


        // --------------------------------------------------------
        // Wait until clickable
        // --------------------------------------------------------

        WebUI.waitForElementClickable(
                slot,
                10,
                FailureHandling.STOP_ON_FAILURE
        )


        // --------------------------------------------------------
        // Click appointment time
        // --------------------------------------------------------

        WebUI.click(slot)
    }
}

