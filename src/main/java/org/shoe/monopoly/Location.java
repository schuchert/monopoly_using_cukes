package org.shoe.monopoly;

public class Location {

    public final String name;
    private Location next;

    public Location(String name) {
        this.name = name;
    }

    public Location getNext() {
        return next;
    }

    public void setNext(Location location) {
        next = location;
    }

    Location nextBy(int distance) {
        if(distance > 0)
            return next.nextBy(distance - 1);
        return this;
    }

    public void landOn(Player player) {
    }
}
