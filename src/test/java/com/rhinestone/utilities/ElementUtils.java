package com.rhinestone.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	private static final long durationInSeconds = 30;

	public static WebElement waitForElement(WebElement element, WebDriver ldriver) {

		WebElement webElement = null;

		try {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(durationInSeconds));
			webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return webElement;

	}

	public static Alert waitForAlert(WebDriver ldriver) {

		Alert alert = null;

		try {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(durationInSeconds));
			alert = wait.until(ExpectedConditions.alertIsPresent());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return alert;

	}

	public static WebElement waitForVisibilityOfElement(WebElement element, WebDriver ldriver) {

		WebElement webElement = null;

		try {
			WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(durationInSeconds));
			webElement = wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return webElement;

	}

	public static void click(WebElement element, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		try {
			webelement.click();
		} catch (WebDriverException e) {
			JavascriptExecutor js = ((JavascriptExecutor) ldriver);
			js.executeScript("arguments[0].click();", element);
		}
	}

	public static void sendKeys(WebElement element, String value, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		webelement.click();
		webelement.clear();
		webelement.sendKeys(value);
	}
	
	public static void clickOnDynamicElement(List<WebElement> elements, String text) {

		for (WebElement element : elements) {

			if (element.getText().equals(text)) {
				element.click();
				break;
			}
		}
	}

	public static boolean isDisplayedDynamicElement(List<WebElement> elements, String text) {

		boolean flag = false;

		for (WebElement element : elements) {

			if (element.getText().equals(text)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static String getText(WebElement element, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		return (webelement.getText());
	}

	public static boolean isDisplayed(WebElement element, WebDriver ldriver) {
		try {
			WebElement webelement = waitForVisibilityOfElement(element, ldriver);
			return (webelement.isDisplayed());
		} catch (Throwable e) {
			return false;
		}
	}

	public static boolean isSelected(WebElement element, WebDriver ldriver) {
		try {
			WebElement webelement = waitForVisibilityOfElement(element, ldriver);
			return (webelement.isSelected());
		} catch (Throwable e) {
			return false;
		}
	}

	public static boolean isEnabled(WebElement element, WebDriver ldriver) {
		try {
			WebElement webelement = waitForVisibilityOfElement(element, ldriver);
			return (webelement.isEnabled());
		} catch (Throwable e) {
			return false;
		}
	}
	
	public static void switchWindowByTitle(String windowTitle, int count, WebDriver ldriver) {

		Set<String> windowList = ldriver.getWindowHandles();

		String[] array = windowList.toArray(new String[0]);

		ldriver.switchTo().window(array[count - 1]);
	}

	public static void switchToNewWindow(WebDriver ldriver) {

		Set<String> s = ldriver.getWindowHandles();
		Object popup[] = s.toArray();
		ldriver.switchTo().window(popup[1].toString());
	}
	
	public static void switchToFrameByIndex(int index, WebDriver ldriver) {

		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public static void switchToFrameById(String id_name, WebDriver ldriver) {

		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(id_name));
	}

	public static void switchToFrameByWebElement(WebElement element, WebDriver ldriver) {

		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(durationInSeconds));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public static int getSize(List<WebElement> element) {

		return (element.size());
	}

	public static String getAttribute(WebElement element, String value, WebDriver ldriver) {

		WebElement webelement = waitForVisibilityOfElement(element, ldriver);
		return (webelement.getAttribute(value));
	}

	public static void selectByVisibleText(WebElement element, String dropDownOption, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		Select select = new Select(webelement);
		select.selectByVisibleText(dropDownOption);
	}

	public static void selectByIndex(WebElement element, int index, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		Select select = new Select(webelement);
		select.selectByIndex(index);
	}

	public static void selectByValue(WebElement element, String value, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		Select select = new Select(webelement);
		select.selectByValue(value);
	}

	public static void deselectAll(WebElement element, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		Select select = new Select(webelement);
		select.deselectAll();
	}

	public static void deselectByVisibleText(WebElement element, String dropDownOption, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		Select select = new Select(webelement);
		select.deselectByVisibleText(dropDownOption);
	}

	public static void deselectByIndex(WebElement element, int index, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		Select select = new Select(webelement);
		select.deselectByIndex(index);
	}

	public static void deselectByValue(WebElement element, String value, WebDriver ldriver) {

		WebElement webelement = waitForElement(element, ldriver);
		Select select = new Select(webelement);
		select.deselectByValue(value);
	}

	public static boolean isMultiple(WebElement element, WebDriver ldriver) {

		try {
			WebElement webelement = waitForVisibilityOfElement(element, ldriver);
			Select select = new Select(webelement);
			return (select.isMultiple());
		} catch (Throwable e) {
			return false;
		}
	}

	public static void mouseHoverAndClick(WebElement element, WebDriver ldriver) {

		WebElement webelement = waitForVisibilityOfElement(element, ldriver);
		Actions actions = new Actions(ldriver);
		actions.moveToElement(webelement).click().build().perform();
	}

	public static void actionsClick(WebElement element, WebDriver ldriver) {

		WebElement webelement = waitForVisibilityOfElement(element, ldriver);
		Actions actions = new Actions(ldriver);
		actions.click(webelement).build().perform();
	}

	public static void actionsMouseHover(WebElement element, WebDriver ldriver) {

		WebElement webelement = waitForVisibilityOfElement(element, ldriver);
		Actions actions = new Actions(ldriver);
		actions.moveToElement(webelement).build().perform();
	}

	public static void actionsContextClick(WebElement element, WebDriver ldriver) {

		WebElement webelement = waitForVisibilityOfElement(element, ldriver);
		Actions actions = new Actions(ldriver);
		actions.contextClick(webelement).build().perform();
	}

	public static void actionsDoubleClick(WebElement element, WebDriver ldriver) {

		WebElement webelement = waitForVisibilityOfElement(element, ldriver);
		Actions actions = new Actions(ldriver);
		actions.doubleClick(webelement).build().perform();
	}

	public static void actionsDragAndDrop(WebElement source, WebElement target, WebDriver ldriver) {

		Actions actions = new Actions(ldriver);
		actions.dragAndDrop(source, target).build().perform();
	}

	public static void actionsDragAndDropBy(WebElement element, int x, int y, WebDriver ldriver) {

		Actions actions = new Actions(ldriver);
		actions.dragAndDropBy(element, x, y).build().perform();
	}

	public static void actionsKeyDownAndKeyUp(WebElement element, WebDriver ldriver) {

		WebElement webelement = waitForVisibilityOfElement(element, ldriver);
		Actions actions = new Actions(ldriver);
		actions.keyDown(Keys.CONTROL).click(webelement).keyUp(Keys.CONTROL).build().perform();
	}

	public static void actionsTargetSendKeys(WebElement element, String value, WebDriver ldriver) {

		WebElement webelement = waitForVisibilityOfElement(element, ldriver);
		Actions actions = new Actions(ldriver);
		actions.sendKeys(webelement, value).build().perform();
	}

	public static void alertAccept(WebDriver ldriver) {

		Alert alert = waitForAlert(ldriver);
		alert.accept();

	}

	public static void alertDismiss(WebDriver ldriver) {

		Alert alert = waitForAlert(ldriver);
		alert.dismiss();

	}

	public static String alertGetText(WebDriver ldriver) {

		Alert alert = waitForAlert(ldriver);
		return alert.getText();
	}

	public static void alertSendKeys(String value, WebDriver ldriver) {

		Alert alert = waitForAlert(ldriver);
		alert.sendKeys(value);
	}

	public static void switchToDefaultFrame(WebDriver ldriver) {

		ldriver.switchTo().defaultContent();
	}

	public static void switchToOldWindow(WebDriver ldriver) {

		Set<String> s = ldriver.getWindowHandles();
		Object popup[] = s.toArray();
		ldriver.switchTo().window(popup[0].toString());
	}

	public static int getColumncount(WebElement table) {

		List<WebElement> columns = table.findElements(By.tagName("td"));
		int a = columns.size();
		return a;
	}

	public static int getRowCount(WebElement table) {

		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}

	public static boolean isAlertPresent(WebDriver ldriver) {
		try {
			ldriver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public static String getTitle(WebDriver ldriver) {

		return (ldriver.getTitle());
	}

	public static String getCurrentURL(WebDriver ldriver) {

		return (ldriver.getCurrentUrl());
	}

	public static void fluentWait(WebElement element, int timeOut, WebDriver ldriver) {
		Wait<WebDriver> wait = null;
		try {
			wait = new FluentWait<WebDriver>((WebDriver) ldriver).withTimeout(Duration.ofSeconds(20))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String screenShot(String filename, WebDriver ldriver) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) ldriver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename
				+ "_" + dateName + ".png";
		return newImageString;
	}

	public static void attack(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String gramlinscript = "    javascript: (function() {\r\n" + "    function callback() {\r\n"
				+ "        gremlins.createHorde({\r\n"
				+ "            species: [gremlins.species.clicker(),gremlins.species.toucher(),gremlins.species.formFiller(),gremlins.species.scroller(),gremlins.species.typer()],\r\n"
				+ "            mogwais: [gremlins.mogwais.alert(),gremlins.mogwais.fps(),gremlins.mogwais.gizmo()],\r\n"
				+ "            strategies: [gremlins.strategies.distribution(),gremlins.strategies.allTogether(),gremlins.strategies.bySpecies()]\r\n"
				+ "        }).unleash();\r\n" + "    }\r\n" + "    var s = document.createElement(\"script\");\r\n"
				+ "    s.src = \"https://unpkg.com/gremlins.js\";\r\n" + "    if (s.addEventListener) {\r\n"
				+ "        s.addEventListener(\"load\", callback, false);\r\n" + "    } else if (s.readyState) {\r\n"
				+ "        s.onreadystatechange = callback;\r\n" + "    }\r\n" + "    document.body.appendChild(s);\r\n"
				+ "    })()";
		js.executeAsyncScript(gramlinscript);
	}

	public static void drawBorder(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public static void generateAlert(WebDriver driver, String alertMessage) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("alert('" + alertMessage + "')");

	}

	public static void refreshBrowserByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	public static void flash(WebElement element, WebDriver driver) {
		String backcolor = element.getCssValue("backgroundColor");

		for (int i = 0; i < 300; i++) {
			changeColor("#000000", element, driver);
			changeColor(backcolor, element, driver);
		}
	}

	public static void changeColor(String color, WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public static void scrollPgDown(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public static void ScrollVerticallyPgUp(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");

	}

	public static void scrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static String getDomainByJavaScript(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String domain = js.executeScript("return document.domain;").toString();
		return domain;
	}

	public static String getTitleByJavaScript(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	public static String getURLByJavaScript(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String url = js.executeScript("return document.URL;").toString();
		return url;
	}

	public static void clickElementByJavaScript(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);

	}

	public static void typeElementByJavaScript(WebElement element, WebDriver driver, String textToBeTyped) {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].value='" + textToBeTyped + "'", element);

	}
	
	public static void closeBrowser(WebDriver ldriver) {

		ldriver.close();
	}

	public static void quiteBrowser(WebDriver ldriver) {

		ldriver.quit();
	}
}