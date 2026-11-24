package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {

    private final WebDriver driver;

    private final By pageTitle = By.className("title");
    private final By inventoryContainer = By.id("inventory_container");
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductsPageDisplayed() {
        try {
            return driver.findElement(pageTitle).isDisplayed() 
                    && driver.findElement(pageTitle).getText().equalsIgnoreCase("Products");
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void addProductToCart(String productName) {
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(buttonId)).click();
    }

    public String getCartItemCount() {
        try {
            WebElement badge = driver.findElement(shoppingCartBadge);
            return badge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCart() {
        driver.findElement(shoppingCartLink).click();
    }
}
