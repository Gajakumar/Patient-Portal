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

println "=== PHASE 2.2 OBJECT REPOSITORY REFINEMENT (DRY RUN) ==="

List<String> deleteCandidates = []

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

	// 🔹 1. Extract selector text
	def selectorText = xml
			?.children()
			?.find { it.name() == 'selectorCollection' }
			?.children()
			?.findAll { it.name() == 'entry' }
			?.collect { it.value?.text() }
			?.join(" ")
			?.toLowerCase()

	// 🔹 2. Decide prefix
	String prefix = "obj_"

	if (selectorText) {
		if (selectorText.contains("//button") || selectorText.contains("<button"))
			prefix = "btn_"
		else if (
				selectorText.contains("//input") &&
				(selectorText.contains("type='button'") ||
				 selectorText.contains("type=\"button\"") ||
				 selectorText.contains("type='submit'") ||
				 selectorText.contains("type=\"submit\""))
		)
			prefix = "btn_"
		else if (selectorText.contains("//input"))
			prefix = "txt_"
		else if (selectorText.contains("//select"))
			prefix = "ddl_"
		else if (selectorText.contains("//a"))
			prefix = "lnk_"
		else if (selectorText.contains("//span") || selectorText.contains("//p"))
			prefix = "lbl_"
		else if (selectorText.contains("//iframe"))
			prefix = "iframe_"
		else if (selectorText.contains("//canvas"))
			prefix = "canvas_"
	}

	// 🔹 3. Cleanup name (remove noise)
	def cleanName = originalFile
			.replaceFirst(/(?i)^(btn|txt|ddl|lnk|lbl|obj|iframe|canvas)[_-]?/, '')
			.replaceFirst(/(?i)^(button|input|select|span|div|a|label|p|li|td)[_-]?/, '')
			.replaceAll(/[(),]/, '')
			.replaceAll(/\s+/, '_')
			.replaceAll(/__+/, '_')

	// 🔹 4. Digit / OTP buttons
	boolean isDigit = cleanName.toLowerCase() ==~ /.*digit[_-]?\d.*/
	if (isDigit) {
		prefix = "btn_"
		cleanName = cleanName.replaceAll(/(?i).*digit[_-]?/, "Digit_")
	}

	// 🔹 5. Normalize case
	cleanName = cleanName
			.split("[_\\-]")
			.findAll { it }
			.collect { it.capitalize() }
			.join("_")

	// 🔹 6. Junk detection
	if (!cleanName ||
		cleanName ==~ /^\d+$/ ||
		cleanName.length() < 3 ||
		cleanName in ["Div", "Td", "Li", "P", "Span"]) {

		deleteCandidates << originalFile
		println "DELETE CANDIDATE: $originalFile"
		return
	}

	def newName = prefix + cleanName
	def newPath = path.parent.resolve(newName + ".rs")

	// 🔹 DRY RUN LOG
	if (originalFile != newName) {
		println "RENAME: $originalFile  →  $newName"
	}

	// 🔥 ENABLE ONLY AFTER FINAL REVIEW
	// Files.move(path, newPath)
}

println "=== DELETE CANDIDATES SUMMARY ==="
deleteCandidates.unique().each { println it }

println "=== PHASE 2.2 DRY RUN COMPLETE ==="
