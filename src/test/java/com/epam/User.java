package com.epam;

import org.w3c.dom.Element;

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

	public User(Element eElement) {
		this.password = eElement.getElementsByTagName("password").item(0).getTextContent();
		this.login = eElement.getElementsByTagName("login").item(0).getTextContent();

	}

}
