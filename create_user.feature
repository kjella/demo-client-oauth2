Feature: See that a user is able to register
  Scenario Outline: A user is registering to CircleKID
    When I register my user details with <email>
    Then I should be able to return user with the same email

  Examples:
  | email             |
  | tes5t@email23.com |