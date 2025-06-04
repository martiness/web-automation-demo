package com.demo.ui.core;

import com.demo.ui.utils.ConfigReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Abstract base class for all UI test classes.
 * <p>
 * Provides a common setup and teardown mechanism for WebDriver-based tests,
 * including browser instantiation, resolution configuration, environment selection,
 * and navigation to the base URL.
 * <p>
 * Subclasses should extend this class to inherit shared test lifecycle logic.
 */
public abstract class BaseTest {

    // WebDriver instance used for executing browser actions.
    protected WebDriver driver;

    // Explicit wait instance used for element synchronization.
    protected WebDriverWait wait;

    // Base URL of the application under test, loaded from environment config.
    protected String baseUrl;

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    /**
     * Initializes the WebDriver before each test method.
     * <p>
     * - Reads configuration from system properties and config files.<br>
     * - Instantiates a browser via {@link DriverFactory}.<br>
     * - Applies screen resolution via {@link ResolutionManager}.<br>
     * - Navigates to the configured base URL and verifies page title.<br>
     * - Logs active configuration parameters.
     */
    @BeforeEach
    public void setUp() {

        String browser = ConfigReader.getBrowser();

        driver = DriverFactory.createDriver(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        ResolutionManager.apply(driver, ConfigReader.getResolution());

        baseUrl = ConfigReader.getBaseUrl();
        driver.get(baseUrl);
        wait.until(ExpectedConditions.titleContains("Swag Labs"));

        logger.info("Running tests on: {}", ConfigReader.getEnvironment());
        logger.info("Browser: {}", browser);
        logger.info("Resolution: {}", ConfigReader.getResolution());
        logger.info("Base URL: {}", baseUrl);
    }

    /**
     * Quits the WebDriver session after each test method execution.
     * Ensures browser instances are properly closed and logs the teardown status.
     */
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
