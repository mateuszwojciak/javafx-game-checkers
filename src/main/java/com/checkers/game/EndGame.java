package com.checkers.game;

import com.checkers.board.Board;
import com.checkers.figure.FigurePositions;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EndGame {
    private Board board;
    private Set<FigurePositions> restOfWhites = new HashSet<>();
    private Set<FigurePositions> restOfBlacks = new HashSet<>();

    public EndGame(Board board) {
        this.board = board;
    }

    public boolean checkEndGame(Set<FigurePositions> positions) {
        calculateFigures(positions);

        if(restOfWhites.size() == 0) {
            board.getRanking().setBlackWins();
            new EndGameInfo(board).blacksWin();
            return true;
        }

        if(restOfBlacks.size() == 0) {
            board.getRanking().setWhiteWins();
            new EndGameInfo(board).whitesWin();
            return true;
        }

        if(restOfWhites.size() == 1 && restOfBlacks.size() == 1) {
            board.getRanking().setDraws();
            new EndGameInfo(board).draw();
            return true;
        }
        return false;
    }


    public void calculateFigures(Set<FigurePositions> positions) {
        restOfWhites.clear();
        restOfBlacks.clear();

        Set<FigurePositions> whites = positions.stream()
                .filter(position -> board.getFigure(position).getFigureColor().isWhite())
                .collect(Collectors.toSet());

        Set<FigurePositions> blacks = positions.stream()
                .filter(position -> board.getFigure(position).getFigureColor().isBlack())
                .collect(Collectors.toSet());

        restOfWhites.addAll(whites);
        restOfBlacks.addAll(blacks);
    }

    public Set<FigurePositions> getRestOfBlacks() {
        return restOfBlacks;
    }

    public Set<FigurePositions> getRestOfWhites() {
        return restOfWhites;
    }
}
