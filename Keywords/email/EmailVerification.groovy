package email

import javax.mail.*
import java.util.regex.*
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

public class EmailVerification {

    @Keyword
    static String verifyAccessEmailsWithPolling(
            String host,
            String username,
            String password,
            String expectedName,
            String expectedPhone,
            String expectedEmail,
            String expectedSender,
            int timeoutSeconds
    ) {

        boolean firstEmailVerified = false
        boolean secondEmailVerified = false
        String activationLink = null

        long startTime = System.currentTimeMillis()
        long endTime = startTime + (timeoutSeconds * 1000)

        Properties props = new Properties()
        props.put("mail.store.protocol", "imaps")
        props.put("mail.imaps.ssl.enable", "true")

        Session session = Session.getInstance(props, null)
        Store store = session.getStore("imaps")
        store.connect(host, username, password)

        Folder inbox = store.getFolder("INBOX")
        inbox.open(Folder.READ_ONLY)

        println("📨 Polling started...")

        while (System.currentTimeMillis() < endTime) {

            Message[] messages = inbox.getMessages()
            int start = Math.max(messages.length - 30, 0)

            for (int i = messages.length - 1; i >= start; i--) {

                Message message = messages[i]

                String subject = message.getSubject()
                String from = message.getFrom()[0].toString()
                String content = getFullContent(message)

                println("Checking Email -> Subject: ${subject}")
                println("From: ${from}")

                if (!from.toLowerCase().contains(expectedSender.toLowerCase()))
                    continue

                // FIRST EMAIL
                if (!firstEmailVerified &&
                        subject.toLowerCase().contains("granted access to")) {

                    if (content.contains(expectedName) &&
                            content.contains(expectedPhone) &&
                            content.contains(expectedEmail)) {

                        println("✅ First email matched")
                        firstEmailVerified = true
                    }
                }

                // SECOND EMAIL
                if (!secondEmailVerified &&
                        subject.toLowerCase().contains("been granted access")) {

                    Pattern pattern = Pattern.compile(
                            "https://ptportal-react\\.maximeyes\\.com/\\S+",
                            Pattern.CASE_INSENSITIVE)

                    Matcher matcher = pattern.matcher(content)

                    if (matcher.find()) {
                        activationLink = matcher.group()
                        println("✅ Second email matched")
                        println("Activation Link: ${activationLink}")
                        secondEmailVerified = true
                    }
                }

                if (firstEmailVerified && secondEmailVerified)
                    break
            }

            if (firstEmailVerified && secondEmailVerified)
                break

            Thread.sleep(5000)
            inbox.close(false)
            inbox.open(Folder.READ_ONLY)
        }

        inbox.close(false)
        store.close()

        if (!firstEmailVerified)
            KeywordUtil.markFailed("❌ First email not found")

        if (!secondEmailVerified)
            KeywordUtil.markFailed("❌ Second email not found")

        return activationLink
    }

    // 🔥 MUST BE STATIC
    public static String getFullContent(Message message) {
        try {
            if (message.isMimeType("text/*"))
                return message.getContent().toString()

            if (message.isMimeType("multipart/*")) {
                Multipart multipart = (Multipart) message.getContent()
                String result = ""
                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i)
                    if (bodyPart.isMimeType("text/*"))
                        result += bodyPart.getContent().toString()
                }
                return result
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        return ""
    }
}
