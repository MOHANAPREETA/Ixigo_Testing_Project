package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {
	public static By loginbutton=By.xpath("//button[text()='Log in/Sign up']");
    public static By mobile=By.xpath("//input[@placeholder='Enter Mobile Number']");
    public static By continuebutton=By.xpath("//button[text()='Continue']");
    public static By otpInputs=By.xpath("//input[@type='tel' or @inputmode='numeric']");
    public static By verify = By.xpath("//*[text()='Verify' or normalize-space()='Verify']");
    public static By flight = By.xpath("//a[@href='/flights']");
    public static By round = By.xpath("//button[text()='Round Trip']");	
    public static By from = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div");
    public static By click_from = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input");
    public static By to = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div");
    public static By click_to = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input");
    public static By departure_btn = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div/div/div/p[2]");
    public static By return_btn = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/div[1]/div");
    public static By dep_month = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[1]/button[2]/span[1]");
    public static By dep_year = By.xpath("//span[text()='2025']");
    public static By searchButton = By.xpath("//button[normalize-space()='Search' or contains(@class,'search')]");
    public static By calendarNextBtn = By.xpath("//button[contains(@aria-label,'Next') or contains(@class,'next')]");
    public static By calendarMonthYear = By.xpath("//div[contains(@class,'month') or contains(@class,'Month') or contains(@class,'calendarHeader')]");
    public static By travellersPanel = By.xpath("//div[contains(text(),'Travellers') or contains(@class,'traveller') or contains(@class,'passenger')]");
    public static By adultsPlusBtn = By.xpath("(//button[contains(.,'+')])[1]");
    public static By childrenPlusBtn = By.xpath("(//button[contains(.,'+')])[2]");
    public static By infantsPlusBtn = By.xpath("(//button[contains(.,'+')])[3]");
    public static By travellersApplyBtn = By.xpath("//button[normalize-space()='Apply' or normalize-space()='Done']");

    public static By travelClassDropdown = By.xpath("//div[contains(@class,'cabin') or contains(.,'Class') or contains(@data-testid,'cabin')]");
    // results container generic
    public static By resultsContainer = By.xpath("//div[contains(@class,'listing') or contains(@class,'results') or contains(@id,'results') or //h1[contains(.,'Flights')]]");

    // helper dynamic locators
    public static By dayCell(String day) {
        // looks for day cells represented by <p>, <div> or <span> with exact visible text equal to day.
        String xpath1 = "//p[normalize-space()='" + day + "']";
        String xpath2 = "//div[normalize-space()='" + day + "']";
        String xpath3 = "//span[normalize-space()='" + day + "']";
        return By.xpath(xpath1 + " | " + xpath2 + " | " + xpath3);
    }
    public static By travelClassOption(String cls) {
        String xpath = String.format(
            "//li[normalize-space(.)='%s'] | " +
            "//div[contains(@class,'option') and normalize-space(.)='%s'] | " +
            "//button[normalize-space(.)='%s']",
            cls, cls, cls
        );
        return By.xpath(xpath);
    }

}
