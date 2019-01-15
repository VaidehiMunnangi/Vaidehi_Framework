package com.MercuryTravel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BaseInitialization{

	@FindBy(linkText = "International Holidays")
	private WebElement internationHolidayLink;

	@FindBy(linkText = "Indian Holidays")
	private WebElement indianHolidayLink;

	@FindBy(linkText = "Flights")
	private WebElement flightLink;

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	// TO DO: Please update other links

	@FindBy(linkText = "Customer Login")
	private WebElement customerLogin;

	@FindBy(linkText = "User Login")
	private WebElement userLogin;

	@FindBy(linkText = "Register")
	private WebElement RegisterLink;

	@FindBy(id = "sign_user_email")
	private WebElement userEmailId;

	@FindBy(id = "sign_user_password")
	private WebElement userPassword;

	@FindBy(xpath = "//div[@id='modalLogin']//form[@class='form-signin']//button")
	private WebElement loginButton;

	@FindBy(partialLinkText = "Welcome,")
	private WebElement welcomeText;
	
	public HomePage(WebDriver driver) {
		
		super(driver);
		PageFactory.initElements(driver, this);
		
	}
	
	public void userLogin(String sUserID,String sUserPassword,WebDriver driver) throws Exception{
		
		explicitWait(customerLogin,driver);
		mouseControl.moveToElement(customerLogin);
		elementControl.clickElement(userLogin);
		Thread.sleep(2000);
		elementControl.setText(userEmailId, sUserID);
		elementControl.setText(userPassword, sUserPassword);
		elementControl.clickElement(loginButton);		
	}
	
	private void explicitWait(WebElement customerLogin,WebDriver driver) {
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		customerLogin = wait.until(ExpectedConditions.elementToBeClickable(customerLogin));
		
	}

	public String getWelcomeText() throws Exception {
		
		return elementControl.getText(welcomeText);
		
	}

}
