package designPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonPOM1Style {

	public WebElement SearchBox;
	public WebElement SearchDropDown;
	public WebElement SearchButton;

	public AmazonPOM1Style(WebDriver driver) {
		SearchDropDown = driver.findElement(By.id("searchDropdownBox"));
		SearchBox = driver.findElement(By.id("twotabsearchtextbox"));
		SearchButton = driver.findElement(By.xpath("//input[@value='Go']"));
	}
}
