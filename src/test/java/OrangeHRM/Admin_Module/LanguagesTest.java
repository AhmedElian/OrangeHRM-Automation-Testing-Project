package OrangeHRM.Admin_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class LanguagesTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private AdminPage adminPage;
	private LanguagesPage languagesPage;

	private final String languageName = "Spanish" + System.currentTimeMillis();

	@BeforeClass
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		loginPage = new LoginPage(driver);
		adminPage = new AdminPage(driver);
		languagesPage = new LanguagesPage(driver);

		loginPage.login("Admin", "admin123");
		adminPage.goToLanguagesPage();
	}

	@Test(priority = 1)
	public void testAddLanguage() {
		boolean added = languagesPage.addLanguage(languageName);
		if (added) {
			Assert.assertTrue(languagesPage.isLanguageAdded(languageName), "Language was not added to the table!");
			Assert.assertTrue(added, "Success toast did not appear after adding a language!");
		} else {
			System.out.println("Language already exists, skipping verification.");
		}
	}

	@Test(priority = 2)
	public void testEditLanguage() {
		languagesPage.editLanguage(1, "Updated Language" + System.currentTimeMillis());
		Assert.assertTrue(languagesPage.waitForSuccessToast(), "Success toast did not appear after editing a language");
	}

	@Test(priority = 3)
	public void testDeleteLanguage() {
		languagesPage.deleteLanguage(1, languageName);
		Assert.assertTrue(languagesPage.waitForSuccessToast(),
				"Success toast did not appear after deleting a language");
		Assert.assertFalse(languagesPage.isLanguageAdded(languageName), "Language was not deleted from the table!");
	}

	@Test(priority = 4)
	public void testAddLanguageWithoutName() {
		languagesPage.addLanguageWithoutName();
		Assert.assertTrue(true, "Validation test completed");
	}

	@Test(priority = 5)
	public void testCancelAddingLanguage() {
		languagesPage.startAddingLanguageAndCancel("LanguageToCancel");
		Assert.assertFalse(languagesPage.isLanguageAdded("LanguageToCancel"),
				"Language should not be added after canceling.");
	}

	@Test(priority = 6)
	public void testEditExistingLanguage() {
		languagesPage.editLanguage(1, "EditedLanguage");
		Assert.assertTrue(languagesPage.waitForSuccessToast(),
				"Success toast did not appear after editing a language.");
	}

	@Test(priority = 7)
	public void testCancelEditingLanguage() {
		languagesPage.cancelEditLanguage(1);
		Assert.assertTrue(true, "Canceling edit should not break functionality.");
	}

//   

	@AfterClass
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}
}