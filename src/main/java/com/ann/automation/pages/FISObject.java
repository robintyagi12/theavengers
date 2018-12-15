package com.ann.automation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.PDPPageObject;
import com.ann.automation.pages.PLPPageObject;
import com.ann.automation.pages.SearchPageObject;

public class FISObject {

	private static WebDriver driver = null;
	static Utils utils = null;
	static XLUtils xlUtils = null;
	PLPPageObject plpPage = null;
	HomePageObjects homePage = null;
	SearchPageObject searcPage = null;
	PDPPageObject productDetails = null;
	public String size = null;
	public String color = null;
	public static String productName_PDP = null;

	public FISObject(WebDriver driver, String brand, String browser, String device) {

		this.driver = driver;
		utils = new Utils(brand, browser, device);
		xlUtils = new XLUtils();
		plpPage = new PLPPageObject(driver, brand, browser, device);
		homePage = new HomePageObjects(driver, brand, browser, device);
		searcPage = new SearchPageObject(driver, brand, browser, device);
		productDetails = new PDPPageObject(driver, brand, browser, device);
	}

	public synchronized WebElement EnterZip() throws Exception {
		return utils.findElementByLocator(driver, "FIS_Enter_ZipCode", "Unable to find Enter Zip code field");
	}

	public synchronized WebElement defaultDistanceValue() throws Exception {
		return utils.findElementByLocator(driver, "FIS_defaultDistanceValue", "Unable to find Default distance field");
	}

	public synchronized void clickFISbutton() throws Exception {
		WebElement fis_button = utils.findElementByLocator(driver, "FIS_findInStoreBtn",
				"| Unable to locate find in store btn");
		fis_button.click();
	}

	public synchronized void CloseFIS() throws Exception {
		WebElement closefis = utils.findElementByLocator(driver, "FIS_closeFIS", "|CLose FIS");
		closefis.click();
	}

	public synchronized List<WebElement> getDirections() throws Exception {

		if (Utils.isDeviceMobile() && !Utils.isBrandFullPrice())
			return utils.findElementsByLocator(driver, "MBL_FIS_GetDirections", "|Get Directions");

		List<WebElement> elem = utils.findElementsByLocator(driver, "FIS_GetDirections", "|Get Directions");
		return elem;
	}

	public synchronized List<String> VerifyDistance() throws Exception {
		List<String> distances = null;
		try {
			distances = new ArrayList<>();
			List<WebElement> distance = new ArrayList<>();
			if (Utils.device.equals("desktop"))
				distance = lst_distanceValues();
			else {
				distance = lst_distanceValues();
				Assert.assertEquals(utils
						.findElementByLocator(driver, "FIS_defaultDistanceValue", "Unable to locate distance dropdown")
						.getText().toUpperCase(), "25 MILES");
			}
			for (int i = 0; i < distance.size(); i++) {
				String Distance = null;
				// Verify 25 miles is default selected
				if (Utils.device.equals("desktop"))
					Distance = distance.get(i).getAttribute("data-value");
				else
					Distance = distance.get(i).getText().replaceAll("[^\\d]", "").replaceAll("[\\s]", "");

				distances.add(Distance);

				if (Utils.device.equals("desktop")) {
					if (distance.get(i).getAttribute("aria-selected").equals("true") && Distance.equals("25")) {
						Assert.assertEquals(Distance, "25");
						ReporterLog.pass("25 Miles is selected as default");
					} else {
						ReporterLog.fail(Distance + " is not selected as default");
					}

				}
			}

		} catch (AssertionError e) {
			throw (e);
		}
		return distances;

	}

	/**
	 * @Prateek Method to select distance value for both desktop and mobile in Find
	 *          In Store or ick Up In Store
	 * 
	 * @param index
	 *            - Index of the drop down value to be selected starting from 1
	 * @throws Exception
	 */
	public synchronized void selectStoreDistanceValue(int index) throws Exception {

		ReporterLog.log("Clicking on Store distance drop down...");

		clickdistanceDropdown();

		ReporterLog.log("Clicked on Store distance drop down. Checking to see drop down values are present...");

		if (Utils.isDeviceMobile()) {

			utils.isElementsPresent(driver, "MBL_FIS_distance_dropdown", "Distance drop down values are present");
			WebElement element = utils.findElementByLocator(driver, "MBL_FIS_distance_dropdown",
					"Distance drop down values are present");

			Select select = new Select(element);
			select.selectByIndex(index);

		} else {

			lst_distanceValues().get(index).click();
		}
	}

	public synchronized List<WebElement> lst_distanceValues() throws Exception {

		List<WebElement> values = null;
		if (Utils.device.equals("desktop"))
			values = utils.findElementsByLocator(driver, "FIS_distance", "Unable to locate distance dropdown");
		else
			values = utils.findElementsByLocator(driver, "FIS_distanceValue_Mbl", "Unable to locate distance dropdown");
		return values;
	}

	public synchronized void clickdistanceDropdown() throws Exception {

		WebElement distanceDropdown = null;

		if (Utils.isDeviceMobile()) {

			utils.isElementPresent(driver, "MBL_FIS_distance_dropdown", "| FIS distance");
			distanceDropdown = utils.findElementByLocator(driver, "MBL_FIS_distance_dropdown", "| FIS distance");

		} else {
			utils.isElementPresent(driver, "FIS_distance_dropdown", "| FIS distance");
			distanceDropdown = utils.findElementByLocator(driver, "FIS_distance_dropdown", "| FIS distance");
		}

		ReporterLog.log("Clicking on Distance drop down in FIS...");
		distanceDropdown.click();
	}

	public synchronized void validateSizeColorCheck(String attribute) throws Exception {
		switch (attribute) {
		case "Color": {
			List<WebElement> colors = productDetails.list_ColorSwatch();
			for (WebElement el : colors) {
				if (el.getAttribute("aria-checked").contains("true")) {
					color = el.getText();
				}
			}
		}
			break;
		case "Size": {
			List<WebElement> size1 = productDetails.list_SizeSwatch();
			for (WebElement el : size1) {
				if (el.getAttribute("aria-checked").contains("true")) {
					size = el.getText();
				}
			}
		}
			break;
		}

	}

	public synchronized void VerifyStoreDetails() throws Exception {
		List<WebElement> storeList = utils.findElementsByLocator(driver, "FIS_FindAddress",
				"No Adresses for Store found ");
		List<WebElement> logo = utils.findElementsByLocator(driver, "FIS_Logo", "FIS Logo not found");
		List<WebElement> address = utils.findElementsByLocator(driver, "SL_StoreAddress", "Address not found");
		List<WebElement> phone = utils.findElementsByLocator(driver, "FIS_PhoneNumber", "Unable to find phone number");
		List<WebElement> distance = utils.findElementsByLocator(driver, "SL_StoreDistance", "Distance not found");
		List<WebElement> storeStatus = utils.findElementsByLocator(driver, "SL_StoreOpenStatus", "");
		List<WebElement> workingHours = utils.findElementsByLocator(driver, "SL_StoreOpenTimings", "");
		for (int i = 0; i < storeList.size(); i++) {
			Assert.assertTrue(logo.get(i).isDisplayed(), "Logo is not dislayed");
			Assert.assertTrue(address.get(i).isDisplayed() && address.get(i).getText() != null,
					"Address are not displayed");
			// Assert.assertTrue(phone.get(i).isDisplayed()&&
			// phone.get(i).getText()!=null,"Phone number not displayed");
			Assert.assertTrue(storeStatus.get(i).isDisplayed() && storeStatus.get(i).getText() != null,
					"Store status not displayed");
			Assert.assertTrue(distance.get(i).isDisplayed() && distance.get(i).getText() != null,
					"distance not displayed");
			Assert.assertTrue(workingHours.get(i).isDisplayed() && workingHours.get(i).getText() != null,
					"Working hours not displayed");

		}
		Assert.assertEquals(storeList.size(), logo.size(), "Logo is not dislayed");
		Assert.assertEquals(storeList.size(), address.size(), "Address are not displayed");
		Assert.assertEquals(storeList.size(), phone.size(), "Phone number not displayed");
		Assert.assertEquals(storeList.size(), distance.size(), "distance not displayed");
		Assert.assertEquals(storeList.size(), storeStatus.size(), "Store status not displayed");
		Assert.assertEquals(storeList.size(), workingHours.size(), "Working hours not displayed");
	}

	public synchronized WebElement fis_storeresult() throws Exception, AssertionError {
		return utils.findElementByLocator(driver, "FIS_GetDirections", "Store Result message");
	}

	public synchronized WebElement ContinueShopping() throws Exception {
		return utils.findElementByLocator(driver, "FIS_continueShop", "Unable to locate Continue Shopping button");
	}

	public synchronized List<WebElement> showMoreDetails() {

		try {

			return utils.findElementsByLocator(driver, "FIS_See_More_Details", "See More Details link not present");
		} catch (Exception e) {
			ReporterLog.fail(e.getMessage());
		}
		return null;
	}

	public synchronized WebElement showLessLink() {

		try {

			return utils.findElementByLocator(driver, "FIS_Show_Less", "Show Less link not visible");
		} catch (Exception e) {
			ReporterLog.fail(e.getMessage());
		}
		return null;
	}

	public synchronized void clickLastShowMoreDetails() {

		if (Utils.isBrandFullPrice()) {

			ReporterLog.log("Clicking on Show More Details");
			int index = showMoreDetails().size();

			try {

				utils.scrollToViewElement(driver, showMoreDetails().get(index - 1));
			} catch (Exception e) {
				ReporterLog.fail("Not able to scroll to last See More Details Element");
			}

			showMoreDetails().get(index - 1).click();
			ReporterLog.log("Clicked on Last Show More Details link");

			utils.isElementClickable(driver, "FIS_Show_Less");
		}
	}
}
