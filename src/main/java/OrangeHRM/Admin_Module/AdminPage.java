package OrangeHRM.Admin_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AdminPage {
	WebDriver driver;
	WebDriverWait wait;

	By adminMenu = By.xpath("//span[text()='Admin']");
	By qualificationsMenu = By.xpath("//span[normalize-space()='Qualifications']");
	By skillsOption = By.xpath("//a[normalize-space()='Skills']");
	By educationOption = By.xpath("//a[normalize-space()='Education']");
	By languagesOption = By.xpath("//a[normalize-space()='Languages']");

	public AdminPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void goToSkillsPage() {
		wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(qualificationsMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(skillsOption)).click();
	}

	public void goToEducationPage() {
		wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(qualificationsMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(educationOption)).click();
	}

	public void goToLanguagesPage() {
		wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(qualificationsMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(languagesOption)).click();
	}
}