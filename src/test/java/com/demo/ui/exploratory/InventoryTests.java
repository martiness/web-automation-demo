package com.demo.ui.exploratory;

import com.demo.ui.BaseTest;
import com.demo.ui.pages.InventoryPage;
import com.demo.ui.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTests extends BaseTest {

    @Test
    public void testAddFirstAndLastItemToCart() {
        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();
        inventoryPage.addLastItemToCart();

        int cardCount = inventoryPage.getNumberOfItemsToCart();
        assertEquals(2, cardCount, "Expected 2 items in the cart. However there was: " + cardCount);
    }

    @Test
    public void testAddAllItemToCart() {
        // Driver
        driver.get(baseUrl);

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        // Inventory
        InventoryPage inventoryPage = new InventoryPage(driver);

        // Adding All items to the Card
        List<WebElement> allItems = inventoryPage.getAddItemsToCart();
        int totalItems = allItems.size();
        for (WebElement item : allItems) {
            item.click();
        }

        // Verification that All items were added to the card
        int cardCount = inventoryPage.getNumberOfItemsToCart();
        assertEquals(totalItems, cardCount, "Expected all items in the cart. However there was: " + cardCount);
    }

    @Test
    public void testRemoveAllItemFromCart() {
        // Driver
        driver.get(baseUrl);

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        // Inventory
        InventoryPage inventoryPage = new InventoryPage(driver);

        // Adding All items to the Card
        List<WebElement> addButtons = inventoryPage.getAddItemsToCart();
        for (WebElement button : addButtons) {
            button.click();
        }

        // Verification that All items were added to the card
        int cardCount = inventoryPage.getNumberOfItemsToCart();
        assertEquals(addButtons.size(), cardCount, "Expected all items in the cart. However there was: " + cardCount);

        // Removing All items from the Card
        List<WebElement> removeButtons = inventoryPage.getRemoveButtons();
        for (WebElement button : removeButtons) {
            button.click();
        }

        // Varification that items were successfully removed from the card
        int finalCardCount = inventoryPage.getNumberOfItemsToCart();
        assertEquals(0, finalCardCount, "Expected cart to be empty. However there was: " + finalCardCount);
    }
}
