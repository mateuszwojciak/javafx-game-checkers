package com.checkers.moves;

import com.checkers.board.Board;
import com.checkers.figure.Figure;
import com.checkers.figure.FigurePositions;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NormalMoves {
    private Board board;

    private Set<FigurePositions> possibleMoves = new HashSet<>();
    private Set<FigurePositions> allPossibleBlack = new HashSet<>();

    public NormalMoves(Board board) {
        this.board = board;
    }

    public void normalMoveCalculator(FigurePositions position, boolean up) {
        normalMove(position, possibleMoves, up);
    }

    private void normalMove(FigurePositions actualPosition, Set<FigurePositions> possibleMoves, boolean up) {
        int direction = up ? - 1 : 1;

        FigurePositions left = new FigurePositions(actualPosition.getColumn() - 1, actualPosition.getRow() + direction);
        FigurePositions right = new FigurePositions(actualPosition.getColumn() + 1, actualPosition.getRow() + direction);

        if (left.isValidPosition() && board.isFieldNull(left)) {
            possibleMoves.add(left);
        }

        if (right.isValidPosition() && board.isFieldNull(right)) {
            possibleMoves.add(right);
        }
    }

    public void allPossibleBlackMoves () {
        allPossibleBlack.clear();

        try {
            for (Map.Entry<FigurePositions, Figure> blacks : board.getBoard().entrySet()) {
                if (blacks.getValue().getFigureColor().isWhite()) {
                    continue;
                }

                possibleMoves.clear();

                if (blacks.getValue().getFigureType().isNormal()) {
                    normalMoveCalculator(blacks.getKey(), false);
                    for (FigurePositions position : possibleMoves) {
                        if (position != null && position.isValidPosition()) {
                            allPossibleBlack.add(blacks.getKey());
                        }
                    }
                } else {
                    normalMoveCalculator(blacks.getKey(), true);
                    normalMoveCalculator(blacks.getKey(), false);
                    for (FigurePositions position : possibleMoves) {
                        if (position != null && position.isValidPosition()) {
                            allPossibleBlack.add(blacks.getKey());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e);
        }
    }

    public void movesDifficultyNormal() {
        allPossibleBlack.clear();

        for(Map.Entry<FigurePositions, Figure> blacks : board.getBoard().entrySet()) {
            if(blacks.getValue().getFigureColor().isWhite()) {
                continue;
            }

            possibleMoves.clear();

            if(blacks.getValue().getFigureType().isNormal()) {
                normalMoveCalculator(blacks.getKey(), false);
                for(FigurePositions position : possibleMoves){
                    if(position != null && position.isValidPosition() && blacks.getKey().getRow() == 6) {
                        allPossibleBlack.add(blacks.getKey());
                    }
                }
            } else {
                normalMoveCalculator(blacks.getKey(), true);
                normalMoveCalculator(blacks.getKey(), false);
                for(FigurePositions position : possibleMoves){
                    if(position != null && position.isValidPosition()) {
                        allPossibleBlack.add(blacks.getKey());
                    }
                }
            }
        }
    }

    public Set<FigurePositions> getPossibleMoves() {
        return possibleMoves;
    }

    public Set<FigurePositions> getAllPossibleBlack() {
        return allPossibleBlack;
    }

    public void clear() {
        possibleMoves.clear();
        allPossibleBlack.clear();
    }
}