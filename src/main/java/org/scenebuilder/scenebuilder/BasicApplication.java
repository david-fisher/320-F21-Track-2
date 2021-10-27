package org.scenebuilder.scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BasicApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        // load fxml file (which specifies the controller)
        Parent root = FXMLLoader.load(getClass().getResource("mainFXML.fxml"));

        // create new instance of the controller class
        // inject all fx:id tagged objects from fxml file
        // and marked with @FXML annotation in controller

        // full screen dimensions
        Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
        double width = screenDimensions.getWidth();
        double height = screenDimensions.getHeight();

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);

        //stage.setMaximized(true);

        stage.show();
    }

    public static ArrayList<DummyGame> getNewGames() {

        // get a list of games which the player can select to start a new game
        ArrayList<DummyGame> newGames = new ArrayList<>();

        // filler for now
        DummyGame game1 = new DummyGame("square", 150, 100, "Game 1", 2, 8, false);
        DummyGame game2 = new DummyGame("triangle", 200, 150, "Game 2", 1, 4, true);

        newGames.add(game1);
        newGames.add(game2);
        newGames.add(game1);
        newGames.add(game2);
        newGames.add(game1);
        newGames.add(game2);
        newGames.add(game1);
        newGames.add(game2);
        newGames.add(game1);
        newGames.add(game2);

        return newGames;
    }

    public static ArrayList<DummyGame> getSavedGames() {

        // get a list of saved games which the player can continue
        ArrayList<DummyGame> savedGames = new ArrayList<>();

        // filler for now
        DummyGame game1 = new DummyGame("square", 150, 100, "Game 1", 2, 8, false);
        DummyGame game2 = new DummyGame("triangle", 200, 150, "Game 2", 1, 4, true);

        savedGames.add(game1);
        savedGames.add(game2);

        return savedGames;
    }

    public static void main(String[] args) {
        launch();
    }
}