package com.snakegame.snakegame;

import com.snakegame.snakegame.model.Board;
import com.snakegame.snakegame.util.event.Event;

public class BoardChangedEvent implements Event {

    private final Board board;


    public BoardChangedEvent(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
