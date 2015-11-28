package com.epam.date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import com.epam.date.TestDataProvider;

/**
 * @author kedr
 * 
 *         Parsing a file with the parameters and the choice of driver
 */
public class StartTest {

	public static WebDriver driver;
	private static String browser;

	public static void start() {
		try {
			File fXmlFile = new File(System.getProperty("user.dir") + "/src/test/java/com/epam/date/TestRun.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("date");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					TestDataProvider.login = eElement.getElementsByTagName("login").item(0).getTextContent();
					TestDataProvider.url = eElement.getElementsByTagName("url").item(0).getTextContent();
					TestDataProvider.password = eElement.getElementsByTagName("password").item(0).getTextContent();
					browser = eElement.getElementsByTagName("browser").item(0).getTextContent();
					driverSelection(browser);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param name
	 *            browser
	 */
	private static void driverSelection(String name) {
		switch (name) {
		case "FF":
			driver = new FirefoxDriver();
			break;
		case "Chrome":
			driver = new ChromeDriver();
			break;
		default:
			break;
		}
	}
}
