package com.pages;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.objectrepository.Locators;
import com.parameters.Reporter;

public class Login_page {

	WebDriver driver;
	WebDriverWait wait;
	ExtentTest extTest;

	public Login_page(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		this.extTest = extTest;
	}

	/** Click on Login button */
	public void clickLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in/Sign up']"))).click();
	}

	/** Enter mobile number into login field */
	public boolean enterMobileNumber(String mobile) {
		clickLoginButton();
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Mobile Number']")))
					.sendKeys(mobile);
			Reporter.generateReport(driver, extTest, Status.PASS, "Mobile number entered");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter mobile number");
			return false;
		}
	}

	/** Click Continue after entering mobile number */
	public boolean clickContinueForMobile() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']"))).click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Continue for login");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Please enter a valid number");

			return false;
		}
	}

	/** Manually enter OTP from console */
	public boolean enterOtpManually() {
		try {
			System.out.print("Enter OTP from SMS: ");
			Scanner sc = new Scanner(System.in);
			String otp = sc.nextLine();
			sc.close();

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[@type='tel' or @inputmode='numeric']")))
					.sendKeys(otp);
			Reporter.generateReport(driver, extTest, Status.PASS, "OTP entered");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter OTP");
			return false;
		}
	}

	/** Verify that the user navigated to flights page */
	public boolean navigatedpage() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='One Way']")));
			Reporter.generateReport(driver, extTest, Status.PASS, "Flight details displayed");
			return true;
		} catch (TimeoutException te) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Navigation failed");
			return false;
		}
	}

	/** Enter an invalid number for negative testing */
	public boolean invalidnumber(String number) {
		try {
			clickLoginButton();
			WebElement mobileInput = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Mobile Number']")));
			mobileInput.clear();
			mobileInput.sendKeys(number);
			Reporter.generateReport(driver, extTest, Status.PASS, "Number is invalid");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to enter mobile number");
			return false;
		}
	}

	/** Verify error message for invalid phone number */
	public boolean verifyErrorMessage(String expectedMessage) {
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[text()='Please enter a valid phone number']")));
			Reporter.generateReport(driver, extTest, Status.PASS, "Please enter a valid phone number");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
