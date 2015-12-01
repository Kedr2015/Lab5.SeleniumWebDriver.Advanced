package com.epam.data.pages;

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

	/**
	 * The method of writing a new message
	 * 
	 * @return NewMailPage
	 */
	public NewMailPage writeNewLetter() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		return new DraftsMailPage(driver);
	}

	/**
	 * Method go to page Sent Mail
	 * 
	 * @return SentMailPage
	 */
	public SentMailPage goToSentsMail() {
		goToSentButton.click();
		checkDialogBox();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new SentMailPage(driver);
	}

}
