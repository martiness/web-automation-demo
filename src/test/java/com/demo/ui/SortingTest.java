package com.demo.ui;

import com.demo.ui.pages.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("sorting")
public class SortingTest extends BaseTest {
    /* Scenario 2
    Log in with the standard user
    Verify when for sorting it is selected "Price (high to low)"
    Then the items are sorted in the correct manner
    Logout from the system
     */


    @Test
    public void testScenarioTwo() {
        driver.get(baseUrl);

        // Log in with the standard user
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        //Sort product items by price
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortByCategory("Price (high to low)");

        // Verify when for sorting it is selected "Price (high to low)"
        // Take current prices
        List<Double> actualPrices = inventoryPage.getItemPrices();
        // Print current prices in the console
        System.out.println("Product prices: ");
        actualPrices.forEach(price -> System.out.println(" - $" + price));
        // Copy and sort the prices
        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        sortedPrices.sort(Comparator.reverseOrder());
        // Print sorted prices in the console
        System.out.println("Expected prices (sorted in descending order): ");
        sortedPrices.forEach(price -> System.out.println(" - $" + price));
        // Compare that expected (sorted) prices art the same with the actual
        assertEquals(sortedPrices, actualPrices, "Items are not sorted from High to Low");

        // Logout
        MenuPage menuPage = new MenuPage(driver);
        menuPage.clickOnMenuButton();
        menuPage.logout();
        assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }
}
