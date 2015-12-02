package com.epam.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.epam.XmlUtils;
import com.epam.pages.LoginPage;
import com.epam.pages.MainMailPage;

import org.w3c.dom.Element;
import java.util.concurrent.TimeUnit;

/**
 * @author kedr
 * 
 *         Parsing a file with the parameters and the choice of driver
 */
public class BaseTest {

	protected WebDriver driver;
	protected Element file;

	/**
	 * Driver selection
	 * 
	 * @param name
	 *            browser
	 */
	public void driverSelection(String browser) {
		switch (browser) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			throw new RuntimeException("Browser is not selected");
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeTest
	@Parameters({ "browserName" })
	public void startTest(@Optional("firefox") String browser) {
		driverSelection(browser);
		file = XmlUtils.capturingFileXML();
		driver.get(XmlUtils.getParameterFromXML("url", file));
	}

	@BeforeTest(dependsOnMethods = "startTest")
	public void test() {
		new LoginPage(driver).loginMetod(XmlUtils.initializationUser(file));
	}

	/**
	 * Actions after the test class
	 */
	@AfterTest
	public void closeBrowser() {
		// Sign Out
		new MainMailPage(driver).exitSystem();
		// Close Browser
		driver.close();

	}

}
