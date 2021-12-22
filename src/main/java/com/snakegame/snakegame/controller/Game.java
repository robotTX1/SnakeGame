package com.snakegame.snakegame.controller;

import com.snakegame.snakegame.DirectionChangedEvent;
import com.snakegame.snakegame.BoardChangedEvent;
import com.snakegame.snakegame.model.*;
import com.snakegame.snakegame.util.event.EventBus;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Game {
    private static final int UPDATE_TIME = 100;

    private final Snake snake;
    private Position food;
    private Timeline timeline;
    private final Board board;
    private final EventBus eventBus;
    private Direction tmpDirection = Direction.UP;
    private final GameRule gameRule;

    public Game(Board board, EventBus eventBus) {
        this.board = board;
        this.eventBus = eventBus;
        this.snake = new Snake(new Position(board.getWidth() / 2, board.getHeight() / 2));
        snake.grow();
        snake.grow();

//        gameRule = new StandardGameRule();
        gameRule = new LoopbackGameRule();
        eventBus.listenFor(DirectionChangedEvent.class, this::handleDirectionChanged);
    }

    private void handleDirectionChanged(DirectionChangedEvent event) {
        if(event.getNewDir().getDir() != snake.getDir().getDir()) {
            tmpDirection = event.getNewDir();
        }
    }

    private void resetTimer() {
        if(timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(new KeyFrame(Duration.millis(UPDATE_TIME), this::moveSnake));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void startGame() {
        resetTimer();
        spawnFood();
    }

    private void moveSnake() {
        snake.setDir(tmpDirection);
        Position newPosition = gameRule.nextPosition(board, snake);
        snake.setPosition(newPosition);
        checkCollisions();
        updateBoard();
    }

    private void checkCollisions() {
        CollisionType collisionType = gameRule.testForCollision(board, snake, food);
        switch (collisionType) {
            case COLLISION:
                gameOver();
                break;
            case FOOD:
                increaseScore();
                break;
            case NONE:
                return;
            default:
                throw new IllegalStateException("Illegal collision type " + collisionType.toString());
        }
    }

    private void increaseScore() {
        snake.grow();
        spawnFood();
    }

    private void gameOver() {
        timeline.stop();
    }

    private void moveSnake(ActionEvent actionEvent) {
        moveSnake();
    }

    private void spawnFood() {
        int x, y;
        do {
            x = (int) (Math.random() * board.getWidth());
            y = (int) (Math.random() * board.getHeight());
        } while(board.getCellState(x, y) != CellState.EMPTY);

        food = new Position(x, y);
    }

    private void updateBoard() {
        board.clear();

        board.setCellState(food.getX(), food.getY(), CellState.FOOD);

        SnakePiece piece = snake;
        do {
            board.setCellState(piece.getPosition().getX(), piece.getPosition().getY(), CellState.SNAKE);
            piece = piece.getNextPiece();
        } while(piece != null);

        eventBus.emit(new BoardChangedEvent(board));
    }
}
