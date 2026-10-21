package email

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import javax.mail.*
import javax.mail.internet.MimeMultipart
import javax.mail.search.*

import com.kms.katalon.core.util.KeywordUtil

public class NoLongerAccess {
	/**
	 * Verifies the "access revoked" email for a given patient.
	 *
	 * @param host         IMAP host, e.g. imap.gmail.com
	 * @param username     mailbox user, e.g. gajakumara@first-insight.com
	 * @param password     mailbox (app) password
	 * @param patientName  dynamic patient name, e.g. "Fggjlb Nrolvymo"
	 * @param timeoutSec   max seconds to wait for the email
	 * @return true if a matching email is found and all checks pass
	 */
	@Keyword
	def verifyAccessRevokedEmail(String host, String username, String password,
			String patientName, int timeoutSec = 120) {

		String expectedFrom    = "do-not-reply@maximeyes.com"
		String expectedSubject = "Your access to ${patientName} health records has been revoked."
		String expectedBody    = "no longer have access to view or manage ${patientName} health records through the Patient Portal."
		String expectedFooter  = "First Insight Vision"

		Properties props = new Properties()
		props.put("mail.store.protocol", "imaps")
		props.put("mail.imaps.host", host)
		props.put("mail.imaps.port", "993")
		props.put("mail.imaps.ssl.enable", "true")

		Store store = null
		Folder inbox = null
		long end = System.currentTimeMillis() + (timeoutSec * 1000L)

		try {
			Session session = Session.getInstance(props)
			store = session.getStore("imaps")
			store.connect(host, username, password)

			inbox = store.getFolder("INBOX")
			inbox.open(Folder.READ_ONLY)

			// Server-side filter: sender + subject contains patient name
			SearchTerm term = new AndTerm([
				new FromStringTerm(expectedFrom),
				new SubjectTerm(patientName)
			] as SearchTerm[])

			while (System.currentTimeMillis() < end) {
				Message[] msgs = inbox.search(term)

				// newest first
				for (int i = msgs.length - 1; i >= 0; i--) {
					Message m = msgs[i]
					String subject = normalize(m.getSubject())

					if (subject.equalsIgnoreCase(normalize(expectedSubject))) {
						String body = normalize(getTextFromMessage(m))

						boolean bodyOk   = body.toLowerCase().contains(normalize(expectedBody).toLowerCase())
						boolean footerOk = body.toLowerCase().contains(expectedFooter.toLowerCase())

						KeywordUtil.logInfo("Email found. Subject: ${subject}")
						KeywordUtil.logInfo("Body: ${body}")

						if (bodyOk && footerOk) {
							KeywordUtil.markPassed("Email verified for patient: ${patientName}")
							return true
						}
						KeywordUtil.markFailed("Email found but body content did not match. Body was: ${body}")
						return false
					}
				}
				Thread.sleep(5000) // poll every 5s
				inbox.close(false)
				inbox.open(Folder.READ_ONLY) // refresh to see new mail
			}
			KeywordUtil.markFailed("No email found within ${timeoutSec}s with subject: ${expectedSubject}")
			return false

		} finally {
			try { if (inbox?.isOpen()) inbox.close(false) } catch (ignored) {}
			try { store?.close() } catch (ignored) {}
		}
	}

	// ---------- helpers ----------

	private static String normalize(String s) {
		return (s ?: "").replaceAll("\\s+", " ").trim()
	}

	private static String getTextFromMessage(Part part) {
		if (part.isMimeType("text/plain")) {
			return part.getContent().toString()
		}
		if (part.isMimeType("text/html")) {
			// strip tags for HTML-only emails
			return part.getContent().toString().replaceAll("(?s)<style.*?</style>", "")
					.replaceAll("<[^>]+>", " ").replace("&nbsp;", " ")
		}
		if (part.isMimeType("multipart/*")) {
			MimeMultipart mp = (MimeMultipart) part.getContent()
			StringBuilder sb = new StringBuilder()
			for (int i = 0; i < mp.getCount(); i++) {
				sb.append(getTextFromMessage(mp.getBodyPart(i))).append(" ")
			}
			return sb.toString()
		}
		return ""
	}

}
