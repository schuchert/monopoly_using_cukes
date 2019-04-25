Feature: Player Moving Around Board
  Description: Shows results of player moving around the board

  Background: Small game board
    Given a board with the following named locations
      | Go         | Mediterranean Avenue | Community Chest | Just Visiting     |
      | Boardwalk  |                      |                 | St. Charles Place |
      | Luxury Tax |                      |                 | Electric Company  |
      | Park Place |                      |                 | States Avenue     |
      | Chance     |                      |                 | Virginia Avenue   |
      | Go To Jail | B. & O. Railroad     | Water Works     | Free Parking      |

  Scenario Outline: Starting at <start> and rolling <roll>
    Given a player named "Horse" is on "<start>"
    When the player rolls <roll>
    Then the player should land on "<end>"

    Examples:
      | start        | roll | end                  |
      | Go           | 7    | Virginia Avenue      |
      | Free Parking | 8    | Go                   |
      | Boardwalk    | 2    | Mediterranean Avenue |