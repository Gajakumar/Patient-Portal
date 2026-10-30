package com.portal.utils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.configuration.RunConfiguration
import groovy.sql.Sql
import internal.GlobalVariable
import java.sql.Timestamp
import java.text.SimpleDateFormat

class DBDuplicateCheck {

	// ======== Connection ========
	static final String DB_URL    = 'jdbc:mysql://qamysqlserver.mysql.database.azure.com:3306/max_welcomeform_ptportal282automation?useSSL=true&requireSSL=true&serverTimezone=UTC'
	static final String DB_USER   = 'max_dev_usr'            // if login fails try 'max_dev_usr@qamysqlserver'
	static final String DB_DRIVER = 'com.mysql.cj.jdbc.Driver'
	// Password comes from Profiles > default > GlobalVariable DB_PASSWORD

	// ======== Table / columns ========
	static final String TABLE            = 'pt_customers'
	static final String COL_ID           = 'CUSTOMER_ID'
	static final String COL_PATIENT_NO   = 'MAXIMEYES_PATIENT_NUMBER'
	static final String COL_FIRST        = 'FIRST_NAME'
	static final String COL_LAST         = 'LAST_NAME'
	static final String COL_DOB          = 'DATE_OF_BIRTH'
	static final String COL_ACCT_RESP_ID = 'ACCOUNT_RESPONSIBLE_ID'
	static final String COL_ACCT_RESP_NO = 'ACCT_RESP_PATIENT_NUMBER'
	static final String COL_CREATE_DATE  = 'CREATE_DATE'
	static final String COL_CREATE_PROC  = 'CREATE_PROCESS'
	static final String COL_LOGIN        = 'EMAIL_ADDRESS'     // TODO: or CUSTOMER_EMAIL, whichever the portal logs in with
	static final String COL_EMAIL_ALT    = 'CUSTOMER_EMAIL'

	// Identifier columns that should normally be unique per patient
	static final List ID_COLS_TO_CHECK = ['MAXIMEYES_PATIENT_NUMBER', 'PMS_PATIENT_ID', 'UPDOX_PATIENT_ID', 'PATIENTID']

	// ======== Output files (under <project>/Reports) ========
	static final String LOG_FILE = 'Reports/db_duplicate_check.log'
	static final String CSV_FILE = 'Reports/db_duplicate_attribution.csv'

	// ======== State kept for the whole suite run ========
	static Map<String, Map> baseline = [:]     // duplicate groups that existed before the current test case
	static List<Map> summary = []              // one entry per test case
	static Timestamp lastCheckTime = null      // DB clock, used to list rows created by a test case
	static boolean baselineReady = false

	private Sql connect() {
		return Sql.newInstance(DB_URL, DB_USER, GlobalVariable.DB_PASSWORD as String, DB_DRIVER)
	}

	// ------------------------------------------------------------------
	// 1) BEFORE THE SUITE: record the duplicates that already exist
	// ------------------------------------------------------------------
	@Keyword
	def takeBaseline() {
		Sql sql = null
		try {
			sql = connect()
			baseline = collectDuplicates(sql)
			lastCheckTime = toTimestamp(sql.firstRow('SELECT NOW() AS now_ts').now_ts)
			summary.clear()
			baselineReady = true

			new File(RunConfiguration.getProjectDir() + '/' + CSV_FILE).with {
				parentFile.mkdirs()
				text = 'time,test_case,status,duplicate_type,value,count,customer_ids,names\n'
			}
			String msg = "BASELINE taken: ${baseline.size()} duplicate group(s) already in DB before the suite started (not blamed on any test case)."
			KeywordUtil.logInfo(msg)
			writeLog("\n########## SUITE START ${now()} ##########\n${msg}\n" +
					baseline.collect { k, v -> "   [pre-existing] ${v.type}: ${v.value} x${v.cnt} ids=${v.ids}" }.join('\n') + '\n')
		} catch (Exception e) {
			KeywordUtil.markWarning("DB baseline failed: ${e.message}")
		} finally {
			sql?.close()
		}
	}

	// ------------------------------------------------------------------
	// 2) AFTER EVERY TEST CASE: find duplicates that did not exist before it ran
	// ------------------------------------------------------------------
	@Keyword
	def checkNewDuplicates(String testCaseId, String testStatus = '', boolean failOnDuplicate = false) {
		if (!baselineReady) {              // e.g. a single test case was run, not a suite
			takeBaseline()
			KeywordUtil.logInfo("No baseline existed (not run from a suite). Baseline taken now; comparison starts with the next test case.")
			return false
		}
		Sql sql = null
		List newRows = []
		Map newDups = [:]
		try {
			sql = connect()
			Timestamp checkTime = toTimestamp(sql.firstRow('SELECT NOW() AS now_ts').now_ts)

			Map<String, Map> current = collectDuplicates(sql)
			newDups = current.findAll { k, v -> !baseline.containsKey(k) || v.cnt > baseline[k].cnt }

			// Informational: patient rows created since the previous check
			try {
				String qNew = "SELECT ${COL_ID} AS customer_id, ${COL_FIRST} AS first_name, ${COL_LAST} AS last_name, " +
						"${COL_PATIENT_NO} AS patient_no, ${COL_LOGIN} AS login_email, ${COL_CREATE_DATE} AS created, " +
						"${COL_CREATE_PROC} AS created_by FROM ${TABLE} WHERE ${COL_CREATE_DATE} >= ?"
				newRows = sql.rows(qNew, [lastCheckTime])
			} catch (Exception e) {
				KeywordUtil.logInfo("Could not list newly created rows: ${e.message}")
			}

			lastCheckTime = checkTime
			baseline = current          // next test case is compared against the state after this one
		} catch (Exception e) {
			KeywordUtil.markWarning("DB duplicate check failed to run for ${testCaseId}: ${e.message}")
			return false
		} finally {
			sql?.close()
		}

		StringBuilder report = new StringBuilder()
		report.append("\n===== ${now()} | TC: ${testCaseId} | Status: ${testStatus} =====\n")
		report.append("Patient rows created during this test case: ${newRows.size()}\n")
		newRows.each { report.append("   [new row] ${it}\n") }

		File csv = new File(RunConfiguration.getProjectDir() + '/' + CSV_FILE)
		newDups.each { k, v ->
			report.append("   >>> NEW DUPLICATE: ${v.type} = ${v.value} (x${v.cnt}) customer_ids=${v.ids} names=${v.names}\n")
			csv.append([now(), testCaseId, testStatus, v.type, v.value, v.cnt, v.ids, v.names].collect { csvEscape(it) }.join(',') + '\n')
		}
		report.append(newDups ? ">>> RESULT: this test case INTRODUCED ${newDups.size()} duplicate group(s)\n" : ">>> RESULT: no new duplicates\n")

		KeywordUtil.logInfo(report.toString())
		writeLog(report.toString())
		summary << [testCase: testCaseId, status: testStatus, newDupCount: newDups.size(), newRowCount: newRows.size()]

		if (newDups) {
			String m = "Test case '${testCaseId}' introduced ${newDups.size()} duplicate patient group(s) in ${TABLE}"
			if (failOnDuplicate) KeywordUtil.markFailed(m) else KeywordUtil.markWarning(m)
		}
		return newDups.size() > 0
	}

	// ------------------------------------------------------------------
	// 3) AFTER THE SUITE: one-glance summary
	// ------------------------------------------------------------------
	@Keyword
	def writeSuiteSummary() {
		StringBuilder sb = new StringBuilder("\n########## SUITE SUMMARY ${now()} ##########\n")
		List culprits = summary.findAll { it.newDupCount > 0 }
		sb.append("Test cases run: ${summary.size()} | Test cases that introduced duplicates: ${culprits.size()}\n")
		culprits.each { sb.append("   !! ${it.testCase} (${it.status}) -> ${it.newDupCount} new duplicate group(s)\n") }
		if (culprits.isEmpty()) sb.append("   No test case introduced duplicates.\n")
		sb.append("Per test case rows created: " + summary.collect { "${it.testCase}=${it.newRowCount}" }.join(', ') + '\n')
		KeywordUtil.logInfo(sb.toString())
		writeLog(sb.toString())
		baselineReady = false
	}

	// ------------------------------------------------------------------
	// Optional: call right after login to compare profile shown vs DB
	// ------------------------------------------------------------------
	@Keyword
	def verifyProfilePatientNumber(String loginValue, String displayedPatientNumber) {
		Sql sql = null
		try {
			sql = connect()
			String q = "SELECT ${COL_ID} AS customer_id, ${COL_FIRST} AS first_name, ${COL_LAST} AS last_name, " +
					"${COL_PATIENT_NO} AS patient_no, ${COL_ACCT_RESP_ID} AS acct_resp_id, ${COL_ACCT_RESP_NO} AS acct_resp_patient_no " +
					"FROM ${TABLE} WHERE ${COL_LOGIN} = ? OR ${COL_EMAIL_ALT} = ?"
			def rows = sql.rows(q, [loginValue, loginValue])
			String msg
			if (rows.isEmpty()) {
				msg = "VERIFY: no DB row found for login '${loginValue}'"
				KeywordUtil.markFailed(msg)
			} else if (rows.size() > 1) {
				msg = "VERIFY: ${rows.size()} DB rows share login '${loginValue}' (portal may pick the wrong one): ${rows}"
				KeywordUtil.markFailed(msg)
			} else {
				String expected = rows[0].patient_no?.toString()?.trim()
				String actual = displayedPatientNumber?.trim()
				if (expected == actual) {
					msg = "VERIFY OK: '${loginValue}' shows patient ${actual}, matches DB (${rows[0].first_name} ${rows[0].last_name})"
					KeywordUtil.markPassed(msg)
				} else {
					msg = "VERIFY MISMATCH: '${loginValue}' should be patient ${expected} (${rows[0].first_name} ${rows[0].last_name}, customer_id ${rows[0].customer_id}) but portal shows ${actual}"
					KeywordUtil.markFailed(msg)
				}
			}
			writeLog("\n${now()} ${msg}\n")
		} finally {
			sql?.close()
		}
	}

	// ==================== helpers ====================

	/** Returns every duplicate group currently in the table, keyed so two runs can be compared. */
	private Map<String, Map> collectDuplicates(Sql sql) {
		Map<String, Map> result = [:]

		ID_COLS_TO_CHECK.each { String col ->
			String q = "SELECT ${col} AS v, COUNT(*) AS cnt, GROUP_CONCAT(${COL_ID}) AS ids, " +
					"GROUP_CONCAT(CONCAT(${COL_FIRST},' ',${COL_LAST}) SEPARATOR ' | ') AS names " +
					"FROM ${TABLE} WHERE ${col} IS NOT NULL AND ${col} <> '' GROUP BY ${col} HAVING COUNT(*) > 1"
			sql.rows(q).each { r ->
				result["${col}=${r.v}".toString()] = [type: "Duplicate ${col}".toString(), value: r.v?.toString(), cnt: (r.cnt as Number).intValue(), ids: r.ids?.toString(), names: r.names?.toString()]
			}
		}

		String qLogin = "SELECT ${COL_LOGIN} AS v, COUNT(*) AS cnt, GROUP_CONCAT(${COL_ID}) AS ids, " +
				"GROUP_CONCAT(CONCAT(${COL_FIRST},' ',${COL_LAST}) SEPARATOR ' | ') AS names " +
				"FROM ${TABLE} WHERE ${COL_LOGIN} IS NOT NULL AND ${COL_LOGIN} <> '' GROUP BY ${COL_LOGIN} HAVING COUNT(*) > 1"
		sql.rows(qLogin).each { r ->
			result["LOGIN=${r.v}".toString()] = [type: "Shared login ${COL_LOGIN}".toString(), value: r.v?.toString(), cnt: (r.cnt as Number).intValue(), ids: r.ids?.toString(), names: r.names?.toString()]
		}

		String qIdentity = "SELECT ${COL_FIRST} AS f, ${COL_LAST} AS l, ${COL_DOB} AS d, COUNT(*) AS cnt, " +
				"GROUP_CONCAT(${COL_ID}) AS ids, GROUP_CONCAT(${COL_PATIENT_NO}) AS pnos " +
				"FROM ${TABLE} GROUP BY ${COL_FIRST}, ${COL_LAST}, ${COL_DOB} HAVING COUNT(*) > 1"
		sql.rows(qIdentity).each { r ->
			String val = "${r.f} ${r.l} DOB ${r.d}".toString()
			result["IDENTITY=${val}".toString()] = [type: 'Duplicate name+DOB', value: val, cnt: (r.cnt as Number).intValue(), ids: r.ids?.toString(), names: "patient_numbers=${r.pnos}".toString()]
		}
		return result
	}

	/** Newer Connector/J returns LocalDateTime for date columns; older versions return Timestamp. */
	private Timestamp toTimestamp(Object o) {
		if (o instanceof Timestamp) return (Timestamp) o
		if (o instanceof java.time.LocalDateTime) return Timestamp.valueOf((java.time.LocalDateTime) o)
		if (o instanceof java.util.Date) return new Timestamp(((java.util.Date) o).getTime())
		return Timestamp.valueOf(o.toString().replace('T', ' '))
	}

	private String now() { new SimpleDateFormat('yyyy-MM-dd HH:mm:ss').format(new Date()) }

	private String csvEscape(Object o) { '"' + (o == null ? '' : o.toString()).replace('"', '""') + '"' }

	private void writeLog(String text) {
		File f = new File(RunConfiguration.getProjectDir() + '/' + LOG_FILE)
		f.parentFile.mkdirs()
		f.append(text)
	}
}