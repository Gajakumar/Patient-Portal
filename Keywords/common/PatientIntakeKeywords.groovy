
package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class PatientIntakeKeywords {

    @Keyword
    def clickPatientIntakeTab(String tabName) {

        WebDriver driver = DriverFactory.getWebDriver()

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(30)
        )

        // Switch to Patient Intake Form iframe
        WebElement iframe = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//iframe[@title='Patient Intake Form - Step 1']")
            )
        )

        driver.switchTo().frame(iframe)

        try {
            WebElement tab = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath(
                        "//ul[@id='tabs-list']//li[normalize-space(.)='${tabName}']"
                    )
                )
            )

            tab.click()

            wait.until(
                ExpectedConditions.attributeToBe(
                    By.xpath(
                        "//ul[@id='tabs-list']//li[normalize-space(.)='${tabName}']"
                    ),
                    "aria-selected",
                    "true"
                )
            )

        } finally {
            driver.switchTo().defaultContent()
        }
    }
}
