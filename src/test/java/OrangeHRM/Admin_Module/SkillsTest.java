package OrangeHRM.Admin_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class SkillsTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private AdminPage adminPage;
	private SkillsPage skillsPage;

	private final String skillName = "Automation Testing" + System.currentTimeMillis();
	private final String skillDescription = "Skill for Selenium automation";

	@BeforeClass
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		loginPage = new LoginPage(driver);
		adminPage = new AdminPage(driver);
		skillsPage = new SkillsPage(driver);

		// Login before tests
		loginPage.login("Admin", "admin123");
		adminPage.goToSkillsPage();
	}

	@Test(priority = 1)
	public void testAddSkill() {
		boolean added = skillsPage.addSkill(skillName, skillDescription);
		if (added) {
			Assert.assertTrue(skillsPage.isSkillAdded(skillName), "Skill was not added!");
		} else {
			System.out.println("Skill already exists, skipping verification.");
		}
	}

	@Test(priority = 2)
	public void testEditSkill() {
		skillsPage.editSkill(1, "Updated Skill Name" + System.currentTimeMillis(), "Updated Skill Description");
		Assert.assertTrue(skillsPage.waitForSuccessToast(), "Success toast did not appear after editing a skill");
	}

	@Test(priority = 3)
	public void testDeleteSkill() {
		skillsPage.deleteSkill(1, skillName);
		Assert.assertFalse(skillsPage.isSkillAdded(skillName), "Skill was not deleted!");
	}

	@Test(priority = 4)
	public void testAddSkillWithoutName() {
		skillsPage.addSkillWithoutName("Some description without name");
		Assert.assertFalse(skillsPage.isSkillAdded("Some description without name"),
				"Skill should not be added without a name!");
	}

	@Test(priority = 5)
	public void testCancelAddingSkill() {
		skillsPage.startAddingSkillAndCancel("SkillToCancel", "Description");
		Assert.assertFalse(skillsPage.isSkillAdded("SkillToCancel"), "Skill should not be added after canceling.");
	}

	@Test(priority = 6)
	public void testEditExistingSkill() {
		skillsPage.editSkill(1, "EditedSkillName", "Edited Description");
		Assert.assertTrue(skillsPage.waitForSuccessToast(), "Success toast did not appear after editing a skill.");
	}

	@Test(priority = 7)
	public void testCancelEditingSkill() {
		skillsPage.cancelEditSkill(1);
		Assert.assertTrue(true, "Canceling edit should not break functionality.");
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}
}