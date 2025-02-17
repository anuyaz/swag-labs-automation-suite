package Inventory;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Constants;
import pages.InventoryPage;
import pages.LoginPage;
import utills.TestBase;

import static pages.Constants.*;

public class InventoryTest extends TestBase {

    private InventoryPage inventoryPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void navigateToInventoryPage() {
        inventoryPage = new InventoryPage(driver);
        loginPage = new LoginPage(driver);
        driver.get(baseURL);
        loginPage.loginWithCorrectCredentials(userName,password);
    }

    @Test(priority = 1)
    public void numberOfInventoriesPerPage() {
        int actualInventoriesPerPage = inventoryPage.inventorySize();
        int expectedInventoriesPerPage = 6;
        Assert.assertEquals(actualInventoriesPerPage, expectedInventoriesPerPage);
    }

    @Test(priority = 2)
    public void isVisibleAllItemImages() {
        int actualInventoriesPerPage = inventoryPage.inventorySize();
        boolean test = inventoryPage.isVisibleInventoryImage(actualInventoriesPerPage);
        Assert.assertTrue(test);
    }

    @Test(priority = 3)
    public void isVisibleAllItemDetails() {
        int actualInventoriesPerPage = inventoryPage.inventorySize();
        boolean result = inventoryPage.isInventoryDetailsVisible(actualInventoriesPerPage);
        Assert.assertTrue(result);
    }

    @Test(priority = 4)
    public void isImageClickNavigateToDetailPage() {
        inventoryPage.clickImage();
        String actualNavigation = driver.getCurrentUrl();
        String expectedNavigation = "https://www.saucedemo.com/inventory-item.html?id=4";
        Assert.assertEquals(actualNavigation, expectedNavigation);
    }

    @Test(priority = 5)
    public void isTitleClickNavigateToDetailPage() {
        inventoryPage.clickTitle();
        String actualNavigation = driver.getCurrentUrl();
        String expectedNavigation = "https://www.saucedemo.com/inventory-item.html?id=4";
        Assert.assertEquals(actualNavigation, expectedNavigation);
        System.out.println("Pass");
    }

    @Test(priority = 6)
    public void isButtonNameChangeAfterAddToCart() {
        inventoryPage.clickAddToCartButton();
        String actualButtonName = inventoryPage.readButtonName();
        String expectedButtonName = "Remove";
        Assert.assertEquals(actualButtonName, expectedButtonName);
    }

    @Test(priority = 7)
    public void isCartNavigateToCartPage() {
        inventoryPage.clickAddToCartButton();
        inventoryPage.clickCart();
        String actualNavigationPage = driver.getCurrentUrl();
        String expectedNavigationPage = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(actualNavigationPage, expectedNavigationPage);
    }

    @Test(priority = 8)
    public void isCorrectInventoryInCart() {
        inventoryPage.clickAddToCartButton();
        inventoryPage.clickCart();
        String actualItemNameInCart = inventoryPage.readInventoryTitleInCart();
        String expectedItemNameInCart = "Sauce Labs Backpack";
        Assert.assertEquals(actualItemNameInCart, expectedItemNameInCart);
    }

    @AfterMethod
    public void closePage() {
        driver.quit();
    }

}
