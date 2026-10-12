
package email

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import javax.mail.*
import javax.mail.search.*
import java.util.Properties
import java.util.Date
import java.util.Locale
import java.util.regex.Pattern

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class GmailAppointmentReschedule {

	private static final String IMAP_HOST = 'imap.gmail.com'
	private static final String SENDER = 'do-not-reply@maximeyes.com'
	private static final String SUBJECT = 'First Insight Vision Appointment Reminder'

	@Keyword
	Map<String, String> getRescheduleAppointmentLinks(
			String gmailUser,
			String gmailPassword,
			String patientName,
			String appointmentDateTime) {

		Store store = null
		Folder inbox = null

		Map<String, String> result = [
			cancelRescheduleUrl: '',
			intakeFormUrl      : ''
		]

		long startTime = System.currentTimeMillis()

		try {
			// -------- Connect to Gmail --------
			Properties props = new Properties()
			props.put('mail.store.protocol', 'imaps')
			props.put('mail.imaps.host', IMAP_HOST)
			props.put('mail.imaps.port', '993')
			props.put('mail.imaps.ssl.enable', 'true')
			props.put('mail.imaps.connectiontimeout', '15000')
			props.put('mail.imaps.timeout', '15000')
			props.put('mail.imaps.writetimeout', '15000')

			Session session = Session.getInstance(props)

			store = session.getStore('imaps')
			store.connect(IMAP_HOST, gmailUser, gmailPassword)

			inbox = store.getFolder('INBOX')
			inbox.open(Folder.READ_ONLY)

			KeywordUtil.logInfo(
				"Gmail IMAP connected in ${System.currentTimeMillis() - startTime} ms"
			)

			// -------- Search recent reminder emails --------
			Calendar calendar = Calendar.getInstance()
			calendar.add(Calendar.DAY_OF_YEAR, -7)
			Date sevenDaysAgo = calendar.getTime()

			SearchTerm senderTerm =
				new FromStringTerm(SENDER)

			SearchTerm subjectTerm =
				new SubjectTerm(SUBJECT)

			SearchTerm receivedTerm =
				new ReceivedDateTerm(ComparisonTerm.GE, sevenDaysAgo)

			SearchTerm searchTerm = new AndTerm([
				senderTerm,
				subjectTerm,
				receivedTerm
			] as SearchTerm[])

			long searchStart = System.currentTimeMillis()
			Message[] messages = inbox.search(searchTerm)

			KeywordUtil.logInfo(
				"IMAP search completed in ${System.currentTimeMillis() - searchStart} ms"
			)
			KeywordUtil.logInfo("Matching reminder emails: ${messages.length}")

			if (messages.length == 0) {
				KeywordUtil.markFailedAndStop(
					"No reminder emails found from ${SENDER} with subject '${SUBJECT}' in the last 7 days."
				)
			}

			// Fetch email metadata together to reduce individual metadata requests.
			FetchProfile profile = new FetchProfile()
			profile.add(FetchProfile.Item.ENVELOPE)
			profile.add(FetchProfile.Item.FLAGS)
			profile.add(FetchProfile.Item.CONTENT_INFO)

			inbox.fetch(messages, profile)
		

			// Newest email first.
			List<Message> sortedMessages = messages.toList()
			sortedMessages.sort { Message a, Message b ->
				Date dateA = a.getReceivedDate()
				Date dateB = b.getReceivedDate()

				if (dateA == null && dateB == null) return 0
				if (dateA == null) return 1
				if (dateB == null) return -1

				return dateB.compareTo(dateA)
			}

			String expectedPatient = normalize(patientName)
			String expectedDateTime = normalize(appointmentDateTime)

			int inspected = 0

			// -------- Inspect newest messages first --------
			for (Message message : sortedMessages.take(1)) {
				inspected++

				Map<String, Object> emailContent = [
					plainText: new StringBuilder(),
					html     : new StringBuilder()
				]

				// Read MIME content once for this message.
				collectEmailContent(message, emailContent)

				String plainText = emailContent.plainText.toString()
				String html = emailContent.html.toString()

//				String htmlText = html ? Jsoup.parse(html).text() : ''
//				String combinedText = normalize(
//					plainText + ' ' + htmlText
//				)
//
//				boolean patientMatches =
//					combinedText.contains(expectedPatient)
//
//				boolean dateMatches =
//					combinedText.contains(expectedDateTime)
//
//				boolean rescheduleMatches =
//					combinedText.contains('appointment has been rescheduled') ||
//					combinedText.contains('appointment was rescheduled')
				
			String htmlText = html ? Jsoup.parse(html).text() : ''
String combinedText = normalize(plainText + ' ' + htmlText)

KeywordUtil.logInfo("Expected patient: ${expectedPatient}")
KeywordUtil.logInfo("Expected date/time: ${expectedDateTime}")
KeywordUtil.logInfo("Email visible text: ${htmlText}")

boolean patientMatches = combinedText.contains(expectedPatient)
boolean dateMatches = combinedText.contains(expectedDateTime)

boolean rescheduleMatches =
    combinedText.contains('appointment has been rescheduled') ||
    combinedText.contains('appointment was rescheduled') ||
    combinedText.contains('has been rescheduled')

KeywordUtil.logInfo(
    "Match results: patient=${patientMatches}, " +
    "date=${dateMatches}, reschedule=${rescheduleMatches}"
)
	//=======================================================================================
				KeywordUtil.logInfo(
					"Match results: patient=${patientMatches}, " +
					"date=${dateMatches}, reschedule=${rescheduleMatches}"
				)

				KeywordUtil.logInfo(
					"Email #${inspected}: received=${message.getReceivedDate()}, " +
					"patientMatch=${patientMatches}, " +
					"dateMatch=${dateMatches}, " +
					"rescheduleMatch=${rescheduleMatches}, " +
					"HTML length=${html.length()}, " +
					"plain-text length=${plainText.length()}"
				)

				if (!(patientMatches && dateMatches && rescheduleMatches)) {
					continue
				}

				KeywordUtil.logInfo(
					"Matching reschedule email found: ${message.getSubject()}"
				)

				// -------- Extract links from HTML anchors --------
				List<Map<String, String>> anchorLinks = []

				if (html) {
					Document document = Jsoup.parse(html)

					for (Element anchor : document.select('a[href]')) {
						String label = anchor.text()?.trim() ?: ''
						String href = anchor.attr('abs:href')?.trim() ?: ''

						if (!href) {
							href = anchor.attr('href')?.trim() ?: ''
						}

						if (isHttpUrl(href)) {
							anchorLinks.add([
								label: label,
								url  : href
							])

							KeywordUtil.logInfo(
								"Email link found: label='${label}', URL='${href}'"
							)
						}
					}
				}

				// First pass: classify links by visible anchor text.
				for (Map<String, String> link : anchorLinks) {
					String label = normalize(link.label)
					String url = link.url

					if (!result.cancelRescheduleUrl &&
						(label.contains('cancel') ||
						 label.contains('reschedule'))) {

						result.cancelRescheduleUrl = extractMaximEyesUrl(url)
					}

					if (!result.intakeFormUrl &&
						(label.contains('intake') ||
						 label.contains('online form') ||
						 label.contains('complete your form'))) {

						result.intakeFormUrl = extractMaximEyesUrl(url)
					}
				}

				// Second pass: identify MaximEyes tracking links even if
				// their anchor labels differ from the expected wording.
				List<String> maximEyesLinks = anchorLinks
					.collect { it.url }
					.findAll {
						String u = it.toLowerCase(Locale.ENGLISH)
						u.contains('awstrack.me') ||
						u.contains('t.maximeyes.com')
					}
					.unique()

				if (!result.cancelRescheduleUrl && maximEyesLinks.size() >= 1) {
					result.cancelRescheduleUrl =
						extractMaximEyesUrl(maximEyesLinks[0])
				}

				if (!result.intakeFormUrl && maximEyesLinks.size() >= 2) {
					String secondUrl = extractMaximEyesUrl(maximEyesLinks[1])

					if (secondUrl != result.cancelRescheduleUrl) {
						result.intakeFormUrl = secondUrl
					}
				}

				// Final fallback: some messages contain only plain text.
				if (!result.cancelRescheduleUrl || !result.intakeFormUrl) {
					List<String> textUrls = extractUrls(plainText)

					if (textUrls.isEmpty()) {
						textUrls = extractUrls(html)
					}

					for (String url : textUrls) {
						String extractedUrl = extractMaximEyesUrl(url)

						if (!result.cancelRescheduleUrl) {
							result.cancelRescheduleUrl = extractedUrl
						} else if (!result.intakeFormUrl &&
								   extractedUrl != result.cancelRescheduleUrl) {
							result.intakeFormUrl = extractedUrl
						}

						if (result.cancelRescheduleUrl &&
							result.intakeFormUrl) {
							break
						}
					}
				}

				KeywordUtil.logInfo(
					"Cancel/reschedule URL extracted: " +
					"${!result.cancelRescheduleUrl.isEmpty()}"
				)

				KeywordUtil.logInfo(
					"Intake form URL extracted: " +
					"${!result.intakeFormUrl.isEmpty()}"
				)

				if (result.cancelRescheduleUrl && result.intakeFormUrl) {
					KeywordUtil.logInfo(
						"Both appointment links extracted successfully. " +
						"Messages inspected: ${inspected}"
					)

					return result
				}

				// The email matched, but the links were incomplete.
				// Continue checking older matching emails in case another
				// copy contains the complete HTML content.
				KeywordUtil.logInfo(
					"Matching email did not contain both links. " +
					"Continuing to the next email."
				)

				result = [
					cancelRescheduleUrl: '',
					intakeFormUrl      : ''
				]
			}

			KeywordUtil.markFailedAndStop(
				"No matching reschedule email with both links was found. " +
				"Messages inspected: ${inspected}. " +
				"Check the HTML/link diagnostic logs."
			)

			return result

		} catch (Exception e) {
			KeywordUtil.logInfo(
				"Reschedule email keyword failed: ${e.getClass().getName()}: ${e.message}"
			)

			throw e

		} finally {
			try {
				if (inbox != null && inbox.isOpen()) {
					inbox.close(false)
				}
			} catch (Exception ignored) {
				KeywordUtil.logInfo('Could not close the INBOX cleanly.')
			}

			try {
				if (store != null && store.isConnected()) {
					store.close()
				}
			} catch (Exception ignored) {
				KeywordUtil.logInfo('Could not close the IMAP store cleanly.')
			}
		}
	}

	/**
	 * Recursively reads MIME parts. Each part's content is fetched once
	 * and stored as plain text or raw HTML.
	 */
	private void collectEmailContent(Part part, Map<String, Object> output)
        throws Exception {

    Object content = part.getContent()

    if (content instanceof Multipart) {
        Multipart multipart = (Multipart) content

        for (int i = 0; i < multipart.getCount(); i++) {
            collectEmailContent(multipart.getBodyPart(i), output)
        }

    } else if (content instanceof Part) {
        collectEmailContent((Part) content, output)

    } else if (content instanceof String) {
        String value = (String) content

        if (part.isMimeType('text/html')) {
            StringBuilder html =
                (StringBuilder) output.get('html')

            html.append(value).append('\n')

        } else if (part.isMimeType('text/plain')) {
            StringBuilder plainText =
                (StringBuilder) output.get('plainText')

            plainText.append(value).append('\n')
        }
    }
}

	/**
	 * Extracts HTTP/HTTPS URLs from text or HTML.
	 */
	private List<String> extractUrls(String value) {
		List<String> urls = []

		if (!value) {
			return urls
		}

		Pattern pattern = Pattern.compile(
			"(?i)https?://[^\\s<>\"']+"
		)

		def matcher = pattern.matcher(value)

		while (matcher.find()) {
			String url = matcher.group().trim()
			url = url.replaceAll(/[),.;\]}]+$/, '')

			if (isHttpUrl(url)) {
				urls.add(url)
			}
		}

		return urls.unique()
	}

	/**
	 * Keeps the MaximEyes destination when it is embedded in an
	 * AWS tracking URL; otherwise returns the original URL.
	 */
	private String extractMaximEyesUrl(String url) {
		if (!url) {
			return ''
		}

		String decodedDestination = url.replaceAll(
			'(?i)(https?):%2f%2ft\\.maximeyes\\.com%2f([^/]+).*',
			'$1://t.maximeyes.com/$2'
		)

		return decodedDestination ?: url
	}

	private boolean isHttpUrl(String url) {
		return url != null &&
			(url.toLowerCase(Locale.ENGLISH).startsWith('https://') ||
			 url.toLowerCase(Locale.ENGLISH).startsWith('http://'))
	}

	private String normalize(String value) {
		return (value ?: '')
			.replaceAll('\\s+', ' ')
			.trim()
			.toLowerCase(Locale.ENGLISH)
	}
}