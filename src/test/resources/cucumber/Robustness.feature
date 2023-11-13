Feature: Testing for Robustness where the players attempt to break the rules of the game.

  Scenario: Testing invalid cases for game set-up
    Given An invalid number of players 8
    And   A valid number of players 3

    Given An invalid name for Player 1 ""
    And   A valid name for Player 1 "Fred"
    Given An invalid name for Player 2 ""
    And   A valid name for Player 2 "Joe"
    Given An invalid name for Player 3 ""
    And   A valid name for Player 3 "Paul"
    Given An invalid number for initial health points -10
    And   A valid number for initial health points 50
    Then  Player count should be 3
    And   Player 1 name should be "Fred"
    And   Player 2 name should be "Joe"
    And   Player 3 name should be "Paul"
    And   Initial health points should be 50