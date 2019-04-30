package org.shoe.monopoly;

public class Player {
    private final String name;
    public Location current;

    public Player(String name, Location startingLocation) {
        this.name = name;
        this.current = startingLocation;
    }

    public void takeTurn(Dice dice) {
        dice.roll();
        int distance = dice.faceValue();
        current = current.nextBy(distance);
        current.landOn(this);
    }
}
