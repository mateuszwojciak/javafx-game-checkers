package com.checkers.figure;

import java.util.HashMap;
import java.util.Map;

public class BlackFigures {
    private final Map<FigurePositions, Figure> blackFiguresMap = new HashMap<>();

    public Map<FigurePositions, Figure> setUpFigures() {
        blackFiguresMap.put(new FigurePositions(0,0), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));
        blackFiguresMap.put(new FigurePositions(2,0), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));
        blackFiguresMap.put(new FigurePositions(4,0), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));
        blackFiguresMap.put(new FigurePositions(6,0), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));

        blackFiguresMap.put(new FigurePositions(1,1), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));
        blackFiguresMap.put(new FigurePositions(3,1), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));
        blackFiguresMap.put(new FigurePositions(5,1), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));
        blackFiguresMap.put(new FigurePositions(7,1), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));

        blackFiguresMap.put(new FigurePositions(0,2), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));
        blackFiguresMap.put(new FigurePositions(2,2), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));
        blackFiguresMap.put(new FigurePositions(4,2), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));
        blackFiguresMap.put(new FigurePositions(6,2), new Figure(Figure.Color.BLACK, Figure.Type.NORMAL));

        return blackFiguresMap;
    }

    public Map<FigurePositions, Figure> getBlackFiguresMap() {
        return blackFiguresMap;
    }
}
