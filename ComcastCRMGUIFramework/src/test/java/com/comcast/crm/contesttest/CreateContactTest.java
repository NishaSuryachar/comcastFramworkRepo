package com.comcast.crm.contesttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.camcast.crm.objectRepositoryUtility.ContactInfoPage;
import com.camcast.crm.objectRepositoryUtility.ContactsPage;
import com.camcast.crm.objectRepositoryUtility.CreateNewContactPage;
import com.camcast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.camcast.crm.objectRepositoryUtility.HomePage;
import com.camcast.crm.objectRepositoryUtility.LoginPage;
import com.camcast.crm.objectRepositoryUtility.OrganisationInfoPage;
import com.camcast.crm.objectRepositoryUtility.OrganisationPopUp;
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
public class CreateContactTest extends BaseClass{

	@Test(groups = "smokeTest")
	public void createContactTest() throws IOException, InterruptedException
	{
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		/*step 1:Read Data From Excel File*/
		String Lastname = eutil.getDataFromExcelFile("contacts", 1, 3)+jutil.getRandomNum();

		/*step 2:navigate to oraganisation module*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to contact page");
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();

		/*step3:click on create oraganisation Button*/
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create contact page");
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContactLookUpBtn().click();

		/*step 4:Enter all the details and create new organisation*/
		UtilityClassObject.getTest().log(Status.INFO, "Create contact");
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createContactWithLastName(Lastname);

		/*verify the LastName*/
		ContactInfoPage cip=new ContactInfoPage(driver);
		
		String actHeader = cip.getHeaderMsg().getText();
		boolean status = actHeader.trim().contains(Lastname);
		Assert.assertTrue(status);
		
		String actLastName = cip.getHeaderLastname().getText();
		String text = actLastName.trim();
		SoftAssert s=new SoftAssert();
		s.assertEquals(text,Lastname);
		s.assertAll();
	}
	
	@Test(groups = "regressionTest")
	public void CreateContactWithSupportDateTest() throws IOException, InterruptedException
	{
		/*step 1:Read Data From Excel File*/
		String Lastname = eutil.getDataFromExcelFile("contacts", 4, 2)+jutil.getRandomNum();

		/*step 2:navigate to oraganisation module*/
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();

		/*step3:click on create oraganisation Button*/
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContactLookUpBtn().click();

		/*step 4:Enter all the details and create new organisation */
		String startDate = jutil.getSystemData();
		String endDate = jutil.getRequiredDate(30);

		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createContactWithSupportDate(Lastname, startDate, endDate);

		/*verify the StartDate and EndData*/

		ContactInfoPage cip=new ContactInfoPage(driver);
		String actStartDate = cip.getHeaderstartDate().getText();
		Assert.assertEquals(actStartDate, startDate);
		
		
		String actEndDate = cip.getHeaderendDate().getText();
		Assert.assertEquals(actEndDate, endDate);
	}
	
	@Test(groups = "regressionTest")
	public void CreateContactWithOrgTest() throws EncryptedDocumentException, IOException, InterruptedException {

		/*step 1:Read Data From Excel */
		String orgName = eutil.getDataFromExcelFile("contacts", 7, 2)+jutil.getRandomNum();
		String ContactLastname = eutil.getDataFromExcelFile("contacts", 7, 3);

		/*step 2:navigate to oraganisation module*/
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();

		/*step3:click on create oraganisation Button*/
		OrgansisationsPage op=new OrgansisationsPage(driver);
		op.getCreateNewOrgBtn().click();

		/*step 4:Enter all the details and create new organisation*/
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.getOrgNameEdt().sendKeys(orgName);
		cnop.getSaveBtn().click();


		/*verify the header Msg Expected Result*/
		OrganisationInfoPage oip=new OrganisationInfoPage(driver);
		String actOrgName = oip.getHeaderOrgName().getText();
		String text = actOrgName.trim();
		Assert.assertEquals(text,orgName);

		/*step 5:navigate to contact */
		hp.getContactLink().click();

		/*step 6:click on create contact*/
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContactLookUpBtn().click();

		/*step 7:enter all details and create new organisation*/
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.getLastnameEdt().sendKeys(ContactLastname);

		//driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		cncp.getChooseOrgFromLookUp().click();


		/*SwitchTo child window*/
		wutil.switchToBasedOnUrl(driver, "module=Accounts");

		OrganisationPopUp opu=new OrganisationPopUp(driver);
		opu.getSearchEdt().sendKeys(orgName);
		opu.getSearchBtnEdt().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();


		wutil.switchToBasedOnUrl(driver, "Contacts&action");
		cncp.getSaveBtnEdt().click();

		/*verify the Contact Lastname with Expected Result*/
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actLastName = cip.getHeaderLastname().getText();
		String text3 = actLastName.trim();
		Assert.assertEquals(text3,ContactLastname);
		
		/*verify the organisation Name with Expected Result*/

		String actOrgName1 = cip.getHeaderOrgName().getText();
		String text1 = actOrgName1.trim();
		Assert.assertEquals(text1,orgName);
	}

}
