package com.snakegame.snakegame.controller;

import com.snakegame.snakegame.model.Board;
import com.snakegame.snakegame.model.Position;
import com.snakegame.snakegame.model.Snake;
import com.snakegame.snakegame.model.SnakePiece;

public class StandardGameRule implements GameRule {
    @Override
    public CollisionType testForCollision(Board board, Snake snake, Position food) {
        SnakePiece piece = snake.getNextPiece();
        if(piece != null) {
            do {
                if(snake.getPosition().equals(piece.getPosition())) {
                    return CollisionType.COLLISION;
                }
                piece = piece.getNextPiece();
            } while(piece != null);
        }

        if(snake.getPosition().equals(food)) {
            return CollisionType.FOOD;
        }

        if(snake.getPosition().getX() < 0 || snake.getPosition().getY() < 0) return CollisionType.COLLISION;
        if(snake.getPosition().getX() == board.getWidth() || snake.getPosition().getY() == board.getHeight()) return CollisionType.COLLISION;

        return CollisionType.NONE;
    }

    @Override
    public Position nextPosition(Board board, Snake snake) {
        switch (snake.getDir()) {
            case UP:
                return new Position(snake.getPosition().getX(), snake.getPosition().getY() - 1);
            case RIGHT:
                return new Position(snake.getPosition().getX() + 1, snake.getPosition().getY());
            case DOWN:
                return new Position(snake.getPosition().getX(), snake.getPosition().getY() + 1);
            case LEFT:
                return new Position(snake.getPosition().getX() - 1, snake.getPosition().getY());
        }
        throw new IllegalStateException("Illegal direction: " + snake.getDir().toString());
    }
}
