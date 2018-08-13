package com.lking.tictactoe;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Board {
    private Set<Square> takenSquares = null;

    public Board() {
        takenSquares = new HashSet<>();
    }

    private Board(Set<Square> takenSquares) {
        this.takenSquares = takenSquares;
    }

    public boolean alreadyTaken(Square square) {
        return takenSquares.contains(square);
    }

    public Board take(Square toPlay) {
        var newBoard = new HashSet<Square>(takenSquares);
        newBoard.add(toPlay);
        return new Board(newBoard);
    }

    public boolean isFull() {
        return takenSquares.size() == 9;
    }

    public boolean hasWon() {
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
        return winningCombos.anyMatch(combo -> combo.allMatch(square -> takenSquares.contains(square)));
    }
}
