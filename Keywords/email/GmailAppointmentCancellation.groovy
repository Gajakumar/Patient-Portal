package email

import javax.mail.*
import javax.mail.search.*
import org.jsoup.Jsoup

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class GmailAppointmentCancellation {

    @Keyword
   boolean verifyAppointmentCancellationEmail(String gmailUser,
                                           String gmailPassword,
                                           String patientName,
                                           String appointmentDateTime,
                                           int lookbackMinutes = 15,
                                           int timeoutSeconds = 180,
                                           String subjectContains = "First Insight Vision Appointment Reminder") {

    Properties props = new Properties()
    props.put("mail.store.protocol", "imaps")

    Session session = Session.getInstance(props, null)
    Store store = session.getStore("imaps")
    store.connect("imap.gmail.com", gmailUser, gmailPassword)

    List<String> folders = ["INBOX", "[Gmail]/Spam"]
    String nameNorm = normalize(patientName ?: "")
    String dateNorm = normalize(appointmentDateTime ?: "")
    String subjNeedle = normalize(subjectContains ?: "")

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

                    // Newest to oldest, stop at the first message older than the cutoff
                    for (int n = total; n >= 1; n--) {
                        Message msg = folder.getMessage(n)

                        Date received = msg.getReceivedDate()
                        if (received != null && received.time < cutoff) break

                        String from = (msg.getFrom() ? msg.getFrom().collect { it.toString() }.join(",") : "").toLowerCase()
                        if (!from.contains("maximeyes")) continue

                        String subj = normalize(msg.getSubject() ?: "")
                        if (subjNeedle && !subj.contains(subjNeedle)) continue

                        String body = normalize(getEmailText(msg))

                        if (nameNorm && !body.contains(nameNorm)) continue

                        // Accept both US and UK spelling
                        if (!(body.contains("has been canceled") || body.contains("has been cancelled"))) continue

                        if (dateNorm && !body.contains(dateNorm)) continue

                        KeywordUtil.logInfo("Cancellation email verified successfully.")
                        KeywordUtil.logInfo(body)
                        return true
                    }
                } finally {
                    folder.close(false)
                }
            }

            KeywordUtil.logInfo("Cancellation email not found yet, retrying in 5s...")
            Thread.sleep(5000)
        }
    } finally {
        store.close()
    }

    KeywordUtil.markFailed("Appointment cancellation email not found within ${timeoutSeconds}s.")
    return false
}

private String normalize(String s) {
    return s.replace('\u00A0', ' ').replaceAll("\\s+", " ").trim().toLowerCase()
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
	

@Keyword
String convertAppointmentDate(String appointmentDate) {

	DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(
		"MM/dd/yyyy hh:mm a",
		Locale.ENGLISH
	)

	DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(
		"EEE MM-dd-yyyy hh:mm a",
		Locale.ENGLISH
	)

	return LocalDateTime.parse(
		appointmentDate.trim(),
		inputFormat
	).format(outputFormat)
}

	
	
}