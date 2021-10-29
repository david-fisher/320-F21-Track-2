package org.scenebuilder.scenebuilder;

import javafx.scene.image.Image;

public class DummyGame {

    // a "fake" game object

    // board attributes
    private String shape;
    private int width;
    private int height;

    String name;

    // game attributes
    private int minPlayers;
    private int maxPlayers;
    private boolean isTutorialMode;

    private Image icon;

    public DummyGame(String shape, int width, int height, String name, int minPlayers, int maxPlayers, boolean isTutorialMode, Image icon) {
        this.shape = shape;
        this.width = width;
        this.height = height;
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.isTutorialMode = isTutorialMode;
        this.icon = icon;
    }

    // getters
    public String getShape() { return shape; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public String getName() { return name; }
    public int getMinPlayers() { return minPlayers; }
    public int getMaxPlayers() { return maxPlayers; }
    public boolean getIsTutorialMode() { return isTutorialMode; }
    public Image getIcon() { return icon; }

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

        DummyGame game1 = new DummyGame("square", 150, 100, "Game 1", 2, 8, false, new Image("http://upload.wikimedia.org/wikipedia/commons/1/16/Appearance_of_sky_for_weather_forecast,_Dhaka,_Bangladesh.JPG"));
        //System.out.println(game1);

        DummyGame game2 = new DummyGame("triangle", 200, 150, "Game 2", 1, 4, true, new Image("http://upload.wikimedia.org/wikipedia/commons/1/16/Appearance_of_sky_for_weather_forecast,_Dhaka,_Bangladesh.JPG"));
        //System.out.println(game2);
    }
}
