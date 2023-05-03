package com.rhinestone.testcase;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rhinestone.pageobject.Cartpage;
import com.rhinestone.pageobject.Inventorypage;
import com.rhinestone.pageobject.Loginpage;

public class TC008_ACaddprdttest extends Baseclass{

	@Test(dataProvider = "datasuppy")
	public void shoppingCartPage(String sdata) {

		log.info("***** TC008_ACaddprdttest Started *****");

		String[] data = sdata.split(",");

		Loginpage lgpg = new Loginpage(driver);
		Assert.assertEquals(lgpg.getPageTitle(), config.getPageTitle());
		log.info("User Navigated To Loginpage Title");

		lgpg.enterUserNameField(data[0]);
		lgpg.enterUserPasswordField(data[1]);
		Inventorypage invntpg = lgpg.clickOnLoginButton();
		log.info("User Entered Invalid Credentials And Clicked On Login Button");

		Assert.assertTrue(invntpg.getCurrentUrl().contains("/inventory.html"));
		Assert.assertEquals(invntpg.getTextAppLogo(), "Swag Labs");
		Assert.assertTrue(invntpg.isDisplayedShopcartLink());
		log.info("Shopping Cart Link Displayed On WebPage");
		
		Cartpage cartpg = invntpg.clickOnShopcartLink();
		Assert.assertTrue(cartpg.getCurrentUrl().contains("/cart.html"));
		Assert.assertTrue(cartpg.isDisplayedCheckOutBttn());
		Assert.assertTrue(cartpg.isDisplayedContinueShoppingBttn());
		log.info("User Navigated To Shopping Cart Page");

		log.info("***** TC008_ACaddprdttest Completed *****");

	}

	@DataProvider(name = "datasuppy")
	public Object[] dataSupplier() {

		JSONParser parser = new JSONParser();
		Object object = null;

		try {
			String jsonpath = System.getProperty("user.dir") + "//jsonfile//inventory.json";
			FileReader reader = new FileReader(jsonpath);
			object = parser.parse(reader);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		JSONObject jsonobject = (JSONObject) object;
		JSONArray jsonarray = (JSONArray) jsonobject.get("producttest");
		Object[] arr = new Object[jsonarray.size()];

		for (int i = 0; i < jsonarray.size(); i++) {

			JSONObject jnobj = (JSONObject) jsonarray.get(i);
			Object uname = jnobj.get("username");
			Object upass = jnobj.get("password");
			Object prodt = jnobj.get("product");

			arr[i] = uname + "," + upass + "," + prodt;
		}
		return arr;
	}
}
