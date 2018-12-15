package com.ann.automation.tests.desktop.stepDefinitions;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import com.ann.automation.utilities.Placeholder;
import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddEditOrdRevShipAdd extends Placeholder {
	// ------------------------- Variables declaration ------------------------------
	private String updatedAddress=null;
	private final Logger LOG = LogManager.getLogger(this.getClass().getSimpleName());
	// ------------------------------------------------------------------------------
	
	@Given("^User is on Brand Home Page \"([^\"]*)\"$")
	public void user_is_on_Brand_Home_Page(String siteUrl) throws Exception {
		launchBrandSite(siteUrl);
		//captureScreenshot(driver);
		}

	@When("^User clicks on Super & Sub Category from Mega Menu, She lands on the PLP Page$")
	public void user_clicks_on_Super_Sub_Category_from_Mega_Menu_She_lands_on_the_PLP_Page() throws Exception {
		plpPage.navigateToPLP(driver, "Clothing", "Dresses");
		utils.waitForElementToBeVisible(driver, plpPage.list_Products().get(0));	
	}

	@When("^User choose to add product from Quickshop Modal$")
	public void user_choose_to_add_product_from_Quickshop_Modal() {
		plpPage.addProductFromQuickShopModal(driver,plpPage,pdpPage);
	}

	@When("^User choose to log into site as Registered User$")
	public void user_choose_to_log_into_site_as_Registered_User() throws Exception {
		String regUserID=stringDataReaderJSON("EmailID");
		String regPwd=stringDataReaderJSON("Password");
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(90, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
		utils.waitForElementToPresent(driver, "SB_ProceedToCheckout", "proceed to checkout button not loaded",wait);
		checkout.checkOutAsRegistered(driver, regUserID, regPwd);
	}

	@Then("^Verify User shall land on Order Review Page$")
	public void verify_User_shall_land_on_Order_Review_Page() throws Exception {
		try {
			Assert.assertTrue(utils.isElementPresent(driver, "OR_BillTo_AppliedPayment"),"Order Review: Bill To Section- Payment Method");
			Assert.assertFalse(utils.findElementByLocator(driver, "OR_BillTo_AppliedPayment", "Bill To Section-Payment Method not present").getText().isEmpty());
			LOG.info("| Order Review: Payment Method Display");
		} catch (AssertionError e) {
			LOG.error("| Order Review: Billing Payment Method not Display");
			e.printStackTrace();
			errorBuffer.append(e.getMessage() + "\n");
		}
	}

	@Then("^User choose to Add New Shipping Add clicking on Add New Button$")
	public void user_choose_to_Add_New_Shipping_Add_clicking_on_Add_New_Button() throws Exception {
		shippingAddr = XLUtilsObj.accessExcelSheetForeCol(Constants.SHEETNAME_ADDRESS, 1);
		if(!utils.brand.equals("LGFP"))
		{
			shippingObj.clickAddShippingSectionReview(driver);
			shippingObj.fillShippingAddr(driver, shippingAddr);
			shippingObj.btn_SP_ContinuePayment().click();
			shippingObj.handleVerifyYourAddressModal();
			Utils.waitForPageLoaded(driver);
		}
	}

	@Then("^Verify New Shipping Address reflects on Order Review Page$")
	public void verify_New_Shipping_Address_reflects_on_Order_Review_Page() throws Exception {
		updatedAddress=utils.findElementByLocator(driver, "OR_ShippingAddressOrderReview", "").getText().trim();
		try {
			Assert.assertTrue(updatedAddress.contains(shippingAddr.get(1)),"First Name is not as expected");
			LOG.info("| Address has been been successfully reflected on Order Review Page");
		} catch (AssertionError e) {
			LOG.error("| Address has not been reflected on Order Review Page");
			errorBuffer.append(e.getMessage() + "\n");
		}
	}

	@Then("^User choose to Edit Shipping Add clicking on Edit Button$")
	public void user_choose_to_Edit_Shipping_Add_clicking_on_Edit_Button() throws Exception {
		shippingAddr = XLUtilsObj.accessExcelSheetForeCol(Constants.SHEETNAME_ADDRESS, 1);
		shippingObj.clickEditShippingSectionReview(driver);
		shippingObj.fillShippingAddr(driver, shippingAddr);
		shippingObj.btn_SP_ContinuePayment().click();
		shippingObj.handleVerifyYourAddressModal();
		Utils.waitForPageLoaded(driver);
		Thread.sleep(5000);
	}	

	@Then("^Verify Updated Shipping Add on Order Review Page$")
	public void verify_Updated_Shipping_Add_on_Order_Review_Page() throws Exception {
		updatedAddress=utils.findElementByLocator(driver, "OR_ShippingAddressOrderReview", "").getText().trim();
		Thread.sleep(5000);
		try {
			Assert.assertTrue(updatedAddress.contains(shippingAddr.get(1)),"First Name is not as expected");
			LOG.info("| Address has been been successfully reflected on Order Review Page");
		} catch (AssertionError e) {
			LOG.error("| Address has not been reflected on Order Review Page");
			e.printStackTrace();
			errorBuffer.append(e.getMessage() + "\n");
		}
	}
}