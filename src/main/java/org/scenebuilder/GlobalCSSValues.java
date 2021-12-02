package org.scenebuilder;

public class GlobalCSSValues {

    // css
    public static String background;
    public static String secondary;
    public static String accent;

    public static String text;

    public static String buttonBackground;
    public static String buttonText;

    public static void initDefaultPalette() {
        // insert whatever palette should be loaded on startup
        initPurplePalette();
    }

    public static void initPurplePalette() {

        background = "White";
        text = "Black";
        accent = "Blue";

        secondary = "LightGrey";
        buttonBackground = "LightGrey";
        buttonText = "Black";
    }

}
