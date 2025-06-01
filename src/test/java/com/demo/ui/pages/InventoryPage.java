package com.demo.ui.pages;

import com.demo.ui.tests.SortingFlowTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class InventoryPage {
    // Driver
    private final WebDriver driver;
    private WebDriverWait wait;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static final Logger logger = LoggerFactory.getLogger(InventoryPage.class);

    // Elements
    private final By inventoryItems = By.className("inventory_item");
    private final By addToCartButtons = By.cssSelector("button.btn_inventory");
    private final By removeButtons = By.cssSelector("button.btn_secondary");
    private final By itemPrices = By.className("inventory_item_price");
    private final By itemTitles = By.className("inventory_item_name");
    private final By itemDescriptions = By.className("inventory_item_desc");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By sortDropdown = By.className("product_sort_container");
    private final By shoppingCartButton = By.className("shopping_cart_link");

    // Actions
    // Add the First Item to the Cart
    public void addFirstItemToCart(){
        List<WebElement> buttons = getAddItemsToCart();
        if(!buttons.isEmpty()){
            buttons.get(0).click();
        } else {
            throw new NoSuchElementException("No add to cart button found");
        }
    }

    // Add the Last Item to the Cart
    public void addLastItemToCart(){
        List<WebElement> buttons = getAddItemsToCart();
        if(!buttons.isEmpty()){
            buttons.get(buttons.size() - 1).click();
        } else {
            throw new NoSuchElementException("No add to cart button found");
        }
    }

    // Add Item to Cart by Index
    public void addItemsToCartAtIndex(int index) {
        List<WebElement> buttons = getAddItemsToCart();
        if(!buttons.isEmpty()){
            if(index >= 0 && index < buttons.size()) {
                buttons.get(index).click();
            }
        } else {
            throw new NoSuchElementException("No add to cart button found");
        }
    }

    // Get the Number of Items in the Cart
    public int getNumberOfItemsToCart() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) {
            return 0;
        }

        try {
            WebElement badge = wait.until(ExpectedConditions.visibilityOf(badges.get(0)));
            return Integer.parseInt(badge.getText());
        } catch (Exception e) {
            logger.debug("Badge not visible or not parseable.");
            return 0;
        }
    }

    // Sort the product items by Category
    public void sortByCategory(String category){
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        dropdown.click();
        dropdown.findElement(By.xpath("//option[text()='" + category + "']")).click();
    }

    // Get all product items prices
    public List<Double> getItemPrices() {
        List<WebElement> priceElements = driver.findElements(itemPrices);
        return priceElements.stream()
                .map(WebElement::getText)
                .map(s -> s.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    // Click Shopping Card Button
    public void clickShoppingCartButton() {
        WebElement shoppingCartButtonElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(shoppingCartButton));
        shoppingCartButtonElement.click();
    }

    // Helper
    // Get all Items - 'Add to cart' buttons
    public List<WebElement> getAddItemsToCart(){
        List<WebElement> buttons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(addToCartButtons));
        return buttons;
    }

    // Get all Items - 'Remove' buttons
    public List<WebElement> getRemoveButtons() {
        List<WebElement> buttons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(removeButtons));
        return buttons;
    }

    // Get Text from Element
    public List<String> getTextFromElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<WebElement> getAllInventoryItems() {
        List<WebElement> items = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItems));
        return items;
    }
}
