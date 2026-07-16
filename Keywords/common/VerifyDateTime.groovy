package common

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class VerifyDateTime {

    @Keyword
    def verifyCurrentDateTime(TestObject obj, int toleranceMinutes = 2) {

        String actual = WebUI.getText(obj)
                .replace('\u00A0', ' ')
                .trim()

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH)

        LocalDateTime actualTime = LocalDateTime.parse(actual, formatter)

        // Compare against UTC
        LocalDateTime currentTime = ZonedDateTime
                .now(ZoneId.of("UTC"))
                .toLocalDateTime()

        long diff = Math.abs(Duration.between(actualTime, currentTime).toMinutes())

        assert diff <= toleranceMinutes :
                """Timestamp is outside ±${toleranceMinutes} minute(s).
Actual     : ${actual}
Expected   : ${currentTime.format(formatter)}
Difference : ${diff} minute(s)
"""

        WebUI.comment("Timestamp verified successfully.")
    }
}