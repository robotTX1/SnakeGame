module com.snakegame.snakegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.snakegame.snakegame to javafx.fxml;
    exports com.snakegame.snakegame;
    exports com.snakegame.snakegame.model;
    exports com.snakegame.snakegame.util.event;
    exports com.snakegame.snakegame.util.observable;
    exports com.snakegame.snakegame.view;
    opens com.snakegame.snakegame.view to javafx.fxml;
}