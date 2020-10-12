# Api testing excercise
Feature: Verify Pet Api response for all pets with given status and name

  @apiDemo
  Scenario: List all pets with status equal to available and the name equal to doggie

    Given I submit the GET request to get all pets with status "Available"
    Then  "Pets" Api should return valid status code
    And I should receive correct header content
    And I should receive valid response
    And I display list of all pets with name "doggie" and status is "available"
