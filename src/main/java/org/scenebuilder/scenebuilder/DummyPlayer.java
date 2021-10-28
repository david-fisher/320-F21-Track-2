package org.scenebuilder.scenebuilder;

public class DummyPlayer {

    // A temporary player class

    // Temporary player
    private String playerName;
    private boolean isHuman;
    private String color;

    public DummyPlayer(String playerName, boolean isHuman, String color) {
        this.playerName = playerName;
        this.isHuman = isHuman;
        this.color = color;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "DummyPlayer{" +
                "playerName='" + playerName + '\'' +
                ", isHuman=" + isHuman +
                ", color='" + color + '\'' +
                '}';
    }
}
