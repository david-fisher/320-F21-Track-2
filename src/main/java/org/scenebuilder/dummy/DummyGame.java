package org.scenebuilder.dummy;

import org.GameObjects.objects.GameBoard;
import org.RuleEngine.engine.GameState;

public class DummyGame {

    // a "fake" game object

    private GameBoard gameBoard;
    private DummyGameRules gameRules;
    private GameState initialGamestate;
    private String name;

    public DummyGame(String name, GameBoard gameBoard, DummyGameRules gameRules, GameState initialGameState) {
        this.name = name;
        this.gameBoard = gameBoard;
        this.gameRules = gameRules;
        this.initialGamestate = initialGameState;
    }

    // copy constructor
    public DummyGame(DummyGame dummyGame) {
        this.gameBoard = dummyGame.getGameBoard();
        this.gameRules = dummyGame.getGameRules();
        this.initialGamestate = dummyGame.getInitialGamestate();
    }

    // setters
    public void setGameName(String name) { this.name = name; }
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    public void setGameRules(DummyGameRules gameRules) {
        this.gameRules = gameRules;
    }
    public void setInitialGameState(GameState gamestate) {
        this.initialGamestate = gamestate;
    }

    // getters
    public String getGameName() {return this.name; }
    public GameBoard getGameBoard() {
        return this.gameBoard;
    }
    public DummyGameRules getGameRules() {
        return this.gameRules;
    }
    public GameState getInitialGamestate() {
        return this.initialGamestate;
    }


}
