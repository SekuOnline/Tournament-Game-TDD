Feature: Melee
  Run through an individual melee with the correct loser and injury points.

  Scenario Outline: The loser and number of injury points are correctly determined.
    Given Player "1" plays the card <first>
    And Player "2" plays the card <second>
    And Player "3" plays the card <third>
    And Player "4" plays the card <fourth>
    Then  The loser should be <loser>
    And   The injury points should be <injury>

    Examples:
    | first | second  | third | fourth  | loser | injury  |
    | AR13  | AR5     | AR12  | AR7     | P2    | 20      |
    | SW6   | SW7     | SW15  | SW13    | P1    | 30      |
    | SO11  | SO12    | SO6   | SO5     | P4    | 40      |
    | DE9   | DE14    | DE1   | DE5     | P3    | 25      |
    | AR13  | AR8     | ME7   | AR14    | P3    | 45      |
    | AR13  | AR8     | ME15  | AP14    | P2    | 45      |
    | AR13  | AR8     | AP7   | AR14    | P3    | 25      |
    | AR13  | AR8     | AP15  | AR14    | P2    | 25      |
    | DE13  | ME14    | ME14  | ME14    | P1    | 80      |
    | DE8   | ME14    | DE9   | AP10    | P1    | 45      |
    | SW10  | SW1     | SW2   | ME1     | P3    | 40      |
    | SW10  | AP10    | SW15  | ME10    | P3    | 40      |
    | SW10  | SW1     | AL2   | ME2     | P2    | 40      |
    | AL2   | DE7     | SW6   | AR8     | P1    | 35      |
    | AL6   | ME7     | AP8   | SO5     | P4    | 45      |
    | AL12  | DE7     | SW6   | AR8     | P3    | 35      |
    | MESW13| SW10    | SW1   | AL2     | P3    | 40      |
    | APSW13| SW10    | SW1   | SW2     | P3    | 20      |
    | MESW13| SW10    | AL10  | AP10    | P1    | 40      |
    | APSW13| SW10    | AL10  | AP10    | P1    | 20      |
    | MEDE13| DE7     | ME14  | DE10    | P2    | 70      |
    | MEDE13| AP7     | ME14  | DE10    | P2    | 65      |
    | SW10  | AP10    | SW11  | ME11    |-      | 0       |
    | SW10  | AP10    | AL10  | ME10    |-      | 0       |