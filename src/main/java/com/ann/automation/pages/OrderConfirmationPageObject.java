package com.ann.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;
import com.ann.automation.pages.HomePageObjects;
import com.ann.automation.pages.ShoppingBagPageObjects;

public class OrderConfirmationPageObject {
	private static WebDriver driver=null;
	static Utils utils=null;
	ShoppingBagPageObjects shoppingBag=null;
	HomePageObjects homepage=null;
	static XLUtils xlUtils=null;
	public OrderConfirmationPageObject(WebDriver driver,String brand,String browser, String device)
	{
		this.driver=driver;
		utils=new Utils(brand,browser,device);
		shoppingBag=new ShoppingBagPageObjects(driver, brand, browser, device);
		homepage=new HomePageObjects(driver, brand, browser, device);
		xlUtils=new XLUtils();
	}
	/**
	 * Order confirmation | order status link 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_orderStatus() throws Exception {

		return utils.findElementByLocator(driver, "OC_orderStatus_lnk", "| OC | order status link not present ");
	}
	/**
	 * Order confirmation | shipping policy link 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_shippingPolicy() throws Exception {

		return utils.findElementByLocator(driver, "OC_shippingPolicyLink", "| OC | order status link not present ");
	}
	/**
	 * Order confirmation | return link 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_returnPolicy() throws Exception {

		return utils.findElementByLocator(driver, "OC_returnPolicyLink", "| OC | order status link not present ");
	}
	/**
	 * Order confirmation | return link 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_orderSummery() throws Exception {

		return utils.findElementByLocator(driver, "OC_orderSummeryLabel", "| OC | order status link not present ");
	}
	/**
	 * Order confirmation | back to shopping button
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_backToShopping() throws Exception {

		return utils.findElementByLocator(driver, "MBL_OC_BackToShopping", "| OC | back to top shopping button not present");
	}
	
}
