package com.epam.ui.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author kedr
 *
 *         Main Mail page. Locators and work with them
 */
public class MainMailPage extends BasePage {

	/**
	 * @param driver
	 *            Transfer driver instance in the constructor
	 */
	public MainMailPage(WebDriver driver) {
		super(driver);
	}

	// Button exit
	@FindBy(xpath = ".//a[@class='qwh_logout']")
	protected WebElement exitButton;
	// Button New Mail
	@FindBy(xpath = ".//div[@id='left_Menu']//a[@href='/compose']")
	protected WebElement newMailButton;
	// Button go to Drafts
	@FindBy(xpath = ".//a[2][@href='/~Draft;']")
	protected WebElement goToDraftsButton;
	// Button go to Drafts
	@FindBy(xpath = ".//a[2][@href='/~Sent;']")
	protected WebElement goToSentButton;

	// Button go to Drafts
	@FindBy(xpath = ".//*[@id='pmess']//font")
	protected WebElement thruSent;

	// Button go to Drafts
	@FindBy(xpath = ".//*[@id='pmess']//font[text()='Письмо отправлено']/../a")
	protected WebElement thruSentClose;

	// Locator field name
	@FindBy(css = ".refresh-title>span>font")
	protected WebElement title;

	/**
	 * The method of writing a new message
	 * 
	 * @return NewMailPage
	 */
	public NewMailPage writeNewLetter() {
		newMailButton.click();
		checkDialogBox();
		return new NewMailPage(driver);
	}

	/**
	 * Method logout
	 * 
	 * @return LoginPage
	 */
	public LoginPage exitSystem() {
		exitButton.click();
		return new LoginPage(driver);
	}

	/**
	 * Method go to page Drafts Mail
	 * 
	 * @return DraftsMailPage
	 */
	public DraftsMailPage goToDraftsMail() {
		goToDraftsButton.click();
		checkDialogBox();
		if (title.getText().equals("Черновики")) {
			return new DraftsMailPage(driver);
		} else {
			throw new NoSuchElementException("Timeout is over");
		}
	}

	public MainMailPage waitForMessage() {
		if (isElementDisplayed(thruSent)) {
			thruSentClose.click();
			return this;
		} else {
			throw new NoSuchElementException("Timeout is over");
		}
	}

	/**
	 * Method go to page Sent Mail
	 * 
	 * @return SentMailPage
	 */
	public SentMailPage goToSentsMail() {
		goToSentButton.click();
		checkDialogBox();
		if (title.getText().equals("Отправленные")) {
			return new SentMailPage(driver);
		} else {
			throw new NoSuchElementException("Timeout is over");
		}
	}
	
	/**
	 * The method checks availability exit button
	 * 
	 * @return - true or false
	 */
	public boolean IsExitButon() {
		return isElementDisplayed(exitButton);
	}

}
