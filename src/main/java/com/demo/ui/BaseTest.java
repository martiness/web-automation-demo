package com.demo.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Skip Google Password pop-up
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("disable-infobars");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        baseUrl = "https://www.saucedemo.com/";
        driver.get(baseUrl);

        wait.until(ExpectedConditions.titleContains("Swag Labs"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Failed to quit WebDriver: " + e.getMessage());
            }
        }
    }
}
