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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

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

public class CreateOrganisationWithIndustryTest extends BaseClass{

	@Test
	public void CreateOrganisationWithIndustryTest() throws IOException, InterruptedException {
		String orgName = eutil.getDataFromExcelFile("Sheet1", 4, 2)+jutil.getRandomNum();
		String industry = eutil.getDataFromExcelFile("Sheet1", 4, 3);
		String Type = eutil.getDataFromExcelFile("Sheet1", 4, 4);

		//step 2:navigate to oraganisation module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		//step3:click on create oraganisation Button
		OrgansisationsPage op=new OrgansisationsPage(driver);
		op.getCreateNewOrgBtn().click();


		//step 4:Enter all the details and create new organisation
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrgWithIndustryType(orgName, industry, Type);
		
		//verify the Industry and Type info
		OrganisationInfoPage oip=new OrganisationInfoPage(driver);
		String actIndustry = oip.getHeaderIndstry().getText();
		if(actIndustry.contains(industry))
		{
			System.out.println(industry+ " Information is Verified====PASS  ");
		}
		else
		{
			System.out.println(industry+" Information is Not Verified====FAIL ");
		}
		
        String actType = oip.getHeaderTypeName().getText();
        if(actType.contains(Type))
		{
			System.out.println(Type+ " Information is Verified====PASS  ");
		}
		else
		{
			System.out.println(Type+" Information is Not Verified====FAIL ");
		}
	}

}
