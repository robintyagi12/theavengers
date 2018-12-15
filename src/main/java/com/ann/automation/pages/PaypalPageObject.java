package com.ann.automation.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.Utils.MobileView;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.ShoppingBagPageObjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

public class PaypalPageObject {
	private static WebDriver driver=null;
	static Utils utils=null;
	ShoppingBagPageObjects shoppingBag=null;
	HomePageObjects homepage=null;
	static XLUtils xlUtils=null;
	public PaypalPageObject(WebDriver driver,String brand,String browser, String device)
	{
		this.driver=driver;
		utils=new Utils(brand,browser,device);
		shoppingBag = new ShoppingBagPageObjects(driver, brand, browser, device);
		xlUtils=new XLUtils();
		homepage=new HomePageObjects(driver, brand, browser, device);
	}
	/**
	 * Method to enter PayPal payment details
	 */

	public synchronized void enterPayPalDetailsNProceed(WebDriver driver) throws Exception{
		try{
			ReporterLog.headerString("*********Enter PayPal Payment***********");

			String paypalUserEmail= xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 1);
			String paypalUserPassword =xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 2);
			if(utils.browser.equalsIgnoreCase("ios")) {
				MobileView.switchToWebView(driver);
				/*
				 * Login to your paypal account
				 */
				String parent = driver.getWindowHandle();
				MobileView.switchToNative(driver);
				//if (driver instanceof HasTouchScreen) {
				//					TouchActions tap = new TouchActions(driver).doubleTap(shoppingBag.btn_ProceedToPaypal());
				//					tap.perform();

				WebElement elemPayPal = shoppingBag.btn_ProceedToPaypal();
				TouchAction a = new TouchAction((MobileDriver) driver);
				a.tap((int) homepage.getTheElementLocations(elemPayPal, "x-axis")+10,(int) homepage.getTheElementLocations(elemPayPal, "y-axis")+70).perform();
		//		((IOSDriver<WebElement>)driver).getKeyboard().sendKeys(Keys.ENTER);

				//				} else {
				//					utils.clickWebElement(driver, shoppingBag.btn_ProceedToPaypal(),"");
				//				}

				Thread.sleep(15000);
				Utils.waitForPageLoaded(driver);
				MobileView.switchToWebView(driver);
				//				Utils.acceptAlert(driver);
				Thread.sleep(7000);
				/*
				 * Enter username and password
				 */

				utils.switchToWindow(driver, parent);
				//utils.switchToTopWindow();
				driver.switchTo().defaultContent();
				Thread.sleep(10000);
				Utils.waitForPageLoaded(driver);
				MobileView.switchToNative(driver);
				utils.findElementByIOSNameLocator("PaypalLogin").click();
				Utils.waitForPageLoaded(driver);
				if(!utils.findElementByIOSNameLocator("PayPal_Email_IOS").getText().equalsIgnoreCase(paypalUserEmail))
				{
					utils.findElementByIOSNameLocator("PayPal_Email_IOS").sendKeys(paypalUserEmail);
					Thread.sleep(1000);
					utils.findElementByIOSNameLocator("Done").click();
					Utils.waitForPageLoaded(driver);

					utils.findElementByIOSNameLocator("Next").click();
					Utils.waitForPageLoaded(driver);
					//utils.findElementByIOSNameLocator("TextField").clear();
					//Thread.sleep(1000);
					//utils.findElementByIOSClassNameLocator("TextField").sendKeys(Keys.chord(Keys.CONTROL,"a"));
					//utils.findElementByIOSNameLocator("TextField").sendKeys(Keys.DELETE);
					//Thread.sleep(1000);
					utils.findElementByIOSNameLocator("PayPal_Password_IOS").sendKeys(paypalUserPassword);
					Thread.sleep(1000);
				}
				else {
					utils.findElementByIOSNameLocator("PayPal_Password_IOS").sendKeys(paypalUserPassword);
					Thread.sleep(1000);
					
				}
				//utils.findElementByIOSNameLocator("CVV").sendKeys(paypalUserPassword);
				//Thread.sleep(1000);

				utils.findElementByIOSNameLocator("PaypalLogin").click();
				Thread.sleep(30000);
			
				utils.findElementByIOSNameLocator("PayPal_SelectCredit").click();
				Utils.waitForPageLoaded(driver);
				utils.findElementByIOSNameLocator("PayPal_ChangePayment").click();
				Utils.waitForPageLoaded(driver);
				utils.findElementByIOSNameLocator("PayPal_Balance").click();
				Utils.waitForPageLoaded(driver);				
				utils.findElementByIOSNameLocator("PaypalContinue").click();

				Thread.sleep(10000);
				Utils.waitForPageLoaded(driver);
			
				Utils.waitForPageLoaded(driver);

				/*
				 * Verify Order review page
				 */
				Thread.sleep(25000);
				Utils.waitForPageLoaded(driver);
				driver.switchTo().window(parent);

				Utils.waitForPageLoaded(driver);
				Thread.sleep(5000);
				driver.switchTo().defaultContent();
				
			}
			else {
				Set<String> windows =driver.getWindowHandles();
				if(windows.size()>1){
					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));
//					driver.manage().window().maximize();
					Utils.waitForPageLoaded(driver);
					utils.waitForElementToBeClickable(driver, "PaypalEmailField", "PayPal email field is not present");
					String PaypalWindowTitle =driver.getTitle();
					if (PaypalWindowTitle.contains("PayPal")==true){
						ReporterLog.actionMsg("PayPal Window Displayed");
						//driver.switchTo().frame(0);
						Thread.sleep(5000);

						if(utils.isElementPresent(driver, "PayPalLogin")) {
							utils.findElementByLocator(driver, "PayPalLogin", "PayPal Login Button not present on first page of PayPal").click();;
							Thread.sleep(2000);
							utils.waitForPageLoaded(driver);
						}
						//PayPal User Email Entry
						ReporterLog.actionMsg("| Enter PayPal Email Id :" + paypalUserEmail);
						utils.waitForElementToPresent(driver, "PaypalEmailField");
						utils.findElementByLocatorAndSendKeys(driver, "PaypalEmailField", paypalUserEmail,"PayPal User Email Input Box Not Displayed");
						if(utils.isElementPresent(driver, "PayPalNext")){
							utils.findElementByLocator(driver, "PayPalNext","Paypal next button element not found").click();
						}
						//PayPal Password Entry
						utils.waitForElementToBeClickable(driver, "PaypalLoginbtn", "Paypal Login button is not present");
						ReporterLog.actionMsg("| Enter PayPal Password :" + paypalUserPassword);
						utils.findElementByLocatorAndSendKeys(driver, "PaypalPwdField",paypalUserPassword, "PayPal Password field Not found on paypal page");
						//Click PayPal Login Button
						ReporterLog.actionMsg("| Click PayPal Login Button");
						WebElement elem = utils.findElementByLocator(driver, "PaypalLoginbtn","Paypal log in submit button element not found");
						utils.clickWebElement(driver, elem, "Paypal log in submit button element not found");
						Thread.sleep(2000);
						Utils.waitForPageLoaded(driver);
						utils.waitForElementToPresent(driver, "PaypalContinuebtn", 40);
						//Click PayPal Continue Button
						ReporterLog.actionMsg("| Click PayPal Continue Button on the next page of PayPal window");
						utils.clickWebElement(driver, utils.findElementByLocator(driver, "PaypalContinuebtn","Paypal continue button not found"), "paypal continue button");
						Thread.sleep(1000);
						utils.clickWebElement(driver, utils.findElementByLocator(driver, "PaypalContinuebtn","Paypal continue button not found"), "paypal continue button");
						//						Thread.sleep(15000);
						Utils.waitForPageLoaded(driver);
						Thread.sleep(10000);
						//Switch to Default Page                   
						driver.switchTo().window(tabs.get(0));

					}else{
						ReporterLog.fail("| PayPal Window Not Displayed");
						Assert.assertFalse(PaypalWindowTitle.contains("PayPal Checkout")==true, "PayPal Window incorrectly Displayed");
					}
				}else{
					ReporterLog.fail("| New PayPal Window Not Displayed"); 
					Assert.assertFalse(windows.size()>1, "New PayPal Window Not Displayed");
				}
			}

		}catch(Exception e){
			CustomException.throwExceptionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}
	}
	/**
	 * Method to enter PayPal payment details and select an invalid address already added to the account
	 */

	public synchronized void enterPayPalDetailsNChangeAddress(WebDriver driver) throws Exception{
		try{
			ReporterLog.headerString("*********Enter PayPal Payment***********");
			Set<String> windows =driver.getWindowHandles();
			if(windows.size()>1){
				//Read PayPal Data from excel
				String paypalUserEmail= xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 1);
				String paypalUserPassword =xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 2);
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.manage().window().maximize();
				Utils.waitForPageLoaded(driver);
				utils.waitForElementToBeClickable(driver, "PaypalEmailField", "PayPal email field is not present");
				String PaypalWindowTitle =driver.getTitle();
				if (PaypalWindowTitle.contains("PayPal")==true){
					ReporterLog.actionMsg("PayPal Window Displayed");
					//driver.switchTo().frame(0);
					Thread.sleep(5000);

					if(utils.isElementPresent(driver, "PayPalLogin")) {
						utils.findElementByLocator(driver, "PayPalLogin", "PayPal Login Button not present on first page of PayPal").click();;
						Thread.sleep(2000);
						utils.waitForPageLoaded(driver);
					}
					//PayPal User Email Entry
					ReporterLog.actionMsg("| Enter PayPal Email Id :" + paypalUserEmail);
					utils.findElementByLocatorAndSendKeys(driver, "PaypalEmailField", paypalUserEmail,"PayPal User Email Input Box Not Displayed");
					if(utils.isElementPresent(driver, "PayPalNext")){
						utils.findElementByLocator(driver, "PayPalNext","Paypal next button element not found").click();
					}
					//PayPal Password Entry
					utils.waitForElementToBeClickable(driver, "PaypalLoginbtn", "Paypal Login button is not present");
					ReporterLog.actionMsg("| Enter PayPal Password :" + paypalUserPassword);
					utils.findElementByLocatorAndSendKeys(driver, "PaypalPwdField",paypalUserPassword, "PayPal Password field Not found on paypal page");
					//Click PayPal Login Button
					ReporterLog.actionMsg("| Click PayPal Login Button");
					WebElement elem = utils.findElementByLocator(driver, "PaypalLoginbtn","Paypal log in submit button element not found");
					utils.clickWebElement(driver, elem, "Paypal log in submit button element not found");
					Thread.sleep(15000);
					Utils.waitForPageLoaded(driver);
					//Click PayPal Continue Button
					ReporterLog.actionMsg("| Click PayPal Continue Button on the next page of PayPal window");
					WebDriverWait wait = new WebDriverWait(driver, 150000);
					utils.waitForElementToPresent(driver, "Paypal_ChangeAddress", "change address button not found", wait);
					utils.findElementByLocator(driver, "Paypal_ChangeAddress", "change address button not found").click();
					Utils.waitForPageLoaded(driver);
					utils.clickWebElement(driver, utils.findElementByLocator(driver, "Paypal_addressWithIncorrectZipCode","incorrect address not found"), "incorrect address not found");
					Utils.waitForPageLoaded(driver);
					utils.waitForElementToPresent(driver, "PaypalContinuebtn", "change address button not found", wait);
					WebElement el=utils.findElementByLocator(driver, "PaypalContinuebtn","Paypal continue button not found");
					utils.scrollDownToElement(driver, el);
					Thread.sleep(2000);
					//utils.clickWebElement(driver, utils.findElementByLocator(driver, "PaypalContinuebtn","Paypal continue button not found"), "paypal continue button");
					el.click();
					//						Thread.sleep(15000);
					Utils.waitForPageLoaded(driver);
					Thread.sleep(10000);
					//Switch to Default Page                   
					driver.switchTo().window(tabs.get(0));

					/*Add code for Address recommendation modal*/

				}else{
					ReporterLog.fail("| PayPal Window Not Displayed");
					Assert.assertFalse(PaypalWindowTitle.contains("PayPal Checkout")==true, "PayPal Window incorrectly Displayed");
				}
			}else{
				ReporterLog.fail("| New PayPal Window Not Displayed"); 
				Assert.assertFalse(windows.size()>1, "New PayPal Window Not Displayed");
			}

		}catch(Exception e){
			CustomException.throwExceptionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}
	}
	/**
	 * Method to enter PayPal payment details
	 */

	public synchronized String enterPayPalDetails_returnSelectedAddress(WebDriver driver) throws Exception{
		String value=null;
		try{

			ReporterLog.headerString("*********Enter PayPal Payment***********");
			Set<String> windows =driver.getWindowHandles();
			if(windows.size()>1){
				//Read PayPal Data from excel
				String paypalUserEmail= xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 1);
				String paypalUserPassword =xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 2);
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.manage().window().maximize();
				Utils.waitForPageLoaded(driver);
				utils.waitForElementToBeClickable(driver, "PaypalEmailField", "PayPal email field is not present");
				String PaypalWindowTitle =driver.getTitle();
				if (PaypalWindowTitle.contains("PayPal")==true){
					ReporterLog.actionMsg("PayPal Window Displayed");
					//driver.switchTo().frame(0);
					Thread.sleep(5000);

					if(utils.isElementPresent(driver, "PayPalLogin")) {
						utils.findElementByLocator(driver, "PayPalLogin", "PayPal Login Button not present on first page of PayPal").click();;
						Thread.sleep(2000);
						utils.waitForPageLoaded(driver);
					}
					//PayPal User Email Entry
					ReporterLog.actionMsg("| Enter PayPal Email Id :" + paypalUserEmail);
					utils.findElementByLocatorAndSendKeys(driver, "PaypalEmailField", paypalUserEmail,"PayPal User Email Input Box Not Displayed");
					if(utils.isElementPresent(driver, "PayPalNext")){
						utils.findElementByLocator(driver, "PayPalNext","Paypal next button element not found").click();
					}
					//PayPal Password Entry
					utils.waitForElementToBeClickable(driver, "PaypalLoginbtn", "Paypal Login button is not present");
					ReporterLog.actionMsg("| Enter PayPal Password :" + paypalUserPassword);
					utils.findElementByLocatorAndSendKeys(driver, "PaypalPwdField",paypalUserPassword, "PayPal Password field Not found on paypal page");
					//Click PayPal Login Button
					ReporterLog.actionMsg("| Click PayPal Login Button");
					WebElement elem = utils.findElementByLocator(driver, "PaypalLoginbtn","Paypal log in submit button element not found");
					utils.clickWebElement(driver, elem, "Paypal log in submit button element not found");
					Thread.sleep(2000);
					Utils.waitForPageLoaded(driver);
					value=utils.findElementByLocator(driver, "Paypal_SelectedAddress", "selected address not found").getText();
					//Click PayPal Continue Button
					ReporterLog.actionMsg("| Click PayPal Continue Button on the next page of PayPal window");
					utils.clickWebElement(driver, utils.findElementByLocator(driver, "PaypalContinuebtn","Paypal continue button not found"), "paypal continue button");
					//						Thread.sleep(15000);
					Utils.waitForPageLoaded(driver);
					Thread.sleep(10000);
					//Switch to Default Page                   
					driver.switchTo().window(tabs.get(0));

				}else{
					ReporterLog.fail("| PayPal Window Not Displayed");
					Assert.assertFalse(PaypalWindowTitle.contains("PayPal Checkout")==true, "PayPal Window incorrectly Displayed");
				}
			}else{
				ReporterLog.fail("| New PayPal Window Not Displayed"); 
				Assert.assertFalse(windows.size()>1, "New PayPal Window Not Displayed");
			}


		}catch(Exception e){
			CustomException.throwExceptionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}

		return value;
	}
	public WebElement paypal_details() throws Exception{

		return utils.findElementByLocator(driver, "paypal_details", "paypal details section not found");
	}

	/**
	 * Method to enter PayPal payment details
	 */

	public synchronized List<String> enterPayPalDetails_returnBillingAddr(WebDriver driver) throws Exception{
		List<String> list = new ArrayList<String>();
		try{

			ReporterLog.headerString("*********Enter PayPal Payment***********");
			Set<String> windows =driver.getWindowHandles();
			if(windows.size()>1){
				//Read PayPal Data from excel
				String paypalUserEmail= xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 1);
				String paypalUserPassword =xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 2);
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.manage().window().maximize();
				Utils.waitForPageLoaded(driver);
				utils.waitForElementToBeClickable(driver, "PaypalEmailField", "PayPal email field is not present");
				String PaypalWindowTitle =driver.getTitle();
				if (PaypalWindowTitle.contains("PayPal")==true){
					ReporterLog.actionMsg("PayPal Window Displayed");
					//driver.switchTo().frame(0);
					Thread.sleep(5000);

					if(utils.isElementPresent(driver, "PayPalLogin")) {
						utils.findElementByLocator(driver, "PayPalLogin", "PayPal Login Button not present on first page of PayPal").click();;
						Thread.sleep(2000);
						utils.waitForPageLoaded(driver);
					}
					//PayPal User Email Entry
					ReporterLog.actionMsg("| Enter PayPal Email Id :" + paypalUserEmail);
					utils.findElementByLocatorAndSendKeys(driver, "PaypalEmailField", paypalUserEmail,"PayPal User Email Input Box Not Displayed");
					if(utils.isElementPresent(driver, "PayPalNext")){
						utils.findElementByLocator(driver, "PayPalNext","Paypal next button element not found").click();
					}
					//PayPal Password Entry
					utils.waitForElementToBeClickable(driver, "PaypalLoginbtn", "Paypal Login button is not present");
					ReporterLog.actionMsg("| Enter PayPal Password :" + paypalUserPassword);
					utils.findElementByLocatorAndSendKeys(driver, "PaypalPwdField",paypalUserPassword, "PayPal Password field Not found on paypal page");
					//Click PayPal Login Button
					ReporterLog.actionMsg("| Click PayPal Login Button");
					WebElement elem = utils.findElementByLocator(driver, "PaypalLoginbtn","Paypal log in submit button element not found");
					utils.clickWebElement(driver, elem, "Paypal log in submit button element not found");
					Thread.sleep(2000);
					Utils.waitForPageLoaded(driver);
					utils.findElementByLocator(driver, "PaypalChangePaymentLink", "").click();
					Thread.sleep(1000);
					utils.findElementByLocator(driver, "PaypalAddLink", "").click();
					Thread.sleep(1000);
					list.add(0,utils.findElementByLocator(driver, "PaypalBillingAddr_firstName", "").getText());
					list.add(1,utils.findElementByLocator(driver, "PaypalBillingAddr_lastName", "").getText());
					list.add(2,utils.findElementByLocator(driver, "PaypalBillingAddrDrpDown", "").getText());
					utils.findElementByLocator(driver, "PaypalAddCardBackBtn", "").click();
					utils.waitForPageLoaded(driver);
					//Click PayPal Continue Button
					ReporterLog.actionMsg("| Click PayPal Continue Button on the next page of PayPal window");
					utils.clickWebElement(driver, utils.findElementByLocator(driver, "PaypalContinuebtn","Paypal continue button not found"), "paypal continue button");
					//						Thread.sleep(15000);
					Utils.waitForPageLoaded(driver);
					Thread.sleep(10000);
					//Switch to Default Page                   
					driver.switchTo().window(tabs.get(0));

				}else{
					ReporterLog.fail("| PayPal Window Not Displayed");
					Assert.assertFalse(PaypalWindowTitle.contains("PayPal Checkout")==true, "PayPal Window incorrectly Displayed");
				}
			}else{
				ReporterLog.fail("| New PayPal Window Not Displayed"); 
				Assert.assertFalse(windows.size()>1, "New PayPal Window Not Displayed");
			}


		}catch(Exception e){
			CustomException.throwExceptionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}
		return list;
	}

	/**
	 * Method to enter PayPal payment details
	 */

	public synchronized List<String> enterPayPalDetails_returnBillingAddrIOS(WebDriver driver) throws Exception{
		List<String> list = new ArrayList<String>();
		try{

			ReporterLog.headerString("*********Enter PayPal Payment***********");
	
				//Read PayPal Data from excel
				String paypalUserEmail= xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 1);
				String paypalUserPassword =xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, 9, 2);
		
				String parent = driver.getWindowHandle();
				MobileView.switchToNative(driver);
				WebElement elemPayPal = shoppingBag.btn_ProceedToPaypal();
				TouchAction a = new TouchAction((MobileDriver) driver);
				a.tap((int) homepage.getTheElementLocations(elemPayPal, "x-axis")+10,(int) homepage.getTheElementLocations(elemPayPal, "y-axis")+70).perform();

				Thread.sleep(15000);
				Utils.waitForPageLoaded(driver);
				MobileView.switchToWebView(driver);
				Thread.sleep(7000);
				/*
				 * Enter username and password
				 */

				utils.switchToWindow(driver, parent);
				//utils.switchToTopWindow();
				driver.switchTo().defaultContent();
				Thread.sleep(10000);
				Utils.waitForPageLoaded(driver);
				MobileView.switchToNative(driver);
				utils.findElementByIOSNameLocator("PaypalLogin").click();
				Utils.waitForPageLoaded(driver);
				if(!utils.findElementByIOSNameLocator("PayPal_Email_IOS").getText().equalsIgnoreCase(paypalUserEmail))
				{
					utils.findElementByIOSNameLocator("PayPal_Email_IOS").sendKeys(paypalUserEmail);
					Thread.sleep(1000);
					utils.findElementByIOSNameLocator("Done").click();
					Utils.waitForPageLoaded(driver);

					utils.findElementByIOSNameLocator("Next").click();
					Utils.waitForPageLoaded(driver);
					utils.findElementByIOSNameLocator("PayPal_Password_IOS").sendKeys(paypalUserPassword);
					Thread.sleep(1000);
				}
				else {
					utils.findElementByIOSNameLocator("PayPal_Password_IOS").sendKeys(paypalUserPassword);
					Thread.sleep(1000);
					
				}
				utils.findElementByIOSNameLocator("PaypalLogin").click();
				Thread.sleep(30000);
			
				utils.findElementByIOSNameLocator("PayPal_ChangePayment").click();
				Utils.waitForPageLoaded(driver);
				Thread.sleep(5000);
				
				utils.findElementByIOSNameLocator("PayPal_AddPayment").click();
				Utils.waitForPageLoaded(driver);
				MobileView.switchToNative(driver);
				List<WebElement> textField=((IOSDriver)driver).findElements(By.className("XCUIElementTypeTextField"));
				for(WebElement el:textField)
				{
					if(el.getAttribute("value")!=null)
					{
						System.out.println(el.getAttribute("value"));
						if(el.getAttribute("value").contains("su")){
						list.add(0, el.getAttribute("value"));
						break;
						}
					}
				}
						textField=((IOSDriver)driver).findElements(By.className("XCUIElementTypeTextField"));
						for(WebElement el:textField)
						{
							if(el.getAttribute("value")!=null)
							{
								System.out.println(el.getAttribute("value"));
								if(el.getAttribute("value").contains("ja")){
								list.add(1, el.getAttribute("value"));
								break;
								}

					}
				}

				textField=((IOSDriver)driver).findElements(By.className("XCUIElementTypeOther"));
				for(WebElement el:textField)
				{
					if(el.getAttribute("value")!=null)
					{
						System.out.println(el.getAttribute("value"));
						if(el.getAttribute("value").contains("1800")){
						list.add(2, el.getAttribute("value"));
						break;
						}
					}
				}
				
				utils.findElementByIOSNameLocator("Paypal_BackPaymentAdd").click();
				Utils.waitForPageLoaded(driver);
				
				utils.findElementByIOSNameLocator("Paypal_BackPaymentAdd").click();
				Utils.waitForPageLoaded(driver);
				
				utils.findElementByIOSNameLocator("PayPal_SelectCredit").click();
				Utils.waitForPageLoaded(driver);
				utils.findElementByIOSNameLocator("PayPal_ChangePayment").click();
				Utils.waitForPageLoaded(driver);
				utils.findElementByIOSNameLocator("PayPal_Balance").click();
				Utils.waitForPageLoaded(driver);				
				utils.findElementByIOSNameLocator("PaypalContinue").click();

				Thread.sleep(10000);
				Utils.waitForPageLoaded(driver);
			
				Utils.waitForPageLoaded(driver);

				/*
				 * Verify Order review page
				 */
				Thread.sleep(25000);
				Utils.waitForPageLoaded(driver);
				driver.switchTo().window(parent);

				Utils.waitForPageLoaded(driver);
				Thread.sleep(5000);
				driver.switchTo().defaultContent();
			
		}catch(Exception e){
			CustomException.throwExceptionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}
		return list;
	
	}

	/**
	 * Method to return PayPal Modal Heading
	 */

	public synchronized boolean returnPaypalModalHeading(WebDriver driver) throws Exception{
		String value=null;boolean paypalModalFlag=false;
		try{
			Set<String> windows =driver.getWindowHandles();
			if(windows.size()>1){
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				driver.manage().window().maximize();
				Utils.waitForPageLoaded(driver);
				//utils.waitForElementToBeClickable(driver, "PaypalEmailField", "PayPal email field is not present");
				String PaypalWindowTitle =driver.getTitle();
				if (PaypalWindowTitle.contains("PayPal")==true){
					paypalModalFlag=true;
					ReporterLog.actionMsg("PayPal Window Displayed");
					//driver.switchTo().frame(0);
					Thread.sleep(5000);
					driver.switchTo().window(tabs.get(1)).close();
					Utils.waitForPageLoaded(driver);
					Thread.sleep(10000);
					//Switch to Default Page                   
					driver.switchTo().window(tabs.get(0));

				}else{
					ReporterLog.fail("| PayPal Window Not Displayed");
					Assert.assertFalse(PaypalWindowTitle.contains("PayPal Checkout")==true, "PayPal Window incorrectly Displayed");
				}
			}else{
				ReporterLog.fail("| New PayPal Window Not Displayed"); 
				Assert.assertFalse(windows.size()>1, "New PayPal Window Not Displayed");
			}

		}catch(Exception e){
			CustomException.throwExceptionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "PAYMENT", "| Place Order As PayPal Payment",driver); 
		}

		return paypalModalFlag;
	}
	
}
