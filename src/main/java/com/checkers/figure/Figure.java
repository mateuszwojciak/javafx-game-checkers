package com.checkers.figure;

public class Figure {
    private Color figureColor;
    private Type figureType;

    public Figure(Color figureColor, Type figureType) {
        this.figureColor = figureColor;
        this.figureType = figureType;
    }

    public Color getFigureColor() {
        return figureColor;
    }

    public Type getFigureType() {
        return figureType;
    }

    public enum Color {
        BLACK, WHITE;

        public boolean isWhite() {
            return this == WHITE;
        }

        public boolean isBlack() {
            return this == BLACK;
        }
    }

    public enum Type {
        NORMAL, QUEEN;

        public boolean isNormal() {
            return  this == NORMAL;
        }

        public boolean isQueen() {
            return this == QUEEN;
        }
    }

}
