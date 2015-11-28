package com.epam.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.date.StartTest;
import com.epam.date.TestDataProvider;
import com.epam.pages.DraftsMailPage;
import com.epam.pages.LoginPage;
import com.epam.pages.NewMailPage;

/**
 * @author kedr
 * 
 *         The test checks the Create And Save Message system
 */
public class CreateAndSaveMessageTest {

	// Create an instance login page
	LoginPage loginPlace;
	// Create an instance new mail page
	NewMailPage newMailPlace;
	// Create an instance drafts mail page
	DraftsMailPage DraftsMailPlace;

	/**
	 * Actions before starting the test class
	 */
	@BeforeTest
	public void startBrowser() {
		// Initialization driver and date
		StartTest.start();
		// Create an instance new mail page
		newMailPlace = PageFactory.initElements(StartTest.driver, NewMailPage.class);
		// Create an instance drafts mail page
		DraftsMailPlace = PageFactory.initElements(StartTest.driver, DraftsMailPage.class);
		// DraftsMailPlace = new DraftsMailPage(driver);
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
		// Sign Out
		newMailPlace.signOut();
		// Close Browser
		StartTest.driver.close();

	}

	/**
	 * To validate the creation and preservation of his letters
	 * 
	 * @param to
	 *            - recipient
	 * @param subject
	 *            - subject mail
	 * @param text
	 *            - text of the letter
	 */
	@Test(dataProvider = "newMailData", dataProviderClass = TestDataProvider.class)
	public void saveMailTest(String to, String subject, String text) {
		System.out.println("Test 2.1 Create a new message");
		newMailPlace.pressButtonNewMail();
		Assert.assertTrue(newMailPlace.isFieldTo(), "Page to create a new letter is not open");
		System.out.println("Test 2.2 Fill in the fields and save the result\nRecipient = " + to + "\nSubject = "
				+ subject + "\nText mail = " + text);
		newMailPlace.inputToMail(to);
		newMailPlace.inputSubjectMail(subject);
		newMailPlace.inputTextMail(text);
		newMailPlace.saveMail();
		Assert.assertTrue(newMailPlace.isInfoSaveInscription(), "The letter is not saved");
	}

	/**
	 * Check previously saved letter
	 * 
	 * @param to
	 *            - recipient
	 * @param subject
	 *            - subject mail
	 * @param text
	 *            - text of the letter
	 */
	@Test(dataProvider = "newMailData", dependsOnMethods = "saveMailTest", dataProviderClass = TestDataProvider.class)
	public void checkDrafts(String to, String subject, String text) {
		System.out.println(
				"Test 3 Check stored emails\nRecipient = " + to + "\nSubject = " + subject + "\nText mail = " + text);
		newMailPlace.openDrafts();
		Assert.assertTrue(DraftsMailPlace.checkForDrafts(subject, text),
				"The draft has not been preserved\nWith options:\nRecipient = " + to + "\nSubject = " + subject
						+ "\nText mail = " + text);
	}
}
