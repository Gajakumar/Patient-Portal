
package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

class PasswordKeywords {

    @Keyword
    def verifyPasswordCriteria() {

        List<String> expectedCriteria = [
            'At least 8 characters long',
            'At least 1 uppercase letter (A, B, C...)',
            'At least 1 lowercase letter (a, b, c...)',
            'At least 1 number (1, 2, 3...)',
            'At least 1 special character/punctuation (%, @, #, ?...)'
        ]

        TestObject criteriaContainer = new TestObject('passwordCriteriaContainer')
        criteriaContainer.addProperty(
            'xpath',
            ConditionType.EQUALS,
            "//h2[normalize-space()='Password Criteria']/following-sibling::div"
        )

        WebUI.waitForElementVisible(criteriaContainer, 10)

        String actualText = WebUI.getText(criteriaContainer)

        expectedCriteria.each { expectedText ->

            if (actualText.contains(expectedText)) {
                KeywordUtil.logInfo(
                    "PASS: Password criteria displayed - ${expectedText}"
                )
            } else {
                KeywordUtil.markFailed(
                    "FAIL: Password criteria missing - ${expectedText}"
                )
            }
        }

        KeywordUtil.logInfo('Password criteria verification completed.')
    }
	
	
	@Keyword
	def verifyLinkExpiredMessage() {
	
		String expectedHeading = 'Link Expired'
	
		String expectedMessage = 'The link has expired or is no longer valid The link is valid for 60 minutes or one-time use only. To receive a new link, please use Forgot Username/Password.'
	
		TestObject expiredHeading = new TestObject('expiredHeading')
		expiredHeading.addProperty(
			'xpath',
			ConditionType.EQUALS,
			"//main[@aria-label='Link expired message']//h1"
		)
	
		TestObject expiredMessage = new TestObject('expiredMessage')
		expiredMessage.addProperty(
			'xpath',
			ConditionType.EQUALS,
			"//main[@aria-label='Link expired message']//p"
		)
	
		WebUI.waitForElementVisible(expiredHeading, 10)
	
		// Verify heading
		String actualHeading = WebUI.getText(expiredHeading)
	
		WebUI.verifyMatch(
			actualHeading,
			expectedHeading,
			false
		)
	
		KeywordUtil.logInfo("PASS: Heading displayed - ${actualHeading}")
	
		// Verify message
		String actualMessage = WebUI.getText(expiredMessage)
	
		if (actualMessage.trim() == expectedMessage) {
			KeywordUtil.logInfo("PASS: Link expired message displayed correctly.")
		} else {
			KeywordUtil.markFailed(
				"FAIL: Link expired message mismatch.\n" +
				"EXPECTED: ${expectedMessage}\n" +
				"ACTUAL: ${actualMessage}"
			)
		}
	
		KeywordUtil.logInfo('Link Expired page verification completed.')
	}
	
}
