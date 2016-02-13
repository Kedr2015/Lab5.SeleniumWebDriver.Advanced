package com.epam.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.bo.User;

/**
 * @author kedr
 *
 *         Login page. Locators and work with them
 */
public class LoginPage extends BasePage {

	/**
	 * Transfer driver instance in the constructor
	 * 
	 * @param driver
	 *            -webdriver
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Locator field name
	@FindBy(xpath = "//input[@name='login'][@title='Логин']")
	private WebElement userNameLocator;
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
	 * 
	 * The method of login.
	 * 
	 * @param name
	 *            - user password
	 */
	public void inputName(String name) {
		executor.executeScript("arguments[0].value='" + name + "';", userNameLocator);
	}

	/**
	 * Clicking on the Login
	 */
	public void pressButtonInput() {
		executor.executeScript("arguments[0].click();", loginButton);
	}

	/**
	 * The authorization system
	 * 
	 * @param user
	 * @return MainMailPage
	 */
	public MainMailPage loginMetod(User user) {
		inputName(user.getLogin());
		inputText(userPasswordXpath, user.getPassword());
		pressButtonInput();
		clickElement(openMailButton);
		return new MainMailPage(driver);
	}

}
