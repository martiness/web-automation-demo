package com.demo.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By finishButton = By.id("finish");

    public void clickFinish() {
        WebElement finish = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finish.click();
    }
}
