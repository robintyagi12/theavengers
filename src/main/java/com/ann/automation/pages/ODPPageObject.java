package com.ann.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;

public class ODPPageObject {

	private WebDriver driver=null;
	static Utils utils=null;
	public XLUtils xlUtils=null;
	HomePageObjects homepage=null;

	public ODPPageObject(WebDriver driver,String brand,String browser, String device)
	{
		this.driver=driver;
		utils=new Utils(brand, browser, device);
		xlUtils=new XLUtils();
		homepage= new HomePageObjects(driver, brand, browser, device);
	}
	/**
	 * This function return Webelement to Close ODP
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement CloseODP() throws Exception
	{
		return utils.findElementByLocator(driver, "ODP_close_Btn", "Unable to locate ODP close button");
		
	}
	
	public synchronized List<WebElement> imagesODP() throws Exception
	{
		return utils.findElementsByLocator(driver, "OPD_Image", "Unable to locate ODP close button");

	}
	public synchronized List<WebElement> OtherItemimages() throws Exception
	{
		return utils.findElementsByLocator(driver, "ODP_OtherItemImg", "Unable to locate ODP close button");

	}
	/**
	 * Get the list of Shop look items
	 *  
	 * @return List<Webelement>
	 * @throws Exception
	 */
	public synchronized List<WebElement> shopLookItems() throws Exception {
		
		return utils.findElementsByLocator(driver, "ODP_ShopLooks", "|Shop Look Items");
	}
	
	/**
	 * Method to mousehover to the main category
	 * @param mainCategory
	 * @throws Exception
	 */
	public synchronized void mouseHoverToMainCategory(String mainCategory) throws Exception
	{
		try {
			if(Utils.device.equals("mobile"))
				homepage.click_lnk_hamburger();
			
			Utils.waitForPageLoaded(driver);
			String mainMenuXpath1 = xlUtils.getObjectFromXMLString("PLP_MainMenuXpath1", Constants.ENVIRONMENT);
			String mainMenuXpath2 = xlUtils.getObjectFromXMLString("PLP_MainMenuXpath2", Constants.ENVIRONMENT);
			WebElement mainMenuXpath = driver.findElement(By.xpath(mainMenuXpath1+mainCategory+mainMenuXpath2));
			
			if(Utils.device.equals("mobile")) {
				mainMenuXpath.click();
				ReporterLog.pass("Clicked on element"+mainCategory);
			}
			else {
			utils.mouseHoverJScript(driver,mainMenuXpath);
			}
			
		}catch(Exception e){
			CustomException.throwExceptionError(e, "ODP", "Mouse Hover to main category failed", driver);
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "ODP", "Mouse Hover to main category failed", driver);
		}
	}
	
	/**
	 * Method to check whether the Feature is present under the mentioned category and under 
	 * Features check that the links are present under it.
	 * @return
	 * @throws Exception 
	 * @param mainCategory : Main Categorey name ex. clothing, shoes etc 
	 */
	public synchronized boolean checkFeatureIsPresentAndLinks(String mainCategory) throws Exception
	{
		try {
			
			mouseHoverToMainCategory(mainCategory);

			String subMenuXpath1 = xlUtils.getObjectFromXMLString("PLP_subMenuXpath1", Constants.ENVIRONMENT);
			String subMenuXpath2 = xlUtils.getObjectFromXMLString("PLP_subMenuXpath2", Constants.ENVIRONMENT);
			String subMenuLink = null;
			if(Utils.device.equals("desktop")) {
			if(utils.brand.equals("ATF"))
			{
			 subMenuLink= subMenuXpath1+"FEATURES"+subMenuXpath2;
			}
			else
			{
				subMenuLink= subMenuXpath1+"Features"+subMenuXpath2;
			}
			WebElement subMenuXpath = driver.findElement(By.xpath(subMenuLink));
			
			//Checking that the Features is present or not
			if(subMenuXpath.isDisplayed() && subMenuXpath.getText().equalsIgnoreCase("Features")) {
				ReporterLog.pass("Features is present");
				String featureLinksList = subMenuLink+xlUtils.getObjectFromXMLString("PLP_subMenuFeatureLinksList", Constants.ENVIRONMENT);
				List<WebElement> featureLinks = driver.findElements(By.xpath(featureLinksList));
				Assert.assertTrue(featureLinks.size()>=1,"Links are not present under Features");
				ReporterLog.pass("Features have the list mentioned and not blank");
				return true;
			}
			
			else {
				ReporterLog.fail("Features not present in the Main Category "+mainCategory);
				return false;
			}
			}else
			{
				List<WebElement> subItems = utils.findElementsByLocator(driver, "PLP_MBL_subMenuFeature", "Features link not present");
				Assert.assertTrue(subItems.size()>=1,"Feature link present");
				ReporterLog.pass("Feature link present");
				String mainMenuXpath1 = xlUtils.getObjectFromXMLString("PLP_MainMenuXpath1", Constants.ENVIRONMENT);
				String mainMenuXpath2 = xlUtils.getObjectFromXMLString("PLP_MainMenuXpath2", Constants.ENVIRONMENT);
				WebElement mainMenuXpath = driver.findElement(By.xpath(mainMenuXpath1+mainCategory+mainMenuXpath2));
				mainMenuXpath.click();
				homepage.click_lnk_hamburger();
				return true;
			}
		}catch(Exception e){
			CustomException.throwExceptionError(e, "ODP", "Feature not Present", driver);
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "ODP", "Feature not Present", driver);
		}
		return false;
	}
	
	public synchronized List<WebElement> checkOdpCarousel() throws Exception
	{
		return utils.findElementsByLocator(driver, "PLP_ODP_corsuel", "ODP Carosusel");
	}

}
