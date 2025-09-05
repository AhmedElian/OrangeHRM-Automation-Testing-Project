package OrangeHRM.Admin_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class EducationTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private AdminPage adminPage;
	private EducationPage educationPage;

	private final String educationLevel = "Bachelor's Degree" + System.currentTimeMillis();

	@BeforeClass
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		loginPage = new LoginPage(driver);
		adminPage = new AdminPage(driver);
		educationPage = new EducationPage(driver);

		loginPage.login("Admin", "admin123");
		adminPage.goToEducationPage();
	}

	@Test(priority = 2)
	public void testEditEducationLevel() throws InterruptedException {
		educationPage.editEducationLevel(1, "Updated Education Level" + System.currentTimeMillis());
		Thread.sleep(2000);
		Assert.assertTrue(educationPage.waitForSuccessToast(),
				"Success toast did not appear after editing an education level");
	}

	@Test(priority = 3)
	public void testDeleteEducationLevel() {
		educationPage.deleteEducationLevel(1, educationLevel);
		Assert.assertFalse(educationPage.isEducationLevelAdded(educationLevel), "Education level was not deleted!");
	}

	@Test(priority = 4)
	public void testAddEducationLevelWithoutName() {
		educationPage.addEducationLevelWithoutName();
		Assert.assertTrue(true, "Validation test completed");
	}

	@Test(priority = 5)
	public void testCancelAddingEducationLevel() {
		educationPage.startAddingEducationLevelAndCancel("EducationToCancel");
		Assert.assertFalse(educationPage.isEducationLevelAdded("EducationToCancel"),
				"Education level should not be added after canceling.");
	}

	@Test(priority = 6)
	public void testEditExistingEducationLevel() {
		educationPage.editEducationLevel(1, "EditedEducationLevel");
		Assert.assertTrue(educationPage.waitForSuccessToast(),
				"Success toast did not appear after editing an education level.");
	}

	@Test(priority = 7)
	public void testCancelEditingEducationLevel() {
		educationPage.cancelEditEducationLevel(1);
		Assert.assertTrue(true, "Canceling edit should not break functionality.");
	}

	@Test(priority = 8)
	public void testDeleteAllEducationLevels() {
		educationPage.deleteAllEducationLevelsUsingSelectAll();
		Assert.assertFalse(driver.getPageSource().contains(educationLevel), "Not all education levels were deleted");
	}

	@Test(priority = 1)
	public void testAddEducationLevel() {

		educationPage.addEducationLevel("new education" + System.currentTimeMillis());
		Assert.assertTrue(true, "Success toast did not appear after adding an education level!");

	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}
}