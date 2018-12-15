package com.ann.automation.pages;

import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.ShoppingBagPageObjects;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

public class CheckoutPageObject {
	private static WebDriver driver = null;
	static Utils utils = null;
	ShoppingBagPageObjects shoppingBag = null;
	HomePageObjects homepage = null;
	static XLUtils xlUtils = null;
	//public Logger Log = Logger.getLogger(this.getClass().getSimpleName());
	
	public CheckoutPageObject(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
		shoppingBag = new ShoppingBagPageObjects(driver, brand, browser, device);
		homepage = new HomePageObjects(driver, brand, browser, device);
		xlUtils = new XLUtils();
	}

	/**
	 * Shopping cart Login Modal: Guest Email id
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement sb_LoginModalGuestEmail() throws Exception {
		
		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_SB_LoginModalGuestEmail",
					"| SB PopUp: Guest Email addr Txt field not present");

		return utils.findElementByLocator(driver, "SB_LoginModalGuestEmail",
				"| SB PopUp: Guest Email addr Txt field not present");
	}

	/**
	 * Shopping cart Login Modal: Register customer Email id
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement sb_LoginModalRegCustmEmail() throws Exception {

		return utils.findElementByLocator(driver, "SB_LoginModalRegCustmEmail",
				"| SB PopUp: Customer Email addr Txt field not present");
	}

	/**
	 * Shopping cart Login Modal: Register customer password
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement sb_LoginModalRegCustmPwd() throws Exception {

		return utils.findElementByLocator(driver, "SB_LoginModalRegCustmPwd",
				"| SB PopUp: Customer pwd addr Txt field not present");
	}

	/**
	 * Shopping cart Login Modal: Guest continue button
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_LoginModalGuestContinue() throws Exception {

		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_SB_LoginModalGuestContinue",
					"| SB PopUp: Guest continue button not present");

		else
			return utils.findElementByLocator(driver, "SB_LoginModalGuestContinue",
					"| SB PopUp: Guest continue button not present");
	}

	/**
	 * Checkout Header
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_CheckoutHeader() throws Exception {

		return utils.findElementByLocator(driver, "CheckoutPageHeading", "| Checkout Page header is not present");
	}

	/**
	 * Click Proceed to Checkout as Guest Btn
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement btn_clickProceedGuestCheckout() throws Exception {
		return utils.findElementByLocator(driver, "checkout_checkoutAsGuest", "| Checkout as Guest not present");
	}

	@SuppressWarnings("unchecked")
	public synchronized void checkOutAsGuest(WebDriver driver, String guestEmail) throws Exception {

		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			Thread.sleep(4000);
			utils.waitForElementToPresent(driver, "SB_ProceedToCheckout", "proceed to checkout button not loaded",
					wait);
			utils.scrollToViewElement(driver, shoppingBag.btn_ProceedToCheckout());
			Thread.sleep(4000);
			shoppingBag.btn_ProceedToCheckout().click();
			Utils.waitForPageLoaded(driver);
			// utils.clickWebElement(driver,
			// shoppingBag.btn_ProceedToCheckout(), "proceed to checkout not
			// found");
			Reporter.log(Constants.DELIMITER + "*****Shopping Bag: clicked on proceed to checkout*****");
			Thread.sleep(4000);
			utils.isElementPresentForAllDevice(driver, "SB_LoginModalGuestEmail");
			if (utils.device.equals("desktop") || Utils.browser.equals("android")) {
				if (Utils.browser.equals("desktop")) {
					utils.clickWebElement(driver, sb_LoginModalGuestEmail(), "guest text box");// sb_LoginModalGuestEmail().click();
				}

				if (Utils.device.equals("desktop") || Utils.browser.equals("android")) {
					Thread.sleep(4000);
					sb_LoginModalGuestEmail().sendKeys(guestEmail);

					if (Utils.device.equals("desktop")) {

						Assert.assertTrue(utils.isElementPresent(driver, "SB_LoginModalGuestContinue", "continue btn"),
								"Guest continue button not displayed");
			
					} else {

						Thread.sleep(1000);

						utils.waitForElementToBeClickable(driver, "MBL_SB_LoginModalGuestContinue",
								"checkout now not clickable");

						Assert.assertTrue(
								utils.isElementPresent(driver, "MBL_SB_LoginModalGuestContinue", "continue btn"),
								"Guest continue button not displayed");

					}

					btn_LoginModalGuestContinue().click();
					Reporter.log(Constants.DELIMITER + "*****Shopping Bag: clicked on guest continue button*****");
					Utils.waitForPageLoaded(driver);
		
				}
			} else {
				if (Utils.browser.equals("ios")) {
					utils.scrollDownToElement(driver, sb_LoginModalGuestEmail());
					TouchAction a = new TouchAction((MobileDriver) driver);
					a.tap((int) homepage.getTheElementLocations(sb_LoginModalGuestEmail(), "x-axis") + 10,
							(int) homepage.getTheElementLocations(sb_LoginModalGuestEmail(), "y-axis") + 100).perform();

					((IOSDriver<WebElement>) driver).getKeyboard().sendKeys(guestEmail);
					Thread.sleep(2000);
					// utils.clickOnTextBox_EnterData_Mobile("Email Address",
					// guestEmail);
					btn_LoginModalGuestContinue().click();
					Reporter.log(Constants.DELIMITER + "*****Shopping Bag: clicked on guest continue button*****");
					Utils.waitForPageLoaded(driver);
					Assert.assertTrue(utils.isElementsPresent(driver, "SP_ShippingPanel", "shipping Page not loaded"));

				}
			}
		}

		catch (Exception e) {
			CustomException.throwExceptionError(e, "ShoppingBag", "Checkout action failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "ShoppingBag", "Checkout action failed", driver);
		}

	}

	/**
	 * Shopping cart Login Modal: Guest continue button
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_LoginModalRegUserSignIn() throws Exception {

		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_SB_LoginModalRegisteredSignIn",
					"| SB PopUp: Registered SignIN button not present");

		else
			return utils.findElementByLocator(driver, "SB_LoginModalRegisteredSignIn",
					"| SB PopUp: Registered SignIN button not present");
	}

	public synchronized void clickProceedToCheckout(WebDriver driver) throws Exception {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(1,
				TimeUnit.SECONDS);
		utils.waitForElementToPresent(driver, "SB_ProceedToCheckout", "proceed to checkout button not loaded", wait);
		utils.scrollToViewElement(driver, shoppingBag.btn_ProceedToCheckout());
		Thread.sleep(2000);
		shoppingBag.btn_ProceedToCheckout().click();
		Utils.waitForPageLoaded(driver);
	}

	@SuppressWarnings("unchecked")
	public synchronized void checkOutAsRegistered(WebDriver driver, String regUserID, String regPwd) throws Exception {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			utils.waitForElementToPresent(driver, "SB_ProceedToCheckout", "proceed to checkout button not loaded",
					wait);
			utils.scrollToViewElement(driver, shoppingBag.btn_ProceedToCheckout());
			Thread.sleep(2000);
			shoppingBag.btn_ProceedToCheckout().click();

			Utils.waitForPageLoaded(driver);
			// utils.clickWebElement(driver,
			// shoppingBag.btn_ProceedToCheckout(), "proceed to checkout not
			// found");
			Reporter.log(Constants.DELIMITER + "*****Shopping Bag: clicked on proceed to checkout*****");
			if (Utils.device.equals("desktop") || Utils.browser.equals("android")) {

				// utils.clickWebElement(driver, sb_LoginModalRegCustmEmail(),
				// "registered text box");
				Thread.sleep(2000);
				sb_LoginModalRegCustmEmail().click();
				sb_LoginModalRegCustmEmail().clear();

				sb_LoginModalRegCustmEmail().sendKeys(regUserID);
				sb_LoginModalRegCustmPwd().sendKeys(regPwd);
				if (Utils.device.equals("desktop")) {
					Assert.assertTrue(utils.isElementPresent(driver, "SB_LoginModalRegisteredSignIn", "SignIn btn"),
							"Registered continue button not displayed");
					// utils.waitForElementToBeClickable(driver,
					// "SB_LoginModalGuestContinue", "checkout now not
					// clickable");
				} else {
					Assert.assertTrue(utils.isElementPresent(driver, "MBL_SB_LoginModalRegisteredSignIn", "SignIn btn"),
							"Registered continue button not displayed");
					utils.waitForElementToBeClickable(driver, "MBL_SB_LoginModalGuestContinue",
							"checkout now not clickable");
				}
				btn_LoginModalRegUserSignIn().click();
				Reporter.log(Constants.DELIMITER + "*****Shopping Bag: clicked on registered signin button*****");
				Thread.sleep(1000);
				Utils.waitForPageLoaded(driver);
			}

			else {
				if (Utils.browser.equals("ios")) {
					utils.scrollDownToElement(driver, sb_LoginModalRegCustmEmail());
					TouchAction a = new TouchAction((MobileDriver) driver);
					a.tap((int) homepage.getTheElementLocations(sb_LoginModalRegCustmEmail(), "x-axis") + 10,
							(int) homepage.getTheElementLocations(sb_LoginModalRegCustmEmail(), "y-axis") + 100)
							.perform();

					((IOSDriver<WebElement>) driver).getKeyboard().sendKeys(regUserID);
					Thread.sleep(2000);
					utils.findElementByIOSNameLocator("Done").click();
					utils.findElementByIOSNameLocator("MBL_Password_IOS").sendKeys(regPwd);
					// a.tap((int) homepage.getTheElementLocations(sb_LoginModalRegCustmPwd(),
					// "x-axis") + 10,
					// (int) homepage.getTheElementLocations(sb_LoginModalRegCustmPwd(), "y-axis") +
					// 100)
					// .perform();
					//
					// ((IOSDriver<WebElement>) driver).getKeyboard().sendKeys(regPwd);

					btn_LoginModalRegUserSignIn().click();
					Reporter.log(Constants.DELIMITER + "*****Shopping Bag: clicked on registered signin button*****");
					Utils.waitForPageLoaded(driver);
					Assert.assertTrue(
							utils.isElementPresent(driver, "SP_CheckoutTextHeader", "checkout Page not loaded"));
				}
			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "ShoppingBag", "Registered Checkout action failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "ShoppingBag", "Registered Checkout action failed", driver);
		}

		Utils.waitForPageLoaded(driver);
		//Log.info("Logged In as Registered User");
		Thread.sleep(4000);
	}
}