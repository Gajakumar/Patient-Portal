package utils

import javax.mail.*
import javax.mail.search.*
import javax.mail.internet.MimeMultipart
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

class EmailUtils {

    @Keyword
    def verifyAccessGrantEmail(String host, String username, String password,
                               String expectedName, String expectedPhone, String expectedEmail,
                               int retryCount = 5, int delaySeconds = 10) {

        Properties props = new Properties()
        props.put("mail.store.protocol", "imaps")

        Session session = Session.getInstance(props, null)
        Store store = session.getStore()

        boolean emailFound = false

        try {
            store.connect(host, username, password)

            Folder inbox = store.getFolder("INBOX")
            inbox.open(Folder.READ_ONLY)

            for (int attempt = 0; attempt < retryCount; attempt++) {

                KeywordUtil.logInfo("Checking email... Attempt: ${attempt + 1}")

                // Search by subject (Name)
                SearchTerm subjectTerm = new SubjectTerm(expectedName)
                Message[] messages = inbox.search(subjectTerm)

                for (Message msg : messages) {

                    String subject = msg.getSubject()

                    if (subject != null && subject.contains(expectedName)) {

                        String body = getEmailBody(msg)

                        if (body.contains(expectedName) &&
                            body.contains(expectedPhone) &&
                            body.contains(expectedEmail)) {

                            KeywordUtil.markPassed("✅ Email verified successfully")
                            emailFound = true
                            break
                        }
                    }
                }

                if (emailFound) break

                Thread.sleep(delaySeconds * 1000)
                inbox.close(false)
                inbox.open(Folder.READ_ONLY)
            }

            inbox.close(false)

        } catch (Exception e) {
            KeywordUtil.markFailed("❌ Error while verifying email: " + e.getMessage())
        } finally {
            store.close()
        }

        if (!emailFound) {
            KeywordUtil.markFailed("❌ Expected email not found or content mismatch")
        }
    }

    // ===== Helper to extract email body =====
    private String getEmailBody(Message message) {

        try {
            if (message.getContent() instanceof String) {
                return message.getContent()
            } else if (message.getContent() instanceof MimeMultipart) {
                return getTextFromMimeMultipart((MimeMultipart) message.getContent())
            }
        } catch (Exception e) {
            return ""
        }

        return ""
    }

    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) {

        String result = ""

        int count = mimeMultipart.getCount()

        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i)

            if (bodyPart.isMimeType("text/plain") || bodyPart.isMimeType("text/html")) {
                result += bodyPart.getContent()
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result += getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent())
            }
        }

        return result
    }
}