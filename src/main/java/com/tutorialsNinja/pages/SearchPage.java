package com.tutorialsNinja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;

	@FindBy(linkText="HP LP3065")
	private WebElement validHPProduct;
	
	
	@FindBy(xpath="//*[@id=\"content\"]/p[2]")
	private WebElement noProductMessage;
	
	
	
	public SearchPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusOfHPValidProduct() {
		
		boolean displaystatus=validHPProduct.isDisplayed();
		return displaystatus;
	}
	
	public String retrieveNoProductMessageText() {
		
		String noProductMessageText = noProductMessage.getText();
		return noProductMessageText;
	
	}
}

