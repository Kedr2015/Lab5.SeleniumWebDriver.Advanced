package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * @author kedr
 * 
 *         Drafts Mail page. Locators and work with them
 */
public class DraftsMailPage extends MainMailPage {
	/**
	 * @param driver
	 *            -webdriver Transfer driver instance in the constructor
	 */
	public DraftsMailPage(WebDriver driver) {
		super(driver);
	}

	// Mask Search saved messages
	private String identificationDraft = ".//div[@class='figlist']//div[@class='theme']/a[text()='%s']/span[text()='- %s']";

	/**
	 * The method checks the messages on the page for the input parameters
	 * 
	 * @param subject
	 *            - subject mail
	 * @param text
	 *            - text mail
	 * @return - Availability of posts on the page
	 */
	public boolean checkForDrafts(String subject, String text) {
		try {
			return driver.findElement(By.xpath(String.format(identificationDraft, subject, text))).isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
