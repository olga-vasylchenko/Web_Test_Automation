Feature: Search results sorting options work correctly

  Scenario: 'Cheapest' sorting option works correctly for train results
    Given I perform a search for today
    When I select Train mode and Cheapest sorting options on Search Results page
    Then the results are sorted by price in ascending order