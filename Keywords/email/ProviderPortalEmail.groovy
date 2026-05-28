package email

import javax.mail.*
import javax.mail.internet.MimeMultipart
import javax.mail.search.FlagTerm
import javax.mail.Flags
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import org.jsoup.Jsoup
import java.util.Properties

class ProviderPortalEmail {

    private static final String HOST = "imap.gmail.com"
    private static final String USERNAME = "gajakumara@first-insight.com"
    private static final String PASSWORD = "qnkj qbyt goya wbhd"   // ✅ no spaces

    @Keyword
    def verifyProviderPortalEmail(
            String expectedSubject,
            String expectedBody,
            List<String> expectedAttachments) {

        Properties props = new Properties()
        props.put("mail.store.protocol", "imaps")

        Session session = Session.getInstance(props, null)
        Store store = session.getStore()

        int maxAttempts = 6
        boolean emailFound = false

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            println("🔁 Attempt: " + attempt)

            store.connect(HOST, USERNAME, PASSWORD)
            Folder inbox = store.getFolder("INBOX")
            inbox.open(Folder.READ_ONLY)

            // ✅ Only unread emails
            Message[] messages = inbox.search(
                new FlagTerm(new Flags(Flags.Flag.SEEN), false)
            )

            println("📬 Unread count: " + messages.length)

            for (int i = messages.length - 1; i >= 0; i--) {

                Message message = messages[i]

                try {
                    String subject = message.getSubject()
                    String from = message.getFrom()[0].toString()

                    println("📩 Subject: [" + subject + "]")
                    println("📩 From: " + from)

                    // ==========================
                    // SUBJECT FILTER (ROBUST)
                    // ==========================
                    if (!(subject?.trim()?.toLowerCase()
                            ?.contains(expectedSubject.toLowerCase())) ||
                        !from.contains("do-not-reply@maximeyes.com")) {
                        continue
                    }

                    // ==========================
                    // BODY (ROBUST)
                    // ==========================
                    String body = extractBody(message)

                    String cleanBody = body.toLowerCase()
                                            .replaceAll("\\s+", " ")
                                            .trim()

                    println("📝 Body: [" + cleanBody + "]")

                    if (!cleanBody.contains(expectedBody.toLowerCase())) {
                        continue
                    }

                    // ==========================
                    // ATTACHMENTS (FIXED)
                    // ==========================
                    List<String> actualFiles = extractAttachments(message)

                    println("📎 Final Attachments: " + actualFiles)

                    if (actualFiles.size() == 0) {
                        KeywordUtil.markFailedAndStop("❌ No attachments found")
                    }

                    // Validate files
                    expectedAttachments.each { file ->
                        if (!actualFiles.any {
                            it.toLowerCase().contains(file.toLowerCase())
                        }) {
                            KeywordUtil.markFailedAndStop("❌ Missing: " + file)
                        }
                    }

                    // Validate count
                    if (actualFiles.size() != expectedAttachments.size()) {
                        KeywordUtil.markFailedAndStop(
                            "❌ Count mismatch. Expected: ${expectedAttachments.size()} Actual: ${actualFiles.size()}"
                        )
                    }

                    KeywordUtil.markPassed("✅ Email verified: " + expectedSubject)
                    emailFound = true
                    break

                } catch (Exception e) {
                    println("❌ Exception:")
                    e.printStackTrace()
                }
            }

            inbox.close(false)
            store.close()

            if (emailFound) break

            println("⏳ Waiting for email...")
            Thread.sleep(10000)
        }

        if (!emailFound) {
            KeywordUtil.markFailed("❌ Email not found: " + expectedSubject)
        }
    }

    // ==========================
    // BODY EXTRACTION
    // ==========================
    private String extractBody(Message message) {

        try {
            if (message.getContent() instanceof String) {
                return message.getContent()
            }

            if (message.getContent() instanceof MimeMultipart) {

                MimeMultipart multipart = (MimeMultipart) message.getContent()

                for (int i = 0; i < multipart.getCount(); i++) {

                    BodyPart part = multipart.getBodyPart(i)

                    if (part.isMimeType("text/plain") ||
                        part.isMimeType("text/html")) {

                        return part.getContent().toString()
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace()
        }

        return ""
    }

    // ==========================
    // ATTACHMENT EXTRACTION (FINAL FIX)
    // ==========================
    private List<String> extractAttachments(Message message) {

        Set<String> files = new HashSet<>()   // ✅ removes duplicates

        try {
            if (message.getContent() instanceof MimeMultipart) {

                MimeMultipart multipart = (MimeMultipart) message.getContent()

                for (int i = 0; i < multipart.getCount(); i++) {

                    BodyPart part = multipart.getBodyPart(i)

                    String disposition = part.getDisposition()
                    String fileName = part.getFileName()

                    // ✅ ONLY real attachments (ignore inline images)
                    if (Part.ATTACHMENT.equalsIgnoreCase(disposition) &&
                        fileName != null) {

                        files.add(fileName)
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace()
        }

        return new ArrayList<>(files)
    }

}

	