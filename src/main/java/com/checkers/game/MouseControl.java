package com.checkers.game;

import com.checkers.board.Board;
import com.checkers.figure.FigurePositions;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class MouseControl {
    private Board board;

    public MouseControl(Board board) {
        this.board = board;
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            FigurePositions clickPosition = new FigurePositions((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 58) / 62));

            if(!clickPosition.isValidPosition()) {
                return;
            }

            board.handleMove(clickPosition);
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }
}
