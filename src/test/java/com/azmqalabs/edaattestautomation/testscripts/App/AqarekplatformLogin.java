package com.azmqalabs.edaattestautomation.testscripts.App;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.azmqalabs.edaattestautomation.apppages.collectionofpages.Pages;
import com.azmqalabs.edaattestautomation.common.DBUtil;
import com.azmqalabs.edaattestautomation.common.ExtentManager;
import com.azmqalabs.edaattestautomation.common.InitializeDriver;
import com.azmqalabs.edaattestautomation.common.Log;
import com.azmqalabs.edaattestautomation.common.ReadData;
import com.azmqalabs.edaattestautomation.common.webDriverEventListener;
import com.azmqalabs.edaattestautomation.objectrepository.EdaatOR;
import com.codoid.products.fillo.Recordset;
import com.github.dockerjava.api.model.Capability;
import com.mysql.cj.x.protobuf.MysqlxConnection.Capabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

// TEST LISTENER
@Listeners(com.azmqalabs.edaattestautomation.common.TestListener.class)
public class AqarekplatformLogin {
	
	
	// DECLARATION SECTION
	  static String TestDataTab="AqarekPlatformLogin";  
	  //public WebDriver driver;
	  public AndroidDriver driver;
	  private InitializeDriver initializeDriver;
	  private EventFiringWebDriver driverEvent;
	  private webDriverEventListener eventListener;
	  
	  public AppiumServiceBuilder builder;
	  public AppiumDriverLocalService service;
	
	  Map<Object, Object> testdatamap;
	  Map<Object, Object> testresultsmap;
	  private String TestScriptID="";
	  static Recordset recTestData;
	  static ArrayList<String> arrListTestDataColumnNames;
	  String sBrowserTestData="";
	  String browserFromXML="";
	  ExtentReports extent;
	  ExtentTest test;
	  Pages Pages;
	  Log Log;
	  DBUtil DBUtil;
	  
		// FACTORY - DATA PROVIDER
	    @Factory (dataProvider="TestDataProvider")
	    public AqarekplatformLogin(Map<Object, Object> testdatamap){
	    	this.testdatamap=testdatamap;
	        this.TestScriptID=testdatamap.get("TestScriptID").toString();
	        this.sBrowserTestData=testdatamap.get("Browser").toString();
	 	    }
	    @Override
	    public String toString(){
	    	return String.format("%S", TestScriptID);
	    }
	    
	    
		// DATA PROVIDER - FOR TEST DATA
	    @DataProvider
	    public static Object[][] TestDataProvider() throws Exception {
	    	Map<String, String> TestDataColNames;
	      	ReadData.retrieveLoginEnvDetails();
	      	arrListTestDataColumnNames=ReadData.readDataTableColumns(TestDataTab);
		  	TestDataColNames=ReadData.mapTestDataTableColumns(arrListTestDataColumnNames);
	      	recTestData=ReadData.readTestData(TestDataTab);
	        int lastRowOfTestData=recTestData.getCount();
	        Object[][] testdataobj = new Object[lastRowOfTestData][1];
	        testdataobj=ReadData.mapTestData(TestDataColNames,recTestData);
	      	return testdataobj;
	    }

		// TEST METHOD
//	    @Parameters({ "username", "password" })
	    @Test
	    public void myTest() throws InterruptedException, Exception{
	
	    	
        	Pages.AqarekPlatformPage.launchApplication(testdatamap,Log);   
        	Pages.AqarekPlatformPage.MainPage(testdatamap,Log);
        	Pages.AqarekPlatformPage.Login(testdatamap,Log);
        	Pages.AqarekPlatformPage.Logout(testdatamap,Log);

        }
	    
	    
		// BEFORE CLASS METHOD - BROWSER INITIALIZATImON
		   @BeforeClass
		    public void beforeClass() throws Exception {

		        browserFromXML=sBrowserTestData;
		        // EXTENT REPORT
		        extent=ExtentManager.CreateExtentReportExtent();
		    	String className=this.getClass().getSimpleName();
		  	  	test=ExtentManager.CreateExtentReportTest(extent,className,testdatamap.get("TestDescription").toString(), browserFromXML,TestScriptID);
		  	  	// INITIALIZE DRIVER
		  	  	initializeDriver=new InitializeDriver();
//		  		driver=initializeDriver.initDriver(browserFromXML, testdatamap);
		  	    driver=(AndroidDriver)initializeDriver.initDriver(browserFromXML, testdatamap);
//		  		driverEvent = new EventFiringWebDriver(driver);
//				eventListener = new webDriverEventListener(test);
//				driverEvent.register(eventListener);
//				this.driver=driverEvent;
				// INITIALIZE PAGE FACTORY OBJECT
				Pages=new Pages(driver,test);
			    Log=new Log(driver,test);
				DBUtil=new DBUtil(driver,test);
				// LOG TEST MACHINE/USER DETAILS
//				Log.QAMachineInfo();
		    }
		// AFTER CLASS
	    @AfterClass
	    public void afterClass() {
			

	        extent.flush();

	    }
	    
		// AFTER TEST - NEED TO USE FOR LOGIN/LOGOUT HANDLING PURPOSE
	    @AfterTest
	    public void afterTest() {
	        System.out.println("After test");
	    }
	    
	    	  
	    
}
