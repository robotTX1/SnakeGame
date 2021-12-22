package com.snakegame.snakegame.model;

public class Board {
    private final int width;
    private final int height;
    private final CellState[][] board;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new CellState[height][width];
        clear();
    }

    public void setCellState(int x, int y, CellState newState) {
        if(!isValidPosition(x, y)) return;

        this.board[y][x] = newState;
    }

    public CellState getCellState(int x, int y) {
        return board[y][x];
    }

    public void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.board[y][x] = CellState.EMPTY;
            }
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
