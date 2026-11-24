
package com.saucedemo.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.saucedemo.utils.ConfigReader;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    // Initialize browser with default from config or chrome
    public static void initializeDriver() {

        String browser = ConfigReader.getProperty("browser", "chrome");

        initializeDriver(browser);
    }

    // Initialize specified browser
    public static void initializeDriver(String browser) {

        if (driver.get() == null) {

            WebDriver webDriver;

            String targetBrowser = (browser != null && !browser.trim().isEmpty())
                    ? browser.trim() : "chrome";

            boolean isHeadless = Boolean.parseBoolean(
                    ConfigReader.getProperty("headless", "false"));

            // Browser selection
            if (targetBrowser.equalsIgnoreCase("chrome")) {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");

                if (isHeadless) {
                    options.addArguments("--headless=new");
                }

                webDriver = new ChromeDriver(options);

            } else if (targetBrowser.equalsIgnoreCase("firefox")) {

                FirefoxOptions options = new FirefoxOptions();

                if (isHeadless) {
                    options.addArguments("-headless");
                }

                webDriver = new FirefoxDriver(options);

            } else if (targetBrowser.equalsIgnoreCase("edge")) {

                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");

                if (isHeadless) {
                    options.addArguments("--headless=new");
                }

                webDriver = new EdgeDriver(options);

            } else {
                throw new IllegalArgumentException(
                        "Unsupported browser: " + targetBrowser);
            }

            try {

                // Configure browser timeouts
                int implicitWait = Integer.parseInt(
                        ConfigReader.getProperty("implicitWait", "0"));

                int pageLoadTimeout = Integer.parseInt(
                        ConfigReader.getProperty("pageLoadTimeout", "30"));

                webDriver.manage().timeouts()
                        .implicitlyWait(Duration.ofSeconds(implicitWait));

                webDriver.manage().timeouts()
                        .pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));

                webDriver.manage().timeouts()
                        .scriptTimeout(Duration.ofSeconds(30));

                // Maximize browser in headed mode
                if (!isHeadless) {
                    webDriver.manage().window().maximize();
                }

                // Store driver in ThreadLocal
                driver.set(webDriver);

            } catch (RuntimeException e) {

                webDriver.quit();
                throw e;
            }
        }
    }

    // Return current browser instance
    public static WebDriver getDriver() {

        if (driver.get() == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized! Call initializeDriver() first.");
        }

        return driver.get();
    }

    // Close browser
    public static void quitDriver() {

        WebDriver webDriver = driver.get();

        if (webDriver != null) {

            try {
                webDriver.quit();
            } finally {
                driver.remove();
            }
        }
    }
}
