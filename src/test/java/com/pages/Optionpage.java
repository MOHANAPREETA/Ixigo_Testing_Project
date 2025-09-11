package com.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.objectrepository.Locators;
import com.parameters.Reporter;

public class Optionpage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extTest;

    public Optionpage(WebDriver driver, ExtentTest extTest) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.extTest = extTest;
    }

    public boolean selectingOffer() {
        driver.findElement(Locators.offer).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id=\"__next\"]/div[1]/div[3]/div[1]/div[1]/div/div[1]/a")));
            Reporter.generateReport(driver, extTest, Status.PASS, "Offer page is opened");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to open offer page");
            return false;
        }
    }

    public boolean selectingDropdown() {
        String parentWindowHandle = driver.getWindowHandle();
        try {
            WebElement moreDropdown = wait.until(ExpectedConditions.elementToBeClickable(Locators.more));
            moreDropdown.click();

            WebElement planItem = wait.until(ExpectedConditions.elementToBeClickable(Locators.plan));
            planItem.click();

            Set<String> allWindowHandles = driver.getWindowHandles();
            for (String handle : allWindowHandles) {
                if (!handle.equals(parentWindowHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Manali']")));
            Reporter.generateReport(driver, extTest, Status.PASS, "Plan page is opened successfully in a new window.");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL,
                    "Failed to open plan page or switch to new window. Exception: " + e.getMessage());
            return false;
        }
    }

    public boolean choosingPlan() {
        driver.findElement(Locators.trip).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Aluva']")));
            Reporter.generateReport(driver, extTest, Status.PASS, "Plan page is opened");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to open plan page");
            return false;
        }
    }

    public void enteringFrom(String from) {
        driver.findElement(Locators.dropdown).click();
        driver.findElement(Locators.search_locate).sendKeys(from);

        wait.until(ExpectedConditions.elementToBeClickable(Locators.first_search));
        driver.findElement(Locators.first_search).click();
    }

    public boolean enteringWhere(String where) {
        driver.findElement(Locators.search_where).click();
        driver.findElement(Locators.search_locate).sendKeys(where);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'overflow-auto')]/a[1]")));
        driver.findElement(Locators.loca).click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/a[1]")));
            Reporter.generateReport(driver, extTest, Status.PASS, "Location dialogue box is opened");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to open location dialogue box");
            return false;
        }
    }

    public boolean choosingHotel() {
        try {
            driver.findElement(Locators.hotel).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.view_all));
            Reporter.generateReport(driver, extTest, Status.PASS, "Hotels option is displayed");
            return true;
        } catch (Exception e) {
            Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to display Hotels option");
            return false;
        }
    }

    public void clickingHotel() {
        driver.findElement(Locators.view_all).click();
    }
}
