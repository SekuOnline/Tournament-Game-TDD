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

    #Beginning of Melee 1
    Given Fred is the leader of the melee
    Given Fred is given "SO11 AL"
    And   Joe is given "SO6 SW1 DE1 AR1 AL"
    And   Paul is given "SO7"
    Given Fred plays AL
    And   Fred chooses value 1 for AL
    And   Fred plays SO11
    Given Joe plays SW1
    And   Joe plays DE1
    And   Joe plays AR1
    And   Joe plays AL
    And   Joe plays SO6
    Given Paul plays SO7
    Then  The melee occurs
    And   Joe should be the loser
    And   Injury points should be 25
    And   Joe has taken 25 damage

    #Beginning of Melee 2
    Given Joe is the leader of the melee
    Given Joe is given "AR8"
    Given Paul is given "ME"
    Given Fred is given "AP"
    Given Joe plays AR8
    Given Paul plays ME
    And   Paul chooses value 16 for ME
    And   Paul chooses value 9 for ME
    Given Fred plays AP
    And   Fred chooses value 20 for AP
    And   Fred chooses value 10 for AP
    Then  The melee occurs
    And   Joe should be the loser
    And   Injury points should be 40
    And   Joe has taken 65 damage

    #Beginning of Melee 3
    Given Joe is the leader of the melee

    Given Joe is given "SW9"
    Given Paul is given "SW7 AL"
    Given Fred is given "SW3"

    Given Joe plays SW9
    And   Paul plays AL
    And   Paul plays SW7
    And   Fred plays SW3

    Then  The melee occurs
    And   Fred should be the loser
    And   Injury points should be 25
    And   Fred has taken 25 damage


    #Beginning of Melee 4
    Given Fred is the leader of the melee

    Given Fred is given "DE9"
    And   Joe is given "DE6 AL"
    And   Paul is given "DE1"

    Given Fred plays DE9
    And   Joe plays AL
    And   Joe plays DE6
    And   Paul plays DE1
    
    Then  The melee occurs
    And   Paul should be the loser
    And   Injury points should be 25
    And   Paul has taken 25 damage

