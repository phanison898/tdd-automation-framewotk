package com.phani.aut.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
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
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static long PAGE_LOAD_TIME = 60;
	private static long IMPLICIT_WAIT = 5;
	private static String BROWSER= "chrome";
	private static String BROWSER_URL= "http://www.google.co.in";
	private static String GRID_URL= "http://52.66.196.189:4444/wd/hub/";
	
	public static Properties config;
	
	public WebFactory() {
		config = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/phani/aut/config/config.properties");
			config.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@BeforeTest
	public void setup() {
		//initBrowser(BROWSER);
		initBrowser(BROWSER,GRID_URL);
	}
	
	@AfterTest
	public void clean() {
		quitBrowser();
	}
	
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
		default:
			try {
				throw new Exception("NoBrowserFoundException");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		initdriver();
	}

	public static void initBrowser(String browser, String grid_url) {
		DesiredCapabilities cap = null;
		switch (browser) {
		case BrowserType.CHROME:
			cap = new DesiredCapabilities();
			cap.setCapability(CapabilityType.BROWSER_NAME, "chrome");
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
		case BrowserType.HTMLUNIT:
			cap = DesiredCapabilities.htmlUnit();
			break;
		}
		
		URL url = null;
		try {
			url = new URL(grid_url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.set(new RemoteWebDriver(url, cap));
		initdriver();
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
		getDriver().manage().timeouts().implicitlyWait(PAGE_LOAD_TIME, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(IMPLICIT_WAIT, TimeUnit.SECONDS);
		getDriver().get(BROWSER_URL);
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
			driver.set(new SafariDriver());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void Opera() {
		WebDriverManager.operadriver().setup();
		driver.set(new OperaDriver());
	}
}
