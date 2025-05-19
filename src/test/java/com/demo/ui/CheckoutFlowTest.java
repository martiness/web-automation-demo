package com.demo.ui;

import com.demo.ui.pages.*;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllureJunit5.class)
public class CheckoutFlowTest extends BaseTest {
    /*  Scenario 1
    Use the standard user and password (they are prone to change, think how to obtain them)

    Log in with the standard user
    Add the first and the last item in the cart, verify the correct items are added
    Remove the first item and add previous to the last item to the cart, verify the content again
    Go to checkout
    Finish the order
    Verify order is placed
    Verify cart is empty
    Logout from the system
    */
    private static final String ITEM_BACKPACK = "Sauce Labs Backpack";
    private static final String ITEM_TSHIRT = "Test.allTheThings() T-Shirt (Red)";
    private static final String ITEM_ONESIE = "Sauce Labs Onesie";

    @Epic("UI Tests")
    @Feature("Checkout Flow")
    @Story("Complete Order")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Verify successful order placement")
    @Description("Test that completes full checkout and verifies confirmation.")
    @Tag("checkout")
    @Test
    public void testCheckoutFlow() {
        driver.get(baseUrl);

        // Log in with the standard user
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        // Add the first and the last item in the cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();
        inventoryPage.addLastItemToCart();
        inventoryPage.clickShoppingCartButton();

        // Verify the correct items are added to the Cart
        CartPage cartPage = new CartPage(driver);
        List<String> titles = cartPage.getCartItemTitles();
        assertTrue(titles.contains(ITEM_BACKPACK));
        assertTrue(titles.contains(ITEM_TSHIRT));

        // Remove the first item
        String removeItem = titles.get(0);
        cartPage.removeItemByName(removeItem);

        // Add previous to the last item to the cart
        cartPage.clickContinueShoppingButton();
        inventoryPage.addItemsToCartAtIndex(4);
        inventoryPage.clickShoppingCartButton();

        // Verify the content of Cart again
        titles = cartPage.getCartItemTitles();
        assertTrue(titles.contains(ITEM_TSHIRT));
        assertTrue(titles.contains(ITEM_ONESIE));

        // Go to checkout
        cartPage.clickCheckoutButton();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutForm("John", "Doe", "1000");

        // Finish the order
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();

        // Verify order is placed
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        String confirmation = completePage.getConfirmationMessage();
        assertEquals("Thank you for your order!", confirmation);

        // Verify Shopping Cart has zero items
        completePage.clickBackHomeButton();
        int finalCardCount = inventoryPage.getNumberOfItemsToCart();
        assertEquals(0, finalCardCount, "Expected cart to be empty. However there was: " + finalCardCount);

        // Verify the Cart is empty
        inventoryPage.clickShoppingCartButton();
        assertEquals(0, cartPage.getNumberOfCartItems(), "Expected cart to be empty after checkout");

        // Logout from the system
        MenuPage menuPage = new MenuPage(driver);
        menuPage.clickOnMenuButton();
        menuPage.logout();

        // Verify that user is at Login page
        assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }
}
