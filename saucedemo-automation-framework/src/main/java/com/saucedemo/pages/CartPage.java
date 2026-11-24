package com.saucedemo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    private final WebDriver driver;

    private final By pageTitle = By.className("title");
    private final By cartItems = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartPageDisplayed() {
        try {
            return driver.findElement(pageTitle).getText().equalsIgnoreCase("Your Cart");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isItemInCart(String itemName) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }
}
