package com.phani.aut.base;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void initBrowser(String browser) {

		switch (browser) {
		case BrowserType.CHROME:
			Chrome();
			break;
		case BrowserType.FIREFOX:
			Firefox();
			break;
		case BrowserType.EDGE:
			Edge();
			break;
		case BrowserType.IE:
			IE();
			break;
		case BrowserType.SAFARI:
			Safari();
			break;
		case BrowserType.OPERA:
			Opera();
			break;
		case BrowserType.HTMLUNIT:
			HtmlUnit();
			break;
		default:
			try {
				throw new Exception("NoBrowserFoundException");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		initdriver();
	}

	public static void initBrowser(String browser, URL url) {
		DesiredCapabilities cap = null;
		switch (browser) {
		case BrowserType.CHROME:
			cap = DesiredCapabilities.chrome();
			break;
		case BrowserType.FIREFOX:
			cap = DesiredCapabilities.firefox();
			break;
		case BrowserType.EDGE:
			cap = DesiredCapabilities.edge();
			break;
		case BrowserType.IE:
			cap = DesiredCapabilities.internetExplorer();
			break;
		case BrowserType.OPERA:
			cap = DesiredCapabilities.opera();
			break;
		case BrowserType.SAFARI:
			cap = DesiredCapabilities.safari();
			break;
		}
		driver.set(new RemoteWebDriver(url, cap));
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitBrowser() {
		getDriver().quit();
	}

	private static void initdriver() {
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(0, TimeUnit.SECONDS);
	}

	private static void Chrome() {
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
	}

	private static void Firefox() {
		WebDriverManager.firefoxdriver().setup();
		driver.set(new FirefoxDriver());
	}

	private static void Edge() {
		WebDriverManager.edgedriver().setup();
		driver.set(new EdgeDriver());
	}

	private static void IE() {
		WebDriverManager.iedriver().setup();
		driver.set(new InternetExplorerDriver());
	}

	private static void Safari() {
		try {
			throw new Exception("NoSafariBrowserFoundException");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void Opera() {
		WebDriverManager.operadriver().setup();
		driver.set(new OperaDriver());
	}

	private static void HtmlUnit() {
	}

}
