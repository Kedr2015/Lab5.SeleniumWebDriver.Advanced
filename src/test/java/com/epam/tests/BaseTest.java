package com.epam.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.epam.ui.XmlUtils;
import com.epam.ui.pages.LoginPage;
import com.epam.ui.pages.MainMailPage;
import com.epam.ui.webdriver.Driver;

import org.w3c.dom.Element;

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
    public void driverSelection() {
	driver = Driver.getWebDriverInstance();
    }

    @BeforeTest
    public void startTest() {
	driverSelection();
	file = XmlUtils.parseFileXML();
	driver.get(XmlUtils.getParameterFromXML("url", file));
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

    @Test
    public void test1() {
	System.out.println("Checking login to the system.");
	boolean openMainPage = new LoginPage(driver).loginMetod(XmlUtils.initializationUser(file)).IsExitButon();
	Assert.assertTrue(openMainPage, "Login not implemented");
    }

}
