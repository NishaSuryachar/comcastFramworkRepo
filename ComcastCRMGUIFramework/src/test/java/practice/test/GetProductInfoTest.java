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

public class GetProductInfoTest {

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelUtility eutil=new ExcelUtility();
		int rowCount = eutil.getRowCount("Sheet3");
		System.out.println(rowCount);
		Object[][] objArr=new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) {
        objArr[i][0]=eutil.getDataFromExcelFile("Sheet3", i+1, 0);
        objArr[i][1]=eutil.getDataFromExcelFile("Sheet3", i+1, 1);
	}
		
		return objArr;
	}
	
	@Test(dataProvider = "getData")
	public void getProductInfo(String brandName,String ProductName)
	{
	   
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		String x = "//span[text()='"+ProductName+"']/../../../../../div[1]/div[3]/div/div/div/div/div/a/span/span/span[2]";
	    String price = driver.findElement(By.xpath(x)).getText();
	    System.out.println(price);
	    driver.close();
	}
}
