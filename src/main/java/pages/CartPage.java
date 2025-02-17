package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver webDriver){
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    WebElement continueShoppingButton, checkoutButton, firstName, lastName, zipCode, cancelPersonalDetailButton, continueButton, error;

    public static final String CONTINUESHOPPING_XPATH = "//*[@id=\"continue-shopping\"]";
    public static final String CHECKOUTBUTTON_XPATH = "//*[@id=\"checkout\"]";
    public static final String FIRSTNAME_XPATH = "//*[@id=\"first-name\"]";
    public static final String LASTNAME_XPATH = "//*[@id=\"last-name\"]";
    public static final String ZIPCODE_XPATH = "//*[@id=\"postal-code\"]";
    public static final String CONTINUEBUTTON_XPATH = "//*[@id=\"continue\"]";
    public static final String CANCELBUTTON_XPATH = "//*[@id=\"cancel\"]";
    public static final String ERRORMESSAGE_XPATH = "//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3";

    public void clickContinueShoopingButton(){
        continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CONTINUESHOPPING_XPATH)));
        continueShoppingButton.click();
    }

    public void clickCheckoutButton(){
        checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CHECKOUTBUTTON_XPATH)));
        checkoutButton.click();
    }

    public void enterFirstName(String fname){
        firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRSTNAME_XPATH)));
        firstName.sendKeys(fname);
    }

    public void enterLastName(String lname){
        lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LASTNAME_XPATH)));
        lastName.sendKeys(lname);
    }

    public void enterZipCode(int postalCode){
        zipCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ZIPCODE_XPATH)));
        zipCode.sendKeys(String.valueOf(postalCode));
    }

    public void clickContinueButton(){
        continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CONTINUEBUTTON_XPATH)));
        continueButton.click();
    }

    public void enterFullDetails(String fname, String lname, int postalCode){
        this.enterFirstName(fname);
        this.enterLastName(lname);
        this.enterZipCode(postalCode);
    }

    public String readErrorMessage(){
        error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERRORMESSAGE_XPATH)));
        String errorMessage = error.getText();
        return errorMessage;
    }

    public void clickCancelButton(){
        cancelPersonalDetailButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CANCELBUTTON_XPATH)));
        cancelPersonalDetailButton.click();
    }

    public void goToCheckout(String fname, String lname, int postalCode){
        this.clickCheckoutButton();
        this.enterFullDetails(fname,lname,postalCode);
        this.clickContinueButton();
    }
}
