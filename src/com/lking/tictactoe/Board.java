package com.lking.tictactoe;

import java.util.HashSet;
import java.util.Set;

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
}
