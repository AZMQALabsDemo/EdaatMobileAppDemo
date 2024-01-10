package com.azmqalabs.edaattestautomation.apppages.admin.pages;

import java.io.IOException;
import java.sql.DriverManager;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Dimension;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.azmqalabs.edaattestautomation.apppages.masterpages.BasePage;
import com.azmqalabs.edaattestautomation.common.Log;
import com.azmqalabs.edaattestautomation.common.uielement.fieldDecorator;
import com.azmqalabs.edaattestautomation.objectrepository.EdaatOR;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;



public class AqarekPlatformPage extends BasePage {

	public AqarekPlatformPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;

		PageFactory.initElements(new fieldDecorator(driver, test), this);
	}

	@FindBy(xpath = EdaatOR.LoginPage_Username)
	public WebElement Username;



	public void launchApplication(Map<Object, Object> testdatamap,Log Log) throws IOException {
		try {
			   this.getScreen();
                Log.ReportEvent("PASS", "Step1 : Verify Aqarek Application Launched Suceessfully");
			} 	catch (Exception e) {
			test.log(Status.FAIL, "* Launch application is Fail * ");
			}
	}
	public void MainPage(Map<Object, Object> testdatamap, Log Log ) throws IOException {
		try {
			Thread.sleep(5000);
		   	WebElement MainMenu = driver.findElement(By.xpath(EdaatOR.Aqarek_MainMenue.toString()));
			VerifyValue1(MainMenu.getText(),testdatamap.get("MainPage").toString());
			Log.ReportEvent("PASS", "Step2 : Verify Main Page is Dislayed");
	   	    this.getScreen();
		}
		catch (Exception e) {
			test.log(Status.FAIL, "* Verify Main Page is FAILED * ");
			this.getScreen();
		}
	}
	
	public void Login(Map<Object,Object> testdatamap, Log Log){
	       try
	     		{
			   AndroidClickUsingActions(EdaatOR.Aqarek_Myrequestmenu.toString());
  			   this.getScreen();
  			   AndroidEdit(EdaatOR.Aqarek_MobileNotextfield.toString(), testdatamap.get("MobileNo").toString());
  		    	AndroidClickUsingActions(EdaatOR.Aqarek_SigninBtn.toString());
  			   this.getScreen();
//  			   String OTP = DBConnect("");    			    
//  			    char OTP1 = OTP.charAt(0);
//  			    char OTP2 = OTP.charAt(1);
//  			    char OTP3 = OTP.charAt(2);
//  			    char OTP4 = OTP.charAt(3);
//  			   AndroidEdit(EdaatOR.Aqarek_OTPtextfield1.toString(), String.valueOf(OTP1));
//  			   AndroidEdit(EdaatOR.Aqarek_OTPtextfield2.toString(), String.valueOf(OTP2));
//  			   AndroidEdit(EdaatOR.Aqarek_OTPtextfield3.toString(), String.valueOf(OTP3));
//  			   AndroidEdit(EdaatOR.Aqarek_OTPtextfield4.toString(), String.valueOf(OTP4));
  			   AndroidEdit(EdaatOR.Aqarek_OTPtextfield1.toString(), testdatamap.get("OTP1").toString());
 			   AndroidEdit(EdaatOR.Aqarek_OTPtextfield2.toString(), testdatamap.get("OTP2").toString());
 			   AndroidEdit(EdaatOR.Aqarek_OTPtextfield3.toString(), testdatamap.get("OTP3").toString());
 			   AndroidEdit(EdaatOR.Aqarek_OTPtextfield4.toString(), testdatamap.get("OTP4").toString());
				   this.getScreen();
 		       AndroidClickUsingActions(EdaatOR.Aqarek_OTPConfirm.toString());
 		       AndroidClickUsingActions(EdaatOR.Aqarek_MoreMenu.toString());
			   	   Thread.sleep(2000);
			       scroll(0.5,0.8,0.5,0.2);
 		       AndroidClickUsingActions(EdaatOR.Aqarek_EngLanguage.toString());
  			   this.getScreen();
				   Thread.sleep(6000);
				   	WebElement Home = driver.findElement(By.xpath(EdaatOR.Aqarek_HomePagetxt.toString()));
			   	   Thread.sleep(1000);
					VerifyValue1(Home.getText(),testdatamap.get("HomePage").toString());
					horizontalSwipeByPercentage(0.9, 0.1, 0.1);

					this.getScreen();
				   Thread.sleep(2000);
				   test.log(Status.PASS, "* Login to the application is Passed * ");
		           Log.ReportEvent("PASS", "Step3 : Verify Aqarek Login is Suceessfull");
		           
		           
	 			}
	       catch (Exception e) {
			test.log(Status.FAIL, "* Login to the application is Fail * ");
		}
		}
	

	public void Logout(Map<Object, Object> testdatamap, Log Log) {
		try {
		       AndroidClickUsingActions(EdaatOR.Aqarek_EngMoreMenu.toString());
			   WebElement MorePagetxt = driver.findElement(By.xpath(EdaatOR.Aqarek_MorePagetxt.toString()));
   	     	   VerifyValue1(MorePagetxt.getText(),testdatamap.get("MorePage").toString());
   	     	   Thread.sleep(2000);
        	   this.getScreen();
        	   scroll(0.5, 0.8, 0.5, 0.2);
        	   Thread.sleep(2000);
         	   this.getScreen();
		       AndroidClickUsingActions(EdaatOR.Aqarek_Logout.toString());
        	   Thread.sleep(1000);
         	   this.getScreen();
			 test.log(Status.PASS, "* Tearms and Conditions Page is Pass * ");
	         Log.ReportEvent("PASS", "Step4 : Verify Terms and condition Page is displayed Suceessfull");
	         driver.quit();
      	     Log.ReportEvent("Pass", "Step5 : Close app  Suceessful"); 	
		}
		catch (Exception e) {
			test.log(Status.FAIL, "* Tearms and Conditions Page is Fail * ");
		}
	}
	
	
	public void Signin(Map<Object,Object> testdatamap){
		try {
			  
     	   	   Thread.sleep(10000);
     	   	   Actions actions = new Actions(driver);
     	   	   WebElement click = driver.findElement(By.xpath(EdaatOR.Aqarek_Myrequestmenu.toString()));
     	   	   Thread.sleep(2000);
     	   	   this.getScreen();
		   	   actions.click(click).build().perform();
			   Thread.sleep(3000);	
			   this.getScreen();
			   driver.findElement(By.xpath(EdaatOR.Aqarek_MobileNotextfield.toString())).sendKeys(testdatamap.get("MobileNo").toString());
			   Thread.sleep(5000);
			   this.getScreen();
			   WebElement signin = driver.findElement(By.xpath(EdaatOR.Aqarek_SigninBtn.toString()));
		   	   actions.click(signin).build().perform();
		   	   Thread.sleep(2000);
		   	   WebElement tostmsg = driver.findElement(By.xpath(EdaatOR.Aqarek_Signin_ErrorMessage.toString()));
		       VerifyValue1(tostmsg.getText(),testdatamap.get("ErrorMessage").toString());
		       this.getScreen();
		       Thread.sleep(3000);	
		   	   test.log(Status.PASS, "* Signin Error Message PASSED * ");

			} 
		catch (Exception e) {
			test.log(Status.FAIL, "* Signin Error Message FAILED * ");
		}
	}


		
			
}
		 
