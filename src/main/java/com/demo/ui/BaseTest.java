package com.demo.ui;

import com.demo.utils.BrowserResolution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import com.demo.utils.ConfigReader;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                firefoxOptions.addPreference("dom.disable_open_during_load", false);
                firefoxOptions.addPreference("signon.rememberSignons", false);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("disable-infobars");
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                driver = new ChromeDriver(options);
                break;
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        String resolutionKey = ConfigReader.get("browser.resolution"); // e.g. FULL_HD
        try {
            BrowserResolution resolution = BrowserResolution.from(resolutionKey);
            driver.manage().window().setSize(resolution.toDimension());
        } catch (Exception e) {
            System.out.println("Invalid resolution config: '" + resolutionKey + "', using default maximize.");
            driver.manage().window().maximize();
        }

        baseUrl = ConfigReader.get("base.url");
        driver.get(baseUrl);
        wait.until(ExpectedConditions.titleContains("Swag Labs"));

        System.out.println("Running tests on: " + System.getProperty("env", "dev"));
        System.out.println("Browser: " + browser);
        System.out.println("Resolution: " + resolutionKey);
        System.out.println("Base URL: " + baseUrl);
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
