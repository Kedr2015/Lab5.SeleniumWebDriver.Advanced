package com.epam.runner;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import com.epam.config.Settings;
import com.epam.exception.TestNgRunException;

public class Runner {

    protected TestNG testng = new TestNG();
    private String testNgConfig;

    public static void main(String[] args) {
	new Runner(args).run();
    }

    public Runner(String[] args) {
	Settings settings = new Settings();
	CmdLineParser parser = new CmdLineParser(settings);

	try {
	    parser.parseArgument(args);
	    testNgConfig = settings.testNgPath;
	} catch (CmdLineException e) {
	    System.err.println("error: " + e.toString());
	    parser.printUsage(System.out);
	}
    }

    public void run() {
	try {
	    XmlSuite xmlSuite = new Parser(testNgConfig).parseToList().get(0);
	    this.testng.setCommandLineSuite(xmlSuite);
	    this.testng.run();
	} catch (Exception ex) {
	    throw new TestNgRunException("Error running Test NG suite " + ex.getMessage());

	}
    }
}
