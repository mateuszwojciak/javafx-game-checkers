package com.checkers.menu;

import com.checkers.board.Board;
import com.checkers.figure.Figure;
import com.checkers.figure.FigurePositions;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SaveLoadGame {
    private File file = new File("board.list");

    private Map<FigurePositions, Figure> loadBoard = new HashMap<>();
    private Board board;

    public SaveLoadGame(Board board) {
        this.board = board;
    }

    public void saveGame() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(board.getBoard());
            oos.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void loadGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readBoard = ois.readObject();
            if(readBoard instanceof HashMap) {
                loadBoard = (HashMap<FigurePositions, Figure>) readBoard;
            }
            ois.close();
            board.setBoard(loadBoard);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void removeFile() {
        File file = new File("board.list");
        file.delete();
    }

    public boolean isFileExist() {
        File file = new File("board.list");
        return file.exists();
    }
}