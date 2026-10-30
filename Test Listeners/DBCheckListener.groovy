import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.portal.utils.DBDuplicateCheck

class DBCheckListener {

	/** Once, before the first test case: remember which duplicates already exist. */
	@BeforeTestSuite
	def beforeSuite(TestSuiteContext testSuiteContext) {
		new DBDuplicateCheck().takeBaseline()
	}

	/** After every test case (pass or fail): report duplicates that this test case introduced. */
	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) {
		new DBDuplicateCheck().checkNewDuplicates(
			testCaseContext.getTestCaseId(),
			testCaseContext.getTestCaseStatus(),
			false)   // set to true to mark the test case failed when it introduces duplicates
	}

	/** Once, after the last test case: print which test cases caused duplicates. */
	@AfterTestSuite
	def afterSuite(TestSuiteContext testSuiteContext) {
		new DBDuplicateCheck().writeSuiteSummary()
	}
}