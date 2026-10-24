package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import javax.mail.*
import javax.mail.internet.*
import javax.mail.search.*

public class ForwardEmailVerification {

	@Keyword
	def verifyEmail(String host,
					String username,
					String password,
					String fromEmail,
					String subject,
					List<String> expectedBodyParts = [],
					String expectedAttachmentName = null,
					int timeoutSeconds = 120,
					int pollIntervalSeconds = 5) {

		Properties props = new Properties()
		props.put("mail.store.protocol", "imaps")

		// Ignore emails older than this keyword call (1 min tolerance for clock differences)
		Date startTime = new Date(System.currentTimeMillis() - 60 * 1000)

		Store store = null
		Folder inbox = null
		Message message = null

		long deadline = System.currentTimeMillis() + (timeoutSeconds * 1000L)
		int attempt = 0

		try {
			// =============================
			// ⏳ WAIT UNTIL EMAIL ARRIVES
			// =============================
			while (message == null && System.currentTimeMillis() < deadline) {
				attempt++

				// Reconnect on every poll so the server returns fresh mail
				closeQuietly(inbox, store)
				Session session = Session.getInstance(props, null)
				store = session.getStore("imaps")
				store.connect(host, username, password)
				inbox = store.getFolder("INBOX")
				inbox.open(Folder.READ_ONLY)

				SearchTerm searchTerm = new AndTerm(
						new FromStringTerm(fromEmail),
						new SubjectTerm(subject))

				Message[] messages = inbox.search(searchTerm)

				// Pick the newest matching email received after startTime
				for (int i = messages.length - 1; i >= 0; i--) {
					Date received = messages[i].getReceivedDate()
					if (received == null || !received.before(startTime)) {
						message = messages[i]
						break
					}
				}

				if (message == null) {
					KeywordUtil.logInfo("⏳ Attempt ${attempt}: email not received yet, retrying in ${pollIntervalSeconds}s...")
					Thread.sleep(pollIntervalSeconds * 1000L)
				}
			}

			if (message == null) {
				KeywordUtil.markFailed("❌ Email NOT received within ${timeoutSeconds} seconds")
				return
			}

			KeywordUtil.logInfo("✅ Email found after ${attempt} attempt(s): " + message.getSubject())

			// =============================
			// 📩 GET BODY CONTENT
			// =============================
			String bodyText = getBodyText(message)
			bodyText = bodyText.replaceAll("\\s+", " ")

			expectedBodyParts.each { keyword ->
				if (!bodyText.contains(keyword)) {
					KeywordUtil.markFailed("❌ Missing text in email body: " + keyword)
				}
			}
			KeywordUtil.logInfo("✅ Email body verified")

			// =============================
			// 📎 VERIFY ATTACHMENT
			// =============================
			if (expectedAttachmentName != null) {
				boolean attachmentFound = hasAttachment(message, expectedAttachmentName)

				if (!attachmentFound) {
					KeywordUtil.markFailed("❌ Attachment NOT found: " + expectedAttachmentName)
					return
				}
				KeywordUtil.logInfo("✅ Attachment verified: " + expectedAttachmentName)
			}

			KeywordUtil.markPassed("🎉 Email verification completed successfully")

		} finally {
			closeQuietly(inbox, store)
		}
	}

	// ---------- helpers ----------

	private String getBodyText(Part part) {
		if (part.isMimeType("text/plain")) {
			return part.getContent().toString()
		}
		if (part.isMimeType("text/html")) {
			return part.getContent().toString()
		}
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent()
			String result = ""
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart bp = mp.getBodyPart(i)
				if (Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())) continue
				result += " " + getBodyText(bp)   // handles nested multiparts
			}
			return result
		}
		return ""
	}

	private boolean hasAttachment(Part part, String expectedName) {
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent()
			for (int i = 0; i < mp.getCount(); i++) {
				if (hasAttachment(mp.getBodyPart(i), expectedName)) return true
			}
		} else if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()) || part.getFileName() != null) {
			String fileName = MimeUtility.decodeText(part.getFileName())
			KeywordUtil.logInfo("📎 Found: " + fileName)
			return fileName.contains(expectedName)
		}
		return false
	}

	private void closeQuietly(Folder folder, Store store) {
		try { if (folder != null && folder.isOpen()) folder.close(false) } catch (Exception ignored) {}
		try { if (store != null && store.isConnected()) store.close() } catch (Exception ignored) {}
	}
}