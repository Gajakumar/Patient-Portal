
package otp
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import javax.mail.*
import javax.mail.internet.MimeMultipart
import java.util.Properties
import javax.mail.internet.*
import javax.mail.search.*


class GmailOTPHandler {

//	@Keyword
//	static String readOTP(String host, String username, String password, String fromEmail, String subjectKeyword) {
//
//		String otp = ""
//
//		try {
//			WebUI.delay(10)
//
//			Properties props = new Properties()
//			props.put("mail.store.protocol", "imaps")
//
//			Session session = Session.getDefaultInstance(props, null)
//			Store store = session.getStore("imaps")
//			store.connect(host, username, password)
//
//			Folder inbox = store.getFolder("INBOX")
//			inbox.open(Folder.READ_ONLY)
//
//			Message[] messages = inbox.getMessages()
//
//			// Latest email first
//			for (int i = messages.length - 1; i >= 0; i--) {
//
//				Message msg = messages[i]
//
//				if (msg.getFrom()[0].toString().contains(fromEmail) &&
//						msg.getSubject().contains(subjectKeyword)) {
//
//					String body = getBody(msg)
//
//					// extract 4-digit OTP
//					def matcher = body =~ /\b\d{4}\b/
//					if (matcher.find()) {
//						otp = matcher.group()
//						break
//					}
//				}
//			}
//
//			inbox.close(false)
//			store.close()
//		} catch (Exception e) {
//			println("ERROR while reading OTP: " + e)
//		}
//
//		return otp
//	}
//
//
//	private static String getBody(Message message) {
//		if (message.getContent() instanceof String) {
//			return message.getContent().toString()
//		} else if (message.getContent() instanceof MimeMultipart) {
//			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent()
//			return mimeMultipart.getBodyPart(0).getContent().toString()
//		}
//		return ""
//	}
	
//	@Keyword
//static String readOTP(String host, String username, String password, String fromEmail, String subjectKeyword) {
//
//    String otp = ""
//    int maxRetries = 12          // total wait = maxRetries * delaySeconds
//    int delaySeconds = 5
//
//    // Reference point: only accept emails received after this moment.
//    // Small buffer subtracted to absorb clock skew between local machine and mail server.
//    Date afterTime = new Date(System.currentTimeMillis() - 30000) // 30s buffer
//
//    for (int attempt = 1; attempt <= maxRetries; attempt++) {
//
//        try {
//            Properties props = new Properties()
//            props.put("mail.store.protocol", "imaps")
//
//            Session session = Session.getDefaultInstance(props, null)
//            Store store = session.getStore("imaps")
//            store.connect(host, username, password)
//
//            Folder inbox = store.getFolder("INBOX")
//            inbox.open(Folder.READ_ONLY)
//
//            Message[] messages = inbox.getMessages()
//
//            // Latest email first
//            for (int i = messages.length - 1; i >= 0; i--) {
//
//                Message msg = messages[i]
//
//                Date received = msg.getReceivedDate()
//                if (received == null) {
//                    received = msg.getSentDate() // fallback if server doesn't set received date
//                }
//                if (received == null || !received.after(afterTime)) {
//                    continue // skip anything not newer than our reference time
//                }
//
//                if (msg.getFrom()[0].toString().contains(fromEmail) &&
//                        msg.getSubject().contains(subjectKeyword)) {
//
//                    String body = getBody(msg)
//
//                    def matcher = body =~ /\b\d{4}\b/
//                    if (matcher.find()) {
//                        otp = matcher.group()
//                        break
//                    }
//                }
//            }
//
//            inbox.close(false)
//            store.close()
//
//        } catch (Exception e) {
//            println("ERROR while reading OTP (attempt ${attempt}): " + e)
//        }
//
//        if (!otp.isEmpty()) {
//            println("OTP found on attempt ${attempt}: ${otp}")
//            break
//        }
//
//        println("OTP not found yet, retrying in ${delaySeconds}s... (attempt ${attempt}/${maxRetries})")
//        WebUI.delay(delaySeconds)
//    }
//
//    if (otp.isEmpty()) {
//        println("ERROR: OTP not received within timeout window.")
//    }
//
//    return otp
//}
//
//private static String getBody(Message message) {
//    if (message.getContent() instanceof String) {
//        return message.getContent().toString()
//    } else if (message.getContent() instanceof MimeMultipart) {
//        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent()
//        return mimeMultipart.getBodyPart(0).getContent().toString()
//    }
//    return ""
//}
	
	@Keyword
	static String readOTP(String host, String username, String password, String fromEmail, String subjectKeyword,
						   int timeoutInSeconds = 60, int pollIntervalInSeconds = 5) {
	 
		String otp = ""
		// Capture the moment we START waiting for the OTP.
		// Any email received before this is ignored, so we can never pick up a stale/old OTP.
		Date searchStartTime = new Date()
	 
		Store store = null
		Folder inbox = null
	 
		try {
			Properties props = new Properties()
			props.put("mail.store.protocol", "imaps")
			Session session = Session.getDefaultInstance(props, null)
			store = session.getStore("imaps")
			store.connect(host, username, password)
	 
			long deadline = System.currentTimeMillis() + (timeoutInSeconds * 1000L)
			int attempt = 0
	 
			while (System.currentTimeMillis() < deadline) {
				attempt++
	 
				inbox = store.getFolder("INBOX")
				inbox.open(Folder.READ_ONLY)
	 
				// Small negative buffer to absorb clock skew between the mail server and this machine.
				Date bufferedStart = new Date(searchStartTime.time - 60000)
	 
				SearchTerm fromTerm = new FromStringTerm(fromEmail)
				SearchTerm subjectTerm = new SubjectTerm(subjectKeyword)
				SearchTerm dateTerm = new ReceivedDateTerm(ComparisonTerm.GE, bufferedStart)
				SearchTerm combined = new AndTerm([fromTerm, subjectTerm, dateTerm] as SearchTerm[])
	 
				Message[] messages = inbox.search(combined)
	 
				if (messages.length > 0) {
					// Sort newest first so we always read the latest matching OTP email.
					messages = messages.sort { -(it.getReceivedDate()?.time ?: 0L) }
	 
					for (Message msg : messages) {
						// Extra safety: skip anything actually older than our start time.
						Date received = msg.getReceivedDate()
						if (received != null && received.before(bufferedStart)) {
							continue
						}
	 
						String body = getBody(msg)
						def matcher = body =~ /\b\d{4}\b/
						if (matcher.find()) {
							otp = matcher.group()
							println("OTP found on attempt " + attempt + ": " + otp + " | Email received at: " + received)
							break
						}
					}
				}
	 
				inbox.close(false)
				inbox = null
	 
				if (!otp.isEmpty()) {
					break
				}
	 
				println("Attempt " + attempt + ": OTP not received yet, retrying in " + pollIntervalInSeconds + "s...")
				WebUI.delay(pollIntervalInSeconds)
			}
	 
			if (otp.isEmpty()) {
				println("ERROR: OTP not received within " + timeoutInSeconds + " seconds.")
			}
	 
		} catch (Exception e) {
			println("ERROR while reading OTP: " + e)
		} finally {
			try { if (inbox != null && inbox.isOpen()) inbox.close(false) } catch (Exception ignore) {}
			try { if (store != null && store.isConnected()) store.close() } catch (Exception ignore) {}
		}
	 
		return otp
	}
	 
	private static String getBody(Message message) {
		Object content = message.getContent()
		if (content instanceof String) {
			return content.toString()
		} else if (content instanceof MimeMultipart) {
			return getTextFromMimeMultipart((MimeMultipart) content)
		}
		return ""
	}
	 
	// Recursively walks multipart content so nested parts (e.g. multipart/alternative
	// inside multipart/mixed) and HTML-only emails aren't missed.
	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
		StringBuilder result = new StringBuilder()
		int count = mimeMultipart.getCount()
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i)
			Object content = bodyPart.getContent()
			if (content instanceof String) {
				result.append(content.toString())
			} else if (content instanceof MimeMultipart) {
				result.append(getTextFromMimeMultipart((MimeMultipart) content))
			}
		}
		return result.toString()
	}
}