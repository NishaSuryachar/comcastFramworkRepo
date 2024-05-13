package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
     }
	
	
	
	/**
	 * This method will minimize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method perform implicitly wait
	 * @param driver
	 */
	public void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	}
	
	/**
	 * This method perform explicitly wait
	 * @param driver
	 * @param element
	 */
	public void explicitlyWaitVisibilityOfEle(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method perform explicitly wait action
	 * @param driver
	 * @param element
	 */
	public void explicitlyWaitEleToBeClick(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method contains switch to Child window based on url
	 * @param driver
	 * @param PartialUrl
	 */
	public void switchToBasedOnUrl(WebDriver driver,String PartialUrl)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String WindowID=it.next();
			driver.switchTo().window(WindowID);
			String actTitle = driver.getCurrentUrl();
			if(actTitle.contains(PartialUrl))
			{
				break;
			}
		}
	}

	/**
	 * This method contains switch to Child window based on Title
	 */
	public void switchToBasedOnTitle(WebDriver driver,String PartialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String WindowID=it.next();
			driver.switchTo().window(WindowID);
			String actTitle = driver.getCurrentUrl();
			if(actTitle.contains(PartialTitle))
			{
				break;
			}
		}
	}
	
	/**
	 * This method contains operation on DropDown based on Index
	 * @param element
	 * @param index
	 */
	public void SelectByIndex(WebElement element,int index)
	{
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	
	/**
	 * This method contains operation on DropDown based on visible Text
	 * @param element
	 * @param text
	 */
	public void SelectByVisibleText(WebElement element,String text)
	{
		Select s=new Select(element);
		s.selectByVisibleText(text);
	}
	
	/**
	 * This method contains operation on DropDown based on value
	 * @param element
	 * @param value
	 */
	public void SelectByValue(WebElement element,String value)
	{
		Select s=new Select(element);
		s.selectByValue(value);
	}
	/**
	 * This method contains deselect operation on DropDown based on index
	 * @param element
	 * @param index
	 */
	public void deSelectByIndex(WebElement element,int index)
	{
		Select s=new Select(element);
		s.deselectByIndex(index);
	}
/**
 * This method contains deselect operation on DropDown based on value
 * @param element
 * @param value
 */
	public void deSelectByValue(WebElement element,String value)
	{
		Select s=new Select(element);
		s.deselectByValue(value);
	}
	
	/**
	 * This method contains deselect operation on DropDown based on visible Text
	 * @param element
	 * @param text
	 */
	public void deSelectByVisibleText(WebElement element,String text)
	{
		Select s=new Select(element);
		s.deselectByVisibleText(text);
	}
	
	/**
	 * This method contains deselect All operation 
	 * @param element
	 */
	public void deSelectAll(WebElement element)
	{
		Select s=new Select(element);
		s.deselectAll();
	}
	
	/*
	 * This method perform Move the cursor to the Particular web element 
	 */
    public void MoveToElement(WebDriver driver,WebElement element)
    {
    	Actions a=new Actions(driver);
    	a.moveToElement(element).build().perform();
    }
    
    /**
     * This method perform right click action on WebElement
     * @param driver
     * @param element
     */
    public void rightClick(WebDriver driver,WebElement element)
    {
    	Actions a=new Actions(driver);
    	a.contextClick(element).build().perform();;
    }
    
    /*
     * This method perform double click action on WebElement
     */
    public void DoubleClick(WebDriver driver,WebElement element)
    {
    	Actions a=new Actions(driver);
    	a.doubleClick(element).build().perform();;
    }
    
    /*
     * This method perform click the element and hold the element action
     */
    public void ClickAndHold(WebDriver driver,WebElement element)
    {
    	Actions a=new Actions(driver);
    	a.clickAndHold(element).build().perform();;
    }
    
    /*
     * This method perform drag the element and drop the element action
     */
    public void DragAndDrop(WebDriver driver,WebElement src,WebElement dst)
    {
    	Actions a=new Actions(driver);
    	a.dragAndDrop(src, dst).build().perform();;
    }
	
    /*
     * This method perform alert opertion for accept
     */
	public void AlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/*
	 * This method perform alert opertion for cancel
	 */
	public void AlertAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
