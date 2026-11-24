package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.saucedemo.base.DriverFactory;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    private void initPages() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
            loginPage = new LoginPage(driver);
            productsPage = new ProductsPage(driver);
        }
    }

    @Given("user is on the SauceDemo login page")
    public void user_is_on_the_saucedemo_login_page() {
        initPages();
        String url = ConfigReader.getProperty("url", "https://www.saucedemo.com/");
        driver.get(url);
    }

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        initPages();
        loginPage.enterUsername(username);
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        initPages();
        loginPage.enterPassword(password);
    }

    @When("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        initPages();
        loginPage.clickLogin();
    }

    @Then("user should be redirected to the products page")
    public void user_should_be_redirected_to_the_products_page() {
        initPages();
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page was not displayed after login!");
    }

    @Then("user should see an error message")
    public void user_should_see_an_error_message() {
        initPages();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed!");
    }
}
