package com.demo.ui;

import com.demo.ui.pages.CartPage;
import com.demo.ui.pages.InventoryPage;
import com.demo.ui.pages.LoginPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTests extends BaseTest {

    @Test
    public void testCartContainsAddedItems() {
        driver.get(baseUrl);

        // Login as Standard User
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        // Add Fist and Last Item to the Card
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();
        inventoryPage.addLastItemToCart();

        // Navigate to Cart page
        inventoryPage.clickShoppingCartButton();
        CartPage cartPage = new CartPage(driver);

        // Validate that Cart page is loaded and fist and last items are added successfully
        List<String> titles = cartPage.getCartItemTitles();
        assertTrue(titles.contains("Sauce Labs Backpack"));
        assertTrue(titles.contains("Test.allTheThings() T-Shirt (Red)"));
    }

    @Test
    public void testCartRemoveLastItemFromCart() {
        driver.get(baseUrl);

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        // Inventory
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();
        inventoryPage.addLastItemToCart();

        // Open cart
        inventoryPage.clickShoppingCartButton();
        CartPage cartPage = new CartPage(driver);

        // Get all item titles before removal
        List<String> titlesBefore = cartPage.getCartItemTitles();
        String lastItem = titlesBefore.get(titlesBefore.size() - 1);

        // Remove last item
        cartPage.removeItemByName(lastItem);

        // Verify that last item is removed
        List<String> titlesAfter = cartPage.getCartItemTitles();
        assertFalse(titlesAfter.contains(lastItem), "Expected item to be removed: " + lastItem);
    }
}
