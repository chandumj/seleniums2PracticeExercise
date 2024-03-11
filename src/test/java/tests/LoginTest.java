package tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import pages.LoginPage;
import utilities.ExcelUtility;
import utilities.MyListener;
@Listeners(MyListener.class)
public class LoginTest {
	ExtentReports ex=MyListener.getInstance();
	
	WebDriver driver;
	LoginPage lp;
	@BeforeTest
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver =new ChromeDriver();
		lp=new LoginPage(driver);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		
	}
	
	@Test(dataProvider = "testData")
	public void testLogin(String email,String password) {
		lp.login(email, password);
		String ExpectTitle ="Demo Web Shop";
		String actualTitle= driver.getTitle();
		Assert.assertEquals(actualTitle, ExpectTitle);
		lp.getLoginBtn().click();
		
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	@DataProvider(name = "testData")
	public Object[][] testData() throws EncryptedDocumentException, IOException{
		ExcelUtility xL=new ExcelUtility();
		return xL.getTestData("Sheet1");
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
