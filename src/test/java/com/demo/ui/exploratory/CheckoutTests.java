package com.demo.ui.exploratory;

import com.demo.ui.base.BaseTest;
import com.demo.ui.pages.CartPage;
import com.demo.ui.pages.CheckoutPage;
import com.demo.ui.pages.CheckoutOverviewPage;
import com.demo.ui.pages.CheckoutCompletePage;
import com.demo.ui.pages.InventoryPage;
import com.demo.ui.pages.LoginPage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled // Internal exploratory verification tests
@Tag("exploratory")
public class CheckoutTests extends BaseTest {

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
