package com.snakegame.snakegame.view;

import javafx.scene.canvas.GraphicsContext;

public interface DrawLayer {
    void draw(GraphicsContext g);
    int getLayer();
}
