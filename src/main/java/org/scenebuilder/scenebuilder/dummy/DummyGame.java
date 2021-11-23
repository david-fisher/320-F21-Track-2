package org.scenebuilder.scenebuilder.dummy;

public class DummyGame {

    // a "fake" game object

    private DummyGameBoard gameBoard;
    private DummyGameRules gameRules;
    private DummyGamestate initialGamestate;

    public DummyGame(DummyGameBoard gameBoard, DummyGameRules gameRules, DummyGamestate initialGameState) {
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
    public void setGameBoard(DummyGameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    public void setGameRules(DummyGameRules gameRules) {
        this.gameRules = gameRules;
    }
    public void setInitialGameState(DummyGamestate gamestate) {
        this.initialGamestate = gamestate;
    }

    // getters
    public DummyGameBoard getGameBoard() {
        return this.gameBoard;
    }
    public DummyGameRules getGameRules() {
        return this.gameRules;
    }
    public DummyGamestate getInitialGamestate() {
        return this.initialGamestate;
    }


}
