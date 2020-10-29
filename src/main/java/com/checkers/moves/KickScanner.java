package com.checkers.moves;

import com.checkers.board.Board;
import com.checkers.figure.Figure;
import com.checkers.figure.FigurePositions;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KickScanner {
    private Board board;

    private Set<FigurePositions> allPossibleKicks = new HashSet<>();
    private Set<FigurePositions> allFiguresWhichKick = new HashSet<>();

    public KickScanner(Board board) {
        this.board = board;
    }

    public void calculateAllPossibleWhiteKicks() {
        allPossibleKicks.clear();
        allFiguresWhichKick.clear();

        for (Map.Entry<FigurePositions, Figure> whiteFigure : board.getBoard().entrySet()) {
            if(whiteFigure.getValue().getFigureColor().isWhite() && whiteFigure.getValue().getFigureType().isNormal()){
                int col = whiteFigure.getKey().getColumn();
                int row = whiteFigure.getKey().getRow();

                calculateAllPossibleKicks(whiteFigure.getKey(), col, row + 1, row + 2, row - 1, row - 2);
            }
        }
    }

    public void calculateAllPossibleBlackKicks() {
        allPossibleKicks.clear();
        allFiguresWhichKick.clear();

        for (Map.Entry<FigurePositions, Figure> blackFigure : board.getBoard().entrySet()) {
            if(blackFigure.getValue().getFigureColor().isBlack() && blackFigure.getValue().getFigureType().isNormal()){
                FigurePositions key = blackFigure.getKey();
                int column = key.getColumn();
                int row = key.getRow();

                calculateAllPossibleKicks(key, column, row + 1, row + 2, row - 1, row - 2);
            }
        }
    }

    private void calculateAllPossibleKicks(FigurePositions key, int column, int nextRow, int secondNextRow, int backRow, int secondBackRow) {
        calculatePossibleKicks(key, nextRow, secondNextRow, column - 1, column - 2, column + 1, column + 2);
        calculatePossibleKicks(key, backRow, secondBackRow, column - 1, column - 2, column + 1, column + 2);
    }

    private void calculatePossibleKicks(FigurePositions key, int nextRow, int secondNextRow, int leftCol, int secondLeftCol, int rightCol, int secondRightCol) {
        calculatePossibleKick(key, nextRow, secondNextRow, leftCol, secondLeftCol);
        calculatePossibleKick(key, nextRow, secondNextRow, rightCol, secondRightCol);
    }

    private void calculatePossibleKick(FigurePositions key, int nextRow, int secondNextRow, int rightCol, int secondRightCol) {
        if (new FigurePositions(rightCol, nextRow).isValidPosition()
                && !board.isFieldNull(new FigurePositions(rightCol, nextRow))
                && board.isFieldNull(new FigurePositions(secondRightCol, secondNextRow))
                && new FigurePositions(secondRightCol, secondNextRow).isValidPosition()
                && board.getFigure(new FigurePositions(rightCol, nextRow)).getFigureColor() != board.getFigure(key).getFigureColor()) {

            allPossibleKicks.add(new FigurePositions(rightCol, nextRow));
            allFiguresWhichKick.add(key);
        }
    }

    public void clear() {
        allPossibleKicks.clear();
        allFiguresWhichKick.clear();
    }

    public Set<FigurePositions> getAllPossibleKicks() {
        return allPossibleKicks;
    }

    public Set<FigurePositions> getAllFiguresWhichKick() {
        return allFiguresWhichKick;
    }
}
