package designPattern;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;

public class DemoAmazonPageFactoryStyle {

	CommonDriver cmndriver;
	AmazonPageFactoryStyle homepage;
	WebDriver driver;
	
	@BeforeClass
	public void invokeBrowser() throws Exception {
		
		cmndriver = new CommonDriver("chrome");
		cmndriver.setPageloadTimeout(40);
		cmndriver.setElementDetectionTimeout(10);
		cmndriver.navigateToFirstUrl("https://www.amazon.com/");
		driver = cmndriver.getDriver();
		homepage = new AmazonPageFactoryStyle(driver);
	}
	
	@Test
	public void verifySearchProduct() throws Exception {

		homepage.searchProduct("Books", "Selenium");				
	}
	
	@AfterClass
	public void closeBrowser() throws Exception {
		cmndriver.closeAllBrowsers();
	}
}
