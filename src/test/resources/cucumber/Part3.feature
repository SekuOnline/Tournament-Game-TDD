Feature: Testing multiple scenarios

  #Scenario B
  Scenario: B
    Given A number of players 3
    And   A name for player "A"
    And   A name for player "B"
    And   A name for player "C"
    And   A valid number for initial health points 20
    And   Game is initialized

    Given A is given "SW2 AR2"
    And   B is given "SW1"
    And   C is given "SW3 AR3"

    Given The melee begins
    Given A plays SW2
    And   B plays SW1
    And   C plays SW3

    Given The round occurs

    Then The winners should be "A C"
    And  A should have health greater than 0
    And  B should have health less than 0
    And  C should have health greater than 0

  Scenario: C
    Given A number of players 3
    And   A name for player "A"
    And   A name for player "B"
    And   A name for player "C"
    And   A valid number for initial health points 50
    And   Game is initialized

    Given A is given "MESW AR2 DE2 SO2 AR6 SW6 DE6 SO6 SW14 AR14 DE14 SO14"
    And   B is given "SW1 AR1 DE1 SO1 AR5 SW5 DE5 SO5 SW13 AR13 DE13 SO13"
    And   C is given "SW2 AR3 DE3 SO3 AR7 SW7 DE7 SO7 SW15 AR15 DE15 SO15"

    Given The melee begins
    Given A plays MESW
    And   A chooses value 2 for MESW
    And   B plays SW1
    And   C plays SW2

    Given A plays AR2
    And   B plays AR1
    And   C plays AR3

    Given The round occurs
    Then The winners should be "A C"
