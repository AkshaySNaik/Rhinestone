package com.rhinestone.zdatadriven;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rhinestone.pageobject.Inventorypage;
import com.rhinestone.pageobject.Loginpage;
import com.rhinestone.utilities.MyXlsReader;
import com.rhinestone.utilities.ReadXlsxFile;

public class TC001_LFDDtest extends Baseclassdatadriven {

	@Test(dataProvider = "datasuppy")
	public void loginFuctionDataDriven(HashMap<String, String> hMap) {

		log.info("***** TC001_LFDDtest Started *****");

		if (!ReadXlsxFile.isRunnable(reader, "LoginTest", "Testcases") || hMap.get("Runmode").equals("N")) {

			throw new SkipException("Test Skipped Due To Runmode Set As N");
		}

		setBrowser(hMap.get("Browser"));
		
		Loginpage lgpg = new Loginpage(driver);
		Assert.assertEquals(lgpg.getPageTitle(), config.getPageTitle());
		log.info("User Navigated To Loginpage Title");

		lgpg.enterUserNameField(hMap.get("Username"));
		lgpg.enterUserPasswordField(hMap.get("Password"));
		Inventorypage invntpg = lgpg.clickOnLoginButton();
		log.info("User Entered Valid Credentials And Clicked On Login Button");

		boolean expresult = false;
		String expres = hMap.get("ExpectedResult");

		if (expres.equalsIgnoreCase("Success")) {
			expresult = true;
		} else if (expres.equalsIgnoreCase("Failure")) {
			expresult = false;
		}

		boolean acturesult;

		try {
			Assert.assertTrue(invntpg.getCurrentUrl().contains("/inventory.html"));
			Assert.assertEquals(invntpg.getTextAppLogo(), "Swag Labs");
			Assert.assertTrue(invntpg.isDisplayedShopcartLink());
			Assert.assertTrue(invntpg.isDisplayedProductName());
			Assert.assertTrue(invntpg.isDisplayedInventory());
			acturesult = invntpg.isDisplayedShopcartLink();
			log.info("User Successfully Navigated To Inventorys Page");

		} catch (Throwable e) {

			Assert.assertEquals(lgpg.getCurrentUrl(), "https://www.saucedemo.com/");
			Assert.assertTrue(lgpg.isDisplayedErrorMessage());
			acturesult = false;
			log.info("User Not Navigated To Inventorys Page Due To Invalid Credentials");
		}

		Assert.assertEquals(acturesult, expresult);

		log.info("***** TC001_LFDDtest Completed *****");

	}

	@DataProvider(name = "datasuppy")
	public Object[][] dataSupplier() {

		Object[][] data = null;

		try {
			String filepath = System.getProperty("user.dir") + "//testdata//datadrivenfile.xlsx";
			reader = new MyXlsReader(filepath);
			data = ReadXlsxFile.getTestData(reader, "logindata", "Data");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return data;
	}
}
