package com.rhinestone.testcase;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rhinestone.pageobject.Loginpage;

public class TC002_LFinvalidtest extends Baseclass {

	@Test(dataProvider = "datasuppy")
	public void loginWithInValidCredentials(String sdata) {

		log.info("***** TC002_LFinvalidtest Started *****");

		String[] data = sdata.split(",");

		Loginpage lgpg = new Loginpage(driver);
		Assert.assertEquals(lgpg.getPageTitle(), config.getPageTitle());
		log.info("User Navigated To Loginpage Title");

		lgpg.enterUserNameField(data[0]);
		lgpg.enterUserPasswordField(data[1]);
		lgpg.clickOnLoginButton();
		log.info("User Entered Invalid Credentials And Clicked On Login Button");

		Assert.assertEquals(lgpg.getCurrentUrl(), "https://www.saucedemo.com/");
		Assert.assertTrue(lgpg.isDisplayedErrorMessage());
		Assert.assertEquals(lgpg.getTextErrorMessage(),
				"Epic sadface: Username and password do not match any user in this service");
		log.info("User Still In Login Page Due To Invalid Credentials");

		log.info("***** TC002_LFinvalidtest Completed *****");

	}

	@DataProvider(name = "datasuppy")
	public Object[] dataSupplier() {

		JSONParser parser = new JSONParser();
		Object object = null;

		try {
			String jsonpath = System.getProperty("user.dir") + "//jsonfile//login.json";
			FileReader reader = new FileReader(jsonpath);
			object = parser.parse(reader);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JSONObject jsonobject = (JSONObject) object;
		JSONArray jsonarray = (JSONArray) jsonobject.get("invalidtest");
		Object[] arr = new Object[jsonarray.size()];

		for (int i = 0; i < jsonarray.size(); i++) {

			JSONObject jnobj = (JSONObject) jsonarray.get(i);
			Object uname = jnobj.get("username");
			Object upass = jnobj.get("password");

			arr[i] = uname + "," + upass;
		}
		return arr;
	}
}
