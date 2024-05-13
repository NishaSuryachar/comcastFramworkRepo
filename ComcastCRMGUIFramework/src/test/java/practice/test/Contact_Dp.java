package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class Contact_Dp {

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		Object[][] objArr=new Object[3][2];
        objArr[0][0]="Nisha";
        objArr[0][1]="M S";
        
        objArr[1][0]="shruthi";
        objArr[1][1]="M S";
        
        objArr[2][0]="Arjun";
        objArr[2][1]="M S";
	
		
		return objArr;
	}
	
	@Test(dataProvider = "getData")
	public void getProductInfo(String Firstname,String Lastname)
	{
	   System.out.println("Firstname : "+Firstname+" Lastname : "+Lastname);
	}
}
