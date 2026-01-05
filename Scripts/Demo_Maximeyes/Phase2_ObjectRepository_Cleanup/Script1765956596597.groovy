import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.configuration.RunConfiguration
import java.nio.file.*
import groovy.xml.*

def projectDir = RunConfiguration.getProjectDir()
def repoPath = Paths.get(projectDir, "Object Repository")

println "=== PHASE 2 OBJECT REPOSITORY CLEANUP (DRY RUN) ==="

Files.walk(repoPath).each { path ->
	if (!path.toString().endsWith(".rs")) return

	def originalFile = path.fileName.toString().replace(".rs", "")

	def xml
	try {
		xml = new XmlParser().parse(path.toFile())
	} catch (Exception e) {
		println "SKIP (XML ERROR): $originalFile"
		return
	}

	// 🔹 1. Detect HTML tag
	def tag = xml.tag?.text()?.toLowerCase()

	// 🔹 2. Decide prefix by tag
	String prefix = "obj_"
	switch (tag) {
		case "button": prefix = "btn_"; break
		case "input": prefix = "txt_"; break
		case "select": prefix = "ddl_"; break
		case "a": prefix = "lnk_"; break
		case "span": prefix = "lbl_"; break
		case "label": prefix = "lbl_"; break
	}

	// 🔹 3. Cleanup original name
	def cleanName = originalFile
        .replaceFirst(/(?i)^(btn|txt|ddl|lnk|lbl|obj)[_-]?/, '')
        .replaceFirst(/(?i)^(button|input|select|span|div|a|label)[_-]?/, '')
        .replaceAll(/__+/, '_')

	// 🔹 4. Detect DIGIT / KEYPAD
	boolean isDigit = cleanName.toLowerCase() ==~ /.*digit[_-]?\d.*/
	if (isDigit) {
		prefix = "btn_"
		cleanName = cleanName.replaceAll(/.*digit[_-]?/, "Digit_")
	}

	// 🔹 5. Normalize Case
	cleanName = cleanName
			.split("[_\\-]")
			.collect { it.capitalize() }
			.join("_")

	def newName = prefix + cleanName
	def newPath = path.parent.resolve(newName + ".rs")

	// 🔹 DRY RUN LOG
	if (originalFile != newName) {
		println "RENAME: $originalFile  →  $newName"
	}
	
	// 🔥 UNCOMMENT ONLY AFTER REVIEW
	// Files.move(path, newPath)
}

println "=== PHASE 2 DRY RUN COMPLETE ==="
