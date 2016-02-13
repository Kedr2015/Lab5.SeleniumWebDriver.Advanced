package com.epam.runner;

import org.testng.annotations.DataProvider;

/**
 * @author kedr Test data for authorization
 */
public class TestDataProvider {
	/**
	 * 
	 * The input data for the test.
	 * 
	 * @return - the recipient, subject and text of the letter
	 */
	@DataProvider
	public static Object[][] newMailData() {
		return new Object[][] { { "epamtestselendm@qip.ru", "Test2", "Test3" },
				{ "varchenko.nikita.v@gmail.com", "Test2Test", "Test3Test" }

		};
	}

}
