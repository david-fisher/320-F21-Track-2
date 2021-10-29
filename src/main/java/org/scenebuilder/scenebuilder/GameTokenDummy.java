package org.scenebuilder.scenebuilder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class GameTokenDummy {

    // dummy class, eventually to be replaced by actual game tokens
    private Color tokenColor;
    private Shape tokenShape;

    public GameTokenDummy(Color tokenColor, Shape tokenShape) {
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

    public Shape getTokenShape() {
        return tokenShape;
    }
}
