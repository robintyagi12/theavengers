package com.ann.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.Utils.MobileView;
import com.ann.automation.utilities.Utils.ReporterLog;

import io.appium.java_client.ios.IOSDriver;



public class CustomerServiceObjects{
	private static WebDriver driver=null;
	static Utils utils=null;
	public CustomerServiceObjects(WebDriver driver,String brand,String browser, String device)
	{
		this.driver=driver;
		utils=new Utils(brand, browser, device);
	}
	
	/**
	 * Get the Customer Service link from home page	
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lbl_FindAnAnswer() throws Exception{
		return utils.findElementByLocator(driver, "CS_lbl_FindAnAnswer", "| Customer Service");
	}
	
	public synchronized WebElement Click_ViewOrder() throws Exception
	{
		return utils.findElementByLocator(driver, "CS_btn_viewOrder", "| View Order");
	}
	
	/**
	 * Get the Privacy Policy	
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lbl_privacySecurity() throws Exception{
		return utils.findElementByLocator(driver, "CSC_PrivacySecurity", "| Customer Service");
	}
	/**
	 * Get Place holder value for Order Number field
	 * @return
	 * @throws Exception
	 */
	
	public synchronized String getOrderNumberPlaceHolder() throws Exception
	{
		return utils.findElementByLocator(driver, "CS_CU_OrderNumber","Order Number Placeholder").getAttribute("placeholder");
	}
	
	public synchronized void OrderNumber() throws Exception
	{
		WebElement ordernumber = utils.findElementByLocator(driver, "CS_CU_OrderNumber","Order Number Placeholder");
		// Validating for Invalid Order Number length<8
		ordernumber.sendKeys("1234");
		ordernumber.sendKeys(Keys.TAB);
		Assert.assertTrue(utils.isElementPresent(driver, "CS_CU_Order_errorMsg"),"Error message is not present");
		ReporterLog.pass("Error message is present as order number length < 8");
		ordernumber.clear();
		ordernumber.sendKeys("123456789");
		ordernumber.sendKeys(Keys.TAB);
		Assert.assertTrue(utils.isElementPresent(driver, "CS_CU_Order_Validation"),"Green check is not present");
		ReporterLog.pass("Green check is present on entering Valid Order Number");
		Assert.assertTrue(utils.isElementPresent(driver, "CS_CU_OrderNumberText", "Order Number Text Post entring Order Number"),"Order Number Text is not present");
		ReporterLog.pass("Order Number Text above Order number field is visible");
		
		
		
	}
	/**
	 * Get the Terms Of Use
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lbl_termsOfUse() throws Exception{
		return utils.findElementByLocator(driver, "CSC_TermOfUse", "| Customer Service");
	}
	
	/**
	 * Get the Customer Service link from home page	
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lbl_topAnswer() throws Exception{
		WebElement element=null;
		if(utils.device.equals("desktop"))
		{
			element= utils.findElementByLocator(driver, "CS_lnk_topQuestions", "| Customer Service");
		}
		else{
			element= utils.findElementByLocator(driver, "CS_lnk_topQuestions_Mobile", "| Customer Service");
			
		}
		return element;
	}
	
	/**
	 * Get the Top Question List Div
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lbl_topAnswerListDiv() throws Exception{
		return utils.findElementByLocator(driver, "CSC_TopListDiv", "| Customer Service");
	}
	
	/**
	 * Get the Top Question Div
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lbl_topAnswerDiv() throws Exception{
		return utils.findElementByLocator(driver, "CSC_CloseTopQuestionDiv", "| Customer Service");
	}
	
	/**
	 * Get the Customer Service link from home page	
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_topQuestions() throws Exception{
		return utils.findElementsByLocator(driver, "CS_lst_topQuestions", "| Customer Service");
	}
	

	/**
	 * Get the Privacy Policy Question
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_PrivacyPolicy() throws Exception{
		return utils.findElementsByLocator(driver, "CSC_PrivacySecurityQuestion", "| Customer Service");
	}
	
	/**
	 * Get the TERMS OF USE Question
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_termsOfUse() throws Exception{
		return utils.findElementsByLocator(driver, "CSC_TermOfUseQues", "| Customer Service");
	}
	
	/**
	 * Get the Customer Service link from home page	
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_MB_topQuestionsDropdown() throws Exception{
		return utils.findElementsByLocator(driver, "CS_MB_topQuestionList", "| Customer Service");
	}
	
	/**
	 * Get the Customer Service link from home page	
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_GiftingServices_quesions() throws Exception{
		return utils.findElementsByLocator(driver, "CS_lst_giftingServicesQuestion", "| Customer Service");
	}
	/**
	 * Get the Customer Service link from home page	
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_returnsAndExchangeTopics() throws Exception{
		if(utils.brand.equals("ATF"))
		{
		return utils.findElementsByLocator(driver, "CS_lst_retAndExchQuestion", "| Customer Service");
		}else
		{
			return utils.findElementsByLocator(driver, "CS_lst_retAndExchQuestion_LO", "| Customer Service");
		}
	}
	/**
	 * Get the Customer Service link from home page	
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_giftingServices_topics() throws Exception{
		if(utils.brand.equals("ATF"))
		{
		return utils.findElementsByLocator(driver, "CS_lst_giftingServicesQuestion", "| Customer Service");
		}
		else{
			
			return utils.findElementsByLocator(driver, "CS_lst_giftingServicesQuestion_LO", "| Customer Service");
		}
	}
	/**
	 * Get the Customer Service link from home page	
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_shoppingOnATFTopics() throws Exception{
		if(utils.brand.equals("ATF"))
		{
		return utils.findElementsByLocator(driver, "CS_lst_shoppingATQuestions", "| Customer Service");
		}else
		{
			return utils.findElementsByLocator(driver, "CS_lst_shoppingATQuestions_LO", "| Customer Service");
		}
	}
	
	
	/**
	 * Get the Contact Us	
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement contactUsSection() throws Exception{
		return utils.findElementByLocator(driver, "section_ContactUs", "| Contact Us Tab");
	}
	
	/**
	 * Get the Contact Us	
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement contactUsTab() throws Exception{
		return utils.findElementByLocator(driver, "ContactUsTab", "| Contact Us Tab");
	}
	/**
	 * Get the Contact Us Tab -email
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement contactUsTab_txt_email() throws Exception{
		return utils.findElementByLocator(driver, "CS_CU_txt_email", "| E-Mail TextBox");
	}
	/**
	 * Get the Contact Us Tab -name
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement contactUsTab_txt_name() throws Exception{
		return utils.findElementByLocator(driver, "CS_CU_txt_Name", "| E-Mail TextBox");
	}
	/**
	 * Get the Contact Us Tab -name
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement contactUsTab_txt_confirmEmail() throws Exception{
		return utils.findElementByLocator(driver, "CS_CU_txt_ConfirmMail", "| E-Mail TextBox");
	}
	/**
	 * Get the Contact Us Tab -name
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement contactUsTab_drpdwn_subject() throws Exception{
		WebElement element=null;
		if(utils.device.equals("desktop"))
		{
			element= utils.findElementByLocator(driver, "CS_CU_drpdwn_Subject", "| E-Mail TextBox");
		}
		else if(Utils.browser.equals("ios"))
		{
			MobileView.switchToNative(driver);
			List<WebElement>elements= ((IOSDriver)driver).findElements(By.className("XCUIElementTypeOther"));
			for(WebElement e:elements)
			{
				if(e.getAttribute("value")!=null) {
					if(e.getAttribute("value").equalsIgnoreCase("Subject"))
					{
						element=e;
						return element;
					}
				}
			}
		}
		else if(Utils.browser.equals("android"))
		{
			element= utils.findElementByLocator(driver, "CS_CU_drpdwn_Subject_Android", "| E-Mail TextBox");
			
		}
		return element;
	}
	/**
	 * Get the Contact Us Tab -name
	 * @return
	 * @throws Exception
	 */
	public void select_subjectOption(int value) throws Exception{
		List<WebElement> options=utils.findElementsByLocator(driver, "CS_CU_li_subjectOptions", "subject options not found");
		options.get(value-1).click();
		Thread.sleep(2000);
	}
	/**
	 * Get the Contact Us Tab -name
	 * @return
	 * @throws Exception
	 */
	public void select_subjectOption_android(int value) throws Exception{
		List<WebElement> options=utils.findElementsByLocator(driver, "CS_CU_li_subjectOptions_android", "subject options not found");
		options.get(value-1).click();
		Thread.sleep(2000);
	}
	/**
	 * Get the Contact Us Tab -email send button
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement contactUsTab_btn_send() throws Exception{
		return utils.findElementByLocator(driver, "CS_EU_btn_send", "| E-Mail TextBox");
	}
	/**
	 * Get the email Us Tab -message textbox
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_emailUs_msg() throws Exception{
		return utils.findElementByLocator(driver, "CS_CU_txt_Message", "| message TextBox");
	}
	
	/**
	 * Get the gifting service link
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lnk_giftingServices() throws Exception{
		return utils.findElementByLocator(driver, "CS_lnk_giftingServices", "| gifting services link");
	}
	/**
	 * Get the Contact Us Tab -callus
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lnk_CallUs() throws Exception{
		return utils.findElementByLocator(driver, "CS_CU_lnk_CallUs", "| Call Us");
	}
	/**
	 * Get the Contact Us Tab -email ud
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lnk_emailId_emailUs() throws Exception{
		if(utils.brand.equals("ATF"))
		{
		return utils.findElementByLocator(driver, "CS_EU_emailId", "| email id");
		}
		else
		{
			return utils.findElementByLocator(driver, "CS_EU_emailIdLO", "| email id");
		}
	}
	
	
	/**
	 * Get the Contact Us details
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement para_callUs_details() throws Exception{
		return utils.findElementByLocator(driver, "CS_CallUs_details", "| calling details");
	}
	/**
	 * Get the Contact Us details
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement para_writeUs_details() throws Exception{
		return utils.findElementByLocator(driver, "CS_WriteUs_details", "| calling details");
	}
	/**
	 * Get the Contact Us Tab -Write Us
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lnk_WriteUs() throws Exception{
		return utils.findElementByLocator(driver, "CS_CU_lnk_WriteUs", "| Write Us");
	}
	/**
	 * Get the Contact Us Tab -LiveChat
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement contactUsTab_lnk_LiveChat() throws Exception{
		return utils.findElementByLocator(driver, "SB_LiveChatLink", "| LiveChat");
	}
	
	public synchronized WebElement lbl_CU_emailUS() throws Exception{
		return utils.findElementByLocator(driver, "CS_CU_lbl_EmailUs", "| EmailUs");
	}
	
	/**
	 * Get the tab_CustomerService
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement tab_CustomerService() throws Exception{
		return utils.findElementByLocator(driver, "tab_CustomerService", "| Write Us");
	}
	/**
	 * Get the Top Questions - Order Status Tracking
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lnk_orderStatusTracking() throws Exception{
		return utils.findElementByLocator(driver, "CS_lnk_OrderStatusAndTracking", "| Order Status Tracking Link");
	}
	
	
	/**
	 * Get the Order Email Address TextBox
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_OrderEmailAddressText() throws Exception{
		return utils.findElementByLocator(driver,"CS_OS_txt_Email", "Order email text box locator element not found");
		
	}
	
	/**
	 * Get the Order Id TextBox
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_OrderNumber() throws Exception{
		return utils.findElementByLocator(driver,"CS_OS_txt_OrderNumber","Order Number text box element not found" );
		
	}
	
	/**
	 * Get the View Order Button
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement btn_ViewOrderButton() throws Exception{
		return utils.findElementByLocator(driver,"CS_btn_viewOrder","View Order button not found");
		
	}
	


	
	/**
	 * Get the Top Questions - Shopping Anntaylor
	 * @return
	 * @throws Exception
	 */
	public static WebElement link_TopQuestions_ShoppingAnntaylor() throws Exception{
		return utils.findElementByLocator(driver, "CustomerService-link-ShoppingAnntaylor", "| Link 'TopQuestions-ShoppingAnntaylor'");
		
	}

	/**
	 * Get the search bar text field 
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_search() throws Exception{
		return utils.findElementByLocator(driver,"CS_txtSearchInput","text Search Box  not found");
		
	}
	
	/**
	 * Get the search bar text field placeholder
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_searchFiled() throws Exception{
		return utils.findElementByLocator(driver,"CS_lbl_FindAnAnsPlaceHolder","text search place holder not found");
		
	}
	/**
	 * Get the tab links
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnks_CSTabs() throws Exception{
		return utils.findElementsByLocator(driver,"CS_tab_links","tabs not present");
		
	}
	/**
	 * Get the customer service link
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lnk_customerService() throws Exception{
		return utils.findElementByLocator(driver,"CS_CustomerService_lnk","customer service link not present in tab");
		
	}
	/**
	 * customer service | button search
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement btn_search() throws Exception{
		return utils.findElementByLocator(driver,"CS_btn_Search","search button not found");
		
	}
	/**
	 * customer service | button search
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement txt_termOfUseLink() throws Exception{
		return utils.findElementByLocator(driver,"CS_TermsOfUse","term of use not present");
		
	}
	/**
	 * Get the accrodian options for tab options
	 * @return
	 * @throws Exception
	 */
	public synchronized List<WebElement> options_accordian() throws Exception{
		return utils.findElementsByLocator(driver,"CS_accordianItems","tabs not present");
		
	}
	/**
	 * privacy security link
	 * @return
	 * @throws Exception
	 */
	public synchronized WebElement lnk_privacySecurity() throws Exception{
		return utils.findElementByLocator(driver,"CS_customerServicePrivacyLink","privacy security link not present");
		
	}
	
	
	
}
