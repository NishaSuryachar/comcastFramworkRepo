package com.camcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class products {

	@FindBy(xpath = "//img[@title='Create Product...']")
	private WebElement createproductLookUpButton;
	
	public products(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getCreateproductLookUpButton() {
		return createproductLookUpButton;
	}
	
}
