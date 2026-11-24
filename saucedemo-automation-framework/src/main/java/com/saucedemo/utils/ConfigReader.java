
package com.saucedemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    // Load configuration file
    private static void loadProperties() {

        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input != null) {
                properties.load(input);
            } else {
                try (FileInputStream fileInput =
                        new FileInputStream(
                                "src/test/resources/config.properties")) {

                    properties.load(fileInput);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not load config.properties", e);
        }
    }

    // Get property value
    public static String getProperty(String key) {

        // System property takes priority
        String systemProperty = System.getProperty(key);

        if (systemProperty != null &&
                !systemProperty.trim().isEmpty()) {
            return systemProperty;
        }

        return properties.getProperty(key);
    }

    // Get property with default value
    public static String getProperty(
            String key, String defaultValue) {

        String value = getProperty(key);

        return (value != null && !value.trim().isEmpty())
                ? value : defaultValue;
    }
}
