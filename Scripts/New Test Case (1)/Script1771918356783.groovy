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

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Username'), 'HvIwLoIs')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password'))

WebUI.setEncryptedText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password'), 'cvW8qx4B2o1gIDzvWT+0mQ==')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Confirm Password'))

WebUI.setEncryptedText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Confirm Password'), 
    'cvW8qx4B2o1gIDzvWT+0mQ==')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h1_Create Credentials'), 
    'Create Credentials')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h2_Choose a unique Username'), 
    'Choose a unique Username')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h2_Choose a new Password'), 
    'Choose a new Password')

WebUI.verifyElementPresent(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/path_icon'), 0)

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h1_Sign Up Completed'), 
    'Sign Up Completed')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_pageTitle'), 'Sign up completed')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Username_1'), 'HvIwLoIs')



WebUI.setEncryptedText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password_1'), 'cvW8qx4B2o1gIDzvWT+0mQ==')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_signInButton'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Security code digit 1 of 4'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed_1'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/h3_HvIwLoIs DCgghJzngx'), 
    'HvIwLoIs DCgghJzngx')

//WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_HD'), 'HD')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_Select User'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/li_John Doe'))

WebUI.rightClick(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_John Doe'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_John Doe'), 'Select User')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.rightClick(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_You are viewing record for John Doe'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_You are viewing record for John Doe_1'), 
    'You are viewing record for John Doe')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/svg_text-primary'))



WebUI.verifyElementNotClickable(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_Profile'))



WebUI.verifyElementNotClickable(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_Communication Preferences'))



WebUI.verifyElementNotClickable(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_Authorized Individuals'))



WebUI.verifyElementNotClickable(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/span_Opt Out'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/li_Log Out'))

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Username_1'), 'HvIwLoIs')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password_1'))

WebUI.setEncryptedText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password_1'), 'cvW8qx4B2o1gIDzvWT+0mQ==')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_signInButton'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Security code digit 1 of 4'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed_1'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_Sign Up as a patientPlan your first visit to'))

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Legal First Name'),
	'value',
	'John Doe',
	10
)

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Last Name'),
	'value',
	'John Doe',
	10
)

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_(000) 000-0000'),
	'value',
	'John Doe',
	10
)

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Email'),
	'value',
	'John Doe',
	10
)

WebUI.verifyElementAttributeValue(
	findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'),
	'value',
	'',
	10
)

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Legal First Name'), 
    '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Last Name'), '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_(000) 000-0000'), 
    '')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Email'), '')


WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'), 
    '')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_DOB is required'), 
    'DOB is required!')

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'), '67/54/3222')

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_DOB is required'), 
    'Please enter a valid date in MM/DD/YYYY format')

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'), '12/12/2000')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.rightClick(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_Sign up completed'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/p_Sign up completed'), 
    'Sign up completed')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Username_1'), 'HvIwLoIs')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password_1'))

WebUI.setEncryptedText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password_1'), 'hjE5xb79D+FS56R2jpR+tw==')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_signInButton'))

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_MM_DD_YYYY'), '12/12/2000')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_I Accept'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/canvas_signature-canvas'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_New Password'))

WebUI.setEncryptedText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_New Password'), 
    'cvW8qx4B2o1gIDzvWT+0mQ==')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Confirm Password'))

WebUI.setEncryptedText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Confirm Password'), 
    'cvW8qx4B2o1gIDzvWT+0mQ==')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed'))

WebUI.rightClick(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_2'))

WebUI.verifyElementText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/div_2'), 'Password updated successfully!')

WebUI.setText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Username_1'), 'HvIwLoIs')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password_1'))

WebUI.setEncryptedText(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Password_1'), 'cvW8qx4B2o1gIDzvWT+0mQ==')

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_signInButton'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/input_Security code digit 1 of 4'))

WebUI.click(findTestObject('Authorized Individual/Auth User Sign Up/Page_Patient Portal/button_Proceed_1'))

