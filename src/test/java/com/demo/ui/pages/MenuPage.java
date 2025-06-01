package com.demo.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuPage {
    // Driver
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Elements
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    // Actions
    public void clickOnMenuButton() {
        WebElement menuBtn = wait.until(
                ExpectedConditions.elementToBeClickable(menuButton));
        menuBtn.click();
    }
    public void logout() {
        WebElement logoutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(logoutLink));
        logoutBtn.click();
    }
}
