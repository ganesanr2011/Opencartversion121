package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002LoginTest extends BaseClass
{
	@Test(groups={"Sanity","Master"})
	public void loginTest()
	{
		try
		{
		logger.info("Test execution started");
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		logger.info("Clicked myaccount");
		hp.clickLogin();
		logger.info("Clicked login");
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		logger.info("Provided login credentials");
		lp.clickLogin();
		logger.info("Logged in account page");
		MyAccountPage ap=new MyAccountPage(driver);
		boolean ts=ap.myAccountPageDisplayed();
		logger.info("account verification");
		Assert.assertTrue(ts);
		}catch(Exception e)
		{
			Assert.fail();
		}
				
		logger.info("login test execution completed");
	}
	}
