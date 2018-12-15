package com.ann.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;

public class LoginPageObjects {
	private static WebDriver driver=null;
	static Utils utils=null;
	public HomePageObjects homepage= null;
	
	public LoginPageObjects(WebDriver driver,String brand,String browser,String device)
	{
		this.driver=driver;
		utils=new Utils(brand, browser, device);
		homepage = new HomePageObjects(driver, brand, browser, device);
	}
	/**
	 * Get the signin link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public WebElement lnk_SigIn() throws Exception {

		return utils.findElementByLocator(driver, "HP_UN_Lnk_sigin", "| Login email not found");
	}
	/**
	 * Get the login email text box
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public WebElement login_txt_email() throws Exception {

		return utils.findElementByLocator(driver, "Login_txt_email", "| Login email not found");
	}
	/**
	 * Get the login password text box
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public WebElement login_txt_password() throws Exception {

		return utils.findElementByLocator(driver, "Login_txt_password", "| Login password not found");
	}
	/**
	 * Get the sign In button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public WebElement login_btn_SignIn() throws Exception {

		return utils.findElementByLocator(driver, "Login_btn_SignIn", "| Login sigin button not found");
	}
	/**
	 * Get the my account header
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public WebElement lnk_MyAccountHeader() throws Exception{
		return utils.findElementByLocator(driver, "HP_Lnk_MyAccountHeader", "My Account Header not found");
	}

	/**
	 * Enter Login user name
	 * 
	 * 
	 * @throws Exception
	 */
	public void enter_login_email(String email) throws Exception
	{
		utils.sendKeysOnWebElement(driver, login_txt_email(), email, "password not entered");
	}
	/**
	 * Enter Login password
	 * 
	 * 
	 * @throws Exception
	 */
	public void enter_login_password(String password) throws Exception
	{
		utils.sendKeysOnWebElement(driver, login_txt_password(), password, "password not entered");
	}
	/**
	 * click signin button
	 * 
	 * 
	 * @throws Exception
	 */
	public void clickSigInButton() throws Exception
	{
		utils.clickWebElement(driver, login_btn_SignIn(), "SignIn button not found");
	}
	/**
	 * Method used for loggin into the website
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void loginToSite(WebDriver driver, String userName, String password) throws Exception{
		try{ 

			Reporter.log(Constants.DELIMITER + "*************Login Page: Login To Account************");

			Thread.sleep(500);
			WebElement elemLink = homepage.lnk_signIn();
			utils.clickWebElement(driver, elemLink, " | Sign In Element not found");

			Thread.sleep(1000);
			Utils.waitForPageLoaded(driver);
			Reporter.log("clicked on sigin link" );
			enter_login_email(userName);
			ReporterLog.actionMsg("| Login Page: " + userName +" is entered in Email text box" );

			enter_login_password(password);
			ReporterLog.actionMsg("| Login Page: " + password +" is entered in Password text box" );

			//Clicking on the Sign In Button on the Login Page
			clickSigInButton();
			ReporterLog.actionMsg("| Login Page: Click action is perfromed on Sign In button" );

			Utils.waitForPageLoaded(driver);

			//Verification : My Account page displayed
			Assert.assertTrue(utils.isElementPresent(driver, "HP_Lnk_MyAccountHeader"),"| Login Failed");
			ReporterLog.pass("| User Successfully Navigated to My Account | Page Title: " + driver.getTitle());	

		}catch(Exception e){
			CustomException.throwExceptionError(e, "Login", "Login action failed", driver);
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "Login", "Login action failed", driver);
		}
	}
}
