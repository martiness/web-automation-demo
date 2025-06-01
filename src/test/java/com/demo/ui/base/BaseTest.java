package com.demo.ui.base;

import com.demo.ui.utils.BrowserResolution;
import com.demo.ui.utils.ConfigReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;

/**
 * Abstract base class for all test classes.
 *
 * Handles common setup and teardown logic:
 * - Browser selection (Chrome or Firefox)
 * - Custom browser options
 * - Resolution configuration
 * - Environment-specific base URL
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp() {
        // Read browser type from system properties (default is Chrome)
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        // Instantiate the appropriate WebDriver with browser-specific options
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

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        // Set browser window size based on config, fallback to maximize
        String resolutionKey = ConfigReader.get("browser.resolution"); // e.g. FULL_HD
        try {
            BrowserResolution resolution = BrowserResolution.from(resolutionKey);
            driver.manage().window().setSize(resolution.toDimension());
            logger.info("Resolution set to: {}", resolutionKey);
        } catch (Exception e) {
            logger.warn("Invalid resolution config: {}, using default maximize.", resolutionKey);
            driver.manage().window().maximize();
        }

        // Load base URL from config
        baseUrl = ConfigReader.get("base.url");
        driver.get(baseUrl);
        wait.until(ExpectedConditions.titleContains("Swag Labs"));

        // Log execution configuration to console
        logger.info("Running tests on: {}", System.getProperty("env", "dev"));
        logger.info("Browser: {}", browser);
        logger.info("Resolution: {}", resolutionKey);
        logger.info("Base URL: {}", baseUrl);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver session closed successfully.");
            } catch (Exception e) {
                logger.error("Failed to quit WebDriver: {}", e.getMessage());
            }
        }
    }
}
