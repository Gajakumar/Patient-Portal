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
	

	@Keyword
	String convertAppointmentDate(String appointmentDate) {
	
		DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(
			"MM/dd/yyyy hh:mm a"
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