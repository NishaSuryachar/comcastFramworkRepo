package com.comcast.crm.generic.ListenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.BaseTest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImplementationClass implements ITestListener,ISuiteListener
{
	public ExtentReports report;
	public static ExtentTest test;
	String date;

	public void onFinish(ISuite suite) {
		
		System.out.println("Report BackUP");
		report.flush();


	}

	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		date = new Date().toString().replace(" ","_").replace(":", "_");
		
		//Extent report config
		ExtentSparkReporter spark=new ExtentSparkReporter(".\\AdvanceReport\\report"+date+".html");
		spark.config().setDocumentTitle("CRM Test suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setReportName("Nisha M S");
		spark.config().setTheme(Theme.DARK);

		//add Environment information and create Test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("platform", "Windows");
		report.setSystemInfo("browser", "edge");

	}

	public void onFinish(ITestContext context) {


	}

	public void onStart(ITestContext context) {


	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {


	}

	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();

		TakesScreenshot ts=(TakesScreenshot)UtilityClassObject.getdriver();
	
		String filepath = ts.getScreenshotAs(OutputType.BASE64);

		
//		File dst=new File(".\\screenshot\\"+testName+date+".png");
     	test.addScreenCaptureFromBase64String(filepath, testName+"-"+date);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"=========FAIL=========");

	}

	public void onTestSkipped(ITestResult result) {


	}

	public void onTestStart(ITestResult result) {
		System.out.println("======"+result.getMethod().getMethodName()+"=========START=========");
		test = report.createTest(result.getMethod().getMethodName());
	    UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"=========STARTED=========");

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("======"+result.getMethod().getMethodName()+"========END==========");
		test.log(Status.PASS, result.getMethod().getMethodName()+"=========COMPLETED=========");
	}

}
