package utils

import javax.mail.*
import javax.mail.search.*
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

class VerifyPasswordChangedEmail {

    @Keyword
    def verifyEmailReceived(String emailAddress, String emailPassword) {

        Properties props = new Properties()
        props.put("mail.store.protocol", "imaps")

        Session session = Session.getInstance(props, null)
        Store store = session.getStore("imaps")
        store.connect("imap.gmail.com", emailAddress, emailPassword)

        Folder inbox = store.getFolder("INBOX")
        inbox.open(Folder.READ_ONLY)

        // Search emails received in the last 10 minutes
        Calendar cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE, -10)
        Date tenMinutesAgo = cal.getTime()

        SearchTerm searchTerm = new AndTerm(
            new FromStringTerm("do-not-reply@maximeyes.com"),
            new AndTerm(
                new SubjectTerm("Password has been changed for Patient Portal"),
                new ReceivedDateTerm(ComparisonTerm.GE, tenMinutesAgo)
            )
        )

        Message[] messages = inbox.search(searchTerm)

        if (messages.length == 0) {
            inbox.close(false)
            store.close()
            KeywordUtil.markFailed("Password change confirmation email was not received.")
            return false
        }

        // Latest matching email
        Message message = messages[messages.length - 1]
        String body = getText(message)

        KeywordUtil.logInfo("Password Change Email Found")
        KeywordUtil.logInfo("Subject: ${message.subject}")

        List<String> expectedText = [
            "This is to confirm that your Patient Portal password was changed recently.",
            "Please do not reply to this system generated email.",
            "Thanks,",
            "First Insight"
        ]

        expectedText.each {
            assert body.contains(it) : "Missing text: ${it}"
        }

        inbox.close(false)
        store.close()

        KeywordUtil.markPassed("Password change email verified successfully.")
        return true
    }

    private String getText(Part part) {

        if (part.isMimeType("text/*")) {
            return part.getContent().toString()
        }

        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent()
            String result = ""

            for (int i = 0; i < mp.getCount(); i++) {
                result += getText(mp.getBodyPart(i))
            }
            return result
        }

        return ""
    }
}