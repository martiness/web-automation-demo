package com.demo.ui.tests;

import com.demo.ui.base.BaseTest;
import com.demo.ui.pages.InventoryPage;
import com.demo.ui.pages.MenuPage;
import com.demo.ui.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortingFlowTest extends BaseTest {
    /* Scenario 2
    Log in with the standard user
    Verify when for sorting it is selected "Price (high to low)"
    Then the items are sorted in the correct manner
    Logout from the system
     */

    @Epic("UI Tests")
    @Feature("Sorting Flow")
    @Story("Sorting Order")
    @DisplayName("Verify successful product sorting")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that products are sorted and logout.")
    @Tag("sorting")
    @Test
    public void testSortingFlow() {
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
