package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public WebElement usernameTextBox, passwordTextBox, loginButton;

    private static final String USERNAME_XPATH = "//*[@id=\"user-name\"]";
    private static final String PASSWORD_XPATH = "//*[@id=\"password\"]";
    private static final String LOGINBUTTON_XPATH = "//*[@id=\"login-button\"]";

    public LoginPage(WebDriver webDriver){
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void enterUserName(String userName){
        usernameTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USERNAME_XPATH)));
        usernameTextBox.sendKeys(userName);
    }

    public void enterPassword(String password){
        passwordTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_XPATH)));
        passwordTextBox.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGINBUTTON_XPATH)));
        loginButton.click();
    }

    public void loginWithCorrectCredentials(String userName, String password){
        this.enterUserName(userName);
        this.enterPassword(password);
        this.clickLoginButton();
    }
}
