package com.checkers.figure;

import java.util.HashMap;
import java.util.Map;

public class WhiteFigures {
    private final Map<FigurePositions, Figure> whiteFiguresMap = new HashMap<>();

    public Map<FigurePositions, Figure> setUpFigures() {
        whiteFiguresMap.put(new FigurePositions(1,5), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));
        whiteFiguresMap.put(new FigurePositions(3,5), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));
        whiteFiguresMap.put(new FigurePositions(5,5), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));
        whiteFiguresMap.put(new FigurePositions(7,5), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));

        whiteFiguresMap.put(new FigurePositions(0,6), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));
        whiteFiguresMap.put(new FigurePositions(2,6), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));
        whiteFiguresMap.put(new FigurePositions(4,6), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));
        whiteFiguresMap.put(new FigurePositions(6,6), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));

        whiteFiguresMap.put(new FigurePositions(1,7), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));
        whiteFiguresMap.put(new FigurePositions(3,7), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));
        whiteFiguresMap.put(new FigurePositions(5,7), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));
        whiteFiguresMap.put(new FigurePositions(7,7), new Figure(Figure.Color.WHITE, Figure.Type.NORMAL));

        return whiteFiguresMap;
    }

    public Map<FigurePositions, Figure> getWhiteFiguresMap() {
        return whiteFiguresMap;
    }
}
