package com.rhinestone.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cartpage {

	WebDriver ldriver;

	public Cartpage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//div[@class='inventory_details_name large_size']")
	private WebElement prdttext;

	@FindBy(xpath = "//button[@id='continue-shopping']")
	private WebElement contshopp;

	@FindBy(xpath = "//button[@id='checkout']")
	private WebElement checkbttn;

	@FindBy(xpath = "//div[@class='inventory_item_name']")
	private WebElement prdtcart;
	
	@FindBy(xpath = "//div[@class='cart_item']//button")
	private WebElement removebttn;

	/////////////////////////////////////////////////

	public boolean isDisplayedRemoveBttn() {

		return (removebttn.isDisplayed());
	}
	
	public void clickOnRemoveBttn() {

		removebttn.click();
	}
	
	public boolean isDisplayedDynamicProduct(String name) {

		boolean flag;
		String txt = prdtcart.getText();
		if (txt.equalsIgnoreCase(name)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean isDisplayedCheckOutBttn() {

		return (checkbttn.isDisplayed());
	}

	public Loginpage clickOnCheckoutButton() {

		checkbttn.click();
		return (new Loginpage(ldriver));
	}

	public boolean isDisplayedContinueShoppingBttn() {

		return (contshopp.isDisplayed());
	}

	public Inventorypage clickOnContinueShoppingBttn() {

		contshopp.click();
		return (new Inventorypage(ldriver));
	}

	public String getCurrentUrl() {

		return (ldriver.getCurrentUrl());
	}

}
