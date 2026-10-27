
package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.List
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.TimeoutException
import java.time.Duration

class VerifyHipaaPolicy {

    // ---------------------------------------------------------
    // Reusable TestObject helpers
    // ---------------------------------------------------------

    private TestObject byId(String id) {
        TestObject object = new TestObject("id_" + id)
        object.addProperty(
            "id",
            ConditionType.EQUALS,
            id
        )
        return object
    }

    private TestObject byCss(String css) {
        TestObject object = new TestObject("css_" + css)
        object.addProperty(
            "css",
            ConditionType.EQUALS,
            css
        )
        return object
    }

    // ---------------------------------------------------------
    // Switch to the iframe containing the policy form
    // ---------------------------------------------------------

    

private void switchToPolicyFormFrame(
    String iframeXPath,
    String targetElementId
) {
    WebDriver driver = DriverFactory.getWebDriver()
    driver.switchTo().defaultContent()

    By frameLocator = By.xpath(iframeXPath)

    try {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until({
            WebDriver d ->
                return !d.findElements(frameLocator).isEmpty()
        })

        driver.switchTo().frame(
            driver.findElement(frameLocator)
        )

        new WebDriverWait(driver, Duration.ofSeconds(30)).until({
            WebDriver d ->
                return !d.findElements(By.id(targetElementId)).isEmpty()
        })

        KeywordUtil.logInfo(
            "Switched to iframe: ${iframeXPath}. " +
            "Found element: ${targetElementId}"
        )

    } catch (Exception e) {
        driver.switchTo().defaultContent()

        KeywordUtil.markFailedAndStop(
            "Unable to locate iframe or element '${targetElementId}'. " +
            "Iframe XPath: ${iframeXPath}. Error: ${e.message}"
        )
    }
}



    // ---------------------------------------------------------
    // Verify patient name
    // ---------------------------------------------------------

    private void verifyPatientName(String expectedPatientName) {
        WebUI.waitForElementPresent(byId('first_10'), 15)
        WebUI.waitForElementPresent(byId('last_10'), 15)

        String firstName = WebUI.getAttribute(
            byId('first_10'),
            'value'
        )

        String lastName = WebUI.getAttribute(
            byId('last_10'),
            'value'
        )

        String actualPatientName =
            "${firstName} ${lastName}".trim()

        KeywordUtil.logInfo(
            "Expected Patient: ${expectedPatientName}"
        )

        KeywordUtil.logInfo(
            "Actual Patient: ${actualPatientName}"
        )

        if (!actualPatientName.equalsIgnoreCase(
            expectedPatientName.trim()
        )) {
            KeywordUtil.markFailedAndStop(
                "Patient name mismatch. Expected: " +
                "${expectedPatientName}, Actual: ${actualPatientName}"
            )
        }

        KeywordUtil.logInfo("Patient name verified successfully.")
    }

    // ---------------------------------------------------------
    // Verify today's date
    // ---------------------------------------------------------

    private void verifyTodayDate(String dateElementId) {
        WebUI.waitForElementPresent(
            byId(dateElementId),
            15
        )

        String actualDate = WebUI.getAttribute(
            byId(dateElementId),
            'value'
        )

        String expectedDate =
            new SimpleDateFormat('MM-dd-yyyy').format(new Date())

        KeywordUtil.logInfo(
            "Expected Date: ${expectedDate}"
        )

        KeywordUtil.logInfo(
            "Actual Date: ${actualDate}"
        )

        if (actualDate?.trim() != expectedDate) {
            KeywordUtil.markFailedAndStop(
                "Policy date mismatch. Expected: ${expectedDate}, " +
                "Actual: ${actualDate}"
            )
        }

        KeywordUtil.logInfo("Policy date verified successfully.")
    }

    // ---------------------------------------------------------
    // Verify policy heading
    // ---------------------------------------------------------

    private void verifyPolicyHeading(String expectedHeading) {
        WebUI.waitForElementPresent(byId('header_1'), 15)

        String actualHeading = WebUI.getText(
            byId('header_1')
        ).trim()

        KeywordUtil.logInfo(
            "Expected Heading: ${expectedHeading}"
        )

        KeywordUtil.logInfo(
            "Actual Heading: ${actualHeading}"
        )

        if (!actualHeading.equalsIgnoreCase(expectedHeading)) {
            KeywordUtil.markFailedAndStop(
                "Policy heading mismatch. Expected: " +
                "${expectedHeading}, Actual: ${actualHeading}"
            )
        }
    }

    // ---------------------------------------------------------
    // Select Accepted or Declined
    // ---------------------------------------------------------

    
private void selectPolicyStatus(
    String status,
    String acceptedRadioId,
    String declinedRadioId
) {
    String normalizedStatus = status?.trim()?.toLowerCase()
    String radioId

    if (normalizedStatus == 'accepted') {
        radioId = acceptedRadioId
    } else if (normalizedStatus == 'declined') {
        radioId = declinedRadioId
    } else {
        KeywordUtil.markFailedAndStop(
            "Invalid status '${status}'. Use Accepted or Declined."
        )
        return
    }

    TestObject radioObject = byId(radioId)

    WebUI.waitForElementPresent(radioObject, 15)

    // Skip clicking if the requested radio button is already selected.
    if (WebUI.verifyElementChecked(
        radioObject,
        2,
        com.kms.katalon.core.model.FailureHandling.OPTIONAL
    )) {
        KeywordUtil.logInfo(
            "Radio button '${status}' is already selected. Skipping click."
        )
        return
    }

    // Click the associated label because it overlays the radio input.
    TestObject labelObject = byId('label_' + radioId)

    WebUI.waitForElementPresent(labelObject, 10)
    WebUI.scrollToElement(labelObject, 5)
    WebUI.click(labelObject)

    // Verify selection after clicking.
    if (!WebUI.verifyElementChecked(
        radioObject,
        5,
        com.kms.katalon.core.model.FailureHandling.OPTIONAL
    )) {
        KeywordUtil.markFailedAndStop(
            "Failed to select policy status: ${status}"
        )
    }

    KeywordUtil.logInfo(
        "Radio button '${status}' selected successfully."
    )
}


    // ---------------------------------------------------------
    // Draw and verify signature
    // ---------------------------------------------------------

    private void signPolicy() {
        WebDriver driver = DriverFactory.getWebDriver()

        TestObject canvasObject = byCss(
            "#signature_pad_9 canvas.signatureCanvas"
        )

        WebUI.waitForElementPresent(canvasObject, 15)
        WebUI.scrollToElement(canvasObject, 5)

        WebElement canvas = WebUI.findWebElement(
            canvasObject,
            10
        )

        // Draw a simple signature-like stroke on the canvas.
        Actions actions = new Actions(driver)

        actions.moveToElement(canvas, -35, 5)
            .clickAndHold()
            .moveByOffset(10, -8)
            .moveByOffset(10, 12)
            .moveByOffset(10, -15)
            .moveByOffset(10, 8)
            .moveByOffset(10, -3)
            .release()
            .perform()

        WebUI.delay(1)

        String signatureValue = WebUI.getAttribute(
            byId('input_9'),
            'value'
        )

        if (signatureValue == null ||
            signatureValue.trim().isEmpty()) {

            KeywordUtil.markFailedAndStop(
                "Signature is empty. The signature canvas may not " +
                "have registered the drawing."
            )
        }

        KeywordUtil.logInfo(
            "Signature entered successfully."
        )
    }

    // ---------------------------------------------------------
    // Submit policy
    // ---------------------------------------------------------

    private void submitPolicy() {
        WebUI.waitForElementPresent(byId('input_2'), 15)
        WebUI.click(byId('input_2'))

        KeywordUtil.logInfo("Policy submit button clicked.")
    }

    // =========================================================
    // HIPAA POLICY KEYWORD
    // =========================================================

    @Keyword
    def verifyAndCompleteHipaaPolicy(
        String expectedPatientName,
        String status
    ) {
        KeywordUtil.logInfo("Starting HIPAA Policy verification.")

switchToPolicyFormFrame(
    "//iframe[@title='Patient Intake Form - Step 1']",
    'first_10'
)

        verifyPolicyHeading('HIPAA Policy')
        verifyPatientName(expectedPatientName)
        verifyTodayDate('lite_mode_42')

        selectPolicyStatus(
            status,
            'input_43_0',
            'input_43_1'
        )

        signPolicy()
        submitPolicy()

        KeywordUtil.logInfo(
            "HIPAA Policy verification and submission completed."
        )
    }

    // =========================================================
    // BILLING POLICY KEYWORD
    // =========================================================

    @Keyword
    def verifyAndCompleteBillingPolicy(
        String expectedPatientName,
        String status
    ) {
        KeywordUtil.logInfo("Starting Billing Policy verification.")

switchToPolicyFormFrame(
    "//iframe[@title='Patient Intake Form - Step 2']",
    'first_10'
)

        verifyPolicyHeading('Billing Policy')
        verifyPatientName(expectedPatientName)
        verifyTodayDate('lite_mode_18')

        selectPolicyStatus(
            status,
            'input_19_0',
            'input_19_1'
        )

        signPolicy()
        submitPolicy()

        KeywordUtil.logInfo(
            "Billing Policy verification and submission completed."
        )
    }
}
