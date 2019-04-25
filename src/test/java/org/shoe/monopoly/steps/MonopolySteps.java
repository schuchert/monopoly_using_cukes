package org.shoe.monopoly.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.mockito.Mockito;
import org.shoe.monopoly.Board;
import org.shoe.monopoly.Dice;
import org.shoe.monopoly.Location;
import org.shoe.monopoly.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class MonopolySteps {

    private Board board;
    private Player player;

    @Given("a board with the following named locations")
    public void a_board_with_the_following_named_locations(io.cucumber.datatable.DataTable dataTable) {
        List<Location> locations  = locationsFrom(dataTable);

        int locationCount = locations.size();
        for(int index = 0; index < locationCount; ++index) {
            locations.get(index).setNext(locations.get((index + 1) % locationCount));
        }

        board = new Board(locations);
    }

    private List<Location> locationsFrom(DataTable dataTable) {
        List<Location> locations = new ArrayList<>();
        int height = dataTable.height();
        int width = dataTable.width();


        for (int i = 0; i < width; ++i) {
            locations.add(new Location(dataTable.cell(0,i)));
        }
        for (int i = 1; i < height - 1; ++i) {
            locations.add(new Location(dataTable.cell(i, width - 1)));
        }
        for (int i = width - 1; i >= 0; --i) {
            locations.add(new Location(dataTable.cell(height - 1, i)));
        }
        for (int i = height - 2; i > 0; --i) {
            locations.add(new Location(dataTable.cell(i, 0)));
        }
        return locations;
    }

    @Given("a player named {string} is on {string}")
    public void a_player_named_is_on(String playerName, String locationName) {
        player = new Player(playerName, board.locationNamed(locationName));
    }

    @When("the player rolls {int}")
    public void the_player_rolls(Integer rollValue) {
        Dice stub = Mockito.mock(Dice.class);
        when(stub.faceValue()).thenReturn(rollValue);
        player.takeTurn(stub);
    }

    @Then("the player should land on {string}")
    public void the_player_should_land_on(String destination) {
        assertEquals(destination, player.current.name);
    }
}
