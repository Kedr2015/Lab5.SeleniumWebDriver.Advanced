package com.epam.tests;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.epam.User;
import com.epam.data.pages.LoginPage;
import com.epam.data.pages.MainMailPage;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author kedr
 * 
 *         Parsing a file with the parameters and the choice of driver
 */
public class BaseTest {

	protected WebDriver driver;

	/**
	 * Selecting a file for piercing
	 * 
	 * @return Element
	 */
	private Element capturingFileXML() {
		try {
			File fXmlFile = new File(System.getProperty("user.dir") + "/src/main/resources/TestRun.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("date");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					return eElement;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Create User
	 * 
	 * @param eElement
	 * @return UserTest
	 */
	private User initializationUser(Element eElement) {
		User UserTest = new User(eElement);
		return UserTest;
	}

	/**
	 * Parsing XML
	 * 
	 * @param parametr
	 * @param eElement
	 * @return string
	 */
	private String getPametrFromXML(String parametr, Element eElement) {
		return eElement.getElementsByTagName(parametr).item(0).getTextContent();
	}

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
					System.getProperty("user.dir") + "/src/main/resources/chromedriver");
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
		driver.get(getPametrFromXML("url", capturingFileXML()));
	}

	@BeforeTest(dependsOnMethods = "startTest")
	public void test() {
		new LoginPage(driver).loginMetod(initializationUser(capturingFileXML()));
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
