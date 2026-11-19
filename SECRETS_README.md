# Secrets & transfer exclusions

Credentials are NOT stored in this project's source. They are read at runtime by
`Keywords/com/portal/config/EnvConfig.groovy` (OS env -> `-D` system property -> project-root `.env`)
and copied into the matching GlobalVariables by `Test Listeners/EnvSecretsListener.groovy`.

## Local runs
1. Copy `.env.example` to `.env` in the project root and fill in real values.
2. `.env` is git-ignored and marked `export-ignore` (excluded from `git archive` / GitHub zip downloads).

## NEVER include these when zipping, exporting, uploading or copying the project to any cloud machine
- `.env` (and any `.env.*` except `.env.example`)
- `Reports/`, `bin/`, `.cache/`, `Screenshots/` (execution logs/compiled classes can contain values typed during runs)
- `settings/internal/com.kms.katalon.execution.properties` and
  `settings/internal/com.kms.katalon.integration.analytics.properties` contain Katalon-encrypted
  passwords (email report / TestOps) managed by Katalon Studio itself - see manual-review notes.

Safe zip options:
- `git archive --format=zip -o ..\Patient-Portal.zip HEAD` (tracked files only; .env is ignored + export-ignore)
- PowerShell: collect files with
  `Get-ChildItem -Recurse -File | Where-Object { $_.FullName -notmatch '\\(\.git|Reports|bin|\.cache|Screenshots)\\' -and ($_.Name -notmatch '^\.env' -or $_.Name -eq '.env.example') }`
  and pass them to `Compress-Archive`.

## Cloud / CI execution
Configure every variable listed in `.env.example` as an environment variable (secret store) on the runner.