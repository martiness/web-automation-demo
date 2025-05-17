package com.demo.ui;


import com.demo.ui.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTests extends BaseTest
{
    @Test
    public void testValidLogin(){
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
    }

    @Test
    public void testInvalidLogin(){
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("user", "password");
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.getErrorMessage());
    }

    @Test
    public void testEmptyLogin(){
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("", "");
        assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Username is required"));
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            System.out.println("Quitting driver...");
            driver.quit();
            driver = null;
        }
    }


}
