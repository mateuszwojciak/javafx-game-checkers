package com.checkers.board;

import com.checkers.figure.Figure;
import com.checkers.figure.FigurePositions;
import com.checkers.menu.LoadGame;
import com.checkers.menu.MenuDesign;
import com.checkers.menu.NewGame;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

import java.util.Map;
import java.util.Objects;

public class BackgroundGraphics {
    private Board board;

    private BorderPane borderPane = new BorderPane();
    private static GridPane grid = new GridPane();
    private MenuDesign menuDesign = new MenuDesign();
    private Background background;
    private Image imageBoard = new Image("file:src/main/resources/background.jpg");
    private static Image light = new Image("file:src/main/resources/circleLight.png");

    public BackgroundGraphics(Board board) {
        this.board = board;

        createBoardBackground();
        createBoardLayout();

        createFigures();

        borderPane.setCenter(grid);
        borderPane.setTop(menuDesign.getMenuBar());

        menuDesign.getNewGame().setOnAction(e -> new NewGame().start(board));
        menuDesign.getSaveGame().setOnAction(e -> board.getSaveGame().save());
        menuDesign.getLoadGame().setOnAction(e -> new LoadGame().load());
    }

    private void createFigures() {
        for(Map.Entry<FigurePositions, Figure> figures : board.getBoard().entrySet()) {
            addFigure(figures.getKey(), figures.getValue(), false);
        }
    }

    private Background createBoardBackground() {
        BackgroundSize backgroundSize = new BackgroundSize(612,612,true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundImage);
        return background;
    }

    private void createBoardLayout() {
        grid = new GridPane();
        grid.setPadding(new Insets(38,50,38,50));
        grid.setBackground(createBoardBackground());

        for(int i = 0; i < 8; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(64);
            columnConstraints.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(columnConstraints);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(64);
            rowConstraints.setValignment(VPos.CENTER);
            grid.getRowConstraints().add(rowConstraints);
        }

        grid.setGridLinesVisible(false);
    }

    protected static void addFigure(FigurePositions positions, Figure figure, boolean light) {
        grid.add(new ImageView(generateImagePath(figure, light)), positions.getColumn(), positions.getRow());
    }

    protected static void addLightMove(FigurePositions position) {
        grid.add(new ImageView(light), position.getColumn(), position.getRow());
    }

    protected static void removeFigure(FigurePositions position) {
        grid.getChildren().removeIf(node -> node instanceof ImageView && Objects.equals(GridPane.getColumnIndex(node), position.getColumn())
                && Objects.equals(GridPane.getRowIndex(node), position.getRow()));
    }

    private static Image generateImagePath(Figure figure, boolean light) {
        if(light) {
            return new Image(LinkToResources.getPath(figure.getFigureColor() + "_figure_light.png"));
        } else {
            return new Image(LinkToResources.getPath(figure.getFigureColor() + "_figure.png"));
        }
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }
}