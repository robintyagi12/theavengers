package com.ann.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
//import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
//import org.openqa.selenium.interactions.HasTouchScreen;
//import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ann.automation.pages.HomePageObjects;
import com.google.common.base.Function;

//import atu.testrecorder.ATUTestRecorder;
//import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Utils {
	public int count = 0;
	public static String steps = "";
	private static WebElement element;
	private static List<WebElement> elementList;
	private static By locator = null;
	static Wait<IOSDriver<WebElement>> waitiOS = null;
	static Wait<AppiumDriver<WebElement>> waitandroid = null;
	static Wait<WebDriver> wait = null;
	public static Alert alert = null;
	boolean cookieExist = false;
	static WebDriver driver = null;
	public static String iOSLocator = null;
	public static String androidLocator = null;
	public static String inHouseBaseUrl = null;
	public static String prebaseUrl = null;
	public static String baseUrl = null;
	protected static String deviceUDID = null;
	public static String browser = null;
	public static String device = null;
	public static String brand = null;
	public HomePageObjects homepage = null;
	public static boolean Regression = true;
	public static Process p, p1;
	public XLUtils xlUtils = null;
	public static String bounceX_AT = "OFF";
	public static String bounceX_LOFT = "OFF";
	public static String bounceX_LGFP = "OFF";
	public static String bounceX_ATFP = "OFF";
	public static String bounceX_LOFP = "OFF";
	//public ATUTestRecorder recorder = null;

	public Utils(String brand, String browser, String device) {
		try {
			xlUtils = new XLUtils();
			// Get base url and in house application URL from test data sheet
			prebaseUrl = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 1, Constants.COL_PREURL);
			String env = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 1, 1);
			// browser =
			// xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT,
			// 1,Constants.COL_BROWSER);
			this.browser = browser;
			this.device = device;
			this.brand = brand;

			if (this.brand.equals("ATF")) {
				baseUrl = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 1, Constants.COL_URL);
			} else if (this.brand.equals("LO")) {
				baseUrl = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 2, Constants.COL_URL);
			} else if (this.brand.equals("ATFP")) {
				baseUrl = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 3, Constants.COL_URL);
			} else if (this.brand.equals("LOFP")) {
				baseUrl = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 4, Constants.COL_URL);
			} else if (this.brand.equals("LGFP")) {
				baseUrl = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 5, Constants.COL_URL);
			}

			// device=xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT,
			// 1,Constants.COL_DEVICE);
			if (env.equalsIgnoreCase("dryrun")) {
				Regression = false;
			}

			String bounceX = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 1, 6);
			if (bounceX.equalsIgnoreCase("on")) {
				bounceX_AT = "on";
			}
			bounceX = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 2, 6);
			if (bounceX.equalsIgnoreCase("on")) {
				bounceX_LOFT = "on";
			}

			bounceX = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 3, 6);
			if (bounceX.equalsIgnoreCase("on")) {
				bounceX_ATFP = "on";
			}
			bounceX = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 4, 6);
			if (bounceX.equalsIgnoreCase("on")) {
				bounceX_LOFP = "on";
			}

			bounceX = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ENVIRONMENT, 5, 6);
			if (bounceX.equalsIgnoreCase("on")) {
				bounceX_LGFP = "on";
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Method to initiateBrowser
	 * 
	 * @throws Exception
	 */

	/**
	 * Method to generate random email
	 * 
	 * @param driver
	 * @return
	 */
	public synchronized String generateRandomEmail() {
		try {
			String email = null;

			email = "acceptemail" + Math.round(Math.random() * 13570) + "@gmail.com";
			Assert.assertFalse(email.isEmpty(), "| Random Email not generated");
			Reporter.log(Constants.DELIMITER + Constants.PASS + "| Random Email :" + email);
			return email;

		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Random Email not generated");
			throw (e);
		}

	}

	/**
	 * Method to generate random numbers
	 * 
	 * @return int
	 */
	@SuppressWarnings("unused")
	private int getRandomNumber() {
		final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	/**
	 * Method to find element and assert if null and print exception message, else
	 * enter keys
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized void clickWebElement(WebDriver driver, WebElement element, String exceptionMessage)
			throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", element);

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			throw (e);
		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			throw (e);
		}

	}

	public synchronized String getDatetime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String founddate = dateFormat.format(date);
		// System.out.println(dateFormat.format(date)); // 2014/08/06 15:59:48

		String[] parts = founddate.split(" ");
		// String part1 = parts[0]; // 004
		String[] appenderpart1 = parts[0].split("/");
		String[] appenderpart2 = parts[1].split(":");
		String appender = "";

		for (String part : appenderpart1)
			appender = appender + part;

		for (String part : appenderpart2)
			appender = appender + part;

		return appender;
	}

	public synchronized boolean validateTextInURL(WebDriver driver, String text, String exceptionMessage) throws Exception {
		boolean status = false;
		try {

			status = driver.getCurrentUrl().contains(text);
		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			throw (e);
		}
		return status;
	}

	/**
	 * Method to highlight the found element with green dashed border
	 * 
	 * @param WebDriver
	 *            , element
	 * @return WebElement
	 */
	public synchronized static void highlightElement(Object driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px red');", element);

	}

	/*
	 * try {
	 * 
	 * JavascriptExecutor js = (JavascriptExecutor) driver; js. executeScript(
	 * "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');"
	 * , element); Thread.sleep(500);
	 * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	 * element, "border:1px dotted springgreen !important;"); } catch (Exception e)
	 * { Reporter.log(Constants.DELIMITER + Constants.FAIL +
	 * "| Element not Found |"); try { throw (e); } catch (Exception e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * }
	 */

	/**
	 * Method to find the element per locator, and return the element back.
	 * 
	 * @param By
	 *            , WebDriver
	 * @return WebElement
	 */
	@SuppressWarnings("unchecked")
	public synchronized static WebElement getElement(final By locator, Object driver) {
		try {
			// if(getDriverName(driver).contains("IOSDriver")){
			// waitiOS = new
			// FluentWait<IOSDriver<WebElement>>((IOSDriver<WebElement>)
			// driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
			// TimeUnit.SECONDS);
			// element =
			// waitiOS.until(ExpectedConditions.presenceOfElementLocated(locator));
			// }
			// else{
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			// }
			return element;

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Element not Found By |" + locator);
			return null;
		}
	}

	/**
	 * Method to find the elements per locator, and return the element back.
	 * 
	 * @param By
	 *            , WebDriver
	 * @return WebElement
	 */
	@SuppressWarnings("unchecked")
	public synchronized static List<WebElement> getElements(final By locator, Object driver) {
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			elementList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			// wait = new FluentWait<WebDriver>(driver).withTimeout(20,
			// TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
			// elementList =
			// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

			return elementList;

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Elements not Found By |" + locator);
			return null;
		}
	}

	/**
	 * Method to find the element per the XPATH, log the incident, and return the
	 * element back.
	 * 
	 * @param xPath
	 *            ,WebDriver, wait
	 * @return WebElement
	 */
	public synchronized WebElement findElementByXPath(final String xPath, WebDriver driver) {
		try {
			Actions actions = new Actions(driver);

			wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			actions.moveToElement(element);
			highlightElement(driver, element);
			return element;

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Element not Found by xPath|");
			throw new IllegalStateException("| Element not Found |" + "xPath:" + xPath + e);
		}
	}

	/**
	 * Method to perform Click action on the element found by findElementByXPath
	 * 
	 * @param xPath
	 */
	public synchronized WebElement findElementByXPathAndClick(String xPath, WebDriver driver) {
		WebElement el = findElementByXPath(xPath, driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", el);
		return el;

	}

	/**
	 * Compact way to verify if an element is on the page
	 * 
	 * @param driver
	 * @param object
	 * @return boolean value
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean isElementPresent(final Object driver, String object) {
		try {
			// code to detect locators for full price site which are prefix with
			// FP_
			if (isBrandFullPrice() && !object.startsWith("FP_")) {
				object = "FP_" + object;
			}

			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			Utils.highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public synchronized boolean isElementPresentForAllDevice(final Object driver, String object) {
		try {
			// code to detect locators for full price site which are prefix with
			// FP_

			if (isDeviceMobile())
				object = "MBL_" + object;

			if (isBrandFullPrice()) {
				object = "FP_" + object;
			}

			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			Utils.highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Compact way to verify if an element is on the page
	 * 
	 * @param driver
	 * @param object
	 * @return boolean value
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean isElementPresentAfterLongInterval(final Object driver, String object) {
		try {

			if (isDeviceMobile())
				object = "MBL_" + object;

			// code to detect locators for full price site which are prefix with
			// FP_
			if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
				object = "FP_" + object;
			}

			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);

			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			// wait = new FluentWait<WebDriver>(driver).withTimeout(20,
			// TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
			// element =
			// wait.until(ExpectedConditions.elementToBeClickable(locator));

			Utils.highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Compact way to verify if an element is on the page
	 * 
	 * @param driver
	 * @param object
	 * @return boolean value
	 */
	public synchronized boolean isElementPresent(final Object driver, String object, int timeout) {
		try {

			// code to detect locators for full price site which are prefix with
			// FP_
			if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
				object = "FP_" + object;
			}

			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);

			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			// wait = new FluentWait<WebDriver>(driver).withTimeout(20,
			// TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
			// element =
			// wait.until(ExpectedConditions.elementToBeClickable(locator));

			Utils.highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Compact way to verify if an element is on the page
	 * 
	 * @param driver
	 * @param object
	 * @return boolean value
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean isElementClickable(final Object driver, String object) {
		try {

			if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
				object = "FP_" + object;
			}

			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);

			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			// wait = new FluentWait<WebDriver>(driver).withTimeout(20,
			// TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
			// element =
			// wait.until(ExpectedConditions.elementToBeClickable(locator));

			Utils.highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Compact way to verify if an element is on the page
	 * 
	 * @param driver
	 * @param object
	 * @return boolean value
	 */
	@SuppressWarnings("unchecked")
	public synchronized static boolean isElementPresent(final Object driver, WebElement element) {
		try {

			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.elementToBeClickable(element));

			// wait = new FluentWait<WebDriver>(driver).withTimeout(20,
			// TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
			// element =
			// wait.until(ExpectedConditions.elementToBeClickable(element));

			Utils.highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param Navigation
	 *            check URL
	 */

	public void verifyPageNavigationURL(WebDriver driver, String text) {
		try {

			if (brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO")) {
				String currentURL = driver.getCurrentUrl();
				if (!currentURL.toUpperCase().contains(text.toUpperCase())) {
					Assert.fail("Page Not navigated");
					ReporterLog.fail("Page Not navigated");
				}
			}
		} catch (Exception e) {
			ReporterLog.fail("Exception Occur" + e);
		}
	}

	/**
	 * Compact way to verify if an element is on the page
	 * 
	 * @param driver
	 * @param by
	 * @return boolean value
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean isElementPresent(final Object driver, By by) {
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.elementToBeClickable(by));

			// wait = new FluentWait<WebDriver>(driver).withTimeout(20,
			// TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
			// element =
			// wait.until(ExpectedConditions.elementToBeClickable(by));

			Utils.highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Compact way to verify if an elements is on the page
	 * 
	 * @param driver
	 * @param by
	 * @return boolean value
	 */
	@SuppressWarnings("unchecked")
	public synchronized static boolean isElementsPresent(final Object driver, final By by) {

		try {

			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			elementList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

			// wait = new FluentWait<WebDriver>(driver).withTimeout(20,
			// TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
			// elementList =
			// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to find element and assert if null and print exception message, else
	 * return webelement
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return webelement
	 * @throws Exception
	 **/
	public synchronized WebElement findElementByLocator(WebDriver driver, String object, String exceptionMessage)
			throws Exception {

		// code to detect locators for full price site which are prefix with FP_
		if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
			if (!object.startsWith("FP_")) {
				object = "FP_" + object;
			}
		}

		if (device.equals("mobile")) {
			MobileView.switchToWebView(driver);
		}
		try {
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			element = getElement(locator, driver);
			Assert.assertTrue(element != null, exceptionMessage);
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Captures", exceptionMessage, driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "Captures", exceptionMessage, driver);
		}
		Thread.sleep(2000);
		return element;
	}

	/**
	 * Method to find elements and assert if null and print exception message, else
	 * return List<WebElement>
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return
	 * @return List<WebElement>
	 * @throws Exception
	 */
	public WebElement findElementByIOSXpathLocator(WebDriver driver, String locator) throws Exception {
		try {
			iOSLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			element = ((IOSDriver) driver).findElement(By.xpath(iOSLocator));

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
		return element;
	}

	/**
	 * Method to find elements and assert if null and print exception message, else
	 * return List<WebElement>
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return
	 * @return List<WebElement>
	 * @throws Exception
	 */
	public WebElement findElementByIOSXpathLocatorAndSelect(WebDriver driver, String locator, String value)
			throws Exception {
		try {
			iOSLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			element = ((IOSDriver) driver).findElement(By.xpath(iOSLocator));

			((IOSElement) element).setValue(value);
			findElementByIOSNameLocator("Done").click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
		return element;
	}

	public WebElement findElementByIOSXpathLocatorAndClick(WebDriver driver, String locator) throws Exception {
		try {
			iOSLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(3000);
			element = ((IOSDriver) driver).findElement(By.xpath(iOSLocator));
			element.click();

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
		return element;
	}

	public synchronized WebElement findElementByAndroidXpathLocator(WebDriver driver, String locator) throws Exception {
		try {
			// String androidLocator = xlUtils.getXpathFromXML(locator,
			// Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			element = ((AndroidDriver) driver).findElement(By.xpath("+locator+"));

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
		return element;
	}

	public List<WebElement> findElementsByIOSClassNameLocator(WebDriver driver, String locator) throws Exception {
		List<WebElement> elements = null;
		try {
			iOSLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			elements = ((IOSDriver) driver).findElements(By.className(iOSLocator));

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
		return elements;
	}

	/**
	 * Method to find element using xpath for Android
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public WebElement findElementByAndroidXpathLocator(String locator) throws Exception {
		try {
			androidLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			element = driver.findElement(By.xpath(androidLocator));

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
		return element;
	}

	public WebElement findElementByIOSNameLocator(String locator) throws Exception {
		try {
			iOSLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			element = ((IOSDriver) driver).findElement(By.name(iOSLocator));

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
		return element;
	}

	public List<WebElement> findElementsByIOSNameLocator(String locator) throws Exception {
		try {
			iOSLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			return ((IOSDriver) driver).findElements(By.name(iOSLocator));

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}

	}

	/**
	 * Method to find element using xpath for Android
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public List<WebElement> findElementByAndroidClassNameLocator(String locator) throws Exception {
		try {
			androidLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			List<WebElement> elementLis = driver.findElements(By.className(androidLocator));
			return elementLis;

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
	}

	/**
	 * Method to find element using id for Android
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public WebElement findElementByAndroidID(String locator) throws Exception {
		try {

			if (isBrandFullPrice()) {
				locator = "FP_" + locator;
			}

			androidLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			element = driver.findElement(By.id(androidLocator));

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
		return element;
	}

	/**
	 * Method to find elements and assert if null and print exception message, else
	 * return List<WebElement>
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return
	 * @return List<WebElement>
	 * @throws Exception
	 */
	public synchronized List<WebElement> findElementsByLocator(WebDriver driver, String object, String exceptionMessage)
			throws Exception {

		// code to detect locators for full price site which are prefix with FP_
		if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
			object = "FP_" + object;
		}

		if (device.equals("mobile")) {
			MobileView.switchToWebView(driver);
		}
		try {
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			elementList = getElements(locator, driver);
			Assert.assertTrue(elementList != null, exceptionMessage);

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Captures",
					Thread.currentThread().getStackTrace()[2].getMethodName(), driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "Captures",
					Thread.currentThread().getStackTrace()[2].getMethodName(), driver);
		}
		return elementList;
	}

	/**
	 * Method to find find element and return boolean value for the presence of
	 * element
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return boolean value
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean isElementPresent(WebDriver driver, String object, String exceptionMessage)
			throws Exception {
		try {

			if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
				object = "FP_" + object;
			}
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			if (device.equals("mobile") && browser.equals("ios")) {
				MobileView.switchToWebView(driver);
				waitiOS = new FluentWait<IOSDriver<WebElement>>((IOSDriver<WebElement>) driver)
						.withTimeout(20, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
				element = waitiOS.until(ExpectedConditions.elementToBeClickable(locator));
			} else if (browser.equals("android")) {
				MobileView.switchToWebView(driver);
				waitandroid = new FluentWait<AppiumDriver<WebElement>>((AppiumDriver<WebElement>) driver)
						.withTimeout(20, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
				element = waitandroid.until(ExpectedConditions.elementToBeClickable(locator));
			} else {
				wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
						TimeUnit.SECONDS);
				element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			}
			Utils.highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			return false;
		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			return false;
		}
	}

	/**
	 * Method to find elements and return boolean value for the presence of element
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return boolean value
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean isElementsPresent(Object driver, String object, String exceptionMessage)
			throws Exception {
		try {

			if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
				object = "FP_" + object;
			}
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			elementList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			return true;

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			return false;
		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			return false;
		}
	}

	/**
	 * Method to find element by locator and check if it is invisible, return true
	 * if found else false
	 * 
	 * @param driver
	 * @param object
	 * @param exceptionMessage
	 * @return boolean value
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean isElementInvisible(Object driver, String object, String exceptionMessage)
			throws Exception {
		try {

			if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
				object = "FP_" + object;
			}

			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.MILLISECONDS);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			return false;
		}
	}

	/**
	 * Method to find element by locator and check if it is visible, return true if
	 * found else false
	 * 
	 * @param driver
	 * @param object
	 * @param exceptionMessage
	 * @return boolean value
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean isElementVisible(Object driver, String object, String exceptionMessage)
			throws Exception {
		try {

			// code to detect locators for full price site which are prefix with
			// FP_
			if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
				object = "FP_" + object;
			}

			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			Utils.highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			return false;
		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			return false;
		}
	}

	/**
	 * Method to check that the Sub Menu is present or not
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized void checkElementSubMenu(Object driver, WebElement ele, String object, String exceptionMessage)
			throws Exception {
		try {
			if (device.equals("mobile")) {
				// clickWebElement(driver,ele,"Element Not clicked");
				ele.click();
			} else {
				Actions action = new Actions((WebDriver) driver);
				action.moveToElement(ele).build().perform();
			}
			Thread.sleep(1000);
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			// Utils.highlightElement(driver, element);

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			Assert.fail(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			Assert.fail(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
		}
	}

	/**
	 * Method to find element and assert if null and print exception message, else
	 * enter keys
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized void sendKeysOnWebElement(WebDriver driver, WebElement element, String keys,
			String exceptionMessage) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			Assert.assertTrue(element != null, exceptionMessage);

			element.clear();
			element.sendKeys(keys);

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Captures", exceptionMessage, driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "Captures", exceptionMessage, driver);
		}
	}

	/**
	 * Method to find element and assert if null and print exception message, else
	 * enter keys
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement findElementByLocatorAndSendKeys(WebDriver driver, String object, String keys,
			String exceptionMessage) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			element = getElement(locator, driver);
			Assert.assertTrue(element != null, exceptionMessage);

			// element.clear();
			element.sendKeys(keys);

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Captures", exceptionMessage, driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "Captures", exceptionMessage, driver);
		}
		return element;
	}

	/**
	 * Method to select values from list of WebElements
	 * 
	 * @param driver
	 * @param elList
	 * @param value
	 * @return
	 * @throws Exception
	 * 
	 */
	public synchronized boolean selectValueFromList(final List<WebElement> elList, WebDriver driver, String value)
			throws Exception {
		boolean optionFound = false;
		value = value.toLowerCase();
		try {
			Thread.sleep(500);
			for (WebElement option : elList) {
				if (value.equalsIgnoreCase(option.getText().toLowerCase().replaceAll("[\\s]", ""))) {
					option.click();
					Reporter.log(Constants.DELIMITER + "| Selected Value: " + value);
					optionFound = true;
					break;
				}
			}
		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Element not Found |");
			return optionFound;
		}
		return optionFound;
	}

	/**
	 * Method to click on value from list of WebElements
	 * 
	 * @param driver
	 * @param elList
	 * @param value
	 * @return
	 * @throws Exception
	 * 
	 */
	public synchronized boolean clickValueFromList(final List<WebElement> elList, Object driver, String value)
			throws Exception {
		boolean optionFound = false;
		try {
			Thread.sleep(500);
			for (WebElement option : elList) {
				if (value.equalsIgnoreCase(option.getText())) {
					option.click();
					Reporter.log(Constants.DELIMITER + "| Selected Value: " + value);
					optionFound = true;
					break;
				}
			}
		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Element not Found |");
			return false;
		}
		return optionFound;
	}

	/**
	 * Select drop down by value, parameter By of li tag which has list of dropdown
	 * values
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * @throws Exception
	 */
	public synchronized void selectDropDownByValue(final String object, WebDriver driver, String value)
			throws Exception {
		try {
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);

			isElementPresent(driver, object);
			WebElement el = findElementByLocator(driver, object, "element not found");
			// el.click();

			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

			Select se = new Select(el);
			se.selectByVisibleText(value);

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Element not Found |" + locator);
			throw (e);
		}
	}

	/**
	 * Method to get absolute xpath from element
	 * 
	 * @param element
	 * @param driver
	 * @return
	 */
	public synchronized static String getAbsoluteXPath(WebElement element, Object driver) {
		return (String) ((JavascriptExecutor) driver)
				.executeScript("function absoluteXPath(element) {" + "var comp, comps = [];" + "var parent = null;"
						+ "var xpath = '';" + "var getPos = function(element) {" + "var position = 1, curNode;"
						+ "if (element.nodeType == Node.ATTRIBUTE_NODE) {" + "return null;" + "}"
						+ "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {"
						+ "if (curNode.nodeName == element.nodeName) {" + "++position;" + "}" + "}" + "return position;"
						+ "};" +

						"if (element instanceof Document) {" + "return '/';" + "}" +

						"for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"
						+ "comp = comps[comps.length] = {};" + "switch (element.nodeType) {" + "case Node.TEXT_NODE:"
						+ "comp.name = 'text()';" + "break;" + "case Node.ATTRIBUTE_NODE:"
						+ "comp.name = '@' + element.nodeName;" + "break;" + "case Node.PROCESSING_INSTRUCTION_NODE:"
						+ "comp.name = 'processing-instruction()';" + "break;" + "case Node.COMMENT_NODE:"
						+ "comp.name = 'comment()';" + "break;" + "case Node.ELEMENT_NODE:"
						+ "comp.name = element.nodeName;" + "break;" + "}" + "comp.position = getPos(element);" + "}" +

						"for (var i = comps.length - 1; i >= 0; i--) {" + "comp = comps[i];"
						+ "xpath += '/' + comp.name.toLowerCase();" + "if (comp.position !== null) {"
						+ "xpath += '[' + comp.position + ']';" + "}" + "}" +

						"return xpath;" +

						"} return absoluteXPath(arguments[0]);", element);
	}

	/**
	 * Method to scrolldown to boottom of the page
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void scrollDown(WebDriver driver) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScrollDown = (JavascriptExecutor) driver;
			jseScrollDown.executeScript("scroll(0, 1500)");
			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scrolldown to bottom of the page
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void scrollDownToBottomOfPage(WebDriver driver) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScrollDown = (JavascriptExecutor) driver;
			jseScrollDown.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scrollup to top of the page
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void scrollUpToTopOfPage(WebDriver driver) throws Exception {

		ReporterLog.log("Scrolling to the top of the page...");
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScrollDown = (JavascriptExecutor) driver;
			jseScrollDown.executeScript("window.scrollTo(0, 0)");
			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scrolldown to x position
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void scrollDownByxPosition(WebDriver driver, int xPos) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScrollDown = (JavascriptExecutor) driver;
			Thread.sleep(3000);
			jseScrollDown.executeScript("scroll(0, " + xPos + ")");
			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scrollUp to x position
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void scrollUpByxPostion(WebDriver driver, int xPos) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScrollUp = (JavascriptExecutor) driver;
			jseScrollUp.executeScript("scroll(0, - " + xPos + ")");
			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scrollDown by window
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void scrollDownByWindow(WebDriver driver, int xPos) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScrollUp = (JavascriptExecutor) driver;
			jseScrollUp.executeScript("window.scrollBy(0,  " + xPos + ")", "");
			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scrollUp by window
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void scrollUpByWindow(WebDriver driver, int xPos) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScrollUp = (JavascriptExecutor) driver;
			jseScrollUp.executeScript("window.scrollBy(0, - " + xPos + ")", "");
			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scrollUp to boottom of the page
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void scrollUp(WebDriver driver) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScrollUp = (JavascriptExecutor) driver;
			jseScrollUp.executeScript("scroll(0, -1500)");
			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scrollUp to the element to perform actions
	 * 
	 * @param driver
	 * @param pageElement
	 * @throws Exception
	 */
	public synchronized void scrollUpToElement(WebDriver driver, WebElement pageElement) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			int getPositionY = 0;
			JavascriptExecutor jseScroll = (JavascriptExecutor) driver;

			Point point = pageElement.getLocation();

			Utils.highlightElement(driver, pageElement);

			if (pageElement != null) {
				getPositionY = point.y;
				jseScroll.executeScript("javascript:window.scrollBy(0,- " + getPositionY + ")");

			}

			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scroll Down to the element to perform actions
	 * 
	 * @param driver
	 * @param pageElement
	 * @throws Exception
	 */
	public synchronized void scrollDownToElement(WebDriver driver, WebElement pageElement) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			int getPositionY = 0;
			JavascriptExecutor jseScroll = (JavascriptExecutor) driver;

			Point point = pageElement.getLocation();

			// Utils.highlightElement(driver, pageElement);

			if (pageElement != null) {
				getPositionY = point.y;
				jseScroll.executeScript("javascript:window.scrollBy(0, " + getPositionY + ")");

			}

			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to scroll Down to the element to perform actions
	 * 
	 * @param driver
	 * @param pageElement
	 * @throws Exception
	 */
	public synchronized void scrollToViewElement(WebDriver driver, WebElement pageElement) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScroll = (JavascriptExecutor) driver;

			Utils.highlightElement(driver, pageElement);

			jseScroll.executeScript("arguments[0].scrollIntoView(false);", pageElement);

			Thread.sleep(1000);

		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Select drop down by Li value
	 * 
	 * @param driver
	 * @param webElement
	 * @param value
	 * @throws Exception 
	 */
	public synchronized void selectDropdown(final WebElement webElement, WebDriver driver, String value) throws Exception {
		try {
			wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return webElement;
				}
			});

			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);",
			// webElement);
			new Select(element).selectByValue(value);

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Element not Found |");
			throw (e);
		}
	}

	/**
	 * Method to wait for element's presence on page
	 * 
	 * @param driver
	 * @param object
	 * @param exceptionMessage
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement waitForElementToPresent(WebDriver driver, String object, String exceptionMessage,
			Wait<WebDriver> wait) throws Exception {
		if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
			object = "FP_" + object;
		}

		try {
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);

			// wait = new FluentWait<WebDriver>(driver).withTimeout(20,
			// TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			Assert.assertTrue(element != null, exceptionMessage);

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			// Utils.takeScreenshot(driver, "Captures",
			// Thread.currentThread().getStackTrace()[2].getMethodName());
			throw (e);
		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			throw (e);
			// Utils.takeScreenshot(driver, "Captures",
			// Thread.currentThread().getStackTrace()[2].getMethodName());
		}
		return element;
	}

	public synchronized boolean waitForElementToPresent(Object driver, String object, int timeout) throws Exception {
		Boolean bVisible = false;
		bVisible = isElementPresent(driver, object, timeout);
		if (!bVisible) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "element not found");
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
		}
		return bVisible;
	}

	/**
	 * Method to wait for element's presence on page
	 * 
	 * @param driver
	 * @param object
	 * @param exceptionMessage
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement waitForElementToPresent(WebDriver driver, String object, String exceptionMessage)
			throws Exception {
		try {

			// code to detect locators for full price site which are prefix with
			// FP_
			if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
				object = "FP_" + object;
			}

			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);

			wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			Assert.assertTrue(element != null, exceptionMessage);

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			throw (e);
		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
		}
		return element;
	}

	/**
	 * Method to wait for element's presence on page
	 * 
	 * @param driver
	 * @param object
	 * @param exceptionMessage
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement waitForElementToBeClickable(WebDriver driver, String object, String exceptionMessage)

			throws Exception {
		try {
			if (!(brand.equalsIgnoreCase("ATF") || brand.equalsIgnoreCase("LO"))) {
				object = "FP_" + object;
			}
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);

			wait = new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			Assert.assertTrue(element != null, exceptionMessage);

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
			throw (e);
		} catch (AssertionError e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + exceptionMessage);
			Utils.takeScreenshot(driver, "Captures", Thread.currentThread().getStackTrace()[2].getMethodName());
		}
		return element;
	}

	public synchronized void waitForElementToPresent(WebDriver driver, String object) throws Exception {
		int i = 0;
		while (isElementPresent(driver, object) != true) {
			Thread.sleep(100);
			i = i + 1;
			if (i == 30) {
				break;
			}
		}
	}

	public synchronized void switchToWindow(WebDriver driver, String parent) throws InterruptedException {
		// Handle windows change
		// String base = driver.getWindowHandle();
		try {

			Set<String> set = driver.getWindowHandles();
			// assert set.size() == 2;
			Thread.sleep(1000);
			// int size=set.size();
			for (String s : set) {
				if (!s.equals(parent)) {
					driver.switchTo().window(s);
					Thread.sleep(3000);
					break;
				}
			}
			Thread.sleep(1000);
			// System.out.println("First :" + (String) set.toArray()[0]);
			// //System.out.println("Second :" + (String) set.toArray()[1]);
			// // driver.context((String) set.toArray()[1]);
			//
			// driver.switchTo().window((String) set.toArray()[size-1]);
			// Thread.sleep(3000);

		} catch (Exception e) {
			System.out.println("failed in switching window, exception is" + e.getMessage());
		}
	}

	/**
	 * Method to scroll to end of page- In PLP if page gets loads after scrolling to
	 * end then the function check and wait for few seconds and scroll till end
	 * 
	 * @throws Exception
	 */
	public synchronized void scrollEndOfPage() throws Exception {
		try {
			// Scroll down to the end of page
			JavascriptExecutor js = ((JavascriptExecutor) driver);

			boolean scrollHeight = true;
			while (scrollHeight) {
				Object val = js.executeScript(
						"if ((window.innerHeight + window.scrollY) < document.body.offsetHeight){return true}else{return false}");

				if (val.toString().equals("true")) {
					// if not at the end of page
					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					// Wait till more products to get load
					Thread.sleep(5000);
				}
				// if reached to EOP then getting out of loop
				else
					scrollHeight = false;
			}
			ReporterLog.actionMsg("Reached to End of the Page");

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "PLP", "Left Navigation Panel Failed", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "PLP", "Left Navigation Panel Failed", driver);
		}
	}

	/**
	 * Method to take screenshot and save in a given foldername with given filename
	 * 
	 * @param driver
	 * @param WebDriver
	 *            folderName, fileName
	 * @throws Exception
	 * @throws IOException
	 */
	public synchronized static void takeScreenshot(Object driver, String folderName, String fileName) throws Exception {
		try {
			/**
			 * Get the current system date and time
			 */
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
			Date now = new Date();
			String strDate = sdfDate.format(now);
			String strTime = sdfTime.format(now);
			strTime = strTime.replace(":", "-");
			String sDateTime = "_D" + strDate + "_T" + strTime;

			/**
			 * Get the file path and create a folder by current date
			 */
			String filPath = "screenshots" + File.separator + "AT" + File.separator + folderName + File.separator
					+ strDate;
			File folderByDate = new File(filPath);
			folderByDate.mkdir();

			// Takes the screenshot when test fails
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(filPath + File.separator + fileName + sDateTime + ".JPEG"));

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Exception in TakeScreenshot Method|");
			throw e;
		}
	}

	public static class CustomException {

		/**
		 * Custom assertion method to print failure message and take screenshots with
		 * calling method name.
		 * 
		 * @param a
		 * @param errorMsg
		 * @param module
		 * @throws AssertionError
		 * @throws Exception
		 */
		public synchronized static void throwAssertionError(AssertionError a, String module, String errorMsg,
				Object driver) throws AssertionError, Exception {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| " + errorMsg);
			Utils.takeScreenshot(driver, module, Thread.currentThread().getStackTrace()[2].getMethodName());
			throw a;
		}

		/**
		 * Custom exception method to print failure message and take screenshots with
		 * calling method name.
		 * 
		 * @param e
		 * @param errorMsg
		 * @param module
		 * @throws Exception
		 */
		public synchronized static void throwExceptionError(Exception e, String module, String errorMsg, Object driver)
				throws Exception {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| " + errorMsg);
			Utils.takeScreenshot(driver, module, Thread.currentThread().getStackTrace()[2].getMethodName());
			throw e;
		}
	}

	/**
	 * Steps to initiate browser and open open application url 1. get browser from
	 * testdata sheet/testng.xml file 2. get application url from testdata sheet 3.
	 * get driver instance by calling getBrowser method 4. open browser and Ship To
	 * US if context changed to International
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized static WebDriver initiateBrowser(WebDriver driver) throws Exception {

		try {
			System.out.println("Driver in initiateDriver:" + driver);
			if (device.equals("desktop")) {
				driver.manage().window().maximize();
			}

			driver.get(baseUrl);
			System.out.println("after get url");
			ReporterLog.actionMsg("Open Website");
			waitForPageLoaded(driver);

			if (XLUtils.env.equalsIgnoreCase("perfb"))
				setCookievalue(driver);

			HandleBounceExchangePopUp(driver);

			// code to change country to US on full price sites
			if ((brand.equalsIgnoreCase("ATFP") || brand.equalsIgnoreCase("LOFP"))
					&& !driver.getCurrentUrl().contains("origin")) {
				changeCountry(driver);
			}

			Thread.sleep(3000);
			// new ScriptsStartup(driver);
		} catch (Exception e) {
			throw e;
		}
		return driver;
	}

	public static void changeCountry(WebDriver driver) throws Exception {

		By by = null;
		by = By.xpath("//img[@alt='ship to US']");

		// Site already opens in US mode
		if (isCountryUS(driver))
			return;

		WebElement HoverElement = driver.findElement(By.xpath("//div[@id='country-selector']//img[@alt='ship to IN']"));
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(HoverElement).build().perform();
		} catch (Exception e) {

			ReporterLog.fail("Not able to hover on " + HoverElement.toString());
		}

		by = By.xpath("//input[@value='SHIP TO THE U.S.' and @aria-label='Ship to the U.S.']");
		wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1,
				TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));

		driver.findElement(by).click();

		Thread.sleep(3000);
	}

	private static boolean isCountryUS(WebDriver driver) {

		return driver.findElement(By.xpath("//a[@class='international-shipping']/img")).getAttribute("alt").toString()
				.equalsIgnoreCase("ship to US");
	}

	@SuppressWarnings("unchecked")
	public synchronized static void pageRefresh(Object driver) throws Exception {
		if (driver.toString().contains("IOSDriver")) {
			((IOSDriver<WebElement>) driver).navigate().refresh();
			Thread.sleep(3000);
			Utils.waitForPageLoaded((IOSDriver<WebElement>) driver);
		} else {
			((WebDriver) driver).navigate().refresh();
			Thread.sleep(3000);
			Utils.waitForPageLoaded((WebDriver) driver);
		}
	}

	/**
	 * Method to find element and assert if null and print exception message, else
	 * click
	 * 
	 * @param driver
	 * @param objectString
	 * @param exceptionMessage
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized static WebElement findElementByLocatorAndClick(Object driver, String object,
			String exceptionMessage) throws Exception {
		try {
			XLUtils xlUtils = new XLUtils();
			locator = xlUtils.getObjectFromXML(object, Constants.ENVIRONMENT);
			element = getElement(locator, driver);
			Assert.assertTrue(element != null, exceptionMessage);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "Captures", exceptionMessage, driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "Captures", exceptionMessage, driver);
		}
		return element;
	}

	public synchronized static void HandleBounceExchangePopUp(WebDriver driver) throws Exception {
		if (bounceX_AT.equalsIgnoreCase("ON") && brand.equalsIgnoreCase("ATF")) {
			HandleBounceExchange(driver);
		}
		if (bounceX_LOFT.equalsIgnoreCase("ON") && brand.equalsIgnoreCase("LO")) {
			HandleBounceExchange(driver);
		}
		if (bounceX_LGFP.equalsIgnoreCase("ON") && Utils.brand.equalsIgnoreCase("LGFP")) {
			HandleBounceExchangeFP(driver);
		}
		if (bounceX_ATFP.equalsIgnoreCase("ON") && Utils.brand.equalsIgnoreCase("ATFP")) {
			HandleBounceExchangeFP(driver);
		}
		if (bounceX_LOFP.equalsIgnoreCase("ON") && Utils.brand.equalsIgnoreCase("LOFP")) {
			HandleBounceExchangeFP(driver);
		}
	}

	public synchronized static void HandleBounceExchange(WebDriver driver) throws Exception {

		Thread.sleep(10000);
		int count = 0;

		try {
			WebElement elem = driver.findElement(By.xpath("//*[text()='NO THANKS, I PREFER TO PAY FULL PRICE']"));
			elem.click();
			return;
		} catch (Exception e) {
			System.out.println("No Thanks I Prefer To Pay Full Price modal not present");
		}

		By by = By.xpath("//a[@class='bx-close bx-close-link bx-close-inside']");

		isElementsPresent(driver, by);
		for (WebElement popUp : driver.findElements(by)) {
			try {

				// Using Javascript click to close BounceX on Fatory sites in mobile
				if (isDeviceMobile()) {

					MobileView.switchToWebView(driver);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click()", popUp);
					count++;

				} else {
					if (popUp.findElement(By.xpath("./*[name()='svg']")).isDisplayed()) {
						popUp.findElement(By.xpath("./*[name()='svg']")).click();
						count++;
					}
				}
			} catch (Exception e) {

				System.out.println("BounceX detected but could not be closed");
			}
		}
		if (count == 0)
			System.out.println("No bounceX present");

		Thread.sleep(2000);
		Utils.waitForPageLoaded(driver);

	}

	public synchronized static void HandleBounceExchangeFP(IOSDriver<WebElement> driver) throws Exception {
		Thread.sleep(10000);
		List<WebElement> popUps = driver.findElements(By.xpath("//a[@class='bx-close bx-close-link bx-close-inside']"));
		for (WebElement popUp : popUps) {
			try {
				// System.out.println(popUp.isDisplayed());
				if (popUp.findElement(By.xpath("./*[name()='svg']")).isDisplayed()) {
					popUp.findElement(By.xpath("./*[name()='svg']")).click();
				}
			} catch (Exception e) {
			}
		}
		Thread.sleep(2000);
		waitForPageLoaded(driver);
	}

	public synchronized static void HandleBounceExchangeFP(WebDriver driver) throws Exception {

		By by = null;
		if (brand.equals("ATFP")) {
			by = By.id("closeButton");
		} else if (brand.equals("LOFP")) {
			by = By.xpath(".//*[text()='Continue Shopping']");
		} else if (brand.equals("LGFP")) {
			by = By.xpath("//*[text()='Continue without my intro offer']");
		}

		boolean flag = true;

		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(1,
					TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {

			flag = false;
			ReporterLog.warn("BouonceX not found on " + Utils.brand);
		}

		try {

			if (flag) {

				if (Utils.isDeviceMobile()) {

					MobileView.switchToWebView(driver);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click()", element);

				} else {

					driver.findElement(by).click();
				}
				System.out.println("Clicked on close icon of bounceX modal for " + brand);
				Thread.sleep(2000);

			} else {

				System.out.println("BounceX not found on " + brand);
			}
		} catch (Exception e) {

			ReporterLog.fail("Bounce X not closed");

		}
	}

	public static void setCookievalue(WebDriver driver) {
		try {
			String expCookieVal = Constants.THROTTLE;
			Cookie cookie = driver.manage().getCookieNamed("throttle");
			ReporterLog.actionMsg("Default Throttle Cookie value:" + cookie.getValue());
			// System.out.println("JSESSION Cookie value:"
			// +driver.manage().getCookieNamed("JSESSIONID").toString());
			if (!(cookie.getValue().equalsIgnoreCase(expCookieVal))) {
				driver.manage().deleteCookie(cookie);
				Cookie selcookie = new Cookie("throttle", expCookieVal);
				driver.manage().addCookie(selcookie);
				ReporterLog.actionMsg(
						"After setting throttle Cookie value:" + driver.manage().getCookieNamed("throttle").toString());

			}
			driver.navigate().refresh();
			Thread.sleep(1000);
			waitForPageLoaded(driver);
		} catch (Exception e) {
		}

	}

	// public void getCookievalue(WebDriver driver)throws Exception{
	// try{
	// Cookie cookie = driver.manage().getCookieNamed("throttle");
	// // System.out.println("On page: " + driver.getCurrentUrl() + " Throttle
	// Cookie value:" + cookie.getValue() + " Domain Name:
	// "+ cookie.getDomain() + "
	// Path: "+ cookie.getPath() );
	// // System.out.println("JSESSION Cookie value:"
	// +driver.manage().getCookieNamed("JSESSIONID").toString());
	//
	// ReporterLog.actionMsg("On page: " + driver.getCurrentUrl() + " Throttle
	// Cookie value:" + cookie.getValue() + " Domain Name:
	// "+ cookie.getDomain() + "
	// Path: "+ cookie.getPath() );
	// ReporterLog.actionMsg("JSESSION Cookie value:"
	// +driver.manage().getCookieNamed("JSESSIONID").toString());
	//
	// try{
	// Assert.assertTrue(cookie.getValue().equalsIgnoreCase(THROTTLE)," Throttle
	// cookie value is not same as: " + THROTTLE);
	// }
	// catch(AssertionError e)
	// {
	// ReporterLog.fail("Throttle Cookie value is not as expected: " +
	// THROTTLE);
	// throw(e);
	// }
	//
	// }catch(Exception e){}
	//
	// }
	public synchronized static IOSDriver<WebElement> getBrowserIosDriver(String sBrowser, String remote)
			throws Exception {
		IOSDriver<WebElement> loc_driver = null;
		if (sBrowser.equalsIgnoreCase("ios")) {

			// IOSDriver<WebElement> iosdriver= null;
			DesiredCapabilities capabilities = new DesiredCapabilities();
			deviceUDID = "a3ac68002fc0417db14fd80b5003918d25e532ba"; // un-comment
																		// it in
																		// case
																		// of
																		// real
																		// device

			String appPath = "/Users/pbhogr/Desktop/SafariApp11/SafariLauncher.app";
			// String appPath =
			// "/Users/dmish1/Desktop/Safari/SafariLauncher.app";
			// String appPath =
			// "/Users/agu261/Desktop/safariApp11/SafariLauncher.app";

			// String appPath =
			// "/Users/test/Library/Developer/Xcode/DerivedData/SafariLauncher-bdlexaesozvsqjfotybjbsufhakt/Build/Products/Debug-iphonesimulator/SafariLauncher.app";

			if (deviceUDID != null) {
				p = Runtime.getRuntime().exec(
						"/usr/local/Cellar/ios-webkit-debug-proxy/1.8/bin/ios_webkit_debug_proxy -c a3ac68002fc0417db14fd80b5003918d25e532ba:27753");

				// /
				// usr/local/Cellar/ios-webkit-debug-proxy/HEAD-55d0a95/bin/ios_webkit_debug_proxy
				// /
				// usr/local/Cellar/ios-webkit-debug-proxy/1.7.1/bin/ios_webkit_debug_proxy
				/**
				 * CAPABILITIES SETTING FOR LAUNCHING THE APP ON REAL DEVICE STARTS
				 * -----########
				 */
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
				capabilities.setCapability("browser", "");
				capabilities.setCapability(MobileCapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
				capabilities.setCapability("safariAllowPopups", false);
				capabilities.setCapability("safariIgnoreFraudWarning", true);
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability("automationName", "XCUITest");
				// capabilities.setCapability("showXcodeLog", true);
				SafariOptions safariOptions = new SafariOptions();

				safariOptions.setUseCleanSession(true);
				capabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
				capabilities.setCapability("udid", deviceUDID);
				capabilities.setCapability("bundleId", "com.ann.safariLauncher-parvi");

				capabilities.setCapability("useNewWDA", true);
				capabilities.setCapability("wdaLocalPort", 8200);
				capabilities.setCapability("app", appPath);
				File app = new File(appPath);
				capabilities.setCapability("app", app.getAbsolutePath());

				/**
				 * CAPABILITIES SETTING FOR REAL DEVICE ENDS -------#######
				 */

			} else if (deviceUDID == null) {

				/**
				 * CAPABILITIES SETTING FOR LAUNCHING THE APP ON SIMULATOR STARTS -----########
				 */
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
				capabilities.setCapability("browser", "");
				capabilities.setCapability(MobileCapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Plus");
				capabilities.setCapability("safariAllowPopups", false);
				capabilities.setCapability("safariIgnoreFraudWarning", true);
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability("automationName", "XCUITest");
				// capabilities.setCapability("showXcodeLog", true);
				SafariOptions safariOptions = new SafariOptions();

				safariOptions.setUseCleanSession(true);
				capabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
				capabilities.setCapability("udid", deviceUDID);
				capabilities.setCapability("bundleId", "com.ann.safariLauncher-parvi");

				capabilities.setCapability("useNewWDA", true);
				capabilities.setCapability("wdaLocalPort", 8200);
				capabilities.setCapability("app", appPath);
				File app = new File(appPath);
				capabilities.setCapability("app", app.getAbsolutePath());

				/**
				 * CAPABILITIES SETTING FOR SIMULATOR ENDS -------#######
				 */

			}

			loc_driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			Thread.sleep(5000);
			loc_driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			System.out.println("Driver :" + loc_driver);
			MobileView.switchToWebView(loc_driver);
			// driver=loc_driver;

		}
		return loc_driver;
	}

	/**
	 * Initiate driver per provided browser string
	 * 
	 * @return driver
	 * @param sBrowser
	 *            , driver
	 * @throws Exception
	 */
	public synchronized static WebDriver getBrowser(String sBrowser, String remote) throws Exception {
		try {

			String os = System.getProperty("os.name").toLowerCase();
			// Setting relative path of driver
			if (sBrowser.equalsIgnoreCase("firefox")) {
				if (remote.equalsIgnoreCase("yes")) {
					driver = new RemoteWebDriver(new URL("http://192.168.130.30:4444/wd/hub"),
							DesiredCapabilities.firefox());
				} else {

					File ff = null;
					if (os.contains("mac")) {
						System.out.println(os + "OS Name");
						ff = new File("drivers" + File.separator + "geckodriver");
					} else {
						System.out.println(os + "OS Name");
						ff = new File("drivers" + File.separator + "geckodriver.exe");
					}
					DesiredCapabilities capabilities = new DesiredCapabilities();
					// FirefoxOptions options = new FirefoxOptions();
					capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					capabilities.setCapability("marionette", false);

					// capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS,
					// options);
					System.setProperty("webdriver.gecko.driver", ff.toString());
					System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
					driver = new FirefoxDriver();
				}

			} else if (sBrowser.equalsIgnoreCase("chrome")) {

				if (remote.equalsIgnoreCase("yes")) {
					driver = new RemoteWebDriver(new URL("http://192.168.130.30:4444/wd/hub"),
							DesiredCapabilities.chrome());
				} else {
					File chrome = null;
					System.out.println("OS Name=" + os);
					if (os.contains("mac")) {
						chrome = new File("drivers" + File.separator + "chromedriver");
					} else if (os.contains("unix") || os.contains("linux")) {
						chrome = new File("drivers" + File.separator + "chromedriver");
					} else {
						chrome = new File("drivers" + File.separator + "chromedriver.exe");
					}
					System.setProperty("webdriver.chrome.driver", chrome.toString());
					DesiredCapabilities capabilities = new DesiredCapabilities();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("test-type");
					options.addArguments("--disable-extensions");
					capabilities.setCapability("chrome.binary", chrome);
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					driver = new ChromeDriver(capabilities);

				}

			} else if (sBrowser.equalsIgnoreCase("iexplore")) {

				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,
						UnexpectedAlertBehaviour.IGNORE);
				capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
				capabilities.setJavascriptEnabled(true);
				// InternetExplorerOptions opt = new InternetExplorerOptions();
				// opt.merge(capabilities);
				File iexplore = new File("drivers" + File.separator + "IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", iexplore.toString());
				driver = new InternetExplorerDriver(capabilities);
			} else if (sBrowser.equalsIgnoreCase("safari")) {
				// Using the Safari technology preview
				SafariOptions s = new SafariOptions();
				s.setUseCleanSession(true);
				// s.setUseTechnologyPreview(true); //TO DO -uncomment
				// if(!s.getUseTechnologyPreview())
				// Assert.fail("Driver Not initialized properly");
				// driver = new SafariDriver(s);
				// driver=new RemoteWebDriver(new
				// URL("http://10.207.184.188:4444/wd/hub"),DesiredCapabilities.safari());
			} else if (sBrowser.equalsIgnoreCase("ios")) {
				IOSDriver<WebElement> loc_driver = null;
				// IOSDriver<WebElement> iosdriver= null;
				DesiredCapabilities capabilities = new DesiredCapabilities();
				deviceUDID = "a3ac68002fc0417db14fd80b5003918d25e532ba"; // un-comment
																			// it
																			// in
																			// case
																			// of
																			// real
																			// device

				String appPath = "/Users/anigupta2/Desktop/SafariApp11/SafariLauncher.app";
				// String appPath =
				// "/Users/dmish1/Desktop/Safari/SafariLauncher.app";
				// String appPath =
				// "/Users/anigupta2/Desktop/safariApp11/SafariLauncher.app";
				// String appPath =
				// "/Users/test/Library/Developer/Xcode/DerivedData/SafariLauncher-bdlexaesozvsqjfotybjbsufhakt/Build/Products/Debug-iphonesimulator/SafariLauncher.app";

				if (deviceUDID != null) {
					p = Runtime.getRuntime().exec(
							"/usr/local/Cellar/ios-webkit-debug-proxy/1.8/bin/ios_webkit_debug_proxy -c a3ac68002fc0417db14fd80b5003918d25e532ba:27753");

					// /
					// usr/local/Cellar/ios-webkit-debug-proxy/HEAD-55d0a95/bin/ios_webkit_debug_proxy
					// /
					// usr/local/Cellar/ios-webkit-debug-proxy/1.7.1/bin/ios_webkit_debug_proxy
					/**
					 * CAPABILITIES SETTING FOR LAUNCHING THE APP ON REAL DEVICE STARTS
					 * -----########
					 */
					capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
					capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
					capabilities.setCapability("browser", "");
					capabilities.setCapability(MobileCapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
					capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
					capabilities.setCapability("safariAllowPopups", false);
					capabilities.setCapability("safariIgnoreFraudWarning", true);
					capabilities.setCapability("autoAcceptAlerts", true);
					capabilities.setCapability("automationName", "XCUITest");
					// capabilities.setCapability("showXcodeLog", true);
					SafariOptions safariOptions = new SafariOptions();

					safariOptions.setUseCleanSession(true);
					capabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
					capabilities.setCapability("udid", deviceUDID);
					capabilities.setCapability("bundleId", "com.ann.safariLauncher-A");

					capabilities.setCapability("useNewWDA", true);
					capabilities.setCapability("wdaLocalPort", 8200);
					capabilities.setCapability("app", appPath);
					File app = new File(appPath);
					capabilities.setCapability("app", app.getAbsolutePath());

					/**
					 * CAPABILITIES SETTING FOR REAL DEVICE ENDS -------#######
					 */

				} else if (deviceUDID == null) {

					/**
					 * CAPABILITIES SETTING FOR LAUNCHING THE APP ON SIMULATOR STARTS -----########
					 */
					capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
					capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
					capabilities.setCapability("browser", "");
					capabilities.setCapability(MobileCapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
					capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Plus");
					capabilities.setCapability("safariAllowPopups", false);
					capabilities.setCapability("safariIgnoreFraudWarning", true);
					capabilities.setCapability("autoAcceptAlerts", true);
					capabilities.setCapability("automationName", "XCUITest");
					// capabilities.setCapability("showXcodeLog", true);
					SafariOptions safariOptions = new SafariOptions();

					safariOptions.setUseCleanSession(true);
					capabilities.setCapability(SafariOptions.CAPABILITY, safariOptions);
					capabilities.setCapability("udid", deviceUDID);
					capabilities.setCapability("bundleId", "com.ann.safariLauncher-A");

					capabilities.setCapability("useNewWDA", true);
					capabilities.setCapability("wdaLocalPort", 8200);
					capabilities.setCapability("app", appPath);
					File app = new File(appPath);
					capabilities.setCapability("app", app.getAbsolutePath());

					/**
					 * CAPABILITIES SETTING FOR SIMULATOR ENDS -------#######
					 */

				}

				loc_driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				Thread.sleep(5000);
				loc_driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				System.out.println("Driver :" + loc_driver);
				MobileView.switchToWebView(loc_driver);
				driver = loc_driver;
			} else if (sBrowser.equalsIgnoreCase("android")) {
				DesiredCapabilities capabilities = DesiredCapabilities.android();
				// AndroidDriver<WebElement> anddriver;
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
				capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
				capabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);

				// Create object of URL class and specify the appium server
				// address
				URL url = new URL("http://127.0.0.1:4723/wd/hub");

				// Create object of AndroidDriver class and pass the url and
				// capability that we
				// created
				driver = new AndroidDriver<WebElement>(url, capabilities);
				MobileView.switchToWebView(driver);

			}

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);

			System.out.println("Driver Setup Successfully");
			Reporter.log(Constants.DELIMITER + "Driver Setup Successfully");
		}

		catch (Exception e) {
			System.out.println("Driver not initiated " + e);
		}
		return driver;

	}

	public synchronized static void waitForPageLoaded(WebDriver driver) throws InterruptedException {
		if (device.equals("mobile")) {
			MobileView.switchToWebView(driver);
		}
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(expectation);
			Thread.sleep(1000);
		} catch (Throwable error) {
			ReporterLog.warn("Timeout waiting for Page Load Request to complete.");
		}
		// try{
		// if(driver.findElement(By.xpath("//*[contains(@class,'loading')]")).isDisplayed())
		// { Thread.sleep(10000);
		// }
		// }
		// catch(NoSuchElementException a){
		//
		// }
	}

	public synchronized void tooltipinfovalidation(WebDriver driver, String Object1, String Object2, String msg)
			throws Exception {
		String value;
		WebElement ele = findElementByLocator(driver, Object1, Object1 + "not found");
		if (device.equals("desktop")) {
			mouseHoverJScript(driver, ele);
		} else {
			scrollDownToElement(driver, ele);
			Thread.sleep(2000);
			if (Utils.browser.equals("android")) {
				ele.click();
			} else {
				if (Object1.equals("toolTip_ShippingMethod_Header") && Utils.browser.equals("ios")) {
					homepage = new HomePageObjects(driver, brand, browser, device);

					TouchAction a = new TouchAction((MobileDriver) driver);
					int x = (int) homepage.getTheElementLocations(ele, "x-axis");
					int y = (int) homepage.getTheElementLocations(ele, "y-axis");
					a.tap(x, y + 80).perform();
				} else
					ele.click();
			}
			Thread.sleep(2000);
		}
		WebElement ele1 = findElementByLocator(driver, Object2, Object2 + "not found");
		value = ele1.getText().trim().replaceAll("[\\n\\r]", " ");

		System.out.println(value);
		try {
			System.out.println(msg);
			Assert.assertEquals(value, msg);
			ReporterLog.pass("| Order Summary : shipping Taxes info message  Display");

		} catch (Exception e) {
			ReporterLog.fail("| Order Summary : shipping Taxes info message not  Display");

			e.printStackTrace();
		}

	}

	public synchronized void toolTipInfoValidationContains(WebDriver driver, String Object1, String Object2, String msg)
			throws Exception {
		String value;
		WebElement ele = findElementByLocator(driver, Object1, Object1 + "not found");
		if (device.equals("desktop")) {
			mouseHoverJScript(driver, ele);
		} else {
			scrollDownToElement(driver, ele);
			Thread.sleep(2000);
			// TouchAction a = new TouchAction((IOSDriver)driver);
			// a.tap(ele);
			if (Utils.browser.equals("android")) {
				ele.click();
			}
			ele.click();
			Thread.sleep(2000);
		}
		WebElement ele1 = findElementByLocator(driver, Object2, Object2 + "not found");
		value = ele1.getText();

		System.out.println(value);
		try {
			Assert.assertTrue(value.contains(msg));
			ReporterLog.pass("| Validation : ToolTip is Displayed");

		} catch (Exception e) {
			ReporterLog.fail("| Validation : ToolTip is not Displayed");
			e.printStackTrace();
		}

	}

	/**
	 * Move the mouse to the particular location.
	 *
	 * @return void
	 * @throws Exception
	 */

	public synchronized void mouseHoverJScript(WebDriver driver, WebElement HoverElement) throws Exception {

		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);
	}

	public synchronized void mouseHoverScript(WebDriver driver, WebElement HoverElement) throws Exception {

		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(HoverElement).build().perform();
		} catch (Exception e) {

			ReporterLog.fail("Not able to hover on " + HoverElement.toString());
		}
	}

	/**
	 * Method to scrolldown to x position
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public synchronized void scrollDownByxPostion(WebDriver driver, int xPos) throws Exception {
		try {
			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			JavascriptExecutor jseScrollDown = (JavascriptExecutor) driver;
			jseScrollDown.executeScript("scroll(0, " + xPos + ")");
			Thread.sleep(500);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * This class contains custom reporter methods to print status of scripts as
	 * pass, Constants.FAIL and print header message.
	 * 
	 * @author hmarka
	 *
	 */
	public static class ReporterLog extends Reporter {

		/**
		 * Method to print pass message on reporter logs.
		 * 
		 * @param passMsg
		 */
		public synchronized static void pass(String passMsg) {
			Reporter.log(Constants.DELIMITER + Constants.PASS + "| " + passMsg);
		}

		/**
		 * Method to print Constants.FAIL message on reporter logs.
		 * 
		 * @param passMsg
		 */
		public synchronized static void fail(String failMsg) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| " + failMsg);
		}

		/**
		 * Method to print warn message on reporter logs.
		 * 
		 * @param passMsg
		 */
		public synchronized static void warn(String failMsg) {
			Reporter.log(Constants.DELIMITER + Constants.WARN + "| " + failMsg);
		}

		/**
		 * Method to print header message on reporter log.
		 * 
		 * @param headerMsg
		 */
		public synchronized static void headerString(String headerMsg) {
			Reporter.log(Constants.DELIMITER + "***** " + headerMsg + " *****" + Constants.DELIMITER);
		}

		/**
		 * Method to print action message on reporter log.
		 * 
		 * @param actionMsg
		 */
		public synchronized static void actionMsg(String actionMsg) {
			LogDefect_JIRA.steps = LogDefect_JIRA.steps + "\n" + actionMsg;
			Reporter.log(Constants.DELIMITER + "| " + actionMsg);
		}
	}

	public static class MobileView {
		public static Set<String> contextNames = null;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void switchToWebView(WebDriver driver) {
			if (browser.equals("ios")) {
				contextNames = ((IOSDriver) driver).getContextHandles();
			} else {
				contextNames = ((AppiumDriver) driver).getContextHandles();
			}
			for (String contextName : contextNames) {
				if (browser.equals("`") && contextName.contains("CHROMIUM")) {
					((AppiumDriver) driver).context(contextName);
					break;
				} else if (browser.equals("ios") && contextName.contains("WEB")) {
					((IOSDriver) driver).context(contextName);
					break;
				}
			}
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static void switchToNative(WebDriver driver) {
			if (browser.equals("ios")) {
				contextNames = ((IOSDriver) driver).getContextHandles();
			} else {
				contextNames = ((AppiumDriver) driver).getContextHandles();
			}
			for (String contextName : contextNames) {
				if (browser.equals("android") && contextName.contains("NATIVE")) {

					((AppiumDriver) driver).context(contextName);
					break;
				}
				if (browser.equals("ios") && contextName.contains("NATIVE")) {

					((IOSDriver) driver).context(contextName);
					break;
				}
			}
		}
	}

	public void waitForElementPresent(WebElement element, WebDriver driver) throws Exception {
		try {

			if (device.equals("mobile")) {
				MobileView.switchToWebView(driver);
			}
			WebDriverWait wait = new WebDriverWait(driver, 20000);
			wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			Assert.fail("Element not present within the time limit" + e);
			throw e;
		}
	}

	/**
	 * @throws Exception
	 * @throws AssertionError
	 * @param: SubCategory
	 *             position starting from top
	 * @param: MegaMenu
	 *             position starting from left
	 * @param: Sub
	 *             category type - Feature or not
	 * @description : This function is used to click on specific category from Top
	 *              category
	 */

	public synchronized void moveToSpecificSubCategoryTab(WebDriver driver, int SubcategoryPosition,
			int MegaMenuPosition, String SubCategoryType) throws AssertionError, Exception {
		int subType = 1;
		try {
			if (SubCategoryType.equals("Features"))
				subType = 2;

			String megaMenuXpath = ".//*[@data-slnm-id='navbar']/ul/li[" + MegaMenuPosition + "]";
			WebElement megMenuEle = driver.findElement(By.xpath(megaMenuXpath));
			mouseHoverJScript(driver, megMenuEle);
			String subCategoryListXpath = ".//*[@data-slnm-id='megaMenu']/div[" + subType + "]/ul/li["
					+ SubcategoryPosition + "]/a";
			WebElement subCategoryEle = driver.findElement(By.xpath(subCategoryListXpath));
			clickWebElement(driver, subCategoryEle, "Not clicked on Sub Category list");

		} catch (Exception e) {
			CustomException.throwExceptionError(e, "HomePage", "Browser not initiated", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "HomePage", "Browser not initiated", driver);
		}
	}

	/**
	 * @throws Exception
	 * @param: String
	 * @description: clicked on the specific product under the PLP page.
	 * 
	 */

	public synchronized void clickOnSpecificProductUnderPLP(WebDriver driver, String productName) throws Exception {
		try {
			String productXpath = ".//*[conatins(text(),'" + productName + "')]";
			WebElement productNameEle = driver.findElement(By.xpath(productXpath));
			clickWebElement(driver, productNameEle, "Product Not found on PLP page");
		} catch (Exception e) {
			CustomException.throwExceptionError(e, "HomePage", "Browser not initiated", driver);
		} catch (AssertionError e) {
			CustomException.throwAssertionError(e, "HomePage", "Browser not initiated", driver);
		}

	}

	/**
	 * Method to enter data in text box - MOBILE
	 * 
	 * @param fieldName
	 * @param data
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public synchronized void clickOnTextBox_EnterData_Mobile(String fieldName, String data) throws Exception {
		try {
			MobileView.switchToNative(driver);
			@SuppressWarnings("rawtypes")
			List<WebElement> textBoxes = ((IOSDriver) driver).findElements(By.className("XCUIElementTypeTextField"));
			for (WebElement textBox : textBoxes) {
				if (textBox.getAttribute("value") != null) {
					if (textBox.getAttribute("value").equalsIgnoreCase(fieldName)) {
						textBox.click();
						break;
					}
				}
			}

			((IOSDriver<WebElement>) driver).getKeyboard().sendKeys(data);
			Thread.sleep(2000);

			findElementByIOSNameLocator("Done").click();
			Thread.sleep(2000);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to enter data in text box - MOBILE
	 * 
	 * @param fieldName
	 * @param data
	 * @throws Exception
	 */
	public synchronized void enterData_MobileIOS_Label(String fieldName, String data) throws Exception {
		try {
			MobileView.switchToNative(driver);
			List<WebElement> textBoxes = ((IOSDriver) driver).findElements(By.className("XCUIElementTypeTextField"));
			for (WebElement textBox : textBoxes) {
				if (textBox.getAttribute("label") != null) {
					if (textBox.getAttribute("label").contains(fieldName)) {
						textBox.click();
						break;
					}
				}
			}

			((IOSDriver<WebElement>) driver).getKeyboard().sendKeys(data);
			Thread.sleep(2000);

			findElementByIOSNameLocator("Done").click();
			Thread.sleep(2000);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method to enter data in text area - MOBILE
	 * 
	 * @param fieldName
	 * @param data
	 * @throws Exception
	 */
	public synchronized void clickOnTextArea_EnterData_Mobile(String fieldName, String data) throws Exception {
		try {
			MobileView.switchToNative(driver);
			List<WebElement> textBoxes = ((IOSDriver) driver).findElements(By.className("XCUIElementTypeTextView"));
			for (WebElement textBox : textBoxes) {
				if (textBox.getAttribute("value") != null) {
					if (textBox.getAttribute("value").equalsIgnoreCase(fieldName)) {
						textBox.click();
						break;
					}
				}
			}

			((IOSDriver<WebElement>) driver).getKeyboard().sendKeys(data);
			Thread.sleep(2000);

			findElementByIOSNameLocator("Done").click();
			Thread.sleep(2000);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Waiting for Web Element to be present max time of 30 sec to wait
	 */

	public synchronized void waitForElementToBeVisible(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 70000);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * * Waiting for Web Elements to be present max time of 30 sec to wait
	 */
	public synchronized void waitForElementsToBeVisible(WebDriver driver, List<WebElement> element) {

		WebDriverWait wait = new WebDriverWait(driver, 50000);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	/**
	 * Delete the text entered in the IOS text field
	 * 
	 * @param textSize
	 *            : size of text want to delete
	 * @throws Exception
	 */
	public synchronized void deleteTextFromTextBoxIOS(int textSize) throws Exception {
		try {
			// Keypad should be opened
			if (textSize != 0) {
				ReporterLog.actionMsg("Deleting the value from the field");
				MobileView.switchToNative(driver);
				WebElement element = findElementByIOSNameLocator("delete");
				for (int i = 0; i < textSize; i++) {
					element.click();
				}
			}
		} catch (Exception e) {
			try {

				if (textSize != 0) {
					ReporterLog.actionMsg("Deleting the value from the field");
					MobileView.switchToNative(driver);
					WebElement element = findElementByIOSNameLocator("Delete");
					for (int i = 0; i < textSize; i++) {
						element.click();
					}
				}

			} catch (Exception e1) {
				throw (e1);
			}

		}
	}

	/**
	 * Opening the IOS keypad
	 * 
	 * @param valueInTextField
	 *            : The present value displayed on the field
	 * @param field
	 *            : type of field like textbox,textarea etc
	 * @throws InterruptedException
	 */
	public synchronized void openIOSKeyboard(String valueInTextField, String field) throws InterruptedException {
		try {
			MobileView.switchToNative(driver);
			if (field.equals("textbox")) {
				List<WebElement> textBoxes = ((IOSDriver) driver)
						.findElements(By.className("XCUIElementTypeTextField"));
				for (WebElement textBox : textBoxes) {
					if (textBox.getAttribute("value") != null) {
						if (textBox.getAttribute("value").equalsIgnoreCase(valueInTextField)) {
							textBox.click();
							break;
						}
					}
				}
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Getting the IOS Web Elements based on the field name
	 * 
	 * @param valueInTextField
	 *            : The present value displayed on the field
	 * @param field
	 *            : type of field like textbox,textarea etc
	 * @return: WebElement
	 * @throws Exception 
	 */
	public synchronized WebElement getElementIOS(String valueInTextField, String field) throws Exception {
		try {
			MobileView.switchToNative(driver);
			if (field.equals("textbox")) {
				List<WebElement> textBoxes = ((IOSDriver) driver)
						.findElements(By.className("XCUIElementTypeTextField"));
				for (WebElement textBox : textBoxes) {
					if (textBox.getAttribute("value") != null) {
						if (textBox.getAttribute("value").equalsIgnoreCase(valueInTextField)) {
							return textBox;
						}
					}
				}
			}

			if (field.equals("textbox_label")) {
				List<WebElement> textBoxes = ((IOSDriver) driver)
						.findElements(By.className("XCUIElementTypeTextField"));
				for (WebElement textBox : textBoxes) {
					if (textBox.getAttribute("label") != null) {
						if (textBox.getAttribute("label").equalsIgnoreCase(valueInTextField)) {
							return textBox;
						}
					}
				}
			}
			Thread.sleep(2000);
			return null;
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Select Value from dropdown in Android
	 * 
	 * @param locator
	 * @param valueToBeSelected
	 * @throws Exception
	 */
	public synchronized void selectValueFromDropDown_Android(String locator, String valueToBeSelected)
			throws Exception {
		try {
			List<WebElement> OptionList = findElementByAndroidClassNameLocator(locator);

			for (int i = 0; i < OptionList.size(); i++) {
				if (OptionList.get(i).getAttribute("text").equals(valueToBeSelected)) {
					OptionList.get(i).click();
					Utils.waitForPageLoaded(driver);
				}
			}

		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * check the value in drop down in Android
	 * 
	 * @param locator
	 * @param data
	 * @param dropdownElement
	 * @throws Exception
	 */
	public synchronized void checkValueInDropDown_Android(String locator, String data, WebElement dropdownElement)
			throws Exception {
		try {
			List<WebElement> OptionList = findElementByAndroidClassNameLocator(locator);

			String pickervalue[] = data.split(",");

			if (OptionList.size() == pickervalue.length + 1) {
				ReporterLog.fail("More values is coming in month field");
				Assert.fail();
			}
			for (int i = 0; i < OptionList.size(); i++) {
				if (i != 0)
					dropdownElement.click();

				if (OptionList.get(i).getAttribute("text").equals(pickervalue[i])) {
					OptionList.get(i).click();
					Utils.waitForPageLoaded(driver);
				}
			}
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Return Digits
	 * 
	 * @param str
	 * @return
	 */
	public synchronized String returnDigits(String str) {
		String number = "";
		String letter = "";
		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if (Character.isDigit(a)) {
				number = number + a;

			} else {
				letter = letter + a;

			}
		}
		return number;
	}

	public synchronized boolean isAndroidIdElementPresent(String locator) {
		try {

			androidLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);

			Thread.sleep(1000);
			element = driver.findElement(By.id(androidLocator));

			waitandroid = new FluentWait<AppiumDriver<WebElement>>((AppiumDriver<WebElement>) driver)
					.withTimeout(20, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.id(androidLocator)));

			// highlightElement(driver, element);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public synchronized void waitForAndroidIdElementToProceed(String object) throws Exception {
		int i = 0;
		while (isAndroidIdElementPresent(object) != true) {
			Thread.sleep(100);
			i = i + 1;
			if (i == 30) {
				break;
			}
		}
		Thread.sleep(500);
	}

	/*
	 * Method to return list of web elements Android
	 */
	public List<WebElement> findElementsByAndroidClassNameLocator(String locator) throws Exception {
		List<WebElement> elements = null;
		try {
			androidLocator = xlUtils.getXpathFromXML(locator, Constants.ENVIRONMENT);
			MobileView.switchToNative(driver);
			Thread.sleep(1000);
			elements = ((AndroidDriver) driver).findElements(By.className(androidLocator));

		} catch (Exception e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		} catch (AssertionError e) {
			// CommonScripts.MobileView.switchToWebView();
			throw (e);
		}
		return elements;
	}

	/**
	 * Method to check if the current brans is a full price brand or nor, namely
	 * Anntaylor, Loft or Lou & Grey
	 * 
	 * @return true or false
	 */
	public static synchronized boolean isBrandFullPrice() {

		String[] brandList = new String[3];
		brandList[0] = "ATFP"; // Anntaylor Full Price Brand
		brandList[1] = "LOFP"; // Loft Full Price Brand
		brandList[2] = "LGFP"; // Lou & Grey Full Price Brand

		for (String brand : brandList) {
			if (Utils.brand.equalsIgnoreCase(brand))
				return true;
		}
		return false;
	}

//	public void setupAUTTestRecorder(String className) {
//
//		try {
//
//			String path = System.getProperty("user.dir") + File.separator + "/src/test/resources/ScriptVideos" + File.separator;
//			recorder = new ATUTestRecorder(path, className, false);
//			recorder.start();
//			ReporterLog.log("Test Recorder Started");
//
//		} catch (ATUTestRecorderException e) {
//
//			e.printStackTrace();
//			ReporterLog.warn("AUTTestRecorder not setup");
//		}
//	}
//
//	public void stopAUTTestRecorder() {
//
//		try {
//
//			recorder.stop();
//			ReporterLog.log("Test Recorder Stopped");
//
//		} catch (ATUTestRecorderException e) {
//
//			ReporterLog.warn("AUTTestRecorder not stoped");
//		}
//	}

	public static boolean isDeviceMobile() {

		ReporterLog.log("Checking if script is running on mobile...");

		if (Utils.device.equalsIgnoreCase(Constants.DEVICE_TYPE_MOBILE)) {

			ReporterLog.log("Script is runnig on mobile...");
			return true;
		}

		ReporterLog.log("Script is not runnig on mobile...");
		return false;
	}

	public static void handlePopUps() {

		List<WebElement> elementList = driver
				.findElements(By.xpath("//*[@class='bx-close bx-close-link bx-close-inside']"));

		if (!elementList.isEmpty()) {

			WebElement element = elementList.get(0);

			try {

				element.click();

			} catch (Exception e) {

				System.out.println("BounceX not detected on PDP page of " + Utils.brand);
			}
		}
	}
}
