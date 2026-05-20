package utils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

import javax.mail.*


public class ProviderPortalEmailUtils {

    static final String HOST = "imap.gmail.com"
    static final String USERNAME = "gajakumara@first-insight.com"
    static final String PASSWORD = GlobalVariable.Email_Key
    static final String EXPECTED_FROM = "do-not-reply@maximeyes.com"

    @Keyword
    def verifyEmail(String expectedSubject, String expectedBody, int maxWaitSeconds = 60) {

        int waitInterval = 5000   // faster polling (5 sec)
        int retries = (int)(maxWaitSeconds * 1000 / waitInterval)

        boolean emailFound = false

        for (int i = 0; i < retries; i++) {

            Store store = null
            Folder inbox = null

            try {
                Properties props = new Properties()
                props.put("mail.store.protocol", "imaps")

                Session session = Session.getInstance(props, null)
                store = session.getStore("imaps")
                store.connect(HOST, USERNAME, PASSWORD)

                inbox = store.getFolder("INBOX")
                inbox.open(Folder.READ_ONLY)

                Message[] messages = inbox.getMessages()

                long now = System.currentTimeMillis()
                long fiveMinAgo = now - (5 * 60 * 1000)

                // 🔁 Check latest emails first
                for (int j = messages.length - 1; j >= 0; j--) {

                    Message msg = messages[j]

                    Date receivedDate = msg.getReceivedDate()
                    if (receivedDate == null) continue

                    // ❌ skip old emails
                    if (receivedDate.getTime() < fiveMinAgo) break

                    String from = msg.getFrom()[0].toString()
                    String subject = msg.getSubject()
                    String body = getCleanBody(msg)

                    // 🔍 Debug logs (very useful)
                    println("📩 Checking email -> Subject: " + subject)
                    println("📨 From: " + from)

                    if (from.contains(EXPECTED_FROM) &&
                        subject.equalsIgnoreCase(expectedSubject) &&
                        body.contains(expectedBody.toLowerCase())) {

                        KeywordUtil.logInfo("✅ Email verified successfully")
                        KeywordUtil.logInfo("Matched Subject: " + subject)
                        KeywordUtil.logInfo("Received Time: " + receivedDate)

                        emailFound = true
                        break
                    }
                }

                if (emailFound) break

                KeywordUtil.logInfo("⏳ Waiting for email...")
                Thread.sleep(waitInterval)

            } catch (Exception e) {
                KeywordUtil.logInfo("⚠️ Retry due to: " + e.getMessage())
            } finally {
                if (inbox != null && inbox.isOpen()) inbox.close(false)
                if (store != null && store.isConnected()) store.close()
            }
        }

        if (!emailFound) {
            KeywordUtil.markFailed("❌ Expected email not received within " + maxWaitSeconds + " seconds")
        }
    }

    // 🔥 Clean + Normalize email body (handles HTML properly)
    private String getCleanBody(Message message) {

        String raw = ""

        try {
            if (message.isMimeType("text/plain") || message.isMimeType("text/html")) {
                raw = message.getContent().toString()
            }
            else if (message.isMimeType("multipart/*")) {
                Multipart mp = (Multipart) message.getContent()

                for (int i = 0; i < mp.getCount(); i++) {
                    BodyPart bp = mp.getBodyPart(i)

                    if (bp.isMimeType("text/plain") || bp.isMimeType("text/html")) {
                        raw += bp.getContent().toString()
                    }
                }
            }
        } catch (Exception e) {
            return ""
        }

        // 🧹 CLEAN HTML + normalize
        return raw
                .replaceAll("<[^>]*>", " ")   // remove HTML tags
                .replaceAll("&nbsp;", " ")
                .replaceAll("\\s+", " ")
                .trim()
                .toLowerCase()
    }
}