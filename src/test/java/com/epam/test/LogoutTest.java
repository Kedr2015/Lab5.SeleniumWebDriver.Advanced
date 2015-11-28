package com.epam.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.date.StartTest;
import com.epam.date.TestDataProvider;
import com.epam.pages.LoginPage;
import com.epam.pages.MainMailPage;

/**
 * @author kedr
 * 
 *         The test checks the Logout system
 */
public class LogoutTest {
	MainMailPage mailMailPlace;
	LoginPage loginPlace;

	/**
	 * Actions before starting the test class
	 */
	@BeforeTest
	public void startBrowser() {
		// Initialization driver and date
		StartTest.start();
		// Create an instance of the Main Mail page
		mailMailPlace = PageFactory.initElements(StartTest.driver, MainMailPage.class);
		// Create an instance of the login page
		loginPlace = PageFactory.initElements(StartTest.driver, LoginPage.class);
		// Time waiting objects on the page
		StartTest.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Open the window
		StartTest.driver.manage().window().maximize();
		// Going to pages
		StartTest.driver.get(TestDataProvider.url);
		// Enter login
		loginPlace.inputName(TestDataProvider.login);
		// Enter password
		loginPlace.inputPassword(TestDataProvider.password);
		// Log in
		loginPlace.pressButtonInput();
		// Go to home page mail
		loginPlace.pressButtonOpenMail();
	}

	/**
	 * Actions after the test class
	 */
	@AfterTest
	public void closeBrowser() {
		// Close Browser
		StartTest.driver.close();
	}

	/**
	 * The test checks the Logout system
	 */
	@Test
	public void logout() {
		System.out.println("Test 4 Logout from system");
		mailMailPlace.signOut();
		Assert.assertTrue(loginPlace.isUserNameDisplayed(), "Logon failure occurred");
	}
}
