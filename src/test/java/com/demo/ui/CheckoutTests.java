package com.demo.ui;

import com.demo.ui.pages.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTests extends BaseTest{

    @Test
    public void testSuccessfulCheckoutFlow() {
        driver.get(baseUrl);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstItemToCart();
        inventoryPage.clickShoppingCartButton();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutForm("John", "Doe", "1000");

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.clickFinish();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        String confirmation = completePage.getConfirmationMessage();

        assertEquals("Thank you for your order!", confirmation);
    }
}
