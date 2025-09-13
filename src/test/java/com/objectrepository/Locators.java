package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {
	
	 // To/From inputs
    public static By tobtn = By.xpath("//input[@placeholder='To']");
    public static By toPlace = By.xpath("//input[@placeholder='To']");

    // Departure button (corrected XPath)
    public static By deptBtn = By.xpath("//input[@placeholder='Depart']");


    // Calendar navigation
    public static By monthYear = By.xpath("//span[contains(@class,'rd-month-label')]");
    public static By nextbtn = By.xpath("//button[contains(@class,'rd-next')]");

    // Travellers section
    public static By travellersPanel = By.xpath("//div[contains(@class,'passenger-class-input')]");

    public static By adultsPlusBtn = By.xpath("//button[contains(@class,'adults') and contains(@class,'plus')]");
    public static By childrenPlusBtn = By.xpath("//button[contains(@class,'children') and contains(@class,'plus')]");
    public static By infantsPlusBtn = By.xpath("//button[contains(@class,'infants') and contains(@class,'plus')]");

    public static By travelClassDropdown = By.xpath("//div[contains(@class,'travelClass')]");
    public static By travellersApplyBtn = By.xpath("//button[contains(text(),'Apply')]");

    // Dynamic locator for travel class option
    public static By travelClassOption(String cls) {
        return By.xpath("//li[normalize-space()='" + cls + "'] | //div[contains(@class,'option') and normalize-space()='" + cls + "']");}

//------------------------------------------------------------------------------
	   
	
}
