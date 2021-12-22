package com.snakegame.snakegame.view;

import com.snakegame.snakegame.model.Board;
import com.snakegame.snakegame.model.CellState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SpriteDrawLayer implements DrawLayer {

    private final Board board;
    private Map<String, Image> sprites = new HashMap<>();

    public SpriteDrawLayer(Board board) {
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
                    Path path = FileSystems.getDefault().getPath("src/main/resources/assets/apple.png").toAbsolutePath();
                    try {
                        InputStream stream = new FileInputStream(path.toFile());
                        g.drawImage(new Image(stream), x, y, 1, 1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
    }

    @Override
    public int getLayer() {
        return 1;
    }
}
