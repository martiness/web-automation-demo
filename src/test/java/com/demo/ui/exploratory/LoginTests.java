package com.demo.ui.exploratory;

import com.demo.ui.base.BaseTest;
import com.demo.ui.pages.LoginPage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled // Internal exploratory verification tests
@Tag("exploratory")
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
}
