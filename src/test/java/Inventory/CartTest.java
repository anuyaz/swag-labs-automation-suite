package Inventory;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utills.TestBase;

import static pages.Constants.*;

public class CartTest extends TestBase {

    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    public CartPage cartPage;

    private final String firstName = "Anuradha";
    private final String lastName = "kanakarathna";
    private final int postalCode = 123;

    @BeforeMethod
    public void navigateToInventoryPage() {
        inventoryPage = new InventoryPage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        driver.get(baseURL);
        loginPage.loginWithCorrectCredentials(userName,password);
        inventoryPage.addItemToCart();
    }

    @Test(priority = 1)
    public void isContinueButtonNavogateToInventoryPage(){
        cartPage.clickContinueShoopingButton();
        String actualNavigation = driver.getCurrentUrl();
        String expectedNavigation = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(actualNavigation, expectedNavigation);
    }

    @Test(priority = 2)
    public void isCheckoutNavigateToPersonalDetailPage(){
        cartPage.clickCheckoutButton();
        String actualNavigation = driver.getCurrentUrl();
        String expectedNavigation = "https://www.saucedemo.com/checkout-step-one.html";
        Assert.assertEquals(actualNavigation, expectedNavigation);
    }

    @Test(priority = 3)
    public void continueWithMissingFistName(){
        cartPage.clickCheckoutButton();
        cartPage.enterLastName(lastName);
        cartPage.enterZipCode(postalCode);
        cartPage.clickContinueButton();
        String actualErrorMessage = cartPage.readErrorMessage();
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test(priority = 4)
    public void continueWithMissingLastName(){
        cartPage.clickCheckoutButton();
        cartPage.enterFirstName(firstName);
        cartPage.enterZipCode(postalCode);
        cartPage.clickContinueButton();
        String actualErrorMessage = cartPage.readErrorMessage();
        String expectedErrorMessage = "Error: Last Name is required";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test(priority = 5)
    public void continueWithMissingZipCode(){
        cartPage.clickCheckoutButton();
        cartPage.enterFirstName(firstName);
        cartPage.enterLastName(lastName);
        cartPage.clickContinueButton();
        String actualErrorMessage = cartPage.readErrorMessage();
        String expectedErrorMessage = "Error: Postal Code is required";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test(priority = 6)
    public void enterCorrectPersonalDetails(){
        cartPage.clickCheckoutButton();
        cartPage.enterFullDetails(firstName,lastName,postalCode);
        cartPage.clickContinueButton();
        String actualNavigation = driver.getCurrentUrl();
        String expectedNavigation = "https://www.saucedemo.com/checkout-step-two.html";
        Assert.assertEquals(actualNavigation, expectedNavigation);
    }

    @Test(priority = 7)
    public void cancelButtonNavigateToCartPage(){
        cartPage.clickCheckoutButton();
        cartPage.clickCancelButton();
        String actualNavigation = driver.getCurrentUrl();
        String expectedNavigation = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(actualNavigation, expectedNavigation);
    }

    @AfterMethod
    public void closePage() {
        driver.quit();
    }

}
