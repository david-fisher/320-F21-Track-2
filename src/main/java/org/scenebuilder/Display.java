package org.scenebuilder;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import org.GameObjects.objects.*;


import javafx.scene.input.MouseEvent;
import org.scenebuilder.controllers.PlayController;

import java.util.ArrayList;

//static methods
public class Display {

    public static void displayDie(int roll) {
//        ImageView dieImage = (ImageView) d;
//        Die die = (Die) dieImage.getUserData();
//        System.out.println(ClassLoader.getResource("Dice1.png"));
//        switch (roll) {
//            case 1:
//                dieImage.setImage(new Image(getClass().getResource("Dice1.png").toString(), die.getWidth(), die.getHeight(), true, true));
//                break;
//            case 2:
//                dieImage.setImage(new Image(getClass().getResource("Dice2.png").toString(), die.getWidth(), die.getHeight(), true, true));
//                break;
//            case 3:
//                dieImage.setImage(new Image(getClass().getResource("Dice3.png").toString(), die.getWidth(), die.getHeight(), true, true));
//                break;
//            case 4:
//                dieImage.setImage(new Image(getClass().getResource("Dice4.png").toString(), die.getWidth(), die.getHeight(), true, true));
//                break;
//            case 5:
//                dieImage.setImage(new Image(getClass().getResource("Dice5.png").toString(), die.getWidth(), die.getHeight(), true, true));
//                break;
//            case 6:
//                dieImage.setImage(new Image(getClass().getResource("Dice6.png").toString(), die.getWidth(), die.getHeight(), true, true));
//                break;
//        }
//    }
//
//        playParent.getChildren().add(diceDisplay);
//        diceDisplay.setLayoutX(playWidth / 2 - 100);
//        diceDisplay.setLayoutY(playHeight / 2 - 90);
//
//    final Timeline timeline = new Timeline();
//        timeline.setAutoReverse(true);
//        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500),
//    k -> playParent.getChildren().remove(diceDisplay)));
//        timeline.play();
    }

    private static final Object KEY = new Object();
    public static Tile moveOptions(ArrayList<Tile> tiles) {
        //Return if there is only one choice
        if (tiles.size() == 1) {
            return tiles.get(0);
        }
        //Create the onClick event for viable tiles
        EventHandler<MouseEvent> handler = event -> {
            if (Platform.isNestedLoopRunning()) {
                Shape parent = (Shape) event.getSource();
                Platform.exitNestedEventLoop(KEY, parent.getUserData());
            }
        };
        //Highlight the viable tiles and add the onClick event
        Runnable runner = () -> {
            for (int i = 0; i < tiles.size(); i++) {
                Tile tile = tiles.get(i);
                Shape parent = tile.getParent();
                parent.setStroke(Color.GOLD);
                parent.setStrokeWidth(2);
                DropShadow borderGlow= new DropShadow();
                borderGlow.setOffsetY(0f);
                borderGlow.setOffsetX(0f);
                borderGlow.setColor(Color.YELLOW);
                parent.setEffect(borderGlow);
                parent.toFront();
                parent.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
            }
        };
        //Run the runner after entering the event loop
        Platform.runLater(runner);
        //Get the viable tile that was chosen by the player
        Tile chosen = (Tile) Platform.enterNestedEventLoop(KEY);
        //Remove the onClick events and highlighting from the viable tiles
        for (int i = 0; i < tiles.size(); i++) {
            Shape parent = tiles.get(i).getParent();
            parent.removeEventHandler(MouseEvent.MOUSE_CLICKED, handler);
            parent.setEffect(null);
            parent.setStrokeWidth(0);
        }
        return chosen;
    }

    public static void highlight(Tile tile, Object KEY) {
        Shape parent = tile.getParent();
        System.out.println("Parent xPos: " + parent.getLayoutX());
        parent.setStroke(Color.LIMEGREEN);
        parent.setStrokeWidth(2);
        DropShadow borderGlow= new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.GOLD);
        parent.setEffect(borderGlow);
        parent.setOnMouseClicked(event -> {
            Platform.exitNestedEventLoop(KEY, parent.getUserData());
        });

    }


    public static void print(String string) {

    }

}
