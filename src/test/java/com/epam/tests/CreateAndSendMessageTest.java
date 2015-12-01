package com.epam.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.TestDataProvider;
import com.epam.pages.NewMailPage;

/**
 * @author kedr
 * 
 *         The test checks the The test checks the Create And Send Message
 *         system
 */
public class CreateAndSendMessageTest extends BaseTest {
	// Create an instance new mail page
	private NewMailPage newMailPlace;

	@BeforeTest
	public void setUp() {
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
		String actualSubjectAndTextMail = newMailPlace.writeNewLetter().sendMail(to, subject, text).waitForMessage()
				.goToSentsMail().getSubjectAndTextMailAndTo();
		String expectedSubjectAndTextMail = subject + "- " + text;
		Assert.assertEquals(actualSubjectAndTextMail, expectedSubjectAndTextMail,
				"The letter is not sent\nWith options:\nSubject = " + subject + "\nText mail = " + text);
	}

}
