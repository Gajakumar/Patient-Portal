package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

class ActivityLogKeywords {

    /* ===============================
       CONSTANTS
       =============================== */
    private static final ZoneId GMT_ZONE = ZoneId.of('GMT')

    private static final DateTimeFormatter UI_FORMATTER =
        DateTimeFormatter.ofPattern(
            'MM/dd/yyyy hh:mm:ss a',
            Locale.US
        )

    /* ===============================
       FETCH LOG DATES FROM UI
       =============================== */
    @Keyword
    List<ZonedDateTime> getLogDates(TestObject dateTimeCells) {

        List<WebElement> rows =
            WebUiCommonHelper.findWebElements(dateTimeCells, 10)

        assert rows.size() > 0 : '❌ No Activity Logs found'

        return rows.collect { WebElement el ->

            String cleanText = el.text
                .replace('\u00A0', ' ')
                .replaceAll('\\s+', ' ')
                .trim()

            LocalDateTime.parse(cleanText, UI_FORMATTER)
                         .atZone(GMT_ZONE)
        }
    }

    /* ===============================
       VERIFY LAST 7 DAYS (GMT)
       =============================== */
    @Keyword
    void verifyLogsInLast7Days(List<ZonedDateTime> logDates) {

        ZonedDateTime nowGMT =
            ZonedDateTime.now(GMT_ZONE)

        ZonedDateTime startDate =
            nowGMT.minusDays(6)
                  .truncatedTo(ChronoUnit.DAYS)

        logDates.each { ZonedDateTime dt ->
            assert !dt.isBefore(startDate) && !dt.isAfter(nowGMT) :
                "❌ Log date NOT in last 7 days (GMT): ${dt}"
        }

        WebUI.comment('✅ All log dates fall within last 7 days (GMT)')
    }

    /* ===============================
       VERIFY SORTING
       =============================== */
    @Keyword
    void verifySortedDates(
        List<ZonedDateTime> actualDates,
        boolean asc,
        String errorMsg
    ) {

        List<ZonedDateTime> expected =
            new ArrayList<>(actualDates)

        expected.sort { a, b ->
            asc ? a <=> b : b <=> a
        }

        assert actualDates == expected : errorMsg

        WebUI.comment(
            "✅ Dates sorted correctly (${asc ? 'ASC' : 'DESC'})"
        )
    }
}
