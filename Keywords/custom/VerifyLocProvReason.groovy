package custom

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory

/**
 * Put in: Keywords/custom/VerifyLocProvReason.groovy
 *
 * Compares portal dropdown options (Location / Provider / Reason)
 * against the MaximEyes Office Admin grids (DevExtreme dxDataGrid).
 *
 * Speed: each grid is read with ONE JavaScript call (not hundreds of
 * Selenium element calls), and the grid is read once per verify().
 * If a row is not in the DOM, the grid search box is used as a fallback.
 */
class VerifyLocProvReason {

    private static final Map<String, String> GRID_IDS = [
        LOCATION: 'pracLocGridContainer',
        PROVIDER: 'providerGridContainer',
        REASON  : 'AptReasonGridContainer'
    ]

    private static final List<String> failures = []

    // Reads every rendered data row in one round trip.
    // ID = first number inside the first onclick="..." of the row, read from the
    // row's outerHTML so odd markup (e.g. the stray  "=""  in the provider grid) cannot break it.
    //   Location : DeletePracticeLocation(this, 13)
    //   Provider : ISAuthorizedToDelete(22)
    //   Reason   : copyData(3)
    private static final String READ_ROWS_JS = '''
        var grid = document.getElementById(arguments[0]);
        if (!grid) return [];
        var out = [];
        var rows = grid.querySelectorAll('tr.dx-data-row');
        for (var i = 0; i < rows.length; i++) {
            var tr = rows[i];
            var cells = [];
            for (var j = 0; j < tr.children.length; j++) cells.push(tr.children[j].textContent);
            var m = tr.outerHTML.match(/onclick="[^"]*?(\\d+)/);
            out.push({id: m ? m[1] : null, cells: cells});
        }
        return out;
    '''

    // ------------------------------------------------------------------
    // PUBLIC KEYWORDS
    // ------------------------------------------------------------------

    @Keyword
    static void reset() {
        failures.clear()
    }

    /** PORTAL page: read all real options of a <select> as [[id:'3', text:'...'], ...] */
    @Keyword
    static List<Map> readPortalDropdown(String selectId) {
        WebDriver d = DriverFactory.getWebDriver()
        List<Map> items = d.findElements(By.xpath("//select[@id='${selectId}']/option[@value!='']")).collect { WebElement o ->
            [id: o.getAttribute('value').trim(), text: clean(o.getAttribute('textContent'))]
        }
        if (items.isEmpty()) {
            KeywordUtil.markFailed("No options found in #${selectId} (not loaded yet?)")
        }
        KeywordUtil.logInfo("Portal #${selectId}: ${items.size()} option(s) -> ${items}")
        return items
    }

    /**
     * MAXIMEYES page (the relevant tab must already be open).
     * type = 'LOCATION' | 'PROVIDER' | 'REASON'
     */
    @Keyword
    static void verify(String type, List<Map> portalItems) {
        type = type.toUpperCase()
        String gridId = GRID_IDS[type]
        if (!gridId) {
            throw new IllegalArgumentException("Unknown type '${type}'. Use LOCATION, PROVIDER or REASON")
        }

        waitForGrid(gridId)

        List<Map> rows = readRows(gridId)          // read ONCE
        if (rows.every { it.id == null }) {
            fail("${type}: could not read IDs from grid #${gridId}. First row cells: ${rows ? rows[0].cells : 'none'}")
            return
        }
        KeywordUtil.logInfo("${type}: ${rows.size()} row(s) rendered in MaximEyes")

        portalItems.each { Map p ->
            String label = "${type} id=${p.id} '${p.text}'"

            Map row = rows.find { it.id == p.id }
            boolean viaSearch = false

            // not rendered on the current page -> use the grid search box
            if (row == null) {
                row = searchAndFind(gridId, p.id, searchTerm(type, p.text))
                viaSearch = (row != null)
            }

            if (row == null) {
                fail("${label} NOT FOUND in MaximEyes (grid + search box). IDs seen in grid: ${rows.collect { it.id }}")
                return
            }

            String problem = compare(type, p.text, row.cells)
            if (problem) {
                fail("${label} MISMATCH: ${problem}")
            } else {
                KeywordUtil.logInfo("[OK] ${label} found${viaSearch ? ' (via search box)' : ''}")
            }
        }

        // info only: rows in MaximEyes that the portal does not offer
        Set<String> portalIds = portalItems.collect { it.id } as Set
        List extra = rows.findAll { !(it.id in portalIds) }
        KeywordUtil.logInfo("${type}: in MaximEyes but NOT in portal dropdown (${extra.size()}): " +
                extra.collect { "${it.id}:${it.cells[0]}" })
    }

    /** Call at the very end of the test case */
    @Keyword
    static void assertNoFailures() {
        if (failures.isEmpty()) {
            KeywordUtil.markPassed("All portal values exist in MaximEyes")
        } else {
            String msg = "Portal vs MaximEyes differences (${failures.size()}):\n - " + failures.join('\n - ')
            failures.clear()
            KeywordUtil.markFailed(msg)
        }
    }

    // ------------------------------------------------------------------
    // PER-TYPE RULES
    // ------------------------------------------------------------------
    // Grid cell indexes (0-based):
    //   LOCATION : 0 Location Name | 1 Business | 2 City | 3 Phone | 4 Is Active
    //   PROVIDER : 0 Last Name | 1 First Name | 2 Credentials | 3 Speciality | 4 Locations
    //   REASON   : 0 Name | 1 Description | 2 Default Status | 3 Colour | 4 Is Active | 5 New Pt

    private static String searchTerm(String type, String portalText) {
        switch (type) {
            case 'LOCATION':
                int i = portalText.lastIndexOf(' - ')
                return i >= 0 ? portalText.substring(0, i).trim() : portalText
            case 'PROVIDER':
                // search matches one column at a time, so use only the last word ("Davis")
                return portalText.tokenize(' ')[-1]
            default:
                return portalText
        }
    }

    /** returns null when OK, otherwise a description of the difference */
    private static String compare(String type, String portalText, List<String> c) {
        switch (type) {
            case 'LOCATION':
                // portal format: "Location Name - City, ST"
                int idx = portalText.lastIndexOf(' - ')
                String locName = idx >= 0 ? portalText.substring(0, idx) : portalText
                String locCity = idx >= 0 ? portalText.substring(idx + 3).split(',')[0].trim() : ''
                if (!eq(locName, c[0])) return "name portal='${locName}' vs MaximEyes='${c[0]}'"
                if (locCity && !eq(locCity, c[2])) return "city portal='${locCity}' vs MaximEyes='${c[2]}'"
                if (!eq(c[4], 'Yes')) return "location is Inactive in MaximEyes"
                return null

            case 'PROVIDER':
                // grid shows Last (cell 0) then First (cell 1); portal shows "First Last"
                String full = "${c[1]} ${c[0]}".toString()
                return eq(portalText, full) ? null : "portal='${portalText}' vs MaximEyes='${full}'"

            case 'REASON':
                // portal shows the DESCRIPTION (falls back to Name when description is blank)
                String expected = c[1] ? c[1] : c[0]
                return eq(portalText, expected) ? null : "portal='${portalText}' vs MaximEyes description='${c[1]}' (name='${c[0]}')"
        }
        return "unsupported type"
    }

    // ------------------------------------------------------------------
    // GRID HELPERS
    // ------------------------------------------------------------------

    /** All rendered data rows: [[id:'22', cells:['Davis','Albert',...]], ...] */
    private static List<Map> readRows(String gridId) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getWebDriver()
        List raw = (List) js.executeScript(READ_ROWS_JS, gridId)
        return raw.collect { Map r ->
            [id   : (r.id == null ? null : r.id.toString()),
             cells: ((List) r.cells).collect { clean(it as String) }]
        }
    }

    /** types term in the grid search box, waits for the row with that id, then clears the search */
    private static Map searchAndFind(String gridId, String id, String term) {
        if (searchBox(gridId) == null) {
            KeywordUtil.markWarning("Grid ${gridId} has no search box")
            return null
        }
        setSearch(gridId, term)
        Map found = null
        for (int i = 0; i < 20 && found == null; i++) {
            found = readRows(gridId).find { it.id == id }
            if (found == null) Thread.sleep(300)
        }
        setSearch(gridId, '')
        return found
    }

    private static WebElement searchBox(String gridId) {
        List<WebElement> l = DriverFactory.getWebDriver().findElements(
                By.xpath("//div[@id='${gridId}']//div[contains(@class,'dx-datagrid-search-panel')]//input"))
        return l.isEmpty() ? null : l[0]
    }

    private static void setSearch(String gridId, String text) {
        WebElement box = searchBox(gridId)
        box.sendKeys(Keys.chord(Keys.CONTROL, 'a'), Keys.BACK_SPACE)
        if (text) box.sendKeys(text)
        Thread.sleep(700)   // DevExtreme debounces search input
    }

    private static void waitForGrid(String gridId) {
        for (int i = 0; i < 50; i++) {
            if (!readRows(gridId).isEmpty()) return
            Thread.sleep(300)
        }
        KeywordUtil.markFailed("Grid #${gridId} did not load any rows - is the correct MaximEyes tab open?")
    }

    // ------------------------------------------------------------------
    // SMALL UTILS
    // ------------------------------------------------------------------
    private static void fail(String msg) {
        failures << msg
        KeywordUtil.markWarning("[FAIL] " + msg)
    }

    private static String clean(String s) {
        return s == null ? '' : s.replace('\u00A0', ' ').replaceAll(/\s+/, ' ').trim()
    }

    private static boolean eq(String a, String b) {
        return clean(a).equalsIgnoreCase(clean(b))
    }
}