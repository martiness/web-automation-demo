package com.demo.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CartPage {
    // Driver
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Elements
    private final By cartItemTitles = By.className("inventory_item_name");
    private final By cartItemPrices = By.className("inventory_item_price");
    private final By cartItemDescriptions = By.className("inventory_item_desc");
    private final By removeButtons = By.cssSelector("button.cart_button");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    //Actions
    // Get Titles of all products in the Cart
    public List<String> getCartItemTitles() {
        List<String> cardItemTitles = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(this.cartItemTitles))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return cardItemTitles;
    }

    // Get Prices of all products in the Cart
    public List<String> getCartItemPrices() {
        List<String> cardItemPrices = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(this.cartItemPrices))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return cardItemPrices;
    }

    // Get Descriptions of all products in the Cart
    public List<String> getCartItemDescriptions() {
        List<String> cardItemDescriptions = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(this.cartItemDescriptions))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return cardItemDescriptions;
    }

    // Get Number of cart items
    public int getNumberOfCartItems() {
        List<WebElement> items = driver.findElements(this.cartItemTitles);
        if (!items.isEmpty()) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(this.cartItemTitles));
        }
        return items.size();
    }

    // Remove all Items from the Cart
    public void removeAllItems() {
        List<WebElement> removeItems = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(removeButtons));

        for (WebElement button : removeItems) {
            button.click();
        }
    }

    // Remove Specific item from the Cart
    public void removeItemByName(String itemName) {
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        int originalCount = cartItems.size();

        for (WebElement item : cartItems) {
            WebElement nameElement = item.findElement(By.className("inventory_item_name"));
            String name = nameElement.getText().trim();

            if (name.equalsIgnoreCase(itemName.trim())) {
                WebElement removeButton = item.findElement(By.tagName("button"));
                wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();

                wait.until(driver -> driver.findElements(By.className("cart_item")).size() < originalCount);
                return;
            }
        }

        throw new NoSuchElementException("Item not found in cart: " + itemName);
    }

    // Click Checkout button
    public void clickCheckoutButton() {
        WebElement checkoutButtonElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(this.checkoutButton));
        checkoutButtonElement.click();
    }

    // Click Continue Shopping button
    public void clickContinueShoppingButton() {
        WebElement continueShoppingButtonElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(this.continueShoppingButton));
        continueShoppingButtonElement.click();
    }
}
