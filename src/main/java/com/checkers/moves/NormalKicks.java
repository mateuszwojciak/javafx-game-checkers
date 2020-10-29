package com.checkers.moves;

import com.checkers.board.Board;
import com.checkers.figure.FigurePositions;

import java.util.HashSet;
import java.util.Set;

public class NormalKicks {
    private Board board;

    private Set<FigurePositions> possibleKickMoves = new HashSet<>();
    private Set<FigurePositions> possibleKicks = new HashSet<>();

    public NormalKicks(Board board) {
        this.board = board;
    }

    public void kickMovesCalculator(FigurePositions position) {
        possibleKickMoves.clear();
        possibleKicks.clear();

        if(kickMove(position, 1, 1)) {
            possibleKickMoves.add(new FigurePositions(position.getColumn() + 2, position.getRow() + 2));
            possibleKicks.add(new FigurePositions(position.getColumn() + 1, position.getRow() + 1));
        }

        if(kickMove(position, - 1, - 1)) {
            possibleKickMoves.add(new FigurePositions(position.getColumn() - 2, position.getRow() - 2));
            possibleKicks.add(new FigurePositions(position.getColumn() - 1, position.getRow() - 1));
        }

        if(kickMove(position, 1, - 1)) {
            possibleKickMoves.add(new FigurePositions(position.getColumn() + 2, position.getRow() - 2));
            possibleKicks.add(new FigurePositions(position.getColumn() + 1, position.getRow() - 1));
        }

        if(kickMove(position, - 1, 1)) {
            possibleKickMoves.add(new FigurePositions(position.getColumn() - 2, position.getRow() + 2));
            possibleKicks.add(new FigurePositions(position.getColumn() - 1, position.getRow() + 1));
        }
    }

    private boolean kickMove(FigurePositions actualPosition, int col, int row) {
        return new FigurePositions(actualPosition.getColumn() + col, actualPosition.getRow() + row).isValidPosition() &&
                !board.isFieldNull(new FigurePositions(actualPosition.getColumn() + col, actualPosition.getRow() + row))
                && new FigurePositions(actualPosition.getColumn() + (col * 2), actualPosition.getRow() + (row * 2)).isValidPosition()
                && board.isFieldNull(new FigurePositions(actualPosition.getColumn() + (col * 2), actualPosition.getRow() + (row * 2)))
                && (board.getFigure(actualPosition).getFigureColor() != board.getFigure(
                new FigurePositions(actualPosition.getColumn() + col, actualPosition.getRow() + row)).getFigureColor());
    }

    public Set<FigurePositions> getPossibleKickMoves() {
        return possibleKickMoves;
    }

    public Set<FigurePositions> getPossibleKicks() {
        return possibleKicks;
    }

    public void clear() {
        possibleKickMoves.clear();
        possibleKicks.clear();
    }
}
