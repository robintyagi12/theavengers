package com.ann.automation.pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.Utils.MobileView;
import com.ann.automation.utilities.Utils.ReporterLog;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PaymentPageObject {
	private static WebDriver driver = null;
	static Utils utils = null;
	static XLUtils xlUtils = null;

	public PaymentPageObject(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
		xlUtils = new XLUtils();
	}

	/**
	 * Get the Paypal radio button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement rdb_paypal() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "PaypalRadiobtn", "paypal radio button not found");
		return element;
	}

	/**
	 * Get the Paypal button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_payPal() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "SBCheckoutPaypal", "paypal button not found");
		return element;
	}

	/**
	 * Get the card number text box
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	/**
	 * Get the Add New Shipping button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_addShipping() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_AddShipping",
				"add new shipping button not found");
		return element;
	}

	/**
	 * Get the Edit Shipping button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_editShipping() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_EditShipping",
				"edit shipping button not found");
		return element;
	}

	/**
	 * Get the card Image Present
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement cardImagePresent_Android() throws Exception {
		WebElement element = utils.findElementByAndroidID("Payment_CardPresent_Android");
		return element;
	}

	public synchronized WebElement txt_cardNumber() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Pay_txt_CardNo", "card number not found");
		return element;
	}

	public synchronized WebElement txt_cardNumber_sithourSRFrame() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Pay_tct_CardNo_WithoutSRFrame",
				"card number not found");
		return element;
	}

	/**
	 * Get the card expiry month
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement drpdwn_expiryMonth() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_Pay_drpdwn_expiryMonth", "expiry month not found");
		else
			return utils.findElementByLocator(driver, "Pay_drpdwn_expiryMonth", "expiry month not found");
	}

	/**
	 * Get the card expiry month
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement drpdwn_expiryMonth_withoutSRFrame() throws Exception {
		return utils.findElementByLocator(driver, "MBL_Pay_drpdwn_expiryMonth", "expiry month not found");
	}

	/**
	 * Get the card expiry year
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement drpdwn_expiryYear() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_Pay_drpdwn_expiryYear", "expiry year not found");
		else
			return utils.findElementByLocator(driver, "Pay_drpdwn_expiryYear", "expiry year not found");
	}

	/**
	 * Get the card expiry year
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement drpdwn_expiryYear_withoutSRFrame() throws Exception {
		return utils.findElementByLocator(driver, "MBL_Pay_drpdwn_expiryYear", "expiry year not found");
	}

	/**
	 * Get the card cvv
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_CVV() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_txt_CVV", "cvv not found");
		return element;
	}

	/**
	 * Get the card cvv
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_CVV_withoutSRFrame() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_txt_CVV_withoutSRFrame", "cvv not found");
		return element;
	}

	/**
	 * Get the card cvv
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_CVV_Reg() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_CVV_reviewPage", "cvv not found");
		return element;
	}

	/**
	 * Get the label expiry
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_Expiry() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "PaymentExpiryLbl", "expiry label not found");
		return element;
	}

	/**
	 * Get the label expiry
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_SecurityCode() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "PaymentSecurityCodeLbl",
				"security code label not found");
		return element;
	}

	/**
	 * Get the card image
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement img_card() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Pay_txt_Card_Img", "card image not found");
		return element;
	}

	/**
	 * Get the gift card section
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement section_giftCard() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_sectionGiftCard",
				"gift card section not found");
		return element;
	}

	/**
	 * Get the gift card section tooltip
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement section_giftCardTooltip() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_sectionGiftcardTooltip",
				"gift card section tooltip not found");
		return element;
	}

	/**
	 * Get the gift card remove
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement giftCardRemove() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_giftCardCloseBtn",
				"gift card remove button not found");
		return element;
	}

	/**
	 * Get the payment page same billing shipping checkbox
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement chkbox_sameBillingShipping() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_chckbox_samebillingshipping",
				"gift card section not found");
		return element;
	}

	/**
	 * Get the gift card number textbox
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_GC_Number() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_txt_GCNumber", "gift card number not found");
		return element;
	}

	/**
	 * Get the pin number textbox
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_GC_PinNumber() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_txt_PinNumber", "pin number not found");
		return element;
	}

	/**
	 * Get the GC apply button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_GC_Apply() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_ApplyGC", "pin number not found");
		return element;
	}

	/**
	 * Shipping page: Shipping Addr | First Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement Payment_AddrFirstName() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "Payment_Addr_FirstName", "SP: First Name text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | Last Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement Payment_AddrLastName() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "Payment_Addr_LastName", "SP: Last Name text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | Addr1
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement Payment_Addr_Addr1() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "Payment_Addr_Addr1", "SP: Addr1 text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | Addr2
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement Payment_Addr_Addr2() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "Payment_Addr_Addr2", "SP: Addr2 text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | PO Box
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement Payment_Addr_POBox() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "Payment_Addr_POBox", "SP: PO Box CheckBox not present");
	}

	/**
	 * Shipping page: Shipping Addr | Zipcode
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement Payment_Addr_ZipCode() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "Payment_Addr_Zipcode", "SP: ZipCode Text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | City
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement Payment_Addr_City() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "Payment_Addr_City", "SP: city text field not present");
	}

	/**
	 * Shipping page: Shipping Addr | PhoneNumber
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement Payment_Addr_PhoneNum() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "Payment_Addr_PhNum", "SP: phone number text field not present");
	}

	/**
	 * Get the Shipping Policy button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_ShippingPolicy() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_ShippingPolicy",
				"Shipping Policy not found");
		return element;
	}

	/**
	 * Get the Shipping Policy Close button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_ShippingPolicyClose() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_ShippingPolicyClose",
				"Shipping Policy Close not found");
		return element;
	}

	/**
	 * Get the Shipping Policy button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement shippingPolicyFAQOverlay() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_ShippingPolicyFAQOverlay",
				"Shipping Policy FAQ Overlay not found");
		return element;
	}

	/**
	 * Get the Return button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_Return() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_Return", "Return not found");
		return element;
	}

	/**
	 * Get the Return Close button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_ReturnClose() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_ReturnClose",
				"Shipping Policy Close not found");
		return element;
	}

	/**
	 * Get the Return Close button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_ErrorMsgInvalidCard() throws Exception {
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("Payment_InvalidCardNoMsgIOS");
		else
			return utils.findElementByAndroidID("Payment_InvalidCardNoMsgAndroid");
	}

	/**
	 * Get the Shipping Policy button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement returnFAQOverlay() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_ReturnFAQOverlay",
				"Shipping Policy FAQ Overlay not found");
		return element;
	}

	/**
	 * Get the error message on applying wrong GC card no.
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_errorMessage() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_GC_errormsg", "pin number not found");
		return element;
	}

	/**
	 * validation error
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_validation_errorMessage() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_validationError_firstName",
				"validation error");
		return element;
	}

	/**
	 * Get the error message on applying wrong GC card no.
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_paymentBottom_RequireField_disclaimer() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_lbl_requireFields", "pin number not found");
		return element;
	}

	/**
	 * Get the GC applied section
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_giftCard_applied() throws Exception {
		if (utils.isElementInvisible(driver, "Payment_GC_applied", "GC applied details not found")) {
			section_giftCard().click();
			Thread.sleep(1000);
		}
		WebElement element = utils.findElementByLocator(driver, "Payment_GC_applied", "GC applied details not found");
		return element;
	}

	/**
	 * Get the Order review button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_orderReview() throws Exception {

		if (Utils.isDeviceMobile())
			return utils.findElementByLocator(driver, "MBL_Payment_btn_OrderReview",
					"Order Review button not found in mobile");

		WebElement element = utils.findElementByLocator(driver, "Payment_btn_OrderReview",
				"Order Review button not found");
		return element;
	}

	/**
	 * Added By "Sunil Jangir" | Aug-30 Get the Order review button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_editBillingReview() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_btnEditBillingSection", "pin number not found");
		return element;
	}

	public synchronized WebElement btn_addNewBtnBillingReview() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_btnAddNewBillingSection", "pin number not found");
		return element;
	}

	/**
	 * Get the Order review button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_orderReview_withoutSRFrame() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_btn_OrderReview_withoutSRFrame",
				"pin number not found");
		return element;
	}

	/**
	 * Get the First name error msg button for Field not blank
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_FirstNameErrorMessageNotBlank_Mobile() throws Exception {
		try {
			if (Utils.browser.equals("ios"))
				return utils.findElementByIOSNameLocator("Payment_FirstNameErrorMsgNotBlank_IOS");
			else
				return utils.findElementByAndroidXpathLocator("Payment_FirstNameErrorMsg_Android");
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Get the First name error msg button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_FirstNameErrorMessage_Mobile() throws Exception {
		try {
			if (Utils.browser.equals("ios"))
				return utils.findElementByIOSNameLocator("Payment_FirstNameErrorMsg_IOS");
			else
				return utils.findElementByAndroidXpathLocator("Payment_FirstNameErrorMsg_Android");
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Get the Satic text below billing
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_billingStaticText() throws Exception {
		try {
			if (Utils.browser.equals("ios"))
				return utils.findElementByIOSNameLocator("Payment_StaticTextBelowBillingIOS");
			else
				return utils.findElementByAndroidXpathLocator("Payment_StaticTextBelowBillingAndroid");
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Get the Zip code name error msg button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_ZipCodeErrorMessage_Mobile() throws Exception {
		try {
			if (Utils.browser.equals("ios")) {
				if (XLUtils.env.equalsIgnoreCase("breakfix"))
					return utils.findElementByIOSNameLocator("Payment_ZipCodeErrorMsg_IOS_breakfix");
				else
					return utils.findElementByIOSNameLocator("Payment_ZipCodeErrorMsg_IOS");
			} else
				return utils.findElementByAndroidXpathLocator("Payment_ZipCodeErrorMsg_Android");
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Get the City name error msg button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_CityErrorMessage_Mobile() throws Exception {
		try {
			if (Utils.browser.equals("ios"))
				return utils.findElementByIOSNameLocator("Payment_CityCodeErrorMsg_IOS");
			else
				return utils.findElementByAndroidXpathLocator("Payment_CityErrorMsg_Android");
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Get the Order review button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_FirstName_Mobile() throws Exception {
		MobileView.switchToNative(driver);
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("Payment_FirstNameIOS");
		else
			return utils.findElementByAndroidID("Payment_FirstNameAndroid");
	}

	/**
	 * Get the Zip code Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_ZipCode_Mobile() throws Exception {
		MobileView.switchToNative(driver);
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("Payment_ZipCodeIOS");
		else
			return utils.findElementByAndroidID("Payment_ZipCodeAndroid");
	}

	/**
	 * Get the Last Name Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_LastName_MobileAndroid() throws Exception {
		MobileView.switchToNative(driver);
		return utils.findElementByAndroidID("Payment_LastNameAndroid");
	}

	/**
	 * Get the Address1 Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_Address1_MobileAndroid() throws Exception {
		MobileView.switchToNative(driver);
		return utils.findElementByAndroidID("Payment_Address1Android");
	}

	/**
	 * Get the Address 2 Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_Address2_MobileAndroid() throws Exception {
		MobileView.switchToNative(driver);
		return utils.findElementByAndroidID("Payment_Address2Android");
	}

	/**
	 * Get the City Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_City_Mobile() throws Exception {
		MobileView.switchToNative(driver);
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("Payment_CityIOS");
		else
			return utils.findElementByAndroidID("Payment_CityAndroid");
	}

	/**
	 * Get the Phone Number Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_PhoneNumber_MobileAndroid() throws Exception {
		MobileView.switchToNative(driver);
		return utils.findElementByAndroidID("Payment_PhoneNumberAndroid");
	}

	/**
	 * Get the Card number Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_CardNumber_MobileAndroid() throws Exception {
		MobileView.switchToNative(driver);
		return utils.findElementByAndroidID("Payment_CardNumber_Android");
	}

	/**
	 * Get the CVV Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_CVV_MobileAndroid() throws Exception {
		MobileView.switchToNative(driver);
		return utils.findElementByAndroidID("Payment_CVV_Android");
	}

	/**
	 * Get the Month Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_CC_Month_MobileAndroid() throws Exception {
		WebElement el = utils.findElementByAndroidID("Payment_Month_Android");
		el.click();
		return el;
	}

	/**
	 * Get the Month Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement text_CC_Month_MobileAndroid() throws Exception {
		WebElement el = utils.findElementByAndroidID("Payment_Month_Android");
		return el;
	}

	/**
	 * Get the Year Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_CC_Year_MobileAndroid() throws Exception {
		return utils.findElementByAndroidID("Payment_Year_Android");
	}

	/**
	 * Get the State Android Mobile
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_State_MobileAndroid() throws Exception {
		MobileView.switchToNative(driver);
		return utils.findElementByAndroidID("Payment_StateAndroid");
	}

	/**
	 * Get the Order review button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void click_btn_reviewOrderMobile() throws Exception {
		if (Utils.browser.equals("ios")) {
			utils.scrollDownByxPosition(driver, 1000);
			utils.findElementByIOSNameLocator("ReviewOrder_IOS").click();
		} else {
			utils.scrollDownByxPosition(driver, 1200);
			Thread.sleep(2000);
			utils.findElementByAndroidID("ReviewOrder_Android").click();
		}
	}

	/**
	 * Get the Close Gift Card
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_closeGiftCard() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_removeAppliedCard",
				"Close Gift card not found");
		return element;
	}

	/**
	 * Get the security code tooltip
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement tooltip_securityCode() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_tooltip_securityCode",
				"security code tooltip not found");
		return element;
	}

	/**
	 * Method to getCardRownumber and return row No
	 * 
	 * @param driver
	 * @param cardType
	 * @return rowNo
	 * @throws Exception
	 */
	public synchronized int getCardTypeRowNumber(Object driver, String cardType) throws Exception {
		int rowNo = 0;
		try {
			// Get rowNo for cardType
			ReporterLog.actionMsg("Card Type: " + cardType);

			if (cardType.equalsIgnoreCase("Visa")) {
				rowNo = 2;
			} else if (cardType.equalsIgnoreCase("MasterCard")) {
				rowNo = 1;
			} else if (cardType.equalsIgnoreCase("AnntaylorLoft Card")) {
				rowNo = 3;
			} else if (cardType.equalsIgnoreCase("Ann Taylor/LOFT Mastercard")) {
				rowNo = 4;
			} else if (cardType.equalsIgnoreCase("American Express")) {
				rowNo = 5;
			} else if (cardType.equalsIgnoreCase("Discover")) {
				rowNo = 6;
			} else if (cardType.equalsIgnoreCase("Diners")) {
				rowNo = 7;
			} else if (cardType.equalsIgnoreCase("JCB")) {
				rowNo = 8;
			}
		} catch (Exception e) {
			throw (e);
		}
		return rowNo;

	}

	/**
	 * Method to enter billing details(credit card number, expiry month, year) and
	 * return row No
	 * 
	 * @param driver
	 * @param rowNumber
	 * @return rowNo
	 * @throws Exception
	 */
	public synchronized int payByCard(WebDriver driver, String cardType) throws Exception {
		String excelData;
		int rowNo = 0;
		try {
			if (utils.isElementPresent(driver, "PaymentPanel")) {

				if (!Utils.brand.equals("LGFP")) {
					Assert.assertTrue(utils.isElementPresentForAllDevice(driver, "Payment_Header"),
							"Payment header displayed");
					ReporterLog.pass("Payment header is displayed");
					// Assert.assertTrue(utils.isElementPresent(driver,
					// "MBL_Payment_ShippingHandling_Header"),
					// "Shipping Handling header displayed");
					ReporterLog.pass("shipping and Handling Header present");
				}

				rowNo = getCardTypeRowNumber(driver, cardType);

				// PAYMENT METHOD
				Thread.sleep(2000);
				if (utils.isElementPresent(driver, "Paymentiframe")) {
					switchToiframe();
					Utils.waitForPageLoaded(driver);
					utils.waitForElementToBeClickable(driver, "Pay_txt_CardNo", "ccredit card not loaded");

					if (utils.isElementPresent(driver, "Pay_txt_CardNo")) {
						// Enter card number
						excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 1);
						txt_cardNumber().click();
						txt_cardNumber().clear();
						Thread.sleep(2000);
						txt_cardNumber().sendKeys(excelData);
						txt_cardNumber().sendKeys(Keys.TAB);

						Thread.sleep(2000);

						// If card type is not anntaylor loft card then select
						// expiry date from drop down list
						if ((cardType.equalsIgnoreCase("AnntaylorLoft Card")) != true) {

							// Click Exp Month Dropdown
							drpdwn_expiryMonth().click();
							Thread.sleep(1000);

							// Select Month From Dropdown
							excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 3);

							List<WebElement> elExMonthList = getExpiryMonthList();
							Reporter.log(Constants.DELIMITER + "Expiry Month Enabled: "
									+ utils.selectValueFromList(elExMonthList, driver, excelData));

							// Click Exp Year Dropdown
							drpdwn_expiryYear().click();
							Thread.sleep(2000);

							// Select Year From Dropdown
							excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 4);
							List<WebElement> elExYearList = getExpiryYearList();
							Reporter.log(Constants.DELIMITER + "Expiry Year Enabled: "
									+ utils.selectValueFromList(elExYearList, driver, excelData));
						}

					}
				} else {
					if (utils.isElementPresent(driver, "Pay_tct_CardNo_WithoutSRFrame")) {
						// Enter card number
						excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 1);
						txt_cardNumber_sithourSRFrame().click();
						txt_cardNumber_sithourSRFrame().clear();
						Thread.sleep(2000);
						txt_cardNumber_sithourSRFrame().sendKeys(excelData);
						txt_cardNumber_sithourSRFrame().sendKeys(Keys.TAB);

						Thread.sleep(2000);

						// If card type is not anntaylor loft card then select
						// expiry date from drop down list
						if ((cardType.equalsIgnoreCase("AnntaylorLoft Card")) != true) {

							// Click Exp Month Dropdown
							Select se_mnth = new Select(drpdwn_expiryMonth_withoutSRFrame());
							Thread.sleep(1000);

							// Select Month From Dropdown
							excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 3);
							// List<WebElement> elExMonthList = utils.findElementsByLocator(driver,
							// "Pay_expMonthOptions_withoutSRframe",
							// "| Billing : Expiry Month");
							// Reporter.log(Constants.DELIMITER + "Expiry Month Enabled: "
							// + utils.selectValueFromList(elExMonthList, driver, excelData));
							se_mnth.selectByVisibleText(excelData);
							Thread.sleep(2000);
							// Click Exp Year Dropdown
							Select se_year = new Select(drpdwn_expiryYear_withoutSRFrame());

							Thread.sleep(2000);

							// Select Year From Dropdown
							excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 4);
							// List<WebElement> elExYearList = utils.findElementsByLocator(driver,
							// "Pay_expYearOptions_withoutSRframe",
							// "| Billing : Expiry Year");
							// Reporter.log(Constants.DELIMITER + "Expiry Year Enabled: "
							// + utils.selectValueFromList(elExYearList, driver, excelData));
							se_year.selectByVisibleText(excelData);
						}

					}

				}
			}
			// Return row no to get security code for respective card type
			return rowNo;

		} catch (Exception e) {
			throw (e);
		}

	}

	public synchronized List<WebElement> getExpiryMonthList() throws Exception {

		if (Utils.isDeviceMobile())
			return utils.findElementsByLocator(driver, "MBL_Pay_expMonthOptions", "| Billing : Expiry Month");

		else
			return utils.findElementsByLocator(driver, "Pay_expMonthOptions", "| Billing : Expiry Month");
	}

	public synchronized List<WebElement> getExpiryYearList() throws Exception {

		if (Utils.isDeviceMobile())
			return utils.findElementsByLocator(driver, "MBL_Pay_expYearOptions", "| Billing : Expiry Year");

		else
			return utils.findElementsByLocator(driver, "Pay_expYearOptions", "| Billing : Expiry Year");
	}

	/**
	 * Method to select a value from month drop down on android
	 * 
	 * @param driver
	 * @param rowNumber
	 * @return rowNo
	 * @throws Exception
	 */
	public synchronized void selectValueForMonth_Android(String value) throws Exception {
		try {
			txt_CC_Month_MobileAndroid();
			Thread.sleep(1000);
			((AndroidDriver<WebElement>) driver).scrollTo(value);
			List<WebElement> dropdownvalues = driver.findElements(By.id("android:id/text1"));
			for (WebElement el : dropdownvalues) {
				if (el.getText().equals(value)) {
					el.click();
					break;
				}
			}
		} catch (Exception e) {
			ReporterLog.fail("Selecting value from month failed");
			throw e;
		}
	}

	/**
	 * Method to select a value from month drop down on android
	 * 
	 * @param driver
	 * @param rowNumber
	 * @return rowNo
	 * @throws Exception
	 */
	public synchronized void selectValueForYear_Android(String value) throws Exception {
		try {
			txt_CC_Year_MobileAndroid().click();
			Thread.sleep(1000);
			((AndroidDriver<WebElement>) driver).scrollTo(value);
			List<WebElement> dropdownvalues = driver.findElements(By.id("android:id/text1"));
			for (WebElement el : dropdownvalues) {
				if (el.getText().equals(value)) {
					el.click();
					break;
				}
			}
		} catch (Exception e) {
			ReporterLog.fail("Selecting value from year failed");
			throw e;
		}
	}

	/**
	 * Method to enter billing details(credit card number, expiry month, year) and
	 * return row No
	 * 
	 * @param driver
	 * @param rowNumber
	 * @return rowNo
	 * @throws Exception
	 */
	public synchronized int payByCard_Mobile(WebDriver driver, String cardType) throws Exception {
		String excelData;
		try {
			int rowNo = 0;
			if (cardType.equalsIgnoreCase("Visa")) {
				rowNo = 2;
			} else if (cardType.equalsIgnoreCase("MasterCard")) {
				rowNo = 1;
			} else if (cardType.equalsIgnoreCase("AnntaylorLoft Card")) {
				rowNo = 3;
			} else if (cardType.equalsIgnoreCase("Ann Taylor/LOFT Mastercard")) {
				rowNo = 4;
			} else if (cardType.equalsIgnoreCase("American Express")) {
				rowNo = 5;
			} else if (cardType.equalsIgnoreCase("Discover")) {
				rowNo = 6;
			} else if (cardType.equalsIgnoreCase("Diners")) {
				rowNo = 7;
			} else if (cardType.equalsIgnoreCase("JCB")) {
				rowNo = 8;
			}
			// Enter security code
			// PAYMENT METHOD

			Thread.sleep(3000);
			if (Utils.browser.equals("ios")) {

				List<WebElement> textField = utils.findElementsByIOSClassNameLocator(driver, "sortBy_Native_ClassName");

				// Enter card number
				excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 1);
				// utils.scrollDownToElement(driver,
				// utils.findElementByLocator(driver, "Payment_Header_Mbl",
				// ""));
				utils.scrollDownToElement(driver, utils.findElementByLocator(driver, "Payment_Header_Mbl", ""));
				utils.clickOnTextBox_EnterData_Mobile("Card Number", excelData);

				// If card type is not anntaylor loft card then select expiry
				// date from drop down list
				if ((cardType.equalsIgnoreCase("AnntaylorLoft Card")) != true) {

					// Select Month From Dropdown
					excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 3);
					utils.findElementByIOSNameLocator("Month_IOS").click();
					utils.findElementByIOSXpathLocatorAndSelect(driver, "PickerWheel", excelData);

					Thread.sleep(1000);

					excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 4);

					// if(xlUtils.env.equalsIgnoreCase("breakfix") ||
					// xlUtils.env.equalsIgnoreCase("dryrun")) {
					utils.findElementByIOSNameLocator("Month_IOS").click();
					utils.findElementByIOSNameLocator("Next").click();
					// }else
					// utils.findElementByIOSNameLocator("Year_IOS").click();

					utils.findElementByIOSXpathLocatorAndSelect(driver, "PickerWheel", excelData);

					Thread.sleep(1000);

				}
			} else {
				// android
				excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 1);
				txt_CardNumber_MobileAndroid().sendKeys(excelData);
				Thread.sleep(5000);
				// If card type is not anntaylor loft card then select expiry
				// date from drop down list
				if ((cardType.equalsIgnoreCase("AnntaylorLoft Card")) != true) {

					// Select Month From Dropdown
					excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 3);
					selectValueForMonth_Android(excelData);

					Thread.sleep(3000);

					excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 4);
					selectValueForYear_Android(excelData);
					Thread.sleep(1000);
				}
			}
			// Return row no to get security code for respective card type
			return rowNo;

		} catch (Exception e) {
			ReporterLog.fail("failed adding payment data");
			throw (e);
		}

	}

	/**
	 * Method to enter billing details(credit card number, expiry month, year) and
	 * return row No
	 * 
	 * @param driver
	 * @param rowNumber
	 * @return rowNo
	 * @throws Exception
	 */
	public synchronized int addCreditCard(WebDriver driver, String cardType) throws Exception {
		try {

			// Get rowNo for cardType
			int rowNo = payByCard(driver, cardType);
			if (!xlUtils.env.equalsIgnoreCase("perfb")) {
				if (utils.isElementPresent(driver, "Payment_txt_CVV")
						&& !cardType.equalsIgnoreCase("AnntaylorLoft Card")) {
					String excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 5);
					if (utils.isElementClickable(driver, "Payment_txt_CVV")) {
						txt_CVV().sendKeys(excelData);
					}
				}
				driver.switchTo().defaultContent();
			} else {
				if (utils.isElementPresent(driver, "Payment_txt_CVV_withoutSRFrame")
						&& !cardType.equalsIgnoreCase("AnntaylorLoft Card")) {
					String excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 5);
					if (utils.isElementClickable(driver, "Payment_txt_CVV_withoutSRFrame")) {
						txt_CVV_withoutSRFrame().sendKeys(excelData);
					}
				}
			}
			// Return row no to get security code for respective card type
			return rowNo;

		} catch (Exception e) {
			throw (e);
		}

	}

	public synchronized int storelocator_addCreditCard(WebDriver driver, String cardType) throws Exception {
		try {

			// Get rowNo for cardType
			int rowNo = payByCard(driver, cardType);

			if (!xlUtils.env.equalsIgnoreCase("perfb")) {
				if (utils.isElementPresent(driver, "Payment_txt_CVV")
						&& !cardType.equalsIgnoreCase("AnntaylorLoft Card")) {
					String excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 5);
					if (utils.isElementClickable(driver, "Payment_txt_CVV")) {
						txt_CVV().sendKeys(excelData);
					}
				}
				// driver.switchTo().defaultContent();
			} else {
				if (utils.isElementPresent(driver, "Payment_txt_CVV_withoutSRFrame")
						&& !cardType.equalsIgnoreCase("AnntaylorLoft Card")) {
					String excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 5);
					if (utils.isElementClickable(driver, "Payment_txt_CVV_withoutSRFrame")) {
						txt_CVV_withoutSRFrame().sendKeys(excelData);
					}
				}
			}
			// Return row no to get security code for respective card type
			return rowNo;

		} catch (Exception e) {
			throw (e);
		}

	}

	/**
	 * Method to click order review
	 * 
	 * @param driver
	 * @param rowNumber
	 * @return rowNo
	 * @throws Exception
	 */
	public synchronized void clickOrderReview(WebDriver driver) throws Exception {
		if (!xlUtils.env.equalsIgnoreCase("perfb")) {
			Thread.sleep(2000);
			switchToiframe();
			btn_orderReview().click();
			ReporterLog.pass("Clicked on Review Order");
			Utils.waitForPageLoaded(driver);
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
		} else {
			Thread.sleep(2000);
			btn_orderReview_withoutSRFrame().click();
			ReporterLog.pass("Clicked on Review Order");
			Utils.waitForPageLoaded(driver);
		}
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
	public synchronized void clickEditBillingSectionReview(WebDriver driver) throws Exception {
		Thread.sleep(2000);
		btn_editBillingReview().click();
		ReporterLog.pass("Clicked on Review Order");
		Utils.waitForPageLoaded(driver);
		Thread.sleep(1000);
	}

	public synchronized void clickAddNewBillingSectionReview(WebDriver driver) throws Exception {
		Thread.sleep(5000);
		btn_addNewBtnBillingReview().click();
		ReporterLog.pass("Clicked on Review Order");
		Utils.waitForPageLoaded(driver);
		Thread.sleep(1000);
	}

	/**
	 * Method to enter billing details(credit card number, expiry month, year) and
	 * return row No
	 * 
	 * @param driver
	 * @param rowNumber
	 * @return rowNo
	 * @throws Exception
	 */
	public synchronized int addCreditCard_Mobile(WebDriver driver, String cardType) throws Exception {
		try {
			Utils.waitForPageLoaded(driver);
			Thread.sleep(15000);
			if (utils.browser.equals("android"))
				utils.waitForAndroidIdElementToProceed("Payment_CardNumber_Android");
			utils.waitForElementToPresent(driver, "Payment_Header_Mbl");
			// Assert.assertTrue(utils.isElementPresent(driver, "Payment_Header_Mbl"),
			// "Payment header displayed");
			// ReporterLog.pass("Payment header is displayed");

			// Get rowNo for cardType
			int rowNo = payByCard_Mobile(driver, cardType);
			// Enter CVV if CVV field enabled
			String excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 5);

			if (Utils.browser.equals("ios")) {
				WebElement element = utils.getElementIOS("CVV", "textbox");
				if (element != null && (cardType.equalsIgnoreCase("AnntaylorLoft Card")) != true)
					utils.clickOnTextBox_EnterData_Mobile("CVV", excelData);
			} else {
				if ((cardType.equalsIgnoreCase("AnntaylorLoft Card")) != true)
					if (utils.isAndroidIdElementPresent("Payment_CVV_Android")) {
						txt_CVV_MobileAndroid().sendKeys(excelData);
					}
			}
			return rowNo;

		} catch (Exception e) {
			throw (e);
		}

	}

	/**
	 * Method to get the test data GC number, pin and click on Apply button on
	 * billing page.
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void payByGC_EGC(WebDriver driver, int rowNo, String SheetName) throws Exception {
		String excelData;
		try {
			Assert.assertTrue(utils.isElementPresent(driver, "Payment_Header"), "Payment header displayed");
			ReporterLog.pass("Payment header is displayed");
			// Assert.assertTrue(utils.isElementPresent(driver,
			// "Payment_ShippingHandling_Header"), "Shipping Handling header
			// displayed");
			// ReporterLog.pass("shipping and Handling Header present");
			if (!utils.isElementVisible(driver, "Payment_txt_GCNumber", "gc section is expaneded")) {
				Reporter.log(Constants.DELIMITER + "**********************Payment by Gift Card**********************");
				section_giftCard().click();
			}
			// Enter GC
			excelData = xlUtils.AccessExcelSheetForCellData(SheetName, rowNo, 0);
			txt_GC_Number().clear();
			txt_GC_Number().sendKeys(excelData);
			Reporter.log(Constants.DELIMITER + Constants.PASS + "| Gift card number Entered");

			excelData = xlUtils.AccessExcelSheetForCellData(SheetName, rowNo, 1);

			txt_GC_PinNumber().clear();
			txt_GC_PinNumber().sendKeys(excelData);
			Reporter.log(Constants.DELIMITER + Constants.PASS + "| GC PIN Entered");
			// Click on Apply Button
			btn_GC_Apply().click();
			Reporter.log(Constants.DELIMITER + Constants.PASS + "| Gift card number applied");

			Utils.waitForPageLoaded(driver);

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| GC Payment Failed");
			throw new IllegalStateException("| GC Payment Failed" + e);
		}

	}

	/**
	 * Method to get the test data GC number, pin and click on Apply button on
	 * billing page. (MOBILE)
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void payByGC_EGC_Mobile(WebDriver driver, int rowNo, String SheetName) throws Exception {
		String excelData;
		utils.scrollUp(driver);
		Assert.assertTrue(utils.isElementPresent(driver, "Payment_Header_Mbl"), "Payment header displayed");
		ReporterLog.pass("Payment header is displayed");

		if (!utils.isElementVisible(driver, "Payment_txt_GCNumber", "gc section is expaneded")) {
			Reporter.log(Constants.DELIMITER + "**********************Payment by Gift Card**********************");
			section_giftCard().click();
		}

		try {
			if (Utils.browser.equals("ios")) {
				// Enter GC
				excelData = xlUtils.AccessExcelSheetForCellData(SheetName, rowNo, 0);
				// utils.scrollDownToElement(driver, txt_GC_Number());
				txt_GC_Number().clear();
				utils.enterData_MobileIOS_Label("Card Number", excelData);
				Reporter.log(Constants.DELIMITER + Constants.PASS + "| Gift card number Entered");

				excelData = xlUtils.AccessExcelSheetForCellData(SheetName, rowNo, 1);
				if (excelData.equals("0"))
					excelData = "";
				txt_GC_PinNumber().clear();
				// utils.scrollDownToElement(driver, txt_GC_PinNumber());
				if (XLUtils.env.equalsIgnoreCase("breakfix")) {
					utils.findElementByIOSNameLocator("MBL_PinEmail_breakfix").click();
					utils.deleteTextFromTextBoxIOS(5);
					utils.findElementByIOSNameLocator("MBL_PinEmail_breakfix").sendKeys(excelData);
					utils.findElementByIOSNameLocator("Done").click();
				} else {
					utils.findElementByIOSNameLocator("MBL_PinEmail").click();
					utils.findElementByIOSNameLocator("MBL_PinEmail").sendKeys(excelData);
					utils.findElementByIOSNameLocator("Done").click();
				}
				// txt_GC_PinNumber().clear();
				// utils.enterData_MobileIOS_Label("Pin/Email", excelData);

				Reporter.log(Constants.DELIMITER + Constants.PASS + "| GC PIN Entered");
			} else {
				excelData = xlUtils.AccessExcelSheetForCellData(SheetName, rowNo, 0);
				txt_GC_Number().clear();
				Thread.sleep(2000);
				txt_GC_Number().sendKeys(excelData);
				Reporter.log(Constants.DELIMITER + Constants.PASS + "| Gift card number Entered");

				excelData = xlUtils.AccessExcelSheetForCellData(SheetName, rowNo, 1);

				if (excelData.equals("0"))
					excelData = "";

				txt_GC_PinNumber().clear();
				Thread.sleep(2000);
				txt_GC_PinNumber().sendKeys(excelData);
				Reporter.log(Constants.DELIMITER + Constants.PASS + "| GC PIN Entered");
			}
			// Click on Apply Button
			btn_GC_Apply().click();
			Reporter.log(Constants.DELIMITER + Constants.PASS + "| Gift card number applied");

			Utils.waitForPageLoaded(driver);

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| GC Payment Failed");
			throw new IllegalStateException("| GC Payment Failed" + e);
		}

	}

	/**
	 * Method to switch frame for payment options
	 * 
	 * @throws Exception
	 */
	public synchronized void switchToiframe() throws Exception {
		try {
			utils.waitForElementToBeVisible(driver, driver.findElement(By.name("sparkred-iframe")));
			// switching to iframe
			driver.switchTo().frame("sparkred-iframe");
			Thread.sleep(5000);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to get the payment details needed.
	 * 
	 * @param cardType
	 * @param fieldName
	 * @param paymentMethod
	 * @return
	 * @throws Exception
	 */
	public synchronized String getPaymentData(String cardType, String fieldName, String paymentMethod)
			throws Exception {
		try {
			if (paymentMethod.equals("credit card")) {
				int rowNo = 0;
				if (cardType.equalsIgnoreCase("Visa")) {
					rowNo = 2;
				} else if (cardType.equalsIgnoreCase("MasterCard")) {
					rowNo = 1;
				} else if (cardType.equalsIgnoreCase("AnntaylorLoft Card")) {
					rowNo = 3;
				} else if (cardType.equalsIgnoreCase("Ann Taylor/LOFT Mastercard")) {
					rowNo = 4;
				} else if (cardType.equalsIgnoreCase("American Express")) {
					rowNo = 5;
				} else if (cardType.equalsIgnoreCase("Discover")) {
					rowNo = 6;
				} else if (cardType.equalsIgnoreCase("Diners")) {
					rowNo = 7;
				}

				if (fieldName.equals("cardNumber"))
					return xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 1);

				if (fieldName.equals("Month"))
					return xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 3);

				if (fieldName.equals("Year"))
					return xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 4);

			}

			if (paymentMethod.equals("gc")) {
				if (fieldName.equals("cardNumber_500"))
					return xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_GC, 1, 0);

			}

			if (paymentMethod.equals("egc")) {
				if (fieldName.equals("cardNumber_500"))
					return xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_EGC, 1, 0);

			}

		} catch (Exception e) {
			throw (e);
		}
		return null;
	}

	/**
	 * Get the gift card tool tip text
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_toolTipGiftCard() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_giftCard_tooltipText",
				"gift card tooltip txt not found");
		return element;
	}

	/**
	 * Get the gift card tool tip link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_toolTipLinkGiftCard() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_giftCard_tooltipLink",
				"gift card tooltip txt not found");
		return element;
	}

	/**
	 * Get the gift card tool tip close button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_toolTipLinkGiftCardOverlayClose() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_toolTipOverlay_clsButton",
				"gift card tooltip overlay close button");
		return element;
	}

	/**
	 * Payment | close button of gift card
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_giftCardClose() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "OR_giftCardCloseBtn",
				"gift card close button not found");
		return element;
	}

	/**
	 * Payment | check bal link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_checkBal() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_giftCardCheckBal",
				"gift card close button not found");
		return element;
	}

	/**
	 * Payment | check bal overlay | input : card num
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_giftCardNumber() throws Exception {

		return utils.findElementByLocator(driver, "checkBalOverlay_cardNum", "gift card overlay | num");
	}

	/**
	 * Payment | check bal overlay | input : card num
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> txt_giftCardNumber_CheckBal() throws Exception {

		return utils.findElementsByLocator(driver, "checkBalOverlay_cardNum", "gift card overlay | num");
	}

	/**
	 * Payment | check bal overlay | input : pin num
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_giftCardPinNumber() throws Exception {
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MBL_PinEmail_breakfix");
		else
			return utils.findElementByLocator(driver, "checkBalOverlay_pinNum", "gift card overlay | pin");

	}

	/**
	 * Payment | check bal overlay | btn : check bal
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_checkBal() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "checkBalOverlay_checkBalBtn",
				"gift card overlay | bal btn");
		return element;
	}

	/**
	 * Payment | check bal overlay | close btn
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_checkBalOverlayCloseBtn() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "checkBalOverlay_closeBtn",
				"gift card  overlay | close button not found");
		return element;
	}

	/**
	 * Payment | check bal overlay | error msg header
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_checkBalOverlayHeaderError() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "checkBalOverlay_HeaderError",
				"gift card  overlay |error header not found");
		return element;
	}

	/**
	 * Payment | EGC/GC applied msg
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_GC_EGC_appliedMsg() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "EGC_applied", "gift card  applied msg not found");
		return element;
	}

	/**
	 * Payment | wallet tab
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_paymentWalletTab() throws Exception {

		utils.isElementPresent(driver, "Payment_wallet_tab");
		WebElement element = utils.findElementByLocator(driver, "Payment_wallet_tab", "payment wallet tab");
		return element;
	}

	/**
	 * Payment | wallet tab
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_paymentEditOrAddTab() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_addOrEditTab", "payment add/edit tab");
		return element;
	}

	/**
	 * Payment | wallet tab
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_paymentMethods() throws Exception {
		List<WebElement> list = utils.findElementsByLocator(driver, "PaymentMethodsList", "payment methods");
		return list;
	}

	public boolean checkPaymentMethods() throws Exception {
		boolean flag = false;
		for (WebElement element : list_paymentMethods()) {
			element.isDisplayed();
			flag = true;
		}
		return flag;
	}

	/**
	 * Payment | wallet tab
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement label_paymentMethod() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "PaymentMethodLabel", "payment add/edit tab");
		return element;
	}

	/**
	 * Payment | wallet tab | card and addr details.
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	// xpath should be collections of objects for addr and card details except
	// card image
	public synchronized List<WebElement> lst_walletAddrCardDetails() throws Exception {
		List<WebElement> element = utils.findElementsByLocator(driver, "Payment_Wallet_AddrCard_Details",
				"payment add/edit tab");
		return element;
	}

	/*
	 * Phone number validation for (XXX) XXX-XXXX
	 */
	/**
	 * Payment | wallet tab | close button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_closeOnWallet() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_close_Overlay", "close button on wallet");
		return element;
	}

	public boolean phoneNumberFormatMatcher(String phNum) {
		String pattern = "^\\(\\d{3}\\) \\d{3}-\\d{4}$";
		Pattern p = Pattern.compile(pattern);// . represents single character
		Matcher m = p.matcher(phNum);
		boolean b = m.matches();
		return b;
	}

	/**
	 * Get the card Image Present
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement cardImagePresent() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_CardPresentInField", "card image not present");
		return element;
	}

	/**
	 * Get the options from month drop down
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public List<WebElement> expMonthList() throws Exception {
		List<WebElement> elExMonthList = utils.findElementsByLocator(driver, "Pay_expMonthOptions",
				"| Billing : Expiry Month");
		return elExMonthList;
	}

	public List<WebElement> expYearList() throws Exception {
		List<WebElement> elExYearList = utils.findElementsByLocator(driver, "Pay_expYearOptions",
				"| Billing : Expiry Year");
		return elExYearList;
	}

	/**
	 * To check that the values in month select is correct or not
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean optionsPresent(int valueLenth, String data) throws Exception {
		String val = null;
		int i = 0;
		String optionValue[] = data.split(",");

		try {
			for (i = 1; i < valueLenth; i++) {
				if (optionValue[0].equals("01"))
					val = expMonthList().get(i).getText().replaceAll("\n", "").trim();
				else
					val = expYearList().get(i).getText().replaceAll("\n", "").trim();
				if (!optionValue[i - 1].equals(val)) {
					ReporterLog.fail("Value not present " + optionValue[i] + " in the  dropdown");
					return false;
				}
			}
		} catch (Exception e) {
			ReporterLog.fail("Value not present " + optionValue[i]);
			Assert.fail("Value not present " + optionValue[i]);
			return false;
		}

		return true;
	}

	/**
	 * Get tool tip content of cvv
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_cvvToolTipContent() throws Exception {
		WebElement element = utils.findElementByLocator(driver, "Payment_tooltip_securityCode_details",
				"cvv tool tip content not present");
		return element;
	}

	/**
	 * Get selected payment card if more then one card present
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement selectedPaymentCard_Mobile() throws Exception {
		MobileView.switchToNative(driver);
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MBL_Payment_SelectedCardOption");
		else
			return utils.findElementByAndroidID("MBL_Payment_SelectedCardOption");
	}

	/**
	 * Get selected payment card image if more then one card present
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement selectedPaymentCardImg_Mobile() throws Exception {
		MobileView.switchToNative(driver);
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MBL_selectedCard_Imag");
		else
			return utils.findElementByAndroidID("MBL_selectedCard_Imag");
	}

	/**
	 * Get selected payment card image if more then one card present
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_selectedPaymentCardOptions_Mobile() throws Exception {
		MobileView.switchToNative(driver);
		if (Utils.browser.equals("ios"))
			return utils.findElementsByIOSClassNameLocator(driver, "MBL_Payment_MultiCard_Options");
		else
			return utils.findElementsByAndroidClassNameLocator("MBL_Payment_MultiCard_Options");

	}

	/**
	 * Get bill to address dropdown
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement billTodropDown() throws Exception {
		MobileView.switchToNative(driver);
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MBL_billTo_optionsDropdown");
		else
			return utils.findElementByAndroidID("MBL_billTo_optionsDropdown");
	}

	/**
	 * Get bill to address dropdown
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> optns_billTodropDown() throws Exception {
		MobileView.switchToNative(driver);
		if (Utils.browser.equals("ios"))
			return utils.findElementsByIOSNameLocator("MBL_billTo_options");
		else
			return utils.findElementsByAndroidClassNameLocator("MBL_billTo_options");
	}

	/**
	 * Get bill to address dropdown
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_editCloseMobile() throws Exception {

		return utils.findElementByLocator(driver, "ODP_close_Btn", "");
	}

	/**
	 * Get save button txt on edit overlay
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_saveOnEditOverlay() throws Exception {
		MobileView.switchToNative(driver);
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MBL_Payment_editOverlaysaveBtn");
		else
			return utils.findElementByAndroidID("MBL_Payment_editOverlaysaveBtn");
	}

	/**
	 * Get add payment method label on add new payment overlay
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_PaymentMethodOnNewPaymentOverlay() throws Exception {

		return utils.findElementByLocator(driver, "MA_AddNewTitle_Payment", "");
	}

	/**
	 * Get the save button on edit payment overlay
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_saveOnPaymentOverlay() throws Exception {
		if (Utils.browser.equals("ios")) {
			utils.scrollDownByxPosition(driver, 1000);
			return utils.findElementByIOSNameLocator("ReviewOrder_IOS");
		} else {
			utils.scrollDownByxPosition(driver, 1200);
			Thread.sleep(2000);
			return utils.findElementByAndroidID("ReviewOrder_Android");
		}
	}

	/**
	 * Billing page: Method to enter addr details in billing addr form
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void fillBillingAddrMobile(WebDriver driver, List<String> list) throws Exception {
		try {
			if (Utils.browser.equals("android")) {
				Reporter.log(Constants.DELIMITER + "| Billing Page: Enter first name in Billing addr");
				txt_FirstName_Mobile().clear();
				txt_FirstName_Mobile().sendKeys(list.get(1));
				Reporter.log(Constants.DELIMITER + "| Billing Page: Enter Last name in Billing addr");
				txt_LastName_MobileAndroid().clear();
				txt_LastName_MobileAndroid().sendKeys(list.get(2));
				Reporter.log(Constants.DELIMITER + "| Billing Page: Enter addr1 name in Billing addr");
				txt_Address1_MobileAndroid().clear();
				txt_Address1_MobileAndroid().sendKeys(list.get(3));
				Reporter.log(Constants.DELIMITER + "| Billing Page: Select PO box checkbox if Yes");
				Reporter.log(Constants.DELIMITER + "| Billing Page: Enter addr2 in Billing addr");
				if (!(list.get(5) == null)) {
					txt_Address2_MobileAndroid().clear();
					txt_Address2_MobileAndroid().sendKeys(list.get(5));
				}
				txt_ZipCode_Mobile().clear();
				txt_ZipCode_Mobile().sendKeys(list.get(8));
				txt_ZipCode_Mobile().sendKeys(Keys.TAB);
				utils.scrollToViewElement(driver, txt_PhoneNumber_MobileAndroid());
				Thread.sleep(2000);
				utils.waitForElementToBeClickable(driver, "Payment_PhoneNumberAndroid", "phone number");
				txt_PhoneNumber_MobileAndroid().click();
				Reporter.log(Constants.DELIMITER + "| Shipping Page: Enter Zipcode in shipping addr");
				Utils.waitForPageLoaded(driver);
				Thread.sleep(3000);
				Reporter.log(Constants.DELIMITER + "| Billing Page: Enter phone number in Billing addr");
				txt_PhoneNumber_MobileAndroid().clear();
				txt_PhoneNumber_MobileAndroid().sendKeys(list.get(9));
				txt_PhoneNumber_MobileAndroid().sendKeys(Keys.TAB);
			} else {
				utils.findElementByIOSNameLocator("Payment_FirstNameIOS").sendKeys(list.get(1));
				utils.findElementByIOSNameLocator("Next_IOS").click();
				utils.findElementByIOSNameLocator("Payment_LastNameIOS").sendKeys(list.get(2));
				utils.findElementByIOSNameLocator("Next_IOS").click();
				utils.findElementByIOSNameLocator("Payment_Address1IOS").sendKeys(list.get(3));
				utils.findElementByIOSNameLocator("Next_IOS").click();
				utils.findElementByIOSNameLocator("Payment_ZipCodeIOS").sendKeys(list.get(8));
				utils.findElementByIOSNameLocator("Next_IOS").click();
				utils.findElementByIOSNameLocator("Done").click();
				utils.clickOnTextBox_EnterData_Mobile("Phone Number", list.get(9));
				utils.findElementByIOSNameLocator("MBL_Save_btn_IOS").click();
				Utils.waitForPageLoaded(driver);
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Billing Page", "adding Billing details failed", driver);
		} catch (AssertionError a) {
			CustomException.throwAssertionError(a, "Billing Page", "adding Billing details failed", driver);
		}
	}

	/**
	 * Get label of shipping and billing addr
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_billingAdnShippingCheckbox_Mobile() throws Exception {
		Thread.sleep(2000);
		if (Utils.browser.equals("ios")) {
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			return ((IOSDriver) driver).findElement(By.name("My Billing & Shipping Addresses are the same"));

		} else
			return utils.findElementByAndroidXpathLocator("Payment_BillingAddressCheckbox_Android");

	}

	/**
	 * Get phone number field error msg
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_billingPhNumError_Mobile() throws Exception {
		Thread.sleep(2000);
		return utils.findElementByAndroidID("Payment_PhoneNumErrorMsgAndroid");

	}

	/**
	 * Get phone number field tooltip
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement toolTip_billingPhNum_Mobile() throws Exception {
		Thread.sleep(2000);
		return utils.findElementByAndroidID("Payment_PhoneNumTootip");

	}

	/**
	 * Get phone number field tooltip txt
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement toolTipTxt_billingPhNum_Mobile() throws Exception {
		Thread.sleep(2000);
		return utils.findElementByAndroidID("Payment_phoneNumTooltipText");

	}

	/**
	 * Get phone number field tooltip txt
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void removeGCEGC() throws Exception {

		if (utils.isElementPresent(driver, "OR_btn_PlaceOrder")) {
			WebElement elem = utils.findElementByLocator(driver, "MBL_SP_header_billing", "Billing section not found");
			utils.clickWebElement(driver, elem, "MBL_SP_header_billing");
			Thread.sleep(2000);
			utils.waitForPageLoaded(driver);
			section_giftCard().click();
			Thread.sleep(2000);
			giftCardRemove().click();

		}
	}

	public synchronized void fillBillingAddr(WebDriver driver, List<String> list) throws Exception {
		try {
			if (utils.isElementPresent(driver, "Payment_Addr_FirstName")) {
				Reporter.log(Constants.DELIMITER + "| Billing Page: Enter first name in Billing addr");
				Payment_AddrFirstName().clear();
				Payment_AddrFirstName().sendKeys(list.get(1));
				Reporter.log(Constants.DELIMITER + "| Billing Page: Enter Last name in Billing addr");
				Payment_AddrLastName().clear();
				Payment_AddrLastName().sendKeys(list.get(2));
				Reporter.log(Constants.DELIMITER + "| Billing Page: Enter addr1 name in Billing addr");
				Payment_Addr_Addr1().clear();
				Payment_Addr_Addr1().sendKeys(list.get(3));

				Reporter.log(Constants.DELIMITER + "| Billing Page: Enter addr2 in Billing addr");
				if (!(list.get(5) == null)) {
					Payment_Addr_Addr2().clear();
					Payment_Addr_Addr2().sendKeys(list.get(5));
				}
				Payment_Addr_ZipCode().clear();
				Payment_Addr_ZipCode().sendKeys(list.get(8));
				Payment_Addr_ZipCode().sendKeys(Keys.TAB);

				Thread.sleep(5000);
				if (!verifyCorrectCityIsPopulatedInPaymentIFrame(list.get(6))) {

					Payment_Addr_City().clear();
					Payment_Addr_City().sendKeys(list.get(6));
					Thread.sleep(2000);
				}

				utils.scrollToViewElement(driver, Payment_Addr_PhoneNum());
				utils.waitForElementToBeClickable(driver, "Payment_Addr_PhNum", "phone number");
				Payment_Addr_PhoneNum().click();

				Utils.waitForPageLoaded(driver);
				// Thread.sleep(3000);
				// SP_Addr_City().sendKeys(list.get(6));
				// Reporter.log(Constants.DELIMITER + "| Billing Page: Enter city name in
				// Billing addr" );
				//
				// utils.selectValueFromList(lst_Addr_State(),driver,list.get(7));
				// Reporter.log(Constants.DELIMITER + "| Billing Page: Enter state name in
				// Billing addr" );

				// Reporter.log(Constants.DELIMITER + "| Billing Page: Enter phone number in
				// Billing addr" );
				Payment_Addr_PhoneNum().clear();
				Payment_Addr_PhoneNum().sendKeys(list.get(9));
				Payment_Addr_PhoneNum().sendKeys(Keys.TAB);
				try {

					/*
					 * if(utils.device.equals("desktop")) {
					 * Assert.assertTrue(utils.isElementPresent(driver, "Payment_BillingMethods",
					 * "| Payment PAGE: Payment Method Not Enabled")); }
					 */

					driver.switchTo().defaultContent();

				} catch (AssertionError e) {
					throw (e);
				}
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Payment Page", "adding Payment details failed", driver);
		} catch (AssertionError a) {
			CustomException.throwAssertionError(a, "Payment Page", "adding Payment details failed", driver);
		}
	}

	private boolean verifyCorrectCityIsPopulatedInPaymentIFrame(String cityName) throws Exception {

		ReporterLog.log("Method to verify that correct city is populated in payment iframe...");
		return (Payment_Addr_City().getText().equals(cityName)
				|| Payment_Addr_City().getAttribute("value").equals(cityName));

	}

	public synchronized void changePayment(int x, int y) throws Exception {
		TouchAction a = new TouchAction((MobileDriver) driver);
		// a.tap(utils.findElementsByIOSNameLocator("Select_DifferentPayment_IOS").get(1)).perform();
		a.tap(x, y + 100).perform();

	}

	public synchronized WebElement walletRecommendationTab() {

		try {
			return utils.findElementByLocator(driver, "SP_AddressBook_Tab",
					"Address Recommendation Tab on Shipping page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized String selectCardFromRecommendations(int index) throws Exception {

		String cardDetails = null;
		ReporterLog.log("Clicking on Wallet Recommendation tab");

		txt_paymentWalletTab().click();

		if (verifyWalletRecommendationOpen()) {

			List<WebElement> cardsList = getRecommendationCards();

			if (!cardsList.isEmpty() && cardsList.size() > index) {

				ReporterLog.log("Clicking on Address with index =" + index);
				cardDetails = cardsList.get(index).getText();
				cardsList.get(index).click();

				ReporterLog.log("Clicking on Continue button in modal");
				cardRecommendationContinue().click();
			}
		}
		return cardDetails;
	}

	private WebElement cardRecommendationContinue() {

		try {
			return utils.findElementByLocator(driver, "Payment_WalletModal_Continue",
					"Continue button in Address Recommendation Tab on Shipping page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private boolean verifyWalletRecommendationOpen() {

		boolean flag = false;

		try {

			flag = cardRecommendationTabHeader().isDisplayed();

		} catch (Exception e) {

			ReporterLog.log("Address Recommendation Modal not getting displayed");
			flag = false;
		}

		if (flag) {

			List<WebElement> addressesList = getRecommendationCards();

			if (!addressesList.isEmpty()) {

				return true;
			}
		}

		return false;
	}

	private List<WebElement> getRecommendationCards() {

		try {

			return utils.findElementsByLocator(driver, "Payment_WalletModal_Cards", "Card Recommendations in modal");

		} catch (Exception e) {

			ReporterLog.log("Not able to get card recommendations from modal");
		}
		return null;
	}

	private WebElement cardRecommendationTabHeader() throws Exception {

		utils.isElementPresent(driver, "SP_WalletModal_Header");
		return utils.findElementByLocator(driver, "Payment_WalletModal_Header", "Wallet Modal Header on Payment Page");
	}
}
