package testBase;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
public class BaseClass

{
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups={"Sanity", "Regression","Master"})
	@Parameters({"os", "browser"})
	public void set(String os, String br) throws IOException 
	{
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		logger=LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capability= new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows"))
			{
				capability.setPlatform(Platform.WIN10);
			}else if(os.equalsIgnoreCase("mac"))
			{
				capability.setPlatform(Platform.MAC);
			}else
			{
				System.out.println("No matching os");
				return;
			}
		switch(br.toLowerCase())
		{
		case "chrome": capability.setBrowserName("Chrome"); break;
		case "edge":capability.setBrowserName("MicrosoftEdge");
		default: System.out.println("invalid browser name"); return;
		}
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capability);
				
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{	
		switch(br.toLowerCase()) 
		{
		case "chrome": driver=new ChromeDriver();break;
		case "edge": driver=new EdgeDriver();break;
		case "firefox": driver=new FirefoxDriver();break;
		default: System.out.println("Invalid browser selection"); return;
				}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("appURL1"));
		driver.manage().window().maximize();
		}
	@AfterClass(groups={"Sanity", "Regression","Master"})
	public void tearDown() throws InterruptedException 
	{
		driver.close();
	}
	public String randomString() 
	{
		String randomstr= RandomStringUtils.randomAlphabetic(5);
		return randomstr;
	}
	public String randomNumber() 
	{
		String randomNum= RandomStringUtils.randomNumeric(9);
		return randomNum;
	}
	public String ranAlphaNumeric()
	{
		String randomstr= RandomStringUtils.randomAlphabetic(5);
		String randomNum= RandomStringUtils.randomNumeric(9);
		return (randomstr+"@"+randomNum);
	}
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		java.io.File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		java.io.File targetFile=new java.io.File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
				
	}

	}
