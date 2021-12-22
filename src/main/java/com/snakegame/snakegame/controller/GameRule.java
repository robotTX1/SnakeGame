package com.snakegame.snakegame.controller;

import com.snakegame.snakegame.model.Board;
import com.snakegame.snakegame.model.Position;
import com.snakegame.snakegame.model.Snake;

public interface GameRule {
    CollisionType testForCollision(Board board, Snake snake, Position food);
    Position nextPosition(Board board, Snake snake);
}
