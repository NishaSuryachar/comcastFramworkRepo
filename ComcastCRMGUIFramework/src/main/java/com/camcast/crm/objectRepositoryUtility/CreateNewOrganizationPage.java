package com.camcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {

	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name = "industry")
	private WebElement industrydd;
	
	@FindBy(name="accounttype")
	private WebElement typedd;
	
	@FindBy(id="phone")
	private WebElement phoneNo;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	public WebElement getIndustrydd() {
		return industrydd;
	}

	public WebElement getTypedd() {
		return typedd;
	}



	public WebElement getPhoneNo() {
		return phoneNo;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void createOrg(String orgName)
	{
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrgWithIndustryType(String orgName,String industry,String Type)
	{
		orgNameEdt.sendKeys(orgName);
		Select s1=new Select(industrydd);
		s1.selectByVisibleText(industry);
		Select s2=new Select(typedd);
		s2.selectByVisibleText(Type);
		saveBtn.click();
	}
	public void createOrgWithPhoneNo(String orgName,String phoneNum)
	{
		orgNameEdt.sendKeys(orgName);
		phoneNo.sendKeys(phoneNum);
		saveBtn.click();
	}
}
