package com.demo.ui.exploratory;

import com.demo.ui.BaseTest;
import com.demo.ui.pages.CartPage;
import com.demo.ui.pages.InventoryPage;
import com.demo.ui.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled // Internal exploratory verification tests
@Tag("exploratory")
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

    @Test
    public void testCartTitlesDetails() {
        driver.get(baseUrl);

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        // Inventory
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemsToCartAtIndex(2); // add the third item
        inventoryPage.addItemsToCartAtIndex(3); // add the forth item

        // Open cart
        inventoryPage.clickShoppingCartButton();
        CartPage cartPage = new CartPage(driver);

        // Verify item titles match
        List<String> titles = cartPage.getCartItemTitles();
        Assertions.assertTrue(titles.contains("Sauce Labs Bolt T-Shirt"));
        Assertions.assertTrue(titles.contains("Sauce Labs Fleece Jacket"));
    }

    @Test
    public void testCartPricesDetails() {
        driver.get(baseUrl);

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        // Inventory
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemsToCartAtIndex(2); // add the third item
        inventoryPage.addItemsToCartAtIndex(4); // add the fifth item

        // Open cart
        inventoryPage.clickShoppingCartButton();
        CartPage cartPage = new CartPage(driver);

        // Verify items prices match
        List<String> prices = cartPage.getCartItemPrices();
        Assertions.assertTrue(prices.contains("$15.99"));
        Assertions.assertTrue(prices.contains("$7.99"));
    }

    @Test
    public void testCartDescriptionsDetails() {
        driver.get(baseUrl);

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        // Inventory
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemsToCartAtIndex(1); // add the second item
        inventoryPage.addItemsToCartAtIndex(5); // add the sixth item

        // Open cart
        inventoryPage.clickShoppingCartButton();
        CartPage cartPage = new CartPage(driver);

        // Verify items titles match
        List<String> descriptions = cartPage.getCartItemDescriptions();
        Assertions.assertTrue(descriptions.contains("A red light isn't the desired state in testing but it sure helps " +
                "when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."));
        Assertions.assertTrue(descriptions.contains("This classic Sauce Labs t-shirt is perfect to wear when cozying " +
                "up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton."));
    }
}
