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

import internal.GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.JavascriptExecutor

class XMLUploadHelper {

    @Keyword
    static void uploadStoredXMLViaJS() {

        File xmlFile = FileStore.getFile()
        String absolutePath = xmlFile.absolutePath.replace("\\", "\\\\")

        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getWebDriver()

        js.executeScript("""
            const input = document.createElement('input');
            input.type = 'file';
            input.style.display = 'none';

            document.body.appendChild(input);

            const dt = new DataTransfer();
            const file = new File([''], '${xmlFile.name}', { type: 'text/xml' });
            dt.items.add(file);
            input.files = dt.files;

            input.dispatchEvent(new Event('change', { bubbles: true }));
        """)

        println "✅ XML uploaded successfully: ${xmlFile.name}"
    }
}
