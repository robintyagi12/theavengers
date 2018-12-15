package com.jcg.logging;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Example {
	static int i;
	static String browser = "null";
	private static final Logger LOG = LogManager.getLogger(Log4j2Example.class);

	public static void main(String[] args) {

		switch (browser) {
		case "null":
		case "GOOGLE_CHROME":
			System.out.println("GOOGLE_CHROME");
			break;
		case "FF":
			System.out.println("FF");
			break;
		default:
			System.out.println("IE");
		}

		LOG.debug("This Will Be Printed On Debug");
		LOG.info("This Will Be Printed On Info");
		LOG.warn("This Will Be Printed On Warn");
		LOG.error("This Will Be Printed On Error");
		LOG.fatal("This Will Be Printed On Fatal");

		LOG.info("Appending string: {}.", "Hello, World");
	}

	public static void propertyFile() {
		i = 10;
		Properties props;
		final String propertyFilePath1 = "configs//configuration.properties";
		BufferedReader reader1 = null;
		try {
			reader1 = new BufferedReader(new FileReader(propertyFilePath1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// reader2 = new BufferedReader(new FileReader(propertyFilePath2));
		props = new Properties();

		try {
			props.load(reader1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// props.load(reader2);
		browser = props.getProperty("browser");
		System.out.println(browser);

	}

}