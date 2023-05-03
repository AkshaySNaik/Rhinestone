package com.rhinestone.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

	WebDriver ldriver;

	public Loginpage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id = "user-name")
	private WebElement usernamefield;

	@FindBy(id = "password")
	private WebElement passwrdfield;

	@FindBy(id = "login-button")
	private WebElement loginbttn;

	@FindBy(xpath = "//div[@class='error-message-container error']")
	private WebElement errormsg;

	////////////////////////////////////////////

	public String getTextErrorMessage() {

		return (errormsg.getText());
	}

	public boolean isDisplayedErrorMessage() {

		return (errormsg.isDisplayed());
	}

	public void enterUserNameField(String username) {

		usernamefield.sendKeys(username);
	}

	public void enterUserPasswordField(String password) {

		passwrdfield.sendKeys(password);
	}

	public Inventorypage clickOnLoginButton() {

		loginbttn.click();
		return (new Inventorypage(ldriver));
	}

	public String getPageTitle() {

		return (ldriver.getTitle());
	}

	public String getCurrentUrl() {

		return (ldriver.getCurrentUrl());
	}

}
