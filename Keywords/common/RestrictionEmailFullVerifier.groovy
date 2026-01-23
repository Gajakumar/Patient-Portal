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
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable
import javax.mail.*
import javax.mail.search.SubjectTerm


class RestrictionEmailFullVerifier {
	
		/**
		 * Fetches latest email with exact subject, waits until it arrives, and verifies content
		 */
		@Keyword
		static void fetchAndVerifyDeniedEmailBySubject(
				String imapHost,
				String emailId,
				String emailPassword,
				String expectedSubject,           // exact subject to wait for
				String expectedPatientName,
				String expectedUSCDIElement,
				int maxWaitSeconds = 180,
				int pollIntervalSeconds = 10
		) {
	
			long startTime = System.currentTimeMillis()
			Map emailData = null
	
			while ((System.currentTimeMillis() - startTime) < maxWaitSeconds * 1000) {
	
				emailData = fetchLatestEmailBySubject(
						imapHost, emailId, emailPassword, expectedSubject
				)
	
				if (emailData != null) {
					KeywordUtil.logInfo("✅ Email found with subject: ${expectedSubject}")
					break
				}
	
				KeywordUtil.logInfo("⏳ Email not found yet. Retrying in ${pollIntervalSeconds} seconds...")
				Thread.sleep(pollIntervalSeconds * 1000)
			}
	
			assert emailData != null :
					"❌ Email with subject '${expectedSubject}' not received within ${maxWaitSeconds} seconds"
	
			verifyEmailContent(
					emailData.subject,
					emailData.body,
					expectedPatientName,
					expectedUSCDIElement
			)
		}
	
		// ================= FETCH EMAIL BY EXACT SUBJECT =================
		private static Map fetchLatestEmailBySubject(
				String imapHost,
				String emailId,
				String emailPassword,
				String expectedSubject
		) {
	
			Properties props = new Properties()
			props.put("mail.store.protocol", "imaps")
	
			Session session = Session.getInstance(props, null)
			Store store = session.getStore()
			store.connect(imapHost, emailId, emailPassword)
	
			Folder inbox = store.getFolder("INBOX")
			inbox.open(Folder.READ_ONLY)
	
			Message[] messages = inbox.search(new SubjectTerm(expectedSubject))
	
			if (messages.length == 0) {
				inbox.close(false)
				store.close()
				return null
			}
	
			Message msg = messages[messages.length - 1]
	
			// ✅ Read everything BEFORE closing folder
			String subject = msg.subject
			String body = extractBody(msg)
	
			inbox.close(false)
			store.close()
	
			return [
					subject: subject,
					body   : body
			]
		}
	
		// ================= VERIFY EMAIL CONTENT =================
		private static void verifyEmailContent(
				String subject,
				String body,
				String expectedPatientName,
				String expectedUSCDIElement
		) {
	
			assert subject != null : "❌ Email subject is NULL"
			assert body != null : "❌ Email body is NULL"
	
			// Normalize body
			body = body.replaceAll("\\r", "")
					   .replaceAll("\\n+", "\n")
					   .trim()
	
			// ===== Subject check =====
			assert subject ==~ /${subject}/ : "❌ Subject mismatch"
	
			// ===== Static content =====
			assert body.contains("recent request to restrict access") :
					"❌ Missing restriction explanation"
			assert body.contains("decided to deny the requested restriction") :
					"❌ Denial message missing"
			assert body.contains("Thank you for using Maximeyes") :
					"❌ Signature missing"
	
			// ===== Dynamic content =====
			// Split patient name into parts (first + last)
def nameParts = expectedPatientName.split("\\s+")
for (part in nameParts) {
    assert body.contains(part) : "❌ Patient name part '${part}' not found in email"
}
			assert body.contains("USCDI Data Element(s):") :
					"❌ USCDI label missing"
			assert body.contains(expectedUSCDIElement) :
					"❌ USCDI value mismatch"
	
			// ===== Date check (any valid format) =====
			assert body =~ /Date of Request:\s*[A-Za-z]+\s+\d{1,2},\s+\d{4},\s+\d{1,2}:\d{2}\s+(AM|PM)\s+\([A-Z]{2,4}\)/ :
					"❌ Date of Request missing or invalid"
		}
	
		// ================= EXTRACT BODY (PLAIN + HTML) =================
		private static String extractBody(Message message) {
			try {
				if (message.isMimeType("text/plain")) {
					return message.getContent()
				} else if (message.isMimeType("text/html")) {
					String html = message.getContent().toString()
					return html.replaceAll("\\<.*?\\>", "") // crude HTML strip
				} else if (message.isMimeType("multipart/*")) {
					Multipart mp = (Multipart) message.getContent()
					for (int i = 0; i < mp.getCount(); i++) {
						BodyPart bp = mp.getBodyPart(i)
						if (bp.isMimeType("text/plain")) {
							return bp.getContent()
						} else if (bp.isMimeType("text/html")) {
							String html = bp.getContent().toString()
							return html.replaceAll("\\<.*?\\>", "")
						}
					}
				}
			} catch(Exception e) {
				e.printStackTrace()
			}
			return null
		}
	}