package com.ann.automation.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.PLPPageObject;
import com.ann.automation.pages.SearchPageObject;

public class PDPPageObject {
	private static WebDriver driver = null;
	static Utils utils = null;
	static XLUtils xlUtils = null;
	PLPPageObject plpPage = null;
	HomePageObjects homePage = null;
	SearchPageObject searcPage = null;
	public String size = null;
	public String color = null;
	public static String productName_PDP = null;
	public String val;

	public PDPPageObject(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
		xlUtils = new XLUtils();
		plpPage = new PLPPageObject(driver, brand, browser, device);
		homePage = new HomePageObjects(driver, brand, browser, device);
		searcPage = new SearchPageObject(driver, brand, browser, device);
	}

	/**
	 * Get the Size Types
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_SizeType() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementsByLocator(driver, "PDP_Lnk_MB_SizeType_Options", "|PDP: Size Types not present");
		else
			return utils.findElementsByLocator(driver, "PDP_Lnk_SizeType", "|PDP: Size Types not present");
	}

	/**
	 * click Size Types
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void selectSizeType() throws Exception {
		int listSize = list_SizeType().size();
		for (int i = 0; i < listSize; i++) {
			if (!list_SizeType().get(i).getAttribute("class").contains("highlight")) {
				list_SizeType().get(i).click();
				Utils.waitForPageLoaded(driver);
				break;
			}

		}
	}

	/**
	 * get Size Types
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized String getSelectSizeType() throws Exception {
		String selectedSizeType = null;
		for (int i = 0; i < list_SizeType().size(); i++) {
			if (list_SizeType().get(i).getAttribute("class").contains("highlight")) {
				selectedSizeType = list_SizeType().get(i).getText();
				break;
			}
		}
		return selectedSizeType;
	}

	public synchronized WebElement locateStoreBtn() throws Exception {
		return utils.findElementByLocator(driver, "FIS_locateStoreBtn", "| FIS");

	}

	public synchronized void ClickLocateStoreBtn() throws Exception {

		Utils.isElementPresent(driver, locateStoreBtn());
		// utils.clickWebElement(driver, locateStoreBtn(), "Unable to Click Find
		// in
		// Store Btn");
		utils.scrollToViewElement(driver, locateStoreBtn());
		Utils.handlePopUps();
		locateStoreBtn().click();
	}

	/**
	 * Get the Color Swatch
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_ColorSwatch() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementsByLocator(driver, "MBL_PDP_Lnk_ColorSwatch",
					"| PDP: Color swatch Not Displayed On PDP");

		return utils.findElementsByLocator(driver, "PDP_Lnk_ColorSwatch", "| PDP: Color swatch Not Displayed On PDP");
	}

	/**
	 * Get the Color Swatch - Default color - 1st color in swatch
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_ColorSwatch_DefaultColor() throws Exception {

		return utils.findElementsByLocator(driver, "PDP_Lnk_ColorSwatchDefault",
				"| PDP: Color swatch Not Displayed On PDP");
	}

	/**
	 * Get the Color Swatch
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_ColorSwatchQuickShop() throws Exception {

		return utils.findElementsByLocator(driver, "PDP_Lnk_ColorSwatchQuickShop",
				"| PDP: Color swatch Not Displayed On PDP");
	}

	/**
	 * Get the final Sale message
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement pdp_finalSaleMsg() throws Exception {

		return utils.findElementByLocator(driver, "PDP_FinalSaleMsg", "| PDP: Final Message Not Displayed On PDP");
	}

	/**
	 * click Color Swatch
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void selectColor() throws Exception {
		Utils.waitForPageLoaded(driver);
		Thread.sleep(2000);

		List<WebElement> colors = list_ColorSwatch();

		if (!colors.isEmpty() && !colors.get(0).isDisplayed()) {

			driver.navigate().refresh();
			Utils.waitForPageLoaded(driver);
		}

		utils.waitForElementsToBeVisible(driver, list_ColorSwatch());
		utils.waitForElementToBeVisible(driver, pdp_btn_AddToBag());

		for (int i = 0; i < colors.size(); i++) {
			if (!colors.get(i).getAttribute("class").contains("disabled")) {
				utils.clickWebElement(driver, colors.get(i), "color selection");
				if (XLUtils.env.equalsIgnoreCase("breakfix") || XLUtils.env.equalsIgnoreCase("dryrun")) {
					color = utils.findElementByLocator(driver, "PDP_ColorSwatchName", "").getText();
				} else
					color = colors.get(i).getText();

				System.out.println("*********** " + color);
				break;
			}
		}
	}

	public synchronized void addProductOfSpecificColorAndSize(WebDriver driver, String color, String size)
			throws Exception {

		try {

			List<WebElement> colorList = utils.findElementsByLocator(driver, "PDP_ColorSwatch",
					"| PDP: Color Swatch Not Present");
			for (WebElement sl : colorList) {
				String ColorAttributes = sl.getAttribute("class").toString();

				if (ColorAttributes.contains("disabled") == false && sl.getText().equalsIgnoreCase(color)) {
					sl.click();
					Thread.sleep(2000);
					break;
				}
			}
			List<WebElement> SizeList = utils.findElementsByLocator(driver, "PDP_SizeSwatch",
					"| PDP: Color Swatch Not Present");
			for (WebElement sl : SizeList) {
				String SizeAttributes = sl.getAttribute("class").toString();

				if (SizeAttributes.contains("disabled") == false && sl.getText().equalsIgnoreCase(size)) {
					sl.click();
					Thread.sleep(2000);
					break;
				}
			}
			WebElement btnaddtobag = driver.findElement(By.id("pdp-add-to-bag"));
			btnaddtobag.click();
			Thread.sleep(8000);// This is time for Item addition to cart
								// confirmation modal disappears
		} catch (Exception e) {
			throw new Error("Search Specific Color And Size Add To Bag STH Operation Fail..." + e);
		}
	}

	/**
	 * click Color Swatch
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void selectColorQuickShop() throws Exception {
		List<WebElement> colors = list_ColorSwatchQuickShop();
		for (WebElement el : colors) {
			if (!el.getAttribute("class").contains("disabled")) {
				utils.clickWebElement(driver, el, "color selection");
				color = el.getText();
				break;
			}
		}
	}

	/**
	 * Get the Color Swatch Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_ColorSwatchName() throws Exception {

		return utils.findElementsByLocator(driver, "PDP_ColorSwatchName",
				"| PDP: Color swatch Name Not Displayed On PDP");
	}

	/**
	 * Get the Size Swatch
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_SizeSwatch() throws Exception {

		if (Utils.isDeviceMobile() && !Utils.brand.equals("LGFP"))
			return utils.findElementsByLocator(driver, "PDP_Lnk_MB_SizeSwatch_options", "| PDP: Size not present");

		else if (Utils.isDeviceMobile() && Utils.brand.equals("LGFP"))
			return utils.findElementsByLocator(driver, "MBL_SizeSwatch_LG", "| PDP: Size not present");

		else
			return utils.findElementsByLocator(driver, "PDP_Lnk_SizeSwatch", "| PDP: Size not present");
	}

	/**
	 * click Size Swatch
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void selectSize() throws Exception {
		List<WebElement> sizes = list_SizeSwatch();
		for (WebElement el : sizes) {
			if (el.isEnabled()) {
				utils.clickWebElement(driver, el, "size selection");
				size = el.getText();
				System.out.println("************* " + size);
				break;
			}
		}
	}

	/**
	 * click Size Swatch
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void selectParticularSize(String size) throws Exception {
		List<WebElement> sizes = list_SizeSwatch();
		for (WebElement el : sizes) {
			if (el.getText().equalsIgnoreCase(size)) {
				utils.clickWebElement(driver, el, "size selection");
				break;
			}
		}
	}

	/**
	 * Get the ADD TO BAG button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement pdp_btn_AddToBag() throws Exception {

		return utils.findElementByLocator(driver, "PDP_btn_AddToBag", "| PDP: Add To Bag not present");
	}

	/**
	 * Get the ADD TO BAG button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void pdp_AddToBag() throws Exception {

		// Code to remove focus from zoomed image on page load
		if (!Utils.isDeviceMobile()) {
			Actions actions = new Actions(driver);
			actions.moveToElement(productPrice()).build().perform();
		}

		utils.scrollToViewElement(driver, pdp_btn_AddToBag());

		// Handling Pop-Up that sometimes over shadows the ADD TO BAG button in mobile
		if (Utils.isDeviceMobile())
			handleBounceXOnPDP();

		pdp_btn_AddToBag().click();
		// Thread.sleep(3000);

		if (!utils.brand.equals("LGFP"))
			Assert.assertTrue(utils.isElementPresent(driver, "PDP_continueShopping"),
					"Item Added To Bag modal not displayed in mobile");
	}

	public synchronized void handleBounceXOnPDP() {

		List<WebElement> elementList = driver
				.findElements(By.xpath("//*[@class='bx-close bx-close-link bx-close-inside']"));

		if (!elementList.isEmpty()) {

			WebElement element = elementList.get(0);

			boolean flag = false;

			try {

				flag = element.isDisplayed();

			} catch (Exception e) {

				System.out.println("BounceX detected on PDP page of " + Utils.brand);
			}

			if (flag) {

				element.click();
			}
		}
	}

	/**
	 * Method used for Add Item to Shopping Bag.
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void addToBag(WebDriver driver, int rowNo, int colNo) throws Exception {
		try {
			if (rowNo != 0 && colNo != 0)
				openPDP(driver, rowNo, colNo);

			// plpPage.navigateToPLP(driver, "Clothing", "New Arrivals");
			Reporter.log(Constants.DELIMITER
					+ "*************Product Description Page: Add Item to Shopping Bag************");

			Thread.sleep(500);

			// Clicking on the Size Types on the Product Description Page
			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Size Types");

			// Clicking on the Color Swatch on the Product Description Page
			selectColor();
			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Color Swatch");

			// Clicking on the Size Swatch on the Product Description Page
			if (utils.device.equals("desktop"))
				selectSize();
			else
				selectSizeMobile();

			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Size Swatch");

			if (utils.browser.equals("android")) {
				utils.scrollDownByxPostion(driver, 1000);
				// pdp_btn_AddToBag().click();
				pdp_AddToBag();
			} else {

				// productName_PDP = txt_ProductName().getText();
				// Clicking on the Add To Bag Button on the Product Description
				// Page

				// Clicking on the Add To Bag Button on the Product Description
				// Page

				// Clicking on the Add To Bag Button on the Product Description
				// Page

				pdp_AddToBag();
			}
			Reporter.log(Constants.DELIMITER + "| Login Page: Click action is perfromed on Add To Bag button");

			Utils.waitForPageLoaded(driver);
			// Assert.assertTrue(utils.isElementPresent(driver,
			// "PDPAddToBagNotification",
			// "Add To Bag Notifications"),
			// "'ITEM ADDED TO BAG' Not displayed");
			ReporterLog.actionMsg("Item Successfully Added to Bag");
			Thread.sleep(6000);
			if (utils.browser.equals("android")) {
				utils.scrollUp(driver);
				Thread.sleep(1000);
			}

			homePage.click_lnk_shoppingBag();
			Utils.waitForPageLoaded(driver);

			// utils.waitForElementToBeClickable(driver,
			// "PDPAddToBagNotification",
			// "checkout now not clickable");
			// utils.findElementByLocator(driver, "PDPAddToBagNotification",
			// "continue
			// shopping not found").click();
			// utils.waitForPageLoaded(driver);
			// //
			// driver.get("https://origin-stage2-factory.r.anntaylor.com/cart");
			// // Utils.waitForPageLoaded(driver);

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PDP", "Add To Bag action failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PDP", "Add To Bag action failed", driver);
		}
	}

	public synchronized void SelectSpecificColor(String color) throws Exception {
		List<WebElement> colors = list_ColorSwatch();
		for (WebElement el : colors) {
			if (el.getAttribute("title").contains(color)) {
				utils.clickWebElement(driver, el, "color selection");
				break;
			}
		}
	}

	/**
	 * Method used for Add Item to Shopping Bag.
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void addToBagSpecificSizeAndColor(WebDriver driver, int rowNo, int colNo, String size,
			String color) throws Exception {
		try {
			if (rowNo == 0 && colNo == 0)
				ReporterLog.actionMsg("PDP page already opened");
			else
				openPDP(driver, rowNo, colNo);

			Reporter.log(Constants.DELIMITER
					+ "*************Product Description Page: Add Item to Shopping Bag************");

			Thread.sleep(2000);
			// try {
			// Clicking on the Size Types on the Product Description Page
			/* commented on 03/12 as still no size type being seen on the PDP */
			// List<WebElement> sizesType=list_SizeType();
			// for(WebElement el:sizesType)
			// {
			// if(el.getText().equalsIgnoreCase(sizetype))
			// {
			// utils.clickWebElement(driver, el, "size selection");
			// break;
			// }
			// }
			// }catch(AssertionError e)
			// {
			// ReporterLog.warn("No Size Type Present");
			// }
			// catch (Exception e) {
			// ReporterLog.warn("No Size Type Present");
			// }
			Thread.sleep(3000);
			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Size Types");
			if (color.isEmpty()) {
				selectColor();
			} else {
				List<WebElement> colors = list_ColorSwatch();

				if (XLUtils.env.equalsIgnoreCase("breakfix") || XLUtils.env.equalsIgnoreCase("dryrun")) {
					for (WebElement el : colors) {
						el.click();
						Utils.waitForPageLoaded(driver);
						if (utils.findElementByLocator(driver, "PDP_ColorSwatchName", "").getText().toUpperCase()
								.contains(color.toUpperCase())) {
							break;
						}
					}
				} else {
					// Clicking on the Color Swatch on the Product Description
					// Page
					for (WebElement el : colors) {
						if (el.getAttribute("title").contains(color)) {
							utils.clickWebElement(driver, el, "color selection");
							break;
						}
					}
				}
			}
			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Color Swatch");
			Thread.sleep(2000);

			// Clicking on the Size Swatch on the Product Description Page
			if (utils.device.equals("desktop")) {
				if (size.isEmpty()) {
					selectSize();
				} else {
					selectParticularSize(size);
				}
			} else {
				if (size.isEmpty()) {
					selectSizeMobile();
				} else {
					selectParticularSizeMobile(size);
				}
			}
			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Size Swatch");
			Thread.sleep(2000);
			// Clicking on the Add To Bag Button on the Product Description Page
			pdp_AddToBag();
			Reporter.log(Constants.DELIMITER + "| Login Page: Click action is perfromed on Add To Bag button");
			// Utils.waitForPageLoaded(driver);

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PDP", "Add To Bag action failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PDP", "Add To Bag action failed", driver);
		}
	}

	/**
	 * Method used for Add Item to Shopping Bag.
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void openPDP(WebDriver driver, int rowNo, int columnNo) throws Exception {
		String pdpURL;
		try {
			pdpURL = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_SEARCH, rowNo, 1);
			searcPage.searchByData(pdpURL);
			if (Utils.device.equals("desktop"))
				driver.navigate().refresh();
			Utils.waitForPageLoaded(driver);
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PDP", "Add To Bag action failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PDP", "Add To Bag action failed", driver);
		}

	}

	/**
	 * Get the Checkout button
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement pdp_btn_checkout() throws Exception {

		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "PDP_btn_FlyoutViewBag", "| PDP:Checkout button not present");

		else if (!Utils.brand.equals("LGFP")) {
			utils.isElementPresent(driver, "PDP_btn_FlyoutCheckout");
			return utils.findElementByLocator(driver, "PDP_btn_FlyoutCheckout", "| PDP:Checkout button not present");
		}

		else
			return utils.findElementByLocator(driver, "PDP_btn_FlyoutCheckout_LG", "| PDP:Checkout button not present");
	}

	/**
	 * Click on checkout button
	 * 
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_lnk_checkout() throws Exception {
		utils.clickWebElement(driver, pdp_btn_checkout(), "Checkout button not found");

	}

	/**
	 * Get the Product Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_ProductName() throws Exception {

		return utils.findElementByLocator(driver, "PDP_ProductName", "| PDP:Product Name not present");
	}

	/**
	 * Get the Bread Chrumb
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> txt_breadChrumb() throws Exception {

		return utils.findElementsByLocator(driver, "PDP_BreadCrumb", "| PDP:Bread Crumb not present");
	}

	/**
	 * Get the Bread Chrumb
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement div_pdpBreadcrumb_parent() throws Exception {

		return utils.findElementByLocator(driver, "PDP_BreadCrumb_Parent", "| PDP:Bread Crumb not present");
	}

	/**
	 * Get the Price
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement pdpPrice() throws Exception {

		return utils.findElementByLocator(driver, "PDP_Price", "| PDP:Price not present");
	}

	/**
	 * Get the Color Swatch Price
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> colorSwatchPrice() throws Exception {

		return utils.findElementsByLocator(driver, "PDP_ColorSwatchPrice", "| PDP:Color Swatch Price not present");
	}

	/**
	 * Get the Marketing message
	 * 
	 * @return Webelement
	 * @throws Exception
	 */

	public synchronized WebElement pdpMarketingMsg() throws Exception {
		return utils.findElementByLocator(driver, "PDP_MarketingMsg", "| PDP: Marketing Message div not present");
	}

	/**
	 * Get the Error Message For Add to bag without selecting any thing
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement error_EmptySelectionBag() throws Exception {

		return utils.findElementByLocator(driver, "PLP_QS_AddBag_Error_message",
				"| PDP:Error Message For Add to bag without selecting any thing not present");
	}

	/**
	 * Get the Content(Product Name) of the Shopping bag page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement shoppingBagProductName() throws Exception {

		return utils.findElementByLocator(driver, "PDP_ShoppingBagProduct",
				"| PDP:Content(Product Name) of the Shopping bag page");
	}

	/**
	 * Click on continue shopping button
	 * 
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_lnk_continueShopping() throws Exception {
		utils.clickWebElement(driver, btn_continueShopping(), "Continue Shopping button not found");

	}

	public synchronized void click_lnk_writeareview() throws Exception {
		utils.clickWebElement(driver, lnk_writeareview(), "Continue Shopping button not found");

	}

	/**
	 * Get the Product Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_continueShopping() throws Exception {

		return utils.findElementByLocator(driver, "PDP_continueShopping", "| PDP:Continue Shopping not present");
	}

	// Added By Sunil Jangir | Sep-05
	public synchronized WebElement lnk_writeareview() throws Exception {

		return utils.findElementByLocator(driver, "BV_WriteAReviewLink", "| PDP:Write A Review link not present");
	}

	public synchronized void submitReviewsOnPDP() throws Exception {
		Utils.waitForPageLoaded(driver);

		utils.findElementByLocator(driver, "BV_WriteAReviewLink", "| BVForm:Write A Review link not present").click();
		Thread.sleep(10000);
		utils.findElementByLocator(driver, "BV_SelectRating", "| BVForm:Ratings are not available for selection")
				.click();

		String reviewTitle = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_BAZAARVOICE, 1, 0);
		utils.findElementByLocator(driver, "BV_ReviewTitle", "| BVForm:Review Title edit field not available")
				.sendKeys(reviewTitle);

		String reviews = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_BAZAARVOICE, 1, 1);
		utils.findElementByLocator(driver, "BV_Reviews", "| BVForm:Reviews edit field not available").sendKeys(reviews);

		utils.findElementByLocator(driver, "BV_recommended", "| BVForm:Recommended button is not available").click();

		String nickName = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_BAZAARVOICE, 1, 2);
		utils.findElementByLocator(driver, "BV_nickname", "| BVForm:Nickname edit field not available")
				.sendKeys(nickName);

		String whereDoYouLive = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_BAZAARVOICE, 1, 3);
		utils.findElementByLocator(driver, "BV_wheredoyoulive", "| BVForm:WhereDoYouLive edit field not available")
				.sendKeys(whereDoYouLive);

		String bvemail = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_BAZAARVOICE, 1, 4);
		utils.findElementByLocator(driver, "BV_email", "| BVForm:Email edit field not available").sendKeys(bvemail);

		// Selecting Age
		WebElement age = utils.findElementByLocator(driver, "BV_age", "| BVForm:Age dropdown is not found");

		age.click();
		Thread.sleep(2000);

		Select select = new Select(age);
		select.selectByIndex(1);
		age.click();

		// Selecting Style
		WebElement style = utils.findElementByLocator(driver, "BV_style", "| BVForm:Age dropdown is not found");

		style.click();
		Thread.sleep(2000);

		select = new Select(style);
		select.selectByIndex(1);
		style.click();

		// Selecting Body Type
		WebElement bodyType = utils.findElementByLocator(driver, "BV_bodyType", "| BVForm:Age dropdown is not found");

		bodyType.click();
		Thread.sleep(2000);

		select = new Select(bodyType);
		select.selectByIndex(1);
		bodyType.click();

		// Selecting Height
		WebElement height = utils.findElementByLocator(driver, "BV_height", "| BVForm:Age dropdown is not found");

		height.click();
		Thread.sleep(2000);

		select = new Select(height);
		select.selectByIndex(1);
		height.click();

		// Selecting Inches
		WebElement Inches = utils.findElementByLocator(driver, "BV_inches", "| BVForm:Age dropdown is not found");

		Inches.click();
		Thread.sleep(2000);

		select = new Select(Inches);
		select.selectByIndex(1);
		Thread.sleep(3000);
		Inches.click();

		utils.scrollDownByxPostion(driver, 1000);
		Thread.sleep(3000);

		// Selecting Is This First Time Purchase Option
		WebElement firstTimePurchase = utils.findElementByLocator(driver, "BV_firsttimepurchase",
				"| BVForm:Age dropdown is not found");

		firstTimePurchase.click();
		Thread.sleep(2000);

		select = new Select(firstTimePurchase);
		select.selectByIndex(1);
		firstTimePurchase.click();

		Thread.sleep(5000);

		// Selecting Did you read product reviews
		WebElement doYouRead = utils.findElementByLocator(driver, "BV_reviewsread",
				"| BVForm:Age dropdown is not found");

		doYouRead.click();
		Thread.sleep(2000);

		select = new Select(doYouRead);
		select.selectByIndex(1);
		doYouRead.click();
		Thread.sleep(3000);

		// Selecting Where Do You Purchase The Product
		WebElement whereDoYouPurchase = utils.findElementByLocator(driver, "BV_wheredoyoupurchase",
				"| BVForm:Age dropdown is not found");

		whereDoYouPurchase.click();
		Thread.sleep(3000);

		select = new Select(whereDoYouPurchase);
		select.selectByIndex(1);
		whereDoYouPurchase.click();
		Thread.sleep(3000);

		// Clicking Fit #
		WebElement fit = utils.findElementByLocator(driver, "BV_fit", "| BVForm:Age dropdown is not found");
		fit.click();
		Thread.sleep(3000);

		// Clicking recommendation #
		WebElement recommend = utils.findElementByLocator(driver, "BV_recommend", "| BVForm:Age dropdown is not found");
		recommend.click();
		Thread.sleep(3000);

		// Clicking Check Box i.e. "I agree to the terms & conditions"
		WebElement termsAgree = utils.findElementByLocator(driver, "BV_termsagree",
				"| BVForm:Age dropdown is not found");
		termsAgree.click();
		Thread.sleep(3000);

		// Clicking Check Box i.e. "I agree to the terms & conditions"
		WebElement postReview = utils.findElementByLocator(driver, "BV_reviewsubmit",
				"| BVForm:Age dropdown is not found");
		postReview.click();
		Thread.sleep(7000);

		WebElement reviewSubmitText = utils.findElementByLocator(driver, "BV_reviewsubmitconfirmation",
				"| BVForm:Age dropdown is not found");
		Assert.assertTrue(reviewSubmitText.getText().contains("submitted"));
		Thread.sleep(10000);

	}

	/**
	 * Get the Product Detail Accordion Infomation div
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement productInfomationAccordionDetail() throws Exception {

		return utils.findElementByLocator(driver, "PDP_ProductInfomationAccordionDetail",
				"| PDP:Product Description Accordion Infomation div not present");
	}

	/**
	 * Click on the Product Infomation Accordion Label
	 * 
	 * @throws Exception
	 */
	public synchronized void click_productInfomationAccordianLabel() throws Exception {
		utils.clickWebElement(driver, ProductInfomationAccordionLabel(),
				"Product Infomation Accordion Label not found");

	}

	/**
	 * Get the Product Label Accordion
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement ProductInfomationAccordionLabel() throws Exception {

		return utils.findElementByLocator(driver, "PDP_ProductInfomationAccordionLabel",
				"| PDP:Product Infomation Accordion Label not present");
	}

	/**
	 * Get the Product Infomation Description Accordion
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement ProductInfomationAccordionDescription() throws Exception {

		return utils.findElementByLocator(driver, "PDP_ProductInfomationAccordionDescription",
				"| PDP:Product Description Accordion not present");
	}

	/**
	 * Get the Fabric Care label
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement fabricCareAccordionLabel() throws Exception {

		return utils.findElementByLocator(driver, "PDP_FabricInfoLabel",
				"| PDP:Fabric Label Accordion Infomation div not present");
	}

	/**
	 * Get the Product infomation inside flyout
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement productInfomationFlyout() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "PDP_MB_FLyOutProductInfomation",
					"|PDP:Product Infomation Flyout not present");
		else
			return utils.findElementByLocator(driver, "PDP_FlyoverProductInfomation",
					"| PDP:Product Infomation Flyout not present");
	}

	/**
	 * Get the Size Guide Label
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement sizeGuideLabel() throws Exception {

		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "PDP_MB_SizeGuide", "| Size Guide label not present");
		else
			return utils.findElementByLocator(driver, "PDP_SizeGuide", "| Size Guide label not present");
	}

	/**
	 * Click on Size Guide
	 * 
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_lnk_sizeGuide() throws Exception {
		utils.clickWebElement(driver, sizeGuideLabel(), "Size Guide label not found");

	}

	/**
	 * Get the Size Guide Description
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_viewFullSizeGuide() throws Exception {
		WebElement element = null;
		if (utils.brand.equals("ATF") && utils.device.equals("desktop")) {
			element = utils.findElementByLocator(driver, "PDP_ViewSizeGuide_Link",
					"| Size Guide Description not present");
		} else if (utils.brand.equals("LO") || utils.device.equalsIgnoreCase("mobile")) {
			element = utils.findElementByLocator(driver, "PDP_LO_lnk_SizeChart",
					"| Size Guide Description not present");
		}
		return element;
	}

	/**
	 * Get the Shipping & Return Label
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement shippingReturnLabel() throws Exception {

		return utils.findElementByLocator(driver, "PDP_ShippingReturn", "| Shipping & Return label not present");
	}

	/**
	 * Click on Shipping & Return
	 * 
	 * @return void
	 * @throws Exception
	 */
	public synchronized void click_lnk_shippingReturn() throws Exception {
		utils.clickWebElement(driver, shippingReturnLabel(), "Shipping & Return label not found");

	}

	/**
	 * Get the Shipping & Return Description
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement shippingReturnDescription() throws Exception {

		return utils.findElementByLocator(driver, "PDP_ShippingReturnDescription",
				"| Shipping & Return Description not present");
	}

	/**
	 * Get the Partial Markdown price
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement productPrice() throws Exception {

		return utils.findElementByLocator(driver, "PDP_priceRange", "| Mark down price not present");
	}

	/**
	 * Method used for Add Item to Shopping Bag.
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void addToBagSpecificSizeAndColorMobile(WebDriver driver, int rowNo, int colNo, String sizetype,
			String color) throws Exception {
		try {
			if (rowNo == 0 && colNo == 0)
				ReporterLog.actionMsg("PDP page already opened");
			else
				openPDP(driver, rowNo, colNo);

			Reporter.log(Constants.DELIMITER
					+ "*************Product Description Page: Add Item to Shopping Bag************");

			Thread.sleep(500);

			// Clicking on the Size Types on the Product Description Page
			try {
				if (!sizetype.isEmpty()) {
					selectParticularSizeTypeMobile(sizetype);
				}
			} catch (Exception e) {
				ReporterLog.fail("Size type not there is the product");
			} catch (AssertionError e) {
				ReporterLog.fail("Size type not there is the product");
			}
			Thread.sleep(3000);

			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Size Types");
			if (color.isEmpty()) {
				selectColor();
			} else {
				// Clicking on the Color Swatch on the Product Description Page
				List<WebElement> colors = list_ColorSwatch();
				if (XLUtils.env.equalsIgnoreCase("breakfix") || XLUtils.env.equalsIgnoreCase("dryrun")) {
					for (WebElement el : colors) {
						el.click();
						Utils.waitForPageLoaded(driver);
						if (utils.findElementByLocator(driver, "PDP_ColorSwatchName", "").getText().toUpperCase()
								.contains(color.toUpperCase())) {
							break;
						}
					}
				} else {
					for (WebElement el : colors) {
						if (el.getAttribute("title").contains(color)) {
							el.click();
							Thread.sleep(2000);
							break;
						}
					}
				}
			}
			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Color Swatch");

			// Clicking on the Size Swatch on the Product Description Page
			selectSizeMobile();
			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Size Swatch");

			// Clicking on the Add To Bag Button on the Product Description Page
			utils.scrollToViewElement(driver, pdp_btn_AddToBag());
			pdp_btn_AddToBag().click();
			Reporter.log(Constants.DELIMITER + "| Login Page: Click action is perfromed on Add To Bag button");

			// Thread.sleep(2000);
			if (Utils.device.equals("desktop")) {
				Assert.assertTrue(utils.isElementPresent(driver, "PDPAddToBagNotification", "Add To Bag Notifications"),
						"'ITEM ADDED TO BAG' Not displayed");
				ReporterLog.actionMsg("Item Successfully Added to Bag");
			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PDP", "Add To Bag action failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PDP", "Add To Bag action failed", driver);
		}
	}

	/**
	 * click Size Swatch
	 * 
	 * 
	 * @throws Exception
	 */
	public synchronized void selectSizeMobile() throws Exception {

		if (Utils.brand.equals("LGFP")) {

			Assert.assertTrue(
					utils.isElementsPresent(driver, "MBL_SizeSwatch_LG", "Sizes not present for LGFP on mobile"));

			try {
				List<WebElement> sizes = utils.findElementsByLocator(driver, "MBL_SizeSwatch_LG",
						"Size not present on mobile");

				for (WebElement element : sizes) {

					if (element.isDisplayed()) {
						element.click();
						return;
					}
				}
			} catch (Exception e) {
				ReporterLog.log("Sizes not displayed on LGFP");
			}
			return;
		}

		String value = null;
		utils.scrollDownByxPosition(driver, 400);
		List<WebElement> sizes = list_SizeSwatch();

		for (WebElement e : sizes) {
			if (e.isEnabled()) {
				value = e.getText();
				if (value.equalsIgnoreCase("Select a size") || value.equalsIgnoreCase("Please select a size"))
					continue;
				else
					break;
			}
		}
		size = value;
		utils.selectDropDownByValue("PDP_Lnk_MB_SizeSwatch", driver, value);

	}

	public synchronized void selectParticularSizeMobile(String size) throws Exception {
		String value = null;
		utils.scrollDownByxPosition(driver, 400);
		List<WebElement> sizes = list_SizeSwatch();

		for (WebElement e : sizes) {
			if (e.getText().equalsIgnoreCase(size) && (!e.getText().equalsIgnoreCase("select a size")
					|| !e.getText().equalsIgnoreCase("please select a size"))) {
				value = e.getText();
				break;
			}
		}
		utils.selectDropDownByValue("PDP_Lnk_MB_SizeSwatch", driver, value);

	}

	public synchronized void selectParticularSizeTypeMobile(String sizeType) throws Exception {
		String value = null;
		utils.scrollDownByxPosition(driver, 400);
		List<WebElement> size_types = list_SizeType();

		for (WebElement e : size_types) {
			if (e.getText().equalsIgnoreCase(sizeType)) {
				value = e.getText();
				break;
			}
		}
		utils.selectDropDownByValue("PDP_Lnk_MB_SizeType", driver, value);

	}

	/**
	 * Get and Validate the color of webelement
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void validateElementColor(WebDriver driver, String object, String colorText,
			String customMessage) throws Exception {
		WebElement elem = utils.findElementByLocator(driver, object, "Product Name is not present");
		String color = elem.getCssValue("color");
		String hex = Color.fromString(color).asHex();
		if (colorText.equalsIgnoreCase("black")) {
			Assert.assertTrue(hex.equalsIgnoreCase("#000000"), "Color text is not Black");
			ReporterLog.pass("Color text is Black for " + customMessage);
		}
	}

	/**
	 * Get the product image src
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> productImage() throws Exception {
		List<WebElement> images = null;
		try {
			images = utils.findElementsByLocator(driver, "PDP_productImage", "| product image not present");
		} catch (Exception e) {

		}
		return images;
	}

	/**
	 * Get the order value in mini bag
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_miniBagOrderValue() throws Exception {

		return utils.findElementByLocator(driver, "PDP_MiniBag_orderValue", "Mini Bag| order value");
	}

	/**
	 * Get product link on mini bag
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_productNameMiniBag() throws Exception {

		return utils.findElementByLocator(driver, "PDP_MiniBag_productLNK", "Mini Bag| Product name");
	}

	/**
	 * Method used for Add Item to Shopping Bag.
	 * 
	 * 
	 * @throws Exception
	 */

	/**
	 * Method is used to select the second size from the PDP page.
	 * 
	 * @param driver
	 * @param rowNo
	 * @param colNo
	 * @throws Exception
	 */

	public synchronized void storelocator_selectSize() throws Exception {
		List<WebElement> sizes = list_SizeSwatch();
		for (WebElement el : sizes) {
			if (el.isEnabled())

			{

				utils.clickWebElement(driver, el, "size selection");
				size = el.getText();
				System.out.println("************* " + size);
				break;
			}
		}
	}

	public synchronized void addToBagPDP(WebDriver driver, int rowNo, int colNo) throws Exception {
		try {
			openPDP(driver, rowNo, colNo);
			// plpPage.navigateToPLP(driver, "Clothing", "New Arrivals");
			Reporter.log(Constants.DELIMITER
					+ "*************Product Description Page: Add Item to Shopping Bag************");

			Thread.sleep(500);

			// Clicking on the Size Types on the Product Description Page
			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Size Types");

			// Clicking on the Color Swatch on the Product Description Page
			selectColor();
			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Color Swatch");

			// Clicking on the Size Swatch on the Product Description Page
			if (utils.device.equals("desktop"))
				selectSize();
			else
				selectSizeMobile();

			Reporter.log(Constants.DELIMITER + "| PDP Page: Click action is performed on Size Swatch");

			if (utils.browser.equals("android")) {
				utils.scrollDownByxPostion(driver, 1000);
				pdp_btn_AddToBag().click();
			} else {

				productName_PDP = txt_ProductName().getText();
				// Clicking on the Add To Bag Button on the Product Description
				// Page

				// Clicking on the Add To Bag Button on the Product Description
				// Page

				// Clicking on the Add To Bag Button on the Product Description
				// Page

				pdp_AddToBag();
			}
			Reporter.log(Constants.DELIMITER + "| Login Page: Click action is perfromed on Add To Bag button");

			Utils.waitForPageLoaded(driver);
			// Assert.assertTrue(utils.isElementPresent(driver,
			// "PDPAddToBagNotification",
			// "Add To Bag Notifications"),
			// "'ITEM ADDED TO BAG' Not displayed");
			ReporterLog.actionMsg("Item Successfully Added to Bag");
			Thread.sleep(6000);
			if (utils.browser.equals("android")) {
				utils.scrollUp(driver);
			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PDP", "Add To Bag action failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PDP", "Add To Bag action failed", driver);
		}
	}

	/**
	 * Method used for clicking on Add To Wishlist link on PDP page
	 * 
	 * @throws Exception
	 */
	public synchronized void clickOnWishList(WebDriver driver) throws Exception {

		utils.isElementPresent(driver, "Add_To_WishList");

		try {

			WebElement elem = utils.findElementByLocator(driver, "Add_To_WishList", "Add To Wishlist not clicked");
			elem.click();
			ReporterLog.actionMsg("Add To WishList clicked");

		} catch (Exception e) {

			CustomException.throwExceptionError(e, "PDP", "Add To WishList action failed", driver);
		}
	}

	/**
	 * Method used for verifying message dispayed after clicking on Add To Wishlist
	 * 
	 * @throws Exception
	 */
	public synchronized void verifyAddToWishlistMsg(WebDriver driver) throws Exception {

		try {

			String elemText = utils.findElementByLocator(driver, "WishList_Added_Msg",
					"ADDED text not displayed after clicking on Add To Wishlist").getText();
			Assert.assertEquals(elemText, "ADDED");
		} catch (Exception e) {

			CustomException.throwExceptionError(e, "PDP", "Add To WishList Modal not displayed", driver);
		}
	}

	public synchronized void verifyMayWeSuggestSection(WebDriver driver)
			throws Exception, InterruptedException, AssertionError, IOException {

		/* Buffer to hold your errors */
		StringBuffer errorBuffer = new StringBuffer();

		// Verification : May We Suggest section on PDP
		try {
			Assert.assertTrue(utils.isElementPresent(driver, "PDPRVSection",
					"People like you also viewed : Section not showing up on PDP"));
			Reporter.log(
					Constants.DELIMITER + Constants.PASS + "| People like you also viewed : section showing up on PDP");
		} catch (AssertionError e) {
			errorBuffer.append(e.getMessage() + "\n");
		}

		// Verification : May We Suggest Header
		try {
			Assert.assertTrue(utils.isElementPresent(driver, "PDPRVHeader",
					"People like you also viewed : Header not showing up on PDP"));
			Reporter.log(
					Constants.DELIMITER + Constants.PASS + "| People like you also viewed : Header showing up on PDP");
		} catch (AssertionError e) {
			errorBuffer.append(e.getMessage() + "\n");
		}
		// Verification : People like you also viewed : Product Image
		try {
			if (Utils.brand.equals("ATFP")) {
				Assert.assertTrue(utils.isElementPresent(driver, "AT_PDPRVProductImage",
						"People like you also viewed : Product Image not showing up on PDP"));
				Reporter.log(Constants.DELIMITER + Constants.PASS
						+ "| People like you also viewed : Product Image showing up on PDP");
			} else if (Utils.brand.equals("LOFP")) {
				Assert.assertTrue(utils.isElementPresent(driver, "LO_PDPRVProductImage",
						"People like you also viewed : Product Image not showing up on PDP"));
				Reporter.log(Constants.DELIMITER + Constants.PASS
						+ "| People like you also viewed : Product Image showing up on PDP");
			} else {
				Assert.assertTrue(utils.isElementPresent(driver, "PDPRVProductImage",
						"People like you also viewed : Product Image not showing up on PDP"));
				Reporter.log(Constants.DELIMITER + Constants.PASS
						+ "| People like you also viewed : Product Image showing up on PDP");
			}
		} catch (AssertionError e) {
			errorBuffer.append(e.getMessage() + "\n");
		}

		// Verification : People like you also viewed : Product Price
		try {
			if (Utils.brand.equals("ATFP")) {
				Assert.assertTrue(utils.isElementPresent(driver, "AT_PDPRVProductPrice",
						"People like you also viewed : Product Price not showing up for People like you also viewed"));
				Reporter.log(Constants.DELIMITER + Constants.PASS
						+ "| People like you also viewed : Product Price showing up for People like you also viewed");
			} else if (Utils.brand.equals("LOFP")) {
				Assert.assertTrue(utils.isElementPresent(driver, "LO_PDPRVProductPrice",
						"People like you also viewed : Product Price not showing up for People like you also viewed"));
				Reporter.log(Constants.DELIMITER + Constants.PASS
						+ "| People like you also viewed : Product Price showing up for People like you also viewed");
			} else {
				Assert.assertTrue(utils.isElementPresent(driver, "PDPRVProductPrice",
						"People like you also viewed : Product Price not showing up for People like you also viewed"));
				Reporter.log(Constants.DELIMITER + Constants.PASS
						+ "| People like you also viewed : Product Price showing up for People like you also viewed");
			}

		} catch (AssertionError e) {
			errorBuffer.append(e.getMessage() + "\n");
		}

		// Verification : People like you also viewed : Product Name
		try {
			if (Utils.brand.equals("ATFP")) {
				Assert.assertTrue(utils.isElementPresent(driver, "AT_PDPRVProductName",
						"People like you also viewed : Product Name not showing up for People like you also viewed"));
				Reporter.log(Constants.DELIMITER + Constants.PASS
						+ "| People like you also viewed : Product Name showing up for People like you also viewed");
			} else if (Utils.brand.equals("LOFP")) {
				Assert.assertTrue(utils.isElementPresent(driver, "LO_PDPRVProductName",
						"People like you also viewed : Product Name not showing up for People like you also viewed"));
				Reporter.log(Constants.DELIMITER + Constants.PASS
						+ "| People like you also viewed : Product Name showing up for People like you also viewed");
			} else {
				Assert.assertTrue(utils.isElementPresent(driver, "PDPRVProductName",
						"People like you also viewed : Product Name not showing up for People like you also viewed"));
				Reporter.log(Constants.DELIMITER + Constants.PASS
						+ "| People like you also viewed : Product Name showing up for People like you also viewed");
			}
		} catch (AssertionError e) {
			errorBuffer.append(e.getMessage() + "\n");
		}
		// Verification : People like you also viewed : Product Image
		// redirecting to PDP
		try {
			if (Utils.brand.equals("ATFP")) {
				WebElement elProduct = utils.findElementByLocator(driver, "AT_PDPRVProductImage",
						"| May We Suggest | Product Image ");

				elProduct.click();
			} else if (Utils.brand.equals("LOFP")) {
				WebElement e2Product = utils.findElementByLocator(driver, "LO_PDPRVProductImage",
						"| May We Suggest | Product Image ");

				e2Product.click();

			} else {
				WebElement e3Product = utils.findElementByLocator(driver, "PDPRVProductImage",
						"| May We Suggest | Product Image ");

				e3Product.click();
			}

			Utils.waitForPageLoaded(driver);

			addToBag(driver, 0, 0);
			Utils.waitForPageLoaded(driver);

		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL
					+ "| People like you also viewed : Product Image not redirecting to PDP");
			errorBuffer.append(e.getMessage() + "\n");
		}

	}

	public synchronized void selectGCPrize(String value) throws Exception {

		utils.isElementsPresent(driver, "GC_Product_Price", "Prices of GC on PDP");

		List<WebElement> gcPrices = utils.findElementsByLocator(driver, "GC_Product_Price", "Prices of GC on PDP");

		for (WebElement element : gcPrices) {

			if (element.getText().contains(value)) {

				element.click();
				break;
			}

		}
	}

	public synchronized void enterGCDetailsOnPDP(String to, String from, String message) throws Exception {

		utils.isElementPresent(driver, "GC_Recepient_To");

		WebElement toElement = utils.findElementByLocator(driver, "GC_Recepient_To", "GC TO not present");
		toElement.sendKeys(to);

		utils.findElementByLocator(driver, "GC_Recepient_From", "GC TO not present").sendKeys(from);

		utils.findElementByLocator(driver, "GC_Recepient_Message", "GC TO not present").sendKeys(message);
	}
}
