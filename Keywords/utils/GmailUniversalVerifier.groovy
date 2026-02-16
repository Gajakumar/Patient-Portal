package utils

import javax.mail.*
import javax.mail.internet.*
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.annotation.Keyword
import internal.GlobalVariable

class GmailUniversalVerifier {

    @Keyword
    def validateGrantAndActivationEmails(String fullName,
                                         String phone,
                                         String email,
                                         int maxWaitSeconds = 60) {

        String username = GlobalVariable.MyEmail_Id
        String password = GlobalVariable.Email_Key

        Properties props = new Properties()
        props.put("mail.store.protocol", "imaps")
        props.put("mail.imaps.host", "imap.gmail.com")
        props.put("mail.imaps.port", "993")
        props.put("mail.imaps.ssl.enable", "true")
        props.put("mail.imaps.ssl.trust", "imap.gmail.com")

        Session session = Session.getInstance(props, null)
        Store store = session.getStore("imaps")
        store.connect(username, password)

        Folder inbox = store.getFolder("INBOX")
        inbox.open(Folder.READ_ONLY)

        Message grantEmail = null
        Message activationEmail = null

        int waited = 0

        while (waited < maxWaitSeconds) {

            inbox.getMessageCount()
            int total = inbox.getMessageCount()
            int start = Math.max(1, total - 40)

            Message[] messages = inbox.getMessages(start, total)

            for (int i = messages.length - 1; i >= 0; i--) {

                String subject = messages[i].getSubject()
                if (subject == null) continue

                if (subject.contains("You have granted access for your health records")
                        && grantEmail == null) {
                    grantEmail = messages[i]
                }

                if (subject.contains("You have been granted Access")
                        && activationEmail == null) {
                    activationEmail = messages[i]
                }

                if (grantEmail != null && activationEmail != null) break
            }

            if (grantEmail != null && activationEmail != null) break

            Thread.sleep(5000)
            waited += 5
        }

        if (grantEmail == null) {
            KeywordUtil.markFailedAndStop("❌ Grant email not found!")
        }

        if (activationEmail == null) {
            KeywordUtil.markFailedAndStop("❌ Activation email not found!")
        }

        String grantBody = getTextFromMessage(grantEmail)
        String activationBody = getTextFromMessage(activationEmail)

        // ================================
        // VERIFY GRANT EMAIL
        // ================================

        if (!grantEmail.getSubject()
                .contains("You have granted access for your health records")) {
            KeywordUtil.markFailedAndStop("❌ Grant email subject mismatch!")
        }

        if (!grantBody.contains("You have granted access for your health records")) {
            KeywordUtil.markFailedAndStop("❌ Grant email body text missing!")
        }

        if (!grantBody.contains("Name: " + fullName)) {
            KeywordUtil.markFailedAndStop("❌ Name mismatch in grant email!")
        }

        if (!grantBody.contains("Phone: " + phone)) {
            KeywordUtil.markFailedAndStop("❌ Phone mismatch in grant email!")
        }

        if (!grantBody.contains("Email: " + email)) {
            KeywordUtil.markFailedAndStop("❌ Email mismatch in grant email!")
        }

        println("✅ Grant email verified successfully")

        // ================================
        // VERIFY ACTIVATION EMAIL
        // ================================

        if (!activationEmail.getSubject()
                .contains("You have been granted Access")) {
            KeywordUtil.markFailedAndStop("❌ Activation email subject mismatch!")
        }

        if (!activationBody.contains("Click here to complete the activation process")) {
            KeywordUtil.markFailedAndStop("❌ Activation email body text missing!")
        }

        println("✅ Activation email verified successfully")

        // ================================
        // EXTRACT ACTIVATION LINK
        // ================================

        def matcher = (activationBody =~ /(https?:\/\/[^\s"]+)/)

        String activationLink = null

        if (matcher.find()) {
            activationLink = matcher.group(1)
            println("✅ Activation Link Extracted: " + activationLink)
        } else {
            KeywordUtil.markFailedAndStop("❌ Activation link not found!")
        }

        inbox.close(false)
        store.close()

        return activationLink
    }

    private String getTextFromMessage(Message message) {

        if (message.isMimeType("text/plain")) {
            return message.getContent().toString()
        }

        if (message.isMimeType("multipart/*")) {

            Multipart multipart = (Multipart) message.getContent()

            for (int i = 0; i < multipart.getCount(); i++) {

                BodyPart bodyPart = multipart.getBodyPart(i)

                if (bodyPart.isMimeType("text/plain")) {
                    return bodyPart.getContent().toString()
                }

                if (bodyPart.isMimeType("text/html")) {
                    return bodyPart.getContent().toString()
                }
            }
        }

        return ""
    }
}
