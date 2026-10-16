package custom

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import CustomKeywords

import com.kms.katalon.core.webui.driver.DriverFactory

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import internal.GlobalVariable as GlobalVariable

class SocCcdaTestKeywords {

	private static final String ME_MIX = 'Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/'
	private static final String PP_SOC = 'Object Repository/SOC Verification On PP/Page_Patient Portal/'
	private static final String PP_SIGNIN = 'Object Repository/PatientPortal/SignInPage_Patient Portal/'

	private static TestObject xpathTo(String name, String xpath) {
		TestObject to = new TestObject(name)
		to.addProperty('xpath', ConditionType.EQUALS, xpath)
		return to
	}

	@Keyword
	def openSummaryOfCareFromEncounter() {
		WebUI.click(findTestObject(ME_MIX + 'span_TOC Req_mif-Hamburger fg-gray font30'))
		WebUI.click(findTestObject(ME_MIX + 'span_Summary Of Care(C-CDA)'))
		WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30, FailureHandling.OPTIONAL)
		WebUI.waitForElementVisible(findTestObject(ME_MIX + 'h2_Summary of Care (C-CDA)'), 30)
	}

	@Keyword
	def setReferralNoteCheckbox(boolean checked) {
		List<String> xpaths = [
			"//input[@id='idNotesReferal']",
			"//*[@id='idNotesReferal']",
		]
		WebElement box = null
		for (String xp : xpaths) {
			List<WebElement> found = DriverFactory.getWebDriver().findElements(By.xpath(xp))
			if (!found.isEmpty()) {
				box = found.get(0)
				break
			}
		}
		if (box == null) {
			throw new IllegalStateException('Referral Note checkbox not found on SOC screen.')
		}
		boolean isChecked = box.isSelected()
		if (isChecked != checked) {
			box.click()
			WebUI.delay(1)
		}
	}

	@Keyword
	def clickSocGearIconOnMaximEyes() {
		List<String> xpaths = [
			"//h2[contains(normalize-space(),'Summary of Care')]/ancestor::div[contains(@class,'dialog') or contains(@class,'window')][1]//span[contains(@class,'mif-cog')]",
			"//h2[contains(normalize-space(),'Summary of Care')]/following::span[contains(@class,'mif-cog')][1]",
			"//span[contains(@class,'mif-cog') and (contains(@title,'Setting') or contains(@title,'setting') or contains(@title,'Gear'))]"
		]
		for (String xp : xpaths) {
			TestObject gear = xpathTo('soc_me_gear', xp)
			if (WebUI.waitForElementClickable(gear, 5, FailureHandling.OPTIONAL)) {
				WebUI.click(gear)
				WebUI.delay(2)
				return
			}
		}
		throw new IllegalStateException('SOC Gear icon not found on MaximEyes Summary of Care screen.')
	}

	@Keyword
	def verifySomethingWentWrongNotShown(int timeoutSec = 8) {
		TestObject sww = xpathTo('sww_toast',
			"//*[contains(@class,'jquery-notific8-message') or contains(@class,'Toastify__toast') or contains(@class,'toast')]" +
			"[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'something went wrong')]")
		if (WebUI.waitForElementPresent(sww, timeoutSec, FailureHandling.OPTIONAL)) {
			String msg = WebUI.getText(sww)
			throw new AssertionError("SWW toast displayed: ${msg}")
		}
		CustomKeywords.'common.ToastHelper.verifySWWToastNotDisplayed'('Something went wrong', timeoutSec)
	}

	/** Portal transmit/download: {PatientId}_{LastName}_{FirstName} */
	@Keyword
	String buildExpectedCcdaFileBaseName(String patientId, String lastName, String firstName) {
		String id = (patientId ?: '').trim()
		String ln = (lastName ?: '').trim()
		String fn = (firstName ?: '').trim()
		return "${id}_${ln}_${fn}"
	}

	/** Full attachment name, e.g. 105114_Tgzwmoam_Fxkfqf.xml or .pdf */
	@Keyword
	String buildExpectedCcdaAttachmentName(String patientId, String lastName, String firstName, String extension) {
		String ext = (extension ?: '').trim().replaceFirst(/^\.+/, '')
		return "${buildExpectedCcdaFileBaseName(patientId, lastName, firstName)}.${ext}"
	}

	@Keyword
	def verifyTransmitEmailAttachment(String emailSubject, String extension) {
		String expectedFileName = buildExpectedCcdaAttachmentName(
			GlobalVariable.GV_PatientID, GlobalVariable.PatientLastName, GlobalVariable.PatientFirstName, extension)
		KeywordUtil.logInfo("Expected transmit attachment: ${expectedFileName}")
		CustomKeywords.'common.ForwardEmailVerification.verifyEmail'(
			'imap.gmail.com',
			GlobalVariable.MyEmail_Id,
			GlobalVariable.Email_Key,
			GlobalVariable.Sender_Email,
			emailSubject,
			[],
			expectedFileName)
	}

	@Keyword
	def verifyDownloadedFileNameContains(String filePath, String expectedBaseName, String extension) {
		File f = new File(filePath)
		assert f.exists() : "Downloaded file missing: ${filePath}"
		String name = f.getName()
		String expected = "${expectedBaseName}.${extension}".toLowerCase()
		if (!name.toLowerCase().contains(expectedBaseName.toLowerCase()) || !name.toLowerCase().endsWith(".${extension}".toLowerCase())) {
			KeywordUtil.logInfo("Actual downloaded file name: ${name}")
			KeywordUtil.logInfo("Expected pattern: *${expectedBaseName}*.${extension}")
			throw new AssertionError("File name mismatch. Actual: ${name}, expected to contain ${expectedBaseName} and end with .${extension}")
		}
	}

	@Keyword
	def openLatestHealthSummaryOnPortal() {
		TestObject unread = findTestObject('Object Repository/Page_Patient Portal/div_1unread messages')
		if (WebUI.waitForElementClickable(unread, 10, FailureHandling.OPTIONAL)) {
			WebUI.click(unread)
		} else {
			WebUI.click(findTestObject('Object Repository/Page_Patient Portal/First Health Summary'))
		}
		WebUI.waitForElementNotVisible(findTestObject(PP_SIGNIN + 'Loader'), 30, FailureHandling.OPTIONAL)
	}

	@Keyword
	def verifyCreatedOnDateInSocIframe(String expectedDateMmDdYyyy) {
		TestObject createdOn = xpathTo('soc_created_on',
			"//b[normalize-space()='Created On:']/following-sibling::font[1]")
		WebUI.switchToDefaultContent()
		List<WebElement> frames = DriverFactory.getWebDriver().findElements(By.tagName('iframe'))
		for (int i = 0; i < frames.size(); i++) {
			if (WebUI.switchToFrame(xpathTo('soc_iframe', "(//iframe)[${i + 1}]"), 5, FailureHandling.OPTIONAL)) {
				if (WebUI.waitForElementPresent(createdOn, 5, FailureHandling.OPTIONAL)) {
					String actual = WebUI.getText(createdOn).trim()
					WebUI.verifyEqual(actual, expectedDateMmDdYyyy, FailureHandling.STOP_ON_FAILURE)
					WebUI.switchToDefaultContent()
					return
				}
				WebUI.switchToDefaultContent()
			}
		}
		throw new IllegalStateException("Created On date not found in SOC iframe. Expected: ${expectedDateMmDdYyyy}")
	}

	@Keyword
	def verifyReferralNoteLoincInXmlFile(String xmlFilePath) {
		String xml = new File(xmlFilePath).getText('UTF-8')
		assert xml.contains('57133-1') : 'Missing LOINC code 57133-1 in XML'
		assert xml.contains('Referral Note') : 'Missing Referral Note displayName in XML'
		assert xml.toLowerCase().contains('loinc') : 'Missing LOINC codeSystemName in XML'
	}

	@Keyword
	def transmitHealthSummaryToProvider(String fileType, String toEmail, String subject) {
		WebUI.click(findTestObject(PP_SOC + 'btn_Transmit'))
		TestObject fileTypeButton = findTestObject('Page_Patient Portal/Select Format', [('fileType') : fileType])
		WebUI.waitForElementClickable(fileTypeButton, 15)
		WebUI.click(fileTypeButton)
		WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Save'))
		WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_To_form-control mt-1 form-control-md _ef350e_32'), toEmail)
		WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/input_Subject_form-control mt-1 form-contro_eec205_12'), subject)
		WebUI.setText(findTestObject('Object Repository/Page_Patient Portal/textarea_Test Patient'), 'Automated SOC transmit')
		WebUI.click(findTestObject('Object Repository/Page_Patient Portal/button_Send'))
		WebUI.delay(5)
	}

	@Keyword
	def uploadSocToPatientPortalFromMaximEyes() {
		WebUI.click(findTestObject(ME_MIX + 'span_Patient Portal_UploadToPatientPortal'))
		CustomKeywords.'common.ToastHelper.verifyMaximeyesToastMessage'('Upload to Patient Portal completed successfully.')
	}

}
