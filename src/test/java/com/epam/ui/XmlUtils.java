package com.epam.ui;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epam.bo.User;

/**
 * @author Nikita_Varchenko
 * 
 *         Processing an XML file
 */
public class XmlUtils {
	/**
	 * Selecting a file for piercing
	 * 
	 * @return Element
	 */
	public static Element parseFileXML() {
		try {
			File fXmlFile = new File(System.getProperty("user.dir") + "/src/test/resources/TestRun.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("date");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					return eElement;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Create User
	 * 
	 * @param eElement
	 * @return UserTest
	 */
	public static User initializationUser(Element eElement) {
		String password = eElement.getElementsByTagName("password").item(0).getTextContent();
		String login = eElement.getElementsByTagName("login").item(0).getTextContent();
		User testUser = new User(login, password);
		return testUser;
	}

	/**
	 * Parsing XML
	 * 
	 * @param parametr
	 * @param eElement
	 * @return string
	 */
	public static String getParameterFromXML(String parametr, Element eElement) {
		return eElement.getElementsByTagName(parametr).item(0).getTextContent();
	}

}
