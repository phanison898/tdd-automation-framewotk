package com.phani.aut.listeners;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestListener implements ITestListener{
	
	private static ExtentReports extent;
	private static String reportName = System.getProperty("user.dir")+"/TestReports/Report/index.html";
	
	private static Map<String, ExtentTest> parentMap = new HashMap<>();
	private static Map<String, ExtentTest> childMap = new HashMap<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String cls = result.getInstance().getClass().getSimpleName();
		String mtd = result.getMethod().getMethodName();
		
		
		if(!(parentMap.get(cls)==null)) {
			ExtentTest test = parentMap.get(cls).createNode(mtd);
			childMap.put(mtd, test);
		}else {
			ExtentTest Ptest = extent.createTest(cls);
			parentMap.put(cls, Ptest);
			ExtentTest Ctest = parentMap.get(cls).createNode(mtd);
			childMap.put(mtd, Ctest);
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String mtd = result.getMethod().getMethodName();
		childMap.get(mtd).log(Status.PASS, "Test case passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String mtd = result.getMethod().getMethodName();
		childMap.get(mtd).log(Status.FAIL, "Test case failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String mtd = result.getMethod().getMethodName();
		childMap.get(mtd).log(Status.SKIP, "Test case skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		extent = new ExtentReports();
		ExtentHtmlReporter html = new ExtentHtmlReporter(reportName);
		extent.attachReporter(html);
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
