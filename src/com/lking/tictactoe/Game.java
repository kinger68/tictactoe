package com.lking.tictactoe;

import static com.lking.tictactoe.Player.NOBODY;
import static com.lking.tictactoe.Player.O;
import static com.lking.tictactoe.Player.X;
import static com.lking.tictactoe.Status.*;

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

        if (board.hasWon(currentPlayer)) {
            this.status = currentPlayer == X ? X_WINS : O_WINS;
        }
        else if (board.isFull()) {
            this.status = DRAW;
        }
        else {
            this.status = status;
        }
    }

    public GameState state() {
        if (gameIsOver()) {
            return new GameState(status, NOBODY);
        }
        else {
            return new GameState(status, nextPlayer());
        }
    }

    // Return a Game object as for functional programming, this has us not modifying

    // the state of the Game object that called this.
    public Game play(Square play) {
        if (gameIsOver()) {
            return this;
        }
        if (board.alreadyTaken(play)) {
            return new Game(SQUARE_TAKEN, board, currentPlayer);
        } else {
            // Play the square
            return new Game(GAME_ON, board.take(play, nextPlayer()), nextPlayer());
        }
    }

    private boolean gameIsOver() {
        return status == DRAW || status == X_WINS || status == O_WINS;
    }

    private Player nextPlayer() {
        if (currentPlayer == null || currentPlayer == NOBODY)
            return X;
        else
            return currentPlayer == X ? O : X;
    }
}
