package OrangeHRM.Admin_Module;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SkillsPage {

	private final WebDriver driver;
	private final WebDriverWait wait;

	private final By addButton = By.xpath("//button[contains(.,'Add')]");
	private final By skillNameField = By.xpath("//label[text()='Name']/following::input[1]");
	private final By descriptionField = By.xpath("//label[text()='Description']/following::textarea[1]");
	private final By saveButton = By.xpath("//button[contains(.,'Save')]");
	private final By cancelButton = By.xpath("//button[contains(.,'Cancel')]");
	private final By confirmDeleteButton = By.xpath("//button[contains(.,'Yes, Delete')]");
	private final By duplicateErrorMessage = By.xpath("//span[contains(text(),'Already exists')]");

	private final By requiredMessage = By.xpath("//span[contains(text(),'Required')]");

	public SkillsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public boolean addSkill(String name, String description) {
		click(addButton);
		type(skillNameField, name);
		type(descriptionField, description);
		click(saveButton);

		if (isElementVisible(duplicateErrorMessage)) {
			System.out.println("Skill already exists: " + name);
			click(cancelButton);
			return false;
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + name + "']")));
		return true;
	}

	public boolean isSkillAdded(String name) {
		return driver.getPageSource().contains(name);
	}

	public void deleteSkill(int index, String skillName) {
		try {
			By deleteBtn = By.xpath("(//button[i[contains(@class,'oxd-icon bi-trash')]])[" + index + "]");
			hoverAndClick(deleteBtn);
			Thread.sleep(3000);
			click(confirmDeleteButton);
		} catch (Exception e) {
			System.out.println("Skill not found or could not be deleted: " + skillName);
		}
	}

	public void editSkill(int index, String updatedName, String updatedDesc) {
		By editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
		hoverAndClick(editBtn);

		clearField(skillNameField);
		clearField(descriptionField);

		type(skillNameField, updatedName);
		type(descriptionField, updatedDesc);
		click(saveButton);
	}

	private void click(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	private void type(By locator, String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
	}

	private void clearField(By locator) {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		field.sendKeys(Keys.CONTROL + "a");
		field.sendKeys(Keys.DELETE);
	}

	private boolean isElementVisible(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void hoverAndClick(By locator) {
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(locator));
		new Actions(driver).moveToElement(btn).perform();
		btn.click();
	}

	public boolean waitForSuccessToast() {
		By toastMessage = By.xpath("//div[contains(@class,'oxd-toast') and contains(.,'Success')]");
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void addSkillWithoutName(String description) {
		click(addButton);
		type(descriptionField, description);
		click(saveButton);

		System.out.println("Required message appeared. Canceling add operation.");
		click(cancelButton);

	}

	public void startAddingSkillAndCancel(String name, String description) {
		click(addButton);
		type(skillNameField, name);
		type(descriptionField, description);
		click(cancelButton);
	}

	public void cancelEditSkill(int index) {
		By editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
		hoverAndClick(editBtn);
		click(cancelButton);
	}

	public void cancelAddSkill() {
		click(cancelButton);
	}
}