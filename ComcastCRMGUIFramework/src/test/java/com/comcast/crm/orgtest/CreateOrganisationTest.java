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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.camcast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.camcast.crm.objectRepositoryUtility.HomePage;
import com.camcast.crm.objectRepositoryUtility.LoginPage;
import com.camcast.crm.objectRepositoryUtility.OrganisationInfoPage;
import com.camcast.crm.objectRepositoryUtility.OrgansisationsPage;
import com.comcast.crm.generic.BaseTest.BaseClass;
import com.comcast.crm.generic.BaseTest.GridBaseClass;
import com.comcast.crm.generic.ListenerUtility.ListImplementationClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.propertyUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

@Listeners(com.comcast.crm.generic.ListenerUtility.ListImplementationClass.class)
public class CreateOrganisationTest extends BaseClass{

	@Test(groups = "smokeTest")
	public void CreateOrganisationTest() throws IOException, InterruptedException {
	
        /* Read Data From Excel File*/
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String orgName = eutil.getDataFromExcelFile("Sheet1", 1, 2)+jutil.getRandomNum();

		/*step 2:navigate to oraganisation module*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to org page");
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();

		/*step3:click on create oraganisation Button*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create org page");
		OrgansisationsPage op=new OrgansisationsPage(driver);
	    op.getCreateNewOrgBtn().click();

		/*step 4:Enter all the details and create new organisation */
	    UtilityClassObject.getTest().log(Status.INFO, "Create org");
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,orgName+ "Create new org");
		
		/*verify the header Msg Expected Result*/
		OrganisationInfoPage oip=new OrganisationInfoPage(driver);
		
		String actOrgHeaderName = oip.getHeaderMsg().getText();
		System.out.println(actOrgHeaderName);
		boolean status = actOrgHeaderName.trim().contains(orgName);
		Assert.assertTrue(status);
		
		/*verify Header OrgName info Expected Result*/
		String actOrgName = oip.getHeaderOrgName().getText();
		String text2 = actOrgName.trim();
		Assert.assertEquals(text2, orgName);

	}

	@Test(groups = "regressionTest")
	public void CreateOrganisationWithIndustryTest() throws IOException, InterruptedException {
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String orgName = eutil.getDataFromExcelFile("Sheet1", 4, 2)+jutil.getRandomNum();
		String industry = eutil.getDataFromExcelFile("Sheet1", 4, 3);
		String Type = eutil.getDataFromExcelFile("Sheet1", 4, 4);

		/*step 2:navigate to oraganisation module*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to org page");
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		/*step3:click on create oraganisation Button*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create org page");
		OrgansisationsPage op=new OrgansisationsPage(driver);
		op.getCreateNewOrgBtn().click();


		/*step 4:Enter all the details and create new organisation*/
		UtilityClassObject.getTest().log(Status.INFO, "Create org");
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		
		UtilityClassObject.getTest().log(Status.INFO,orgName+ "Create new org");
		cnop.createOrgWithIndustryType(orgName, industry, Type);
		
		/*verify the Industry and Type info*/
		OrganisationInfoPage oip=new OrganisationInfoPage(driver);
		String actIndustry = oip.getHeaderIndstry().getText();
		Assert.assertEquals(actIndustry,industry);
		
        String actType = oip.getHeaderTypeName().getText();
        Assert.assertEquals(actType,Type);
	}

	@Test(groups = "regressionTest")
	public void CreateOrganisationWithPhoneNumberTest() throws IOException, InterruptedException {
		/*step 1:Read Data From Excel File*/
		String orgName = eutil.getDataFromExcelFile("Sheet1", 7, 2)+jutil.getRandomNum();
		String phoneNo = eutil.getDataFromExcelFile("Sheet1", 7, 3);

		/*step 2:navigate to oraganisation module*/
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();

		/*step3:click on create oraganisation Button*/
		OrgansisationsPage op=new OrgansisationsPage(driver);
		op.getCreateNewOrgBtn().click();

		/*step 4:Enter all the details and create new organisation */
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
         cnop.createOrgWithPhoneNo(orgName, phoneNo);

		/*verify the Phone Number*/
		OrganisationInfoPage oip=new OrganisationInfoPage(driver);
		String actPhoneNo = oip.getHeaderPhoneNo().getText();
		Assert.assertEquals(actPhoneNo, phoneNo);
	}
	
	
	
}
