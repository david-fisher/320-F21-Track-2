package org.GamePlay;

public class GlobalCSSValues {

//    public Enum<>
//    public static String[] COLOR_PALETTES = {}

    // css
    public static String background;
    public static String secondary;
    public static String accent;

    public static String text;

    public static String buttonBackground;
    public static String buttonText;

    public static void initDefaultPalette() {
        // insert whatever palette should be loaded on startup
        initGreyScalePalette();
        //initPurplePalette();
    }

    public static void initPurplePalette() {

        background = "#544179";
        text = "#B8E4F0";
        accent = "#000000";
        secondary = "#4c0070";
        buttonBackground = "#6166B3";
        buttonText = "#B8E4F0";
    }

    public static void initGreyScalePalette() {

        background = "White";
        text = "Black";
        accent = "Blue";

        secondary = "#f0f0f0";
        buttonBackground = "LightGrey";
        buttonText = "Black";
    }

}
