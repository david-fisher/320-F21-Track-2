package org.scenebuilder.scenebuilder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.Random;

public class DummyGameToken {

    // dummy class, eventually to be replaced by actual game tokens
    private Color tokenColor;
    private String tokenShape;

    public DummyGameToken(String tokenShape) {
        this.tokenShape = tokenShape;
        setRandomTokenColor();
    }

    public void setRandomTokenColor(){
        Random rand = new Random(System.currentTimeMillis());

        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);

        this.tokenColor = Color.rgb(red, green, blue, .99);
    }


    public void setTokenColor(Color tokenColor) {
        this.tokenColor = tokenColor;
    }

    public void setTokenShape() {
        this.tokenShape = tokenShape;
    }


    public Color getTokenColor() {
        return tokenColor;
    }

    public String getTokenShape() {
        return tokenShape;
    }

    public String getTokenHex() {
        Color c = getTokenColor();
        String hex = String.format( "#%02X%02X%02X",
                (int)( c.getRed() * 255 ),
                (int)( c.getGreen() * 255 ),
                (int)( c.getBlue() * 255 ) );

        return hex;
    }

    @Override
    public String toString() {
        return "{ tokenColor = " + tokenColor + ", tokenShape = " + tokenShape + " }";
    }
}
