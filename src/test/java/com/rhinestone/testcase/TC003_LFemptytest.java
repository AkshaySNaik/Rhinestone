package com.rhinestone.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.rhinestone.pageobject.Loginpage;

public class TC003_LFemptytest extends Baseclass {

	@Test
	public void loginWithEmptyCredentials() {

		log.info("***** TC003_LFemptytest Started *****");

		Loginpage lgpg = new Loginpage(driver);
		Assert.assertEquals(lgpg.getPageTitle(), config.getPageTitle());
		log.info("User Navigated To Loginpage Title");

		lgpg.clickOnLoginButton();
		log.info("User Entered Empty Credentials And Clicked On Login Button");

		Assert.assertEquals(lgpg.getCurrentUrl(), "https://www.saucedemo.com/");
		Assert.assertTrue(lgpg.isDisplayedErrorMessage());
		Assert.assertEquals(lgpg.getTextErrorMessage(), "Epic sadface: Username is required");
		log.info("User Still In Login Page Due To Empty Credentials");

		log.info("***** TC003_LFemptytest Completed *****");

	}
}
