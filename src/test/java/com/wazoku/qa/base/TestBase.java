package com.wazoku.qa.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public abstract class TestBase {

	protected static final String LOGIN_EMAIL_CONFIG_NAME = "login.email";
	protected static final String LOGIN_PASSWORD_CONFIG_NAME = "login.password";
	protected static final String HOME_PAGE_URL = "url.homepage";
	protected static final String DISCOVER_PAGE_URL = "url.discoverpage";

	private WebDriver driver;

	public void setUp() {
		initializeDriver();
	}

	private void initializeDriver() {
		String browserName = AppConfig.getConfigValue("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", AppConfig.getConfigValue("chrome.driver.location"));
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", AppConfig.getConfigValue("firefox.driver.location"));
			driver = new FirefoxDriver();
		}

		driver.manage()
				.window()
				.maximize();
		driver.manage()
				.deleteAllCookies();
		driver.manage()
				.timeouts()
				.pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage()
				.timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);

		driver.get(AppConfig.getConfigValue("url"));
	}

	protected WebDriver getDriver() {
		return driver;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
