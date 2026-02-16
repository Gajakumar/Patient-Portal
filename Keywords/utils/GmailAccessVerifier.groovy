package utils

import javax.mail.*
import javax.mail.search.SubjectTerm
import java.util.Properties
import java.util.regex.*
import com.kms.katalon.core.annotation.Keyword
import internal.GlobalVariable

class GmailAccessVerifier {

    @Keyword
    def verifyAccessEmailAndGetLink(String recordName) {

        String expectedSubject = "You have been granted Access to ${recordName} Records"

        String username = GlobalVariable.MyEmail_Id
        String password = GlobalVariable.Email_Key

        Properties props = new Properties()
        props.put("mail.store.protocol", "imaps")
        props.put("mail.imaps.host", "imap.gmail.com")
        props.put("mail.imaps.port", "993")
        props.put("mail.imaps.ssl.enable", "true")

        Session session = Session.getInstance(props, null)
        Store store = session.getStore("imaps")
        store.connect("imap.gmail.com", username, password)

        Folder inbox = store.getFolder("INBOX")
        inbox.open(Folder.READ_ONLY)

        // ===== Wait for email =====
        Message[] messages = null
        int retry = 0

        while (retry < 6) {
            messages = inbox.search(new SubjectTerm(expectedSubject))
            if (messages.length > 0) break
            Thread.sleep(10000)
            retry++
        }

        assert messages.length > 0 : "Access email not received!"

        Message message = messages[messages.length - 1]

        // ===== Verify Subject =====
        assert message.getSubject().equals(expectedSubject)

        // ===== Extract Body =====
        String emailBody = ""

        if (message.isMimeType("text/plain")) {
            emailBody = message.getContent().toString()
        }
        else if (message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent()
            BodyPart bodyPart = multipart.getBodyPart(0)
            emailBody = bodyPart.getContent().toString()
        }

        emailBody = emailBody.replaceAll("\\s+", " ")

        // ===== Verify Body Content =====
        assert emailBody.contains("You have been granted Access to ${recordName} Records.")

        // ===== Extract Activation Link =====
        Pattern pattern = Pattern.compile("(https://[^\\s]+)")
        Matcher matcher = pattern.matcher(emailBody)

        String activationLink = null

        if (matcher.find()) {
            activationLink = matcher.group(1)
        }

        assert activationLink != null : "Activation link not found in email!"

        inbox.close(false)
        store.close()

        println("✅ Email verified successfully")
        println("🔗 Activation Link: " + activationLink)

        return activationLink
    }
}
