package com.snakegame.snakegame;

import com.snakegame.snakegame.model.Direction;
import com.snakegame.snakegame.util.event.Event;

public class DirectionChangedEvent implements Event {
    private Direction newDir;

    public DirectionChangedEvent(Direction newDir) {
        this.newDir = newDir;
    }

    public Direction getNewDir() {
        return newDir;
    }
}
