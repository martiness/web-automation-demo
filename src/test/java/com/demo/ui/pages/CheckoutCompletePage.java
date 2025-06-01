package com.demo.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {
    // Driver
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Elements
    private final By thankYouMessage = By.className("complete-header");
    private final By backButton = By.id("back-to-products");

    // Actions
    // Get the Confirmation message
    public String getConfirmationMessage() {
        WebElement thankYouMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(thankYouMessage));
        return thankYouMessageElement.getText();
    }

    // Click on the Back button
    public void clickBackHomeButton() {
        WebElement backButtonElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(backButton));
        backButtonElement.click();
    }
}
