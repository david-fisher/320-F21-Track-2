package org.scenebuilder.scenebuilder;

import java.util.ArrayList;

public class SetupData {

    // object to store setup data

    private ArrayList<DummyPlayer> playerList = new ArrayList<>();

    private boolean isTutorialMode;

    public SetupData(ArrayList<DummyPlayer> playerList, boolean isTutorialMode) {
        this.playerList = playerList;
        this.isTutorialMode = isTutorialMode;
    }

    @Override
    public String toString() {

        String setupDataString = "";

        setupDataString += "Players: \n";
        for(int i = 0; i < playerList.size(); ++i) {
            DummyPlayer curPlayer = playerList.get(i);
            setupDataString += curPlayer.toString() + '\n';

        }

        setupDataString += "\nTutorial Mode Active: " + isTutorialMode;

        return setupDataString;
    }
}
