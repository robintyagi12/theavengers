package com.ann.automation.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.ann.automation.utilities.Placeholder;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/checkoutmodule/registereduser/", glue = {
		"com.ann.automation.tests.desktop.stepDefinitions" }, tags = { "@NFPBrands" }, plugin = { "pretty",
				"json:target/cucumber.json", "junit:target/cucumber-reports/Cucumber.xml",
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/ExtentReport.html",
				"html:target/cucumber-reports" }, monochrome = true)
public class RunCuke {
	@BeforeClass
	public static void setUp() {
			}

	@AfterClass
	public static void writeExtentReport() {
		 //********** Loading extent-config.xml from Configuration.properties file
		   Reporter.loadXMLConfig(Placeholder.getRunTime().stringDataReaderJSON("extentreportpath"));
	}
}