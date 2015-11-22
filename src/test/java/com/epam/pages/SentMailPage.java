package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

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

	// Mask Search saved messages
	//private String identificationSent = ".//div[@class='figlist']//div[@class='theme']/a[text()='%s']/span[text()='- %s']";
	private String authorSent=".//div[@class='figlist']//div[@class='theme']/a[text()='%s']/span[text()='- %s']/../../../div[@class='user']//a[@title='%s']";

	/**
	 * The method checks the messages on the page for the input parameters
	 * 
	 * @param subject
	 *            - subject mail
	 * @param text
	 *            - text mail
	 * @return - Availability of posts on the page
	 */
	public boolean checkForSent(String subject, String text,String to) {
		try {
			return (driver.findElement(By.xpath(String.format(authorSent, subject, text,to))).isEnabled());//driver.findElement(By.xpath(String.format(identificationSent, subject, text))).isEnabled()&
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
