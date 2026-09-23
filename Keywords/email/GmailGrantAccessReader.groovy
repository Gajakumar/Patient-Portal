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
import javax.mail.search.*
import com.kms.katalon.core.util.KeywordUtil

public class GmailGrantAccessReader {
		private static final String IMAP_HOST     = "imap.gmail.com"
	private static final String IMAP_PORT     = "993"
	private static final String EXPECTED_FROM = "do-not-reply@maximeyes.com"
 
	private static final int SCAN_LIMIT       = 30    // newest N inbox emails to inspect
	private static final int TIMEOUT_SECONDS  = 60    // how long to wait for the email to arrive
	private static final int POLL_SECONDS     = 5
 
	@Keyword
	static void verifyAccessGrantedEmail(String patientName, String portalVersion) {
 
		String expectedSubject = "You have been granted Access to ${patientName}'s Records"
		String expectedUrl     = "https://ptportal-react.maximeyes.com/ptportal${portalVersion}"
 
		Store store = null
		Folder inbox = null
 
		try {
			store = connect()
			inbox = store.getFolder("INBOX")
			inbox.open(Folder.READ_WRITE)
 
			KeywordUtil.logInfo("Searching for Access Granted email...")
 
			Message email = waitForEmail(inbox, expectedSubject)
 
			if (email == null) {
				KeywordUtil.markFailed("Access Granted email not found.\nExpected Subject: ${expectedSubject}")
				return
			}
 
			String actualFrom    = email.getFrom()[0].toString()
			String actualSubject = email.getSubject()
			String body          = normalize(getEmailBody(email))
 
			// Mark as read straight away so it never stays unread, even if a check below fails
			email.setFlag(Flags.Flag.SEEN, true)
 
			KeywordUtil.logInfo("From    : ${actualFrom}")
			KeywordUtil.logInfo("Subject : ${actualSubject}")
			KeywordUtil.logInfo("Body    : ${body}")
 
			// ---------- Sender ----------
			assert actualFrom.toLowerCase().contains(EXPECTED_FROM) :
				"Expected sender: ${EXPECTED_FROM}, Actual: ${actualFrom}"
 
			// ---------- Subject ----------
			assert normalize(actualSubject).equalsIgnoreCase(normalize(expectedSubject)) :
				"Expected subject: ${expectedSubject}\nActual subject: ${actualSubject}"
 
			// ---------- Patient name ----------
			// Note: the email body has an apostrophe before the name ('Name's Records), unlike the subject
			String expectedBodyText = "You have been granted Access to '${patientName}'s Records"
 
			assert body.contains(normalize(expectedBodyText)) :
				"Patient name text not found in email body.\nExpected: ${expectedBodyText}"
 
			// ---------- Portal URL ----------
			assert body.contains(expectedUrl) :
				"Expected portal URL not found in email body.\nExpected URL: ${expectedUrl}"
 
			// ---------- General content ----------
			assert body.contains("You have been granted Access") :
				"Access Granted text not found."
 
			assert body.contains("You can now login into your account at:") :
				"Login instruction not found."
 
			KeywordUtil.markPassed(
				"Access Granted email verified successfully for ${patientName} | Portal: ${expectedUrl}")
 
		} finally {
			// close(false) = don't expunge; flag changes (SEEN) are still saved to the server
			if (inbox != null && inbox.isOpen()) {
				inbox.close(false)
			}
			if (store != null && store.isConnected()) {
				store.close()
			}
		}
	}
 
	private static Store connect() {
		Properties props = new Properties()
		props.put("mail.store.protocol", "imaps")
		props.put("mail.imaps.host", IMAP_HOST)
		props.put("mail.imaps.port", IMAP_PORT)
		props.put("mail.imaps.ssl.enable", "true")
 
		Store store = Session.getInstance(props).getStore("imaps")
		store.connect(IMAP_HOST, GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key)
		return store
	}
 
	/**
	 * Polls the inbox until a matching email shows up or the timeout expires.
	 * Matching is done in code (not with Gmail's IMAP SEARCH, which is word-based and
	 * unreliable for subjects containing punctuation such as apostrophes).
	 */
	private static Message waitForEmail(Folder inbox, String expectedSubject) {
 
		String wantedSubject = normalize(expectedSubject)
		long deadline = System.currentTimeMillis() + (TIMEOUT_SECONDS * 1000L)
		List<String> seen = []
 
		while (true) {
			int total = inbox.getMessageCount()   // also refreshes the folder state
			int start = Math.max(1, total - SCAN_LIMIT + 1)
 
			if (total > 0) {
				Message[] recent = inbox.getMessages(start, total)
 
				FetchProfile profile = new FetchProfile()
				profile.add(FetchProfile.Item.ENVELOPE)
				inbox.fetch(recent, profile)
 
				seen.clear()
 
				// newest first
				for (int i = recent.length - 1; i >= 0; i--) {
					Message m = recent[i]
					String from = m.getFrom() ? m.getFrom()[0].toString() : ""
					String subject = m.getSubject() ?: ""
 
					if (from.toLowerCase().contains(EXPECTED_FROM)) {
						seen.add(subject)
						if (normalize(subject).equalsIgnoreCase(wantedSubject)) {
							return m
						}
					}
				}
			}
 
			if (System.currentTimeMillis() >= deadline) {
				break
			}
			Thread.sleep(POLL_SECONDS * 1000L)
		}
 
		KeywordUtil.logInfo("Subjects from ${EXPECTED_FROM} in the last ${SCAN_LIMIT} emails: ${seen}")
		return null
	}
 
	/** Returns the text/plain and text/html content of a message, including nested multiparts. */
	private static String getEmailBody(Part part) {
 
		if (part.isMimeType("text/plain") || part.isMimeType("text/html")) {
			return part.getContent().toString()
		}
 
		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent()
			StringBuilder result = new StringBuilder()
 
			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart bodyPart = multipart.getBodyPart(i)
 
				// Skip attachments
				if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
					continue
				}
				result.append(getEmailBody(bodyPart))
			}
			return result.toString()
		}
 
		return ""
	}
 
	/** Makes apostrophes and whitespace consistent so curly quotes / HTML entities don't break matching. */
	private static String normalize(String text) {
		return (text ?: "")
				.replace("\u2019", "'")
				.replace("\u2018", "'")
				.replace("&#39;", "'")
				.replace("&#x27;", "'")
				.replace("&#8217;", "'")
				.replace("&rsquo;", "'")
				.replace("&apos;", "'")
				.replaceAll(/\s+/, " ")
				.trim()
	}

}
