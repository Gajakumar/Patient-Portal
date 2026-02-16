package utils

import javax.mail.*
import javax.mail.search.SubjectTerm
import java.util.Properties
import com.kms.katalon.core.annotation.Keyword
import internal.GlobalVariable

class GmailVerifier {

    @Keyword
    def verifyGrantAccessEmail(String name, String phone, String email) {

        String expectedSubject = "You have granted Access to ${name}"

        // ===== Gmail Credentials from Global Variables =====
        String username = GlobalVariable.MyEmail_Id
        String password = GlobalVariable.Email_Key
        // ===== IMAP Configuration =====
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

        // ===== Wait for Email (max 50 seconds) =====
        Message[] messages = null
        int retry = 0

        while (retry < 5) {
            messages = inbox.search(new SubjectTerm(expectedSubject))
            if (messages.length > 0) break
            Thread.sleep(10000)
            retry++
        }

        assert messages.length > 0 : "Email with subject not received!"

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
        assert emailBody.contains("You have granted access for your health records to ${name}.")
        assert emailBody.contains("Name: ${name}")
        assert emailBody.contains("Phone: ${phone}")
        assert emailBody.contains("Email: ${email}")

        inbox.close(false)
        store.close()

        println("✅ Email Subject & Body Verified Successfully")
    }
}
