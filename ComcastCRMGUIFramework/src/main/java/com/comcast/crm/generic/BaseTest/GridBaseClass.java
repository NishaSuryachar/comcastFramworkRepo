package com.comcast.crm.generic.BaseTest;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.camcast.crm.objectRepositoryUtility.HomePage;
import com.camcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.propertyUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class GridBaseClass
{
	public RemoteWebDriver driver=null;
	public static RemoteWebDriver sdriver=null;
	
	public propertyUtility putil=new propertyUtility();
	public ExcelUtility eutil=new ExcelUtility();
	public JavaUtility jutil=new JavaUtility();
	public WebDriverUtility wutil=new WebDriverUtility();
	public DatabaseUtility dutil=new DatabaseUtility(); 

//	public WebDriver driver=null;
//	public static WebDriver sDriver=null;
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void bsConfig() throws SQLException
	{
		System.out.println("===Connect to DB,Report Config======");
		dutil.getConnection();
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void bcConfig(/*String browser*/) throws IOException
	{
		
		System.out.println("===Launch Browser======");
		//String BROWSER = browser;
		String BROWSER =putil.getDataFromPropertyFile("browser");
		
		URL url = new URL("http://169.254.73.68:4444");
		DesiredCapabilities dc=new DesiredCapabilities();
		
		if(BROWSER.contains("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.contains("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(BROWSER.contains("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		driver=new RemoteWebDriver(url, dc);
		sdriver=driver;
	}
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void bmConfig() throws IOException
	{
		System.out.println("===Login======");
		String URL = putil.getDataFromPropertyFile("url");
		String USERNAME = putil.getDataFromPropertyFile("username");
		String PASSWORD = putil.getDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.LoginToApp(URL,USERNAME, PASSWORD);
	}
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void amConfig() throws InterruptedException
	{
		System.out.println("===Logout======");
		HomePage hp=new HomePage(driver);
		hp.LogOutFromApp(driver);
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void acConfig()
	{
		System.out.println("===Close Browser======");
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void asConfig() throws SQLException
	{
		System.out.println("===Close DB,Report BackUp======");
		dutil.closeConnection();
	}
}
