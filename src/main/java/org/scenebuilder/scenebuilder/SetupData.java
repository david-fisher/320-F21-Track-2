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
}
