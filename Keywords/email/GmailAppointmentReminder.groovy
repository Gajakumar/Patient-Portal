package email

import javax.mail.*
import javax.mail.search.*
import java.util.Properties
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil


class GmailAppointmentReminder {

  @Keyword
    String getCancelRescheduleLink(String gmailUser,
                                   String gmailPassword,
                                   String patientName,
                                   int lookbackMinutes = 10,
                                   int timeoutSeconds = 90) {

        Properties props = new Properties()
        props.put("mail.store.protocol", "imaps")

        Session session = Session.getInstance(props, null)
        Store store = session.getStore()
        store.connect("imap.gmail.com", gmailUser, gmailPassword)

        List<String> folders = ["INBOX", "[Gmail]/Spam"]
        String nameNorm = normalize(patientName ?: "")
        long end = System.currentTimeMillis() + timeoutSeconds * 1000L
        long cutoff = System.currentTimeMillis() - lookbackMinutes * 60L * 1000L

        try {
            while (System.currentTimeMillis() < end) {

                for (String folderName : folders) {
                    Folder folder = store.getFolder(folderName)
                    if (!folder.exists()) continue
                    folder.open(Folder.READ_ONLY)

                    try {
                        int total = folder.getMessageCount()

                        // Newest to oldest; stop at the first message older than the cutoff
                        for (int n = total; n >= 1; n--) {
                            Message msg = folder.getMessage(n)

                            Date received = msg.getReceivedDate()
                            if (received != null && received.time < cutoff) {
                                break
                            }

                            String from = (msg.getFrom() ? msg.getFrom().collect { it.toString() }.join(",") : "").toLowerCase()
                            if (!from.contains("maximeyes")) continue

                            String subj = normalize(msg.getSubject() ?: "")
                            if (!subj.contains("appointment reminder")) continue

                            String body = normalize(getText(msg))
                            if (nameNorm && !body.contains(nameNorm)) continue

                            String html = getHtml(msg)
                            if (html == null) continue

                            Document doc = Jsoup.parse(html)
                            def link = doc.select("a").find {
                                String t = normalize(it.text())
                                t.contains("cancel") && t.contains("reschedule")
                            }

                            if (link) {
                                String url = link.attr("href")
                                KeywordUtil.logInfo("Cancel/Reschedule URL : ${url}")
                                return url
                            }
                        }
                    } finally {
                        folder.close(false)
                    }
                }

                KeywordUtil.logInfo("Not found yet, retrying in 5s...")
                Thread.sleep(5000)
            }
        } finally {
            store.close()
        }

        KeywordUtil.markFailed("Cancel/Reschedule link not found in the last ${lookbackMinutes} min (waited ${timeoutSeconds}s).")
        return null
    }

    private String normalize(String s) {
        return s.replace('\u00A0', ' ').replaceAll("\\s+", " ").trim().toLowerCase()
    }

    private String getText(Part part) {
        if (part.isMimeType("text/plain"))
            return part.getContent().toString()
        if (part.isMimeType("text/html"))
            return Jsoup.parse(part.getContent().toString()).text()
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent()
            StringBuilder sb = new StringBuilder()
            for (int i = 0; i < mp.getCount(); i++) sb.append(getText(mp.getBodyPart(i)))
            return sb.toString()
        }
        return ""
    }

    private String getHtml(Part part) {
        if (part.isMimeType("text/html"))
            return part.getContent().toString()
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent()
            for (int i = 0; i < mp.getCount(); i++) {
                String html = getHtml(mp.getBodyPart(i))
                if (html != null) return html
            }
        }
        return null
    }
	
	@Keyword
	boolean verifyAppointmentCancellationEmail(String gmailUser,
											  String gmailPassword,
											  String patientName,
											  String appointmentDateTime) {

		Properties props = new Properties()
		props.put("mail.store.protocol", "imaps")

		Session session = Session.getInstance(props, null)
		Store store = session.getStore("imaps")
		store.connect("imap.gmail.com", gmailUser, gmailPassword)

		Folder inbox = store.getFolder("INBOX")
		inbox.open(Folder.READ_ONLY)

		SearchTerm sender = new FromStringTerm("do-not-reply@maximeyes.com")
		SearchTerm subject = new SubjectTerm("First Insight Vision Appointment")
		SearchTerm search = new AndTerm(sender, subject)

		Message[] messages = inbox.search(search)

		Arrays.sort(messages, new Comparator<Message>() {
			@Override
			int compare(Message a, Message b) {
			return b.getReceivedDate().compareTo(a.getReceivedDate())
			}
		})

		for (Message msg : messages) {

			String body = getEmailText(msg)

			if (!body.contains("Dear ${patientName},")) {
				continue
			}

			if (!body.toLowerCase().contains("has been canceled")) {
				continue
			}

			if (appointmentDateTime && !body.contains(appointmentDateTime)) {
				continue
			}

			KeywordUtil.logInfo("Cancellation email verified successfully.")
			KeywordUtil.logInfo(body)

			inbox.close(false)
			store.close()
			return true
		}

		inbox.close(false)
		store.close()

		KeywordUtil.markFailed("Appointment cancellation email not found.")
		return false
	}

	private String getEmailText(Part part) {

		if (part.isMimeType("text/plain"))
			return part.getContent().toString()

		if (part.isMimeType("text/html"))
			return Jsoup.parse(part.getContent().toString()).text()

		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent()
			String text = ""
			for (int i = 0; i < mp.count; i++) {
				text += getEmailText(mp.getBodyPart(i))
			}
			return text
		}

		return ""
	}
	
	
}