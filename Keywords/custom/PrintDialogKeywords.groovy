package custom

import java.util.Set
import java.util.concurrent.*

import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.Keys

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.logging.KeywordLogger

public class PrintDialogKeywords {

    private static KeywordLogger logger = new KeywordLogger()

    /**
     * Closes the Chrome print dialog, whether it opens as a separate
     * chrome://print window OR renders as an in-tab overlay on the current window.
     *
     * @param originalHandle the window handle captured BEFORE print was triggered
     * @param maxWaitSeconds how long to poll for a new window before assuming in-tab overlay
     */
    @Keyword
    def boolean verifyAndClosePrintDialog(String originalHandle, int maxWaitSeconds = 5) {
        WebDriver driver = DriverFactory.getWebDriver()

        Set<String> originalHandles = new HashSet<>()
        originalHandles.add(originalHandle)

        String printWindowHandle = null
        int retries = maxWaitSeconds

        // Poll briefly to see if print opened as a SEPARATE window
        while (retries > 0 && printWindowHandle == null) {
            Set<String> allHandles = driver.getWindowHandles()
            allHandles.removeAll(originalHandles)

            if (allHandles.size() > 0) {
                printWindowHandle = allHandles.iterator().next()
            } else {
                WebUI.delay(1)
                retries--
            }
        }

        if (printWindowHandle != null) {
            // CASE 1: Print opened as a separate window (older Chrome behavior)
            driver.switchTo().window(printWindowHandle)
            String url = driver.getCurrentUrl()
            logger.logInfo("Print dialog opened as separate window. URL: " + url)

            closeWithTimeout(driver, 5)

            try {
                driver.switchTo().window(originalHandle)
            } catch (Exception e) {
                logger.logInfo("Could not switch back to original window: " + e.getMessage())
            }
            return true

        } else {
            // CASE 2: Print rendered as an in-tab overlay on the CURRENT window
            logger.logInfo("No separate print window found — assuming in-tab print overlay. Sending ESCAPE.")
            try {
                driver.switchTo().window(originalHandle)
                new Actions(driver).sendKeys(Keys.ESCAPE).perform()
                WebUI.delay(1)
                logger.logInfo("ESCAPE sent to close in-tab print overlay.")
                return true
            } catch (Exception e) {
                logger.logInfo("Failed to send ESCAPE to close print overlay: " + e.getMessage())
                return false
            }
        }
    }

    /**
     * Attempts driver.close() with a hard timeout, since close() on a
     * chrome://print window can hang up to ~20s natively.
     */
    private void closeWithTimeout(WebDriver driver, int timeoutSeconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor()
        Future future = executor.submit({ driver.close() } as Callable)

        try {
            future.get(timeoutSeconds, TimeUnit.SECONDS)
            logger.logInfo("Print window closed via driver.close()")
        } catch (TimeoutException te) {
            future.cancel(true)
            logger.logInfo("driver.close() timed out after ${timeoutSeconds}s, falling back to ESCAPE")
            try {
                new Actions(driver).sendKeys(Keys.ESCAPE).perform()
            } catch (Exception e2) {
                logger.logInfo("ESCAPE fallback also failed: " + e2.getMessage())
            }
        } catch (Exception e) {
            logger.logInfo("driver.close() failed: " + e.getMessage() + " — falling back to ESCAPE")
            try {
                new Actions(driver).sendKeys(Keys.ESCAPE).perform()
            } catch (Exception e2) {
                logger.logInfo("ESCAPE fallback also failed: " + e2.getMessage())
            }
        } finally {
            executor.shutdownNow()
        }
    }
}