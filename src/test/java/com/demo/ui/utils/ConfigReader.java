package com.demo.ui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for reading configuration properties from environment-specific files.
 *
 * Loads the appropriate config file based on the system property `env` (e.g., "dev", "staging", "test").
 * Defaults to "dev" if no environment is specified.
 */
public class ConfigReader {
    private static final Properties properties = new Properties();
    private static final String DEFAULT_ENV = "dev";

    // Static block to load configuration at class loading time
    static {
        // Determine which environment to use (default: dev)
        String env = System.getProperty("env", DEFAULT_ENV);
        String fileName = "config/" + env + ".properties";
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Cannot find config file: " + fileName);
            }
            // Load properties from the selected environment file
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + fileName, e);
        }
    }

    /**
     * Retrieves the value for a given property key from the loaded configuration.
     *
     * @param key the property name to retrieve
     * @return the corresponding value from the environment config
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Retrieves the base URL for the current test environment.
     * This value is expected to be defined as {@code base.url} in the environment config file.
     *
     * @return the base URL as a {@code String}
     */
    public static String getBaseUrl() {
        return get("base.url");
    }

    /**
     * Retrieves the selected browser type.
     * This value is read from the system property {@code browser} or defaults to {@code chrome}.
     *
     * @return the name of the browser in lowercase (e.g., {@code chrome}, {@code firefox})
     */
    public static String getBrowser() {
        return System.getProperty("browser", "chrome").toLowerCase();
    }

    /**
     * Retrieves the configured screen resolution.
     * This value is expected to be defined as {@code browser.resolution} in the config file.
     *
     * @return the resolution key (e.g., {@code FULL_HD}, {@code HD})
     */
    public static String getResolution() {
        return get("browser.resolution");
    }

    /**
     * Retrieves the current environment key.
     * This is read from the system property {@code env} or defaults to {@code dev}.
     *
     * @return the name of the current environment
     */
    public static String getEnvironment() {
        return System.getProperty("env", DEFAULT_ENV);
    }
}



