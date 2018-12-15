package com.ann.automation.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;

public class LogDefect_JIRA {
	// Global Variables
	private static String location;
	private String mainWindow;
	private String summary = "";
	private String priority = "";
	private String description = "";
	private String tags = "";
	public String Sprint = "";
	private String component = "";
	static WebDriver driver;
	private String userName = "agupta293@sapient.com";
	private String password = "Welcome@2";
	private String url = "https://ann1954.atlassian.net/secure/Dashboard.jspa";
	public String plannedVersion;
	public String targetVersion;
	public String assignieName;
	private String browserName;
	public static String steps="";
	// Web Elements Used

	@FindBy(xpath = "//*[contains(text(),'Continue')]")
	private WebElement btn_Continue;

	@FindBy(xpath = "//*[text()='Log in']")
	private WebElement LogIn;

	@FindBy(xpath = ".//*[@aria-label='Create']")
	private WebElement createNewTicket;

	@FindBy(id = "priority-field")
	private WebElement priorityField;

	@FindBy(id = "summary")
	private WebElement summaryField;

	@FindBy(id = "description")
	private WebElement descriptionField;

	@FindBy(xpath = "//*[contains(@href,'/login')]")
	private WebElement login;

	@FindBy(xpath = ".//*[contains(text(),'Steps to Test')]/following-sibling::textarea")
	private WebElement steptsToTest;

	@FindBy(id = "issuetype-field")
	private WebElement issueType;

	@FindBy(id = "customfield_10006-field")
	private WebElement sprint;

	@FindBy(xpath = ".//*[@id='labels-multi-select']/textarea")
	private WebElement labels;

	@FindBy(xpath = ".//select[@id='customfield_10609']/option")
	private List<WebElement> components;

	@FindBy(id = "customfield_11500")
	private WebElement dueDate;

	@FindBy(id = "create-issue-submit")
	private WebElement createTicket;

	@FindBy(xpath = ".//*[@id='customfield_10400']/option")
	private List<WebElement> plannedVer;

	@FindBy(xpath = ".//*[@id='customfield_10400']")
	private WebElement planned_Ver;

	@FindBy(xpath = ".//*[@id='customfield_10401']/option")
	private List<WebElement> targetVer;

	@FindBy(id = "assignee-field")
	private WebElement assigneeName;

	@FindBy(xpath = ".//*[@id='customfield_10801']/option")
	private List<WebElement> browserName_select;

	@FindBy(xpath = ".//button[contains(text(),'browse')]")
	private WebElement uploadImage;

	@FindBy(xpath = ".//*[text()='Upload a file']/ancestor::button")
	private WebElement uploadFile;

	@FindBy(xpath = "//*[text()='Insert  1 file']/ancestor::button")
	private WebElement insertFile;

	@FindBy(id = "username")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(xpath = ".//*[contains(@class,'upload-finished')]")
	private WebElement waitFileUpload;

	/**
	 * For Page Factory initialising the driver so that the web elements can be
	 * created.
	 * 
	 * @param driver
	 */
	public LogDefect_JIRA(WebDriver driver) {
		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	/**
	 * Basic Information passed by user before the test starts.
	 * 
	 * @param summary
	 * @param priority
	 * @param tags
	 * @param component
	 */
	public void defectFieldsInfo(String summary, String priority, String tags, String component) {
		this.summary = summary;
		this.priority = priority;
		this.tags = tags;
		this.component = component;
	}

	/**
	 * Setting initial values for the parameter (values set By user )
	 * 
	 * @param sprint
	 * @param planned
	 * @param target
	 * @param browserName
	 */
	public void setDefaultContents(String sprint, String planned, String target, String browserName) {
		this.Sprint = sprint;
		this.plannedVersion = planned;
		this.targetVersion = target;
		this.browserName = browserName;
	}

	/**
	 * Function to log defect in JIRA
	 * 
	 * @param description
	 */
	public void logDefct_JIRA(String description) {
		try {
			
			this.description = description;
			takeScreenshot("defect_");
			waitForPageLoadedJIRA(driver);
			// Opening JIRA
			openJIRA();

			try {
				// Checking that if it is already opened or not
				login.click();
				waitForPageLoadedJIRA(driver);
				username.sendKeys(userName);
				btn_Continue.click();
				waitForPageLoadedJIRA(driver);
				passwordField.sendKeys(password);

				LogIn.click();
				waitForPageLoadedJIRA(driver);

			} catch (Exception e) {
				System.out.println("******Already Logged in in JIRA********");
			}

			// click on (+) to create new tickets
			createNewTicket.click();
			waitForPageLoadedJIRA(driver);
			System.out.println("************STARTED LOGGING DEFECT IN JIRA******************");
			// setting issueType to Defect
			issueType.clear();
			issueType.sendKeys("Defect");
			waitForPageLoadedJIRA(driver);
			issueType.sendKeys(Keys.ENTER);
			waitForPageLoadedJIRA(driver);

			// Enter Summary
			System.out.println("> Summary entered");
			summaryField.sendKeys(summary);
			waitForPageLoadedJIRA(driver);

			// Set Priority
			System.out.println("> Priority entered");
			priorityField.click();
			priorityField.clear();
			priorityField.sendKeys(priority);
			waitForPageLoadedJIRA(driver);
			priorityField.sendKeys(Keys.ENTER);
			waitForPageLoadedJIRA(driver);

			System.out.println("> Selected Sprint");
			sprint.click();
			sprint.sendKeys(Sprint);
			waitForPageLoadedJIRA(driver);
			sprint.sendKeys(Keys.ENTER);
			waitForPageLoadedJIRA(driver);

			System.out.println("> Selected Planned version");
			for (int i = 0; i < plannedVer.size(); i++) {
				if (plannedVer.get(i).getText().replaceAll("[\\s]", "")
						.equalsIgnoreCase(plannedVersion.replaceAll("[\\s]", ""))) {
					plannedVer.get(i).click();
					break;
				}
			}

			waitForPageLoadedJIRA(driver);
			System.out.println("> Selected Target version");
			for (int i = 0; i < targetVer.size(); i++) {
				if (targetVer.get(i).getText().replaceAll("[\\s]", "")
						.equalsIgnoreCase(targetVersion.replaceAll("[\\s]", ""))) {
					targetVer.get(i).click();
					break;
				}
			}
			waitForPageLoadedJIRA(driver);

			System.out.println("> Selected Assignee Name");
			assigneeName.click();
			assigneeName.sendKeys(assignieName);
			waitForPageLoadedJIRA(driver);
			assigneeName.sendKeys(Keys.ENTER);
			waitForPageLoadedJIRA(driver);

			descriptionField.click();
			Thread.sleep(1000);
			descriptionField.sendKeys(this.description);
			Thread.sleep(1000);
			System.out.println("> Entered Description");

			selectLabel();
			System.out.println("> Entered the labels");

			steptsToTest.click();
			Thread.sleep(1000);
			System.out.println("> Entered Steps To Test");

			steptsToTest.sendKeys(steps);
			System.out.println("> Steps to Test Entered");
			steps="";
			selectComponent();
			System.out.println("> Components selected");

			waitForPageLoadedJIRA(driver);

			selectBrowser();
			System.out.println("> Browser selected");

			waitForPageLoadedJIRA(driver);

			uploadImage();
			System.out.println("> Image Uploaded");

			dueDate.clear();
			System.out.println("> Due Date cleared");

			createTicket.click();
			waitForPageLoadedJIRA(driver);

			// closing JIRA
			driver.close();
			driver.switchTo().window(mainWindow);
			
			System.out.println("*************TICKET CREATED*******************");
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Wait for page to be loaded
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	private synchronized static void waitForPageLoadedJIRA(WebDriver driver) throws InterruptedException {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(expectation);
			Thread.sleep(5000);
		} catch (Throwable error) {
			ReporterLog.warn("Timeout waiting for Page Load Request to complete.");
		}

	}

	/**
	 * Opening URL in new Tab
	 * 
	 * @throws InterruptedException
	 */
	private synchronized void openJIRA() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");
		Thread.sleep(5000);
		mainWindow = driver.getWindowHandle();
		Set<String> chalid = driver.getWindowHandles();
		for (String c : chalid) {
			if (!c.equals(mainWindow)) {
				driver.switchTo().window(c);
				if (driver.getTitle().isEmpty())
					break;
				else
					continue;
			}
		}
		Thread.sleep(2000);

		// opening URL
		driver.get(url);
		waitForPageLoadedJIRA(driver);
		WebDriverWait wait = new WebDriverWait(driver, 90000);
		wait.until(ExpectedConditions.visibilityOf(login));

	}

	/**
	 * Selecting the component
	 * 
	 * @throws InterruptedException
	 */
	private void selectComponent() throws InterruptedException {

		if (component.contains(",")) {
			String variousComponent[] = component.split(",");
			for (int j = 0; j < variousComponent.length; j++) {

				for (int i = 0; i < components.size(); i++) {
					if (components.get(i).getText().replaceAll("[\\s]", "")
							.equalsIgnoreCase(variousComponent[j].replaceAll("[\\s]", ""))) {
						components.get(i).click();
						break;
					}
				}
			}
		} else {

			for (int i = 0; i < components.size(); i++) {
				if (components.get(i).getText().replaceAll("[\\s]", "")
						.equalsIgnoreCase(component.replaceAll("[\\s]", ""))) {
					components.get(i).click();
					break;
				}
			}
		}
	}

	/**
	 * Setting the Label
	 * 
	 * @throws InterruptedException
	 */
	private void selectLabel() throws InterruptedException {
		labels.click();
		if (tags.contains(",")) {
			String label[] = tags.split(",");

			for (int i = 0; i < label.length; i++) {
				labels.sendKeys(label[i]);
				waitForPageLoadedJIRA(driver);
				labels.sendKeys(Keys.ENTER);
				waitForPageLoadedJIRA(driver);
			}
		} else {
			labels.sendKeys(tags);
			labels.sendKeys(Keys.ENTER);
		}
	}

	/**
	 * Function to select the browser
	 * 
	 * @throws InterruptedException
	 */
	private void selectBrowser() throws InterruptedException {
		browserName_select.get(0).click();
		Utils.waitForPageLoaded(driver);
		if (browserName.contains(",")) {
			String variousComponent[] = browserName.split(",");
			for (int j = 0; j < variousComponent.length; j++) {

				for (int i = 0; i < browserName_select.size(); i++) {
					if (browserName_select.get(i).getText().replaceAll("[\\s]", "")
							.equalsIgnoreCase(variousComponent[j].replaceAll("[\\s]", ""))) {
						browserName_select.get(i).click();
						break;
					}
				}
			}
		} else {

			for (int i = 0; i < browserName_select.size(); i++) {
				if (browserName_select.get(i).getText().replaceAll("[\\s]", "")
						.equalsIgnoreCase(browserName.replaceAll("[\\s]", ""))) {
					browserName_select.get(i).click();
					break;
				}
			}
		}
	}

	/**
	 * Opening the Image dialog and calling the upload function according to the
	 * device
	 * 
	 * @throws Exception
	 */
	public void uploadImage() throws Exception {
		uploadImage.click();
		waitForPageLoadedJIRA(driver);
		List<WebElement> iframe = driver.findElements(By.tagName("iframe"));
		for (int i = 0; i < iframe.size(); i++) {
			iframe = driver.findElements(By.tagName("iframe"));
			driver.switchTo().frame(iframe.get(i));
			waitForPageLoadedJIRA(driver);
			if (checkElementPresent(uploadFile)) {
				uploadFile.click();
				String os = System.getProperty("os.name").toLowerCase();
				if (os.contains("mac"))
					uploadImageInJIRA_mac();
				else
					uploadImageInJIRA_win();

				insertFile.click();
				waitForPageLoadedJIRA(driver);
				Thread.sleep(10000);
				break;
			} else {
				driver.switchTo().defaultContent();
				continue;
			}
		}
		driver.switchTo().defaultContent();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90000);
			wait.until(ExpectedConditions.visibilityOf(waitFileUpload));
		} catch (Exception e) {
			System.out.println("Waited for 90 sec but file not uploaded" + e);
		}
	}

	/**
	 * Checking element is present or not
	 * 
	 * @param element
	 * @return
	 */
	public boolean checkElementPresent(WebElement element) {
		try {
			if (element.isDisplayed())
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
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
	public synchronized static void takeScreenshot(String fileName) throws Exception {
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
			String filPath = "screenshots_JIRA" + File.separator + "Jira" + File.separator + strDate;
			File folderByDate = new File(filPath);
			folderByDate.mkdir();
			location = filPath + File.separator + fileName + sDateTime + ".JPEG";
			// Takes the screenshot when test fails
		/*	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(location)); */

			/* Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		     ImageIO.write(fpScreenshot.getImage(),"JPEG",new File(location));
		     Thread.sleep(3000);*/

		} catch (Exception e) {
			Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Exception in TakeScreenshot Method|");
			throw e;
		}
	}

	/**
	 * Uploading Image in JIRA for MAC
	 * 
	 * @throws AWTException
	 */
	public void uploadImageInJIRA_mac() throws AWTException {
		String myCurrentDir = System.getProperty("user.dir");
		location = myCurrentDir + File.separator + location;

		StringSelection stringSelection = new StringSelection(location);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		Robot robot = new Robot();

		((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Open Goto window

		robot.keyPress(KeyEvent.VK_META);

		robot.keyPress(KeyEvent.VK_SHIFT);

		robot.keyPress(KeyEvent.VK_G);

		robot.keyRelease(KeyEvent.VK_META);

		robot.keyRelease(KeyEvent.VK_SHIFT);

		robot.keyRelease(KeyEvent.VK_G);

		robot.delay(1000);

		// Paste the clipboard value

		robot.keyPress(KeyEvent.VK_META);

		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_META);

		robot.keyRelease(KeyEvent.VK_V);

		robot.delay(5000);

		// Press Enter key to close the Goto window and Upload window

		robot.keyPress(KeyEvent.VK_ENTER);

		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.delay(5000);

		robot.keyPress(KeyEvent.VK_ENTER);

		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.delay(1000);

	}

	/**
	 * Uploading Image in JIA for win
	 * 
	 * @throws AWTException
	 * @throws InterruptedException 
	 */
	private void uploadImageInJIRA_win() throws AWTException, InterruptedException {
		String myCurrentDir = System.getProperty("user.dir");
		location = myCurrentDir + File.separator + location;

		StringSelection stringSelection = new StringSelection(location);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot robot = new Robot();
		Thread.sleep(2000);

		 robot.keyPress(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
         waitForPageLoadedJIRA(driver);
	}

}
