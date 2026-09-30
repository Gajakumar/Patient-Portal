package custom

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import java.util.Locale

import com.kms.katalon.core.util.KeywordUtil

class AppointmentKeywords {

	// ============================================================
	// FORMATTERS
	// !! Keep the values you already have in your original class.
	// !! The patterns below are only my best guess.
	// ============================================================
//	private static final DateTimeFormatter INPUT_FMT = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH) // must match DATE_FORMAT used in the test
//	private static final DateTimeFormatter MONTH_FMT = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)  // calendar month header, e.g. "September 2026"
//	private static final DateTimeFormatter DAY_NAME  = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)       // e.g. "Friday"
//	private static final DateTimeFormatter MONTH_DAY = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH)     // e.g. "Sep 25"
	private static final DateTimeFormatter INPUT_FMT =
				DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH)
	    private static final DateTimeFormatter MONTH_FMT =
	            DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH)
	
	    private static final DateTimeFormatter DAY_NAME =
	            DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)
	
	    private static final DateTimeFormatter MONTH_DAY =
	            DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH)

	// ============================================================
	// MAIN KEYWORD
	// ============================================================

	/**
	 * Selects the date range and an appointment time.
	 *
	 * appointmentTime is a PREFERRED time. If it is not available
	 * (already booked by someone else), the first available slot on
	 * that day that is not in excludeTimes is booked instead.
	 *
	 * @return the time that was actually booked (e.g. "01:35 PM")
	 *
	 * The 4-argument call still works because excludeTimes is optional.
	 */
	@Keyword
	String selectAppointmentDateTime(
			String fromDate,
			String toDate,
			String appointmentDate,
			String appointmentTime,
			List<String> excludeTimes = []) {

		// Convert input strings to LocalDate
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
		println("Preferred Time     : " + appointmentTime)
		println("Excluded Times     : " + excludeTimes)
		println("==========================================")

		// Select calendar FROM date
		selectCalendarDate(from)

		// Select calendar TO date
		selectCalendarDate(to)

		// Confirm date range
		TestObject confirmButton = findTestObject('Appointments/Calendar/btn_Confirm')

		WebUI.waitForElementClickable(confirmButton, 10, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(confirmButton)

		// Select appointment time (preferred, else first available)
		return selectTimeSlot(appt, appointmentTime, excludeTimes)
	}


	// ============================================================
	// CALENDAR DATE SELECTION (unchanged logic)
	// ============================================================

	/**
	 * Dynamically selects any calendar date.
	 *
	 * Checks the LEFT and RIGHT calendars. If the target month is
	 * not visible, navigates Previous / Next until it is displayed.
	 */


private void selectCalendarDate(LocalDate targetDate) {

    String targetMonth = targetDate.format(MONTH_FMT)

    println("------------------------------------------")
    println("Selecting Calendar Date")
    println("Target Date  : " + targetDate)
    println("Target Month : " + targetMonth)
    println("------------------------------------------")

    YearMonth targetYearMonth = YearMonth.from(targetDate)

    while (true) {

        // Read currently displayed months
        String leftMonthText = WebUI.getText(
            findTestObject('Appointments/Calendar/lbl_LeftMonth')
        ).trim()

        String rightMonthText = WebUI.getText(
            findTestObject('Appointments/Calendar/lbl_RightMonth')
        ).trim()

        println("Current Left Month  : " + leftMonthText)
        println("Current Right Month : " + rightMonthText)

        // Parse displayed month headers
        YearMonth leftMonth
        YearMonth rightMonth

        try {

            leftMonth = YearMonth.parse(
                leftMonthText,
                MONTH_FMT
            )

            rightMonth = YearMonth.parse(
                rightMonthText,
                MONTH_FMT
            )

        } catch (Exception e) {

            KeywordUtil.markFailed(
                "Unable to parse calendar month header.\n" +
                "Left Month  : ${leftMonthText}\n" +
                "Right Month : ${rightMonthText}\n" +
                "Expected format: MMM yyyy\n" +
                "Error: ${e.message}"
            )

            return
        }

        // Target month is in LEFT calendar
        if (leftMonth == targetYearMonth) {

            println("Target month found in LEFT calendar")

            clickDay(
                findTestObject('Appointments/Calendar/lbl_LeftMonth'),
                targetDate.dayOfMonth
            )

            return
        }

        // Target month is in RIGHT calendar
        if (rightMonth == targetYearMonth) {

            println("Target month found in RIGHT calendar")

            clickDay(
                findTestObject('Appointments/Calendar/lbl_RightMonth'),
                targetDate.dayOfMonth
            )

            return
        }

        // Target month is before the currently visible range
        if (targetYearMonth.isBefore(leftMonth)) {

            println("Target month is before visible months")
            println("Clicking PREVIOUS month")

            WebUI.click(
                findTestObject('Appointments/Calendar/btn_Previous')
            )

        } else {

            // Target month is after the currently visible range
            println("Target month is after visible months")
            println("Clicking NEXT month")

            WebUI.click(
                findTestObject('Appointments/Calendar/btn_Next')
            )
        }

        // Allow calendar DOM to update
        WebUI.delay(0.5)
    }
}


	// ============================================================
	// CLICK CALENDAR DAY (unchanged logic)
	// ============================================================

	/**
	 * Clicks the requested day inside the calendar associated with
	 * the supplied month label (lbl_LeftMonth or lbl_RightMonth).
	 *
	 * Enabled dates contain "cursor-pointer";
	 * disabled dates contain "cursor-not-allowed".
	 */
	private void clickDay(TestObject monthLabel, int day) {

		String monthXPath = monthLabel.findPropertyValue("xpath")

		println("Month XPath : " + monthXPath)
		println("Day         : " + day)

		// Nearest ancestor of the month label that contains a grid-cols-7 calendar
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

		TestObject dayObject = new TestObject("DynamicCalendarDay_" + day)
		dayObject.addProperty("xpath", ConditionType.EQUALS, xpath)

		// Wait for day to appear
		WebUI.waitForElementVisible(dayObject, 10, FailureHandling.STOP_ON_FAILURE)

		// Scroll to day
		WebUI.scrollToElement(dayObject, 5)

		// Wait until clickable
		WebUI.waitForElementClickable(dayObject, 10, FailureHandling.STOP_ON_FAILURE)

		// Click day
		WebUI.click(dayObject)
	}


	// ============================================================
	// HELPER: normalize odd spaces (nbsp / narrow nbsp) and trim
	// ============================================================
	private String normTime(String s) {
		return s.replaceAll(/[\s\u00A0\u202F]+/, ' ').trim()
	}


	// ============================================================
	// APPOINTMENT TIME SELECTION (with fallback)
	// ============================================================

	/**
	 * Picks a time under the requested date:
	 *  1. Reads all usable slots inside that day's container.
	 *  2. Uses the preferred time if it is usable.
	 *  3. Otherwise uses the first usable slot not in excludeTimes.
	 *  4. Clicks it and returns the time that was booked.
	 */
	private String selectTimeSlot(
			LocalDate appointmentDate,
			String preferredTime,
			List<String> excludeTimes) {

		String dayName  = appointmentDate.format(DAY_NAME).toUpperCase(Locale.ENGLISH)
		String monthDay = appointmentDate.format(MONTH_DAY).toUpperCase(Locale.ENGLISH)

		println("------------------------------------------")
		println("Selecting Appointment Time")
		println("Day       : " + dayName)
		println("Date      : " + monthDay)
		println("Preferred : " + preferredTime)
		println("------------------------------------------")

		// Container of the requested day
		String dayContainerXPath =
				"//div[contains(@class,'mb-6')]" +
				"[.//div[normalize-space()='" + dayName + "']" +
				" and .//div[contains(normalize-space(),'" + monthDay + "')]]"

		// ---- Step 1: read every enabled button inside that day container ----
		TestObject allSlots = new TestObject("DayAllSlots")
		allSlots.addProperty("xpath", ConditionType.EQUALS,
				dayContainerXPath + "//button[@type='button' and not(@disabled) and (contains(., 'AM') or contains(., 'PM'))]")

		// Wait for slots to render (does not fail here; checked below)
		WebUI.waitForElementPresent(allSlots, 15, FailureHandling.OPTIONAL)

		List<WebElement> buttons = WebUI.findWebElements(allSlots, 5)
		println("Buttons found in day container: " + buttons.size())

		// ---- Step 2: keep only real, usable, not-yet-used time slots ----
		List<String> excluded = excludeTimes.collect { normTime(it) }

		List<String> available = []
		buttons.each { WebElement el ->
			String t   = normTime(el.getText())
			String cls = el.getAttribute("class") ?: ""

			boolean looksLikeTime    = (t ==~ /^\d{1,2}:\d{2} (AM|PM)$/)
			boolean unavailableStyle = cls.contains("cursor-not-allowed")   // adjust if booked slots use another class

			if (looksLikeTime && el.isDisplayed() && el.isEnabled()
					&& !unavailableStyle && !excluded.contains(t) && !available.contains(t)) {
				available.add(t)
			}
		}

		println("Available slots: " + available)

		if (available.isEmpty()) {
			throw new Exception("No available time slots for " + dayName + " " + monthDay)
		}

		// ---- Step 3: preferred time if available, else first available ----
		String wanted     = normTime(preferredTime)
		String timeToBook = available.contains(wanted) ? wanted : available.first()

		if (timeToBook != wanted) {
			println("Preferred time " + wanted + " is not available. Falling back to " + timeToBook)
		}

		// ---- Step 4: click the chosen slot ----
		TestObject slot = new TestObject("DynamicAppointmentTime")

		String xpath =
				dayContainerXPath +
				"//button[.//*[normalize-space()='" + timeToBook + "']]"

		println("Appointment Slot XPath:")
		println(xpath)

		slot.addProperty("xpath", ConditionType.EQUALS, xpath)

		WebUI.scrollToElement(slot, 10)
		WebUI.waitForElementClickable(slot, 10, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(slot)

		println("Booked time: " + timeToBook)
		return timeToBook
	}
}














//package custom
//
//import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
//
//import java.time.LocalDate
//import java.time.YearMonth
//import java.time.format.DateTimeFormatter
//import java.util.Locale
//
//import com.kms.katalon.core.annotation.Keyword
//import com.kms.katalon.core.model.FailureHandling
//import com.kms.katalon.core.testobject.ConditionType
//import com.kms.katalon.core.testobject.TestObject
//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//
//
//class AppointmentKeywords {
//
//    // ============================================================
//    // Date Formatters
//    // ============================================================
//
//    private static final DateTimeFormatter INPUT_FMT =
//            DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH)
//
//    /*
//     * Calendar UI displays:
//     *
//     * Sep 2026
//     * Oct 2026
//     *
//     * Locale.ENGLISH is important because the JVM may not
//     * use English as its default locale.
//     */
//    private static final DateTimeFormatter MONTH_FMT =
//            DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH)
//
//    private static final DateTimeFormatter DAY_NAME =
//            DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)
//
//    private static final DateTimeFormatter MONTH_DAY =
//            DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH)
//
//
//    // ============================================================
//    // Main Keyword
//    // ============================================================
//
//    /**
//     * Selects:
//     *
//     * 1. Calendar start date
//     * 2. Calendar end date
//     * 3. Appointment date
//     * 4. Appointment time
//     *
//     * Example:
//     *
//     * selectAppointmentDateTime(
//     *     "09/01/2026",
//     *     "09/30/2026",
//     *     "09/25/2026",
//     *     "02:30 PM"
//     * )
//     */
//    @Keyword
//    def selectAppointmentDateTime(
//            String fromDate,
//            String toDate,
//            String appointmentDate,
//            String appointmentTime) {
//
//        // --------------------------------------------------------
//        // Convert input strings to LocalDate
//        // --------------------------------------------------------
//
//        LocalDate from = LocalDate.parse(
//                fromDate,
//                INPUT_FMT
//        )
//
//        LocalDate to = LocalDate.parse(
//                toDate,
//                INPUT_FMT
//        )
//
//        LocalDate appt = LocalDate.parse(
//                appointmentDate,
//                INPUT_FMT
//        )
//
//        println("==========================================")
//        println("Appointment Selection")
//        println("From Date          : " + fromDate)
//        println("To Date            : " + toDate)
//        println("Appointment Date   : " + appointmentDate)
//        println("Appointment Time   : " + appointmentTime)
//        println("==========================================")
//
//
//        // --------------------------------------------------------
//        // Select calendar FROM date
//        // --------------------------------------------------------
//
//        selectCalendarDate(from)
//
//
//        // --------------------------------------------------------
//        // Select calendar TO date
//        // --------------------------------------------------------
//
//        selectCalendarDate(to)
//
//
//        // --------------------------------------------------------
//        // Confirm date range
//        // --------------------------------------------------------
//
//        TestObject confirmButton =
//                findTestObject('Appointments/Calendar/btn_Confirm')
//
//        WebUI.waitForElementClickable(
//                confirmButton,
//                10,
//                FailureHandling.STOP_ON_FAILURE
//        )
//
//        WebUI.click(confirmButton)
//
//
//        // --------------------------------------------------------
//        // Select appointment time
//        // --------------------------------------------------------
//
//        selectTimeSlot(
//                appt,
//                appointmentTime
//        )
//    }
//
//
//    // ============================================================
//    // Calendar Date Selection
//    // ============================================================
//
//    /**
//     * Dynamically selects any calendar date.
//     *
//     * The method checks:
//     *
//     * LEFT calendar
//     * RIGHT calendar
//     *
//     * If the target month is not visible, it navigates
//     * Previous / Next until the target month is displayed.
//     */
//    private void selectCalendarDate(LocalDate targetDate) {
//
//        String targetMonth =
//                targetDate.format(MONTH_FMT)
//
//        println("------------------------------------------")
//        println("Selecting Calendar Date")
//        println("Target Date  : " + targetDate)
//        println("Target Month : " + targetMonth)
//        println("------------------------------------------")
//
//
//        while (true) {
//
//            // ----------------------------------------------------
//            // Read currently displayed months
//            // ----------------------------------------------------
//
//            String leftMonth =
//                    WebUI.getText(
//                            findTestObject(
//                                    'Appointments/Calendar/lbl_LeftMonth'
//                            )
//                    ).trim()
//
//            String rightMonth =
//                    WebUI.getText(
//                            findTestObject(
//                                    'Appointments/Calendar/lbl_RightMonth'
//                            )
//                    ).trim()
//
//
//            println("Current Left Month  : " + leftMonth)
//            println("Current Right Month : " + rightMonth)
//
//
//            // ----------------------------------------------------
//            // Target month is LEFT calendar
//            // ----------------------------------------------------
//
//            if (leftMonth.equalsIgnoreCase(targetMonth)) {
//
//                println(
//                        "Target month found in LEFT calendar"
//                )
//
//                clickDay(
//                        findTestObject(
//                                'Appointments/Calendar/lbl_LeftMonth'
//                        ),
//                        targetDate.dayOfMonth
//                )
//
//                return
//            }
//
//
//            // ----------------------------------------------------
//            // Target month is RIGHT calendar
//            // ----------------------------------------------------
//
//            if (rightMonth.equalsIgnoreCase(targetMonth)) {
//
//                println(
//                        "Target month found in RIGHT calendar"
//                )
//
//                clickDay(
//                        findTestObject(
//                                'Appointments/Calendar/lbl_RightMonth'
//                        ),
//                        targetDate.dayOfMonth
//                )
//
//                return
//            }
//
//
//            // ----------------------------------------------------
//            // Target month is not visible
//            // Determine navigation direction
//            // ----------------------------------------------------
//
//            YearMonth left =
//                    YearMonth.parse(
//                            leftMonth,
//                            MONTH_FMT
//                    )
//
//            YearMonth target =
//                    YearMonth.from(targetDate)
//
//
//            if (target.isBefore(left)) {
//
//                println(
//                        "Clicking PREVIOUS month"
//                )
//
//                WebUI.click(
//                        findTestObject(
//                                'Appointments/Calendar/btn_Previous'
//                        )
//                )
//
//            } else {
//
//                println(
//                        "Clicking NEXT month"
//                )
//
//                WebUI.click(
//                        findTestObject(
//                                'Appointments/Calendar/btn_Next'
//                        )
//                )
//            }
//
//
//            // Allow calendar DOM to update
//            WebUI.delay(0.5)
//        }
//    }
//
//
//    // ============================================================
//    // Click Calendar Day
//    // ============================================================
//
//    /**
//     * Clicks the requested day inside the calendar associated
//     * with the supplied month label.
//     *
//     * The supplied TestObject is either:
//     *
//     * lbl_LeftMonth
//     * lbl_RightMonth
//     *
//     * The date HTML is:
//     *
//     * <div class="grid grid-cols-7 gap-1">
//     *
//     *     ...
//     *
//     *     <div class="... cursor-pointer ...">
//     *         25
//     *     </div>
//     *
//     * </div>
//     *
//     * Disabled dates contain:
//     *
//     * cursor-not-allowed
//     *
//     * Therefore we specifically require:
//     *
//     * cursor-pointer
//     */
//    private void clickDay(
//            TestObject monthLabel,
//            int day) {
//
//        String monthXPath =
//                monthLabel.findPropertyValue("xpath")
//
//
//        println("Month XPath : " + monthXPath)
//        println("Day         : " + day)
//
//
//        /*
//         * Find the calendar container that contains the month
//         * label, then find its date grid.
//         *
//         * The exact DOM around the month header can vary, so
//         * we locate the nearest ancestor containing a
//         * grid-cols-7 calendar.
//         */
//        String xpath =
//                "(" +
//                monthXPath +
//                "/ancestor::div[" +
//                ".//div[" +
//                "contains(@class,'grid-cols-7')" +
//                " and contains(@class,'gap-1')" +
//                "]" +
//                "][1]" +
//                "//div[" +
//                "contains(@class,'grid-cols-7')" +
//                " and contains(@class,'gap-1')" +
//                "]" +
//                "//div[" +
//                "normalize-space()='" + day + "'" +
//                " and contains(@class,'cursor-pointer')" +
//                "]" +
//                ")[1]"
//
//
//        println("Generated Day XPath:")
//        println(xpath)
//
//
//        TestObject dayObject =
//                new TestObject(
//                        "DynamicCalendarDay_" + day
//                )
//
//
//        dayObject.addProperty(
//                "xpath",
//                ConditionType.EQUALS,
//                xpath
//        )
//
//
//        // --------------------------------------------------------
//        // Wait for day to appear
//        // --------------------------------------------------------
//
//        WebUI.waitForElementVisible(
//                dayObject,
//                10,
//                FailureHandling.STOP_ON_FAILURE
//        )
//
//
//        // --------------------------------------------------------
//        // Scroll to day
//        // --------------------------------------------------------
//
//        WebUI.scrollToElement(
//                dayObject,
//                5
//        )
//
//
//        // --------------------------------------------------------
//        // Wait until clickable
//        // --------------------------------------------------------
//
//        WebUI.waitForElementClickable(
//                dayObject,
//                10,
//                FailureHandling.STOP_ON_FAILURE
//        )
//
//
//        // --------------------------------------------------------
//        // Click day
//        // --------------------------------------------------------
//
//        WebUI.click(dayObject)
//    }
//
//
//    // ============================================================
//    // Appointment Time Selection
//    // ============================================================
//
//    /**
//     * Selects the requested appointment time under the
//     * requested appointment date.
//     *
//     * Example:
//     *
//     * Friday
//     * Sep 25
//     * 02:30 PM
//     */
//    private void selectTimeSlot(
//            LocalDate appointmentDate,
//            String appointmentTime) {
//
//
//        String dayName =
//                appointmentDate
//                        .format(DAY_NAME)
//                        .toUpperCase(Locale.ENGLISH)
//
//
//        String monthDay =
//                appointmentDate
//                        .format(MONTH_DAY)
//                        .toUpperCase(Locale.ENGLISH)
//
//
//        println("------------------------------------------")
//        println("Selecting Appointment Time")
//        println("Day       : " + dayName)
//        println("Date      : " + monthDay)
//        println("Time      : " + appointmentTime)
//        println("------------------------------------------")
//
//
//        TestObject slot =
//                new TestObject(
//                        "DynamicAppointmentTime"
//                )
//
//
//        String xpath =
//                "//div[contains(@class,'mb-6')]" +
//                "[.//div[normalize-space()='" +
//                dayName +
//                "']" +
//                " and .//div[contains(" +
//                "normalize-space(),'" +
//                monthDay +
//                "')]]" +
//                "//button[" +
//                ".//*[normalize-space()='" +
//                appointmentTime +
//                "']" +
//                "]"
//
//
//        println("Appointment Slot XPath:")
//        println(xpath)
//
//
//        slot.addProperty(
//                "xpath",
//                ConditionType.EQUALS,
//                xpath
//        )
//
//
//        // --------------------------------------------------------
//        // Scroll to appointment time
//        // --------------------------------------------------------
//
//        WebUI.scrollToElement(
//                slot,
//                10
//        )
//
//
//        // --------------------------------------------------------
//        // Wait until clickable
//        // --------------------------------------------------------
//
//        WebUI.waitForElementClickable(
//                slot,
//                10,
//                FailureHandling.STOP_ON_FAILURE
//        )
//
//
//        // --------------------------------------------------------
//        // Click appointment time
//        // --------------------------------------------------------
//
//        WebUI.click(slot)
//    }
//}
//
