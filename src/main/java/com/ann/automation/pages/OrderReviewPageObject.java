package com.ann.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.ReporterLog;

public class OrderReviewPageObject {
	private static WebDriver driver = null;
	static Utils utils = null;
	static XLUtils xlUtils = null;

	public OrderReviewPageObject(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
		xlUtils = new XLUtils();
	}

	/**
	 * Get the cvv field
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txtCVV() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_CVV", "cvv not found");
		return element;
	}

	/**
	 * Get the billing address on Order review page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement para_BillingAddress() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_Billto_Adress", "billing address not found");
		return element;
	}

	/**
	 * Get the Ship to address on Order review page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement para_ShipToAddress() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_ShippingAddress", "Ship address not found");
		return element;
	}

	/**
	 * Get the paypal logo on Order review page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement img_PayPalLogo() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_PayPalLogo", "Paypal Logo not found");
		return element;
	}

	/**
	 * Get the paypal email on Order review page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_PayPalEmail() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_Paypal_email", "Paypal Email not found");
		return element;
	}

	/**
	 * Get the palce order button on order review page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_PlaceOrder() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_btn_PlaceOrder", "place order button not found");
		return element;
	}

	/**
	 * Clcik the place order Button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_btn_PlaceOrder() throws Exception {
		ReporterLog.actionMsg("Clicking on Place order");
		utils.clickWebElement(driver, btn_PlaceOrder(), "Place Order not found");
		Utils.waitForPageLoaded(driver);
		utils.waitForElementToPresent(driver, "Order_OrderNumber", 40);

	}

	/**
	 * Get the applied payment on Order review page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized WebElement para_appliedPayment() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_BillTo_AppliedPayment",
				"billing payment details not found");
		return element;
	}

	public synchronized WebElement para_appliedPayment_card() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_BillTo_AppliedPayment",
				"billing payment details not found");
		return element;
	}

	/**
	 * Get the applied payment on Order review page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement para_appliedPayment_GC() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_BillTo_AppliedPayment_GC",
				"billing payment details not found");
		return element;
	}

	/**
	 * Get the Thank you messgae after placing order
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_ThankYouMessage() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Order_ConfirmationMessage_ThankYou",
				"Thank You not found");
		return element;
	}

	/**
	 * Get the Confirmation Email messgae after placing order
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_ConfirmationEmailMessage() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Order_ConfirmationMessageEmail",
				"Confirmation Email not found");
		return element;
	}

	/**
	 * Get the Order number after placing order
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_ConfirmationEmailOderNumber() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Order_ConfirmationMessageOrderNumber",
				"Confirmation Order Number not found");
		return element;
	}

	/**
	 * Get the Order number after placing order
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement ReviewOrderStatusText() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "ReviewOrderStatusText", " Order Number not found");
		return element;
	}

	/**
	 * Get the Edit Shipping button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_editShipping() throws Exception {

		utils.isElementPresent(driver, "Payment_btn_EditShipping");
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_EditShipping", "pin number not found");
		return element;
	}

	/**
	 * Get the Edit Billing To button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_editBillingTo() throws Exception {

		if (Utils.brand.equals("LGFP"))
			return utils.findElementByLocator(driver, "PaymentMethod_Edit_LG", "Billing Address/Method change on LGFP");

		utils.isElementPresent(driver, "PaymentMethod_Edit");
		WebElement element = utils.findElementByLocator(driver, "PaymentMethod_Edit", "pin number not found");
		return element;
	}

	/**
	 * Get the Payment Gift card Title
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_gcTitle() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_GiftCardTitle", "gift card title not found");
		return element;
	}

	/**
	 * Get the Payment Gift card Title
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement img_gcImage() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_GiftCardImage", "gift card image not found");
		return element;
	}

	/**
	 * Get the Payment Gift card number
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_gcNumber() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_GiftCardNumber", "gift card number not found");
		return element;
	}

	/**
	 * Get the Terms N Condition label
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_termsNCondition() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_TermsNConditionLbl", "gift card number not found");
		return element;
	}

	/**
	 * Get the
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_termsUse() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_TermsNConditionLink", "gift card number not found");
		return element;
	}

	/**
	 * Get the label payment header
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_PaymentHeader() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_Payment_HeaderLabel",
				"OR : payment header section");
		return element;
	}

	/**
	 * Get the label bill to header
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_Bill_To_Heder() throws Exception {
		WebElement element = null;
		if (utils.device.equals("mobile")) {
			element = utils.findElementByLocator(driver, "MBL_BillTo_HeaderLabel", "OR : bill to header not found");
		} else
			element = utils.findElementByLocator(driver, "OR_BillTo_HeaderLabel", "OR : bill to header not found");
		return element;

	}

	/**
	 * Get the button of ship to edit
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_ORShipToEdit() throws Exception {
		WebElement element = null;
		/*
		 * if(utils.device.equals("mobile")) element=utils.findElementByLocator(driver,
		 * "MBL_shippingAndHandling_Btn","OR : shipping edit button not found"); else
		 */
		element = utils.findElementByLocator(driver, "OR_ShipToEdit", "OR : shipping edit button not found");
		return element;
	}

	/**
	 * Get the button of ship to edit
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_ORShipToNew() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_ShipToNew", "OR : shipping new button not found");
		return element;
	}

	/**
	 * Get the label of the saved card
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_ORSavedCard() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_cardLabel", "OR :card label not found");
		return element;
	}

	/**
	 * Get the label of the saved card expire date
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_savedExpireDate() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_billing_expiryDate_lbl",
				"OR :card expire date not found");
		return element;
	}

	/**
	 * Get the label of the saved card expire date
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_savedGCCard() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_billing_GCEGC_lbl", "OR :GC card label");
		return element;
	}

	/**
	 * Get the view items text on OR
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_viewItems() throws Exception {
		WebElement element = utils.findElementByAndroidID("MBL_viewItemsHeader");
		return element;
	}

	/**
	 * Get the view items text on OR
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_viewItemHeader_Billing() throws Exception {
		WebElement element = utils.findElementByAndroidID("MBL_viewItemsHeaderBilling");
		return element;
	}

	/**
	 * Get the view items accordian on OR
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement accrodian_viewItems() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "MBL_viewItemsHeaderAccordian",
				"OR :view items accordian");
		return element;
	}

	/**
	 * Get the create email ID textbox
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_createEmailID() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_OR_CreateAcntEmailID", "OR : Create email ID");

		WebElement element = utils.findElementByLocator(driver, "OR_CreateAcntEmailID", "OR : Create email ID");
		return element;
	}

	/**
	 * Get the email ID's password textbox
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_createEmailIDPswd() throws Exception {
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("Payment_CreateAcc_Password");

		else if (Utils.browser.equals("android") && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_OR_CreateAcntPassword", "\"OR : email ID's password");

		return utils.findElementByLocator(driver, "OR_CreateAcntPassword", "OR : email ID's password");
	}

	/**
	 * Get the email ID's confirm password textbox
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_createEmailIDConfirmPswd() throws Exception {

		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("Payment_CreateAcc_PasswordConfirm");

		else if (Utils.browser.equals("android") && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_OR_CreateAcntConfirmPassword",
					"OR : email ID's confirm password");

		return utils.findElementByLocator(driver, "OR_CreateAcntConfirmPassword", "OR : email ID's confirm password");
	}

	/**
	 * Get the paypal email
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_orderReviewEmail() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_Paypal_email", "OR : email ID");
		return element;
	}
}
