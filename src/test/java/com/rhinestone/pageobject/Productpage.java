package com.rhinestone.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Productpage {

	WebDriver ldriver;

	public Productpage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//div[@class='inventory_details_name large_size']")
	private WebElement prdttext;

	@FindBy(xpath = "//div[@class='inventory_details_desc_container']//button")
	private WebElement cartbutton;

	//////////////////////////////////////////////////////////////////////////////////////

	public String getTextOfProduct() {

		return (prdttext.getText());
	}

	public void clickOnAddToCartButton() {

		cartbutton.click();
	}

	public boolean isDisplayedAddToCartButton() {

		return (cartbutton.isDisplayed());
	}

	public String getPageTitle() {

		return (ldriver.getTitle());
	}

	public String getCurrentUrl() {

		return (ldriver.getCurrentUrl());
	}

}
