package com.epam.date;

import org.testng.annotations.DataProvider;

/**
 * @author kedr Test data for authorization
 */
public class TestDataProvider {
	// Website address
	public static String url = "";
	// user login
	public static String login = "";
	// user password
	public static String password = "";

	
	/**
	 * 
	 * The input data for the test.
	 * 
	 * @return - the recipient, subject and text of the letter
	 */
	@DataProvider
	public static Object[][] newMailData() {
		return new Object[][] { { "epamtestselenium@qip.ru", "Test2", "Test3" },
				{ "varchenko.nikita.v@gmail.com", "Test2Test", "Test3Test" }

		};
	}

}
