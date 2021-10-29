package org.scenebuilder.scenebuilder;

public class Player {

    // object to store Player information
    private String playerName;
    private GameTokenDummy playerToken;
    private boolean isHuman;

    public Player(String playerName, GameTokenDummy playerToken, boolean isHuman) {
        this.playerName = playerName;
        this.playerToken = playerToken;
        this.isHuman = isHuman;
    }


    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerToken(GameTokenDummy playerToken) {
        this.playerToken = playerToken;
    }

    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }


    public String getPlayerName() {
        return playerName;
    }

    public GameTokenDummy getPlayerToken() {
        return playerToken;
    }

    public boolean getIsHuman() {
        return isHuman;
    }
}
