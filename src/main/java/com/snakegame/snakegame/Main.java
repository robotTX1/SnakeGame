package com.snakegame.snakegame;

import com.snakegame.snakegame.util.event.EventBus;
import com.snakegame.snakegame.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        EventBus eventBus = new EventBus();

        MainView mainView = new MainView(eventBus);
        Scene scene = new Scene(mainView, 500, 500);
        scene.getRoot().requestFocus();
        stage.setTitle("Snake game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}