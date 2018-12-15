package com.ann.automation.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.utilities.Utils.MobileView;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.PaymentPageObject;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileBy.ByAndroidUIAutomator;

public class MyAccountPageObject {

	private static WebDriver driver = null;
	static Utils utils = null;
	public XLUtils xlUtils = null;
	private HomePageObjects homePage = null;

	public MyAccountPageObject(WebDriver driver, String brand, String browser, String device) {
		this.driver = driver;
		utils = new Utils(brand, browser, device);
		homePage = new HomePageObjects(driver, brand, browser, device);
		xlUtils = new XLUtils();

	}

	public synchronized void ClickSignIn() throws Exception {
		if (utils.device.equals("mobile")) {
			homePage.lnk_hamBurger().click();
			Thread.sleep(1000);
		} else {
			WebElement elemSignIn = utils.findElementByLocator(driver, "HP_lnk_SignIn", "Unable to locate SignIn link");
			utils.mouseHoverJScript(driver, elemSignIn);
		}

		utils.clickWebElement(driver, SignIn(), "Sign In  not present");
		ReporterLog.actionMsg("Sign In Clicked");
	}

	public synchronized WebElement SignIn() throws Exception {
		if (utils.device.equalsIgnoreCase("mobile")) {
			return utils.findElementByLocator(driver, "MBL_HP_lnk_SignIn", "SignIn Not visible on MouseHover");
		} else {
			return utils.findElementByLocator(driver, "MA_SignIn", "SignIn Not visible on MouseHover");
		}
	}

	public synchronized WebElement profile_ClickHere() throws Exception {
		return utils.findElementByLocator(driver, "MA_ProfilePreference_ClickHere",
				"Click here in Profile and perference");
	}

	public synchronized WebElement profile_AssociateID() throws Exception {
		return utils.findElementByLocator(driver, "MA_ProfilePreference_AssociateID",
				"Associate ID in Profile and perference");
	}

	public synchronized WebElement profile_btn_Continue() throws Exception {
		return utils.findElementByLocator(driver, "MA_ProfilePreference_Continue",
				"Continue Button in Profile and perference");
	}

	public synchronized WebElement profile_btn_editPassword() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_MA_EditPassword", "Edit Button in Profile and perference");

		else
			return utils.findElementByLocator(driver, "MA_ProfilePreference_EditPassword",
					"Edit Button in Profile and perference");
	}

	public synchronized WebElement profile_btn_editEmail() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_MA_EditEmail", "Edit Button in Profile and perference");
		else
			return utils.findElementByLocator(driver, "MA_ProfilePreference_EditEmail",
					"Edit Button in Profile and perference");
	}

	public synchronized WebElement profile_txt_oldPassword() throws Exception {
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MA_ProfilePreference_txt_CurrentPassword_IOS");
		else
			return utils.findElementByLocator(driver, "MA_ProfilePreference_txt_CurrentPassword",
					"Edit Button in Profile and perference");
	}

	public synchronized WebElement profile_txt_newPassword() throws Exception {
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MA_ProfilePreference_txt_NewPassword_IOS");
		else
			return utils.findElementByLocator(driver, "MA_ProfilePreference_txt_NewPassword",
					"Edit Button in Profile and perference");
	}

	public synchronized void expand_Preference_btn() throws Exception {
		utils.findElementByLocator(driver, "MBL_expand_Preference", "Edit Button in Profile and perference").click();
	}

	public synchronized WebElement profile_txt_confirmPassword() throws Exception {
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MA_ProfilePreference_txt_ConfirmPassword_IOS");
		else
			return utils.findElementByLocator(driver, "MA_ProfilePreference_txt_ConfirmPassword",
					"Edit Button in Profile and perference");
	}

	public synchronized WebElement profile_btn_SavePassword() throws Exception {
		return utils.findElementByLocator(driver, "MA_ProfilePreference_btn_SavePassword",
				"Edit Button in Profile and perference");
	}

	public synchronized List<WebElement> errorMessages_SignUp() throws Exception {
		return utils.findElementsByLocator(driver, "MA_ProfilePreference_ErrorMsg_EmailMismatch",
				"Edit Button in Profile and perference");
	}

	public synchronized WebElement CreateAccount() throws Exception {
		return utils.findElementByLocator(driver, "MA_createAccount", "My Account Not visible on MouseHover");
	}

	public synchronized WebElement profile_UpdateEmailAddress() throws Exception {
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MA_ProfilePreference_UpdateEmail_IOS");
		else
			return utils.findElementByLocator(driver, "MA_ProfilePreference_UpdateEmail",
					"My Account Not visible on MouseHover");
	}

	public synchronized WebElement profile_ConfirmEmailAddress() throws Exception {
		if (Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("MA_ProfilePreference_ConfirmEmail_IOS");
		else
			return utils.findElementByLocator(driver, "MA_ProfilePreference_ConfirmEmail",
					"My Account Not visible on MouseHover");
	}

	public synchronized WebElement payment_CardNumber() throws Exception {
		return utils.findElementByLocator(driver, "MA_CardNumber_Payment", "My Account Not visible on MouseHover");
	}

	public synchronized WebElement payment_Year() throws Exception {
		return utils.findElementByLocator(driver, "MA_ExpirationDaye_Payment", "My Account Not visible on MouseHover");
	}

	public synchronized WebElement profile_txt_ZipCode() throws Exception {
		return utils.findElementByLocator(driver, "MA_ProfilePreference_ZipCode",
				"My Account Not visible on MouseHover");
	}

	public synchronized WebElement profile_Password() throws Exception {
		return utils.findElementByLocator(driver, "MA_ProfilePreference_Password",
				"My Account Not visible on MouseHover");
	}

	public synchronized WebElement orderReturn_BilledTo() throws Exception {
		return utils.findElementByLocator(driver, "MA_OrderReturn_BilledToSection",
				"My Account Not visible on MouseHover");
	}

	public synchronized WebElement orderReturn_PaymentSection() throws Exception {
		return utils.findElementByLocator(driver, "MA_OrderReturn_PaymentSection",
				"My Account Not visible on MouseHover");
	}

	public synchronized WebElement orderReturn_ShippedTo() throws Exception {
		return utils.findElementByLocator(driver, "MA_OrderReturn_ShippedToSection",
				"My Account Not visible on MouseHover");
	}

	public synchronized WebElement signOut() throws Exception {
		if (utils.device.equals("mobile")) {
			return utils.findElementByLocator(driver, "MBL_MA_SignOut", "My Account Not visible on MouseHover");
		} else {
			return utils.findElementByLocator(driver, "MA_SignOut", "My Account Not visible on MouseHover");
		}
	}

	public synchronized WebElement profile_txt_DOB() throws Exception {
		return utils.findElementByLocator(driver, "MA_ProfilePreference_DOB", "My Account Not visible on MouseHover");
	}

	public synchronized WebElement profile_btn_Submit() throws Exception {
		return utils.findElementByLocator(driver, "MA_ProfilePreference_Submit",
				"My Account Not visible on MouseHover");
	}

	public synchronized WebElement dashboard_EditEmail() throws Exception {
		return utils.findElementByLocator(driver, "MA_Dashboard_EditEmail", "Edit email not visible");
	}

	public synchronized WebElement dashboard_EditPassword() throws Exception {
		return utils.findElementByLocator(driver, "MA_Dashboard_EditPassword", "edit password not visible");
	}

	public synchronized WebElement dashboard_ProfilePreference_View() throws Exception {
		if (utils.brand.equals("ATF")) {
			return utils.findElementByLocator(driver, "MA_DashBoard_ProfilePreference_ViewLink",
					"view profile preferences not visible");
		} else {
			return utils.findElementByLocator(driver, "MA_DashBoard_ProfilePreference_ViewLinkLO",
					"view profile preferences not visible");
		}
	}

	public synchronized WebElement Orderstatus() throws Exception {
		return utils.findElementByLocator(driver, "MA_checkOrderStatus",
				"Check Order Status Not visible on MouseHover");
	}

	public synchronized void ClickCreateAccount() throws Exception {

		if (Utils.isDeviceMobile()) {

			utils.isElementPresent(driver, "MB_HamBurger");
			homePage.lnk_hamBurger().click();
			Thread.sleep(1000);

			WebElement elemSignIn = utils.findElementByLocator(driver, "MBL_HP_lnk_SignIn",
					"Unable to locate SignIn link");

			// elemSignIn.click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", elemSignIn);

		} else {

			WebElement elemSignIn = utils.findElementByLocator(driver, "HP_lnk_SignIn", "Unable to locate SignIn link");

			utils.mouseHoverScript(driver, elemSignIn);
			Thread.sleep(2000);

			if (!Utils.brand.equals("LGFP")) {
				elemSignIn = utils.findElementByLocator(driver, "HP_Btn_GuestSignIn", "Unable to locate SignIn link");

				Assert.assertTrue(Utils.isElementPresent(driver, elemSignIn), "Sign In Dropdown not clicked.");
				elemSignIn.click();
			} else {
				CreateAccount().click();
			}

		}
	}

	public synchronized void ClickCheckOrderStatus() throws Exception {
		if (utils.device.equals("mobile")) {
			homePage.lnk_hamBurger().click();
			Thread.sleep(1000);
		} else {
			WebElement SignIn = utils.findElementByLocator(driver, "HP_lnk_SignIn", "Unable to locate SignIn link");
			utils.mouseHoverJScript(driver, SignIn);
		}
		utils.clickWebElement(driver, Orderstatus(), " Check order status is not present");
	}

	public synchronized WebElement EmailField() throws Exception {
		return utils.findElementByLocator(driver, "MA_Signin_Email", "Email text field not presented");
	}

	public synchronized WebElement Passwordfield() throws Exception {
		return utils.findElementByLocator(driver, "MA_Signin_Pwd", "Password text field not displayed");
	}

	public synchronized WebElement accountName_UtilityNav() throws Exception {
		return utils.findElementByLocator(driver, "MA_MainAccountUserName_UtilityNav",
				"Password text field not displayed");
	}

	public synchronized WebElement Submit_btn() throws Exception {
		return utils.findElementByLocator(driver, "MA_Signin_btn", "Submit button not displayed");
	}

	public synchronized WebElement txt_breadCrumb_Home() throws Exception {
		return utils.findElementByLocator(driver, "MA_Breadcrumb_Home", "Submit button not displayed");
	}

	public synchronized WebElement txt_breadCrumb_MyAccount() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MA_MBL_BreadCrumb_MyAccount", "Submit button not displayed");
		else
			return utils.findElementByLocator(driver, "MA_BreadCrumb_MyAccount", "Submit button not displayed");
	}

	public synchronized WebElement breadCrumb_Parent() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MA_MBL_BreadCrumb_parent", "Submit button not displayed");
		else
			return utils.findElementByLocator(driver, "MA_BreadCrumb_parent", "Submit button not displayed");
	}

	public synchronized WebElement signInEmail() throws Exception {

		Assert.assertTrue(utils.isElementPresent(driver, "Login_txt_email"), "Sign in email field is not present");
		return utils.findElementByLocator(driver, "Login_txt_email", "Sign in email field is not present");
	}

	public synchronized void clickSignInAccountTab() throws Exception {

		if (Utils.isBrandFullPrice() && Utils.isDeviceMobile()) {

			ReporterLog.log("Clicking on Sign In tab for FP sites in mobile...");
			utils.isElementPresent(driver, "MBL_MA_Sign_In_Account_Tab");
			utils.findElementByLocator(driver, "MBL_MA_Sign_In_Account_Tab", "Sign In Account Tab present").click();
		}
	}

	public synchronized WebElement signInPassword() throws Exception {

		Assert.assertTrue(utils.isElementPresent(driver, "Login_txt_password"), "Password field is not present");
		return utils.findElementByLocator(driver, "Login_txt_password", "Password field is not present");
	}

	public synchronized WebElement signInSubmit() throws Exception {

		Assert.assertTrue(utils.isElementPresent(driver, "Login_btn_SignIn"), "Submit button is not present");
		return utils.findElementByLocator(driver, "Login_btn_SignIn", "Submit button is not present");

	}

	public synchronized WebElement Fname() throws Exception {
		if (Utils.device.equals("mobile") && Utils.browser.equals("ios"))
			return utils.findElementByIOSNameLocator("SignUp_FirstName_IOS");
		else {
			Assert.assertTrue(utils.isElementPresent(driver, "MA_Signup_Fname"),
					"First name text field is not present");
			return utils.findElementByLocator(driver, "MA_Signup_Fname", "First name text field is not present");
		}
	}

	/**
	 * @Prateek
	 * 
	 * 			Method used to create a new account
	 * @param excelRowIndex
	 *            - Row number of MyAccount tab in excel sheet
	 * @throws Exception
	 */
	public synchronized void signUp(int excelRowIndex) throws Exception {

		Utils.waitForPageLoaded(driver);

		String newEmail = "AnnTaylor" + utils.getDatetime() + "@gmail.com";
		String Fname = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_MYACCOUNT, excelRowIndex, 4);
		String Lname = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_MYACCOUNT, excelRowIndex, 5);
		String New_password = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_MYACCOUNT, excelRowIndex, 3);
		String Confirm_password = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_MYACCOUNT, excelRowIndex, 3);

		Fname().sendKeys(Fname);

		Lname().sendKeys(Lname);

		NewEmail().sendKeys(newEmail);

		NewPassword().sendKeys(New_password);

		ConfirmPassword().sendKeys(Confirm_password);

		RecieveEmail().click();

		utils.scrollDownToBottomOfPage(driver);

		Signup().click();

		Assert.assertTrue(utils.isElementPresent(driver, "MA_Header"), "New Account Not Created");
	}

	public synchronized WebElement Lname() throws Exception {

		if (Utils.isDeviceMobile()) {

			if (Utils.browser.equals("ios")) {

				return utils.findElementByIOSNameLocator("SignUp_LastName_IOS");

			} else if (Utils.isBrandFullPrice()) {

				return utils.findElementByLocator(driver, "MB_MA_signup_Lname", "Last name text field is not present");
			}
		}

		Assert.assertTrue(utils.isElementPresent(driver, "MA_signup_Lname"), "Last name text field is not present");
		return utils.findElementByLocator(driver, "MA_signup_Lname", "Last name text field is not present");
	}

	public synchronized WebElement NewEmail() throws Exception {
		if (Utils.device.equals("mobile") && Utils.browser.equals("ios"))
			return utils.findElementsByIOSNameLocator("MBL_EmailID_IOS").get(0);
		else {
			Assert.assertTrue(utils.isElementPresent(driver, "MA_Signup_Email"), "Email text field is not present");
			return utils.findElementByLocator(driver, "MA_Signup_Email", "Email text field is not present");
		}
	}

	public synchronized WebElement NewPassword() throws Exception {
		if (Utils.device.equals("mobile") && Utils.browser.equals("ios"))
			return utils.findElementsByIOSNameLocator("MBL_Password_IOS").get(0);
		else {
			Assert.assertTrue(utils.isElementPresent(driver, "MA_Signup_pwd"),
					"New Password text field is not present");
			return utils.findElementByLocator(driver, "MA_Signup_pwd", "New Password text field is not present");
		}
	}

	public synchronized WebElement ConfirmPassword() throws Exception {

		if (Utils.device.equals("mobile")) {
			if (Utils.browser.equals("ios")) {

				return utils.findElementByIOSNameLocator("SignUp_ConfirmPass_IOS");

			} else if (Utils.isBrandFullPrice()) {

				return utils.findElementByLocator(driver, "MBL_MA_Signup_Confrm_pwd",
						"Confirm Password text field is not present");
			}
		}
		Assert.assertTrue(utils.isElementPresent(driver, "MA_Signup_Confrm_pwd"),
				"Confirm Password text field is not present");
		WebElement element = utils.findElementByLocator(driver, "MA_Signup_Confrm_pwd",
				"Confirm Password text field is not present");
		return element;
	}

	public synchronized WebElement RecieveEmail() throws Exception {

		Assert.assertTrue(utils.isElementPresent(driver, "MA_Signup_revEmail"),
				"Recieve Email checkbox is not present");
		return utils.findElementByLocator(driver, "MA_Signup_revEmail", "Recieve Email checkbox is not present");
	}

	public synchronized WebElement Signup() throws Exception {

		if (Utils.device.equals("mobile") && Utils.isBrandFullPrice()) {

			return driver.findElement(By.id("signup-submit"));
		}

		Assert.assertTrue(utils.isElementPresent(driver, "MA_Signup_btn"), "Sign up button is not present");
		return utils.findElementByLocator(driver, "MA_Signup_btn", "Sign up button is not present");
	}

	public synchronized WebElement ErrorMessage_Registermail() throws Exception {
		return utils.findElementByLocator(driver, "MA_Signup_registerEmail_error_mg",
				"Error message for using register email on signup not displayed");
	}

	public synchronized WebElement Errormessage_fname() throws Exception {
		Assert.assertTrue(utils.isElementPresent(driver, "MA_fname_errormsg"), "Fname Error message is not present");
		return utils.findElementByLocator(driver, "MA_fname_errormsg", "Fname Error message is not present");
	}

	public synchronized WebElement Errormessage_lname() throws Exception {
		Assert.assertTrue(utils.isElementPresent(driver, "MA_lname_errormsg"),
				"last name Error message is not present");
		return utils.findElementByLocator(driver, "MA_lname_errormsg", "last name Error message is not present");
	}

	public synchronized WebElement Errormessage_Email() throws Exception {
		Assert.assertTrue(utils.isElementPresent(driver, "MA_email_errormsg"), "Email Error message is not present");
		return utils.findElementByLocator(driver, "MA_email_errormsg", "Email Error message is not present");
	}

	public synchronized WebElement Errormessage_newpassword() throws Exception {
		Assert.assertTrue(utils.isElementPresent(driver, "MA_newpassword_errormsg"),
				"New password Error message is not present");
		return utils.findElementByLocator(driver, "MA_newpassword_errormsg",
				"New password Error message is not present");
	}

	public synchronized WebElement Errormessage_confirmPassword() throws Exception {
		Assert.assertTrue(utils.isElementPresent(driver, "MA_cofrmpwd_errormsg"),
				"Confirm password Error message is not present");
		return utils.findElementByLocator(driver, "MA_cofrmpwd_errormsg",
				"Confirm password Error message is not present");
	}

	public synchronized WebElement ForgotPassword() throws Exception {
		return utils.findElementByLocator(driver, "MA_forgot_password", "Forgot password link is not present");
	}

	public synchronized WebElement ForgotPassword_email() throws Exception {
		return utils.findElementByLocator(driver, "MA_forgotPwd_email",
				"Recovery Password :Email field not displayed on forgot password modal");
	}

	public synchronized WebElement Forgotpassword_submit() throws Exception {
		return utils.findElementByLocator(driver, "MA_forgotPwd_submit",
				"Forgot password modal : Submit button not present");
	}

	public synchronized WebElement getuserName() throws Exception {
		return utils.findElementByLocator(driver, "MA_DashBoard_ProfilePreference_UserName",
				"Registered username is not dispalyed");
	}

	public synchronized WebElement getuserEmail() throws Exception {
		if (Utils.brand.equals("ATF")) {
			return utils.findElementByLocator(driver, "MA_DashBoard_ProfilePreference_Email",
					"Register user email not displayed");
		} else {
			return utils.findElementByLocator(driver, "MA_DashBoard_ProfilePreference_EmailLO",
					"Register user email not displayed");
		}
	}

	public synchronized WebElement AddressBook_lnk() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice()) {

			return utils.findElementByAndroidID("MBL_MA_AddressBook_lnk");
		}
		return utils.findElementByLocator(driver, "MA_AddressBook_lnk", "Address book link not dispayed");
	}

	public synchronized WebElement paymentMethod_lnk() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByAndroidID("MBL_MA_PaymentsMethod");

		else if (Utils.brand.equals("ATF") || Utils.brand.equals("ATFP"))
			return utils.findElementByLocator(driver, "MA_PaymentsMethod", "Payment method lnk not displayed");

		else if (Utils.brand.equals("LO") || Utils.brand.equals("LOFP"))
			return utils.findElementByLocator(driver, "MA_Wallet_LO", "Payment method lnk not displayed");

		else
			return utils.findElementByLocator(driver, "MA_Wallet_LG", "Payment method lnk not displayed");
	}

	public synchronized WebElement OrderReturns_lnk() throws Exception {
		return utils.findElementByLocator(driver, "MA_orderStatus_lnk", "Orders and Returs link not available");
	}

	public synchronized WebElement Profile_Preference_lnk() throws Exception {
		return utils.findElementByLocator(driver, "MA_dashboard_profile_preference_lnk",
				"Profile and Preference link is not available");
	}

	public synchronized WebElement AddressBook_Header() throws Exception {
		return utils.findElementByLocator(driver, "MA_AddressBookHeader", "Address book Header not dispayed");
	}

	public synchronized WebElement AddNewAddressbtn() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice()) {

			MobileView.switchToWebView(driver);
			utils.isElementPresent(driver, "MBL_MA_AddressBookAddNew");

			return utils.findElementByLocator(driver, "MBL_MA_AddressBookAddNew",
					"Add New Address button not displayed");
		} else {

			return utils.findElementByLocator(driver, "MA_AddressBookAddNew", "Address book link not dispayed");
		}
	}

	/**
	 * Author - @Prateek | Method to add a new address in Address Book section of My
	 * Account. The method checks if correct city is getting auto-populated else
	 * fills the correct city by itself.
	 * 
	 * @param excelRowIndex
	 *            - Index of row of excel sheet of My Accout tab indexed from zero
	 * @throws Exception
	 */
	public synchronized void addNewAddress(int excelRowIndex) throws Exception {

		String Add_FName = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelRowIndex, 1);
		String Add_LName = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelRowIndex, 2);
		String Add_Address = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelRowIndex, 3);
		String Add_zipCode = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelRowIndex, 8);
		String Add_City = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelRowIndex, 6);
		String Add_phone = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelRowIndex, 9);

		Utils.waitForPageLoaded(driver);

		if (!Utils.brand.equals("LGFP")) {
			Assert.assertTrue(utils.isElementPresent(driver, "MA_AddressBookHeader"),
					"Address Book Header is not displayed");
		}

		AddNewAddressbtn().click();

		Assert.assertTrue(utils.isElementPresent(driver, "MA_AddressBookModalHeader"),
				"Address Book Modal Header is not displayed");

		Address_Fname().sendKeys(Add_FName);

		Address_Lname().sendKeys(Add_LName);

		Address_Addr1().sendKeys(Add_Address);
		Thread.sleep(2000);

		Address_Zip().sendKeys(Add_zipCode);
		Address_Zip().sendKeys(Keys.TAB);
		Thread.sleep(5000);

		utils.isElementPresent(driver, "MA_AddAddress_City_Header", "City Not Populated After Entering Zipcode");

		if (!verifyCorrectCityIsPopulatedInAddressModal(Add_City)) {

			Address_city().clear();
			Address_city().sendKeys(Add_City);
			Thread.sleep(2000);
		}

		Address_phone().sendKeys(Add_phone);

		Address_save_btn().click();
		Thread.sleep(1000);

		Utils.waitForPageLoaded(driver);

		Assert.assertTrue(utils.isElementPresent(driver, "MA_Address_remove_btn"),
				"Address has not been added as Remove button is not present");
	}

	/**
	 * Author - @Prateek | Method to verify that correct city is auto-populated in
	 * Address Book modal using Google services as the city gets mis-populated at
	 * times Method utilises the getText() function for FP sites and getAttribute()
	 * function for Factory sites to get the value of the city auto-populated
	 * 
	 * @param cityName
	 *            - Actual city name that should be present
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public synchronized boolean verifyCorrectCityIsPopulatedInAddressModal(String cityName) throws Exception {

		ReporterLog.log("Method to verify that correct city is populated...");
		return (Address_city().getText().equals(cityName) || Address_city().getAttribute("value").equals(cityName));
	}

	public synchronized WebElement Address_Fname() throws Exception {

		if (Utils.device.equals("mobile") && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_Address_fname", "First name field is not visible");

		return utils.findElementByLocator(driver, "MA_Address_fname", "First name field is not visible");
	}

	public synchronized WebElement Address_Lname() throws Exception {

		if (Utils.device.equals("mobile") && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_Address_Lname",
					"Last name field is not displayed in mobile");

		return utils.findElementByLocator(driver, "MA_Address_Lname", "Last name field is not displayed");
	}

	public synchronized WebElement Address_Addr1() throws Exception {

		if (Utils.device.equals("mobile") && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_Address_Addr1",
					"Address Line 1 is not displayed in mobile");

		return utils.findElementByLocator(driver, "MA_Address_Addr1", "Address Line 1 is not displayed");
	}

	public synchronized WebElement Address_Addr2() throws Exception {
		return utils.findElementByLocator(driver, "MA_Address_Addr2", "Address Line 2 is not displayed");
	}

	public synchronized WebElement Address_Zip() throws Exception {

		if (Utils.device.equals("mobile") && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_Address_zip", "Zip field is not displayed in mobile");

		return utils.findElementByLocator(driver, "MA_Address_zip", "Zip field is not displayed");
	}

	public synchronized WebElement Address_city() throws Exception {

		if (Utils.device.equals("mobile") && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_Address_city", "City field is not displayed in mobile");

		return utils.findElementByLocator(driver, "MA_Address_city", "City field is not displayed");
	}

	public synchronized WebElement Address_state() throws Exception {
		return utils.findElementByLocator(driver, "MA_Address_state", "state field is not displayed");
	}

	public synchronized WebElement Address_phone() throws Exception {

		if (Utils.device.equals("mobile") && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_Address_phn",
					"Phone number field is not displayed in mobile");

		return utils.findElementByLocator(driver, "MA_Address_phn", "Phone number field is not displayed");
	}

	public synchronized WebElement Address_clearAll_btn() throws Exception {
		return utils.findElementByLocator(driver, "MA_Address_clearAll", "ClearAll button is not displayed");
	}

	public synchronized WebElement Address_save_btn() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_Address_save_btn",
					"Save button is not displayed in mobile");

		return utils.findElementByLocator(driver, "MA_Address_save_btn", "Save button is not displayed");
	}

	public synchronized WebElement EditAddressbtn() throws Exception {
		return utils.findElementByLocator(driver, "MA_Address_edit_btn", "Edit Address button is not displayed");
	}

	public synchronized WebElement RemoveAddressbtn() throws Exception {
		return utils.findElementByLocator(driver, "MA_Address_remove_btn", "Remove Address button is not displayed");
	}

	public synchronized List<WebElement> lst_RemoveAddressbtn() throws Exception {
		return utils.findElementsByLocator(driver, "MA_Address_remove_btn", "Remove Address button is not displayed");
	}

	public synchronized WebElement ConfirmRemoveAddressbtn() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_MA_Address_remove_confirm_btn",
					"Confirm Remove Address button is not displayed");
		else

			return utils.findElementByLocator(driver, "MA_Address_remove_confirm_btn",
					"Confirm Remove Address button is not displayed");
	}

	public synchronized WebElement closebtn() throws Exception {
		return utils.findElementByLocator(driver, "MA_CloseButton", "Close button is not displayed");
	}

	public synchronized WebElement SearchOrder() throws Exception {
		Assert.assertTrue(utils.isElementPresent(driver, "MA_searchOrder"), "Search Order text field not displayed");
		return utils.findElementByLocator(driver, "MA_searchOrder", "Search Order text field not displayed");
	}

	public synchronized WebElement SubmitSearchOrder() throws Exception {
		Assert.assertTrue(utils.isElementPresent(driver, "MA_searchOrder_submit"),
				"submit Search Order button not displayed");
		return utils.findElementByLocator(driver, "MA_searchOrder_submit", "Submit Search Order button not displayed");
	}

	public synchronized void SearchparticularOrder(String OrderNumber) throws Exception {
		List<WebElement> ordernumber = utils.findElementsByLocator(driver, "MA_OrderReturn_OrdersNumber",
				"No Order found");
		for (int i = 1; i < ordernumber.size(); i++) {
			if (ordernumber.get(i).getText().equals(OrderNumber)) {
				// ordernumber.get(i).click();
				Assert.assertTrue(utils.isElementPresent(driver, "MA_orderstaus_afterexpand"),
						"Searched order is not expanded");
				break;
			} else {
				ReporterLog.fail("Order number " + ordernumber + " is not found");
			}
		}
	}

	public synchronized WebElement expandSearchResult() throws Exception {
		return utils.findElementByLocator(driver, "MA_expand_searchOrder", "+ not displayed for expansion");
	}

	public synchronized void addNewCard(int excelAddressRowIndex, String cardType) throws Exception {

		PaymentPageObject paymentpage = new PaymentPageObject(driver, Utils.brand, Utils.browser, Utils.device);

		ReporterLog.headerString("********** Verify Add Payment **************");
		String Add_FName = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelAddressRowIndex, 1);
		String Add_LName = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelAddressRowIndex, 2);
		String Add_Address = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelAddressRowIndex, 3);
		String Add_zipCode = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelAddressRowIndex, 8);
		String Add_phone = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelAddressRowIndex, 9);
		String Add_City = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_ADDRESS, excelAddressRowIndex, 6);

		Thread.sleep(2000);
		verifyPaymentMenthodsHeader();

		utils.scrollToViewElement(driver, AddNewPaymentbtn());
		Thread.sleep(2000);
		AddNewPaymentbtn().click();
		Thread.sleep(5000);

		Assert.assertTrue(utils.isElementPresent(driver, "Paymentiframe"),
				"Payment Iframe for adding payment is not displayed");

		int rowNo = paymentpage.getCardTypeRowNumber(driver, cardType);

		paymentpage.switchToiframe();
		Thread.sleep(3000);

		Utils.waitForPageLoaded(driver);
		utils.waitForElementToBeClickable(driver, "Pay_txt_CardNo", "credit card not loaded");

		String excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 1);

		paymentpage.txt_cardNumber().click();
		paymentpage.txt_cardNumber().clear();
		Thread.sleep(2000);

		paymentpage.txt_cardNumber().sendKeys(excelData);
		paymentpage.txt_cardNumber().sendKeys(Keys.TAB);
		Thread.sleep(2000);

		// If card type is not anntaylor loft card then select expiry date from drop
		// down list
		if ((cardType.equalsIgnoreCase("AnntaylorLoft Card")) != true) {

			// Click Exp Month Dropdown
			paymentpage.drpdwn_expiryMonth().click();
			Thread.sleep(1000);

			// Select Month From Dropdown
			excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 3);
			List<WebElement> elExMonthList = utils.findElementsByLocator(driver, "Pay_expMonthOptions",
					"| Billing : Expiry Month");
			Reporter.log(Constants.DELIMITER + "Expiry Month Enabled: "
					+ utils.selectValueFromList(elExMonthList, driver, excelData));

			// Click Exp Year Dropdown
			paymentpage.drpdwn_expiryYear().click();
			Thread.sleep(1000);

			// Select Year From Dropdown
			excelData = xlUtils.AccessExcelSheetForCellData(Constants.SHEETNAME_CREDITCARD, rowNo, 4);
			List<WebElement> elExYearList = utils.findElementsByLocator(driver, "Pay_expYearOptions",
					"| Billing : Expiry Year");
			Reporter.log(Constants.DELIMITER + "Expiry Year Enabled: "
					+ utils.selectValueFromList(elExYearList, driver, excelData));
		}

		BillingInfo_Fname().sendKeys(Add_FName);
		ReporterLog.actionMsg("Entered Billing Address First Name");

		BillingInfo_Lname().sendKeys(Add_LName);
		ReporterLog.actionMsg("Entered Billing Address Last Name");

		BillingInfo_Addr1().sendKeys(Add_Address);
		ReporterLog.actionMsg("Entered Billing Address Address Line1");

		BillingInfo_Zip().sendKeys(Add_zipCode);
		BillingInfo_Zip().sendKeys(Keys.TAB);
		Thread.sleep(5000);

		if (!verifyCorrectCityIsPopulatedInPaymentIFrame(Add_City)) {

			BillingInfo_city().clear();
			BillingInfo_city().sendKeys(Add_City);
			Thread.sleep(2000);
		}

		BillingInfo_phone().sendKeys(Add_phone);

		WebElement elem = BillingInfo_save_btn();
		utils.clickWebElement(driver, elem, "save button is not clicked");

		Thread.sleep(2000);
		Utils.waitForPageLoaded(driver);
		ReporterLog.actionMsg("Clicked Save Button");

		driver.switchTo().defaultContent();
		Thread.sleep(1000);

		Assert.assertTrue(utils.isElementPresent(driver, "MA_billing_remove_btn"),
				"Payment has not been added as Remove button is not present");
		ReporterLog.pass("Card is added: " + cardType);
	}

	public synchronized void verifyPaymentMenthodsHeader() {

		ReporterLog.log("Verifying Add New Card Modal Header is present....");

		if (Utils.brand.equalsIgnoreCase("ATF") || Utils.brand.equalsIgnoreCase("ATFP")) {

			Assert.assertTrue(utils.isElementPresent(driver, "MA_PaymentsMethod_header"),
					"Payment  Header is not displayed");

		} else if (Utils.brand.equalsIgnoreCase("LO") || Utils.brand.equalsIgnoreCase("LOFP")) {

			Assert.assertTrue(utils.isElementPresent(driver, "MA_PaymentsMethod_headerLO"),
					"Payment  Header is not displayed");
		}

	}

	/**
	 * Author - @Prateek | Method to verify that correct city is auto-populated in
	 * SparkRed Iframe using Google services as the city gets mis-populated at times
	 * Method utilises the getText() function for FP sites and getAttribute()
	 * function for Factory sites to get the value of the city auto-populated
	 * 
	 * @param cityName
	 *            - Actual city name that should be present
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public synchronized boolean verifyCorrectCityIsPopulatedInPaymentIFrame(String cityName) throws Exception {

		ReporterLog.log("Method to verify that correct city is populated in payment iframe...");
		return (BillingInfo_city().getText().equals(cityName)
				|| BillingInfo_city().getAttribute("value").equals(cityName));
	}

	public synchronized WebElement AddNewPaymentbtn() throws Exception {
		if (utils.device.equals("mobile")) {
			return utils.findElementByLocator(driver, "MBL_MA_paymentMethod_addnew", "Add new Button is not dispayed");
		} else {
			if (Utils.brand.equals("LO") || Utils.brand.equals("LOFP")) {
				return utils.findElementByLocator(driver, "MA_paymentMethod_addnewLO",
						"Add new Button is not dispayed");
			} else {
				return utils.findElementByLocator(driver, "MA_paymentMethod_addnew", "Add new Button is not dispayed");
			}
		}
	}

	public synchronized WebElement BillingInfo_Fname() throws Exception {
		return utils.findElementByLocator(driver, "MA_billingInfo_fname", "First name field is not visible");
	}

	public synchronized WebElement BillingInfo_Lname() throws Exception {
		return utils.findElementByLocator(driver, "MA_billingInfo_Lname", "Last name field is not displayed");
	}

	public synchronized WebElement BillingInfo_Addr1() throws Exception {
		return utils.findElementByLocator(driver, "MA_billingInfo_Addr1", "Address Line 1 is not displayed");
	}

	public synchronized void clickSignout() throws Exception {

		if (utils.device.equals("mobile")) {
			utils.scrollUp(driver);
			utils.waitForElementToBeClickable(driver, "MB_HamBurger", "");
			Thread.sleep(1000);
			homePage.lnk_hamBurger().click();
			Thread.sleep(1000);
		} else {
			WebElement elemSignIn = utils.findElementByLocator(driver, "HP_lnk_SignIn", "Unable to locate SignIn link");
			utils.mouseHoverJScript(driver, elemSignIn);
		}
		utils.clickWebElement(driver, signOut(), "Sign Out link not found");
		Utils.waitForPageLoaded(driver);
		driver.navigate().refresh();
		Thread.sleep(1000);
		Utils.waitForPageLoaded(driver);
	}

	public synchronized WebElement BillingInfo_Zip() throws Exception {
		return utils.findElementByLocator(driver, "MA_billingInfo_zip", "Zip field is not displayed");
	}

	public synchronized WebElement BillingInfo_city() throws Exception {
		return utils.findElementByLocator(driver, "MA_billingInfo_city", "City field is not displayed");
	}

	public synchronized void loginToAccount(String userName, String password) throws Exception {
		if (Utils.browser.equals("android") || Utils.device.equals("desktop")) {
			EmailField().sendKeys(userName);
			ReporterLog.actionMsg("Entered user ID: " + userName);
			Passwordfield().sendKeys(password);
			ReporterLog.actionMsg("Entered Password " + password);
			Thread.sleep(1000);
			Submit_btn().click();
			ReporterLog.actionMsg("Clicked submit button");
			Thread.sleep(1000);
			Utils.waitForPageLoaded(driver);
			utils.waitForElementToPresent(driver, "MA_DashbordHeader", 90);
			ReporterLog.pass("Succesfully logged in site");
		} else {
			EmailField().click();
			utils.findElementsByIOSNameLocator("MBL_EmailID_IOS").get(1).sendKeys(userName);
			ReporterLog.actionMsg("Entered user ID: " + userName);
			utils.findElementsByIOSNameLocator("MBL_Password_IOS").get(1).sendKeys(password);
			ReporterLog.actionMsg("Entered Password " + password);
			Submit_btn().click();
			ReporterLog.actionMsg("Clicked submit button");
			Thread.sleep(1000);
			Utils.waitForPageLoaded(driver);
			utils.waitForElementToPresent(driver, "MA_DashbordHeader", 90);
			ReporterLog.pass("Succesfully logged in site");
		}
	}

	public synchronized WebElement BillingInfo_state() throws Exception {
		return utils.findElementByLocator(driver, "MA_billingInfo_state", "state field is not displayed");
	}

	public synchronized WebElement BillingInfo_phone() throws Exception {
		return utils.findElementByLocator(driver, "MA_billingInfo_phn", "Zip field is not displayed");
	}

	public synchronized WebElement Billing_clearAll_btn() throws Exception {
		return utils.findElementByLocator(driver, "MA_billingInfo_clearAll", "ClearAll button is not displayed");
	}

	public synchronized WebElement BillingInfo_save_btn() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_billingInfo_save_btn",
					"Save button is not displayed in mobile");

		return utils.findElementByLocator(driver, "MA_billingInfo_save_btn", "Save button is not displayed");
	}

	public synchronized WebElement EditCardbtn() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_billing_edit_btn",
					"Edit Card button is not displayed in mobile");

		return utils.findElementByLocator(driver, "MA_billing_edit_btn", "Edit Card button is not displayed");
	}

	public synchronized WebElement RemoveCardbtn() throws Exception {

		if (Utils.isDeviceMobile() && Utils.isBrandFullPrice())
			return utils.findElementByLocator(driver, "MBL_MA_billing_remove_btn",
					"Remove Card button is not displayed in mobile");

		return utils.findElementByLocator(driver, "MA_billing_remove_btn", "Remove Card button is not displayed");
	}

	public synchronized List<WebElement> lst_RemoveCardbtn() throws Exception {
		return utils.findElementsByLocator(driver, "MA_billing_remove_btn", "Remove Card button is not displayed");
	}

	public synchronized WebElement ConfirmRemoveCardbtn() throws Exception {
		if (Utils.isDeviceMobile()) {
			return utils.findElementByLocator(driver, "MBL_MA_billing_remove_confirm_btn",
					"Confirm Remove Card button is not displayed");
		} else {
			return utils.findElementByLocator(driver, "MA_billing_remove_confirm_btn",
					"Confirm Remove Card button is not displayed");
		}
	}

	public synchronized WebElement SavedCardType() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementByLocator(driver, "MBL_CardImage", "Saved Card Type is not displayed");
		else
			return utils.findElementByLocator(driver, "MA_CardType_Payment", "Saved Card Type is not displayed");
	}

	public synchronized List<WebElement> Check_default_selected_card() throws Exception {
		if (Utils.device.equals("mobile"))
			return utils.findElementsByLocator(driver, "MBL_MA_paymentoption_checkDefault", "No Card is selected");
		else
			return utils.findElementsByLocator(driver, "MA_paymentoption_checkDefault", "No Card is selected");
	}

	public synchronized List<WebElement> orderReturn_OrderNumber() throws Exception {
		return utils.findElementsByLocator(driver, "MA_OrderReturn_OrdersNumber", "No Order Number is selected");
	}

	/**
	 * Get the Save button
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement btn_Billing_Save_Mobile() throws Exception {
		MobileView.switchToNative(driver);
		return utils.findElementByAndroidID("MBL_MA_billingInfo_save_btn");
	}

	public synchronized void addAssociateID(String associateID, String zipCode, String dob) throws Exception {
		profile_AssociateID().sendKeys(associateID);

		// clicking on continue
		profile_btn_Continue().click();
		Utils.waitForPageLoaded(driver);

		// waiting for security question label
		utils.waitForElementToBeVisible(driver, utils.findElementByLocator(driver,
				"MA_ProfilePreference_SecurityQuestion_lbl", "Security Question label not present"));

		// entering zipcode and DOB
		profile_txt_ZipCode().sendKeys(zipCode);
		profile_txt_DOB().sendKeys(dob);
		// profile_btn_Submit().click();
		profile_btn_Continue().click();
		Utils.waitForPageLoaded(driver);

	}

	public synchronized void removeAssociateID() throws Exception {
		utils.findElementByLocator(driver, "MA_ProfilePreference_Remove", "Remove Button not found").click();
		Utils.waitForPageLoaded(driver);

		utils.findElementByLocator(driver, "MA_ProfilePreference_Remove_Confirm", "Confirm Remove Button not found")
				.click();
		Utils.waitForPageLoaded(driver);

	}

	public synchronized String creditCardType() throws Exception {
		String cardType = null;
		if (orderReturn_PaymentSection().getText().contains("Visa")) {
			cardType = "Visa";
		} else if (orderReturn_PaymentSection().getText().contains("Master")) {
			cardType = "MasterCard";
		} else if (orderReturn_PaymentSection().getText().contains("AnntaylorLoft Card")) {
			cardType = "AnntaylorLoft Card";
		} else if (orderReturn_PaymentSection().getText().contains("Ann Taylor/LOFT Mastercard")) {
			cardType = "Ann Taylor/LOFT Mastercard";
		} else if (orderReturn_PaymentSection().getText().contains("American Express")) {
			cardType = "American Express";
		} else if (orderReturn_PaymentSection().getText().contains("Discover")) {
			cardType = "Discover";
		} else if (orderReturn_PaymentSection().getText().contains("Diners")) {
			cardType = "Diners";
		} else if (orderReturn_PaymentSection().getText().contains("JCB")) {
			cardType = "JCB";
		}
		return cardType;
	}

	public synchronized void removeAllAddresses() throws Exception {
		AddressBook_lnk().click();
		ReporterLog.actionMsg("Clicked Address Book");
		Utils.waitForPageLoaded(driver);

		if (utils.isElementPresent(driver, "MA_Address_remove_btn", 5)) {
			List<WebElement> removeAll = lst_RemoveAddressbtn();
			for (int i = 1; i <= removeAll.size(); i++) {
				List<WebElement> removeBtn = lst_RemoveAddressbtn();
				utils.clickWebElement(driver, removeBtn.get(removeAll.size() - i), "");
				Thread.sleep(1000);
				ConfirmRemoveAddressbtn().click();
				Thread.sleep(1000);
				Utils.waitForPageLoaded(driver);
			}
			ReporterLog.actionMsg("Removed all addresses");
		}
	}

	public synchronized void removeAllCards() throws Exception {
		paymentMethod_lnk().click();
		ReporterLog.actionMsg("Clicked Payment Method/Wallet");
		Utils.waitForPageLoaded(driver);

		if (utils.isElementPresent(driver, "MA_billing_remove_btn", 5)) {
			List<WebElement> removeAll = lst_RemoveCardbtn();
			for (int i = 1; i <= removeAll.size(); i++) {
				List<WebElement> removeBtn = lst_RemoveCardbtn();
				utils.clickWebElement(driver, removeBtn.get(removeAll.size() - i), "");
				Thread.sleep(1000);
				ConfirmRemoveCardbtn().click();
				Thread.sleep(1000);
				Utils.waitForPageLoaded(driver);
			}
			ReporterLog.actionMsg("Removed all cards");
		}
	}

	/**
	 * Get the first name in addr
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_shippingAddrName() throws Exception {

		return utils.findElementByLocator(driver, "MA_AddressName_PaymentMethod", "name not shown");
	}

	/**
	 * Get the addr1 in addr
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_shippingAddr1() throws Exception {

		return utils.findElementByLocator(driver, "MA_Address_PaymentMethod", "addr1 not shown");
	}

	/**
	 * Get the city and state in add
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_shippingAddrCityNState() throws Exception {

		return utils.findElementByLocator(driver, "MA_AddressCityState_PaymentMethod", "cityandstate not shown");
	}

	/**
	 * Get the phone num in addr
	 * 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_shippingAddrPhoneNum() throws Exception {

		return utils.findElementByLocator(driver, "MA_AddressPhoneNo_PaymentMethod", "phone not shown");
	}

	/**
	 * Click on Wishlist in My Account Navigation
	 * 
	 * @return WebElement
	 * @throws Exception
	 */

	public synchronized void wishList_Link() throws Exception {

		if (Utils.isDeviceMobile()) {

			utils.isElementPresent(driver, "MBL_WishList_MainNav");
			WebElement elem = utils.findElementByLocator(driver, "MBL_WishList_MainNav", "Wishlist not displayed");
			elem.click();
			ReporterLog.actionMsg("Clicked on Wishlist in My Account in mobile");

		} else {

			utils.isElementPresent(driver, "WishList_MainNav");
			WebElement elem = utils.findElementByLocator(driver, "WishList_MainNav", "Wishlist not displayed");
			elem.click();
			ReporterLog.actionMsg("Clicked on Wishlist in My Account");
		}
	}

	/**
	 * Verify Wishlist header in My Account Navigation
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized void verifyWishListHeader(String numItems) throws Exception {

		Thread.sleep(3000);
		utils.isElementPresent(driver, "WishList_Header");
		WebElement wishListElement = utils.findElementByLocator(driver, "WishList_Header",
				"Wishlist Header not displayed");

		Assert.assertTrue(wishListElement.getText().contains(numItems));
	}

	/**
	 * Retuns empty wishlist message
	 * 
	 * @return String
	 * @throws Exception
	 */
	public synchronized String wishListEmptyMsg() throws Exception {

		utils.isElementPresent(driver, "WishList_Empty_Msg");
		return utils.findElementByLocator(driver, "WishList_Empty_Msg", "Wishlist empty message displayed").getText();
	}

	/**
	 * Remove item from wishlist
	 * 
	 * @return String
	 * @throws Exception
	 */
	public synchronized void removeProductFromWishList() throws Exception {

		utils.isElementPresent(driver, "WishList_Remove");
		utils.findElementByLocator(driver, "WishList_Remove", "Wishlist empty message displayed").click();
		ReporterLog.actionMsg("Clicked on Remove button in Wishlist");
	}

	public synchronized void removeProductsFromWishList() throws Exception {

		utils.isElementsPresent(driver, "WishList_Remove", "Remove button present in Wishlist");
		List<WebElement> removeButtons = utils.findElementsByLocator(driver, "WishList_Remove",
				"Wishlist empty message displayed");

		for (WebElement button : removeButtons) {

			button.click();
			Thread.sleep(5000);
		}

		ReporterLog.log("All Items have been cleared from Wishlist");
	}

}
