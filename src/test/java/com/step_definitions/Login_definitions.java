package com.step_definitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.Login_page;
import com.setup.Base;
import com.utils.TestDataContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_definitions {

	WebDriver driver = Hooks.driver;
	ExtentTest extTest = Hooks.extTest;
	Login_page loginPage;

	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		// ✅ Initialize page object
		loginPage = new Login_page(driver, extTest);

		String expectedUrl = "https://www.ixigo.com/";
		String actualUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl, "User is not on Ixigo login page!");
		System.out.println("✅ The website has been launched successfully");
	}

	// --------- Negative flow ---------
	@When("the user enters invalid mobileno as {string}")
	public void the_user_enters_invalid_mobileno_as(String invalidMobile) {
		int row = TestDataContext.currentRow;
		invalidMobile = TestDataContext.excelData[row][3];
		boolean res = loginPage.invalidnumber(invalidMobile);
		Assert.assertTrue(res);
	}

	@When("the user clicks the continue button")
	public void the_user_clicks_the_continue_button() {
		loginPage.clickContinueForMobile();
	}

	@Then("the system should display {string}")
	public void the_system_should_display(String expectedMessage) {
		int row = TestDataContext.currentRow;
		expectedMessage = TestDataContext.excelData[row][4];
		boolean res = loginPage.verifyErrorMessage(expectedMessage);
		Assert.assertTrue(res);
	}

	// --------- Positive flow ---------
	@When("the user enters mobileno as {string}")
	public void the_user_enters_mobileno_as(String mobileNo) {
		int row = TestDataContext.currentRow;
		mobileNo = TestDataContext.excelData[row][2];
		boolean actRes = loginPage.enterMobileNumber(mobileNo);
		Assert.assertTrue(actRes, "Failed to enter mobile number!");

		loginPage.clickContinueForMobile();
	}

	@When("enters the correct OTP")
	public void enters_the_correct_otp() {
		Assert.assertTrue(loginPage.enterOtpManually(), "Failed to enter OTP");
		Base.sleep();
	}

	@Then("the user should be navigated to the booking page")
	public void the_user_should_be_navigated_to_the_booking_page() {
		Assert.assertTrue(loginPage.navigatedpage(), "User not navigated to booking page");
	}
}
