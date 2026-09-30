
package custom

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.exception.StepFailedException

class AppointmentSettingsKeywords {

    /**
     * Select dropdown values and set checkbox states.
     *
     * Example dropdownValues:
     *
     * [
     *     "SCHEDULING_HOURS"              : "12",
     *     "CANCELATION_HOURS"             : "3",
     *     "DAILY_LIMIT_HOURS"             : "0",
     *     "ApptLimitForOnlineAppointment" : "5"
     * ]
     *
     * Example checkboxValues:
     *
     * [
     *     "idLimitOnlineEnable"               : true,
     *     "idIsOnlineApptActivityReportEnable": false,
     *     "idIsEnableOnlineScheduleInsurance": true
     * ]
     */
    @Keyword
    def setAppointmentSettings(
            Map<String, String> dropdownValues,
            Map<String, Boolean> checkboxValues) {

        // =========================================================
        // STEP 1: SET CHECKBOX STATES FIRST
        // =========================================================
        //
        // This must happen before dropdown selection because
        // ApptLimitForOnlineAppointment depends on
        // idLimitOnlineEnable.
        //
        // =========================================================

        checkboxValues.each { checkboxId, expectedState ->

            TestObject checkbox = new TestObject(
                    "dynamicCheckbox_${checkboxId}"
            )

            checkbox.addProperty(
                    "xpath",
                    ConditionType.EQUALS,
                    "//*[@id='${checkboxId}']"
            )

            WebUI.comment(
                    "Setting checkbox: ${checkboxId}"
            )

            WebUI.waitForElementVisible(
                    checkbox,
                    10
            )

            boolean actualState = WebUI.verifyElementChecked(
                    checkbox,
                    5,
                    FailureHandling.OPTIONAL
            )

            // -----------------------------------------------------
            // Click only when current state is different
            // -----------------------------------------------------

            if (actualState != expectedState) {

                WebUI.click(checkbox)

                WebUI.comment(
                        "Checkbox '${checkboxId}' changed to ${expectedState}"
                )

            } else {

                WebUI.comment(
                        "Checkbox '${checkboxId}' already set to ${expectedState}"
                )
            }

            // -----------------------------------------------------
            // Verify final checkbox state
            // -----------------------------------------------------

            boolean finalState = WebUI.verifyElementChecked(
                    checkbox,
                    5,
                    FailureHandling.OPTIONAL
            )

            assert finalState == expectedState :
                    "Checkbox '${checkboxId}' expected ${expectedState}, " +
                    "but actual state is ${finalState}"
        }


        // =========================================================
        // STEP 2: SELECT DROPDOWN VALUES
        // =========================================================

        dropdownValues.each { fieldId, value ->

            TestObject dropdown = new TestObject(
                    "dynamicDropdown_${fieldId}"
            )

            dropdown.addProperty(
                    "xpath",
                    ConditionType.EQUALS,
                    "//*[@id='${fieldId}']"
            )

            WebUI.comment(
                    "Setting dropdown: ${fieldId} = ${value}"
            )

            WebUI.waitForElementVisible(
                    dropdown,
                    10
            )


            // =====================================================
            // SPECIAL DEPENDENCY
            // =====================================================
            //
            // ApptLimitForOnlineAppointment is disabled until
            // idLimitOnlineEnable is enabled.
            //
            // =====================================================

            if (fieldId == "ApptLimitForOnlineAppointment") {

                boolean onlineLimitEnabled =
                        checkboxValues.containsKey("idLimitOnlineEnable") &&
                        checkboxValues["idLimitOnlineEnable"]


                // -------------------------------------------------
                // If online appointment limit is enabled
                // -------------------------------------------------

                if (onlineLimitEnabled) {

                    WebUI.comment(
                            "Waiting for " +
                            "ApptLimitForOnlineAppointment " +
                            "to become enabled..."
                    )

                    waitForOnlineAppointmentLimitEnabled(15)

                    WebUI.comment(
                            "ApptLimitForOnlineAppointment is now enabled."
                    )

                } else {

                    // -------------------------------------------------
                    // Do not try to select a disabled dropdown
                    // -------------------------------------------------

                    WebUI.comment(
                            "idLimitOnlineEnable is false. " +
                            "Skipping ApptLimitForOnlineAppointment."
                    )

                    return
                }
            }


            // =====================================================
            // SELECT DROPDOWN VALUE
            // =====================================================

            WebUI.selectOptionByValue(
                    dropdown,
                    value,
                    false
            )

            WebUI.comment(
                    "Selected '${value}' for dropdown '${fieldId}'"
            )


            // =====================================================
            // VERIFY DROPDOWN VALUE
            // =====================================================

            String actualValue = WebUI.getAttribute(
                    dropdown,
                    "value"
            )

            WebUI.comment(
                    "Dropdown '${fieldId}' expected value: '${value}'"
            )

            WebUI.comment(
                    "Dropdown '${fieldId}' actual value: '${actualValue}'"
            )

            assert actualValue == value :
                    "Dropdown '${fieldId}' expected '${value}', " +
                    "but actual value is '${actualValue}'"

            WebUI.comment(
                    "Verified '${fieldId}' = '${value}'"
            )
        }


        WebUI.comment(
                "=============================================="
        )

        WebUI.comment(
                "Appointment settings configured successfully."
        )

        WebUI.comment(
                "=============================================="
        )
    }


    /**
     * Wait until ApptLimitForOnlineAppointment becomes enabled.
     *
     * Checks the actual HTML disabled property using JavaScript.
     *
     * Example:
     *
     * <select id="ApptLimitForOnlineAppointment" disabled>
     *
     * Once enabled:
     *
     * <select id="ApptLimitForOnlineAppointment">
     */
    private void waitForOnlineAppointmentLimitEnabled(
            int timeoutSeconds) {

        long endTime =
                System.currentTimeMillis() +
                (timeoutSeconds * 1000L)


        while (System.currentTimeMillis() < endTime) {

            boolean isDisabled = WebUI.executeJavaScript(
                    "return document.getElementById(" +
                    "'ApptLimitForOnlineAppointment').disabled;",
                    null
            )


            // -----------------------------------------------------
            // Dropdown is enabled
            // -----------------------------------------------------

            if (!isDisabled) {

                WebUI.comment(
                        "ApptLimitForOnlineAppointment is enabled."
                )

                return
            }


            // -----------------------------------------------------
            // Dropdown is still disabled
            // -----------------------------------------------------

            WebUI.comment(
                    "ApptLimitForOnlineAppointment is still disabled. " +
                    "Waiting 1 second..."
            )

            WebUI.delay(1)
        }


        // =========================================================
        // TIMEOUT
        // =========================================================

        throw new StepFailedException(
                "ApptLimitForOnlineAppointment did not become " +
                "enabled within " +
                timeoutSeconds +
                " seconds after enabling " +
                "idLimitOnlineEnable."
        )
    }
}

