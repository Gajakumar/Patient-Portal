package email

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import javax.mail.*
import javax.mail.internet.MimeMultipart
import org.jsoup.Jsoup
import java.util.Properties

class ProviderPEmail {

    @Keyword
    def verifyProviderPEmail(
            String expectedSubject,
            String bodyPattern,
            List<String> expectedAttachments = [],
            int timeoutSeconds = 30
    ) {

        // 🔐 CONFIG
        String host = "imap.gmail.com"
        String username = "gajakumara@first-insight.com"
        String password = "qnkj qbyt goya wbhd"

        Store store
        Folder inbox

        try {
            // 🔹 IMAP Setup
            Properties props = new Properties()
            props.put("mail.store.protocol", "imaps")
            props.put("mail.imaps.host", host)
            props.put("mail.imaps.port", "993")
            props.put("mail.imaps.ssl.enable", "true")

            Session session = Session.getInstance(props, null)
            store = session.getStore("imaps")
            store.connect(host, username, password)

            inbox = store.getFolder("INBOX")
            inbox.open(Folder.READ_ONLY)

            Message targetMessage = null
            int elapsed = 0

            // 🔁 WAIT + FILTER BY SUBJECT
            while (elapsed < timeoutSeconds) {

                Message[] messages = inbox.getMessages()

                for (int i = messages.length - 1; i >= 0; i--) {
                    if (messages[i].getSubject()?.contains(expectedSubject)) {
                        targetMessage = messages[i]
                        break
                    }
                }

                if (targetMessage != null) break

                println("⏳ Waiting for email...")
                Thread.sleep(2000)
                elapsed += 2
            }

            if (targetMessage == null) {
                KeywordUtil.markFailedAndStop("❌ No email found with subject: " + expectedSubject)
            }

            println("📩 Subject: " + targetMessage.getSubject())

            // ============================
            // 🔥 FIXED BODY EXTRACTION
            // ============================
            def content = targetMessage.getContent()
            String actualBody = content.toString()

            // ✅ ALWAYS clean HTML (CRITICAL FIX)
            if (actualBody.toLowerCase().contains("<html") || actualBody.toLowerCase().contains("<body")) {
                actualBody = Jsoup.parse(actualBody).text()
            }

            // ✅ Normalize text
            actualBody = actualBody
                    .replaceAll("\\s+", " ")
                    .replaceAll("[^\\x20-\\x7E]", "")
                    .trim()

            println("📝 Clean Body: " + actualBody)

            // ============================
            // ✅ BODY VALIDATION (REGEX)
            // ============================
            if (!actualBody.matches(".*${bodyPattern}.*")) {
                KeywordUtil.markFailedAndStop("❌ Body mismatch. Pattern not found\nActual: " + actualBody)
            }

            // ============================
            // 📎 ATTACHMENT EXTRACTION
            // ============================
            List<String> actualAttachments = []

            if (content instanceof MimeMultipart) {

                MimeMultipart multipart = (MimeMultipart) content

                for (int i = 0; i < multipart.getCount(); i++) {

                    BodyPart part = multipart.getBodyPart(i)
                    String disposition = part.getDisposition()

                    if (disposition != null &&
                            (disposition.equalsIgnoreCase(Part.ATTACHMENT) ||
                             disposition.equalsIgnoreCase(Part.INLINE))) {

                        actualAttachments.add(part.getFileName())
                    }
                }
            }

            println("📎 Attachments: " + actualAttachments)

            // ============================
            // ✅ SMART ATTACHMENT VALIDATION
            // ============================
            if (expectedAttachments && expectedAttachments.size() > 0) {

                if (!actualAttachments || actualAttachments.size() == 0) {
                    KeywordUtil.markFailedAndStop("❌ Expected attachments but none found")
                }

                expectedAttachments.each { file ->
                    if (!actualAttachments.contains(file)) {
                        KeywordUtil.markFailedAndStop("❌ Missing attachment: " + file)
                    }
                }

            } else {

                if (actualAttachments && actualAttachments.size() > 0) {
                    KeywordUtil.markFailedAndStop("❌ Unexpected attachments found: " + actualAttachments)
                }
            }

            KeywordUtil.markPassed("✅ Email verification successful")

        } catch (Exception e) {
            KeywordUtil.markFailedAndStop("❌ Exception: " + e.getMessage())
        } finally {
            inbox?.close(false)
            store?.close()
        }
    }
}