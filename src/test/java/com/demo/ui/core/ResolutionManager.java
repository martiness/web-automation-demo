
package com.demo.ui.core;

import com.demo.ui.utils.BrowserResolution;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class responsible for applying browser window resolution
 * based on a provided configuration key (e.g., "FULL_HD", "HD").
 *
 * If the provided resolution key is invalid or unsupported, it falls back
 * to maximizing the window.
 */
public class ResolutionManager {

    private static final Logger logger = LoggerFactory.getLogger(ResolutionManager.class);

    /**
     * Applies the browser window size based on a resolution key.
     * <p>
     * The resolution key must match a value in the {@link BrowserResolution} enum.
     * If parsing fails, the window will be maximized instead.
     *
     * @param driver        the WebDriver instance to modify
     * @param resolutionKey the resolution key (e.g., "FULL_HD", "HD", "SMALL")
     */
    public static void apply(WebDriver driver, String resolutionKey) {
        try {
            BrowserResolution resolution = BrowserResolution.from(resolutionKey);
            driver.manage().window().setSize(resolution.toDimension());
            logger.info("Resolution set to: {}", resolutionKey);
        } catch (Exception e) {
            logger.warn("Invalid resolution config: {}, using default maximize.", resolutionKey);
            driver.manage().window().maximize();
        }
    }
}


