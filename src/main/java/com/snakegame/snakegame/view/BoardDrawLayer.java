package com.snakegame.snakegame.view;

import com.snakegame.snakegame.model.Board;
import com.snakegame.snakegame.model.CellState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BoardDrawLayer implements DrawLayer {

    private Board board;

    public BoardDrawLayer(Board board) {
        this.board = board;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.LIGHTGREEN);
        g.fillRect(0,0, board.getWidth(), board.getHeight());

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if(board.getCellState(x, y) == CellState.SNAKE) {
                    g.setFill(Color.GREEN);
                    g.fillRect(x, y, 1, 1);
                }
                if(board.getCellState(x, y) == CellState.FOOD) {
                    g.setFill(Color.RED);
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

    }

    @Override
    public int getLayer() {
        return 1;
    }
}
