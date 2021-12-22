package com.snakegame.snakegame.view;

import com.snakegame.snakegame.DirectionChangedEvent;
import com.snakegame.snakegame.controller.Game;
import com.snakegame.snakegame.model.Board;
import com.snakegame.snakegame.model.Direction;
import com.snakegame.snakegame.util.event.EventBus;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {
    private final Board board;

    private final EventBus eventBus;

    public MainView(EventBus eventBus) {
        this.eventBus = eventBus;
        this.setOnKeyPressed(this::handleKeyPressed);

        board = new Board(17, 17);

        Game game = new Game(board, eventBus);
        game.startGame();

        CanvasView canvasView = new CanvasView(board, eventBus);
//        canvasView.addNewDrawLayer(new BoardDrawLayer(board));
        canvasView.addNewDrawLayer(new SpriteDrawLayer(board));
        canvasView.addNewDrawLayer(new GridDrawLayer(board));

        this.setCenter(canvasView);
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                eventBus.emit(new DirectionChangedEvent(Direction.UP));
                break;
            case D:
                eventBus.emit(new DirectionChangedEvent(Direction.RIGHT));
                break;
            case S:
                eventBus.emit(new DirectionChangedEvent(Direction.DOWN));
                break;
            case A:
                eventBus.emit(new DirectionChangedEvent(Direction.LEFT));
                break;
        }
    }
}
