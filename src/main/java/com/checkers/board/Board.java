package com.checkers.board;

import com.checkers.figure.BlackFigures;
import com.checkers.figure.Figure;
import com.checkers.figure.FigurePositions;
import com.checkers.figure.WhiteFigures;
import com.checkers.game.EndGame;
import com.checkers.menu.LoadGame;
import com.checkers.menu.Ranking;
import com.checkers.menu.SaveGame;
import com.checkers.menu.SaveLoadGame;
import com.checkers.moves.Computer;
import com.checkers.moves.KickScanner;
import com.checkers.moves.NormalKicks;
import com.checkers.moves.NormalMoves;

import java.util.*;

public class Board {
    private Map<FigurePositions, Figure> board = new HashMap<>();

    private Set<FigurePositions> possiblePromote = new HashSet<>();

    private BlackFigures blackFigures = new BlackFigures();
    private WhiteFigures whiteFigures = new WhiteFigures();

    private NormalMoves normalMoves = new NormalMoves(this);
    private NormalKicks normalKicks = new NormalKicks(this);

    private KickScanner kickScanner;
    private Computer computer;

    private EndGame endGame = new EndGame(this);
    private Ranking ranking = new Ranking();

    private SaveLoadGame saveLoadGame = new SaveLoadGame(this);
    private SaveGame saveGame = new SaveGame(this);
    private LoadGame loadGame = new LoadGame();

    private boolean turn = true;
    private boolean isKick = false;

    private FigurePositions pickedPosition;

    public Board() {

        if(saveLoadGame.isFileExist()) {
            saveLoadGame.loadGame();
            saveLoadGame.removeFile();
            loadGame.loadInfo();
        } else {

            putAllFigures();
        }

        this.kickScanner = new KickScanner(this);
        this.computer = new Computer(this);
    }

    public void putAllFigures() {
        board.putAll(whiteFigures.setUpFigures());
        board.putAll(blackFigures.setUpFigures());
    }

    public void addFigureToBoard(FigurePositions position, Figure figure) {
        board.put(position, figure);
    }

    public void removeFigureFromBoard(FigurePositions position) {
        board.remove(position);
    }

    public Figure getFigure(FigurePositions position) {
        return board.get(position);
    }

    public boolean isFieldNull(FigurePositions position) {
        return board.get(position) == null;
    }

    private void pickFigure(FigurePositions position, FigurePositions oldPosition, boolean light) {
        Figure figureNew = getFigure(position);
        Figure figureOld = getFigure(oldPosition);

        if(oldPosition != null) {
            BackgroundGraphics.removeFigure(oldPosition);
            BackgroundGraphics.addFigure(oldPosition, figureOld, !light);
        }


        BackgroundGraphics.removeFigure(position);
        BackgroundGraphics.addFigure(position, figureNew, light);
    }

    private void moveFigure(FigurePositions newPosition, FigurePositions oldPosition) {
        Figure figure = getFigure(oldPosition);

        BackgroundGraphics.addFigure(newPosition, figure, false);
        BackgroundGraphics.removeFigure(oldPosition);

        moveFigureOnBoard(newPosition, oldPosition, figure);
    }

    public void moveFigureOnBoard(FigurePositions newPosition, FigurePositions oldPosition, Figure figure) {
        removeFigureFromBoard(oldPosition);
        addFigureToBoard(newPosition, figure);
    }

    private void kickFigure(FigurePositions newPosition, FigurePositions oldPosition) {
        Figure figure = getFigure(oldPosition);

        FigurePositions kickPosition = findOppositePosition(newPosition, normalKicks.getPossibleKicks());

        BackgroundGraphics.addFigure(newPosition, figure, false);
        BackgroundGraphics.removeFigure(oldPosition);
        BackgroundGraphics.removeFigure(kickPosition);

        kickFigureFromBoard(newPosition, oldPosition, kickPosition, figure);

        normalKicks.kickMovesCalculator(newPosition);

        if(!normalKicks.getPossibleKickMoves().isEmpty() && figure.getFigureType().isNormal()) {
            normalKicks.getPossibleKickMoves().forEach(this::addLightMove);

            BackgroundGraphics.removeFigure(oldPosition);
            BackgroundGraphics.addFigure(newPosition, figure, true);
        }
    }

    public void kickFigureFromBoard(FigurePositions newPosition, FigurePositions oldPosition, FigurePositions kickPosition, Figure figure) {
        addFigureToBoard(newPosition, figure);
        removeFigureFromBoard(oldPosition);
        removeFigureFromBoard(kickPosition);
    }

    private void addLightMove(FigurePositions position) {
        BackgroundGraphics.addLightMove(position);
    }

    private void unLightMove(FigurePositions position) {
        BackgroundGraphics.removeFigure(position);
    }

    public FigurePositions findOppositePosition(FigurePositions position, Set<FigurePositions> normalPosition) {
        FigurePositions upLeft = new FigurePositions(position.getColumn() - 1, position.getRow() - 1);

        if(normalPosition.contains(upLeft)) {
            return upLeft;
        }

        FigurePositions downLeft = new FigurePositions(position.getColumn() - 1, position.getRow() + 1);

        if(normalPosition.contains(downLeft)) {
            return downLeft;
        }

        FigurePositions upRight = new FigurePositions(position.getColumn() + 1, position.getRow() - 1);

        if(normalPosition.contains(upRight)) {
            return upRight;
        }

        FigurePositions downRight = new FigurePositions(position.getColumn() + 1, position.getRow() + 1);

        if(normalPosition.contains(downRight)) {
            return downRight;
        }

        return null;
    }

    public void setDifficultyLevel(int difficulty) {
        computer.setDifficultyLevel(difficulty);
    }

    public void handleMove(FigurePositions position) {

        if(turn) {

            kickScanner.calculateAllPossibleWhiteKicks();

            if(!kickScanner.getAllPossibleKicks().isEmpty()) {

                if((kickScanner.getAllFiguresWhichKick().contains(position))
                        && getFigure(position).getFigureColor().isWhite()
                        && !isKick) {

                    pickFigure(position, pickedPosition, true);
                    pickedPosition = position;

                    normalKicks.getPossibleKickMoves().forEach(this::unLightMove);

                    if(getFigure(position).getFigureType().isNormal()) {

                        normalKicks.kickMovesCalculator(position);
                        normalKicks.getPossibleKickMoves().forEach(this::addLightMove);

                    }

                } else {

                    if(normalKicks.getPossibleKickMoves().contains(position)
                            && getFigure(pickedPosition).getFigureType().isNormal()) {

                        normalKicks.getPossibleKickMoves().forEach(this::unLightMove);

                        kickFigure(position, pickedPosition);
                        pickedPosition = position;

                        if(normalKicks.getPossibleKickMoves().isEmpty()) {

                            turn = false;

                            isKick = false;

                            endKick();

                        } else {

                            isKick = true;
                        }
                    }
                }

            } else {

                if(!isFieldNull(position)
                        && getFigure(position).getFigureColor() == Figure.Color.WHITE) {

                    pickFigure(position, pickedPosition,true);
                    pickedPosition = position;

                    normalMoves.getPossibleMoves().forEach(this::unLightMove);

                    normalMoves.clear();

                    if(getFigure(position).getFigureType().isNormal()) {

                        normalMoves.normalMoveCalculator(position, true);
                        normalMoves.getPossibleMoves().forEach(this::addLightMove);

                    }

                } else if(normalMoves.getPossibleMoves().contains(position)
                        && pickedPosition != null) {

                    normalMoves.getPossibleMoves().forEach(this::unLightMove);

                    moveFigure(position, pickedPosition);

                    turn = false;

                    endTurn();

                }
            }
        }

        if(!turn) {

            computerMove();

        }
    }

    private void computerMove() {

        do {

            if(computer.checkBlacksEnd(endGame.getRestOfBlacks())) {
                break;
            }

            kickScanner.calculateAllPossibleBlackKicks();

            if (!kickScanner.getAllPossibleKicks().isEmpty()) {

                Set<FigurePositions> allBlacks = new HashSet<>();

                allBlacks.addAll(kickScanner.getAllFiguresWhichKick());

                FigurePositions computerKick = computer.selectPosition(allBlacks);

                pickedPosition = computerKick;

                pickFigure(computerKick, pickedPosition, true);

                if (getFigure(pickedPosition).getFigureType().isNormal()) {

                    normalKicks.kickMovesCalculator(pickedPosition);

                    if (!normalKicks.getPossibleKickMoves().isEmpty()) {

                        computerKick = computer.selectPosition(normalKicks.getPossibleKickMoves());

                        kickFigure(computerKick, pickedPosition);

                        if (normalKicks.getPossibleKickMoves().isEmpty()) {

                            endKick();

                            setTurn(true);
                        }
                    }
                }

            } else {

                if(computer.getDifficultyLevel() == 1) {

                    normalMoves.movesDifficultyNormal();

                    if(normalMoves.getAllPossibleBlack().isEmpty()) {

                        normalMoves.allPossibleBlackMoves();
                    }

                } else {

                    normalMoves.allPossibleBlackMoves();

                }

                FigurePositions computerMove = computer.selectPosition(normalMoves.getAllPossibleBlack());

                pickedPosition = computerMove;

                if (getFigure(computerMove).getFigureType().isNormal()) {

                    normalMoves.clear();

                    normalMoves.normalMoveCalculator(computerMove, false);

                    computerMove = computer.selectPosition(normalMoves.getPossibleMoves());

                    moveFigure(computerMove, pickedPosition);

                    setTurn(true);

                    endTurn();

                }
            }
        } while(!turn);
    }

    private void endTurn() {
        pickedPosition = null;

        //promote();
        endGame.checkEndGame(getBoard().keySet());

        normalMoves.clear();
        normalKicks.clear();
        kickScanner.clear();
    }

    private void endKick() {
        pickedPosition = null;

        endGame.checkEndGame(getBoard().keySet());

        normalKicks.clear();
        kickScanner.clear();
    }

    public Ranking getRanking() {
        return ranking;
    }

    public SaveLoadGame getSaveLoadGame() {
        return saveLoadGame;
    }

    protected SaveGame getSaveGame() {
        return saveGame;
    }

    public Computer getComputer() {
        return computer;
    }

    public Map<FigurePositions, Figure> getBoard() {
        return board;
    }

    public void setBoard(Map<FigurePositions, Figure> board) {
        this.board = board;
    }

    protected void setTurn(boolean turn) {
        this.turn = turn;
    }
}
