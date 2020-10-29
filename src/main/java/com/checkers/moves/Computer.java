package com.checkers.moves;

import com.checkers.board.Board;
import com.checkers.figure.FigurePositions;

import java.util.Random;
import java.util.Set;

public class Computer {
    private Random random = new Random();
    private Board board;

    private int difficultyLevel = 0;

    public Computer(Board board) {
        this.board = board;
    }

    public FigurePositions selectPosition(Set<FigurePositions> positions) {
        Object[] object = positions.toArray();
        return (FigurePositions) object[random.nextInt(object.length)];
    }

    public boolean checkBlacksEnd(Set<FigurePositions> restOfBlacks) {
        return restOfBlacks.size() == 0;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }
}
