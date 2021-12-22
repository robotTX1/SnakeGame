package com.snakegame.snakegame.model;

public class Snake implements SnakePiece{
    private Position position;
    private Direction dir = Direction.UP;
    private SnakePiece nextPiece;


    public Snake(Position position) {
        this.position = position;
    }

    @Override
    public void setPosition(Position position) {
        if(hasNextPiece()) nextPiece.setPosition(this.position);
        this.position = position;
    }

    @Override
    public boolean isTailPiece() {
        return false;
    }

    @Override
    public void addNewPiece() {
        this.nextPiece = new SnakeBody(this.position);
    }

    @Override
    public void grow() {
        if(hasNextPiece()) {
            this.nextPiece.grow();
        } else {
            addNewPiece();
        }
    }

    private boolean hasNextPiece() {
        return nextPiece != null;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public SnakePiece getNextPiece() {
        return nextPiece;
    }
}
