package com.epam.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author kedr
 *
 *         New Mail page. Locators and work with them
 */
public class NewMailPage extends MainMailPage {

	/**
	 * @param driver
	 *            -webdriver Transfer driver instance in the constructor
	 */
	public NewMailPage(WebDriver driver) {
		super(driver);
	}

	// The send of the letter
	@FindBy(xpath = ".//div[1][@class='send']//button[@class='inline']")
	private WebElement sendToMail;
	// The recipient of the letter
	@FindBy(xpath = ".//*[@id='to']")
	private WebElement toMail;
	// subject mail
	@FindBy(xpath = ".//*[@id='subject']")
	private WebElement subjectMail;
	// Text mail
	@FindBy(xpath = ".//*[@id='compose_text']")
	private WebElement textMail;
	// Conservation Letters to drafts
	@FindBy(xpath = ".//div[@class='textfield']//a[@class='compose-save']")
	private WebElement saveNewMailButton;

	/**
	 * The method of writing a new message
	 * 
	 */
	public void writterMail(String to, String subject, String text) {
		toMail.sendKeys(to);
		subjectMail.sendKeys(subject);
		textMail.sendKeys(text);
	}

	/**
	 * The method of send a new message
	 * 
	 */
	public MainMailPage sendMail(String to, String subject, String text) {
		writterMail(to, subject, text);
		sendToMail.click();
		checkDialogBox();
		return new MainMailPage(driver);
	}

	/**
	 * The method of save a new message
	 * 
	 */
	public NewMailPage saveMail(String to, String subject, String text) {
		writterMail(to, subject, text);
		saveNewMailButton.click();
		return this;
	}

}
