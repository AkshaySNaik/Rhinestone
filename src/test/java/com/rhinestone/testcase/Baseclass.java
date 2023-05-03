package com.rhinestone.testcase;

import java.io.File;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.rhinestone.utilities.MyXlsReader;
import com.rhinestone.utilities.Readconfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {

	public Readconfig config = new Readconfig();
	String browser = config.getBrowser();
	String URL = config.getURL();

	public static WebDriver driver;
	public static MyXlsReader reader;
	public static Logger log = LogManager.getLogger(Baseclass.class.getName());

	//Before Class Method
	@BeforeClass(groups = "smoke")
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

		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get(URL);
	}

	//After Class Method
	@AfterClass(groups = "smoke")
	public void tearDown() {

		driver.quit();
	}

	//Screenshot Capture Method
	public void captureScreenshot(WebDriver driver, String name) {

		File scrs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String filepath = System.getProperty("user.dir") + "//screenshot//" + name + ".png";
			FileUtils.copyFile(scrs, new File(filepath));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	//Random String Values
	public String getRandomStringValues(int num) {

		return (RandomStringUtils.randomAlphabetic(num));
	}

	//Random Integer Values
	public int getRandomIntValues(int num) {

		Random random = new Random();
		return (random.nextInt(num));
	}

	//Random String Numeric Values
	public String getRandomStringNumericValues(int num) {

		return (RandomStringUtils.randomNumeric(num));
	}
	
	//Set Browser By ExcelSheet Method
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

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		driver.get(URL);
	}
}
