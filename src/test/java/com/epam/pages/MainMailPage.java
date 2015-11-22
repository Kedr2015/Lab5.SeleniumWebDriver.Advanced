package com.epam.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * @author kedr
 *
 *         Main Mail page. Locators and work with them
 */
public class MainMailPage {

	public WebDriver driver;// webdriver
	protected Actions builder;

	/**
	 * @param driver
	 *            Transfer driver instance in the constructor
	 */
	public MainMailPage(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(this.driver);
	}

	// Button exit
	@FindBy(xpath = ".//a[@class='qwh_logout']")
	WebElement exitButton;
	// Button New Mail
	@FindBy(xpath = ".//div[@id='left_Menu']//a[@href='/compose']")
	WebElement newMailButton;
	// Button go to Drafts
	@FindBy(xpath = ".//a[2][@href='/~Draft;']")
	WebElement goToDraftsButton;
	// The mark about saving
	@FindBy(xpath = ".//font[contains(text() , 'Письмо отправлено')]")
	WebElement shippingSend;
	// Button go to Drafts
	@FindBy(xpath = ".//a[2][@href='/~Sent;']")
	WebElement goToSentButton;

	/**
	 * Sign Out
	 */
	public void signOut() {
		builder.moveToElement(exitButton).click().perform();
		checkDialogBox();
	}

	/**
	 * The method checks the opening of the Main Mail page
	 * 
	 * @return - The presence of the Button New Mail on the page
	 */
	public boolean isInfoSendMailDisplayed() {
		try {
			return shippingSend.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * The method checks the opening of the Main Mail page
	 * 
	 * @return - The presence of the Button New Mail on the page
	 */
	public boolean isNewMailDisplayed() {
		try {
			return newMailButton.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Create a new message
	 */
	public void pressButtonNewMail() {
		builder.moveToElement(newMailButton).click().perform();
		checkDialogBox();
	}

	/**
	 * Go to page drafts mail
	 */
	public void openDrafts() {
		builder.moveToElement(goToDraftsButton).click().perform();
		checkDialogBox();
	}
	
	/**
	 * Go to page sent mail
	 */
	public void openSent() {
		builder.moveToElement(goToSentButton).click().perform();
		checkDialogBox();
	}

	/**
	 * Bypass dialog
	 */
	private void checkDialogBox() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			Thread.sleep(2000);
		} catch (NoAlertPresentException ex) {
		} catch (InterruptedException e) {
		}
	}
}
