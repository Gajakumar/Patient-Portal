package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import javax.mail.*
import javax.mail.internet.*
import javax.mail.search.*
import com.kms.katalon.core.util.KeywordUtil

public class ForwardEmailVerification {
	
	@Keyword
def verifyEmail(String host,
                String username,
                String password,
                String fromEmail,
                String subject,
                List<String> expectedBodyParts = [],
                String expectedAttachmentName = null) {

    Properties props = new Properties()
    props.put("mail.store.protocol", "imaps")

    Session session = Session.getDefaultInstance(props, null)
    Store store = session.getStore("imaps")
    store.connect(host, username, password)   // ✅ FIXED

    Folder inbox = store.getFolder("INBOX")
    inbox.open(Folder.READ_ONLY)

    // 🔍 Search email
    SearchTerm sender = new FromStringTerm(fromEmail)
    SearchTerm emailSubject = new SubjectTerm(subject)   // ✅ FIXED
    SearchTerm searchTerm = new AndTerm(sender, emailSubject) // ✅ FIXED

    Message[] messages = inbox.search(searchTerm)

    assert messages.length > 0 : "❌ Email NOT received"

    Message message = messages[messages.length - 1]
    KeywordUtil.logInfo("✅ Email found: " + message.getSubject())

    // =============================
    // 📩 GET BODY CONTENT
    // =============================
    String bodyText = ""

    if (message.isMimeType("text/plain") || message.isMimeType("text/html")) {
        bodyText = message.getContent().toString()
    } else if (message.isMimeType("multipart/*")) {

        Multipart multipart = (Multipart) message.getContent()

        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart part = multipart.getBodyPart(i)

            if (part.isMimeType("text/plain")) {
                bodyText = part.getContent().toString()
                break
            } else if (part.isMimeType("text/html")) {
                bodyText = part.getContent().toString()
            }
        }
    }

    bodyText = bodyText.replaceAll("\\s+", " ")

    // =============================
    // 🧠 VERIFY BODY KEYWORDS
    // =============================
    expectedBodyParts.each { keyword ->   // ✅ FIXED
        if (!bodyText.contains(keyword)) {
            KeywordUtil.markFailed("❌ Missing text in email body: " + keyword)
        }
    }

    KeywordUtil.logInfo("✅ Email body verified")

    // =============================
    // 📎 VERIFY ATTACHMENT
    // =============================
    if (expectedAttachmentName != null) {   // ✅ FIXED

        boolean attachmentFound = false

        if (message.isMimeType("multipart/*")) {

            Multipart multipart = (Multipart) message.getContent()

            for (int i = 0; i < multipart.getCount(); i++) {

                BodyPart part = multipart.getBodyPart(i)

                if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())
                        || part.getFileName() != null) {

                    String fileName = MimeUtility.decodeText(part.getFileName())

                    KeywordUtil.logInfo("📎 Found: " + fileName)

                    if (fileName.contains(expectedAttachmentName)) { // ✅ FIXED
                        attachmentFound = true
                        break
                    }
                }
            }
        }

        assert attachmentFound : "❌ Attachment NOT found: " + expectedAttachmentName

        KeywordUtil.logInfo("✅ Attachment verified: " + expectedAttachmentName)
    }

    inbox.close(false)
    store.close()

    KeywordUtil.markPassed("🎉 Email verification completed successfully")
}

}
