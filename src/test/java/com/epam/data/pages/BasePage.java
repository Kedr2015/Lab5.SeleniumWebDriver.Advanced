package com.epam.data.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/**
 * @author kedr
 * 
 *         Base Page Page which is taken as a basis for all the others
 */
public class BasePage {

	protected static Actions builder;
	protected static WebDriver driver;
	protected static JavascriptExecutor executor;

	/**
	 * Transfer driver instance in the constructor
	 * 
	 * @param driver
	 *            -webdriver
	 */
	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
		PageFactory.initElements(driver, this);
		builder = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}

	/**
	 * The method checks the item
	 * 
	 * @return - The presence of the name field on the page
	 */
	public boolean isElementDisplayed(WebElement nameElement) {
		try {
			return nameElement.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Method enters a value in the desired item
	 * 
	 * @param nameElement
	 *            -The selected item
	 * @param text
	 *            - The selected text
	 */
	public void inputText(WebElement nameElement, String text) {
		builder.moveToElement(nameElement).click().click().sendKeys(text).perform();
	}

	/**
	 * Method click on the selected item
	 * 
	 * @param nameElement
	 *            -The selected item
	 */
	public void clickElement(WebElement nameElement, int time) {
		builder.moveToElement(nameElement).click().perform();
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		checkDialogBox();
	}

	/**
	 * Bypass dialog
	 */
	public static void checkDialogBox() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			Thread.sleep(2000);
		} catch (NoAlertPresentException ex) {
		} catch (InterruptedException e) {
		}
	}
}
