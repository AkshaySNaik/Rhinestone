package com.rhinestone.testcase;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rhinestone.pageobject.Loginpage;
import com.rhinestone.utilities.MyXlsReader;
import com.rhinestone.utilities.ReadXlsxFile;

public class TC004_LFvalidnametest extends Baseclass {

	@Test(dataProvider = "datasuppy")
	public void loginWithValidUserName(HashMap<String, String> hMap) {

		log.info("***** TC004_LFvalidnametest Started *****");

		if (!ReadXlsxFile.isRunnable(reader, "Login", "Testcases") || hMap.get("Runmode").equals("N")) {

			throw new RuntimeException("Test Case Skipped Due To Run Mode Set to N");
		}

		Loginpage lgpg = new Loginpage(driver);
		Assert.assertEquals(lgpg.getPageTitle(), config.getPageTitle());
		log.info("User Navigated To Loginpage Title");

		lgpg.enterUserNameField(hMap.get("Username"));
		lgpg.enterUserPasswordField(hMap.get("Password"));
		lgpg.clickOnLoginButton();
		log.info("User Entered Valid Credentials And Clicked On Login Button");

		Assert.assertEquals(lgpg.getCurrentUrl(), "https://www.saucedemo.com/");
		Assert.assertTrue(lgpg.isDisplayedErrorMessage());
		Assert.assertEquals(lgpg.getTextErrorMessage(),
				"Epic sadface: Username and password do not match any user in this service");
		log.info("User Still In Login Page Due To Invalid Password");

		log.info("***** TC004_LFvalidnametest Completed *****");

	}

	@DataProvider(name = "datasuppy")
	public Object[][] dataSupplier() {

		Object[][] data = null;

		try {
			String filepath = System.getProperty("user.dir") + "//testdata//testdatafile.xlsx";
			reader = new MyXlsReader(filepath);
			data = ReadXlsxFile.getTestData(reader, "vaidemailinvalidpassword", "Logindata");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return data;
	}
}
