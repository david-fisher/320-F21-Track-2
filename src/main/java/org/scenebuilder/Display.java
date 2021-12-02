package org.scenebuilder;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.GameObjects.objects.*;


import java.util.ArrayList;

//static methods
public class Display {

    public static void displayDie(int roll) {

    }


    public static Tile displayMoveOptions(ArrayList<Tile> tiles) {
// TODO: This method will be implemented by Minjex.
        if (tiles.size() == 1) {
            return tiles.get(0);
        }
        Tile chosen = null;
        boolean waiting = true;
        //highlight tiles
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            Shape parent = tile.getParent();
            DropShadow borderGlow= new DropShadow();
            borderGlow.setOffsetY(0f);
            borderGlow.setOffsetX(0f);
            borderGlow.setColor(Color.GOLD);
            parent.setEffect(borderGlow);
            parent.setOnMouseClicked(event -> {
                parent.setDisable(true);
            });
        }

        while (waiting) {
            for (int i = 0; i < tiles.size(); i++) {
                Tile tile = tiles.get(i);
                if (tile.getParent().isDisable()) {
                    tile.getParent().setDisable(false);
                    chosen = tile;
                    break;
                }
            }
        }
        return chosen;
    }


    public static void print(String string) {

    }

}
