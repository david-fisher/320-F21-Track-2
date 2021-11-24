package org.scenebuilder.scenebuilder;

import java.util.Arrays;

public class SettingsObject {

    public static final String[] RESOLUTION_TABLE = { "1280 x 1024 pixels", "1600 x 1200 pixels", "1680 x 1050 pixels", "1900 x 1200 pixels"};
    public static final String[] THEME_TABLE = { "Default", "Dark" };

    private boolean isFullScreen;
    private String windowSize;
    private String theme;

    public SettingsObject() {
        this.isFullScreen = true;
        this.windowSize = RESOLUTION_TABLE[0];
        this.theme = THEME_TABLE[0];
    }
    public SettingsObject(boolean isFullScreen, String windowSize, String theme) {
        this.isFullScreen = isFullScreen;
        this.windowSize = windowSize;
        this.theme = theme;
    }

    // setters
    public void setIsFullScreen(boolean isFullScreen) { this.isFullScreen = isFullScreen; }
    public void setWindowSize(String windowSize) { this.windowSize = windowSize; }
    public void setTheme(String theme) { this.theme = theme; }

    // getters
    public boolean getIsFullScreen() { return this.isFullScreen; }
    public String getWindowSize() { return this.windowSize; }
    public String getTheme() { return this.theme; }

    public int getWindowSizeIndex() { return Arrays.asList(RESOLUTION_TABLE).indexOf(this.windowSize); }
    public int getThemeIndex() { return Arrays.asList(THEME_TABLE).indexOf(this.theme); }


    public void calculateScreenDimensions() {


    }
}
