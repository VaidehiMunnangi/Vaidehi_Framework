package com.edureka.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class HomePageTests extends TestSetup{

	@Test
	public void verifyUserLoginToMercuryTravel() throws Exception {
		
		extentTest = extent.createTest("TC1: Verify user login to the application");
		extentTest.log(Status.INFO, "Verify user login");
		String userID = configProperty.getProperty("userID");
		String pwd = configProperty.getProperty("pwd");
		homepage.userLogin(userID, pwd, driver);
		
		extentTest.log(Status.INFO, "Validating the welcome text");
		String expectedText = configProperty.getProperty("welcomeText");
		String actualText = homepage.getWelcomeText();
		Assert.assertEquals(actualText, expectedText);
		extentTest.log(Status.INFO, "Actual and Expected text matched");
	}
}
