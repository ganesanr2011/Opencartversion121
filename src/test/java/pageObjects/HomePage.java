package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
@FindBy(xpath="//span[normalize-space()='My Account']") WebElement myaccount;
@FindBy(xpath="//a[normalize-space()='Register']") WebElement reglink;
@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") WebElement loginlink;
public void clickMyaccount()
{
	myaccount.click();
	}
public void clickRegister()
{
	reglink.click();
	}
public void clickLogin()
{
	loginlink.click();
}
}
