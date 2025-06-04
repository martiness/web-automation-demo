package com.demo.ui.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Collections;

/**
 * Factory class responsible for instantiating WebDriver instances
 * based on the specified browser type (e.g., Chrome or Firefox).
 *
 * Applies custom browser options and ensures the correct driver binaries
 * are available using WebDriverManager.
 */
public class DriverFactory {

    /**
     * Creates a WebDriver instance for the given browser.
     * <p>
     * If no match is found, Chrome is used as the default.
     * <p>
     * The created WebDriver is configured with browser-specific options
     * such as incognito/private mode, and disabling popups or notifications.
     *
     * @param browser the name of the browser (e.g., "chrome", "firefox")
     * @return a configured {@link WebDriver} instance
     */
    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase())
        {
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-save-password-bubble");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                return new ChromeDriver(chromeOptions);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                firefoxOptions.addPreference("dom.disable_open_during_load", false);
                firefoxOptions.addPreference("signon.rememberSignons", false);
                return new FirefoxDriver(firefoxOptions);
        }
    }
}
