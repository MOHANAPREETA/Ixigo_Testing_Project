package com.step_definitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.Selection_Page;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Selection_definitions {

	WebDriver driver = Hooks.driver;
	ExtentTest extTest = Hooks.extTest;
	Selection_Page select;

	@Given("the user is on the selection page")
	public void the_user_is_on_the_selection_page() {
		select = new Selection_Page(driver, extTest);
		boolean actRes = select.loadPage();
		Assert.assertTrue(actRes, "❌ Failed to load selection page");

	}

	@Given("the user clicks first filter departure as {string}")
	public void the_user_clicks_first_filter_departure_as(String departureFilter) {
		// For now, only Morning is supported
		boolean actRes = select.applyDepartureMorning();
		Assert.assertTrue(actRes, "❌ Failed to apply departure filter: " + departureFilter);

	}

	@Given("the user clicks second filter arrival as {string}")
	public void the_user_clicks_second_filter_arrival_as(String arrivalFilter) {
		// For now, only Morning is supported
		boolean actRes = select.applyArrivalMorning();
		Assert.assertTrue(actRes, "❌ Failed to apply arrival filter: " + arrivalFilter);

	}

	@Then("select the first available flight")
	public void select_the_first_available_flight() {
		boolean actRes = select.selectFirstAvailableFlight();
		Assert.assertTrue(actRes, "❌ Failed to select first available flight");

	}
}
