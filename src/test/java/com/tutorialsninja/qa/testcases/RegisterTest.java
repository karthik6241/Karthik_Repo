package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.pages.AccountSuccessPage;
import com.tutorialsNinja.pages.HomePage;
import com.tutorialsNinja.pages.RegisterPage;
import com.tutorialsNinja.utils.Utilities;
import com.tutorialsninja.qa.base.Base;

public class RegisterTest extends Base {
	
	RegisterPage registerpage;
	AccountSuccessPage accountsuccesspage;
	
	public RegisterTest() {
		super();
	}
	
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		registerpage=homepage.navigateToRegisterPage();
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}

	
	@Test(priority=1)
	public void VerifyRegisteringanAccountWithMandatoryfields() {
		
		accountsuccesspage=registerpage.registerWithMandatoryFields(dataprop.getProperty("firstName"),dataprop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("telephoneNumber"), prop.getProperty("VaildPassword"));
		Assert.assertEquals(accountsuccesspage.retrieveAccountSuccessPageHeading(), dataprop.getProperty("accountSuccessfullycreatedHeading"),"Account Success Page is Not Displayed");
		
		
	}
	
	@Test(priority=2)
	public void VerifyRegisteringanAccountbyprovidingallthefields() {
		
		accountsuccesspage = registerpage.registerWithAllFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("telephoneNumber"),prop.getProperty("VaildPassword"));
		Assert.assertEquals(accountsuccesspage.retrieveAccountSuccessPageHeading(), dataprop.getProperty("accountSuccessfullycreatedHeading"),"Account Success Page is Not Displayed");
		

	}
	
	@Test(priority=3)
	public void VerifyRegisteringanAccountWithExistingEmailAddress() {
		
		
		accountsuccesspage = registerpage.registerWithAllFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"),prop.getProperty("ValidEmail"), dataprop.getProperty("telephoneNumber"),prop.getProperty("VaildPassword"));
		Assert.assertTrue(registerpage.retrieveDuplicateEmailAddressWarning().contains(dataprop.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate is not displayed");
		
	
	}
	
	@Test(priority=4)
	public void VerifyRegisteringanAccountWithoutFillingAnyDetails() {
		
		
		
		registerpage.clickOnContinueButton();
		Assert.assertTrue(registerpage.displayStatusOfWarningMessages(dataprop.getProperty("privacyPolicyWarning"),dataprop.getProperty("firstNameWarning"),dataprop.getProperty("lastNameWarning"),dataprop.getProperty("emailWarning"),dataprop.getProperty("telephoneWarning"),dataprop.getProperty("passwordWarning")));		
		
		
	}
}
