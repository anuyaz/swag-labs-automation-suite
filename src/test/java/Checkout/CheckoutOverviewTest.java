package Checkout;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutOverviewPage;
import pages.InventoryPage;
import pages.LoginPage;
import utills.TestBase;

import static pages.Constants.*;

public class CheckoutOverviewTest extends TestBase {

    InventoryPage inventoryPage;
    LoginPage loginPage;
    CartPage cartPage;
    CheckoutOverviewPage checkoutOverviewPage;

    @BeforeMethod
    public void navigateToInventoryPage() {
        inventoryPage = new InventoryPage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        driver.get(baseURL);
        loginPage.loginWithCorrectCredentials(userName,password);
        inventoryPage.addItemToCart();
        cartPage.goToCheckout(firstName,lastName,postalCode);
    }

    @Test(priority = 1)
    public void checkTotal(){
        double inventoryPrice = checkoutOverviewPage.readInventoryPrice();
        double taxPrice = checkoutOverviewPage.readTax();
        double totalPrice = checkoutOverviewPage.readTotalPrice();
        double expectedTotal = inventoryPrice + taxPrice;
        Assert.assertEquals(totalPrice,expectedTotal);
    }

    @Test(priority = 2)
    public void finishButtonNavigateToCompletePage(){
        checkoutOverviewPage.clickFinishButton();
        String actualNavigation = driver.getCurrentUrl();
        String expectedNavigation = "https://www.saucedemo.com/checkout-complete.html";
        Assert.assertEquals(actualNavigation, expectedNavigation);
    }

    @Test(priority = 3)
    public void cancelButtonNavigateToInventoryPage(){
        checkoutOverviewPage.clickCancelButton();
        String actualNavigation = driver.getCurrentUrl();
        String expectedNavigation = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(actualNavigation, expectedNavigation);
    }

    @Test(priority = 4)
    public void cancelNotRemoveCartItem(){
        checkoutOverviewPage.clickCancelButton();
        inventoryPage.clickCart();
        boolean cartItemsAvailability = inventoryPage.readInventoryTitleInCart().isEmpty();
        Assert.assertEquals(cartItemsAvailability, false);
    }

    @AfterMethod
    public void closePage() {
        driver.quit();
    }
}
