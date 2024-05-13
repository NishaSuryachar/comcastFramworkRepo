package com.camcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateNewContactPage {

	@FindBy(name = "lastname")
	private WebElement lastnameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement chooseOrgFromLookUp;
	
	@FindBy(name = "support_start_date")
	private WebElement startDateEdt;
	
	@FindBy(name = "support_end_date")
	private WebElement endDateEdt;
	
	@FindBy(xpath  = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtnEdt;
	
	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}

	public WebElement getStartDateEdt() {
		return startDateEdt;
	}

	public WebElement getEndDateEdt() {
		return endDateEdt;
	}

	public WebElement getSaveBtnEdt() {
		return saveBtnEdt;
	}
	
	
	public WebElement getChooseOrgFromLookUp() {
		return chooseOrgFromLookUp;
	}

	public void createContactWithLastName(String lastname)
	{
		lastnameEdt.sendKeys(lastname);
		saveBtnEdt.click();
	}
	
	public void createContactWithSupportDate(String lastname,String startdate,String enddate)
	{
		lastnameEdt.sendKeys(lastname);
		startDateEdt.clear();
		startDateEdt.sendKeys(startdate);
		endDateEdt.clear();
		endDateEdt.sendKeys(enddate);
		saveBtnEdt.click();
	}
}
