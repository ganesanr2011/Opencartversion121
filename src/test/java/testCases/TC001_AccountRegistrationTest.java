package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	
	@Test(groups={"Sanity","Regression","Master"})
	public void verify_Account_Registration()
	{
		try
		{		
		logger.info("Test TC001_AccountRegistrationTest execution started");
		HomePage hp= new HomePage(driver);
		hp.clickMyaccount();
		logger.info("Clicked account page link");
		hp.clickRegister();
		logger.info("Clicked Register page link");
		
		AccountRegistrationPage acpage=new AccountRegistrationPage(driver);
		logger.info("Providing customer details");
		acpage.setFirstName(randomString().toUpperCase());
		acpage.setLastName(randomString().toUpperCase());
		acpage.setEmail(randomString()+"@gmail.com");
		acpage.setTelephone(randomNumber());
		String pwd=ranAlphaNumeric();
		acpage.setPassword(pwd);
		acpage.setConfirmPassword(pwd);
		acpage.policyClick();
		acpage.clickContinue();
		logger.info("Validating expected Message");
		String conmsg=acpage.checkConfirmation();
		if(conmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed");
			logger.debug("debug logs");
			Assert.assertTrue(false);
		}	
		//Assert.assertEquals(conmsg, "Your Account Has Been Createddd!");
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("Test execution completed");
	}
		
}
