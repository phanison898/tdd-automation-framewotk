package com.phani.aut.base;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.Test;

public class Testing {

	@Test
	public void test() throws InterruptedException {
		WebFactory.initBrowser(BrowserType.CHROME);
		Thread.sleep(3000);
		WebFactory.quitBrowser();
	}
	
}
