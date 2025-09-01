package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.de.test.BaseDeTest;



public class LoginTest extends BaseDeTest {
		
	@Test(priority = 1)

	public void failedLoginTest() {
		loginPage.enterUsername(configReader.remplirUserName());
		loginPage.enterPassword(configReader.remplirPassword());
		loginPage.clickbuttonLogin();
		loginPage.waitforerrormsg(configReader.geterrormsg());
		Assert.assertTrue(eleutill.isDisplayed(loginPage.errorMessage), "Error message not displayed!");
	}
	
	

}
