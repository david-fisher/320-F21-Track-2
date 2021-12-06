package org.scenebuilder.scenebuilder;

import java.util.Arrays;

public class SettingsObject {

    public static final String[] RESOLUTION_STRING_TABLE = { "1280 x 1024 pixels", "1600 x 1200 pixels", "1680 x 1050 pixels", "1900 x 1200 pixels"};
    public static final int[][] RESOLUTION_TABLE = {{1280, 1024}, {1600, 1200}, {1680, 1050}, {1900, 1200}};

    public static final String[] THEME_STRING_TABLE = { "Default", "Dark" };


    private boolean isFullScreen;
    private String windowSize;
    private String theme;

    public SettingsObject() {
        this.isFullScreen = true;
        this.windowSize = RESOLUTION_STRING_TABLE[0];
        this.theme = THEME_STRING_TABLE[0];
    }
    public SettingsObject(boolean isFullScreen, String windowSize, String theme) {
        this.isFullScreen = isFullScreen;
        this.windowSize = windowSize;
        this.theme = theme;
    }

    // setters
    public void setIsFullScreen(boolean isFullScreen) { this.isFullScreen = isFullScreen; }
    public void setWindowSize(String windowSize) { this.windowSize = windowSize; }
    public void setWindowSize(int[] screenDimensions) {

        windowSize = screenDimensions[0] + " x " + screenDimensions[1] + " pixels";
    }
    public void setTheme(String theme) { this.theme = theme; }

    // getters
    public boolean getIsFullScreen() { return this.isFullScreen; }
    public String getWindowSizeString() { return this.windowSize; }
    public int[] getWindowSize() {
        int index = getWindowSizeIndex();

        if(index == -1) {
            return stringToScreenDimensions();
        } else {
            return RESOLUTION_TABLE[index];
        }
    }
    public String getTheme() { return this.theme; }

    public int getWindowSizeIndex() { return Arrays.asList(RESOLUTION_STRING_TABLE).indexOf(this.windowSize); }
    public int getThemeIndex() { return Arrays.asList(THEME_STRING_TABLE).indexOf(this.theme); }

    private int[] stringToScreenDimensions() {

        int[] screenDimensions =  new int[2];

        int indexOfX = this.windowSize.indexOf("x");
        screenDimensions[0] = Integer.parseInt(this.windowSize.substring(0, indexOfX - 1));

        int indexOfP = this.windowSize.indexOf("p");
        screenDimensions[1] = Integer.parseInt(this.windowSize.substring(indexOfX + 2, indexOfP - 1));

        return screenDimensions;
    }
}
