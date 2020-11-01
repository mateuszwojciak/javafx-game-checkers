package com.checkers;

import com.checkers.board.BackgroundGraphics;
import com.checkers.board.Board;
import com.checkers.game.MouseControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Checkers extends Application {
    private static Stage primaryStage = new Stage();

    private Board board = new Board();
    private BackgroundGraphics graphics = new BackgroundGraphics(board);
    private MouseControl mouseControl = new MouseControl(board);

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Scene scene = new Scene(graphics.getBorderPane(), 612, 612, Color.BLACK);
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseControl.getMouseClick());

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void close() {
        primaryStage.close();
    }
}