package com.phani.aut.base;

import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Testing {
	@Test
	public void test1() {
		WebFactory.initBrowser(BrowserType.CHROME);
		Assert.assertTrue(true);
	}
	
	@Test
	public void test2() {
		System.out.println("Test-2 case started");
		Assert.assertTrue(true);
		System.out.println("Test-2 case started");
	}
	
	@Test
	public void test3() {
		System.out.println("Test-3 case started");
		Assert.assertTrue(true);
		System.out.println("Test-3 case started");
	}
	
	@Test
	public void test4() {
		System.out.println("Test-4 case started");
		Assert.assertTrue(true);
		System.out.println("Test-4 case started");
	}
	
	@Test
	public void test5() {
		System.out.println("Test-5 case started");
		Assert.assertTrue(true);
		System.out.println("Test-5 case started");
	}
	
}
