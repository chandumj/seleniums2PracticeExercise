package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy(xpath = "//div[@class='header-links']/descendant::a[2]")
	WebElement login;
	
	@FindBy(xpath =  "//input[@id='Email']")
	WebElement email;
	
	@FindBy(xpath =  "//input[@id='Password']")
	WebElement Password;
	
	@FindBy(xpath =  "(//input[@type='submit'])[2]")
	WebElement loginbtn;
	
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	
	public void login(String gmail,String password) {
		login.click();
		email.clear();
		email.sendKeys(gmail);
		Password.clear();
		Password.sendKeys(password);
		loginbtn.click();
	}
	
	public WebElement getLoginBtn() {
		return login;
	}
}



