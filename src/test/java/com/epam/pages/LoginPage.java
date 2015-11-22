package com.epam.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * @author kedr
 *
 *         Login page. Locators and work with them
 */
public class LoginPage {
	private WebDriver driver;// webdriver
	private Actions builder;
	private JavascriptExecutor executor;

	// Locator field name
	@FindBy(xpath = "//input[@name='login'][@title='Логин']")
	WebElement userNameLocator;
	// Locator field password
	@FindBy(xpath = "//input[@name='password'][@title='Пароль']")
	private WebElement userPasswordXpath;
	// Button input
	@FindBy(css = ".lovesearchbutton")
	private WebElement loginButton;
	// Button opening page box
	@FindBy(xpath = "//div[@class='post']//h2/a")
	private WebElement openMailButton;

	/**
	 * Transfer driver instance in the constructor
	 * 
	 * @param driver
	 *            -webdriver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(this.driver);
		executor = (JavascriptExecutor)driver;
	}

	/**
	 * The method checks the opening of the login page
	 * 
	 * @return - The presence of the name field on the page
	 */
	public boolean isUserNameDisplayed() {
		try {
			return userNameLocator.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * 
	 * The method of login.
	 * 
	 * @param name
	 *            - user password
	 */
	public void inputName(String name) {
		builder.moveToElement(userNameLocator).click().sendKeys(name).perform();
	}

	/**
	 * The method of entering a password
	 * 
	 * @param password
	 *            - user password
	 */
	public void inputPassword(String password) {
		builder.moveToElement(userPasswordXpath).click().sendKeys(password).perform();
	}

	/**
	 * Clicking on the Login
	 */
	public void pressButtonInput() {
		executor.executeScript("arguments[0].click();", loginButton);
	}

	/**
	 * Clicking on mail
	 */
	public void pressButtonOpenMail() {
		builder.moveToElement(openMailButton).click().perform();
	}

}
