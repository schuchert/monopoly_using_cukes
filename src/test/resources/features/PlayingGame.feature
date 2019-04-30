Feature: Playing a simple game

  Scenario: Game Demonstration
    Given players named: horse, car
    When the game is played for 20 rounds
    Then each player will have taken 20 turns
    And each player will have landed 20 times