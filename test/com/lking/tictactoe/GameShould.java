package com.lking.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static com.lking.tictactoe.Player.NOBODY;
import static com.lking.tictactoe.Player.X;
import static com.lking.tictactoe.Square.*;
import static com.lking.tictactoe.Status.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@DisplayName("Game adjudicator should")
public class GameShould {
    @Test
    void wait_for_x_to_play_first() {
        var game = new Game();
        assertThat(game.state(),equalTo(new GameState(GAME_ON, X)));
    }

    @Test
    void alternate_the_players() {
        var game = new Game();
        game = game.play(TOP_LEFT);
        game = game.play(TOP_CENTER);

        assertThat(game.state(),equalTo(new GameState(GAME_ON, X)));
    }

    @Test
    void not_allow_a_square_to_be_played_twice() {
        var game = play(TOP_LEFT, TOP_RIGHT, TOP_CENTER, BOTTOM_RIGHT, BOTTOM_RIGHT);
        assertThat(game.state(),equalTo(new GameState(SQUARE_TAKEN, X)));
    }

    // This set of plays that results in a draw for the game
    // X O X
    // X X O
    // O X O
    @Test
    void recognize_a_draw() {
        var game = play(TOP_LEFT, TOP_CENTER, TOP_RIGHT,
                        MID_RIGHT, MID_CENTER, BOTTOM_RIGHT,
                        MID_LEFT, BOTTOM_LEFT, BOTTOM_CENTER);
        assertThat(game.state(),equalTo(new GameState(DRAW, NOBODY)));
    }

    // This set of plays that results in X Winning Top Row
    @ParameterizedTest
    @CsvSource({
            "TOP_LEFT, MID_LEFT, TOP_CENTER, MID_CENTER, TOP_RIGHT",
            "MID_LEFT, TOP_LEFT, MID_CENTER, TOP_CENTER, MID_RIGHT",
            "BOTTOM_LEFT, MID_LEFT, BOTTOM_CENTER, MID_CENTER, BOTTOM_RIGHT",
            "TOP_LEFT, TOP_CENTER, MID_LEFT, TOP_RIGHT, BOTTOM_LEFT",
            "TOP_CENTER, TOP_LEFT, MID_CENTER, TOP_RIGHT, BOTTOM_CENTER",
            "TOP_RIGHT, MID_CENTER, MID_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT",
            "TOP_LEFT, MID_LEFT, MID_CENTER, MID_RIGHT, BOTTOM_RIGHT",
            "TOP_RIGHT, MID_LEFT, MID_CENTER, MID_RIGHT, BOTTOM_LEFT",
    })
    void recognize_that_x_has_won(Square s1, Square s2, Square s3, Square s4, Square s5) {
        var game = play(s1, s2, s3, s4, s5);
        assertThat(game.state(),equalTo(new GameState(X_WINS, NOBODY)));
    }

    @Test
    void recognize_that_o_has_won() {
        var game = play(MID_CENTER, TOP_LEFT, BOTTOM_RIGHT, MID_LEFT, TOP_RIGHT, BOTTOM_LEFT);
        assertThat(game.state(),equalTo(new GameState(O_WINS, NOBODY)));
    }

    // O X X
    // X O X
    // O O X
    @Test
    void recognize_x_win_on_last_move() {
        var game = play(TOP_CENTER, TOP_LEFT, TOP_RIGHT,
                        MID_CENTER, MID_LEFT, BOTTOM_LEFT,
                        MID_RIGHT, BOTTOM_CENTER, BOTTOM_RIGHT);
        assertThat(game.state(),equalTo(new GameState(X_WINS, NOBODY)));
    }

    // X O X
    // X O X
    // O O X
    @Test
    void recognize_o_win_on_last_move() {
        var game = play(TOP_LEFT, TOP_CENTER, MID_LEFT,
                        MID_CENTER, TOP_RIGHT, BOTTOM_LEFT,
                        BOTTOM_RIGHT, BOTTOM_CENTER);
        assertThat(game.state(),equalTo(new GameState(O_WINS, NOBODY)));
    }

    private Game play(Square... squares) {
        // This goes over all the squares, and reduces it to 1 game starting with
        // a seed value of the game, the accumulator (playing), and a combine function
        // which for this purpose would be nothing as we don't have to combine a game
        // with a game.
        return Arrays.stream(squares)
                .reduce(new Game(), Game::play, (a, b) -> null);

    }

}
