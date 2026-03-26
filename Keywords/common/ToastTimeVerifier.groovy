package common

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.regex.*

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
class ToastTimeVerifier {

	@Keyword
def verifyGmtTimeToast(TestObject toastObj = null, int toleranceMinutes = 5) {

    // 1️⃣ Use ACTIVE toast locator (ignore exiting toast)
    TestObject activeToast = new TestObject().addProperty(
        "xpath",
        ConditionType.EQUALS,
        "//div[contains(@class,'Toastify__toast') and @data-in='true']"
    )

    String rawToast = ""
    int attempts = 0
    int maxAttempts = 5

    // 2️⃣ Retry mechanism (toast is short-lived)
    while (attempts < maxAttempts) {

        if (WebUI.waitForElementVisible(activeToast, 5, FailureHandling.OPTIONAL)) {

            rawToast = WebUI.getAttribute(activeToast, "textContent")?.trim()

            if (rawToast) {
                break
            }
        }

        WebUI.delay(1)
        attempts++
    }

    // 3️⃣ Final validation
    assert rawToast :
        "❌ Toast message NOT captured.\n" +
        "Possible reasons:\n" +
        "1. Toast disappeared too quickly\n" +
        "2. Locator issue\n" +
        "3. UI not triggered properly"

    println "RAW TOAST >>> ${rawToast} <<<"

    // 4️⃣ Clean text
    String toastText = rawToast
        .replaceAll("\\s+", " ")
        .replaceAll("close", "")
        .trim()

    println "=========== CLEAN TOAST TEXT ==========="
    println toastText
    println "========================================"


    // 5️⃣ Extract Time (12h + 24h)
    Pattern timePattern = Pattern.compile(
        "\\b(\\d{1,2}:\\d{2})\\s*(AM|PM)?\\b",
        Pattern.CASE_INSENSITIVE
    )

    Matcher matcher = timePattern.matcher(toastText)

    assert matcher.find() :
        "❌ Time not found in toast.\nActual: ${toastText}"

    String timePart = matcher.group(1)?.trim()
    String amPmPart = matcher.group(2)?.trim()?.toUpperCase()

    println "🕒 Extracted Time: ${timePart} ${amPmPart ?: '(24h format)'}"


    // 6️⃣ Parse Time Safely
    LocalTime toastTime

    try {
        if (amPmPart) {
            String fullTime = "${timePart} ${amPmPart}"

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
            toastTime = LocalTime.parse(fullTime, formatter)

        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm")
            toastTime = LocalTime.parse(timePart, formatter)
        }

    } catch (Exception e) {
        throw new AssertionError(
            "❌ Time parsing failed.\n" +
            "Extracted: ${timePart} ${amPmPart ?: ''}\n" +
            "Toast: ${toastText}\n" +
            "Error: ${e.message}"
        )
    }


    // 7️⃣ Convert to GMT
    ZonedDateTime toastGmt = ZonedDateTime.of(
        LocalDate.now(ZoneId.of("GMT")),
        toastTime,
        ZoneId.of("GMT")
    )

    ZonedDateTime nowGmt = ZonedDateTime.now(ZoneId.of("GMT"))

    long diffMinutes = Math.abs(
        ChronoUnit.MINUTES.between(toastGmt, nowGmt)
    )

    println "⏱ GMT Time Difference: ${diffMinutes} minutes"


    // 8️⃣ Business Assertions
    assert toastText.contains("Your account was locked at") :
        "❌ Expected text missing: 'Your account was locked at'"

    assert toastText.contains("15 minutes from the lock time") :
        "❌ Expected text missing: '15 minutes from the lock time'"


    // 9️⃣ Final Time Validation
    assert diffMinutes <= toleranceMinutes :
        "❌ Time mismatch.\n" +
        "Allowed: ${toleranceMinutes} min\n" +
        "Actual: ${diffMinutes} min\n" +
        "Toast GMT: ${toastGmt}\n" +
        "Current GMT: ${nowGmt}"
}
}
