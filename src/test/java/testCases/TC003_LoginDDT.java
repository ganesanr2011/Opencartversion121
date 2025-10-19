package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass 
{
@Test	
public void verify_loginDDT(String email, String pwd, String exp)
{
	logger.info("Test execution started TC003_loginDDT");
	try {	
	HomePage hp=new HomePage(driver);
	hp.clickMyaccount();
	logger.info("Clicked myaccount");
	hp.clickLogin();
	logger.info("Clicked login");
	
	LoginPage lp=new LoginPage(driver);
	lp.setEmailAddress(email);
	lp.setPassword(pwd);
	logger.info("Provided login credentials");
	lp.clickLogin();
	logger.info("Logged in account page");
	
	MyAccountPage ap=new MyAccountPage(driver);
	boolean ts=ap.myAccountPageDisplayed();
	if(exp.equalsIgnoreCase("valid"))
	{
		if(ts==true)
		{
			ap.clickLogout();
			Assert.assertTrue(true);
		}else
		{
			Assert.assertTrue(false);
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(ts==true)
			{
				ap.clickLogout();
				Assert.assertTrue(false);
			}else
			{
				Assert.assertTrue(true);
			}
		}
		
	}
	}catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("Test execution completed TC003_loginDDT");
	
	}
	
}

