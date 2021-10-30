package org.scenebuilder.scenebuilder;

public class DummyPlayer {

    // object to store Player information
    private String playerName;
    private DummyGameToken playerToken;
    private boolean isHuman;

    public DummyPlayer(String playerName, DummyGameToken playerToken, boolean isHuman) {
        this.playerName = playerName;
        this.playerToken = playerToken;
        this.isHuman = isHuman;
    }


    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerToken(DummyGameToken playerToken) {
        this.playerToken = playerToken;
    }

    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }


    public String getPlayerName() {
        return playerName;
    }

    public DummyGameToken getPlayerToken() {
        return playerToken;
    }

    public boolean getIsHuman() {
        return isHuman;
    }

    @Override
    public String toString() {
        return "{ " +
                "playerName = " + playerName +
                ", playerToken = " + playerToken.toString() +
                ", isHuman = " + isHuman +
                " }";
    }
}
