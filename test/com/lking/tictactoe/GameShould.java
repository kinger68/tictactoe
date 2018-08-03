package com.lking.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.lking.tictactoe.Player.X;
import static com.lking.tictactoe.Player.O;
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
    void wait_for_o_to_play_after_x() {
        var game = new Game();
        game = game.play();
        assertThat(game.state(),equalTo(new GameState(GAME_ON, O)));
    }

    @Test
    void alternate_the_players() {
        var game = new Game();
        game = game.play();
        game = game.play();

        assertThat(game.state(),equalTo(new GameState(GAME_ON, X)));
    }
}
