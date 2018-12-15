package com.ann.automation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.PaymentPageObject;

public class StoreLocatorPageObject {
	private static WebDriver driver = null;
	public PaymentPageObject paymentObj = null;
	public XLUtils xlUtils = null;
	public XLUtils XLUtilsObj = null;
	static Utils utils = null;
	List<String> shippingAddr = new ArrayList<String>();

	public StoreLocatorPageObject(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
	}

	/**
	 * view all location link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_viewAllLocations() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SL_viewAllLocations", "view all location link");
	}

	/**
	 * result count
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_resultText() throws Exception, AssertionError {

		if (Utils.isDeviceMobile())
			return utils.findElementByLocator(driver, "MBL_SL_label_storeResult", "result count text");

		return utils.findElementByLocator(driver, "SL_label_storeResult", "result count text");
	}

	/**
	 * zipcode text box
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_zipCode() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SL_txtZipCode", "zipcode text box");
	}

	/**
	 * first store address line1
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_firstStore_addressLine1() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SL_firstStoreAddressLine1", "address line 1 not found");
	}

	/**
	 * first store address line2
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_firstStore_addressLine2() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SL_firstStoreAddressLine2", "address line 2 not found");
	}

	/**
	 * AT tab
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_viewStoreDetails_buttons() throws Exception, AssertionError {
		return utils.findElementsByLocator(driver, "SL_btn_viewStoreDetails", "AT Tab");
	}

	/**
	 * AT tab
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement tab_ATTab() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SL_tabAT", "AT Tab");
	}

	public synchronized WebElement tab_ATFTab() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SL_tabATF", "AT Tab");
	}

	/**
	 * search button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_search() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SL_btnSearch", "zipcode text box");
	}

	/**
	 * distance dropdown
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement drpdwn_distance() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "SL_DistanceDropdown", "distance dropdown not found");
	}

	/**
	 * list of distance strings from the distance dropdown
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<String> value_distances() throws Exception, AssertionError {
		List<String> distances = new ArrayList<String>();
		List<WebElement> options = utils.findElementsByLocator(driver, "SL_DistanceDropdown_Options",
				"all distance options");
		for (WebElement el : options) {
			distances.add(el.getText());
		}
		return distances;
	}

	/**
	 * list of distance options
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_distances() throws Exception, AssertionError {
		List<WebElement> options = utils.findElementsByLocator(driver, "SL_DistanceDropdown_Options",
				"all distance options");

		return options;
	}

	public synchronized WebElement PickupStore() throws Exception {
		return utils.findElementByLocator(driver, "FIS_StoreLocator_PickupStore", "| FIS");

	}

	public synchronized void clickPickUpInStoreBtn() throws Exception {
		utils.clickWebElement(driver, PickupStore(), "Unable to Click Find in Store Btn");
	}

	public synchronized int addCreditCardBillingDetail(WebDriver driver, String cardType) throws Exception {
		try {

			// Get rowNo for cardType
			int rowNo = paymentObj.payByCard(driver, cardType);
			if (!xlUtils.env.equalsIgnoreCase("perfb")) {
				if (utils.isElementPresent(driver, "Payment_txt_CVV")
						&& !cardType.equalsIgnoreCase("AnntaylorLoft Card")) {
					String excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 5);
					if (utils.isElementClickable(driver, "Payment_txt_CVV")) {
						paymentObj.txt_CVV().sendKeys(excelData);
					}
				}

				shippingAddr = XLUtilsObj.accessExcelSheetForeCol(Constants.SHEETNAME_ADDRESS, 1);
				// paymentObj.switchToiframe();
				paymentObj.fillBillingAddr(driver, shippingAddr);

				driver.switchTo().defaultContent();
			} else {
				if (utils.isElementPresent(driver, "Payment_txt_CVV_withoutSRFrame")
						&& !cardType.equalsIgnoreCase("AnntaylorLoft Card")) {
					String excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 5);
					if (utils.isElementClickable(driver, "Payment_txt_CVV_withoutSRFrame")) {
						paymentObj.txt_CVV_withoutSRFrame().sendKeys(excelData);
					}
				}
			}
			// Return row no to get security code for respective card type
			return rowNo;

		} catch (Exception e) {
			throw (e);
		}

	}

	public synchronized List<WebElement> getStoreAddressesMobile() throws Exception {

		if (!Utils.brand.equals("LGFP"))
			return utils.findElementsByLocator(driver, "MBL_SL_StoreAddress", "store addresses");
		else
			return utils.findElementsByLocator(driver, "MBL_SL_StoreAddress_LG", "store addresses");
	}

	public synchronized List<WebElement> getStoreDistancesMobile() throws Exception {

		if (!Utils.brand.equals("LGFP"))
			return utils.findElementsByLocator(driver, "MBL_SL_StoreDistance", "store addresses");
		else
			return utils.findElementsByLocator(driver, "MBL_SL_StoreDistance_LG", "store addresses");
	}
	
	public synchronized List<WebElement> getStoreStatusMobile() throws Exception {

		if (!Utils.brand.equals("LGFP"))
			return utils.findElementsByLocator(driver, "SL_StoreOpenStatus", "store addresses");
		else
			return utils.findElementsByLocator(driver, "SL_StoreOpenStatus_LG", "store addresses");
	}
	
	public synchronized List<WebElement> getStoreTimmingsMobile() throws Exception {

		if (!Utils.brand.equals("LGFP"))
			return utils.findElementsByLocator(driver, "MBL_SL_StoreOpenTimings", "store addresses");
		else
			return utils.findElementsByLocator(driver, "MBL_SL_StoreOpenTimings_LG", "store addresses");
	}

}
