package org.scenebuilder.scenebuilder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.Random;

public class DummyGameToken {

    private String tokenID;

    private Color tokenColor;
    private String tokenShape;

    public DummyGameToken(String tokenID, Color tokenColor, String tokenShape) {
        this.tokenID = tokenID;
        this.tokenColor = tokenColor;
        this.tokenShape = tokenShape;

    }

    public DummyGameToken(String tokenID, String tokenShape) {

        this.tokenID = tokenID;
        this.tokenShape = tokenShape;

        setRandomTokenColor();
    }

    // setters
    public void setTokenID(String tokenID) { this.tokenID = tokenID; }
    public void setTokenShape() {
        this.tokenShape = tokenShape;
    }
    public void setTokenColor(Color tokenColor) {
        this.tokenColor = tokenColor;
    }

    // getters
    public String getTokenID() { return this.tokenID; }
    public String getTokenShape() {
        return this.tokenShape;
    }
    public Color getTokenColor() {
        return this.tokenColor;
    }
    public String getTokenHex() {
        Color c = getTokenColor();
        String hex = String.format( "#%02X%02X%02X",
                (int)( c.getRed() * 255 ),
                (int)( c.getGreen() * 255 ),
                (int)( c.getBlue() * 255 ) );

        return hex;
    }

    // modifiers
    public void setRandomTokenColor(){
        Random rand = new Random(System.currentTimeMillis());

        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);

        this.tokenColor = Color.rgb(red, green, blue, .99);
    }


}
