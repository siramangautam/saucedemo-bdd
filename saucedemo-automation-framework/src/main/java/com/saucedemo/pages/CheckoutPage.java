package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private final WebDriver driver;

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCustomerInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public boolean isOrderCompleted() {
        try {
            return driver.findElement(completeHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
