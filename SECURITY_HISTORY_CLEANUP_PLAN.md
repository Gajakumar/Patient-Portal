# Security: Git history cleanup plan (Patient-Portal) - DRAFT, NOT EXECUTED

Status: prepared 2026-11-19 (IST). **No history rewrite, force-push, commit or other git state change has been made.**
Execute only after explicit owner approval. This file contains NO secret values.

## 1. Exposure summary
- Repo `github.com/Gajakumar/Patient-Portal` is PUBLIC (default branch `main`, remote head `2c5a1f7c89` = local HEAD, 0 ahead/0 behind).
- GitHub API (public, 2026-11-19): forks 0, stars 0, watchers 0, network 0, PRs 0 (all states), tags 0, releases 0, branches: `main` only, Actions workflows 0 / runs 0, no gists.
- 16 secrets exposed in **31 commits on `main`** (all pushed) + 1 upstream Katalon sample commit (`1af7995871`, public default OAuth client, not ours).
- **Same secrets also exist in the owner's other PUBLIC repos** (scan on 2026-11-19, values >= 8 chars only):
  - `Gajakumar/Evaa-Admin-Console`: GMAIL_APP_PASSWORD, PP_UPDATE_PASSWORD, SMTP_PASSWORD, MAXIMEYES_QA8_PASSWORD, KATALON_OAUTH2_CLIENT_SECRET, Katalon email-report password (enc), Katalon TestOps password (enc)
  - `Gajakumar/Evaa-AI-Katalon`: SMTP_PASSWORD, Katalon email-report password (enc)
  - `Gajakumar/calculator-bdd-tests` (fork of Katalon sample): only the public Katalon default OAuth client.
  => Rewriting Patient-Portal alone does NOT end the exposure. Rotation is mandatory regardless.

## 2. Affected commits (Patient-Portal, branch main) - variable names only
| Commit | Date (IST) | Author | Message | Variables |
|---|---|---|---|---|
| 3a6582d6fe | 2026-01-05 | Gajakumar | 1212 | KATALON_EMAIL_REPORT_PASSWORD(enc), KATALON_OAUTH2_CLIENT_SECRET, MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_QA8_PASSWORD, PP_REST_UPDATED_PASSWORD, PP_UPDATE_PASSWORD, SMTP_PASSWORD |
| 2518410104 | 2026-01-06 | Gajakumar | 06 Jan 2026 | KATALON_TESTOPS_PASSWORD(enc) |
| b09add1680 | 2026-01-07 | Gajakumar | 07 Jan 2026 | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| c50ee4d2f2 | 2026-01-08 | Gajakumar | 08 Jan 2026 | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| 3a06283dbe | 2026-01-19 | Gajakumar | 19 Jan 2025 | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| 8e39a43a93 | 2026-01-27 | Gajakumar | 27 Jan 2026 | MAXIMEYES_AUTHORITY_PASSWORD, PP_REST_UPDATED_PASSWORD, PP_UPDATE_PASSWORD |
| e46fbaac49 | 2026-01-28 | Gajakumar | 28 Jan 2026 | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD, PP_CHANGED_PASSWORD, PP_REST_UPDATED_PASSWORD, PP_UPDATE_PASSWORD |
| c1e76d438a | 2026-02-16 | Gajakumar | 16 Feb 2026 | PP_API_AUTH_TOKEN, PP_API_SESSION_GUID |
| c875de8eef | 2026-03-09 | Gajakumar | 9 march 2026 | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| 4751f398fd | 2026-04-09 | Gajakumar | 09 April 2026 | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| 98853acf4c | 2026-04-13 | Gajakumar | 13 april | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| befcb7eeb5 | 2026-04-14 | Gajakumar | 14 April | PP_UPDATE_PASSWORD |
| e3de5081d6 | 2026-06-03 | Gajakumar | 3 june 2026 | MAXIMEYES_AUTHORITY_PASSWORD, PP_UPDATE_PASSWORD |
| 1f7fecedab | 2026-06-08 | Gajakumar | 8 June 2026 | MAXIMEYES_QA8_PASSWORD |
| 41093b7f5f | 2026-07-02 | Gajakumar | 2 July 2026 | PP_UPDATE_PASSWORD |
| 2a1be10595 | 2026-08-13 | Gajakumar | 13 Aug DB Migration PP | DB_PASSWORD_ALT |
| 004ffa4ac1 | 2026-09-01 | Gajakumar | 1 sept | PP_UPDATE_PASSWORD |
| 752a2c89bc | 2026-09-07 | Gajakumar | 7 sept | MAXIMEYES_PASSWORD |
| 46aaa68249 | 2026-09-23 | Gajakumar | 23 Sept | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| 3a98f483eb | 2026-09-25 | Gajakumar | 09/25 | PP_UPDATE_PASSWORD |
| 97b90cd3a8 | 2026-09-28 | Gajakumar | 09/28 | PP_UPDATE_PASSWORD |
| bb1fde2f6f | 2026-09-30 | Gajakumar | 09/30 | PP_UPDATE_PASSWORD |
| 6cfc807b10 | 2026-10-19 | Gajakumar | 19 oct | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| b3d2c92b7f | 2026-10-21 | Gajakumar | 21 Oct - New Automation Url | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD, PP_UPDATE_PASSWORD |
| 1f09f38dc8 | 2026-10-24 | Gajakumar | 24 oct | MAXIMEYES_AUTHORITY_PASSWORD, PP_UPDATE_PASSWORD |
| 5266498e4c | 2026-10-25 | Gajakumar | 25 Oct | MAXIMEYES_AUTHORITY_PASSWORD, PP_UPDATE_PASSWORD |
| 7c82f0a4f7 | 2026-10-27 | Gajakumar | 27 Oct | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| 479d989863 | 2026-10-30 | Gajakumar | 30 Oct | DB_PASSWORD |
| 8252d49d7d | 2026-11-16 | Gajakumar | 16 nov | GMAIL_APP_PASSWORD, PP_UPDATE_PASSWORD |
| 2c5a1f7c89 | 2026-11-17 | Gajakumar | 17 nov (current HEAD) | MAXIMEYES_AUTHORITY_PASSWORD, MAXIMEYES_SIGNATURE_PASSWORD |
| 1af7995871 | 2020-07-21 | Anh Le (upstream Katalon sample) | update | KATALON_OAUTH2_CLIENT_SECRET (public Katalon default) |

Method: `git log --all -S<value>` per secret (commits where the occurrence count changed). Every later commit up to HEAD still *contains* the values in its tree until the working-tree fixes are committed. The 5-6 digit signature/authority values were matched only in Scripts/Profiles/Keywords/settings/Test Cases/Suites; some Keyword hits are coincidental substrings of phone numbers.

### Affected files (any commit)
- Profiles/default.glbl (MAXIMEYES_PASSWORD, GMAIL_APP_PASSWORD, PP_UPDATE/CHANGED/REST_UPDATED_PASSWORD, DB_PASSWORD, DB_PASSWORD_ALT, MAXIMEYES_QA8/SIGNATURE/AUTHORITY_PASSWORD in older versions)
- Test Listeners/EmailListener.groovy (SMTP_PASSWORD)
- Include/scripts/groovy/com/katalon/KatalonHelper.groovy (KATALON_OAUTH2_CLIENT_SECRET)
- Object Repository/GetHealthSummaryDetail.rs (PP_API_AUTH_TOKEN, PP_API_SESSION_GUID)
- Object Repository/Forgot Username and Password/Page_Patient Portal/input_Reset Your Password_form-control ps-5_{2c0f9a_1,2c0f9a_2,f51da5_8,f51da5_9,f51da5_10,f51da5_11}.rs
- settings/internal/com.kms.katalon.execution.properties (Katalon email-report password, encrypted)
- settings/internal/com.kms.katalon.integration.analytics.properties (Katalon TestOps password, encrypted)
- Scripts: common/Maximeyes/UserLogin, Demo_Maximeyes/{DemoTest, PtAutoComplete, PtPhotoUpload, ePrescribe2}, New Test Case, PatientPortal/{TCFor_Forgot_Password_Patient_Portal*, TCFor_Health_Summary_Enc_Sign_Off*, TCFor_Upload_Education_Material_on_Enc_SignOff}, Updated Patient Portal/{Appointments/New Test Case*, Appointments/TCFor_Create_New_Provider_User, General TCs/MIPSKno2, Intake/AutoLogin, Provider Portal/New Test Case, Provider Portal/TC_PP_Messages_Verify_Search_Functionality_Across_Folders, TCFor_Forgot_Password_Patient_Portal, TCFor_Health_Summary_Enc_Sign_Off, TCFor_Upload_Education_Material_on_Encounter_Sign_Off}, common/Patient_Portal_Common/Create Random Patient in Maximeyes  (* = deleted from HEAD, still in history)
- NOTE: Scripts/Demo_Maximeyes/DemoTest/Script1765547222837.groovy is still in HEAD; it no longer contains the values in the working tree.
- Reports/, bin/, .cache/: Reports touched by 2 commits but no secret values found in tracked history; `.env` never committed.

## 3. Order of operations (do not skip step A)
A. **Rotate every secret first** (section 6). History rewriting does not un-expose anything already public.
B. Containment (optional, immediate): make Patient-Portal, Evaa-Admin-Console and Evaa-AI-Katalon private (Settings > General > Danger Zone > Change visibility). Reversible; stops new anonymous access.
C. Back up the local project (incl. 5 pre-existing unsaved edits) - section 4.1.
D. Rewrite history in a fresh mirror clone (section 4.2), verify, force-push.
E. Re-point the local working folder without touching working files (section 4.3).
F. GitHub-side purge (section 5). Repeat for Evaa-Admin-Console and Evaa-AI-Katalon.

## 4. Commands (Windows PowerShell; git = GitHub Desktop's git or Git for Windows)

### 4.0 Tools
    py -m pip install --user git-filter-repo        # Python 3.14 is installed at C:\Python314
    # alternative: BFG Repo-Cleaner (java -jar bfg.jar --replace-text ...), Java is installed

### 4.1 Preserve current working tree (incl. 5 pre-existing unsaved edits, 22 sanitized files, new files, .env)
    $P = "C:\Users\Gajakumar_a\Katalon Studio\Patient-Portal"
    $B = "C:\Users\Gajakumar_a\Katalon Studio\_pre-rewrite-backup-$(Get-Date -f yyyyMMdd_HHmm)"   # NOT inside "Back Up"
    robocopy $P $B /E /COPY:DAT /R:1 /W:1          # full copy incl. .git and .env - local only, delete after verification
    cd $P; git status --porcelain > "$B\status-before.txt"; git diff --stat > "$B\diffstat-before.txt"
    # Do NOT export `git diff` patches: the '-' lines would contain the old secret values.

### 4.2 Rewrite in a fresh mirror clone
    $W = "C:\Users\Gajakumar_a\pp-rewrite"; $S = "C:\Users\Gajakumar_a\pp-secure"   # both OUTSIDE the repo
    New-Item -ItemType Directory -Force $W,$S | Out-Null
    git clone --mirror https://github.com/Gajakumar/Patient-Portal.git "$W\Patient-Portal.git"

    # Build the replacements file from local values WITHOUT printing them (run from $P):
    $lines = @()
    Get-Content "$P\.env" | Where-Object { $_ -match '^[A-Z0-9_]+(PASSWORD|SECRET|TOKEN|GUID)=' } | ForEach-Object {
      $k,$v = $_ -split '=',2; if ($v -match "^'(.*)'$") { $v = $Matches[1] }
      if ($v.Length -ge 8) { $lines += "$v==>REMOVED-$k" }
      else { $e=[regex]::Escape($v); $lines += "regex:(['""])$e(['""])==>\1REMOVED-$k\2" }   # short numeric values: quoted occurrences only (avoid phone numbers)
    }
    $ep = (Get-Content "$P\settings\internal\com.kms.katalon.execution.properties" | ? { $_ -match '^mailConfig\.password=' }) -replace '^mailConfig\.password=',''
    $ap = (Get-Content "$P\settings\internal\com.kms.katalon.integration.analytics.properties" | ? { $_ -match '^onpremise\.password=' }) -replace '^onpremise\.password=',''
    $lines += "$ep==>REMOVED-KATALON_EMAIL_REPORT_PASSWORD"; $lines += "$ap==>REMOVED-KATALON_TESTOPS_PASSWORD"
    [IO.File]::WriteAllLines("$S\replacements.txt", $lines)
    # (KATALON_OAUTH2_CLIENT_SECRET is the public Katalon default; replacing it is optional - drop that line to keep upstream history identical.)

    cd "$W\Patient-Portal.git"
    git filter-repo --replace-text "$S\replacements.txt" --replace-message "$S\replacements.txt"
    # Optional extra: also strip messages/paths, e.g. --invert-paths --path Reports/  (Reports has no secrets; not required)

    # Verify (prints counts only):
    Get-Content "$S\replacements.txt" | % { ($_ -split '==>')[0] } | % { if ($_ -notlike 'regex:*') { $n = (git log --all --oneline "-S$_" | Measure-Object).Count; "remaining commits: $n" } }
    git log --oneline | Select-Object -First 5

    # Push (requires approval; temporarily disable branch protection / "block force pushes" if enabled):
    git remote add origin https://github.com/Gajakumar/Patient-Portal.git   # filter-repo removes origin
    git push --force --mirror origin

### 4.3 Re-point the local working folder (working files are NOT modified)
    cd $P
    git fetch origin
    git reset --mixed origin/main         # moves main + index to rewritten history; working tree untouched
    git status --porcelain > "$B\status-after.txt"; Compare-Object (gc "$B\status-before.txt") (gc "$B\status-after.txt")
    # Expected: same 27 modified + 4 untracked entries (5 pre-existing edits + 22 sanitized files + config/new files).
    # Then purge old objects locally:
    git remote remove upstream            # optional: upstream Katalon sample refs are unrelated
    git reflog expire --expire=now --all; git gc --prune=now --aggressive
    # Finally delete $B, $W and $S (they contain the old values) once verified:
    Remove-Item $B,$W,$S -Recurse -Force

Alternative (Git Bash, BFG):
    git clone --mirror https://github.com/Gajakumar/Patient-Portal.git
    java -jar bfg.jar --replace-text /c/Users/Gajakumar_a/pp-secure/replacements.txt Patient-Portal.git
    cd Patient-Portal.git && git reflog expire --expire=now --all && git gc --prune=now --aggressive && git push --force
    (BFG protects the HEAD commit by default - commit the sanitized working tree first, or use --no-blob-protection.)

## 5. Impact of rewriting
- Every commit hash from 3a6582d6fe (2026-01-05) onward changes (~ all 245 own commits). Old hashes in notes, tickets, Katalon TestOps/CI links break.
- Anyone with a clone must re-clone (or `git fetch && git reset --hard origin/main` after saving work); pushing from an old clone re-introduces the secrets.
- Forks: none today (verified via API). Forks created before the rewrite would keep old history.
- GitHub keeps old commits reachable by SHA (and in cached views / search / activity feed) until garbage-collected. Open a request with GitHub Support ("remove cached views / sensitive data") after the force-push, listing the old SHAs above. No PRs exist, so no refs/pull/* to purge.
- Alternative that guarantees GitHub-side purge: delete the repo and re-create it, pushing the rewritten mirror (loses stars/issues/settings - currently 0/0).
- Secret scanning: check Security > Secret scanning alerts on each repo (owner login needed); close alerts as "revoked" after rotation.
- Same steps must be repeated for Gajakumar/Evaa-Admin-Console and Gajakumar/Evaa-AI-Katalon.

## 6. Rotation checklist (do BEFORE rewriting)
| Variable | Where to rotate |
|---|---|
| MAXIMEYES_PASSWORD (+ review MAXIMEYES_USERNAME) | MaximEyes QA/automation tenant - user admin |
| MAXIMEYES_QA8_PASSWORD | qa8.maximeyes.com account |
| MAXIMEYES_SIGNATURE_PASSWORD | MaximEyes user e-signature password (use a non-trivial value) |
| MAXIMEYES_AUTHORITY_PASSWORD | MaximEyes practice/admin "authority password" setting |
| GMAIL_APP_PASSWORD (GMAIL_ADDRESS) | Google Account > Security > App passwords: revoke + recreate; review recent activity/forwarding rules |
| PP_UPDATE_PASSWORD, PP_CHANGED_PASSWORD, PP_REST_UPDATED_PASSWORD | Patient Portal QA (ptportal / ptportalapiqacert) test accounts - reset passwords |
| PP_API_AUTH_TOKEN, PP_API_SESSION_GUID | Patient Portal API: revoke session / invalidate token; ask API team whether JWT signing key rotation is needed |
| DB_USERNAME / DB_PASSWORD / DB_PASSWORD_ALT | QA MySQL (host in GlobalVariable DB_URL) - DBA rotation, review access logs, restrict network access |
| SMTP_USERNAME / SMTP_PASSWORD | Mail account used by EmailListener - revoke app password / change password |
| Katalon email-report password (settings execution.properties) | Same mailbox as Katalon Project Settings > Email; rotate and re-enter (Katalon encryption is reversible - treat as plaintext) |
| Katalon TestOps password (analytics.properties) | Katalon TestOps account (onpremise.email) - change password, revoke API keys, enable 2FA |
| KATALON_OAUTH2_CLIENT_ID/SECRET | Public Katalon default client - cannot/need not rotate |
After rotation: update the local `.env` and the cloud runner's environment variables.