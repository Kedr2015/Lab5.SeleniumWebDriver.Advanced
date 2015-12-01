package com.epam.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.TestDataProvider;
import com.epam.data.pages.NewMailPage;
import com.epam.data.pages.SentMailPage;

/**
 * @author kedr
 * 
 *         The test checks the The test checks the Create And Send Message
 *         system
 */
public class CreateAndSendMessageTest extends BaseTest {
	// Create an instance login page
	SentMailPage SentMailPlace;
	// Create an instance new mail page
	NewMailPage newMailPlace;
	// Create an instance new mail page

	@BeforeTest
	public void setUp() {
		SentMailPlace = new SentMailPage(driver);
		newMailPlace = new NewMailPage(driver);
	}

	/**
	 * To validate the creation and send of his letters
	 * 
	 * @param to
	 *            - recipient
	 * @param subject
	 *            - subject mail
	 * @param text
	 *            - text of the letter
	 */
	@Test(dataProvider = "newMailData", dataProviderClass = TestDataProvider.class)
	public void sendMailTest(String to, String subject, String text) {
		System.out.println("Create and send a new message\nWith options:\nRecipient = " + to + "\nSubject = " + subject
				+ "\nText mail = " + text);
		newMailPlace.writeNewLetter().sendMail(to, subject, text).goToSentsMail();
		Assert.assertTrue(SentMailPlace.checkForSent(subject, text, to),
				"The letter is not send\nWith options:\nRecipient = " + to + "\nSubject = " + subject + "\nText mail = "
						+ text);
	}

}
