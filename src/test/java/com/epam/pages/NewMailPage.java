package com.epam.pages;

import org.openqa.selenium.NoSuchElementException;
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
	//

	// The send of the letter
	@FindBy(xpath = ".//div[1][@class='send']//button[@class='inline']")
	WebElement sendToMail;
	// The recipient of the letter
	@FindBy(xpath = ".//*[@id='to']")
	WebElement toMail;
	// subject mail
	@FindBy(xpath = ".//*[@id='subject']")
	WebElement subjectMail;
	// Text mail
	@FindBy(xpath = ".//*[@id='compose_text']")
	WebElement textMail;
	// Conservation Letters to drafts
	@FindBy(xpath = ".//div[@class='textfield']//a[@class='compose-save']")
	WebElement saveNewMailButton;
	// The mark about saving
	@FindBy(xpath = ".//div[@class='header clearfix']//span[@class='compose-draft-info'][contains(text() , 'сохранено')]")
	WebElement infoSave;

	/**
	 * Enter a destination
	 * 
	 * @param to
	 *            - recipient
	 */
	public void inputToMail(String to) {
		builder.moveToElement(toMail).click().sendKeys(to).perform();
	}

	/**
	 * Enter subject
	 * 
	 * @param subject
	 *            - subject mail
	 */
	public void inputSubjectMail(String subject) {
		builder.moveToElement(subjectMail).click().click().sendKeys(subject).perform();
	}

	public void inputTextMail(String text) {
		builder.moveToElement(textMail).click().sendKeys(text).perform();
	}

	/**
	 * Save mail
	 */
	public void saveMail() {
		builder.moveToElement(saveNewMailButton).click().perform();
	}

	/**
	 * Send mail
	 */
	public void sendMail() {
		builder.moveToElement(sendToMail).click().perform();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method checks the opening of the New Mail page
	 * 
	 * @return - The presence of the field To on the page
	 */
	public boolean isFieldTo() {
		try {
			return toMail.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Checking mark about saving
	 */
	public boolean isInfoSaveInscription() {
		try {
			return infoSave.isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
