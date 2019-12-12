package com.phani.aut.base;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Testing {
	
	
	@Test
	public void test(){
		WebFactory.initBrowser(BrowserType.CHROME);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebFactory.quitBrowser();
	}

}
