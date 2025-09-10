package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectrepository.Locators;
import com.parameters.Reporter;

public class SelectionPage extends Searchingpage {

    public SelectionPage(WebDriver driver, ExtentTest extTest) {
        super(driver, extTest);   // call Searchingpage constructor
    }

    public boolean loadPage() {
      
        
        selectRoundTrip();
        enterBoardingPlace("chennai");
        enterLandingPlace("Mumbai");
        handlePopupIfExists();
        setTravellersAndClass(1, 0, 1, "Economy");
        clickSearch();
      

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.resultsContainer));
            Reporter.generateReport(driver, extTest, Status.PASS, "The page has been landed");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to load page");
            return false;
        }
    }

    // ✅ Select filter dynamically based on input
    public boolean applyFilter(String filterName) {
    	handlePriceLockPopup();
        try {
            By filterLocator = By.xpath("//label[contains(normalize-space(),'" + filterName + "')]");
            WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(filterLocator));
            filterElement.click();
            Reporter.generateReport(driver, extTest, Status.PASS, "Filter selected: " + filterName);
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to select filter: " + filterName + " | " + e.getMessage());
            return false;
        }
    }

    // ✅ Handle price lock popup if it appears
    public void handlePriceLockPopup() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	try {
    	    // Wait for the "Okay, Got It!" button to be visible and clickable
    	    WebElement okayButton = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//button[contains(text(),'Okay, Got It!')]")
    	    ));
    	    okayButton.click(); // Dismiss the Price Lock popup
    	} catch (TimeoutException e) {
    	    // Button not found -- the popup may not have appeared, continue test execution
    	}

    }
}
