package com.lking.tictactoe;

import static com.lking.tictactoe.Player.NOBODY;
import static com.lking.tictactoe.Player.O;
import static com.lking.tictactoe.Player.X;
import static com.lking.tictactoe.Status.DRAW;
import static com.lking.tictactoe.Status.GAME_ON;
import static com.lking.tictactoe.Status.SQUARE_TAKEN;

public class Game {
    private final Player currentPlayer;
    private Board board;
    private Status status;

    public Game() {
        board = new Board();
        status = GAME_ON;
        currentPlayer = null;
    }

    private Game(Board board, Player currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    private Game(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private Game(Status status, Board board, Player currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        if (board.isFull()) {
            this.status = DRAW;
        }
        else {
            this.status = status;
        }
    }

    public GameState state() {
        if (status == DRAW) {
            return new GameState(status, NOBODY);
        }
        else {
            return new GameState(status, nextPlayer());
        }
    }

    private Player nextPlayer() {
        if (currentPlayer == null || currentPlayer == NOBODY)
            return X;
        else
            return currentPlayer == X ? O : X;
    }

    // Return a Game object as for functional programming, this has us not modifying
    // the state of the Game object that called this.
    public Game play(Square play) {
        if (board.alreadyTaken(play)) {
            return new Game(SQUARE_TAKEN, board, currentPlayer);
        } else {
            // Play the square
            return new Game(GAME_ON, board.take(play), nextPlayer());
        }
    }
}
