package com.snakegame.snakegame.model;

public interface SnakePiece {
    void setPosition(Position position);
    boolean isTailPiece();
    void addNewPiece();
    void grow();
    Position getPosition();
    SnakePiece getNextPiece();
}
