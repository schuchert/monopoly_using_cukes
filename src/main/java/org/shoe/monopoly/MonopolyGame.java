package org.shoe.monopoly;

import java.util.List;
import java.util.stream.IntStream;

public class MonopolyGame {
    private final Board board;
    private final List<Player> players;
    private Dice dice;

    public MonopolyGame(Board board, List<Player> players, Dice dice) {
        this.board = board;
        this.players = players;
        this.dice = dice;
    }

    public void playFor(int rounds) {
        IntStream.range(0, rounds).forEach(round -> players.forEach(p -> p.takeTurn(dice)));
    }
}
