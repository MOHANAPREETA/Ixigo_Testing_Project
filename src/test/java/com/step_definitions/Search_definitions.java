package com.step_definitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.Searching_page;
import com.setup.Base;
import com.utils.TestDataContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Search_definitions {

	WebDriver driver = Hooks.driver;
	ExtentTest extTest = Hooks.extTest;
	Searching_page search;

	@Given("the user is on the homepage")
	public void the_user_is_on_the_homepage() {
		// Hooks setup already launched the site
		search = new Searching_page(driver, extTest);

		// Optionally validate URL
		String currentUrl = Base.driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("ixigo.com"), "❌ Unexpected homepage URL: " + currentUrl);
	}

	@When("the user selects {string} trip type")
	public void the_user_selects_trip_type(String tripType) {
		if (tripType.equalsIgnoreCase("Round Trip")) {
			boolean res = search.selectRoundTrip();
			Assert.assertTrue(res, "Round trip is selected");

		} else {
			throw new IllegalArgumentException("❌ Unsupported trip type: " + tripType);
		}

	}

	@When("the user enters origin as {string}")
	public void the_user_enters_origin_as(String from) {
		int row = TestDataContext.currentRow;
		from = TestDataContext.excelData[row][5];
		boolean res = search.enterBoardingPlace(from);
		Assert.assertTrue(res, "Boarding place is entered");

	}

	@When("the user enters destination as {string}")
	public void the_user_enters_destination_as(String to) {
		int row = TestDataContext.currentRow;
		to = TestDataContext.excelData[row][6];
		boolean res = search.enterLandingPlace(to);
		Assert.assertTrue(res, "Destination  place is entered");
		boolean act = search.enteringdate("21-09-2025", "25-09-2025");
		Assert.assertTrue(res, "Departuer and return dates are entered");

	}

	@When("the user sets travellers as {string} adults, {string} children, {string} infants and class as {string}")
	public void the_user_sets_travellers_and_class(String adults, String children, String infants, String travelClass) {
		int row = TestDataContext.currentRow;
		adults = TestDataContext.excelData[row][7];
		children = TestDataContext.excelData[row][8];
		infants = TestDataContext.excelData[row][9];
		travelClass = TestDataContext.excelData[row][10];
		int a = Integer.parseInt(adults.trim());
		int c = Integer.parseInt(children.trim());
		int i = Integer.parseInt(infants.trim());
		boolean res = search.setTravellersAndClass(a, c, i, travelClass);
		Assert.assertTrue(res, "Travellers and class is entered");
	}

	@When("the user clicks Search")
	public void the_user_clicks_search() {
		boolean res = search.clickSearch();
		Assert.assertTrue(res, "search has been selected");
	}

	@Then("search results should be displayed")
	public void search_results_should_be_displayed() {
		boolean res = search.areResultsDisplayed();
		Assert.assertTrue(res, "❌ Expected search results to be displayed");
	}
}
