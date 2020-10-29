package com.checkers.menu;

import com.checkers.board.Board;
import javafx.scene.control.Alert;

public class SaveGame {
    private Board board;

    public SaveGame(Board board) {
        this.board = board;
    }

    public void save() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Game");
        alert.setHeaderText(null);
        alert.setContentText("Game Saved!");

        board.getSaveLoadGame().saveGame();

        alert.showAndWait();
    }
}
