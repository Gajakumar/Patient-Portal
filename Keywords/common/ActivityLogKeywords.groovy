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

class ActivityLogKeywords {

    /* ===============================
       CONSTANTS (SAFE STATIC SCOPE)
       =============================== */
    private static final ZoneId GMT_ZONE = ZoneId.of("GMT")

    private static final DateTimeFormatter DATE_FORMATTER =
        new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("MM/dd/yyyy hh:mm:ss a")
            .toFormatter(Locale.US)

    /* ===============================
       PRIVATE HELPERS
       =============================== */
    private static String cleanText(String raw) {
        return raw.replace('\u00A0', ' ')
                  .replaceAll('\\s+', ' ')
                  .trim()
    }

    /* ===============================
       FETCH LOG DATES
       =============================== */
    @Keyword
    List<ZonedDateTime> getLogDates(TestObject dateTimeCells) {

        List<WebElement> rows =
            WebUiCommonHelper.findWebElements(dateTimeCells, 10)
                .findAll { it.isDisplayed() }

        assert rows && rows.size() > 0 :
            "❌ No Activity Log rows found"

        return rows.collect { WebElement el ->
            LocalDateTime
                .parse(cleanText(el.text), DATE_FORMATTER)
                .atZone(GMT_ZONE)
        }
    }

    /* ===============================
       VERIFY CURRENT WEEK (GMT)
       =============================== */
    @Keyword
    void verifyLogsInCurrentWeek(List<ZonedDateTime> logDates) {

        ZonedDateTime nowGMT = ZonedDateTime.now(GMT_ZONE)

        ZonedDateTime weekStart =
            nowGMT.with(
                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
            ).truncatedTo(ChronoUnit.DAYS)

        ZonedDateTime weekEnd =
            weekStart.plusDays(6).with(LocalTime.MAX)

        logDates.each { ZonedDateTime dt ->
            assert !dt.isBefore(weekStart) && !dt.isAfter(weekEnd) :
                "❌ Log date NOT in current week (GMT): ${dt}"
        }

        WebUI.comment("✅ All log dates fall within current week (GMT)")
    }

    /* ===============================
       VERIFY SORT ORDER
       =============================== */
    @Keyword
    void verifySortedDates(
        List<ZonedDateTime> actualDates,
        boolean ascending,
        String failureMessage
    ) {
        List<ZonedDateTime> expected =
            new ArrayList<>(actualDates)

        expected.sort { a, b ->
            ascending ? a <=> b : b <=> a
        }

        assert actualDates == expected : failureMessage

        WebUI.comment(
            "✅ Dates sorted correctly (${ascending ? 'ASC' : 'DESC'})"
        )
    }
}
