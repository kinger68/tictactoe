package com.lking.tictactoe;

import static com.lking.tictactoe.Player.X;
import static com.lking.tictactoe.Status.GAME_ON;

public class Game {
    GameState state;

    public GameState state() {
        return state;
    }

    // Return a Game object as for functional programming, this has us not modifying
    // the state of the Game object that called this.
    public Game play() {
        return new Game();
    }
}
