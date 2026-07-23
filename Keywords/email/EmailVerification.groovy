//package email
//
//import javax.mail.*
//import java.util.regex.*
//import com.kms.katalon.core.annotation.Keyword
//import com.kms.katalon.core.util.KeywordUtil
//
//public class EmailVerification {
//
//    @Keyword
//    static String verifyAccessEmailsWithPolling(
//            String host,
//            String username,
//            String password,
//            String expectedName,
//            String expectedPhone,
//            String expectedEmail,
//            String expectedSender,
//            int timeoutSeconds
//    ) {
//
//        boolean firstEmailVerified = false
//        boolean secondEmailVerified = false
//        String activationLink = null
//
//        long startTime = System.currentTimeMillis()
//        long endTime = startTime + (timeoutSeconds * 1000)
//
//        Properties props = new Properties()
//        props.put("mail.store.protocol", "imaps")
//        props.put("mail.imaps.ssl.enable", "true")
//
//        Session session = Session.getInstance(props, null)
//        Store store = session.getStore("imaps")
//        store.connect(host, username, password)
//
//        Folder inbox = store.getFolder("INBOX")
//        inbox.open(Folder.READ_ONLY)
//
//        println("📨 Polling started...")
//
//        while (System.currentTimeMillis() < endTime) {
//
//            Message[] messages = inbox.getMessages()
//            int start = Math.max(messages.length - 30, 0)
//
//            for (int i = messages.length - 1; i >= start; i--) {
//
//                Message message = messages[i]
//
//                String subject = message.getSubject()
//                String from = message.getFrom()[0].toString()
//                String content = getFullContent(message)
//
//                println("Checking Email -> Subject: ${subject}")
//                println("From: ${from}")
//
//                if (!from.toLowerCase().contains(expectedSender.toLowerCase()))
//                    continue
//
//                // FIRST EMAIL
//                if (!firstEmailVerified &&
//                        subject.toLowerCase().contains("granted access to")) {
//
//                    if (content.contains(expectedName) &&
//                            content.contains(expectedPhone) &&
//                            content.contains(expectedEmail)) {
//
//                        println("✅ First email matched")
//                        firstEmailVerified = true
//                    }
//                }
//
//                // SECOND EMAIL
//                if (!secondEmailVerified &&
//                        subject.toLowerCase().contains("been granted access")) {
//
//                    Pattern pattern = Pattern.compile(
//                            "https://ptportal-react\\.maximeyes\\.com/\\S+",
//                            Pattern.CASE_INSENSITIVE)
//
//                    Matcher matcher = pattern.matcher(content)
//
//                    if (matcher.find()) {
//                        activationLink = matcher.group()
//                        println("✅ Second email matched")
//                        println("Activation Link: ${activationLink}")
//                        secondEmailVerified = true
//                    }
//                }
//
//                if (firstEmailVerified && secondEmailVerified)
//                    break
//            }
//
//            if (firstEmailVerified && secondEmailVerified)
//                break
//				
//				
//            Thread.sleep(5000)
//            inbox.close(false)
//            inbox.open(Folder.READ_ONLY)
//        }
//
//        inbox.close(false)
//        store.close()
//
//        if (!firstEmailVerified)
//            KeywordUtil.markFailed("❌ First email not found")
//
//        if (!secondEmailVerified)
//            KeywordUtil.markFailed("❌ Second email not found")
//
//        return activationLink
//    }
//
//    // 🔥 MUST BE STATIC
//    public static String getFullContent(Message message) {
//        try {
//            if (message.isMimeType("text/*"))
//                return message.getContent().toString()
//
//            if (message.isMimeType("multipart/*")) {
//                Multipart multipart = (Multipart) message.getContent()
//                String result = ""
//                for (int i = 0; i < multipart.getCount(); i++) {
//                    BodyPart bodyPart = multipart.getBodyPart(i)
//                    if (bodyPart.isMimeType("text/*"))
//                        result += bodyPart.getContent().toString()
//                }
//                return result
//            }
//        } catch (Exception e) {
//            e.printStackTrace()
//        }
//        return ""
//    }
//}


package email

import javax.mail.*
import javax.mail.search.FlagTerm
import java.util.regex.*
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory

class EmailVerification {

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

        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000)

        Properties props = new Properties()
        props.put("mail.store.protocol", "imaps")
        props.put("mail.imaps.ssl.enable", "true")

        Session session = Session.getInstance(props, null)
        Store store = session.getStore("imaps")
        store.connect(host, username, password)

        Folder inbox = store.getFolder("INBOX")
        inbox.open(Folder.READ_WRITE)

        println("📨 Polling started...")

        while (System.currentTimeMillis() < endTime) {

            // 🔄 Refresh inbox
            inbox.close(false)
            inbox.open(Folder.READ_WRITE)

            // ✅ Get ONLY unread emails
            FlagTerm unseenFlag = new FlagTerm(new Flags(Flags.Flag.SEEN), false)
            Message[] messages = inbox.search(unseenFlag)

            if (messages.length == 0) {
                println("📭 No unread emails")
                Thread.sleep(5000)
                continue
            }

            // ✅ Sort by latest first
            messages = messages.sort { it.getSentDate()?.getTime() ?: 0 }

            // ✅ Get ONLY latest email
            Message message = messages[messages.length - 1]

            // ✅ Time filter (last 5 minutes)
            long fiveMinAgo = System.currentTimeMillis() - (5 * 60 * 1000)
            Date sentDate = message.getSentDate()

            if (sentDate == null || sentDate.getTime() < fiveMinAgo) {
                println("⏩ Skipping old email: ${sentDate}")
                Thread.sleep(5000)
                continue
            }

            String subject = message.getSubject() ?: ""
            String from = message.getFrom()[0].toString()
            String content = getFullContent(message)

            println("📩 Processing latest email:")
            println("Subject: ${subject}")
            println("Time: ${sentDate}")

            if (!from.toLowerCase().contains(expectedSender.toLowerCase())) {
                println("⏩ Skipping (sender mismatch)")
                Thread.sleep(5000)
                continue
            }

            // ✅ FIRST EMAIL VALIDATION
            if (!firstEmailVerified &&
                    subject.toLowerCase().contains("granted access to")) {

                if (content.contains(expectedName) &&
                        content.contains(expectedPhone) &&
                        content.contains(expectedEmail)) {

                    println("✅ First email verified")
                    firstEmailVerified = true
                }
            }

            // ✅ SECOND EMAIL (Activation Link)
            if (!secondEmailVerified &&
                    subject.toLowerCase().contains("been granted access")) {

                Pattern pattern = Pattern.compile(
                        "https://ptportal-react\\.maximeyes\\.com/\\S+",
                        Pattern.CASE_INSENSITIVE)

                Matcher matcher = pattern.matcher(content)

                if (matcher.find()) {
                    activationLink = matcher.group()

                    println("✅ Latest activation email verified")
                    println("🔗 Link: ${activationLink}")

                    secondEmailVerified = true
                }
            }

            // ✅ Mark as read (important)
            message.setFlag(Flags.Flag.SEEN, true)

            // ✅ Exit early if done
            if (firstEmailVerified && secondEmailVerified) {
                break
            }
			
			
			try {
				DriverFactory.getWebDriver().getTitle()
				println("Browser session is alive")
			} catch (Exception e) {
				println("Browser session lost")
				throw e
			}
			
		
            Thread.sleep(5000)
        }

        inbox.close(false)
        store.close()

        if (!firstEmailVerified)
            KeywordUtil.markFailed("❌ First email not found")

        if (!secondEmailVerified)
            KeywordUtil.markFailed("❌ Second email not found")

        return activationLink
    }

    // ✅ Extract email content safely
    static String getFullContent(Message message) {
        try {
            if (message.isMimeType("text/*"))
                return message.getContent().toString()

            if (message.isMimeType("multipart/*")) {
                Multipart multipart = (Multipart) message.getContent()
                String result = ""

                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart part = multipart.getBodyPart(i)

                    if (part.isMimeType("text/*")) {
                        result += part.getContent().toString()
                    }
                }
                return result
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        return ""
    }
}