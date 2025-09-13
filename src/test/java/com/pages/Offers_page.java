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
//import com.objectrepository.Locators;
import com.parameters.Reporter;

public class Offers_page {

	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest extTest;

	public Offers_page(WebDriver driver, ExtentTest extTest) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		this.extTest = extTest;
	}

	public boolean selectingOffer() {
		driver.findElement(By.xpath("//p[text()='Offers']")).click();
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
			WebElement moreDropdown = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='More']")));
			moreDropdown.click();

			WebElement planItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Plan']")));
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
		driver.findElement(By.cssSelector(
				"body > div.min-h-full.flex.flex-col.page_container__oILIU > div.max-w-screen-2xl.flex.mx-auto.grow.w-full.page_exploreBody__sc0QV > div > div.flex.gap-40.overflow-x-auto.px-20.xl\\:px-30.border-b.border-secondary.xl\\:border-none.no-scrollbar > div:nth-child(4) > svg > use"))
				.click();
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
		driver.findElement(By.xpath("//p[text()='From']")).click();
		driver.findElement(By.xpath("//*[@id=\"portal-root\"]/div/div/div[2]/div/div[2]/input")).sendKeys(from);

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"portal-root\"]/div/div/div[3]/a/div/div[1]")));
		driver.findElement(By.xpath("//*[@id=\"portal-root\"]/div/div/div[3]/a/div/div[1]")).click();
	}

	public boolean enteringWhere(String where) {
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"portal-root\"]/div/div/div[2]/div/div[2]/input")).sendKeys(where);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'overflow-auto')]/a[1]")));
		driver.findElement(By.xpath("//div[contains(@class, 'overflow-auto')]/a[1]")).click();

		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/a[1]")));
			Reporter.generateReport(driver, extTest, Status.PASS, "Location dialogue box is opened");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to open location dialogue box");
			return false;
		}
	}

	public boolean choosingHotel() {
		try {
			driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/a[2]")).click();
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"places-to-stay\"]/div[1]/div/a")))
					.click();
			Reporter.generateReport(driver, extTest, Status.PASS, "Hotels option is displayed");
			return true;
		} catch (Exception e) {
			Reporter.generateReport(driver, extTest, Status.FAIL, "Failed to display Hotels option");
			return false;
		}
	}

}
