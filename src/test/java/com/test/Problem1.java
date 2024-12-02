package com.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Problem1 {
	WebDriver driver;

	private void login(String userName, String password) {
		enterText(By.id("user-name"), userName);
		enterText(By.id("password"), password);
		clickButton(By.id("login-button"));
		Assert.assertEquals(driver.getTitle(), "Swag Labs");
	}

	// Generic method to enter text in a text box
	private void enterText(By locator, String text) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(text);
	}

	// Generic method to click a button
	private void clickButton(By locator) {
		driver.findElement(locator).click();
	}

	@BeforeSuite
	public void setup() {
		WebDriverManager.edgedriver().setup();

		// Initialize WebDriver (FirefoxDriver)
		driver = new EdgeDriver();

		// Open a website
		driver.get("https://www.saucedemo.com");
		Assert.assertEquals(driver.getTitle(), "Swag Labs");

	}

	@Test
	@Parameters({ "username", "password" })
	public void testLogin(String userName, String password) throws Exception {
		
		

		// Add a small wait to see the result before finishing the test
		try {
			// Perform login using the generic login method
			login(userName, password);
			String destPath=System.getProperty("user.dir") +"\\" + "screenshot.png";
			takeSnapShot(driver,destPath);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Optionally, you can add assertions here to verify successful login
	}

	 public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

	        //Convert web driver object to TakeScreenshot

	        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

	        //Call getScreenshotAs method to create image file

	                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	            //Move image file to new destination

	                File DestFile=new File(fileWithPath);

	                //Copy file at destination

	                FileUtils.copyFile(SrcFile, DestFile);

	    }
	 
	@AfterSuite
	public void tearDown() {
		// Close the browser after tests are completed
		if (driver != null) {
			driver.quit();
		}
	}
}
