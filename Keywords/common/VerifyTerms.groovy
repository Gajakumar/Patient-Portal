package common

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class VerifyTerms {

    /**
     * Verifies that all expected Terms & Conditions sections are present.
     *
     * @param obj TestObject containing the Terms & Conditions text
     * @param expectedSections List of expected headings or text snippets
     */
    @Keyword
    def verifyTermsSections(TestObject obj, List<String> expectedSections) {

        String actualText = WebUI.getText(obj)
                .replaceAll("\\s+", " ")
                .trim()

        expectedSections.each { section ->

            String expected = section
                    .replaceAll("\\s+", " ")
                    .trim()

            assert actualText.contains(expected) :
                    "Expected section not found:\n${expected}"
        }

        WebUI.comment("All Terms & Conditions sections verified successfully.")
    }
}