package com.snakegame.snakegame.view;

import com.snakegame.snakegame.BoardChangedEvent;
import com.snakegame.snakegame.model.Board;
import com.snakegame.snakegame.util.event.EventBus;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Affine;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CanvasView extends Pane {
    private final Affine affine = new Affine();
    private final Canvas canvas = new Canvas(500, 500);

    private final List<DrawLayer> drawLayers = new LinkedList<>();
    private final Board board;

    public CanvasView(Board board, EventBus eventBus) {
        this.board = board;
        affine.appendScale(canvas.getWidth() / board.getWidth(), canvas.getHeight() / board.getHeight());
        canvas.getGraphicsContext2D().setTransform(affine);
        eventBus.listenFor(BoardChangedEvent.class, this::draw);

        this.getChildren().add(canvas);
    }

    private void draw(BoardChangedEvent event) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        for (DrawLayer drawLayer : drawLayers) {
            drawLayer.draw(g);
        }
    }

    public void addNewDrawLayer(DrawLayer drawLayer) {
        this.drawLayers.add(drawLayer);
        drawLayers.sort(Comparator.comparingInt(DrawLayer::getLayer));
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
//        canvas.setWidth(width);
//        canvas.setHeight(height);
//        affine.appendScale(width / board.getWidth(), height / board.getHeight());
//        canvas.getGraphicsContext2D().setTransform(affine);
    }
}
