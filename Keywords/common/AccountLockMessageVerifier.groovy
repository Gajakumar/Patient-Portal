package common

import java.util.regex.Matcher
import java.util.regex.Pattern

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class AccountLockMessageVerifier {

    @Keyword
    def verifyAccountLockCountdown(TestObject messageObj, int maxMinutes = 15) {

        String actualText = WebUI.getText(messageObj).trim()

        println "==============================="
        println "Account Lock Message:"
        println actualText
        println "==============================="

        // Verify static text
        assert actualText.startsWith("You can unlock your account after") :
            "Unexpected message:\n${actualText}"

        assert actualText.contains("from the time locked, by clicking forgot password link.") :
            "Unexpected message:\n${actualText}"

        // Extract countdown timer (MM:SS)
        Pattern pattern = Pattern.compile("(\\d{1,2}):(\\d{2})")
        Matcher matcher = pattern.matcher(actualText)

        assert matcher.find() :
            "Countdown timer not found.\nActual: ${actualText}"

        int minutes = matcher.group(1).toInteger()
        int seconds = matcher.group(2).toInteger()

        println "Remaining Time: ${minutes}:${String.format('%02d', seconds)}"

        // Validate timer
        assert minutes >= 0 && minutes <= maxMinutes :
            "Invalid minutes value: ${minutes}"

        assert seconds >= 0 && seconds <= 59 :
            "Invalid seconds value: ${seconds}"

        println "✅ Account lock countdown message verified successfully."
    }
}