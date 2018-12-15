package com.ann.automation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.Utils.MobileView;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.PLPPageObject;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SearchPageObject {
	private static WebDriver driver = null;
	static Utils utils = null;
	public XLUtils xlUtils = null;
	public HomePageObjects homepage = null;
	PLPPageObject productList = null;
	public static String iOSLocator = null;
	private static WebElement element;
	int count = 1;

	public SearchPageObject(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
		xlUtils = new XLUtils();
		productList = new PLPPageObject(driver, brand, browser, device);
		homepage = new HomePageObjects(driver, brand, browser, device);
	}

	public synchronized List<WebElement> list_typeahead_results() throws Exception {

		List<WebElement> element = null;
		try {
			element = utils.findElementsByLocator(driver, "SRP_typeahead_results", "|Search ");
		} catch (Exception e) {

		}
		return element;
	}

	public synchronized WebElement btn_typeahead_shopall() throws Exception {

		return utils.findElementByLocator(driver, "SRP_typeahead_shopAll", "|Search ");
	}

	public synchronized List<WebElement> btn_typeahead_productImage() throws Exception {

		return utils.findElementsByLocator(driver, "SRP_typeahead_prodImage", "|Search ");
	}

	public synchronized List<WebElement> btn_typeahead_productName() throws Exception {

		return utils.findElementsByLocator(driver, "SRP_typeahead_prodName", "|Search ");
	}

	public synchronized List<WebElement> btn_typeahead_Totalproduct() throws Exception {

		return utils.findElementsByLocator(driver, "SRP_topProductList", "|Search ");
	}

	public synchronized List<WebElement> btn_typeahead_productPrice() throws Exception {

		return utils.findElementsByLocator(driver, "SRP_typeahead_prodPrice", "|Search ");
	}

	public synchronized List<WebElement> btn_typeahead_productRating() throws Exception {

		return utils.findElementsByLocator(driver, "SRP_typeahead_prodRating", "|Search ");
	}

	public synchronized List<WebElement> btn_typeahead_productPromos() throws Exception {

		return utils.findElementsByLocator(driver, "SRP_typeahead_prodPromos", "|Search ");
	}

	public synchronized WebElement txt_noSearchResult() throws Exception {
		WebElement element = null;
		if (Utils.device.equals("mobile")) {
			element = utils.findElementByLocator(driver, "SRP_MB_NoResult", "|Search ");
		} else {
			element = utils.findElementByLocator(driver, "SPR_NoSearchResult", "|Search ");
		}

		return element;
	}

	/**
	 * Method to check that the type ahead is displayed and the corresponding
	 * Product Names,Images,Rating and Promos are present
	 * 
	 * @throws Exception
	 */
	public synchronized void validateProductInfo_typeAhead() throws Exception {
		try {
			count++;
			// total top rated products including the heading
			int items = btn_typeahead_Totalproduct().size();

			if (items > 3) {
				ReporterLog.fail("More than 3 Product displayed on the top rated product");
				Assert.fail("More than 3 Product displayed on the top rated product");

			}
			// checking that the top rated products is present for the link
			if (items == 0) {
				ReporterLog.fail("No Product displayed on the top rated product");
				Assert.fail("No Product displayed on the top rated product");
			} else {

				// int totalTopRatedroduct = items-1;
				int totalTopRatedroduct = items;
				// checking that all product displayed have images
				if (totalTopRatedroduct != btn_typeahead_productImage().size()) {
					int difference = totalTopRatedroduct - btn_typeahead_productImage().size();
					ReporterLog.fail(
							"Images not displayed for link no. " + count + " and total image missing: " + difference);
					Assert.fail(
							"Images not displayed for link no. " + count + " and total image missing: " + difference);
				}
				// checking that all product displayed have Product Name

				if (totalTopRatedroduct != btn_typeahead_productName().size()) {
					int difference = totalTopRatedroduct - btn_typeahead_productName().size();
					ReporterLog.fail("Product Name not displayed for link no. " + count + " and total name missing: "
							+ difference);
					Assert.fail("Product Name not displayed for link no. " + count + " and total name missing: "
							+ difference);
				}

				// checking that all product displayed have prices
				if (totalTopRatedroduct != btn_typeahead_productPrice().size()) {
					int difference = totalTopRatedroduct - btn_typeahead_productPrice().size();
					ReporterLog.fail(
							"Price not displayed for link no. " + count + " and total price missing: " + difference);
					Assert.fail(
							"Price not displayed for link no. " + count + " and total price missing: " + difference);
				}

				// checking that the Product Name not Empty and displayed
				for (int i = 0; i < items - 1; i++) {
					Assert.assertTrue(btn_typeahead_productName().get(i).isDisplayed(),
							"Product name display for item " + i);
					Assert.assertFalse(btn_typeahead_productName().get(i).getText().isEmpty(),
							"Text is not present for item " + i);
				}

				// checking that the Product Price not Empty and displayed

				for (int i = 0; i < items - 1; i++) {
					Assert.assertFalse(btn_typeahead_productPrice().get(i).getText().isEmpty(),
							"Text is not present for item " + i);
					Assert.assertTrue(btn_typeahead_productPrice().get(i).isDisplayed(),
							"Product price display for item " + i);
				}
				try {
					ReporterLog.pass("Rating is only Present for: " + btn_typeahead_productPromos().size());

					// checking that the Product Rating is displayed

					for (int i = 0; i < btn_typeahead_productRating().size(); i++)
						Assert.assertTrue(btn_typeahead_productRating().get(i).isDisplayed(),
								"Product rating display for item " + i);
				} catch (Exception e) {
					ReporterLog.pass("Rating not present for link " + count);
				} catch (AssertionError e) {
					ReporterLog.pass("Rating not present");
				}
				try {
					ReporterLog.pass("Promotion is only Present for: " + btn_typeahead_productPromos().size());
					// checking that the Promo is displayed and not empty
					for (int i = 0; i < btn_typeahead_productPromos().size(); i++) {
						Assert.assertFalse(btn_typeahead_productPromos().get(i).getText().isEmpty(),
								"Text is not present for item " + i);
						Assert.assertTrue(btn_typeahead_productPromos().get(i).isDisplayed(), "Product rating display");
					}
				} catch (Exception e) {
					ReporterLog.pass("Promotions not present  for link " + count);
				} catch (AssertionError e) {
					ReporterLog.pass("Promotions not present");
				}
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Search", "Add To Bag action failed", driver);
			throw e;
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "Search", "Add To Bag action failed", driver);
			throw e;
		}
	}

	/**
	 * METHOD TO GET THE SEARCH KEYWORDS FROM TEST DATA SHEET AND VALIDATE RESULTS
	 * Steps: Scroll up to access all global header elements Verification : Get list
	 * of header tag and verify if search results page displayed, return list of
	 * expected values Verification : Verify if null search results page displayed,
	 * prints null search message
	 * 
	 * @param driver
	 * @param rowNo
	 * @param colNo
	 * @throws Exception
	 */
	public synchronized void searchByData(String data) throws Exception {
		try {
			ReporterLog.actionMsg(Constants.DELIMITER + "-------------------Search By Style ID--------------------");

			// Scroll up to access all global header elements
			utils.scrollUp(driver);
			if (Utils.device.equals("mobile"))
				searchDataMobile(data); // -- sending data in search box using keyboard opening (MOBILE)

			else {
				utils.waitForElementToBeClickable(driver, "HP_Lnk_Search", "search link");
				// Search link
				utils.waitForElementToBeVisible(driver, homepage.lnk_search());
				homepage.click_lnk_search();

				Utils.waitForPageLoaded(driver);
				// Verify Type Ahead Flyout modal
				utils.waitForElementPresent(homepage.txt_SearchBox(), driver);
				// Verify Type Ahead Flyout search text box and send style id
				homepage.txt_SearchBox().sendKeys(data);
				homepage.txt_SearchBox().sendKeys(Keys.ENTER);
				Thread.sleep(2000);
			}
			// Verify Type Ahead Flyout magnifying lense
			// utils.findElementByLocator(driver, "TypeAheadSearchButton", "| Click action
			// failed on search button").click();
			Utils.waitForPageLoaded(driver);
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Search", "Search by style id", driver);
			throw (e);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "Search", "Search by style id", driver);
			throw (e);
		}
	}

	/**
	 * To check that the values in picker wheel are coming correct
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean getPickerWheelValues(int totalIteamInPickerWheel, String data) throws Exception {
		String lastVal = null;
		int i = 0;
		iOSLocator = xlUtils.getXpathFromXML("PickerWheel", Constants.ENVIRONMENT);
		MobileView.switchToNative(driver);
		Thread.sleep(1000);
		element = ((IOSDriver) driver).findElement(By.xpath(iOSLocator));
		String pickervalue[] = data.split(",");

		try {
			for (i = 0; i < totalIteamInPickerWheel; i++) {
				((IOSElement) element).setValue(pickervalue[i]);
				String val = element.getText();
				if (!pickervalue[i].equals(val)) {
					ReporterLog.fail("Value not present " + pickervalue[i] + " in the Sort dropdown");
					return false;
				}
			}
		} catch (Exception e) {
			ReporterLog.fail("Value not present " + pickervalue[i]);
			Assert.fail("Value not present " + pickervalue[i]);
			return false;
		}

		return true;
	}

	/**
	 * Method to enter data in search box from Mobile keyboard and Press ENTER
	 */
	public synchronized void searchDataMobile(String data) throws Exception {
		if (utils.browser.equals("ios")) {
			TouchAction a = new TouchAction((MobileDriver) driver);
			int x = (int) homepage.getTheElementLocations(homepage.txt_SearchBox(), "x-axis");
			int y = (int) homepage.getTheElementLocations(homepage.txt_SearchBox(), "y-axis");
			a.tap(x + 100, y + 100).perform();
			((IOSDriver<WebElement>) driver).getKeyboard().sendKeys(data);
			((IOSDriver<WebElement>) driver).getKeyboard().sendKeys(Keys.ENTER);
			// utils.clickOnTextBox_EnterData_Mobile("Search", data);

		} else {
			homepage.txt_SearchBox().sendKeys(data);
			homepage.txt_SearchBox().sendKeys(Keys.ENTER);
			Utils.waitForPageLoaded(driver);
		}

	}

	public synchronized WebElement txt_typeahead_suggestedKeyword() throws Exception {

		return utils.findElementByLocator(driver, "SRP_typeAheadSuggestedKeyword", "|Search type ahead not displayed ");
	}

	public synchronized WebElement txt_typeahead_highLightedKeyword() throws Exception {

		return utils.findElementByLocator(driver, "SRP_typeAheadHighlightedKeyword",
				"|Search type ahead not displayed ");
	}

	public synchronized WebElement lnk_SRP_contactUs() throws Exception {

		return utils.findElementByLocator(driver, "SRP_ContactUs", "|SRP | cotact us link ");
	}

	public synchronized WebElement lnk_search_closeButton() throws Exception {

		return utils.findElementByLocator(driver, "Search_closeButton", "|SRP | close button ");
	}

	public synchronized WebElement txt_NosearchResult_specificMsg() throws Exception {

		return utils.findElementByLocator(driver, "SRP_SecificSearchTermText", "|SRP | specific search ");
	}

	public synchronized WebElement lnk_NosearchResult_specificLink() throws Exception {

		return utils.findElementByLocator(driver, "SRP_specificSearchTermLink", "|SRP | specific search ");
	}

	public synchronized WebElement txt_searchDidYouMean() throws Exception {

		return utils.findElementByLocator(driver, "SRP_didYouMean", "|SRP | specific search ");
	}

}
