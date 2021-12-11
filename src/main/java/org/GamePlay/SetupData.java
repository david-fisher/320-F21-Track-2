package org.GamePlay;

import org.GameObjects.objects.Player;

import java.util.ArrayList;

public class SetupData {

    // object to store setup data

    private ArrayList<Player> playerList = new ArrayList<>();
    private boolean isTutorialMode;

    public SetupData() {

    }

    public SetupData(ArrayList<Player> playerList, boolean isTutorialMode) {
        this.playerList = playerList;
        this.isTutorialMode = isTutorialMode;
    }

    // setters
    public void setPlayerList(ArrayList<Player> playerList) { this.playerList = playerList; }
    public void setIsTutorialMode(boolean isTutorialMode) { this.isTutorialMode = isTutorialMode; }

    // modifiers
    public void addPlayer(Player player) { this.playerList.add(player); }
    public void removePlayer(Player player) { this.playerList.remove(player); }
    public void removePlayer(int i) { this.playerList.remove(i); }
    public void setPlayer(int i, Player player) { this.playerList.set(i, player); }

    // getters
    public ArrayList<Player> getPlayers() { return this.playerList; }
    public Player getPlayer(int i) { return this.playerList.get(i); }
    public boolean isTutorialMode() { return this.isTutorialMode; }

    @Override
    public String toString() {

        String setupDataString = "";

        setupDataString += "Players: \n";
        for(int i = 0; i < playerList.size(); ++i) {
            Player curPlayer = playerList.get(i);
            setupDataString += curPlayer.toString() + '\n';

        }

        setupDataString += "\nTutorial Mode Active: " + isTutorialMode;

        return setupDataString;
    }
}
