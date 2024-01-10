package com.azmqalabs.edaattestautomation.common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {
	private  AppiumDriverLocalService Service;
	private AppiumServiceBuilder builder;
    private static AppiumDriverLocalService appiumDriverLocalService;
	
	public ExtentTest test;
	public String InfoFontColorPrefix = "<span ><font color='purple';font-size:10%; line-height:20px>";
	public String InfoFontColorSuffix = "</font></span>";
	public String ErrorFontColorPrefix = "<span><font color='red';font-size:16px; line-height:20px>";
	public String ErrorFontColorSuffix = "</font></span>";

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("on test method " + getTestMethodName(result) + " start");
		System.out.println("The name of the testcase Skipped is :" + result.getName());

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("on test method " + getTestMethodName(result) + " success");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("on test method " + result + getTestMethodName(result) + " failure");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("test method " + getTestMethodName(result) + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("test failed but within success % " + getTestMethodName(result));
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("on start of test " + context.getName());
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("on finish of test " + context.getName());
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub

	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub

	}
	public  void appiumServer() {
		builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
		builder.withAppiumJS(new File("C:\\Users\\USER\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"));
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"info");
		Service=AppiumDriverLocalService.buildService(builder);	
		builder.withStartUpTimeOut(180000, TimeUnit.SECONDS);
		Service.start();
		
	}
	public void onStart(ISuite suite) {
		appiumServer();
		System.out.println("Appium Server starts");
	}

	public void onFinish(ISuite suite) {
		Service.stop();
		System.out.println("Appium Server stops");
	}

	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}

	private void printTestResults(ITestResult result) {

		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			Reporter.log("Test Method had the following parameters : " + params, true);
		}
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
		}
		Reporter.log("Test Status: " + status, true);
	}

}
