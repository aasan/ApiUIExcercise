# Api testing exercise
Feature: Verify Wikipedia website return search result
  and it can be translate to available languages

  @UIExerciseDemo
  Scenario: Verify Wikipedia website search translate to different language

    Given I navigate to wikipedia website
    Then I should see Search textbox
    And By default language is selected as "English"
    And I enter "London" in the search textbox
    When I click search button on the page
    Then Search result page should displayed with heading "London"
    And I should see other languages available for translate
    And I navigate to result in other language to verify it contains English version link