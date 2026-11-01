package com.portal.utils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.configuration.RunConfiguration
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.sql.Sql
import internal.GlobalVariable
import java.sql.Timestamp
import java.text.SimpleDateFormat

class DBDuplicateCheck {

	// ======== Connection ========
	static final String DB_USER   = 'max_dev_usr'            // if login fails try 'max_dev_usr@qamysqlserver'
	static final String DB_DRIVER = 'com.mysql.cj.jdbc.Driver'
	// Password comes from Profiles > default > GlobalVariable DB_PASSWORD
	static final String DB_NAME             = 'max_welcomeform_ptportal282automation'
	static final String DB_DIRECT_HOST_PORT = 'qamysqlserver.mysql.database.azure.com:3306'   // used when no tunnel is present
	// TestCloud Tunnel forwarding rule QAMYSQL gives the test node env var TUN_QAMYSQL = localhost:<port>
	static final String TUNNEL_ENV_VAR      = 'TUN_QAMYSQL'
	static final String DB_URL_PARAMS       = 'useSSL=true&requireSSL=true&verifyServerCertificate=false&enabledTLSProtocols=TLSv1.2&connectTimeout=20000&socketTimeout=120000&serverTimezone=UTC'

	// ======== Table / columns ========
	static final String TABLE            = 'pt_customers'
	static final String COL_ID           = 'CUSTOMER_ID'
	static final String COL_PATIENT_NO   = 'MAXIMEYES_PATIENT_NUMBER'
	static final String COL_FIRST        = 'FIRST_NAME'
	static final String COL_LAST         = 'LAST_NAME'
	static final String COL_DOB          = 'DATE_OF_BIRTH'
	static final String COL_LOGIN        = 'EMAIL_ADDRESS'     // TODO: or CUSTOMER_EMAIL, whichever the portal logs in with
	static final String COL_EMAIL_ALT    = 'CUSTOMER_EMAIL'
	static final String COL_ACCT_RESP_ID = 'ACCOUNT_RESPONSIBLE_ID'
	static final String COL_ACCT_RESP_NO = 'ACCT_RESP_PATIENT_NUMBER'
	static final String COL_UPDATE_DATE  = 'UPDATE_DATE'
	static final String COL_UPDATE_BY    = 'UPDATE_BY'
	static final String COL_UPDATE_PROC  = 'UPDATE_PROCESS'

	// Identifier columns that should normally be unique per patient
	static final List ID_COLS_TO_CHECK = ['MAXIMEYES_PATIENT_NUMBER', 'PMS_PATIENT_ID', 'UPDOX_PATIENT_ID', 'PATIENTID']

	// Fields watched for changes on EXISTING patients (short key -> column name)
	static final Map FIELD_LABELS = [f: COL_FIRST, l: COL_LAST, d: COL_DOB, p: COL_PATIENT_NO, e: COL_LOGIN]
	static final int MAX_SNAPSHOT_ROWS = 50000
	static final int MAX_LINES_PER_TEST = 50

	// ======== Output files (under <project>/Reports) ========
	static final String LOG_FILE = 'Reports/db_duplicate_check.log'
	static final String CSV_FILE = 'Reports/db_duplicate_attribution.csv'

	// ======== State is stored in a file (static fields are reset between test cases on TestCloud) ========
	static final String STATE_FILE_NAME  = 'katalon_db_dup_state.json'
	static final long   STATE_MAX_AGE_MS = 6L * 60 * 60 * 1000     // ignore a leftover file older than 6 hours

	/** Uses the TestCloud Tunnel forwarded port when present (TUN_<RULE> env var), otherwise connects directly. */
	private String buildUrl() {
		String tunnel = System.getenv(TUNNEL_ENV_VAR)
		String hostPort = tunnel ? tunnel : DB_DIRECT_HOST_PORT
		String url = "jdbc:mysql://${hostPort}/${DB_NAME}?${DB_URL_PARAMS}".toString()
		KeywordUtil.logInfo("DB connection target: ${hostPort} (${tunnel ? 'via TestCloud tunnel' : 'direct'})")
		return url
	}

	/** Connects with up to 3 attempts (network blips on cloud runners); wrong credentials are not retried. */
	private Sql connect() {
		Exception last = null
		for (int attempt = 1; attempt <= 3; attempt++) {
			try {
				return Sql.newInstance(buildUrl(), DB_USER, GlobalVariable.DB_PASSWORD as String, DB_DRIVER)
			} catch (Exception e) {
				last = e
				KeywordUtil.logInfo("DB connect attempt ${attempt}/3 failed: ${e.getClass().simpleName}: ${e.message?.take(300)}" +
						(e.cause ? " | cause: ${e.cause.getClass().simpleName}: ${e.cause.message?.take(300)}" : ''))
				if (e.message?.contains('Access denied')) throw e
				if (attempt < 3) Thread.sleep(3000)
			}
		}
		throw last
	}

	// ------------------------------------------------------------------
	// 1) BEFORE THE SUITE: record the duplicates and patient data that already exist
	// ------------------------------------------------------------------
	@Keyword
	def takeBaseline() {
		Sql sql = null
		try {
			sql = connect()
			Map<String, Map> base = collectDuplicates(sql)
			Map<String, Map> rows = collectRowSnapshot(sql)
			Timestamp t = toTimestamp(sql.firstRow('SELECT NOW() AS now_ts').now_ts)
			saveState(base, t, [], rows)

			new File(RunConfiguration.getProjectDir() + '/' + CSV_FILE).with {
				parentFile.mkdirs()
				text = 'time,test_case,status,issue_type,value,count,customer_ids,details\n'
			}
			String msg = "BASELINE taken: ${base.size()} duplicate group(s) and ${rows.size()} patient row(s) recorded before the suite started (not blamed on any test case)."
			KeywordUtil.logInfo(msg)
			writeLog("\n########## SUITE START ${now()} ##########\n${msg}\n" +
					base.collect { k, v -> "   [pre-existing] ${v.type}: ${v.value} x${v.cnt} ids=${v.ids}" }.join('\n') + '\n')
		} catch (Exception e) {
			KeywordUtil.markWarning("DB baseline failed: ${e.message}")
		} finally {
			sql?.close()
		}
	}

	/** Called before every test case: takes the baseline only if none exists yet. */
	@Keyword
	def ensureBaseline() {
		if (loadState() == null) {
			KeywordUtil.logInfo("No baseline found before test case; taking it now.")
			takeBaseline()
		}
	}

	// ------------------------------------------------------------------
	// 2) AFTER EVERY TEST CASE: new duplicates, new patient rows, and EXISTING patients that were changed
	// ------------------------------------------------------------------
	@Keyword
	def checkNewDuplicates(String testCaseId, String testStatus = '', boolean failOnDuplicate = false) {
		Map state = loadState()
		if (state == null) {
			KeywordUtil.logInfo("No baseline file found. Baseline taken now; comparison starts with the next test case.")
			takeBaseline()
			return false
		}
		Map<String, Map> baseline = state.baseline
		Map<String, Map> prevRows = state.rows
		List summary = state.summary

		Sql sql = null
		Map newDups = [:]
		List newRows = []
		List changed = []
		Map<String, Map> meta = [:]
		try {
			sql = connect()
			Timestamp checkTime = toTimestamp(sql.firstRow('SELECT NOW() AS now_ts').now_ts)

			Map<String, Map> current = collectDuplicates(sql)
			newDups = current.findAll { k, v -> !baseline.containsKey(k) || v.cnt > baseline[k].cnt }

			Map<String, Map> curRows = collectRowSnapshot(sql)
			if (prevRows && curRows) {
				curRows.each { id, cur ->
					Map prev = prevRows[id]
					if (prev == null) {
						newRows << ([id: id] + cur)
					} else {
						List diffs = FIELD_LABELS.findAll { k, label -> prev[k] != cur[k] }
								.collect { k, label -> "${label}: '${prev[k]}' -> '${cur[k]}'".toString() }
						if (diffs) changed << [id: id, diffs: diffs]
					}
				}
			}

			// Who/what updated the changed rows (UPDATE_DATE / UPDATE_BY / UPDATE_PROCESS)
			try {
				List<String> ids = changed.collect { it.id.toString() }.findAll { it ==~ /\d+/ }.take(100)
				if (ids) {
					String qMeta = "SELECT ${COL_ID} AS id, ${COL_UPDATE_DATE} AS ud, ${COL_UPDATE_BY} AS ub, ${COL_UPDATE_PROC} AS up " +
							"FROM ${TABLE} WHERE ${COL_ID} IN (${ids.join(',')})"
					sql.eachRow(qMeta) { r -> meta[r.id.toString()] = [ud: r.ud?.toString(), ub: r.ub?.toString(), up: r.up?.toString()] }
				}
			} catch (Exception e) {
				KeywordUtil.logInfo("Could not read update info: ${e.message}")
			}

			summary << [testCase: testCaseId, status: testStatus, newDupCount: newDups.size(), newRowCount: newRows.size(), changedCount: changed.size()]
			saveState(current, checkTime, summary, curRows)     // next test case is compared against the state after this one
		} catch (Exception e) {
			KeywordUtil.markWarning("DB duplicate check failed to run for ${testCaseId}: ${e.message}")
			return false
		} finally {
			sql?.close()
		}

		StringBuilder report = new StringBuilder()
		report.append("\n===== ${now()} | TC: ${testCaseId} | Status: ${testStatus} =====\n")
		report.append("Patient rows created during this test case: ${newRows.size()}\n")
		newRows.take(MAX_LINES_PER_TEST).each {
			report.append("   [new row] customer_id=${it.id} name=${it.f} ${it.l} dob=${it.d} patient_no=${it.p} login=${it.e}\n")
		}
		report.append("Existing patient rows CHANGED during this test case: ${changed.size()}\n")

		File csv = new File(RunConfiguration.getProjectDir() + '/' + CSV_FILE)
		changed.eachWithIndex { c, i ->
			Map m = meta[c.id.toString()] ?: [:]
			String upd = "UPDATE_DATE=${m.ud} UPDATE_BY=${m.ub} UPDATE_PROCESS=${m.up}"
			if (i < MAX_LINES_PER_TEST) report.append("   >>> CHANGED customer_id=${c.id}: ${c.diffs.join('; ')} | ${upd}\n")
			csv.append([now(), testCaseId, testStatus, 'Existing patient changed', "customer_id ${c.id}", 1, c.id, c.diffs.join('; ') + ' | ' + upd]
					.collect { csvEscape(it) }.join(',') + '\n')
		}
		if (changed.size() > MAX_LINES_PER_TEST) report.append("   ... and ${changed.size() - MAX_LINES_PER_TEST} more changed row(s) (all are in the CSV)\n")

		newDups.each { k, v ->
			report.append("   >>> NEW DUPLICATE: ${v.type} = ${v.value} (x${v.cnt}) customer_ids=${v.ids} names=${v.names}\n")
			csv.append([now(), testCaseId, testStatus, v.type, v.value, v.cnt, v.ids, v.names].collect { csvEscape(it) }.join(',') + '\n')
		}
		report.append(">>> RESULT: ${newDups.size()} new duplicate group(s), ${newRows.size()} new row(s), ${changed.size()} existing row(s) changed\n")

		KeywordUtil.logInfo(report.toString())
		writeLog(report.toString())

		if (newDups) {
			String m = "Test case '${testCaseId}' introduced ${newDups.size()} duplicate patient group(s) in ${TABLE}"
			if (failOnDuplicate) KeywordUtil.markFailed(m) else KeywordUtil.markWarning(m)
		}
		if (changed) {
			KeywordUtil.markWarning("Test case '${testCaseId}' changed ${changed.size()} existing patient row(s) in ${TABLE}")
		}
		return newDups.size() > 0
	}

	// ------------------------------------------------------------------
	// 3) AFTER THE SUITE: one-glance summary
	// ------------------------------------------------------------------
	@Keyword
	def writeSuiteSummary() {
		Map state = loadState()
		List summary = state ? state.summary : []
		StringBuilder sb = new StringBuilder("\n########## SUITE SUMMARY ${now()} ##########\n")
		List dupCulprits = summary.findAll { it.newDupCount > 0 }
		List changeCulprits = summary.findAll { it.changedCount > 0 }
		sb.append("Test cases run: ${summary.size()}\n")
		sb.append("Test cases that introduced duplicates: ${dupCulprits.size()}\n")
		dupCulprits.each { sb.append("   !! ${it.testCase} (${it.status}) -> ${it.newDupCount} new duplicate group(s)\n") }
		sb.append("Test cases that changed existing patients: ${changeCulprits.size()}\n")
		changeCulprits.each { sb.append("   !! ${it.testCase} (${it.status}) -> ${it.changedCount} existing row(s) changed\n") }
		if (dupCulprits.isEmpty() && changeCulprits.isEmpty()) sb.append("   No test case introduced duplicates or changed existing patients.\n")
		sb.append("Rows created per test case: " + summary.collect { "${it.testCase}=${it.newRowCount}" }.join(', ') + '\n')
		KeywordUtil.logInfo(sb.toString())
		writeLog(sb.toString())
		stateFile().delete()
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

	/** Id, name, DOB, patient number and login of every patient, to detect rows that change between test cases. */
	private Map<String, Map> collectRowSnapshot(Sql sql) {
		Map<String, Map> rows = [:]
		String q = "SELECT ${COL_ID} AS id, ${COL_FIRST} AS f, ${COL_LAST} AS l, ${COL_DOB} AS d, " +
				"${COL_PATIENT_NO} AS p, ${COL_LOGIN} AS e FROM ${TABLE}"
		sql.eachRow(q) { r ->
			rows[r.id.toString()] = [f: r.f?.toString(), l: r.l?.toString(), d: r.d?.toString(), p: r.p?.toString(), e: r.e?.toString()]
		}
		if (rows.size() > MAX_SNAPSHOT_ROWS) {
			KeywordUtil.logInfo("Table has ${rows.size()} rows (limit ${MAX_SNAPSHOT_ROWS}); change tracking skipped.")
			return [:]
		}
		return rows
	}

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

	private File stateFile() {
		return new File(System.getProperty('java.io.tmpdir'), STATE_FILE_NAME)
	}

	private void saveState(Map<String, Map> base, Timestamp t, List summary, Map<String, Map> rows) {
		Map state = [baseline: base, lastCheckTime: t.toString(), summary: summary, rows: rows]
		stateFile().text = JsonOutput.toJson(state)
	}

	/** Returns [baseline, lastCheckTime, summary, rows] or null when there is no (fresh) state file. */
	private Map loadState() {
		File f = stateFile()
		if (!f.exists() || (System.currentTimeMillis() - f.lastModified()) > STATE_MAX_AGE_MS) return null
		try {
			def raw = new JsonSlurper().parseText(f.text)
			Map<String, Map> base = [:]
			raw.baseline.each { k, v -> base[k.toString()] = [type: v.type, value: v.value, cnt: (v.cnt as Number).intValue(), ids: v.ids, names: v.names] }
			List summ = raw.summary.collect {
				[testCase: it.testCase, status: it.status, newDupCount: (it.newDupCount as Number).intValue(),
				 newRowCount: (it.newRowCount as Number).intValue(), changedCount: ((it.changedCount ?: 0) as Number).intValue()]
			}
			Map<String, Map> rows = [:]
			raw.rows?.each { k, v -> rows[k.toString()] = [f: v.f, l: v.l, d: v.d, p: v.p, e: v.e] }
			return [baseline: base, lastCheckTime: Timestamp.valueOf(raw.lastCheckTime.toString()), summary: summ, rows: rows]
		} catch (Exception e) {
			KeywordUtil.logInfo("Could not read baseline state file: ${e.message}")
			return null
		}
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