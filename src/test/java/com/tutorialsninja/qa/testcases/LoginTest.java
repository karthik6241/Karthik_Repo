package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsNinja.pages.AccountPage;
import com.tutorialsNinja.pages.HomePage;
import com.tutorialsNinja.pages.LoginPage;
import com.tutorialsNinja.utils.Utilities;
import com.tutorialsninja.qa.base.Base;

public class LoginTest extends Base {
	
	LoginPage loginpage;
	
	public LoginTest() {
		super();
	}
	
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		loginpage=homepage.navigateToLoginPage();
		
	}
	
	@AfterMethod
	public void teardown() {
		

		driver.quit();
	}

	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void VerifyLoginWithValidCredentials(String email, String password){
		

		AccountPage accountpage = loginpage.login(email, password);
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit Your Account Information option is not displayed");
		
		
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData(){
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
		
	}
	
	@Test(priority=2)
	public void VerifyLoginWithInvalidCredentials() {
		
		
		loginpage.login(Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected warning message is not displayed");
		
		
	}
	
	
	@Test(priority=3)
	public void VerifyLoginWithInvalidEmailAddress() {
		
		
		loginpage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("VaildPassword"));
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected warning message is not displayed");
		
		
	}
	
	@Test(priority=4)
	public void VerifyLoginWithInvalidPassword() {
		
		loginpage.login(prop.getProperty("ValidEmail"),dataprop.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected warning message is not displayed");
		
		
	}
	
	@Test(priority=5)
	public void VerifyLoginWithoutCredentials() {
			
		
		
		loginpage.cliclkOnLoginButton();
		
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected warning message is not displayed");
		
	}
	
	
}
