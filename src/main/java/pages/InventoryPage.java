package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {

    WebDriver driver;
    WebDriverWait wait;

    public List<WebElement> inventoryImage, inventoryName, inventoryDetails, inventoryPrice;
    public WebElement addToCartButton_backpack, image_backpack, title_backpack, cart, removeCartButton_backpack, inventoryItemNameInCart;

    public static final String INVENTORYNAME_CLASSNAME = "inventory_item_name";
    public static final String INVENTORYIMAGE_CLASSNAME = "inventory_item_img";
    public static final String INVENTORYDETAILS_CLASSNAME = "inventory_item_desc";
    public static final String INVENTORYPRICE_CLASSNAME = "inventory_item_price";

    public static final String BACKPACK_ADDTOCARTBUTTON_XPATH = "//*[@id=\"add-to-cart-sauce-labs-backpack\"]";
    public static final String BACKPACK_REMOVEBUTTON_XPATH = "//*[@id=\"remove-sauce-labs-backpack\"]";

    public static final String BACKPACKIMAGE_XPATH = "//*[@id=\"item_4_img_link\"]/img";
    public static final String BACKPACKTITLE_XPATH = "//*[@id=\"item_4_title_link\"]/div";
    public static final String CART_XPATH = "//*[@id=\"shopping_cart_container\"]/a";
    public static final String INVENTORYITEMNAMEINCART_XPATH = "//*[@id=\"item_4_title_link\"]/div";

    public InventoryPage(WebDriver webDriver){
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public int inventorySize(){
        inventoryName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(INVENTORYNAME_CLASSNAME)));
        int size = inventoryName.size();
        return size;
    }

    public boolean isVisibleInventoryImage(int numberOfInventories){
        boolean isImagesVisible = true;
        inventoryImage = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(INVENTORYIMAGE_CLASSNAME)));
        for (int i = 0; i < numberOfInventories; i++){
            if (!inventoryImage.get(i).isDisplayed()){
                isImagesVisible = false;
                break;
            }
        }
        return isImagesVisible;
    }

    public boolean isInventoryDetailsVisible(int numberOfInventories){
        inventoryName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(INVENTORYNAME_CLASSNAME)));
        inventoryDetails = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(INVENTORYDETAILS_CLASSNAME)));
        inventoryPrice = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(INVENTORYPRICE_CLASSNAME)));
        boolean isDetailVisible = true;
        for (int i = 0; i < numberOfInventories; i++){
            if(inventoryName.isEmpty() || inventoryDetails.isEmpty() || inventoryPrice.isEmpty()){
                isDetailVisible = false;
                break;
            }
        }
        return isDetailVisible;
    }

    public void clickAddToCartButton(){
        addToCartButton_backpack = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BACKPACK_ADDTOCARTBUTTON_XPATH)));
        addToCartButton_backpack.click();
    }

    public void clickImage(){
        image_backpack = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BACKPACKIMAGE_XPATH)));
        image_backpack.click();
    }

    public void clickTitle(){
        title_backpack = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BACKPACKTITLE_XPATH)));
        title_backpack.click();
    }

    public void clickCart(){
        cart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CART_XPATH)));
        cart.click();
    }

    public String readButtonName(){
        removeCartButton_backpack = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BACKPACK_REMOVEBUTTON_XPATH)));
        String buttonName = removeCartButton_backpack.getText();
        return buttonName;
    }

    public String readInventoryTitleInCart(){
        inventoryItemNameInCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVENTORYITEMNAMEINCART_XPATH)));
        String inventory = inventoryItemNameInCart.getText();
        return inventory;
    }
    public void addItemToCart(){
        this.clickAddToCartButton();
        this.clickCart();;
    }

}
