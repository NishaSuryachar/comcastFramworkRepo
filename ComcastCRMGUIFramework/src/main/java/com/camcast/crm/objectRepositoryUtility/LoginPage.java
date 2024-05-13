package com.camcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{

	@FindBy(name ="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name ="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id ="submitButton")
	private WebElement LoginBtn;
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return LoginBtn;
	}
	
	public void LoginToApp(String url,String username,String password)
	{
		implicitWait(driver);
		maximizeWindow(driver);
		driver.get(url);
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		LoginBtn.click();
	}
}
