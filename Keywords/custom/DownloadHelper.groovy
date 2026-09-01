package custom

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.annotation.Keyword
import java.util.Base64

class DownloadHelper {

    @Keyword
    def String captureBlobDownload(String downloadFolder = null, int timeoutSeconds = 30) {

        WebDriver driver = DriverFactory.getWebDriver()
        JavascriptExecutor js = (JavascriptExecutor) driver

        // Inject override BEFORE the click that triggers the blob download
        String injectScript = """
            window.__capturedBlobPromise = null;
            const originalCreateObjectURL = URL.createObjectURL;
            URL.createObjectURL = function(blob) {
                window.__capturedBlobPromise = new Promise((resolve) => {
                    const reader = new FileReader();
                    reader.onloadend = () => resolve(reader.result);
                    reader.readAsDataURL(blob);
                });
                return originalCreateObjectURL.call(URL, blob);
            };
        """
        js.executeScript(injectScript)

        return "INJECTED" // signal injection is done; caller clicks download next
    }

    @Keyword
    def String retrieveCapturedBlobAsFile(String downloadFolder = null, int timeoutSeconds = 30) {

        WebDriver driver = DriverFactory.getWebDriver()
        JavascriptExecutor js = (JavascriptExecutor) driver

        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000)
        String base64DataUrl = null

        while (System.currentTimeMillis() < endTime) {
            base64DataUrl = (String) js.executeAsyncScript("""
                var callback = arguments[arguments.length - 1];
                if (window.__capturedBlobPromise) {
                    window.__capturedBlobPromise.then(callback);
                } else {
                    callback(null);
                }
            """)
            if (base64DataUrl != null) break
            Thread.sleep(500)
        }

        if (base64DataUrl == null) {
            throw new Exception("Blob was not captured within ${timeoutSeconds}s — createObjectURL override may not have fired")
        }

        String base64Content = base64DataUrl.split(',')[1]
        byte[] decodedBytes = Base64.getDecoder().decode(base64Content)

        String dir = downloadFolder ?: System.getProperty("java.io.tmpdir")
        File outFile = new File(dir, "downloaded_ccda_${System.currentTimeMillis()}.xml")
        outFile.bytes = decodedBytes

        return outFile.absolutePath
    }
}