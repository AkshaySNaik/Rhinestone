package com.rhinestone.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.rhinestone.utilities.ElementUtils;

public class Inventorypage {

	WebDriver ldriver;

	public Inventorypage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//div[@class='app_logo']")
	private WebElement applogo;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement shopcartlink;

	@FindBy(xpath = "//div[@class='inventory_list']/div[1]")
	private WebElement inventorylist;

	@FindBy(xpath = "//span[@class='title']")
	private WebElement prdttitle;

	@FindBy(xpath = "//div[@class='inventory_list']//div[@class='inventory_item_name']")
	private List<WebElement> products;

	@FindBy(xpath = "//select[@class='product_sort_container']")
	private WebElement namebox;

	@FindBy(xpath = "//div[@class='inventory_list']//div[@class='inventory_item_name'][1]")
	private WebElement product;
	
	@FindBy(xpath = "//div[@class='inventory_list']//button")
	private List<WebElement> addcartbtton;
	
	
	///////////////////////////////////////////////////

	
	public boolean isDisplayedDynamicAddToCartButton(String name) {

		return (ElementUtils.isDisplayedDynamicElement(addcartbtton, name));
	}

	public boolean getTextOfProduct(String name) {

		boolean result;
		String prdt = ElementUtils.getText(product, ldriver);
		if (prdt.equalsIgnoreCase(name)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public void selectDropDownBox(String name) {

		Select select = new Select(namebox);
		select.selectByVisibleText(name);
	}

	public boolean isDisplayedDynamicProduct(String name) {

		return (ElementUtils.isDisplayedDynamicElement(products, name));
	}

	public Productpage clickOnDynamicProduct(String name) {

		ElementUtils.clickOnDynamicElement(products, name);
		return (new Productpage(ldriver));
	}

	public String getTextProductName() {

		return (prdttitle.getText());
	}

	public boolean isDisplayedProductName() {

		return (prdttitle.isDisplayed());
	}

	public boolean isDisplayedInventory() {

		return (inventorylist.isDisplayed());
	}

	public boolean isDisplayedShopcartLink() {

		return (shopcartlink.isDisplayed());
	}
	
	public Cartpage clickOnShopcartLink() {

		shopcartlink.click();
		return (new Cartpage(ldriver));
	}

	public boolean isDisplayedAppLogo() {

		return (applogo.isDisplayed());
	}

	public String getTextAppLogo() {

		return (applogo.getText());
	}

	public String getPageTitle() {

		return (ldriver.getTitle());
	}

	public String getCurrentUrl() {

		return (ldriver.getCurrentUrl());
	}

	public void refreshPage() {

		ldriver.navigate().refresh();
	}

	public void naviageForward() {

		ldriver.navigate().forward();
	}

	public void navigateBackward() {

		ldriver.navigate().back();
	}

}
