package com.camcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganisationInfoPage {

	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement headerOrgName;
	
	@FindBy(id = "mouseArea_Industry")
	private WebElement headerIndstry;
	
	@FindBy(id = "mouseArea_Type")
	private WebElement headerTypeName;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement headerPhoneNo;
	
	public OrganisationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}
    
	public WebElement getHeaderOrgName() {
		return headerOrgName;
	}

	public WebElement getHeaderIndstry() {
		return headerIndstry;
	}

	public WebElement getHeaderTypeName() {
		return headerTypeName;
	}

	public WebElement getHeaderPhoneNo() {
		return headerPhoneNo;
	}
	
	
	
	
}
