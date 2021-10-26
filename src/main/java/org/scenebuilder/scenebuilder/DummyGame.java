package org.scenebuilder.scenebuilder;

public class DummyGame {

    // a "fake" game object

    // board attributes
    String shape;
    int width;
    int height;

    String name;

    // game attributes
    int minPlayers;
    int maxPlayers;
    boolean isTutorialMode;

    public DummyGame(String shape, int width, int height, String name, int minPlayers, int maxPlayers, boolean isTutorialMode) {
        this.shape = shape;
        this.width = width;
        this.height = height;
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.isTutorialMode = isTutorialMode;
    }

    // getters
    public String getShape() { return shape; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public String getName() { return name; }
    public int getMinPlayers() { return minPlayers; }
    public int getMaxPlayers() { return maxPlayers; }
    public boolean getIsTutorialMode() { return isTutorialMode; }

    @Override
    public String toString() {
        String gameString = "";

        gameString += "\nShape: " + shape;
        gameString += "\nWidth: " + width;
        gameString += "\nHeight: " + height;
        gameString += "\nName: " + name;
        gameString += "\nMinimum Players: " + minPlayers;
        gameString += "\nMaximum Players: " + maxPlayers;
        gameString += "\nTutorial Mode: " + isTutorialMode;

        return gameString;
    }

    public static void main(String args[]) {

        DummyGame game1 = new DummyGame("square", 150, 100, "Game 1", 2, 8, false);
        //System.out.println(game1);

        DummyGame game2 = new DummyGame("triangle", 200, 150, "Game 2", 1, 4, true);
        //System.out.println(game2);
    }
}
