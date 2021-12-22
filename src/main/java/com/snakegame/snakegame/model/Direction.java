package com.snakegame.snakegame.model;

public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(0),
    LEFT(1);

    private int dir;

    Direction(int dir) {
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }
}
