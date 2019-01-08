package commonLibs.implementation;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import commonLibs.contracts.IScreenshots;

public class ScreenshotControl implements IScreenshots{

	WebDriver driver;
	public ScreenshotControl(WebDriver driver) {
		this.driver = driver;
	}
	@Override
	public String captureAndSaveScreenshot(String FileName) throws Exception {
		FileName = FileName.trim();
		File ImgFile,TempFile;
		ImgFile = new File(FileName);
		if(ImgFile.exists())		
			throw new Exception("File already Exists.....!");
		TakesScreenshot camera = (TakesScreenshot)driver;
		TempFile = camera.getScreenshotAs(OutputType.FILE);
		FileUtils.moveFile(TempFile, ImgFile);
		return ImgFile.getAbsolutePath();
	}
	
}
