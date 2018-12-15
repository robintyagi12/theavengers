package com.ann.automation.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.Utils.MobileView;
import com.ann.automation.utilities.Utils.ReporterLog;
import com.ann.automation.pages.HomePageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;



public class ShoppingBagPageObjects{
	private static WebDriver driver=null;
	static Utils utils=null;
	public HomePageObjects homepage=null;
	public ShoppingBagPageObjects(WebDriver driver,String brand,String browser, String device)
	{
		this.driver=driver;
		utils=new Utils(brand,browser,device);
		homepage=new HomePageObjects(driver, brand, browser, device);
	}

	/**
	 * Empty shopping bag page message
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_ShoppingBagItems() throws Exception, AssertionError{
		return utils.findElementsByLocator(driver, "SB_ShoppingBagItems", "SB: You have No items in your shopping bag");
	}

	/**
	 * Shipping page: Shipping Hearder
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement SB_shoppingBagCount() throws Exception, AssertionError{
		if(utils.device.equalsIgnoreCase("mobile")){
			return utils.findElementByLocator(driver, "MBL_SB_ItemCount", "SP: Shopping bag count not present");	
		}
		{
			return utils.findElementByLocator(driver, "SB_ItemCount", "SP: Shopping bag count not present");
		}
	}
	/**
	 * Empty shopping bag page message
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_NoItemsInSB() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SB_lbl_EmptySBMessage", "SB: You have No items in your shopping bag");
	}

	/**
	 * Gift card in shopping bag
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_GiftCardinSB() throws Exception, AssertionError{
		return utils.findElementsByLocator(driver, "SB_giftCards", "SB: Gift card item ");
	}
	/**
	 * Get list of buttons
	 * Donation in shopping bag
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_DonationsSB() throws Exception, AssertionError{
		return utils.findElementsByLocator(driver, "SB_DonationList", "SB: Donation list item ");
	}
	/**
	 * Donation in Order Summary
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_DonationOrderSummary() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SB-OrderSummary-Donation", "SB: Donation in Order summary");
	}


	/**
	 * Get the product name link on shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_productImage() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SB_ProductImage", "SB: Product Image");
	}
	/**
	 * Get the product name link on shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_productName() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SB_ProductName", "SB: Product Name Link");
	}
	/**
	 * Get the size of the product added in shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_size() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SB_Size", "SB: size Link");
	}
	/**
	 * Get the label of size of the product added in shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_size() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SB_lbl_Size", "SB: size label");
	}
	/**
	 * Get the product name link on shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_styleId() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SB_StyleId", "SB: Style Number");
	}
	/**
	 * Get the Shopping Bag Page Header on shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_ShoppingBagHeader() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SB_PageHeader", "SB: Page Header");
	}

	/**
	 * Get the Shopping Bag Page Top Content Slot on shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement content_TopContentSB() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SBSlotsTop", "SB: Top Content");
	}

	/**
	 * Get the Shopping Bag Page Closeness qualifier Slot on shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement content_closenessQualifierSB() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SB_closenessQualifier", "SB: Top Content");
	}

	/**
	 * Get the Shopping Bag Page Bottom Content Slot on shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement content_BottomContentSB() throws Exception, AssertionError{
		return utils.findElementByLocator(driver, "SBSlotsBottom", "SB: Bottom Content");
	}


	/**
	 * Get the Proceed To Checkout button on Top of shopping bag page
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_ProceedToCheckout() throws Exception{
		return utils.findElementByLocator(driver, "SB_ProceedToCheckout", "SB: Proceed To Checkout button on Top");
	}

	/**
	 * Method to get Edit modal element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement section_EditableModal() throws Exception{
		return utils.findElementByLocator(driver, "SBEditItemModal", "| SB : Editable Modal");
	}

	/**
	 * Method to get added items element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement section_AddedItemsInSB() throws Exception{
		return utils.findElementByLocator(driver, "SB_section_AddedItems", "| SB : Added Items");
	}
	/**
	 * Method to get selected quanity in SB
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_selectedQuantity() throws Exception{
		return utils.findElementByLocator(driver, "SB_selectedQuantity", "| SB : Selected quantity");
	}
	/**
	 * Method to get promo error message on the product in SB
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_promoMessage() throws Exception{
		return utils.findElementByLocator(driver, "SB_lbl_promoMessage", "| SB : promo message on the product");
	}
	/**
	 * Method to get promo success message on the product in SB
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_promoSuccessMessage() throws Exception{
		return utils.findElementByLocator(driver, "SB_lbl_promoSuccess", "| SB : promo message on the product");
	}


	/**
	 * Method to get selected quantity element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement optn_SelectedQtyInEditModalSB() throws Exception{
		return utils.findElementByLocator(driver, "SBSelectedQuantity", "| SB : Selected Qty");
	}

	/**
	 * Method to get quantity element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txt_QtySB() throws Exception{
		WebElement element = null;
		if(utils.device.equals("mobile"))		
			element =  utils.findElementByLocator(driver, "MBL_ShoppingBagCount", "| SB : Qty");
		else
			element =  utils.findElementByLocator(driver, "SB_Qty_value", "| SB : Qty");
		return element;
	}
	/**
	 * Method to get quantity label on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_QtySB() throws Exception{
		return utils.findElementByLocator(driver, "SB_lbl_Qty", "| SB : Qty");
	}

	/**
	 * Method to get quantity option list element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_QtyInEditModalSB() throws Exception{
		return utils.findElementsByLocator(driver, "SBQtyListDropdown", "| SB : Qty list dropdown ");
	}

	/**
	 * Method to get Edit element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_EDIT() throws Exception{
		return utils.findElementByLocator(driver, "SB_btn_Edit", "| SB : EDIT button");
	}

	/**
	 * Method to get Edit first item on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 * @narjya
	 */
	public synchronized WebElement btn_FirstEDIT() throws Exception{
		return utils.findElementByLocator(driver, "SB_btn_frstEdit", "| SB : First EDIT button");
	}

	/**
	 * Method to get Remove button element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_REMOVE() throws Exception{
		return utils.findElementByLocator(driver, "SB_btn_Remove", "| SB : REMOVE button");
	}


	/**
	 * Method to return list of button EDIT on shopping bag page
	 * @return List<WebElement>
	 * @throws Exception 
	 */
	public synchronized List<WebElement> list_btn_EDIT() throws Exception{
		return utils.findElementsByLocator(driver,"SB_btn_Edit", "| SB : List of Edit buttons");
	}

	/**
	 * Method to return list of button REMOVE on shopping bag page
	 * @return List<WebElement>
	 * @throws Exception 
	 */
	public synchronized List<WebElement> list_btn_REMOVE() throws Exception{
		return utils.findElementsByLocator(driver,"SB_btn_Remove", "| SB : List of Remove buttons");
	}

	/**
	 * Method to get Cancel button in Edit section on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_CANCEL_edit() throws Exception{
		return utils.findElementByLocator(driver, "SB_btn_cancelEdit", "| SB : CANCEL button - Edit Section");
	}

	/**
	 * Method to get SAVE button in Edit section on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_SAVE_edit() throws Exception{
		return utils.findElementByLocator(driver, "SB_btn_saveEdit", "| SB : SAVE button -Edit Section");
	}

	/**
	 * Get Element Quantity in Edit section on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement dropDown_Qty() throws Exception{
		return utils.findElementByLocator(driver, "SB_edit_drpdwn_qty", "| SB : Dropdown Quantity -Edit Section");
	}


	/**
	 * Method to return list of options in Qty on shopping bag page
	 * @return List<WebElement>
	 * @throws Exception 
	 */
	public synchronized List<WebElement> list_Qty_options() throws Exception{
		return utils.findElementsByLocator(driver,"SBQtySelect", "| SB : List of options in quantity dropdown");
	}

	/**
	 * Method to get the gift box checkbox element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement chkbox_GiftBox() throws Exception{
		return utils.findElementByLocator(driver, "SB_chkbox_giftBox", "| SB : Gift Box Checkbox");
	}


	/**
	 * Method to get the gift box textField 'To' element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txtField_To_GB() throws Exception{
		return utils.findElementByLocator(driver, "SB_txt_giftBox_To", "| SB : Gift Box TextFiled 'To'");
	}

	/**
	 * Method to get the gift box textField 'From' element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txtField_From_GB() throws Exception{
		return utils.findElementByLocator(driver, "SB_txt_giftBox_From", "| SB : Gift Box TextFiled 'From'");
	}

	/**
	 * Method to get the gift box textArea 'Message' element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txtArea_Message_GB() throws Exception{
		return utils.findElementByLocator(driver, "SB-GiftBox-Message", "| SB : Gift Box TextArea 'Message'");
	}


	/**
	 * Method to get the gift box Header element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement header_GiftBoxModal() throws Exception{
		return utils.findElementByLocator(driver, "SBGBHeader", "| SB : Gift Box Header");
	}

	/**
	 * Method to get the gift box modal close link element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_GiftBoxClose() throws Exception{
		return utils.findElementByLocator(driver, "SBGBCloseLink", "| SB : Gift Box Close Link");
	}

	/**
	 * Method to get the gift box modal cancel button element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_GiftBoxCancel() throws Exception{
		return utils.findElementByLocator(driver, "SBGBCancelButton", "| SB : Gift Box Cancel Button");
	}

	/**
	 * Method to get the gift box modal clear all button element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_GiftBoxClearAll() throws Exception{
		return utils.findElementByLocator(driver, "SBGBResetButton", "| SB : Gift Box Clear All Button");
	}

	/**
	 * Method to get the gift box modal submit button element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_GiftBoxSubmit() throws Exception{
		return utils.findElementByLocator(driver, "SBGBSubmitButton", "| SB : Gift Box Submit Button");
	}
	/**
	 * Method to get the Order Total element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_OrderTotal() throws Exception{
		return utils.findElementByLocator(driver, "SBOrderTotal", "| SB : Order Total Label");
	}
	/**
	 * Method to get the gift box checkbox selected element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement chkbox_GiftBoxSelected() throws Exception{
		return utils.findElementByLocator(driver, "SB-GiftBox-Checked", "| SB : Gift Box Selected Checkbox");
	}


	/**
	 * Method to get EDIT gift box element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement link_GiftBoxEDIT() throws Exception{
		return utils.findElementByLocator(driver, "SB-GiftBox-EDIT", "| SB : Gift Box EDIT link");
	}


	/**
	 * Method to get the product price element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_ProductPrice() throws Exception{
		return utils.findElementByLocator(driver, "SB_ProductPrice", "| SB : Product Price");
	}
	/**
	 * Method to get the Size Type of the first item on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_sizeType() throws Exception{
		return utils.findElementByLocator(driver, "SB_lbl_size_type", "| SB : Size Type");
	}
	/**
	 * Method to get the Size Type of the first item on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_selected_sizeType() throws Exception{
		return utils.findElementByLocator(driver, "SB_lnk_selected_size_type", "| SB : Size Type");
	}
	/**
	 * Method to get the selected color of the first item on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_selectedColor() throws Exception{
		return utils.findElementByLocator(driver, "SB_selected_color", "| SB : Size Type");
	}
	/**
	 * Method to get the order total value on shopping bag
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_shoppingBagOrderTotal_Value() throws Exception{
		return utils.findElementByLocator(driver, "SB_OSOrderBalToCC_value", "| SB : Size Type");
	}
	/**
	 * Method to get the selected color of the first item on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_Color() throws Exception{
		return utils.findElementByLocator(driver, "SB_lbl_color", "| SB : Size Type");
	}
	/**
	 * Method to get the price range color product list element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lbl_PriceRangeColorProductList() throws Exception{
		return utils.findElementsByLocator(driver, "SBPriceRangeColorProductList", "| SB : Price Range color Product List");
	}

	/**
	 * Method to get the Product Price List element on Edit Modal on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lbl_ProductPriceListOnEdit() throws Exception{
		return utils.findElementsByLocator(driver, "SBPriceRangeOnEdit", "| SB : Product Price List on Edit Modal");
	}

	/**
	 * Method to get preselected size element on edit modal of shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_SizeOnEditModal() throws Exception{
		return utils.findElementByLocator(driver, "SBEditModalSize", "| SB : Pre-selected size on Edit Modal");
	}

	/**
	 * Get the Size list element from edit SB modal page
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_SelectSizeOnEditModal() throws Exception{
		if(utils.device.equals("desktop"))
			return utils.findElementsByLocator(driver, "SB_EditSizeList", "| Size List");
		else
			return utils.findElementsByLocator(driver, "SB_EditSizeList_Options_Mobile", "size options not found");
	}
	/**
	 * Get the Size list element from edit SB modal page including Select a size
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_SelectSizeValuesAll() throws Exception{
		return utils.findElementsByLocator(driver, "SBEditSizeValuesAll", "| Size List including Select a size");
	}

	/**
	 * Method to get preselected SizeType element on edit modal of shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_SizeTypeOnEditModal() throws Exception{
		return utils.findElementByLocator(driver, "SBEditModalSizeType", "| SB : Pre-selected sizeType on Edit Modal");
	}
	/**
	 * Get the Size Type list element from edit SB modal page
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_SelectSizeTypeOnEditModal() throws Exception{
		if(utils.device.equals("desktop"))
			return utils.findElementsByLocator(driver, "SBEditSizeTypeList", "| Size Type List");
		else
			return utils.findElementsByLocator(driver, "SBEditSizeTypeList_Options_Mobile", "| Size Type List");
	}

	/**
	 * Get the Color Swatch element from edit SB modal page
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_ColorSwatchOnEditModal() throws Exception{
		return utils.findElementsByLocator(driver, "PLP_colorswatch", "| Color Swatch");
	}
	/**
	 * Get the error message element from shopping bag edit page
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement errmsg_size_SBEdit() throws Exception{
		return utils.findElementByLocator(driver, "SB_Edit_errmsgSize", "| size error message");
	}
	/**
	 * Get the error message element from shopping bag edit page
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement errmsg_color_SBEdit() throws Exception{
		return utils.findElementByLocator(driver, "SB_Edit_errmsgColor", "| size error message");
	}

	/**
	 * Get the live chat element from shopping bag page
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement link_LiveChat() throws Exception{
		return utils.findElementByLocator(driver, "SB_LiveChatLink", "| Live Chat Link");
	}

	/**
	 * Get the live chat header element from shopping bag page
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_LiveChatHeader() throws Exception{
		return utils.findElementByLocator(driver, "SBLiveChatHeader", "| Live Chat Header");
	}

	/**
	 * Get the live chat weekday hours element from shopping bag page
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_LiveChatWeekday() throws Exception{
		return utils.findElementByLocator(driver, "SBLiveChatWeekdayHr", "| Live Chat Weekday Hour");
	}

	/**
	 * Get the live chat weekend hours element from shopping bag page
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_LiveChatWeekend() throws Exception{
		return utils.findElementByLocator(driver, "SBLiveChatWeekendHr", "| Live Chat Weekend Hour");
	}

	/**
	 * Method to get the charitable slot item price list element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lnk_CharitableSlotItemPriceList() throws Exception{
		return utils.findElementsByLocator(driver, "SBCharitablePriceList", "| Charitable Price Item List");
	}

	/**
	 * Method to get the Product List on shopping bag page element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement> list_ProductList() throws Exception{
		return utils.findElementsByLocator(driver, "ProductList", "| Product List on shopping bag page");
	}

	/**
	 * Method to get the Promo code section element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_PromoSection() throws Exception{
		return utils.findElementByLocator(driver, "SB_HaveAPromoCode", "| Promo Apply Section");
	}

	/**
	 * Method to get the Promo code text box element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_PromoCode() throws Exception{
		return utils.findElementByLocator(driver, "SB_PromoCodeTxtBox", "| Promo Apply Textbox");
	}


	/**
	 * Method to get the promo apply button element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement btn_PromoApply() throws Exception{
		return utils.findElementByLocator(driver, "SB_PromoApplyButton", "| Promo Apply Button");
	}

	/**
	 * Method to get the promo applied success message element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_PromoAppliedSucessMsg() throws Exception{
		return utils.findElementByLocator(driver, "SB_PromoMessageSucess", "| Promo Applied Success Message");
	}

	/**
	 * Method to get the promo applied error message element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_PromoAppliedErrorMsg() throws Exception{
		return utils.findElementByLocator(driver, "SB_PromoMessageError", "| Promo Applied Error Message");
	}

	/**
	 * Method to get the applied promo code element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_AppliedPromoCode() throws Exception{
		return utils.findElementByLocator(driver, "SR_PromoApplied", "| Applied Promo Code");
	}

	/**
	 * Method to get the remove applied promo code link element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_RemoveAppliedPromoCode() throws Exception{
		return utils.findElementByLocator(driver, "SB_AppliedPromoRemove", "| Remove Applied Promo Code");
	}

	/**
	 * Method to get the applied order discount message element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_OrderDiscountMsg() throws Exception{
		return utils.findElementByLocator(driver, "SBOrderDiscountMessage", "| Applied Order Discount Message");
	}

	/**
	 * Method to get the applied order discount amount element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement txt_OrderDiscountAmt() throws Exception{
		return utils.findElementByLocator(driver, "SBOrderDiscountAmt", "| Applied Order Discount Amount");
	}

	/**
	 * Method to get the applied item discount message(Promo applied) element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_ItemDiscountMsg() throws Exception{
		return utils.findElementByLocator(driver, "SB_ItemDiscountPromoAppliedMsg", "| Item Discount Message");
	}


	/**
	 * Method to get the applied item discount price element from shopping bag
	 * @return webelement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_ItemDiscountPrice() throws Exception{
		return utils.findElementByLocator(driver, "SB_DiscountProductPrice", "| Item Discount Message");
	}
	/**
	 * Method to get number of items in shopping bag from shopping bag icon
	 * @return integer
	 * @throws Exception
	 */
	public synchronized int NumberOfItemsInSB() throws Exception
	{
		utils.scrollUpByxPostion(driver, 1500);
		try{
			if(driver.getTitle().equalsIgnoreCase("Ann Taylor Checkout") && utils.isElementPresent(driver,"ClosePISOverlay","close pick up in store")){
				utils.findElementByLocator(driver, "ClosePISOverlay", "close pick up in store not found").click();
				utils.waitForPageLoaded(driver);
				Thread.sleep(3000);
			}else{
				homepage.click_lnk_shoppingBag();
				Thread.sleep(5000);
			}
		}
		catch(AssertionError a)
		{

		}
		String text = homepage.lnk_shoppingBag().getText();

		// Adding code to wait for some more time to load a page
		/*while (text.contains("-")) {
				Thread.sleep(2000);
			}*/
		int count=Integer.parseInt(text);
		return count;
	}


	/**
	 * Method to get Qty of item in SB at item level
	 * @return Qty
	 * @throws Exception
	 */
	public synchronized int productQTY() throws Exception
	{
		List<WebElement> lst_qty=utils.findElementsByLocator(driver, "SB-ProductQty", "| SB : Quantity of product in shopping bag");
		int lastEle=lst_qty.size()-1;
		WebElement qty=lst_qty.get(lastEle);
		String valueInQty=qty.getText();
		int value=Integer.parseInt(valueInQty);
		return value;
	}

	/**
	 * Method to find price selected in markdown item in SB
	 * @return WebElement
	 * @throws Exception 
	 */
	public synchronized WebElement selectedMarkdownPrice() throws Exception
	{
		return utils.findElementByLocator(driver, "SBselectedMarkdownPrice", "| Selected markdown price in EDIT modal");
	}

		/**
	 * Method to get Merchandise Subtotal element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized double merchandiseSubtotal() throws Exception{
		WebElement ele=utils.findElementByLocator(driver, "SB_OSOrderMerchandise_value", "| SB : Merchandise Subtotal amount in Order Summary");
		String value=ele.getText();
		String[] arrValue = value.split("\\$");
		double textValue=Double.parseDouble(arrValue[1]);
		return textValue;
	}
	/**
	 * Method to get gift card Subtotal element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized double giftCardSubtotal() throws Exception{
		WebElement ele=utils.findElementByLocator(driver, "SB_OSOrderGift_value", "| SB : Gift applied amount in Order Summary");
		String value=ele.getText();
		String[] arrValue = value.split("\\$");
		double textValue=Double.parseDouble(arrValue[1]);
		return textValue;
	}


	/**
	 * Method to get order total element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized double orderTotalValue() throws Exception{
		WebElement ele=utils.findElementByLocator(driver, "SB_OSOrderTotal_value", "| SB : order amount in Order Summary");
		String value=ele.getText();
		String[] arrValue = value.split("\\$");
		double textValue=Double.parseDouble(arrValue[1]);
		return textValue;
	}

	/**
	 * Method to get promo offer price on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized double promoOfferPrice() throws Exception{
		WebElement ele=utils.findElementByLocator(driver, "SB_PromoDiscountPrice", "| SB : order amount in Order Summary");
		String value=ele.getText();
		String[] arrValue = value.split("\\$");
		double textValue=Double.parseDouble(arrValue[1]);
		return textValue;
	}

	/**
	 * Method to get promo saving value on SB 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized double promoSavingPrice() throws Exception{
		WebElement ele=utils.findElementByLocator(driver, "SB_promoSavingValue", "| SB : promo savings in Order Summary");
		String value=ele.getText();
		String[] arrValue = value.split("\\$");
		double textValue=Double.parseDouble(arrValue[1]);
		return textValue;
	}
	/**
	 * Method to get estimate taxes label element on shopping bag page present in order summary page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_estTaxes() throws Exception{
		return utils.findElementByLocator(driver, "SB_OSTaxes_value", "| SB : Estimate Taxes amount in Order Summary");

	}
	
	/**
	 * Method to get estimate taxes value element on shopping bag page present in order summary page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement estTaxesVal() throws Exception{
		return utils.findElementByLocator(driver, "SB_OSTaxes_value", "| SB : Estimate Taxes amount in Order Summary");

	}

	/**
	 * Method to get Estimate shipping label element on shopping bag page present in order summary page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_estShipping() throws Exception{
		return utils.findElementByLocator(driver, "SB_OSShipping_lbl", "| SB : Estimate Shipping label in Order Summary");
	}

	/**
	 * Method to get Estimate shipping value element on shopping bag page present in order summary page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement estShippingVal() throws Exception{
		return utils.findElementByLocator(driver, "SB_OSShipping_value", "| SB : Estimate Shipping amount in Order Summary");
	}


	/**
	 * Method to get text Gift Box in order summary after adding giftbox element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txt_GiftBox() throws Exception{
		return utils.findElementByLocator(driver, "SB-OrderSummary-GiftBox", "| SB : Gift Box text in Order Summary");
	}

	/**
	 * Method to get 4.95 amount added in order summary after adding giftbox element on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement amount_GiftBox() throws Exception{
		return utils.findElementByLocator(driver, "SB-OrderSummary-GBAmount", "| SB : Gift Box amount in Order Summary");
	}

	/**
	 * Method to get order subtotal in shopping bag
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized double orderSubTotal() throws Exception{
		WebElement ele = utils.findElementByLocator(driver, "SB-OrderSummary-OrderTotal", "| SB : Order subtotal amount in Order Summary");
		String value=ele.getText();
		String[] arrValue = value.split("\\$");
		double textValue=Double.parseDouble(arrValue[1]);
		return textValue;
	}



	/**
	 * Created By: Nirmal-19.02.2018 Method to get order credit card balance in
	 * shopping bag
	 * 
	 * @return WebElement
	 * @throws Exception
	 */

	public  synchronized double order_balance_to_credit_card() throws Exception {
		WebElement ele,ele1;
		String value;


		ele = utils.findElementByLocator(driver, "SB-OrderSummary-OrderCreditBalancel",
				"| SB : Order Credit Balance amount in Order Summary");
		value = ele.getText();
		String[] arrValue = value.split("\\$");
		double textValue = Double.parseDouble(arrValue[1]);
		return textValue;
	}

	/**
	 * Created By: Nirmal-19.02.2018 Method to  click on the Paypal Button
	 * shopping bag
	 * 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_ProceedToPaypal() throws Exception, AssertionError{

		return utils.findElementByLocator(driver, "SB_Paypal", "SB: Proceed To Paypal button on Top");
	}
	
	public synchronized void Click_PickUpInStoreRadio() throws Exception {

		WebElement elem = utils.findElementByLocator(driver, "SB_PickUpInStoreRadio", "SB: Pick Up In Store Radio");
		elem.click();
		ReporterLog.log("Clicked Pick Up In Store Radio");
	}
	
	/**
	 * Method to validate Item level promotions
	 * @throws Exception
	 */
	public synchronized boolean applyPromo(WebDriver driver, String promoCode,String promoStatus) throws Exception{
		boolean status=false;
		String PromoMsg = null;
		try{
			ReporterLog.actionMsg("----------------ITEM LEVEL PROMOTION-----------------");
			try{
				utils.scrollDownToElement(driver, txt_PromoCode());
				WebElement elem;
				//					elem= lbl_PromoSection();
				//					String PromoBoxEnabled = elem.getAttribute("class");
				//					if(!PromoBoxEnabled.contains("active")){
				//						utils.clickWebElement(driver, elem, "promo section");
				//						ReporterLog.actionMsg("promo section expanded");
				//					}
			}catch(Exception e){
				ReporterLog.fail("| SHOPPING BAG : Edit Clicked");
			}
			if(Utils.device.equals("mobile") && Utils.browser.equals("ios")) {
				TouchAction a = new TouchAction((IOSDriver)driver);
				int x = (int) homepage.getTheElementLocations(txt_PromoCode(), "x-axis");
				int y = (int) homepage.getTheElementLocations(txt_PromoCode(), "y-axis");
				a.tap(x+100, y+100).perform();
				Thread.sleep(2000);
				String val=txt_PromoCode().getAttribute("value");
				for(int i=0;i<=val.length();i++)
				{
					((IOSDriver<WebElement>)driver).getKeyboard().sendKeys(Keys.DELETE);

				}
				((IOSDriver<WebElement>)driver).getKeyboard().sendKeys(promoCode);
				System.out.println(txt_PromoCode().getAttribute("value"));
				ReporterLog.actionMsg("PROMO CODE entered: " + promoCode);
			}
			else
			{
				txt_PromoCode().clear();
				txt_PromoCode().sendKeys(promoCode);
			}
			//utils.scrollDownToElement(driver, btn_PromoApply());
			utils.clickWebElement(driver, btn_PromoApply(), "promo apply button");
			//btn_PromoApply().click();
			Utils.waitForPageLoaded(driver);
			ReporterLog.actionMsg("promo code applied");
			if(promoStatus.trim().equalsIgnoreCase("Invalid")){
				PromoMsg = lbl_promoMessage().getAttribute("class");
			}
			else{
				PromoMsg = lbl_promoSuccessMessage().getAttribute("class");
			}
			if(PromoMsg.contains("promo-success") && lbl_promoSuccessMessage().getText().equalsIgnoreCase("your promotion code has been applied")){
				status=true;
			}
			else if(PromoMsg.equalsIgnoreCase("error")){
				ReporterLog.fail("Promo Message" + txt_PromoAppliedErrorMsg().getText());

			}


		}catch(Exception e){
			CustomException.throwExceptionError(e, "Checkout", "",driver); 
		}catch(AssertionError e){
			CustomException.throwAssertionError(e, "Checkout", "",driver); 
		}
		return status;
	}

	/**
	 * Method to clear cart
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized void clearShoppingCart() throws Exception{
		homepage.click_lnk_shoppingBag();
		ReporterLog.actionMsg("Clicked Shopping Bag icon");
		if(utils.isElementPresent(driver, "SB_btn_Remove"))
		{
			List<WebElement> removeAll= lst_ShoppingBagItems();
			for(int i=1; i<=removeAll.size(); i++){
				List<WebElement> removeBtn= list_btn_REMOVE();
				removeBtn.get(removeAll.size()- i).click();
				Thread.sleep(1000);
				Utils.waitForPageLoaded(driver);
			}
			ReporterLog.actionMsg("Removed all the products from Shopping cart");
			driver.navigate().refresh();
			Thread.sleep(1000);
			Utils.waitForPageLoaded(driver);
		}
	}

	/**
	 * Method to get text promo code label 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txt_promoCodeLabel() throws Exception{
		return utils.findElementByLocator(driver, "SB_promoCodeLabel", "| SB : promo code label");
	}
	/**
	 * Method to click on promo tool tip 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_promoToolTip() throws Exception{
		return utils.findElementByLocator(driver, "SB_promoToolTip", "| SB : promo tool tip");
	}
	/**
	 * Method to get promo tool tip msg 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txt_promoToolTipMsg() throws Exception{
		return utils.findElementByLocator(driver, "SB_promoToolTipMsg", "| SB : promo tool tip msg");
	}
	/**
	 * Method to click promo code expand button
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_expandPromoSection() throws Exception{
		return utils.findElementByLocator(driver, "SB_promoCodeSection", "| SB : promo code expand section");
	}
	/**
	 * Method to click marketing banner
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lnk_marketingPromo() throws Exception{
		return utils.findElementByLocator(driver, "SB_promoMarketingBannerLNK", "| SB : promo marketing banner");
	}
	/**
	 * Edit | Quantity options
	 * @return Webelement
	 * @throws Exception
	 */
	public synchronized List<WebElement>  lst_editQuantityOption() throws Exception, AssertionError{
		List<WebElement> element = null;			
		element =  utils.findElementsByLocator(driver, "SB_quantityOption", "SB: qauntity form not present");
		return element;
	}

	/*public void editQuantity(int index) throws AssertionError, Exception{
			WebElement element = lst_editQuantityOption();
			Select select = new Select(element);
			select.selectByIndex(index);
		}*/
	/**
	 * Method to get promo msgs 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized List<WebElement> lst_itemPromos() throws Exception{
		return utils.findElementsByLocator(driver, "SP_ImplicitPromoMsg", "| SB : promo marketing banner");
	}
	/**
	 * Method to click marketing banner
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_editQuantity() throws Exception{
		WebElement element=null;
		if(utils.device.equals("mobile")){
			element =  utils.findElementByLocator(driver, "SB_MB_quantityOption", "SB: qauntity form not present");
		}
		else{
			element =  utils.findElementByLocator(driver, "SB_editQuantity", "| SB : edit button quantity not present");
		}
		return element;	
	}

	/********** shopping bag pop up page objects and Methods **********/
	/**
	 * Method to get sub header of guest checkout
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txt_guestCheckoutSubHeader() throws Exception{
		return utils.findElementByLocator(driver, "SB_guestCheckout_Flyout_subHeader", "| SB : sub header not present");
	}
	/**
	 * Method to get sub header of guest checkout
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txt_registeredCheckoutHeader() throws Exception{
		return utils.findElementByLocator(driver, "SB_RegisteredCheckoutHeader", "| SB : registered checkout header not present");
	}
	/**
	 * Method to get error msg of invalid credentials 
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txt_registeredCheckoutInvalidCredentialsErrorMsg() throws Exception{
		return utils.findElementByLocator(driver, "SB_RegisteredCheckout_errorMsgHeader", "| SB : registered checkout no error not present");
	}
	/**
	 * Method to get error msg of invalid pwd
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txt_registeredCheckoutInvalidPwdErrorMsg() throws Exception{
		return utils.findElementByLocator(driver, "SB_RegisteredCheckout_pwd_errorMsg", "| SB : registered checkout invalid pwd error not found");
	}
	/**
	 * Method to get label of merchandise sub total
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement lbl_MerchandiseSubtotal() throws Exception{
		return utils.findElementByLocator(driver, "SB_OSOrderMerchandise_lbl", "| SB : registered checkout invalid pwd error not found");
	}
	/**
	 * Method to get label of merchandise sub total
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement txt_taxTooltip() throws Exception{
		return utils.findElementByLocator(driver, "SB_OrderSummary_EstTax_TooltipInfotext", "| SB : tool tip text not shown");
	}

	/**
	 * Method to get tool tip
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement taxTooltip() throws Exception{
		return utils.findElementByLocator(driver, "SB_OrderSummary_EstTax_TooltipInfo", "| SB : tool tip text not shown");
	}
	/**
	 * Method to get SR button on shopping bag page
	 * @return WebElement
	 * @throws Exception
	 */
	public synchronized WebElement btn_SR() throws Exception{
		return utils.findElementByLocator(driver, "SB_SR_btn", "| SB : ShopRunner button");
	}
	
	public synchronized WebElement btn_MP() throws Exception{
		return utils.findElementByLocator(driver, "SB_MP_btn", "| SB : MasterPass button");
	}
	
	/**
	 * Method to return SR Modal Heading
	 */
	
}
