package com.epam.tests;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.TestDataProvider;
import com.epam.data.pages.DraftsMailPage;
import com.epam.data.pages.NewMailPage;

/**
 * @author kedr
 * 
 *         The test checks the Create And Save Message system
 */
public class CreateAndSaveMessageTest extends BaseTest {

	// Create an instance new mail page
	NewMailPage newMailPlace;
	// Create an instance drafts mail page
	DraftsMailPage DraftsMailPlace;

	/**
	 * Actions before starting the test class
	 */
	@BeforeTest
	public void setUp() {
		newMailPlace = new NewMailPage(driver);
		DraftsMailPlace = new DraftsMailPage(driver);
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
		System.out.println("Create and save a new message\nWith options:\nRecipient = " + to + "\nSubject = " + subject
				+ "\nText mail = " + text);
		newMailPlace.writeNewLetter().saveMail(to, subject, text).goToDraftsMail();
		Assert.assertTrue(DraftsMailPlace.checkForDrafts(subject, text),
				"The draft has not been preserved\nWith options:\nRecipient = " + to + "\nSubject = " + subject
						+ "\nText mail = " + text);
	}

}
