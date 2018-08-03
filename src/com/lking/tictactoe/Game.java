package com.lking.tictactoe;

import static com.lking.tictactoe.Player.O;
import static com.lking.tictactoe.Player.X;
import static com.lking.tictactoe.Status.GAME_ON;

public class Game {
    private final Player currentPlayer;

    public Game() {
        currentPlayer = X;
    }

    private Game(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameState state() {
        return new GameState(GAME_ON, currentPlayer);
    }

    // Return a Game object as for functional programming, this has us not modifying
    // the state of the Game object that called this.
    public Game play() {
        return new Game(O);
    }
}
