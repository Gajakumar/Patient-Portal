package common

import com.kms.katalon.core.annotation.Keyword
import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent

class RobotUploadHelper {

    @Keyword
    static void uploadFileUsingRobot(String absoluteFilePath) {

        assert absoluteFilePath != null && absoluteFilePath.trim() != '' :
                "❌ File path is null or empty"

        File file = new File(absoluteFilePath)
        assert file.exists() : "❌ File not found: ${absoluteFilePath}"

        Robot robot = new Robot()
        robot.setAutoDelay(500)

        // Copy file path to clipboard
        StringSelection selection = new StringSelection(file.absolutePath)
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null)

        // CTRL + V
        robot.keyPress(KeyEvent.VK_CONTROL)
        robot.keyPress(KeyEvent.VK_V)
        robot.keyRelease(KeyEvent.VK_V)
        robot.keyRelease(KeyEvent.VK_CONTROL)

        // ENTER
        robot.keyPress(KeyEvent.VK_ENTER)
        robot.keyRelease(KeyEvent.VK_ENTER)

        println "✅ File uploaded using Robot: ${file.name}"
    }
}
