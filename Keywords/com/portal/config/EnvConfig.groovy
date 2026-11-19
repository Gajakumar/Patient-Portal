package com.portal.config

import com.kms.katalon.core.configuration.RunConfiguration

/**
 * Central, value-safe access to credentials and secrets.
 *
 * Lookup order for every name:
 *   1. OS / CI environment variable (System.getenv)  - used by cloud runners
 *   2. Java system property (-DNAME=value)
 *   3. <project root>/.env                            - local development only (never committed or transferred)
 *
 * Values are never logged. A missing required value fails with the variable NAME only.
 * See .env.example for the full list of names.
 */
class EnvConfig {

	private static Map<String, String> dotEnvCache = null

	/** Returns the value or throws a clear error naming the missing variable (never its value). */
	static String get(String name) {
		String v = getOptional(name)
		if (v == null || v.isEmpty()) {
			throw new IllegalStateException("Missing required environment variable '" + name +
				"'. Set it in the OS/CI environment or in the project-root .env file (see .env.example).")
		}
		return v
	}

	/** Returns the value, or defaultValue when it is not configured anywhere. */
	static String getOptional(String name, String defaultValue = null) {
		String v = System.getenv(name)
		if (v == null || v.isEmpty()) {
			v = System.getProperty(name)
		}
		if (v == null || v.isEmpty()) {
			v = loadDotEnv().get(name)
		}
		return (v == null || v.isEmpty()) ? defaultValue : v
	}

	static boolean has(String name) {
		return getOptional(name) != null
	}

	static synchronized Map<String, String> loadDotEnv() {
		if (dotEnvCache != null) {
			return dotEnvCache
		}
		Map<String, String> m = [:]
		File f = locateDotEnv()
		if (f != null) {
			f.eachLine('UTF-8') { String raw ->
				String line = raw.trim()
				if (line.startsWith('\uFEFF')) {
					line = line.substring(1).trim()
				}
				if (!line || line.startsWith('#')) {
					return
				}
				if (line.startsWith('export ')) {
					line = line.substring(7).trim()
				}
				int idx = line.indexOf('=')
				if (idx <= 0) {
					return
				}
				String k = line.substring(0, idx).trim()
				String v = line.substring(idx + 1).trim()
				if (v.length() >= 2 && ((v.startsWith("'") && v.endsWith("'")) || (v.startsWith('"') && v.endsWith('"')))) {
					boolean doubleQuoted = v.startsWith('"')
					v = v.substring(1, v.length() - 1)
					if (doubleQuoted) {
						v = v.replace('\\"', '"').replace('\\\\', '\\')
					}
				}
				m.put(k, v)
			}
		}
		dotEnvCache = m
		return m
	}

	private static File locateDotEnv() {
		List<String> dirs = []
		try {
			String pd = RunConfiguration.getProjectDir()
			if (pd) {
				dirs.add(pd)
			}
		} catch (Throwable ignored) {
			// not running inside Katalon
		}
		dirs.add(System.getProperty('user.dir'))
		for (String d : dirs) {
			if (d) {
				File f = new File(d, '.env')
				if (f.isFile()) {
					return f
				}
			}
		}
		return null
	}
}