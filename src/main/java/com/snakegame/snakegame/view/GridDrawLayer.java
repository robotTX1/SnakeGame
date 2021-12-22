package com.snakegame.snakegame.view;

import com.snakegame.snakegame.model.Board;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridDrawLayer implements DrawLayer {
    private final Board board;

    public GridDrawLayer(Board board) {
        this.board = board;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.BLACK);
        g.setLineWidth(0.015);

        for (int i = 0; i < board.getWidth() + 1; i++) {
            g.strokeLine(i, 0, i, board.getHeight());
        }

        for (int i = 0; i < board.getHeight() + 1; i++) {
            g.strokeLine(0, i, board.getWidth(), i);
        }
    }

    @Override
    public int getLayer() {
        return 10;
    }
}
