package com.phani.aut.base;

import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Testing extends WebFactory{
	@Test
	public void test1() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void test2() {
		Assert.assertTrue(false);
	}
	
	@Test
	public void test3() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void test4() {
		Assert.assertTrue(false);
	}
	
	@Test
	public void test5() {
		Assert.assertTrue(true);
	}
}
