package com.azmqalabs.edaattestautomation.apppages.collectionofpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.azmqalabs.edaattestautomation.apppages.biller.pages.*;
import com.azmqalabs.edaattestautomation.common.DBConnect;
import com.azmqalabs.edaattestautomation.common.uielement.fieldDecorator;
import com.azmqalabs.edaattestautomation.apppages.admin.pages.*;
public class Pages{
	private WebDriver driver;
	private ExtentTest test;
	public DBConnect DBConnect;
	public InvokeApplicationPage InvokeApplicationPage;
	public AdminInvokeApplicationPage AdminInvokeApplicationPage;
	public AqarekPlatformPage AqarekPlatformPage;

public Pages(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(new fieldDecorator(driver,test), this);
		DBConnect= new DBConnect(driver,test);
		InvokeApplicationPage = new InvokeApplicationPage(driver,test);
		AdminInvokeApplicationPage=new  AdminInvokeApplicationPage(driver,test);
		AqarekPlatformPage = new AqarekPlatformPage(driver, test);
}
}
