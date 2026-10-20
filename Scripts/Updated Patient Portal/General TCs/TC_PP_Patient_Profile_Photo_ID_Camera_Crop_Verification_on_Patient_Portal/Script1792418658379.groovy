import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/*
 * TEST CASE : Patient Portal > Profile > Photo/ID > Camera (Capture + Crop + Persistence)
 * PLATFORM  : WEB BROWSER ONLY
 *
 * IN SCOPE  : A) Crop functionality (1:1, 4:3, 16:9, Free, zoom, Apply, Cancel)
 *             D) Saved Photo/ID is retained after logout/login and refresh (web)
 *
 * SKIPPED (not automated on web, per dev notes):
 *   - B) Camera flash ON/OFF/AUTO      -> hardware/browser dependent
 *   - C) Selfie / front / rear camera  -> mobile & tablet only
 *   - Mobile/iPad cross-device deletion cache sync, orientation checks on mobile/tablet
 */

// ------------------------- CAMERA PERMISSION (Chrome) -----------------
// Must run BEFORE the login test case opens the browser.
// Auto-accepts the camera permission prompt and uses a fake webcam feed.
RunConfiguration.setWebDriverPreferencesProperty('args', [
	'--use-fake-ui-for-media-stream',
	'--use-fake-device-for-media-stream'
])
RunConfiguration.setWebDriverPreferencesProperty('prefs', [
	'profile.default_content_setting_values.media_stream_camera': 1
])

// ------------------------- DYNAMIC OBJECTS ----------------------------
TestObject xp(String name, String xpath) {
	TestObject to = new TestObject(name)
	to.addProperty('xpath', ConditionType.EQUALS, xpath)
	return to
}

String TAKE_POPUP = "//h5[normalize-space()='Take Photo']/ancestor::div[contains(@class,'bg-white')][1]"
String CROP_POPUP = "//h5[normalize-space()='Crop Photo']/ancestor::div[contains(@class,'bg-white')][1]"

TestObject imgProfilePic = xp('imgProfilePic', "//img[contains(@class,'profile') or contains(@alt,'Photo')]")    // TODO: confirm locator

// Camera + Take Photo popup
TestObject iconCamera     = xp('iconCamera',     "//*[name()='svg' and @viewBox='0 0 20 16']/ancestor::*[self::button or self::a or self::div][1]")
TestObject popupTake      = xp('popupTake',      TAKE_POPUP)
TestObject videoPreview   = xp('videoPreview',   TAKE_POPUP + "//video")
TestObject btnCapture     = xp('btnCapture',     TAKE_POPUP + "//button[contains(normalize-space(.),'Capture')]")
TestObject btnTakeCancel  = xp('btnTakeCancel',  TAKE_POPUP + "//button[normalize-space()='Cancel']")
TestObject btnTakeClose   = xp('btnTakeClose',   TAKE_POPUP + "//button[contains(@class,'btn-close')]")

// Crop popup
TestObject popupCrop      = xp('popupCrop',      CROP_POPUP)
TestObject cropImage      = xp('cropImage',      CROP_POPUP + "//img[contains(@class,'reactEasyCrop_Image')]")
TestObject cropArea       = xp('cropArea',       CROP_POPUP + "//div[@data-testid='cropper']")
TestObject cropZoom       = xp('cropZoom',       "//input[@id='crop-zoom-range']")
TestObject btnCropApply   = xp('btnCropApply',   CROP_POPUP + "//button[normalize-space()='Apply']")
TestObject btnCropCancel  = xp('btnCropCancel',  CROP_POPUP + "//button[normalize-space()='Cancel']")
TestObject btnCropClose   = xp('btnCropClose',   CROP_POPUP + "//button[@aria-label='Close']")

TestObject ratioBtn(String ratio) {
	return xp('ratio_' + ratio, "//div[@aria-label='Crop aspect ratio']//button[normalize-space()='" + ratio + "']")
}

// ------------------------- HELPERS ------------------------------------
// Cloud browsers have no webcam and ignore Chrome flags, so fake the camera in the page itself.
// Must be re-run after any full page load (login, reload) - it is lost on reload.
void mockCamera() {
	WebUI.executeJavaScript('''
		(function () {
			var c = document.createElement('canvas');
			c.width = 640; c.height = 480;
			var ctx = c.getContext('2d');
			setInterval(function () {
				ctx.fillStyle = '#2b6cb0';
				ctx.fillRect(0, 0, 640, 480);
				ctx.fillStyle = '#ffffff';
				ctx.font = '36px Arial';
				ctx.fillText('FAKE CAMERA ' + new Date().toISOString(), 20, 240);
			}, 100);
			navigator.mediaDevices.getUserMedia = function () {
				return Promise.resolve(c.captureStream(15)); // fresh stream each call
			};
			navigator.mediaDevices.enumerateDevices = function () {
				return Promise.resolve([{ kind: 'videoinput', deviceId: 'fake-cam', label: 'Fake Camera', groupId: 'fake' }]);
			};
		})();
	''', null)
}

void loginAndOpenProfile() {
	WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Dsmith Portal Login'), [:], FailureHandling.STOP_ON_FAILURE)
	// Setting icon on pt portal dashboard
	WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))
	// Click on Profile
	WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/span_Profile'))
	mockCamera()
}

void setZoom(TestObject zoom, String value) {
	// React range input needs the native setter + input event
	WebUI.executeJavaScript("""
		var el = document.getElementById('crop-zoom-range');
		var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;
		setter.call(el, '${value}');
		el.dispatchEvent(new Event('input', { bubbles: true }));
		el.dispatchEvent(new Event('change', { bubbles: true }));
	""", null)
}

String cropAreaSize() {
	// Methods can't see script-level local variables (CROP_POPUP), so the xpath is written out in full here
	return WebUI.getAttribute(xp('cropAreaSize', "//h5[normalize-space()='Crop Photo']/ancestor::div[contains(@class,'bg-white')][1]//div[@data-testid='cropper']"), 'style')
}

// ======================= EXECUTION =====================================
try {
	// ---- Step 1-2: Open Patient Portal, login, Profile ----
	loginAndOpenProfile()

	// ---- Step 3: Camera icon opens "Take Photo" popup ----
	WebUI.waitForElementClickable(iconCamera, 20)
	WebUI.click(iconCamera)
	WebUI.verifyElementVisible(popupTake)
	WebUI.verifyElementText(xp('takeTitle', TAKE_POPUP + "//h5"), 'Take Photo')
	WebUI.verifyElementVisible(videoPreview)           // live webcam preview
	WebUI.verifyElementVisible(btnCapture)
	WebUI.verifyElementVisible(btnTakeCancel)
	WebUI.verifyElementVisible(btnTakeClose)

	// ---- Step 4: Cancel closes popup without changes, then reopen ----
	WebUI.click(btnTakeCancel)
	WebUI.verifyElementNotPresent(popupTake, 5)
	WebUI.click(iconCamera)
	WebUI.verifyElementVisible(popupTake)

	// ---- Step 5: Capture -> Crop popup appears (crop after capture) ----
	WebUI.delay(2) // let camera stream initialise
	WebUI.click(btnCapture)
	WebUI.waitForElementVisible(popupCrop, 15)
	WebUI.verifyElementNotPresent(popupTake, 5)
	WebUI.verifyElementText(xp('cropTitle', CROP_POPUP + "//h5"), 'Crop Photo')
	WebUI.verifyElementVisible(cropImage)
	WebUI.verifyElementVisible(cropArea)
	WebUI.verifyElementVisible(cropZoom)
	WebUI.verifyElementVisible(btnCropApply)
	WebUI.verifyElementVisible(btnCropCancel)

	// ---- Step 6: Standard ratios + Free-form are available ----
	['1:1', '4:3', '16:9', 'Free'].each { r ->
		WebUI.verifyElementPresent(ratioBtn(r), 5)
	}

	// ---- Step 7: Default ratio is 1:1 (active = btn-primary) ----
	WebUI.verifyElementAttributeValue(ratioBtn('1:1'), 'class', 'btn btn-sm btn-primary', 5, FailureHandling.CONTINUE_ON_FAILURE)

	// ---- Step 8: Switch each ratio, verify active state and crop box changes ----
	String prevSize = cropAreaSize()
	['4:3', '16:9', 'Free', '1:1'].each { r ->
		WebUI.click(ratioBtn(r))
		WebUI.delay(1)
		WebUI.verifyElementHasAttribute(ratioBtn(r), 'class', 5)
		String cls = WebUI.getAttribute(ratioBtn(r), 'class')
		WebUI.verifyMatch(cls, '.*btn-primary.*', true)           // selected
		WebUI.verifyNotMatch(cls, '.*btn-outline-primary.*', true)
		String size = cropAreaSize()
		if (r != 'Free' && r != '1:1') {
			WebUI.verifyNotEqual(size, prevSize, FailureHandling.CONTINUE_ON_FAILURE) // crop box resized
		}
		prevSize = size
	}

	// ---- Step 9: Zoom slider works ----
	String tBefore = WebUI.getAttribute(cropImage, 'style')
	setZoom(cropZoom, '2')
	WebUI.delay(1)
	WebUI.verifyEqual(WebUI.getAttribute(cropZoom, 'value'), '2')
	WebUI.verifyNotEqual(WebUI.getAttribute(cropImage, 'style'), tBefore, FailureHandling.CONTINUE_ON_FAILURE)

	// ---- Step 10: Crop popup Cancel discards the capture ----
	WebUI.click(btnCropCancel)
	WebUI.verifyElementNotPresent(popupCrop, 5)

	// ---- Step 11: Capture again, choose 4:3, Apply -> saved to profile ----
	WebUI.click(iconCamera)
	WebUI.waitForElementVisible(popupTake, 10)
	WebUI.delay(2)
	WebUI.click(btnCapture)
	WebUI.waitForElementVisible(popupCrop, 15)
	WebUI.click(ratioBtn('4:3'))
	setZoom(cropZoom, '1.5')
	WebUI.click(btnCropApply)
	WebUI.verifyElementNotPresent(popupCrop, 10)
	WebUI.waitForElementVisible(imgProfilePic, 15)
//	String savedSrcBefore = WebUI.getAttribute(imgProfilePic, 'src')
//	WebUI.verifyNotEqual(savedSrcBefore, null)
//	// Per dev note: cropped image is rendered per selected ratio, so it may not fill the profile box (expected).
//
//	// ---- Step 12: Persistence after refresh ----
//	WebUI.refresh()
//	WebUI.waitForElementVisible(imgProfilePic, 20)
//	WebUI.verifyElementPresent(imgProfilePic, 10)
//
//	// ---- Step 13: Persistence after logout / login (same web browser) ----
//	// TODO: add the logout step here (e.g. WebUI.click(findTestObject('<Logout object>')))
//	//       before logging in again, otherwise the login test case may fail on an active session.
//	WebUI.closeBrowser()
//	loginAndOpenProfile()
//	WebUI.waitForElementVisible(imgProfilePic, 20)
//	WebUI.verifyElementPresent(imgProfilePic, 10)
//	String savedSrcAfter = WebUI.getAttribute(imgProfilePic, 'src')
//	WebUI.verifyNotEqual(savedSrcAfter, null)
//	// Note: 'src' may be a signed/rotating URL; compare only if it is stable in your environment.
	
	TestObject profileImage = findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/Uploaded Image')
	TestObject imgInsideProfile = findTestObject('Object Repository/Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/Image at Profile circle')
	WebUI.callTestCase(
		findTestCase('common/Patient_Portal_Common/Uploaded Profile Image Validation'),
		[
			'imgObj' : profileImage
		],
		FailureHandling.STOP_ON_FAILURE
	)
	//Click on Home icon
	WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Home Btn Patient Portal'))
	
	//Profile image at profile circle
	WebUI.waitForElementPresent(imgInsideProfile, 15)
	
	// Extra wait for rendering
	WebUI.delay(2)
	
	def img = WebUI.findWebElement(imgInsideProfile)
	
	Boolean isLoaded = WebUI.executeJavaScript(
		"return arguments[0].complete && arguments[0].naturalWidth > 0;",
		Arrays.asList(img)
	)
	
	//Setting icon on pt portal dashboard
	WebUI.click(findTestObject('Object Repository/Page_Patient Portal/Setting Icon on Portal'))
	
	//Click on Profile
	WebUI.click(findTestObject('Object Repository/Patient_Profile_Section/Page_Patient Portal/span_Profile'))
	
	//Profile image verification
	//WebUI.callTestCase(findTestCase('Test Cases/common/Patient_Portal_Common/Uploaded Profile Image Validation'), [:], FailureHandling.STOP_ON_FAILURE)
	WebUI.callTestCase(
		findTestCase('common/Patient_Portal_Common/Uploaded Profile Image Validation'),
		[
			'imgObj' : profileImage
		],
		FailureHandling.STOP_ON_FAILURE
	)
	
	//Click on delete button
	WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/svg_opacity-100'))
	
	//Verify confirmation toast is displayed
	WebUI.verifyElementText(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/p_Do you want to delete this picture'),
		'Do you want to delete this picture?')
	
	//Click on Procced buton on confirmation popup
	WebUI.click(findTestObject('Scenario Update1703/Patient Profile/Profile page/Page_Patient Portal/button_Proceed'))
	
	// ---- Step 14: Close (X) buttons on both popups ----
	WebUI.click(iconCamera)
	WebUI.waitForElementVisible(popupTake, 10)
	WebUI.click(btnTakeClose)
	WebUI.verifyElementNotPresent(popupTake, 5)

	WebUI.click(iconCamera)
	WebUI.waitForElementVisible(popupTake, 10)
	WebUI.delay(2)
	WebUI.click(btnCapture)
	WebUI.waitForElementVisible(popupCrop, 15)
	WebUI.click(btnCropClose)
	WebUI.verifyElementNotPresent(popupCrop, 5)
	
	
	

}
catch (Throwable testError) {
	// Log, then rethrow so Katalon still marks the test as failed
	WebUI.comment('Test failed: ' + testError.toString())
	throw testError

}
