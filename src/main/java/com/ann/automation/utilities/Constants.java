package com.ann.automation.utilities;


//import com.ann.automation.tests.desktop.stepDefinitions.AddEditOrdRevShipAdd;
import com.ann.automation.utilities.Utils.CustomException;
import com.ann.automation.utilities.XLUtils;

public final class Constants{

	//Test Data Sheet Columns
	
		public static final int COL_SRNO = 0;	
		public static final int COL_STYLENO = 1;
		public static final int COL_USER = 2;
		public static final int COL_USEREMAIL = 3;
		public static final int Col_Password = 3;
		public static final int COL_FIRSTNAME = 5;
		public static final int COL_LASTNAME = 6;
		public static final int COL_ADDRESS1 = 7;
		public static final int COL_POBOX = 8;
		public static final int COL_ADDRESS2 = 9;
		public static final int COL_CITY = 10;
		public static final int COL_STATE = 11;
		public static final int COL_ZIPCODE = 12;
		public static final int COL_PHONENO = 13;
		public static final int COL_SHIPPINGMETHOD = 14;
		public static final int COL_PAYMENTMETHOD = 15;
		public static final int COL_CARDTYPE = 16;
		public static final String THROTTLE = "ord";
		//Credit Card Sheet Colums no
		public static final int COL_CC_CARDTYPE = 0;
		public static final int COL_CC_CARDNO = 1;
		public static final int COL_CC_EXPMONTH = 3;
		public static final int COL_CC_EXPYEAR = 4;
		public static final int COL_CC_CVV = 4;
		
		//Environment Test Data Sheet Columns
		public static final int COL_PREURL = 2;
		public static final int COL_PREINHOUSEURL = 1;
		public static final int COL_URL = 3;
		public static final int COL_INHOUSEURL = 2;
		public static final int COL_DEVICE = 4;
		public static final String SHEETNAME_ASSOCIATEID="AssociateID";
		public static final String ENVIRONMENT = "Factory";
		public static final String SHEETNAME_AT_TESTDATA = "AT_TestData";
		public static final String SHEETNAME_AD="AssociateDiscount";
		public static final String SHEETNAME_MYACCOUNT = "MyAccount";
		public static final String SHEETNAME_ADS = "ADS";
		public static final String SHEETNAME_ADDRESS_VALIDATION = "AddressValidation";
		public static final String SHEETNAME_PROMOTION = "Promotion";
		public static final String SHEETNAME_ADDRESS = "Address";
		public static final String SHEETNAME = null;
		public static final String SHEETNAME_SEARCH = "Search";
		public static final String SHEETNAME_BAZAARVOICE = "BazaarVoice";
		public static final String SHEETNAME_PLP="PLP";
		public static final String FINDINSTORE = "FindInStore";
		public static final String SHEETNAME_CREDITCARD = "CreditCard";
		public static final String SHEETNAME_GC = "GC";
		public static final String SHEETNAME_EGC = "EGC";
		public static final String SHEETNAME_ENVIRONMENT = "Environment";
		public static final String SHEETNAME_ORDERSTATUS = "OrderStatus";
		public static final String SHEETNAME_EMAIL = "Email";
		public static final String DELIMITER = "|<br>";
		public static final String PASS = "<font color='green'><b>PASS</b></font>";
		public static final String FAIL = "<font color='red'><b>FAIL</b></font>";
		public static final String WARN = "<font color='yellow'><b>WARN</b></font>";
		public static final String SHEETNAME_ORDERDETAILS = "OrderDetails";
		public static final String GUEST_EMAILID = "accept_anntaylor.automation@gmail.com";
		public static final String DEVICE_ANDROID = "Android";
		public static final String DEVICE_IOS = "iOS";
		public static final String DEVICE_TYPE_MOBILE = "mobile";
		public static XLUtils xlUtils = new XLUtils();
		public static CustomException customException = new CustomException();
		//public static CustomException customExceptionD = new CustomException();
//		public static CustomException customExceptionM = new CustomException();
		public static final int COL_BROWSER = 3;
		
//		public static final String DEVICE = "mobile";
		
	}
