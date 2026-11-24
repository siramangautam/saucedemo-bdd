Feature: Cart functionality

  Background:
    Given user is on the SauceDemo login page
    When user enters username "standard_user"
    And user enters password "secret_sauce"
    And user clicks on the login button
    And user adds product "Sauce Labs Backpack" to cart

  @smoke
  Scenario: View product in cart
    When user clicks on the shopping cart
    Then cart should contain product "Sauce Labs Backpack"
