package com.lking.tictactoe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private Map<Square, Player> takenSquares = null;

    public Board() {
        takenSquares = new HashMap<>();
    }

    private Board(Map<Square, Player> takenSquares) {
        this.takenSquares = takenSquares;
    }

    public boolean alreadyTaken(Square square) {
        return takenSquares.keySet().contains(square);
    }

    public Board take(Square toPlay, Player player) {
        var newBoard = new HashMap<Square, Player>(takenSquares);
        newBoard.put(toPlay, player);
        return new Board(newBoard);
    }

    public boolean isFull() {
        return takenSquares.size() == 9;
    }

    // A game is won if all the squares in a particular pattern are taken by the
    // same player
    public boolean hasWon(Player player) {
        var winningCombos = Stream.of(
                Stream.of(Square.TOP_LEFT, Square.TOP_CENTER, Square.TOP_RIGHT),
                Stream.of(Square.MID_LEFT, Square.MID_CENTER, Square.MID_RIGHT),
                Stream.of(Square.BOTTOM_LEFT, Square.BOTTOM_CENTER, Square.BOTTOM_RIGHT),
                Stream.of(Square.TOP_LEFT, Square.MID_LEFT, Square.BOTTOM_LEFT),
                Stream.of(Square.TOP_CENTER, Square.MID_CENTER, Square.BOTTOM_CENTER),
                Stream.of(Square.TOP_RIGHT, Square.MID_RIGHT, Square.BOTTOM_RIGHT),
                Stream.of(Square.TOP_LEFT, Square.MID_CENTER, Square.BOTTOM_RIGHT),
                Stream.of(Square.BOTTOM_LEFT, Square.MID_CENTER, Square.TOP_RIGHT)
        );
        return winningCombos.anyMatch(combo -> combo.allMatch(squaresTakenBy(player)::contains));
    }

    // Find all the squares taken by the 'player' argument
    private Set<Square> squaresTakenBy(Player player) {
        return takenSquares.entrySet().stream()
                .filter(entry -> entry.getValue() == player)
                .map(entry -> entry.getKey())
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        String printBoard = "Foo";
        return printBoard;
    }
}
