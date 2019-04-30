package org.shoe.monopoly;

import java.util.List;
import java.util.Optional;

public class Board {
    private List<Location> locations;

    public Board(List<Location> locations) {
        this.locations = locations;
    }

    public Location locationNamed(String locationName) {
        Optional<Location> first = locations.stream().filter(l -> l.name.equals(locationName)).findFirst();
        return first.orElseThrow(()-> new RuntimeException(String.format("Location named '%s' not found", locationName)));
    }
}
