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
import com.rhinestone.pageobject.Productpage;

public class TC010_ACprdtaddtest extends Baseclass{

	@Test(dataProvider = "datasuppy")
	public void shoppingCartPage(String sdata) {

		log.info("***** TC010_ACprdtaddtest Started *****");

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
		
		Productpage prdtpg = invntpg.clickOnDynamicProduct(data[2]);
		prdtpg.clickOnAddToCartButton();
		log.info("Product Added To Cart Page");
		
		Cartpage cartpg = invntpg.clickOnShopcartLink();
		Assert.assertTrue(cartpg.getCurrentUrl().contains("/cart.html"));
		Assert.assertTrue(cartpg.isDisplayedDynamicProduct(data[2]));
		Assert.assertTrue(cartpg.isDisplayedCheckOutBttn());
		Assert.assertTrue(cartpg.isDisplayedContinueShoppingBttn());
		log.info("User Product Added To Cart Page");
		
		Assert.assertTrue(cartpg.isDisplayedRemoveBttn());
		cartpg.clickOnRemoveBttn();
		log.info("Product Removed From Cart Page");

		log.info("***** TC010_ACprdtaddtest Completed *****");

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
