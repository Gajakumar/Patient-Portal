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


//package email
//
//import javax.mail.*
//import javax.mail.search.FlagTerm
//import java.util.regex.*
//import com.kms.katalon.core.annotation.Keyword
//import com.kms.katalon.core.util.KeywordUtil
//import com.kms.katalon.core.webui.driver.DriverFactory
//
//class EmailVerification {
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
//        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000)
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
//        inbox.open(Folder.READ_WRITE)
//
//        println("📨 Polling started...")
//
//        while (System.currentTimeMillis() < endTime) {
//
//            // 🔄 Refresh inbox
//            inbox.close(false)
//            inbox.open(Folder.READ_WRITE)
//
//            // ✅ Get ONLY unread emails
//            FlagTerm unseenFlag = new FlagTerm(new Flags(Flags.Flag.SEEN), false)
//            Message[] messages = inbox.search(unseenFlag)
//
//            if (messages.length == 0) {
//                println("📭 No unread emails")
//                Thread.sleep(5000)
//                continue
//            }
//
//            // ✅ Sort by latest first
//            messages = messages.sort { it.getSentDate()?.getTime() ?: 0 }
//
//            // ✅ Get ONLY latest email
//            Message message = messages[messages.length - 1]
//
//            // ✅ Time filter (last 5 minutes)
//            long fiveMinAgo = System.currentTimeMillis() - (5 * 60 * 1000)
//            Date sentDate = message.getSentDate()
//
//            if (sentDate == null || sentDate.getTime() < fiveMinAgo) {
//                println("⏩ Skipping old email: ${sentDate}")
//                Thread.sleep(5000)
//                continue
//            }
//
//            String subject = message.getSubject() ?: ""
//            String from = message.getFrom()[0].toString()
//            String content = getFullContent(message)
//
//            println("📩 Processing latest email:")
//            println("Subject: ${subject}")
//            println("Time: ${sentDate}")
//
//            if (!from.toLowerCase().contains(expectedSender.toLowerCase())) {
//                println("⏩ Skipping (sender mismatch)")
//                Thread.sleep(5000)
//                continue
//            }
//
//            // ✅ FIRST EMAIL VALIDATION
//            if (!firstEmailVerified &&
//                    subject.toLowerCase().contains("granted access to")) {
//
//                if (content.contains(expectedName) &&
//                        content.contains(expectedPhone) &&
//                        content.contains(expectedEmail)) {
//
//                    println("✅ First email verified")
//                    firstEmailVerified = true
//                }
//            }
//
//            // ✅ SECOND EMAIL (Activation Link)
//            if (!secondEmailVerified &&
//                    subject.toLowerCase().contains("been granted access")) {
//
//                Pattern pattern = Pattern.compile(
//                        "https://ptportal-react\\.maximeyes\\.com/\\S+",
//                        Pattern.CASE_INSENSITIVE)
//
//                Matcher matcher = pattern.matcher(content)
//
//                if (matcher.find()) {
//                    activationLink = matcher.group()
//
//                    println("✅ Latest activation email verified")
//                    println("🔗 Link: ${activationLink}")
//
//                    secondEmailVerified = true
//                }
//            }
//
//            // ✅ Mark as read (important)
//            message.setFlag(Flags.Flag.SEEN, true)
//
//            // ✅ Exit early if done
//            if (firstEmailVerified && secondEmailVerified) {
//                break
//            }
//			
//			
////			try {
////				DriverFactory.getWebDriver().getTitle()
////				println("Browser session is alive")
////			} catch (Exception e) {
////				println("Browser session lost")
////				throw e
////			}
//			
//		
//            Thread.sleep(5000)
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
//    // ✅ Extract email content safely
//    static String getFullContent(Message message) {
//        try {
//            if (message.isMimeType("text/*"))
//                return message.getContent().toString()
//
//            if (message.isMimeType("multipart/*")) {
//                Multipart multipart = (Multipart) message.getContent()
//                String result = ""
//
//                for (int i = 0; i < multipart.getCount(); i++) {
//                    BodyPart part = multipart.getBodyPart(i)
//
//                    if (part.isMimeType("text/*")) {
//                        result += part.getContent().toString()
//                    }
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
import javax.mail.search.*
import java.util.regex.*
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

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

        long startTime = System.currentTimeMillis()
        long endTime = startTime + (timeoutSeconds * 1000L)

        Properties props = new Properties()

        props.put("mail.store.protocol", "imaps")
        props.put("mail.imaps.ssl.enable", "true")

        Session session = Session.getInstance(props, null)

        Store store = null
        Folder inbox = null

        try {

            // =========================================================
            // CONNECT TO MAILBOX
            // =========================================================

            store = session.getStore("imaps")
            store.connect(host, username, password)

            inbox = store.getFolder("INBOX")
            inbox.open(Folder.READ_WRITE)

            println("================================================")
            println("📨 EMAIL POLLING STARTED")
            println("⏱ Timeout          : ${timeoutSeconds} seconds")
            println("⏱ Email filter     : Last 5 minutes + UNREAD")
            println("👤 Expected Name    : ${expectedName}")
            println("📱 Expected Phone   : ${expectedPhone}")
            println("📧 Expected Email   : ${expectedEmail}")
            println("📤 Expected Sender  : ${expectedSender}")
            println("================================================")


            // =========================================================
            // POLLING
            // =========================================================

            while (System.currentTimeMillis() < endTime) {

                // -----------------------------------------------------
                // Refresh inbox
                // -----------------------------------------------------

                if (inbox.isOpen()) {
                    inbox.close(false)
                }

                inbox.open(Folder.READ_WRITE)


                // =====================================================
                // LAST 5 MINUTES
                // =====================================================

                long fiveMinutesAgo =
                        System.currentTimeMillis() - (5 * 60 * 1000L)

                Date fiveMinutesAgoDate =
                        new Date(fiveMinutesAgo)


                // =====================================================
                // UNREAD EMAILS ONLY
                // =====================================================

                SearchTerm unreadTerm =
                        new FlagTerm(
                                new Flags(Flags.Flag.SEEN),
                                false
                        )


                // =====================================================
                // EMAILS RECEIVED IN LAST 5 MINUTES
                // =====================================================

                SearchTerm recentTerm =
                        new ReceivedDateTerm(
                                ComparisonTerm.GE,
                                fiveMinutesAgoDate
                        )


                // =====================================================
                // EXPECTED SENDER
                // =====================================================

                SearchTerm senderTerm =
                        new FromStringTerm(
                                expectedSender
                        )


                // =====================================================
                // FIRST EMAIL SUBJECT
                //
                // Example:
                // You have granted Access to Kuoeku Euddkiae
                // =====================================================

                SearchTerm firstSubjectTerm =
                        new SubjectTerm(
                                "granted access to"
                        )


                // =====================================================
                // SECOND EMAIL SUBJECT
                //
                // Example:
                // You have been granted Access to
                // Joel Jerry's Records
                // =====================================================

                SearchTerm secondSubjectTerm =
                        new SubjectTerm(
                                "been granted access"
                        )


                // Either first OR second subject
                SearchTerm subjectTerm =
                        new OrTerm(
                                firstSubjectTerm,
                                secondSubjectTerm
                        )


                // =====================================================
                // COMBINE ALL SEARCH CONDITIONS
                //
                // UNREAD
                // AND LAST 5 MINUTES
                // AND EXPECTED SENDER
                // AND EXPECTED SUBJECT
                // =====================================================

                SearchTerm searchTerm =
                        new AndTerm(
                                unreadTerm,
                                new AndTerm(
                                        recentTerm,
                                        new AndTerm(
                                                senderTerm,
                                                subjectTerm
                                        )
                                )
                        )


                // =====================================================
                // SERVER-SIDE SEARCH
                // =====================================================

                Message[] messages =
                        inbox.search(searchTerm)


                println("")
                println(
                        "📬 Unread matching emails from last 5 minutes: " +
                        messages.length
                )


                // =====================================================
                // SORT NEWEST FIRST
                // =====================================================

                messages =
                        messages.sort {

                            (it.getSentDate()?.getTime() ?: 0) * -1
                        }


                // =====================================================
                // PROCESS MATCHING EMAILS
                // =====================================================

                for (Message message : messages) {

                    String subject =
                            message.getSubject() ?: ""

                    String subjectLower =
                            subject.toLowerCase().trim()


                    String from = ""

                    try {

                        Address[] addresses =
                                message.getFrom()

                        if (addresses != null &&
                                addresses.length > 0) {

                            from =
                                    addresses[0].toString()
                        }

                    } catch (Exception e) {

                        println(
                                "⚠️ Unable to read sender: " +
                                e.message
                        )
                    }


                    // -------------------------------------------------
                    // Get email body
                    // -------------------------------------------------

                    String content =
                            getFullContent(message)


                    println("")
                    println("------------------------------------------------")
                    println("📩 PROCESSING EMAIL")
                    println("Subject : ${subject}")
                    println("From    : ${from}")
                    println("Date    : ${message.getSentDate()}")
                    println("Content : ${content?.length()} characters")
                    println("------------------------------------------------")


                    // =================================================
                    // FIRST EMAIL VALIDATION
                    // =================================================

                    if (!firstEmailVerified &&
                            subjectLower.contains(
                                    "granted access to"
                            ) &&
                            !subjectLower.contains(
                                    "been granted access"
                            )) {

                        println(
                                "🔎 FIRST EMAIL SUBJECT MATCHED"
                        )


                        boolean nameMatched =
                                containsIgnoreCase(
                                        content,
                                        expectedName
                                )


                        boolean phoneMatched =
                                containsNormalized(
                                        content,
                                        expectedPhone
                                )


                        boolean emailMatched =
                                containsIgnoreCase(
                                        content,
                                        expectedEmail
                                )


                        println(
                                "Name matched : ${nameMatched}"
                        )

                        println(
                                "Phone matched: ${phoneMatched}"
                        )

                        println(
                                "Email matched: ${emailMatched}"
                        )


                        if (nameMatched &&
                                phoneMatched &&
                                emailMatched) {

                            println(
                                    "✅ FIRST EMAIL VERIFIED"
                            )

                            firstEmailVerified = true


                            // IMPORTANT:
                            // Mark READ only after successful validation

                            try {

                                message.setFlag(
                                        Flags.Flag.SEEN,
                                        true
                                )

                            } catch (Exception e) {

                                println(
                                        "⚠️ Could not mark first email as read: " +
                                        e.message
                                )
                            }

                        } else {

                            println(
                                    "❌ FIRST EMAIL CONTENT VALIDATION FAILED"
                            )

                            // DO NOT mark as read.
                            //
                            // It will remain unread and can be
                            // checked again during the next poll.
                        }
                    }


                    // =================================================
                    // SECOND EMAIL / ACTIVATION LINK
                    // =================================================

                    if (!secondEmailVerified &&
                            subjectLower.contains(
                                    "been granted access"
                            )) {

                        println(
                                "🔎 SECOND EMAIL SUBJECT MATCHED"
                        )


                        activationLink =
                                extractActivationLink(
                                        content
                                )


                        if (activationLink != null) {

                            println(
                                    "✅ SECOND EMAIL VERIFIED"
                            )

                            println(
                                    "🔗 Activation Link:"
                            )

                            println(
                                    activationLink
                            )


                            secondEmailVerified = true


                            // Mark READ only after successful validation

                            try {

                                message.setFlag(
                                        Flags.Flag.SEEN,
                                        true
                                )

                            } catch (Exception e) {

                                println(
                                        "⚠️ Could not mark second email as read: " +
                                        e.message
                                )
                            }

                        } else {

                            println(
                                    "❌ SECOND EMAIL FOUND BUT " +
                                    "ACTIVATION LINK NOT FOUND"
                            )

                            // Do NOT mark as read.
                        }
                    }


                    // =================================================
                    // BOTH EMAILS FOUND
                    // =================================================

                    if (firstEmailVerified &&
                            secondEmailVerified) {

                        println("")
                        println("================================================")
                        println("✅ BOTH EMAILS VERIFIED")
                        println("================================================")

                        println(
                                "🔗 Activation Link: " +
                                activationLink
                        )

                        println("================================================")

                        break
                    }
                }


                // =====================================================
                // STOP POLLING
                // =====================================================

                if (firstEmailVerified &&
                        secondEmailVerified) {

                    break
                }


                // =====================================================
                // WAIT 5 SECONDS
                // =====================================================

                println(
                        "⏳ Emails not completely verified."
                )

                println(
                        "⏳ Waiting 5 seconds before next poll..."
                )

                Thread.sleep(5000)
            }


            // =========================================================
            // FINAL RESULT
            // =========================================================

            if (!firstEmailVerified) {

                KeywordUtil.markFailed(
                        "❌ First email not found/verified " +
                        "within ${timeoutSeconds} seconds"
                )
            }


            if (!secondEmailVerified) {

                KeywordUtil.markFailed(
                        "❌ Second email / activation link not found " +
                        "within ${timeoutSeconds} seconds"
                )
            }


            if (firstEmailVerified &&
                    secondEmailVerified) {

                println("")
                println("================================================")
                println("🎉 EMAIL VERIFICATION SUCCESSFUL")
                println("================================================")

                return activationLink
            }


            return activationLink


        } finally {

            // =========================================================
            // CLOSE INBOX
            // =========================================================

            try {

                if (inbox != null &&
                        inbox.isOpen()) {

                    inbox.close(false)
                }

            } catch (Exception e) {

                println(
                        "⚠️ Error closing inbox: " +
                        e.message
                )
            }


            // =========================================================
            // CLOSE STORE
            // =========================================================

            try {

                if (store != null &&
                        store.isConnected()) {

                    store.close()
                }

            } catch (Exception e) {

                println(
                        "⚠️ Error closing mail store: " +
                        e.message
                )
            }
        }
    }


    // ================================================================
    // EXTRACT ACTIVATION LINK
    // ================================================================

    static String extractActivationLink(
            String content
    ) {

        if (!content) {
            return null
        }


        /*
         * Handles:
         *
         * https://ptportal-react.maximeyes.com/ptportal2712?id=XXX&type=loginauthorization
         *
         * and Markdown:
         *
         * [https://ptportal-react.maximeyes.com/...?id=XXX&type=...](...)
         */

        Pattern pattern =
                Pattern.compile(
                        "https://ptportal-react\\.maximeyes\\.com/[^\\s\\]\\)]+",
                        Pattern.CASE_INSENSITIVE
                )


        Matcher matcher =
                pattern.matcher(content)


        if (matcher.find()) {

            String link =
                    matcher.group().trim()


            // Convert escaped & to normal &
            link =
                    link.replace(
                            "\\&",
                            "&"
                    )


            return link
        }


        return null
    }


    // ================================================================
    // CASE-INSENSITIVE TEXT MATCH
    // ================================================================

    static boolean containsIgnoreCase(
            String content,
            String expected
    ) {

        if (!content || !expected) {
            return false
        }


        return content
                .toLowerCase()
                .contains(
                        expected.toLowerCase()
                )
    }


    // ================================================================
    // NORMALIZED PHONE MATCH
    // ================================================================

    static boolean containsNormalized(
            String content,
            String expected
    ) {

        if (!content || !expected) {
            return false
        }


        String normalizedExpected =
                expected.replaceAll(
                        "[^0-9]",
                        ""
                )


        String normalizedContent =
                content.replaceAll(
                        "[^0-9]",
                        ""
                )


        return normalizedContent.contains(
                normalizedExpected
        )
    }


    // ================================================================
    // GET FULL EMAIL CONTENT
    // ================================================================

    static String getFullContent(
            Message message
    ) {

        StringBuilder result =
                new StringBuilder()

        try {

            Object content =
                    message.getContent()

            extractPart(
                    content,
                    result
            )

        } catch (Exception e) {

            println(
                    "⚠️ Error extracting email content: " +
                    e.message
            )

            e.printStackTrace()
        }


        return result.toString()
    }


    // ================================================================
    // RECURSIVE MIME CONTENT EXTRACTION
    // ================================================================

    static void extractPart(
            Object content,
            StringBuilder result
    ) {

        try {

            if (content == null) {
                return
            }


            // ---------------------------------------------------------
            // Text / HTML
            // ---------------------------------------------------------

            if (content instanceof String) {

                result.append("\n")
                result.append(
                        content.toString()
                )

                return
            }


            // ---------------------------------------------------------
            // Multipart
            // ---------------------------------------------------------

            if (content instanceof Multipart) {

                Multipart multipart =
                        (Multipart) content


                for (
                        int i = 0;
                        i < multipart.getCount();
                        i++
                ) {

                    BodyPart bodyPart =
                            multipart.getBodyPart(i)


                    Object partContent =
                            bodyPart.getContent()


                    extractPart(
                            partContent,
                            result
                    )
                }

                return
            }

        } catch (Exception e) {

            println(
                    "⚠️ Error extracting MIME part: " +
                    e.message
            )
        }
    }
}