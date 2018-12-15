package com.ann.automation.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Placeholder;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.Utils.MobileView;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.PDPPageObject;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * PLP Page contains actions methods to perform PLP page related verifications
 * 
 * @author hmarka
 */
public class PLPPageObject {
	private static WebDriver driver = null;
	static Utils utils = null;
	public XLUtils xlUtils = null;
	HomePageObjects homepage = null;
	PDPPageObject pdpObj1 = null;
	public Map<String, String> filterSelected = new HashMap<String, String>();
	public static TreeSet<Integer> sort = new TreeSet<>();
	public static List<Integer> num = new ArrayList<>();
	public boolean flag;
	//public Logger Log = Logger.getLogger(this.getClass().getSimpleName());

	public PLPPageObject(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
		xlUtils = new XLUtils();
		homepage = new HomePageObjects(driver, brand, browser, device);
	}

	/**
	 * Get the list of mega menus
	 * 
	 * @return Lis<Webelement>
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_MegaMenu() throws Exception {

		return utils.findElementsByLocator(driver, "MainNavigationLinks", "|Mega menu");
	}

	/**
	 * Get the sortby filter element on PLP
	 * 
	 * @return Lis<Webelement>
	 * @throws Exception
	 */
	public synchronized WebElement drpdwn_sortBy() throws Exception {
		WebElement element = null;
		if (utils.device.equals("mobile") && Utils.browser.equals("ios")) {
			// MobileView.switchToNative(driver);
			// ((IOSDriver<WebElement>)driver).context("NATIVE_APP");
			Thread.sleep(3000);
			// element=((IOSDriver<WebElement>)driver).findElementByName("FILTER
			// BY");
			// List<WebElement>
			// textField=utils.findElementsByIOSClassNameLocator(driver,"sortBy_Native_ClassName");
			// for(WebElement el:textField)
			// {
			// if(el.getAttribute("value").toString()!=null &&
			// el.getAttribute("value").toString().equals("Sort By"))
			// {
			// element=el;
			// break;
			// }
			//
			// }
			// element=utils.findElementByLocator(driver, "SortByFilter_Mbl", "|
			// Sory By Filter");
			element = utils.findElementByIOSXpathLocatorAndClick(driver, "sortByXpath");
		} else {
			if (utils.device.equals("mobile") && Utils.browser.equals("android")) {
				WebDriverWait wait = new WebDriverWait(driver, 70000);
				wait.until(ExpectedConditions.visibilityOf(lnk_filterExpand()));
				// utils.scrollToViewElement(driver, lnk_filterExpand());
				utils.scrollDownToElement(driver, lnk_filterExpand());
				element = utils.findElementByAndroidXpathLocator("PLP_SortBy_android");

				wait.until(ExpectedConditions.visibilityOf(element));
				TouchAction a = new TouchAction((MobileDriver) driver);
				a.tap(element, (int) homepage.getTheElementLocations(element, "x-axis") + 50,
						(int) homepage.getTheElementLocations(element, "y-axis") + 200).perform();
				// a.tap(element).perform();
				// element.click();
				MobileView.switchToWebView(driver);
			} else if (!Utils.brand.equals("ATFP"))
				element = utils.findElementByLocator(driver, "SortByFilter", "| Sory By Filter");
			else
				element = utils.findElementByLocator(driver, "PLP_SortByFilter", "| Sory By Filter");
		}
		return element;
	}

	/**
	 * Get the searched keyword value on SRP
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_searched_keyword() throws Exception {
		return utils.findElementByLocator(driver, "lbl_searched_keyword", "searched keyword not found");
	}

	/**
	 * Get the list of left navigations
	 * 
	 * @return Lis<WebElement>
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_leftNavigations() throws Exception {
		return utils.findElementsByLocator(driver, "lst_leftNavigations", "Left navigations not found");
	}

	/**
	 * Get the list of promotions on SRP
	 * 
	 * @return Lis<WebElement>
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_promotions() throws Exception {
		return utils.findElementsByLocator(driver, "lst_promotions", "Left avigations not found");
	}

	/**
	 * Method to check PLP pages for the products, if products found then return
	 * list of products Steps: Navigate to main category and click on subcategory
	 * links one by one Verification 1: Product listing page displayed, Check for
	 * expected products by element locators, if found then return element list
	 * Verification 2: Empty Product listing page displayed, display message and
	 * click on main navigation link and click on next subcategory link Verification
	 * 3: Outfit Product listing page displayed, display message and click on main
	 * navigation link and click on next subcategory link Verification 4: We are
	 * sorry page displayed, display message and click on main navigation link and
	 * click on next subcategory link
	 * 
	 * @param driver
	 * @param checkOneCategory
	 *            - if true then search for expected elements on only one regular,
	 *            sale category(not outfit), if false check on all category
	 * @param expectedElStr
	 *            - Object string from object repository to find in PLP
	 * @param preActionOnPLP
	 *            - null or sortByPriceHighToLow, sortByPriceLowToHigh,
	 *            sortByTopRated string
	 * @throws Exception
	 */
	public synchronized List<WebElement> isElementPresentInPLP(WebDriver driver, String expectedElStr,
			boolean checkOneCategory, String preActionOnPLP) throws Exception {
		try {
			Reporter.log(Constants.DELIMITER + "*************PLP: Locate PLP Elements************");
			Thread.sleep(500);

			String mainNavxPath1 = null, mainNavxPath2 = null, mainNavxPath = null;
			String subNavxPath1 = null, subNavxPath2 = null, subNavxPath = null;
			String subNavigationxPath1 = null, subNavigationxPath2 = null, subNavigationxPath3 = null,
					subNavigationxPath = null;

			Actions action = new Actions(driver);

			List<WebElement> expectedElList = null;

			// Getting list of main navigation links
			List<WebElement> mainNavList = list_MegaMenu();
			// Assert.assertTrue(mainNavList!=null, "Main Navigation not
			// found");

			// Perform click one by one on list of Main Navigation links
			for (int mainNavIndex = 1; mainNavIndex <= mainNavList.size(); mainNavIndex++) {

				mainNavxPath1 = xlUtils.getXpathFromXML("MainNavxPath1", Constants.ENVIRONMENT);
				mainNavxPath2 = xlUtils.getXpathFromXML("MainNavxPath2", Constants.ENVIRONMENT);
				mainNavxPath = mainNavxPath1 + mainNavIndex + mainNavxPath2;

				// Click on super category link
				Reporter.log(Constants.DELIMITER + ">>>>Super Category Name: "
						+ utils.findElementByXPath(mainNavxPath, driver).getText());
				// utils.findElementByXPath(mainNavxPath, driver).click();

				// Getting xPath to click on sub category link
				subNavxPath1 = xlUtils.getXpathFromXML("SubNavXpath1", Constants.ENVIRONMENT);
				subNavxPath2 = xlUtils.getXpathFromXML("SubNavXpath2", Constants.ENVIRONMENT);
				subNavxPath = subNavxPath1 + mainNavIndex + subNavxPath2;

				// Getting list of all the subcategory lies under current super
				// category
				List<WebElement> subNavList = driver.findElements(By.xpath(subNavxPath));

				// Perform click one by one on list of SubNavigation links
				for (int subNavIndex = 1; subNavIndex <= subNavList.size(); subNavIndex++) {

					// Get xPath of subnavigation links
					subNavigationxPath1 = xlUtils.getXpathFromXML("SubNavigation1", Constants.ENVIRONMENT);
					subNavigationxPath2 = xlUtils.getXpathFromXML("SubNavigation2", Constants.ENVIRONMENT);
					subNavigationxPath3 = xlUtils.getXpathFromXML("SubNavigation3", Constants.ENVIRONMENT);
					subNavigationxPath = subNavigationxPath1 + mainNavIndex + subNavigationxPath2 + subNavIndex
							+ subNavigationxPath3;

					// Perform hover on Super category link to click on
					// available sub category
					// (Sometimes when we are on subcategory then its shows
					// different supercategory highlighted)
					// Click on Sub category link, to navigate to PLP page
					Reporter.log(Constants.DELIMITER + ">>>Sub Category Name: "
							+ utils.findElementByXPath(subNavigationxPath, driver).getText());
					utils.findElementByXPathAndClick(subNavigationxPath, driver);

					/*
					 * Verification : Check if PLP has list of products, then search for expected
					 * elements on PLP, if found then return list Verification : Check if PLP has
					 * outfit category, then click on main navigation link and then click next
					 * available sub category link
					 */
					if (utils.isElementPresent(driver, "PLPContainItems")) {

						String locator = xlUtils.getXpathFromXML("PLPItemsLiXPath", Constants.ENVIRONMENT);
						if (driver.findElements(By.xpath(locator)).size() != 0) {

							// Get list of all available products on PLP
							Reporter.log(Constants.DELIMITER + "No. of products available on the PLP page: "
									+ driver.findElements(By.xpath(locator)).size());

							utils.scrollDownByxPostion(driver, 200);

							/*
							 * Validation Check | If any action needs to perform on PLP before returning
							 * list of expected elements
							 */
							if (preActionOnPLP != null) {
								// Method call to perform action on PLP i.e.
								// Sorting, Apply filters
								performActionOnPLP(driver, preActionOnPLP);
							}

							/*
							 * Check if PLP has expected list of elements, if found then return list of
							 * elements else break
							 */
							boolean isExpElementPresent = utils.isElementPresent(driver, expectedElStr);

							if (isExpElementPresent) {

								expectedElList = utils.findElementsByLocator(driver, expectedElStr,
										"| Expected element not found on PLP");
								return expectedElList;
							} /*
							 * else{ break; }
							 */
						}

					} else if (utils.isElementPresent(driver, "PLPisOutfitCategory")) {
						Reporter.log(Constants.DELIMITER + "Outfit Category page");
						utils.findElementByXPathAndClick(mainNavxPath, driver);
						checkOneCategory = false;
						continue;
					}

					// Validation check to check the expected element on one
					// category or all category
					if (checkOneCategory == true) {
						break;
					}
				}
				// Validation check to check the expected element on one
				// category or all category
				if (checkOneCategory == true) {
					break;
				}
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Navigation over PLP", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Navigation over PLP", driver);
		}
		return null;
	}

	/**
	 * Method to check NFP PLP pages(i.e. Sale, Final sale) for the products, if
	 * products found the return list of products Steps: Navigate to main category
	 * and click on subcategory links one by one Verification 1: Product listing
	 * page displayed, Check for expected products by element locators, if found
	 * then return element list Verification 2: Empty Product listing page
	 * displayed, display message and click on main navigation link and click on
	 * next subcategory link Verification 3: Outfit Product listing page displayed,
	 * display message and click on main navigation link and click on next
	 * subcategory link Verification 4: We are sorry page displayed, display message
	 * and click on main navigation link and click on next subcategory link
	 * 
	 * @param driver
	 * @param checkOneCategory
	 *            - if true then search for expected elements on only one regular,
	 *            sale category(not outfit), if false check on all category
	 * @param expectedElStr
	 *            - Expected Element String
	 * @param preActionOnPLP
	 *            - Preaction string on PLP
	 * @throws Exception
	 */
	public synchronized List<WebElement> isElementPresentInNFPPLP(WebDriver driver, String expectedElStr,
			boolean checkOneCategory, String preActionOnPLP) throws Exception {
		try {
			Reporter.log(Constants.DELIMITER + "*************PLP: Locate PLP Elements************");
			Thread.sleep(500);

			String subNavxPath1 = null, subNavxPath2 = null, subNavxPath = null;
			String subNavigationxPath1 = null, subNavigationxPath2 = null, subNavigationxPath3 = null,
					subNavigationxPath = null;

			Actions action = new Actions(driver);

			List<WebElement> expectedElList = null;

			// Getting list of main navigation links
			List<WebElement> mainNavList = list_MegaMenu();
			Assert.assertTrue(mainNavList != null, "Main Navigation not found");

			for (WebElement elMainNav : mainNavList) {

				// if elMainNav contains 'Sale' category then proceed to
				// validation
				if (elMainNav.getText().equalsIgnoreCase("SALE")) {

					// Getting position of NFP category
					String mainNavXPath = utils.getAbsoluteXPath(elMainNav, driver);
					String catIndexValue = StringUtils.substringBetween(mainNavXPath, "div[1]/div[", "]/a");

					// Click on super category link
					Reporter.log(Constants.DELIMITER + ">>>>Super Category Name: "
							+ utils.findElementByXPath(mainNavXPath, driver).getText());
					// elMainNav.click();

					// Getting xPath to click on sub category link
					subNavxPath1 = xlUtils.getXpathFromXML("SubNavXpath1", Constants.ENVIRONMENT);
					subNavxPath2 = xlUtils.getXpathFromXML("SubNavXpath2", Constants.ENVIRONMENT);
					subNavxPath = subNavxPath1 + catIndexValue + subNavxPath2;

					// Getting list of all the subcategory lies under current
					// super category
					List<WebElement> subNavList = driver.findElements(By.xpath(subNavxPath));

					// Perform click one by one on list of SubNavigation links
					for (int subNavIndex = 1; subNavIndex <= subNavList.size(); subNavIndex++) {

						// Get xPath of subnavigation links
						subNavigationxPath1 = xlUtils.getXpathFromXML("SubNavigation1", Constants.ENVIRONMENT);
						subNavigationxPath2 = xlUtils.getXpathFromXML("SubNavigation2", Constants.ENVIRONMENT);
						subNavigationxPath3 = xlUtils.getXpathFromXML("SubNavigation3", Constants.ENVIRONMENT);
						subNavigationxPath = subNavigationxPath1 + catIndexValue + subNavigationxPath2 + subNavIndex
								+ subNavigationxPath3;

						// Perform hover on Super category link to click on
						// available sub category
						// (Sometimes when we are on subcategory then its shows
						// different supercategory highlighted)
						// action.moveToElement(driver.findElement(By.xpath(mainNavXPath))).build().perform();
						// Thread.sleep(2000);

						// Click on Sub category link, to navigate to PLP page
						Reporter.log(Constants.DELIMITER + ">>>Sub Category Name: "
								+ utils.findElementByXPath(subNavigationxPath, driver).getText());
						utils.findElementByXPathAndClick(subNavigationxPath, driver);

						/*
						 * Verification : Check if PLP has list of products, then search for expected
						 * elements on PLP, if found then return list Verification : Check if PLP has
						 * outfit category, then click on main navigation link and then click next
						 * available sub category link
						 */
						if (utils.isElementPresent(driver, "PLPContainItems")) {

							String locator = xlUtils.getXpathFromXML("PLPItemsLiXPath", Constants.ENVIRONMENT);
							if (driver.findElements(By.xpath(locator)).size() != 0) {

								// Get list of all available products on PLP
								Reporter.log(Constants.DELIMITER + "No. of products available on the PLP page: "
										+ driver.findElements(By.xpath(locator)).size());

								/*
								 * Validation Check | If any action needs to perform on PLP before returning
								 * list of expected elements
								 */
								if (preActionOnPLP != null) {
									// Method call to perform action on PLP i.e.
									// Sorting, Apply filters
									performActionOnPLP(driver, preActionOnPLP);
								}

								/*
								 * Check if PLP has expected list of elements, if found then return list of
								 * elements else break
								 */
								boolean isExpElementPresent = utils.isElementPresent(driver, expectedElStr);

								if (isExpElementPresent) {

									expectedElList = utils.findElementsByLocator(driver, expectedElStr,
											"| Expected element not found on PLP");
									return expectedElList;
								} /*
								 * else{ break; }
								 */
							}

						} else if (utils.isElementPresent(driver, "PLPisOutfitCategory")) {
							Reporter.log(Constants.DELIMITER + "Outfit Category page");
							utils.findElementByXPathAndClick(mainNavXPath, driver);
							checkOneCategory = false;
							continue;
						}

						// Validation check to check the expected element on one
						// category or all category
						if (checkOneCategory == true) {
							break;
						}
					}
					// Validation check to check the expected element on one
					// category or all category
					if (checkOneCategory == true) {
						break;
					}
				}
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Navigation over PLP", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Navigation over PLP", driver);
		}
		return null;
	}

	public synchronized List<Integer> VerifyLeftNavCount() throws Exception {
		int productCountbeforeAppliedFilter = 0;
		List<Integer> productTally = null;
		try {
			// WebElement leftNav = utils.findElementByLocator(driver,
			// "SRP_leftNav_Result", "| Left Nav on SRP");
			List<WebElement> leftNav = utils.findElementsByLocator(driver, "SRP_leftNav_Result", "| Left Nav on SRP");
			productTally = new ArrayList<>();
			for (int i = 0; i < leftNav.size(); i++) {
				String filtername = leftNav.get(i).getText();
				int startIndex = filtername.indexOf("(");
				int lastIndex = filtername.length();
				String filtercount = filtername.substring(startIndex, lastIndex);

				if (filtercount.startsWith("(") && filtercount.endsWith(")")) {
					String ItemCount = filtercount.substring(filtercount.indexOf("(") + 1, filtercount.length() - 1);
					// Verify Count before Filter is applied
					productCountbeforeAppliedFilter = Integer.parseInt(ItemCount);
					productTally.add(productCountbeforeAppliedFilter);

				}

			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Verify Left Nav Count", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Verify Left Nav Count", driver);
		}
		return productTally;
	}

	/**
	 * Method to perform actions on PLP based on the parameters getting passed per
	 * requirement Switch Statement: case "sortByPriceHighToLow" : Select Sort by
	 * Price High to Low case "sortByPriceLowToHigh" : Select Sort by Price Low to
	 * High case "sortByTopRated" : Select Sort By : Top Rated
	 * 
	 * @param driver
	 * @param preActionOnPLP
	 * @throws Exception
	 */
	public synchronized void performActionOnPLP(WebDriver driver, String preActionOnPLP) throws Exception {
		try {

			WebElement element;

			// Swith statement to perform action
			switch (preActionOnPLP) {

			case "sortByPriceHighToLow": {
				if (Utils.device.equals("desktop")) {
					drpdwn_sortBy().click();
					utils.findElementByLocator(driver, "PLP_sortByHighToLow", "Price High to Low not present").click();
				} else {
					drpdwn_sortBy();
					Thread.sleep(2000);
					if (utils.device.equals("mobile") && Utils.browser.equals("ios")) {
						utils.findElementByIOSXpathLocatorAndSelect(driver, "PickerWheel", "Price High to Low");
					} else {
						MobileView.switchToNative(driver);
						List<WebElement> sortOptionList = utils
								.findElementByAndroidClassNameLocator("PLP_SortByOptions");

						for (WebElement sortBy : sortOptionList) {
							if (sortBy.getAttribute("text").equals("Price High to Low")) {
								sortBy.click();
								Utils.waitForPageLoaded(driver);
								MobileView.switchToWebView(driver);
								break;
							}
						}
					}
				}
				// Click on Sort by Filter
				Utils.waitForPageLoaded(driver);
				Reporter.log(Constants.DELIMITER + "| Sort By : Price High to Low Applied on PLP");
				break;
			}
			case "sortByPriceLowToHigh": {
				if (Utils.device.equals("desktop")) {
					// drpdwn_sortBy().click();
					utils.findElementByLocator(driver, "PLP_sortByLowToHigh", "Price Low to High not present").click();
				} else {

					drpdwn_sortBy();
					Thread.sleep(2000);
					if (utils.device.equals("mobile") && Utils.browser.equals("ios")) {
						utils.findElementByIOSXpathLocatorAndSelect(driver, "PickerWheel", "Price Low to High");
					} else {
						MobileView.switchToNative(driver);
						List<WebElement> sortOptionList = utils
								.findElementByAndroidClassNameLocator("PLP_SortByOptions");
						for (WebElement sortBy : sortOptionList) {
							if (sortBy.getAttribute("text").equals("Price Low to High")) {
								sortBy.click();
								Utils.waitForPageLoaded(driver);
								MobileView.switchToWebView(driver);
								break;
							}
						}
					}
				}
				// Click on Sort by Filter
				Utils.waitForPageLoaded(driver);
				Reporter.log(Constants.DELIMITER + "| Sort By : Price Low to High Applied on PLP");
				break;
			}
			case "sortByTopRated": {
				if (Utils.device.equals("desktop"))
					drpdwn_sortBy().click();
				else {
					// Click on Sort by Filter
					drpdwn_sortBy();
					utils.findElementByIOSXpathLocatorAndSelect(driver, "PickerWheel", "Top Rated");
				}
				Reporter.log(Constants.DELIMITER + "| Sort By : Top Rated Applied on PLP");
				break;
			}

			case "Apply":

				if (Utils.isBrandFullPrice() && Utils.isDeviceMobile())
					element = utils.findElementByLocator(driver, "MBL_PLP_FilterApply", "| Filter Apply Button");

				else
					element = utils.findElementByLocator(driver, "PLP_FilterApply", "| Filter Apply Button");

				utils.clickWebElement(driver, element, "Filter Apply Button Not Found");
				Thread.sleep(5000);
				ReporterLog.pass("Clicked on Apply button");
				break;

			case "FilterApplyMobile":
				element = utils.findElementByLocator(driver, "PLP_FilterApply", "| Filter Apply Button");
				utils.clickWebElement(driver, element, "Filter Apply Button Not Found");
				Utils.waitForPageLoaded(driver);
				ReporterLog.pass("Clicked on Apply button");
				break;

			}

			preActionOnPLP = null;

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Navigation over PLP", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Navigation over PLP", driver);
		}
	}

	/**
	 * Get the Product link under TALL->any sub category
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_Product() throws Exception {
		return utils.findElementByLocator(driver, "PLP_lnk_Product_PDP", "| PDP of Tall Category product");
	}

	/**
	 * Get the Apply Button Text
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_ApplyButtonTextMobile() throws Exception {
		return utils.findElementByLocator(driver, "PLP_FilterApply", "| Apply button");
	}

	public synchronized void click_lnk_Product() throws Exception {
		utils.clickWebElement(driver, lnk_PLP_Product(), "PDP of Tall Category product link not found");
	}

	/**
	 * Get the Color link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_ColorFilter() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_PLP_ColorFilter", "| Color Filter");

		return utils.findElementByLocator(driver, "PLP_ColorFilter", "| Color Filter");
	}

	public synchronized void VerifyExpandFilterMenu() throws Exception {
		WebElement filter = utils.findElementByLocator(driver, "PLP_ColorFilter", "|PLP filters");
		utils.clickWebElement(driver, filter, "Unable to click Color Filter");
		// Verify Filter dropdown expanded
		if (filter.getAttribute("aria-expanded").equals("true")) {
			ReporterLog.pass("Filter dropdown expanded");
			specificFilterProductCount("red", "color");
			utils.clickWebElement(driver, btn_ApplyButtonTextMobile(), "Unable to click Apply button");
			if (filter.getAttribute("aria-expanded").equals("false")) {
				ReporterLog.pass("Filter dropdown collapsed");
			}

		}

	}

	/**
	 * Click on the Color filter
	 * 
	 * @throws Exception
	 */
	public synchronized void click_lnk_PLP_ColorFilter() throws Exception {

		utils.clickWebElement(driver, lnk_PLP_ColorFilter(), "Color Filter Not Found");
	}

	/**
	 * Get the Size link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_SizeFilter() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_PLP_ColorFilter", "| Color Filter");

		return utils.findElementByLocator(driver, "PLP_SizeFilter", "| Size Filter");
	}

	/**
	 * Click on the Color filter
	 * 
	 * @throws Exception
	 */
	public synchronized void click_lnk_PLP_SizeFilter() throws Exception {
		utils.clickWebElement(driver, lnk_PLP_SizeFilter(), "Size Filter Not Found");
		// lnk_PLP_SizeFilter().click();
	}

	/**
	 * Get the More link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_MoreFilter() throws Exception {
		return utils.findElementByLocator(driver, "PLP_MoreFilter", "| More Filter");
	}

	/**
	 * Click on the More filter
	 * 
	 * @throws Exception
	 */
	public synchronized void click_lnk_PLP_MoreFilter() throws Exception {
		utils.clickWebElement(driver, lnk_PLP_MoreFilter(), "More Filter Not Found");
	}

	/**
	 * Get the Sleeve Length link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_SleeveLengthFilter() throws Exception {
		return utils.findElementByLocator(driver, "PLP_MoreFilter", "| Sleeve Length Filter");
	}

	/**
	 * Click on the Sleeve Length filter
	 * 
	 * @throws Exception
	 */
	public synchronized void click_lnk_PLP_SleeveLengthFilter() throws Exception {
		utils.clickWebElement(driver, lnk_PLP_SleeveLengthFilter(), "No Sleeve Length Filter Not Found");
	}

	/**
	 * Get the Sleeve Length link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_PriceFilter() throws Exception {
		return utils.findElementByLocator(driver, "PLP_PriceFilter", "| Price Filter");
	}

	/**
	 * Click on the Sleeve Length filter
	 * 
	 * @throws Exception
	 */
	public synchronized void click_lnk_PLP_PriceFilter() throws Exception {
		utils.clickWebElement(driver, lnk_PLP_PriceFilter(), "Price Filter Not Found");
	}

	/**
	 * Get the Filters Selected Cross Icon
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_PLP_FilterSelectedCrossIcon() throws Exception {

		return utils.findElementsByLocator(driver, "PLP_FilterSelected_CrossIcon", "| Filters Cross Icon Not Present");
	}

	/**
	 * Get the Filters Selected
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_PLP_FilterSelected() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementsByLocator(driver, "PLP_MB_ApplyFilter", "| Filters Not Present");
		else
			return utils.findElementsByLocator(driver, "PLP_FilterSelected", "| Filters Not Present");
	}

	/**
	 * @Prateek Method returns the total filters applied on PLP page for both
	 *          desktop and mobile as well as for FP and Factory sites
	 * @return int
	 * @throws Exception
	 */
	public synchronized int getCountOfFiltersApplied() throws Exception {

		ReporterLog.log("Getting count of total filters selected...");
		WebElement filterElement = null;
		String filterCountValue = null;

		if (Utils.isDeviceMobile()) {

			click_lnk_FilterExpand();

			try {

				filterElement = utils.findElementByLocator(driver, "PLP_MB_ApplyFilter", "| Filters Not Present");
			} catch (Exception e) {

				CustomException.throwExceptionError(e, "PLP", "Not able to get filters applied on PLP", driver);
				e.printStackTrace();
			}

			if (Utils.isBrandFullPrice()) {

				ReporterLog.log("Getting count of total filters selected on full price site on mobile...");
				filterCountValue = filterElement.getAttribute("value");

			} else {

				ReporterLog.log("Getting count of total filters selected on factory site on mobile...");
				filterCountValue = filterElement.getText();
			}

			if (filterCountValue.length() < 6)
				return 0;
			else
				return Integer.parseInt(filterCountValue.substring(7, 8));

		} else {

			ReporterLog.log("Getting count of total filters selected on desktop...");
			return lnk_PLP_FilterSelected().size();
		}
	}

	/**
	 * Get the Apply Filter control
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_FilterApplied() throws Exception {
		return utils.findElementByLocator(driver, "PLP_MB_ApplyFilter", "| Filters Not Present");
	}

	/**
	 * Get the Product Tally
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_ProductTally() throws Exception {
		if (Utils.device.equals("desktop"))
			return utils.findElementByLocator(driver, "PLP_ProductTally", "| Product Tally Not Present");
		else
			return utils.findElementByLocator(driver, "PLP_MB_CountTally", "| Product Tally Not Present");
	}

	/**
	 * Get the Item count
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_ItemCount() throws Exception {
		return utils.findElementByLocator(driver, "PLP_ItemCount", "| Item count Not Present");
	}

	/**
	 * Get the Product Tally Circle
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_PLP_ProductTallyCircle() throws Exception {
		return utils.findElementByLocator(driver, "PLP_ProductTallyCircle", "| Product Tally Not Present");
	}

	public synchronized WebElement FilterResult() throws Exception {
		return utils.findElementByLocator(driver, "PLP_FilterResult", "| Filter Results");
	}

	/**
	 * @description: Navigate to PLP Page
	 * @param driver
	 * @param mainMenuCategory
	 * @param SubMenuCategory
	 * @throws Exception
	 */
	public synchronized void navigateToPLP(WebDriver driver, String mainMenuCategory, String SubMenuCategory)
			throws Exception {
		try {
			if (Utils.device.equals("mobile")) {

				utils.isElementPresent(driver, "MB_HamBurger");
				utils.isElementClickable(driver, "MB_HamBurger");
				// utils.waitForElementToBeClickable(driver, "MB_HamBurger", "hamburger and
				// homepage not loaded");
				homepage.click_lnk_hamburger();
			}
			Utils.waitForPageLoaded(driver);

			if (Utils.brand.equals("LGFP") && mainMenuCategory.equalsIgnoreCase("Clothing")) {
				mainMenuCategory = "CLOTHING";
			}

			String mainMenuXpath1 = null;
			if (Utils.isDeviceMobile() && (Utils.brand.equals("ATFP") || Utils.brand.equals("L0FP")))
				mainMenuXpath1 = xlUtils.getObjectFromXMLString("MBL_PLP_MainMenuXpath1", Constants.ENVIRONMENT);
			else
				mainMenuXpath1 = xlUtils.getObjectFromXMLString("PLP_MainMenuXpath1", Constants.ENVIRONMENT);

			String mainMenuXpath2 = xlUtils.getObjectFromXMLString("PLP_MainMenuXpath2", Constants.ENVIRONMENT);
			WebElement mainMenuXpath = driver.findElement(By.xpath(mainMenuXpath1 + mainMenuCategory + mainMenuXpath2));

			utils.waitForElementToBeVisible(driver, mainMenuXpath);
			utils.waitForElementPresent(mainMenuXpath, driver);
			if (SubMenuCategory == null && Utils.device.equals("desktop")) {
				utils.clickWebElement(driver, mainMenuXpath, "");
				//Log.info("Clicked on element" + mainMenuCategory);
			} else {
				if (SubMenuCategory != null && Utils.device.equals("desktop")) {
					utils.mouseHoverScript(driver, mainMenuXpath);
					//Log.info("Hover to element " + mainMenuCategory);
				}
				if (SubMenuCategory != null && Utils.device.equals("mobile")) {
					mainMenuXpath.click();
					//Log.info("Clicked on element" + mainMenuCategory);
				}
				if (SubMenuCategory == null && Utils.device.equals("mobile")) {
					//Log.info("Sub Category not entered");
					Assert.fail("Sub Category not entered");
				}

				Utils.waitForPageLoaded(driver);

				String subMenuXpath1 = null;

				if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
					subMenuXpath1 = xlUtils.getObjectFromXMLString("MBL_PLP_subMenuXpath1", Constants.ENVIRONMENT);
				else
					subMenuXpath1 = xlUtils.getObjectFromXMLString("PLP_subMenuXpath1", Constants.ENVIRONMENT);

				String subMenuXpath2 = xlUtils.getObjectFromXMLString("PLP_subMenuXpath2", Constants.ENVIRONMENT);
				WebElement subMenuXpath = driver.findElement(By.xpath(subMenuXpath1 + SubMenuCategory + subMenuXpath2));
				utils.clickWebElement(driver, subMenuXpath, "Not able to click on sub category");
				// subMenuXpath.click();

				Thread.sleep(1000);
				Utils.waitForPageLoaded(driver);

				//Log.info("Navigated to PLP page" + SubMenuCategory);

				Utils.waitForPageLoaded(driver);
				ReporterLog.actionMsg("Checking that we ae navigated to correct PLP Page");
				if (!SubMenuCategory.equalsIgnoreCase("Most Loved Looks") && !Utils.brand.equalsIgnoreCase("LGFP")) {
					Assert.assertTrue(utils.isElementVisible(driver, "PLP_PageHeading", "Page Not Navigated"));
					Assert.assertTrue(
							txt_PageHeading().getText().toLowerCase().contains(SubMenuCategory.toLowerCase()));
				}
				//Log.info("Navigated to correct PLP Page");
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			throw (e);
		} catch (AssertionError e) {
			throw (e);
		}
		Utils.waitForPageLoaded(driver);
		Thread.sleep(3000);
	}

	public synchronized void addProductFromQuickShopModal(WebDriver driver, PLPPageObject plpPage, com.ann.automation.pages.PDPPageObject pdpPage){
		try{
			int size = plpPage.list_Products().size();
			if(size != 0){
				//checking for each product present in the PLP page.
				for(int i=0;i<size;i++){
					WebElement elProduct = plpPage.list_Products().get(i);
					if(!elProduct.getAttribute("class").contains("slot")){
						// Verification 1: Quick Shop
						utils.mouseHoverJScript(driver, elProduct);
						//Log.info("Hovering over the Product");
						Thread.sleep(4000);
						WebElement showNowxpath=utils.findElementByLocator(driver, "PLP_shopNow", "QuickShop not found");

						Assert.assertTrue(utils.isElementPresent(driver, "PLP_shopNow"),"Quick Shop Not Available");
						//Log.info("Quick Shop is present");

						utils.clickWebElement(driver, showNowxpath, "Not able to click on web Element");
						//Log.info("Clicked on the Quick Shop");

						//Log.info("| Quick Shop link displayed and clicked from PLP");

						Thread.sleep(6000);
						pdpPage.selectSize();
						Thread.sleep(6000);
						plpPage.QS_AddToBag();
					}
					break;
				}
			}
			//Log.info("Product Added to bag through quickshop successfully");
			Utils.waitForPageLoaded(driver);
			//Log.info("Item Successfully Added to Bag & shopping bag page opened");
			Thread.sleep(3000);
		}catch(Exception e){

		}
	}

	public synchronized WebElement lnk_LeftNavSubCat(WebDriver driver, String leftNavLNK) throws Exception {
		WebElement leftNavLink = null;
		try {
			if (utils.isElementPresent(driver, "PLP_LeftNavSubCatagory_TypesList"))
				leftNavLink = utils.findElementsByLocator(driver, "PLP_LeftNavSubCatagory_TypesList",
						"Sub Category Mini List not found").get(0);
			else
				return null;
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Not Able to click Subcategory", driver);

		}
		return leftNavLink;
	}

	public synchronized boolean accordionStatus(WebDriver driver, String leftNavLNK) throws Exception {
		WebElement leftNavsubLink = null;
		boolean flag = false;
		List<WebElement> leftNavSubCat;
		try {
			String leftNavXpath = xlUtils.getObjectFromXMLString("PLP_LeftNavLNK", Constants.ENVIRONMENT);
			String mainMenuXpath2 = xlUtils.getObjectFromXMLString("PLP_subMenuXpath2", Constants.ENVIRONMENT);
			// accordian status
			String leftNavSubMenu = xlUtils.getObjectFromXMLString("PLP_LeftNavSubMenu", Constants.ENVIRONMENT);
			leftNavsubLink = utils.findElementByXPath(leftNavXpath + leftNavLNK + mainMenuXpath2 + leftNavSubMenu,
					driver);
			// sub category items status
			String leftNavSubList = xlUtils.getObjectFromXMLString("PLP_LeftNavSubList", Constants.ENVIRONMENT);
			leftNavSubCat = driver.findElements(By.xpath(leftNavXpath + leftNavLNK + mainMenuXpath2 + leftNavSubList));
			if (leftNavsubLink.getAttribute("class").contains("active") && leftNavSubCat.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Navigation over PLP", driver);
		}
		return flag;
	}

	/**
	 * Method to get To Price Maximum Price Filter Bar
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_quickShopProductName() throws Exception {

		return utils.findElementByLocator(driver, "PLP_QSProductName", "|Quickshop product name ");
	}

	/**
	 * Method to get To Price Maximum Price Filter Bar
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_ProductTilesPrice() throws Exception {
		if (utils.brand.equals("ATF") || utils.brand.equals("LO"))
			return utils.findElementsByLocator(driver, "PLP_ProductPrice", "| PLP: Price not present");
		else {

			return driver.findElements(By.xpath(".//div[@class='product-wrap']//*[contains(@class,'price')]"));
		}
	}

	/**
	 * Method to get To Price Maximum Price Filter Bar
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_ProductPrice_Complete() throws Exception {

		return utils.findElementsByLocator(driver, "PLP_ProductPrice", "| PLP: Price not present");
	}

	/**
	 * Method to get To sale price on the plp page
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_ProductTilesSalePrice() throws Exception {

		return utils.findElementsByLocator(driver, "PLP_SalePrice", "| PLP: Sale Price not present");
	}

	/**
	 * Method to get Product Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_ProductTilesName() throws Exception {

		return utils.findElementsByLocator(driver, "PLP_ProductName", "| PLP: Product Name not present");
	}

	/**
	 * Method to get Product Name
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_ColorSwatch() throws Exception {

		return utils.findElementsByLocator(driver, "PLP_colorswatch", "| PLP: Product Color Swatch not present");
	}

	/**
	 * Method to get back to top web ele located bottom of the page.
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_backToTop() throws Exception {

		return utils.findElementByLocator(driver, "PLP_BackToTop", "| PLP:Back to topss not present");
	}

	/**
	 * Method to get From Price from Price Filter.
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_FromPriceFilter() throws Exception {

		return utils.findElementByLocator(driver, "PLP_FromPriceFilter", "| PLP:Form Price range not present");
	}

	/**
	 * Method to get To Price from Price Filter
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_ToPriceFilter() throws Exception {

		return utils.findElementByLocator(driver, "PLP_ToPriceFilter", "| PLP:To Price range not present");
	}

	/**
	 * Method to get To Price Minimum Price Filter Bar
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_MinimumPriceFilterBar() throws Exception {

		return utils.findElementByLocator(driver, "PLP_MinPrice", "| PLP:Minimum Price Filter Bar not present");
	}

	/**
	 * Method to get To Price Maximum Price Filter Bar
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_MaximumPriceFilterBar() throws Exception {

		return utils.findElementByLocator(driver, "PLP_MaxPrice", "| PLP:Price Maximum Price Filter Bar not present");
	}

	/**
	 * Method to get To Page Heading
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_PageHeading() throws Exception {

		return utils.findElementByLocator(driver, "PLP_PageHeading", "| PLP:Page Heading");
	}

	/**
	 * Get the product list
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_Products() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementsByLocator(driver, "MBL_PLP_ProductList", "|PLP: Product not found");

		return utils.findElementsByLocator(driver, "PLP_ProductList", "|PLP: Product not found");
	}

	/**
	 * @Prateek
	 * 
	 * 			Method to click product, which is not a slot, by index
	 * 
	 * @param index
	 *            - Index of product, starting from 0, including the slots
	 * @throws Exception
	 */
	public synchronized void clickPLPProductByIndex(int index) throws Exception {

		ReporterLog.log("Clicking on product by index while ignoring slots...");

		List<WebElement> productElementList = list_Products();
		int size = productElementList.size() - 1;

		for (int i = index; i < size; i++) {

			WebElement productElement = productElementList.get(i);

			// checking to see if the selected proeduct is a slot or not
			if (productElement.findElements(By.xpath("./*[contains(@class, 'slot active')]")).isEmpty()) {

				ReporterLog.log("Clicking on product with index = " + index);
				productElement.click();
				break;

			} else {

				ReporterLog.log("Selected product with index = " + i
						+ " is a slot and cannot be clicked. Moving to next product instead");
			}
		}
	}

	/**
	 * Get the product list of final sale
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_FinalSaleProducts() throws Exception {

		return utils.findElementsByLocator(driver, "PLP_finalSale", "|PLP: Final sale Product not found");
	}

	/**
	 * Filter Expand Element on Mobile
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_filterExpand() throws Exception {

		return utils.findElementByLocator(driver, "PLP_MB_FilterExpand", "|PLP: Cannot find the filter");
	}

	/**
	 * Click on Filter Expand Element on Mobile
	 * 
	 * @throws Exception
	 */
	public synchronized void click_lnk_FilterExpand() throws Exception {
		utils.clickWebElement(driver, lnk_filterExpand(), "Filter Expand Not Found");

	}

	/**
	 * Filter Expand Element on Mobile
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_clearAll_Mobile() throws Exception {

		return utils.findElementByLocator(driver, "PLP_MB_ClearAll", "|PLP: Cannot find the Clear All button");
	}

	/**
	 * Click on Filter Expand Element on Mobile
	 * 
	 * @throws Exception
	 */
	public synchronized void click_lnk_lnk_clearAll_Mobile() throws Exception {
		utils.clickWebElement(driver, lnk_clearAll_Mobile(), "Clear All Not Found");
	}

	/**
	 * Clear All Link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_clearAll() throws Exception {

		if (Utils.isDeviceMobile())
			return utils.findElementByLocator(driver, "PLP_MB_ClearAll", "|PLP: Cannot find the Clear All button");

		return utils.findElementByLocator(driver, "PLP_ClearAll", "|PLP: Cannot find the Clear All button");
	}

	/**
	 * Click on Clear All link
	 * 
	 * @throws Exception
	 */
	public synchronized void click_lnk_clearAll() throws Exception {

		if (Utils.device.equals("mobile")) {

			click_lnk_FilterExpand();
			Thread.sleep(3000);
		}

		utils.clickWebElement(driver, lnk_clearAll(), "Clear All Not Found");

	}

	/**
	 * @param driver
	 * @param typeOfFilter
	 * @param filterDescription
	 * @throws Exception
	 */
	public synchronized void selectSpecificFilters(WebDriver driver, String typeOfFilter, String filterDescription)
			throws Exception {
		WebElement element = null;
		try {
			switch (typeOfFilter) {
			case "Color":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on the filter
				click_lnk_PLP_ColorFilter();
				Utils.waitForPageLoaded(driver);

				String color = "";
				if (Utils.isDeviceMobile() && Utils.isBrandFullPrice()) {
					color = xlUtils.getObjectFromXMLString("MBL_PLP_SelectColor_1", Constants.ENVIRONMENT)
							+ filterDescription
							+ xlUtils.getObjectFromXMLString("PLP_SelectColor_2", Constants.ENVIRONMENT);
				} else {
					color = xlUtils.getObjectFromXMLString("PLP_SelectColor_1", Constants.ENVIRONMENT)
							+ filterDescription
							+ xlUtils.getObjectFromXMLString("PLP_SelectColor_2", Constants.ENVIRONMENT);
				}
				element = driver.findElement(By.xpath(color));

				// clicking on the specified filter
				utils.clickWebElement(driver, element, "Not able to click on color filter");
				ReporterLog.pass("Applied Filter:" + typeOfFilter);
				break;

			case "Size":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on the filter

				click_lnk_PLP_SizeFilter();
				Utils.waitForPageLoaded(driver);
				String size = "";

				if (Utils.isDeviceMobile() && Utils.isBrandFullPrice()) {
					size = xlUtils.getObjectFromXMLString("MBL_PLP_SelectSize", Constants.ENVIRONMENT)
							+ filterDescription
							+ xlUtils.getObjectFromXMLString("MBL_PLP_SelectSize_2", Constants.ENVIRONMENT);
				} else {
					size = xlUtils.getObjectFromXMLString("PLP_SelectSize", Constants.ENVIRONMENT) + filterDescription
							+ xlUtils.getObjectFromXMLString("PLP_SelectSize_2", Constants.ENVIRONMENT);
				}
				element = driver.findElement(By.xpath(size));

				utils.clickWebElement(driver, element, "Not able to click on size filter");
				ReporterLog.pass("Applied Filter:" + typeOfFilter);
				break;

			case "Sleeve_Length":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on the filter

				click_lnk_PLP_SleeveLengthFilter();
				String sleeveLength = xlUtils.getObjectFromXMLString("PLP_SelectSleeveLength", Constants.ENVIRONMENT)
						+ filterDescription + "')]/parent::a";
				element = driver.findElement(By.xpath(sleeveLength));


				utils.clickWebElement(driver, element, "Not able to click on sleeve filter");
				ReporterLog.pass("Applied Filter:" + typeOfFilter);
				break;

			case "NarrowByCategory":

				// clicking on the Narrow by category present in mobile
				element = utils.findElementByLocator(driver, "PLP_NarrowByCategory",
						"Narrow By Category Not Present on page");
				// utils.clickWebElement(driver, element, "Not able to click on Narrow by
				// category");
				element.click();
				break;

			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Select Filter", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Select Filter", driver);
		}
	}

	/**
	 * 
	 * @param type:Filter
	 *            name (if filter type is color then "type" will be Red,green etc
	 *            depend upon the color present)
	 * @param filtertype:type
	 *            of filter
	 * @return: the total product present for that type of filter
	 * @throws Exception
	 */
	public synchronized int specificFilterProductCount(String type, String filtertype) throws Exception {
		WebElement element = null;
		try {
			switch (filtertype) {

			case "color":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				click_lnk_PLP_ColorFilter();
				String colorSelected = xlUtils.getObjectFromXMLString("PLP_SelectColor_1", Constants.ENVIRONMENT) + type
						+ xlUtils.getObjectFromXMLString("PLP_SelectColor_2", Constants.ENVIRONMENT);
				element = driver.findElement(By.xpath(colorSelected));
				break;

			case "size":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				click_lnk_PLP_SizeFilter();
				String size = xlUtils.getObjectFromXMLString("PLP_SelectSize", Constants.ENVIRONMENT) + type
						+ xlUtils.getObjectFromXMLString("PLP_SelectColor_2", Constants.ENVIRONMENT);
				element = driver.findElement(By.xpath(size));
				break;
			}
			if (filtertype.equals("size") && element.getText().replaceAll("[\\s (]+[\\d]+[)]", "").matches("[0-9]+"))
				return Integer.parseInt(
						element.getText().replaceAll("^[\\d \\s]*", "").replaceAll("\\(", "").replaceAll("\\)", ""));
			else
				return Integer.parseInt(element.getText().replaceAll("[^\\d]", ""));

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Filter Count", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Filter Count", driver);
		}
		return 0;
	}

	/**
	 * Get the product name in Quick shop
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_QS_ProductName() throws Exception {
		return utils.findElementByLocator(driver, "QSProductName", "| Quick shop:Product name Not Present");
	}

	/**
	 * Add to bag button | Quick Shop
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_QS_AddToBag() throws Exception {
		return utils.findElementByLocator(driver, "PDP_btn_AddToBag", "| Quick shop:Add to Button not displayed");
	}

	public synchronized WebElement btn_QS_AddToBag_PLP() throws Exception {
		return utils.findElementByLocator(driver, "PLP_btn_AddToBag", "| Quick shop:Add to Button not displayed");
	}

	/**
	 * Add to bag button | Quick Shop
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized void QS_AddToBag() throws Exception {
		try {

			if (Utils.brand.equals("ATF") || Utils.brand.equals("LO")) {
				btn_QS_AddToBag().click();
			} else {
				btn_QS_AddToBag_PLP().click();
			}
			Reporter.log(Constants.DELIMITER + "| Quick Shop: Click action is perfromed on Add To Bag button");
			if (Utils.device.equals("desktop")) {
				Thread.sleep(2000);
				utils.waitForElementToBeClickable(driver, "PDPAddToBagNotification", "checkout now not clickable");
				utils.findElementByLocator(driver, "PDPAddToBagNotification", "continue shopping not found").click();
				Utils.waitForPageLoaded(driver);

			} else {
				// utils.waitForElementToBeClickable(driver, "PDP_btn_FlyoutViewBag", "checkout
				// now not clickable");
				Thread.sleep(1000);
				System.out.println(new PDPPageObject(driver, Utils.brand, Utils.browser, Utils.device)
				.pdp_btn_AddToBag().getText());
				Assert.assertTrue(new PDPPageObject(driver, Utils.brand, Utils.browser, Utils.device).pdp_btn_AddToBag()
						.getText().equals("ITEM ADDED TO BAG"));
				Thread.sleep(5000);
				// utils.findElementByLocator(driver, "PDP_btn_FlyoutViewBag", "continue
				// shopping not found").click();
			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Navigation over PLP", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Navigation over PLP", driver);
		}
	}

	public synchronized boolean QS_AddToBag_ErrorMessage() throws Exception {

		try {
			flag = false;
			btn_QS_AddToBag().click();
			Reporter.log(Constants.DELIMITER + "| Quick Shop: Click action is perfromed on Add To Bag button");
			Thread.sleep(2000);
			utils.isElementVisible(driver, "PLP_QS_AddBag_Error_message", "Error message is not visible");
			WebElement error_message = utils.findElementByLocator(driver, "PLP_QS_AddBag_Error_message",
					"Error message is not visible");
			Assert.assertEquals(error_message.getText(), "Please select a size to continue");
			ReporterLog.pass("Error message is visible");
			flag = true;
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Error message on Quick Shop", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Error message on Quick Shop", driver);
		}
		return flag;
	}

	/**
	 * To check whether the opened PLP page is correctly listed in Left Navigation
	 * also and only one category and sub category is opened.
	 * 
	 * @param category:
	 *            Main Category name
	 * @param subcategory:
	 *            sub category name
	 * @throws Exception
	 */

	public synchronized boolean QS_Selected_Size_Default() throws Exception {
		flag = false;
		try {
			List<WebElement> size = utils.findElementsByLocator(driver, "PDP_Lnk_SizeSwatch",
					"Sizes are not displayed");
			for (int i = 0; i < size.size(); i++) {
				String value = size.get(i).getAttribute("aria-checked");
				if (!value.equalsIgnoreCase("false")) {
					ReporterLog.fail("Size :" + size.get(i).getAttribute("aria-label") + " is selected");
					return flag;
				}
			}

			flag = true;
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Error message on Quick Shop", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Error message on Quick Shop", driver);
		}

		return flag;
	}

	public synchronized WebElement closeFilter() throws Exception {
		return utils.findElementByLocator(driver, "PLP_CloseFilter", "| Close Size Type Filter");
	}

	public synchronized WebElement closeByupCarret() throws Exception {
		return utils.findElementByLocator(driver, "PLP_closeupwardscarret", "| Upwards carrot");
	}

	public synchronized void ClickUpwardsCarrot() throws Exception {
		utils.clickWebElement(driver, closeByupCarret(), "Unable to close by clicking upwards Carrot");
	}

	public synchronized void clickCloseFilter() throws Exception {
		utils.clickWebElement(driver, closeFilter(), "Unable to click close filter button");
	}

	public synchronized void leftNavOpenedCategory(String category, String subcategory) throws Exception {
		try {
			if (Utils.device.equals("mobile"))
				homepage.click_lnk_hamburger();

			else {

				// Checking the Category Name Correctly present
				WebElement productNameOpened = utils.findElementByLocator(driver, "PDP_LeftNavOpenCategoryName",
						"Main Category Name as expected not present");
				String openendCategory = productNameOpened.getText().substring(0, category.length());
				Assert.assertEquals(openendCategory.toUpperCase(), category.toUpperCase());
			}
			// checking that only one sub Category selected under main Category
			boolean subCategoryPresent = utils.isElementVisible(driver, "PDP_LeftNavSubCategory",
					"No Subcategory opened");
			if (subCategoryPresent) {
				ReporterLog.pass("Main Category Name is present in Left Nav");
				Assert.assertTrue(utils.findElementByLocator(driver, "PDP_LeftNavSubCategoryName",
						"Left Nav Sub Category xpath not found").getText().equalsIgnoreCase(subcategory));
				ReporterLog.pass("Sub Category Name is active and correct Name is displayed");
			} else {
				ReporterLog.pass("No SubCatrgory List Opened for the Selected category");
			}
			// Fetching elements having main category Name present
			// UNCOMMENT WHEN THE GLOBAL NAV IS PRESENT

			// List<WebElement> categoryOpened =
			// utils.findElementsByLocator(driver,
			// "PDP_LeftNaviagtionCategoriesPan", "Left Navigation Side category
			// Pan List Not present in proper order");
			//
			// int i=categoryOpened.size(),counter=0;
			//
			// //checking that only one Category selected
			// while(i>=0)
			// {
			// if(categoryOpened.get(categoryOpened.size()-i).getAttribute("class").contains("active")
			// &&
			// categoryOpened.get(categoryOpened.size()-i).getText().contains(category))
			// counter++;
			// i--;
			// }
			// //If more than one class found to be active
			// if(counter>1 || counter==0)
			// {
			// ReporterLog.fail("One or more category opened");
			// Assert.fail("One or more category opened");
			// }

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Left Navigation Panel Failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Left Navigation Panel Failed", driver);
		}
	}

	/**
	 * Method select the filters by default according to the type of filter passed
	 * 
	 * @throws Exception
	 * @param :
	 *            typeofFilter: have to send the filter type i.e Color, Size, Price
	 *            etc.
	 * @param: numberofFilter:
	 *             how many filter want to check eg. under color how many color want
	 *             to select from the start.
	 */
	public synchronized void selectfilters(String typeOfFilter, int numberOfFilter) throws Exception {
		try {
			List<WebElement> element = null;
			switch (typeOfFilter) {
			case "Color":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on Color filter dropdown
				Thread.sleep(1000);
				click_lnk_PLP_ColorFilter();
				element = utils.findElementsByLocator(driver, "PLP_FilterColorList", "Color Filter not found");

				if (numberOfFilter > element.size()) {
					ReporterLog.fail("Filter Size excedded");
					Assert.fail();
				} else {
					// loop to select the filter and if already selected then
					// un-select the filter
					for (int i = 0; i < numberOfFilter; i++) {
						if (element.get(i).getAttribute("class").contains("checked")) {
							ReporterLog.pass("Filter already selected");
							ReporterLog.actionMsg("Removing the filter");
						}
						filterSelected.put(element.get(i).getText().replaceAll("[\\s (]+[\\d]+[)]", ""), typeOfFilter);

						element.get(i).click();
					}
				}
				break;

			case "Size":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on Size filter dropdown

				click_lnk_PLP_SizeFilter();
				if (utils.findElementByLocator(driver, "PLP_SizeFilter", "|Size Filter").getAttribute("aria-expanded")
						.equals("true")) {
					ReporterLog.pass("Filter area expanded");
				}
				element = utils.findElementsByLocator(driver, "PLP_FilterSizeList", "Size Filter not found");
				List<WebElement> element_Size = utils.findElementsByLocator(driver, "PLP_FilterSize",
						"Size Filter not found");
				// Verify each filter should have count in parenthesis
				ReporterLog.actionMsg("Verify all filter contains Item count within parenthesis");
				for (int i = 0; i < element.size(); i++) {
					String filtername = element.get(i).getText();
					int startIndex = filtername.indexOf("(");
					int lastIndex = filtername.length();
					String filtercount = filtername.substring(startIndex, lastIndex);

					if (filtercount.startsWith("(") && filtercount.endsWith(")")) {
						String ItemCount = filtercount.substring(filtercount.indexOf("(") + 1,
								filtercount.length() - 1);

						int productCount = Integer.parseInt(ItemCount);

					} else {
						ReporterLog.fail("Filter does not contains Item count within parenthesis");
					}

				}
				ReporterLog.pass("All Filter contains Item count within parenthesis");
				if (numberOfFilter > element.size()) {
					ReporterLog.fail("Filter Size excedded");
					Assert.fail();
				} else {
					// loop to select the filter and if already selected then
					// un-select the filter

					for (int i = 0; i < numberOfFilter; i++) {
						if (element.get(i).getAttribute("class").contains("checked")) {
							ReporterLog.pass("Filter already selected");
							ReporterLog.actionMsg("Removing the filter");
						}
						filterSelected.put(element.get(i).getText().replaceAll("[\\s (]+[\\d]+[)]", ""), typeOfFilter);

						element_Size.get(i).click();
					}
				}
				break;

			case "Sleeve_Length":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on Sleeve filter dropdown

				click_lnk_PLP_SleeveLengthFilter();
				element = utils.findElementsByLocator(driver, "PLP_FilterSleeveList", "Sleeve Filter not found");
				if (numberOfFilter > element.size()) {
					ReporterLog.fail("Filter Size excedded");
					Assert.fail();
				} else {
					// loop to select the filter and if already selected then
					// un-select the filter

					for (int i = 0; i < numberOfFilter; i++) {
						if (element.get(i).getAttribute("class").contains("checked")) {
							ReporterLog.pass("Filter already selected");
							ReporterLog.actionMsg("Removing the filter");
						}
						element.get(i).click();
					}
				}
				break;

			case "More": {
				ReporterLog.actionMsg("Clicking on More filter category");
				click_lnk_PLP_MoreFilter();

				List<WebElement> totalMoreFilters = utils.findElementsByLocator(driver, "PLP_MoreFilterNames",
						"No Filters Found inside More");

				ReporterLog.actionMsg("Clicking on First Sub Filters Present under \"More\"");
				System.out.println("******" + totalMoreFilters.get(0).getText());
				if (totalMoreFilters.get(0).getText().equalsIgnoreCase("Price")) {
					totalMoreFilters.get(0).click();
					WebElement e = driver.findElement(By.xpath(".//*[@role='slider'][1]"));
					int xOffset = (int) homepage.getTheElementLocations(e, "x-axis");
					int yOffset = (int) homepage.getTheElementLocations(e, "y-axis");

					Actions a = new Actions(driver);
					int shifPixel = 5;
					System.out.println(xOffset + shifPixel);
					a.clickAndHold(e).moveByOffset(xOffset + shifPixel, yOffset).release(e).build().perform();

					WebElement priceRange = utils.findElementByLocator(driver, "PLP_PriceRange",
							"Price Range not found");
					filterSelected.put(priceRange.getText(), "PriceRange");
				}

				else {
					totalMoreFilters.get(0).click();

					element = utils.findElementsByLocator(driver, "PLP_FilterInsideMore",
							"No Sub Filter Found inside the More> any Filters ");

					if (numberOfFilter > element.size()) {
						ReporterLog.fail("Filter Size excedded");
						Assert.fail();
					}

					for (int i = 0; i < numberOfFilter; i++) {
						if (element.get(i).getAttribute("class").contains("checked")) {
							ReporterLog.pass("Filter already selected");
							ReporterLog.actionMsg("Removing the filter");
						}
						filterSelected.put(element.get(i).getText().replaceAll("[\\s (]+[\\d]+[)]", ""), typeOfFilter);
						element.get(i).click();
						ReporterLog.pass("Clicked on filter under more" + i);

					}
				}
			}
			break;

			case "Price":

				if (Utils.device.equals("mobile") && Utils.browser.equals("ios")) {
					MobileView.switchToNative(driver);

					List<WebElement> e2 = ((IOSDriver) driver).findElements(By.className("XCUIElementTypeSlider"));
					int xOffset1 = (int) homepage.getTheElementLocations(e2.get(0), "x-axis");
					int yOffset1 = (int) homepage.getTheElementLocations(e2.get(0), "y-axis");
					int scrollYPixel = 2;
					TouchAction a = new TouchAction((IOSDriver) driver);
					a.tap(xOffset1 + numberOfFilter, yOffset1 + scrollYPixel).perform();
					Utils.waitForPageLoaded(driver);
					MobileView.switchToWebView(driver);
				} else {
					if (Utils.device.equals("mobile") && Utils.browser.equals("android")) {
						MobileView.switchToNative(driver);

						WebElement e = driver.findElement(By.xpath("//android.widget.SeekBar[@index='3']"));
						int xOffset = (int) homepage.getTheElementLocations(e, "x-axis");
						int yOffset = (int) homepage.getTheElementLocations(e, "y-axis");

						TouchAction a = new TouchAction((MobileDriver) driver);
						int range = 10;
						a.tap(xOffset + numberOfFilter, yOffset + range).perform();

						MobileView.switchToWebView(driver);

					} else {
						WebElement e = driver.findElement(By.xpath(".//*[@role='slider'][1]"));
						int xOffset = (int) homepage.getTheElementLocations(e, "x-axis");
						int yOffset = (int) homepage.getTheElementLocations(e, "y-axis");

						Actions a = new Actions(driver);
						int newxOffset = xOffset + numberOfFilter;
						a.clickAndHold(e).moveByOffset(newxOffset, yOffset).release(e).build().perform();
					}
				}
				String priceMinValue = utils.findElementByLocator(driver, "PLP_FromPriceFilter", "min price not found")
						.getText();
				String priceMaxValue = utils.findElementByLocator(driver, "PLP_ToPriceFilter", "max price not found")
						.getText();
				String priceFilter = priceMinValue + "-" + priceMaxValue;
				filterSelected.put(priceFilter, "PriceRange");

				break;

			default:
				Assert.fail("Wrong choice entered");
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Left Navigation Panel Failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Left Navigation Panel Failed", driver);
		}
	}

	public synchronized void SizeFilterIndex(List<String> SizeList) throws AssertionError, Exception {
		try {
			HashMap<String, Integer> data = new HashMap<>();
			data.put("00", 0);
			data.put("0", 1);
			data.put("2", 2);
			data.put("4", 3);
			data.put("6", 4);
			data.put("8", 5);
			data.put("10", 6);
			data.put("12", 7);
			data.put("14", 8);
			data.put("16", 9);
			data.put("18", 10);
			data.put("XXS", 11);
			data.put("XS", 12);
			data.put("S", 13);
			data.put("M", 14);
			data.put("L", 15);
			data.put("XL", 16);
			data.put("XXL", 17);

			for (int i = 0; i < SizeList.size(); i++) {
				VerifySizesSorting(data.get(SizeList.get(i)));
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Size Filter", "Index for Size in Size filter not found", driver);
		}

	}

	public synchronized static void VerifySizesSorting(int size) throws AssertionError, Exception {
		sort.add(size);

		num.add(size);
		// System.out.println(num);
		try {
			Assert.assertEquals(sort, num);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "Filters", "Sizes in Size filter are not sorted", driver);
		}

	}

	public synchronized List<Integer> getSelectedFilterOutline() throws Exception {
		List<Integer> filterhighlight = new ArrayList<>();
		List<WebElement> element = utils.findElementsByLocator(driver, "PLP_FilterColorList", "Color Filter not found");
		int count = 0;
		for (int i = 0; i < element.size(); i++) {
			String filteroutline = element.get(i).getAttribute("class");
			if (filteroutline.contains("checked")) {
				filterhighlight.add(count);
				count++;
			}
		}
		return filterhighlight;

	}

	/**
	 * Method to check that the filter that we want to apply is present on PLP or
	 * not.
	 * 
	 * @param: filterName:
	 *             Name of the filter that we want to apply
	 * @throws Exception
	 * 
	 */

	public synchronized void checkFilterIsPresentForSpecificCategory(String filterName) throws Exception {
		try {

			int count = 0;
			List<WebElement> totalFilters = getTotalFiltersOnPLP();

			// Checking the filter present or not
			for (int i = 0; i < totalFilters.size(); i++) {
				System.out.println(totalFilters.get(i).getText());
				if (totalFilters.get(i).getText().equalsIgnoreCase(filterName)) {
					// Filter Found
					ReporterLog.pass("Filter Name Present");
					count++;
					break;
				}
				// If filter present under More filter than clicking on More
				// filter and checking the Filter is present under that or not
				if (totalFilters.get(i).getText().equalsIgnoreCase("More")) {
					click_lnk_PLP_MoreFilter();
					List<WebElement> totalMoreFilters = utils.findElementsByLocator(driver, "PLP_MoreFilterNames",
							"No Filters Found inside More");
					for (int j = 0; j < totalMoreFilters.size(); j++) {
						if (totalMoreFilters.get(j).getText().equalsIgnoreCase(filterName)) {
							// Filter Found under More Category
							ReporterLog.pass("Filter Name Present under More");
							count++;
							break;
						}
					}
				}
			}

			closeFilterModalInMobile();

			if (count == 0) {
				// Filter not present under the PLP page
				ReporterLog.fail("Filter Category Name Not found under PLP");
				Assert.fail();
			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Left Navigation Panel Failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Left Navigation Panel Failed", driver);
		}
	}

	/**
	 * @Prateek
	 * 
	 * 			Method returns total filters on PLP page as a list
	 * @return List<WebElement>
	 * @throws Exception
	 */
	public synchronized List<WebElement> getTotalFiltersOnPLP() throws Exception {

		if (Utils.isDeviceMobile()) {

			Assert.assertTrue(utils.isElementPresent(driver, "PLP_MB_FilterExpand"));
			utils.findElementByLocator(driver, "PLP_MB_FilterExpand", "Not able to find Filter By option in mobile")
			.click();

			Thread.sleep(3000);

			utils.isElementPresent(driver, "MBL_Filter_Modal_Heading");

			return utils.findElementsByLocator(driver, "MBL_PLP_TotalFilters", "No Filter Present for the Category");
		}
		return utils.findElementsByLocator(driver, "PLP_TotalFilters", "No Filter Present for the Category");
	}

	public synchronized void closeFilterModalInMobile() throws Exception {

		ReporterLog.log("Checking if filter modal is present in mobile");

		if (Utils.isDeviceMobile()) {

			utils.isElementPresent(driver, "PLP_MBL_Filter_Modal_Close");
			utils.findElementByLocator(driver, "PLP_MBL_Filter_Modal_Close",
					"Close/Back button in Filter Modal in mobile is present").click();
		}
		Thread.sleep(3000);
	}

	public synchronized List<Integer> MobileFilterCount() throws Exception {
		List<Integer> count = new ArrayList<Integer>();
		List<WebElement> mb_filtercount = utils.findElementsByLocator(driver, "PLP_MB_FilterCount", "| Mobile Filter");
		for (int i = 0; i < mb_filtercount.size(); i++) {
			String countNumber = mb_filtercount.get(i).getText();
			String productcount = countNumber.substring(countNumber.indexOf("(") + 1, countNumber.length() - 1);
			int totlaCount = Integer.parseInt(productcount);
			count.add(totlaCount);
		}
		return count;
	}

	public synchronized void closeMBModal() throws Exception {
		WebElement closeModal = utils.findElementByLocator(driver, "ODP_close_Btn", "| Close Filter Modal");
		utils.clickWebElement(driver, closeModal, "Unable to close Modal");
	}

	/**
	 * Method to get the Prices of all the product tiles into an List
	 * 
	 * @throws Exception
	 * @throws NumberFormatException
	 * 
	 */

	public synchronized List<Double> getPrices() throws NumberFormatException, Exception {
		List<Double> productPrice = new ArrayList<Double>();

		try {
			String price = null;

			// scrolling to the end of page
			utils.scrollEndOfPage();

			// Loop for taking the product price in an array
			for (int i = 0; i < lnk_ProductTilesPrice().size(); i++) {
				price = lnk_ProductTilesPrice().get(i).getText();
				if (price.contains("-")) {
					String[] pricesWithDolor = price.split("-");
					productPrice.add(Double.parseDouble(pricesWithDolor[0].replaceAll("[^\\d.]", "")));
				} else if (price.isEmpty())
					productPrice.add(0.0);
				else {

					if (price.split("\\$").length > 2) {
						String salePrice[] = price.split("\\$");
						salePrice = ArrayUtils.remove(salePrice, 0);
						productPrice.add(Double.parseDouble(salePrice[0].replaceAll("[^\\d.]", "")));
					} else
						productPrice.add(
								Double.parseDouble(lnk_ProductTilesPrice().get(i).getText().replaceAll("[^\\d.]", "")));
				}
			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Left Navigation Panel Failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Left Navigation Panel Failed", driver);
		}
		return productPrice;
	}

	/**
	 * Method to get the Prices of all the product tiles into an List
	 * 
	 * @throws Exception
	 * @throws NumberFormatException
	 * 
	 */

	public synchronized List<Double> getPrices(String filterName, double fromPrice, double toPrice)
			throws NumberFormatException, Exception {
		List<Double> productPrice = new ArrayList<Double>();

		try {
			String price = null;

			// scrolling to the end of page
			utils.scrollEndOfPage();

			// Loop for taking the product price in an array
			for (int i = 0; i < lnk_ProductPrice_Complete().size(); i++) {
				price = lnk_ProductPrice_Complete().get(i).getText();
				if (price.contains("-")) {
					String[] pricesWithDolor = price.split("-");
					if (filterName.equalsIgnoreCase("Price")
							&& fromPrice <= Double.parseDouble(pricesWithDolor[0].replaceAll("[^\\d.]", "")))
						productPrice.add(Double.parseDouble(pricesWithDolor[0].replaceAll("[^\\d.]", "")));
					else {
						productPrice.add(Double.parseDouble(pricesWithDolor[1].replaceAll("[^\\d.]", "")));
					}
				} else if (price.isEmpty())
					productPrice.add(0.0);
				else {

					if (price.split("\\$").length > 2) {
						String salePrice[] = price.split("\\$");
						salePrice = ArrayUtils.remove(salePrice, 0);
						productPrice.add(Double.parseDouble(salePrice[0].replaceAll("[^\\d.]", "")));
					} else
						productPrice.add(
								Double.parseDouble(lnk_ProductTilesPrice().get(i).getText().replaceAll("[^\\d.]", "")));
				}
			}

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Left Navigation Panel Failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Left Navigation Panel Failed", driver);
		}
		return productPrice;
	}

	/**
	 * Checking that the Filter selected is checked after apply. Opening the Filter
	 * again and checking that those filter is present over there and marked.
	 * 
	 * @param driver
	 * @param typeOfFilter:
	 *            Color, Size etc
	 * @param filterDescription:
	 *            If selected Color filter than in parameter we have to pass the
	 *            type of filter ex."Black"
	 * @throws Exception
	 */
	public void checkSpecificFilterSelected(WebDriver driver, String typeOfFilter, String filterDescription)
			throws Exception {
		WebElement element = null;

		try {
			switch (typeOfFilter) {
			case "Color":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on the filter
				click_lnk_PLP_ColorFilter();
				String color = xlUtils.getObjectFromXMLString("PLP_SelectColor_1", Constants.ENVIRONMENT)
						+ filterDescription
						+ xlUtils.getObjectFromXMLString("PLP_SelectColor_2", Constants.ENVIRONMENT);
				element = driver.findElement(By.xpath(color));

				// checking if already selected
				if (element.getAttribute("class").contains("checked")) {
					ReporterLog.pass("Filter already selected");
				} else {
					ReporterLog.fail("Filter Not Selected");
					Assert.fail("Filter Not Selected");
				}
				click_lnk_PLP_ColorFilter();
				checkNameOfFilterPresentAfterSelected(filterDescription);
				break;

			case "Size":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on the filter

				click_lnk_PLP_SizeFilter();
				String size = xlUtils.getObjectFromXMLString("PLP_SelectSize", Constants.ENVIRONMENT)
						+ filterDescription
						+ xlUtils.getObjectFromXMLString("PLP_SelectColor_2", Constants.ENVIRONMENT);
				element = driver.findElement(By.xpath(size));

				// checking if already selected
				if (element.getAttribute("class").contains("checked")) {
					ReporterLog.pass("Filter already selected");
				} else {
					ReporterLog.fail("Filter Not Selected");
					Assert.fail("Filter Not Selected");
				}
				click_lnk_PLP_SizeFilter();
				checkNameOfFilterPresentAfterSelected(filterDescription);
				break;

			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Select Specific Filter", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Select Specific Filter", driver);
		}
	}

	/**
	 * Checking that the Filter selected is checked after apply. Opening the Filter
	 * again and checking that those filter is present over there and marked.
	 * 
	 * @param typeOfFilter:
	 *            Ex. "Color","Size" etc
	 * @param numberOfFilter:
	 *            Ex. Under color how many filter you want to check if they are
	 *            selected or not
	 * @throws Exception
	 */
	public synchronized void checkselectfilters(String typeOfFilter, int numberOfFilter) throws Exception {
		try {
			List<WebElement> element = null;
			switch (typeOfFilter) {
			case "Color":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on Color filter dropdown
				click_lnk_PLP_ColorFilter();
				element = utils.findElementsByLocator(driver, "PLP_FilterColorList", "Color Filter not found");

				if (numberOfFilter > element.size()) {
					ReporterLog.fail("Filter Size excedded");
					Assert.fail();
				} else {
					// loop to select the filter and if already selected then
					// un-select the filter
					for (int i = 0; i < numberOfFilter; i++) {
						if (element.get(i).getAttribute("class").contains("checked")) {
							ReporterLog.pass("Filter already selected");
						} else {
							ReporterLog.fail("Filter Not Selected");
							Assert.fail("Filter Not Selected");
						}
					}
					click_lnk_PLP_ColorFilter();

				}
				break;

			case "Size":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on Size filter dropdown

				click_lnk_PLP_SizeFilter();
				element = utils.findElementsByLocator(driver, "PLP_FilterSize", "Size Filter not found");
				if (numberOfFilter > element.size()) {
					ReporterLog.fail("Filter Size excedded");
					Assert.fail();
				} else {
					// loop to select the filter and if already selected then
					// un-select the filter

					for (int i = 0; i < numberOfFilter; i++) {
						if (element.get(i).getAttribute("class").contains("checked")) {
							ReporterLog.pass("Filter already selected");
						} else {
							ReporterLog.fail("Filter Not Selected");
							Assert.fail("Filter Not Selected");
						}
					}
					click_lnk_PLP_SizeFilter();

				}
				break;

			case "Sleeve_Length":
				if (Utils.device.equals("mobile"))
					click_lnk_FilterExpand();

				// clicking on Sleeve filter dropdown

				click_lnk_PLP_SleeveLengthFilter();
				element = utils.findElementsByLocator(driver, "PLP_FilterSleeveList", "Sleeve Filter not found");
				if (numberOfFilter > element.size()) {
					ReporterLog.fail("Filter Size excedded");
					Assert.fail();
				} else {
					// loop to select the filter and if already selected then
					// un-select the filter

					for (int i = 0; i < numberOfFilter; i++) {
						if (element.get(i).getAttribute("class").contains("checked")) {
							ReporterLog.pass("Filter already selected");
						} else {
							ReporterLog.fail("Filter Not Selected");
							Assert.fail("Filter Not Selected");
						}
					}
				}
				break;

			case "More": {
				ReporterLog.actionMsg("Clicking on More filter category");
				click_lnk_PLP_MoreFilter();

				List<WebElement> totalMoreFilters = utils.findElementsByLocator(driver, "PLP_MoreFilterNames",
						"No Filters Found inside More");

				ReporterLog.actionMsg("Clicking on First Sub Filters Present under \"More\"");

				if (totalMoreFilters.get(0).getText().equalsIgnoreCase("PriceRange")) {
					ReporterLog.fail("Can't check as Price Range found under More");
				}

				else {
					totalMoreFilters.get(0).click();

					element = utils.findElementsByLocator(driver, "PLP_FilterInsideMore",
							"No Sub Filter Found inside the More> any Filters ");

					if (numberOfFilter > element.size()) {
						ReporterLog.fail("Filter Size excedded");
						Assert.fail();
					}

					for (int i = 0; i < numberOfFilter; i++) {
						if (element.get(i).getAttribute("class").contains("checked")) {
							ReporterLog.pass("Filter already selected");
						} else {
							ReporterLog.fail("Filter Not Selected");
							Assert.fail("Filter Not Selected");
						}

					}
				}
			}
			break;
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Select Random Filter", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Select Random Filter", driver);
		}
	}

	/**
	 * Checking that the Filter Name is present after the filter Applied
	 * 
	 * @param filterName:
	 *            Name of filter applied Ex. under "Color" if we select "Black"
	 *            color then we pass "Black" as parameter
	 * @throws Exception
	 */
	public synchronized void checkNameOfFilterPresentAfterSelected(String filterName) throws Exception {
		try {

			// Name of filter not displayed in mobile
			if (Utils.isDeviceMobile())
				return;

			int count = 0;
			int totalFilters = lnk_PLP_FilterSelected().size();
			for (int i = 0; i < totalFilters; i++) {
				if (lnk_PLP_FilterSelected().get(i).getText().replaceAll("\\s", "")
						.equalsIgnoreCase(filterName.replaceAll("\\s", "")))
					count++;
			}
			if (count == 0) {
				ReporterLog.fail("Filter Name Not visible after selected");
				Assert.fail("Filter Name Not visible after selected");
			}
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Select Random Filter", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Select Random Filter", driver);
		}

	}

	/**
	 * Method to get more colors link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_MoreColorSwatches() throws Exception {

		return utils.findElementsByLocator(driver, "PLP_showMoreColors",
				"| PLP: Product more Color Swatch not present");
	}

	/**
	 * Method to get more colors link
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_MoreColorSwatchesList() throws Exception {

		return utils.findElementsByLocator(driver, "PLP_moreColorSwatchList",
				"| PLP: Product more Color Swatch not present");
	}

	/**
	 * Method to get product image src
	 * 
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_productImages() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementsByLocator(driver, "MBL_PLP_productImages", "| PLP: Product image not present");

		return utils.findElementsByLocator(driver, "PLP_productImages", "| PLP: Product image not present");
	}

	// Filter title labels on mobile
	public synchronized List<WebElement> txt_filterTitles() throws Exception {

		return utils.findElementsByLocator(driver, "SRP_MB_FilterTitles", "| PLP: filter labels");
	}

	// Filter back button on mobile
	public synchronized WebElement btn_filtersBackBtn() throws Exception {

		return utils.findElementByLocator(driver, "SRP_MB_FilterBackButton",
				"| PLP: filter back utton when filter clicked");
	}

	// public synchronized int getTotalProductsCount() throws Exception {
	//
	// List<WebElement> elems = utils.findElementsByLocator(driver,
	// "PLP_ProductTallyCircle", "| PLP: Total Product Count from Circle");
	//
	// if (elems.size() > 0) {
	// ReporterLog.log("Total products count present in Circle");
	// return Integer.parseInt(elems.get(0).getText());
	// }
	//
	// ReporterLog.log("Total products count not present in Circle");
	// return utils.findElementsByLocator(driver, "PLP_productImages", "| PLP: Total
	// Product Count").size();
	// }

}
