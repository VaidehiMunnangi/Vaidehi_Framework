package com.edureka.testcases;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.MercuryTravel.pages.HomePage;
import com.MercuryTravel.utils.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;
import commonLibs.utilities.DateUtils;

public class TestSetup {

	CommonDriver cmnDriver;
	WebDriver driver;
	HomePage homepage;
	Properties configProperty;
	String configFilePath;
	String currentWorkingDirectory;

	String executionStartTime;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest extentTest;
	
	ScreenshotControl screenshot;

	@BeforeSuite
	public void preSetup() throws IOException {

		initializeCurrentWorkingDirectory();

		readingConfigFile();

		executionStartTime();

		initializeReports();

	}

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		extentTest = extent.createTest("Test SetUP: Invoking Browser");
		invokeBrowser();
		
		initializeScreenshot();
		extentTest.log(Status.INFO, "Initializing pages");
		initializePages();
	}

	private void initializeScreenshot() {

		screenshot  = new ScreenshotControl(driver);
		
	}

	@AfterMethod
	public void afterAMethod(ITestResult result) throws Exception {
		
		String tcName = result.getName();
		if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "TestCase Passed: " + tcName );
		}else if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, "TestCase Failed: " + tcName );
			captureTheScreenshotAndAttatchItToReport(tcName);
		}else {
			extentTest.log(Status.SKIP, "TestCase Skipped: " + tcName );
		}
			
	}
	
	private void captureTheScreenshotAndAttatchItToReport(String tcName) throws Exception {
		
		String fileName = String.format("%s/screenshots/%s_%s.jpeg", currentWorkingDirectory,tcName,executionStartTime);
		screenshot.captureAndSaveScreenshot(fileName);
		extentTest.addScreenCaptureFromPath(fileName);
	}

	@AfterClass(alwaysRun = true)
	public void cleanup() throws Exception {
		
		extentTest = extent.createTest("Test CleanUP: Closing Browser");
		cmnDriver.closeAllBrowsers();
	}

	@AfterSuite
	public void postCleanUp() {
		extent.flush();
	}

	private void initializeCurrentWorkingDirectory() {

		currentWorkingDirectory = System.getProperty("user.dir");
	}

	private void readingConfigFile() throws IOException {

		configFilePath = String.format("%s/config/config.properties", currentWorkingDirectory);
		configProperty = ConfigReader.readFromConfigFile(configFilePath);
	}

	private void executionStartTime() {

		executionStartTime = DateUtils.getDate();
	}

	private void initializeReports() {
		String file = String.format("%s/reports/MercuryTravel_%s.html", currentWorkingDirectory, executionStartTime);
		htmlReporter = new ExtentHtmlReporter(file);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	private void initializePages() {
		
		extentTest.log(Status.INFO, "Initializing home page");
		homepage = new HomePage(driver);
	}

	private void invokeBrowser() throws Exception {

		extentTest.log(Status.INFO, "Initializing chrome browser");
		cmnDriver = new CommonDriver(configProperty.getProperty("browserType"));
		
		extentTest.log(Status.INFO, "Initializing page load timeout");
		int pageLoadTime = Integer.parseInt(configProperty.getProperty("pageLoadTimeout"));
		cmnDriver.setPageloadTimeout(pageLoadTime);
		
		extentTest.log(Status.INFO, "Initializing element detection timeout");
		int elementDetectionTime = Integer.parseInt(configProperty.getProperty("elementDetectionTimeout"));
		cmnDriver.setElementDetectionTimeout(elementDetectionTime);
		
		extentTest.log(Status.INFO, "Navigating to the path");
		cmnDriver.navigateToFirstUrl(configProperty.getProperty("path"));
		
		driver = cmnDriver.getDriver();
	}

}
