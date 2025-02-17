package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutOverviewPage(WebDriver webDriver){
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    WebElement itemPrice, tax, total, finishButton, cancelButton;

    public static final String ITEMPRICE_XPATH = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]";
    public static final String TAX_XPATH = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]";
    public static final String TOTAL_XPATH = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]";
    public static final String FINISHBUTTON_XPATH = "//*[@id=\"finish\"]";
    public static final String CANCELBUTTON_XPATH = "//*[@id=\"cancel\"]";


    public double readTotalPrice(){
        total = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOTAL_XPATH)));
        String totalPrice = total.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(totalPrice);
    }

    public double readInventoryPrice(){
        itemPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEMPRICE_XPATH)));
        String inventoryPrice = itemPrice.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(inventoryPrice);
    }

    public double readTax(){
        tax = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TAX_XPATH)));
        String taxPrice = tax.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(taxPrice);
    }

    public void clickFinishButton(){
        finishButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FINISHBUTTON_XPATH)));
        finishButton.click();
    }

    public void clickCancelButton(){
        cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CANCELBUTTON_XPATH)));
        cancelButton.click();
    }

}
