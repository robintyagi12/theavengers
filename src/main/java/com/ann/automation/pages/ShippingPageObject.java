package com.ann.automation.pages;

import java.util.List;

//import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.MyAccountPageObject;
import com.ann.automation.pages.ShoppingBagPageObjects;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class ShippingPageObject {
	private static WebDriver driver = null;
	static Utils utils = null;
	public HomePageObjects homepage = null;
	public ShoppingBagPageObjects shoppingBagPage = null;
	public MyAccountPageObject myAccountPage = null;
	//public Logger Log = Logger.getLogger(this.getClass().getSimpleName());
	public StringBuffer errorBuffer = new StringBuffer();

	public ShippingPageObject(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
		shoppingBagPage = new ShoppingBagPageObjects(driver, brand, browser, device);
		myAccountPage = new MyAccountPageObject(driver, brand, browser, device);
		homepage = new HomePageObjects(driver, brand, browser, device);
	}

	/**
	 * Shipping page: Shipping Hearder
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void SP_OSExpandPromoCode() throws Exception, AssertionError {
		if (utils.findElementByLocator(driver, "SB_HaveAPromoCodeSection", "").getAttribute("class").toLowerCase()
				.contains("hide")) {
			shoppingBagPage.txt_promoCodeLabel().click();
			Thread.sleep(1000);
		}
	}

	/**
	 * Shipping page: Shipping Hearder
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_shippingHeader() throws Exception, AssertionError {
		if (utils.device.equals("mobile")) {
			return utils.findElementByLocator(driver, "MBL_SP_header_shipping",
					"SP: First Name text field not present");
		} else {
			return utils.findElementByLocator(driver, "SP_header_shipping", "SP: First Name text field not present");
		}
	}

	/**
	 * Shipping page: Shipping Addr | First Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_AddrFirstName() throws Exception, AssertionError {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_SP_Addr_FirstName",
					"SP: First Name text field not present in mobile");

		return utils.findElementByLocator(driver, "SP_Addr_FirstName", "SP: First Name text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | First Name | error
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_AddrFirstName_Error() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Addr_FirstNameError", "SP: First Name error not present");
	}

	/**
	 * Shipping page: Shipping Addr | View Bag
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_ViewBag_Mobile() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "MBL_ViewItemBag", "SP: View bag field not present");
	}

	/**
	 * Shipping page: Shipping Addr | View Bag
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Shipping_EstimatedDate() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_MBL_EDD", "SP: SLA Estimated date");
	}

	/**
	 * Shipping page: Shipping Addr | Last Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_AddrLastName() throws Exception, AssertionError {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_SP_Addr_LastName",
					"SP: Last Name text field not present in mobile");

		return utils.findElementByLocator(driver, "SP_Addr_LastName", "SP: Last Name text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | Addr1
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_Addr1() throws Exception, AssertionError {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_SP_Addr_Addr1",
					"SP: Addr1 text field not present in mobile");

		return utils.findElementByLocator(driver, "SP_Addr_Addr1", "SP: Addr1 text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | Addr2
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_Addr2() throws Exception, AssertionError {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_SP_Addr_Addr2",
					"SP: Addr2 text field not present in mobile");

		return utils.findElementByLocator(driver, "SP_Addr_Addr2", "SP: Addr2 text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | PO Box
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_POBox() throws Exception, AssertionError {

		// if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
		if (Utils.isDeviceMobile())
			return utils.findElementByLocator(driver, "MBL_SP_Addr_POBox", "SP: PO Box CheckBox not present in mobile");

		return utils.findElementByLocator(driver, "SP_Addr_POBox", "SP: PO Box CheckBox not present");
	}

	/**
	 * Shipping page: Shipping Page Bag header
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_shoppingBag_header() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "Checkout_Bag_Header", "shipping- bag header");
	}

	/**
	 * Shipping page: Shipping Method header
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_shippingMethod_Heading() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "MBL_ShippingMethod", "shipping- bag header");
	}

	/**
	 * Shipping page: Selected Shipping Method
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Selected_shippingMethod_Heading() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_MBL_ShippingMethodSelected",
				"shipping- selected shipping method");
	}

	/**
	 * Shipping page: NBD Price
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_TBD_Price() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_TBD_Price", "shipping- bag header");
	}

	/**
	 * Shipping page: Shipping Page Bag edit modal close button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_shoppingBag_EditModal_close_btn() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Edit_SBContentModal_Close",
				"shipping- bag edit modal close button");
	}

	/**
	 * Shipping page: Shipping Page Bag edit modal cancel button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_shoppingBag_EditModal_cacncel_btn() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Edit_SBContentModal_Cancel_btn",
				"shipping- bag edit modal cancel button");
	}

	/**
	 * Shipping page: Shipping Addr | Zipcode
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_ZipCode() throws Exception, AssertionError {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_SP_Addr_Zipcode",
					"SP: ZipCode Text field not present in mobile");

		return utils.findElementByLocator(driver, "SP_Addr_Zipcode", "SP: ZipCode Text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | Close Address Suggestion
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_CloseAddressSuggestion() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_CloseAddressSuggestion",
				"SP: Close Address Suggestion not present");
	}

	/**
	 * Shipping page: Shipping Addr | City
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_City() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Addr_City", "SP: city text field not present");
	}

	/**
	 * Method to get estimate taxes label element on shopping bag page present in
	 * order summary page
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lbl_Price() throws Exception {
		return utils.findElementsByLocator(driver, "SB_ProductPrice", "| SB : Promo Discount in Order Summary");

	}

	/**
	 * Shipping page: Shipping Addr | PhoneNumber
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_PhoneNum() throws Exception, AssertionError {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_SP_Addr_PhNum",
					"SP: Phone Number text field not present in mobile");
		return utils.findElementByLocator(driver, "SP_Addr_PhNum", "SP: phone number text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | State
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_Addr_State() throws Exception, AssertionError {
		return utils.findElementsByLocator(driver, "SP_Addr_State", "SB: You have No items in your shopping bag");
	}

	/**
	 * Shipping page: Shipping Addr | State
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_StateDropDown() throws Exception, AssertionError {

		if (utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_SP_state", "SP: state");
		else
			return utils.findElementByLocator(driver, "SP_Addr_State", "SP: state");
	}

	/**
	 * Shipping page: Add Shipping Addr Value| State
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Add_Addr_StateDropDown() throws Exception, AssertionError {
		WebElement elem = null;
		if (utils.device.equals("mobile")) {
			elem = utils.findElementByLocator(driver, "MBL_SP_stateAdd", "SP: state");
		}
		return elem;
	}

	/**
	 * Shipping page: Edit Shipping Addr Value| State
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Edit_Addr_StateDropDown() throws Exception, AssertionError {
		WebElement elem = null;
		if (utils.device.equals("mobile")) {
			elem = utils.findElementByLocator(driver, "MBL_SP_stateEditValue", "SP: state");
		}
		return elem;
	}

	/**
	 * Shipping page: continue to payment button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_SP_ContinuePayment() throws Exception, AssertionError {

		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_SP_CheckoutContinueToPayment",
					"SP: continue to payment button not present");
		else
			return utils.findElementByLocator(driver, "SP_CheckoutContinueToPayment",
					"SP: continue to payment button not present");
	}

	/**
	 * Shipping page: Header | Checkout Label
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement label_SP_HeaderCheckout() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_HeaderCheckout", "SP: continue to payment button not present");
	}

	/**
	 * Shipping page: Shipping Methods
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_SP_ShippingMethods() throws Exception {
		if (XLUtils.env.equalsIgnoreCase("dryrun") || XLUtils.env.equalsIgnoreCase("breakfix"))
			return utils.findElementsByLocator(driver, "SP_ShippingMethods_breakfix",
					"SP: Shipping methods div not present.");

		else if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementsByLocator(driver, "MBL_SP_ShippingMethods",
					"SP: Shipping methods div not present.");

		else
			return utils.findElementsByLocator(driver, "SP_ShippingMethods", "SP: Shipping methods div not present.");
	}

	/**
	 * Shipping page: Method to enter addr details in shipping addr form and proceed
	 * to payment page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void fillShippingAddrNSelectShippingMethod(WebDriver driver, List<String> list, String value)
			throws Exception {
		try {
			if (utils.isElementPresent(driver, "SP_Addr_FirstName")) {
				if (Utils.device.equals("mobile")) {
					fillShippingAddrIOS(driver, list);
				} else {
					fillShippingAddr(driver, list);
				}
				selectShippingSLA(driver, value);
				ReporterLog.pass("Selected Shipping SLA: " + value);
				Thread.sleep(3000);

			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Shipping Page", "adding shipping details failed", driver);
		} catch (AssertionError a) {
			CustomException.throwAssertionError(a, "Shipping Page", "adding shipping details failed", driver);
		}
	}

	/**
	 * Shipping page: Method to enter addr details in shipping addr form
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void fillShippingAddr(WebDriver driver, List<String> list) throws Exception {
		try {
			if (utils.isElementPresentAfterLongInterval(driver, "SP_Addr_FirstName")) {
				Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter first name in shipping addr");
				SP_AddrFirstName().clear();
				SP_AddrFirstName().sendKeys(list.get(1));
				Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter Last name in shipping addr");
				SP_AddrLastName().clear();
				SP_AddrLastName().sendKeys(list.get(2));
				Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter addr1 name in shipping addr");
				SP_Addr_Addr1().clear();
				SP_Addr_Addr1().sendKeys(list.get(3));
				Reporter.log(Constants.DELIMITER + "| Shipping Page: Select PO box checkbox if Yes");
				if (list.get(4).equals("Yes")) {
					SP_Addr_POBox().click();
				}
				Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter addr2 in shipping addr");
				if (!(list.get(5) == null)) {
					Thread.sleep(5000);
					SP_Addr_Addr2().clear();
					Thread.sleep(5000);
					SP_Addr_Addr2().sendKeys(list.get(5));
				}
				Thread.sleep(5000);
				SP_Addr_ZipCode().clear();
				Thread.sleep(5000);
				// SP_Addr_ZipCode().sendKeys(Keys.BACK_SPACE);
			}
			SP_Addr_ZipCode().sendKeys(list.get(8));
			SP_Addr_ZipCode().sendKeys(Keys.TAB);

			utils.isElementPresentForAllDevice(driver, "MA_AddAddress_City_Header");
			utils.scrollToViewElement(driver, SP_Addr_PhoneNum());
			utils.isElementPresentForAllDevice(driver, "SP_Addr_PhNum");
			Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter phone number in shipping addr");
			Utils.waitForPageLoaded(driver);
			SP_Addr_PhoneNum().clear();
			SP_Addr_PhoneNum().sendKeys(list.get(9));
			// SP_Addr_PhoneNum().sendKeys(Keys.TAB);
			Thread.sleep(5000);
			//Log.info("Shipping address added/edited succesfully");
			Thread.sleep(2000);
			utils.scrollDownByxPosition(driver, 700);
			Thread.sleep(2000);

		} catch (AssertionError e) {
			throw (e);
		}

	}

	/**
	 * Shipping page: Method to enter addr details in shipping addr form
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void fillShippingAddrIOS(WebDriver driver, List<String> list) throws Exception {
		try {
			Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter first name in shipping addr");
			utils.scrollDownToElement(driver, SP_AddrFirstName());
			SP_AddrFirstName().clear();
			utils.enterData_MobileIOS_Label("First Name", list.get(1));
			Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter Last name in shipping addr");
			// SP_AddrLastName().sendKeys(list.get(2));
			utils.scrollDownToElement(driver, SP_AddrLastName());
			SP_AddrLastName().clear();
			utils.enterData_MobileIOS_Label("Last Name", list.get(2));
			Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter addr1 name in shipping addr");
			utils.scrollDownToElement(driver, SP_Addr_Addr1());
			SP_Addr_Addr1().clear();
			utils.enterData_MobileIOS_Label("Address 1", list.get(3));
			// SP_Addr_Addr1().sendKeys(list.get(3));
			Reporter.log(Constants.DELIMITER + "| Shipping Page: Select PO box checkbox if Yes");
			if (list.get(4).equals("yes")) {
				SP_Addr_POBox().click();
			}
			Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter addr2 in shipping addr");
			if (!(list.get(5) == null)) {

				utils.scrollDownToElement(driver, SP_Addr_Addr2());
				SP_Addr_Addr2().clear();
				SP_Addr_Addr2().sendKeys(list.get(5));
			}
			utils.scrollDownToElement(driver, SP_Addr_ZipCode());
			SP_Addr_ZipCode().clear();
			utils.enterData_MobileIOS_Label("Zip Code", list.get(8));

			// SP_Addr_ZipCode().sendKeys(list.get(8));
			SP_Addr_PhoneNum().click();
			Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter Zipcode in shipping addr");
			Utils.waitForPageLoaded(driver);
			// SP_Addr_City().sendKeys(list.get(6));
			// Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter city
			// name in
			// shipping addr" );
			//
			// utils.selectValueFromList(lst_Addr_State(),driver,list.get(7));
			// Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter state
			// name in
			// shipping addr" );

			Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter phone number in shipping addr");
			utils.scrollDownToElement(driver, SP_Addr_PhoneNum());
			SP_Addr_PhoneNum().clear();
			utils.enterData_MobileIOS_Label("Phone Number", list.get(9));

			try {
				utils.findElementByLocator(driver, "SP_MBL_ShippingMethodSelected_Arrow", "").click();
				Assert.assertTrue(utils.isElementPresent(driver, "SP_ShippingMethods",
						"| SHIPPING PAGE: Shipping Method Not Enabled"));
			} catch (AssertionError e) {
				throw (e);
			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Shipping Page", "adding shipping details failed", driver);
		} catch (AssertionError a) {
			CustomException.throwAssertionError(a, "Shipping Page", "adding shipping details failed", driver);
		}
	}

	/**
	 * Shipping page: Method to enter addr details in shipping addr form
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void editShippingAddr(WebDriver driver, List<String> list) throws Exception {
		try {
			if (utils.isElementPresent(driver, "MA_Address_fname")) {
				if (Utils.browser.equals("android") || Utils.device.equals("desktop")) {
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter first name in shipping addr");
					myAccountPage.Address_Fname().clear();
					myAccountPage.Address_Fname().sendKeys(list.get(1));
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter Last name in shipping addr");
					myAccountPage.Address_Lname().clear();
					myAccountPage.Address_Lname().sendKeys(list.get(2));
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter addr1 name in shipping addr");
					myAccountPage.Address_Addr1().clear();
					myAccountPage.Address_Addr1().sendKeys(list.get(3));

					myAccountPage.Address_Addr2().clear();
					myAccountPage.Address_Addr2().sendKeys(list.get(5));
					myAccountPage.Address_Zip().clear();
					// SP_Addr_ZipCode().click();
					// SP_Addr_ZipCode().sendKeys(Keys.END);
					// Thread.sleep(1000);
					// for(int i=0; i <6 ; i++)
					// {
					// SP_Addr_ZipCode().sendKeys(Keys.BACK_SPACE);
					// }
					myAccountPage.Address_Zip().sendKeys(list.get(8));
					myAccountPage.Address_Zip().sendKeys(Keys.TAB);
					utils.scrollToViewElement(driver, myAccountPage.Address_phone());
					Thread.sleep(2000);
					utils.waitForElementToBeClickable(driver, "MA_Address_phn", "phone number");
					myAccountPage.Address_phone().click();
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter Zipcode in shipping addr");
					Utils.waitForPageLoaded(driver);
					Thread.sleep(3000);
					// SP_Addr_City().sendKeys(list.get(6));
					// Reporter.log(Constants.DELIMITER + "| Shipping Page:
					// Enter city name in
					// shipping addr" );
					//
					// utils.selectValueFromList(lst_Addr_State(),driver,list.get(7));
					// Reporter.log(Constants.DELIMITER + "| Shipping Page:
					// Enter state name in
					// shipping addr" );

					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter phone number in shipping addr");
					myAccountPage.Address_phone().clear();
					myAccountPage.Address_phone().sendKeys(list.get(9));
					myAccountPage.Address_phone().sendKeys(Keys.TAB);
				} else {
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter first name in shipping addr");
					utils.scrollDownToElement(driver, myAccountPage.Address_Fname());
					myAccountPage.Address_Fname().clear();
					utils.enterData_MobileIOS_Label("First Name", list.get(1));
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter Last name in shipping addr");
					// SP_AddrLastName().sendKeys(list.get(2));
					utils.scrollDownToElement(driver, myAccountPage.Address_Lname());
					myAccountPage.Address_Lname().clear();
					utils.enterData_MobileIOS_Label("Last Name", list.get(2));
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter addr1 name in shipping addr");
					utils.scrollDownToElement(driver, myAccountPage.Address_Addr1());
					myAccountPage.Address_Addr1().clear();
					utils.enterData_MobileIOS_Label("Address 1", list.get(3));
					// SP_Addr_Addr1().sendKeys(list.get(3));
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Select PO box checkbox if Yes");
					if (list.get(4).equals("yes")) {
						SP_Addr_POBox().click();
					}
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter addr2 in shipping addr");
					if (!(list.get(5) == null)) {

						utils.scrollDownToElement(driver, myAccountPage.Address_Addr2());
						myAccountPage.Address_Addr2().clear();
						myAccountPage.Address_Addr2().sendKeys(list.get(5));
					}
					utils.scrollDownToElement(driver, myAccountPage.Address_Zip());
					myAccountPage.Address_Zip().clear();
					utils.enterData_MobileIOS_Label("Zip Code", list.get(8));

					// SP_Addr_ZipCode().sendKeys(list.get(8));
					myAccountPage.Address_phone().click();
					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter Zipcode in shipping addr");
					Utils.waitForPageLoaded(driver);
					// SP_Addr_City().sendKeys(list.get(6));
					// Reporter.log(Constants.DELIMITER + "| Shipping Page:
					// Enter city name in
					// shipping addr" );
					//
					// utils.selectValueFromList(lst_Addr_State(),driver,list.get(7));
					// Reporter.log(Constants.DELIMITER + "| Shipping Page:
					// Enter state name in
					// shipping addr" );

					Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter phone number in shipping addr");
					utils.scrollDownToElement(driver, myAccountPage.Address_phone());
					myAccountPage.Address_phone().clear();
					utils.enterData_MobileIOS_Label("Phone Number", list.get(9));

				}
				try {

					if (utils.device.equals("desktop")) {
						Assert.assertTrue(utils.isElementPresent(driver, "SP_ShippingMethods",
								"| SHIPPING PAGE: Shipping Method Not Enabled"));
					}

				} catch (AssertionError e) {
					throw (e);
				}
			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Shipping Page", "editing shipping details failed", driver);
		} catch (AssertionError a) {
			CustomException.throwAssertionError(a, "Shipping Page", "editing shipping details failed", driver);
		}
	}

	/**
	 * Shipping page: Method to enter addr details in shipping addr form
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void selectShippingSLA(WebDriver driver, String value) throws Exception {

		if (Utils.isDeviceMobile() && !Utils.brand.equals("LGFP")) {

			utils.isElementPresent(driver, "SP_MBL_ShippingMethodSelected");

			WebElement element = utils.findElementByLocator(driver, "SP_MBL_ShippingMethodSelected",
					"Shipping Methods not present in mobile...");

			Thread.sleep(5000);
			utils.scrollToViewElement(driver, element);
			element.click();

		} else
			utils.isElementPresent(driver, "SP_ShippingMethods");

		WebElement desiredShippingMethod = null;
		for (WebElement shippingMethod : lst_SP_ShippingMethods()) {

			if (!shippingMethod.getAttribute("class").equalsIgnoreCase("disabled")) {

				String shippingMethodName = shippingMethod.getText();

				if ((shippingMethodName.contains("Standard") && value.contains("STD"))
						|| (shippingMethodName.contains("Expedited") && value.contains("TBD"))
						|| (shippingMethodName.contains("Next") && value.contains("NBD"))) {
					desiredShippingMethod = shippingMethod;
				}
			}
		}

		Assert.assertTrue(Utils.isElementPresent(driver, desiredShippingMethod), "Desired Shipping Method is present");
		ReporterLog.log("Clicking on Shipping Method : " + desiredShippingMethod.getText());
		desiredShippingMethod.click();

		// if (value.equalsIgnoreCase("STD"))
		// utils.clickWebElement(driver, lst_SP_ShippingMethods().get(0),
		// "standard SLA
		// not found");
		//
		// else if (value.equalsIgnoreCase("TBD"))
		// lst_SP_ShippingMethods().get(1).click();
		//
		// else
		// utils.clickWebElement(driver, lst_SP_ShippingMethods().get(2), "NBD
		// SLA not
		// found");

	}

	/**
	 * Shipping page: Method to get the shipping price based on shipping method
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized String getShippingAndHandlingPrice(WebDriver driver, String value) throws Exception {
		String priceValue = null;
		if (value.equalsIgnoreCase("STD")) {
			priceValue = txt_shippingMethodPrice().getText().trim();
		}
		if (value.equalsIgnoreCase("TBD")) {
			priceValue = SP_TBD_Price().getText().trim();
		}
		return priceValue;
	}

	/**
	 * Method to get shipping fields
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_shippingAddrFields() throws Exception {
		List<WebElement> ele = utils.findElementsByLocator(driver, "SP_inputFields", "| SB : input fields");
		return ele;
	}

	/**
	 * Method to get tick marks
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_tickMarksInFields() throws Exception {
		List<WebElement> ele = utils.findElementsByLocator(driver, "SP_checkMarksInFields", "| SP | check marks ");
		return ele;
	}

	/**
	 * Shipping page: phone number | tooltip
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement label_phoneNumToolTip() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_phoneNumToolTip", "SP: phone number tooltip");
	}

	/**
	 * Shipping page: Recommended addr | you have entered label
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement label_recommendedAddrEntered() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_AddressRecommendation_UserEntered_label",
				"SP: Recommended addr | you have entered label");
	}

	/**
	 * Shipping page: Recommended addr | user entered addr
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> txt_recommendedAddrEntered() throws Exception, AssertionError {
		return utils.findElementsByLocator(driver, "SP_AddressRecommendation_UserEntered_Address",
				"SP: Recommended addr | addr");
	}

	/**
	 * Shipping page: Recommended addr | keep this addr button label
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_labelKeepThisAddr() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_AddressRecommendation_UserEntered_KeepThis_Button",
				"SP: Recommended addr | keep this addr");
	}

	/**
	 * Shipping page: Saved addr
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_savedAddr() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_address", "SP: saved addr");
	}

	/**
	 * Shipping page: Recommended addr | Recommended addr label
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement label_recommendedAddr() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_AddressRecommendation_suggested_label",
				"SP: Recommended addr | recommended addr label");
	}

	/**
	 * Shipping page: Recommended addr | suggest addr
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> txt_recommendedAddr() throws Exception, AssertionError {
		return utils.findElementsByLocator(driver, "SP_AddressRecommendation_suggested_Address",
				"SP: Recommended addr | addr");
	}

	/**
	 * Shipping page: Recommended addr | use this addr label
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_useThisAddr() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_AddressRecommendation_btn_useThis",
				"SP: Recommended addr | use this addr button label");
	}

	public synchronized void handleVerifyYourAddressModal() throws Exception {

		if (utils.isElementPresent(driver, "SP_AddressRecommendation_btn_useThis", 10)) {

			WebElement element = utils.findElementByLocator(driver, "SP_AddressRecommendation_btn_useThis",
					"SP: Recommended addr | use this addr button label");

			element.click();

			//Log.info("Clicked on Use This Address in Verify Your Address Modal on Shipping page...");
		}
	}

	public synchronized boolean checkFieldsBlank(WebElement element) {
		if (element.getAttribute("value").isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Method to get Merchandise Subtotal element on shopping bag page
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized String merchandiseSubtotal() throws Exception {
		WebElement ele = utils.findElementByLocator(driver, "SB_OSOrderMerchandise_value",
				"| SB : Merchandise Subtotal amount in Order Summary");
		return ele.getText();
	}

	/**
	 * Method to get U.s postal service addr error msg
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized String txt_USErrorMsg() throws Exception {
		WebElement ele = null;
		if (utils.device.equalsIgnoreCase("mobile")) {
			ele = utils.findElementByLocator(driver, "MBL_SP_US_ErrorMsg",
					"| Sp : U.s postal service addr error msg not found");
		} else {
			ele = utils.findElementByLocator(driver, "SP_US_ErrorMsg",
					"| Sp : U.s postal service addr error msg not found");
		}
		return ele.getText();
	}

	/**
	 * Shipping page: Shipping Addr | First Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_AddrFirstNameError() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Addr_FirstNameError", "SP: First Name Error not present");
	}

	/**
	 * Shipping page: Shipping Addr | Last Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_AddrLastNameError() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Addr_LastNameError", "SP: Last Name Error not present");
	}

	/**
	 * Shipping page: Shipping Addr | Addr1
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_Addr1Error() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Addr_Addr1Error", "SP: Addr1 Error not present");
	}

	/**
	 * Shipping page: Shipping Addr | Zipcode
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_ZipCodeError() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Addr_ZipcodeError", "SP: ZipCode Error not present");
	}

	/**
	 * Shipping page: Shipping Addr | City
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_CityError() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Addr_CityError", "SP: city Error not present");
	}

	/**
	 * Shipping page: Shipping Addr | PhoneNumber
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SP_Addr_PhoneNumError() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Addr_PhNumError", "SP: phone number error not present");
	}

	/**
	 * Shipping page: Recommended addr2 | user entered addr
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_recommendedAddrEntered2() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_AddressRecommendation_UserEntered_Address2",
				"SP: Recommended addr | addr");
	}

	/**
	 * Shipping page: saved shipping method
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_savedShippingMethod() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_ShippingMethod", "SP: saved shipping addr not displayed");
	}

	/**
	 * Shipping page: saved shipping price
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_savedShippingPrice() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_ShippingPrice", "SP: saved shipping price not displayed");
	}

	/**
	 * Shipping page: saved shipping arrival date
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_savedShippingArrivalDate() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_ShippingArrivalDate", "SP: saved shipping price not displayed");
	}

	/**
	 * Shipping page: shipping method name
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_shippingMethodName() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_ShippingMethodsName", "SP: shipping method name not displayed");
	}

	/**
	 * Shipping page: shipping method price
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_shippingMethodPrice() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SB_OSShippingStandard", "SP: shipping method price not displayed");
	}

	/**
	 * Shipping page:state error msg
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_SP_stateErrorMsg() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_Addr_State_error", "SP: state error msg not found");
	}

	/**
	 * Shipping page:saved address drop down mobile
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement dwn_SP_savedAddresMobile() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "MBL_SP_savedAddr_dropDown",
				"SP: saved addresses dropdown  not found");
	}

	/**
	 * Shipping page:saved address drop down options mobile
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> optns_SP_savedAddresOptionsMobile() throws Exception, AssertionError {
		return utils.findElementsByLocator(driver, "MBL_SP_savedAddrOptions",
				"SP: saved addresses dropdown  not found");
	}

	public synchronized Double shippingChargesValue_OrderSummery() throws Exception {
		String shippingCharges = utils.findElementByLocator(driver, "SB_OSShipping_value", "").getText();
		String[] shippingCharge = shippingCharges.split("\\$");
		Double shippingChargesFinal = Double.parseDouble(shippingCharge[1]);
		return shippingChargesFinal;
	}

	/**
	 * Shipping page:SetAsDefault label on Add overlay
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_setAsDefaultCheckbox() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_checkDefaultAddrLabel",
				"SP: no set as default checkbox label displayed");
	}

	/**
	 * /** Shipping page:SetAsDefault label on edit overlay
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_setAsDefaultCheckboxEdit() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_checkDefaultAddrLabelEdit",
				"SP: no set as default checkbox label displayed");
	}

	/**
	 * Shipping page:SetAsDefault checkbox on edit overlay
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement checkBox_setAsDefault() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_checkDefaultAddrCheckbox",
				"SP: no set as default checkbox  displayed");
	}

	/**
	 * Shipping page:cancel button on edit overlay
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_cancel() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_editOverlay_cancelBtn", "SP: cancel button not found");
	}

	/**
	 * Shipping page:next button on edit overlay
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_next() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_editOverlay_NextBtn", "SP: next button not found");
	}

	/**
	 * Shipping page:next button on edit overlay
	 * 
	 * @return
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_addNew() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_addNewBtn", "SP: add new button not found");
	}

	/**
	 * Shipping page: phone number | tooltip
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement label_phoneNumToolTipText() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SP_phoneNumToolTipText", "SP: phone number tooltip text");
	}

	/**
	 * Added By "Sunil Jangir" | Dated: Aug-30 Method to click "Edit" button display
	 * under Billing Section on Order Review Page
	 * 
	 * @param driver
	 * @param rowNumber
	 * @return rowNo
	 * @throws Exception
	 */

	public synchronized void clickAddShippingSectionReview(WebDriver driver) throws Exception {
		try {
			Thread.sleep(2000);
			btn_addShippingReview().click();
			//Log.info("Clicked on Review Order");
			Utils.waitForPageLoaded(driver);
			Thread.sleep(1000);
			//Log.info("| Clicked Edit Button under Shipping section on Order Review Page");
		} catch (AssertionError e) {
			//Log.error("| Edit button under shipping section on order Review page is not found");
			errorBuffer.append(e.getMessage() + "\n");
		}
	}

	public synchronized WebElement btn_addShippingReview() throws Exception {
		WebElement element;
		if (utils.brand.equals("LGFP")) {
			element = utils.findElementByLocator(driver, "OR_btnAddShippingSection", "pin number not found");
		} else {
			element = utils.findElementByLocator(driver, "OR_btnEditShippingSection", "pin number not found");
		}
		return element;
	}

	public synchronized WebElement addressRecommendationTab() {

		try {

			utils.isElementPresent(driver, "SP_AddressBook_Tab");
			return utils.findElementByLocator(driver, "SP_AddressBook_Tab",
					"Address Recommendation Tab on Shipping page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized WebElement addressRecommendationTabHeader() {

		try {
			return utils.findElementByLocator(driver, "SP_AddressBookModal_Header",
					"Address Recommendation Tab on Shipping page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized List<WebElement> getRecommendationAddresses() {

		try {

			return utils.findElementsByLocator(driver, "SP_AddressBookModal_Addreesses",
					"Address Recommendations in modal");

		} catch (Exception e) {

			ReporterLog.log("Not able to get address recommendations from modal");
		}
		return null;
	}

	public synchronized WebElement addressRecommendationContinue() {

		try {
			return utils.findElementByLocator(driver, "SP_AddressBookModal_Continue",
					"Continue button in Address Recommendation Tab on Shipping page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized void clickEditShippingSectionReview(WebDriver driver) throws Exception {
		try{
			Thread.sleep(2000);
			btn_editShippingReview().click();
			//Log.info("Clicked on Review Order");
			Utils.waitForPageLoaded(driver);
			Thread.sleep(1000);
			//Log.info("Clicked edit button under shipping section on order Review page");
		}catch (AssertionError e) {
			//Log.error("Edit button under shipping section on order Review page is not found");
			errorBuffer.append(e.getMessage() + "\n");
		}
		}

		public synchronized WebElement btn_editShippingReview() throws Exception {
			WebElement element;
			if (utils.brand.equals("LGFP")) {
				element = utils.findElementByLocator(driver, "OR_ShippingAddressOrderReview", "pin number not found");
			} else {
				element = utils.findElementByLocator(driver, "OR_btnEditShippingSection", "pin number not found");
			}
			return element;
		}

		public synchronized List<WebElement> list_shippingmethods() throws Exception {
			List<WebElement> ele = utils.findElementsByLocator(driver, "SP_shippingmethods", "| SP | Shipping Methods ");
			return ele;
		}

		public synchronized boolean ShippingMethodPresent(String shipping_name) throws Exception {
			List<WebElement> shipping = list_shippingmethods();
			int i = shipping.size();

			for (WebElement element : shipping) {
				System.out.println("Shipping Methods :" + element.getText() + "========");
				if (element.getText().equals(shipping_name))
					return true;
			}

			return false;

		}

		public synchronized String selectAddressFromRecommendations(int index) {

			String address = null;

			ReporterLog.log("Clicking on Address Book Recommendation tab");

			addressRecommendationTab().click();

			if (verifyAddressRecommendationOpen()) {

				List<WebElement> addressesList = getRecommendationAddresses();

				if (!addressesList.isEmpty() && addressesList.size() > index) {

					ReporterLog.log("Clicking on Address with index =" + index);
					address = addressesList.get(index).getText();
					addressesList.get(index).click();

					ReporterLog.log("Clicking on Continue button in modal");
					addressRecommendationContinue().click();
				}
			}
			return address;
		}

		private synchronized boolean verifyAddressRecommendationOpen() {

			boolean flag = false;

			try {

				flag = addressRecommendationTabHeader().isDisplayed();

			} catch (Exception e) {

				ReporterLog.log("Address Recommendation Modal not getting displayed");
				flag = false;
			}

			if (flag) {

				List<WebElement> addressesList = getRecommendationAddresses();

				if (!addressesList.isEmpty()) {

					return true;
				}
			}

			return false;
		}
	}
