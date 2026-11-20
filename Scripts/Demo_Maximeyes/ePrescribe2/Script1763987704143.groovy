import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import org.openqa.selenium.WebElement as WebElement

//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://qa-autostable7.maximeyes.com/Account/Login')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838 (1)'), 
//    'QA_User')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2024')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (1)'), 'test')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_LastName (1)'), 'enc')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID (1)'), 
//    '76', true)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_EM Code Checker_ePrescribe_102724 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.delay(10)
//
////WebUI.click(findTestObject('Object Repository/Page_MaximEyes/AddMedePrescribe'))
//WebElement rcopiaObj = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)'), 
//    20)
//
//WebUI.executeJavaScript('arguments[0].contentDocument.body.click();', Arrays.asList(rcopiaObj))
//
//TestObject rcopiaObjTO = findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)')
//
//rcopiaObj = WebUiCommonHelper.findWebElement(rcopiaObjTO, 20)
//
//String js = '\nvar obj = arguments[0];\nvar doc = obj.contentDocument || obj.contentWindow.document;\nvar buttons = doc.querySelectorAll(\'button\');\nfor (var b of buttons) {\n   if (b.textContent.includes(\'Add Medication\')) {\n      b.click();\n      return true;\n   }\n}\nreturn false;\n'
//
//WebUI.executeJavaScript(js, Arrays.asList(rcopiaObj))
//
//WebUI.delay(10)
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://qa-autostable7.maximeyes.com/Account/Login')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838 (1)'), 
//    'QA_User')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2024')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (1)'), 'test')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_LastName (1)'), 'enc')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID (1)'), 
//    '76', true)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_EM Code Checker_ePrescribe_102724 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.delay(10)
//
////WebUI.click(findTestObject('Object Repository/Page_MaximEyes/AddMedePrescribe'))
//WebElement rcopiaObj = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)'), 
//    20)
//
//WebUI.executeJavaScript('arguments[0].contentDocument.body.click();', Arrays.asList(rcopiaObj))
//
//TestObject rcopiaObjTO = findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)')
//
//rcopiaObj = WebUiCommonHelper.findWebElement(rcopiaObjTO, 20)
//
//String js = '\nvar obj = arguments[0];\nvar doc = obj.contentDocument || obj.contentWindow.document;\nvar buttons = doc.querySelectorAll(\'button\');\nfor (var b of buttons) {\n   if (b.textContent.includes(\'Add Medication\')) {\n      b.click();\n      return true;\n   }\n}\nreturn false;\n'
//
//WebUI.executeJavaScript(js, Arrays.asList(rcopiaObj))
//
//WebUI.delay(10)
//
//WebUI.acceptAlert()
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://qa-autostable7.maximeyes.com/Account/Login')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838 (1)'), 
//    'QA_User')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2024')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (1)'), 'test')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_LastName (1)'), 'enc')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID (1)'), 
//    '76', true)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_EM Code Checker_ePrescribe_102724 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.delay(10)
//
////WebUI.click(findTestObject('Object Repository/Page_MaximEyes/AddMedePrescribe'))
//WebElement rcopiaObj = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)'), 
//    20)
//
//WebUI.executeJavaScript('arguments[0].contentDocument.body.click();', Arrays.asList(rcopiaObj))
//
//TestObject rcopiaObjTO = findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)')
//
//rcopiaObj = WebUiCommonHelper.findWebElement(rcopiaObjTO, 20)
//
//String js = '\nvar obj = arguments[0];\nvar doc = obj.contentDocument || obj.contentWindow.document;\nvar buttons = doc.querySelectorAll(\'button\');\nfor (var b of buttons) {\n   if (b.textContent.includes(\'Add Medication\')) {\n      b.click();\n      return true;\n   }\n}\nreturn false;\n'
//
//WebUI.executeJavaScript(js, Arrays.asList(rcopiaObj))
//
//WebUI.delay(10)
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://qa-autostable7.maximeyes.com/Account/Login')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838 (1)'), 
//    'QA_User')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2024')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (1)'), 'test')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_LastName (1)'), 'enc')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID (1)'), 
//    '76', true)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_EM Code Checker_ePrescribe_102724 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.delay(10)
//
////WebUI.click(findTestObject('Object Repository/Page_MaximEyes/AddMedePrescribe'))
//WebElement rcopiaObj = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)'), 
//    20)
//
//WebUI.executeJavaScript('arguments[0].contentDocument.body.click();', Arrays.asList(rcopiaObj))
//
//TestObject rcopiaObjTO = findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)')
//
//rcopiaObj = WebUiCommonHelper.findWebElement(rcopiaObjTO, 20)
//
//String js = '\nvar obj = arguments[0];\nvar doc = obj.contentDocument || obj.contentWindow.document;\nvar buttons = doc.querySelectorAll(\'button\');\nfor (var b of buttons) {\n   if (b.textContent.includes(\'Add Medication\')) {\n      b.click();\n      return true;\n   }\n}\nreturn false;\n'
//
//WebUI.executeJavaScript(js, Arrays.asList(rcopiaObj))
//
//WebUI.delay(10)
//
//WebUI.acceptAlert()
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://ptportal-react.maximeyes.com/patient-terms-conditions')
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span'))
//
//WebUI.closeBrowser()
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://qa-autostable7.maximeyes.com/Account/Login')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838 (1)'), 
//    'QA_User')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2024')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (1)'), 'test')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_LastName (1)'), 'enc')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID (1)'), 
//    '76', true)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_EM Code Checker_ePrescribe_102724 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.delay(10)
//
////WebUI.click(findTestObject('Object Repository/Page_MaximEyes/AddMedePrescribe'))
//WebElement rcopiaObj = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)'), 
//    20)
//
//WebUI.executeJavaScript('arguments[0].contentDocument.body.click();', Arrays.asList(rcopiaObj))
//
//TestObject rcopiaObjTO = findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)')
//
//rcopiaObj = WebUiCommonHelper.findWebElement(rcopiaObjTO, 20)
//
//String js = '\nvar obj = arguments[0];\nvar doc = obj.contentDocument || obj.contentWindow.document;\nvar buttons = doc.querySelectorAll(\'button\');\nfor (var b of buttons) {\n   if (b.textContent.includes(\'Add Medication\')) {\n      b.click();\n      return true;\n   }\n}\nreturn false;\n'
//
//WebUI.executeJavaScript(js, Arrays.asList(rcopiaObj))
//
//WebUI.delay(10)
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://qa-autostable7.maximeyes.com/Account/Login')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838 (1)'), 
//    'QA_User')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2024')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (1)'), 'test')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_LastName (1)'), 'enc')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID (1)'), 
//    '76', true)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_EM Code Checker_ePrescribe_102724 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.delay(10)
//
////WebUI.click(findTestObject('Object Repository/Page_MaximEyes/AddMedePrescribe'))
//WebElement rcopiaObj = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)'), 
//    20)
//
//WebUI.executeJavaScript('arguments[0].contentDocument.body.click();', Arrays.asList(rcopiaObj))
//
//TestObject rcopiaObjTO = findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)')
//
//rcopiaObj = WebUiCommonHelper.findWebElement(rcopiaObjTO, 20)
//
//String js = '\nvar obj = arguments[0];\nvar doc = obj.contentDocument || obj.contentWindow.document;\nvar buttons = doc.querySelectorAll(\'button\');\nfor (var b of buttons) {\n   if (b.textContent.includes(\'Add Medication\')) {\n      b.click();\n      return true;\n   }\n}\nreturn false;\n'
//
//WebUI.executeJavaScript(js, Arrays.asList(rcopiaObj))
//
//WebUI.delay(10)
//
//WebUI.acceptAlert()
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://qa-autostable7.maximeyes.com/Account/Login')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838 (1)'), 
//    'QA_User')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2024')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (1)'), 'test')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_LastName (1)'), 'enc')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID (1)'), 
//    '76', true)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_EM Code Checker_ePrescribe_102724 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.delay(10)
//
////WebUI.click(findTestObject('Object Repository/Page_MaximEyes/AddMedePrescribe'))
//WebElement rcopiaObj = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)'), 
//    20)
//
//WebUI.executeJavaScript('arguments[0].contentDocument.body.click();', Arrays.asList(rcopiaObj))
//
//TestObject rcopiaObjTO = findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)')
//
//rcopiaObj = WebUiCommonHelper.findWebElement(rcopiaObjTO, 20)
//
//String js = '\nvar obj = arguments[0];\nvar doc = obj.contentDocument || obj.contentWindow.document;\nvar buttons = doc.querySelectorAll(\'button\');\nfor (var b of buttons) {\n   if (b.textContent.includes(\'Add Medication\')) {\n      b.click();\n      return true;\n   }\n}\nreturn false;\n'
//
//WebUI.executeJavaScript(js, Arrays.asList(rcopiaObj))
//
//WebUI.delay(10)
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://qa-autostable7.maximeyes.com/Account/Login')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838 (1)'), 
//    'QA_User')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2024')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_ACTIONS_imgFindPatient (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_FirstName (1)'), 'test')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Find Patient_LastName (1)'), 'enc')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Active_btnSearchPatient (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/a_Actions_Encounters  Add New Encounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Encounter Type_EncounterTypeID (1)'), 
//    '76', true)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_TOC Req_mif-Hamburger fg-gray font30 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_EM Code Checker_ePrescribe_102724 (1)'))
//
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)
//
//WebUI.delay(10)
//
////WebUI.click(findTestObject('Object Repository/Page_MaximEyes/AddMedePrescribe'))
//WebElement rcopiaObj = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)'), 
//    20)
//
//WebUI.executeJavaScript('arguments[0].contentDocument.body.click();', Arrays.asList(rcopiaObj))
//
//TestObject rcopiaObjTO = findTestObject('Object Repository/Page_MaximEyes/RcopiaObject (1)')
//
//rcopiaObj = WebUiCommonHelper.findWebElement(rcopiaObjTO, 20)
//
//String js = '\nvar obj = arguments[0];\nvar doc = obj.contentDocument || obj.contentWindow.document;\nvar buttons = doc.querySelectorAll(\'button\');\nfor (var b of buttons) {\n   if (b.textContent.includes(\'Add Medication\')) {\n      b.click();\n      return true;\n   }\n}\nreturn false;\n'
//
//WebUI.executeJavaScript(js, Arrays.asList(rcopiaObj))
//
//WebUI.delay(10)
//
//WebUI.acceptAlert()
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://ptportal-react.maximeyes.com/patient-terms-conditions')
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/input_Terms and Conditions Content_acceptTerms'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/canvas__signature-canvas'))
//
//WebUI.click(findTestObject('Object Repository/Page_Patient Portal/span'))
//
//WebUI.closeBrowser()
//
//WebUI.openBrowser('')
//
//WebUI.navigateToUrl('https://qa-autostable8.maximeyes.com/Account/Login')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_52b838 (1)'), 
//    'QA_User')
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2026')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_For your safety, your account will be _21e4db (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_6d0053 (1)'), 
//    'Automation@2024')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_MaximEyes is in use and has been lock_ec6027 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient_mif-addDefault font18 (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/div (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Name_AddNew_Patient_FirstName (1)'), 'erewr')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Name_AddNew_Patient_FirstName (1)'))
//
//WebUI.setText(findTestObject('Object Repository/Page_MaximEyes/input_Name_AddNew_Patient_LastName (1)'), 'qwewe')
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/i_DOB_AddNewPtDob_ (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/div_Group   Group Name_daterangepicker drop_d91a47 (1)'))
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_MaximEyes/select_Preferred Provider_AddNew_Patient_Pr_ffc4bf (1)'), 
//    '1131', true)
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Primary_Value (1)'))
//
//WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Group   Group Name_btnSave (1)'))

//WebUI.openBrowser('')

WebUI.navigateToUrl('https://apptbookingqa1.maximeyes.com/ptportal278')

WebUI.delay(30)

WebUI.click(findTestObject('null'))

WebUI.setText(findTestObject('null'), 
    'test')

WebUI.click(findTestObject('null'))

WebUI.setText(findTestObject('null'), 
    'test')

WebUI.click(findTestObject('null'))

WebUI.setText(findTestObject('null'), 
    'test@test.com')

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

