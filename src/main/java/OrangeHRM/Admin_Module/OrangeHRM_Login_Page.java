package OrangeHRM.Admin_Module;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangeHRM_Login_Page {

	private WebDriver driver;

	private By usernameInput = By
			.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input");
	private By passwordInput = By
			.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input");
	private By loginButton = By.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
	private By checkIfLoggedIn = By.xpath("//div[@id='app']/div[1]/div[1]/header/div[1]/div[1]");
	private By forgotYourPassword = By.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[4]/p");

	private By resetPasswordUsername = By.xpath("//div[@id='app']/div[1]/div[1]/div/form/div[1]/div/div[2]/input");
	private By resetPasswordButton = By.xpath("//div[@id='app']/div[1]/div[1]/div/form/div[2]/button[2]");
	private By checkIfPasswordLinkSent = By.xpath("//div[@id='app']/div[1]/div[1]/div/h6");
	private By backToLoginButton = By.xpath("//div[@id='app']/div[1]/div[1]/div/form/div[2]/button[1]");

	private By checkIfAtLoginPage = By
			.xpath("//div[@id='app' and contains(normalize-space(), 'Login')]/div[1]/div/div[1]/div/div[2]/h5");

	private By checkFooterCopyRightTextValue = By.xpath(
			"//div[@id='app' and contains(normalize-space(), '2025')]/div[1]/div/div[1]/div/div[2]/div[3]/div[2]/p[2]");
	private By checkFooterYouTubeIcon = By.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[3]/div[1]/a[4]");
	private By checkFooterTwitterIcon = By.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[3]/div[1]/a[3]");
	private By checkFooterFacebookIcon = By.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[3]/div[1]/a[2]");
	private By checkFooterLinkedInIcon = By.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[3]/div[1]/a[1]");

	public OrangeHRM_Login_Page(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
		driver.findElement(usernameInput).clear();
		driver.findElement(usernameInput).sendKeys(username);
	}

	public void enterPassword(String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
		driver.findElement(passwordInput).clear();
		driver.findElement(passwordInput).sendKeys(password);
	}

	public void clickLogin() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		driver.findElement(loginButton).click();
	}

	public void clickForgotYourPassword() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(forgotYourPassword));
		driver.findElement(forgotYourPassword).click();
	}

	public void enterResetPasswordUsername(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordUsername));
		driver.findElement(resetPasswordUsername).clear();
		driver.findElement(resetPasswordUsername).sendKeys(username);
	}

	public void clickResetPassword() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(resetPasswordButton));
		driver.findElement(resetPasswordButton).click();
	}

	public void clickBackToLogin() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(backToLoginButton));
		driver.findElement(backToLoginButton).click();
	}

	public boolean isLoggedIn() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(checkIfLoggedIn));
			return dashboard.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAtLoginPage() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement atLoginPage = wait.until(ExpectedConditions.visibilityOfElementLocated(checkIfAtLoginPage));
			return atLoginPage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isPasswordResetLinkSent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement passwordLinkSent = wait
					.until(ExpectedConditions.visibilityOfElementLocated(checkIfPasswordLinkSent));
			return passwordLinkSent.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isPasswordResetLinkSent(String string) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement passwordLinkSent = wait
					.until(ExpectedConditions.visibilityOfElementLocated(checkIfPasswordLinkSent));
			return passwordLinkSent.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isFooterCopyRightTextDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement footerText = wait
					.until(ExpectedConditions.visibilityOfElementLocated(checkFooterCopyRightTextValue));
			return footerText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isFooterYouTubeIconDisplayedAndClickable() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement youtubeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(checkFooterYouTubeIcon));
			return youtubeIcon.isDisplayed() && youtubeIcon.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isFooterTwitterIconDisplayedAndClickable() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement twitterIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(checkFooterTwitterIcon));
			return twitterIcon.isDisplayed() && twitterIcon.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isFooterFacebookIconDisplayedAndClickable() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement facebookIcon = wait
					.until(ExpectedConditions.visibilityOfElementLocated(checkFooterFacebookIcon));
			return facebookIcon.isDisplayed() && facebookIcon.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isFooterLinkedInIconDisplayedAndClickable() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement linkedInIcon = wait
					.until(ExpectedConditions.visibilityOfElementLocated(checkFooterLinkedInIcon));
			return linkedInIcon.isDisplayed() && linkedInIcon.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public void resetPassword(String username) {
		clickForgotYourPassword();
		enterResetPasswordUsername(username);
		clickResetPassword();
	}

	public boolean backToLogin() {
		clickForgotYourPassword();
		enterResetPasswordUsername("Admin");
		clickResetPassword();
		clickBackToLogin();
		return isAtLoginPage();
	}
}