
Feature: Login functionality

  Background:
    Given user is on the SauceDemo login page

  @smoke @Login
  Scenario: Login with valid credentials
    When user enters username "standard_user"
    And user enters password "secret_sauce"
    And user clicks on the login button
    Then user should be redirected to the products page

  @negative
  Scenario: Login with invalid credentials
    When user enters username "invalid_user"
    And user enters password "wrong_password"
    And user clicks on the login button
    Then user should see an error message
