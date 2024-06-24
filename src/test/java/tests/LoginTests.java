package tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }
    
    @Test
    public void MainScreenElements(){
    	WebElement username = driver.findElement(By.id("user-name"));
    	WebElement password = driver.findElement(By.id("password"));
    	WebElement LoginButton = driver.findElement(By.id("login-button"));
    	
    	Assert.assertNotNull(username, "Username field is not present.");
    	Assert.assertNotNull(password, "Password field is not present.");
    	Assert.assertNotNull(LoginButton, "Login Button field is not present.");

    	
    }
    
    @Test
    public void ValidCredentials() {
    	driver.findElement(By.id("user-name")).sendKeys("standard_user");
    	driver.findElement(By.id("password")).sendKeys("secret_sauce");
    	driver.findElement(By.id("login-button")).click();
    	
    	WebElement swagLabsDiv = driver.findElement(By.xpath("//div[contains(text(),'Swag Labs')]"));
        Assert.assertTrue(swagLabsDiv.isDisplayed(), "Login with valid credentials failed.");
    	
    }
    
    @Test
    public void WrongCredentials() {
    	driver.findElement(By.id("user-name")).sendKeys("not_standard_user");
    	driver.findElement(By.id("password")).sendKeys("noy_secret_sauce");
    	driver.findElement(By.id("login-button")).click();
    	
    	WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container.error"));
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertTrue(errorMessage.getText().contains(expectedMessage), "Error message for invalid credentials is incorrect.");
    	
    }
    
    
    @Test
    public void EmptyCredentials() {
    	WebElement LoginButton = driver.findElement(By.id("login-button"));
    	LoginButton.click();
    	
    	WebElement UsernameErrorMessage = driver.findElement(By.cssSelector(".error-message-container.error"));
        String UsernameExpectedMessage = "Epic sadface: Username is required";
        Assert.assertTrue(UsernameErrorMessage.getText().contains(UsernameExpectedMessage), "Error message for empty username is incorrect.");
    	
    	driver.findElement(By.id("user-name")).sendKeys("standard_user");
        LoginButton.click();
        WebElement PasswordErrorMessage = driver.findElement(By.cssSelector("div.error-message-container.error"));
        String PasswordExpectedMessage = "Epic sadface: Password is required";
        Assert.assertTrue(PasswordErrorMessage.getText().contains(PasswordExpectedMessage), "Error message for empty password is incorrect.");
    
        

    	
    	
    }
    
    @AfterMethod
    public void tearDown() throws InterruptedException {
    	if(driver != null) {
    		Thread.sleep(3000);
    		driver.quit();
    	}
    }

   
}
