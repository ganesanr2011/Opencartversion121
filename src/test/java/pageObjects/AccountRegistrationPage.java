package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage
{

public AccountRegistrationPage(WebDriver driver)
{
	super(driver);
	}
@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
@FindBy(xpath="//input[@id='input-lastname']")  WebElement txtLastName;
@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
@FindBy(xpath="//input[@name='agree']") WebElement chkdPolicy;
@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement txtConfirmation;

public void setFirstName(String fname)
{
txtFirstName.sendKeys(fname);	
}
public void setLastName(String lname)
{
txtLastName.sendKeys(lname);	
}
public void setEmail(String email)
{
txtEmail.sendKeys(email);	
}
public void setTelephone(String phno)
{
	txtTelephone.sendKeys(phno);	
}
public void setPassword(String pwd)
{
	txtPassword.sendKeys(pwd);
	}
public void setConfirmPassword(String pwd)
{
	txtConfirmPassword.sendKeys(pwd);
	}
public void policyClick()
{
	chkdPolicy.click();
	}
public void clickContinue()
{
	WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(5));
	mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	//btnContinue.sendKeys(Keys.RETURN);
	//JavascriptExecutor js=(JavascriptExecutor)driver;
	//js.executeScript("arguments[0].click();",btnContinue);
	//Actions act= new Actions(driver);
	//act.moveToElement(btnContinue).click().perform();
	//btnContinue.submit();
	}
public String checkConfirmation()
{
try
{
return (txtConfirmation.getText());
}catch(Exception e)
{
	return e.getMessage();
	}

}}
