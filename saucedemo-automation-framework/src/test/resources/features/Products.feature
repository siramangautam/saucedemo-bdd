Feature: Products functionality

  Background:
    Given user is on the SauceDemo login page
    When user enters username "standard_user"
    And user enters password "secret_sauce"
    And user clicks on the login button

  @smoke
  Scenario: Verify products page display
    Then user should be redirected to the products page

  Scenario: Add product to cart
    When user adds product "Sauce Labs Backpack" to cart
    Then cart badge count should be "1"
