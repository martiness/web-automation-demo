package com.demo.utils;

import org.openqa.selenium.Dimension;

public enum BrowserResolution {
    HD(1280, 720),
    WXGA(1366, 768),
    FULL_HD(1920, 1080),
    SMALL(800, 600);

    private final int width;
    private final int height;

    BrowserResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension toDimension() {
        return new Dimension(width, height);
    }

    public static BrowserResolution from(String value) {
        try {
            return BrowserResolution.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unsupported resolution enum: " + value);
        }
    }
}
