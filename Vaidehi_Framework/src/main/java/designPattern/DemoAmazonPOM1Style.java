package designPattern;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class DemoAmazonPOM1Style {

	CommonDriver cmndriver;
	ElementControl elementControl;
	DropdownControl dropdownControl;
	AmazonPOM1Style homepage;
	WebDriver driver;
	
	@BeforeClass
	public void invokeBrowser() throws Exception {
		
		cmndriver = new CommonDriver("chrome");
		cmndriver.setPageloadTimeout(40);
		cmndriver.setElementDetectionTimeout(10);
		cmndriver.navigateToFirstUrl("https://www.amazon.com/");
		driver = cmndriver.getDriver();
		
		elementControl = new ElementControl();
		dropdownControl = new DropdownControl();
		homepage = new AmazonPOM1Style(driver);
	}
	
	@Test
	public void verifySearchProduct() throws Exception {
		
		dropdownControl.selectViaVisibleText(homepage.SearchDropDown, "Books");
		elementControl.setText(homepage.SearchBox, "Selenium");
		elementControl.clickElement(homepage.SearchButton);
		
	}
	
	@AfterClass
	public void closeBrowser() throws Exception {
		cmndriver.closeAllBrowsers();
	}
}
