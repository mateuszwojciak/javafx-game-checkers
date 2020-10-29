package com.checkers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuDesign {
    private MenuBar menuBar = new MenuBar();

    private Menu game = new Menu("Game");
    private MenuItem newGame = new MenuItem("New game");
    private MenuItem difficulty = new MenuItem("Difficulty");

    private Menu data = new Menu("Data");
    private MenuItem ranking = new MenuItem("Ranking");
    private MenuItem saveGame = new MenuItem("Save game");
    private MenuItem loadGame = new MenuItem("Load game");

    public MenuDesign() {
        createMenu();

        rankingAction();
    }

    private void createMenu() {
        menuBar.getMenus().add(game);
        game.getItems().add(newGame);
        game.getItems().add(difficulty);

        menuBar.getMenus().add(data);
        data.getItems().add(ranking);
        data.getItems().add(saveGame);
        data.getItems().add(loadGame);

    }

    private void rankingAction() {
        ranking.setOnAction(e -> new Ranking().showRanking());
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public MenuItem getSaveGame() {
        return saveGame;
    }

    public MenuItem getLoadGame() {
        return loadGame;
    }

    public MenuItem getNewGame() {
        return newGame;
    }

    public MenuItem getDifficulty() {
        return difficulty;
    }
}
