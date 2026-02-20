import com.kms.katalon.core.annotation.*
import com.kms.katalon.core.context.*
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.model.FailureHandling

class FailureScreenshotListener {
	
	static void addCapabilities() {
		// Preferences dictionary
		Map<String, Object> prefs = new HashMap<>()
		prefs.put("profile.default_content_setting_values.media_stream_camera", 1)
		prefs.put("profile.default_content_setting_values.media_stream_mic", 1)
		prefs.put("profile.default_content_setting_values.geolocation", 1)
		prefs.put("profile.default_content_setting_values.notifications", 1)
		prefs.put("profile.default_content_setting_values.popups", 1)
		prefs.put("profile.default_content_setting_values.automatic_downloads", 1)
		prefs.put("profile.default_content_setting_values.mixed_script", 1)
		prefs.put("profile.default_content_setting_values.media_stream", 1)

		// Optional – Chrome will ignore this, but harmless
		prefs.put("profile.default_content_setting_values.clipboard", 1)

		// Build a single args list (all entries must be pure java.lang.String, no GString)
		List<String> args = new ArrayList<>()
		args.add("--use-fake-ui-for-media-stream")
		args.add("--disable-notifications")
		// Clipboard / security workarounds
		args.add("--disable-blink-features=BlockClipboardAPI")


		// Fake audio device for media stream

		args.add("--use-fake-device-for-media-stream")
		args.add("--no-sandbox")
		args.add("--disable-dev-shm-usage")


		// Apply to TestCloud / local run BEFORE browser launch.
		// These are WebDriver preference properties, not legacy "desiredCapabilities",
		// so they are W3C-compliant and won't trigger W3CCapabilityViolationException.
		RunConfiguration.setWebDriverPreferencesProperty("prefs", prefs)
		RunConfiguration.setWebDriverPreferencesProperty("args", args)
	
	}

	/*
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		
	
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseVariables()

		WebUI.openBrowser('')

		'Maximize the window'
		WebUI.maximizeWindow()

	}
    /**
     * Runs AFTER every test case
     */
    @AfterTestCase
    def afterTestCase(TestCaseContext testCaseContext) {

        println "⏹ Finished Test Case : " + testCaseContext.getTestCaseId()
        println "📌 Status            : " + testCaseContext.getTestCaseStatus()

        if (testCaseContext.getTestCaseStatus() != 'PASS') {

            try {
                def driver = DriverFactory.getWebDriver()

                // ✅ SAFETY CHECK
                if (driver == null || driver.getSessionId() == null) {
                    println "⚠ Browser session not available. Screenshot skipped."
                    return
                }

                String projectDir = RunConfiguration.getProjectDir()
                String failedFolder = projectDir + "/Screenshots/FAILED"
                new File(failedFolder).mkdirs()

                String testCaseName = testCaseContext.getTestCaseId()
                        .replaceAll('[^a-zA-Z0-9_]', '_')

                String timeStamp = new Date().format("yyyyMMdd_HHmmss")

                String screenshotPath =
                        failedFolder + "/" + testCaseName + "_" + timeStamp + ".png"

                WebUI.takeScreenshot(screenshotPath, FailureHandling.OPTIONAL)

                println "📸 Screenshot saved at:"
                println screenshotPath

            } catch (Exception e) {
                println "❌ Screenshot capture failed: " + e.getMessage()
            }
        }

        // Close browser safely
        try {
            if (DriverFactory.getWebDriver() != null) {
                WebUI.closeBrowser()
                println "🧹 Browser closed"
            }
        } catch (Exception e) {
            println "⚠ Browser already closed"
        }
    }

    /**
     * Runs BEFORE test suite
     */
    @BeforeTestSuite
    def beforeTestSuite(TestSuiteContext testSuiteContext) {
        println "🚀 Starting Test Suite : " + testSuiteContext.getTestSuiteId()
    }

    /**
     * Runs AFTER test suite
     */
    @AfterTestSuite
    def afterTestSuite(TestSuiteContext testSuiteContext) {
        println "🏁 Finished Test Suite : " + testSuiteContext.getTestSuiteId()
    }
}
