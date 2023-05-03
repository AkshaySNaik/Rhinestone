package com.rhinestone.zdatadriven;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.rhinestone.utilities.MyXlsReader;
import com.rhinestone.utilities.Readconfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclassdatadriven {

	public Readconfig config = new Readconfig();
	String browser = config.getBrowser();
	String URL = config.getURL();

	public static WebDriver driver;
	public static MyXlsReader reader;
	public static Logger log = LogManager.getLogger(Baseclassdatadriven.class.getName());

	@BeforeMethod(enabled = false)
	public void setUp() {

		switch (browser.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			driver = null;
			break;

		}

		// window Maximized
		driver.manage().window().maximize();

		// Implicit Wait For 10 Second
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// PageLoad Timeout For 10 Second
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		// Navigate To WebPage URL
		driver.get(URL);
	}

	@AfterMethod
	public void tearDown() {

		// Drivers Closed
		driver.quit();

	}

	// Capture The Failed Testcases Screenshot
	public void captureScreenshot(WebDriver driver, String testname) {

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String scrnpath = System.getProperty("user.dir") + "//screenshots//" + testname + ".png";
			FileUtils.copyFile(screenshot, new File(scrnpath));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get Random String Data
	public String getRandomStringValue(int number) {

		return (RandomStringUtils.randomAlphabetic(number));
	}
	
	// Get Random String Numeric Data
	public String getRandomStringNumericValue(int number) {

		return (RandomStringUtils.randomNumeric(number));
	}

	// Get Random Integer Value
	public int getRandomIntValue(int number) {

		Random random = new Random();
		return (random.nextInt(number));
	}

	// Set The Browser By Excel
	public void setBrowser(String browser) {

		switch (browser.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			driver = null;
			break;

		}

		// window Maximized
		driver.manage().window().maximize();

		// Implicit Wait For 10 Second
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// PageLoad Timeout For 10 Second
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		// Navigate To WebPage URL
		driver.get(URL);
	}
}
