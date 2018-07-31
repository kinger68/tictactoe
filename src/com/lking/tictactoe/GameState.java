package com.lking.tictactoe;

public class GameState {
    private final Status status;
    private final Player nextUp;

    public GameState(Status status, Player nextUp) {
        this.status = status;
        this.nextUp = nextUp;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof  GameState
                && ((GameState) obj).status == this.status
                && ((GameState) obj).nextUp == this.nextUp;
    }

    @Override
    public String toString() {
        return "Status : " + status + ", next Up : " + nextUp;
    }
}
