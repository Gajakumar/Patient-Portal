package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import java.nio.file.Files
import java.nio.file.Path
import internal.GlobalVariable

import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.firefox.FirefoxOptions

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chromium.ChromiumDriver
import com.kms.katalon.core.configuration.RunConfiguration

import java.nio.file.*

class BrowserDownloadHelper {

    static Path downloadDir

    @Keyword
    static void configureDownloadDirectory() {

        downloadDir = Files.createTempDirectory("katalon-downloads")

        def driver = DriverFactory.getWebDriver()

        if (driver instanceof ChromeDriver) {
            Map<String, Object> prefs = new HashMap<>()
            prefs.put("download.default_directory", downloadDir.toFile().getAbsolutePath())
            prefs.put("download.prompt_for_download", false)
            prefs.put("safebrowsing.enabled", true)

            ChromeOptions options = new ChromeOptions()
            options.setExperimentalOption("prefs", prefs)

        } else {
            FirefoxProfile profile = new FirefoxProfile()
            profile.setPreference("browser.download.dir", downloadDir.toFile().getAbsolutePath())
            profile.setPreference("browser.download.folderList", 2)
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xml,text/xml")

            FirefoxOptions options = new FirefoxOptions()
            options.setProfile(profile)
        }
    }

   @Keyword
static File waitForLatestXML(int timeoutSeconds) {

    String downloadPath = System.getProperty("user.home") + "/Downloads"
    File downloadDir = new File(downloadPath)

    long endTime = System.currentTimeMillis() + timeoutSeconds * 1000

    while (System.currentTimeMillis() < endTime) {

        File[] files = downloadDir.listFiles()
        if (files != null) {

            File latestXml = files
                .findAll { it.isFile() && it.name.toLowerCase().endsWith(".xml") }
                .sort { -it.lastModified() }
                .find { true }

            if (latestXml != null) {
                return latestXml
            }
        }

        Thread.sleep(1000)
    }

    assert false : "❌ XML file not downloaded within timeout"
}

}