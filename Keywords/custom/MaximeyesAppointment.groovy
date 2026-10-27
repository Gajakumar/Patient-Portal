package custom

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.By
import org.openqa.selenium.interactions.Actions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

public class MaximeyesAppointment {
	
	  // =========================================================
    // SCHEDULER CONFIGURATION
    // =========================================================

    static final int START_HOUR = 0
    static final int MIN_PER_ROW = 5

    static final String CELL_ID_PREFIX =
            'scheduler_containerBlock_DXCntv0_'

    // =========================================================
    // CONVERT APPOINTMENT TIME TO SCHEDULER ROW INDEX
    // =========================================================

    static int timeToRowIndex(String timeStr) {

        if (timeStr == null || timeStr.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    'Appointment time cannot be empty.'
            )
        }

        String input = timeStr.trim().toUpperCase(Locale.ENGLISH)

        Date time = null

        List<String> formats = [
            'hh:mm a',
            'h:mm a',
            'HH:mm'
        ]

        for (String format : formats) {

            SimpleDateFormat sdf =
                    new SimpleDateFormat(format, Locale.ENGLISH)

            sdf.setLenient(false)

            // Use a fixed reference date to avoid timezone/DST
            // changes affecting the calculated time.
            sdf.setTimeZone(TimeZone.getTimeZone('UTC'))

            try {
                time = sdf.parse(input)
                break
            } catch (Exception ignored) {
                // Try the next supported format.
            }
        }

        if (time == null) {
            throw new IllegalArgumentException(
                    "Invalid appointment time: '${timeStr}'. " +
                    "Use formats such as 11:50 AM or 14:30."
            )
        }

        Calendar calendar = Calendar.getInstance(
                TimeZone.getTimeZone('UTC'),
                Locale.ENGLISH
        )

        calendar.setTime(time)

        int hour = calendar.get(Calendar.HOUR_OF_DAY)
        int minute = calendar.get(Calendar.MINUTE)

        int totalMinutes =
                ((hour - START_HOUR) * 60) + minute

        if (totalMinutes < 0) {
            throw new IllegalArgumentException(
                    "Appointment time '${timeStr}' is before " +
                    "${START_HOUR}:00 AM, the configured scheduler start."
            )
        }

        if (totalMinutes % MIN_PER_ROW != 0) {
            throw new IllegalArgumentException(
                    "Appointment time '${timeStr}' is not aligned " +
                    "to the ${MIN_PER_ROW}-minute scheduler grid."
            )
        }

        int rowIndex = totalMinutes / MIN_PER_ROW

        println "Appointment Time : ${timeStr}"
        println "Scheduler Row     : ${rowIndex}"

        return rowIndex
    }

    // =========================================================
    // GET OPEN SCHEDULER CELL
    // =========================================================

    static WebElement getSchedulerCell(String timeStr) {

        WebDriver driver = DriverFactory.getWebDriver()

        JavascriptExecutor js = (JavascriptExecutor) driver

        int rowIndex = timeToRowIndex(timeStr)

        String cellId = CELL_ID_PREFIX + rowIndex

        println "Scheduler Cell ID: ${cellId}"

        WebElement cell = driver.findElement(
                By.id(cellId)
        )

        js.executeScript(
                '''
                arguments[0].scrollIntoView({
                    block: 'center',
                    inline: 'nearest'
                });
                ''',
                cell
        )

        WebUI.delay(1)

        return cell
    }

    // =========================================================
    // VERIFY THAT THE SLOT EXISTS AND IS VISIBLE
    // =========================================================

    static boolean isSchedulerSlotAvailable(String timeStr) {

        WebDriver driver = DriverFactory.getWebDriver()

        WebElement cell = getSchedulerCell(timeStr)

        boolean available = cell.isDisplayed() &&
                            cell.isEnabled()

        println "Slot ${timeStr} displayed/enabled: ${available}"

        return available
    }
	
	
	private static void clickSaveButton() {
		
				TestObject saveButton =
						new TestObject('saveButton')
		
				saveButton.addProperty(
						'xpath',
						ConditionType.EQUALS,
						"//span[normalize-space()='Save Appointment']"
				)
		
				WebUI.waitForElementVisible(
						saveButton,
						10
				)
		
				WebUI.waitForElementClickable(
						saveButton,
						10
				)
		
				println 'Clicking Save'
		
				WebUI.click(saveButton)
		
				WebUI.delay(1)
			}

    // =========================================================
    // BOOK APPOINTMENT BY SELECTING AN OPEN SLOT
    // =========================================================

    @Keyword
    static void bookAppointmentAtTime(String apptTime) {

        if (apptTime == null || apptTime.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    'Please provide an appointment time, e.g. 11:50 AM.'
            )
        }

        println ''
        println '================================================'
        println '          BOOK APPOINTMENT FLOW'
        println '================================================'
        println "Appointment Time : ${apptTime}"
        println '================================================'

        WebDriver driver = DriverFactory.getWebDriver()

        // Step 1: Locate and scroll to the requested scheduler slot.
        WebElement cell = getSchedulerCell(apptTime)

        // Step 2: Confirm that the slot is displayed and enabled.
        if (!cell.isDisplayed() || !cell.isEnabled()) {
            throw new IllegalStateException(
                    "Scheduler slot '${apptTime}' is not available " +
                    "for interaction."
            )
        }

        // Step 3: Double-click the open slot.
        println "Double-clicking open slot: ${apptTime}"

        new Actions(driver)
                .moveToElement(cell)
                .pause(300)
                .doubleClick()
                .perform()

        // Step 4: Allow the application to respond.
        WebUI.delay(1)

        println "Double-click performed for slot: ${apptTime}"
		
		
		// -----------------------------------------------------
		//  Save appointment
		// -----------------------------------------------------

		clickSaveButton()
		WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)
		
        println 'Clicked on save button'
    }

}
