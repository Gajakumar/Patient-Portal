package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement

import java.time.*
import java.time.format.*
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

/* ===============================
   VERIFY LAST 7 DAYS (GMT)
   =============================== */
@Keyword
void verifyLogsInLast7Days(List<ZonedDateTime> logDates) {

    ZonedDateTime nowGMT = ZonedDateTime.now(GMT_ZONE)
    ZonedDateTime startDate = nowGMT.minusDays(6).truncatedTo(ChronoUnit.DAYS)

    logDates.each { ZonedDateTime dt ->
        assert !dt.isBefore(startDate) && !dt.isAfter(nowGMT) :
            "❌ Log date NOT in last 7 days (GMT): ${dt}"
    }

    WebUI.comment("✅ All log dates fall within last 7 days (GMT)")
}