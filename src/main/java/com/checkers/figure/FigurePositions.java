package com.checkers.figure;


public class FigurePositions {
    private int column;
    private int row;

    public FigurePositions(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public boolean isValidPosition() {
        return column >= 0 && column <=7 && row >= 0 && row <=7;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return  row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return  true;
        if (o == null || getClass() != o.getClass()) return false;

        FigurePositions that = (FigurePositions) o;

        if (column != that.column) return false;
        return  row == that.row;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = result + row;
        return result;
    }
}
