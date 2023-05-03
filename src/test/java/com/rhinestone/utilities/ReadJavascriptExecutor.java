package com.rhinestone.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReadJavascriptExecutor {

	// Use Of Gramlin For Monkey Test
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

	// draw border around web element
	public static void drawBorder(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	// To generate alert message
	public static void generateAlert(WebDriver driver, String alertMessage) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("alert('" + alertMessage + "')");

	}

	// Refresh web browser
	public static void refreshBrowserByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	// Flash web element
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

	// Scroll web page down till end
	public static void scrollPgDown(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	// scroll vertically page up
	public static void ScrollVerticallyPgUp(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");

	}

	// Scroll till visibility of web element
	public static void scrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// get domain name
	public static String getDomainByJavaScript(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String domain = js.executeScript("return document.domain;").toString();
		return domain;
	}

	// Get Title of the web page
	public static String getTitleByJavaScript(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	// get URL of the web page
	public static String getURLByJavaScript(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String url = js.executeScript("return document.URL;").toString();
		return url;
	}

	// click web element
	public static void clickElementByJavaScript(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);

	}
	
	// type web element
	public static void typeElementByJavaScript(WebElement element, WebDriver driver, String textToBeTyped) {
		JavascriptExecutor jse = ((JavascriptExecutor)driver);
		jse.executeScript("arguments[0].value='"+textToBeTyped+"'",element);

	}

}