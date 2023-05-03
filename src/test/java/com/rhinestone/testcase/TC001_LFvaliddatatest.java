package com.rhinestone.testcase;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rhinestone.pageobject.Inventorypage;
import com.rhinestone.pageobject.Loginpage;
import com.rhinestone.utilities.MyXlsReader;
import com.rhinestone.utilities.ReadXlsxFile;



public class TC001_LFvaliddatatest extends Baseclass {

	@Test(dataProvider = "datasuppy")
	public void loginWithValidCredentials(HashMap<String, String> hMap) {

		log.info("***** TC001_LFvaliddatatest Started *****");

		if (!ReadXlsxFile.isRunnable(reader, "Login", "Testcases") || hMap.get("Runmode").equals("N")) {

			throw new RuntimeException("Test Case Skipped Due To Run Mode Set to N");
		}

		Loginpage lgpg = new Loginpage(driver);
		Assert.assertEquals(lgpg.getPageTitle(), config.getPageTitle());
		log.info("User Navigated To Loginpage Title");

		lgpg.enterUserNameField(hMap.get("Username"));
		lgpg.enterUserPasswordField(hMap.get("Password"));
		Inventorypage invntpg = lgpg.clickOnLoginButton();
		log.info("User Entered Valid Credentials And Clicked On Login Button");

		Assert.assertTrue(invntpg.getCurrentUrl().contains("/inventory.html"));
		Assert.assertEquals(invntpg.getTextAppLogo(), "Swag Labs");
		Assert.assertTrue(invntpg.isDisplayedShopcartLink());
		Assert.assertTrue(invntpg.isDisplayedProductName());
		Assert.assertTrue(invntpg.isDisplayedInventory());
		log.info("User Successfully Navigated To Inventorys Page");

		log.info("***** TC001_LFvaliddatatest Completed *****");

	}

	@DataProvider(name = "datasuppy")
	public Object[][] dataSupplier() {

		Object[][] data = null;

		try {
			String filepath = System.getProperty("user.dir") + "//testdata//testdatafile.xlsx";
			reader = new MyXlsReader(filepath);
			data = ReadXlsxFile.getTestData(reader, "validcredentials", "Logindata");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return data;
	}
}
