package com.stepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pages.Optionpage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OffersDefinitions {

    WebDriver driver = Hooks.driver;
    ExtentTest extTest = Hooks.extTest;
    Optionpage optionpage;

    static String[][] excelData;

    @Given("the user clicks the offer icon")
    public void the_user_clicks_the_offer_icon() {
        optionpage = new Optionpage(driver, extTest);
        boolean actRes = optionpage.selectingOffer();
        Assert.assertTrue(actRes, "❌ Failed to open offer page");
    }

    @When("the user selects the dropdown More option")
    public void the_user_selects_the_dropdown_more_option() {
        boolean actRes = optionpage.selectingDropdown();
        Assert.assertTrue(actRes, "❌ Failed to open More dropdown or Plan page");
    }

    @When("selects tripType trip type")
    public void selects_trip_type_trip_type() {
        boolean actRes = optionpage.choosingPlan();
        Assert.assertTrue(actRes, "❌ Failed to choose trip type");
    }

    @When("selects from as {string}")
    public void selects_from_as(String from) {
        int row = Hooks.currentrow;
        from = Hooks.excelData[row][0];
        optionpage.enteringFrom(from);
    }

    @When("gives the location where as {string}")
    public void gives_the_location_where_as(String where) {
        int row = Hooks.currentrow;
        where = Hooks.excelData[row][1];
        boolean actRes = optionpage.enteringWhere(where);
        Assert.assertTrue(actRes, "❌ Failed to enter location");
    }

    @Then("user selects the things to do option")
    public void user_selects_the_things_to_do_option() {
        boolean actRes = optionpage.choosingHotel();
        Assert.assertTrue(actRes, "❌ Failed to display Hotels option");
        optionpage.clickingHotel();
    }
}
