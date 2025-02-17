package login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.Constants;
import pages.LoginPage;
import utills.TestBase;

import static pages.Constants.*;

public class LoginTest extends TestBase{

    private LoginPage loginPage;

    @BeforeMethod
    public void navigateLoginPage(){
        loginPage = new LoginPage(driver);
        driver.get(baseURL);
    }

    @Test(priority = 1)
    public void loginSuccess(){
        loginPage.loginWithCorrectCredentials(userName,password);
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(actualURL,expectedURL);
    }

    @Test(priority = 2)
    public void incorrectLogin(){
        loginPage.enterUserName(incorrectUsername);
        loginPage.enterPassword(incorrectPassword);
        loginPage.clickLoginButton();
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertNotEquals(actualURL,expectedURL);
    }

    @AfterMethod
    public void closePage(){
        driver.quit();
    }
}
