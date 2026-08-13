import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

// ============================================================
// Configuration
// ============================================================

String buildVersion = GlobalVariable.BuildVersion

Connection connection = null

try {

	KeywordUtil.logInfo('================================================')
	KeywordUtil.logInfo('QA Automation Precondition Started')
	KeywordUtil.logInfo("Build Version: ${buildVersion}")
	KeywordUtil.logInfo('================================================')

	// ========================================================
	// Get DB Connection
	// ========================================================

	connection = DriverManager.getConnection(
		GlobalVariable.DB_URL,
		GlobalVariable.DB_USERNAME,
		GlobalVariable.DB_PASSWORD
	)

	connection.setAutoCommit(false)

	// ========================================================
	// Current Date
	// ========================================================

	String currentDate = new Date().format('yyyy-MM-dd HH:mm:ss')

	// ========================================================
	// Get CREATE_BY
	// ========================================================

	int createBy = -15

	String userQuery = '''
        SELECT ISNULL(
            (
                SELECT USER_ID
                FROM USERS
                WHERE LOGIN = 'QA_User'
                AND IS_ACTIVE = 1
            ),
            -15
        ) AS CREATE_BY
    '''

	PreparedStatement userStatement = connection.prepareStatement(userQuery)
	ResultSet userResult = userStatement.executeQuery()

	if (userResult.next()) {
		createBy = userResult.getInt('CREATE_BY')
	}

	userResult.close()
	userStatement.close()

	KeywordUtil.logInfo("CREATE_BY: ${createBy}")

	// ========================================================
	// Get CREATE_PROCESS
	// ========================================================

	int createProcess = 17

	String processQuery = '''
        SELECT ISNULL(
            (
                SELECT PROCESS_ID
                FROM PROCESSES
                WHERE PROCESS_DESCRIPTION = 'AUTO_PRECONDITION'
            ),
            17
        ) AS CREATE_PROCESS
    '''

	PreparedStatement processStatement =
			connection.prepareStatement(processQuery)

	ResultSet processResult = processStatement.executeQuery()

	if (processResult.next()) {
		createProcess = processResult.getInt('CREATE_PROCESS')
	}

	processResult.close()
	processStatement.close()

	KeywordUtil.logInfo("CREATE_PROCESS: ${createProcess}")

	// ========================================================
	// Update automation patients
	// ========================================================

	String updateAutomationPatients = '''
        UPDATE CUSTOMERS
        SET CREATE_BY = ?
        WHERE SOCIAL_SECURITY_NUMBER LIKE '999999%'
        AND (IS_ACTIVE = 1 OR IS_PATIENT_ACTIVE = 1)
    '''

	PreparedStatement updatePatientsStatement =
			connection.prepareStatement(updateAutomationPatients)

	updatePatientsStatement.setInt(1, createBy)

	int patientsMarked =
			updatePatientsStatement.executeUpdate()

	updatePatientsStatement.close()

	KeywordUtil.logInfo(
		"Automation patients identified: ${patientsMarked}"
	)

	// ========================================================
	// Deactivate existing automation patients
	// ========================================================

	String deactivatePatients = '''
        UPDATE CUSTOMERS
        SET
            FIRST_NAME = LEFT(CONVERT(VARCHAR(40), NEWID()), 8),
            LAST_NAME = LEFT(CONVERT(VARCHAR(40), NEWID()), 8),
            PREFERRED_NAME = LEFT(CONVERT(VARCHAR(40), NEWID()), 8),
            IS_PATIENT_ACTIVE = 0,
            IS_ACTIVE = 0,
            ACTIVE_INACTIVE_REASON = 'Other',
            ACTIVE_INACTIVE_REASON_TEXT =
                'Inactive by Automation precondition script',
            SOCIAL_SECURITY_NUMBER = '',
            UPDATE_BY = ?,
            UPDATE_PROCESS = ?,
            UPDATE_DATE = GETUTCDATE()
        WHERE (IS_PATIENT_ACTIVE = 1 OR IS_ACTIVE = 1)
        AND (CREATE_BY = ? OR CREATE_BY = -15)
    '''

	PreparedStatement deactivateStatement =
			connection.prepareStatement(deactivatePatients)

	deactivateStatement.setInt(1, createBy)
	deactivateStatement.setInt(2, createProcess)
	deactivateStatement.setInt(3, createBy)

	int deactivatedPatients =
			deactivateStatement.executeUpdate()

	deactivateStatement.close()

	KeywordUtil.logInfo(
		"Patients deactivated: ${deactivatedPatients}"
	)

	// ========================================================
	// Get QA User Location
	// ========================================================

	String locationId = '0'

	String locationQuery = '''
        SELECT CAST(ISNULL(DEFUALT_LOCATION_ID, 0) AS VARCHAR) AS LOCATION_ID
        FROM USERS
        WHERE USER_ID = ?
    '''

	PreparedStatement locationStatement =
			connection.prepareStatement(locationQuery)

	locationStatement.setInt(1, createBy)

	ResultSet locationResult =
			locationStatement.executeQuery()

	if (locationResult.next()) {
		locationId = locationResult.getString('LOCATION_ID')
	}

	locationResult.close()
	locationStatement.close()

	KeywordUtil.logInfo("LOCATION_ID: ${locationId}")

	// ========================================================
	// Payment Print Preference
	// ========================================================

	String paymentPreferenceCheck = '''
        SELECT COUNT(*) AS CNT
        FROM PREFERENCES
        WHERE PREFERENCE_NAME =
            'Default_Print_Preferences_When_Posting_Payments_Print'
        AND IS_ACTIVE = 1
        AND [USER_ID] = 1
    '''

	PreparedStatement paymentCheckStatement =
			connection.prepareStatement(paymentPreferenceCheck)

	ResultSet paymentResult =
			paymentCheckStatement.executeQuery()

	int paymentPreferenceCount = 0

	if (paymentResult.next()) {
		paymentPreferenceCount =
				paymentResult.getInt('CNT')
	}

	paymentResult.close()
	paymentCheckStatement.close()

	if (paymentPreferenceCount == 0) {

		String insertPaymentPreference = '''
            INSERT INTO PREFERENCES
            (
                [USER_ID],
                PREFERENCE_TYPE,
                PREFERENCE_NAME,
                PREFERENCE_VALUE,
                IS_ACTIVE,
                CREATE_BY,
                CREATE_DATE,
                CREATE_PROCESS,
                UPDATE_BY,
                UPDATE_DATE,
                UPDATE_PROCESS,
                PREFERENCE_DESCRIPTION,
                LOCATION_ID
            )
            VALUES
            (
                1,
                'System',
                'Default_Print_Preferences_When_Posting_Payments_Print',
                'False',
                1,
                ?,
                GETUTCDATE(),
                ?,
                ?,
                GETUTCDATE(),
                ?,
                NULL,
                ?
            )
        '''

		PreparedStatement insertPaymentStatement =
				connection.prepareStatement(insertPaymentPreference)

		insertPaymentStatement.setInt(1, createBy)
		insertPaymentStatement.setInt(2, createProcess)
		insertPaymentStatement.setInt(3, createBy)
		insertPaymentStatement.setInt(4, createProcess)
		insertPaymentStatement.setString(5, locationId)

		insertPaymentStatement.executeUpdate()

		insertPaymentStatement.close()

		KeywordUtil.logInfo(
			'Payment print preference created with value False'
		)

	} else {

		String updatePaymentPreference = '''
            UPDATE PREFERENCES
            SET
                PREFERENCE_VALUE = 'False',
                UPDATE_BY = ?,
                UPDATE_DATE = GETUTCDATE(),
                UPDATE_PROCESS = ?
            WHERE PREFERENCE_NAME =
                'Default_Print_Preferences_When_Posting_Payments_Print'
            AND IS_ACTIVE = 1
            AND [USER_ID] = 1
            AND LOCATION_ID = ?
        '''

		PreparedStatement updatePaymentStatement =
				connection.prepareStatement(updatePaymentPreference)

		updatePaymentStatement.setInt(1, createBy)
		updatePaymentStatement.setInt(2, createProcess)
		updatePaymentStatement.setString(3, locationId)

		updatePaymentStatement.executeUpdate()

		updatePaymentStatement.close()

		KeywordUtil.logInfo(
			'Payment print preference updated to False'
		)
	}

	// ========================================================
	// DEFAULT_COUNTRY = USA
	// ========================================================

	String defaultCountryQuery = '''
        IF NOT EXISTS
        (
            SELECT 1
            FROM PREFERENCES
            WHERE PREFERENCE_NAME = 'DEFAULT_COUNTRY'
            AND IS_ACTIVE = 1
        )
        BEGIN

            INSERT INTO PREFERENCES
            (
                [USER_ID],
                PREFERENCE_TYPE,
                PREFERENCE_NAME,
                PREFERENCE_VALUE,
                IS_ACTIVE,
                CREATE_BY,
                CREATE_DATE,
                CREATE_PROCESS,
                UPDATE_BY,
                UPDATE_DATE,
                UPDATE_PROCESS,
                PREFERENCE_DESCRIPTION,
                LOCATION_ID
            )
            VALUES
            (
                NULL,
                'GENERAL',
                'DEFAULT_COUNTRY',
                'USA',
                1,
                ?,
                GETUTCDATE(),
                ?,
                ?,
                GETUTCDATE(),
                ?,
                NULL,
                NULL
            )

        END
        ELSE
        BEGIN

            UPDATE PREFERENCES
            SET
                PREFERENCE_VALUE = 'USA',
                UPDATE_BY = ?,
                UPDATE_DATE = GETUTCDATE(),
                UPDATE_PROCESS = ?
            WHERE PREFERENCE_NAME = 'DEFAULT_COUNTRY'
            AND IS_ACTIVE = 1

        END
    '''

	PreparedStatement countryStatement =
			connection.prepareStatement(defaultCountryQuery)

	countryStatement.setInt(1, createBy)
	countryStatement.setInt(2, createProcess)
	countryStatement.setInt(3, createBy)
	countryStatement.setInt(4, createProcess)
	countryStatement.setInt(5, createBy)
	countryStatement.setInt(6, createProcess)

	countryStatement.executeUpdate()
	countryStatement.close()

	KeywordUtil.logInfo('DEFAULT_COUNTRY set to USA')

	// ========================================================
	// AUTO_LOCK_DURATION = 120
	// ========================================================

	String autoLockQuery = '''
        IF NOT EXISTS
        (
            SELECT 1
            FROM PREFERENCES
            WHERE PREFERENCE_NAME = 'AUTO_LOCK_DURATION'
            AND IS_ACTIVE = 1
        )
        BEGIN

            INSERT INTO PREFERENCES
            (
                [USER_ID],
                PREFERENCE_TYPE,
                PREFERENCE_NAME,
                PREFERENCE_VALUE,
                IS_ACTIVE,
                CREATE_BY,
                CREATE_DATE,
                CREATE_PROCESS,
                UPDATE_BY,
                UPDATE_DATE,
                UPDATE_PROCESS,
                PREFERENCE_DESCRIPTION,
                LOCATION_ID
            )
            VALUES
            (
                NULL,
                'GENERAL',
                'AUTO_LOCK_DURATION',
                '120',
                1,
                ?,
                GETUTCDATE(),
                ?,
                ?,
                GETUTCDATE(),
                ?,
                NULL,
                NULL
            )

        END
        ELSE
        BEGIN

            UPDATE PREFERENCES
            SET
                PREFERENCE_VALUE = '120',
                UPDATE_BY = ?,
                UPDATE_DATE = GETUTCDATE(),
                UPDATE_PROCESS = ?
            WHERE PREFERENCE_NAME = 'AUTO_LOCK_DURATION'
            AND IS_ACTIVE = 1

        END
    '''

	PreparedStatement autoLockStatement =
			connection.prepareStatement(autoLockQuery)

	autoLockStatement.setInt(1, createBy)
	autoLockStatement.setInt(2, createProcess)
	autoLockStatement.setInt(3, createBy)
	autoLockStatement.setInt(4, createProcess)
	autoLockStatement.setInt(5, createBy)
	autoLockStatement.setInt(6, createProcess)

	autoLockStatement.executeUpdate()
	autoLockStatement.close()

	KeywordUtil.logInfo('AUTO_LOCK_DURATION set to 120')

	// ========================================================
	// Notification Preference
	// ========================================================

	String notificationPreferenceQuery = '''
        SELECT
            PREFERENCE_ID,
            LOCATION_ID
        FROM PREFERENCES
        WHERE PREFERENCE_NAME = 'DURATION'
        AND PREFERENCE_TYPE = 'NOTIFICATION_PREFERENCE'
        AND IS_ACTIVE = 1
    '''

	PreparedStatement notificationStatement =
			connection.prepareStatement(notificationPreferenceQuery)

	ResultSet notificationResult =
			notificationStatement.executeQuery()

	int preferenceId = 0
	String notificationLocationId = null

	if (notificationResult.next()) {

		preferenceId =
				notificationResult.getInt('PREFERENCE_ID')

		notificationLocationId =
				notificationResult.getString('LOCATION_ID')
	}

	notificationResult.close()
	notificationStatement.close()

	KeywordUtil.logInfo(
		"Notification Preference ID: ${preferenceId}"
	)

	// ========================================================
	// Execute stored procedure
	// ========================================================

	if (preferenceId > 0) {

		String procedureCall = '''
            EXEC EHR_INSERT_UPDATE_PREFERENCES
                @CREATE_BY = ?,
                @CREATE_PROCESS = ?,
                @STATUS = ?
        '''

		// NOTE:
		// TVP parameters cannot be reliably passed using the
		// standard JDBC PreparedStatement.
		//
		// Therefore, this part is better kept as a SQL statement
		// executed directly through the database connection.

		KeywordUtil.logInfo(
			'Notification preference requires TVP DBO.TVP_PREFERENCES'
		)

		KeywordUtil.logInfo(
			'Original SQL stored procedure call should be executed here.'
		)
	}

	// ========================================================
	// Record successful execution
	// ========================================================

	String automationLogQuery = '''
        INSERT INTO QA_AUTOMATION
        VALUES
        (
            '10_QAA_PRECONDITION_INACTIVE_PATIENTS_AND_PREFERENCE',
            '10_QAA_PRECONDITION_INACTIVE_PATIENTS_AND_PREFERENCE.SQL',
            GETUTCDATE(),
            1,
            'Executed Successfully',
            1,
            GETUTCDATE(),
            1,
            ?
        )
    '''

	PreparedStatement automationLogStatement =
			connection.prepareStatement(automationLogQuery)

	automationLogStatement.setString(1, buildVersion)

	automationLogStatement.executeUpdate()
	automationLogStatement.close()

	// ========================================================
	// Commit
	// ========================================================

	connection.commit()

	KeywordUtil.logInfo('================================================')
	KeywordUtil.logInfo('QA Automation Precondition Completed Successfully')
	KeywordUtil.logInfo('================================================')

} catch (Exception e) {

	if (connection != null) {
		try {
			connection.rollback()
		} catch (Exception rollbackException) {
			KeywordUtil.logWarning(
				"Rollback failed: ${rollbackException.message}"
			)
		}
	}

	KeywordUtil.markFailed(
		"QA Automation Precondition Failed: ${e.message}"
	)

	throw e

} finally {

	if (connection != null) {
		try {
			connection.close()
		} catch (Exception e) {
			KeywordUtil.logWarning(
				"Unable to close database connection: ${e.message}"
			)
		}
	}
}