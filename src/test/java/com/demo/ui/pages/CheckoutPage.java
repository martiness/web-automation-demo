package com.demo.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    // Driver
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Elements
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    // Actions
    // Fill the Fields
    public void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.sendKeys(value);
    }

    // Fill the Checkout Form
    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        fillField(firstNameInput, firstName);
        fillField(lastNameInput, lastName);
        fillField(postalCodeInput, postalCode);

        WebElement continueBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.continueButton));
        continueBtn.click();
    }
}
