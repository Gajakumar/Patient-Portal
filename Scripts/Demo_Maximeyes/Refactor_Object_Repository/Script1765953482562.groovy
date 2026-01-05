import java.nio.file.*
import groovy.xml.*
import com.kms.katalon.core.configuration.RunConfiguration

def repoPath = Paths.get(RunConfiguration.getProjectDir(), "Object Repository")

Files.walk(repoPath).each { path ->
    if (path.toString().endsWith(".rs")) {

        def xml = new XmlParser().parse(path.toFile())
        def selector = xml.selectorCollection?.entry?.value?.text()?.toLowerCase()

        String prefix = "obj_"

        if (selector?.contains("button")) prefix = "btn_"
        else if (selector?.contains("input")) prefix = "txt_"
        else if (selector?.contains("checkbox")) prefix = "chk_"
        else if (selector?.contains("radio")) prefix = "rdo_"
        else if (selector?.contains("a")) prefix = "lnk_"
        else if (selector?.contains("select")) prefix = "ddl_"
        else if (selector?.contains("toast")) prefix = "toast_"
        else if (selector?.contains("modal") || selector?.contains("popup")) prefix = "popup_"

        def newName = prefix + path.fileName.toString()
                                .replace(".rs","")
                                .replaceAll("[^a-zA-Z0-9]", "_")

        def newPath = path.parent.resolve(newName + ".rs")

        // DRY RUN (log only)
        println "RENAME: ${path.fileName} → ${newName}.rs"

        // UNCOMMENT AFTER REVIEW
        // Files.move(path, newPath)
    }
}
