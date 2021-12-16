package org.GamePlay;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import org.GameObjects.objects.*;


import javafx.scene.input.MouseEvent;
import org.RuleEngine.engine.GameState;
import org.GamePlay.controllers.PlayController;

import java.util.ArrayList;

public class Display extends PlayController {

    private static Display display;

    public static Display getDisplay() {
        if (display == null) { display = new Display(); }
        return display;
    }

    private AnchorPane playParent = super.getPlayParent();
    private AnchorPane boardPane = super.getBoardPane();
    private GameState gameState = super.getGameState();

    private Display() {}

    private final Object KEY = new Object();
    public Tile moveOptions(ArrayList<Tile> tiles) {
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
                Shape parent = (Shape) tile.getParent();
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
            Shape parent = (Shape) tiles.get(i).getParent();
            parent.removeEventHandler(MouseEvent.MOUSE_CLICKED, handler);
            parent.setEffect(null);
            parent.setStrokeWidth(0);
        }
        return chosen;
    }

    public void print(String string) {
        Label displayRoll = new Label(string);
        displayRoll.setPrefWidth(150);
        displayRoll.setPrefHeight(50);
        displayRoll.setStyle("-fx-font-family: Serif; -fx-font-size: 25;");
        displayRoll.setTextFill(Color.BLACK);
        displayRoll.setAlignment(Pos.CENTER);
        playParent.getChildren().add(displayRoll);
        displayRoll.setLayoutX(gameState.getGameBoard().getXPos() + boardPane.getPrefWidth() / 2 + 220);
        displayRoll.setLayoutY(gameState.getGameBoard().getYPos() - 3);

        final Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2800), new KeyValue(displayRoll.opacityProperty(), 0)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2800),
                k -> playParent.getChildren().remove(displayRoll)));
        timeline.play();
    }

    public void updatePiece(Gamepiece gp) {
        Shape parent = (Shape) gp.getParent();
        Tile location = gp.getLocation();
        parent.setLayoutX(location.getXPos() + location.getWidth() / 2);
        parent.setLayoutY(location.getYPos() + location.getHeight() / 2);
        parent.toFront();
    }

    public void updateCurrPlayer(Player player) {
        Label indicator = getPlayerTurnIndicator();
        indicator.setText(player.getLabel() + "'s Turn");
        indicator.setStyle("-fx-border-radius: 5 5 5 5; " +
                "-fx-background-radius: 5 5 5 5; " +
                "-fx-font-family: Serif; " +
                "-fx-font-size: 16; " +
                "-fx-border-color: #000000;");
        fillInventory(player.getInventory());
    }

}
