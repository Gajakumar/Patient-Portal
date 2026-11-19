import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.util.KeywordUtil
import com.portal.config.EnvConfig

import internal.GlobalVariable as GlobalVariable

/**
 * Populates credential GlobalVariables from environment variables / the local .env file.
 * The profile (Profiles/default.glbl) keeps these values empty so no secret lives in source.
 * A GlobalVariable that already holds a value (e.g. set at runtime by an earlier test case
 * in the same suite) is left untouched, so existing test behaviour is preserved.
 * Values are never logged; a missing variable only logs its NAME as a warning.
 */
class EnvSecretsListener {

	private static Set<String> warned = new HashSet<String>()

	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuiteContext) {
		applySecrets()
	}

	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		applySecrets()
	}

	static void applySecrets() {
		GlobalVariable.MaxUsername        = fill(GlobalVariable.MaxUsername,        'MAXIMEYES_USERNAME',       'MaxUsername')
		GlobalVariable.MaxPassword        = fill(GlobalVariable.MaxPassword,        'MAXIMEYES_PASSWORD',       'MaxPassword')
		GlobalVariable.MyEmail_Id         = fill(GlobalVariable.MyEmail_Id,         'GMAIL_ADDRESS',            'MyEmail_Id')
		GlobalVariable.Email_Key          = fill(GlobalVariable.Email_Key,          'GMAIL_APP_PASSWORD',       'Email_Key')
		GlobalVariable.UpdatePassword     = fill(GlobalVariable.UpdatePassword,     'PP_UPDATE_PASSWORD',       'UpdatePassword')
		GlobalVariable.changedPassword    = fill(GlobalVariable.changedPassword,    'PP_CHANGED_PASSWORD',      'changedPassword')
		GlobalVariable.RestUpdatedPass    = fill(GlobalVariable.RestUpdatedPass,    'PP_REST_UPDATED_PASSWORD', 'RestUpdatedPass')
		GlobalVariable.apiUsername        = fill(GlobalVariable.apiUsername,        'PP_API_USERNAME',          'apiUsername')
		GlobalVariable.DB_USERNAME        = fill(GlobalVariable.DB_USERNAME,        'DB_USERNAME',              'DB_USERNAME')
		GlobalVariable.DB_PASSWORD        = fill(GlobalVariable.DB_PASSWORD,        'DB_PASSWORD',              'DB_PASSWORD')
		GlobalVariable.DB_PASSWORD1       = fill(GlobalVariable.DB_PASSWORD1,       'DB_PASSWORD_ALT',          'DB_PASSWORD1')
		GlobalVariable.PP_API_AUTH_TOKEN  = fill(GlobalVariable.PP_API_AUTH_TOKEN,  'PP_API_AUTH_TOKEN',        'PP_API_AUTH_TOKEN')
		GlobalVariable.PP_API_SESSION_GUID = fill(GlobalVariable.PP_API_SESSION_GUID, 'PP_API_SESSION_GUID',    'PP_API_SESSION_GUID')
	}

	private static Object fill(Object current, String envName, String gvName) {
		if (current != null && current.toString().length() > 0) {
			return current
		}
		String v = EnvConfig.getOptional(envName)
		if (v != null) {
			return v
		}
		if (warned.add(envName)) {
			KeywordUtil.logInfo("[EnvSecretsListener] Environment variable '" + envName + "' is not set; GlobalVariable." + gvName + " stays empty.")
		}
		return current
	}
}