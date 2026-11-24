package com.saucedemo.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.saucedemo.base.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                WebDriver driver = DriverFactory.getDriver();
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        } finally {
            DriverFactory.quitDriver();
        }
    }
}
