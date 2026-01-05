package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ObjectRepository
import internal.GlobalVariable

class CaptureCredentials {

    @Keyword
    def captureAndStoreCredentials(String objectRepoPath) {

        // ✅ Correct way to get TestObject inside Custom Keyword
        TestObject obj = ObjectRepository.findTestObject(objectRepoPath)

        String fullText = WebUI.getText(obj)

        // Extract URL
        String url = (fullText =~ /(https?:\/\/\S+)/)[0][0]

        // Extract Username
        String username = (fullText =~ /Username:\s*(\S+)/)[0][1]

        // Extract Password
        String password = (fullText =~ /Password:\s*(\S+)/)[0][1]

        // Store in GlobalVariables
        GlobalVariable.PortalURL = url
        GlobalVariable.RestUserName  = username
        GlobalVariable.RestPassword  = password

        WebUI.comment("URL captured: " + url)
        WebUI.comment("Username captured: " + username)
        WebUI.comment("Password captured successfully " + password)
    }
}
