package com.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.parameters.Reporter;
import com.setup.Base;

public class Searching_page {

	WebDriver driver;
	WebDriverWait wait;
	ExtentTest extTest;
	Actions actions;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Searching_page(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(45));
		this.extTest = extTest;
		this.actions = new Actions(driver);
	}

	public void handlePopupIfExists() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement popupFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wiz-iframe-intent")));
			driver.switchTo().frame(popupFrame);
			WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("closeButton")));
			closeBtn.click();
			driver.switchTo().defaultContent();
			Reporter.generateReport(driver, extTest, Status.INFO, "Closed popup successfully");
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.INFO, "No popup to close or already closed");
		}
	}

	public void openFlightsTab() {
		try {
			handlePopupIfExists();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/flights']"))).click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Opened Flights tab");
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to open Flights tab: " + e.getMessage());
		}
	}

	public boolean selectRoundTrip() {
		try {
			handlePopupIfExists();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Round Trip']"))).click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Selected Round Trip");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Round Trip selection failed: " + e.getMessage());
			return false;
		}
	}

	public boolean enterBoardingPlace(String from) {
		try {
			handlePopupIfExists();
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div"))).click();
			driver.findElement(
					By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input"))
					.sendKeys(from);

			List<WebElement> results = new WebDriverWait(driver, Duration.ofSeconds(15))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
							.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]")));

			if (!results.isEmpty()) {
				results.get(0).click();
			} else {
				driver.findElement(By
						.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input"))
						.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			}
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"Failed to enter origin: " + from + " | " + e.getMessage());
			return false;
		}
	}

	public boolean enterLandingPlace(String to) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div"))).click();
			driver.findElement(
					By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input"))
					.sendKeys(to);

			List<WebElement> results = new WebDriverWait(driver, Duration.ofSeconds(15))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
							By.xpath("//span[@class='block truncate' and text()='" + to + "']")));

			if (!results.isEmpty()) {
				results.get(0).click();
			} else {
				Base.sleep();
				driver.findElement(By
						.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input"))
						.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			}
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL,
					"Failed to enter destination: " + to + " | " + e.getMessage());
			return false;
		}
	}

	public void enteringdate(String departure, String ret) {
		// Convert dd-MM-yyyy → MMMM d, yyyy
		DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter ariaFmt = DateTimeFormatter.ofPattern("MMMM d, yyyy");

		LocalDate depDate = LocalDate.parse(departure, inputFmt);
		LocalDate retDate = LocalDate.parse(ret, inputFmt);

		String depLabel = depDate.format(ariaFmt);
		String retLabel = retDate.format(ariaFmt);

		// Open calendar first
		driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div/div")).click();

		// Select departure date
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//abbr[@aria-label='" + depLabel + "']"))).click();

		// Select return date
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//abbr[@aria-label='" + retLabel + "']"))).click();

	}

	public boolean setTravellersAndClass(int aCount, int cCount, int iCount, String travelClass) {
		try {
			handlePopupIfExists();

			// Adults
			WebElement adultBtn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[.//p[normalize-space()='Adults']]//button[@data-testid='" + aCount + "']")));
			adultBtn.click();

			// Children (if > 0)
			if (cCount > 0) {
				WebElement childBtn = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[.//p[normalize-space()='Children']]//button[@data-testid='" + cCount + "']")));
				childBtn.click();
			}

			// Infants (if > 0)
			if (iCount > 0) {
				WebElement infantBtn = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[.//p[normalize-space()='Infants']]//button[@data-testid='" + iCount + "']")));
				infantBtn.click();
			}

			// Travel class (normalize to lowercase)
			WebElement classBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//span[translate(normalize-space(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='"
							+ travelClass.toLowerCase() + "']")));
			classBtn.click();

			// Done button
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Done']"))).click();

			Reporter.generateReport(driver, extTest, Status.PASS, "Travellers set: Adults=" + aCount + ", Children="
					+ cCount + ", Infants=" + iCount + ", Class=" + travelClass);
			return true;

		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to set travellers/class: " + e.getMessage());
			return false;
		}
	}

	public boolean clickSearch() {
		try {
			handlePopupIfExists();
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[normalize-space()='Search' or contains(@class,'search')]"))).click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Clicked Search");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to click Search: " + e.getMessage());
			return false;
		}
	}

	public boolean areResultsDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Filters']")));
			Reporter.generateReport(driver, extTest, Status.PASS, "Search results displayed");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Search results not detected: " + e.getMessage());
			return false;
		}
	}
}
