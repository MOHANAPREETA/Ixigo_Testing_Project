package com.stepDefinition;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.pages.Searchingpage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class searchingdefinitions {
	
	WebDriver driver = Hooks.driver;
    ExtentTest extTest = Hooks.extTest;
    Searchingpage search;
    
	@Given("the user selects the from as {string} and to as {string}")
	public void the_user_selects_the_from_as_and_to_as(String string, String string2) {
		
	    
	}

	@Given("the user selects the departure as {string} and return as {string}")
	public void the_user_selects_the_departure_as_and_return_as(String string, String string2) {
	   
	}

	@Given("the user clicks the travellars icon")
	public void the_user_clicks_the_travellars_icon() {
	    
	}

	@Given("the user selects the special fare")
	public void the_user_selects_the_special_fare() {
	   
	}

	@Then("the user clicks the search button")
	public void the_user_clicks_the_search_button() {
	    
	}


}
