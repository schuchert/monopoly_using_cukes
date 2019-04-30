package org.shoe.monopoly.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.shoe.monopoly.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class MonopolySteps {
    private Board board;
    private MonopolyGame game;
    private Location startSpy;
    private List<Player> players;

    @Given("a board with the following named locations")
    public void a_board_with_the_following_named_locations(DataTable dataTable) {
        List<Location> locations = locationsFrom(dataTable);

        int locationCount = locations.size();
        for (int index = 0; index < locationCount; ++index) {
            locations.get(index).setNext(locations.get((index + 1) % locationCount));
        }

        board = new Board(locations);
    }

    private List<Location> locationsFrom(DataTable dataTable) {
        List<Location> locations = new ArrayList<>();
        int height = dataTable.height();
        int width = dataTable.width();


        for (int i = 0; i < width; ++i) {
            locations.add(new Location(dataTable.cell(0, i)));
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
        players = Collections.singletonList(new Player(playerName, board.locationNamed(locationName)));
    }

    @When("the player rolls {int}")
    public void the_player_rolls(Integer rollValue) {
        Dice stub = mock(Dice.class);
        when(stub.faceValue()).thenReturn(rollValue);
        players.forEach(p -> p.takeTurn(stub));
    }

    @Then("the player should land on {string}")
    public void the_player_should_land_on(String destination) {
        assertEquals(destination, players.get(0).current.name);
    }

    @Given("players named: {string_list}")
    public void players_named_horse_car(List<String> playerNames) {
        startSpy = spy(new Location("go"));
        board = new Board(Collections.singletonList(startSpy));
        players = playerNames.stream().map((name) -> spy(new Player(name, startSpy))).collect(Collectors.toList());
        game = new MonopolyGame(board, players, new Dice());
    }

    @When("the game is played for {int} rounds")
    public void the_game_is_played_for_rounds(int rounds) {
        game.playFor(rounds);
    }

    @Then("each player will have taken {int} turns")
    public void each_player_will_have_taken_turns(int turnCount) {
        players.forEach(p -> verify(p, times(turnCount)).takeTurn(any(Dice.class)));
    }

    @Then("each player will have landed {int} times")
    public void players_will_have_landed_on_locations_times(int playerLandOnCount) {
        players.forEach(p -> verify(startSpy, times(playerLandOnCount)).landOn(p));
    }
}
