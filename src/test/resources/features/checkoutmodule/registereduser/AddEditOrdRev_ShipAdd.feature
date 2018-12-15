@FunctionalTest
Feature: Shipping Add (Add/Edit) As Registered User
		 This test validates Shipping Add(Add/Edit) from Order Reivew Page As Registered User.   

@NFPBrands
Scenario Outline: Add/Edit Shipping Address from Order Review Page as Reg User
	Given User is on Brand Home Page "<siteurl>"
	When  User clicks on Super & Sub Category from Mega Menu, She lands on the PLP Page
	And   User choose to add product from Quickshop Modal
	And   User choose to log into site as Registered User
	Then  Verify User shall land on Order Review Page
	And   User choose to Add New Shipping Add clicking on Add New Button
	Then  Verify New Shipping Address reflects on Order Review Page
	And   User choose to Edit Shipping Add clicking on Edit Button
	Then  Verify Updated Shipping Add on Order Review Page
Examples:
 	|siteurl|
 	|https://factory.anntaylor.com|
 	
@FPBrands
Scenario Outline: Add/Edit Shipping Address from Order Review Page as Reg User
	Given User is on Brand Home Page "<siteurl>"
	When  User clicks on Super & Sub Category from Mega Menu, She lands on the PLP Page
	And   User choose to add product from Quickshop Modal
	And   User choose to log into site as Registered User
	Then  Verify User shall land on Order Review Page
	And   User choose to Add New Shipping Add clicking on Add New Button
	Then  Verify New Shipping Address reflects on Order Review Page
	And   User choose to Edit Shipping Add clicking on Edit Button
	Then  Verify Updated Shipping Add on Order Review Page
Examples:
 	|siteurl|
 	|https://www.anntaylor.com|
 	|https://www.loft.com|