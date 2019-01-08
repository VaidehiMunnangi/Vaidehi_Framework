package designPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class AmazonPOM2Style {

	private WebElement SearchBox;
	private WebElement SearchDropDown;
	private WebElement SearchButton;
	private WebElement getResult;
	
	ElementControl elementControl;
	DropdownControl dropdownControl;

	public AmazonPOM2Style(WebDriver driver) {
		SearchDropDown = driver.findElement(By.id("searchDropdownBox"));
		SearchBox = driver.findElement(By.id("twotabsearchtextbox"));
		SearchButton = driver.findElement(By.xpath("//input[@value='Go']"));
		getResult = driver.findElement(By.id("s-result-count"));
	}
	
	public void searchProduct(String category,String product) throws Exception {

		elementControl = new ElementControl();
		dropdownControl = new DropdownControl();
		
		dropdownControl.selectViaVisibleText(SearchDropDown, category);
		elementControl.setText(SearchBox, product);
		elementControl.clickElement(SearchButton);
		
		System.out.println(elementControl.getText(getResult));
		
	}
}
