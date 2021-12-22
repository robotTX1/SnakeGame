package com.snakegame.snakegame.controller;

import com.snakegame.snakegame.model.Board;
import com.snakegame.snakegame.model.Position;
import com.snakegame.snakegame.model.Snake;
import com.snakegame.snakegame.model.SnakePiece;

public class LoopbackGameRule implements GameRule {
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

        return CollisionType.NONE;
    }

    @Override
    public Position nextPosition(Board board, Snake snake) {
        switch (snake.getDir()) {
            case UP:
                if(snake.getPosition().getY() == 0) return new Position(snake.getPosition().getX(), board.getHeight() - 1);
                else return new Position(snake.getPosition().getX(), snake.getPosition().getY() - 1);
            case RIGHT:
                if(snake.getPosition().getX() == board.getWidth() - 1) return new Position(0, snake.getPosition().getY());
                else return new Position(snake.getPosition().getX() + 1, snake.getPosition().getY());
            case DOWN:
                if(snake.getPosition().getY() == board.getHeight() - 1) return new Position(snake.getPosition().getX(), 0);
                else return new Position(snake.getPosition().getX(), snake.getPosition().getY() + 1);
            case LEFT:
                if(snake.getPosition().getX() == 0) return new Position(board.getWidth()-1, snake.getPosition().getY());
                else return new Position(snake.getPosition().getX() - 1, snake.getPosition().getY());
        }
        throw new IllegalStateException("Illegal direction: " + snake.getDir().toString());
    }
}
