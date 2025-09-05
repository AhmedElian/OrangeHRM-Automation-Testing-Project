package OrangeHRM.Admin_Module;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrangeHRM_User_Management_Test extends OrangeHRM_Test_Base {
	private OrangeHRM_User_Management orangeHRM_User_Management;
	private OrangeHRM_Login_Page orangeHRM_Login_Page;

	@BeforeMethod
	public void setup_2() {
		driver.manage().deleteAllCookies();
		driver.get(getBaseUrl());

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")));

		orangeHRM_User_Management = new OrangeHRM_User_Management(driver);
		orangeHRM_Login_Page = new OrangeHRM_Login_Page(driver);
		orangeHRM_Login_Page.login("Admin", "admin123");
	}

	@Test(priority = 1, groups = "search", dataProvider = "Search_Test_Data", dataProviderClass = OrangeHRM_Read_CSV.class)
	public void performSearch(String username, String userRole, String employeeName, String status, boolean expected)
			throws InterruptedException {
		Reporter.log("Starting Login Test With Username: " + username + " User Role: " + userRole + " Employee Name: "
				+ employeeName + " Status: " + status, true);

		orangeHRM_User_Management.performSearch(username, userRole, employeeName, status);
		boolean result = !orangeHRM_User_Management.isNoRecordsFoundMessageDisplayed();

		Assert.assertEquals(result, expected, "Login result did not match expected!");
		Reporter.log("Test Completed For Username: " + username + " | Expected: " + expected + " | Got: " + result,
				true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 2, groups = "reset")
	public void resetSearch() throws InterruptedException {
		Reporter.log("Starting Reset Search Test", true);

		orangeHRM_User_Management.performFullReset("Admin", "Admin", "Alice Duval", "Enabled");
		boolean isReset = orangeHRM_User_Management.checkUsernameField();
		Assert.assertTrue(isReset, "Search form was not reset properly!");
		Reporter.log("Reset Search Test Completed | Form Reset: " + isReset, true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 3, groups = "reset")
	public void resetSearchWithoutData() throws InterruptedException {
		Reporter.log("Starting Reset Search Without Data Test", true);

		orangeHRM_User_Management.performEmptyReset();
		boolean isReset = orangeHRM_User_Management.checkUsernameField();
		Assert.assertTrue(isReset, "Search form was not reset properly!");
		Reporter.log("Reset Search Without Data Test Completed | Form Reset: " + isReset, true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 4, groups = "delete")
	public void deleteSelectedUsers() throws TimeoutException {
		Reporter.log("Starting Delete Selected Users Test", true);

		orangeHRM_User_Management.deleteAllRecords();
		boolean recordCheckAfterDelete = !orangeHRM_User_Management.checkResultAfterDeleted();
		Assert.assertFalse(recordCheckAfterDelete, "User was not deleted!");
		Reporter.log("Delete Selected Users Test Completed | User Present: " + recordCheckAfterDelete, true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 5, groups = "record count")
	public void addUserRecordCountCheck() throws InterruptedException {
		Reporter.log("Starting Add User Record Count Test", true);
		Reporter.log("Record Count Now Is (1)", true);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		orangeHRM_User_Management.addUser("Admin", "Ahmed Elian", "Enabled", "userFoCheckRecordsFound", "Password@123",
				"Password@123");

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")));

		String afterAddText = orangeHRM_User_Management.getRecordsFoundText();
		Assert.assertTrue(afterAddText.contains("(2) Records Found"),
				"Expected '(2) Records Found' but got: " + afterAddText);

		Reporter.log("After Add: " + afterAddText, true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 6, groups = "record count")
	public void deleteUserRecordCountCheck() {
		Reporter.log("Starting Delete User Record Count Test", true);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		orangeHRM_User_Management.deleteByAction("userFoCheckRecordsFound");

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")));

		String afterDeleteText = orangeHRM_User_Management.getRecordsFoundText();
		Assert.assertTrue(afterDeleteText.contains("(1) Record Found"),
				"Expected '(1) Record Found' but got: " + afterDeleteText);

		Reporter.log("After Delete: " + afterDeleteText, true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 7, groups = "add", dataProvider = "Add_Test_Data", dataProviderClass = OrangeHRM_Read_CSV.class)
	public void addUser(String userRole, String employeeName, String status, String username, String password,
			String confirmPassword, boolean expected) throws InterruptedException {
		Reporter.log("Starting Add User Test With User Role: " + userRole + " Employee Name: " + employeeName
				+ " Status: " + status + " Username: " + username, true);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			WebElement myInfoMenu = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[6]/a/span")));
			myInfoMenu.click();

			WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[1]/div[2]/input")));
			WebElement midName = driver.findElement(By.xpath(
					"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[2]/div[2]/input"));
			WebElement lastName = driver.findElement(By.xpath(
					"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[3]/div[2]/input"));
			WebElement saveButton = driver.findElement(
					By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button"));

			firstName.click();
			firstName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			firstName.sendKeys(Keys.DELETE);
			firstName.sendKeys("Ahmed");

			midName.click();
			midName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			midName.sendKeys(Keys.DELETE);

			lastName.click();
			lastName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			lastName.sendKeys(Keys.DELETE);
			lastName.sendKeys("Elian");

			saveButton.click();

			Reporter.log("My Info updated successfully", true);
		} catch (Exception e) {
			Reporter.log("Failed to update My Info: " + e.getMessage(), true);
		}

		orangeHRM_User_Management.addUser(userRole, employeeName, status, username, password, confirmPassword);

		boolean result = orangeHRM_User_Management.isAddUserSuccessMessageDisplayed(username);
		Assert.assertEquals(result, expected, "Add User result did not match expected!");
		Reporter.log("Test Completed For Username: " + username + " | Expected: " + expected + " | Got: " + result,
				true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 8, groups = "delete")
	public void deleteSingleUser() {
		Reporter.log("Starting Delete Single User Test", true);

		orangeHRM_User_Management.deleteByAction("IAMnewUSERforTEST2");
		boolean isDeleted = orangeHRM_User_Management.isUserDeleted("IAMnewUSERforTEST2");

		Assert.assertTrue(isDeleted, "User was not deleted!");
		Reporter.log("Delete Single User Test Completed | User Deleted: " + isDeleted, true);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test(priority = 9, groups = "edit", dataProvider = "Edit_Test_Data", dataProviderClass = OrangeHRM_Read_CSV.class)
	public void editUser(String userRole, String employeeName, String status, String username, String password,
			String confirmPassword, boolean expected) throws InterruptedException {
		Reporter.log("Starting Edit User Test For Username: " + username, true);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			orangeHRM_User_Management.deleteByAction("NewFromAhmed");
			Reporter.log("User deleted successfully", true);
		} catch (Exception e) {
			Reporter.log("User deletion failed or user does not exist: " + e.getMessage(), true);
		}

		try {
			WebElement myInfoMenu = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[6]/a/span")));
			myInfoMenu.click();

			WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[1]/div[2]/input")));
			WebElement midName = driver.findElement(By.xpath(
					"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[2]/div[2]/input"));
			WebElement lastName = driver.findElement(By.xpath(
					"//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[3]/div[2]/input"));
			WebElement saveButton = driver.findElement(
					By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button"));

			firstName.click();
			firstName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			firstName.sendKeys(Keys.DELETE);
			firstName.sendKeys("Ahmed");

			midName.click();
			midName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			midName.sendKeys(Keys.DELETE);

			lastName.click();
			lastName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			lastName.sendKeys(Keys.DELETE);
			lastName.sendKeys("Elian");

			saveButton.click();

			Reporter.log("My Info updated successfully", true);
		} catch (Exception e) {
			Reporter.log("Failed to update My Info: " + e.getMessage(), true);
		}

		orangeHRM_User_Management.editUser(userRole, employeeName, status, username, password, confirmPassword);

		boolean result = orangeHRM_User_Management.isEditUserSuccessMessageDisplayed(username);
		Assert.assertEquals(result, expected, "Edit User result did not match expected!");

		Reporter.log(
				"Test Completed For Edited Username: " + username + " | Expected: " + expected + " | Got: " + result,
				true);
		System.out.println("----------------------------------------------------------------------");
	}
}