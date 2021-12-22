package com.snakegame.snakegame.model;

public class SnakeBody implements SnakePiece{
    private Position position;
    private SnakePiece nextPiece;

    public SnakeBody(Position position) {
        this.position = position;
    }

    @Override
    public void setPosition(Position position) {
        if(!isTailPiece()) nextPiece.setPosition(this.position);
        this.position = position;
    }

    @Override
    public boolean isTailPiece() {
        return nextPiece == null;
    }

    @Override
    public void addNewPiece() {
        this.nextPiece = new SnakeBody(this.position);
    }

    @Override
    public void grow() {
        if(isTailPiece()) {
            addNewPiece();
        } else {
            this.nextPiece.grow();
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public SnakePiece getNextPiece() {
        return nextPiece;
    }
}
