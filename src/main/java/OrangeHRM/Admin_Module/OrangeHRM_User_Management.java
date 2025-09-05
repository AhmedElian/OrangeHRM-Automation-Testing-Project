package OrangeHRM.Admin_Module;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangeHRM_User_Management {

	private WebDriver driver;

	private By adminFeture = By.xpath("//div[@id='app']/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a");
	private By searchButton = By.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
	private By resetButton = By.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]");
	private By usernameField = By
			.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input");
	private By userRoleList = By.xpath(
			"//div[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]");
	private By employeeNameField = By.xpath(
			"//div[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/input");
	private By statusList = By.xpath(
			"//div[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]");

	private By selectAllRecordsCheckbox = By
			.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[1]/div/div[1]/div/label/span");
	private By deleteSelectedButton = By
			.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/button");
	private By confirmDeleteButton = By.xpath("//div[@id='app']/div[3]/div/div/div/div[3]/button[2]");
	private By cancelDeleteButton = By.xpath("//div[@id='app']/div[3]/div/div/div/div[3]/button[1]");
	private By checkIfAllRecordsDeleted = By.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span");
	private By checkRecordMessage = By
			.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/span");

	private By addButton = By.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
	private By addUserRoleList = By
			.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[2]/i");
	private By addEmployeeNameField = By
			.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input");
	private By addUsernameField = By
			.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
	private By addStatusList = By
			.xpath("//div[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]");
	private By addPasswordField = By
			.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
	private By addConfirmPasswordField = By
			.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
	private By saveButton = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");
	private By cancelButton = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[3]/button[1]");

	private By confirmDeleteByActionButton = By.xpath("//div[@id='app']/div[3]/div/div/div/div[3]/button[2]");

	private By editUserRoleList = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]");
	private By editEmployeeNameField = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input");
	private By editStatusList = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]");
	private By editUsernameField = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
	private By editChangePasswordCheckbox = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[5]/div/div[2]/div/label/span");
	private By editPasswordField = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
	private By editConfirmPasswordField = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
	private By editSaveButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");

	public OrangeHRM_User_Management(WebDriver driver) {
		this.driver = driver;
	}

	public void chooseUserRoleFromEditDropdown(String userRole) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(editUserRoleList)).click();
		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(userRole)) {
				option.click();
				break;
			}
		}
	}

	public void enterEmployeeNameInEditField(String employeeName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(editEmployeeNameField));
		field.click();
		field.sendKeys(Keys.CONTROL + "a");
		field.sendKeys(Keys.DELETE);
		if (employeeName == null || employeeName.trim().isEmpty()) {
			return;
		}
		field.sendKeys(employeeName);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
		field.sendKeys(Keys.ARROW_DOWN);
		field.sendKeys(Keys.ENTER);
	}

	public void chooseFromEditStatusDropdown(String status) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(editStatusList)).click();
		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(status)) {
				option.click();
				break;
			}
		}
	}

	public void enterUsernameInEditField(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(editUsernameField));
		field.click();
		field.sendKeys(Keys.CONTROL + "a");
		field.sendKeys(Keys.DELETE);
		field.sendKeys(username);
	}

	public void clickChangePasswordCheckbox() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(editChangePasswordCheckbox));
		driver.findElement(editChangePasswordCheckbox).click();
	}

	public void enterPasswordInEditField(String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(editPasswordField));
		field.click();
		field.sendKeys(Keys.CONTROL + "a");
		field.sendKeys(Keys.DELETE);
		field.sendKeys(password);
	}

	public void enterConfirmPasswordInEditField(String confirmPassword) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(editConfirmPasswordField));
		field.click();
		field.sendKeys(Keys.CONTROL + "a");
		field.sendKeys(Keys.DELETE);
		field.sendKeys(confirmPassword);
	}

	public void clickEditSaveButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(editSaveButton));
		driver.findElement(editSaveButton).click();
	}

	public void clickAdminFeature() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(adminFeture));
		driver.findElement(adminFeture).click();
	}

	public void enterUsernameInSearchField(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
		driver.findElement(usernameField).clear();
		driver.findElement(usernameField).sendKeys(username);
	}

	public void chooseUserRoleFromDropdown(String userRole) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(userRoleList)).click();
		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(userRole)) {
				option.click();
				break;
			}
		}
	}

	public void chooseFromEmployeeNameDropdown(String employeeName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement field = wait.until(ExpectedConditions.elementToBeClickable(employeeNameField));
		field.click();
		field.sendKeys(employeeName);
		Thread.sleep(2000);
		field.sendKeys(Keys.ARROW_DOWN);
		field.sendKeys(Keys.ENTER);
	}

	public void chooseFromStatusDropdown(String status) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(statusList)).click();
		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(status)) {
				option.click();
				break;
			}
		}
	}

	public void clickSearchButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		driver.findElement(searchButton).click();
	}

	public void clickResetButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(resetButton));
		driver.findElement(resetButton).click();
	}

	public void selectAllRecordsCheckbox() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(selectAllRecordsCheckbox));
		driver.findElement(selectAllRecordsCheckbox).click();
	}

	public void clickDeleteSelectedButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedButton));
		driver.findElement(deleteSelectedButton).click();
	}

	public void clickConfirmDeleteButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
		driver.findElement(confirmDeleteButton).click();
	}

	public void clickCancelDeleteButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(cancelDeleteButton));
		driver.findElement(cancelDeleteButton).click();
	}

	public void clickAddButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(addButton));
		driver.findElement(addButton).click();
	}

	public void chooseUserRoleFromAddDropdown(String userRole) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(addUserRoleList)).click();
		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(userRole)) {
				option.click();
				break;
			}
		}
	}

	public void enterEmployeeNameInAddField(String employeeName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(addEmployeeNameField));
		field.clear();
		if (employeeName == null || employeeName.trim().isEmpty()) {
			return;
		}
		field.sendKeys(employeeName);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
		field.sendKeys(Keys.ARROW_DOWN);
		field.sendKeys(Keys.ENTER);
	}

	public void chooseFromAddStatusDropdown(String status) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(addStatusList)).click();
		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(status)) {
				option.click();
				break;
			}
		}
	}

	public void enterUsernameInAddField(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(addUsernameField));
		driver.findElement(addUsernameField).clear();
		driver.findElement(addUsernameField).sendKeys(username);
	}

	public void enterPasswordInAddField(String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(addPasswordField));
		driver.findElement(addPasswordField).clear();
		driver.findElement(addPasswordField).sendKeys(password);
	}

	public void enterConfirmPasswordInAddField(String confirmPassword) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(addConfirmPasswordField));
		driver.findElement(addConfirmPasswordField).clear();
		driver.findElement(addConfirmPasswordField).sendKeys(confirmPassword);
	}

	public void clickSaveButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(saveButton));
		driver.findElement(saveButton).click();
	}

	public void clickCancelButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
		driver.findElement(cancelButton).click();
	}

	public void clickDeleteByActionButton(String username) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		By deleteByActionButton = By
				.xpath("//div[@role='table']//div[@role='row'][div[@role='cell'][contains(normalize-space(.), '"
						+ username + "')]]//*[@class='oxd-icon bi-trash']");
		wait.until(ExpectedConditions.elementToBeClickable(deleteByActionButton));
		driver.findElement(deleteByActionButton).click();
	}

	public void clickConfirmDeleteByActionButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteByActionButton));
		driver.findElement(confirmDeleteByActionButton).click();
	}

	public String getRecordsFoundText() {
		By recordsFoundLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement recordsText = wait.until(ExpectedConditions.visibilityOfElementLocated(recordsFoundLocator));
		return recordsText.getText().trim();
	}

	public boolean isNoRecordsFoundMessageDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement noRecordsMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(checkRecordMessage));
			return noRecordsMsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getIfAllRecordsDeleted() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(checkIfAllRecordsDeleted));
		return result.getText();
	}

	public boolean isUserPresent() throws TimeoutException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteSelectedButton));
		return true;
	}

	public boolean checkResultAfterDeleted() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement recordMsg = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='(1) Record Found']")));
			return recordMsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean checkUsernameField() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
		String fieldValue = driver.findElement(usernameField).getAttribute("value");
		return fieldValue.isEmpty();
	}

	public boolean isAddUserSuccessMessageDisplayed(String username) {
		try {
			By addUserSuccessMessage = By
					.xpath("//div[@role='table']//div[@role='row']//div[@role='cell'][contains(normalize-space(.), '"
							+ username + "')]");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(addUserSuccessMessage));
			return successMsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isUserDeleted(String username) {
		try {
			By deletedUserRow = By
					.xpath("//div[@role='table']//div[@role='row'][div[@role='cell'][contains(normalize-space(.), '"
							+ username + "')]]");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(deletedUserRow));
		} catch (Exception e) {
			return true;
		}
	}

	public boolean isEditUserSuccessMessageDisplayed(String username) {
		try {
			By editUserSuccessMessage = By
					.xpath("//div[@role='table']//div[@role='row']//div[@role='cell'][contains(normalize-space(.), '"
							+ username + "')]");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(editUserSuccessMessage));
			return successMsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void performSearch(String username, String userRole, String employeeName, String status)
			throws InterruptedException {
		clickAdminFeature();
		enterUsernameInSearchField(username);
		chooseUserRoleFromDropdown(userRole);
		chooseFromEmployeeNameDropdown(employeeName);
		chooseFromStatusDropdown(status);
		driver.findElement(By.xpath(
				"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[1]/label"))
				.click();
		clickSearchButton();
	}

	public void performFullReset(String username, String userRole, String employeeName, String status)
			throws InterruptedException {
		clickAdminFeature();
		enterUsernameInSearchField(username);
		chooseUserRoleFromDropdown(userRole);
		chooseFromEmployeeNameDropdown(employeeName);
		chooseFromStatusDropdown(status);
		clickResetButton();
	}

	public void performEmptyReset() {
		clickAdminFeature();
		clickResetButton();
	}

	public void deleteAllRecords() {
		clickAdminFeature();
		selectAllRecordsCheckbox();
		clickDeleteSelectedButton();
		clickConfirmDeleteButton();
	}

	public void cancelDeleteAllRecords() {
		clickAdminFeature();
		selectAllRecordsCheckbox();
		clickDeleteSelectedButton();
		clickCancelDeleteButton();
	}

	public void addUser(String userRole, String employeeName, String status, String username, String password,
			String confirmPassword) throws InterruptedException {
		clickAdminFeature();
		clickAddButton();
		chooseUserRoleFromAddDropdown(userRole);
		enterEmployeeNameInAddField(employeeName);
		chooseFromAddStatusDropdown(status);
		enterUsernameInAddField(username);
		enterPasswordInAddField(password);
		enterConfirmPasswordInAddField(confirmPassword);
		clickSaveButton();
	}

	public void deleteByAction(String username) {
		clickAdminFeature();
		clickDeleteByActionButton(username);
		clickConfirmDeleteByActionButton();
	}

	public void editUser(String userRole, String employeeName, String status, String username, String password,
			String confirmPassword) throws InterruptedException {
		OrangeHRM_User_Management orangeHRM = new OrangeHRM_User_Management(driver);
		orangeHRM.clickAdminFeature();
		orangeHRM.addUser("Admin", "Ahmed Elian", "Enabled", "NewFromAhmed", "admin123", "admin123");
		By editByActionButton = By.xpath(
				"//div[@role='row'][.//div[@role='cell' and contains(normalize-space(.), 'NewFromAhmed')]]//button[@class='oxd-icon-button oxd-table-cell-action-space']//i[contains(@class,'bi-pencil-fill')]");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(editByActionButton));
		driver.findElement(editByActionButton).click();
		orangeHRM.chooseUserRoleFromEditDropdown(userRole);
		orangeHRM.enterEmployeeNameInEditField(employeeName);
		orangeHRM.chooseFromEditStatusDropdown(status);
		orangeHRM.enterUsernameInEditField(username);
		orangeHRM.clickChangePasswordCheckbox();
		orangeHRM.enterPasswordInEditField(password);
		orangeHRM.enterConfirmPasswordInEditField(confirmPassword);
		orangeHRM.clickEditSaveButton();
	}
}