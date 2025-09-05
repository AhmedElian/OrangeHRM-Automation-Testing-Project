package OrangeHRM.Admin_Module;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrangeHRM_Login_Page_Test extends OrangeHRM_Test_Base {
	private OrangeHRM_Login_Page orangeHRM_Login_Page;

	@BeforeMethod
	public void setup_2() {
		driver.manage().deleteAllCookies();
		driver.get(getBaseUrl());

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")));

		orangeHRM_Login_Page = new OrangeHRM_Login_Page(driver);
	}

	@Test(priority = 1, groups = "login", dataProvider = "Login_Test_Data", dataProviderClass = OrangeHRM_Read_CSV.class)
	public void testLoginData(String username, String password, boolean expected) throws InterruptedException {
		Reporter.log("Starting Login Test With Username: " + username + " Password: " + password, true);

		orangeHRM_Login_Page.login(username, password);
		boolean result = orangeHRM_Login_Page.isLoggedIn();

		Assert.assertEquals(result, expected, "Login result did not match expected!");
		Reporter.log("Test Completed For Username: " + username + " | Expected: " + expected + " | Got: " + result,
				true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 2, groups = "resetPassword", dataProvider = "Reset_Password_Test_Data", dataProviderClass = OrangeHRM_Read_CSV.class)
	public void testResetPassword(String username, boolean expected) throws InterruptedException {
		Reporter.log("Starting Reset Password Test With Username: " + username, true);

		orangeHRM_Login_Page.resetPassword(username);
		boolean result = orangeHRM_Login_Page.isPasswordResetLinkSent();

		Assert.assertEquals(result, expected, "Reset Password result did not match expected!");
		Reporter.log("Test Completed For Username: " + username + " | Expected: " + expected + " | Got: " + result,
				true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 3, groups = "resetPassword")
	public void testResetPassword_BackToLogin() throws InterruptedException {
		Reporter.log("Starting Test Reset Password And Back To Login Page By Back Button With Username: Admin", true);

		boolean expected = orangeHRM_Login_Page.backToLogin();
		Reporter.log("Clicked Back to Login Page button", true);

		boolean result = orangeHRM_Login_Page.isAtLoginPage();
		Reporter.log("Navigated back to Login Page", true);

		Assert.assertEquals(result, expected, "Back to Login Page result did not match expected!");
		Reporter.log("Test Completed For Username: Admin | Expected: " + expected + " | Got: " + result, true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 4, groups = "checkFooter")
	public void testFooterText() {
		Reporter.log("Starting Footer Text Verification", true);

		boolean expectedFooterText = "OrangeHRM OS 5.9 © 2005 - 2024" != null;
		boolean actualFooterText = orangeHRM_Login_Page.isFooterCopyRightTextDisplayed();

		Assert.assertEquals(actualFooterText, expectedFooterText, "Footer text did not match expected!");
		Reporter.log("Footer text verified successfully: " + actualFooterText, true);
	}

	@Test(priority = 5, groups = "checkFooter")
	public void testFooterLinkedIn() {
		Reporter.log("Starting LinkedIn Link Verification", true);
		Assert.assertTrue(orangeHRM_Login_Page.isFooterLinkedInIconDisplayedAndClickable(),
				"LinkedIn link is not present in footer!");
		Reporter.log("LinkedIn link verified successfully", true);
	}

	@Test(priority = 6, groups = "checkFooter")
	public void testFooterFacebook() {
		Reporter.log("Starting Facebook Link Verification", true);
		Assert.assertTrue(orangeHRM_Login_Page.isFooterFacebookIconDisplayedAndClickable(),
				"Facebook link is not present in footer!");
		Reporter.log("Facebook link verified successfully", true);
	}

	@Test(priority = 7, groups = "checkFooter")
	public void testFooterTwitter() {
		Reporter.log("Starting Twitter Link Verification", true);
		Assert.assertTrue(orangeHRM_Login_Page.isFooterTwitterIconDisplayedAndClickable(),
				"Twitter link is not present in footer!");
		Reporter.log("Twitter link verified successfully", true);
	}

	@Test(priority = 8, groups = "checkFooter")
	public void testFooterYouTube() {
		Reporter.log("Starting YouTube Link Verification", true);
		Assert.assertTrue(orangeHRM_Login_Page.isFooterYouTubeIconDisplayedAndClickable(),
				"YouTube link is not present in footer!");
		Reporter.log("YouTube link verified successfully", true);
	}
}