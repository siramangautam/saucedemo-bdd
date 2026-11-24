
package com.saucedemo;

import org.openqa.selenium.WebDriver;

import com.saucedemo.base.DriverFactory;
import com.saucedemo.utils.ConfigReader;

public class DriverFactoryCheck {

    public static void main(String[] args) {

        try {
            // Initialize browser
            DriverFactory.initializeDriver();

            // Get driver instance
            WebDriver driver = DriverFactory.getDriver();

            // Open application
            driver.get(ConfigReader.getProperty("url"));

            // Print browser details
            System.out.println("Browser: " +
                    ConfigReader.getProperty("browser"));

            System.out.println("Page Title: " +
                    driver.getTitle());

            System.out.println("Current URL: " +
                    driver.getCurrentUrl());

        } finally {
            // Close browser
            DriverFactory.quitDriver();
        }
    }
}
