package org.scenebuilder.scenebuilder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class DummyGameToken {

    // dummy class, eventually to be replaced by actual game tokens
    private Color tokenColor;
    private String tokenShape;

    public DummyGameToken(Color tokenColor, String tokenShape) {
        this.tokenColor = tokenColor;
        this.tokenShape = tokenShape;
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

    @Override
    public String toString() {
        return "{ tokenColor = " + tokenColor + ", tokenShape = " + tokenShape + " }";
    }
}
