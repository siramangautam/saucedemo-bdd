package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.saucedemo.base.DriverFactory;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.ProductsPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {

    private WebDriver driver;
    private ProductsPage productsPage;
    private CartPage cartPage;

    private void initPages() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
            productsPage = new ProductsPage(driver);
            cartPage = new CartPage(driver);
        }
    }

    @When("user adds product {string} to cart")
    public void user_adds_product_to_cart(String productName) {
        initPages();
        productsPage.addProductToCart(productName);
    }

    @When("user clicks on the shopping cart")
    public void user_clicks_on_the_shopping_cart() {
        initPages();
        productsPage.clickCart();
    }

    @Then("cart badge count should be {string}")
    public void cart_badge_count_should_be(String count) {
        initPages();
        Assert.assertEquals(productsPage.getCartItemCount(), count);
    }

    @Then("cart should contain product {string}")
    public void cart_should_contain_product(String productName) {
        initPages();
        Assert.assertTrue(cartPage.isItemInCart(productName), "Expected item " + productName + " not found in cart!");
    }
}
