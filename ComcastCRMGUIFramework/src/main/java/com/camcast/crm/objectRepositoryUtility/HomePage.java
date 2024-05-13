package com.camcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement OrgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement ContactLink;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(xpath =  "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administrator;
	
	@FindBy(linkText = "Sign Out")
	private WebElement Signout;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrgLink() {
		return OrgLink;
	}

	public WebElement getContactLink() {
		return ContactLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}
	
	public WebElement getAdministrator() {
		return administrator;
	}

	public WebElement getSignout() {
		return Signout;
	}

	public void navigateToCampaignsPage(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.moveToElement(moreLink).perform();
		campaignsLink.click();
	}

	public void LogOutFromApp(WebDriver driver) throws InterruptedException
	{
		Actions a=new Actions(driver);
		a.moveToElement(administrator).build().perform();
		Thread.sleep(2000);
		Signout.click();
	}
	
	
	
	
}
