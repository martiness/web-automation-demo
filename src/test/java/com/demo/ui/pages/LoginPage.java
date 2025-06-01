package com.demo.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class LoginPage {
    // Driver
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Elements
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");
    private final By loginCredentialBox = By.id("login_credentials");
    private final By passwordCredentialBox = By.className("login_password");

    // Actions
    // Enter Username
    public void enterUserName(String userName) {
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.usernameField));
        usernameField.clear();
        usernameField.sendKeys(userName);
    }

    // Enter Password
    public void enterPassword(String password) {
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Click the Login Button
    public void clickLoginButton() {
        WebElement loginButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.loginButton));
        loginButton.click();
    }

    // Get Error Message
    public String getErrorMessage() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(this.errorMessage));
        return errorMessage.getText();
    }

    // Custom login
    public void loginAs(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickLoginButton();
    }

    // Get Standard Username
    public String getStandardUsername() {
        String allUsernames = wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.loginCredentialBox)).getText();
        return Arrays.stream(allUsernames.split("\n"))
                .filter(u -> u.trim().equals("standard_user"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No 'standard user' found"));
    }

    // Get Standard User Password
    public String getStandardPassword() {
        String passwordText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.passwordCredentialBox)).getText();

        String[] parts = passwordText.split(":");
        if (parts.length > 1) {
            return parts[1].trim();
        } else {
            throw new RuntimeException("The password cannot be found in the password box!");
        }
    }

    // Login as a Standard User
    public void loginAsStandardUser() {
        enterUserName(getStandardUsername());
        enterPassword(getStandardPassword());
        clickLoginButton();
    }
}
