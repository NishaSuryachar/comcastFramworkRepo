package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.camcast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.camcast.crm.objectRepositoryUtility.HomePage;
import com.camcast.crm.objectRepositoryUtility.LoginPage;
import com.camcast.crm.objectRepositoryUtility.OrganisationInfoPage;
import com.camcast.crm.objectRepositoryUtility.OrgansisationsPage;
import com.comcast.crm.generic.BaseTest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.propertyUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class DeleteOrgTest extends BaseClass{

	public void DeleteOrgTest() throws IOException, InterruptedException {
		
		//step 1:Read Data From Excel File
		String orgName = eutil.getDataFromExcelFile("Sheet1", 10, 2)+jutil.getRandomNum();

		//step 2:navigate to oraganisation module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();

		//step3:click on create oraganisation Button
		OrgansisationsPage op=new OrgansisationsPage(driver);
	    op.getCreateNewOrgBtn().click();

		//step 4:Enter all the details and create new organisation 
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);
	
		//verify the header Msg Expected Result
		OrganisationInfoPage oip=new OrganisationInfoPage(driver);
		String actOrgHeaderName = oip.getHeaderMsg().getText();	
		if(actOrgHeaderName.contains(orgName))
		{
			System.out.println(orgName+" is verified =====PASS");
		}
		else
		{
			System.out.println(orgName+" is Not verified =====FAIL");
		}
		
		//verify Header OrgName info Expected Result
		String actOrgName = oip.getHeaderOrgName().getText();
		if(actOrgName.contains(orgName))
		{
			System.out.println(orgName+" is verified =====PASS");
		}
		else
		{
			System.out.println(orgName+" is Not verified =====FAIL");
		}
		hp.getOrgLink().click();
		
		op.getSearchEdt().sendKeys(orgName);
		wutil.SelectByVisibleText(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../..//td[8]/a[text()='del']")).click();
		
		wutil.AlertAndAccept(driver);
	}

}
