package OrangeHRM.Admin_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_Test_Base {
	protected WebDriver driver;

	private static String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	public String getBaseUrl() {
		return baseUrl;
	}

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(2000);
			driver.quit();
		}
	}
}