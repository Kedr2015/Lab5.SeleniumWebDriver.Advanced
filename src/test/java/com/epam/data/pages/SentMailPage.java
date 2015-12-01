package com.epam.data.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * @author kedr
 *
 *         Sent Mail page. Locators and work with them
 */
public class SentMailPage extends MainMailPage {
	/**
	 * @param driver
	 *            -webdriver Transfer driver instance in the constructor
	 */
	public SentMailPage(WebDriver driver) {
		super(driver);
	}

	// Locator field name
	@FindBy(xpath = ".//*[@id='messages_frm']/div[3]/div[1]/div[2]/a")
	private WebElement subjectAndTextMail;

	// Locator field name
	@FindBy(xpath = ".//*[@id='messages_frm']/div[3]/div[1]/div[1]/a[2]")
	private static WebElement toMail;

	/**
	 * The method checks the messages on the page for the input parameters
	 * 
	 * @param subject
	 *            - subject mail
	 * @param text
	 *            - text mail
	 * @param to
	 *            - to mail
	 * @return - Availability of posts on the page
	 */
	public boolean checkForSent(String subject, String text, String to) {
		try {
			return (subjectAndTextMail.getText().equals(subject + "- " + text) && toMail.getText().equals(" " + to));
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
