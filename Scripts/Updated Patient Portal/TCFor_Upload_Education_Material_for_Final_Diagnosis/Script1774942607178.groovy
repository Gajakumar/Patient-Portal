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
import stories.NavigateStory

NavigateStory nav = new NavigateStory()

// =====================================================
// LOGIN TO MAXIMEYES
// =====================================================

WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/User Login in Maximeyes Pt Portal'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

//Create Random Patient
WebUI.callTestCase(
	findTestCase('Test Cases/common/Patient_Portal_Common/Create Random Patient in Maximeyes'),
	[
		('phoneNumber') : GlobalVariable.Mobile,
		('emailId')     : GlobalVariable.MyEmail_Id,
	],
	FailureHandling.STOP_ON_FAILURE
)

//Click on + button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Patient Portal_ptoverviewsignupforpp'))

//Select Send Sign Up Email to
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/span_Send Sign Up Email to_icons'))

//Click on Procced button
WebUI.click(findTestObject('Object Repository/Page_MaximEyes/input_Edit Email Address_btnProceedSaveNewP_fc225c'))

//Wait until busy indicator invisible
WebUI.waitForElementNotVisible(findTestObject('Object Repository/Page_MaximEyes/Busy Indicator'), 30)

//Verify toast msg
WebUI.verifyElementText(findTestObject('Object Repository/Page_MaximEyes/Toast Msg'), 'Patient Portal Sign Up Completed. Email Sent.')

WebUI.delay(10)

//get Username & Password from email
CustomKeywords.'email.GmailCredentialExtractor.extractUsernameAndPassword'(GlobalVariable.MyEmail_Id, GlobalVariable.Email_Key,
	GlobalVariable.Sender_Email, 'Access to your health data')

println('Username: ' + GlobalVariable.GV_Username)

println('Password: ' + GlobalVariable.GV_Password)


//Click on encounter dropdown
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Encounters_dropdown-toggle menu-large rec_046ac3'))

//Click on Create new encounter
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/a_Actions_Encounters  Add New Encounter'))

//Select Encounter type as : Automation Element Test Encounter
WebUI.selectOptionByLabel(
	findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/select_Encounter Type_EncounterTypeID'),
	'Automation Element Test Encounter',
	false
)

//Click on Add button
WebUI.click(findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Transition of Care (TOC) Requested_bt_474ceb'))


TestObject createNewEncounterBtn =
		findTestObject('Object Repository/Maximeyes_Portal_Mix/Page_MaximEyes/input_Confirmation_btnCreateANewEncounter')

//Verify Create new encounter button is displayed then click on it
if (WebUI.verifyElementPresent(createNewEncounterBtn, 5, FailureHandling.OPTIONAL)) {
	WebUI.click(createNewEncounterBtn)
	println('Create New Encounter button clicked')
} else {
	println('Create New Encounter button not displayed – skipping click')
}

WebUI.delay(2)

nav.SelectEncounterElementFromLeftNavOnEncounter([
	pElementPage: "Final Findings",
	pElement    : "Final Outbound Documents"
])

//WebUI.waitForElementNotVisible(findTestObject('Page_MaximEyes/Busy Indicator'), 30)

WebUI.delay(5)

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_glyphicon-circelplus font17 fg-skyblue'))


WebUI.waitForElementNotVisible(
	findTestObject('Object Repository/Scenario Update1703/Page_MaximEyes/Page Loader'),
	30
)

WebUI.waitForElementNotVisible(
	findTestObject('Scenario Update1703/Page_MaximEyes/svg_txRibbonLoader'),
	30
)

def safeClick(TestObject to) {
	TestObject loader = findTestObject('Object Repository/Scenario Update1703/Page_MaximEyes/Page Loader')

	WebUI.waitForElementNotPresent(loader, 30)
	WebUI.waitForElementClickable(to, 20)

	try {
		WebUI.click(to)
	} catch (Exception e) {
		WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(WebUI.findWebElement(to)))
	}
}

WebUI.selectOptionByValue(findTestObject('Scenario Update1703/Page_MaximEyes/select_ddlDocumentType'), '11', false)

WebUI.selectOptionByValue(findTestObject('Scenario Update1703/Page_MaximEyes/select_ddlDocumentTemplate'), '180', false)

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/button_btnSelectDocument'))

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_btnAddRecipient'))

WebUI.selectOptionByValue(findTestObject('Scenario Update1703/Page_MaximEyes/select_ddlRecipientType'), 'Patient', false)

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_chkIsPrint'))

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_icon-checked'))

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_btnAddChildRecipientDetails'))

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_btnSaveChildRecipients'))

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/input_btnSendDocument'))

WebUI.delay(5)

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/a_Cataract Consultation'), 'Cataract Consultation')

WebUI.rightClick(findTestObject('Scenario Update1703/Page_MaximEyes/span_Owvkmi Lomjftuh(Patient)'))

//WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/span_Owvkmi Lomjftuh(Patient)'), 'Owvkmi L...')

//WebUI.rightClick(findTestObject('Scenario Update1703/Page_MaximEyes/span_Sent'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/span_Sent_1'), 'Sent')

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_mif-dropdown font15 fg-skyblue'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Send To Portal'), 'Send To Portal')

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/span_mif-dropdown font15 fg-skyblue_1'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Owvkmi Lomjftuh (Patient)'), '\n                                                        \n                                                            \n\n\n<!--\n(function(){\nvar a = {\'numNegInf\':\'-∞\',\'numPosInf\':\'∞\'};\nfor(var b in a) ASPx.CultureInfo[b] = a[b];\n})();\n\n//-->\n\n\t\n\t\tLoading…\n\t\n\n\n\n\t\n\t\t\n\t\n\n\t\n\t\t\n\t\t\t\n\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\tLast NameFirst NameDOBProviderFax#\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\t     \n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\n<!--\nASPx.createControl(MVCxClientListBox,\'txtPatientRecipientAutocomplete_DDD_L\',\'\',{\'uniqueID\':\'txtPatientRecipientAutocomplete$DDD$L\',\'scStates\':6,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'CustomCallback\':\'\'},\'isSyncEnabled\':false,\'isComboBoxList\':true,\'hasSampleItem\':true,\'isCallbackMode\':true,\'callbackPageSize\':11,\'isHasFakeRow\':true,\'columnFieldNames\':[\'LastName\',\'FirstName\',\'DateOfBirth\',\'Provider\',\'FaxNumber\'],\'textFormatString\':\'{0} {1}\',\'hoverClasses\':[\'dxeListBoxItemHover_Metropolis\'],\'selectedClasses\':[\'dxeListBoxItemSelected_Metropolis\'],\'disabledClasses\':[\'dxeDisabled_Metropolis\'],\'itemsInfo\':[]},{\'SelectedIndexChanged\':function (s, e) { ASPx.CBLBSelectedIndexChanged(\'txtPatientRecipientAutocomplete\', e); },\'ItemClick\':function (s, e) { ASPx.CBLBItemMouseUp(\'txtPatientRecipientAutocomplete\', e); }},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n\t\t\t\n\t\t\n\t\n\n<!--\nASPx.AddHoverItems(\'txtPatientRecipientAutocomplete_DDD\',[[[\'dxpc-closeBtnHover\'],[\'\'],[\'HCB-1\']]]);\nASPx.createControl(ASPxClientPopupControl,\'txtPatientRecipientAutocomplete_DDD\',\'\',{\'uniqueID\':\'txtPatientRecipientAutocomplete$DDD\',\'adjustInnerControlsSizeOnShow\':false,\'popupAnimationType\':\'slide\',\'closeAction\':\'CloseButton\',\'popupHorizontalAlign\':\'LeftSides\',\'popupVerticalAlign\':\'Below\'},{\'Shown\':function (s, e) { ASPx.DDBPCShown(\'txtPatientRecipientAutocomplete\', e); }});\n\n//-->\n\n<!--\nASPx.AddHoverItems(\'txtPatientRecipientAutocomplete\',[[[\'dxeButtonEditButtonHover_Metropolis\'],[\'\'],[\'B-100\',\'B-1\']]]);\nASPx.AddPressedItems(\'txtPatientRecipientAutocomplete\',[[[\'dxeButtonEditButtonPressed_Metropolis\'],[\'\'],[\'B-100\',\'B-1\']]]);\nASPx.AddDisabledItems(\'txtPatientRecipientAutocomplete\',[[[\'dxeDisabled_Metropolis\'],[\'\'],[\'\',\'I\']],[[\'dxeDisabled_Metropolis dxeButtonDisabled_Metropolis\'],[\'\'],[\'B-100\',\'B-1\'],,[[{\'spriteCssClass\':\'dxEditors_edtClearDisabled_Metropolis\'}],[{\'spriteCssClass\':\'dxEditors_edtDropDownDisabled_Metropolis\'}]],[\'Img\']]]);\nASPx.createControl(MVCxClientComboBox,\'txtPatientRecipientAutocomplete\',\'\',{\'callBack\':function(arg) { ; },\'scStates\':2,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'rawValue\':\'\'},\'displayFormat\':\'{0} {1}\',\'autoCompleteAttribute\':{\'name\':\'autocomplete\',\'value\':\'off\'},\'clearButtonDisplayMode\':\'OnHover\',\'dropDownWidth\':\'700px\',\'isCallbackMode\':true,\'dropDownRows\':10,\'filterMinLength\':3,\'lastSuccessValue\':null,\'islastSuccessValueInit\':true,\'allowNull\':true,\'callbackUrl\':\'/Home/FODPatientSearchPartial?ControlName=txtPatientRecipientAutocomplete&FieldName=txtPatientRecipientAutocomplete\'},{\'SelectedIndexChanged\':getRecipientDetails},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n\n                                                            \n                                                            \n\n\n\t\n\t\tLoading…\n\t\n\n\n\n\t\n\t\t\n\t\n\n\t\n\t\t\n\t\t\t\n\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\tLast NameFirst NameClinicSpecialtyFax#Secure Email\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\t      \n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\n<!--\nASPx.createControl(MVCxClientListBox,\'txtExtPhysicianRecipientName_DDD_L\',\'\',{\'uniqueID\':\'txtExtPhysicianRecipientName$DDD$L\',\'scStates\':6,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'CustomCallback\':\'\'},\'isSyncEnabled\':false,\'isComboBoxList\':true,\'hasSampleItem\':true,\'isCallbackMode\':true,\'callbackPageSize\':30,\'isHasFakeRow\':true,\'columnFieldNames\':[\'LastName\',\'FirstName\',\'ClinicName\',\'Speciality\',\'FaxNumber\',\'Secure_Email\'],\'textFormatString\':\'{0} {1} {2}\',\'hoverClasses\':[\'dxeListBoxItemHover_Metropolis\'],\'selectedClasses\':[\'dxeListBoxItemSelected_Metropolis\'],\'disabledClasses\':[\'dxeDisabled_Metropolis\'],\'itemsInfo\':[]},{\'SelectedIndexChanged\':function (s, e) { ASPx.CBLBSelectedIndexChanged(\'txtExtPhysicianRecipientName\', e); },\'ItemClick\':function (s, e) { ASPx.CBLBItemMouseUp(\'txtExtPhysicianRecipientName\', e); }},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n\t\t\t\n\t\t\n\t\n\n<!--\nASPx.AddHoverItems(\'txtExtPhysicianRecipientName_DDD\',[[[\'dxpc-closeBtnHover\'],[\'\'],[\'HCB-1\']]]);\nASPx.createControl(ASPxClientPopupControl,\'txtExtPhysicianRecipientName_DDD\',\'\',{\'uniqueID\':\'txtExtPhysicianRecipientName$DDD\',\'adjustInnerControlsSizeOnShow\':false,\'popupAnimationType\':\'slide\',\'closeAction\':\'CloseButton\',\'popupHorizontalAlign\':\'LeftSides\',\'popupVerticalAlign\':\'Below\'},{\'Shown\':function (s, e) { ASPx.DDBPCShown(\'txtExtPhysicianRecipientName\', e); }});\n\n//-->\n\n<!--\nASPx.AddHoverItems(\'txtExtPhysicianRecipientName\',[[[\'dxeButtonEditButtonHover_Metropolis\'],[\'\'],[\'B-100\',\'B-1\']]]);\nASPx.AddPressedItems(\'txtExtPhysicianRecipientName\',[[[\'dxeButtonEditButtonPressed_Metropolis\'],[\'\'],[\'B-100\',\'B-1\']]]);\nASPx.AddDisabledItems(\'txtExtPhysicianRecipientName\',[[[\'dxeDisabled_Metropolis\'],[\'\'],[\'\',\'I\']],[[\'dxeDisabled_Metropolis dxeButtonDisabled_Metropolis\'],[\'\'],[\'B-100\',\'B-1\'],,[[{\'spriteCssClass\':\'dxEditors_edtClearDisabled_Metropolis\'}],[{\'spriteCssClass\':\'dxEditors_edtDropDownDisabled_Metropolis\'}]],[\'Img\']]]);\nASPx.createControl(MVCxClientComboBox,\'txtExtPhysicianRecipientName\',\'\',{\'callBack\':function(arg) { ; },\'scStates\':2,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'rawValue\':\'\'},\'displayFormat\':\'{0} {1} {2}\',\'autoCompleteAttribute\':{\'name\':\'autocomplete\',\'value\':\'off\'},\'clearButtonDisplayMode\':\'OnHover\',\'dropDownWidth\':\'700px\',\'isCallbackMode\':true,\'dropDownRows\':10,\'lastSuccessValue\':null,\'islastSuccessValueInit\':true,\'allowNull\':true,\'callbackUrl\':\'/Schedule/GetAllExternalPhysician?patientId=2061\'},{\'SelectedIndexChanged\':getRecipientDetails},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n                                                            \n                                                            \n\n\n\t\n\t\tLoading…\n\t\n\n\n\n\t\n\t\t\n\t\n\n\t\n\t\t\n\t\t\t\n\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\tInsurance NameCityStateZipFax#\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\t     \n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\n<!--\nASPx.createControl(MVCxClientListBox,\'txtInsuranceRecipientName_DDD_L\',\'\',{\'uniqueID\':\'txtInsuranceRecipientName$DDD$L\',\'scStates\':6,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'CustomCallback\':\'\'},\'isSyncEnabled\':false,\'isComboBoxList\':true,\'hasSampleItem\':true,\'isCallbackMode\':true,\'callbackPageSize\':30,\'isHasFakeRow\':true,\'columnFieldNames\':[\'InsuranceCompanyName\',\'City\',\'State\',\'ZipCode\',\'FaxNumber\'],\'textFormatString\':\'{0}\',\'hoverClasses\':[\'dxeListBoxItemHover_Metropolis\'],\'selectedClasses\':[\'dxeListBoxItemSelected_Metropolis\'],\'disabledClasses\':[\'dxeDisabled_Metropolis\'],\'itemsInfo\':[]},{\'SelectedIndexChanged\':function (s, e) { ASPx.CBLBSelectedIndexChanged(\'txtInsuranceRecipientName\', e); },\'ItemClick\':function (s, e) { ASPx.CBLBItemMouseUp(\'txtInsuranceRecipientName\', e); }},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n\t\t\t\n\t\t\n\t\n\n<!--\nASPx.AddHoverItems(\'txtInsuranceRecipientName_DDD\',[[[\'dxpc-closeBtnHover\'],[\'\'],[\'HCB-1\']]]);\nASPx.createControl(ASPxClientPopupControl,\'txtInsuranceRecipientName_DDD\',\'\',{\'uniqueID\':\'txtInsuranceRecipientName$DDD\',\'adjustInnerControlsSizeOnShow\':false,\'popupAnimationType\':\'slide\',\'closeAction\':\'CloseButton\',\'popupHorizontalAlign\':\'LeftSides\',\'popupVerticalAlign\':\'Below\'},{\'Shown\':function (s, e) { ASPx.DDBPCShown(\'txtInsuranceRecipientName\', e); }});\n\n//-->\n\n<!--\nASPx.AddHoverItems(\'txtInsuranceRecipientName\',[[[\'dxeButtonEditButtonHover_Metropolis\'],[\'\'],[\'B-100\',\'B-1\']]]);\nASPx.AddPressedItems(\'txtInsuranceRecipientName\',[[[\'dxeButtonEditButtonPressed_Metropolis\'],[\'\'],[\'B-100\',\'B-1\']]]);\nASPx.AddDisabledItems(\'txtInsuranceRecipientName\',[[[\'dxeDisabled_Metropolis\'],[\'\'],[\'\',\'I\']],[[\'dxeDisabled_Metropolis dxeButtonDisabled_Metropolis\'],[\'\'],[\'B-100\',\'B-1\'],,[[{\'spriteCssClass\':\'dxEditors_edtClearDisabled_Metropolis\'}],[{\'spriteCssClass\':\'dxEditors_edtDropDownDisabled_Metropolis\'}]],[\'Img\']]]);\nASPx.createControl(MVCxClientComboBox,\'txtInsuranceRecipientName\',\'\',{\'callBack\':function(arg) { ; },\'scStates\':2,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'rawValue\':\'\'},\'displayFormat\':\'{0}\',\'autoCompleteAttribute\':{\'name\':\'autocomplete\',\'value\':\'off\'},\'clearButtonDisplayMode\':\'OnHover\',\'dropDownWidth\':\'100%\',\'isCallbackMode\':true,\'dropDownRows\':10,\'filterMinLength\':2,\'lastSuccessValue\':null,\'islastSuccessValueInit\':true,\'allowNull\':true,\'callbackUrl\':\'/Schedule/GetAllInsuranceCompanies\'},{\'SelectedIndexChanged\':getRecipientDetails},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n                                                            \n                                                            \n                                                                \n                                                                \n                                                            \n                                                            \n                                                                \n                                                                \n                                                            \n                                                            \n\n\n\t\n\t\tLoading…\n\t\n\n\n\n\t\n\t\t\n\t\n\n\t\n\t\t\n\t\t\t\n\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\tEmployer NamePhoneCityState\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\t    \n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\n<!--\nASPx.createControl(MVCxClientListBox,\'txtEmployerRecipientName_DDD_L\',\'\',{\'uniqueID\':\'txtEmployerRecipientName$DDD$L\',\'scStates\':6,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'CustomCallback\':\'\'},\'isSyncEnabled\':false,\'isComboBoxList\':true,\'hasSampleItem\':true,\'isCallbackMode\':true,\'callbackPageSize\':11,\'isHasFakeRow\':true,\'columnFieldNames\':[\'EmployerName\',\'Phone\',\'City\',\'State\'],\'textFormatString\':\'{0}\',\'hoverClasses\':[\'dxeListBoxItemHover_Metropolis\'],\'selectedClasses\':[\'dxeListBoxItemSelected_Metropolis\'],\'disabledClasses\':[\'dxeDisabled_Metropolis\'],\'itemsInfo\':[]},{\'SelectedIndexChanged\':function (s, e) { ASPx.CBLBSelectedIndexChanged(\'txtEmployerRecipientName\', e); },\'ItemClick\':function (s, e) { ASPx.CBLBItemMouseUp(\'txtEmployerRecipientName\', e); }},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n\t\t\t\n\t\t\n\t\n\n<!--\nASPx.AddHoverItems(\'txtEmployerRecipientName_DDD\',[[[\'dxpc-closeBtnHover\'],[\'\'],[\'HCB-1\']]]);\nASPx.createControl(ASPxClientPopupControl,\'txtEmployerRecipientName_DDD\',\'\',{\'uniqueID\':\'txtEmployerRecipientName$DDD\',\'adjustInnerControlsSizeOnShow\':false,\'popupAnimationType\':\'slide\',\'closeAction\':\'CloseButton\',\'popupHorizontalAlign\':\'LeftSides\',\'popupVerticalAlign\':\'Below\'},{\'Shown\':function (s, e) { ASPx.DDBPCShown(\'txtEmployerRecipientName\', e); }});\n\n//-->\n\n<!--\nASPx.AddHoverItems(\'txtEmployerRecipientName\',[[[\'dxeButtonEditButtonHover_Metropolis\'],[\'\'],[\'B-1\']]]);\nASPx.RemoveHoverItems(\'txtEmployerRecipientName\',[[[\'B-100\']]]);\nASPx.AddPressedItems(\'txtEmployerRecipientName\',[[[\'dxeButtonEditButtonPressed_Metropolis\'],[\'\'],[\'B-1\']]]);\nASPx.RemovePressedItems(\'txtEmployerRecipientName\',[[[\'B-100\']]]);\nASPx.AddDisabledItems(\'txtEmployerRecipientName\',[[[\'dxeDisabled_Metropolis\'],[\'\'],[\'\',\'I\']],[[\'dxeDisabled_Metropolis dxeButtonDisabled_Metropolis\'],[\'\'],[\'B-1\'],,[[{\'spriteCssClass\':\'dxEditors_edtDropDownDisabled_Metropolis\'}]],[\'Img\']]]);\nASPx.RemoveDisabledItems(\'txtEmployerRecipientName\',[[[\'B-100\'],]]);\nASPx.createControl(MVCxClientComboBox,\'txtEmployerRecipientName\',\'\',{\'callBack\':function(arg) { ; },\'scStates\':2,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'rawValue\':\'\'},\'displayFormat\':\'{0}\',\'autoCompleteAttribute\':{\'name\':\'autocomplete\',\'value\':\'off\'},\'dropDownWidth\':\'100%\',\'isCallbackMode\':true,\'dropDownRows\':10,\'filterMinLength\':2,\'lastSuccessValue\':null,\'islastSuccessValueInit\':true,\'callbackUrl\':\'/Schedule/GetEmployersList\'},{\'SelectedIndexChanged\':getRecipientDetails},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n                                                            \n                                                            \n\n\n\t\n\t\tLoading…\n\t\n\n\n\n\t\n\t\t\n\t\n\n\t\n\t\t\n\t\t\t\n\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\tSchool NamePhoneCityState\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\t    \n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\n<!--\nASPx.createControl(MVCxClientListBox,\'txtSchoolRecipientName_DDD_L\',\'\',{\'uniqueID\':\'txtSchoolRecipientName$DDD$L\',\'scStates\':6,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'CustomCallback\':\'\'},\'isSyncEnabled\':false,\'isComboBoxList\':true,\'hasSampleItem\':true,\'isCallbackMode\':true,\'callbackPageSize\':11,\'isHasFakeRow\':true,\'columnFieldNames\':[\'SchoolName\',\'Phone\',\'City\',\'State\'],\'textFormatString\':\'{0}\',\'hoverClasses\':[\'dxeListBoxItemHover_Metropolis\'],\'selectedClasses\':[\'dxeListBoxItemSelected_Metropolis\'],\'disabledClasses\':[\'dxeDisabled_Metropolis\'],\'itemsInfo\':[]},{\'SelectedIndexChanged\':function (s, e) { ASPx.CBLBSelectedIndexChanged(\'txtSchoolRecipientName\', e); },\'ItemClick\':function (s, e) { ASPx.CBLBItemMouseUp(\'txtSchoolRecipientName\', e); }},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n\t\t\t\n\t\t\n\t\n\n<!--\nASPx.AddHoverItems(\'txtSchoolRecipientName_DDD\',[[[\'dxpc-closeBtnHover\'],[\'\'],[\'HCB-1\']]]);\nASPx.createControl(ASPxClientPopupControl,\'txtSchoolRecipientName_DDD\',\'\',{\'uniqueID\':\'txtSchoolRecipientName$DDD\',\'adjustInnerControlsSizeOnShow\':false,\'popupAnimationType\':\'slide\',\'closeAction\':\'CloseButton\',\'popupHorizontalAlign\':\'LeftSides\',\'popupVerticalAlign\':\'Below\'},{\'Shown\':function (s, e) { ASPx.DDBPCShown(\'txtSchoolRecipientName\', e); }});\n\n//-->\n\n<!--\nASPx.AddHoverItems(\'txtSchoolRecipientName\',[[[\'dxeButtonEditButtonHover_Metropolis\'],[\'\'],[\'B-1\']]]);\nASPx.RemoveHoverItems(\'txtSchoolRecipientName\',[[[\'B-100\']]]);\nASPx.AddPressedItems(\'txtSchoolRecipientName\',[[[\'dxeButtonEditButtonPressed_Metropolis\'],[\'\'],[\'B-1\']]]);\nASPx.RemovePressedItems(\'txtSchoolRecipientName\',[[[\'B-100\']]]);\nASPx.AddDisabledItems(\'txtSchoolRecipientName\',[[[\'dxeDisabled_Metropolis\'],[\'\'],[\'\',\'I\']],[[\'dxeDisabled_Metropolis dxeButtonDisabled_Metropolis\'],[\'\'],[\'B-1\'],,[[{\'spriteCssClass\':\'dxEditors_edtDropDownDisabled_Metropolis\'}]],[\'Img\']]]);\nASPx.RemoveDisabledItems(\'txtSchoolRecipientName\',[[[\'B-100\'],]]);\nASPx.createControl(MVCxClientComboBox,\'txtSchoolRecipientName\',\'\',{\'callBack\':function(arg) { ; },\'scStates\':2,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'rawValue\':\'\'},\'displayFormat\':\'{0}\',\'autoCompleteAttribute\':{\'name\':\'autocomplete\',\'value\':\'off\'},\'dropDownWidth\':\'100%\',\'isCallbackMode\':true,\'dropDownRows\':10,\'filterMinLength\':2,\'lastSuccessValue\':null,\'islastSuccessValueInit\':true,\'callbackUrl\':\'/Schedule/GetSchoolManagementList\'},{\'SelectedIndexChanged\':getRecipientDetails},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n                                                            \n                                                            \n                                                                \n                                                            \n                                                            \n\n\n\t\n\t\tLoading…\n\t\n\n\n\n\t\n\t\t\n\t\n\n\t\n\t\t\n\t\t\t\n\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\tClinicSpecialtyFax#Secure Email\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\t    \n\t\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\t\t\n\n\t\t\t\t\t\t\t\n\t\t\t\t\t\t\n\t\t\t\t\t\n\t\t\t\t\n<!--\nASPx.createControl(MVCxClientListBox,\'txtClinicName_DDD_L\',\'\',{\'uniqueID\':\'txtClinicName$DDD$L\',\'scStates\':6,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'CustomCallback\':\'\'},\'isSyncEnabled\':false,\'isComboBoxList\':true,\'hasSampleItem\':true,\'isCallbackMode\':true,\'callbackPageSize\':30,\'isHasFakeRow\':true,\'columnFieldNames\':[\'ClinicName\',\'Speciality\',\'FaxNumber\',\'Secure_Email\'],\'textFormatString\':\'{0}\',\'hoverClasses\':[\'dxeListBoxItemHover_Metropolis\'],\'selectedClasses\':[\'dxeListBoxItemSelected_Metropolis\'],\'disabledClasses\':[\'dxeDisabled_Metropolis\'],\'itemsInfo\':[]},{\'SelectedIndexChanged\':function (s, e) { ASPx.CBLBSelectedIndexChanged(\'txtClinicName\', e); },\'ItemClick\':function (s, e) { ASPx.CBLBItemMouseUp(\'txtClinicName\', e); }},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n\t\t\t\n\t\t\n\t\n\n<!--\nASPx.AddHoverItems(\'txtClinicName_DDD\',[[[\'dxpc-closeBtnHover\'],[\'\'],[\'HCB-1\']]]);\nASPx.createControl(ASPxClientPopupControl,\'txtClinicName_DDD\',\'\',{\'uniqueID\':\'txtClinicName$DDD\',\'adjustInnerControlsSizeOnShow\':false,\'popupAnimationType\':\'slide\',\'closeAction\':\'CloseButton\',\'popupHorizontalAlign\':\'LeftSides\',\'popupVerticalAlign\':\'Below\'},{\'Shown\':function (s, e) { ASPx.DDBPCShown(\'txtClinicName\', e); }});\n\n//-->\n\n<!--\nASPx.AddHoverItems(\'txtClinicName\',[[[\'dxeButtonEditButtonHover_Metropolis\'],[\'\'],[\'B-100\',\'B-1\']]]);\nASPx.AddPressedItems(\'txtClinicName\',[[[\'dxeButtonEditButtonPressed_Metropolis\'],[\'\'],[\'B-100\',\'B-1\']]]);\nASPx.AddDisabledItems(\'txtClinicName\',[[[\'dxeDisabled_Metropolis\'],[\'\'],[\'\',\'I\']],[[\'dxeDisabled_Metropolis dxeButtonDisabled_Metropolis\'],[\'\'],[\'B-100\',\'B-1\'],,[[{\'spriteCssClass\':\'dxEditors_edtClearDisabled_Metropolis\'}],[{\'spriteCssClass\':\'dxEditors_edtDropDownDisabled_Metropolis\'}]],[\'Img\']]]);\nASPx.createControl(MVCxClientComboBox,\'txtClinicName\',\'\',{\'callBack\':function(arg) { ; },\'scStates\':2,\'scPostfix\':\'Metropolis\',\'stateObject\':{\'rawValue\':\'\'},\'displayFormat\':\'{0}\',\'autoCompleteAttribute\':{\'name\':\'autocomplete\',\'value\':\'off\'},\'clearButtonDisplayMode\':\'OnHover\',\'dropDownWidth\':\'700px\',\'isCallbackMode\':true,\'dropDownRows\':10,\'lastSuccessValue\':null,\'islastSuccessValueInit\':true,\'allowNull\':true,\'callbackUrl\':\'/Schedule/GetAllClinic?patientId=2061\'},{\'SelectedIndexChanged\':getRecipientDetailsForClinic},null,{\'decorationStyles\':[{\'key\':\'F\',\'className\':\'dxeFocused_Metropolis\',\'cssText\':\'\'}]});\n\n//-->\n\n\n                                                            \n                                                            \n                                                                \n                                                            \n                                                        \n                                                    ')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Send To Portal'), 'Send To Portal')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Sent'), 'Sent')

//WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_QA_User_03_31_2026 07_11 AM'), 'QA_User:03/31/2026 07:11 AM')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Page_MaximEyes/td_Document sent to Patient Portal successfully'),
	'Document sent to Patient Portal successfully.')

WebUI.click(findTestObject('Scenario Update1703/Page_MaximEyes/div_encounterform13ea63070d'))