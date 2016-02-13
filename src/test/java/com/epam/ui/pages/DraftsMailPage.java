package com.epam.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	// Locator field name
	@FindBy(xpath = ".//*[@id='messages_frm']/div[3]/div[1]/div[2]/a")
	private WebElement subjectAndTextMail;

	/**
	 * The method checks the messages on the page for the input parameters
	 * 
	 * @param subject
	 *            - subject mail
	 * @param text
	 *            - text mail
	 * @return - Availability of posts on the page
	 */
	public String getSubjectAndTextMail() {
		return subjectAndTextMail.getText();
	}

}
