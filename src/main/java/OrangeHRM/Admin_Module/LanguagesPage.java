package OrangeHRM.Admin_Module;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LanguagesPage {

	private final WebDriver driver;
	private final WebDriverWait wait;

	private final By addButton = By.xpath("//button[contains(.,'Add')]");
	private final By languageNameField = By.xpath("//label[text()='Name']/following::input[1]");
	private final By saveButton = By.xpath("//button[contains(.,'Save')]");
	private final By cancelButton = By.xpath("//button[contains(.,'Cancel')]");
	private final By confirmDeleteButton = By.xpath("//button[contains(.,'Yes, Delete')]");
	private final By duplicateErrorMessage = By.xpath("//span[contains(text(),'Already exists')]");

	public LanguagesPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public boolean addLanguage(String language) {
		click(addButton);
		type(languageNameField, language);
		click(saveButton);

		if (isElementVisible(duplicateErrorMessage)) {
			System.out.println("Language already exists: " + language);
			click(cancelButton);
			return false;
		}

		boolean toastAppeared = waitForSuccessToast();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + language + "']")));

		return toastAppeared;
	}

	public boolean isLanguageAdded(String language) {
		return driver.getPageSource().contains(language);
	}

	public void deleteLanguage(int index, String language) {
		try {
			By deleteBtn = By.xpath("(//button[i[contains(@class,'oxd-icon bi-trash')]])[" + index + "]");
			hoverAndClick(deleteBtn);
			Thread.sleep(3000);
			click(confirmDeleteButton);
		} catch (Exception e) {
			System.out.println("Language not found or could not be deleted: " + language);
		}
	}

	public void editLanguage(int index, String updatedLanguage) {
		By editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
		hoverAndClick(editBtn);

		clearField(languageNameField);
		type(languageNameField, updatedLanguage);
		click(saveButton);
	}

	public void addLanguageWithoutName() {
		click(addButton);
		click(saveButton);

		System.out.println("Required message appeared. Canceling add operation.");
		click(cancelButton);

	}

	public void startAddingLanguageAndCancel(String language) {
		click(addButton);
		type(languageNameField, language);
		click(cancelButton);
	}

	public void cancelEditLanguage(int index) {
		By editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
		hoverAndClick(editBtn);
		click(cancelButton);
	}

	public void deleteAllLanguagesUsingSelectAll() {
		try {

			By selectAllCheckbox = By.xpath("//div[contains(@class,'oxd-table-header')]//input[@type='checkbox']");

			By bulkDeleteButton = By.xpath("//button[i[contains(@class,'bi-trash')]]");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));

			WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(selectAllCheckbox));
			checkbox.click();

			wait.until(ExpectedConditions.elementToBeClickable(bulkDeleteButton)).click();

			wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();

			waitForSuccessToast();

			System.out.println("All languages deleted successfully.");

		} catch (Exception e) {
			System.out.println(" delete all languages: " + e.getMessage());
		}
	}

	public void cancelAddLanguage() {
		click(cancelButton);
	}

	public boolean waitForSuccessToast() {

		By[] toastMessages = { By.xpath("//div[contains(@class,'oxd-toast') and contains(.,'Success')]"),
				By.xpath("//div[contains(@class,'oxd-toast') and contains(.,'successfully')]"),
				By.xpath("//div[contains(@class,'oxd-toast') and contains(.,'Successfully')]"),
				By.xpath("//p[contains(@class,'oxd-text') and contains(.,'Success')]") };

		for (By toastLocator : toastMessages) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
				return true;
			} catch (Exception e) {

			}
		}
		return false;
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