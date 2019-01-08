package designPattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.ElementControl;

public class AmazonPageFactoryStyle {
	
	@CacheLookup
	@FindBy(id="twotabsearchtextbox")	
	private WebElement SearchBox;
	
	@CacheLookup
	@FindBy(id="searchDropdownBox")
	private WebElement SearchDropDown;
	
	@CacheLookup
	@FindBy(xpath="//input[@value='Go']")
	private WebElement SearchButton;
	
	@FindBy(id="s-result-count")
	private WebElement getResult;
	
	ElementControl elementControl;
	DropdownControl dropdownControl;

	public AmazonPageFactoryStyle(WebDriver driver) {
		PageFactory.initElements(driver, this);
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
