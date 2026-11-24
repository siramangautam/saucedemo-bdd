package com.saucedemo.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

    features = "src/test/resources/features",

    glue = {
        "com.saucedemo.stepdefinitions",
        "com.saucedemo.hooks"
    },

    tags = "@smoke",

    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"
    },

    monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
/*
3. Understand the annotations
@CucumberOptions

This annotation configures how Cucumber executes your tests.
Configuration	Purpose
features	Location of .feature files
glue	Packages containing Hooks and Step Definitions
plugin	Reporting configuration
pretty	Readable console output
html	Generates HTML report
json	Generates JSON report
monochrome	Removes unnecessary console formatting characters

AbstractTestNGCucumberTests

This class integrates Cucumber with TestNG.

It allows TestNG to execute Cucumber scenarios as test cases.

@DataProvider

The DataProvider supplies scenarios to TestNG for execution.

We have set:
@DataProvider(parallel = false)


This means scenarios execute sequentially rather than in parallel.
4. How to execute the framework
Once the class is created, open your terminal at the project root.

Run:
mvn clean test


Maven will:
1. Clean the previous build.
2. Compile the Java source files.
3. Compile the test files.
4. Discover and execute the TestNG Cucumber runner.
5. Execute the Hooks and Step Definitions.
6. Generate reports.
*/

