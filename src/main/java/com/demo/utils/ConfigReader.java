package com.demo.utils;

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

    // Static block to load configuration at class loading time
    static {
        // Determine which environment to use (default: dev)
        String env = System.getProperty("env", "dev"); // default environment
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
}



