package com.epam.bo;

/**
 * @author kedr
 * 
 *         user Class
 */
public class User {

	private String login;
	private String password;

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public User(String login, String password) {
		this.password = password;
		this.login = login;
	}

}
