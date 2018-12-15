package com.ann.automation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.ReporterLog;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

public class HomePageObjects {
	private static WebDriver driver = null;
	static Utils utils = null;
	static XLUtils xlUtils = null;

	public HomePageObjects(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
		xlUtils = new XLUtils();
	}

	/**
	 * Get the shopping bag link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_SearchBox() throws Exception {

		if (Utils.isDeviceMobile())
			return utils.findElementByLocator(driver, "MB_SearchBox", "| Search Box on utility navigation");
		else
			return utils.findElementByLocator(driver, "HP_txt_SearchBox", "| Search Box on utility navigation");
	}

	/**
	 * Get the Sub menu category list
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_subMenuCategoryListTypes() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementsByLocator(driver, "MB_TP_SubMenuCategoryListType", "| Sub Menu Category listing");

		else
			return utils.findElementsByLocator(driver, "TP_SubMenuCategoryListType", "| Sub Menu Category listing");
	}

	public synchronized List<WebElement> sub_category() throws Exception {
		return utils.findElementsByLocator(driver, "MBL_HP_SubCategory", " Sub-sub category");
	}

	/**
	 * Get the Ham Burger element
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_hamBurger() throws Exception {
		return utils.findElementByLocator(driver, "MB_HamBurger", "| Ham Burger");
	}

	/**
	 * Get the Ham Burger element
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_CreditCard() throws Exception {
		return utils.findElementByLocator(driver, "MB_CreditCard", "| Credit card");
	}

	/**
	 * Get the Tall Category link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_TallCategory() throws Exception {
		return utils.findElementByLocator(driver, "TP_TallCategory", "| Tall Link on top navigation");
	}

	/**
	 * Get the Tall Sub Category link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_TallSubCategory() throws Exception {
		return utils.findElementByLocator(driver, "TP_TallSubCategory", "| Tall Link Sub category");
	}

	/**
	 * Get the My Account
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_myAccount() throws Exception {
		return utils.findElementByLocator(driver, "HP_MyAccountDashboard", "| My Account on utility navigation");
	}

	/**
	 * Get the Mini shopping bag link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_MiniShoppingBag() throws Exception {
		return utils.findElementsByLocator(driver, "HP_ShoppingBagMiniCart", "| Mini Shopping Bag utility navigation");
	}

	/**
	 * Get the Ann Logo Link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_annLogo() throws Exception {

		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MB_Logo", "Ann Logo link not found");
		else
			return utils.findElementByLocator(driver, "HP_Logo", "| Ann Logo on utility navigation");
	}

	/**
	 * Get the Collapse
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_Collapse() throws Exception {

		return utils.findElementByLocator(driver, "MB_CloseIcon", "| Close Icon Han Burger navigation");
	}

	/**
	 * Get the Create Account Link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_CreateAccount() throws Exception {

		return utils.findElementByLocator(driver, "HP_UN_Lnk_CreateAccount", "| Create Account on utility navigation");
	}

	/**
	 * Get the Check order Link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_CheckOrderStatus() throws Exception {
		if (Utils.device.equals("mobile")) {
			return utils.findElementByLocator(driver, "MB_OrderStatus", "Check Order Status on utility navigation");
		} else {
			if (xlUtils.env.equalsIgnoreCase("DryRun")) {
				return utils.findElementByLocator(driver, "HP_UN_Lnk_OrderStatus",
						"| Check Order Status on utility navigation");
			} else {
				return utils.findElementByLocator(driver, "HP_UN_Lnk_CheckOrderStatus",
						"| Check Order Status on utility navigation");
			}
		}
	}

	/**
	 * Get the shopping bag link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_shoppingBag() throws Exception {
		
		try {

			driver.findElement(By.xpath("//*[text()='Continue Shopping' or text()='Continue shopping']")).click();

		} catch (Exception e) {
		}

		utils.isElementPresent(driver, "HP_UN_Lnk_ShoppingBag");
		return utils.findElementByLocator(driver, "HP_UN_Lnk_ShoppingBag", "| Shopping bag on utility navigation");
	}

	/**
	 * Get the Search link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_search() throws Exception {
		return utils.findElementByLocator(driver, "HP_Lnk_Search", "| Search on utility navigation");
	}

	/**
	 * Get the SignIn link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_signIn() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_HP_lnk_SignIn", "| SignIn on utility navigation");
		else
			return utils.findElementByLocator(driver, "HP_lnk_SignIn", "| SignIn on utility navigation");
	}

	/**
	 * Get the SignIn link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_GuestSignIn() throws Exception {

		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MB_SignIn", "Guest SignIn on utility navigation");
		else
			return utils.findElementByLocator(driver, "HP_Btn_GuestSignIn", "| Guest SignIn on utility navigation");
	}

	/**
	 * Get the privacy shopping bag link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_storeLocator() throws Exception {

		if (Utils.isDeviceMobile() && Utils.brand.contains("ATF")) {
			return utils.findElementByLocator(driver, "MBL_HP_storelocater", "customer service link in the footer");

		} else {

			if (utils.device.equals("mobile") && Utils.brand.equals("LO")) {
				return utils.findElementByLocator(driver, "MBL_HP_storelocater_LO",
						"customer service link in the footer");

			} else
				return utils.findElementByLocator(driver, "HP_UN_Lnk_StoreLocator",
						"| store locator on utility navigation");
		}

	}

	/**
	 * Get the privacy policy link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_customerService() throws Exception {
		if (utils.device.equals("mobile")) {
			return utils.findElementByLocator(driver, "MBL_HP_footer_lnk_customerService",
					"customer service link in the footer");
		} else {
			return utils.findElementByLocator(driver, "HP_footer_lnk_customerService",
					"| customer service link in the footer");
		}
	}

	public synchronized WebElement lnk_customerServiceUtility() throws Exception {
		if (Utils.device.equals("mobile")) {
			return utils.findElementByLocator(driver, "MB_CustomerService", "customer service link in the footer");
		}
		return null;
	}

	/**
	 * Get the privacy policy link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_aboutAnnFactory() throws Exception {
		return utils.findElementByLocator(driver, "HP_footer_lnk_AboutAnnFactory",
				"| customer service link in the footer");
	}

	/**
	 * Get the privacy policy link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_ourCompany() throws Exception {
		return utils.findElementByLocator(driver, "HP_footer_lnk_ourcompany", "| our company link in the footer");
	}

	/**
	 * click o the anntaylor factory
	 * 
	 * @throws Exception
	 */
	public synchronized void click_aboutAnnFactory() throws Exception {
		utils.clickWebElement(driver, lnk_aboutAnnFactory(), "Ann taylor factory link clicked");
	}

	/**
	 * click on our company on footer
	 * 
	 * @throws Exception
	 */
	public synchronized void click_OurCompany() throws Exception {
		utils.clickWebElement(driver, lnk_aboutAnnFactory(), "Ann taylor factory link clicked");
	}

	/**
	 * Get the Customer service link under hamburger
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_customerServiceHamburger() throws Exception {
		return utils.findElementByLocator(driver, "MBL_HP_footer_lnk_customerService_HamBurger",
				"| customer service link in the footer");
	}

	/**
	 * click to the Customer service link under hamburger
	 * 
	 * @throws Exception
	 */
	public synchronized void click_lnk_customerServiceHamburger() throws Exception {
		utils.clickWebElement(driver, lnk_customerServiceHamburger(), "Customer Service in Hamburger link clicked");
	}

	/**
	 * Get the track link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_trackYourOrder() throws Exception {
		return utils.findElementByLocator(driver, "HP_Footer_lnk_trackOrder", "| track your order link in the footer");
	}

	/**
	 * Click the track order link in footer
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_trackYourOrder() throws Exception {
		utils.clickWebElement(driver, lnk_trackYourOrder(), "track your order not found");

	}

	/**
	 * Click on credit card
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_creditCard() throws Exception {
		utils.clickWebElement(driver, lnk_CreditCard(), "credit card not found");

	}

	/**
	 * Get the returns link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_returnsAndExchng() throws Exception {
		return utils.findElementByLocator(driver, "HP_Footer_lnk_ReturnsExchanges",
				"| returns and exchange link in the footer");
	}

	/**
	 * Click the returns link in footer
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_returnsAndExchange() throws Exception {
		utils.clickWebElement(driver, lnk_returnsAndExchng(), "returns and exchange not found");

	}

	/**
	 * Click the Ham Burger
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_hamburger() throws Exception {

		utils.isElementClickable(driver, "MB_HamBurger");
		utils.findElementByLocator(driver, "MB_HamBurger", "Ham Burger not found").click();
	}

	/**
	 * Click the Tall Navigation link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_tallCategory() throws Exception {
		utils.clickWebElement(driver, lnk_TallCategory(), "Tall Category not found");

	}

	/**
	 * Click the Customer Service link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_customerService() throws Exception {

		utils.clickWebElement(driver, lnk_customerService(), "Customer Service not found");

	}

	/**
	 * Click the Customer Service link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_customerServiceUtility() throws Exception {

		// utils.clickWebElement(driver, lnk_customerServiceUtility(), "Customer Service
		// not found");
		lnk_customerServiceUtility().click();
	}

	/**
	 * Click the Tall Navigation Sub category link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_tallSubCategory() throws Exception {
		utils.clickWebElement(driver, lnk_TallSubCategory(), "Tall Sub Category not found");

	}

	/**
	 * Get the about our adds link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_aboutOurAdds() throws Exception {
		return utils.findElementByLocator(driver, "HP_Footer_lnk_AboutOurAds", "| about our adds link in the footer");
	}

	/**
	 * Click the about our adds link in footer
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_aboutOurAdds() throws Exception {
		utils.clickWebElement(driver, lnk_aboutOurAdds(), "about our adds not found");

	}

	/**
	 * Get the anncares link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_annCares() throws Exception {
		return utils.findElementByLocator(driver, "HP_footer_lnk_annCares", "| ann Cares link in the footer");
	}

	/**
	 * Click the anncares link in footer
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_annCares() throws Exception {
		utils.clickWebElement(driver, lnk_annCares(), "anncares not found");

	}

	/**
	 * Get the about our adds link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_loft() throws Exception {
		return utils.findElementByLocator(driver, "HP_footer_lnk_loft", "| loft.com link in the footer");
	}

	/**
	 * Click the loft link in footer
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_loft() throws Exception {
		utils.clickWebElement(driver, lnk_loft(), "loft link not found");

	}

	/**
	 * Get the anntaylor link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_anntaylor() throws Exception {
		return utils.findElementByLocator(driver, "HP_footer_lnk_anntaylor", "| anntaylor.com link in the footer");
	}

	/**
	 * Click the anntaylor link in footer
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_anntaylor() throws Exception {
		utils.clickWebElement(driver, lnk_anntaylor(), "anntaylor link not found");

	}

	/**
	 * Get the footer store locator link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_footer_storeLocator() throws Exception {
		if (Utils.device.equals("mobile")) {
			return utils.findElementByLocator(driver, "MBL_HP_footer_lnk_storelocator",
					"storelocator link in the footer");
		} else {
			return utils.findElementByLocator(driver, "HP_footer_lnk_storelocator",
					"| storelocator link in the footer");
		}
	}

	/**
	 * Click on the california policy link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_footer_storeLocator() throws Exception {
		utils.clickWebElement(driver, lnk_footer_storeLocator(), "storelocator element not found");

	}

	/**
	 * Get the corporate link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_footer_corporate() throws Exception {

		return utils.findElementByLocator(driver, "HP_footer_lnk_corporate", "| corporate link in the footer");
	}

	/**
	 * Get the privacy policy link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized WebElement lnk_privacyPolicy() throws Exception {
		if (utils.device.equals("mobile")) {
			return utils.findElementByLocator(driver, "HP_Lnk_PrivacyPolicyMobile",
					"Privacy Link Policy in the footer");
		} else {
			return utils.findElementByLocator(driver, "HP_Lnk_PrivacyPolicy", "| Privacy Link Policy in the footer");
		}
	}

	/**
	 * Get the all rights reserved lable
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_allRightsReserved() throws Exception {
		return utils.findElementByLocator(driver, "HP_lable_rightReserved", "| Rights reserved in the footer");
	}

	/**
	 * Get the california policy link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_CaliforniaPolicy() throws Exception {
		return utils.findElementByLocator(driver, "HP_lnk_CaliforniaPolicy", "| California Policy in the footer");
	}

	/**
	 * Get the terms of use link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_TermsofUse() throws Exception {
		return utils.findElementByLocator(driver, "HP_lnk_TermsofUse", "| Terms of use Link in the footer");
	}

	/**
	 * Get the below Hanburger banner
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_bannerAfterHamBurger() throws Exception {

		return utils.findElementByLocator(driver, "MB_Promo", "|First Banner link");
	}

	/**
	 * Get the responsibly Ann link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_ResponsiblyAnn() throws Exception {
		return utils.findElementByLocator(driver, "HP_lnk_ResponsiblyAnn", "| Responsibly Ann Link in the footer");
	}

	/**
	 * Get the california transparency link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_CaliforniaTransparency() throws Exception {
		return utils.findElementByLocator(driver, "HP_lnk_CaliforniaTransparency",
				"| California Transparency Link in the footer");
	}

	// /**
	// * Get the corporate link
	// *
	// * @return Webelement
	// * @throws Exception
	// */
	// public WebElement lnk_Corporate() throws Exception {
	// return utils.findElementByLocator(driver, "HP_lnk_Corporate", "| Corporate
	// Link in the footer");
	// }
	/**
	 * Get the investors link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_Investors() throws Exception {
		return utils.findElementByLocator(driver, "HP_lnk_corporate_Investors", "| Investors Link in the footer");
	}

	/**
	 * Get the site map link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_SiteMap() throws Exception {
		return utils.findElementByLocator(driver, "HP_lnk_SiteMap", "| Privacy Link Policy in the footer");
	}

	/**
	 * Get the Contact Us link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_ContactUs() throws Exception {
		return utils.findElementByLocator(driver, "HP_Footer_lnk_ContactUS", "| Contact Us in the footer");
	}

	/**
	 * Get the shipping info link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_shippingInfo() throws Exception {
		return utils.findElementByLocator(driver, "HP_Footer_lnk_ShippingInfo", "| shipping info in the footer");
	}

	/**
	 * Click on the california policy link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_shippingInfo() throws Exception {
		utils.clickWebElement(driver, lnk_shippingInfo(), "shipping info element not found");

	}

	/**
	 * Get the size guide link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_sizeGuide() throws Exception {
		return utils.findElementByLocator(driver, "HP_Footer_lnk_SizeGuide", "| size guide in the footer");
	}

	/**
	 * Click on the size guide link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_sizeGuide() throws Exception {
		utils.clickWebElement(driver, lnk_sizeGuide(), "size guide element not found");

	}

	/**
	 * Get the email signup text box link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_EmailSignUp() throws Exception {

		return utils.findElementByLocator(driver, "HP_footer_txt_emailSignUp", "| Email signup textbox in the footer");
	}

	/**
	 * Get the email id textfield link
	 * 
	 * @return Webelement
	 * @throws Exception
	 *             (Added by Robin)
	 */
	public synchronized WebElement txt_EmailID() throws Exception {

		return utils.findElementByLocator(driver, "HP_Footer_Cheetah_Modal_Email_Id",
				"| Email signup textbox in the footer");
	}

	/**
	 * Get the First Name textfield link
	 * 
	 * @return Webelement
	 * @throws Exception
	 *             (Added by Robin)
	 */
	public synchronized WebElement txt_FName() throws Exception {

		return utils.findElementByLocator(driver, "HP_Footer_Cheetah_Modal_FName",
				"| First Name entered in textbox in the Cheetah Modal");
	}

	/**
	 * Get the First Name textfield link
	 * 
	 * @return Webelement
	 * @throws Exception
	 *             (Added by Robin)
	 */
	public synchronized WebElement txt_Street() throws Exception {

		return utils.findElementByLocator(driver, "HP_Footer_Cheetah_Modal_Street",
				"| Street entered in textbox in the Cheetah Modal");
	}

	/**
	 * Get the First Name textfield link
	 * 
	 * @return Webelement
	 * @throws Exception
	 *             (Added by Robin)
	 */
	public synchronized WebElement txt_City() throws Exception {

		return utils.findElementByLocator(driver, "HP_Footer_Cheetah_Modal_City",
				"| Street entered in textbox in the Cheetah Modal");
	}

	/**
	 * Get the Last Name textfield link
	 * 
	 * @return Webelement
	 * @throws Exception
	 *             (Added by Robin)
	 */
	public synchronized WebElement txt_LName() throws Exception {

		return utils.findElementByLocator(driver, "HP_Footer_Cheetah_Modal_LName",
				"| Last Name entered in textbox in the Cheetah Modal");
	}

	/**
	 * Get the PinCode textfield link
	 * 
	 * @return Webelement
	 * @throws Exception
	 *             (Added by Robin)
	 */
	public synchronized WebElement txt_PinCode() throws Exception {

		return utils.findElementByLocator(driver, "HP_Footer_Cheetah_Modal_PinCode",
				"| Pin Code entered in textbox in the Cheetah Modal");
	}

	/**
	 * Get the PinCode textfield link
	 * 
	 * @return Webelement
	 * @throws Exception
	 *             (Added by Robin)
	 */
	public synchronized WebElement txt_Save_Button() throws Exception {

		return utils.findElementByLocator(driver, "HP_Footer_Cheetah_Modal_PinCode",
				"| Last Name entered in textbox in the Cheetah Modal");
	}

	/**
	 * Get the Thank you message link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_EmailSignUpThankYouMessage() throws Exception {
		return utils.findElementByLocator(driver, "Join_ThankYou", "| Thank you message link");
	}

	/**
	 * Get the Thank you message link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_LGEMailSignUpModalMessage() throws Exception {
		return utils.findElementByLocator(driver, "LG_Join_ThankYou", "| Thank you message link");
	}

	/**
	 * Get the Thank you message link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_ATEmailSignUpThankYouMessage() throws Exception {
		return utils.findElementByLocator(driver, "AT_Join_ThankYou", "| Thank you message link");
	}

	/**
	 * Get the Confirm Email which already entered link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_EmailSignUpConfirmEmail() throws Exception {
		return utils.findElementByLocator(driver, "Join_EmailAddress", "| Confirm Email which already entered link");
	}

	/**
	 * Get the Email List Selected
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_EmailList() throws Exception {
		return utils.findElementByLocator(driver, "Join_SelectEmailList", "| Email List Selected link");
	}

	/**
	 * Click on the Email SignUp
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_lnk_emailSignUp() throws Exception {
		utils.clickWebElement(driver, txt_EmailSignUp(), "email signup element not found");

	}

	/**
	 * Get the Email Label Selected
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_EmailListLabel() throws Exception {
		return utils.findElementByLocator(driver, "HP_EmailLabel", "| Email Label Selected link");
	}

	/**
	 * Click on the Email Label SignUp
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_lnk_emailSignUpLabel() throws Exception {
		utils.clickWebElement(driver, txt_EmailListLabel(), "email signup label element not found");

	}

	/**
	 * Get the email confirm signup text box link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_EmailSignUpConfirm() throws Exception {
		return utils.findElementByLocator(driver, "HP_footer_txt_emailConfirm",
				"| Email Confirm signup textbox in the footer");
	}

	/**
	 * Get the Error message Email SignUp
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> error_EmailSignUp() throws Exception {
		return utils.findElementsByLocator(driver, "err_EmailSignUp", "| Error Email signup in the footer");
	}

	/**
	 * Click on the Email SignUp
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_lnk_emailSignUpConfirm() throws Exception {
		utils.clickWebElement(driver, txt_EmailSignUpConfirm(), "email Confirm signup element not found");

	}

	/**
	 * Get the Join Button Email SignUp
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_EmailSignUpJoin() throws Exception {
		return utils.findElementByLocator(driver, "HP_EmailSignUp_Join", "| Join Email signup button in the footer");
	}

	/**
	 * Get the Save Button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_SaveButton() throws Exception {
		return utils.findElementByLocator(driver, "HP_Footer_Cheetah_Modal_Save_Button",
				"| Join Email signup button in the footer");
	}

	/**
	 * Get the Save Button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_SelectBrand() throws Exception {
		return utils.findElementByLocator(driver, "HP_Select_Brand_CheckBox",
				"| Join Email signup button in the footer");
	}

	/**
	 * Get the Save Button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_CloseBrandModal() throws Exception {
		return utils.findElementByLocator(driver, "HP_Close_Brand_Modal", "| Join Email signup button in the footer");
	}

	/**
	 * Get the Save Button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_CloseModalButton() throws Exception {
		return utils.findElementByLocator(driver, "HP_Footer_Cheetah_Modal_Close_Button",
				"| Join Email signup button in the footer");
	}

	/**
	 * Click on the Label message Email SignUp
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_btn_EmailSignUpJoin() throws Exception {
		utils.clickWebElement(driver, btn_EmailSignUpJoin(), "Join Email signup button in the footer");

	}

	/**
	 * Click on the Save Button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_Cheetah_Modal_Save_Button() throws Exception {
		utils.clickWebElement(driver, btn_SaveButton(), "Clicking on Save Button");
	}

	/**
	 * Click on the Save Button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_SelectBrand() throws Exception {
		utils.clickWebElement(driver, btn_SelectBrand(), "Clicking on Save Button");
	}

	/**
	 * Click on the Save Button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_closeBrandModalButton() throws Exception {
		utils.clickWebElement(driver, btn_CloseBrandModal(), "Clicking on Save Button");
	}

	/**
	 * Click on the Save Button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_ModalClose_Button() throws Exception {
		utils.clickWebElement(driver, btn_CloseModalButton(), "Clicking on Save Button");
	}

	/**
	 * Click on the privacy link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_lnk_privacyPolicy_Click() throws Exception {
		utils.clickWebElement(driver, lnk_privacyPolicy(), "privacy element not found");

	}

	/**
	 * Click on the store locator footer
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_lnk_store_Locatore() throws Exception {
		utils.clickWebElement(driver, lnk_footer_storeLocator(), "Store Locatore element not found");
		// lnk_footer_storeLocator().click();
	}

	/**
	 * Click on the coorperate footer
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized void click_lnk_corporate() throws Exception {
		utils.clickWebElement(driver, lnk_footer_corporate(), "corporate element not found");
	}

	/**
	 * Click on the california policy link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_CaliforniaPolicy() throws Exception {
		utils.clickWebElement(driver, lnk_CaliforniaPolicy(), "california policy element not found");
		// lnk_CaliforniaPolicy().click();
	}

	/**
	 * Click on the term of use link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_TermsofUse() throws Exception {
		utils.clickWebElement(driver, lnk_TermsofUse(), "california policy element not found");

	}

	/**
	 * Click on the responsibly Ann link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_ResponsiblyAnn() throws Exception {
		utils.clickWebElement(driver, lnk_ResponsiblyAnn(), "california policy element not found");

	}

	/**
	 * Click on the California transparency link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_CaliforniaTransparency() throws Exception {
		utils.clickWebElement(driver, lnk_CaliforniaTransparency(), "california policy element not found");

	}

	/**
	 * Click on the corporate link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	// public void click_lnk_Corporate() throws Exception {
	// utils.clickWebElement(driver, lnk_Corporate(), "corporate element not
	// found");
	//
	// }
	/**
	 * Click on the investors link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_Investors() throws Exception {
		utils.clickWebElement(driver, lnk_Investors(), "investors element not found");

	}

	/**
	 * Get the top navigation on the home page
	 *
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void top_navigation_link() throws Exception {
		utils.findElementByLocator(driver, "top_navigation", "top navigation not found");
	}

	/**
	 * Click on the Shopping bag link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_shoppingBag() throws Exception {

		WebElement elem = lnk_shoppingBag();
		utils.scrollUpToTopOfPage(driver);
		Thread.sleep(3000);
		elem.click();
		ReporterLog.log("Clicked on Shopping Bag link on Header...");
	}

	/**
	 * Click on the Search link on the home page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_search() throws Exception {
		// utils.clickWebElement(driver, lnk_search(), "investors element not found");
		lnk_search().click();
	}

	/**
	 * Get sub menu content list
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> getMegaMenuList() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementsByLocator(driver, "MB_TopNavigationIndividualCategory", "Sub Menu Navigation");
		else
			return utils.findElementsByLocator(driver, "SubHeader_Lnk_TopNavigation", "Sub Menu Navigation");

	}

	/**
	 * click on Guest SignIn Button
	 *
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_GuestSignIn() throws Exception {
		if (Utils.device.equals("mobile")) {
			// utils.clickWebElement(driver, lnk_GuestSignIn(), "Guest SignIn button not
			// found");
			// utils.scrollDownToElement(driver, lnk_GuestSignIn());
			lnk_GuestSignIn().click();
		} else {
			utils.mouseHoverJScript(driver, lnk_signIn());
			utils.clickWebElement(driver, lnk_GuestSignIn(), "Guest SignIn button not found");
		}

	}

	/**
	 * Click on the create account page
	 * 
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_lnk_CreateAccount() throws Exception {
		utils.clickWebElement(driver, lnk_CreateAccount(), "create account element not found");

	}

	/**
	 * Click on the check order status page
	 * 
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_lnk_CheckOrderStatus() throws Exception {

		utils.clickWebElement(driver, lnk_CheckOrderStatus(), "check order status element not found");
		// lnk_CheckOrderStatus().click();
	}

	/**
	 * Click on the Site Map
	 * 
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_lnk_SiteMap() throws Exception {
		utils.clickWebElement(driver, lnk_SiteMap(), "Site Map element not found");

	}

	/**
	 * Click on the contact us
	 * 
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_lnk_ContactUs() throws Exception {
		utils.clickWebElement(driver, lnk_ContactUs(), "Contact Us element not found");

	}

	/**
	 * click on Store Location link
	 *
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_storeLocator() throws Exception {
		utils.clickWebElement(driver, lnk_storeLocator(), "store locator link not found");
	}

	/**
	 * click on Home Page Logo
	 *
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_AnnLogo() throws Exception {
		// utils.clickWebElement(driver, lnk_annLogo(), "Ann Logo link not found");
		lnk_annLogo().click();
	}

	/**
	 * click on Collapse Icon in HamBurger
	 *
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_collapse() throws Exception {
		utils.findElementByLocator(driver, "MB_CloseIcon", "hamburger clpse element not found").click();

	}

	/**
	 * click on Submenu link
	 *
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_SubMenu(WebElement submenu) throws Exception {
		utils.clickWebElement(driver, submenu, "Sub Menu link not found");

	}

	/**
	 * click on My Account Button
	 *
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_MyAccount() throws Exception {
		utils.clickWebElement(driver, lnk_myAccount(), "My Account link not found");

	}

	/**
	 * Get Mini cart size
	 *
	 * @return int
	 * @throws Exception
	 */
	public synchronized int miniCartSize(List<WebElement> cart) throws Exception {
		return cart.size();
	}

	/**
	 * Check the Mini cart items in Paranthesis
	 *
	 * @return int
	 * @throws Exception
	 */
	public synchronized boolean checkTotalItemInMiniCartBagParanthesis(int size) throws Exception {
		if (!lnk_shoppingBag().getText().contains("(" + size + ")"))
			return false;
		else
			return true;
	}

	/**
	 * Verify the displayed text
	 *
	 * @return int
	 * @throws Exception
	 */

	public synchronized void verifyText(String text, WebElement element) {
		if (!element.getText().contains(text)) {
			Assert.fail("Text Not present");
			ReporterLog.fail("Text Not present");
		}
	}

	/**
	 * Check All the links in the mega menu are working properly
	 *
	 * @return void
	 * @throws Exception
	 */
	public synchronized void clickOnAllSubMenuItemNavigationCheck(String exceptionMessage) throws Exception {

		try {

			List<WebElement> megaMenuElement = new ArrayList<WebElement>();
			megaMenuElement = getMegaMenuList();

			List<WebElement> subMenuElementTypes = new ArrayList<WebElement>();

			for (int i = 0; i < megaMenuElement.size(); i++) {

				if (i > 0)
					Utils.waitForPageLoaded(driver);

				utils.mouseHoverJScript(driver, getMegaMenuList().get(i));

				subMenuElementTypes = lnk_subMenuCategoryListTypes();

				for (int j = 0; j < subMenuElementTypes.size(); j++) {

					String firstHalfxpath = ".//*[@data-slnm-id='megaMenu']/div[";
					String secondHalfyxpath = "]/ul/li/a";

					if (j > 0) {

						Utils.waitForPageLoaded(driver);
						utils.mouseHoverJScript(driver, getMegaMenuList().get(i));

					}

					List<WebElement> subMenuElement = driver
							.findElements(By.xpath(firstHalfxpath + (j + 1) + secondHalfyxpath));

					if (subMenuElement.size() == 0)
						Assert.fail("No such element in the list");

					for (int k = 0; k < subMenuElement.size(); k++) {

						if (k > 0) {
							Utils.waitForPageLoaded(driver);
							WebElement megaMenu = getMegaMenuList().get(i);
							utils.mouseHoverJScript(driver, megaMenu);

						}
						if (j > 0) {
							utils.mouseHoverJScript(driver, getMegaMenuList().get(i));
						}

						List<WebElement> subMenuElementNewReference = driver
								.findElements(By.xpath(firstHalfxpath + (j + 1) + secondHalfyxpath));
						JavascriptExecutor jse = (JavascriptExecutor) driver;
						String subMenuProductListName = jse
								.executeScript("return arguments[0].text", subMenuElementNewReference.get(k))
								.toString();
						click_SubMenu(subMenuElementNewReference.get(k));
						Utils.waitForPageLoaded(driver);
						// Assert.assertTrue(utils.isElementVisible(driver, "PLP_AllProductListing",
						// "PLP Page"), "PLP not validation");
						utils.validateTextInURL(driver, subMenuProductListName.toLowerCase().replaceAll(" ", "-"),
								"Value in URL not present");
						driver.navigate().back();
						Thread.sleep(5000);
						Utils.waitForPageLoaded(driver);
					}

				}
			}

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			Assert.fail();
		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			Assert.fail();
		}
	}

	/**
	 * @description: Shopping cart size change
	 * @param: int
	 * @throws: Exception
	 */

	public synchronized void checkCartSizeAfterUpdate(int oldSize, int newSize) {
		try {
			if (oldSize == newSize)
				Assert.fail("Items in bag not updated");
			else
				ReporterLog.pass("Item Upgraded in bag");
		} catch (Exception e) {
			Assert.fail("Exception occurred" + e);
			ReporterLog.fail("Exception occurred" + e);
		}
	}

	/**
	 * @description: Verify Page Title
	 * @param: String
	 * @throws: Exception
	 */
	public synchronized void validatePageTitle(String expectedtitle) {
		try {
			String actualtitle = driver.getTitle();
			if (actualtitle.contains(expectedtitle))
				ReporterLog.pass("Value Matched");
			else {
				Assert.fail("Title Not matched-Page Not navigated properly");
				ReporterLog.fail("Title Not matched-Page Not navigated properly");
			}

		} catch (Exception e) {
			Assert.fail("Title Not matched-Page Not navigated properly" + e);
			ReporterLog.fail("Title Not matched-Page Not navigated properly" + e);
		}
	}

	/**
	 * @decription: get the Location value
	 */

	public synchronized double getTheElementLocations(WebElement element, String corrdinateChoice) {
		Point point = element.getLocation();
		switch (corrdinateChoice) {

		case "x-axis":
			return point.getX();

		case "y-axis":
			return point.getY();

		}
		return 0;
	}

	/**
	 * Get the ann career link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_annCareer() throws Exception {
		return utils.findElementByLocator(driver, "HP_footer_lnk_careers", "| ann Cares link in the footer");
	}

	/**
	 * Click the career link in footer
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_lnk_annCareer() throws Exception {
		utils.clickWebElement(driver, lnk_annCareer(), "anncares not found");

	}

	/**
	 * get invalid email address error message
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement invalidErrorMsg() throws Exception {
		return utils.findElementByLocator(driver, "err_EmailAddressInvalidEmail", "error message not found");

	}

	/**
	 * Get the MyAccount link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_nav_myAccount() throws Exception {
		return utils.findElementByLocator(driver, "MBL_HP_lnk_MyAccount", "| My Account in hamburger");
	}

	public synchronized String getSearchBoxText() throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String text = (String) js.executeScript(
				"return window.getComputedStyle(document.querySelector('.typeahead-overlay .typeahead-wrapper'),':before').getPropertyValue('content')");
		return text;
	}

	public synchronized void clickWishListUtility() {

		try {

			utils.isElementPresent(driver, "Wishlist_Utility_Nav");

			utils.findElementByLocator(driver, "Wishlist_Utility_Nav", "Wishlist not found in Utility Navigation")
					.click();

			ReporterLog.actionMsg("Utility Navigation clicked in Utility Navigation");

		} catch (Exception e) {

			ReporterLog.fail("Not able to click on Wishlist");
		}
	}

}
