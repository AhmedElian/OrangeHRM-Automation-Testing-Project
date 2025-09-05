package OrangeHRM.Admin_Module;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EducationPage {

	private final WebDriver driver;
	private final WebDriverWait wait;

	private final By addButton = By.xpath("//button[contains(.,'Add')]");
	private final By educationLevelField = By.xpath("//label[text()='Level']/following::input[1]");
	private final By saveButton = By.xpath("//button[contains(.,'Save')]");
	private final By cancelButton = By.xpath("//button[contains(.,'Cancel')]");
	private final By confirmDeleteButton = By.xpath("//button[contains(.,'Yes, Delete')]");
	private final By duplicateErrorMessage = By.xpath("//span[contains(text(),'Already exists')]");

	public EducationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public boolean addEducationLevel(String level) {
		click(addButton);
		type(educationLevelField, level);
		click(saveButton);

		if (isElementVisible(duplicateErrorMessage)) {
			System.out.println("Education level already exists: " + level);
			click(cancelButton);
			return false;
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + level + "']")));
		return true;
	}

	public boolean isEducationLevelAdded(String level) {
		return driver.getPageSource().contains(level);
	}

	public void deleteEducationLevel(int index, String educationLevel) {
		try {
			By deleteBtn = By.xpath("(//button[i[contains(@class,'oxd-icon bi-trash')]])[" + index + "]");
			hoverAndClick(deleteBtn);
			Thread.sleep(3000);
			click(confirmDeleteButton);
		} catch (Exception e) {
			System.out.println("Education level not found or could not be deleted: " + educationLevel);
		}
	}

	public void editEducationLevel(int index, String updatedLevel) {
		By editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
		hoverAndClick(editBtn);

		clearField(educationLevelField);
		type(educationLevelField, updatedLevel);
		click(saveButton);
	}

	public void addEducationLevelWithoutName() {
		click(addButton);
		click(saveButton);

		System.out.println("Required message appeared. Canceling add operation.");
		click(cancelButton);

	}

	public void startAddingEducationLevelAndCancel(String level) {
		click(addButton);
		type(educationLevelField, level);
		click(cancelButton);
	}

	public void cancelEditEducationLevel(int index) {
		By editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
		hoverAndClick(editBtn);
		click(cancelButton);
	}

	public void deleteAllEducationLevelsUsingSelectAll() {
		try {

			By selectAllCheckbox = By
					.className("oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input");

			By bulkDeleteButton = By.xpath("//button[i[contains(@class,'bi-trash')]]");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));

			WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(selectAllCheckbox));
			checkbox.click();

			wait.until(ExpectedConditions.elementToBeClickable(bulkDeleteButton)).click();

			wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();

			wait.until(ExpectedConditions.invisibilityOfElementLocated(
					By.xpath("//div[@role='row' and contains(@class,'oxd-table-card')]")));

			System.out.println("All education levels deleted successfully.");

		} catch (Exception e) {
			System.out.println("Failed to delete all education levels: " + e.getMessage());
		}
	}

	public void cancelAddEducationLevel() {
		click(cancelButton);
	}

	public boolean waitForSuccessToast() {
		By toastMessage = By
				.xpath("//div[contains(@class,'oxd-toast') and (contains(.,'Success') or contains(.,'Successfully'))]");

		try {
			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

			WebElement toast = shortWait.until(ExpectedConditions.presenceOfElementLocated(toastMessage));

			String message = toast.getText().trim();
			System.out.println("Toast message: " + message);

			return message.contains("Success");
		} catch (TimeoutException e) {
			System.out.println("Success toast did not appear in time.");
			return false;
		}
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
}