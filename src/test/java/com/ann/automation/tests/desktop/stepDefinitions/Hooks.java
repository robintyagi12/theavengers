package com.ann.automation.tests.desktop.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.ann.automation.utilities.Placeholder;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends Placeholder {
	private final Logger LOG = LogManager.getLogger(this.getClass().getSimpleName());

	@Before
	public void BeforeSteps(Scenario scenario){
		LOG.info("***** FEATURE FILE :-- +Placeholder.getInstance().getFeatureFileName(scenario)+ --: *****");
		LOG.info("***** Scenario Started :-- "+scenario.getName()+" --: *****");
		// ********** Opening Browser Before Every Test Case Execution For The Given URL In Feature File. **********
		getBrowserInstantiation();
	}

	@After
	public void AfterSteps(Scenario scenario){
		try{
			if(scenario.isFailed()){
				scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES),"image/png");
				driver.close();
				LOG.error("***** Scenario Completed : "+scenario.getName()+", Test Case is -> "+scenario.getStatus()+" *****");
				driver = WebDriverThreadLocal.get();
				if(driver != null){
					driver.quit();
				}
			}else{
				driver.close();
				LOG.info("***** Scenario Completed : "+scenario.getName()+", Test Case is -> "+scenario.getStatus()+" *****");
				driver = WebDriverThreadLocal.get();
				if(driver != null){
					driver.quit();
				}
			}
		}
		catch(Exception e){
			LOG.error("Test Case "+scenario.getStatus()+" : "+e.getMessage());
			e.printStackTrace();
		}
	}
}