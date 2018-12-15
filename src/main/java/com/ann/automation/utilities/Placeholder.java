package com.ann.automation.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.ann.automation.manager.WebDriverType;
//import atu.testrecorder.ATUTestRecorder;
import com.ann.automation.pages.CheckoutPageObject;
import com.ann.automation.pages.CustomerServiceObjects;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.LoginPageObjects;
import com.ann.automation.pages.MyAccountPageObject;
import com.ann.automation.pages.OrderReviewPageObject;
import com.ann.automation.pages.PDPPageObject;
import com.ann.automation.pages.PLPPageObject;
import com.ann.automation.pages.PaymentPageObject;
import com.ann.automation.pages.SearchPageObject;
import com.ann.automation.pages.ShippingPageObject;
import com.ann.automation.pages.StoreLocatorPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class Placeholder {

	// ************ Declaration & Initialization of all the Variables & Page Objects
	public static Placeholder pholder;
	public static Properties props;
	String brandValue, size = null, color = null;
	DesiredCapabilities dCaps = null;
	private final Logger LOG = LogManager.getLogger(this.getClass().getSimpleName());
	String osName = System.getProperty("os.name").toUpperCase();
	String osArch = System.getProperty("os.arch").toUpperCase();
	static InputStream input = null;
	public static final ThreadLocal<WebDriver> WebDriverThreadLocal = new InheritableThreadLocal<WebDriver>();
	public static WebDriver driver = null;
	public Utils utils = null;
	public XLUtils XLUtilsObj = null;
	// public ATUTestRecorder recorder;
	protected HomePageObjects homepage = null;
	protected PLPPageObject plpPage = null;
	protected SearchPageObject searchPage = null;
	protected PDPPageObject pdpPage = null;
	protected LoginPageObjects loginPage = null;
	protected MyAccountPageObject myaccount = null;
	protected CheckoutPageObject checkout;
	protected ShippingPageObject shippingObj;
	protected PaymentPageObject paymentObj = null;
	protected OrderReviewPageObject orderReviewObj = null;
	public CustomerServiceObjects customerService = null;
	public StoreLocatorPageObject storeLocatorPage = null;
	protected List<String> shippingAddr = new ArrayList<String>();
	protected StringBuffer errorBuffer = new StringBuffer();
	private static Placeholder runTimeInstance = new Placeholder();
	int implicitWait=0;
	String browser = null, device = null, broswerVersion = null, browserHeadlessMode=null, remoteLevel=null;
	
	//************************** ()()()() Method To Launch Browser ()()()() ****************************  
	public synchronized WebDriver getBrowserInstantiation() {
		//--------------- Reading Browser's Configuration from JSON ---------------
		browser = stringDataReaderJSON("browser");
		device = stringDataReaderJSON("device");
		browserHeadlessMode = stringDataReaderJSON("browserHeadlessMode");
		remoteLevel = stringDataReaderJSON("remoteLevel");
		//implicitWait = intDataReaderJSON("implicitWait");
		//-------------------------------------------------------------------------
		try {
			switch (browser.toUpperCase()) {
			case WebDriverType.CHROME:
				if (WebDriverType.REMOTEWEBDRIVER.equalsIgnoreCase(remoteLevel)) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
					options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
					options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					options.setAcceptInsecureCerts(true);
					if (browserHeadlessMode.equalsIgnoreCase("enable")) {
						options.setHeadless(true);
					}
					options.addArguments("--ignore-certificate-errors");
					options.addArguments("disable-infobars");
					options.addArguments("test-type");
					options.addArguments("--disable-extensions");
					options.addArguments("--disable-notifications");
					options.addArguments("--disable-component-update");
					options.addArguments("start-maximized");
					driver = new ChromeDriver(options);
					driver.manage().deleteAllCookies();
					LOG.info("Chrome Browser Launched on : " + osName + " | Downloaded Version : "+ WebDriverManager.chromedriver().getDownloadedVersion());
					break;
				}else {
					driver = new RemoteWebDriver(new URL("http://192.168.130.30:4444/wd/hub"),
							DesiredCapabilities.chrome());
					break;
				}
			case WebDriverType.FIREFOX:
				if (WebDriverType.REMOTEWEBDRIVER.equalsIgnoreCase(remoteLevel)) {
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions options = new FirefoxOptions();
					options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
					options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
					options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					options.setAcceptInsecureCerts(true);
					if (props.getProperty("browserHeadlessMode").equalsIgnoreCase("enable")) {
						options.setHeadless(false);
					}
					options.setCapability("ignoreProtectedModeSettings", true);
					options.setCapability("marionette", false);
					options.addArguments("test-type");
					options.addArguments("--disable-extensions");
					options.addArguments("--disable-notifications");
					options.addArguments("start-maximized");
					driver = new FirefoxDriver(options);
					driver.manage().deleteAllCookies();
					LOG.info("Firefox Browser Launched on : " + osName + " | Downloaded Version : "+ WebDriverManager.firefoxdriver().getDownloadedVersion());
					break;
				}else {
					driver = new RemoteWebDriver(new URL("http://192.168.130.30:4444/wd/hub"),
							DesiredCapabilities.firefox());
					break;
				}	
			case WebDriverType.INTERNETEXPLORER:
				if (WebDriverType.REMOTEWEBDRIVER.equalsIgnoreCase(remoteLevel)) {
					WebDriverManager.iedriver().setup();
					InternetExplorerOptions options = new InternetExplorerOptions();
					options.setCapability("ignoreProtectedModeSettings", true);
					options.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
					options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
					options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					options.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
					options.setCapability("ignoreZoomSetting", true);
					options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
					options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
					options.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,UnexpectedAlertBehaviour.IGNORE);
					options.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
					driver = new InternetExplorerDriver(options);
					driver.manage().deleteAllCookies();
					LOG.info("IE Explorer Browser Launched on : " + osName + " | Downloaded Version : "+ WebDriverManager.iedriver().getDownloadedVersion());
					break;
				}else {
					driver = new RemoteWebDriver(new URL("http://192.168.130.30:4444/wd/hub"),
							DesiredCapabilities.internetExplorer());
					break;
				}		
			case WebDriverType.PHANTOMJS:
				if (WebDriverType.REMOTEWEBDRIVER.equalsIgnoreCase(remoteLevel)) {
					WebDriverManager.phantomjs().setup();
					dCaps = new DesiredCapabilities();
					dCaps.setJavascriptEnabled(true);
					dCaps.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
					dCaps.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
					dCaps.setCapability(CapabilityType.ENABLE_PROFILING_CAPABILITY, true);
					dCaps.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
					dCaps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
					dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
							new String[] { "--web-security=false",
									"--ssl-protocol=any", "--ignore-ssl-errors=true",
					"--webdriver-loglevel=INFO" });
					driver = new PhantomJSDriver(dCaps);
					driver.manage().deleteAllCookies();
					break;
				}else {
					driver = new RemoteWebDriver(new URL("http://192.168.130.30:4444/wd/hub"),
							DesiredCapabilities.phantomjs());
					break;
				}
			case WebDriverType.SAFARI:
				if (WebDriverType.REMOTEWEBDRIVER.equalsIgnoreCase(remoteLevel)) {
					driver = new SafariDriver();
					driver.manage().deleteAllCookies();
					break;
				}else {
					driver = new RemoteWebDriver(new URL("http://192.168.130.30:4444/wd/hub"),
							DesiredCapabilities.safari());
					break;
				}
			case WebDriverType.EDGE:
				if (WebDriverType.REMOTEWEBDRIVER.equalsIgnoreCase(remoteLevel)) {
					WebDriverManager.edgedriver().setup();
					EdgeOptions options = new EdgeOptions();
					options.setPageLoadStrategy("eager");
					driver = new EdgeDriver(options);
					driver.manage().deleteAllCookies();
				}else {
					driver = new RemoteWebDriver(new URL("http://192.168.130.30:4444/wd/hub"),
							DesiredCapabilities.edge());
					break;
				}
			default:
				LOG.error("Browser configs value not matched in ProductionTestData.json");
			}
		} catch (Exception e) {
			LOG.error("[ERROR]: browser type must be one of 'GOOGLE_CHROME','MOZILLA_FIREFOX','MICROSOFT_IE', NOT :"+browser + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Error occured during browser instantiation");
		}
		WebDriverThreadLocal.set(driver);
		driver = WebDriverThreadLocal.get();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static Placeholder getRunTime() {
		return runTimeInstance;
	}

	protected void pageObjectsInitializion(String brand, WebDriver driver) throws Exception {
		browser = stringDataReaderJSON("browser");
		device = stringDataReaderJSON("device");
		utils = new Utils(brand, browser, device);
		// utils.setupAUTTestRecorder(brand + " - " + this.getClass().getSimpleName());
		XLUtilsObj = (XLUtilsObj == null) ? new XLUtils() : XLUtilsObj;
		homepage = (homepage == null) ? new HomePageObjects(driver, brand, browser, device) : homepage;
		plpPage = (plpPage == null) ? new PLPPageObject(driver, brand, browser, device) : plpPage;
		searchPage = (searchPage == null) ? new SearchPageObject(driver, brand, browser, device) : searchPage;
		pdpPage = (pdpPage == null) ? new PDPPageObject(driver, brand, browser, device) : pdpPage;
		loginPage = (loginPage == null) ? new LoginPageObjects(driver, brand, browser, device) : loginPage;
		myaccount = (myaccount == null) ? new MyAccountPageObject(driver, brand, browser, device) : myaccount;
		checkout = (checkout == null) ? new CheckoutPageObject(driver, brand, browser, device) : checkout;
		shippingObj = (shippingObj == null) ? new ShippingPageObject(driver, brand, browser, device) : shippingObj;
		paymentObj = (paymentObj == null) ? new PaymentPageObject(driver, brand, browser, device) : paymentObj;
		orderReviewObj = (orderReviewObj == null) ? new OrderReviewPageObject(driver, brand, browser, device)
				: orderReviewObj;
		customerService = (customerService == null) ? new CustomerServiceObjects(driver, brand, browser, device)
				: customerService;
		storeLocatorPage = (storeLocatorPage == null) ? new StoreLocatorPageObject(driver, brand, browser, device)
				: storeLocatorPage;
	}

	public synchronized void setBrandValue(String siteUrl, WebDriver driver) throws Exception {
		if (siteUrl.equalsIgnoreCase("https://factory.anntaylor.com")) {
			brandValue = "ATF";
			pageObjectsInitializion(brandValue, driver);
		} else if (siteUrl.equalsIgnoreCase("https://outlet.loft.com")) {
			brandValue = "LO";
			pageObjectsInitializion(brandValue, driver);
		} else if (siteUrl.equalsIgnoreCase("https://www.anntaylor.com")) {
			brandValue = "ATFP";
			pageObjectsInitializion(brandValue, driver);
		} else if (siteUrl.equalsIgnoreCase("https://www.loft.com")) {
			brandValue = "LOFP";
			pageObjectsInitializion(brandValue, driver);
		} else if (siteUrl.equalsIgnoreCase("https://www.louandgrey.com")) {
			brandValue = "LGFP";
			pageObjectsInitializion(brandValue, driver);
		} else {
			System.out.println("in else");
		}
	}

	public void launchBrandSite(String siteUrl) throws Exception {
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();
		//Platform platform = caps.getPlatform();
		//System.out.println(platform.getCurrent());
		//System.out.println(platform.getMajorVersion() + platform.name());
		//System.out.println(platform.getPartOfOsName());
		setBrandValue(siteUrl, driver);
		driver.get(siteUrl);
		// Log.info(siteUrl+" launched on "+browserName+" browser's version
		// "+browserVersion+ " on OS : "+osName);
	}

	public String stringDataReaderJSON(String sKey) {
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader("./src/test/resources/testdata/ProductionTestData.json"));
			JSONObject jsonObject = (JSONObject) object;
			sKey = (String) jsonObject.get(sKey);
		} catch (FileNotFoundException fe) {
			LOG.error("configuration file not found :"+fe.getMessage());
			fe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sKey;
	}
	
//	public int intDataReaderJSON(String sKey) {
//		JSONParser parser = new JSONParser();
//		int value=0;
//		try {
//			Object object = parser.parse(new FileReader("./src/test/resources/testdata/ProductionTestData.json"));
//			JSONObject jsonObject = (JSONObject) object;
//			sKey = (String) jsonObject.get(sKey);
//			value  = Integer.parseInt(sKey);
//		} catch (FileNotFoundException fe) {
//			LOG.error("configuration file not found :"+fe.getMessage());
//			fe.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return value;
//	}

	public void captureScreenshot(WebDriver driver) {
		String destinationPath = null;
		File sourceFile = null, destFolder;
		String fileSuffix = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		try {
			if (brandValue.equalsIgnoreCase("ATF")) {
				sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				destFolder = new File("./src/test/resources/screenshot/Factory");
				destinationPath = destFolder.getCanonicalPath() + "/" + fileSuffix + ".png";
			} else if (brandValue.equalsIgnoreCase("LO")) {
				sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				destFolder = new File("./src/test/resources/screenshot/Outlet");
				destinationPath = destFolder.getCanonicalPath() + "/" + LocalDateTime.now() + ".png";
			} else if (brandValue.equalsIgnoreCase("ATFP")) {
				sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				destFolder = new File("./src/test/resources/screenshot/AnnTaylor");
				destinationPath = destFolder.getCanonicalPath() + "/" + LocalDateTime.now() + ".png";
			} else if (brandValue.equalsIgnoreCase("LOFP")) {
				sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				destFolder = new File("./src/test/resources/screenshot/Loft");
				destinationPath = destFolder.getCanonicalPath() + "/" + LocalDateTime.now() + ".png";
			} else if (brandValue.equalsIgnoreCase("LGFP")) {
				sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				destFolder = new File("./src/test/resources/screenshot/L&G");
				destinationPath = destFolder.getCanonicalPath() + "/" + LocalDateTime.now() + ".png";
			}
			FileUtils.copyFile(sourceFile, new File(destinationPath));
		} catch (Exception e) {
			LOG.warn("Error capturing screenshot...\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	// public String getFeatureFileName(Scenario scenario) {
	// String featureName = "FEATURE - ";
	// String rawFeatureName = scenario.getId().split(";")[0].replace("-"," ");
	// featureName = featureName + rawFeatureName.substring(0, 1).toUpperCase() +
	// rawFeatureName.substring(1).toUpperCase();
	// return featureName;
	// }
}