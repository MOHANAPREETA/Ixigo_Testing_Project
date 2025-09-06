package com.stepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Booking {
	
	WebDriver driver = Hooks.driver;
	ExtentTest extTest = Hooks.extTest;
	
	@Given("the user in on the login page")
	public void the_user_in_on_the_login_page() {
		String expRes = "https://www.ixigo.com/";
		String actRes = driver.getCurrentUrl();
		Assert.assertEquals(actRes, expRes);
		System.out.println("The website has been launched");
	}
	@When("the user attemts to enter inavlid mobileno as {string}")
	public void the_user_attemts_to_enter_inavlid_mobileno_as(String string) {
	    
	}
	@When("the user clicks the login button")
	public void the_user_clicks_the_login_button() {
	   
	}
	@Then("the system should display {string}")
	public void the_system_should_display(String string) {
	  
	}

	
	@Given("the is on the login page")
	public void the_is_on_the_login_page() {
	    
	}
	@When("the user enters mobileno as {string}")
	public void the_user_enters_mobileno_as(String string) {
	   
	}
	@When("enters the correct OTP")
	public void enters_the_correct_otp() {
	   
	}
	@Then("user should be navigated to the booking page")
	public void user_should_be_navigated_to_the_booking_page() {
	   
	}


	@Given("the user enters {string}")
	public void the_user_enters(String string) {
	   
	}
	@When("the page is loaded")
	public void the_page_is_loaded() {
	  
	}
	@Then("the website should show error {string}")
	public void the_website_should_show_error(String string) {
	   
	}


}
