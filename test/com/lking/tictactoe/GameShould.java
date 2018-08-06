package com.lking.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.lking.tictactoe.Player.NONE;
import static com.lking.tictactoe.Player.X;
import static com.lking.tictactoe.Status.DRAW;
import static com.lking.tictactoe.Status.GAME_ON;
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
        game = game.play(Square.TOP_LEFT);
        game = game.play(Square.TOP_CENTER);

        assertThat(game.state(),equalTo(new GameState(GAME_ON, X)));
    }

    @Test
    void not_allow_a_square_to_be_played_twice() {
        var game = play(Square.TOP_LEFT, Square.TOP_RIGHT, Square.TOP_RIGHT);
        assertThat(game.state(),equalTo(new GameState(Status.SQUARE_TAKEN, X)));
    }

    // This set of plays that results in a draw for the game
    private final Square[] drawPlays = new Square[] {Square.TOP_LEFT, Square.TOP_CENTER, Square.TOP_RIGHT,
            Square.MID_RIGHT, Square.MID_CENTER, Square.BOTTOM_RIGHT,
            Square.MID_LEFT, Square.BOTTOM_LEFT, Square.BOTTOM_CENTER};

    // X O X
    // X X O
    // O X O
    @Test
    void detect_when_there_is_a_draw() {
        var game = play(drawPlays);
        assertThat(game.state(),equalTo(new GameState(DRAW, NONE)));
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
