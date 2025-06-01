package com.demo.ui.utils;

import org.openqa.selenium.Dimension;

/**
 * Enum that defines common browser screen resolutions to be used during test execution.
 * Each resolution is mapped to specific width and height values.
 */
public enum BrowserResolution {
    HD(1280, 720),
    WXGA(1366, 768),
    FULL_HD(1920, 1080),
    SMALL(800, 600);

    private final int width;
    private final int height;

    /**
     * Constructor to initialize resolution dimensions.
     *
     * @param width  the width of the browser window in pixels
     * @param height the height of the browser window in pixels
     */
    BrowserResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Converts this resolution to a Selenium Dimension object,
     * which can be used to set browser window size.
     *
     * @return Dimension object with the corresponding width and height
     */
    public Dimension toDimension() {
        return new Dimension(width, height);
    }

    /**
     * Parses a string value (e.g., "FULL_HD") and returns the corresponding enum constant.
     *
     * @param value the name of the resolution as a string
     * @return the matching BrowserResolution enum constant
     * @throws RuntimeException if the given value does not match any predefined resolutions
     */
    public static BrowserResolution from(String value) {
        try {
            return BrowserResolution.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unsupported resolution enum: " + value);
        }
    }
}
