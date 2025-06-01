package com.demo.ui.exploratory;

import com.demo.ui.base.BaseTest;
import com.demo.ui.pages.LoginPage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled // Internal exploratory verification tests
@Tag("exploratory")
public class LoginTests extends BaseTest
{
    private static final Logger logger = LoggerFactory.getLogger(LoginTests.class);

    @Test
    public void testValidLogin(){
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        logger.debug("Login page URL: {}", driver.getCurrentUrl());
        loginPage.loginAsStandardUser();
        String currentUrl = driver.getCurrentUrl();
        logger.debug("Target page URL: {}", currentUrl);
        assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
    }

    @Test
    public void testInvalidLogin(){
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("user", "password");
        logger.debug("Login page URL: {}", driver.getCurrentUrl());
        logger.debug("Error message: {}", loginPage.getErrorMessage());
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.getErrorMessage());
    }

    @Test
    public void testEmptyLogin(){
        driver.get(baseUrl);
        logger.debug("Login page URL: {}", driver.getCurrentUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("", "");
        logger.debug("Error message: {}", loginPage.getErrorMessage());
        assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Username is required"));
    }
}
