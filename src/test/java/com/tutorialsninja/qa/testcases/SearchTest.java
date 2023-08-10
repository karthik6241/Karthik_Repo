package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.pages.HomePage;
import com.tutorialsNinja.pages.SearchPage;
import com.tutorialsninja.qa.base.Base;

public class SearchTest extends Base {
	
	SearchPage searchpage;
	HomePage homepage;
	
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homepage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
	
	
		searchpage = homepage.searchForAProduct(dataprop.getProperty("validProduct"));
		
		Assert.assertTrue(searchpage.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in the search results");
	}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct() {
		
		
		searchpage = homepage.searchForAProduct(dataprop.getProperty("invalidProduct"));
		Assert.assertEquals(searchpage.retrieveNoProductMessageText(),"abcd","No product message in search results is not displayed");
		
	}
	
	@Test(priority=3,dependsOnMethods={"verifySearchWithValidProduct","verifySearchWithInValidProduct"})
	public void verifySearchWithoutAnyProduct() {
		
		searchpage=homepage.clickOnSearchButton();
		Assert.assertEquals(searchpage.retrieveNoProductMessageText(), dataprop.getProperty("noProductSearchInSearchResults"), "No Product in search results is not displayed");
		
	}
	

}
