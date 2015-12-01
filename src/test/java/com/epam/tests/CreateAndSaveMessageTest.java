package com.epam.tests;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.TestDataProvider;
import com.epam.pages.NewMailPage;

/**
 * @author kedr
 * 
 *         The test checks the Create And Save Message system
 */
public class CreateAndSaveMessageTest extends BaseTest {

	// Create an instance new mail page
	private NewMailPage newMailPage;

	/**
	 * Actions before starting the test class
	 */
	@BeforeTest
	public void setUp() {
		newMailPage = new NewMailPage(driver);
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
		String actualSubjectAndTextMail = newMailPage.writeNewLetter().saveMail(to, subject, text).goToDraftsMail()
				.getSubjectAndTextMail();
		String expectedSubjectAndTextMail = subject + "- " + text;
		Assert.assertEquals(actualSubjectAndTextMail, expectedSubjectAndTextMail,
				"The draft has not been preserved\nWith options:\nRecipient = " + to + "\nSubject = " + subject
						+ "\nText mail = " + text);
	}

}
